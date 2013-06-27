package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-06-27
 * @hibernate.class table="Td01_glsj"
 */

public class Td01_glsj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 98478590863283472L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td01_num"
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
 * 歌华任务号
 */
 private String ghrwh;

 /**
 * @hibernate.property column="ghrwh"
 * @return Returns the ghrwh.
 */
 public String getGhrwh() {
    return ghrwh;
 }

 public void setGhrwh(String ghrwh) {
    this.ghrwh = ghrwh;
  }

 /**
 * 勘察类型
 */
 private String kclx;

 /**
 * @hibernate.property column="kclx"
 * @return Returns the kclx.
 */
 public String getKclx() {
    return kclx;
 }

 public void setKclx(String kclx) {
    this.kclx = kclx;
  }

 /**
 * 专项工程
 */
 private String zxgc;

 /**
 * @hibernate.property column="zxgc"
 * @return Returns the zxgc.
 */
 public String getZxgc() {
    return zxgc;
 }

 public void setZxgc(String zxgc) {
    this.zxgc = zxgc;
  }

 /**
 * 区域
 */
 private String qy;

 /**
 * @hibernate.property column="qy"
 * @return Returns the qy.
 */
 public String getQy() {
    return qy;
 }

 public void setQy(String qy) {
    this.qy = qy;
  }

 /**
 * 节点名称(地址)
 */
 private String jdmc;

 /**
 * @hibernate.property column="jdmc"
 * @return Returns the jdmc.
 */
 public String getJdmc() {
    return jdmc;
 }

 public void setJdmc(String jdmc) {
    this.jdmc = jdmc;
  }

 /**
 * 任务完成情况
 */
 private String rwwcqk;

 /**
 * @hibernate.property column="rwwcqk"
 * @return Returns the rwwcqk.
 */
 public String getRwwcqk() {
    return rwwcqk;
 }

 public void setRwwcqk(String rwwcqk) {
    this.rwwcqk = rwwcqk;
  }

 /**
 * 勘察任务书接受日期
 */
 private Date kcrwsjsrq;

 /**
 * @hibernate.property column="kcrwsjsrq"
 * @return Returns the kcrwsjsrq.
 */
 public Date getKcrwsjsrq() {
    return kcrwsjsrq;
 }

 public void setKcrwsjsrq(Date kcrwsjsrq) {
    this.kcrwsjsrq = kcrwsjsrq;
  }

 /**
 * 歌华时限要求
 */
 private Date ghsxyq;

 /**
 * @hibernate.property column="ghsxyq"
 * @return Returns the ghsxyq.
 */
 public Date getGhsxyq() {
    return ghsxyq;
 }

 public void setGhsxyq(Date ghsxyq) {
    this.ghsxyq = ghsxyq;
  }

 /**
 * 视宽时限要求
 */
 private Date sksxyq;

 /**
 * @hibernate.property column="sksxyq"
 * @return Returns the sksxyq.
 */
 public Date getSksxyq() {
    return sksxyq;
 }

 public void setSksxyq(Date sksxyq) {
    this.sksxyq = sksxyq;
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
 * 架空(揽长)
 */
 private Long jk_lc;

 /**
 * @hibernate.property column="jk_lc"
 * @return Returns the jk_lc.
 */
 public Long getJk_lc() {
    return jk_lc;
 }

 public void setJk_lc(Long jk_lc) {
    this.jk_lc = jk_lc;
  }

 /**
 * 架空(路由)
 */
 private Long jk_ly;

 /**
 * @hibernate.property column="jk_ly"
 * @return Returns the jk_ly.
 */
 public Long getJk_ly() {
    return jk_ly;
 }

 public void setJk_ly(Long jk_ly) {
    this.jk_ly = jk_ly;
  }

 /**
 * 管道(揽长)
 */
 private Long gd_lc;

 /**
 * @hibernate.property column="gd_lc"
 * @return Returns the gd_lc.
 */
 public Long getGd_lc() {
    return gd_lc;
 }

 public void setGd_lc(Long gd_lc) {
    this.gd_lc = gd_lc;
  }

 /**
 * 管道(路由)
 */
 private Long gd_ly;

 /**
 * @hibernate.property column="gd_ly"
 * @return Returns the gd_ly.
 */
 public Long getGd_ly() {
    return gd_ly;
 }

 public void setGd_ly(Long gd_ly) {
    this.gd_ly = gd_ly;
  }

 /**
 * 拆除(揽长)
 */
 private Long cc_lc;

 /**
 * @hibernate.property column="cc_lc"
 * @return Returns the cc_lc.
 */
 public Long getCc_lc() {
    return cc_lc;
 }

 public void setCc_lc(Long cc_lc) {
    this.cc_lc = cc_lc;
  }

 /**
 * 拆除(路由)
 */
 private Long cc_ly;

 /**
 * @hibernate.property column="cc_ly"
 * @return Returns the cc_ly.
 */
 public Long getCc_ly() {
    return cc_ly;
 }

 public void setCc_ly(Long cc_ly) {
    this.cc_ly = cc_ly;
  }

 /**
 * 道路长
 */
 private Long dlc;

 /**
 * @hibernate.property column="dlc"
 * @return Returns the dlc.
 */
 public Long getDlc() {
    return dlc;
 }

 public void setDlc(Long dlc) {
    this.dlc = dlc;
  }

 /**
 * 管道需求长
 */
 private Long gdxqc;

 /**
 * @hibernate.property column="gdxqc"
 * @return Returns the gdxqc.
 */
 public Long getGdxqc() {
    return gdxqc;
 }

 public void setGdxqc(Long gdxqc) {
    this.gdxqc = gdxqc;
  }

 /**
 * 揽长和
 */
 private Long lch;

 /**
 * @hibernate.property column="lch"
 * @return Returns the lch.
 */
 public Long getLch() {
    return lch;
 }

 public void setLch(Long lch) {
    this.lch = lch;
  }

 /**
 * 路由和
 */
 private Long lyh;

 /**
 * @hibernate.property column="lyh"
 * @return Returns the lyh.
 */
 public Long getLyh() {
    return lyh;
 }

 public void setLyh(Long lyh) {
    this.lyh = lyh;
  }

 /**
 * 电子版
 */
 private String dzb;

 /**
 * @hibernate.property column="dzb"
 * @return Returns the dzb.
 */
 public String getDzb() {
    return dzb;
 }

 public void setDzb(String dzb) {
    this.dzb = dzb;
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
 * 情况说明
 */
 private String qksm;

 /**
 * @hibernate.property column="qksm"
 * @return Returns the qksm.
 */
 public String getQksm() {
    return qksm;
 }

 public void setQksm(String qksm) {
    this.qksm = qksm;
  }

 /**
 * 情况说明上报日期
 */
 private String qksmsbrq;

 /**
 * @hibernate.property column="qksmsbrq"
 * @return Returns the qksmsbrq.
 */
 public String getQksmsbrq() {
    return qksmsbrq;
 }

 public void setQksmsbrq(String qksmsbrq) {
    this.qksmsbrq = qksmsbrq;
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
 * 提交歌华日期
 */
 private Date tjghrq;

 /**
 * @hibernate.property column="tjghrq"
 * @return Returns the tjghrq.
 */
 public Date getTjghrq() {
    return tjghrq;
 }

 public void setTjghrq(Date tjghrq) {
    this.tjghrq = tjghrq;
  }

 /**
 * 结算长度
 */
 private Long jscd;

 /**
 * @hibernate.property column="jscd"
 * @return Returns the jscd.
 */
 public Long getJscd() {
    return jscd;
 }

 public void setJscd(Long jscd) {
    this.jscd = jscd;
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
 * 汇款日期
 */
 private Date hkrq;

 /**
 * @hibernate.property column="hkrq"
 * @return Returns the hkrq.
 */
 public Date getHkrq() {
    return hkrq;
 }

 public void setHkrq(Date hkrq) {
    this.hkrq = hkrq;
  }

 /**
 * 杆公里
 */
 private Long ggl;

 /**
 * @hibernate.property column="ggl"
 * @return Returns the ggl.
 */
 public Long getGgl() {
    return ggl;
 }

 public void setGgl(Long ggl) {
    this.ggl = ggl;
  }

 /**
 * 沟公里
 */
 private Long gougl;

 /**
 * @hibernate.property column="gougl"
 * @return Returns the gougl.
 */
 public Long getGougl() {
    return gougl;
 }

 public void setGougl(Long gougl) {
    this.gougl = gougl;
  }

 /**
 * 勘察费估算
 */
 private Long kcfgs;

 /**
 * @hibernate.property column="kcfgs"
 * @return Returns the kcfgs.
 */
 public Long getKcfgs() {
    return kcfgs;
 }

 public void setKcfgs(Long kcfgs) {
    this.kcfgs = kcfgs;
  }

 /**
 * 视频协议编号
 */
 private String spxybh;

 /**
 * @hibernate.property column="spxybh"
 * @return Returns the spxybh.
 */
 public String getSpxybh() {
    return spxybh;
 }

 public void setSpxybh(String spxybh) {
    this.spxybh = spxybh;
  }

 /**
 * 任务来源2
 */
 private String rwly_2;

 /**
 * @hibernate.property column="rwly_2"
 * @return Returns the rwly_2.
 */
 public String getRwly_2() {
    return rwly_2;
 }

 public void setRwly_2(String rwly_2) {
    this.rwly_2 = rwly_2;
  }


}
