package com.easybuynet.web.admin;

import com.easybuynet.entity.*;
import com.easybuynet.service.User.IUserService;
import com.easybuynet.service.User.IUserServiceImpl;
import com.easybuynet.utils.BaseServlet;
import com.easybuynet.utils.Md5;
import com.easybuynet.web.pre.SessionMap;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@WebServlet("/admin_login")
public class Login extends BaseServlet {
    private Logger logger = Logger.getLogger(Login.class.getName());
    private IUserService ius = null;

    @Override
    public void init() throws ServletException {
        ius = new IUserServiceImpl();
    }

    /**
     * 验证是否登入成功
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public Result login(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Result result = new Result();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User u = new User();
        u.setU_loginname(username);
        u.setU_pwd(Md5.toMD5(password));
        u.setU_islogin(Constants.ISLOGIN);
        User user = ius.login(u);
        if (null != user) {
            HttpSession session = request.getSession();
            SessionMap.putSession(session, user);
            session.setAttribute("admin_user", user);
            session.setAttribute("ISLOGIN", Constants.ISLOGIN);
            session.setAttribute("NOLOGIN", Constants.NOLOGIN);
            session.setAttribute("ROOT", Constants.ROOT);
            session.setAttribute("NOREMOVE", Constants.NOREMOVE);
            session.setAttribute("ISREMOVE", Constants.ISREMOVE);
            session.setAttribute("NOADD", Constants.NOADD);
            session.setAttribute("ISADD", Constants.ISADD);
            session.setAttribute("NOUPDATE", Constants.NOUPDATE);
            session.setAttribute("ISUPDATE", Constants.ISUPDATE);
            return result.Success();
        }
        return result.Fail("用户名或者密码错误");
    }


}
