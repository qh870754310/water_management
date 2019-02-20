package com.qh.water_management.modules.common.utils;

import com.qh.modules.common.common.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/11/16 13:18
 * @Description: 返回数据实体类
 */
public class Result extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public Result() {
        put("statusCode", 0);
    }

    public Result(String statusCode, String message) {
        put("statusCode", statusCode);
        put("message", message);
    }

    public static Result error() {
        return new Result(Constant.RESULT.CODE_NO.getValue(), Constant.RESULT.MSG_NO.getValue());
    }

    public static Result error(String message) {
        return error(Constant.RESULT.CODE_NO.getValue(), message);
    }

    public static Result error(String statusCode, String message) {
        Result result = new Result();
        result.put("statusCode",statusCode);
        result.put("message", message);
        return result;
    }

    public static Result ok(String message) {
        Result r = new Result();
        r.put("message", message);
        return r;
    }

    public static Result ok(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }

    public static Result ok() {
        return new Result(Constant.RESULT.CODE_YES.getValue(),Constant.RESULT.MSG_YES.getValue());
    }

    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}

