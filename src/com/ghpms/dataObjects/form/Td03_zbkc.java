package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2014-03-12
 * @hibernate.class table="Td03_zbkc"
 */

public class Td03_zbkc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 390701653993064000L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td03_num"
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
 * 视宽协议编号（备注）
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
 * 勘察单位1
 */
 private String dw1;

 /**
 * @hibernate.property column="dw1"
 * @return Returns the dw1.
 */
 public String getDw1() {
    return dw1;
 }

 public void setDw1(String dw1) {
    this.dw1 = dw1;
  }

 /**
 * 勘察单位2
 */
 private String dw2;

 /**
 * @hibernate.property column="dw2"
 * @return Returns the dw2.
 */
 public String getDw2() {
    return dw2;
 }

 public void setDw2(String dw2) {
    this.dw2 = dw2;
  }

 /**
 * 勘察单位3
 */
 private String dw3;

 /**
 * @hibernate.property column="dw3"
 * @return Returns the dw3.
 */
 public String getDw3() {
    return dw3;
 }

 public void setDw3(String dw3) {
    this.dw3 = dw3;
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
 private Date ghkgsj;

 /**
 * @hibernate.property column="ghkgsj"
 * @return Returns the ghkgsj.
 */
 public Date getGhkgsj() {
    return ghkgsj;
 }

 public void setGhkgsj(Date ghkgsj) {
    this.ghkgsj = ghkgsj;
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
 * 令号申请依据[选项]
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
 * 任务来源[选项]
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
 * 验收报告合同部接收时间
 */
 private Date ysbghtbjssj;

 /**
 * @hibernate.property column="ysbghtbjssj"
 * @return Returns the ysbghtbjssj.
 */
 public Date getYsbghtbjssj() {
    return ysbghtbjssj;
 }

 public void setYsbghtbjssj(Date ysbghtbjssj) {
    this.ysbghtbjssj = ysbghtbjssj;
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
 * 任务书接收时间
 */
 private Date rwsjssj;

 /**
 * @hibernate.property column="rwsjssj"
 * @return Returns the rwsjssj.
 */
 public Date getRwsjssj() {
    return rwsjssj;
 }

 public void setRwsjssj(Date rwsjssj) {
    this.rwsjssj = rwsjssj;
  }

 /**
 * 视宽项目负责人
 */
 private String skxmfzr;

 /**
 * @hibernate.property column="skxmfzr"
 * @return Returns the skxmfzr.
 */
 public String getSkxmfzr() {
    return skxmfzr;
 }

 public void setSkxmfzr(String skxmfzr) {
    this.skxmfzr = skxmfzr;
  }

 /**
 * 是否需要完成设计图[选项][XOR]
 */
 private String sfxywcsjt;

 /**
 * @hibernate.property column="sfxywcsjt"
 * @return Returns the sfxywcsjt.
 */
 public String getSfxywcsjt() {
    return sfxywcsjt;
 }

 public void setSfxywcsjt(String sfxywcsjt) {
    this.sfxywcsjt = sfxywcsjt;
  }

 /**
 * 设计情况说明
 */
 private String sjqksm;

 /**
 * @hibernate.property column="sjqksm"
 * @return Returns the sjqksm.
 */
 public String getSjqksm() {
    return sjqksm;
 }

 public void setSjqksm(String sjqksm) {
    this.sjqksm = sjqksm;
  }

 /**
 * 设计工作量[选项]
 */
 private String sjgzl;

 /**
 * @hibernate.property column="sjgzl"
 * @return Returns the sjgzl.
 */
 public String getSjgzl() {
    return sjgzl;
 }

 public void setSjgzl(String sjgzl) {
    this.sjgzl = sjgzl;
  }

 /**
 * 设计状态[选项]
 */
 private String sjzt;

 /**
 * @hibernate.property column="sjzt"
 * @return Returns the sjzt.
 */
 public String getSjzt() {
    return sjzt;
 }

 public void setSjzt(String sjzt) {
    this.sjzt = sjzt;
  }

 /**
 * 设计提交日期
 */
 private Date sjtjrq;

 /**
 * @hibernate.property column="sjtjrq"
 * @return Returns the sjtjrq.
 */
 public Date getSjtjrq() {
    return sjtjrq;
 }

 public void setSjtjrq(Date sjtjrq) {
    this.sjtjrq = sjtjrq;
  }

 /**
 * 超时统计[选项][XOR]
 */
 private String cstj;

 /**
 * @hibernate.property column="cstj"
 * @return Returns the cstj.
 */
 public String getCstj() {
    return cstj;
 }

 public void setCstj(String cstj) {
    this.cstj = cstj;
  }

 /**
 * 歌华施工协议盖章日期
 */
 private Date ghsgxygzrq;

 /**
 * @hibernate.property column="ghsgxygzrq"
 * @return Returns the ghsgxygzrq.
 */
 public Date getGhsgxygzrq() {
    return ghsgxygzrq;
 }

 public void setGhsgxygzrq(Date ghsgxygzrq) {
    this.ghsgxygzrq = ghsgxygzrq;
  }

 /**
 * 歌华施工协议盖章返回日期
 */
 private Date ghsgxygzfhrq;

 /**
 * @hibernate.property column="ghsgxygzfhrq"
 * @return Returns the ghsgxygzfhrq.
 */
 public Date getGhsgxygzfhrq() {
    return ghsgxygzfhrq;
 }

 public void setGhsgxygzfhrq(Date ghsgxygzfhrq) {
    this.ghsgxygzfhrq = ghsgxygzfhrq;
  }

 /**
 * 验收报告返回合同部日期
 */
 private Date ysbgfhhtbrq;

 /**
 * @hibernate.property column="ysbgfhhtbrq"
 * @return Returns the ysbgfhhtbrq.
 */
 public Date getYsbgfhhtbrq() {
    return ysbgfhhtbrq;
 }

 public void setYsbgfhhtbrq(Date ysbgfhhtbrq) {
    this.ysbgfhhtbrq = ysbgfhhtbrq;
  }

 /**
 * 调试协议盖章日期
 */
 private Date tsxygzrq;

 /**
 * @hibernate.property column="tsxygzrq"
 * @return Returns the tsxygzrq.
 */
 public Date getTsxygzrq() {
    return tsxygzrq;
 }

 public void setTsxygzrq(Date tsxygzrq) {
    this.tsxygzrq = tsxygzrq;
  }

 /**
 * 调试协议盖章返回日期
 */
 private Date tsxygzfhrq;

 /**
 * @hibernate.property column="tsxygzfhrq"
 * @return Returns the tsxygzfhrq.
 */
 public Date getTsxygzfhrq() {
    return tsxygzfhrq;
 }

 public void setTsxygzfhrq(Date tsxygzfhrq) {
    this.tsxygzfhrq = tsxygzfhrq;
  }

 /**
 * 歌华施工协议金额
 */
 private Double ghsgxyje;

 /**
 * @hibernate.property column="ghsgxyje"
 * @return Returns the ghsgxyje.
 */
 public Double getGhsgxyje() {
    return ghsgxyje;
 }

 public void setGhsgxyje(Double ghsgxyje) {
    this.ghsgxyje = ghsgxyje;
  }

 /**
 * 歌华调试协议金额
 */
 private Double ghtsxyje;

 /**
 * @hibernate.property column="ghtsxyje"
 * @return Returns the ghtsxyje.
 */
 public Double getGhtsxyje() {
    return ghtsxyje;
 }

 public void setGhtsxyje(Double ghtsxyje) {
    this.ghtsxyje = ghtsxyje;
  }

 /**
 * 结算状态说明
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
 * 施工、调试对上结算金额
 */
 private Double sgtsdsjsje;

 /**
 * @hibernate.property column="sgtsdsjsje"
 * @return Returns the sgtsdsjsje.
 */
 public Double getSgtsdsjsje() {
    return sgtsdsjsje;
 }

 public void setSgtsdsjsje(Double sgtsdsjsje) {
    this.sgtsdsjsje = sgtsdsjsje;
  }

 /**
 * 设计对上结算金额
 */
 private Double sjdsjsje;

 /**
 * @hibernate.property column="sjdsjsje"
 * @return Returns the sjdsjsje.
 */
 public Double getSjdsjsje() {
    return sjdsjsje;
 }

 public void setSjdsjsje(Double sjdsjsje) {
    this.sjdsjsje = sjdsjsje;
  }

 /**
 * 对上结算批次
 */
 private String dsjspc;

 /**
 * @hibernate.property column="dsjspc"
 * @return Returns the dsjspc.
 */
 public String getDsjspc() {
    return dsjspc;
 }

 public void setDsjspc(String dsjspc) {
    this.dsjspc = dsjspc;
  }

 /**
 * 开发票日期
 */
 private Date kfprq;

 /**
 * @hibernate.property column="kfprq"
 * @return Returns the kfprq.
 */
 public Date getKfprq() {
    return kfprq;
 }

 public void setKfprq(Date kfprq) {
    this.kfprq = kfprq;
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
 * 器材结算日期
 */
 private Date qcjsrq;

 /**
 * @hibernate.property column="qcjsrq"
 * @return Returns the qcjsrq.
 */
 public Date getQcjsrq() {
    return qcjsrq;
 }

 public void setQcjsrq(Date qcjsrq) {
    this.qcjsrq = qcjsrq;
  }

 /**
 * 计财结算申请日期
 */
 private Date jcjssqrq;

 /**
 * @hibernate.property column="jcjssqrq"
 * @return Returns the jcjssqrq.
 */
 public Date getJcjssqrq() {
    return jcjssqrq;
 }

 public void setJcjssqrq(Date jcjssqrq) {
    this.jcjssqrq = jcjssqrq;
  }

 /**
 * 计财结算日期
 */
 private Date jcjsrq;

 /**
 * @hibernate.property column="jcjsrq"
 * @return Returns the jcjsrq.
 */
 public Date getJcjsrq() {
    return jcjsrq;
 }

 public void setJcjsrq(Date jcjsrq) {
    this.jcjsrq = jcjsrq;
  }

 /**
 * 预付金额
 */
 private Double yfje;

 /**
 * @hibernate.property column="yfje"
 * @return Returns the yfje.
 */
 public Double getYfje() {
    return yfje;
 }

 public void setYfje(Double yfje) {
    this.yfje = yfje;
  }

 /**
 * 预付日期
 */
 private Date yfrq;

 /**
 * @hibernate.property column="yfrq"
 * @return Returns the yfrq.
 */
 public Date getYfrq() {
    return yfrq;
 }

 public void setYfrq(Date yfrq) {
    this.yfrq = yfrq;
  }

 /**
 * 回款日期
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
 private Double dxyfkje;

 /**
 * @hibernate.property column="dxyfkje"
 * @return Returns the dxyfkje.
 */
 public Double getDxyfkje() {
    return dxyfkje;
 }

 public void setDxyfkje(Double dxyfkje) {
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


}
