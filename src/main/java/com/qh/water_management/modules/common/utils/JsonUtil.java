package com.qh.water_management.modules.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @Author: qh
 * @Date: 2019/1/8 14:48
 * @Description: ajax 回执
 */
@Data
public class JsonUtil {

    //默认成功
    private boolean flag = true;

    private String msg;

    private JSONObject jsonObj;

    private Integer status;

    private Object data;

    public JsonUtil() {
    }

    public JsonUtil(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }


    public JsonUtil(boolean flag, String msg, Integer status) {
        this.flag = flag;
        this.msg = msg;
        this.status = status;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * restful 返回
     */
    public static JsonUtil error(String msg) {
        return new JsonUtil(false, msg);
    }

    public static JsonUtil success(String msg) {
        return new JsonUtil(true, msg);
    }
}
