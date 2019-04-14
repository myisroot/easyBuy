package com.easybuynet.entity;

import java.io.Serializable;

public class Easy_user_address implements Serializable {
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

    public String getA_isdelete() {
        return a_isdelete;
    }

    public void setA_isdelete(String a_isdelete) {
        this.a_isdelete = a_isdelete;
    }

    private String a_isdelete;

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

    @Override
    public String toString() {
        return "Easy_user_address{" +
                "a_id=" + a_id +
                ", u_id=" + u_id +
                ", a_address='" + a_address + '\'' +
                ", a_creattime='" + a_creattime + '\'' +
                ", a_isdefault='" + a_isdefault + '\'' +
                ", a_remark='" + a_remark + '\'' +
                ", a_phone='" + a_phone + '\'' +
                ", a_area='" + a_area + '\'' +
                ", a_username='" + a_username + '\'' +
                '}';
    }
}

