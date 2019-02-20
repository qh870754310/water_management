package com.qh.water_management.modules.activiti.actlistener;

import com.qh.water_management.modules.entity.activiti.BaseTask;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import java.util.Map;

/**
 * @Author: qh
 * @Date: 2019/1/8 15:55
 * @Description: 流程监听器 动态注入节点办理人
 */
public class ActStartNodeListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        //KEY
        String nodeId = delegateTask.getTaskDefinitionKey();
        Map<String, Object> variables = delegateTask.getVariables();
        BaseTask baseTask  = (BaseTask) variables.get("baseTask");
        delegateTask.setAssignee(baseTask.getUserId());
    }
}
