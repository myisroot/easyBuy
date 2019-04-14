package com.easybuynet.entity;

import java.io.Serializable;

public class User_address implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer a_id;
    private Integer u_id;
    private String a_address;
    private String a_creattime;
    private String a_isdefault;
    private String a_remark;
    private String u_loginname;
    private String u_name;

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

    public void setU_loginname(String u_loginname) {
        this.u_loginname = u_loginname;
    }

    public String getU_loginname() {
        return u_loginname;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_name() {
        return u_name;
    }
}

