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
	private static final long serialVersionUID = 643292155476785024L;

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
 * 工程类型[选项]
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
 * 视宽项目负责人[人员]
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
 * 勘察设计部门或单位[选项]
 */
 private String kcsjbmhdw;

 /**
 * @hibernate.property column="kcsjbmhdw"
 * @return Returns the kcsjbmhdw.
 */
 public String getKcsjbmhdw() {
    return kcsjbmhdw;
 }

 public void setKcsjbmhdw(String kcsjbmhdw) {
    this.kcsjbmhdw = kcsjbmhdw;
  }

 /**
 * 勘察设计部门或单位负责人[人员]
 */
 private String dcsjbmhdwfzr;

 /**
 * @hibernate.property column="dcsjbmhdwfzr"
 * @return Returns the dcsjbmhdwfzr.
 */
 public String getDcsjbmhdwfzr() {
    return dcsjbmhdwfzr;
 }

 public void setDcsjbmhdwfzr(String dcsjbmhdwfzr) {
    this.dcsjbmhdwfzr = dcsjbmhdwfzr;
  }

 /**
 * 沟公里
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
 * 大孔数
 */
 private Long dks;

 /**
 * @hibernate.property column="dks"
 * @return Returns the dks.
 */
 public Long getDks() {
    return dks;
 }

 public void setDks(Long dks) {
    this.dks = dks;
  }

 /**
 * 孔公里
 */
 private Long kgl;

 /**
 * @hibernate.property column="kgl"
 * @return Returns the kgl.
 */
 public Long getKgl() {
    return kgl;
 }

 public void setKgl(Long kgl) {
    this.kgl = kgl;
  }

 /**
 * 子孔公里
 */
 private Long zkgl;

 /**
 * @hibernate.property column="zkgl"
 * @return Returns the zkgl.
 */
 public Long getZkgl() {
    return zkgl;
 }

 public void setZkgl(Long zkgl) {
    this.zkgl = zkgl;
  }

 /**
 * 管材[选项]
 */
 private String gc;

 /**
 * @hibernate.property column="gc"
 * @return Returns the gc.
 */
 public String getGc() {
    return gc;
 }

 public void setGc(String gc) {
    this.gc = gc;
  }

 /**
 * 合同金额（元）
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
 * 完工日期
 */
 private Date wgrq;

 /**
 * @hibernate.property column="wgrq"
 * @return Returns the wgrq.
 */
 public Date getWgrq() {
    return wgrq;
 }

 public void setWgrq(Date wgrq) {
    this.wgrq = wgrq;
  }

 /**
 * 报竣日期
 */
 private Date bjrq;

 /**
 * @hibernate.property column="bjrq"
 * @return Returns the bjrq.
 */
 public Date getBjrq() {
    return bjrq;
 }

 public void setBjrq(Date bjrq) {
    this.bjrq = bjrq;
  }

 /**
 * 合同盖章日期
 */
 private Date htgzrq;

 /**
 * @hibernate.property column="htgzrq"
 * @return Returns the htgzrq.
 */
 public Date getHtgzrq() {
    return htgzrq;
 }

 public void setHtgzrq(Date htgzrq) {
    this.htgzrq = htgzrq;
  }

 /**
 * 合同盖章返回日期
 */
 private Date htgzfhrq;

 /**
 * @hibernate.property column="htgzfhrq"
 * @return Returns the htgzfhrq.
 */
 public Date getHtgzfhrq() {
    return htgzfhrq;
 }

 public void setHtgzfhrq(Date htgzfhrq) {
    this.htgzfhrq = htgzfhrq;
  }

 /**
 * 孔公里单价
 */
 private Long kgldj;

 /**
 * @hibernate.property column="kgldj"
 * @return Returns the kgldj.
 */
 public Long getKgldj() {
    return kgldj;
 }

 public void setKgldj(Long kgldj) {
    this.kgldj = kgldj;
  }

 /**
 * 付款进度
 */
 private String fkjd;

 /**
 * @hibernate.property column="fkjd"
 * @return Returns the fkjd.
 */
 public String getFkjd() {
    return fkjd;
 }

 public void setFkjd(String fkjd) {
    this.fkjd = fkjd;
  }

 /**
 * 结算情况说明
 */
 private String jsqksm;

 /**
 * @hibernate.property column="jsqksm"
 * @return Returns the jsqksm.
 */
 public String getJsqksm() {
    return jsqksm;
 }

 public void setJsqksm(String jsqksm) {
    this.jsqksm = jsqksm;
  }

 /**
 * 收款批次
 */
 private String skpc;

 /**
 * @hibernate.property column="skpc"
 * @return Returns the skpc.
 */
 public String getSkpc() {
    return skpc;
 }

 public void setSkpc(String skpc) {
    this.skpc = skpc;
  }

 /**
 * 收款时间
 */
 private Date sksj;

 /**
 * @hibernate.property column="sksj"
 * @return Returns the sksj.
 */
 public Date getSksj() {
    return sksj;
 }

 public void setSksj(Date sksj) {
    this.sksj = sksj;
  }

 /**
 * 实际应收金额
 */
 private Long sjysje;

 /**
 * @hibernate.property column="sjysje"
 * @return Returns the sjysje.
 */
 public Long getSjysje() {
    return sjysje;
 }

 public void setSjysje(Long sjysje) {
    this.sjysje = sjysje;
  }

 /**
 * 对下合同签订日期
 */
 private Date dxhtqkrq;

 /**
 * @hibernate.property column="dxhtqkrq"
 * @return Returns the dxhtqkrq.
 */
 public Date getDxhtqkrq() {
    return dxhtqkrq;
 }

 public void setDxhtqkrq(Date dxhtqkrq) {
    this.dxhtqkrq = dxhtqkrq;
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
 * 甲方预付款日期
 */
 private Date jfyfkrq;

 /**
 * @hibernate.property column="jfyfkrq"
 * @return Returns the jfyfkrq.
 */
 public Date getJfyfkrq() {
    return jfyfkrq;
 }

 public void setJfyfkrq(Date jfyfkrq) {
    this.jfyfkrq = jfyfkrq;
  }

 /**
 * 甲方实际支付金额
 */
 private Long jfsjzfje;

 /**
 * @hibernate.property column="jfsjzfje"
 * @return Returns the jfsjzfje.
 */
 public Long getJfsjzfje() {
    return jfsjzfje;
 }

 public void setJfsjzfje(Long jfsjzfje) {
    this.jfsjzfje = jfsjzfje;
  }

 /**
 * 甲方决算总价
 */
 private Long jfjszj;

 /**
 * @hibernate.property column="jfjszj"
 * @return Returns the jfjszj.
 */
 public Long getJfjszj() {
    return jfjszj;
 }

 public void setJfjszj(Long jfjszj) {
    this.jfjszj = jfjszj;
  }

 /**
 * 甲方结算日期
 */
 private Date jfjsrq;

 /**
 * @hibernate.property column="jfjsrq"
 * @return Returns the jfjsrq.
 */
 public Date getJfjsrq() {
    return jfjsrq;
 }

 public void setJfjsrq(Date jfjsrq) {
    this.jfjsrq = jfjsrq;
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
 * 施工单位工程预算总价
 */
 private Long sgdwgcyszj;

 /**
 * @hibernate.property column="sgdwgcyszj"
 * @return Returns the sgdwgcyszj.
 */
 public Long getSgdwgcyszj() {
    return sgdwgcyszj;
 }

 public void setSgdwgcyszj(Long sgdwgcyszj) {
    this.sgdwgcyszj = sgdwgcyszj;
  }

 /**
 * 视宽要求完工日期
 */
 private Date skyqwgrq;

 /**
 * @hibernate.property column="skyqwgrq"
 * @return Returns the skyqwgrq.
 */
 public Date getSkyqwgrq() {
    return skyqwgrq;
 }

 public void setSkyqwgrq(Date skyqwgrq) {
    this.skyqwgrq = skyqwgrq;
  }

 /**
 * 视宽预付款金额
 */
 private Long skyfkje;

 /**
 * @hibernate.property column="skyfkje"
 * @return Returns the skyfkje.
 */
 public Long getSkyfkje() {
    return skyfkje;
 }

 public void setSkyfkje(Long skyfkje) {
    this.skyfkje = skyfkje;
  }

 /**
 * 视宽预付时间
 */
 private Date skyfsj;

 /**
 * @hibernate.property column="skyfsj"
 * @return Returns the skyfsj.
 */
 public Date getSkyfsj() {
    return skyfsj;
 }

 public void setSkyfsj(Date skyfsj) {
    this.skyfsj = skyfsj;
  }

 /**
 * 视宽预付金额
 */
 private Long skyfje;

 /**
 * @hibernate.property column="skyfje"
 * @return Returns the skyfje.
 */
 public Long getSkyfje() {
    return skyfje;
 }

 public void setSkyfje(Long skyfje) {
    this.skyfje = skyfje;
  }

 /**
 * 视宽结算日期
 */
 private Date skjsrq;

 /**
 * @hibernate.property column="skjsrq"
 * @return Returns the skjsrq.
 */
 public Date getSkjsrq() {
    return skjsrq;
 }

 public void setSkjsrq(Date skjsrq) {
    this.skjsrq = skjsrq;
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
