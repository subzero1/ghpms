package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2014-02-14
 * @hibernate.class table="Td01_glkc"
 */

public class Td01_glkc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 899459589116097408L;

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
 private Double skxyje;

 /**
 * @hibernate.property column="skxyje"
 * @return Returns the skxyje.
 */
 public Double getSkxyje() {
    return skxyje;
 }

 public void setSkxyje(Double skxyje) {
    this.skxyje = skxyje;
  }

 /**
 * 录入GIS日期

 */
 private Date lrgisrq;

 /**
 * @hibernate.property column="lrgisrq"
 * @return Returns the lrgisrq.
 */
 public Date getLrgisrq() {
    return lrgisrq;
 }

 public void setLrgisrq(Date lrgisrq) {
    this.lrgisrq = lrgisrq;
  }

 /**
 * 首次勘察日期

 */
 private Date sckcrq;

 /**
 * @hibernate.property column="sckcrq"
 * @return Returns the sckcrq.
 */
 public Date getSckcrq() {
    return sckcrq;
 }

 public void setSckcrq(Date sckcrq) {
    this.sckcrq = sckcrq;
  }

 /**
 * 勘察次数

 */
 private Long kccs;

 /**
 * @hibernate.property column="kccs"
 * @return Returns the kccs.
 */
 public Long getKccs() {
    return kccs;
 }

 public void setKccs(Long kccs) {
    this.kccs = kccs;
  }

 /**
 * 勘察日期

 */
 private Date kcrq;

 /**
 * @hibernate.property column="kcrq"
 * @return Returns the kcrq.
 */
 public Date getKcrq() {
    return kcrq;
 }

 public void setKcrq(Date kcrq) {
    this.kcrq = kcrq;
  }

 /**
 * 歌华施工任务编号

 */
 private String ghsgrwbh;

 /**
 * @hibernate.property column="ghsgrwbh"
 * @return Returns the ghsgrwbh.
 */
 public String getGhsgrwbh() {
    return ghsgrwbh;
 }

 public void setGhsgrwbh(String ghsgrwbh) {
    this.ghsgrwbh = ghsgrwbh;
  }

 /**
 * 缆长和

 */
 private Double lch;

 /**
 * @hibernate.property column="lch"
 * @return Returns the lch.
 */
 public Double getLch() {
    return lch;
 }

 public void setLch(Double lch) {
    this.lch = lch;
  }

 /**
 * 熔接量

 */
 private Double rjl;

 /**
 * @hibernate.property column="rjl"
 * @return Returns the rjl.
 */
 public Double getRjl() {
    return rjl;
 }

 public void setRjl(Double rjl) {
    this.rjl = rjl;
  }

 /**
 * 歌华协议接受日期
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
 * 勘察类型[选项]
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
 * 光节点地址
 */
 private String gjddz;

 /**
 * @hibernate.property column="gjddz"
 * @return Returns the gjddz.
 */
 public String getGjddz() {
    return gjddz;
 }

 public void setGjddz(String gjddz) {
    this.gjddz = gjddz;
  }

 /**
 * 歌华任务书接收时间
 */
 private Date ghrwsjssj;

 /**
 * @hibernate.property column="ghrwsjssj"
 * @return Returns the ghrwsjssj.
 */
 public Date getGhrwsjssj() {
    return ghrwsjssj;
 }

 public void setGhrwsjssj(Date ghrwsjssj) {
    this.ghrwsjssj = ghrwsjssj;
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
 * 歌华项目负责人
 */
 private String ghxmfzr;

 /**
 * @hibernate.property column="ghxmfzr"
 * @return Returns the ghxmfzr.
 */
 public String getGhxmfzr() {
    return ghxmfzr;
 }

 public void setGhxmfzr(String ghxmfzr) {
    this.ghxmfzr = ghxmfzr;
  }

 /**
 * 市场部负责人[人员]
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
 * 视宽协议编号(备注)
 */
 private String skbh_bz;

 /**
 * @hibernate.property column="skbh_bz"
 * @return Returns the skbh_bz.
 */
 public String getSkbh_bz() {
    return skbh_bz;
 }

 public void setSkbh_bz(String skbh_bz) {
    this.skbh_bz = skbh_bz;
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
 * 任务完成情况[选项]
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
 * 勘察任务书接收时间
 */
 private Date kcrwsjssj;

 /**
 * @hibernate.property column="kcrwsjssj"
 * @return Returns the kcrwsjssj.
 */
 public Date getKcrwsjssj() {
    return kcrwsjssj;
 }

 public void setKcrwsjssj(Date kcrwsjssj) {
    this.kcrwsjssj = kcrwsjssj;
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
 * 视宽审核退回
 */
 private Long skshth;

 /**
 * @hibernate.property column="skshth"
 * @return Returns the skshth.
 */
 public Long getSkshth() {
    return skshth;
 }

 public void setSkshth(Long skshth) {
    this.skshth = skshth;
  }

 /**
 * 报图时间
 */
 private Date btsj;

 /**
 * @hibernate.property column="btsj"
 * @return Returns the btsj.
 */
 public Date getBtsj() {
    return btsj;
 }

 public void setBtsj(Date btsj) {
    this.btsj = btsj;
  }

 /**
 * 歌华审核退回
 */
 private Long ghshth;

 /**
 * @hibernate.property column="ghshth"
 * @return Returns the ghshth.
 */
 public Long getGhshth() {
    return ghshth;
 }

 public void setGhshth(Long ghshth) {
    this.ghshth = ghshth;
  }

 /**
 * 现场勘察单位[选项]
 */
 private String xckcdw;

 /**
 * @hibernate.property column="xckcdw"
 * @return Returns the xckcdw.
 */
 public String getXckcdw() {
    return xckcdw;
 }

 public void setXckcdw(String xckcdw) {
    this.xckcdw = xckcdw;
  }

 /**
 * 现场勘察人
 */
 private String sxkcr;

 /**
 * @hibernate.property column="sxkcr"
 * @return Returns the sxkcr.
 */
 public String getSxkcr() {
    return sxkcr;
 }

 public void setSxkcr(String sxkcr) {
    this.sxkcr = sxkcr;
  }

 /**
 * 光节点名称
 */
 private String gjdmc;

 /**
 * @hibernate.property column="gjdmc"
 * @return Returns the gjdmc.
 */
 public String getGjdmc() {
    return gjdmc;
 }

 public void setGjdmc(String gjdmc) {
    this.gjdmc = gjdmc;
  }

 /**
 * 架空（缆长）
 */
 private Double jk_lc;

 /**
 * @hibernate.property column="jk_lc"
 * @return Returns the jk_lc.
 */
 public Double getJk_lc() {
    return jk_lc;
 }

 public void setJk_lc(Double jk_lc) {
    this.jk_lc = jk_lc;
  }

 /**
 * 架空（路由）
 */
 private Double jk_ly;

 /**
 * @hibernate.property column="jk_ly"
 * @return Returns the jk_ly.
 */
 public Double getJk_ly() {
    return jk_ly;
 }

 public void setJk_ly(Double jk_ly) {
    this.jk_ly = jk_ly;
  }

 /**
 * 管道（缆长）
 */
 private Double gd_lc;

 /**
 * @hibernate.property column="gd_lc"
 * @return Returns the gd_lc.
 */
 public Double getGd_lc() {
    return gd_lc;
 }

 public void setGd_lc(Double gd_lc) {
    this.gd_lc = gd_lc;
  }

 /**
 * 管道（路由）
 */
 private Double gd_ly;

 /**
 * @hibernate.property column="gd_ly"
 * @return Returns the gd_ly.
 */
 public Double getGd_ly() {
    return gd_ly;
 }

 public void setGd_ly(Double gd_ly) {
    this.gd_ly = gd_ly;
  }

 /**
 * 拆除（缆长）
 */
 private Double cc_lc;

 /**
 * @hibernate.property column="cc_lc"
 * @return Returns the cc_lc.
 */
 public Double getCc_lc() {
    return cc_lc;
 }

 public void setCc_lc(Double cc_lc) {
    this.cc_lc = cc_lc;
  }

 /**
 * 拆除（路由）
 */
 private Double cc_ly;

 /**
 * @hibernate.property column="cc_ly"
 * @return Returns the cc_ly.
 */
 public Double getCc_ly() {
    return cc_ly;
 }

 public void setCc_ly(Double cc_ly) {
    this.cc_ly = cc_ly;
  }

 /**
 * 杆公里
 */
 private Double ggl;

 /**
 * @hibernate.property column="ggl"
 * @return Returns the ggl.
 */
 public Double getGgl() {
    return ggl;
 }

 public void setGgl(Double ggl) {
    this.ggl = ggl;
  }

 /**
 * 沟公里
 */
 private Double gougl;

 /**
 * @hibernate.property column="gougl"
 * @return Returns the gougl.
 */
 public Double getGougl() {
    return gougl;
 }

 public void setGougl(Double gougl) {
    this.gougl = gougl;
  }

 /**
 * 道路长
 */
 private Double dlc;

 /**
 * @hibernate.property column="dlc"
 * @return Returns the dlc.
 */
 public Double getDlc() {
    return dlc;
 }

 public void setDlc(Double dlc) {
    this.dlc = dlc;
  }

 /**
 * 管道需求长
 */
 private Double gdxqc;

 /**
 * @hibernate.property column="gdxqc"
 * @return Returns the gdxqc.
 */
 public Double getGdxqc() {
    return gdxqc;
 }

 public void setGdxqc(Double gdxqc) {
    this.gdxqc = gdxqc;
  }

 /**
 * 路由和
 */
 private Double lyh;

 /**
 * @hibernate.property column="lyh"
 * @return Returns the lyh.
 */
 public Double getLyh() {
    return lyh;
 }

 public void setLyh(Double lyh) {
    this.lyh = lyh;
  }

 /**
 * 二次勘查[选项][XOR]
 */
 private String eckc;

 /**
 * @hibernate.property column="eckc"
 * @return Returns the eckc.
 */
 public String getEckc() {
    return eckc;
 }

 public void setEckc(String eckc) {
    this.eckc = eckc;
  }

 /**
 * 二次勘查杆公里
 */
 private Double eckcggl;

 /**
 * @hibernate.property column="eckcggl"
 * @return Returns the eckcggl.
 */
 public Double getEckcggl() {
    return eckcggl;
 }

 public void setEckcggl(Double eckcggl) {
    this.eckcggl = eckcggl;
  }

 /**
 * 二次勘查沟公里
 */
 private Double eckcgougl;

 /**
 * @hibernate.property column="eckcgougl"
 * @return Returns the eckcgougl.
 */
 public Double getEckcgougl() {
    return eckcgougl;
 }

 public void setEckcgougl(Double eckcgougl) {
    this.eckcgougl = eckcgougl;
  }

 /**
 * 勘查设计费估算
 */
 private Double kcsjfgs;

 /**
 * @hibernate.property column="kcsjfgs"
 * @return Returns the kcsjfgs.
 */
 public Double getKcsjfgs() {
    return kcsjfgs;
 }

 public void setKcsjfgs(Double kcsjfgs) {
    this.kcsjfgs = kcsjfgs;
  }

 /**
 * 电子版
 */
 private Long dzb;

 /**
 * @hibernate.property column="dzb"
 * @return Returns the dzb.
 */
 public Long getDzb() {
    return dzb;
 }

 public void setDzb(Long dzb) {
    this.dzb = dzb;
  }

 /**
 * 歌华工程任务编号
 */
 private String ghgcrwbh;

 /**
 * @hibernate.property column="ghgcrwbh"
 * @return Returns the ghgcrwbh.
 */
 public String getGhgcrwbh() {
    return ghgcrwbh;
 }

 public void setGhgcrwbh(String ghgcrwbh) {
    this.ghgcrwbh = ghgcrwbh;
  }

 /**
 * 情况说明类型[选项]
 */
 private String qksmlx;

 /**
 * @hibernate.property column="qksmlx"
 * @return Returns the qksmlx.
 */
 public String getQksmlx() {
    return qksmlx;
 }

 public void setQksmlx(String qksmlx) {
    this.qksmlx = qksmlx;
  }

 /**
 * 是否现场勘查[选项][XOR]
 */
 private String sfxckc;

 /**
 * @hibernate.property column="sfxckc"
 * @return Returns the sfxckc.
 */
 public String getSfxckc() {
    return sfxckc;
 }

 public void setSfxckc(String sfxckc) {
    this.sfxckc = sfxckc;
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
 * 情况说明上报时间
 */
 private Date qksmsbsj;

 /**
 * @hibernate.property column="qksmsbsj"
 * @return Returns the qksmsbsj.
 */
 public Date getQksmsbsj() {
    return qksmsbsj;
 }

 public void setQksmsbsj(Date qksmsbsj) {
    this.qksmsbsj = qksmsbsj;
  }

 /**
 * 超时无情况说明[选项][XOR]
 */
 private String cswqksm;

 /**
 * @hibernate.property column="cswqksm"
 * @return Returns the cswqksm.
 */
 public String getCswqksm() {
    return cswqksm;
 }

 public void setCswqksm(String cswqksm) {
    this.cswqksm = cswqksm;
  }

 /**
 * 其它特殊说明
 */
 private String qttssm;

 /**
 * @hibernate.property column="qttssm"
 * @return Returns the qttssm.
 */
 public String getQttssm() {
    return qttssm;
 }

 public void setQttssm(String qttssm) {
    this.qttssm = qttssm;
  }

 /**
 * 结算长度
 */
 private Double jscd;

 /**
 * @hibernate.property column="jscd"
 * @return Returns the jscd.
 */
 public Double getJscd() {
    return jscd;
 }

 public void setJscd(Double jscd) {
    this.jscd = jscd;
  }

 /**
 * 对上结算金额
 */
 private Double dsjsje;

 /**
 * @hibernate.property column="dsjsje"
 * @return Returns the dsjsje.
 */
 public Double getDsjsje() {
    return dsjsje;
 }

 public void setDsjsje(Double dsjsje) {
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
 private Date dskfprq;

 /**
 * @hibernate.property column="dskfprq"
 * @return Returns the dskfprq.
 */
 public Date getDskfprq() {
    return dskfprq;
 }

 public void setDskfprq(Date dskfprq) {
    this.dskfprq = dskfprq;
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
 private Date sbqcjssj;

 /**
 * @hibernate.property column="sbqcjssj"
 * @return Returns the sbqcjssj.
 */
 public Date getSbqcjssj() {
    return sbqcjssj;
 }

 public void setSbqcjssj(Date sbqcjssj) {
    this.sbqcjssj = sbqcjssj;
  }

 /**
 * 对上器材结算日期
 */
 private Date dsqcjssj;

 /**
 * @hibernate.property column="dsqcjssj"
 * @return Returns the dsqcjssj.
 */
 public Date getDsqcjssj() {
    return dsqcjssj;
 }

 public void setDsqcjssj(Date dsqcjssj) {
    this.dsqcjssj = dsqcjssj;
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
 private Double dxjsje;

 /**
 * @hibernate.property column="dxjsje"
 * @return Returns the dxjsje.
 */
 public Double getDxjsje() {
    return dxjsje;
 }

 public void setDxjsje(Double dxjsje) {
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
 private Date dxfkpzrq;

 /**
 * @hibernate.property column="dxfkpzrq"
 * @return Returns the dxfkpzrq.
 */
 public Date getDxfkpzrq() {
    return dxfkpzrq;
 }

 public void setDxfkpzrq(Date dxfkpzrq) {
    this.dxfkpzrq = dxfkpzrq;
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
 private Double dxfkje;

 /**
 * @hibernate.property column="dxfkje"
 * @return Returns the dxfkje.
 */
 public Double getDxfkje() {
    return dxfkje;
 }

 public void setDxfkje(Double dxfkje) {
    this.dxfkje = dxfkje;
  }

 /**
 * 对下预付款批次
 */
 private String dxfkpc;

 /**
 * @hibernate.property column="dxfkpc"
 * @return Returns the dxfkpc.
 */
 public String getDxfkpc() {
    return dxfkpc;
 }

 public void setDxfkpc(String dxfkpc) {
    this.dxfkpc = dxfkpc;
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
 private Double gsnbjsj;

 /**
 * @hibernate.property column="gsnbjsj"
 * @return Returns the gsnbjsj.
 */
 public Double getGsnbjsj() {
    return gsnbjsj;
 }

 public void setGsnbjsj(Double gsnbjsj) {
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


}
