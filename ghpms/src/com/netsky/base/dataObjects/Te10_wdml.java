package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-01-07
 * @hibernate.class table="Te10_wdml"
 */

public class Te10_wdml implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 253395504334467328L;

	/**
	 * 标识
	 */
	private Long id;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="te10_num"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 上级标识
	 */
	private Long up_id;

	/**
	 * @hibernate.property column="up_id"
	 * @return Returns the up_id.
	 */
	public Long getUp_id() {
		return up_id;
	}

	public void setUp_id(Long up_id) {
		this.up_id = up_id;
	}

	/**
	 * 名称
	 */
	private String mc;

	/**
	 * @hibernate.property column="mc"
	 * @return Returns the mc.
	 */
	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	/**
	 * 备注
	 */
	private String bz;

	/**
	 * @hibernate.property column="bz"
	 * @return Returns the bz.
	 */
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * 查阅范围（全部/部门1,部门2/岗位1,岗位2/姓名1,姓名2）
	 */
	private String cyfw;

	/**
	 * @hibernate.property column="cyfw"
	 * @return Returns the cyfw.
	 */
	public String getCyfw() {
		return cyfw;
	}

	public void setCyfw(String cyfw) {
		this.cyfw = cyfw;
	}

	/**
	 * 查阅类型（所有：0；按部门：1；按岗位2；按人：3；）
	 */
	private String cylb;

	/**
	 * @hibernate.property column="cylb"
	 * @return Returns the cylb.
	 */
	public String getCylb() {
		return cylb;
	}

	public void setCylb(String cylb) {
		this.cylb = cylb;
	}

	/**
	 * 创建人
	 */
	private String cjr;

	/**
	 * @hibernate.property column="cjr"
	 * @return Returns the cjr.
	 */
	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	/**
	 * 创建时间
	 */
	private Date cjsj;

	/**
	 * @hibernate.property column="cjsj"
	 * @return Returns the cjsj.
	 */
	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	/**
	 * 创建部门
	 */
	private String cjbm;

	/**
	 * @hibernate.property column="cjbm"
	 * @return Returns the cjbm.
	 */
	public String getCjbm() {
		return cjbm;
	}

	public void setCjbm(String cjbm) {
		this.cjbm = cjbm;
	}

}
