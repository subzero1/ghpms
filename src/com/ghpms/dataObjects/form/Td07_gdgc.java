package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-07-02
 * @hibernate.class table="Td07_gdgc"
 */

public class Td07_gdgc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 146292416545980800L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td07_num"
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
 * 甲方工程编号

 */
 private String jfgcbh;

 /**
 * @hibernate.property column="jfgcbh"
 * @return Returns the jfgcbh.
 */
 public String getJfgcbh() {
    return jfgcbh;
 }

 public void setJfgcbh(String jfgcbh) {
    this.jfgcbh = jfgcbh;
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
 * 项目经理

 */
 private String xmjl;

 /**
 * @hibernate.property column="xmjl"
 * @return Returns the xmjl.
 */
 public String getXmjl() {
    return xmjl;
 }

 public void setXmjl(String xmjl) {
    this.xmjl = xmjl;
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
 * 协议开工日期

 */
 private Date xykgrq;

 /**
 * @hibernate.property column="xykgrq"
 * @return Returns the xykgrq.
 */
 public Date getXykgrq() {
    return xykgrq;
 }

 public void setXykgrq(Date xykgrq) {
    this.xykgrq = xykgrq;
  }

 /**
 * 协议完工日期

 */
 private Date xywgrq;

 /**
 * @hibernate.property column="xywgrq"
 * @return Returns the xywgrq.
 */
 public Date getXywgrq() {
    return xywgrq;
 }

 public void setXywgrq(Date xywgrq) {
    this.xywgrq = xywgrq;
  }

 /**
 * 实际开工日期

 */
 private Date sjkgrq;

 /**
 * @hibernate.property column="sjkgrq"
 * @return Returns the sjkgrq.
 */
 public Date getSjkgrq() {
    return sjkgrq;
 }

 public void setSjkgrq(Date sjkgrq) {
    this.sjkgrq = sjkgrq;
  }

 /**
 * 实际竣工日期

 */
 private Date sjjgrq;

 /**
 * @hibernate.property column="sjjgrq"
 * @return Returns the sjjgrq.
 */
 public Date getSjjgrq() {
    return sjjgrq;
 }

 public void setSjjgrq(Date sjjgrq) {
    this.sjjgrq = sjjgrq;
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
 * 施工单位负责人

 */
 private String sgdwfzr;

 /**
 * @hibernate.property column="sgdwfzr"
 * @return Returns the sgdwfzr.
 */
 public String getSgdwfzr() {
    return sgdwfzr;
 }

 public void setSgdwfzr(String sgdwfzr) {
    this.sgdwfzr = sgdwfzr;
  }

 /**
 * 协议签订日期

 */
 private Date xyqdrq;

 /**
 * @hibernate.property column="xyqdrq"
 * @return Returns the xyqdrq.
 */
 public Date getXyqdrq() {
    return xyqdrq;
 }

 public void setXyqdrq(Date xyqdrq) {
    this.xyqdrq = xyqdrq;
  }

 /**
 * 协议返回日期

 */
 private Date xyfhrq;

 /**
 * @hibernate.property column="xyfhrq"
 * @return Returns the xyfhrq.
 */
 public Date getXyfhrq() {
    return xyfhrq;
 }

 public void setXyfhrq(Date xyfhrq) {
    this.xyfhrq = xyfhrq;
  }

 /**
 * 合同金额

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
 * 材料费

 */
 private Long clf;

 /**
 * @hibernate.property column="clf"
 * @return Returns the clf.
 */
 public Long getClf() {
    return clf;
 }

 public void setClf(Long clf) {
    this.clf = clf;
  }

 /**
 * 人工费

 */
 private Long rgf;

 /**
 * @hibernate.property column="rgf"
 * @return Returns the rgf.
 */
 public Long getRgf() {
    return rgf;
 }

 public void setRgf(Long rgf) {
    this.rgf = rgf;
  }

 /**
 * 其他费

 */
 private Long qtf;

 /**
 * @hibernate.property column="qtf"
 * @return Returns the qtf.
 */
 public Long getQtf() {
    return qtf;
 }

 public void setQtf(Long qtf) {
    this.qtf = qtf;
  }

 /**
 * 实收金额

 */
 private Long ssje;

 /**
 * @hibernate.property column="ssje"
 * @return Returns the ssje.
 */
 public Long getSsje() {
    return ssje;
 }

 public void setSsje(Long ssje) {
    this.ssje = ssje;
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
 * 质保金

 */
 private Long zbj;

 /**
 * @hibernate.property column="zbj"
 * @return Returns the zbj.
 */
 public Long getZbj() {
    return zbj;
 }

 public void setZbj(Long zbj) {
    this.zbj = zbj;
  }

 /**
 * 对下付款金额

 */
 private Long dxfkje;

 /**
 * @hibernate.property column="dxfkje"
 * @return Returns the dxfkje.
 */
 public Long getDxfkje() {
    return dxfkje;
 }

 public void setDxfkje(Long dxfkje) {
    this.dxfkje = dxfkje;
  }

 /**
 * 其中工程款

 */
 private Long qzgck;

 /**
 * @hibernate.property column="qzgck"
 * @return Returns the qzgck.
 */
 public Long getQzgck() {
    return qzgck;
 }

 public void setQzgck(Long qzgck) {
    this.qzgck = qzgck;
  }

 /**
 * 其中材料费

 */
 private Long qzclf;

 /**
 * @hibernate.property column="qzclf"
 * @return Returns the qzclf.
 */
 public Long getQzclf() {
    return qzclf;
 }

 public void setQzclf(Long qzclf) {
    this.qzclf = qzclf;
  }

 /**
 * 其中人工费

 */
 private Long qzrgf;

 /**
 * @hibernate.property column="qzrgf"
 * @return Returns the qzrgf.
 */
 public Long getQzrgf() {
    return qzrgf;
 }

 public void setQzrgf(Long qzrgf) {
    this.qzrgf = qzrgf;
  }

 /**
 * 其中其他直接费

 */
 private Long qzqtzjf;

 /**
 * @hibernate.property column="qzqtzjf"
 * @return Returns the qzqtzjf.
 */
 public Long getQzqtzjf() {
    return qzqtzjf;
 }

 public void setQzqtzjf(Long qzqtzjf) {
    this.qzqtzjf = qzqtzjf;
  }

 /**
 * 其中间接费用

 */
 private Long qzjjfy;

 /**
 * @hibernate.property column="qzjjfy"
 * @return Returns the qzjjfy.
 */
 public Long getQzjjfy() {
    return qzjjfy;
 }

 public void setQzjjfy(Long qzjjfy) {
    this.qzjjfy = qzjjfy;
  }

 /**
 * 毛利率

 */
 private Long mll;

 /**
 * @hibernate.property column="mll"
 * @return Returns the mll.
 */
 public Long getMll() {
    return mll;
 }

 public void setMll(Long mll) {
    this.mll = mll;
  }

 /**
 * 设计管道长度

 */
 private Long sjfdcd;

 /**
 * @hibernate.property column="sjfdcd"
 * @return Returns the sjfdcd.
 */
 public Long getSjfdcd() {
    return sjfdcd;
 }

 public void setSjfdcd(Long sjfdcd) {
    this.sjfdcd = sjfdcd;
  }

 /**
 * 设计引上根数

 */
 private Long sjysgs;

 /**
 * @hibernate.property column="sjysgs"
 * @return Returns the sjysgs.
 */
 public Long getSjysgs() {
    return sjysgs;
 }

 public void setSjysgs(Long sjysgs) {
    this.sjysgs = sjysgs;
  }

 /**
 * 设计管孔数

 */
 private Long sjgks;

 /**
 * @hibernate.property column="sjgks"
 * @return Returns the sjgks.
 */
 public Long getSjgks() {
    return sjgks;
 }

 public void setSjgks(Long sjgks) {
    this.sjgks = sjgks;
  }

 /**
 * 设计人孔\手孔（座）

 */
 private Long sjrk_sk;

 /**
 * @hibernate.property column="sjrk_sk"
 * @return Returns the sjrk_sk.
 */
 public Long getSjrk_sk() {
    return sjrk_sk;
 }

 public void setSjrk_sk(Long sjrk_sk) {
    this.sjrk_sk = sjrk_sk;
  }

 /**
 * 设计新立杆

 */
 private Long sjxlg;

 /**
 * @hibernate.property column="sjxlg"
 * @return Returns the sjxlg.
 */
 public Long getSjxlg() {
    return sjxlg;
 }

 public void setSjxlg(Long sjxlg) {
    this.sjxlg = sjxlg;
  }

 /**
 * 竣工管道长度

 */
 private Long jggdcd;

 /**
 * @hibernate.property column="jggdcd"
 * @return Returns the jggdcd.
 */
 public Long getJggdcd() {
    return jggdcd;
 }

 public void setJggdcd(Long jggdcd) {
    this.jggdcd = jggdcd;
  }

 /**
 * 竣工引上根数

 */
 private Long jgysgs;

 /**
 * @hibernate.property column="jgysgs"
 * @return Returns the jgysgs.
 */
 public Long getJgysgs() {
    return jgysgs;
 }

 public void setJgysgs(Long jgysgs) {
    this.jgysgs = jgysgs;
  }

 /**
 * 竣工人孔座数

 */
 private Long jgrkzs;

 /**
 * @hibernate.property column="jgrkzs"
 * @return Returns the jgrkzs.
 */
 public Long getJgrkzs() {
    return jgrkzs;
 }

 public void setJgrkzs(Long jgrkzs) {
    this.jgrkzs = jgrkzs;
  }

 /**
 * 竣工新立杆

 */
 private Long jgxlg;

 /**
 * @hibernate.property column="jgxlg"
 * @return Returns the jgxlg.
 */
 public Long getJgxlg() {
    return jgxlg;
 }

 public void setJgxlg(Long jgxlg) {
    this.jgxlg = jgxlg;
  }

 /**
 * 竣工机箱

 */
 private Long jgjx;

 /**
 * @hibernate.property column="jgjx"
 * @return Returns the jgjx.
 */
 public Long getJgjx() {
    return jgjx;
 }

 public void setJgjx(Long jgjx) {
    this.jgjx = jgjx;
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
 * 撤令日期

 */
 private Date clrq;

 /**
 * @hibernate.property column="clrq"
 * @return Returns the clrq.
 */
 public Date getClrq() {
    return clrq;
 }

 public void setClrq(Date clrq) {
    this.clrq = clrq;
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
 * 计划任务书要求完成日期

 */
 private Date jsrwsyqwcrq;

 /**
 * @hibernate.property column="jsrwsyqwcrq"
 * @return Returns the jsrwsyqwcrq.
 */
 public Date getJsrwsyqwcrq() {
    return jsrwsyqwcrq;
 }

 public void setJsrwsyqwcrq(Date jsrwsyqwcrq) {
    this.jsrwsyqwcrq = jsrwsyqwcrq;
  }


}
