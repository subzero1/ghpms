package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Te08_message"
 */

public class Te08_message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 400609628639217920L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="te08_num"
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
 * 发送人
 */
 private String fsr;

 /**
 * @hibernate.property column="fsr"
 * @return Returns the fsr.
 */
 public String getFsr() {
    return fsr;
 }

 public void setFsr(String fsr) {
    this.fsr = fsr;
  }

 /**
 * 接收人
 */
 private String jsr;

 /**
 * @hibernate.property column="jsr"
 * @return Returns the jsr.
 */
 public String getJsr() {
    return jsr;
 }

 public void setJsr(String jsr) {
    this.jsr = jsr;
  }

 /**
 * 发送时间
 */
 private Date fssj;

 /**
 * @hibernate.property column="fssj"
 * @return Returns the fssj.
 */
 public Date getFssj() {
    return fssj;
 }

 public void setFssj(Date fssj) {
    this.fssj = fssj;
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
 * 发送状态
 */
 private String state;

 /**
 * @hibernate.property column="state"
 * @return Returns the state.
 */
 public String getState() {
    return state;
 }

 public void setState(String state) {
    this.state = state;
  }


}
