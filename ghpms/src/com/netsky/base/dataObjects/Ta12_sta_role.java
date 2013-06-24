package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta12_sta_role"
 */

public class Ta12_sta_role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 893640635612827008L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta12_num"
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
 * 岗位标识ta02.id
 */
 private Long station_id;

 /**
 * @hibernate.property column="station_id"
 * @return Returns the station_id.
 */
 public Long getStation_id() {
    return station_id;
 }

 public void setStation_id(Long station_id) {
    this.station_id = station_id;
  }

 /**
 * 角色标识ta04.id
 */
 private Long role_id;

 /**
 * @hibernate.property column="role_id"
 * @return Returns the role_id.
 */
 public Long getRole_id() {
    return role_id;
 }

 public void setRole_id(Long role_id) {
    this.role_id = role_id;
  }


}
