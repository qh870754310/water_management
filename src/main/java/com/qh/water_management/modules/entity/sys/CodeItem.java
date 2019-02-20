package com.qh.water_management.modules.entity.sys;

import com.qh.water_management.modules.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author: qh
 * @Date: 2018/12/12 14:27
 * @Description:
 */
@Getter
@Setter
@Alias("sys_code_item")
public class CodeItem extends BaseEntity implements Serializable {

    private String id;

    private String parentId;

    private String text;

    private String url;

    private String iconCls;

    private String codeSetId;

    private String code;

    private String type;

    private Integer levelId;

    private Integer sort;

    private String state;

    private Integer status;

    private String children;

}
