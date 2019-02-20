package com.qh.water_management.modules.service.activiti.impl;

import com.qh.water_management.modules.common.utils.ReType;
import com.qh.water_management.modules.entity.activiti.UserLeave;
import com.qh.water_management.modules.service.activiti.UserLeaveService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: qh
 * @Date: 2019/1/9 10:20
 * @Description:
 */
@Service("userLeaveService")
public class UserLeaveServiceImpl implements UserLeaveService {
    @Override
    public List<UserLeave> select(UserLeave userLeave) {
        return null;
    }

    @Override
    public List<UserLeave> selectAll() {
        return null;
    }

    @Override
    public List<UserLeave> selectByIds(String ids) {
        return null;
    }

    @Override
    public int selectCount(UserLeave userLeave) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(UserLeave record) {
        return 0;
    }

    @Override
    public int insertSelective(UserLeave record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(UserLeave record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(UserLeave record) {
        return 0;
    }

    @Override
    public List<UserLeave> selectListByPage(UserLeave record) {
        return null;
    }

    @Override
    public int deleteByPrimaryKey(Object o) {
        return 0;
    }

    @Override
    public int delete(UserLeave userLeave) {
        return 0;
    }

    @Override
    public boolean existsWithPrimaryKey(Object o) {
        return false;
    }

    @Override
    public UserLeave selectByPrimaryKey(Object o) {
        return null;
    }

    @Override
    public UserLeave selectOne(UserLeave userLeave) {
        return null;
    }

    @Override
    public int deleteByIds(String s) {
        return 0;
    }

    @Override
    public int insertList(List<UserLeave> list) {
        return 0;
    }

    @Override
    public int insertUseGeneratedKeys(UserLeave userLeave) {
        return 0;
    }

    @Override
    public int deleteByExample(Object o) {
        return 0;
    }

    @Override
    public List<UserLeave> selectByExample(Object o) {
        return null;
    }

    @Override
    public int selectCountByExample(Object o) {
        return 0;
    }

    @Override
    public UserLeave selectOneByExample(Object o) {
        return null;
    }

    @Override
    public int updateByExample(UserLeave userLeave, Object o) {
        return 0;
    }

    @Override
    public int updateByExampleSelective(UserLeave userLeave, Object o) {
        return 0;
    }

    @Override
    public List<UserLeave> selectByExampleAndRowBounds(Object o, RowBounds rowBounds) {
        return null;
    }

    @Override
    public List<UserLeave> selectByRowBounds(UserLeave userLeave, RowBounds rowBounds) {
        return null;
    }

    @Override
    public ReType show(UserLeave userLeave, int page, int limit) {
        return null;
    }

    @Override
    public ReType getList(UserLeave userLeave, int page, int limit) {
        return null;
    }

    @Override
    public String showAll(UserLeave userLeave) {
        return null;
    }
}
