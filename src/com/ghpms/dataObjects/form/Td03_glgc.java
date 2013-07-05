package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-07-05
 * @hibernate.class table="Td03_glgc"
 */

public class Td03_glgc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 519678673067924288L;

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
 * 行政区别
 */
 private String xzqb;

 /**
 * @hibernate.property column="xzqb"
 * @return Returns the xzqb.
 */
 public String getXzqb() {
    return xzqb;
 }

 public void setXzqb(String xzqb) {
    this.xzqb = xzqb;
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
 * 敷设单位
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
 * 敷设状况
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
 * 焊接单位
 */
 private String hjdw;

 /**
 * @hibernate.property column="hjdw"
 * @return Returns the hjdw.
 */
 public String getHjdw() {
    return hjdw;
 }

 public void setHjdw(String hjdw) {
    this.hjdw = hjdw;
  }

 /**
 * 焊接状况
 */
 private String hjzk;

 /**
 * @hibernate.property column="hjzk"
 * @return Returns the hjzk.
 */
 public String getHjzk() {
    return hjzk;
 }

 public void setHjzk(String hjzk) {
    this.hjzk = hjzk;
  }

 /**
 * 总状况
 */
 private String zzk;

 /**
 * @hibernate.property column="zzk"
 * @return Returns the zzk.
 */
 public String getZzk() {
    return zzk;
 }

 public void setZzk(String zzk) {
    this.zzk = zzk;
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
 * 歌华任务接受日期
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
 * 视宽竣工日期
 */
 private Date skjgrq;

 /**
 * @hibernate.property column="skjgrq"
 * @return Returns the skjgrq.
 */
 public Date getSkjgrq() {
    return skjgrq;
 }

 public void setSkjgrq(Date skjgrq) {
    this.skjgrq = skjgrq;
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
 * 勘察负责人
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
 * 敷设负责人
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
 * 敷设报告
 */
 private String fsbg;

 /**
 * @hibernate.property column="fsbg"
 * @return Returns the fsbg.
 */
 public String getFsbg() {
    return fsbg;
 }

 public void setFsbg(String fsbg) {
    this.fsbg = fsbg;
  }

 /**
 * 焊接负责人
 */
 private String hjfzr;

 /**
 * @hibernate.property column="hjfzr"
 * @return Returns the hjfzr.
 */
 public String getHjfzr() {
    return hjfzr;
 }

 public void setHjfzr(String hjfzr) {
    this.hjfzr = hjfzr;
  }

 /**
 * 焊接位置
 */
 private String hjwz;

 /**
 * @hibernate.property column="hjwz"
 * @return Returns the hjwz.
 */
 public String getHjwz() {
    return hjwz;
 }

 public void setHjwz(String hjwz) {
    this.hjwz = hjwz;
  }

 /**
 * 光机位置
 */
 private String gjwz;

 /**
 * @hibernate.property column="gjwz"
 * @return Returns the gjwz.
 */
 public String getGjwz() {
    return gjwz;
 }

 public void setGjwz(String gjwz) {
    this.gjwz = gjwz;
  }

 /**
 * 焊接报告
 */
 private String hjbg;

 /**
 * @hibernate.property column="hjbg"
 * @return Returns the hjbg.
 */
 public String getHjbg() {
    return hjbg;
 }

 public void setHjbg(String hjbg) {
    this.hjbg = hjbg;
  }

 /**
 * 工程量分配
 */
 private String gclfp;

 /**
 * @hibernate.property column="gclfp"
 * @return Returns the gclfp.
 */
 public String getGclfp() {
    return gclfp;
 }

 public void setGclfp(String gclfp) {
    this.gclfp = gclfp;
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
 * 交货数量
 */
 private Long jhsl;

 /**
 * @hibernate.property column="jhsl"
 * @return Returns the jhsl.
 */
 public Long getJhsl() {
    return jhsl;
 }

 public void setJhsl(Long jhsl) {
    this.jhsl = jhsl;
  }

 /**
 * 交货日期
 */
 private Date jhrq;

 /**
 * @hibernate.property column="jhrq"
 * @return Returns the jhrq.
 */
 public Date getJhrq() {
    return jhrq;
 }

 public void setJhrq(Date jhrq) {
    this.jhrq = jhrq;
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
 * 架空(协议)
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
 * 管道(协议)
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
 * 拆除(协议)
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
 * 子管(协议)
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
 * 焊接(协议)
 */
 private Long hj_xy;

 /**
 * @hibernate.property column="hj_xy"
 * @return Returns the hj_xy.
 */
 public Long getHj_xy() {
    return hj_xy;
 }

 public void setHj_xy(Long hj_xy) {
    this.hj_xy = hj_xy;
  }

 /**
 * 上架(协议)
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
 * 装调(协议)
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
 * 24芯以下开盒(协议)

 */
 private Long kh24_xy;

 /**
 * @hibernate.property column="kh24_xy"
 * @return Returns the kh24_xy.
 */
 public Long getKh24_xy() {
    return kh24_xy;
 }

 public void setKh24_xy(Long kh24_xy) {
    this.kh24_xy = kh24_xy;
  }

 /**
 * 28-100芯开盒（协议）

 */
 private Long kh28_xy;

 /**
 * @hibernate.property column="kh28_xy"
 * @return Returns the kh28_xy.
 */
 public Long getKh28_xy() {
    return kh28_xy;
 }

 public void setKh28_xy(Long kh28_xy) {
    this.kh28_xy = kh28_xy;
  }

 /**
 * 8米立杆(协议)

 */
 private Long lg8_xy;

 /**
 * @hibernate.property column="lg8_xy"
 * @return Returns the lg8_xy.
 */
 public Long getLg8_xy() {
    return lg8_xy;
 }

 public void setLg8_xy(Long lg8_xy) {
    this.lg8_xy = lg8_xy;
  }

 /**
 * 10米立杆(协议)

 */
 private Long lg10_xy;

 /**
 * @hibernate.property column="lg10_xy"
 * @return Returns the lg10_xy.
 */
 public Long getLg10_xy() {
    return lg10_xy;
 }

 public void setLg10_xy(Long lg10_xy) {
    this.lg10_xy = lg10_xy;
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
 * 附加歌华协议金额

 */
 private Long fjghxyje;

 /**
 * @hibernate.property column="fjghxyje"
 * @return Returns the fjghxyje.
 */
 public Long getFjghxyje() {
    return fjghxyje;
 }

 public void setFjghxyje(Long fjghxyje) {
    this.fjghxyje = fjghxyje;
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
 * 协议情况

 */
 private String xyqk;

 /**
 * @hibernate.property column="xyqk"
 * @return Returns the xyqk.
 */
 public String getXyqk() {
    return xyqk;
 }

 public void setXyqk(String xyqk) {
    this.xyqk = xyqk;
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
 * 实际架空

 */
 private Long sjjk;

 /**
 * @hibernate.property column="sjjk"
 * @return Returns the sjjk.
 */
 public Long getSjjk() {
    return sjjk;
 }

 public void setSjjk(Long sjjk) {
    this.sjjk = sjjk;
  }

 /**
 * 实际管道

 */
 private Long sjgd;

 /**
 * @hibernate.property column="sjgd"
 * @return Returns the sjgd.
 */
 public Long getSjgd() {
    return sjgd;
 }

 public void setSjgd(Long sjgd) {
    this.sjgd = sjgd;
  }

 /**
 * 实际拆除

 */
 private Long sjcc;

 /**
 * @hibernate.property column="sjcc"
 * @return Returns the sjcc.
 */
 public Long getSjcc() {
    return sjcc;
 }

 public void setSjcc(Long sjcc) {
    this.sjcc = sjcc;
  }

 /**
 * 实际拉线

 */
 private Long sjlx;

 /**
 * @hibernate.property column="sjlx"
 * @return Returns the sjlx.
 */
 public Long getSjlx() {
    return sjlx;
 }

 public void setSjlx(Long sjlx) {
    this.sjlx = sjlx;
  }

 /**
 * 实际立杆(8米)

 */
 private Long sjlg8;

 /**
 * @hibernate.property column="sjlg8"
 * @return Returns the sjlg8.
 */
 public Long getSjlg8() {
    return sjlg8;
 }

 public void setSjlg8(Long sjlg8) {
    this.sjlg8 = sjlg8;
  }

 /**
 * 实际立杆(10米)

 */
 private Long sjlg10;

 /**
 * @hibernate.property column="sjlg10"
 * @return Returns the sjlg10.
 */
 public Long getSjlg10() {
    return sjlg10;
 }

 public void setSjlg10(Long sjlg10) {
    this.sjlg10 = sjlg10;
  }

 /**
 * 实际子管

 */
 private Long sjzg;

 /**
 * @hibernate.property column="sjzg"
 * @return Returns the sjzg.
 */
 public Long getSjzg() {
    return sjzg;
 }

 public void setSjzg(Long sjzg) {
    this.sjzg = sjzg;
  }

 /**
 * 实际熔接

 */
 private Long sjrj;

 /**
 * @hibernate.property column="sjrj"
 * @return Returns the sjrj.
 */
 public Long getSjrj() {
    return sjrj;
 }

 public void setSjrj(Long sjrj) {
    this.sjrj = sjrj;
  }

 /**
 * 实际上架

 */
 private Long sjsj;

 /**
 * @hibernate.property column="sjsj"
 * @return Returns the sjsj.
 */
 public Long getSjsj() {
    return sjsj;
 }

 public void setSjsj(Long sjsj) {
    this.sjsj = sjsj;
  }

 /**
 * 实际装调

 */
 private Long sjzt;

 /**
 * @hibernate.property column="sjzt"
 * @return Returns the sjzt.
 */
 public Long getSjzt() {
    return sjzt;
 }

 public void setSjzt(Long sjzt) {
    this.sjzt = sjzt;
  }

 /**
 * 24芯以下开盒（含）

 */
 private Long kh24;

 /**
 * @hibernate.property column="kh24"
 * @return Returns the kh24.
 */
 public Long getKh24() {
    return kh24;
 }

 public void setKh24(Long kh24) {
    this.kh24 = kh24;
  }

 /**
 * 28-100芯开盒

 */
 private Long kh28;

 /**
 * @hibernate.property column="kh28"
 * @return Returns the kh28.
 */
 public Long getKh28() {
    return kh28;
 }

 public void setKh28(Long kh28) {
    this.kh28 = kh28;
  }

 /**
 * 拉线(协议)

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
 * 熔接盘放缆

 */
 private Long rjpfl;

 /**
 * @hibernate.property column="rjpfl"
 * @return Returns the rjpfl.
 */
 public Long getRjpfl() {
    return rjpfl;
 }

 public void setRjpfl(Long rjpfl) {
    this.rjpfl = rjpfl;
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
 * 歌华撤令批次

 */
 private Long ghclpc;

 /**
 * @hibernate.property column="ghclpc"
 * @return Returns the ghclpc.
 */
 public Long getGhclpc() {
    return ghclpc;
 }

 public void setGhclpc(Long ghclpc) {
    this.ghclpc = ghclpc;
  }

 /**
 * 歌华结算日期

 */
 private Date ghjsrq;

 /**
 * @hibernate.property column="ghjsrq"
 * @return Returns the ghjsrq.
 */
 public Date getGhjsrq() {
    return ghjsrq;
 }

 public void setGhjsrq(Date ghjsrq) {
    this.ghjsrq = ghjsrq;
  }

 /**
 * 歌华结算金额

 */
 private Long ghjsje;

 /**
 * @hibernate.property column="ghjsje"
 * @return Returns the ghjsje.
 */
 public Long getGhjsje() {
    return ghjsje;
 }

 public void setGhjsje(Long ghjsje) {
    this.ghjsje = ghjsje;
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
 * 视宽结算金额

 */
 private Long skjsje;

 /**
 * @hibernate.property column="skjsje"
 * @return Returns the skjsje.
 */
 public Long getSkjsje() {
    return skjsje;
 }

 public void setSkjsje(Long skjsje) {
    this.skjsje = skjsje;
  }

 /**
 * 视宽撤令批次+存档日期

 */
 private String skclpc_cdrq;

 /**
 * @hibernate.property column="skclpc_cdrq"
 * @return Returns the skclpc_cdrq.
 */
 public String getSkclpc_cdrq() {
    return skclpc_cdrq;
 }

 public void setSkclpc_cdrq(String skclpc_cdrq) {
    this.skclpc_cdrq = skclpc_cdrq;
  }

 /**
 * 分类

 */
 private String fl;

 /**
 * @hibernate.property column="fl"
 * @return Returns the fl.
 */
 public String getFl() {
    return fl;
 }

 public void setFl(String fl) {
    this.fl = fl;
  }

 /**
 * 应报验日期（60天）

 */
 private Date ybyrq;

 /**
 * @hibernate.property column="ybyrq"
 * @return Returns the ybyrq.
 */
 public Date getYbyrq() {
    return ybyrq;
 }

 public void setYbyrq(Date ybyrq) {
    this.ybyrq = ybyrq;
  }

 /**
 * 应返回验收报告日期（30天）

 */
 private Date yfhysbgrq;

 /**
 * @hibernate.property column="yfhysbgrq"
 * @return Returns the yfhysbgrq.
 */
 public Date getYfhysbgrq() {
    return yfhysbgrq;
 }

 public void setYfhysbgrq(Date yfhysbgrq) {
    this.yfhysbgrq = yfhysbgrq;
  }

 /**
 * 合干、外包、视宽

 */
 private String hgwbsk;

 /**
 * @hibernate.property column="hgwbsk"
 * @return Returns the hgwbsk.
 */
 public String getHgwbsk() {
    return hgwbsk;
 }

 public void setHgwbsk(String hgwbsk) {
    this.hgwbsk = hgwbsk;
  }

 /**
 * 数据编号

 */
 private String sjbh;

 /**
 * @hibernate.property column="sjbh"
 * @return Returns the sjbh.
 */
 public String getSjbh() {
    return sjbh;
 }

 public void setSjbh(String sjbh) {
    this.sjbh = sjbh;
  }

 /**
 * 歌华外付款单位

 */
 private String ghwfkdw;

 /**
 * @hibernate.property column="ghwfkdw"
 * @return Returns the ghwfkdw.
 */
 public String getGhwfkdw() {
    return ghwfkdw;
 }

 public void setGhwfkdw(String ghwfkdw) {
    this.ghwfkdw = ghwfkdw;
  }

 /**
 * 歌华外付款金额

 */
 private Long ghwfkje;

 /**
 * @hibernate.property column="ghwfkje"
 * @return Returns the ghwfkje.
 */
 public Long getGhwfkje() {
    return ghwfkje;
 }

 public void setGhwfkje(Long ghwfkje) {
    this.ghwfkje = ghwfkje;
  }

 /**
 * 歌华外收款日期

 */
 private Date ghwfkrq;

 /**
 * @hibernate.property column="ghwfkrq"
 * @return Returns the ghwfkrq.
 */
 public Date getGhwfkrq() {
    return ghwfkrq;
 }

 public void setGhwfkrq(Date ghwfkrq) {
    this.ghwfkrq = ghwfkrq;
  }

 /**
 * 歌华外付款单位协议号

 */
 private String ghwfkdwxyh;

 /**
 * @hibernate.property column="ghwfkdwxyh"
 * @return Returns the ghwfkdwxyh.
 */
 public String getGhwfkdwxyh() {
    return ghwfkdwxyh;
 }

 public void setGhwfkdwxyh(String ghwfkdwxyh) {
    this.ghwfkdwxyh = ghwfkdwxyh;
  }

 /**
 * 市场部项目负责人

 */
 private String scbxmfzr;

 /**
 * @hibernate.property column="scbxmfzr"
 * @return Returns the scbxmfzr.
 */
 public String getScbxmfzr() {
    return scbxmfzr;
 }

 public void setScbxmfzr(String scbxmfzr) {
    this.scbxmfzr = scbxmfzr;
  }

 /**
 * 歌华外协议签订日期

 */
 private Date ghwxyqdrq;

 /**
 * @hibernate.property column="ghwxyqdrq"
 * @return Returns the ghwxyqdrq.
 */
 public Date getGhwxyqdrq() {
    return ghwxyqdrq;
 }

 public void setGhwxyqdrq(Date ghwxyqdrq) {
    this.ghwxyqdrq = ghwxyqdrq;
  }

 /**
 * 104芯以上开盒

 */
 private Long kh104;

 /**
 * @hibernate.property column="kh104"
 * @return Returns the kh104.
 */
 public Long getKh104() {
    return kh104;
 }

 public void setKh104(Long kh104) {
    this.kh104 = kh104;
  }

 /**
 * 歌华外协议返回日期

 */
 private Date ghwxyfhrq;

 /**
 * @hibernate.property column="ghwxyfhrq"
 * @return Returns the ghwxyfhrq.
 */
 public Date getGhwxyfhrq() {
    return ghwxyfhrq;
 }

 public void setGhwxyfhrq(Date ghwxyfhrq) {
    this.ghwxyfhrq = ghwxyfhrq;
  }

 /**
 * 计建部工程类型

 */
 private Date jjbgclx;

 /**
 * @hibernate.property column="jjbgclx"
 * @return Returns the jjbgclx.
 */
 public Date getJjbgclx() {
    return jjbgclx;
 }

 public void setJjbgclx(Date jjbgclx) {
    this.jjbgclx = jjbgclx;
  }


}
