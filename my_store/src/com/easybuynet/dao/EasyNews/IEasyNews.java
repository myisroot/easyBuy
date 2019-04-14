package com.easybuynet.dao.EasyNews;

import com.easybuynet.entity.Easy_news;

import java.sql.SQLException;
import java.util.List;

public interface IEasyNews {
    //获取所有新闻
    List<Easy_news> getAllNews(Easy_news object) throws SQLException;

    //分页获取新闻
    List<Easy_news> getNews(Easy_news object, Integer curr, Integer pageSize, Boolean flag) throws SQLException;

    //删除新闻
    int delNews(Easy_news object) throws SQLException;
}
