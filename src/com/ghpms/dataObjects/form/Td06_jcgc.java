package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-07-05
 * @hibernate.class table="Td06_jcgc"
 */

public class Td06_jcgc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 131029284354775680L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td06_num"
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
 * 视宽编号
 */
 private String skbh;

 /**
 * @hibernate.property column="skbh"
 * @return Returns the skbh.
 */
 public String getSkbh() {
    return skbh;
 }

 public void setSkbh(String skbh) {
    this.skbh = skbh;
  }

 /**
 * 对下合同金额

 */
 private Long dxhtqdje;

 /**
 * @hibernate.property column="dxhtqdje"
 * @return Returns the dxhtqdje.
 */
 public Long getDxhtqdje() {
    return dxhtqdje;
 }

 public void setDxhtqdje(Long dxhtqdje) {
    this.dxhtqdje = dxhtqdje;
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
 * 项目负责人

 */
 private String zmfzr;

 /**
 * @hibernate.property column="zmfzr"
 * @return Returns the zmfzr.
 */
 public String getZmfzr() {
    return zmfzr;
 }

 public void setZmfzr(String zmfzr) {
    this.zmfzr = zmfzr;
  }

 /**
 * 歌华预付款金额

 */
 private Long ghyfkje;

 /**
 * @hibernate.property column="ghyfkje"
 * @return Returns the ghyfkje.
 */
 public Long getGhyfkje() {
    return ghyfkje;
 }

 public void setGhyfkje(Long ghyfkje) {
    this.ghyfkje = ghyfkje;
  }

 /**
 * 歌华预付款日期

 */
 private Date ghyfkrq;

 /**
 * @hibernate.property column="ghyfkrq"
 * @return Returns the ghyfkrq.
 */
 public Date getGhyfkrq() {
    return ghyfkrq;
 }

 public void setGhyfkrq(Date ghyfkrq) {
    this.ghyfkrq = ghyfkrq;
  }

 /**
 * 歌华实付金额

 */
 private Long ghsfje;

 /**
 * @hibernate.property column="ghsfje"
 * @return Returns the ghsfje.
 */
 public Long getGhsfje() {
    return ghsfje;
 }

 public void setGhsfje(Long ghsfje) {
    this.ghsfje = ghsfje;
  }

 /**
 * 歌华结算日期

 */
 private Date ghjsrq;

 /**
 * @hibernate.property column="ghjsrq"
 * @return Returns the ghjsrq.
 */
 public Date getGhjsrq() {
    return ghjsrq;
 }

 public void setGhjsrq(Date ghjsrq) {
    this.ghjsrq = ghjsrq;
  }

 /**
 * 视宽预付金额

 */
 private Long skyfje;

 /**
 * @hibernate.property column="skyfje"
 * @return Returns the skyfje.
 */
 public Long getSkyfje() {
    return skyfje;
 }

 public void setSkyfje(Long skyfje) {
    this.skyfje = skyfje;
  }

 /**
 * 视宽预付日期

 */
 private Date skyfrq;

 /**
 * @hibernate.property column="skyfrq"
 * @return Returns the skyfrq.
 */
 public Date getSkyfrq() {
    return skyfrq;
 }

 public void setSkyfrq(Date skyfrq) {
    this.skyfrq = skyfrq;
  }

 /**
 * 视宽实付金额

 */
 private Long sksfje;

 /**
 * @hibernate.property column="sksfje"
 * @return Returns the sksfje.
 */
 public Long getSksfje() {
    return sksfje;
 }

 public void setSksfje(Long sksfje) {
    this.sksfje = sksfje;
  }

 /**
 * 视宽结算日期

 */
 private Date skjsrq;

 /**
 * @hibernate.property column="skjsrq"
 * @return Returns the skjsrq.
 */
 public Date getSkjsrq() {
    return skjsrq;
 }

 public void setSkjsrq(Date skjsrq) {
    this.skjsrq = skjsrq;
  }

 /**
 * 视宽要求完工日期

 */
 private Date skyqwgrq;

 /**
 * @hibernate.property column="skyqwgrq"
 * @return Returns the skyqwgrq.
 */
 public Date getSkyqwgrq() {
    return skyqwgrq;
 }

 public void setSkyqwgrq(Date skyqwgrq) {
    this.skyqwgrq = skyqwgrq;
  }

 /**
 * 报竣日期

 */
 private Date bjrq;

 /**
 * @hibernate.property column="bjrq"
 * @return Returns the bjrq.
 */
 public Date getBjrq() {
    return bjrq;
 }

 public void setBjrq(Date bjrq) {
    this.bjrq = bjrq;
  }

 /**
 * 决算总价

 */
 private Long jszj;

 /**
 * @hibernate.property column="jszj"
 * @return Returns the jszj.
 */
 public Long getJszj() {
    return jszj;
 }

 public void setJszj(Long jszj) {
    this.jszj = jszj;
  }

 /**
 * 歌华工程编号

 */
 private String ghbh;

 /**
 * @hibernate.property column="ghbh"
 * @return Returns the ghbh.
 */
 public String getGhbh() {
    return ghbh;
 }

 public void setGhbh(String ghbh) {
    this.ghbh = ghbh;
  }

 /**
 * 视宽协议编号

 */
 private String skxybh;

 /**
 * @hibernate.property column="skxybh"
 * @return Returns the skxybh.
 */
 public String getSkxybh() {
    return skxybh;
 }

 public void setSkxybh(String skxybh) {
    this.skxybh = skxybh;
  }

 /**
 * 工程状态

 */
 private String gczt;

 /**
 * @hibernate.property column="gczt"
 * @return Returns the gczt.
 */
 public String getGczt() {
    return gczt;
 }

 public void setGczt(String gczt) {
    this.gczt = gczt;
  }

 /**
 * 施工单位

 */
 private String sgdw;

 /**
 * @hibernate.property column="sgdw"
 * @return Returns the sgdw.
 */
 public String getSgdw() {
    return sgdw;
 }

 public void setSgdw(String sgdw) {
    this.sgdw = sgdw;
  }

 /**
 * 工程名称

 */
 private String gcmc;

 /**
 * @hibernate.property column="gcmc"
 * @return Returns the gcmc.
 */
 public String getGcmc() {
    return gcmc;
 }

 public void setGcmc(String gcmc) {
    this.gcmc = gcmc;
  }

 /**
 * 工程类型

 */
 private String gclx;

 /**
 * @hibernate.property column="gclx"
 * @return Returns the gclx.
 */
 public String getGclx() {
    return gclx;
 }

 public void setGclx(String gclx) {
    this.gclx = gclx;
  }

 /**
 * 工程地点

 */
 private String gcdd;

 /**
 * @hibernate.property column="gcdd"
 * @return Returns the gcdd.
 */
 public String getGcdd() {
    return gcdd;
 }

 public void setGcdd(String gcdd) {
    this.gcdd = gcdd;
  }

 /**
 * 任务接收日期

 */
 private Date rwjsrq;

 /**
 * @hibernate.property column="rwjsrq"
 * @return Returns the rwjsrq.
 */
 public Date getRwjsrq() {
    return rwjsrq;
 }

 public void setRwjsrq(Date rwjsrq) {
    this.rwjsrq = rwjsrq;
  }

 /**
 * 令号申请依据

 */
 private String lhsqyj;

 /**
 * @hibernate.property column="lhsqyj"
 * @return Returns the lhsqyj.
 */
 public String getLhsqyj() {
    return lhsqyj;
 }

 public void setLhsqyj(String lhsqyj) {
    this.lhsqyj = lhsqyj;
  }

 /**
 * 歌华负责人

 */
 private String ghfzr;

 /**
 * @hibernate.property column="ghfzr"
 * @return Returns the ghfzr.
 */
 public String getGhfzr() {
    return ghfzr;
 }

 public void setGhfzr(String ghfzr) {
    this.ghfzr = ghfzr;
  }

 /**
 * 歌华合同金额

 */
 private Long ghhtje;

 /**
 * @hibernate.property column="ghhtje"
 * @return Returns the ghhtje.
 */
 public Long getGhhtje() {
    return ghhtje;
 }

 public void setGhhtje(Long ghhtje) {
    this.ghhtje = ghhtje;
  }

 /**
 * 歌华合同签订日期

 */
 private Date ghhtqdrq;

 /**
 * @hibernate.property column="ghhtqdrq"
 * @return Returns the ghhtqdrq.
 */
 public Date getGhhtqdrq() {
    return ghhtqdrq;
 }

 public void setGhhtqdrq(Date ghhtqdrq) {
    this.ghhtqdrq = ghhtqdrq;
  }

 /**
 * 歌华合同返回日期

 */
 private Date ghhtfhrq;

 /**
 * @hibernate.property column="ghhtfhrq"
 * @return Returns the ghhtfhrq.
 */
 public Date getGhhtfhrq() {
    return ghhtfhrq;
 }

 public void setGhhtfhrq(Date ghhtfhrq) {
    this.ghhtfhrq = ghhtfhrq;
  }

 /**
 * 对下合同签订日期

 */
 private Date dxhtqdrq;

 /**
 * @hibernate.property column="dxhtqdrq"
 * @return Returns the dxhtqdrq.
 */
 public Date getDxhtqdrq() {
    return dxhtqdrq;
 }

 public void setDxhtqdrq(Date dxhtqdrq) {
    this.dxhtqdrq = dxhtqdrq;
  }


}
