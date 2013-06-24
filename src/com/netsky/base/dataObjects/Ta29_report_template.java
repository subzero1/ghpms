package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta29_report_template"
 */

public class Ta29_report_template implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 721255389073149440L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta29_num"
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
 * 模板类型(1:报表;2:查询)
 */
 private Long type;

 /**
 * @hibernate.property column="type"
 * @return Returns the type.
 */
 public Long getType() {
    return type;
 }

 public void setType(Long type) {
    this.type = type;
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
 * 用户姓名
 */
 private String user_name;

 /**
 * @hibernate.property column="user_name"
 * @return Returns the user_name.
 */
 public String getUser_name() {
    return user_name;
 }

 public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

 /**
 * 模板名称
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
 * 模块标识
 */
 private Long module_id;

 /**
 * @hibernate.property column="module_id"
 * @return Returns the module_id.
 */
 public Long getModule_id() {
    return module_id;
 }

 public void setModule_id(Long module_id) {
    this.module_id = module_id;
  }


}
