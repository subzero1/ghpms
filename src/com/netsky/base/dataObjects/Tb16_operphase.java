package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.*;
/**
 * @author mengying 2009-12-11
 * @hibernate.class table="Tb16_operphase"
 */
public class Tb16_operphase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8579863768232186459L;

	/**
	 * 项目ID
	 */
	private Long project_id;
	
	/**
	 * 阶段ID
	 */
	private Long phase_id;
	
	/**
	 * 开始时间
	 */
	private Date start_time;
	
	/**
	 * 结束时间
	 */
	private Date end_time;
	
	/**
	 * ????????'0' ?????'1'  ????
	 */
	private String iscur;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tb16_NUM"
	 * @return Returns the id.
	 */

	public Long getProject_id() {
		return project_id;
	}

	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	public Long getPhase_id() {
		return phase_id;
	}

	public void setPhase_id(Long phase_id) {
		this.phase_id = phase_id;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getIscur() {
		return iscur;
	}

	public void setIscur(String iscur) {
		this.iscur = iscur;
	}
}
