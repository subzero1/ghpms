package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta23_personalization"
 */

public class Ta23_personalization implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 348257816148884096L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta23_num"
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
 * 用户标识
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
 * 个性化对象标识
 */
 private Long po_id;

 /**
 * @hibernate.property column="po_id"
 * @return Returns the po_id.
 */
 public Long getPo_id() {
    return po_id;
 }

 public void setPo_id(Long po_id) {
    this.po_id = po_id;
  }

 /**
 * 类别标识：1，快捷菜单po_id对应ta04.id； 2，提醒表单po_id对应ta06.id；
 */
 private Integer type;

 /**
 * @hibernate.property column="type"
 * @return Returns the type.
 */
 public Integer getType() {
    return type;
 }

 public void setType(Integer type) {
    this.type = type;
  }


}
