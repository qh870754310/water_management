package com.qh.water_management.modules.dao.sys;

import com.qh.water_management.modules.common.dao.BaseDao;
import com.qh.water_management.modules.entity.sys.DictionaryCate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/10/29 10:09
 * @Description:
 */
@Mapper
public interface DictSetDao extends BaseDao<DictionaryCate> {

    List<Map> getDicItemByCode(String code);
}
