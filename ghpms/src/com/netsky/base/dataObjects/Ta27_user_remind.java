package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta27_user_remind"
 */

public class Ta27_user_remind implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 513861851616831296L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta27_num"
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
 * 用户标识ta03.id
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
 * 提醒类型（1：预警超时文档；2：待办回复文档；3：在线提问权限申请管理员处理完成）
 */
 private Long remind_type;

 /**
 * @hibernate.property column="remind_type"
 * @return Returns the remind_type.
 */
 public Long getRemind_type() {
    return remind_type;
 }

 public void setRemind_type(Long remind_type) {
    this.remind_type = remind_type;
  }

 /**
 * 手机提醒标志位（0：不提醒； 1：提醒）
 */
 private Long mobile_flag;

 /**
 * @hibernate.property column="mobile_flag"
 * @return Returns the mobile_flag.
 */
 public Long getMobile_flag() {
    return mobile_flag;
 }

 public void setMobile_flag(Long mobile_flag) {
    this.mobile_flag = mobile_flag;
  }

 /**
 * 短消息提醒标志位（0：不提醒； 1：提醒）
 */
 private Long message_flag;

 /**
 * @hibernate.property column="message_flag"
 * @return Returns the message_flag.
 */
 public Long getMessage_flag() {
    return message_flag;
 }

 public void setMessage_flag(Long message_flag) {
    this.message_flag = message_flag;
  }


}
