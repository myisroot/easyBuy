package com.easybuynet.dao.EasyOrder;

import com.easybuynet.entity.Easy_order;
import com.easybuynet.utils.JdbcUtils;
import com.easybuynet.utils.SqlParam;
import com.easybuynet.utils.SqlUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class IEasyOrderImpl implements IEasyOrder {
    private QueryRunner qr = JdbcUtils.getQueryRunner();
    private SqlUtil sqlUtil = new SqlUtil();

    /**
     * 添加订单
     *
     * @param easy_order
     * @return
     * @throws SQLException
     */
    @Override
    public int saveOrder(Easy_order easy_order) {
        StringBuffer sql = sqlUtil.getSql(easy_order, SqlParam.INSERT);
        List<Object> sqlParams = sqlUtil.getSqlParams(easy_order, SqlParam.INSERT);
        return JdbcUtils.getPrimaryKey(sql.toString(), sqlParams.toArray());
    }

    /**
     * @param easy_order 实体类查询条件
     * @param curr       当前页
     * @param pageSize   页面大小
     * @param flag       是否分页
     * @return
     * @throws SQLException
     */
    @Override
    public List<Easy_order> getOrderByUid(Easy_order easy_order, Integer curr, Integer pageSize, Boolean flag) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(easy_order, SqlParam.SELECT);
        List<Object> sqlParams = sqlUtil.getSqlParams(easy_order, SqlParam.SELECT);
        if (flag) {
            sql.append(" ORDER BY o_id DESC  ");
            sql.append(" limit ? ,?;");
            sqlParams.add((curr - 1) * pageSize);
            sqlParams.add(pageSize);
        }
        return qr.query(sql.toString(), new BeanListHandler<>(Easy_order.class), sqlParams.toArray());
    }

    @Override
    public int delOrder(Easy_order easy_order) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(easy_order, SqlParam.DELETE);
        List<Object> sqlParams = sqlUtil.getSqlParams(easy_order, SqlParam.DELETE);
        return qr.update(sql.toString(), sqlParams.toArray());
    }

    @Override
    public List<Easy_order> getOrderByUid(Easy_order easy_order) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(easy_order, SqlParam.SELECT);
        List<Object> sqlParams = sqlUtil.getSqlParams(easy_order, SqlParam.SELECT);
        return qr.query(sql.toString(), new BeanListHandler<>(Easy_order.class), sqlParams.toArray());
    }
}
