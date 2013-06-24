package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta07_formfield"
 */

public class Ta07_formfield implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 201275335985254496L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta07_num"
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
 * ta06.id
 */
 private Long module_id;

 /**
 * @hibernate.property column="module_id"
 * @return Returns the module_id.
 */
 public Long getModule_id() {
    return module_id;
 }

 public void setModule_id(Long module_id) {
    this.module_id = module_id;
  }

 /**
 * dataObject 属性名称
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
 * dataObject 属性注释
 */
 private String comments;

 /**
 * @hibernate.property column="comments"
 * @return Returns the comments.
 */
 public String getComments() {
    return comments;
 }

 public void setComments(String comments) {
    this.comments = comments;
  }

 /**
 * dataObject 属性数据类型
 */
 private String datatype;

 /**
 * @hibernate.property column="datatype"
 * @return Returns the datatype.
 */
 public String getDatatype() {
    return datatype;
 }

 public void setDatatype(String datatype) {
    this.datatype = datatype;
  }

 /**
 * dataObject 属性长度；如：12.2 表示number(12,2)，10表示number(10)或varchar2(10)
 */
 private Double datalength;

 /**
 * @hibernate.property column="datalength"
 * @return Returns the datalength.
 */
 public Double getDatalength() {
    return datalength;
 }

 public void setDatalength(Double datalength) {
    this.datalength = datalength;
  }

 /**
 * N为非空，Y为空
 */
 private String nullable;

 /**
 * @hibernate.property column="nullable"
 * @return Returns the nullable.
 */
 public String getNullable() {
    return nullable;
 }

 public void setNullable(String nullable) {
    this.nullable = nullable;
  }

 /**
 * dataObject 名称含包名
 */
 private String object_name;

 /**
 * @hibernate.property column="object_name"
 * @return Returns the object_name.
 */
 public String getObject_name() {
    return object_name;
 }

 public void setObject_name(String object_name) {
    this.object_name = object_name;
  }

 /**
 * 表单查询列宽度
 */
 private Long width;

 /**
 * @hibernate.property column="width"
 * @return Returns the width.
 */
 public Long getWidth() {
    return width;
 }

 public void setWidth(Long width) {
    this.width = width;
  }

 /**
 * 表单查询列对齐方式
 */
 private String align;

 /**
 * @hibernate.property column="align"
 * @return Returns the align.
 */
 public String getAlign() {
    return align;
 }

 public void setAlign(String align) {
    this.align = align;
  }

 /**
 * 默认显示列：1显示，0不显示
 */
 private Long show_flag;

 /**
 * @hibernate.property column="show_flag"
 * @return Returns the show_flag.
 */
 public Long getShow_flag() {
    return show_flag;
 }

 public void setShow_flag(Long show_flag) {
    this.show_flag = show_flag;
  }

 /**
 * 查询列：1显示，0不显示
 */
 private Long search_flag;

 /**
 * @hibernate.property column="search_flag"
 * @return Returns the search_flag.
 */
 public Long getSearch_flag() {
    return search_flag;
 }

 public void setSearch_flag(Long search_flag) {
    this.search_flag = search_flag;
  }

 /**
 * 排序列：1显示，0不显示
 */
 private Long order_flag;

 /**
 * @hibernate.property column="order_flag"
 * @return Returns the order_flag.
 */
 public Long getOrder_flag() {
    return order_flag;
 }

 public void setOrder_flag(Long order_flag) {
    this.order_flag = order_flag;
  }

 /**
 * 字段显示顺序
 */
 private Long ord;

 /**
 * @hibernate.property column="ord"
 * @return Returns the ord.
 */
 public Long getOrd() {
    return ord;
 }

 public void setOrd(Long ord) {
    this.ord = ord;
  }

 /**
 * 日期查询列：1显示，0不显示
 */
 private Long date_flag;

 /**
 * @hibernate.property column="date_flag"
 * @return Returns the date_flag.
 */
 public Long getDate_flag() {
    return date_flag;
 }

 public void setDate_flag(Long date_flag) {
    this.date_flag = date_flag;
  }

 /**
 * 字段是否属于明细表：1是
 */
 private Long isdetail;

 /**
 * @hibernate.property column="isdetail"
 * @return Returns the isdetail.
 */
 public Long getIsdetail() {
    return isdetail;
 }

 public void setIsdetail(Long isdetail) {
    this.isdetail = isdetail;
  }


}
