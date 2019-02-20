package com.qh.water_management.modules.service.activiti;

import com.qh.water_management.modules.entity.activiti.ExtendActModelEntity;

/**
 * @Author: qh
 * @Date: 2019/1/10 16:47
 * @Description: activiti模型接口类
 */
public interface ActModelService {

    String CreateModel(ExtendActModelEntity extendActModelEntity) throws Exception;
}
