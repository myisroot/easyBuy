package com.easybuynet.utils;

import com.easybuynet.entity.Easy_order;
import com.easybuynet.entity.Order_address;
import com.easybuynet.entity.Order_info;

import java.util.List;

public class OrderVo {
    private Order_address order;
    private List<Order_info> details;

    public Order_address getOrder() {
        return order;
    }

    public void setOrder(Order_address order) {
        this.order = order;
    }

    public List<Order_info> getDetails() {
        return details;
    }

    public void setDetails(List<Order_info> details) {
        this.details = details;
    }
}