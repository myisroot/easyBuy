package com.easybuynet.entity;

import java.io.Serializable;

public class Easy_order_detail implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer d_id;
    private Integer o_id;
    private Integer p_id;
    private Integer d_quantity;
    private String d_cost;
    private String p_name;

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public void setD_id(Integer d_id) {
        this.d_id = d_id;
    }

    public Integer getD_id() {
        return d_id;
    }

    public void setO_id(Integer o_id) {
        this.o_id = o_id;
    }

    public Integer getO_id() {
        return o_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setD_quantity(Integer d_quantity) {
        this.d_quantity = d_quantity;
    }

    public Integer getD_quantity() {
        return d_quantity;
    }

    public void setD_cost(String d_cost) {
        this.d_cost = d_cost;
    }

    public String getD_cost() {
        return d_cost;
    }

    @Override
    public String toString() {
        return "Easy_order_detail{" +
                "d_id=" + d_id +
                ", o_id=" + o_id +
                ", p_id=" + p_id +
                ", d_quantity=" + d_quantity +
                ", d_cost='" + d_cost + '\'' +
                ", p_name='" + p_name + '\'' +
                '}';
    }
}

