package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta01_dept"
 */

public class Ta01_dept implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6302767897755412L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta01_num"
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
 * 部门编码
 */
 private String code;

 /**
 * @hibernate.property column="code"
 * @return Returns the code.
 */
 public String getCode() {
    return code;
 }

 public void setCode(String code) {
    this.code = code;
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
 * 0:不可用；1：可用
 */
 private Long useflag;

 /**
 * @hibernate.property column="useflag"
 * @return Returns the useflag.
 */
 public Long getUseflag() {
    return useflag;
 }

 public void setUseflag(Long useflag) {
    this.useflag = useflag;
  }

 /**
 * 所属地区
 */
 private String area_name;

 /**
 * @hibernate.property column="area_name"
 * @return Returns the area_name.
 */
 public String getArea_name() {
    return area_name;
 }

 public void setArea_name(String area_name) {
    this.area_name = area_name;
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

 private Long showflag;

 /**
  * @hibernate.property column="showflag"
  * @return Returns the showflag.
  */
public Long getShowflag() {
	return showflag;
}

public void setShowflag(Long showflag) {
	this.showflag = showflag;
}
/**
 * 上级部门
 */
private Long parent_dept;

public Long getParent_dept() {
	return parent_dept;
}

public void setParent_dept(Long parent_dept) {
	this.parent_dept = parent_dept;
}

 
 
}
