package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Te06_remind_queue"
 */

public class Te06_remind_queue implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 807532844746798336L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="te06_num"
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
 * 提醒内容
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

 /**
 * 提醒时间
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
 * 接收人列表name1,name2,name3
 */
 private String persons;

 /**
 * @hibernate.property column="persons"
 * @return Returns the persons.
 */
 public String getPersons() {
    return persons;
 }

 public void setPersons(String persons) {
    this.persons = persons;
  }

 /**
 * 接收人标识id1,id2,id3
 */
 private String ids;

 /**
 * @hibernate.property column="ids"
 * @return Returns the ids.
 */
 public String getIds() {
    return ids;
 }

 public void setIds(String ids) {
    this.ids = ids;
  }

 /**
 * 接收人电话tel1,tel2,tel3
 */
 private String tels;

 /**
 * @hibernate.property column="tels"
 * @return Returns the tels.
 */
 public String getTels() {
    return tels;
 }

 public void setTels(String tels) {
    this.tels = tels;
  }

 /**
 * 提醒类型（短信、系统）
 */
 private String type;

 /**
 * @hibernate.property column="type"
 * @return Returns the type.
 */
 public String getType() {
    return type;
 }

 public void setType(String type) {
    this.type = type;
  }

 /**
 * 提醒次数
 */
 private Long times;

 /**
 * @hibernate.property column="times"
 * @return Returns the times.
 */
 public Long getTimes() {
    return times;
 }

 public void setTimes(Long times) {
    this.times = times;
  }

 /**
 * 链接地址
 */
 private String uri;

 /**
 * @hibernate.property column="uri"
 * @return Returns the uri.
 */
 public String getUri() {
    return uri;
 }

 public void setUri(String uri) {
    this.uri = uri;
  }

 /**
 * 
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


}
