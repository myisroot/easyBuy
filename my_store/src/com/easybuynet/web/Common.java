package com.easybuynet.web;

import com.easybuynet.entity.User;
import com.easybuynet.service.User.IUserService;
import com.easybuynet.service.User.IUserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class Common {
    private static IUserService iUserService = new IUserServiceImpl();

    public static User getUserFormSession(HttpServletRequest request, String name) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(name.trim());
        try {
            Integer u_id = user.getU_id();
            User u = new User();
            u.setU_id(u_id);
            return iUserService.getUserById(u);
        } catch (SQLException e) {
            e.printStackTrace();
            return user;
        }
    }
}
