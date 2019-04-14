package com.easybuynet.utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(BaseServlet.class.getName());

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Object object = null;
        try {
            resp.setContentType("text/html;charset=utf-8");
            req.setCharacterEncoding("utf-8");
            String action = req.getParameter("action");
            Class<?> clazz = this.getClass();
            Method method = clazz.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            if (method != null) {
                object = method.invoke(this, req, resp);
                toView(req, resp, object);
            }
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            logger.error(e);
            req.getRequestDispatcher("/shop/404.jsp").forward(req, resp);
        } catch (Exception e) {
            logger.error(e);
            if (object != null) {
                if (object instanceof String) {
                    req.getRequestDispatcher("/shop/500.jsp").forward(req, resp);
                }
            } else {
                e.printStackTrace();
                req.getRequestDispatcher("/shop/500.jsp").forward(req, resp);
            }
        }
    }

    public void toView(HttpServletRequest req, HttpServletResponse resp, Object object) {
        try {
            if (null != object) {
                if (object instanceof String) {
                    String path = String.valueOf(object);
                    if (!"".equals(path)) {
                        req.getRequestDispatcher(path).forward(req, resp);
                    }
                } else {
                    PrintWriter pw = resp.getWriter();
                    pw.write(JSONObject.toJSONString(object));
                    pw.flush();
                    pw.close();
                }
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
    }
}
