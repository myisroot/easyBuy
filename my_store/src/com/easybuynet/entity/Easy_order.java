package com.easybuynet.entity;

import java.io.Serializable;

public class Easy_order implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer o_id;
    private Integer u_id;
    private String u_loginname;
    private String u_address;
    private String o_createtime;
    private String o_cost;
    private String o_serialnumber;
    private Integer a_id;

    public void setO_id(Integer o_id) {
        this.o_id = o_id;
    }

    public Integer getO_id() {
        return o_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_loginname(String u_loginname) {
        this.u_loginname = u_loginname;
    }

    public String getU_loginname() {
        return u_loginname;
    }

    public void setU_address(String u_address) {
        this.u_address = u_address;
    }

    public String getU_address() {
        return u_address;
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

    public void setA_id(Integer a_id) {
        this.a_id = a_id;
    }

    public Integer getA_id() {
        return a_id;
    }

    @Override
    public String toString() {
        return "Easy_order{" +
                "o_id=" + o_id +
                ", u_id=" + u_id +
                ", u_loginname='" + u_loginname + '\'' +
                ", u_address='" + u_address + '\'' +
                ", o_createtime='" + o_createtime + '\'' +
                ", o_cost='" + o_cost + '\'' +
                ", o_serialnumber='" + o_serialnumber + '\'' +
                ", a_id=" + a_id +
                '}';
    }
}

