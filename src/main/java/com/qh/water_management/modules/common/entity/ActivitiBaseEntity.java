package com.qh.water_management.modules.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: qh
 * @Date: 2019/1/4 17:14
 * @Description: activiti公共属性，需要用到流程的业务，需要继承
 */
@Getter
@Setter
public class ActivitiBaseEntity extends BaseEntity {

    /**
     * 业务流程状态 1=草稿，2=审批中，3=结束
     */
    private String status;

    /**
     * 审批结果： 1为同意，2为不同意，3为审批中
     */
    private String actResult;

    /**
     * 流程发起时间
     */
    private Date startTime;

    /**
     * 流程实例Id
     */
    private String instanceId;

    /**
     * 流程定义id
     */
    private String defId;

    /**
     * 流程发起人
     */
    private String startUserId;

    /**
     * 业务流程单据编号
     */
    private String code;

}
