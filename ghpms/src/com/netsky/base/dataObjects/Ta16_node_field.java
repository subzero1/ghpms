package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta16_node_field"
 */

public class Ta16_node_field implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 687019384285182592L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta16_num"
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
 * 节点标识TB02.ID
 */
 private Long node_id;

 /**
 * @hibernate.property column="node_id"
 * @return Returns the node_id.
 */
 public Long getNode_id() {
    return node_id;
 }

 public void setNode_id(Long node_id) {
    this.node_id = node_id;
  }

 /**
 * 字段标识TA07.ID
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


}
