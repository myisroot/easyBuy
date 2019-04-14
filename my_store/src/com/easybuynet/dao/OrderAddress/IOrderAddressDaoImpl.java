package com.easybuynet.dao.OrderAddress;

import com.easybuynet.entity.Easy_order;
import com.easybuynet.entity.Order_address;
import com.easybuynet.utils.JdbcUtils;
import com.easybuynet.utils.SqlParam;
import com.easybuynet.utils.SqlUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class IOrderAddressDaoImpl implements IOrderAddressDao {
    private QueryRunner qr = JdbcUtils.getQueryRunner();
    private SqlUtil sqlUtil = new SqlUtil();

    @Override
    public List<Order_address> getOrderByUid(Order_address order_address, Integer curr, Integer pageSize, Boolean flag) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(order_address, SqlParam.SELECT);
        List<Object> sqlParams = sqlUtil.getSqlParams(order_address, SqlParam.SELECT);
        if (flag) {
            sql.append(" ORDER BY o_id DESC  ");
            sql.append(" limit ? ,?;");
            sqlParams.add((curr - 1) * pageSize);
            sqlParams.add(pageSize);
        }
        return qr.query(sql.toString(), new BeanListHandler<>(Order_address.class), sqlParams.toArray());
    }
}
