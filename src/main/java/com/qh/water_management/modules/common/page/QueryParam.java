package com.qh.water_management.modules.common.page;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: qh
 * @Date: 2018/10/29 14:08
 * @Description: 查询参数实体类
 */
@Getter
@Setter
public class QueryParam implements Serializable {

    private Integer rows;//每页数量
    private Integer page;//页码
    private String sort;//排序字段
    private String order;//排序类型
    private String condition;//查询条件
    private String sqlConditon;//sql条件
}
