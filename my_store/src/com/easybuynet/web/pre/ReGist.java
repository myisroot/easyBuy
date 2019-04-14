package com.easybuynet.web.pre;

import com.easybuynet.entity.Constants;
import com.easybuynet.entity.Result;
import com.easybuynet.entity.User;
import com.easybuynet.service.User.IUserService;
import com.easybuynet.service.User.IUserServiceImpl;
import com.easybuynet.utils.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@WebServlet("/login")
public class ReGist extends BaseServlet {
    private static Logger logger = Logger.getLogger(ReGist.class.getName());

    //创建对象
    private IUserService ius = null;

    /**
     * 初始化对象
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        ius = new IUserServiceImpl();
    }


    /**
     * 判断验证码是否输入正确
     *
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    public String testReGist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter pw = resp.getWriter();
        if (String.valueOf(req.getSession().getAttribute("code")).equalsIgnoreCase(req.getParameter("code"))) {
            pw.write("true");
        } else {
            pw.write("false");
        }
        return null;
    }

    /**
     * 添加用户 注册
     *
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    public String addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Map<String, String[]> map = req.getParameterMap();
            User user = new User();
            BeanUtils.populate(user, map);
            user.setU_isadmin(Constants.USER);
            user.setU_createtime(Tools.format(new Date()));
            //加密
            user.setU_pwd(Md5.toMD5(user.getU_pwd()));
            int i = ius.addUser(user);
            if (i > 0) {
                logger.info("注册成功");
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        } finally {
            resp.sendRedirect(req.getContextPath() + "/shop/Login.jsp");
        }
        return null;
    }

    /**
     * 判断用户名是否存在
     *
     * @param req
     * @param resp
     * @return
     */
    public String testName(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Boolean flag = ius.getUserByName(req.getParameter("u_loginname"));
            if (!flag) {
                resp.getWriter().write("false");
            }
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证登入
     *
     * @param req
     * @param resp
     * @return
     */
    public String login(HttpServletRequest req, HttpServletResponse resp) {
        try {
            PrintWriter pw = resp.getWriter();
            //查询用户是否存在
            User user = ius.login(req.getParameter("u_loginname"), Md5.toMD5(req.getParameter("u_pwd")));
            if (user != null) {
                pw.write("true");
                req.getSession().setAttribute("user", user);
                SessionMap.putSession(req.getSession(), user);
                logger.info("登入成功");
                if (null == req.getParameter("flag")) {
                    //判断是否需要转发
                    resp.sendRedirect(req.getContextPath() + "/Home?action=index");
                }
            } else {
                pw.write("false");
            }
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 退出登入
     *
     * @param req
     * @param resp
     * @return
     */
    public String exit(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getSession().removeAttribute("user");
            resp.sendRedirect(req.getContextPath() + "/shop/Login.jsp");
            logger.info("退出成功");
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return null;
    }

    public Result getEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = Tools.getCode(6);
        HttpSession session = request.getSession();
        Result result = new Result();
        String email = request.getParameter("email");
        boolean flag = getInfoCode.sendCode(email, code, "146551", "27926d7cce567cfaf2ce2933c29aa377");
        logger.debug("发送验证码" + flag);
        if (flag) {
            session.setAttribute("email", email);
            session.setAttribute("code", code);
            session.setAttribute("time", System.currentTimeMillis());
            return result.Success();
        }
        return result.Fail("发送失败");
    }

    public Result updatePwd(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String code = request.getParameter("code");
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        Result result = new Result();
        HttpSession session = request.getSession();
        long newTime = System.currentTimeMillis() / 1000;
        Long time = (Long) session.getAttribute("time");
        String oldEmail = (String) session.getAttribute("email");
        String oldCode = (String) session.getAttribute("code");
        session.getAttribute("");
        if (newTime - time / 1000 > 120) {
            return result.Fail("验证码已过期");
        }
        if (!oldEmail.equals(email)) {
            return result.Fail("两次手机号请保持一致");
        }
        if (!oldCode.equalsIgnoreCase(code)) {
            return result.Fail("验证码错误");
        }
        int i = ius.updatePwdByEmail(email, pwd);
        if (i <= 0) {
            return result.Fail("请确认手机号与注册时的手机号一致");
        }
        session.removeAttribute("time");
        session.removeAttribute("email");
        session.removeAttribute("code");
        return result.Success();
    }
}
