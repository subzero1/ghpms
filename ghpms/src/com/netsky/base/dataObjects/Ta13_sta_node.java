package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta13_sta_node"
 */

public class Ta13_sta_node implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 11957194630222712L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta13_num"
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
 * 岗位标识ta02.id
 */
 private Long station_id;

 /**
 * @hibernate.property column="station_id"
 * @return Returns the station_id.
 */
 public Long getStation_id() {
    return station_id;
 }

 public void setStation_id(Long station_id) {
    this.station_id = station_id;
  }

 /**
 * 节点标识ta04.id
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
