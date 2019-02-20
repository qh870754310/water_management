package com.qh.water_management.modules.entity.sys;

import com.qh.water_management.modules.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author: qh
 * @Date: 2018/11/15 17:04
 * @Description: 系统菜单
 */
@Getter
@Setter
@Alias("sys_menu")
public class Menu extends BaseEntity implements Serializable {

    /**
     * 菜单Id
     */
    private String id;

    /**
     * 父级菜单id
     */
    private String parentId;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单编码
     */
    private String code;

    /**
     * 排序
     */
    private String sort;

    /**
     * 状态（0：显示，-1：隐藏）
     */
    private Integer status;

    /**
     * 是否展开（0-折叠，1-展开）
     */
    private Integer expanded;



}
