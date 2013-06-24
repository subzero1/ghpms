package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta24_phase_timelimit"
 */

public class Ta24_phase_timelimit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 399174616277233152L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta24_num"
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
 * 阶段标识
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
 * 阶段名称
 */
 private String phase_name;

 /**
 * @hibernate.property column="phase_name"
 * @return Returns the phase_name.
 */
 public String getPhase_name() {
    return phase_name;
 }

 public void setPhase_name(String phase_name) {
    this.phase_name = phase_name;
  }

 /**
 * 工程类别
 */
 private String gclb;

 /**
 * @hibernate.property column="gclb"
 * @return Returns the gclb.
 */
 public String getGclb() {
    return gclb;
 }

 public void setGclb(String gclb) {
    this.gclb = gclb;
  }

 /**
 * 期限
 */
 private Long timelimit;

 /**
 * @hibernate.property column="timelimit"
 * @return Returns the timelimit.
 */
 public Long getTimelimit() {
    return timelimit;
 }

 public void setTimelimit(Long timelimit) {
    this.timelimit = timelimit;
  }


}
