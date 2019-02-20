package com.qh.water_management.modules.entity.generator;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: qh
 * @Date: 2019/1/7 10:28
 * @Description:  列的属性
 */

@Getter
@Setter
public class ColumnEntity {

    //列名
    private String columnName;
    //列名类型
    private String dataType;
    //列名备注
    private String comments;

    //属性名称（第一个字母大写），如：user_name => UserName
    private String attrName;
    //属性名称（第一个字母小写），如：user_name => userName
    private String attrname;
    //属性类别
    private String attrType;
    //auto_increment
    private String extra;

}
