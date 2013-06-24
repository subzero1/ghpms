package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta15_group_node"
 */

public class Ta15_group_node implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 700619581193318016L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta15_num"
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
 * 群组标识TA05.ID
 */
 private Long group_id;

 /**
 * @hibernate.property column="group_id"
 * @return Returns the group_id.
 */
 public Long getGroup_id() {
    return group_id;
 }

 public void setGroup_id(Long group_id) {
    this.group_id = group_id;
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


}
