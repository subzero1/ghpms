package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta31_worklist_cfg"
 */

public class Ta31_worklist_cfg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 887527504497236992L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta31_num"
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
 * 用户标识ta03.id
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

 /**
 * 字段标识 TA07.ID
 */
 private Long field_id;

 /**
 * @hibernate.property column="field_id"
 * @return Returns the field_id.
 */
 public Long getField_id() {
    return field_id;
 }

 public void setField_id(Long field_id) {
    this.field_id = field_id;
  }

 /**
 * 排序号
 */
 private Integer ord;

 /**
 * @hibernate.property column="ord"
 * @return Returns the ord.
 */
 public Integer getOrd() {
    return ord;
 }

 public void setOrd(Integer ord) {
    this.ord = ord;
  }


}
