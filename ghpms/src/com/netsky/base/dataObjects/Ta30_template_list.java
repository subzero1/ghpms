package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta30_template_list"
 */

public class Ta30_template_list implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 898034170030750592L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta30_num"
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
 * 模板标识
 */
 private Long parent_id;

 /**
 * @hibernate.property column="parent_id"
 * @return Returns the parent_id.
 */
 public Long getParent_id() {
    return parent_id;
 }

 public void setParent_id(Long parent_id) {
    this.parent_id = parent_id;
  }

 /**
 * 类型(sum_field，xway，yway，select_field，key，interval)
 */
 private String type;

 /**
 * @hibernate.property column="type"
 * @return Returns the type.
 */
 public String getType() {
    return type;
 }

 public void setType(String type) {
    this.type = type;
  }

 /**
 * 关键字
 */
 private String key;

 /**
 * @hibernate.property column="key"
 * @return Returns the key.
 */
 public String getKey() {
    return key;
 }

 public void setKey(String key) {
    this.key = key;
  }

 /**
 * 值
 */
 private String value;

 /**
 * @hibernate.property column="value"
 * @return Returns the value.
 */
 public String getValue() {
    return value;
 }

 public void setValue(String value) {
    this.value = value;
  }


}
