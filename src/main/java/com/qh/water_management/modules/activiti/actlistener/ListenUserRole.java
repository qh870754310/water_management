package com.qh.water_management.modules.activiti.actlistener;

import com.qh.water_management.modules.common.utils.JsonUtil;
import com.qh.water_management.modules.entity.sys.Role;
import com.qh.water_management.modules.entity.sys.User;
import com.qh.water_management.modules.service.sys.UserService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: qh
 * @Date: 2019/1/9 9:17
 * @Description: 切入实现系统用户 角色 用户-角色 同步到 activiti 用户 组 用户组 同步到工作流,模块化 无侵入
 */
@Aspect
@Component
public class ListenUserRole {

    @Autowired
    IdentityService identityService;

    @Autowired
    UserService userService;

    /**********************用户处理begin***************************/
    /**
     * 明确切入方法的参数
     * @param joinPoint
     * @return
     * @throws Throwable
     */
   /* @Around("execution(com.qh.water_management.modules.common.utils.JsonUtil com.qh.water_management.modules.controller.sys.UserController.updateUser(*,String[]))")
    public Object listenerUserUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        Object o = new Object();
        //更新前拿到用户-角色数据
        SysRoleUser sysRoleUser = new SysRoleUser();
        Object[] args = joinPoint.getArgs();
        sysRoleUser.setUserId(((SysUser) args[0]).getId());
        List<SysRoleUser> keyList = userService.selectByCondition(sysRoleUser);
        List<String> strings = new ArrayList<>();
        keyList.forEach(sysRoleUser1 -> strings.add(sysRoleUser1.getRoleId()));
        o = joinPoint.proceed(joinPoint.getArgs());
        JsonUtil jsonUtil = (JsonUtil) o;
        if (jsonUtil.isFlag()) {
            changeUser(args, strings);
        }
        return o;
    }*/


    /**
     * 新增用户监听 同步工作流用户表 环绕注解能得到 插入用户id
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    /*@Around("execution(com.qh.water_management.modules.common.utils.JsonUtil com.qh.water_management.modules.controller.sys.UserController.addUser(*,String[])))")
    public Object listenerUserInsert(ProceedingJoinPoint joinPoint) throws Throwable {
        Object o = joinPoint.proceed(joinPoint.getArgs());
        Object[] args = joinPoint.getArgs();
        if (args.length == 2) {
            JsonUtil jsonUtil = (JsonUtil) o;
            if (jsonUtil.isFlag()) {
                changeUser(args, Arrays.asList((String[]) args[1]));
            }
        }
        return o;
    }


    @Around("execution(com.qh.water_management.modules.common.utils.JsonUtil com.qh.water_management.modules.controller.sys.UserController.del(..))")
    public Object listenDelUser(ProceedingJoinPoint joinPoint) throws Throwable {
        Object o = joinPoint.proceed(joinPoint.getArgs());
        JsonUtil jsonUtil = (JsonUtil) o;
        if (jsonUtil.isFlag()) {
            Object[] args = joinPoint.getArgs();
            identityService.deleteUser((String) args[0]);
        }
        return o;
    }*/

    /**
     * 保存进 activiti 用户 角色 用户角色中间表
     * @param obj
     * @param strings
     */
    /*private void changeUser(Object[] obj, List<String> strings) {
        User user = (User) obj[0];
        identityService.deleteUser(user.getId());
        org.activiti.engine.identity.User au = new UserEntity();
        au.setId(user.getId());
        au.setFirstName(user.getUserName());
        au.setEmail(user.getEmail());
        identityService.saveUser(au);

        //删除用户-组关联
        for (String roleId : strings) {
            identityService.deleteMembership(user.getId(), roleId);
        }
        //再次关联
        if (!strings.isEmpty()) {
            for (String roleId : strings) {
                identityService.createMembership(user.getId(), roleId);
            }
        }
    }*/


    /**********************用户处理end***************************/


    /**********************角色处理begin***************************/
    /*@Around("execution(com.qh.water_management.modules.common.utils.JsonUtil com.qh.water_management.modules.controller.sys.RoleController.addRole(*,String[]))")
    public Object listenRoleInsert(ProceedingJoinPoint joinPoint) throws Throwable {
        Object o = joinPoint.proceed(joinPoint.getArgs());
        JsonUtil j = (JsonUtil) o;
        if (j.isFlag()) {
            Object[] args = joinPoint.getArgs();
            if (args.length == 2) {
                changeRole(args);
            }
        }
        return o;
    }

    @Around("execution(com.qh.water_management.modules.common.utils.JsonUtil com.qh.water_management.modules.controller.sys.RoleController.updateUser(*,String[]))")
    public Object listenRoleUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        Object o = joinPoint.proceed(joinPoint.getArgs());
        Object[] args = joinPoint.getArgs();
        if(args.length == 2) {
            if (((JsonUtil)o).isFlag()) {
                changeRole(args);
            }
        }
        return o;
    }*/

    /*@Around("execution(com.qh.water_management.modules.common.utils.JsonUtil com.qh.water_management.modules.controller.sys.RoleController.del(..))")
    public Object listenDelRole(ProceedingJoinPoint joinPoint) throws Throwable {
        Object o = joinPoint.proceed(joinPoint.getArgs());
        JsonUtil jsonUtil = (JsonUtil) o;
        if (jsonUtil.isFlag()) {
            Object[] args = joinPoint.getArgs();
            identityService.deleteGroup((String) args[0]);
        }
        return o;
    }*/

    /**
     * 更新进组
     * @param obj
     */
    /*private void changeRole(Object[] obj) {
        Role role = (Role) obj[0];
        identityService.deleteGroup(role.getId());
        Group group = new GroupEntity();
        group.setId(role.getId());
        group.setName(role.getRoleName());
        identityService.saveGroup(group);
    }*/

    /**********************角色处理end***************************/

}
