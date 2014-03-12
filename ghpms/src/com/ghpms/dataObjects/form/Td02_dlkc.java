package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2014-03-12
 * @hibernate.class table="Td02_dlkc"
 */

public class Td02_dlkc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 142105530339022400L;

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
 * 光节点覆盖户数
 */
 private Long gjdfghs;

 /**
 * @hibernate.property column="gjdfghs"
 * @return Returns the gjdfghs.
 */
 public Long getGjdfghs() {
    return gjdfghs;
 }

 public void setGjdfghs(Long gjdfghs) {
    this.gjdfghs = gjdfghs;
  }

 /**
 * 设计备注
 */
 private Long sjbz;

 /**
 * @hibernate.property column="sjbz"
 * @return Returns the sjbz.
 */
 public Long getSjbz() {
    return sjbz;
 }

 public void setSjbz(Long sjbz) {
    this.sjbz = sjbz;
  }

 /**
 * 实际改造户数
 */
 private Long sjgzhs;

 /**
 * @hibernate.property column="sjgzhs"
 * @return Returns the sjgzhs.
 */
 public Long getSjgzhs() {
    return sjgzhs;
 }

 public void setSjgzhs(Long sjgzhs) {
    this.sjgzhs = sjgzhs;
  }

 /**
 * 不改造户数
 */
 private Long bgzhs;

 /**
 * @hibernate.property column="bgzhs"
 * @return Returns the bgzhs.
 */
 public Long getBgzhs() {
    return bgzhs;
 }

 public void setBgzhs(Long bgzhs) {
    this.bgzhs = bgzhs;
  }

 /**
 * 改造方式[选项]
 */
 private String gzfs;

 /**
 * @hibernate.property column="gzfs"
 * @return Returns the gzfs.
 */
 public String getGzfs() {
    return gzfs;
 }

 public void setGzfs(String gzfs) {
    this.gzfs = gzfs;
  }

 /**
 * 光缆是否到位[选项][XOR]
 */
 private String glsfdw;

 /**
 * @hibernate.property column="glsfdw"
 * @return Returns the glsfdw.
 */
 public String getGlsfdw() {
    return glsfdw;
 }

 public void setGlsfdw(String glsfdw) {
    this.glsfdw = glsfdw;
  }

 /**
 * 结算户数
 */
 private Long jshs;

 /**
 * @hibernate.property column="jshs"
 * @return Returns the jshs.
 */
 public Long getJshs() {
    return jshs;
 }

 public void setJshs(Long jshs) {
    this.jshs = jshs;
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
 * 付款单位/部门
 */
 private String fkdw;

 /**
 * @hibernate.property column="fkdw"
 * @return Returns the fkdw.
 */
 public String getFkdw() {
    return fkdw;
 }

 public void setFkdw(String fkdw) {
    this.fkdw = fkdw;
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
 * 结算单报财务审核日期
 */
 private Date jsdbcwshrq;

 /**
 * @hibernate.property column="jsdbcwshrq"
 * @return Returns the jsdbcwshrq.
 */
 public Date getJsdbcwshrq() {
    return jsdbcwshrq;
 }

 public void setJsdbcwshrq(Date jsdbcwshrq) {
    this.jsdbcwshrq = jsdbcwshrq;
  }

 /**
 * 付款审批单签字日期
 */
 private Date fkspdqzrq;

 /**
 * @hibernate.property column="fkspdqzrq"
 * @return Returns the fkspdqzrq.
 */
 public Date getFkspdqzrq() {
    return fkspdqzrq;
 }

 public void setFkspdqzrq(Date fkspdqzrq) {
    this.fkspdqzrq = fkspdqzrq;
  }

 /**
 * 对下付款批次
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
 * 对下付款金额
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
 * 视宽内部结算价格
 */
 private Double sknbjsjg;

 /**
 * @hibernate.property column="sknbjsjg"
 * @return Returns the sknbjsjg.
 */
 public Double getSknbjsjg() {
    return sknbjsjg;
 }

 public void setSknbjsjg(Double sknbjsjg) {
    this.sknbjsjg = sknbjsjg;
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
 * 歌华任务书编号
 */
 private String ghrwsbh;

 /**
 * @hibernate.property column="ghrwsbh"
 * @return Returns the ghrwsbh.
 */
 public String getGhrwsbh() {
    return ghrwsbh;
 }

 public void setGhrwsbh(String ghrwsbh) {
    this.ghrwsbh = ghrwsbh;
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
 * 提交设计成果时间
 */
 private Date tjsjcgsj;

 /**
 * @hibernate.property column="tjsjcgsj"
 * @return Returns the tjsjcgsj.
 */
 public Date getTjsjcgsj() {
    return tjsjcgsj;
 }

 public void setTjsjcgsj(Date tjsjcgsj) {
    this.tjsjcgsj = tjsjcgsj;
  }

 /**
 * 接收工程年份[选项]
 */
 private Long jsgcnf;

 /**
 * @hibernate.property column="jsgcnf"
 * @return Returns the jsgcnf.
 */
 public Long getJsgcnf() {
    return jsgcnf;
 }

 public void setJsgcnf(Long jsgcnf) {
    this.jsgcnf = jsgcnf;
  }

 /**
 * 网技部接收日期
 */
 private Date wlbjsrq;

 /**
 * @hibernate.property column="wlbjsrq"
 * @return Returns the wlbjsrq.
 */
 public Date getWlbjsrq() {
    return wlbjsrq;
 }

 public void setWlbjsrq(Date wlbjsrq) {
    this.wlbjsrq = wlbjsrq;
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
 * 设计方式[选项]
 */
 private String sjfs;

 /**
 * @hibernate.property column="sjfs"
 * @return Returns the sjfs.
 */
 public String getSjfs() {
    return sjfs;
 }

 public void setSjfs(String sjfs) {
    this.sjfs = sjfs;
  }

 /**
 * 勘察人员[人员]
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
 * 设计人员[选项]
 */
 private String sjry;

 /**
 * @hibernate.property column="sjry"
 * @return Returns the sjry.
 */
 public String getSjry() {
    return sjry;
 }

 public void setSjry(String sjry) {
    this.sjry = sjry;
  }

 /**
 * 勘察完成日期
 */
 private Date kcwcrq;

 /**
 * @hibernate.property column="kcwcrq"
 * @return Returns the kcwcrq.
 */
 public Date getKcwcrq() {
    return kcwcrq;
 }

 public void setKcwcrq(Date kcwcrq) {
    this.kcwcrq = kcwcrq;
  }

 /**
 * 设计完成日期
 */
 private Date sjwcrq;

 /**
 * @hibernate.property column="sjwcrq"
 * @return Returns the sjwcrq.
 */
 public Date getSjwcrq() {
    return sjwcrq;
 }

 public void setSjwcrq(Date sjwcrq) {
    this.sjwcrq = sjwcrq;
  }

 /**
 * 图纸上报日期
 */
 private Date tzsbrq;

 /**
 * @hibernate.property column="tzsbrq"
 * @return Returns the tzsbrq.
 */
 public Date getTzsbrq() {
    return tzsbrq;
 }

 public void setTzsbrq(Date tzsbrq) {
    this.tzsbrq = tzsbrq;
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


}
