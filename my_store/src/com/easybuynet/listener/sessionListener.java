package com.easybuynet.listener;

import com.easybuynet.entity.User;
import com.easybuynet.web.pre.SessionMap;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class sessionListener implements HttpSessionListener {

    public sessionListener() {

    }


    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("create session");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        User user = (User) se.getSession().getAttribute("user");
        if (null != user) {
            SessionMap.moveUserSession(user);
        } else {
            user = (User) se.getSession().getAttribute("admin_user");
            SessionMap.moveAdminSession(user);
        }
        System.out.println("remove session");
    }

}
