package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author mengying 2010-1-7
 * @hibernate.class table="Tb02_node"
 */

public class Tb02_node implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 所属流程标识
	 */
	private Long flow_id;
	
	/**
	 * 类别：1 审批型；2 非审批型；3 会签型
	 */
	private Integer node_type;
	
	/**
	 * 操作时限
	 */
	private Double oper_days;
	
	
	/**
	 * 附带数据
	 */
	private String salve_data;
	
	
	/**
	 * 可后退节点
	 */
	private String rollback_nodes;
	
	/**
	 * 流程显示方向：horizontal　横向；vertical竖向
	 */
	private String disp;
	
	
	/**
	 * 是否为流程开始节点 Y 为可以做开始节点，N为不可以
	 */
	private String canStart;
	
	/**
	 * 可后退节点
	 */
	private String remark;
	
	/**
	 * 扩展的字段
	 */
	private String ext;	

	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tb02_NUM"
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
	 * @hibernate.property column="name"
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property column="flow_id"
	 * @return the flow_id
	 */
	public Long getFlow_id() {
		return flow_id;
	}

	/**
	 * @param flow_id the flow_id to set
	 */
	public void setFlow_id(Long flow_id) {
		this.flow_id = flow_id;
	}

	/**
	 * @hibernate.property column="node_type"
	 * @return the node_type
	 */
	public Integer getNode_type() {
		return node_type;
	}

	/**
	 * @param node_type the node_type to set
	 */
	public void setNode_type(Integer node_type) {
		this.node_type = node_type;
	}

	/**
	 * @hibernate.property column="oper_days"
	 * @return the oper_days
	 */
	public Double getOper_days() {
		return oper_days;
	}

	/**
	 * @param oper_days the oper_days to set
	 */
	public void setOper_days(Double oper_days) {
		this.oper_days = oper_days;
	}


	/**
	 * @hibernate.property column="salve_data"
	 * @return the salve_data
	 */
	public String getSalve_data() {
		return salve_data;
	}

	/**
	 * @param salve_data the salve_data to set
	 */
	public void setSalve_data(String salve_data) {
		this.salve_data = salve_data;
	}

	/**
	 * @hibernate.property column="rollback_nodes"
	 * @return the rollback_nodes
	 */
	public String getRollback_nodes() {
		return rollback_nodes;
	}

	/**
	 * @param rollback_nodes the rollback_nodes to set
	 */
	public void setRollback_nodes(String rollback_nodes) {
		this.rollback_nodes = rollback_nodes;
	}

	/**
	 * @hibernate.property column="disp"
	 * @return the disp
	 */
	public String getDisp() {
		return disp;
	}

	/**
	 * @param disp the disp to set
	 */
	public void setDisp(String disp) {
		this.disp = disp;
	}

	/**
	 * @hibernate.property column="canStart"
	 * @return Returns the canStart.
	 */
	public String getCanStart() {
		return canStart;
	}

	/**
	 * @param canStart The canStart to set.
	 */
	public void setCanStart(String canStart) {
		this.canStart = canStart;
	}

	/**
	 * @hibernate.property column="remark"
	 * @return Returns the remark.
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark The remark to set.
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @hibernate.property column="ext"
	 * @return Returns the ext.
	 */
	public String getExt() {
		return ext;
	}

	/**
	 * @param ext The ext to set.
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}
}
