package com.easybuynet.entity;

import java.io.Serializable;

public class Easy_news implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer n_id;
    private String n_title;
    private String n_content;
    private String n_creattime;

    public void setN_id(Integer n_id) {
        this.n_id = n_id;
    }

    public Integer getN_id() {
        return n_id;
    }

    public void setN_title(String n_title) {
        this.n_title = n_title;
    }

    public String getN_title() {
        return n_title;
    }

    public void setN_content(String n_content) {
        this.n_content = n_content;
    }

    public String getN_content() {
        return n_content;
    }

    public void setN_creattime(String n_creattime) {
        this.n_creattime = n_creattime;
    }

    public String getN_creattime() {
        return n_creattime;
    }

    @Override
    public String toString() {
        return "Easy_news{" +
                "n_id=" + n_id +
                ", n_title='" + n_title + '\'' +
                ", n_content='" + n_content + '\'' +
                ", n_creattime='" + n_creattime + '\'' +
                '}';
    }
}

