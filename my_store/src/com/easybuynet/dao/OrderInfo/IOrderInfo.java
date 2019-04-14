package com.easybuynet.dao.OrderInfo;

import com.easybuynet.entity.Order_info;

import java.sql.SQLException;
import java.util.List;

public interface IOrderInfo {
    //根据订单id查询订单详细信息
    List<Order_info> getOrderInfoByOid(Integer oid) throws SQLException;
}
