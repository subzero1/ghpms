package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lee.xiangyu 2012-6-1
 * @hibernate.class table="Tz07_dataobject_cfg"
 */
public class Tz07_dataobject_cfg implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5550724252993706138L;
	private Long id;
	private String obj_desc;
	private String status;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="TZ07_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="obj_desc"
	 * @return Returns the obj_desc.
	 */
	public String getObj_desc() {
		return obj_desc;
	}

	public void setObj_desc(String obj_desc) {
		this.obj_desc = obj_desc;
	}

	/**
	 * @hibernate.property column="status"
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
