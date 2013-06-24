package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Te03_online"
 */

public class Te03_online implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 304290679599435584L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="te03_num"
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
 private Long up_id;

 /**
 * @hibernate.property column="up_id"
 * @return Returns the up_id.
 */
 public Long getUp_id() {
    return up_id;
 }

 public void setUp_id(Long up_id) {
    this.up_id = up_id;
  }

 /**
 * 工号
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
 * 用户名
 */
 private String aq_name;

 /**
 * @hibernate.property column="aq_name"
 * @return Returns the aq_name.
 */
 public String getAq_name() {
    return aq_name;
 }

 public void setAq_name(String aq_name) {
    this.aq_name = aq_name;
  }

 /**
 * 标题
 */
 private String title;

 /**
 * @hibernate.property column="title"
 * @return Returns the title.
 */
 public String getTitle() {
    return title;
 }

 public void setTitle(String title) {
    this.title = title;
  }

 /**
 * TA04.ID,在线提问 15、权限申请 17、系统公告 601
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
 * 发布日期
 */
 private Date aq_date;

 /**
 * @hibernate.property column="aq_date"
 * @return Returns the aq_date.
 */
 public Date getAq_date() {
    return aq_date;
 }

 public void setAq_date(Date aq_date) {
    this.aq_date = aq_date;
  }

 /**
 * 发布人IP地址
 */
 private String aq_ip;

 /**
 * @hibernate.property column="aq_ip"
 * @return Returns the aq_ip.
 */
 public String getAq_ip() {
    return aq_ip;
 }

 public void setAq_ip(String aq_ip) {
    this.aq_ip = aq_ip;
  }

 /**
 * 状态：未处理、已处理
 */
 private String status;

 /**
 * @hibernate.property column="status"
 * @return Returns the status.
 */
 public String getStatus() {
    return status;
 }

 public void setStatus(String status) {
    this.status = status;
  }

 /**
 * 阅读次数
 */
 private Long ydcs;

 /**
 * @hibernate.property column="ydcs"
 * @return Returns the ydcs.
 */
 public Long getYdcs() {
    return ydcs;
 }

 public void setYdcs(Long ydcs) {
    this.ydcs = ydcs;
  }

 /**
 * 提出人id
 */
 private Long aq_id;

 /**
 * @hibernate.property column="aq_id"
 * @return Returns the aq_id.
 */
 public Long getAq_id() {
    return aq_id;
 }

 public void setAq_id(Long aq_id) {
    this.aq_id = aq_id;
  }

 /**
 * 提出人电话
 */
 private String aq_tel;

 /**
 * @hibernate.property column="aq_tel"
 * @return Returns the aq_tel.
 */
 public String getAq_tel() {
    return aq_tel;
 }

 public void setAq_tel(String aq_tel) {
    this.aq_tel = aq_tel;
  }

 /**
 * 置顶标记位：0：正常；1：置顶
 */
 private Long flag;

 /**
 * @hibernate.property column="flag"
 * @return Returns the flag.
 */
 public Long getFlag() {
    return flag;
 }

 public void setFlag(Long flag) {
    this.flag = flag;
  }

 /**
 * 内容
 */
 private String content;

 /**
 * @hibernate.property column="content"
 * @return Returns the content.
 */
 public String getContent() {
    return content;
 }

 public void setContent(String content) {
    this.content = content;
  }


}
