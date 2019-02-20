package com.qh.water_management.modules.common.common;

/**
 * @Author: qh
 * @Date: 2019/1/8 15:25
 * @Description:
 */
public class MyException extends RuntimeException {

    private String message;

    public MyException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
