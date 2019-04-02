package com.cyrj.pharmacymessage.bean;

public class BaseBean<T>{
    /**
     * 返回的类型
     */
    private int type;
    /**
     * 返回的错误信息
     */
    private String errorMessage;

    /**
     * 业务请求是否成功
     */
    private boolean isSuccess;

    /**
     * 响应业务对象类
     */
    private T data;


    /**
     * 错误标识 0 普通错误；其他错误类型可随接口自定义（主要是业务错误）：
     * <p/>
     * 1 已退回；
     * 2 已停嘱；
     * 3 已摆药、已核对、已成品；
     * 4 计费有关
     */
    private int errorflag;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorflag() {
        return errorflag;
    }

    public void setErrorflag(int errorflag) {
        this.errorflag = errorflag;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
