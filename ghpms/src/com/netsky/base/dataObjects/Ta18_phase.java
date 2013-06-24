package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta18_phase"
 */

public class Ta18_phase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 907404597692329216L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta18_num"
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
 * 进度名称
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
 * 排序
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

 /**
 * ta06.id 串
 */
 private String module_ids;

 /**
 * @hibernate.property column="module_ids"
 * @return Returns the module_ids.
 */
 public String getModule_ids() {
    return module_ids;
 }

 public void setModule_ids(String module_ids) {
    this.module_ids = module_ids;
  }

 /**
 * 节点名称串，此字段与modules结合实现超时项目相关人员
 */
 private String node_names;

 /**
 * @hibernate.property column="node_names"
 * @return Returns the node_names.
 */
 public String getNode_names() {
    return node_names;
 }

 public void setNode_names(String node_names) {
    this.node_names = node_names;
  }


}
