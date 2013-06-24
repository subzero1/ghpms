package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta25_face_module"
 */

public class Ta25_face_module implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 447882378243191232L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta25_num"
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
 * 模块名称
 */
 private String module_name;

 /**
 * @hibernate.property column="module_name"
 * @return Returns the module_name.
 */
 public String getModule_name() {
    return module_name;
 }

 public void setModule_name(String module_name) {
    this.module_name = module_name;
  }

 /**
 * 角色ID，TA04.ID
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

 /**
 * 模块链接路径
 */
 private String module_url;

 /**
 * @hibernate.property column="module_url"
 * @return Returns the module_url.
 */
 public String getModule_url() {
    return module_url;
 }

 public void setModule_url(String module_url) {
    this.module_url = module_url;
  }

 /**
 * 界面类型：1，左侧下 2，右下
 */
 private Long ui_type;

 /**
 * @hibernate.property column="ui_type"
 * @return Returns the ui_type.
 */
 public Long getUi_type() {
    return ui_type;
 }

 public void setUi_type(Long ui_type) {
    this.ui_type = ui_type;
  }


}
