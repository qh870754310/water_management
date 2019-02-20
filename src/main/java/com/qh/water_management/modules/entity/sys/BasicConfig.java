package com.qh.water_management.modules.entity.sys;

import com.qh.water_management.modules.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author: qh
 * @Date: 2018/11/30 17:05
 * @Description: 基本配置类
 */
@Getter
@Setter
@Alias("sys_basic_config")
public class BasicConfig extends BaseEntity implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * 值
     */
    private String value;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String description;

    /**
     * 修改状态
     */
    private Integer modifyFlag;

}
