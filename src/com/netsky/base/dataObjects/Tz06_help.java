package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-09-03
 * @hibernate.class table="Tz06_help"
 */

public class Tz06_help implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 531300897140504192L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tz06_num"
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
	 * 关键字
	 */
	 private String keys;
	
	 /**
	 * @hibernate.property column="keys"
	 * @return Returns the keys.
	 */
	 public String getKeys() {
	    return keys;
	 }
	
	 public void setKeys(String keys) {
	    this.keys = keys;
	  }
	
	 /**
	 * 记录人
	 */
	 private String recordor;
	
	 /**
	 * @hibernate.property column="recordor"
	 * @return Returns the recordor.
	 */
	 public String getRecordor() {
	    return recordor;
	 }
	
	 public void setRecordor(String recordor) {
	    this.recordor = recordor;
	  }
	
	 /**
	 * 记录时间
	 */
	 private Date record_date;
	
	 /**
	 * @hibernate.property column="record_date"
	 * @return Returns the record_date.
	 */
	 public Date getRecord_date() {
	    return record_date;
	 }
	
	 public void setRecord_date(Date record_date) {
	    this.record_date = record_date;
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
