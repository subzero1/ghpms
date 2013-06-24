package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.*;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="Tb17_approve"
 */
public class Tb17_approve implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1047721887493617640L;

	/**
	 * 标识
	 */
	private Long id;

	/**
	 * 项目标识
	 */
	private Long project_id;

	/**
	 * 模块ID
	 */
	private Long module_id;

	/**
	 * 审批人员ID
	 */
	private Long user_id;

	/**
	 * 审批人员ID
	 */
	private String user_name;

	/**
	 * 审批岗位
	 */
	private String station;

	/**
	 * 审批时间
	 */
	private Date oper_time;

	/**
	 * 审批意见： 4同意 5 修改 6不发表意见 7暂缓
	 *  5.审批同意时插入，或 回退时插入。
	 *  7.审批暂缓后插入。且流程所有相关表单都转移到办结里.可以恢复
	 */
	private Integer check_result;
	
	/**
	 * 审批意见的字符串表达
	 */
	private String result_str ;

	/**
	 * 审批具体意见
	 */
	private String check_idea;

	/**
	 * 节点ID
	 */
	private Long node_id;

	/**
	 * 文档ID
	 */
	private Long doc_id;

	/**
	 * 
	 */
	private Long opernode_id;



	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tb17_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="project_id"
	 * @return the project_id
	 */
	public Long getProject_id() {
		return project_id;
	}

	/**
	 * @param project_id the project_id to set
	 */
	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	/**
	 * @hibernate.property column="module_id"
	 * @return the module_id
	 */
	public Long getModule_id() {
		return module_id;
	}

	/**
	 * @param module_id the module_id to set
	 */
	public void setModule_id(Long module_id) {
		this.module_id = module_id;
	}

	/**
	 * @hibernate.property column="user_id"
	 * @return the user_id
	 */
	public Long getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * @hibernate.property column="user_name"
	 * @return Returns the user_name.
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name The user_name to set.
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	/**
	 * @hibernate.property column="station"
	 * @return the station
	 */
	public String getStation() {
		return station;
	}

	/**
	 * @param station the station to set
	 */
	public void setStation(String station) {
		this.station = station;
	}

	/**
	 * @hibernate.property column="oper_time"
	 * @return the oper_time
	 */
	public Date getOper_time() {
		return oper_time;
	}

	/**
	 * @param oper_time the oper_time to set
	 */
	public void setOper_time(Date oper_time) {
		this.oper_time = oper_time;
	}

	/**
	 * @hibernate.property column="check_result"
	 * @return the check_result
	 */
	public Integer getCheck_result() {
		return check_result;
	}

	/**
	 * @param check_result the check_result to set
	 */
	public void setCheck_result(Integer check_result) {
		this.check_result = check_result;
	}

	/**
	 * @hibernate.property column="check_idea"
	 * @return the check_idea
	 */
	public String getCheck_idea() {
		return check_idea;
	}

	/**
	 * @param check_idea the check_idea to set
	 */
	public void setCheck_idea(String check_idea) {
		this.check_idea = check_idea;
	}

	/**
	 * @hibernate.property column="node_id"
	 * @return the node_id
	 */
	public Long getNode_id() {
		return node_id;
	}

	/**
	 * @param node_id the node_id to set
	 */
	public void setNode_id(Long node_id) {
		this.node_id = node_id;
	}

	/**
	 * @hibernate.property column="doc_id"
	 * @return the doc_id
	 */
	public Long getDoc_id() {
		return doc_id;
	}

	/**
	 * @param doc_id the doc_id to set
	 */
	public void setDoc_id(Long doc_id) {
		this.doc_id = doc_id;
	}

	/**
	 * @hibernate.property column="opernode_id"
	 * @return the opernode_id
	 */
	public Long getOpernode_id() {
		return opernode_id;
	}

	/**
	 * @param opernode_id the opernode_id to set
	 */
	public void setOpernode_id(Long opernode_id) {
		this.opernode_id = opernode_id;
	}

	/**
	 * @hibernate.property column="result_str"
	 * @return Returns the result_str.
	 */
	public String getResult_str() {
		return result_str;
	}

	/**
	 * @param result_str The result_str to set.
	 */
	public void setResult_str(String result_str) {
		this.result_str = result_str;
	}
}
