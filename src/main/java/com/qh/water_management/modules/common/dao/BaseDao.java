package com.qh.water_management.modules.common.dao;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/11/30 16:43
 * @Description: 基础Dao(还需在XML文件里，有对应的SQL语句)
 */
public interface BaseDao<T> {

    List<T> queryList(Map<String, Object> map);

    int queryTotal();

    int queryTotal(Map<String, Object> params);

    int save(T t);

    int update(T t);

    int delete(Object[] uuid_array);

    Map<String, Object> getDetailByUuid(String uuid);

    T queryObject(Object id);
}
