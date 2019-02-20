package com.qh.water_management.modules.common.controller;

import com.qh.water_management.modules.common.utils.UserUtils;
import com.qh.water_management.modules.entity.sys.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: qh
 * @Date: 2018/11/16 13:31
 * @Description: 公共的控件器，可在类中实现一些共同的方法和属性 持续更新中
 */
public class BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public User getUser() {
        return UserUtils.getCurrentUser();
    }

    /**
     * 获取当前登录用户的Id
     * @return
     */
    public String getUserId() {
        User userEntity = getUser();
        if (null != userEntity && null != userEntity.getId()) {
            return userEntity.getId();
        }
        return "";
    }
}
