package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta08_reportfield"
 */

public class Ta08_reportfield implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 613628051129338624L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta08_num"
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
 * 默认查询显示字段
 */
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
 * 统计查询类别
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
 * 查询类型 1：关键字； 2：复选 ；3：金额 ；4：人员； 5：日期
 */
 private Long searchtype;

 /**
 * @hibernate.property column="searchtype"
 * @return Returns the searchtype.
 */
 public Long getSearchtype() {
    return searchtype;
 }

 public void setSearchtype(Long searchtype) {
    this.searchtype = searchtype;
  }

 /**
 * 查询标志位  1：查询条件
 */
 private Long searchflag;

 /**
 * @hibernate.property column="searchflag"
 * @return Returns the searchflag.
 */
 public Long getSearchflag() {
    return searchflag;
 }

 public void setSearchflag(Long searchflag) {
    this.searchflag = searchflag;
  }

 /**
 * 统计标志位  1：统计条件
 */
 private Long reportflag;

 /**
 * @hibernate.property column="reportflag"
 * @return Returns the reportflag.
 */
 public Long getReportflag() {
    return reportflag;
 }

 public void setReportflag(Long reportflag) {
    this.reportflag = reportflag;
  }

 /**
 * 统计选择项  1：选择项
 */
 private Long statisticflag;

 /**
 * @hibernate.property column="statisticflag"
 * @return Returns the statisticflag.
 */
 public Long getStatisticflag() {
    return statisticflag;
 }

 public void setStatisticflag(Long statisticflag) {
    this.statisticflag = statisticflag;
  }

 /**
 * 选择路径，当SEARCHTYPE＝2时有效
 */
 private String selecturl;

 /**
 * @hibernate.property column="selecturl"
 * @return Returns the selecturl.
 */
 public String getSelecturl() {
    return selecturl;
 }

 public void setSelecturl(String selecturl) {
    this.selecturl = selecturl;
  }

 /**
 * 综合报表查询列宽度
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
 * 综合报表查询列对齐方式
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


}
