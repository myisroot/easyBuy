package com.easybuynet.web.pre;

import com.easybuynet.entity.User;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionMap {
    static Map<Integer, HttpSession> sessionMap = new HashMap<>();

    public static synchronized void putSession(HttpSession httpSession, User user) {
        sessionMap.put(user.getU_id(), httpSession);
    }

    public static synchronized void moveUserSession(User user) {
        HttpSession session = sessionMap.get(user.getU_id());
        if (null != session) {
            session.removeAttribute("user");
        }
        sessionMap.remove(user.getU_id());
    }

    public static synchronized void moveAdminSession(User user) {
        HttpSession session = sessionMap.get(user.getU_id());
        if (null != session) {
            session.removeAttribute("admin_user");
        }
        sessionMap.remove(user.getU_id());
    }

    public static synchronized void destroyedSession(User user) {
        HttpSession session = sessionMap.get(user.getU_id());
        session.invalidate();
    }

    public static void show() {
        for (Map.Entry<Integer, HttpSession> entry : sessionMap.entrySet()) {
            System.out.println(entry.getKey() + "===" + entry.getValue());
        }
    }
}
