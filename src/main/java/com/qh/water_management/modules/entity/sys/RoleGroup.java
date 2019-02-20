package com.qh.water_management.modules.entity.sys;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author: qh
 * @Date: 2018/11/15 17:40
 * @Description:
 */
@Getter
@Setter
@Alias("role_group")
public class RoleGroup implements Serializable {

    /**
     * 角色组id
     */
    private String id;

    /**
     * 角色组名称
     */
    private String roleGroupName;

    /**
     * 父角色组id
     */
    private String groupParentName;

}
