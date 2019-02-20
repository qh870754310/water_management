package com.qh.water_management.modules.entity.sys;

import com.qh.water_management.modules.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author: qh
 * @Date: 2018/11/15 16:30
 * @Description:
 */
@Getter
@Setter
@Alias("sys_user")
public class User extends BaseEntity implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 用户名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * salt盐加密
     */
    private String salt;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 曾用名
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 所属机构
     */
    private String orgId;

    /**
     * 职务
     */
    private String post;

    /**
     * 固定电话
     */
    private String telephone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String cellphone;

    /**
     * 角色授权
     */
    private String userGroup;

    /**
     * 数据授权
     */
    private String dataAuth;

    /**
     * 状态(启用，禁用)
     */
    private Integer status;

    /**
     * 所属区域
     */
    private String zone;

    private String country;

    private String province;

    private String city;

    private String district;

    private String nativePlace;

    private String nation;

    private String idCard;

    private Integer del;

    private String school;

    private String education;

    private String degree;

}
