package com.qh.water_management.modules.entity.sys;

import com.qh.water_management.modules.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author: qh
 * @Date: 2018/11/16 9:36
 * @Description: 数据字典项
 */
@Getter
@Setter
@Alias("sys_data_dict_dtl")
public class DictionaryDetail extends BaseEntity implements Serializable {

    private String id;

    /**
     * 父子点集id
     */
    private String puuid;

    /**
     * 字典项名称
     */
    private String text;

    /**
     * 字典项值
     */
    private String value;

    /**
     * 默认选中
     */
    private Boolean selected;

    /**
     * 排序
     */
    private Integer sort;

    /**
     *
     */
    private String language;
}
