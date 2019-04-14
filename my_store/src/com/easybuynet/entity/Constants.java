package com.easybuynet.entity;

public class Constants {
    //成功状态码
    public static Integer SUCCESS = 1;
    //失败状态码
    public static Integer FAIL = -1;

    public final static Integer ISLOGIN = 1;
    public final static Integer NOLOGIN = -1;
    //不为管理员
    public final static Integer USER = -1;
    //为超级管理员
    public final static Integer ROOT = 1;
    //为管理员
    public final static Integer ADMIN = 2;
    //删除权限
    public final static Integer NOREMOVE = -1;
    public final static Integer ISREMOVE = 1;
    //添加权限
    public final static Integer NOADD = -1;
    public final static Integer ISADD = 1;
    //修改权限
    public final static Integer NOUPDATE = -1;
    public final static Integer ISUPDATE = 1;


    //是否删除地址
    public final static String ISDELADDRESS = "0";
    public final static String NODELADDRESS = "1";

    //是否是默认地址
    public final static String ISDEFAULT = "1";
    public final static String NODEFAULT = "0";
}
