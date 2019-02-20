package com.qh.water_management.modules.activiti.actlistener;

import com.qh.water_management.modules.common.utils.SpringUtil;
import com.qh.water_management.modules.entity.activiti.ActAssignee;
import com.qh.water_management.modules.utils.AssigneeType;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import java.util.List;

/**
 * @Author: qh
 * @Date: 2019/1/8 14:15
 * @Description: 流程监听器 动态注入节点办理人, 实现事件监听器的唯一要求是实现org.activiti.engine.delegate.event.ActivitiEventListener。
 */
public class ActNodeListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {

        //KEY
        String nodeId = delegateTask.getTaskDefinitionKey();
        /*ActAssigneeService actAssigneeService = SpringUtil.getBean(ActAssigneeServiceImpl.class);
        List<ActAssignee> assigneeList = actAssigneeService.selectListByPage(new ActAssignee(nodeId));
        for (ActAssignee assignee : assigneeList) {
            switch (assignee.getAssigneeType()) {
                case AssigneeType.GROUP_TYPE:
                    delegateTask.addCandidateGroup(assignee.getRoleId());
                    break;
                case AssigneeType.USER_TYPE:
                    delegateTask.addCandidateUser(assignee.getAssignee());
                    break;
            }
        }*/
    }
}
