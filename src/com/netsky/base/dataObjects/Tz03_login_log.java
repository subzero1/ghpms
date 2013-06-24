package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tz03_login_log"
 */

public class Tz03_login_log implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 141658334607544960L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tz03_num"
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
 * 登录人员工号
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
 * 登录人员姓名
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
 * 登录人员IP
 */
 private String login_ip;

 /**
 * @hibernate.property column="login_ip"
 * @return Returns the login_ip.
 */
 public String getLogin_ip() {
    return login_ip;
 }

 public void setLogin_ip(String login_ip) {
    this.login_ip = login_ip;
  }

 /**
 * 登录时间
 */
 private Date login_date;

 /**
 * @hibernate.property column="login_date"
 * @return Returns the login_date.
 */
 public Date getLogin_date() {
    return login_date;
 }

 public void setLogin_date(Date login_date) {
    this.login_date = login_date;
  }

 /**
 * 注销时间
 */
 private Date logout_date;

 /**
 * @hibernate.property column="logout_date"
 * @return Returns the logout_date.
 */
 public Date getLogout_date() {
    return logout_date;
 }

 public void setLogout_date(Date logout_date) {
    this.logout_date = logout_date;
  }

 /**
 * 当登录人前系统信息
 */
 private String systeminfo;

 /**
 * @hibernate.property column="systeminfo"
 * @return Returns the systeminfo.
 */
 public String getSysteminfo() {
    return systeminfo;
 }

 public void setSysteminfo(String systeminfo) {
    this.systeminfo = systeminfo;
  }


}
