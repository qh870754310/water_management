package com.qh.water_management.modules.entity.activiti;

import com.qh.water_management.modules.common.entity.BaseEntity;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2019/1/9 11:20
 * @Description: 业务流程对应的业务表
 */
public class ExtendActBusinessEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 可写
     */
    private List<Map<String, Object>> writes;

    /**
     * 可设置为条件
     */
    private List<Map<String, Object>> judgs;

    private String id;

    /**
     *  业务流程名字
     */
    @NotBlank(message = "名称不能为空")
    private String text;

    /**
     * 流程Key
     */
    @NotBlank(message = "流程key不能为空")
    private String actKey;

    /**
     * 图标
     */
    private String iconCls;

    /**
     * 类路径
     */
    private String classurl;

    /**
     * 1=根节点，2=分组，3=业务类，4=回调
     */
    private String type;

    /**
     * 父节点id
     */
    @NotBlank(message = "父节点不能为空")
    private String parentId;

    /**
     * 层级
     */
    private Integer levelId;

    /**
     * 同一级节点中的序号
     */
    private Integer sort;

    /**
     * 父节点名称
     */
    private String parentName;

    /**
     * 是否展开
     */

    private String state;


    private Integer status;


    /**
     * 设置：
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * 获取：
     */
    public String getId() {
        return id;
    }
    /**
     * 设置：业务流程名字
     */
    public String getText() {
        return text;
    }

    /**
     * 获取：业务流程名字
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 设置：流程key
     */
    public void setActKey(String actKey) {
        this.actKey = actKey;
    }
    /**
     * 获取：流程key
     */
    public String getActKey() {
        return actKey;
    }
    /**
     * 设置：类路径
     */
    public void setClassurl(String classurl) {
        this.classurl = classurl;
    }
    /**
     * 获取：类路径
     */
    public String getClassurl() {
        return classurl;
    }
    /**
     * 设置：0=根节点 1=分组 2=业务类 3=回调
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * 获取：0=根节点 1=分组 2=业务类 3=回调
     */
    public String getType() {
        return type;
    }
    /**
     * 设置：父节点id
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    /**
     * 获取：父节点id
     */
    public String getParentId() {
        return parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<Map<String, Object>> getWrites() {
        return writes;
    }

    public void setWrites(List<Map<String, Object>> writes) {
        this.writes = writes;
    }

    public List<Map<String, Object>> getJudgs() {
        return judgs;
    }

    public void setJudgs(List<Map<String, Object>> judgs) {
        this.judgs = judgs;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
