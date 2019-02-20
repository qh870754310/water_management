package com.qh.water_management.modules.common.xss;

import com.qh.water_management.modules.common.common.RRException;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: qh
 * @Date: 2018/10/29 13:17
 * @Description: SQL过滤
 */
public class SQLFilter {

    /**
     * SQL注入过滤
     *
     * @param str 待验证的字符串
     * @return
     */
    public static String sqlInject(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "update", "delete", "select", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for (String keyword : keywords) {
            if (str.indexOf(keyword) != -1) {
                throw new RRException("包含非法字符");
            }
        }
        return str;
    }
}
