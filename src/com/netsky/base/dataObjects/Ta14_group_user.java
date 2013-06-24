package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta14_group_user"
 */

public class Ta14_group_user implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 846541992377129984L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta14_num"
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
 * 群组标识TA05.ID
 */
 private Long group_id;

 /**
 * @hibernate.property column="group_id"
 * @return Returns the group_id.
 */
 public Long getGroup_id() {
    return group_id;
 }

 public void setGroup_id(Long group_id) {
    this.group_id = group_id;
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
