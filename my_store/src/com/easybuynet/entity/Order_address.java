package com.easybuynet.entity;

import java.io.Serializable;

public class Order_address implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer a_id;
    private Integer u_id;
    private String a_address;
    private String a_creattime;
    private String a_isdefault;
    private String a_remark;
    private String a_phone;
    private String a_area;
    private String a_username;
    private Integer o_id;
    private String o_createtime;
    private String o_cost;
    private String o_serialnumber;

    public void setA_id(Integer a_id) {
        this.a_id = a_id;
    }

    public Integer getA_id() {
        return a_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setA_address(String a_address) {
        this.a_address = a_address;
    }

    public String getA_address() {
        return a_address;
    }

    public void setA_creattime(String a_creattime) {
        this.a_creattime = a_creattime;
    }

    public String getA_creattime() {
        return a_creattime;
    }

    public void setA_isdefault(String a_isdefault) {
        this.a_isdefault = a_isdefault;
    }

    public String getA_isdefault() {
        return a_isdefault;
    }

    public void setA_remark(String a_remark) {
        this.a_remark = a_remark;
    }

    public String getA_remark() {
        return a_remark;
    }

    public void setA_phone(String a_phone) {
        this.a_phone = a_phone;
    }

    public String getA_phone() {
        return a_phone;
    }

    public void setA_area(String a_area) {
        this.a_area = a_area;
    }

    public String getA_area() {
        return a_area;
    }

    public void setA_username(String a_username) {
        this.a_username = a_username;
    }

    public String getA_username() {
        return a_username;
    }

    public void setO_id(Integer o_id) {
        this.o_id = o_id;
    }

    public Integer getO_id() {
        return o_id;
    }

    public void setO_createtime(String o_createtime) {
        this.o_createtime = o_createtime;
    }

    public String getO_createtime() {
        return o_createtime;
    }

    public void setO_cost(String o_cost) {
        this.o_cost = o_cost;
    }

    public String getO_cost() {
        return o_cost;
    }

    public void setO_serialnumber(String o_serialnumber) {
        this.o_serialnumber = o_serialnumber;
    }

    public String getO_serialnumber() {
        return o_serialnumber;
    }

    @Override
    public String toString() {
        return "Order_address{" +
                "a_id=" + a_id +
                ", u_id=" + u_id +
                ", a_address='" + a_address + '\'' +
                ", a_creattime='" + a_creattime + '\'' +
                ", a_isdefault='" + a_isdefault + '\'' +
                ", a_remark='" + a_remark + '\'' +
                ", a_phone='" + a_phone + '\'' +
                ", a_area='" + a_area + '\'' +
                ", a_username='" + a_username + '\'' +
                ", o_id=" + o_id +
                ", o_createtime='" + o_createtime + '\'' +
                ", o_cost='" + o_cost + '\'' +
                ", o_serialnumber='" + o_serialnumber + '\'' +
                '}';
    }
}

