package com.easybuynet.dao.EasyOrderDetail;

import com.easybuynet.entity.Easy_order_detail;
import com.easybuynet.utils.JdbcUtils;
import com.easybuynet.utils.SqlParam;
import com.easybuynet.utils.SqlUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class IEasyOrderDetailImpl implements IEasyOrderDetail {
    private QueryRunner qr = JdbcUtils.getQueryRunner();
    private SqlUtil sqlUtil = new SqlUtil();

    /**
     * 添加订单详情
     *
     * @param easy_order_detail
     * @return
     */
    @Override
    public int saveOrderDetail(Easy_order_detail easy_order_detail) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(easy_order_detail, SqlParam.INSERT);
        List<Object> sqlParams = sqlUtil.getSqlParams(easy_order_detail, SqlParam.INSERT);
        return qr.update(sql.toString(), sqlParams.toArray());
    }

    @Override
    public List<Easy_order_detail> getAllByOid(Easy_order_detail easy_order_detail) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(easy_order_detail, SqlParam.SELECT);
        List<Object> sqlParams = sqlUtil.getSqlParams(easy_order_detail, SqlParam.SELECT);
        return qr.query(sql.toString(), new BeanListHandler<>(Easy_order_detail.class), sqlParams.toArray());
    }

    @Override
    public int delOrderInfoByOid(Easy_order_detail easy_order_detail) throws SQLException {
        StringBuffer sql = new StringBuffer("DELETE FROM Easy_order_detail WHERE o_id=?;");
        return qr.update(sql.toString(), easy_order_detail.getO_id());
    }
}
