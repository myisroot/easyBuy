package com.easybuynet.web.admin;

import com.easybuynet.entity.*;
import com.easybuynet.service.EasyNewsService.EasyNewsServiceImpl;
import com.easybuynet.service.EasyNewsService.IEasyNewsService;
import com.easybuynet.utils.BaseServlet;
import com.easybuynet.web.Common;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.sql.SQLException;

@WebServlet("/admin_news")

public class News extends BaseServlet {

    private IEasyNewsService iEasyNewsService = null;

    @Override
    public void init() throws ServletException {
        iEasyNewsService = new EasyNewsServiceImpl();
    }

    /**
     * 获取所有新闻
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String getNews(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Integer pageSize = 6;
        Easy_news easyNews = new Easy_news();
        String currPaNo = request.getParameter("currPaNo");
        String title = request.getParameter("title");
        easyNews.setN_title(title);
        PageBean pageBean = iEasyNewsService.getNew(easyNews, currPaNo, pageSize);
        //获取商品分页数据
        request.setAttribute("title", title);
        request.setAttribute("news", pageBean.getData());
        request.setAttribute("curr", pageBean.getCurrPaNo());
        request.setAttribute("pageBean", pageBean);
        return "/admin/easy_news.jsp";
    }


    /**
     * 删除新闻
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public Result delNews(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String nid = request.getParameter("nid");
        User user = Common.getUserFormSession(request,"admin_user");
        return iEasyNewsService.delNew(nid, user);
    }
}
