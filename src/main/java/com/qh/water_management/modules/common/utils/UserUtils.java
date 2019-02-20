package com.qh.water_management.modules.common.utils;

import com.qh.water_management.modules.entity.sys.User;
import com.qh.water_management.modules.service.sys.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @Author: qh
 * @Date: 2018/11/16 13:32
 * @Description: 用户工具类
 */
public class UserUtils {

    @Autowired
    private UserService userService;

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static User getCurrentUser() {
        User user = ShiroUtils.getUser();
        return user;
    }

    /**
     * 获取当前登录用户id 待完善缓存
     *
     * @return
     */
    public static String getCurrentUserId() {
        User user = ShiroUtils.getUser();
        return user.getId();
    }

    public static void main(String[] args) {

        //root:admin
        //password:09384606b9ea5f1c326b21802b1980c6
        //sale:qj50vmMmeZcHslV62WIY
        String salt = RandomStringUtils.randomAlphanumeric(20);
        String password = ShiroUtils.EncodeSalt("123456", salt);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(uuid);
        System.out.println(salt);
        System.out.println(password);
    }

}
