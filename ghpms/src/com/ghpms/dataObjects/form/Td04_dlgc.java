package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-07-02
 * @hibernate.class table="Td04_dlgc"
 */

public class Td04_dlgc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 399663692732996736L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td04_num"
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
 * 歌华工程编号

 */
 private String ghgcbh;

 /**
 * @hibernate.property column="ghgcbh"
 * @return Returns the ghgcbh.
 */
 public String getGhgcbh() {
    return ghgcbh;
 }

 public void setGhgcbh(String ghgcbh) {
    this.ghgcbh = ghgcbh;
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
 * 工程监理

 */
 private String gcjl;

 /**
 * @hibernate.property column="gcjl"
 * @return Returns the gcjl.
 */
 public String getGcjl() {
    return gcjl;
 }

 public void setGcjl(String gcjl) {
    this.gcjl = gcjl;
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
 * 报验日期

 */
 private Date byrq;

 /**
 * @hibernate.property column="byrq"
 * @return Returns the byrq.
 */
 public Date getByrq() {
    return byrq;
 }

 public void setByrq(Date byrq) {
    this.byrq = byrq;
  }

 /**
 * 验收日期

 */
 private Date ysrq;

 /**
 * @hibernate.property column="ysrq"
 * @return Returns the ysrq.
 */
 public Date getYsrq() {
    return ysrq;
 }

 public void setYsrq(Date ysrq) {
    this.ysrq = ysrq;
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
 * 开工户数

 */
 private Long kghs;

 /**
 * @hibernate.property column="kghs"
 * @return Returns the kghs.
 */
 public Long getKghs() {
    return kghs;
 }

 public void setKghs(Long kghs) {
    this.kghs = kghs;
  }

 /**
 * 设计长度

 */
 private Long sjcd;

 /**
 * @hibernate.property column="sjcd"
 * @return Returns the sjcd.
 */
 public Long getSjcd() {
    return sjcd;
 }

 public void setSjcd(Long sjcd) {
    this.sjcd = sjcd;
  }

 /**
 * 视宽接收日期

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
 * 令号申请依据

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
 * 验收报告返回日期

 */
 private Date ysbgfhrq;

 /**
 * @hibernate.property column="ysbgfhrq"
 * @return Returns the ysbgfhrq.
 */
 public Date getYsbgfhrq() {
    return ysbgfhrq;
 }

 public void setYsbgfhrq(Date ysbgfhrq) {
    this.ysbgfhrq = ysbgfhrq;
  }

 /**
 * 歌华协议签订日期

 */
 private Date ghxyqdrq;

 /**
 * @hibernate.property column="ghxyqdrq"
 * @return Returns the ghxyqdrq.
 */
 public Date getGhxyqdrq() {
    return ghxyqdrq;
 }

 public void setGhxyqdrq(Date ghxyqdrq) {
    this.ghxyqdrq = ghxyqdrq;
  }

 /**
 * 歌华协议盖章返回日期

 */
 private Date ghxygzfhrq;

 /**
 * @hibernate.property column="ghxygzfhrq"
 * @return Returns the ghxygzfhrq.
 */
 public Date getGhxygzfhrq() {
    return ghxygzfhrq;
 }

 public void setGhxygzfhrq(Date ghxygzfhrq) {
    this.ghxygzfhrq = ghxygzfhrq;
  }

 /**
 * 干线长度（米）

 */
 private Long gxcd;

 /**
 * @hibernate.property column="gxcd"
 * @return Returns the gxcd.
 */
 public Long getGxcd() {
    return gxcd;
 }

 public void setGxcd(Long gxcd) {
    this.gxcd = gxcd;
  }

 /**
 * 分配网干线放大器调试（台）

 */
 private Long fdqts;

 /**
 * @hibernate.property column="fdqts"
 * @return Returns the fdqts.
 */
 public Long getFdqts() {
    return fdqts;
 }

 public void setFdqts(Long fdqts) {
    this.fdqts = fdqts;
  }

 /**
 * A类改造网（户）

 */
 private Long algzw;

 /**
 * @hibernate.property column="algzw"
 * @return Returns the algzw.
 */
 public Long getAlgzw() {
    return algzw;
 }

 public void setAlgzw(Long algzw) {
    this.algzw = algzw;
  }

 /**
 * 新建双向网（户）楼房

 */
 private Long xjsxwlf;

 /**
 * @hibernate.property column="xjsxwlf"
 * @return Returns the xjsxwlf.
 */
 public Long getXjsxwlf() {
    return xjsxwlf;
 }

 public void setXjsxwlf(Long xjsxwlf) {
    this.xjsxwlf = xjsxwlf;
  }

 /**
 * 新建双向网（户）平房

 */
 private Long xjsxwpf;

 /**
 * @hibernate.property column="xjsxwpf"
 * @return Returns the xjsxwpf.
 */
 public Long getXjsxwpf() {
    return xjsxwpf;
 }

 public void setXjsxwpf(Long xjsxwpf) {
    this.xjsxwpf = xjsxwpf;
  }

 /**
 * 调试（户）

 */
 private Long ts;

 /**
 * @hibernate.property column="ts"
 * @return Returns the ts.
 */
 public Long getTs() {
    return ts;
 }

 public void setTs(Long ts) {
    this.ts = ts;
  }

 /**
 * 配合（户）

 */
 private Long ph;

 /**
 * @hibernate.property column="ph"
 * @return Returns the ph.
 */
 public Long getPh() {
    return ph;
 }

 public void setPh(Long ph) {
    this.ph = ph;
  }

 /**
 * 调试配合（户）

 */
 private Long tsph;

 /**
 * @hibernate.property column="tsph"
 * @return Returns the tsph.
 */
 public Long getTsph() {
    return tsph;
 }

 public void setTsph(Long tsph) {
    this.tsph = tsph;
  }

 /**
 * 光机装调（台）

 */
 private String gjzt;

 /**
 * @hibernate.property column="gjzt"
 * @return Returns the gjzt.
 */
 public String getGjzt() {
    return gjzt;
 }

 public void setGjzt(String gjzt) {
    this.gjzt = gjzt;
  }

 /**
 * 调试工程编号

 */
 private String tsgcbh;

 /**
 * @hibernate.property column="tsgcbh"
 * @return Returns the tsgcbh.
 */
 public String getTsgcbh() {
    return tsgcbh;
 }

 public void setTsgcbh(String tsgcbh) {
    this.tsgcbh = tsgcbh;
  }

 /**
 * 从第二端起

 */
 private Long cdedq;

 /**
 * @hibernate.property column="cdedq"
 * @return Returns the cdedq.
 */
 public Long getCdedq() {
    return cdedq;
 }

 public void setCdedq(Long cdedq) {
    this.cdedq = cdedq;
  }

 /**
 * 电缆架设长度

 */
 private Long dljscd;

 /**
 * @hibernate.property column="dljscd"
 * @return Returns the dljscd.
 */
 public Long getDljscd() {
    return dljscd;
 }

 public void setDljscd(Long dljscd) {
    this.dljscd = dljscd;
  }

 /**
 * 拆除电缆

 */
 private Long ccdl;

 /**
 * @hibernate.property column="ccdl"
 * @return Returns the ccdl.
 */
 public Long getCcdl() {
    return ccdl;
 }

 public void setCcdl(Long ccdl) {
    this.ccdl = ccdl;
  }

 /**
 * 歌华协议金额

 */
 private Long ghxyje;

 /**
 * @hibernate.property column="ghxyje"
 * @return Returns the ghxyje.
 */
 public Long getGhxyje() {
    return ghxyje;
 }

 public void setGhxyje(Long ghxyje) {
    this.ghxyje = ghxyje;
  }

 /**
 * 视宽协议签定日期

 */
 private Date skxyqdrq;

 /**
 * @hibernate.property column="skxyqdrq"
 * @return Returns the skxyqdrq.
 */
 public Date getSkxyqdrq() {
    return skxyqdrq;
 }

 public void setSkxyqdrq(Date skxyqdrq) {
    this.skxyqdrq = skxyqdrq;
  }

 /**
 * 对下协议金额

 */
 private Long dxxyje;

 /**
 * @hibernate.property column="dxxyje"
 * @return Returns the dxxyje.
 */
 public Long getDxxyje() {
    return dxxyje;
 }

 public void setDxxyje(Long dxxyje) {
    this.dxxyje = dxxyje;
  }

 /**
 * 工程状态

 */
 private String gczt;

 /**
 * @hibernate.property column="gczt"
 * @return Returns the gczt.
 */
 public String getGczt() {
    return gczt;
 }

 public void setGczt(String gczt) {
    this.gczt = gczt;
  }

 /**
 * 付费标准

 */
 private String ffbz;

 /**
 * @hibernate.property column="ffbz"
 * @return Returns the ffbz.
 */
 public String getFfbz() {
    return ffbz;
 }

 public void setFfbz(String ffbz) {
    this.ffbz = ffbz;
  }

 /**
 * 工程完工日期

 */
 private Date gcwgrq;

 /**
 * @hibernate.property column="gcwgrq"
 * @return Returns the gcwgrq.
 */
 public Date getGcwgrq() {
    return gcwgrq;
 }

 public void setGcwgrq(Date gcwgrq) {
    this.gcwgrq = gcwgrq;
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
 * 市场部负责人

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
 * 分公司负责人

 */
 private String fgsfzr;

 /**
 * @hibernate.property column="fgsfzr"
 * @return Returns the fgsfzr.
 */
 public String getFgsfzr() {
    return fgsfzr;
 }

 public void setFgsfzr(String fgsfzr) {
    this.fgsfzr = fgsfzr;
  }


}
