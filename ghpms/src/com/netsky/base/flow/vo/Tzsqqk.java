package com.netsky.base.flow.vo;

import java.util.Date;
/**
 * @description:
 * 回复文档列表
 * @class name:com.netsky.base.flow.vo.Tzsqqk
 * @author lee.xy Jan 28, 2012
 * @hibernate.class table="tzsqqk"
 */
public class Tzsqqk {
	
	/**
	 * 虚拟主建
	 */
	private Long id;
	
	/**
	 * 申请部门
	 */
	private String sqbm;
	
	/**
	 * 申请编号
	 */
	private String sqbh;
	
	/**
	 * 项目名称
	 */
	private String xmmc;
	
	/**
	 * 建设性质
	 */
	private String jsxz;
	
	/**
	 * 所属专业
	 */
	private String sszy;
	
	/**
	 * 备注
	 */
	private String bz;
	
	/**
	 * 设计单位
	 */
	private String sjdw;
	
	/**
	 * 设计时限
	 */
	private Integer sjsx;
	
	/**
	 * 项目管理员
	 */
	private String xmgly;
	
	/**
	 * 工程管理员
	 */
	private String gcgly;
	
	/**
	 * 规划管理员
	 */
	private String ghgly;
	
	/**
	 * 设计管理员
	 */
	private String sjgly;
	
	/**
	 * 设计人员
	 */
	private String sjry;
	
	/**
	 * 需求提出时间
	 */
	private Date xqqcsj;
	
	/**
	 * 图纸下载时间
	 */
	private Date tzxzsj;
	
	/**
	 * 图纸上传时间
	 */
	private Date tzscsj;
	
	/**
	 * 图纸替换时间
	 */
	private Date tzthsj;
	
	/**
	 * 状态
	 */
	private String zt;
	
	/**
	 * @hibernate.id generator-class="assigned"
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
	 * @hibernate.property column="sqbm"
	 * @return Returns the sqbm.
	 */
	public String getSqbm() {
		return sqbm;
	}

	public void setSqbm(String sqbm) {
		this.sqbm = sqbm;
	}

	/**
	 * @hibernate.property column="sqbh"
	 * @return Returns the sqbh.
	 */
	public String getSqbh() {
		return sqbh;
	}

	public void setSqbh(String sqbh) {
		this.sqbh = sqbh;
	}

	/**
	 * @hibernate.property column="jsxz"
	 * @return Returns the jsxz.
	 */
	public String getJsxz() {
		return jsxz;
	}

	public void setJsxz(String jsxz) {
		this.jsxz = jsxz;
	}

	/**
	 * @hibernate.property column="sszy"
	 * @return Returns the sszy.
	 */
	public String getSszy() {
		return sszy;
	}

	public void setSszy(String sszy) {
		this.sszy = sszy;
	}

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
	 * @hibernate.property column="sjdw"
	 * @return Returns the sjdw.
	 */
	public String getSjdw() {
		return sjdw;
	}

	public void setSjdw(String sjdw) {
		this.sjdw = sjdw;
	}

	/**
	 * @hibernate.property column="sjsx"
	 * @return Returns the sjsx.
	 */
	public Integer getSjsx() {
		return sjsx;
	}

	public void setSjsx(Integer sjsx) {
		this.sjsx = sjsx;
	}

	/**
	 * @hibernate.property column="xmgly"
	 * @return Returns the xmgly.
	 */
	public String getXmgly() {
		return xmgly;
	}

	public void setXmgly(String xmgly) {
		this.xmgly = xmgly;
	}

	/**
	 * @hibernate.property column="gcgly"
	 * @return Returns the gcgly.
	 */
	public String getGcgly() {
		return gcgly;
	}

	public void setGcgly(String gcgly) {
		this.gcgly = gcgly;
	}

	/**
	 * @hibernate.property column="ghgly"
	 * @return Returns the ghgly.
	 */
	public String getGhgly() {
		return ghgly;
	}

	public void setGhgly(String ghgly) {
		this.ghgly = ghgly;
	}

	/**
	 * @hibernate.property column="sjgly"
	 * @return Returns the sjgly.
	 */
	public String getSjgly() {
		return sjgly;
	}

	public void setSjgly(String sjgly) {
		this.sjgly = sjgly;
	}

	/**
	 * @hibernate.property column="sjry"
	 * @return Returns the sjry.
	 */
	public String getSjry() {
		return sjry;
	}

	public void setSjry(String sjry) {
		this.sjry = sjry;
	}

	/**
	 * @hibernate.property column="xqqcsj"
	 * @return Returns the xqqcsj.
	 */
	public Date getXqqcsj() {
		return xqqcsj;
	}

	public void setXqqcsj(Date xqqcsj) {
		this.xqqcsj = xqqcsj;
	}

	/**
	 * @hibernate.property column="tzxzsj"
	 * @return Returns the tzxzsj.
	 */
	public Date getTzxzsj() {
		return tzxzsj;
	}

	public void setTzxzsj(Date tzxzsj) {
		this.tzxzsj = tzxzsj;
	}

	/**
	 * @hibernate.property column="tzscsj"
	 * @return Returns the tzscsj.
	 */
	public Date getTzscsj() {
		return tzscsj;
	}

	public void setTzscsj(Date tzscsj) {
		this.tzscsj = tzscsj;
	}

	/**
	 * @hibernate.property column="tzthsj"
	 * @return Returns the tzthsj.
	 */
	public Date getTzthsj() {
		return tzthsj;
	}

	public void setTzthsj(Date tzthsj) {
		this.tzthsj = tzthsj;
	}

	/**
	 * @hibernate.property column="zt"
	 * @return Returns the zt.
	 */
	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	
}
