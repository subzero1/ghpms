package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Te04_message"
 */

public class Te04_message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 137671485384500144L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="te04_num"
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
 * 发送人标识
 */
 private Long sender_id;

 /**
 * @hibernate.property column="sender_id"
 * @return Returns the sender_id.
 */
 public Long getSender_id() {
    return sender_id;
 }

 public void setSender_id(Long sender_id) {
    this.sender_id = sender_id;
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

 /**
 * 发送日期
 */
 private Date send_date;

 /**
 * @hibernate.property column="send_date"
 * @return Returns the send_date.
 */
 public Date getSend_date() {
    return send_date;
 }

 public void setSend_date(Date send_date) {
    this.send_date = send_date;
  }

 /**
 * 阅读标示 
 */
 private String read_flag;

 /**
 * @hibernate.property column="read_flag"
 * @return Returns the read_flag.
 */
 public String getRead_flag() {
    return read_flag;
 }

 public void setRead_flag(String read_flag) {
    this.read_flag = read_flag;
  }

 /**
 * 阅读人标识
 */
 private String reader_id;

 /**
 * @hibernate.property column="reader_id"
 * @return Returns the reader_id.
 */
 public String getReader_id() {
    return reader_id;
 }

 public void setReader_id(String reader_id) {
    this.reader_id = reader_id;
  }

 /**
 * 回复标识
 */
 private Long repeat_flag;

 /**
 * @hibernate.property column="repeat_flag"
 * @return Returns the repeat_flag.
 */
 public Long getRepeat_flag() {
    return repeat_flag;
 }

 public void setRepeat_flag(Long repeat_flag) {
    this.repeat_flag = repeat_flag;
  }

 /**
 * 发送状态（0：草稿箱；1：已发送；3：彻底删除）
 */
 private Long send_flag;

 /**
 * @hibernate.property column="send_flag"
 * @return Returns the send_flag.
 */
 public Long getSend_flag() {
    return send_flag;
 }

 public void setSend_flag(Long send_flag) {
    this.send_flag = send_flag;
  }

 /**
 * 删除人id串
 */
 private String receive_flag;

 /**
 * @hibernate.property column="receive_flag"
 * @return Returns the receive_flag.
 */
 public String getReceive_flag() {
    return receive_flag;
 }

 public void setReceive_flag(String receive_flag) {
    this.receive_flag = receive_flag;
  }

 /**
 * 附件个数
 */
 private Long fujian_flag;

 /**
 * @hibernate.property column="fujian_flag"
 * @return Returns the fujian_flag.
 */
 public Long getFujian_flag() {
    return fujian_flag;
 }

 public void setFujian_flag(Long fujian_flag) {
    this.fujian_flag = fujian_flag;
  }

 /**
 * 阅读人姓名
 */
 private String reader_name;

 /**
 * @hibernate.property column="reader_name"
 * @return Returns the reader_name.
 */
 public String getReader_name() {
    return reader_name;
 }

 public void setReader_name(String reader_name) {
    this.reader_name = reader_name;
  }

 private Long delete_flag;

 /**
  * @hibernate.property column="delete_flag"
  * @return Returns the delete_flag.
  */
public Long getDelete_flag() {
	return delete_flag;
}

public void setDelete_flag(Long delete_flag) {
	this.delete_flag = delete_flag;
}

private Long parent_id;

public Long getParent_id() {
	return parent_id;
}

public void setParent_id(Long parent_id) {
	this.parent_id = parent_id;
}

 
 
}
