package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2014-03-12
 * @hibernate.class table="Td21_qtgc"
 */

public class Td21_qtgc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 123318788079458064L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td21_num"
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
 * 施工单位1
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
 * 施工单位2
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
 * 施工单位3
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
 * 工程类型
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
 * 项目状态
 */
 private String xmzt;

 /**
 * @hibernate.property column="xmzt"
 * @return Returns the xmzt.
 */
 public String getXmzt() {
    return xmzt;
 }

 public void setXmzt(String xmzt) {
    this.xmzt = xmzt;
  }

 /**
 * 对上合同返回日期
 */
 private Date dshtfhrq;

 /**
 * @hibernate.property column="dshtfhrq"
 * @return Returns the dshtfhrq.
 */
 public Date getDshtfhrq() {
    return dshtfhrq;
 }

 public void setDshtfhrq(Date dshtfhrq) {
    this.dshtfhrq = dshtfhrq;
  }

 /**
 * 协议金额
 */
 private Double xyje;

 /**
 * @hibernate.property column="xyje"
 * @return Returns the xyje.
 */
 public Double getXyje() {
    return xyje;
 }

 public void setXyje(Double xyje) {
    this.xyje = xyje;
  }

 /**
 * 预收款金额
 */
 private Double yskje;

 /**
 * @hibernate.property column="yskje"
 * @return Returns the yskje.
 */
 public Double getYskje() {
    return yskje;
 }

 public void setYskje(Double yskje) {
    this.yskje = yskje;
  }

 /**
 * 结算金额
 */
 private Double jsje;

 /**
 * @hibernate.property column="jsje"
 * @return Returns the jsje.
 */
 public Double getJsje() {
    return jsje;
 }

 public void setJsje(Double jsje) {
    this.jsje = jsje;
  }

 /**
 * 付款单位
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
 * 对下合同签订日期
 */
 private Double dxhtqdrq;

 /**
 * @hibernate.property column="dxhtqdrq"
 * @return Returns the dxhtqdrq.
 */
 public Double getDxhtqdrq() {
    return dxhtqdrq;
 }

 public void setDxhtqdrq(Double dxhtqdrq) {
    this.dxhtqdrq = dxhtqdrq;
  }

 /**
 * 对下预付金额
 */
 private Double dxyfje;

 /**
 * @hibernate.property column="dxyfje"
 * @return Returns the dxyfje.
 */
 public Double getDxyfje() {
    return dxyfje;
 }

 public void setDxyfje(Double dxyfje) {
    this.dxyfje = dxyfje;
  }

 /**
 * 对下结算金额
 */
 private Double dxjeje;

 /**
 * @hibernate.property column="dxjeje"
 * @return Returns the dxjeje.
 */
 public Double getDxjeje() {
    return dxjeje;
 }

 public void setDxjeje(Double dxjeje) {
    this.dxjeje = dxjeje;
  }

 /**
 * 预估毛利率
 */
 private Double ygmll;

 /**
 * @hibernate.property column="ygmll"
 * @return Returns the ygmll.
 */
 public Double getYgmll() {
    return ygmll;
 }

 public void setYgmll(Double ygmll) {
    this.ygmll = ygmll;
  }

 /**
 * 工程实施状态
 */
 private String gcsszt;

 /**
 * @hibernate.property column="gcsszt"
 * @return Returns the gcsszt.
 */
 public String getGcsszt() {
    return gcsszt;
 }

 public void setGcsszt(String gcsszt) {
    this.gcsszt = gcsszt;
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
 * 对下合同金额
 */
 private Double dxhtje;

 /**
 * @hibernate.property column="dxhtje"
 * @return Returns the dxhtje.
 */
 public Double getDxhtje() {
    return dxhtje;
 }

 public void setDxhtje(Double dxhtje) {
    this.dxhtje = dxhtje;
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


}
