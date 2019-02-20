package com.qh.water_management.modules.entity.sys;

import com.qh.water_management.modules.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author: qh
 * @Date: 2018/11/15 17:52
 * @Description: 组织机构
 */
@Getter
@Setter
@Alias("sys_organization")
public class Organization extends BaseEntity implements Serializable {

    /**
     * 组织机构id
     */
    private String id;
    /**
     * 父组织机构
     */
    private String parentId;
    /**
     * 组织机构名称
     */
    private String text;

    /**
     * 类型
     */
    private String typeText;

    /**
     *
     */
    private String typeValue;

    /**
     * 负责人
     */
    private String userId;

    /**
     * 代码
     */
    private String code;

    /**
     * 状态（0-启用， 1-禁止）
     */
    private String status;

    /**
     *
     */
    private String state;

    /**
     * 图标
     */
    private String iconCls;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否有子节点
     */
    private Boolean children;

    /**
     * 层级
     */
    private Integer levelId;

    /**
     *
     */
    private String leaderId;

    /**
     *
     */
    private Integer del;

}
