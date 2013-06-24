package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta26_remind_cfg"
 */

public class Ta26_remind_cfg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 215289995993310560L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta26_num"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	 /**
 * 节点名称【代表岗位】，用节点名称去匹配TB02.ID
 */
 private String node_name;

 /**
 * @hibernate.property column="node_name"
 * @return Returns the node_name.
 */
 public String getNode_name() {
    return node_name;
 }

 public void setNode_name(String node_name) {
    this.node_name = node_name;
  }

 /**
 * 模块ID，TA06.ID
 */
 private String module_ids;

 /**
 * @hibernate.property column="module_ids"
 * @return Returns the module_ids.
 */
 public String getModule_ids() {
    return module_ids;
 }

 public void setModule_ids(String module_ids) {
    this.module_ids = module_ids;
  }

 /**
 * 节点状态，同TB15.DOC_STATUS
 */
 private String node_status;

 /**
 * @hibernate.property column="node_status"
 * @return Returns the node_status.
 */
 public String getNode_status() {
    return node_status;
 }

 public void setNode_status(String node_status) {
    this.node_status = node_status;
  }

 /**
 * 提前天数，提前几天提醒
 */
 private Long pre_days;

 /**
 * @hibernate.property column="pre_days"
 * @return Returns the pre_days.
 */
 public Long getPre_days() {
    return pre_days;
 }

 public void setPre_days(Long pre_days) {
    this.pre_days = pre_days;
  }

 /**
 * 工程状态列表，工程状态同TD00.GCZT
 */
 private String project_status;

 /**
 * @hibernate.property column="project_status"
 * @return Returns the project_status.
 */
 public String getProject_status() {
    return project_status;
 }

 public void setProject_status(String project_status) {
    this.project_status = project_status;
  }

 /**
 * 备注
 */
 private String remark;

 /**
 * @hibernate.property column="remark"
 * @return Returns the remark.
 */
 public String getRemark() {
    return remark;
 }

 public void setRemark(String remark) {
    this.remark = remark;
  }


}
