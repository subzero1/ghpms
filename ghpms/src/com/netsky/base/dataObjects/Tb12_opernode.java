package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mengying 2010-1-7
 * @hibernate.class table="Tb12_opernode"
 */

public class Tb12_opernode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4562357031803704060L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 项目ID
	 */
	private Long project_id;
	
	/**
	 * 流程对应表单ID
	 */
	private Long doc_id;
	
	/**
	 * 当前表单类别 标识 ID
	 */
	private Long module_id;	
	
	/**
	 * 节点ID
	 */
	private Long node_id;
	
	/**
	 * 节点 名称
	 */
	private String node_name;	
	
	/**
	 * 节点 名称
	 */
	private Integer node_type;	
	
	/**
	 * 节点状态：0 待办；1 新建；2 在办；3  发送；4 回复同意； 5 回复修改； 6 同意受理 ；7 暂缓； 8  办结
	 */
	private Integer node_status;
	
	/**
	 * 开始时间
	 */
	private Date start_time;
	
	/**
	 * 结束时间
	 */
	private Date end_time;
	
	/**
	 * 第几次经过该节点 （ 当前节点对应tb15中tb12_ord,当前节点发送记录（tb13)中tb12_ord ,ord三者一至）
	 */
	private Long ord;
	
	/**
	 * 修改节点：node_id ；回退节点：回退源；平常节点：-1
	 */
	private Long modify_node;
	
	/**
	 * 可回退节点
	 */
	private String rollback_nodes;
	
	/**
	 * 扩展的字段
	 */
	private String node_ext;
	
	/**
	 * 本节点是否为子流程根节点。一个表单可能有多个根节点（回退引起）
	 */
	private String cflow_root="0";
	

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tb12_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="project_id"
	 * @return the project_id
	 */
	public Long getProject_id() {
		return project_id;
	}

	/**
	 * @param project_id the project_id to set
	 */
	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	/**
	 * @hibernate.property column="doc_id"
	 * @return the doc_id
	 */
	public Long getDoc_id() {
		return doc_id;
	}

	/**
	 * @param doc_id the doc_id to set
	 */
	public void setDoc_id(Long doc_id) {
		this.doc_id = doc_id;
	}

	/**
	 * @hibernate.property column="node_id"
	 * @return the node_id
	 */
	public Long getNode_id() {
		return node_id;
	}

	/**
	 * @param node_id the node_id to set
	 */
	public void setNode_id(Long node_id) {
		this.node_id = node_id;
	}

	/**
	 * @hibernate.property column="ord"
	 * @return the ord
	 */
	public Long getOrd() {
		return ord;
	}

	/**
	 * @param ord the ord to set
	 */
	public void setOrd(Long ord) {
		this.ord = ord;
	}

	/**
	 * @hibernate.property column="node_status"
	 * @return the node_status
	 */
	public Integer getNode_status() {
		return node_status;
	}

	/**
	 * @param node_status the node_status to set
	 */
	public void setNode_status(Integer node_status) {
		this.node_status = node_status;
	}

	/**
	 * @hibernate.property column="start_time"
	 * @return the start_time
	 */
	public Date getStart_time() {
		return start_time;
	}

	/**
	 * @param start_time the start_time to set
	 */
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	/**
	 * @hibernate.property column="end_time"
	 * @return the end_time
	 */
	public Date getEnd_time() {
		return end_time;
	}

	/**
	 * @param end_time the end_time to set
	 */
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	/**
	 * @hibernate.property column="modify_node"
	 * @return the modify_node
	 */
	public Long getModify_node() {
		return modify_node;
	}

	/**
	 * @param modify_node the modify_node to set
	 */
	public void setModify_node(Long modify_node) {
		this.modify_node = modify_node;
	}

	/**
	 * @hibernate.property column="node_name"
	 * @return the node_name
	 */
	public String getNode_name() {
		return node_name;
	}

	/**
	 * @param node_name the node_name to set
	 */
	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}

	/**
	 * @hibernate.property column="module_id"
	 * @return the module_id
	 */
	public Long getModule_id() {
		return module_id;
	}

	/**
	 * @param module_id the module_id to set
	 */
	public void setModule_id(Long module_id) {
		this.module_id = module_id;
	}

	/**
	 * @hibernate.property column="node_type"
	 * @return Returns the node_type.
	 */
	public Integer getNode_type() {
		return node_type;
	}

	/**
	 * @param node_type The node_type to set.
	 */
	public void setNode_type(Integer node_type) {
		this.node_type = node_type;
	}

	/**
	 * @hibernate.property column="rollback_nodes"
	 * @return Returns the rollback_nodes.
	 */
	public String getRollback_nodes() {
		return rollback_nodes;
	}

	/**
	 * @param rollback_nodes The rollback_nodes to set.
	 */
	public void setRollback_nodes(String rollback_nodes) {
		this.rollback_nodes = rollback_nodes;
	}
	
	/**
	 * @hibernate.property column="node_ext"
	 * @return Returns the node_ext.
	 */
	public String getNode_ext() {
		return node_ext;
	}

	/**
	 * @param ext The ext to set.
	 */
	public void setNode_ext(String node_ext) {
		this.node_ext = node_ext;
	}

	/**
	 * @hibernate.property column="cflow_root"
	 * @return Returns the cflow_root.
	 */
	public String getCflow_root() {
		return cflow_root;
	}

	/**
	 * @param cflow_root The cflow_root to set.
	 */
	public void setCflow_root(String cflow_root) {
		this.cflow_root = cflow_root;
	}
}
