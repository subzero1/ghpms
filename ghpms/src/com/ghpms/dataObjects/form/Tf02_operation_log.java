package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-09-24
 * @hibernate.class table="Tf02_operation_log"
 */

public class Tf02_operation_log implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9321972864332450L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf02_num"
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
 * 登陆标识
 */
 private Long login_id;

 /**
 * @hibernate.property column="login_id"
 * @return Returns the login_id.
 */
 public Long getLogin_id() {
    return login_id;
 }

 public void setLogin_id(Long login_id) {
    this.login_id = login_id;
  }

 /**
 * 方法名
 */
 private String method_name;

 /**
 * @hibernate.property column="method_name"
 * @return Returns the method_name.
 */
 public String getMethod_name() {
    return method_name;
 }

 public void setMethod_name(String method_name) {
    this.method_name = method_name;
  }

 /**
 * 请求URL
 */
 private String request_url;

 /**
 * @hibernate.property column="request_url"
 * @return Returns the request_url.
 */
 public String getRequest_url() {
    return request_url;
 }

 public void setRequest_url(String request_url) {
    this.request_url = request_url;
  }

 /**
 * 起始时间
 */
 private Date start_time;

 /**
 * @hibernate.property column="start_time"
 * @return Returns the start_time.
 */
 public Date getStart_time() {
    return start_time;
 }

 public void setStart_time(Date start_time) {
    this.start_time = start_time;
  }

 /**
 * 终了时间
 */
 private Date end_time;

 /**
 * @hibernate.property column="end_time"
 * @return Returns the end_time.
 */
 public Date getEnd_time() {
    return end_time;
 }

 public void setEnd_time(Date end_time) {
    this.end_time = end_time;
  }

 /**
 * 总时间(秒)
 */
 private Long tatal_time;

 /**
 * @hibernate.property column="tatal_time"
 * @return Returns the tatal_time.
 */
 public Long getTatal_time() {
    return tatal_time;
 }

 public void setTatal_time(Long tatal_time) {
    this.tatal_time = tatal_time;
  }

 /**
 * 用户名
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
 * IP地址
 */
 private String user_address;

 /**
 * @hibernate.property column="user_address"
 * @return Returns the user_address.
 */
 public String getUser_address() {
    return user_address;
 }

 public void setUser_address(String user_address) {
    this.user_address = user_address;
  }

 /**
 * session_id
 */
 private String session_id;

 /**
 * @hibernate.property column="session_id"
 * @return Returns the session_id.
 */
 public String getSession_id() {
    return session_id;
 }

 public void setSession_id(String session_id) {
    this.session_id = session_id;
  }


}
