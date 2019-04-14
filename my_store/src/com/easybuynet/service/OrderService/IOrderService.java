package com.easybuynet.service.OrderService;

import com.easybuynet.entity.*;
import com.easybuynet.utils.Cart;
import com.easybuynet.utils.OrderVo;

import java.sql.SQLException;
import java.util.List;

public interface IOrderService {
    //生成订单
    Easy_order addOrder(User user, String address, Cart cart, Integer aid);

    //删除订单
    int delOrder(Easy_order easy_order) throws SQLException;

    //获取订单以及商品
    List<OrderVo> getOrderVo(Order_address easy_order, Integer curr, Integer pageSize, Boolean flag) throws SQLException;

    //获取订单数量
    int getOrderCount(Order_address easy_order) throws SQLException;

    //根据订单id查询订单信息
    List<Order_info> getOrderInfoByOid(Integer oid) throws SQLException;

    //根据用户查询订单以及分页
    List<Order_address> getOrder(Order_address easy_order, Integer curr, Integer pageSize, Boolean flag) throws SQLException;
}
