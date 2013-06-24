package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Te05_task_remind"
 */

public class Te05_task_remind implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 272352742652988160L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="te05_num"
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
 * 备注
 */
 private String remark;

 /**
 * @hibernate.property column="remark"
 * @return Returns the remark.
 */
 public String getRemark() {
    return remark;
 }

 public void setRemark(String remark) {
    this.remark = remark;
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
 * 下次提醒时间
 */
 private Date next_time;

 /**
 * @hibernate.property column="next_time"
 * @return Returns the next_time.
 */
 public Date getNext_time() {
    return next_time;
 }

 public void setNext_time(Date next_time) {
    this.next_time = next_time;
  }

 /**
 * 接收人列表
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
 * 接收人标识
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
 * 接收人电话
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
 * 发送频度（不重复、按天、按工作日）
 */
 private String frequence;

 /**
 * @hibernate.property column="frequence"
 * @return Returns the frequence.
 */
 public String getFrequence() {
    return frequence;
 }

 public void setFrequence(String frequence) {
    this.frequence = frequence;
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
 * 已经提醒次数
 */
 private Long fact_times;

 /**
 * @hibernate.property column="fact_times"
 * @return Returns the fact_times.
 */
 public Long getFact_times() {
    return fact_times;
 }

 public void setFact_times(Long fact_times) {
    this.fact_times = fact_times;
  }

 /**
 * 提醒关键字（名称）
 */
 private String remind_key;

 /**
 * @hibernate.property column="remind_key"
 * @return Returns the remind_key.
 */
 public String getRemind_key() {
    return remind_key;
 }

 public void setRemind_key(String remind_key) {
    this.remind_key = remind_key;
  }

 /**
 * 扩展参数：比如【remind_key=表单提醒，则EXT_PARAMS=[project_id=${project_id}][doc_id=${doc_id}][user_id=${user_id}])】
 */
 private String ext_params;

 /**
 * @hibernate.property column="ext_params"
 * @return Returns the ext_params.
 */
 public String getExt_params() {
    return ext_params;
 }

 public void setExt_params(String ext_params) {
    this.ext_params = ext_params;
  }


}
