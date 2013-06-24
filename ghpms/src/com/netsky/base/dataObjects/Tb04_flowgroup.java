package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author mengying 2010-1-7
 * @hibernate.class table="Tb04_flowgroup"
 */

public class Tb04_flowgroup implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6947262904767411278L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 主流程ID
	 */
	private Long pflow_id;
	
	/**
	 * 子流程ID
	 */
	private Long cflow_id;
	
	/**
	 * 子流程在流程组合中序号
	 */
	private Long seq;
	
	/**
	 * 子流程对应的表单模块ID
	 */
	private Long module_id;
	
	/**
	 * 子流程的发送节点
	 */
	private String sourceNode;
	
	/**
	 * 子流程的开始节点
	 */
	private String startNode;	
	
	/**
	 * 参数：用来配置流程启动的先决条件
	 */
	private String paras;


	/**
	 *  @hibernate.property column="paras"
	 * @return Returns the paras.
	 */
	public String getParas() {
		return paras;
	}

	/**
	 * @param paras The paras to set.
	 */
	public void setParas(String paras) {
		this.paras = paras;
	}

	/**
	 * @hibernate.property column="sourceNode"
	 * @return Returns the sourceNode.
	 */
	public String getSourceNode() {
		return sourceNode;
	}

	/**
	 * @param sourceNodes The sourceNodes to set.
	 */
	public void setSourceNode(String sourceNode) {
		this.sourceNode = sourceNode;
	}

	/**
	 * @hibernate.property column="pflow_id"
	 * @return the pflow_id
	 */
	public Long getPflow_id() {
		return pflow_id;
	}

	/**
	 * @param pflow_id the pflow_id to set
	 */
	public void setPflow_id(Long pflow_id) {
		this.pflow_id = pflow_id;
	}

	/**
	 * @hibernate.property column="cflow_id"
	 * @return the cflow_id
	 */
	public Long getCflow_id() {
		return cflow_id;
	}

	/**
	 * @param cflow_id the cflow_id to set
	 */
	public void setCflow_id(Long cflow_id) {
		this.cflow_id = cflow_id;
	}

	/**
	 * @hibernate.property column="seq"
	 * @return the seq
	 */
	public Long getSeq() {
		return seq;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setSeq(Long seq) {
		this.seq = seq;
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
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tb04_NUM"
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
	 * @hibernate.property column="startNode"
	 * @return Returns the startNode.
	 */
	public String getStartNode() {
		return startNode;
	}

	/**
	 * @param startNode The startNode to set.
	 */
	public void setStartNode(String startNode) {
		this.startNode = startNode;
	}
}
