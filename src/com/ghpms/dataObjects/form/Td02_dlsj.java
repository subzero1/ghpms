package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-06-27
 * @hibernate.class table="Td02_dlsj"
 */

public class Td02_dlsj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 74083556291705936L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td02_num"
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
 * 视频编号
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
 * 项目名称
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
 * 光节点编号

 */
 private String gjdbh;

 /**
 * @hibernate.property column="gjdbh"
 * @return Returns the gjdbh.
 */
 public String getGjdbh() {
    return gjdbh;
 }

 public void setGjdbh(String gjdbh) {
    this.gjdbh = gjdbh;
  }

 /**
 * 任务来源

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
 * 勘察人员

 */
 private String kcry;

 /**
 * @hibernate.property column="kcry"
 * @return Returns the kcry.
 */
 public String getKcry() {
    return kcry;
 }

 public void setKcry(String kcry) {
    this.kcry = kcry;
  }

 /**
 * 勘察单位

 */
 private String kcdw;

 /**
 * @hibernate.property column="kcdw"
 * @return Returns the kcdw.
 */
 public String getKcdw() {
    return kcdw;
 }

 public void setKcdw(String kcdw) {
    this.kcdw = kcdw;
  }

 /**
 * 勘察协议签订日期

 */
 private Date kcxyqdrq;

 /**
 * @hibernate.property column="kcxyqdrq"
 * @return Returns the kcxyqdrq.
 */
 public Date getKcxyqdrq() {
    return kcxyqdrq;
 }

 public void setKcxyqdrq(Date kcxyqdrq) {
    this.kcxyqdrq = kcxyqdrq;
  }

 /**
 * 勘察协议返回日期

 */
 private Date kcxyfhrq;

 /**
 * @hibernate.property column="kcxyfhrq"
 * @return Returns the kcxyfhrq.
 */
 public Date getKcxyfhrq() {
    return kcxyfhrq;
 }

 public void setKcxyfhrq(Date kcxyfhrq) {
    this.kcxyfhrq = kcxyfhrq;
  }

 /**
 * 勘察协议金额

 */
 private Long kcxyje;

 /**
 * @hibernate.property column="kcxyje"
 * @return Returns the kcxyje.
 */
 public Long getKcxyje() {
    return kcxyje;
 }

 public void setKcxyje(Long kcxyje) {
    this.kcxyje = kcxyje;
  }

 /**
 * 协议户数

 */
 private Long xyhs;

 /**
 * @hibernate.property column="xyhs"
 * @return Returns the xyhs.
 */
 public Long getXyhs() {
    return xyhs;
 }

 public void setXyhs(Long xyhs) {
    this.xyhs = xyhs;
  }

 /**
 * 预计歌华勘察费

 */
 private Long yjghkcf;

 /**
 * @hibernate.property column="yjghkcf"
 * @return Returns the yjghkcf.
 */
 public Long getYjghkcf() {
    return yjghkcf;
 }

 public void setYjghkcf(Long yjghkcf) {
    this.yjghkcf = yjghkcf;
  }

 /**
 * 所属行政区

 */
 private String ssxzq;

 /**
 * @hibernate.property column="ssxzq"
 * @return Returns the ssxzq.
 */
 public String getSsxzq() {
    return ssxzq;
 }

 public void setSsxzq(String ssxzq) {
    this.ssxzq = ssxzq;
  }

 /**
 * 歌华完成时限

 */
 private Date ghwcsx;

 /**
 * @hibernate.property column="ghwcsx"
 * @return Returns the ghwcsx.
 */
 public Date getGhwcsx() {
    return ghwcsx;
 }

 public void setGhwcsx(Date ghwcsx) {
    this.ghwcsx = ghwcsx;
  }

 /**
 * 付费标准

 */
 private String yfbz;

 /**
 * @hibernate.property column="yfbz"
 * @return Returns the yfbz.
 */
 public String getYfbz() {
    return yfbz;
 }

 public void setYfbz(String yfbz) {
    this.yfbz = yfbz;
  }

 /**
 * 报图日期

 */
 private Date btrq;

 /**
 * @hibernate.property column="btrq"
 * @return Returns the btrq.
 */
 public Date getBtrq() {
    return btrq;
 }

 public void setBtrq(Date btrq) {
    this.btrq = btrq;
  }

 /**
 * 结算金额

 */
 private Long jsje;

 /**
 * @hibernate.property column="jsje"
 * @return Returns the jsje.
 */
 public Long getJsje() {
    return jsje;
 }

 public void setJsje(Long jsje) {
    this.jsje = jsje;
  }

 /**
 * 结算日期

 */
 private Date jsrq;

 /**
 * @hibernate.property column="jsrq"
 * @return Returns the jsrq.
 */
 public Date getJsrq() {
    return jsrq;
 }

 public void setJsrq(Date jsrq) {
    this.jsrq = jsrq;
  }

 /**
 * 视宽协议号

 */
 private String skxyh;

 /**
 * @hibernate.property column="skxyh"
 * @return Returns the skxyh.
 */
 public String getSkxyh() {
    return skxyh;
 }

 public void setSkxyh(String skxyh) {
    this.skxyh = skxyh;
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


}
