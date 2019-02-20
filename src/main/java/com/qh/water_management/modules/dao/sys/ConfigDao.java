package com.qh.water_management.modules.dao.sys;

import com.qh.water_management.modules.common.dao.BaseDao;
import com.qh.water_management.modules.entity.sys.BasicConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;


/**
 * @Author: qh
 * @Date: 2018/11/30 16:17
 * @Description:
 */
@Mapper
public interface ConfigDao extends BaseDao<BasicConfig> {


}
