package com.easybuynet.dao.EasyOrder;

import com.easybuynet.entity.Easy_order;

import java.sql.SQLException;
import java.util.List;

public interface IEasyOrder {
    /**
     * 添加订单
     *
     * @param easy_order
     * @return
     * @throws SQLException
     */
    int saveOrder(Easy_order easy_order);

    /**
     * @param easy_order 实体类查询条件
     * @param curr       当前页
     * @param pageSize   页面大小
     * @param flag       是否分页
     * @return
     * @throws SQLException
     */
    List<Easy_order> getOrderByUid(Easy_order easy_order, Integer curr, Integer pageSize, Boolean flag) throws SQLException;

    /**
     * 删除订单
     *
     * @param easy_order
     * @return
     * @throws SQLException
     */
    int delOrder(Easy_order easy_order) throws SQLException;


    List<Easy_order> getOrderByUid(Easy_order easy_order) throws SQLException;
}
