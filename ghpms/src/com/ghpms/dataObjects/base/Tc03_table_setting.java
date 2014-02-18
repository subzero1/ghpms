package com.ghpms.dataObjects.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2014-02-14
 * @hibernate.class table="Tc03_table_setting"
 */

public class Tc03_table_setting implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 512852810372202880L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tc03_num"
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
 * 链接
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
 * 扩展属性
 */
 private String ext;

 /**
 * @hibernate.property column="ext"
 * @return Returns the ext.
 */
 public String getExt() {
    return ext;
 }

 public void setExt(String ext) {
    this.ext = ext;
  }

 /**
 * 排序
 */
 private Long seq;

 /**
 * @hibernate.property column="seq"
 * @return Returns the seq.
 */
 public Long getSeq() {
    return seq;
 }

 public void setSeq(Long seq) {
    this.seq = seq;
  }


}
