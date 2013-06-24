package com.netsky.base.flow.vo;

import java.util.Date;

public class Cswdxx {
	private Integer id;
	private String bdmc;
	private String bdbh;
	private String xmmc;
	private String czr;
	private String czrdh;
	private String czrbm;
	private Date jhwcsj;
	private Integer user_id;
	private Integer project_id;
	private Integer doc_id;
	private Integer module_id;
	private Integer node_id;
	private Integer opernode_id;
	private String cqlx;
	
	/**
	 * @hibernate.property column="bdmc"
	 * @return Returns the bdmc.
	 */
	public String getBdmc() {
		return bdmc;
	}

	/**
	 * @param bdmc
	 *            The bdmc to set.
	 */
	public void setBdmc(String bdmc) {
		this.bdmc = bdmc;
	}

	/**
	 * @hibernate.property column="bdbh"
	 * @return Returns the bdbh.
	 */
	public String getBdbh() {
		return bdbh;
	}

	/**
	 * @param bdbh
	 *            The bdbh to set.
	 */
	public void setBdbh(String bdbh) {
		this.bdbh = bdbh;
	}

	/**
	 * @hibernate.property column="xmmc"
	 * @return Returns the xmmc.
	 */
	public String getXmmc() {
		return xmmc;
	}

	/**
	 * @param xmmc
	 *            The xmmc to set.
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	/**
	 * @hibernate.property column="czr"
	 * @return Returns the czr.
	 */
	public String getCzr() {
		return czr;
	}

	/**
	 * @param czr
	 *            The czr to set.
	 */
	public void setCzr(String czr) {
		this.czr = czr;
	}

	/**
	 * @hibernate.property column="czrdh"
	 * @return Returns the czrdh.
	 */
	public String getCzrdh() {
		return czrdh;
	}

	/**
	 * @param czrdh
	 *            The czrdh to set.
	 */
	public void setCzrdh(String czrdh) {
		this.czrdh = czrdh;
	}

	/**
	 * @hibernate.property column="czrbm"
	 * @return Returns the czrbm.
	 */
	public String getCzrbm() {
		return czrbm;
	}

	/**
	 * @param czrbm
	 *            The czrbm to set.
	 */
	public void setCzrbm(String czrbm) {
		this.czrbm = czrbm;
	}

	/**
	 * @hibernate.property column="jhwcsj"
	 * @return Returns the jhwcsj.
	 */
	public Date getJhwcsj() {
		return jhwcsj;
	}

	/**
	 * @param jhwcsj
	 *            The jhwcsj to set.
	 */
	public void setJhwcsj(Date jhwcsj) {
		this.jhwcsj = jhwcsj;
	}

	/**
	 * @hibernate.property column="user_id"
	 * @return Returns the user_id.
	 */
	public Integer getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id
	 *            The user_id to set.
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	/**
	 * @hibernate.property column="project_id"
	 * @return Returns the project_id.
	 */
	public Integer getProject_id() {
		return project_id;
	}

	/**
	 * @param project_id
	 *            The project_id to set.
	 */
	public void setProject_id(Integer project_id) {
		this.project_id = project_id;
	}

	/**
	 * @hibernate.property column="doc_id"
	 * @return Returns the doc_id.
	 */
	public Integer getDoc_id() {
		return doc_id;
	}

	/**
	 * @param doc_id
	 *            The doc_id to set.
	 */
	public void setDoc_id(Integer doc_id) {
		this.doc_id = doc_id;
	}

	/**
	 * @hibernate.property column="module_id"
	 * @return Returns the module_id.
	 */
	public Integer getModule_id() {
		return module_id;
	}

	/**
	 * @param module_id
	 *            The module_id to set.
	 */
	public void setModule_id(Integer module_id) {
		this.module_id = module_id;
	}

	/**
	 * @hibernate.property column="node_id"
	 * @return Returns the node_id.
	 */
	public Integer getNode_id() {
		return node_id;
	}

	/**
	 * @param node_id
	 *            The node_id to set.
	 */
	public void setNode_id(Integer node_id) {
		this.node_id = node_id;
	}

	/**
	 * @hibernate.property column="opernode_id"
	 * @return Returns the opernode_id.
	 */
	public Integer getOpernode_id() {
		return opernode_id;
	}

	/**
	 * @param opernode_id
	 *            The opernode_id to set.
	 */
	public void setOpernode_id(Integer opernode_id) {
		this.opernode_id = opernode_id;
	}

	/**
	 * @hibernate.id generator-class="assigned"
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="cqlx"
	 * @return Returns the cqlx.
	 */
	public String getCqlx() {
		return cqlx;
	}

	public void setCqlx(String cqlx) {
		this.cqlx = cqlx;
	}
	
}
