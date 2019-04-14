package com.easybuynet.web.admin;

import com.easybuynet.entity.User;
import com.easybuynet.service.User.IUserService;
import com.easybuynet.service.User.IUserServiceImpl;
import com.easybuynet.utils.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/chat")

public class Chat extends BaseServlet {

    private IUserService iUserService=null;
    @Override
    public void init() throws ServletException {
        iUserService=new IUserServiceImpl();
    }

    public String showChat(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        User user=new User();
        List<User> allUser = iUserService.getAllUser(user);
        request.setAttribute("allUser",allUser);
        return "/chat/demo.jsp";
    }
}
