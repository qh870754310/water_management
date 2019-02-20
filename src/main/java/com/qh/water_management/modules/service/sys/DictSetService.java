package com.qh.water_management.modules.service.sys;

import com.qh.water_management.modules.entity.sys.DictionaryCate;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/10/29 10:07
 * @Description:
 */
public interface DictSetService {

    /**
     *
     * @param code
     * @return
     */
    List<Map> getDicItemByCode(String code);

    /**
     * 查询字典集
     * @param params
     * @return
     */
    Map<String, Object>  getPageSet(Map<String, Object> params);

    /**
     * 保存
     * @param dictionaryCate
     * @throws Exception
     */
    void save(DictionaryCate dictionaryCate) throws Exception;

    /**
     * 编辑
     * @param dictionaryCate
     * @throws Exception
     */
    void update(DictionaryCate dictionaryCate) throws Exception;

    /**
     * 删除
     * @param params
     * @throws Exception
     */
    void delete(Map<String, Object> params) throws Exception;
}
