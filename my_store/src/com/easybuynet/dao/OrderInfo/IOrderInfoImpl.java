package com.easybuynet.dao.OrderInfo;

import com.easybuynet.entity.Order_info;
import com.easybuynet.utils.JdbcUtils;
import com.easybuynet.utils.SqlParam;
import com.easybuynet.utils.SqlUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class IOrderInfoImpl implements IOrderInfo {
    private QueryRunner qr = JdbcUtils.getQueryRunner();
    private SqlUtil jdbcUtils = JdbcUtils.getSqlUtil();

    @Override
    public List<Order_info> getOrderInfoByOid(Integer oid) throws SQLException {
        Order_info orderInfo = new Order_info();
        orderInfo.setO_id(oid);
        StringBuffer sql = jdbcUtils.getSql(orderInfo, SqlParam.SELECT);
        List<Object> sqlParams = jdbcUtils.getSqlParams(orderInfo, SqlParam.SELECT);
        return qr.query(sql.toString(), new BeanListHandler<>(Order_info.class), sqlParams.toArray());
    }
}
