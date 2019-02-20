package com.qh.water_management.modules.common.common;

/**
 * @Author: qh
 * @Date: 2018/10/29 13:19
 * @Description: 自定义异常
 */
public class RRException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String msg;

    private int code = 500;

    public RRException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public RRException(String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
    }

    public RRException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public RRException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
