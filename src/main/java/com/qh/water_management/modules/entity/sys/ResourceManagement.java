package com.qh.water_management.modules.entity.sys;

import com.qh.water_management.modules.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: qh
 * @Date: 2018/11/16 9:17
 * @Description: 资源管理
 */
@Getter
@Setter
@Alias("sys_resources")
public class ResourceManagement extends BaseEntity implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 父级资源id
     */
    private String parentId;

    /**
     * 名称
     */
    private String text;

    /**
     *
     */
    private String codeSetId;

    /**
     * 资源类别
     */
    private String resourceType;

    /**
     * 资源地址或标识
     */
    private String url;

    /**
     * 层级
     */
    private Integer levelId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否有子节点
     */
    private String state;

    /**
     * 资源图标
     */
    private String iconCls;

    /**
     * 子类菜单
     */
    private List list;

    /**
     *
     */
    private String code;

}
