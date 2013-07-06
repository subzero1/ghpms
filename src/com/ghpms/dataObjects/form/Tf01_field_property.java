package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-07-06
 * @hibernate.class table="Tf01_field_property"
 */

public class Tf01_field_property implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 532699878625140672L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf01_num"
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
 * field标识
 */
 private Long field_id;

 /**
 * @hibernate.property column="field_id"
 * @return Returns the field_id.
 */
 public Long getField_id() {
    return field_id;
 }

 public void setField_id(Long field_id) {
    this.field_id = field_id;
  }

 /**
 * sql語句
 */
 private String p_sql;

 /**
 * @hibernate.property column="p_sql"
 * @return Returns the p_sql.
 */
 public String getP_sql() {
    return p_sql;
 }

 public void setP_sql(String p_sql) {
    this.p_sql = p_sql;
  }


}
