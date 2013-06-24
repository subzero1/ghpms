package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;
/**
 * @author lee.xiangyu 2009-12-11
 * @hibernate.class table="Tb18_project_phase"
 */

public class Tb18_project_phase implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5625706464819635671L;

	private Long id;
	
	private Long project_id;
	
	private Long phase_id;
	
	private String phase_name;
	
	private Long qx ;
	
	private Date jhkssj;
	
	private Date jhwcsj;

	private Date sjkssj;
	
	private Date sjwcsj;
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tb18_NUM"
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
	 * @hibernate.property column="project_id"
	 * @return the project_id
	 */
	public Long getProject_id() {
		return project_id;
	}

	/**
	 * @param relevance_table the project_id to set
	 */
	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	/**
	 * @hibernate.property column="phase_id"
	 * @return the phase_id
	 */
	public Long getPhase_id() {
		return phase_id;
	}

	/**
	 * @param relevance_table the phase_id to set
	 */
	public void setPhase_id(Long phase_id) {
		this.phase_id = phase_id;
	}

	/**
	 * @hibernate.property column="phase_name"
	 * @return the String
	 */
	public String getPhase_name() {
		return phase_name;
	}

	/**
	 * @param relevance_table the phase_name to set
	 */
	public void setPhase_name(String phase_name) {
		this.phase_name = phase_name;
	}

	/**
	 * @hibernate.property column="jhkssj"
	 * @return the jhkssj
	 */
	public Date getJhkssj() {
		return jhkssj;
	}

	/**
	 * @param relevance_table the jhkssj to set
	 */
	public void setJhkssj(Date jhkssj) {
		this.jhkssj = jhkssj;
	}

	/**
	 * @hibernate.property column="jhwcsj"
	 * @return the jhwcsj
	 */
	public Date getJhwcsj() {
		return jhwcsj;
	}

	/**
	 * @param relevance_table the jhwcsj to set
	 */
	public void setJhwcsj(Date jhwcsj) {
		this.jhwcsj = jhwcsj;
	}

	/**
	 * @hibernate.property column="qx"
	 * @return the qx
	 */
	public Long getQx() {
		return qx;
	}

	/**
	 * @param relevance_table the qx to set
	 */
	public void setQx(Long qx) {
		this.qx = qx;
	}

	/**
	 * @hibernate.property column="sjkssj"
	 * @return the sjkssj
	 */
	public Date getSjkssj() {
		return sjkssj;
	}

	/**
	 * @param relevance_table the sjkssj to set
	 */
	public void setSjkssj(Date sjkssj) {
		this.sjkssj = sjkssj;
	}

	/**
	 * @hibernate.property column="sjwcsj"
	 * @return the sjwcsj
	 */
	public Date getSjwcsj() {
		return sjwcsj;
	}

	/**
	 * @param relevance_table the sjwcsj to set
	 */
	public void setSjwcsj(Date sjwcsj) {
		this.sjwcsj = sjwcsj;
	}
}

