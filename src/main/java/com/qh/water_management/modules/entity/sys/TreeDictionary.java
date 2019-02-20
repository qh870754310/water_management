package com.qh.water_management.modules.entity.sys;

import com.qh.water_management.modules.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author: qh
 * @Date: 2018/11/16 9:58
 * @Description: 树形字典
 */
@Getter
@Setter
@Alias("sys_tree_dict")
public class TreeDictionary extends BaseEntity implements Serializable {

    private String id;

    /**
     * 父级编号
     */
    private String parentId;

    /**
     * 体系编号
     */
    private String codeSetId;

    /**
     * 指标名称
     */
    private String text;

    /**
     * 指标代码
     */
    private String code;

    /**
     * 是否可展开
     */
    private Boolean state;

    /**
     * 显示图标
     */
    private String iconcls;

    /**
     * 指标排序
     */
    private Integer sort;

    /**
     * 层级
     */
    private String levelId;

    /**
     * 状态
     */
    private Integer status;
}
