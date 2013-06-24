package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tz02_oper_log"
 */

public class Tz02_oper_log implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 375131605705795200L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tz02_num"
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
 private String login_id;

 /**
 * @hibernate.property column="login_id"
 * @return Returns the login_id.
 */
 public String getLogin_id() {
    return login_id;
 }

 public void setLogin_id(String login_id) {
    this.login_id = login_id;
  }

 /**
 * 
 */
 private String opuser;

 /**
 * @hibernate.property column="opuser"
 * @return Returns the opuser.
 */
 public String getOpuser() {
    return opuser;
 }

 public void setOpuser(String opuser) {
    this.opuser = opuser;
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
 private String opdesc;

 /**
 * @hibernate.property column="opdesc"
 * @return Returns the opdesc.
 */
 public String getOpdesc() {
    return opdesc;
 }

 public void setOpdesc(String opdesc) {
    this.opdesc = opdesc;
  }

 /**
 * 
 */
 private String optype;

 /**
 * @hibernate.property column="optype"
 * @return Returns the optype.
 */
 public String getOptype() {
    return optype;
 }

 public void setOptype(String optype) {
    this.optype = optype;
  }

 /**
 * 
 */
 private String optable;

 /**
 * @hibernate.property column="optable"
 * @return Returns the optable.
 */
 public String getOptable() {
    return optable;
 }

 public void setOptable(String optable) {
    this.optable = optable;
  }

 /**
 * 
 */
 private String old_data;

 /**
 * @hibernate.property column="old_data"
 * @return Returns the old_data.
 */
 public String getOld_data() {
    return old_data;
 }

 public void setOld_data(String old_data) {
    this.old_data = old_data;
  }

 /**
 * 
 */
 private String new_data;

 /**
 * @hibernate.property column="new_data"
 * @return Returns the new_data.
 */
 public String getNew_data() {
    return new_data;
 }

 public void setNew_data(String new_data) {
    this.new_data = new_data;
  }


}
