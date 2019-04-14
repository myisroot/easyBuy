package com.easybuynet.service.EasyNewsService;

import com.easybuynet.entity.Easy_news;
import com.easybuynet.entity.PageBean;
import com.easybuynet.entity.Result;
import com.easybuynet.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IEasyNewsService {
    //获取所有新闻
    List<Easy_news> getAllNews(Easy_news easy_news) throws SQLException;

    List<Easy_news> getNews(Easy_news easy_news, Integer curr, Integer pageSize) throws SQLException;

    int getNewsCount(Easy_news easy_news) throws SQLException;

    Result delNew(String nid, User user) throws SQLException;

    PageBean getNew(Easy_news easy_news, String curr, Integer pageSize) throws SQLException;
}
