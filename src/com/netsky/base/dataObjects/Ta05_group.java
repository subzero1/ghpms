package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta05_group"
 */

public class Ta05_group implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 153905065049397376L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta05_num"
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
 * 群组级别
 */
 private Long grade;

 /**
 * @hibernate.property column="grade"
 * @return Returns the grade.
 */
 public Long getGrade() {
    return grade;
 }

 public void setGrade(Long grade) {
    this.grade = grade;
  }

 /**
 * 群组名称
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
 * 上一级标识
 */
 private Long up_id;

 /**
 * @hibernate.property column="up_id"
 * @return Returns the up_id.
 */
 public Long getUp_id() {
    return up_id;
 }

 public void setUp_id(Long up_id) {
    this.up_id = up_id;
  }

 /**
 * 群组描述
 */
 private String dis;

 /**
 * @hibernate.property column="dis"
 * @return Returns the dis.
 */
 public String getDis() {
    return dis;
 }

 public void setDis(String dis) {
    this.dis = dis;
  }


}
