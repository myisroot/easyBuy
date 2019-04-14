package com.easybuynet.filter;

import com.easybuynet.entity.User;

import javax.servlet.*;
import java.io.IOException;

public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
