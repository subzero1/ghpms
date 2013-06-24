package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta19_flow_phase"
 */

public class Ta19_flow_phase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 275923729753473472L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta19_num"
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
 * 流程标识
 */
 private Long flow_id;

 /**
 * @hibernate.property column="flow_id"
 * @return Returns the flow_id.
 */
 public Long getFlow_id() {
    return flow_id;
 }

 public void setFlow_id(Long flow_id) {
    this.flow_id = flow_id;
  }

 /**
 * 项目进度标识
 */
 private Long phase_id;

 /**
 * @hibernate.property column="phase_id"
 * @return Returns the phase_id.
 */
 public Long getPhase_id() {
    return phase_id;
 }

 public void setPhase_id(Long phase_id) {
    this.phase_id = phase_id;
  }

 /**
 * 是否当前流程的起始阶段
 */
 private String isstart;

 /**
 * @hibernate.property column="isstart"
 * @return Returns the isstart.
 */
 public String getIsstart() {
    return isstart;
 }

 public void setIsstart(String isstart) {
    this.isstart = isstart;
  }


}
