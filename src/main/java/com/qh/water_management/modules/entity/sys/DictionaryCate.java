package com.qh.water_management.modules.entity.sys;

import com.qh.water_management.modules.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author: qh
 * @Date: 2018/11/16 9:25
 * @Description: 数据字典集
 */
@Getter
@Setter
@Alias("sys_data_dict_cate")
public class DictionaryCate extends BaseEntity implements Serializable {

    private String id;

    /**
     * 字典集名称
     */
    private String text;

    /**
     * 字典集代码
     */
    private String code;

    /**
     * 值
     */
    private String value;

    /**
     * 排序
     */
    private Integer sort;

}
