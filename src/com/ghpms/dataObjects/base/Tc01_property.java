package com.ghpms.dataObjects.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tc01_property"
 */

public class Tc01_property implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 874553105124825088L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tc01_num"
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
 * 属性名称
 */
 private String name;

 /**
 * @hibernate.property column="name"
 * @return Returns the name.
 */
 public String getName() {
    return name;
 }

 public void setName(String name) {
    this.name = name;
  }

 /**
 * 属性分类
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
 * 局点性质：[1]报规划岗
 */
 private String flag;

 /**
 * @hibernate.property column="flag"
 * @return Returns the flag.
 */
 public String getFlag() {
    return flag;
 }

 public void setFlag(String flag) {
    this.flag = flag;
  }

 private String ext_col;

public String getExt_col() {
	return ext_col;
}

public void setExt_col(String ext_col) {
	this.ext_col = ext_col;
}
}
