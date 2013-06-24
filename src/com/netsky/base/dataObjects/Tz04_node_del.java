package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tz04_node_del"
 */

public class Tz04_node_del implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 397789023466151552L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tz04_num"
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
 * 操作人员工号
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
 * 操作人员姓名
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
 * 操作人员IP
 */
 private String oper_ip;

 /**
 * @hibernate.property column="oper_ip"
 * @return Returns the oper_ip.
 */
 public String getOper_ip() {
    return oper_ip;
 }

 public void setOper_ip(String oper_ip) {
    this.oper_ip = oper_ip;
  }

 /**
 * 操作时间
 */
 private Date oper_date;

 /**
 * @hibernate.property column="oper_date"
 * @return Returns the oper_date.
 */
 public Date getOper_date() {
    return oper_date;
 }

 public void setOper_date(Date oper_date) {
    this.oper_date = oper_date;
  }

 /**
 * 工程编号
 */
 private String gcbh;

 /**
 * @hibernate.property column="gcbh"
 * @return Returns the gcbh.
 */
 public String getGcbh() {
    return gcbh;
 }

 public void setGcbh(String gcbh) {
    this.gcbh = gcbh;
  }

 /**
 * 工程名称
 */
 private String gcmc;

 /**
 * @hibernate.property column="gcmc"
 * @return Returns the gcmc.
 */
 public String getGcmc() {
    return gcmc;
 }

 public void setGcmc(String gcmc) {
    this.gcmc = gcmc;
  }

 /**
 * 动作
 */
 private String action;

 /**
 * @hibernate.property column="action"
 * @return Returns the action.
 */
 public String getAction() {
    return action;
 }

 public void setAction(String action) {
    this.action = action;
  }

 /**
 * 结点标识
 */
 private Long node_id;

 /**
 * @hibernate.property column="node_id"
 * @return Returns the node_id.
 */
 public Long getNode_id() {
    return node_id;
 }

 public void setNode_id(Long node_id) {
    this.node_id = node_id;
  }

 /**
 * 结点名称
 */
 private String node_name;

 /**
 * @hibernate.property column="node_name"
 * @return Returns the node_name.
 */
 public String getNode_name() {
    return node_name;
 }

 public void setNode_name(String node_name) {
    this.node_name = node_name;
  }


}
