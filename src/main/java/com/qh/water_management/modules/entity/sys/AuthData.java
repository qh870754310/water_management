package com.qh.water_management.modules.entity.sys;

import com.qh.water_management.modules.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author: qh
 * @Date: 2018/12/14 10:29
 * @Description:
 */
@Getter
@Setter
@Alias("sys_auth_data")
public class AuthData extends BaseEntity implements Serializable {

    private String id;

    private String parentId;

    private String text;

    private String orgId;

    private Integer sort;

    private Integer levelId;

    private Integer del;

    private String status;

    private String state;
    
}
