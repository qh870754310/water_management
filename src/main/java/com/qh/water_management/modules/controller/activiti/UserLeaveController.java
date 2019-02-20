package com.qh.water_management.modules.controller.activiti;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qh.water_management.modules.common.common.MyException;
import com.qh.water_management.modules.common.controller.BaseController;
import com.qh.water_management.modules.common.utils.ReType;
import com.qh.water_management.modules.entity.activiti.UserLeave;
import com.qh.water_management.modules.service.activiti.UserLeaveService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: qh
 * @Date: 2019/1/9 10:19
 * @Description: 请假流程示例
 */
@Controller
@RequestMapping(value = "/leave/")
public class UserLeaveController extends BaseController {

    @Autowired
    UserLeaveService userLeaveService;

    @Autowired
    RuntimeService runtimeService;

    @GetMapping(value = "showMain")
    public String showMain(Model model) {
        return "/dashboard/dashboard";
    }

    @GetMapping(value = "showMain2")
    public String showMain2(Model model) {
        return "/dashboard/dashboard2";
    }

    @GetMapping(value = "showLeave")
    public String showUser(Model model) {
        return "/act/leave/leaveList";
    }


    /*@GetMapping(value = "showLeaveList")
    @ResponseBody
    public ReType showLeaveList(Model model, UserLeave userLeave, String page, String limit) {
        String userId = CommonUtil.getUser().getId();
        userLeave.setUserId(userId);
        List<UserLeave> tList = null;
        Page<UserLeave> tPage = PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
        try {
            tList = leaveService.selectListByPage(userLeave);
            for (UserLeave leave : tList) {
                ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                        .processInstanceId(leave.getProcessInstanceId()).singleResult();
                //保证运行ing
                if (instance != null) {
                    Task task = this.taskService.createTaskQuery().processInstanceId(leave.getProcessInstanceId()).singleResult();
                    leave.setTaskName(task.getName());
                }
            }
        } catch (MyException e) {
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), tList);
    }*/
}
