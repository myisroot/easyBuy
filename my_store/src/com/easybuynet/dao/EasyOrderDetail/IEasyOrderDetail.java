package com.easybuynet.dao.EasyOrderDetail;

import com.easybuynet.entity.Easy_order_detail;

import java.sql.SQLException;
import java.util.List;

public interface IEasyOrderDetail {
    /**
     * 添加订单详情
     *
     * @param easy_order_detail
     * @return
     */
    int saveOrderDetail(Easy_order_detail easy_order_detail) throws SQLException;

    /**
     * 根据订单id查询订单详细信息
     *
     * @param easy_order_detail 实体类自动生成查询条件
     * @return
     * @throws SQLException
     */
    List<Easy_order_detail> getAllByOid(Easy_order_detail easy_order_detail) throws SQLException;

    /**
     * 删除订单信息
     *
     * @param easy_order_detail
     * @return
     * @throws SQLException
     */
    int delOrderInfoByOid(Easy_order_detail easy_order_detail) throws SQLException;
}
