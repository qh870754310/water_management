package com.qh.water_management.modules.entity.sys;

import com.qh.water_management.modules.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author: qh
 * @Date: 2018/12/13 10:04
 * @Description:
 */
@Getter
@Setter
@Alias("sys_auth_access")
public class AuthAccess extends BaseEntity implements Serializable {

    private String id;

    private String roleId;

    private String menuId;

}
