package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2014-03-19
 * @hibernate.class table="Td35_gdwhgc"
 */

public class Td35_gdwhgc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 270433901927159776L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td35_num"
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
 * 管道维护类型[选项]
 */
 private String gdwhlx;

 /**
 * @hibernate.property column="gdwhlx"
 * @return Returns the gdwhlx.
 */
 public String getGdwhlx() {
    return gdwhlx;
 }

 public void setGdwhlx(String gdwhlx) {
    this.gdwhlx = gdwhlx;
  }

 /**
 * 数量
 */
 private Long sl;

 /**
 * @hibernate.property column="sl"
 * @return Returns the sl.
 */
 public Long getSl() {
    return sl;
 }

 public void setSl(Long sl) {
    this.sl = sl;
  }

 /**
 * 使用数量
 */
 private Long sysl;

 /**
 * @hibernate.property column="sysl"
 * @return Returns the sysl.
 */
 public Long getSysl() {
    return sysl;
 }

 public void setSysl(Long sysl) {
    this.sysl = sysl;
  }

 /**
 * 剩余数量
 */
 private Long shysl;

 /**
 * @hibernate.property column="shysl"
 * @return Returns the shysl.
 */
 public Long getShysl() {
    return shysl;
 }

 public void setShysl(Long shysl) {
    this.shysl = shysl;
  }

 /**
 * 使用日期
 */
 private Date syrq;

 /**
 * @hibernate.property column="syrq"
 * @return Returns the syrq.
 */
 public Date getSyrq() {
    return syrq;
 }

 public void setSyrq(Date syrq) {
    this.syrq = syrq;
  }

 /**
 * 使用地址
 */
 private String sydz;

 /**
 * @hibernate.property column="sydz"
 * @return Returns the sydz.
 */
 public String getSydz() {
    return sydz;
 }

 public void setSydz(String sydz) {
    this.sydz = sydz;
  }

 /**
 * 使用原因
 */
 private String syyy;

 /**
 * @hibernate.property column="syyy"
 * @return Returns the syyy.
 */
 public String getSyyy() {
    return syyy;
 }

 public void setSyyy(String syyy) {
    this.syyy = syyy;
  }

 /**
 * 路段维护员姓名[人员]
 */
 private String ldwhyxm;

 /**
 * @hibernate.property column="ldwhyxm"
 * @return Returns the ldwhyxm.
 */
 public String getLdwhyxm() {
    return ldwhyxm;
 }

 public void setLdwhyxm(String ldwhyxm) {
    this.ldwhyxm = ldwhyxm;
  }

 /**
 * 信息来源
 */
 private String xxly;

 /**
 * @hibernate.property column="xxly"
 * @return Returns the xxly.
 */
 public String getXxly() {
    return xxly;
 }

 public void setXxly(String xxly) {
    this.xxly = xxly;
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


}
