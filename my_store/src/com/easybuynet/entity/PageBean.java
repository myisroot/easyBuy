package com.easybuynet.entity;

import java.util.List;

public class PageBean {
    //当前页
    private Integer currPaNo;
    //页面大小
    private Integer pageSize;
    //页数量
    private Integer pageCounts;
    //总记录
    private Integer allCounts;
    //数据
    private Object data;

    public Integer getCurrPaNo() {
        return currPaNo;
    }

    public void setCurrPaNo(Integer currPaNo) {
        this.currPaNo = currPaNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCounts() {
        return allCounts % pageSize == 0 ? (allCounts / pageSize) : (allCounts / pageSize) + 1;
    }

    public void setPageCounts(Integer pageCounts) {
        this.pageCounts = pageCounts;
    }

    public Integer getAllCounts() {
        return allCounts;
    }

    public void setAllCounts(Integer allCounts) {
        this.allCounts = allCounts;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}