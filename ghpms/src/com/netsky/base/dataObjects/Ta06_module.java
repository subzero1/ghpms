package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta06_module"
 */

public class Ta06_module implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 134986322280696736L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta06_num"
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
 * 模块名称
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
 * 模块类型：1：表单；2：其他
 */
 private Long type;

 /**
 * @hibernate.property column="type"
 * @return Returns the type.
 */
 public Long getType() {
    return type;
 }

 public void setType(Long type) {
    this.type = type;
  }

 /**
 * 项目信息表
 */
 private String project_table;

 /**
 * @hibernate.property column="project_table"
 * @return Returns the project_table.
 */
 public String getProject_table() {
    return project_table;
 }

 public void setProject_table(String project_table) {
    this.project_table = project_table;
  }

 /**
 * 备注
 */
 private String form_table;

 /**
 * @hibernate.property column="form_table"
 * @return Returns the form_table.
 */
 public String getForm_table() {
    return form_table;
 }

 public void setForm_table(String form_table) {
    this.form_table = form_table;
  }

 /**
 * 表单URL
 */
 private String form_url;

 /**
 * @hibernate.property column="form_url"
 * @return Returns the form_url.
 */
 public String getForm_url() {
    return form_url;
 }

 public void setForm_url(String form_url) {
    this.form_url = form_url;
  }

 /**
 * 表单建成、编号前缀
 */
 private String form_name;

 /**
 * @hibernate.property column="form_name"
 * @return Returns the form_name.
 */
 public String getForm_name() {
    return form_name;
 }

 public void setForm_name(String form_name) {
    this.form_name = form_name;
  }

 /**
 * 表单图片
 */
 private String form_pic;

 /**
 * @hibernate.property column="form_pic"
 * @return Returns the form_pic.
 */
 public String getForm_pic() {
    return form_pic;
 }

 public void setForm_pic(String form_pic) {
    this.form_pic = form_pic;
  }

 /**
 * 表单附件 TA06.ID
 */
 private String slave_module;

 /**
 * @hibernate.property column="slave_module"
 * @return Returns the slave_module.
 */
 public String getSlave_module() {
    return slave_module;
 }

 public void setSlave_module(String slave_module) {
    this.slave_module = slave_module;
  }

 /**
 * 辅助表名列表:T1,T2,T3,按PROJECT_ID关联
 */
 private String aux_table;

 /**
 * @hibernate.property column="aux_table"
 * @return Returns the aux_table.
 */
 public String getAux_table() {
    return aux_table;
 }

 public void setAux_table(String aux_table) {
    this.aux_table = aux_table;
  }

 /**
 * 明细表名列表:T1,T2,T3,按PARENT_ID关联
 */
 private String detail_table;

 /**
 * @hibernate.property column="detail_table"
 * @return Returns the detail_table.
 */
 public String getDetail_table() {
    return detail_table;
 }

 public void setDetail_table(String detail_table) {
    this.detail_table = detail_table;
  }

 /**
 * 排序字段
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
