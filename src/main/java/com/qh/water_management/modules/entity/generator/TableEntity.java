package com.qh.water_management.modules.entity.generator;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: qh
 * @Date: 2019/1/7 10:27
 * @Description: 表数据
 */

@Getter
@Setter
public class TableEntity {

    //表名
    private String tableName;

    //表的备注
    private String comments;

    //表的主键
    private ColumnEntity pk;

    //表的列名（不包含主键）
    private List<ColumnEntity> columns;

    //类名（第一个字母大写），如：sys_user  => SysUser
    private String className;

    //类名（第一个字母小写）， 如：sys_user => sysUser
    private String classname;

}
