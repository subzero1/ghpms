package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mengying 2010-1-7
 * @hibernate.class table="Tb11_operflow"
 */

public class Tb11_operflow implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4479464169124692449L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 项目流程标识
	 */
	private Long project_id;
	
	/**
	 * 流程标识
	 */
	private Long flow_id;
	
	/**
	 * 名称
	 */
	private String name;	
	
	/**
	 * 流程描述
	 */
	private String description;
	
	/**
	 * 显示：代表性简要说明，或流程标识性图标
	 */
	private String display;
	
	/**
	 * 项目流程标识
	 */
	private Date start_time;
	
	/**
	 * 项目流程标识
	 */
	private Date end_time;
	
	/**
	 * 项目流程标识
	 */
	private String status;
	
	/**
	 * 项目流程标识
	 */
	private String rollback_modules;	

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
	 * @hibernate.property column="flow_id"
	 * @return the flow_id
	 */
	public Long getFlow_id() {
		return flow_id;
	}

	/**
	 * @param flow_id the flow_id to set
	 */
	public void setFlow_id(Long flow_id) {
		this.flow_id = flow_id;
	}

	/**
	 * @hibernate.property column="start_time"
	 * @return the start_time
	 */
	public Date getStart_time() {
		return start_time;
	}

	/**
	 * @param start_time the start_time to set
	 */
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	/**
	 * @hibernate.property column="end_time"
	 * @return the end_time
	 */
	public Date getEnd_time() {
		return end_time;
	}

	/**
	 * @param end_time the end_time to set
	 */
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	/**
	 * @hibernate.property column="status"
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tb11_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="name"
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property column="description"
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @hibernate.property column="display"
	 * @return Returns the display.
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * @param display The display to set.
	 */
	public void setDisplay(String display) {
		this.display = display;
	}

	/**
	 * @hibernate.property column="rollback_modules"
	 * @return Returns the rollback_modules.
	 */
	public String getRollback_modules() {
		return rollback_modules;
	}

	/**
	 * @param rollback_modules The rollback_modules to set.
	 */
	public void setRollback_modules(String rollback_modules) {
		this.rollback_modules = rollback_modules;
	}
}
