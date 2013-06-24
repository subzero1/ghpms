package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta32_node_user_limit"
 */

public class Ta32_node_user_limit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 640647761816820864L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta32_num"
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
 * 配置人员ID 
 */
 private Long user_id;

 /**
 * @hibernate.property column="user_id"
 * @return Returns the user_id.
 */
 public Long getUser_id() {
    return user_id;
 }

 public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

 /**
 * 节点标识
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
 * 待选择人员标识
 */
 private Long node_user_id;

 /**
 * @hibernate.property column="node_user_id"
 * @return Returns the node_user_id.
 */
 public Long getNode_user_id() {
    return node_user_id;
 }

 public void setNode_user_id(Long node_user_id) {
    this.node_user_id = node_user_id;
  }

 /**
 * 待选择人员 [工号]姓名
 */
 private String node_user_name;

 /**
 * @hibernate.property column="node_user_name"
 * @return Returns the node_user_name.
 */
 public String getNode_user_name() {
    return node_user_name;
 }

 public void setNode_user_name(String node_user_name) {
    this.node_user_name = node_user_name;
  }


}
