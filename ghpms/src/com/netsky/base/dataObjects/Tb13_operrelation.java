package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mengying 2010-1-7
 * @hibernate.class table="Tb13_operrelation"
 */

public class Tb13_operrelation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2018267301994951893L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 项目标识
	 */
	private Long project_id;
	
	/**
	 * 开始节点对应的表单ID
	 */
	private Long doc_id;
	
	/**
	 * 开始节点对应的表单module_id
	 */
	private Long module_id;
	
	/**
	 * 关系标识
	 */
	private Long relation_id;
	
	/**
	 * 开始节点
	 */
	private Long source_id;
	
	/**
	 * 结束节点
	 */
	private Long dest_id;
	
	/**
	 * 操作时间
	 */
	private Date oper_time;
	
	/**
	 * tb12_ord 
	 */
	private Long tb12_ord;
	
	/**
	 * 路由名称
	 */
	private String relation_name;
	
	/**
	 * 路由序列
	 */
	private Integer seq;	

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tb13_NUM"
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
	 * @hibernate.property column="module_id"
	 * @return Returns the module_id.
	 */
	public Long getModule_id() {
		return module_id;
	}

	/**
	 * @param module_id The module_id to set.
	 */
	public void setModule_id(Long module_id) {
		this.module_id = module_id;
	}
	/**
	 * @hibernate.property column="relation_id"
	 * @return the relation_id
	 */
	public Long getRelation_id() {
		return relation_id;
	}

	/**
	 * @param relation_id the relation_id to set
	 */
	public void setRelation_id(Long relation_id) {
		this.relation_id = relation_id;
	}

	/**
	 * @hibernate.property column="source_id"
	 * @return the source_id
	 */
	public Long getSource_id() {
		return source_id;
	}

	/**
	 * @param source_id the source_id to set
	 */
	public void setSource_id(Long source_id) {
		this.source_id = source_id;
	}

	/**
	 * @hibernate.property column="dest_id"
	 * @return the dest_id
	 */
	public Long getDest_id() {
		return dest_id;
	}

	/**
	 * @param dest_id the dest_id to set
	 */
	public void setDest_id(Long dest_id) {
		this.dest_id = dest_id;
	}



	/**
	 * @hibernate.property column="tb12_ord"
	 * @return the tb12_ord
	 */
	public Long getTb12_ord() {
		return tb12_ord;
	}

	/**
	 * @param tb12_ord the tb12_ord to set
	 */
	public void setTb12_ord(Long tb12_ord) {
		this.tb12_ord = tb12_ord;
	}

	/**
	 * @hibernate.property column="relation_name"
	 * @return the relation_name
	 */
	public String getRelation_name() {
		return relation_name;
	}

	/**
	 * @param relation_name the relation_name to set
	 */
	public void setRelation_name(String relation_name) {
		this.relation_name = relation_name;
	}

	/**
	 * @hibernate.property column="oper_time"
	 * @return Returns the oper_time.
	 */
	public Date getOper_time() {
		return oper_time;
	}

	/**
	 * @param oper_time The oper_time to set.
	 */
	public void setOper_time(Date oper_time) {
		this.oper_time = oper_time;
	}

	/**
	 * @hibernate.property column="seq"
	 * @return Returns the seq.
	 */
	public Integer getSeq() {
		return seq;
	}

	/**
	 * @param seq The seq to set.
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}


	
}
