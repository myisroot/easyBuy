package com.easybuynet.dao.OrderAddress;

import com.easybuynet.entity.Easy_order;
import com.easybuynet.entity.Order_address;

import java.sql.SQLException;
import java.util.List;

public interface IOrderAddressDao {
    /**
     * @param easy_order 实体类查询条件
     * @param curr       当前页
     * @param pageSize   页面大小
     * @param flag       是否分页
     * @return
     * @throws SQLException
     */
    List<Order_address> getOrderByUid(Order_address easy_order, Integer curr, Integer pageSize, Boolean flag) throws SQLException;

}
