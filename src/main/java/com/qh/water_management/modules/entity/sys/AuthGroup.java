package com.qh.water_management.modules.entity.sys;

import com.qh.water_management.modules.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author: qh
 * @Date: 2018/12/12 18:09
 * @Description: 角色权限
 */
@Setter
@Getter
@Alias("sys_auth_group")
public class AuthGroup extends BaseEntity implements Serializable {

    private String id;

    private String parentId;

    private String text;

    private Integer levelId;

    private String menuId;

    private String code;

    private String codeSetId;

    private Integer sort;

    private String state;

    private String status;

}
