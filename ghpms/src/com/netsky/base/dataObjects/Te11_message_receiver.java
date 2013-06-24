package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-05-25
 * @hibernate.class table="Te11_message_receiver"
 */

public class Te11_message_receiver implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 395681511217326528L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="te11_num"
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
 private Long msg_id;

 /**
 * @hibernate.property column="msg_id"
 * @return Returns the msg_id.
 */
 public Long getMsg_id() {
    return msg_id;
 }

 public void setMsg_id(Long msg_id) {
    this.msg_id = msg_id;
  }

 /**
 * 阅读人标识
 */
 private Long reader_id;

 /**
 * @hibernate.property column="reader_id"
 * @return Returns the reader_id.
 */
 public Long getReader_id() {
    return reader_id;
 }

 public void setReader_id(Long reader_id) {
    this.reader_id = reader_id;
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

 /**
 * 阅读标识
 */
 private Long read_flag;

 /**
 * @hibernate.property column="read_flag"
 * @return Returns the read_flag.
 */
 public Long getRead_flag() {
    return read_flag;
 }

 public void setRead_flag(Long read_flag) {
    this.read_flag = read_flag;
  }

 /**
 * 删除标识
 */
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


}
