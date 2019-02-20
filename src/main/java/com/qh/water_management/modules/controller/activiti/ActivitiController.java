package com.qh.water_management.modules.controller.activiti;

import com.qh.water_management.modules.common.common.MyException;
import com.qh.water_management.modules.common.controller.BaseController;
import com.qh.water_management.modules.common.utils.JsonUtil;
import com.qh.water_management.modules.entity.sys.Role;
import com.qh.water_management.modules.service.sys.UserService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: qh
 * @Date: 2019/1/8 14:44
 * @Description: 流程管理 流程创建、发布 流程节点绑定角色/用户(绑定用户 开始ing)
 */
@Controller
@RequestMapping(value = "/act/")
public class ActivitiController extends BaseController {

    @Autowired
    private RepositoryService  repositoryService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private UserService userService;


    /**
     * 同步用户，角色， 用户角色到activiti表中
     * @return
     */
    /*@PostMapping(value = "syncdata")
    @ResponseBody
    public JsonUtil syncdata() {
        JsonUtil j = new JsonUtil();
        try {
            List<com.qh.water_management.modules.entity.sys.User> userList =userService.selectListByPage(new com.qh.water_management.modules.entity.sys.User());
            org.activiti.engine.identity.User au = null;
            for (com.qh.water_management.modules.entity.sys.User user : userList) {
                au = new UserEntity();
                au.setId(user.getId());
                au.setFirstName(user.getUserName());
                au.setEmail(user.getEmail());
                identityService.deleteUser(au.getId());
                identityService.saveUser(au);
            }
            List<SysRole> sysRoleList = roleService.selectListByPage(new SysRole());
            Group group = null;
            for (SysRole role : sysRoleList) {
                group = new GroupEntity();
                group.setId(role.getId());
                group.setName(role.getRoleName());
                identityService.deleteGroup(group.getId());
                identityService.saveGroup(group);
            }
            List<SysRoleUser> roleUserList = roleUserService.selectByCondition(new SysRoleUser());

            for (SysRoleUser sysRoleUser : roleUserList) {
                identityService.deleteMembership(sysRoleUser.getUserId(), sysRoleUser.getRoleId());
                identityService.createMembership(sysRoleUser.getUserId(), sysRoleUser.getRoleId());
            }
            j.setMsg("同步成功");
        } catch (MyException e) {
            j.setFlag(false);
            j.setMsg("同步失败");
            e.printStackTrace();
        }
        return j;
    }*/
}
