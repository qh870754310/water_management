package com.qh.water_management.modules.entity.sys;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author: qh
 * @Date: 2018/11/15 17:32
 * @Description:
 */
@Getter
@Setter
@Alias("user_group")
public class UserGroup implements Serializable {

    /**
     * 用户组id
     */
    private String id;

    /**
     * 用户组名称
     */
    private String userGroupName;

    /**
     * 父用户组Id
     */
    private String groupParentId;
}
