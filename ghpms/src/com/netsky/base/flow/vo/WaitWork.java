package com.netsky.base.flow.vo;

import java.sql.Date;

/**
 * @description:
 * 待复文档列表
 * @class name:com.netsky.base.flow.vo.WaitWork
 * @author wind Jan 28, 2010
 * @hibernate.class table="wait_work"
 */
public class WaitWork {
	
	/**
	 * 虚拟主建
	 */
	private Long id;
	
	/**
	 * 节点ID
	 */
	private Long flow_id;
	
	/**
	 * 节点ID
	 */
	private Long node_id;
	
	/**
	 * 文档类别标识
	 */
	private Long module_id;
	
	/**
	 * 项目ID
	 */
	private Long project_id;
	
	/**
	 * 文档ID
	 */
	private Long doc_id;
	
	/**
	 * 当前文档对应 tb12_id
	 */
	private Long opernode_id;
	
	/**
	 * 人员ID
	 */
	private Long user_id;
	
	/**
	 * 当前人处理时间
	 */
	private String oper_time;
	
	/**
	 * 相关处理人
	 */
	private String near_user;
	
	/**
	 * 相关处理状态(同node_status)：
	 * <br>0待办 ；1 新建； 2在办；3发送；4 回复同意； 5 回复修改； 6 回复不同意 ；7 回复不发表意见； 8  办结
	 */
	private String near_status;
	
	/**
	 * 文档状态（同tb12_node_status）：
	 */
	private Integer status_num;
	
	/**
	 * 相关处理状态(同status)：
	 * <br>0待办 ；1 新建； 2在办；3发送；4 回复同意； 5 回复修改； 6 回复不同意 ；7 回复不发表意见； 8  办结
	 */
	private String doc_status;
	
	/**
	 * 模块名称
	 */
	private String module_name;
	
	/**
	 * 流程显示
	 */
	private String flowflag;
	
	/**
	 * 流程显示
	 */
	private String flowname;	
	
	/**
	 * 流程状态
	 */
	private String flow_status;		

	
	/**
	 * @hibernate.property column="flow_id"
	 * @return Returns the flow_id.
	 */
	public Long getFlow_id() {
		return flow_id;
	}

	/**
	 * @param flow_id The flow_id to set.
	 */
	public void setFlow_id(Long flow_id) {
		this.flow_id = flow_id;
	}

	/**
	 * @hibernate.property column="node_id"
	 * @return Returns the node_id.
	 */
	public Long getNode_id() {
		return node_id;
	}

	/**
	 * @param node_id The node_id to set.
	 */
	public void setNode_id(Long node_id) {
		this.node_id = node_id;
	}

	/**
	 * @hibernate.property column="module_id"
	 * @return Returns the module_id.
	 */
	public Long getModule_id() {
		return module_id;
	}

	/**
	 * @param module_id The module_id to set.
	 */
	public void setModule_id(Long module_id) {
		this.module_id = module_id;
	}

	/**
	 * @hibernate.property column="project_id"
	 * @return Returns the project_id.
	 */
	public Long getProject_id() {
		return project_id;
	}

	/**
	 * @param project_id The project_id to set.
	 */
	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	/**
	 * @hibernate.property column="doc_id"
	 * @return Returns the doc_id.
	 */
	public Long getDoc_id() {
		return doc_id;
	}

	/**
	 * @param doc_id The doc_id to set.
	 */
	public void setDoc_id(Long doc_id) {
		this.doc_id = doc_id;
	}

	/**
	 * @hibernate.property column="opernode_id"
	 * @return Long
	 */
	public Long getOpernode_id() {
		return opernode_id;
	}

	/**
	 * @param opernode_id The opernode_id to set.
	 */
	public void setOpernode_id(Long opernode_id) {
		this.opernode_id = opernode_id;
	}

	/**
	 * @hibernate.property column="user_id"
	 * @return Returns the user_id.
	 */
	public Long getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id The user_id to set.
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	/**
	 * @hibernate.property column="oper_time"
	 * @return Returns the oper_time.
	 */
	public String getOper_time() {
		return oper_time;
	}

	/**
	 * @param oper_time The oper_time to set.
	 */
	public void setOper_time(String oper_time) {
		this.oper_time = oper_time;
	}

	/**
	 * @hibernate.property column="near_user"
	 * @return Returns the near_user.
	 */
	public String getNear_user() {
		return near_user;
	}

	/**
	 * @param near_user The near_user to set.
	 */
	public void setNear_user(String near_user) {
		this.near_user = near_user;
	}

	/**
	 * @hibernate.property column="near_status"
	 * @return Returns the near_status.
	 */
	public String getNear_status() {
		return near_status;
	}

	/**
	 * @param near_status The near_status to set.
	 */
	public void setNear_status(String near_status) {
		this.near_status = near_status;
	}

	/**
	 * @hibernate.property column="status_num"
	 * @return Returns the status_num.
	 */
	public Integer getStatus_num() {
		return status_num;
	}

	/**
	 * @param status_num The status_num to set.
	 */
	public void setStatus_num(Integer status_num) {
		this.status_num = status_num;
	}

	/**
	 * @hibernate.property column="doc_status"
	 * @return Returns the doc_status.
	 */
	public String getDoc_status() {
		return doc_status;
	}

	/**
	 * @param doc_status The doc_status to set.
	 */
	public void setDoc_status(String doc_status) {
		this.doc_status = doc_status;
	}

	/**
	 * @hibernate.property column="module_name"
	 * @return Returns the module_name.
	 */
	public String getModule_name() {
		return module_name;
	}

	/**
	 * @param module_name The module_name to set.
	 */
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	/**
	 * @hibernate.property column="flowflag"
	 * @return Returns the flowflag.
	 */
	public String getFlowflag() {
		return flowflag;
	}

	/**
	 * @param flowflag The flowflag to set.
	 */
	public void setFlowflag(String flowflag) {
		this.flowflag = flowflag;
	}

	/**
	 * @hibernate.property column="flowname"
	 * @return Returns the flowname.
	 */
	public String getFlowname() {
		return flowname;
	}

	/**
	 * @param flowname The flowname to set.
	 */
	public void setFlowname(String flowname) {
		this.flowname = flowname;
	}

	/**
	 * @hibernate.property column="flow_status"
	 * @return Returns the flow_status.
	 */
	public String getFlow_status() {
		return flow_status;
	}

	/**
	 * @param flow_status The flow_status to set.
	 */
	public void setFlow_status(String flow_status) {
		this.flow_status = flow_status;
	}

	/**
	 * @hibernate.id generator-class="assigned"
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

}
