package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-07-05
 * @hibernate.class table="Td08_qtgc"
 */

public class Td08_qtgc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 436784930012244800L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td08_num"
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
 * 歌华编号
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
 * 合同金额

 */
 private Long htje;

 /**
 * @hibernate.property column="htje"
 * @return Returns the htje.
 */
 public Long getHtje() {
    return htje;
 }

 public void setHtje(Long htje) {
    this.htje = htje;
  }

 /**
 * 合同签订日期

 */
 private Date htqdrq;

 /**
 * @hibernate.property column="htqdrq"
 * @return Returns the htqdrq.
 */
 public Date getHtqdrq() {
    return htqdrq;
 }

 public void setHtqdrq(Date htqdrq) {
    this.htqdrq = htqdrq;
  }

 /**
 * 合同返回日期

 */
 private Date htfhrq;

 /**
 * @hibernate.property column="htfhrq"
 * @return Returns the htfhrq.
 */
 public Date getHtfhrq() {
    return htfhrq;
 }

 public void setHtfhrq(Date htfhrq) {
    this.htfhrq = htfhrq;
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
 * 项目负责人

 */
 private String xmfzr;

 /**
 * @hibernate.property column="xmfzr"
 * @return Returns the xmfzr.
 */
 public String getXmfzr() {
    return xmfzr;
 }

 public void setXmfzr(String xmfzr) {
    this.xmfzr = xmfzr;
  }

 /**
 * 对上预付款金额

 */
 private Long dsyfje;

 /**
 * @hibernate.property column="dsyfje"
 * @return Returns the dsyfje.
 */
 public Long getDsyfje() {
    return dsyfje;
 }

 public void setDsyfje(Long dsyfje) {
    this.dsyfje = dsyfje;
  }

 /**
 * 对上预付款日期

 */
 private Date dsyfkrq;

 /**
 * @hibernate.property column="dsyfkrq"
 * @return Returns the dsyfkrq.
 */
 public Date getDsyfkrq() {
    return dsyfkrq;
 }

 public void setDsyfkrq(Date dsyfkrq) {
    this.dsyfkrq = dsyfkrq;
  }

 /**
 * 对上实付金额

 */
 private Long dssfje;

 /**
 * @hibernate.property column="dssfje"
 * @return Returns the dssfje.
 */
 public Long getDssfje() {
    return dssfje;
 }

 public void setDssfje(Long dssfje) {
    this.dssfje = dssfje;
  }

 /**
 * 对上结算日期

 */
 private Date dsjsrq;

 /**
 * @hibernate.property column="dsjsrq"
 * @return Returns the dsjsrq.
 */
 public Date getDsjsrq() {
    return dsjsrq;
 }

 public void setDsjsrq(Date dsjsrq) {
    this.dsjsrq = dsjsrq;
  }

 /**
 * 视宽对下预付金额

 */
 private Long skdxyfje;

 /**
 * @hibernate.property column="skdxyfje"
 * @return Returns the skdxyfje.
 */
 public Long getSkdxyfje() {
    return skdxyfje;
 }

 public void setSkdxyfje(Long skdxyfje) {
    this.skdxyfje = skdxyfje;
  }

 /**
 * 视宽对下预付日期

 */
 private Date skdxyfrq;

 /**
 * @hibernate.property column="skdxyfrq"
 * @return Returns the skdxyfrq.
 */
 public Date getSkdxyfrq() {
    return skdxyfrq;
 }

 public void setSkdxyfrq(Date skdxyfrq) {
    this.skdxyfrq = skdxyfrq;
  }

 /**
 * 视宽对下实付金额

 */
 private Long skdxsfje;

 /**
 * @hibernate.property column="skdxsfje"
 * @return Returns the skdxsfje.
 */
 public Long getSkdxsfje() {
    return skdxsfje;
 }

 public void setSkdxsfje(Long skdxsfje) {
    this.skdxsfje = skdxsfje;
  }

 /**
 * 视宽对下结算日期

 */
 private Date skdxjsrq;

 /**
 * @hibernate.property column="skdxjsrq"
 * @return Returns the skdxjsrq.
 */
 public Date getSkdxjsrq() {
    return skdxjsrq;
 }

 public void setSkdxjsrq(Date skdxjsrq) {
    this.skdxjsrq = skdxjsrq;
  }

 /**
 * 对上付款单位（发包方）

 */
 private String dsfkdw;

 /**
 * @hibernate.property column="dsfkdw"
 * @return Returns the dsfkdw.
 */
 public String getDsfkdw() {
    return dsfkdw;
 }

 public void setDsfkdw(String dsfkdw) {
    this.dsfkdw = dsfkdw;
  }


}
