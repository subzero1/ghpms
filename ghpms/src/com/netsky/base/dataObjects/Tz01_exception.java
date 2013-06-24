package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tz01_exception"
 */

public class Tz01_exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 765283360075834752L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tz01_num"
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
 private Date optime;

 /**
 * @hibernate.property column="optime"
 * @return Returns the optime.
 */
 public Date getOptime() {
    return optime;
 }

 public void setOptime(Date optime) {
    this.optime = optime;
  }

 /**
 * 
 */
 private String opclass;

 /**
 * @hibernate.property column="opclass"
 * @return Returns the opclass.
 */
 public String getOpclass() {
    return opclass;
 }

 public void setOpclass(String opclass) {
    this.opclass = opclass;
  }

 /**
 * 
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
 * 
 */
 private String stacktrace;

 /**
 * @hibernate.property column="stacktrace"
 * @return Returns the stacktrace.
 */
 public String getStacktrace() {
    return stacktrace;
 }

 public void setStacktrace(String stacktrace) {
    this.stacktrace = stacktrace;
  }


}
