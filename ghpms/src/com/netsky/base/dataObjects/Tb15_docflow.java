package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.*;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="Tb15_docflow"
 */

public class Tb15_docflow implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7201768709714492804L;

	/**
	 * 项目ID
	 */
	private Long id;
	
	/**
	 * 项目ID
	 */
	private Long project_id;
	
	/**
	 * 节点ID
	 */
	private Long node_id;
	
	/**
	 * 文档ID
	 */
	private Long doc_id;
	
	/**
	 * 人员ID
	 */
	private Long user_id;
	
	/**
	 * 文档状态（同tb12_node_status）：
	 * <br>0 待办；1 新建；2 在办；3  发送；4 回复同意； 5 回复修改； 6 回复不同意 ；7 回复不发表意见； 8  办结
	 */
	private Integer doc_status;
	
	/**
	 * 文档类别标识
	 */
	private Long module_id;
	
	/**
	 * 相关处理状态(同node_status)：
	 * <br>0待办 ；1 新建； 2在办；3发送；4 回复同意； 5 回复修改； 6 回复不同意 ；7 回复不发表意见； 8  办结
	 */
	private String oper_status;
	
	/**
	 * 相关处理人
	 */
	private String oper_user;
	
	/**
	 * 当前人处理时间
	 */
	private Date oper_time;
	
	/**
	 * 当前人受理时间
	 */
	private Date accept_time;
	
	/**
	 * 回退到二次节点处理人员；如果施工任务书中项目经理处理人员，工期调整单，工程变更单，初审报告，报项目经理时，都报到huser_id手中。
	 */
	private Long huser_id;
	
	/**
	 * 是否可写 ： 0 不可写；1 可写
	 */
	private Integer write_limit;
	
	/**
	 * 本字段只在发送收回时用。发送前是否可写 ： 0 不可写；1 可写
	 */
	private Integer hWrite_limit;	
	
	/**
	 * 当前文档所在群ID
	 */
	private Long group_id;
	
	/**
	 * 有审批权限的人审批意见：4 同意； 5 修改； 6 不同意 ；7 不发表意见；
	 */
	private Integer approve_result;
	
	/**
	 * 当前文档对应 tb12_id
	 */
	private Long opernode_id;

	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tb15_NUM"
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
	 * @hibernate.property column="doc_status"
	 * @return Returns the doc_status.
	 */
	public Integer getDoc_status() {
		return doc_status;
	}

	/**
	 * @param doc_status The doc_status to set.
	 */
	public void setDoc_status(Integer doc_status) {
		this.doc_status = doc_status;
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
	 * @hibernate.property column="oper_status"
	 * @return Returns the oper_status.
	 */
	public String getOper_status() {
		return oper_status;
	}

	/**
	 * @param oper_status The oper_status to set.
	 */
	public void setOper_status(String oper_status) {
		this.oper_status = oper_status;
	}

	/**
	 * @hibernate.property column="oper_user"
	 * @return Returns the oper_user.
	 */
	public String getOper_user() {
		return oper_user;
	}

	/**
	 * @param oper_user The oper_user to set.
	 */
	public void setOper_user(String oper_user) {
		this.oper_user = oper_user;
	}

	/**
	 * @hibernate.property column="oper_time"
	 * @return Returns the oper_time.
	 */
	public Date getOper_time() {
		return oper_time;
	}

	/**
	 * @param oper_time The oper_time to set.
	 */
	public void setOper_time(Date oper_time) {
		this.oper_time = oper_time;
	}


	/**
	 * @hibernate.property column="group_id"
	 * @return Returns the group_id.
	 */
	public Long getGroup_id() {
		return group_id;
	}

	/**
	 * @param group_id The group_id to set.
	 */
	public void setGroup_id(Long group_id) {
		this.group_id = group_id;
	}

	/**
	 * @hibernate.property column="approve_result"
	 * @return Returns the approve_result.
	 */
	public Integer getApprove_result() {
		return approve_result;
	}

	/**
	 * @param approve_result The approve_result to set.
	 */
	public void setApprove_result(Integer approve_result) {
		this.approve_result = approve_result;
	}

	/**
	 * @hibernate.property column="opernode_id"
	 * @return Returns the opernode_id.
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
	 * @hibernate.property column="accept_time"
	 * @return Returns the accept_time.
	 */
	public Date getAccept_time() {
		return accept_time;
	}

	/**
	 * @param accept_time The accept_time to set.
	 */
	public void setAccept_time(Date accept_time) {
		this.accept_time = accept_time;
	}

	/**
	 * @hibernate.property column="write_limit"
	 * @return Returns the write_limit.
	 */
	public Integer getWrite_limit() {
		return write_limit;
	}

	/**
	 * @param write_limit The write_limit to set.
	 */
	public void setWrite_limit(Integer write_limit) {
		this.write_limit = write_limit;
	}

	/**
	 * @hibernate.property column="huser_id"
	 * @return Returns the huser_id.
	 */
	public Long getHuser_id() {
		return huser_id;
	}

	/**
	 * @param huser_id The huser_id to set.
	 */
	public void setHuser_id(Long huser_id) {
		this.huser_id = huser_id;
	}

	/**
	 * @hibernate.property column="hWrite_limit"
	 * @return Returns the hWrite_limit.
	 */
	public Integer getHWrite_limit() {
		return hWrite_limit;
	}

	/**
	 * @param write_limit The hWrite_limit to set.
	 */
	public void setHWrite_limit(Integer write_limit) {
		hWrite_limit = write_limit;
	}
}
