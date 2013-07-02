package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-07-02
 * @hibernate.class table="Td05_zbgc"
 */

public class Td05_zbgc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 685261992095932672L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td05_num"
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
 * 歌华工程编号

 */
 private String ghgcbh;

 /**
 * @hibernate.property column="ghgcbh"
 * @return Returns the ghgcbh.
 */
 public String getGhgcbh() {
    return ghgcbh;
 }

 public void setGhgcbh(String ghgcbh) {
    this.ghgcbh = ghgcbh;
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
 * 完工日期

 */
 private Date wgrq;

 /**
 * @hibernate.property column="wgrq"
 * @return Returns the wgrq.
 */
 public Date getWgrq() {
    return wgrq;
 }

 public void setWgrq(Date wgrq) {
    this.wgrq = wgrq;
  }

 /**
 * 报验日期

 */
 private Date byrq;

 /**
 * @hibernate.property column="byrq"
 * @return Returns the byrq.
 */
 public Date getByrq() {
    return byrq;
 }

 public void setByrq(Date byrq) {
    this.byrq = byrq;
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

 /**
 * 对下合同金额

 */
 private Long dxhtje;

 /**
 * @hibernate.property column="dxhtje"
 * @return Returns the dxhtje.
 */
 public Long getDxhtje() {
    return dxhtje;
 }

 public void setDxhtje(Long dxhtje) {
    this.dxhtje = dxhtje;
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
 * 发包方

 */
 private String fbf;

 /**
 * @hibernate.property column="fbf"
 * @return Returns the fbf.
 */
 public String getFbf() {
    return fbf;
 }

 public void setFbf(String fbf) {
    this.fbf = fbf;
  }

 /**
 * 工程预算总价

 */
 private Long gcyszj;

 /**
 * @hibernate.property column="gcyszj"
 * @return Returns the gcyszj.
 */
 public Long getGcyszj() {
    return gcyszj;
 }

 public void setGcyszj(Long gcyszj) {
    this.gcyszj = gcyszj;
  }

 /**
 * 歌华开工日期

 */
 private Date ghkgrq;

 /**
 * @hibernate.property column="ghkgrq"
 * @return Returns the ghkgrq.
 */
 public Date getGhkgrq() {
    return ghkgrq;
 }

 public void setGhkgrq(Date ghkgrq) {
    this.ghkgrq = ghkgrq;
  }

 /**
 * 歌华竣工日期

 */
 private Date ghjgrq;

 /**
 * @hibernate.property column="ghjgrq"
 * @return Returns the ghjgrq.
 */
 public Date getGhjgrq() {
    return ghjgrq;
 }

 public void setGhjgrq(Date ghjgrq) {
    this.ghjgrq = ghjgrq;
  }


}
