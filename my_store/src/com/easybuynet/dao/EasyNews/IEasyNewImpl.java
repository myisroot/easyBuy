package com.easybuynet.dao.EasyNews;

import com.easybuynet.entity.Easy_news;
import com.easybuynet.utils.JdbcUtils;
import com.easybuynet.utils.SqlParam;
import com.easybuynet.utils.SqlUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class IEasyNewImpl implements IEasyNews {
    private QueryRunner qr = JdbcUtils.getQueryRunner();
    private SqlUtil jdbcUtils = JdbcUtils.getSqlUtil();

    /**
     * 查询新闻展示数据
     *
     * @param object
     * @return
     */
    @Override
    public List<Easy_news> getAllNews(Easy_news object) throws SQLException {
        StringBuffer sql = jdbcUtils.getSql(object, SqlParam.SELECT);
        List<Object> sqlParams = jdbcUtils.getSqlParams(object, SqlParam.SELECT);
        return qr.query(sql.toString(), new BeanListHandler<>(Easy_news.class), sqlParams.toArray());
    }

    @Override
    public List<Easy_news> getNews(Easy_news object, Integer curr, Integer pageSize, Boolean flag) throws SQLException {
        StringBuffer sql = jdbcUtils.getSql(object, SqlParam.SELECT);
        List<Object> sqlParams = jdbcUtils.getSqlParams(object, SqlParam.SELECT);
        if (flag) {
            sql.append(" ORDER BY n_id DESC  ");
            sql.append(" limit ? ,? ;");
            sqlParams.add((curr - 1) * pageSize);
            sqlParams.add(pageSize);
        }
        return qr.query(sql.toString(), new BeanListHandler<>(Easy_news.class), sqlParams.toArray());
    }

    @Override
    public int delNews(Easy_news object) throws SQLException {
        StringBuffer sql = jdbcUtils.getSql(object, SqlParam.DELETE);
        List<Object> sqlParams = jdbcUtils.getSqlParams(object, SqlParam.DELETE);
        return qr.update(sql.toString(), sqlParams.toArray());
    }
}
