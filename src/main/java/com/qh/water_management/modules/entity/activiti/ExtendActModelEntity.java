package com.qh.water_management.modules.entity.activiti;

import com.qh.water_management.modules.common.entity.ActivitiBaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @Author: qh
 * @Date: 2019/1/4 17:13
 * @Description: 流程模板扩展表
 */
@Getter
@Setter
public class ExtendActModelEntity extends ActivitiBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 版本号
     */
    private Integer actVersion;

    /**
     * 关联的 业务表的ID
     */
    @NotBlank(message = "关联业务不能为空")
    private String extendActBusinessId;

    /**
     * activiti中的部署表id
     */
    private String deploymentId;

    /**
     * 描述
     */
    private String description;

    /**
     * activiti中模型表的id
     */
    private String modelId;

    /**
     * 名称
     */
    @NotBlank(message = "模型名称不能为空")
    private String name;

    /**
     * 发布状态 1：已发布，2：未发布
     */
    private String status;

    /**
     * 业务名称
     */
    private String businessName;

    /**
     * 流程key
     */
    private String actKey;

}
