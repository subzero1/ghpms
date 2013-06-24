package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta11_sta_user"
 */

public class Ta11_sta_user implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 499619635675746496L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta11_num"
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
 * 岗位标识TA02.ID
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
 * 用户标识TA03.ID
 */
 private Long user_id;

 /**
 * @hibernate.property column="user_id"
 * @return Returns the user_id.
 */
 public Long getUser_id() {
    return user_id;
 }

 public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }


}
