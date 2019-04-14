package com.easybuynet.web.admin;

import com.easybuynet.entity.Constants;
import com.easybuynet.entity.PageBean;
import com.easybuynet.entity.Result;
import com.easybuynet.entity.User;
import com.easybuynet.service.User.IUserService;
import com.easybuynet.service.User.IUserServiceImpl;
import com.easybuynet.utils.BaseServlet;
import com.easybuynet.utils.Md5;
import com.easybuynet.utils.Tools;
import com.easybuynet.web.Common;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet("/admin_user")

public class AdminUser extends BaseServlet {
    private Logger logger = Logger.getLogger(AdminUser.class.getName());
    private IUserService iUserService = null;

    @Override
    public void init() throws ServletException {
        iUserService = new IUserServiceImpl();
    }

    /**
     * 获取管理员用户和普通用户
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String getAdminUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String flag = request.getParameter("flag");
        String path = "";
        User user = new User();
        //页面大小
        Integer pageSize = 6;
        PageBean pageBean = new PageBean();
        //获取当前页
        String currPaNo = request.getParameter("currPaNo");
        String uName = request.getParameter("uName");
        Integer curr = currPaNo == null || "".equals(currPaNo.trim()) ? 1 : Integer.parseInt(currPaNo);
        if ("true".equals(flag)) {
            user.setU_isadmin(Constants.USER);
            path = "/admin/admin_user.jsp";
        } else {
            user.setU_isadmin(Constants.ROOT);
            path = "/admin/admin_admin.jsp";
        }
        if (null != uName && !"".equals(uName.trim())) {
            String u_name = uName;
            user.setU_loginname(Tools.toFuzzyParam(u_name));
            request.setAttribute("uName", u_name);
        }
        //获取分页数据
        List<User> allUser = iUserService.getAllUser(user, curr, pageSize);
        int allUserCount = iUserService.getAllUserCount(user);
        pageBean.setAllCounts(allUserCount);
        pageBean.setPageSize(pageSize);
        pageBean.setCurrPaNo(curr);
        request.setAttribute("curr", currPaNo);
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("allUser", allUser);
        return path;
    }

    /**
     * 设置权限
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public Result checkLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String uid = request.getParameter("uid");
        String checkType = request.getParameter("checkType");
        String aClass = request.getParameter("class");
        return iUserService.checkUser(uid, checkType, aClass);
    }

    /**
     * 删除管理员
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public Result delAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        User user = Common.getUserFormSession(request, "admin_user");
        Result result = new Result();
        String uid = request.getParameter("uid");
        if (!Constants.ROOT.equals(user.getU_isadmin())) {
            return result.Fail("请联系超级管理员删除");
        }
        if (null == uid || "".equals(uid)) {
            return result.Fail("发生异常");
        }
        User u = new User();
        u.setU_id(Integer.parseInt(uid));
        int i = iUserService.delUserById(u);
        if (i < 0) {
            return result.Fail("删除失败");
        }
        return result.Success();
    }

    /**
     * 添加管理员
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public Result addAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Result result = new Result();
        User user = new User();
        String adminName = request.getParameter("adminName");
        String adminPwd = request.getParameter("adminPwd");
        user.setU_loginname(adminName);
        //验证用户名是否存在
        User u = iUserService.getUserById(user);
        if (null != u) {
            return result.Fail("用户名已存在");
        }
        //添加
        User user1 = new User();
        user1.setU_loginname(adminName);
        user1.setU_createtime(Tools.format(new Date()));
        user1.setU_isadmin(Constants.ADMIN);
        user1.setU_pwd(Md5.toMD5(adminPwd));
        int i = iUserService.addUser(user1);
        if (i < 0) {
            return result.Fail("服务器繁忙");
        }
        return result.Success();
    }

    public Result editAdminPwd(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Result result = new Result();
        String uid = request.getParameter("uid");
        String newPwd = request.getParameter("newPwd");
        if (null == uid || "".equals(uid)) {
            return result.Fail("服务器繁忙");
        }
        User u = new User();
        u.setU_id(Integer.parseInt(uid));
        User user = iUserService.getUserById(u);
        if (user.getU_pwd().equals(Md5.toMD5(newPwd))) {
            return result.Fail("新密码与原密码相同");
        }
        int i = iUserService.editPwdById(Md5.toMD5(newPwd), Integer.parseInt(uid));
        if (i > 0) {
            return result.Success();
        }
        return result.Fail("发生异常");
    }

    /**
     * 退出
     *
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    public Result exit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("admin_user");
        return new Result().Success();
    }

}
