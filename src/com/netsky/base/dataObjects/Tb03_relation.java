package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author mengying 2010-1-7
 * @hibernate.class table="Tb03_relation"
 */

public class Tb03_relation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5600290902846412739L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 类别:1、普通流转 send；2、报批 report（只能审结前操作）
	 */
	private String relation_type;
	
	/**
	 * 所属流程
	 */
	private Long flow_id;
	
	/**
	 * 开始节点
	 */
	private Long source_id;
	
	/**
	 * 结束节点
	 */
	private Long dest_id;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 同一节点关系中的序列
	 */
	private Integer seq;
	
	/**
	 * 是否必走：必走为 must 可选为其它
	 */
	private String mustRouted;
	
	/**
	 * 发竤到本项目历史节点处理人
	 */
	private Long his_node;
	
	/**
	 * 发竤到本项目历史节点处理人
	 */
	private String paras;
	
	/**
	 * 操作图片资源信息
	 */
	private String pic_url;

	/**
	 * 接收人员范围：1、all  有权限的全部；2、group 有权限的群组；
	 * <br>3、curGroup 当前群组 4、Lowgroup 当前群组的下一级群组；
	 */
	private String targetScope;
	
	
	/**
	 * 是否到人 Y为指定到人
	 */
	private String toPeople;
	
	/**
	 * 是否需求js前台表单校验后才发送
	 */
	private String jsVerify;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tb03_NUM"
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
	 * @hibernate.property column="relation_type"
	 * @return the relation_type
	 */
	public String getRelation_type() {
		return relation_type;
	}

	/**
	 * @param relation_type the relation_type to set
	 */
	public void setRelation_type(String relation_type) {
		this.relation_type = relation_type;
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
	 * @hibernate.property column="description"
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @hibernate.property column="seq"
	 * @return the seq
	 */
	public Integer getSeq() {
		return seq;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	/**
	 * @hibernate.property column="his_node"
	 * @return the his_node
	 */
	public Long getHis_node() {
		return his_node;
	}

	/**
	 * @param his_node the his_node to set
	 */
	public void setHis_node(Long his_node) {
		this.his_node = his_node;
	}

	/**
	 * @hibernate.property column="paras"
	 * @return the paras
	 */
	public String getParas() {
		return paras;
	}

	/**
	 * @param paras the paras to set
	 */
	public void setParas(String paras) {
		this.paras = paras;
	}

	/**
	 * @hibernate.property column="pic_url"
	 * @return the pic_url
	 */
	public String getPic_url() {
		return pic_url;
	}

	/**
	 * @param pic_url the pic_url to set
	 */
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	/**
	 * @hibernate.property column="mustRouted"
	 * @return Returns the mustRouted.
	 */
	public String getMustRouted() {
		return mustRouted;
	}

	/**
	 * @param mustRouted The mustRouted to set.
	 */
	public void setMustRouted(String mustRouted) {
		this.mustRouted = mustRouted;
	}

	/**
	 * @hibernate.property column="targetScope"
	 * @return Returns the targetScope.
	 */
	public String getTargetScope() {
		return targetScope;
	}

	/**
	 * @param targetScope The targetScope to set.
	 */
	public void setTargetScope(String targetScope) {
		this.targetScope = targetScope;
	}

	/**
	 * @hibernate.property column="toPeople"
	 * @return Returns the toPeople.
	 */
	public String getToPeople() {
		return toPeople;
	}

	/**
	 * @param toPeople The toPeople to set.
	 */
	public void setToPeople(String toPeople) {
		this.toPeople = toPeople;
	}

	/**
	 * @hibernate.property column="jsVerify"
	 * @return Returns the jsVerify.
	 */
	public String getJsVerify() {
		return jsVerify;
	}

	/**
	 * @param jsVerify The jsVerify to set.
	 */
	public void setJsVerify(String jsVerify) {
		this.jsVerify = jsVerify;
	}
	
	
}
