package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta20_phase_node"
 */

public class Ta20_phase_node implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 451142804696139968L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta20_num"
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
 * 流程标识
 */
 private Long flow_id;

 /**
 * @hibernate.property column="flow_id"
 * @return Returns the flow_id.
 */
 public Long getFlow_id() {
    return flow_id;
 }

 public void setFlow_id(Long flow_id) {
    this.flow_id = flow_id;
  }

 /**
 * 生成项目进度表的节点
 */
 private Long create_node_id;

 /**
 * @hibernate.property column="create_node_id"
 * @return Returns the create_node_id.
 */
 public Long getCreate_node_id() {
    return create_node_id;
 }

 public void setCreate_node_id(Long create_node_id) {
    this.create_node_id = create_node_id;
  }

 /**
 * 第一次配置项目进度表的节点
 */
 private Long config_node_id;

 /**
 * @hibernate.property column="config_node_id"
 * @return Returns the config_node_id.
 */
 public Long getConfig_node_id() {
    return config_node_id;
 }

 public void setConfig_node_id(Long config_node_id) {
    this.config_node_id = config_node_id;
  }


}
