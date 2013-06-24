package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * 流程终止申请表
 * @class name:com.netsky.base.dataObjects.Tb19_suspendapply
 * @author Administrator Aug 12, 2010
 * @hibernate.class table="Tb19_suspendapply"
 */

public class Tb19_suspendapply implements Serializable{

	private static final long serialVersionUID = 5625706464819635671L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 项目标识
	 */
	private Long project_id;
	
	/**
	 *申请时间 
	 */
	private Date time;
	
	/**
	 * 申请原因
	 */
	private String reason ;
	
	/**
	 * 申请人员标识
	 */
	private Long user_id;
	
	/**
	 * 申请人员
	 */
	private String user_name;
	
	/**
	 * 申请节点标识
	 */
	private Long node_id;
	
	/**
	 * 申请节点名称
	 */
	private String node_name;
	
	/**
	 * 申请项目状态：阶段[状态]
	 */
	private String project_status;
	
	/**
	 * 处理人员
	 */
	private String checker;
	
	/**
	 * 处理时间
	 */
	private Date check_time ;
	
	/**
	 * 处理结果
	 */
	private String check_result;
	
	/**
	 * 处理意见
	 */
	private String check_idear;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tb19_NUM"
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
	 * @hibernate.property column="time"
	 * @return Returns the time.
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time The time to set.
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @hibernate.property column="reason"
	 * @return Returns the reason.
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason The reason to set.
	 */
	public void setReason(String reason) {
		this.reason = reason;
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
	 * @hibernate.property column="node_name"
	 * @return Returns the node_name.
	 */
	public String getNode_name() {
		return node_name;
	}

	/**
	 * @param node_name The node_name to set.
	 */
	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}

	/**
	 * @hibernate.property column="project_status"
	 * @return Returns the project_status.
	 */
	public String getProject_status() {
		return project_status;
	}

	/**
	 * @param project_status The project_status to set.
	 */
	public void setProject_status(String project_status) {
		this.project_status = project_status;
	}

	/**
	 * @hibernate.property column="checker"
	 * @return Returns the checker.
	 */
	public String getChecker() {
		return checker;
	}

	/**
	 * @param checker The checker to set.
	 */
	public void setChecker(String checker) {
		this.checker = checker;
	}

	/**
	 * @hibernate.property column="check_time"
	 * @return Returns the check_time.
	 */
	public Date getCheck_time() {
		return check_time;
	}

	/**
	 * @param check_time The check_time to set.
	 */
	public void setCheck_time(Date check_time) {
		this.check_time = check_time;
	}

	/**
	 * @hibernate.property column="check_result"
	 * @return Returns the check_result.
	 */
	public String getCheck_result() {
		return check_result;
	}

	/**
	 * @param check_result The check_result to set.
	 */
	public void setCheck_result(String check_result) {
		this.check_result = check_result;
	}

	/**
	 * @hibernate.property column="check_idear"
	 * @return Returns the check_idear.
	 */
	public String getCheck_idear() {
		return check_idear;
	}

	/**
	 * @param check_idear The check_idear to set.
	 */
	public void setCheck_idear(String check_idear) {
		this.check_idear = check_idear;
	}
	

	
	
}
