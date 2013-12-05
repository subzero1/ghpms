package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-12-04
 * @hibernate.class table="Td11_glgc"
 */

public class Td11_glgc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 10610792660247558L;

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
 * N编号
 */
 private String n_bh;

 /**
 * @hibernate.property column="n_bh"
 * @return Returns the n_bh.
 */
 public String getN_bh() {
    return n_bh;
 }

 public void setN_bh(String n_bh) {
    this.n_bh = n_bh;
  }

 /**
 * 协议盖章返回视宽日期
 */
 private Date xygzfhskrq;

 /**
 * @hibernate.property column="xygzfhskrq"
 * @return Returns the xygzfhskrq.
 */
 public Date getXygzfhskrq() {
    return xygzfhskrq;
 }

 public void setXygzfhskrq(Date xygzfhskrq) {
    this.xygzfhskrq = xygzfhskrq;
  }

 /**
 * 添加视宽协议签订日期
 */
 private Date tjskxyqdrq;

 /**
 * @hibernate.property column="tjskxyqdrq"
 * @return Returns the tjskxyqdrq.
 */
 public Date getTjskxyqdrq() {
    return tjskxyqdrq;
 }

 public void setTjskxyqdrq(Date tjskxyqdrq) {
    this.tjskxyqdrq = tjskxyqdrq;
  }

 /**
 * 视宽协议金额
 */
 private Long skxyje;

 /**
 * @hibernate.property column="skxyje"
 * @return Returns the skxyje.
 */
 public Long getSkxyje() {
    return skxyje;
 }

 public void setSkxyje(Long skxyje) {
    this.skxyje = skxyje;
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
 * 机房代号
 */
 private String jfdh;

 /**
 * @hibernate.property column="jfdh"
 * @return Returns the jfdh.
 */
 public String getJfdh() {
    return jfdh;
 }

 public void setJfdh(String jfdh) {
    this.jfdh = jfdh;
  }

 /**
 * 工程地址
 */
 private String gcdz;

 /**
 * @hibernate.property column="gcdz"
 * @return Returns the gcdz.
 */
 public String getGcdz() {
    return gcdz;
 }

 public void setGcdz(String gcdz) {
    this.gcdz = gcdz;
  }

 /**
 * 任务来源类型[选项]
 */
 private String rwlylx;

 /**
 * @hibernate.property column="rwlylx"
 * @return Returns the rwlylx.
 */
 public String getRwlylx() {
    return rwlylx;
 }

 public void setRwlylx(String rwlylx) {
    this.rwlylx = rwlylx;
  }

 /**
 * 歌华任务接收日期
 */
 private Date ghrwjsrq;

 /**
 * @hibernate.property column="ghrwjsrq"
 * @return Returns the ghrwjsrq.
 */
 public Date getGhrwjsrq() {
    return ghrwjsrq;
 }

 public void setGhrwjsrq(Date ghrwjsrq) {
    this.ghrwjsrq = ghrwjsrq;
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

 /**
 * 歌华监理[人员]
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
 * 专项工程[选项]
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
 * 计建部类型[选项]
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
 * 歌华协议接收日期
 */
 private Date ghxyjsrq;

 /**
 * @hibernate.property column="ghxyjsrq"
 * @return Returns the ghxyjsrq.
 */
 public Date getGhxyjsrq() {
    return ghxyjsrq;
 }

 public void setGhxyjsrq(Date ghxyjsrq) {
    this.ghxyjsrq = ghxyjsrq;
  }

 /**
 * 视宽盖章返回歌华日期
 */
 private Date skgzfhghrq;

 /**
 * @hibernate.property column="skgzfhghrq"
 * @return Returns the skgzfhghrq.
 */
 public Date getSkgzfhghrq() {
    return skgzfhghrq;
 }

 public void setSkgzfhghrq(Date skgzfhghrq) {
    this.skgzfhghrq = skgzfhghrq;
  }

 /**
 * 歌华协议返回日期
 */
 private Date ghxyfhrq;

 /**
 * @hibernate.property column="ghxyfhrq"
 * @return Returns the ghxyfhrq.
 */
 public Date getGhxyfhrq() {
    return ghxyfhrq;
 }

 public void setGhxyfhrq(Date ghxyfhrq) {
    this.ghxyfhrq = ghxyfhrq;
  }

 /**
 * 架空（协议）
 */
 private Long jk_xy;

 /**
 * @hibernate.property column="jk_xy"
 * @return Returns the jk_xy.
 */
 public Long getJk_xy() {
    return jk_xy;
 }

 public void setJk_xy(Long jk_xy) {
    this.jk_xy = jk_xy;
  }

 /**
 * 管道（协议）
 */
 private Long gd_xy;

 /**
 * @hibernate.property column="gd_xy"
 * @return Returns the gd_xy.
 */
 public Long getGd_xy() {
    return gd_xy;
 }

 public void setGd_xy(Long gd_xy) {
    this.gd_xy = gd_xy;
  }

 /**
 * 拆除（协议）
 */
 private Long cc_xy;

 /**
 * @hibernate.property column="cc_xy"
 * @return Returns the cc_xy.
 */
 public Long getCc_xy() {
    return cc_xy;
 }

 public void setCc_xy(Long cc_xy) {
    this.cc_xy = cc_xy;
  }

 /**
 * 子管（协议）
 */
 private Long zg_xy;

 /**
 * @hibernate.property column="zg_xy"
 * @return Returns the zg_xy.
 */
 public Long getZg_xy() {
    return zg_xy;
 }

 public void setZg_xy(Long zg_xy) {
    this.zg_xy = zg_xy;
  }

 /**
 * 熔接（协议）
 */
 private Long rj_xy;

 /**
 * @hibernate.property column="rj_xy"
 * @return Returns the rj_xy.
 */
 public Long getRj_xy() {
    return rj_xy;
 }

 public void setRj_xy(Long rj_xy) {
    this.rj_xy = rj_xy;
  }

 /**
 * 上架（协议）
 */
 private Long sj_xy;

 /**
 * @hibernate.property column="sj_xy"
 * @return Returns the sj_xy.
 */
 public Long getSj_xy() {
    return sj_xy;
 }

 public void setSj_xy(Long sj_xy) {
    this.sj_xy = sj_xy;
  }

 /**
 * 装调（协议）
 */
 private Long zt_xy;

 /**
 * @hibernate.property column="zt_xy"
 * @return Returns the zt_xy.
 */
 public Long getZt_xy() {
    return zt_xy;
 }

 public void setZt_xy(Long zt_xy) {
    this.zt_xy = zt_xy;
  }

 /**
 * 24芯以下开盒（协议）
 */
 private Long kh_24;

 /**
 * @hibernate.property column="kh_24"
 * @return Returns the kh_24.
 */
 public Long getKh_24() {
    return kh_24;
 }

 public void setKh_24(Long kh_24) {
    this.kh_24 = kh_24;
  }

 /**
 * 28—100芯开盒（协议）
 */
 private Long kh_100;

 /**
 * @hibernate.property column="kh_100"
 * @return Returns the kh_100.
 */
 public Long getKh_100() {
    return kh_100;
 }

 public void setKh_100(Long kh_100) {
    this.kh_100 = kh_100;
  }

 /**
 * 104芯以上开盒（协议）
 */
 private Long kh_104;

 /**
 * @hibernate.property column="kh_104"
 * @return Returns the kh_104.
 */
 public Long getKh_104() {
    return kh_104;
 }

 public void setKh_104(Long kh_104) {
    this.kh_104 = kh_104;
  }

 /**
 * 拉线（协议）
 */
 private Long lx_xy;

 /**
 * @hibernate.property column="lx_xy"
 * @return Returns the lx_xy.
 */
 public Long getLx_xy() {
    return lx_xy;
 }

 public void setLx_xy(Long lx_xy) {
    this.lx_xy = lx_xy;
  }

 /**
 * 熔接架空敷设
 */
 private Long rjjkfs;

 /**
 * @hibernate.property column="rjjkfs"
 * @return Returns the rjjkfs.
 */
 public Long getRjjkfs() {
    return rjjkfs;
 }

 public void setRjjkfs(Long rjjkfs) {
    this.rjjkfs = rjjkfs;
  }

 /**
 * 熔接拆除光缆
 */
 private Long rjccgl;

 /**
 * @hibernate.property column="rjccgl"
 * @return Returns the rjccgl.
 */
 public Long getRjccgl() {
    return rjccgl;
 }

 public void setRjccgl(Long rjccgl) {
    this.rjccgl = rjccgl;
  }

 /**
 * 8米立杆（协议）
 */
 private Long lg_8;

 /**
 * @hibernate.property column="lg_8"
 * @return Returns the lg_8.
 */
 public Long getLg_8() {
    return lg_8;
 }

 public void setLg_8(Long lg_8) {
    this.lg_8 = lg_8;
  }

 /**
 * 10米立杆（协议）
 */
 private Long lg_10;

 /**
 * @hibernate.property column="lg_10"
 * @return Returns the lg_10.
 */
 public Long getLg_10() {
    return lg_10;
 }

 public void setLg_10(Long lg_10) {
    this.lg_10 = lg_10;
  }

 /**
 * 12米立杆（协议）
 */
 private Long lg_12;

 /**
 * @hibernate.property column="lg_12"
 * @return Returns the lg_12.
 */
 public Long getLg_12() {
    return lg_12;
 }

 public void setLg_12(Long lg_12) {
    this.lg_12 = lg_12;
  }

 /**
 * 档案编号
 */
 private String dabh;

 /**
 * @hibernate.property column="dabh"
 * @return Returns the dabh.
 */
 public String getDabh() {
    return dabh;
 }

 public void setDabh(String dabh) {
    this.dabh = dabh;
  }

 /**
 * 存档日期
 */
 private Date cdrq;

 /**
 * @hibernate.property column="cdrq"
 * @return Returns the cdrq.
 */
 public Date getCdrq() {
    return cdrq;
 }

 public void setCdrq(Date cdrq) {
    this.cdrq = cdrq;
  }

 /**
 * 视宽开工日期
 */
 private Date skkgrq;

 /**
 * @hibernate.property column="skkgrq"
 * @return Returns the skkgrq.
 */
 public Date getSkkgrq() {
    return skkgrq;
 }

 public void setSkkgrq(Date skkgrq) {
    this.skkgrq = skkgrq;
  }

 /**
 * 敷设单位[选项]
 */
 private String fsdw;

 /**
 * @hibernate.property column="fsdw"
 * @return Returns the fsdw.
 */
 public String getFsdw() {
    return fsdw;
 }

 public void setFsdw(String fsdw) {
    this.fsdw = fsdw;
  }

 /**
 * 熔接单位[选项]
 */
 private String rjdw;

 /**
 * @hibernate.property column="rjdw"
 * @return Returns the rjdw.
 */
 public String getRjdw() {
    return rjdw;
 }

 public void setRjdw(String rjdw) {
    this.rjdw = rjdw;
  }

 /**
 * 两家以上施工单位情况
 */
 private String ljyssgdwqk;

 /**
 * @hibernate.property column="ljyssgdwqk"
 * @return Returns the ljyssgdwqk.
 */
 public String getLjyssgdwqk() {
    return ljyssgdwqk;
 }

 public void setLjyssgdwqk(String ljyssgdwqk) {
    this.ljyssgdwqk = ljyssgdwqk;
  }

 /**
 * 工程量分配情况
 */
 private String gclfpqk;

 /**
 * @hibernate.property column="gclfpqk"
 * @return Returns the gclfpqk.
 */
 public String getGclfpqk() {
    return gclfpqk;
 }

 public void setGclfpqk(String gclfpqk) {
    this.gclfpqk = gclfpqk;
  }

 /**
 * 断信号时间
 */
 private Date dxhsj;

 /**
 * @hibernate.property column="dxhsj"
 * @return Returns the dxhsj.
 */
 public Date getDxhsj() {
    return dxhsj;
 }

 public void setDxhsj(Date dxhsj) {
    this.dxhsj = dxhsj;
  }

 /**
 * 敷设状况[选项]
 */
 private String fszk;

 /**
 * @hibernate.property column="fszk"
 * @return Returns the fszk.
 */
 public String getFszk() {
    return fszk;
 }

 public void setFszk(String fszk) {
    this.fszk = fszk;
  }

 /**
 * 敷设完工日期
 */
 private Date fswgrq;

 /**
 * @hibernate.property column="fswgrq"
 * @return Returns the fswgrq.
 */
 public Date getFswgrq() {
    return fswgrq;
 }

 public void setFswgrq(Date fswgrq) {
    this.fswgrq = fswgrq;
  }

 /**
 * 熔接状况[选项]
 */
 private String rjzk;

 /**
 * @hibernate.property column="rjzk"
 * @return Returns the rjzk.
 */
 public String getRjzk() {
    return rjzk;
 }

 public void setRjzk(String rjzk) {
    this.rjzk = rjzk;
  }

 /**
 * 熔接完工日期
 */
 private Date rjwgrq;

 /**
 * @hibernate.property column="rjwgrq"
 * @return Returns the rjwgrq.
 */
 public Date getRjwgrq() {
    return rjwgrq;
 }

 public void setRjwgrq(Date rjwgrq) {
    this.rjwgrq = rjwgrq;
  }

 /**
 * 完工上架位
 */
 private String wgsjw;

 /**
 * @hibernate.property column="wgsjw"
 * @return Returns the wgsjw.
 */
 public String getWgsjw() {
    return wgsjw;
 }

 public void setWgsjw(String wgsjw) {
    this.wgsjw = wgsjw;
  }

 /**
 * 工程总状况[选项]
 */
 private String gczzk;

 /**
 * @hibernate.property column="gczzk"
 * @return Returns the gczzk.
 */
 public String getGczzk() {
    return gczzk;
 }

 public void setGczzk(String gczzk) {
    this.gczzk = gczzk;
  }

 /**
 * 工程完成日期
 */
 private Date gcwcrq;

 /**
 * @hibernate.property column="gcwcrq"
 * @return Returns the gcwcrq.
 */
 public Date getGcwcrq() {
    return gcwcrq;
 }

 public void setGcwcrq(Date gcwcrq) {
    this.gcwcrq = gcwcrq;
  }

 /**
 * 器材表下发日期
 */
 private Date qcbxfrq;

 /**
 * @hibernate.property column="qcbxfrq"
 * @return Returns the qcbxfrq.
 */
 public Date getQcbxfrq() {
    return qcbxfrq;
 }

 public void setQcbxfrq(Date qcbxfrq) {
    this.qcbxfrq = qcbxfrq;
  }

 /**
 * 勘察负责人[人员]
 */
 private String kcfzr;

 /**
 * @hibernate.property column="kcfzr"
 * @return Returns the kcfzr.
 */
 public String getKcfzr() {
    return kcfzr;
 }

 public void setKcfzr(String kcfzr) {
    this.kcfzr = kcfzr;
  }

 /**
 * 敷设负责人[人员]
 */
 private String fsfzr;

 /**
 * @hibernate.property column="fsfzr"
 * @return Returns the fsfzr.
 */
 public String getFsfzr() {
    return fsfzr;
 }

 public void setFsfzr(String fsfzr) {
    this.fsfzr = fsfzr;
  }

 /**
 * 熔接接单日期
 */
 private Date rhjdrq;

 /**
 * @hibernate.property column="rhjdrq"
 * @return Returns the rhjdrq.
 */
 public Date getRhjdrq() {
    return rhjdrq;
 }

 public void setRhjdrq(Date rhjdrq) {
    this.rhjdrq = rhjdrq;
  }

 /**
 * 熔接负责人[人员]
 */
 private String rjfzr;

 /**
 * @hibernate.property column="rjfzr"
 * @return Returns the rjfzr.
 */
 public String getRjfzr() {
    return rjfzr;
 }

 public void setRjfzr(String rjfzr) {
    this.rjfzr = rjfzr;
  }

 /**
 * 开工前复核完成日期
 */
 private Date kgqfhwcrq;

 /**
 * @hibernate.property column="kgqfhwcrq"
 * @return Returns the kgqfhwcrq.
 */
 public Date getKgqfhwcrq() {
    return kgqfhwcrq;
 }

 public void setKgqfhwcrq(Date kgqfhwcrq) {
    this.kgqfhwcrq = kgqfhwcrq;
  }

 /**
 * 复核结果
 */
 private String fhjg;

 /**
 * @hibernate.property column="fhjg"
 * @return Returns the fhjg.
 */
 public String getFhjg() {
    return fhjg;
 }

 public void setFhjg(String fhjg) {
    this.fhjg = fhjg;
  }

 /**
 * 光缆标牌下发日期
 */
 private Date glbpxfrq;

 /**
 * @hibernate.property column="glbpxfrq"
 * @return Returns the glbpxfrq.
 */
 public Date getGlbpxfrq() {
    return glbpxfrq;
 }

 public void setGlbpxfrq(Date glbpxfrq) {
    this.glbpxfrq = glbpxfrq;
  }

 /**
 * 整缆挂牌日期
 */
 private Date zlgprq;

 /**
 * @hibernate.property column="zlgprq"
 * @return Returns the zlgprq.
 */
 public Date getZlgprq() {
    return zlgprq;
 }

 public void setZlgprq(Date zlgprq) {
    this.zlgprq = zlgprq;
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
 private Date qksmsbrq;

 /**
 * @hibernate.property column="qksmsbrq"
 * @return Returns the qksmsbrq.
 */
 public Date getQksmsbrq() {
    return qksmsbrq;
 }

 public void setQksmsbrq(Date qksmsbrq) {
    this.qksmsbrq = qksmsbrq;
  }

 /**
 * 情况说明返回内容
 */
 private String qksmfhnr;

 /**
 * @hibernate.property column="qksmfhnr"
 * @return Returns the qksmfhnr.
 */
 public String getQksmfhnr() {
    return qksmfhnr;
 }

 public void setQksmfhnr(String qksmfhnr) {
    this.qksmfhnr = qksmfhnr;
  }

 /**
 * 情况说明返回日期
 */
 private Date qksmfhrq;

 /**
 * @hibernate.property column="qksmfhrq"
 * @return Returns the qksmfhrq.
 */
 public Date getQksmfhrq() {
    return qksmfhrq;
 }

 public void setQksmfhrq(Date qksmfhrq) {
    this.qksmfhrq = qksmfhrq;
  }

 /**
 * 申请补料内容
 */
 private Date sqblnr;

 /**
 * @hibernate.property column="sqblnr"
 * @return Returns the sqblnr.
 */
 public Date getSqblnr() {
    return sqblnr;
 }

 public void setSqblnr(Date sqblnr) {
    this.sqblnr = sqblnr;
  }

 /**
 * 申请退缆内容
 */
 private String sqtlnr;

 /**
 * @hibernate.property column="sqtlnr"
 * @return Returns the sqtlnr.
 */
 public String getSqtlnr() {
    return sqtlnr;
 }

 public void setSqtlnr(String sqtlnr) {
    this.sqtlnr = sqtlnr;
  }

 /**
 * 敷设报竣接单日期
 */
 private String fsbjjdrq;

 /**
 * @hibernate.property column="fsbjjdrq"
 * @return Returns the fsbjjdrq.
 */
 public String getFsbjjdrq() {
    return fsbjjdrq;
 }

 public void setFsbjjdrq(String fsbjjdrq) {
    this.fsbjjdrq = fsbjjdrq;
  }

 /**
 * 敷设报竣接单人[人员]
 */
 private String fsbjjdr;

 /**
 * @hibernate.property column="fsbjjdr"
 * @return Returns the fsbjjdr.
 */
 public String getFsbjjdr() {
    return fsbjjdr;
 }

 public void setFsbjjdr(String fsbjjdr) {
    this.fsbjjdr = fsbjjdr;
  }

 /**
 * 敷设报竣日期
 */
 private Date fsbjrq;

 /**
 * @hibernate.property column="fsbjrq"
 * @return Returns the fsbjrq.
 */
 public Date getFsbjrq() {
    return fsbjrq;
 }

 public void setFsbjrq(Date fsbjrq) {
    this.fsbjrq = fsbjrq;
  }

 /**
 * 竣工架空
 */
 private Long jgjk;

 /**
 * @hibernate.property column="jgjk"
 * @return Returns the jgjk.
 */
 public Long getJgjk() {
    return jgjk;
 }

 public void setJgjk(Long jgjk) {
    this.jgjk = jgjk;
  }

 /**
 * 竣工管道敷设(米)
 */
 private Long jggdfs;

 /**
 * @hibernate.property column="jggdfs"
 * @return Returns the jggdfs.
 */
 public Long getJggdfs() {
    return jggdfs;
 }

 public void setJggdfs(Long jggdfs) {
    this.jggdfs = jggdfs;
  }

 /**
 * 吹缆(米)
 */
 private Long cl;

 /**
 * @hibernate.property column="cl"
 * @return Returns the cl.
 */
 public Long getCl() {
    return cl;
 }

 public void setCl(Long cl) {
    this.cl = cl;
  }

 /**
 * 竣工拆除(米)
 */
 private Long jgcc;

 /**
 * @hibernate.property column="jgcc"
 * @return Returns the jgcc.
 */
 public Long getJgcc() {
    return jgcc;
 }

 public void setJgcc(Long jgcc) {
    this.jgcc = jgcc;
  }

 /**
 * 竣工子管敷设(米)
 */
 private Long jgzgfs;

 /**
 * @hibernate.property column="jgzgfs"
 * @return Returns the jgzgfs.
 */
 public Long getJgzgfs() {
    return jgzgfs;
 }

 public void setJgzgfs(Long jgzgfs) {
    this.jgzgfs = jgzgfs;
  }

 /**
 * 计划完成时间
 */
 private Date jhwcsj;

 /**
 * @hibernate.property column="jhwcsj"
 * @return Returns the jhwcsj.
 */
 public Date getJhwcsj() {
    return jhwcsj;
 }

 public void setJhwcsj(Date jhwcsj) {
    this.jhwcsj = jhwcsj;
  }

 /**
 * 实际完成时间
 */
 private Date sjwcsj;

 /**
 * @hibernate.property column="sjwcsj"
 * @return Returns the sjwcsj.
 */
 public Date getSjwcsj() {
    return sjwcsj;
 }

 public void setSjwcsj(Date sjwcsj) {
    this.sjwcsj = sjwcsj;
  }

 /**
 * 市场部协议号
 */
 private String sjbxybh;

 /**
 * @hibernate.property column="sjbxybh"
 * @return Returns the sjbxybh.
 */
 public String getSjbxybh() {
    return sjbxybh;
 }

 public void setSjbxybh(String sjbxybh) {
    this.sjbxybh = sjbxybh;
  }

 /**
 * 二次补退料领料部门
 */
 private String llbm_btl2;

 /**
 * @hibernate.property column="llbm_btl2"
 * @return Returns the llbm_btl2.
 */
 public String getLlbm_btl2() {
    return llbm_btl2;
 }

 public void setLlbm_btl2(String llbm_btl2) {
    this.llbm_btl2 = llbm_btl2;
  }

 /**
 * 二次补退料领料人
 */
 private String llr_btl2;

 /**
 * @hibernate.property column="llr_btl2"
 * @return Returns the llr_btl2.
 */
 public String getLlr_btl2() {
    return llr_btl2;
 }

 public void setLlr_btl2(String llr_btl2) {
    this.llr_btl2 = llr_btl2;
  }

 /**
 * 二次补退料发料人
 */
 private String flr_btl2;

 /**
 * @hibernate.property column="flr_btl2"
 * @return Returns the flr_btl2.
 */
 public String getFlr_btl2() {
    return flr_btl2;
 }

 public void setFlr_btl2(String flr_btl2) {
    this.flr_btl2 = flr_btl2;
  }

 /**
 * 出具此工程最终器材施工使用量出单人

 */
 private String cjcgczzqcsgsylcdr;

 /**
 * @hibernate.property column="cjcgczzqcsgsylcdr"
 * @return Returns the cjcgczzqcsgsylcdr.
 */
 public String getCjcgczzqcsgsylcdr() {
    return cjcgczzqcsgsylcdr;
 }

 public void setCjcgczzqcsgsylcdr(String cjcgczzqcsgsylcdr) {
    this.cjcgczzqcsgsylcdr = cjcgczzqcsgsylcdr;
  }

 /**
 * 出具此工程最终器材施工使用量时间
 */
 private Date cjcgczzqcsgsylsj;

 /**
 * @hibernate.property column="cjcgczzqcsgsylsj"
 * @return Returns the cjcgczzqcsgsylsj.
 */
 public Date getCjcgczzqcsgsylsj() {
    return cjcgczzqcsgsylsj;
 }

 public void setCjcgczzqcsgsylsj(Date cjcgczzqcsgsylsj) {
    this.cjcgczzqcsgsylsj = cjcgczzqcsgsylsj;
  }

 /**
 * 歌华器材结算核对完成时间
 */
 private Date ghqcjshdwcsj;

 /**
 * @hibernate.property column="ghqcjshdwcsj"
 * @return Returns the ghqcjshdwcsj.
 */
 public Date getGhqcjshdwcsj() {
    return ghqcjshdwcsj;
 }

 public void setGhqcjshdwcsj(Date ghqcjshdwcsj) {
    this.ghqcjshdwcsj = ghqcjshdwcsj;
  }

 /**
 * 与歌华核对人
 */
 private String yghhdr;

 /**
 * @hibernate.property column="yghhdr"
 * @return Returns the yghhdr.
 */
 public String getYghhdr() {
    return yghhdr;
 }

 public void setYghhdr(String yghhdr) {
    this.yghhdr = yghhdr;
  }

 /**
 * 收到合同结算部结算单
 */
 private Date sdhtjsbjsd;

 /**
 * @hibernate.property column="sdhtjsbjsd"
 * @return Returns the sdhtjsbjsd.
 */
 public Date getSdhtjsbjsd() {
    return sdhtjsbjsd;
 }

 public void setSdhtjsbjsd(Date sdhtjsbjsd) {
    this.sdhtjsbjsd = sdhtjsbjsd;
  }

 /**
 * 完成审核合同结算部结算单
 */
 private Date wcshhtjsbjsd;

 /**
 * @hibernate.property column="wcshhtjsbjsd"
 * @return Returns the wcshhtjsbjsd.
 */
 public Date getWcshhtjsbjsd() {
    return wcshhtjsbjsd;
 }

 public void setWcshhtjsbjsd(Date wcshhtjsbjsd) {
    this.wcshhtjsbjsd = wcshhtjsbjsd;
  }

 /**
 * 结算单审核人
 */
 private String jsdshr;

 /**
 * @hibernate.property column="jsdshr"
 * @return Returns the jsdshr.
 */
 public String getJsdshr() {
    return jsdshr;
 }

 public void setJsdshr(String jsdshr) {
    this.jsdshr = jsdshr;
  }

 /**
 * 收到施工部门转交自备料结算表
 */
 private Date zbljsb_sdsgbmzj;

 /**
 * @hibernate.property column="zbljsb_sdsgbmzj"
 * @return Returns the zbljsb_sdsgbmzj.
 */
 public Date getZbljsb_sdsgbmzj() {
    return zbljsb_sdsgbmzj;
 }

 public void setZbljsb_sdsgbmzj(Date zbljsb_sdsgbmzj) {
    this.zbljsb_sdsgbmzj = zbljsb_sdsgbmzj;
  }

 /**
 * 完成审核自备料结算表
 */
 private Date zbljsb_wcsh;

 /**
 * @hibernate.property column="zbljsb_wcsh"
 * @return Returns the zbljsb_wcsh.
 */
 public Date getZbljsb_wcsh() {
    return zbljsb_wcsh;
 }

 public void setZbljsb_wcsh(Date zbljsb_wcsh) {
    this.zbljsb_wcsh = zbljsb_wcsh;
  }

 /**
 * 审核自备料结算表审核人
 */
 private String zbljsb_shshr;

 /**
 * @hibernate.property column="zbljsb_shshr"
 * @return Returns the zbljsb_shshr.
 */
 public String getZbljsb_shshr() {
    return zbljsb_shshr;
 }

 public void setZbljsb_shshr(String zbljsb_shshr) {
    this.zbljsb_shshr = zbljsb_shshr;
  }

 /**
 * 收到合同结算部撤令结算单时间
 */
 private Date sdhtjsb_cljsdsj;

 /**
 * @hibernate.property column="sdhtjsb_cljsdsj"
 * @return Returns the sdhtjsb_cljsdsj.
 */
 public Date getSdhtjsb_cljsdsj() {
    return sdhtjsb_cljsdsj;
 }

 public void setSdhtjsb_cljsdsj(Date sdhtjsb_cljsdsj) {
    this.sdhtjsb_cljsdsj = sdhtjsb_cljsdsj;
  }

 /**
 * 完成合同结算部撤令结算单时间
 */
 private Date wchtb_cljsdsj;

 /**
 * @hibernate.property column="wchtb_cljsdsj"
 * @return Returns the wchtb_cljsdsj.
 */
 public Date getWchtb_cljsdsj() {
    return wchtb_cljsdsj;
 }

 public void setWchtb_cljsdsj(Date wchtb_cljsdsj) {
    this.wchtb_cljsdsj = wchtb_cljsdsj;
  }

 /**
 * 撤令结算表审核人
 */
 private String cljsbshr;

 /**
 * @hibernate.property column="cljsbshr"
 * @return Returns the cljsbshr.
 */
 public String getCljsbshr() {
    return cljsbshr;
 }

 public void setCljsbshr(String cljsbshr) {
    this.cljsbshr = cljsbshr;
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
 * 对上结算金额
 */
 private Long dsjsje;

 /**
 * @hibernate.property column="dsjsje"
 * @return Returns the dsjsje.
 */
 public Long getDsjsje() {
    return dsjsje;
 }

 public void setDsjsje(Long dsjsje) {
    this.dsjsje = dsjsje;
  }

 /**
 * 结算状态说明[选项]
 */
 private String jsztsm;

 /**
 * @hibernate.property column="jsztsm"
 * @return Returns the jsztsm.
 */
 public String getJsztsm() {
    return jsztsm;
 }

 public void setJsztsm(String jsztsm) {
    this.jsztsm = jsztsm;
  }

 /**
 * 对上转结算日期
 */
 private Date dszjsrq;

 /**
 * @hibernate.property column="dszjsrq"
 * @return Returns the dszjsrq.
 */
 public Date getDszjsrq() {
    return dszjsrq;
 }

 public void setDszjsrq(Date dszjsrq) {
    this.dszjsrq = dszjsrq;
  }

 /**
 * 对上转结算批次
 */
 private String dszjspc;

 /**
 * @hibernate.property column="dszjspc"
 * @return Returns the dszjspc.
 */
 public String getDszjspc() {
    return dszjspc;
 }

 public void setDszjspc(String dszjspc) {
    this.dszjspc = dszjspc;
  }

 /**
 * 对上开发票日期
 */
 private Date dszkfprq;

 /**
 * @hibernate.property column="dszkfprq"
 * @return Returns the dszkfprq.
 */
 public Date getDszkfprq() {
    return dszkfprq;
 }

 public void setDszkfprq(Date dszkfprq) {
    this.dszkfprq = dszkfprq;
  }

 /**
 * 对上回款日期
 */
 private Date dshkrq;

 /**
 * @hibernate.property column="dshkrq"
 * @return Returns the dshkrq.
 */
 public Date getDshkrq() {
    return dshkrq;
 }

 public void setDshkrq(Date dshkrq) {
    this.dshkrq = dshkrq;
  }

 /**
 * 工程年份[选项]
 */
 private Long gcnf;

 /**
 * @hibernate.property column="gcnf"
 * @return Returns the gcnf.
 */
 public Long getGcnf() {
    return gcnf;
 }

 public void setGcnf(Long gcnf) {
    this.gcnf = gcnf;
  }

 /**
 * 上报器材结算日期
 */
 private Date sbqcjsrq;

 /**
 * @hibernate.property column="sbqcjsrq"
 * @return Returns the sbqcjsrq.
 */
 public Date getSbqcjsrq() {
    return sbqcjsrq;
 }

 public void setSbqcjsrq(Date sbqcjsrq) {
    this.sbqcjsrq = sbqcjsrq;
  }

 /**
 * 对上器材结算日期
 */
 private Date dsqcjsrq;

 /**
 * @hibernate.property column="dsqcjsrq"
 * @return Returns the dsqcjsrq.
 */
 public Date getDsqcjsrq() {
    return dsqcjsrq;
 }

 public void setDsqcjsrq(Date dsqcjsrq) {
    this.dsqcjsrq = dsqcjsrq;
  }

 /**
 * 对下结算进展[选项]
 */
 private String dxjsjz;

 /**
 * @hibernate.property column="dxjsjz"
 * @return Returns the dxjsjz.
 */
 public String getDxjsjz() {
    return dxjsjz;
 }

 public void setDxjsjz(String dxjsjz) {
    this.dxjsjz = dxjsjz;
  }

 /**
 * 对下结算特殊情况说明
 */
 private String dxjstsqksm;

 /**
 * @hibernate.property column="dxjstsqksm"
 * @return Returns the dxjstsqksm.
 */
 public String getDxjstsqksm() {
    return dxjstsqksm;
 }

 public void setDxjstsqksm(String dxjstsqksm) {
    this.dxjstsqksm = dxjstsqksm;
  }

 /**
 * 接收对下结算任务日期
 */
 private Date jsdxjsrwrq;

 /**
 * @hibernate.property column="jsdxjsrwrq"
 * @return Returns the jsdxjsrwrq.
 */
 public Date getJsdxjsrwrq() {
    return jsdxjsrwrq;
 }

 public void setJsdxjsrwrq(Date jsdxjsrwrq) {
    this.jsdxjsrwrq = jsdxjsrwrq;
  }

 /**
 * 对下结算标准
 */
 private String dxjsbz;

 /**
 * @hibernate.property column="dxjsbz"
 * @return Returns the dxjsbz.
 */
 public String getDxjsbz() {
    return dxjsbz;
 }

 public void setDxjsbz(String dxjsbz) {
    this.dxjsbz = dxjsbz;
  }

 /**
 * 对下结算金额
 */
 private Long dxjsje;

 /**
 * @hibernate.property column="dxjsje"
 * @return Returns the dxjsje.
 */
 public Long getDxjsje() {
    return dxjsje;
 }

 public void setDxjsje(Long dxjsje) {
    this.dxjsje = dxjsje;
  }

 /**
 * 结算单签字日期
 */
 private Date jsdqzrq;

 /**
 * @hibernate.property column="jsdqzrq"
 * @return Returns the jsdqzrq.
 */
 public Date getJsdqzrq() {
    return jsdqzrq;
 }

 public void setJsdqzrq(Date jsdqzrq) {
    this.jsdqzrq = jsdqzrq;
  }

 /**
 * 财务审核日期
 */
 private Date cwshrq;

 /**
 * @hibernate.property column="cwshrq"
 * @return Returns the cwshrq.
 */
 public Date getCwshrq() {
    return cwshrq;
 }

 public void setCwshrq(Date cwshrq) {
    this.cwshrq = cwshrq;
  }

 /**
 * 对下结算批次
 */
 private String dxjspc;

 /**
 * @hibernate.property column="dxjspc"
 * @return Returns the dxjspc.
 */
 public String getDxjspc() {
    return dxjspc;
 }

 public void setDxjspc(String dxjspc) {
    this.dxjspc = dxjspc;
  }

 /**
 * 对下付款批准日期
 */
 private Date dxfkpcrq;

 /**
 * @hibernate.property column="dxfkpcrq"
 * @return Returns the dxfkpcrq.
 */
 public Date getDxfkpcrq() {
    return dxfkpcrq;
 }

 public void setDxfkpcrq(Date dxfkpcrq) {
    this.dxfkpcrq = dxfkpcrq;
  }

 /**
 * 领款日期
 */
 private Date lkrq;

 /**
 * @hibernate.property column="lkrq"
 * @return Returns the lkrq.
 */
 public Date getLkrq() {
    return lkrq;
 }

 public void setLkrq(Date lkrq) {
    this.lkrq = lkrq;
  }

 /**
 * 对下预付款金额
 */
 private Long dxyfkje;

 /**
 * @hibernate.property column="dxyfkje"
 * @return Returns the dxyfkje.
 */
 public Long getDxyfkje() {
    return dxyfkje;
 }

 public void setDxyfkje(Long dxyfkje) {
    this.dxyfkje = dxyfkje;
  }

 /**
 * 对下预付款批次
 */
 private String dxyfkpc;

 /**
 * @hibernate.property column="dxyfkpc"
 * @return Returns the dxyfkpc.
 */
 public String getDxyfkpc() {
    return dxyfkpc;
 }

 public void setDxyfkpc(String dxyfkpc) {
    this.dxyfkpc = dxyfkpc;
  }

 /**
 * 预付款领款日期
 */
 private Date yfklkrq;

 /**
 * @hibernate.property column="yfklkrq"
 * @return Returns the yfklkrq.
 */
 public Date getYfklkrq() {
    return yfklkrq;
 }

 public void setYfklkrq(Date yfklkrq) {
    this.yfklkrq = yfklkrq;
  }

 /**
 * 公司内部结算价
 */
 private Long gsnbjsj;

 /**
 * @hibernate.property column="gsnbjsj"
 * @return Returns the gsnbjsj.
 */
 public Long getGsnbjsj() {
    return gsnbjsj;
 }

 public void setGsnbjsj(Long gsnbjsj) {
    this.gsnbjsj = gsnbjsj;
  }

 /**
 * 对上合同办公室盖章时间
 */
 private Date dshtbgsgzsj;

 /**
 * @hibernate.property column="dshtbgsgzsj"
 * @return Returns the dshtbgsgzsj.
 */
 public Date getDshtbgsgzsj() {
    return dshtbgsgzsj;
 }

 public void setDshtbgsgzsj(Date dshtbgsgzsj) {
    this.dshtbgsgzsj = dshtbgsgzsj;
  }

 /**
 * 对下合同办公室盖章时间
 */
 private Date dxhtbgsgzsj;

 /**
 * @hibernate.property column="dxhtbgsgzsj"
 * @return Returns the dxhtbgsgzsj.
 */
 public Date getDxhtbgsgzsj() {
    return dxhtbgsgzsj;
 }

 public void setDxhtbgsgzsj(Date dxhtbgsgzsj) {
    this.dxhtbgsgzsj = dxhtbgsgzsj;
  }

 /**
 * 对上合同原件转发财务部时间
 */
 private Date dshtyjzfcwbsj;

 /**
 * @hibernate.property column="dshtyjzfcwbsj"
 * @return Returns the dshtyjzfcwbsj.
 */
 public Date getDshtyjzfcwbsj() {
    return dshtyjzfcwbsj;
 }

 public void setDshtyjzfcwbsj(Date dshtyjzfcwbsj) {
    this.dshtyjzfcwbsj = dshtyjzfcwbsj;
  }

 /**
 * 对下合同原件转发财务部时间
 */
 private Date dxhtyjzfcwbsj;

 /**
 * @hibernate.property column="dxhtyjzfcwbsj"
 * @return Returns the dxhtyjzfcwbsj.
 */
 public Date getDxhtyjzfcwbsj() {
    return dxhtyjzfcwbsj;
 }

 public void setDxhtyjzfcwbsj(Date dxhtyjzfcwbsj) {
    this.dxhtyjzfcwbsj = dxhtyjzfcwbsj;
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
 * 竣工立8米木电杆(棵)
 */
 private Long jgmdg_8;

 /**
 * @hibernate.property column="jgmdg_8"
 * @return Returns the jgmdg_8.
 */
 public Long getJgmdg_8() {
    return jgmdg_8;
 }

 public void setJgmdg_8(Long jgmdg_8) {
    this.jgmdg_8 = jgmdg_8;
  }

 /**
 * 竣工立8米水泥电杆(棵)
 */
 private Long jgsndg_8;

 /**
 * @hibernate.property column="jgsndg_8"
 * @return Returns the jgsndg_8.
 */
 public Long getJgsndg_8() {
    return jgsndg_8;
 }

 public void setJgsndg_8(Long jgsndg_8) {
    this.jgsndg_8 = jgsndg_8;
  }

 /**
 * 竣工立10米水泥电杆(棵)
 */
 private Long jgsndg_10;

 /**
 * @hibernate.property column="jgsndg_10"
 * @return Returns the jgsndg_10.
 */
 public Long getJgsndg_10() {
    return jgsndg_10;
 }

 public void setJgsndg_10(Long jgsndg_10) {
    this.jgsndg_10 = jgsndg_10;
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
 * 竣工固定拉线(根)
 */
 private Long jggdlx;

 /**
 * @hibernate.property column="jggdlx"
 * @return Returns the jggdlx.
 */
 public Long getJggdlx() {
    return jggdlx;
 }

 public void setJggdlx(Long jggdlx) {
    this.jggdlx = jggdlx;
  }

 /**
 * 熔接报竣接单日期
 */
 private Date rjbjjdrq;

 /**
 * @hibernate.property column="rjbjjdrq"
 * @return Returns the rjbjjdrq.
 */
 public Date getRjbjjdrq() {
    return rjbjjdrq;
 }

 public void setRjbjjdrq(Date rjbjjdrq) {
    this.rjbjjdrq = rjbjjdrq;
  }

 /**
 * 熔接报竣接单人[人员]
 */
 private String rjbjjdr;

 /**
 * @hibernate.property column="rjbjjdr"
 * @return Returns the rjbjjdr.
 */
 public String getRjbjjdr() {
    return rjbjjdr;
 }

 public void setRjbjjdr(String rjbjjdr) {
    this.rjbjjdr = rjbjjdr;
  }

 /**
 * 熔接报竣日期
 */
 private Date rjbjrq;

 /**
 * @hibernate.property column="rjbjrq"
 * @return Returns the rjbjrq.
 */
 public Date getRjbjrq() {
    return rjbjrq;
 }

 public void setRjbjrq(Date rjbjrq) {
    this.rjbjrq = rjbjrq;
  }

 /**
 * 报竣上架位置
 */
 private String bjsjwz;

 /**
 * @hibernate.property column="bjsjwz"
 * @return Returns the bjsjwz.
 */
 public String getBjsjwz() {
    return bjsjwz;
 }

 public void setBjsjwz(String bjsjwz) {
    this.bjsjwz = bjsjwz;
  }

 /**
 * 报竣光机位置
 */
 private String bjgjwz;

 /**
 * @hibernate.property column="bjgjwz"
 * @return Returns the bjgjwz.
 */
 public String getBjgjwz() {
    return bjgjwz;
 }

 public void setBjgjwz(String bjgjwz) {
    this.bjgjwz = bjgjwz;
  }

 /**
 * 报竣熔接报告日期
 */
 private Date bjrjbgrq;

 /**
 * @hibernate.property column="bjrjbgrq"
 * @return Returns the bjrjbgrq.
 */
 public Date getBjrjbgrq() {
    return bjrjbgrq;
 }

 public void setBjrjbgrq(Date bjrjbgrq) {
    this.bjrjbgrq = bjrjbgrq;
  }

 /**
 * 报竣24芯以下开盒(协议)
 */
 private Long bjkh_24;

 /**
 * @hibernate.property column="bjkh_24"
 * @return Returns the bjkh_24.
 */
 public Long getBjkh_24() {
    return bjkh_24;
 }

 public void setBjkh_24(Long bjkh_24) {
    this.bjkh_24 = bjkh_24;
  }

 /**
 * 报竣28芯至100芯
 */
 private Long bj_100;

 /**
 * @hibernate.property column="bj_100"
 * @return Returns the bj_100.
 */
 public Long getBj_100() {
    return bj_100;
 }

 public void setBj_100(Long bj_100) {
    this.bj_100 = bj_100;
  }

 /**
 * 报竣104芯以上
 */
 private Long bj_104;

 /**
 * @hibernate.property column="bj_104"
 * @return Returns the bj_104.
 */
 public Long getBj_104() {
    return bj_104;
 }

 public void setBj_104(Long bj_104) {
    this.bj_104 = bj_104;
  }

 /**
 * 报竣熔接芯数
 */
 private Long bjrjxs;

 /**
 * @hibernate.property column="bjrjxs"
 * @return Returns the bjrjxs.
 */
 public Long getBjrjxs() {
    return bjrjxs;
 }

 public void setBjrjxs(Long bjrjxs) {
    this.bjrjxs = bjrjxs;
  }

 /**
 * 报竣光机装调台数
 */
 private Long bjgjztts;

 /**
 * @hibernate.property column="bjgjztts"
 * @return Returns the bjgjztts.
 */
 public Long getBjgjztts() {
    return bjgjztts;
 }

 public void setBjgjztts(Long bjgjztts) {
    this.bjgjztts = bjgjztts;
  }

 /**
 * 报竣ODF上架芯数
 */
 private Long odfsjxs;

 /**
 * @hibernate.property column="odfsjxs"
 * @return Returns the odfsjxs.
 */
 public Long getOdfsjxs() {
    return odfsjxs;
 }

 public void setOdfsjxs(Long odfsjxs) {
    this.odfsjxs = odfsjxs;
  }

 /**
 * 报竣熔接拆缆（米）
 */
 private Long bjrjcl;

 /**
 * @hibernate.property column="bjrjcl"
 * @return Returns the bjrjcl.
 */
 public Long getBjrjcl() {
    return bjrjcl;
 }

 public void setBjrjcl(Long bjrjcl) {
    this.bjrjcl = bjrjcl;
  }

 /**
 * 报竣熔接架空（米）
 */
 private Long bjrjjk;

 /**
 * @hibernate.property column="bjrjjk"
 * @return Returns the bjrjjk.
 */
 public Long getBjrjjk() {
    return bjrjjk;
 }

 public void setBjrjjk(Long bjrjjk) {
    this.bjrjjk = bjrjjk;
  }

 /**
 * 资料整合接单日期
 */
 private Date zlzhjdrq;

 /**
 * @hibernate.property column="zlzhjdrq"
 * @return Returns the zlzhjdrq.
 */
 public Date getZlzhjdrq() {
    return zlzhjdrq;
 }

 public void setZlzhjdrq(Date zlzhjdrq) {
    this.zlzhjdrq = zlzhjdrq;
  }

 /**
 * 资料整合接单人[人员]
 */
 private String zlzhjdr;

 /**
 * @hibernate.property column="zlzhjdr"
 * @return Returns the zlzhjdr.
 */
 public String getZlzhjdr() {
    return zlzhjdr;
 }

 public void setZlzhjdr(String zlzhjdr) {
    this.zlzhjdr = zlzhjdr;
  }

 /**
 * 资料整合报验日期
 */
 private Date zlzhbyrq;

 /**
 * @hibernate.property column="zlzhbyrq"
 * @return Returns the zlzhbyrq.
 */
 public Date getZlzhbyrq() {
    return zlzhbyrq;
 }

 public void setZlzhbyrq(Date zlzhbyrq) {
    this.zlzhbyrq = zlzhbyrq;
  }

 /**
 * 验收接单日期
 */
 private Date ysjdrq;

 /**
 * @hibernate.property column="ysjdrq"
 * @return Returns the ysjdrq.
 */
 public Date getYsjdrq() {
    return ysjdrq;
 }

 public void setYsjdrq(Date ysjdrq) {
    this.ysjdrq = ysjdrq;
  }

 /**
 * 验收人[人员]
 */
 private String ysr;

 /**
 * @hibernate.property column="ysr"
 * @return Returns the ysr.
 */
 public String getYsr() {
    return ysr;
 }

 public void setYsr(String ysr) {
    this.ysr = ysr;
  }

 /**
 * 歌华验收人[人员]
 */
 private String ghysr;

 /**
 * @hibernate.property column="ghysr"
 * @return Returns the ghysr.
 */
 public String getGhysr() {
    return ghysr;
 }

 public void setGhysr(String ghysr) {
    this.ghysr = ghysr;
  }

 /**
 * 现场验收日期
 */
 private Date xcysrq;

 /**
 * @hibernate.property column="xcysrq"
 * @return Returns the xcysrq.
 */
 public Date getXcysrq() {
    return xcysrq;
 }

 public void setXcysrq(Date xcysrq) {
    this.xcysrq = xcysrq;
  }

 /**
 * 验收合格证日期
 */
 private Date yshgzrq;

 /**
 * @hibernate.property column="yshgzrq"
 * @return Returns the yshgzrq.
 */
 public Date getYshgzrq() {
    return yshgzrq;
 }

 public void setYshgzrq(Date yshgzrq) {
    this.yshgzrq = yshgzrq;
  }

 /**
 * 验收合格证转出日期
 */
 private Date yshgzzcrq;

 /**
 * @hibernate.property column="yshgzzcrq"
 * @return Returns the yshgzzcrq.
 */
 public Date getYshgzzcrq() {
    return yshgzzcrq;
 }

 public void setYshgzzcrq(Date yshgzzcrq) {
    this.yshgzzcrq = yshgzzcrq;
  }

 /**
 * 辅料出库日期
 */
 private Date plckrq;

 /**
 * @hibernate.property column="plckrq"
 * @return Returns the plckrq.
 */
 public Date getPlckrq() {
    return plckrq;
 }

 public void setPlckrq(Date plckrq) {
    this.plckrq = plckrq;
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
 * 收到网络工程部转来任务通知书、器材表
 */
 private Date rwtzsqcb;

 /**
 * @hibernate.property column="rwtzsqcb"
 * @return Returns the rwtzsqcb.
 */
 public Date getRwtzsqcb() {
    return rwtzsqcb;
 }

 public void setRwtzsqcb(Date rwtzsqcb) {
    this.rwtzsqcb = rwtzsqcb;
  }

 /**
 * 领料部门
 */
 private String llbm;

 /**
 * @hibernate.property column="llbm"
 * @return Returns the llbm.
 */
 public String getLlbm() {
    return llbm;
 }

 public void setLlbm(String llbm) {
    this.llbm = llbm;
  }

 /**
 * 领料时间
 */
 private Date llsj;

 /**
 * @hibernate.property column="llsj"
 * @return Returns the llsj.
 */
 public Date getLlsj() {
    return llsj;
 }

 public void setLlsj(Date llsj) {
    this.llsj = llsj;
  }

 /**
 * 领料人
 */
 private String llr;

 /**
 * @hibernate.property column="llr"
 * @return Returns the llr.
 */
 public String getLlr() {
    return llr;
 }

 public void setLlr(String llr) {
    this.llr = llr;
  }

 /**
 * 发料人
 */
 private String flr;

 /**
 * @hibernate.property column="flr"
 * @return Returns the flr.
 */
 public String getFlr() {
    return flr;
 }

 public void setFlr(String flr) {
    this.flr = flr;
  }

 /**
 * 收到网络工程部转来补料器材表
 */
 private String bqcb;

 /**
 * @hibernate.property column="bqcb"
 * @return Returns the bqcb.
 */
 public String getBqcb() {
    return bqcb;
 }

 public void setBqcb(String bqcb) {
    this.bqcb = bqcb;
  }

 /**
 * 二次领料部门
 */
 private String llbm_2;

 /**
 * @hibernate.property column="llbm_2"
 * @return Returns the llbm_2.
 */
 public String getLlbm_2() {
    return llbm_2;
 }

 public void setLlbm_2(String llbm_2) {
    this.llbm_2 = llbm_2;
  }

 /**
 * 二次设计领料时间
 */
 private Date sjllsj_2;

 /**
 * @hibernate.property column="sjllsj_2"
 * @return Returns the sjllsj_2.
 */
 public Date getSjllsj_2() {
    return sjllsj_2;
 }

 public void setSjllsj_2(Date sjllsj_2) {
    this.sjllsj_2 = sjllsj_2;
  }

 /**
 * 二次领料人
 */
 private String llr_2;

 /**
 * @hibernate.property column="llr_2"
 * @return Returns the llr_2.
 */
 public String getLlr_2() {
    return llr_2;
 }

 public void setLlr_2(String llr_2) {
    this.llr_2 = llr_2;
  }

 /**
 * 二次发料人
 */
 private String flr_2;

 /**
 * @hibernate.property column="flr_2"
 * @return Returns the flr_2.
 */
 public String getFlr_2() {
    return flr_2;
 }

 public void setFlr_2(String flr_2) {
    this.flr_2 = flr_2;
  }

 /**
 * 接收采购申请表时间
 */
 private Date jscgsqbsj;

 /**
 * @hibernate.property column="jscgsqbsj"
 * @return Returns the jscgsqbsj.
 */
 public Date getJscgsqbsj() {
    return jscgsqbsj;
 }

 public void setJscgsqbsj(Date jscgsqbsj) {
    this.jscgsqbsj = jscgsqbsj;
  }

 /**
 * 申请部门
 */
 private String sqbm;

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
 * 公司领导批准时间
 */
 private Date gsldpzsj;

 /**
 * @hibernate.property column="gsldpzsj"
 * @return Returns the gsldpzsj.
 */
 public Date getGsldpzsj() {
    return gsldpzsj;
 }

 public void setGsldpzsj(Date gsldpzsj) {
    this.gsldpzsj = gsldpzsj;
  }

 /**
 * 采购器材到库时间
 */
 private Date cgqdksj;

 /**
 * @hibernate.property column="cgqdksj"
 * @return Returns the cgqdksj.
 */
 public Date getCgqdksj() {
    return cgqdksj;
 }

 public void setCgqdksj(Date cgqdksj) {
    this.cgqdksj = cgqdksj;
  }

 /**
 * 采购器材领料人
 */
 private String cgqllr;

 /**
 * @hibernate.property column="cgqllr"
 * @return Returns the cgqllr.
 */
 public String getCgqllr() {
    return cgqllr;
 }

 public void setCgqllr(String cgqllr) {
    this.cgqllr = cgqllr;
  }

 /**
 * 采购器材发料时间
 */
 private Date cgqflsj;

 /**
 * @hibernate.property column="cgqflsj"
 * @return Returns the cgqflsj.
 */
 public Date getCgqflsj() {
    return cgqflsj;
 }

 public void setCgqflsj(Date cgqflsj) {
    this.cgqflsj = cgqflsj;
  }

 /**
 * 采购器材发料人
 */
 private String cgqflr;

 /**
 * @hibernate.property column="cgqflr"
 * @return Returns the cgqflr.
 */
 public String getCgqflr() {
    return cgqflr;
 }

 public void setCgqflr(String cgqflr) {
    this.cgqflr = cgqflr;
  }

 /**
 * 施工部门完工后第一次核料时间
 */
 private Date wg_hksj1;

 /**
 * @hibernate.property column="wg_hksj1"
 * @return Returns the wg_hksj1.
 */
 public Date getWg_hksj1() {
    return wg_hksj1;
 }

 public void setWg_hksj1(Date wg_hksj1) {
    this.wg_hksj1 = wg_hksj1;
  }

 /**
 * 第一次核料歌华料是否需要补退料[选项][XOR]
 */
 private String btk_btl1;

 /**
 * @hibernate.property column="btk_btl1"
 * @return Returns the btk_btl1.
 */
 public String getBtk_btl1() {
    return btk_btl1;
 }

 public void setBtk_btl1(String btk_btl1) {
    this.btk_btl1 = btk_btl1;
  }

 /**
 * 第一次核料视宽自备料是否需要补退料[选项][XOR]
 */
 private String skzblsfxbtl_hl1;

 /**
 * @hibernate.property column="skzblsfxbtl_hl1"
 * @return Returns the skzblsfxbtl_hl1.
 */
 public String getSkzblsfxbtl_hl1() {
    return skzblsfxbtl_hl1;
 }

 public void setSkzblsfxbtl_hl1(String skzblsfxbtl_hl1) {
    this.skzblsfxbtl_hl1 = skzblsfxbtl_hl1;
  }

 /**
 * 第一次核料核料单位
 */
 private String hkdw_hl1;

 /**
 * @hibernate.property column="hkdw_hl1"
 * @return Returns the hkdw_hl1.
 */
 public String getHkdw_hl1() {
    return hkdw_hl1;
 }

 public void setHkdw_hl1(String hkdw_hl1) {
    this.hkdw_hl1 = hkdw_hl1;
  }

 /**
 * 第一次核料核料人
 */
 private String hkr_hl1;

 /**
 * @hibernate.property column="hkr_hl1"
 * @return Returns the hkr_hl1.
 */
 public String getHkr_hl1() {
    return hkr_hl1;
 }

 public void setHkr_hl1(String hkr_hl1) {
    this.hkr_hl1 = hkr_hl1;
  }

 /**
 * 第一次核料补退料人
 */
 private String btlr_hl1;

 /**
 * @hibernate.property column="btlr_hl1"
 * @return Returns the btlr_hl1.
 */
 public String getBtlr_hl1() {
    return btlr_hl1;
 }

 public void setBtlr_hl1(String btlr_hl1) {
    this.btlr_hl1 = btlr_hl1;
  }

 /**
 * 第一次核料补退料时间
 */
 private Date btlsj_hl1;

 /**
 * @hibernate.property column="btlsj_hl1"
 * @return Returns the btlsj_hl1.
 */
 public Date getBtlsj_hl1() {
    return btlsj_hl1;
 }

 public void setBtlsj_hl1(Date btlsj_hl1) {
    this.btlsj_hl1 = btlsj_hl1;
  }

 /**
 * 收到合同结算部移交验收报告、器材结算单
 */
 private Date ysbgqcjsd;

 /**
 * @hibernate.property column="ysbgqcjsd"
 * @return Returns the ysbgqcjsd.
 */
 public Date getYsbgqcjsd() {
    return ysbgqcjsd;
 }

 public void setYsbgqcjsd(Date ysbgqcjsd) {
    this.ysbgqcjsd = ysbgqcjsd;
  }

 /**
 * 二次歌华调拨料核料
 */
 private Date ghtbl_hl2;

 /**
 * @hibernate.property column="ghtbl_hl2"
 * @return Returns the ghtbl_hl2.
 */
 public Date getGhtbl_hl2() {
    return ghtbl_hl2;
 }

 public void setGhtbl_hl2(Date ghtbl_hl2) {
    this.ghtbl_hl2 = ghtbl_hl2;
  }

 /**
 * 是否二次需要补退料[选项][XOR]
 */
 private String sfxybtl2;

 /**
 * @hibernate.property column="sfxybtl2"
 * @return Returns the sfxybtl2.
 */
 public String getSfxybtl2() {
    return sfxybtl2;
 }

 public void setSfxybtl2(String sfxybtl2) {
    this.sfxybtl2 = sfxybtl2;
  }

 /**
 * 二次补退料时间
 */
 private Date sj_btl2;

 /**
 * @hibernate.property column="sj_btl2"
 * @return Returns the sj_btl2.
 */
 public Date getSj_btl2() {
    return sj_btl2;
 }

 public void setSj_btl2(Date sj_btl2) {
    this.sj_btl2 = sj_btl2;
  }


}
