package com.ghpms.dataObjects.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tc02_area"
 */

public class Tc02_area implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 371745309550857280L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tc02_num"
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
 * 地区编码
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
 * 地区名称
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
 * 子集：如古城区 6,7,8，吴中2
 */
 private String inc_names;

 /**
 * @hibernate.property column="inc_names"
 * @return Returns the inc_names.
 */
 public String getInc_names() {
    return inc_names;
 }

 public void setInc_names(String inc_names) {
    this.inc_names = inc_names;
  }

 /**
 * 功能过虑：1 工程地区选择；2 合作单位作业区域（保证金、外协公告）；3 部门所在区域
 */
 private String type;

 /**
 * @hibernate.property column="type"
 * @return Returns the type.
 */
 public String getType() {
    return type;
 }

 public void setType(String type) {
    this.type = type;
  }

 /**
 * 顺序
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
