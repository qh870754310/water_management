package com.qh.water_management.modules.activiti.actlistener;

import com.qh.water_management.modules.entity.activiti.UserLeave;
import org.activiti.engine.delegate.DelegateTask;

import java.util.Map;

/**
 * @Author: qh
 * @Date: 2019/1/8 16:00
 * @Description: 自定义请假流程 监听器
 */
public class LeaveListenerImpl extends ActNodeListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        super.notify(delegateTask);
        String taskId = delegateTask.getId();
        Map<String, Object> map = delegateTask.getVariables();
        UserLeave userLeave = (UserLeave) map.get("userLeave");
        delegateTask.addCandidateUser(userLeave.getUserId());
    }
}
