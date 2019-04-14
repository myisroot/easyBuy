package com.easybuynet.entity;

import java.io.Serializable;

public class Easy_product_category implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer c_id;
    private String c_name;
    private String c_parentid;
    private Integer c_type;
    private String c_iconclass;

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_parentid(String c_parentid) {
        this.c_parentid = c_parentid;
    }

    public String getC_parentid() {
        return c_parentid;
    }

    public void setC_type(Integer c_type) {
        this.c_type = c_type;
    }

    public Integer getC_type() {
        return c_type;
    }

    public void setC_iconclass(String c_iconclass) {
        this.c_iconclass = c_iconclass;
    }

    public String getC_iconclass() {
        return c_iconclass;
    }

    @Override
    public String toString() {
        return "Easy_product_category{" +
                "c_id=" + c_id +
                ", c_name='" + c_name + '\'' +
                ", c_parentid=" + c_parentid +
                ", c_type=" + c_type +
                ", c_iconclass='" + c_iconclass + '\'' +
                '}';
    }
}

