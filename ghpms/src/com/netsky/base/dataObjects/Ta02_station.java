package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta02_station"
 */

public class Ta02_station implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 135998653964642176L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta02_num"
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
 * [1]:施工;[2]:设计;[3]:监理
 */
 private String flag;

 /**
 * @hibernate.property column="flag"
 * @return Returns the flag.
 */
 public String getFlag() {
    return flag;
 }

 public void setFlag(String flag) {
    this.flag = flag;
  }

 /**
 * 名称
 */
 private String name;

 /**
 * @hibernate.property column="name"
 * @return Returns the name.
 */
 public String getName() {
    return name;
 }

 public void setName(String name) {
    this.name = name;
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
 * 排序用
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
