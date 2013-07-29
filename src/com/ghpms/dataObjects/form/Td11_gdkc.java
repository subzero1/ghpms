package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-07-29
 * @hibernate.class table="Td11_gdkc"
 */

public class Td11_gdkc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 638843585980160128L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td11_num"
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
 * 工程编号
 */
 private String gcbh;

 /**
 * @hibernate.property column="gcbh"
 * @return Returns the gcbh.
 */
 public String getGcbh() {
    return gcbh;
 }

 public void setGcbh(String gcbh) {
    this.gcbh = gcbh;
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
 * 行政区域[选项]
 */
 private String xzqy;

 /**
 * @hibernate.property column="xzqy"
 * @return Returns the xzqy.
 */
 public String getXzqy() {
    return xzqy;
 }

 public void setXzqy(String xzqy) {
    this.xzqy = xzqy;
  }

 /**
 * 施工地址

 */
 private String sgdz;

 /**
 * @hibernate.property column="sgdz"
 * @return Returns the sgdz.
 */
 public String getSgdz() {
    return sgdz;
 }

 public void setSgdz(String sgdz) {
    this.sgdz = sgdz;
  }

 /**
 * 歌华监理

 */
 private String ghjl;

 /**
 * @hibernate.property column="ghjl"
 * @return Returns the ghjl.
 */
 public String getGhjl() {
    return ghjl;
 }

 public void setGhjl(String ghjl) {
    this.ghjl = ghjl;
  }

 /**
 * 歌华开工时间

 */
 private Date ghkg;

 /**
 * @hibernate.property column="ghkg"
 * @return Returns the ghkg.
 */
 public Date getGhkg() {
    return ghkg;
 }

 public void setGhkg(Date ghkg) {
    this.ghkg = ghkg;
  }

 /**
 * 歌华竣工时间

 */
 private Date ghjgsj;

 /**
 * @hibernate.property column="ghjgsj"
 * @return Returns the ghjgsj.
 */
 public Date getGhjgsj() {
    return ghjgsj;
 }

 public void setGhjgsj(Date ghjgsj) {
    this.ghjgsj = ghjgsj;
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
[选项]
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
 * 任务来源
[选项]
 */
 private String rwly;

 /**
 * @hibernate.property column="rwly"
 * @return Returns the rwly.
 */
 public String getRwly() {
    return rwly;
 }

 public void setRwly(String rwly) {
    this.rwly = rwly;
  }

 /**
 * 市场部负责人
[人员]
 */
 private String scbfzr;

 /**
 * @hibernate.property column="scbfzr"
 * @return Returns the scbfzr.
 */
 public String getScbfzr() {
    return scbfzr;
 }

 public void setScbfzr(String scbfzr) {
    this.scbfzr = scbfzr;
  }

 /**
 * 建设单位

 */
 private String jsdw;

 /**
 * @hibernate.property column="jsdw"
 * @return Returns the jsdw.
 */
 public String getJsdw() {
    return jsdw;
 }

 public void setJsdw(String jsdw) {
    this.jsdw = jsdw;
  }

 /**
 * 建设单位负责人

 */
 private String jsdwfzr;

 /**
 * @hibernate.property column="jsdwfzr"
 * @return Returns the jsdwfzr.
 */
 public String getJsdwfzr() {
    return jsdwfzr;
 }

 public void setJsdwfzr(String jsdwfzr) {
    this.jsdwfzr = jsdwfzr;
  }

 /**
 * 设计单位

 */
 private String sjdw;

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
 * 设计单位负责人

 */
 private String sjdwfzr;

 /**
 * @hibernate.property column="sjdwfzr"
 * @return Returns the sjdwfzr.
 */
 public String getSjdwfzr() {
    return sjdwfzr;
 }

 public void setSjdwfzr(String sjdwfzr) {
    this.sjdwfzr = sjdwfzr;
  }

 /**
 * 监理单位

 */
 private String jldw;

 /**
 * @hibernate.property column="jldw"
 * @return Returns the jldw.
 */
 public String getJldw() {
    return jldw;
 }

 public void setJldw(String jldw) {
    this.jldw = jldw;
  }

 /**
 * 监理单位负责人

 */
 private String jldwfzr;

 /**
 * @hibernate.property column="jldwfzr"
 * @return Returns the jldwfzr.
 */
 public String getJldwfzr() {
    return jldwfzr;
 }

 public void setJldwfzr(String jldwfzr) {
    this.jldwfzr = jldwfzr;
  }

 /**
 * 中标价格

 */
 private Long zbjg;

 /**
 * @hibernate.property column="zbjg"
 * @return Returns the zbjg.
 */
 public Long getZbjg() {
    return zbjg;
 }

 public void setZbjg(Long zbjg) {
    this.zbjg = zbjg;
  }

 /**
 * 提交设计成果日期

 */
 private Date tjsjcgrq;

 /**
 * @hibernate.property column="tjsjcgrq"
 * @return Returns the tjsjcgrq.
 */
 public Date getTjsjcgrq() {
    return tjsjcgrq;
 }

 public void setTjsjcgrq(Date tjsjcgrq) {
    this.tjsjcgrq = tjsjcgrq;
  }

 /**
 * 歌华任务书编号

 */
 private Date ghrwsbh;

 /**
 * @hibernate.property column="ghrwsbh"
 * @return Returns the ghrwsbh.
 */
 public Date getGhrwsbh() {
    return ghrwsbh;
 }

 public void setGhrwsbh(Date ghrwsbh) {
    this.ghrwsbh = ghrwsbh;
  }


}
