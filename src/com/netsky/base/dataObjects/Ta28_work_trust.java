package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta28_work_trust"
 */

public class Ta28_work_trust implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 768078429684902912L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta28_num"
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
 * 委托方ID
 */
 private Long from_userid;

 /**
 * @hibernate.property column="from_userid"
 * @return Returns the from_userid.
 */
 public Long getFrom_userid() {
    return from_userid;
 }

 public void setFrom_userid(Long from_userid) {
    this.from_userid = from_userid;
  }

 /**
 * 委托方姓名
 */
 private String from_username;

 /**
 * @hibernate.property column="from_username"
 * @return Returns the from_username.
 */
 public String getFrom_username() {
    return from_username;
 }

 public void setFrom_username(String from_username) {
    this.from_username = from_username;
  }

 /**
 * 受托方ID
 */
 private Long to_userid;

 /**
 * @hibernate.property column="to_userid"
 * @return Returns the to_userid.
 */
 public Long getTo_userid() {
    return to_userid;
 }

 public void setTo_userid(Long to_userid) {
    this.to_userid = to_userid;
  }

 /**
 * 受托方姓名
 */
 private String to_username;

 /**
 * @hibernate.property column="to_username"
 * @return Returns the to_username.
 */
 public String getTo_username() {
    return to_username;
 }

 public void setTo_username(String to_username) {
    this.to_username = to_username;
  }

 /**
 * 委托开始时间
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
 * 委托结束时间
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
 * 委托原因
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


}
