package com.qh.water_management.modules.common.utils;

import java.util.UUID;

/**
 * @Author: qh
 * @Date: 2018/12/3 14:15
 * @Description:
 */
public class Utils {

    /**
     *  封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
