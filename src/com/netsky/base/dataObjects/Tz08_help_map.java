package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-09-04
 * @hibernate.class table="Tz08_help_map"
 */

public class Tz08_help_map implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 409501100405362240L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tz08_num"
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
	 * 在线帮助标识
	 */
	 private Long help_id;
	
	 /**
	 * @hibernate.property column="help_id"
	 * @return Returns the help_id.
	 */
	 public Long getHelp_id() {
	    return help_id;
	 }
	
	 public void setHelp_id(Long help_id) {
	    this.help_id = help_id;
	  }


}
