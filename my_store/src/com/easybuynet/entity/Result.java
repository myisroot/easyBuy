package com.easybuynet.entity;

public class Result {
    private int status;  //状态码
    private Object data; //数据
    private String message = "操作失败"; //提示信息

    public Result() {
        super();
    }

    public Result(Object object) {
        this.status = Constants.SUCCESS;
        this.data = object;
    }

    public Result(int status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 返回成功状态
     *
     * @param object
     * @return
     */
    public Result Success(Object object) {
        this.message = "操作成功";
        this.status = Constants.SUCCESS;
        this.data = object;
        return this;
    }

    /**
     * 返回默认成功状态
     *
     * @return
     */
    public Result Success() {
        this.status = Constants.SUCCESS;
        this.message = "操作成功";
        return this;
    }

    /**
     * 返回失败的状态
     *
     * @param message
     * @return
     */
    public Result Fail(String message) {
        this.status = Constants.FAIL;
        this.message = message;
        return this;
    }

}
