package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta17_module_info"
 */

public class Ta17_module_info implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 757328312012796928L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta17_num"
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
 * 
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
 * 关联类型：一对一关联：one_on_one、明细表关联：list、选项表关联：select
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
 * 对应表名称
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
 * 对应表关联列，多条件“,”分隔，与MODULE_COLUMN一一对应
 */
 private String object_column;

 /**
 * @hibernate.property column="object_column"
 * @return Returns the object_column.
 */
 public String getObject_column() {
    return object_column;
 }

 public void setObject_column(String object_column) {
    this.object_column = object_column;
  }

 /**
 * 对应表
 */
 private String relevance_table;

 /**
 * @hibernate.property column="relevance_table"
 * @return Returns the relevance_table.
 */
 public String getRelevance_table() {
    return relevance_table;
 }

 public void setRelevance_table(String relevance_table) {
    this.relevance_table = relevance_table;
  }

 /**
 * 表单表对应列
 */
 private String relevance_column;

 /**
 * @hibernate.property column="relevance_column"
 * @return Returns the relevance_column.
 */
 public String getRelevance_column() {
    return relevance_column;
 }

 public void setRelevance_column(String relevance_column) {
    this.relevance_column = relevance_column;
  }

 /**
 * 选项表关联用于显示的字段
 */
 private String show_column;

 /**
 * @hibernate.property column="show_column"
 * @return Returns the show_column.
 */
 public String getShow_column() {
    return show_column;
 }

 public void setShow_column(String show_column) {
    this.show_column = show_column;
  }


}
