package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Te02_jlfk"
 */

public class Te02_jlfk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 232616955044743904L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="te02_num"
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
 * 
 */
 private Long project_id;

 /**
 * @hibernate.property column="project_id"
 * @return Returns the project_id.
 */
 public Long getProject_id() {
    return project_id;
 }

 public void setProject_id(Long project_id) {
    this.project_id = project_id;
  }

 /**
 * 
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

 /**
 * 
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
 * 
 */
 private String yj;

 /**
 * @hibernate.property column="yj"
 * @return Returns the yj.
 */
 public String getYj() {
    return yj;
 }

 public void setYj(String yj) {
    this.yj = yj;
  }

 /**
 * 
 */
 private Date time;

 /**
 * @hibernate.property column="time"
 * @return Returns the time.
 */
 public Date getTime() {
    return time;
 }

 public void setTime(Date time) {
    this.time = time;
  }

 /**
 * 
 */
 private Long document_id;

 /**
 * @hibernate.property column="document_id"
 * @return Returns the document_id.
 */
 public Long getDocument_id() {
    return document_id;
 }

 public void setDocument_id(Long document_id) {
    this.document_id = document_id;
  }

 /**
 * 
 */
 private String read_flag;

 /**
 * @hibernate.property column="read_flag"
 * @return Returns the read_flag.
 */
 public String getRead_flag() {
    return read_flag;
 }

 public void setRead_flag(String read_flag) {
    this.read_flag = read_flag;
  }


}
