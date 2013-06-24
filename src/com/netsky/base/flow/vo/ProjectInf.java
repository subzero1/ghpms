package com.netsky.base.flow.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-25
 * @hibernate.class table="ProjectInf"
 */

public class ProjectInf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 78919501844831872L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="xxx_num"
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
 * 
 */
 private Date sjkssj;

 /**
 * @hibernate.property column="sjkssj"
 * @return Returns the sjkssj.
 */
 public Date getSjkssj() {
    return sjkssj;
 }

 public void setSjkssj(Date sjkssj) {
    this.sjkssj = sjkssj;
  }

 /**
 * 
 */
 private Date sjjssj;

 /**
 * @hibernate.property column="sjjssj"
 * @return Returns the sjjssj.
 */
 public Date getSjjssj() {
    return sjjssj;
 }

 public void setSjjssj(Date sjjssj) {
    this.sjjssj = sjjssj;
  }

 /**
 * 
 */
 private String xmjd;

 /**
 * @hibernate.property column="xmjd"
 * @return Returns the xmjd.
 */
 public String getXmjd() {
    return xmjd;
 }

 public void setXmjd(String xmjd) {
    this.xmjd = xmjd;
  }

 /**
 * 
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
 * 
 */
 private String jsry;

 /**
 * @hibernate.property column="jsry"
 * @return Returns the jsry.
 */
 public String getJsry() {
    return jsry;
 }

 public void setJsry(String jsry) {
    this.jsry = jsry;
  }

 /**
 * 
 */
 private Date sjshjkssj;

 /**
 * @hibernate.property column="sjshjkssj"
 * @return Returns the sjshjkssj.
 */
 public Date getSjshjkssj() {
    return sjshjkssj;
 }

 public void setSjshjkssj(Date sjshjkssj) {
    this.sjshjkssj = sjshjkssj;
  }

 /**
 * 
 */
 private Date sjshjjssj;

 /**
 * @hibernate.property column="sjshjjssj"
 * @return Returns the sjshjjssj.
 */
 public Date getSjshjjssj() {
    return sjshjjssj;
 }

 public void setSjshjjssj(Date sjshjjssj) {
    this.sjshjjssj = sjshjjssj;
  }

 /**
 * 
 */
 private Date ssrq;

 /**
 * @hibernate.property column="ssrq"
 * @return Returns the ssrq.
 */
 public Date getSsrq() {
    return ssrq;
 }

 public void setSsrq(Date ssrq) {
    this.ssrq = ssrq;
  }

 /**
 * 
 */
 private Date gzrq;

 /**
 * @hibernate.property column="gzrq"
 * @return Returns the gzrq.
 */
 public Date getGzrq() {
    return gzrq;
 }

 public void setGzrq(Date gzrq) {
    this.gzrq = gzrq;
  }

 /**
 * 
 */
 private Date kprq;

 /**
 * @hibernate.property column="kprq"
 * @return Returns the kprq.
 */
 public Date getKprq() {
    return kprq;
 }

 public void setKprq(Date kprq) {
    this.kprq = kprq;
  }

 /**
 * 
 */
 private Date ssjrq;

 /**
 * @hibernate.property column="ssjrq"
 * @return Returns the ssjrq.
 */
 public Date getSsjrq() {
    return ssjrq;
 }

 public void setSsjrq(Date ssjrq) {
    this.ssjrq = ssjrq;
  }

 /**
 * 
 */
 private Long sjfatgcs;

 /**
 * @hibernate.property column="sjfatgcs"
 * @return Returns the sjfatgcs.
 */
 public Long getSjfatgcs() {
    return sjfatgcs;
 }

 public void setSjfatgcs(Long sjfatgcs) {
    this.sjfatgcs = sjfatgcs;
  }

 /**
 * 
 */
 private String ysry;

 /**
 * @hibernate.property column="ysry"
 * @return Returns the ysry.
 */
 public String getYsry() {
    return ysry;
 }

 public void setYsry(String ysry) {
    this.ysry = ysry;
  }

 /**
 * 
 */
 private Date yssj;

 /**
 * @hibernate.property column="yssj"
 * @return Returns the yssj.
 */
 public Date getYssj() {
    return yssj;
 }

 public void setYssj(Date yssj) {
    this.yssj = yssj;
  }

 /**
 * 
 */
 private String sjhtbh;

 /**
 * @hibernate.property column="sjhtbh"
 * @return Returns the sjhtbh.
 */
 public String getSjhtbh() {
    return sjhtbh;
 }

 public void setSjhtbh(String sjhtbh) {
    this.sjhtbh = sjhtbh;
  }

 /**
 * 
 */
 private Long sjhtje;

 /**
 * @hibernate.property column="sjhtje"
 * @return Returns the sjhtje.
 */
 public Long getSjhtje() {
    return sjhtje;
 }

 public void setSjhtje(Long sjhtje) {
    this.sjhtje = sjhtje;
  }

 /**
 * 
 */
 private Date sjhtqdrq;

 /**
 * @hibernate.property column="sjhtqdrq"
 * @return Returns the sjhtqdrq.
 */
 public Date getSjhtqdrq() {
    return sjhtqdrq;
 }

 public void setSjhtqdrq(Date sjhtqdrq) {
    this.sjhtqdrq = sjhtqdrq;
  }

 /**
 * 
 */
 private String sghtbh;

 /**
 * @hibernate.property column="sghtbh"
 * @return Returns the sghtbh.
 */
 public String getSghtbh() {
    return sghtbh;
 }

 public void setSghtbh(String sghtbh) {
    this.sghtbh = sghtbh;
  }

 /**
 * 
 */
 private Long sghtje;

 /**
 * @hibernate.property column="sghtje"
 * @return Returns the sghtje.
 */
 public Long getSghtje() {
    return sghtje;
 }

 public void setSghtje(Long sghtje) {
    this.sghtje = sghtje;
  }

 /**
 * 
 */
 private Date sghtqdrq;

 /**
 * @hibernate.property column="sghtqdrq"
 * @return Returns the sghtqdrq.
 */
 public Date getSghtqdrq() {
    return sghtqdrq;
 }

 public void setSghtqdrq(Date sghtqdrq) {
    this.sghtqdrq = sghtqdrq;
  }

 /**
 * 
 */
 private String jlhtbh;

 /**
 * @hibernate.property column="jlhtbh"
 * @return Returns the jlhtbh.
 */
 public String getJlhtbh() {
    return jlhtbh;
 }

 public void setJlhtbh(String jlhtbh) {
    this.jlhtbh = jlhtbh;
  }

 /**
 * 
 */
 private Long jlhtje;

 /**
 * @hibernate.property column="jlhtje"
 * @return Returns the jlhtje.
 */
 public Long getJlhtje() {
    return jlhtje;
 }

 public void setJlhtje(Long jlhtje) {
    this.jlhtje = jlhtje;
  }

 /**
 * 
 */
 private Date jlhtqdrq;

 /**
 * @hibernate.property column="jlhtqdrq"
 * @return Returns the jlhtqdrq.
 */
 public Date getJlhtqdrq() {
    return jlhtqdrq;
 }

 public void setJlhtqdrq(Date jlhtqdrq) {
    this.jlhtqdrq = jlhtqdrq;
  }

 /**
 * 
 */
 private String bdbh;

 /**
 * @hibernate.property column="bdbh"
 * @return Returns the bdbh.
 */
 public String getBdbh() {
    return bdbh;
 }

 public void setBdbh(String bdbh) {
    this.bdbh = bdbh;
  }

 /**
 * 
 */
 private String ssdq;

 /**
 * @hibernate.property column="ssdq"
 * @return Returns the ssdq.
 */
 public String getSsdq() {
    return ssdq;
 }

 public void setSsdq(String ssdq) {
    this.ssdq = ssdq;
  }

 /**
 * 
 */
 private String gclb;

 /**
 * @hibernate.property column="gclb"
 * @return Returns the gclb.
 */
 public String getGclb() {
    return gclb;
 }

 public void setGclb(String gclb) {
    this.gclb = gclb;
  }

 /**
 * 
 */
 private String zydl;

 /**
 * @hibernate.property column="zydl"
 * @return Returns the zydl.
 */
 public String getZydl() {
    return zydl;
 }

 public void setZydl(String zydl) {
    this.zydl = zydl;
  }

 /**
 * 
 */
 private String zyxx;

 /**
 * @hibernate.property column="zyxx"
 * @return Returns the zyxx.
 */
 public String getZyxx() {
    return zyxx;
 }

 public void setZyxx(String zyxx) {
    this.zyxx = zyxx;
  }

 /**
 * 
 */
 private String jsxz;

 /**
 * @hibernate.property column="jsxz"
 * @return Returns the jsxz.
 */
 public String getJsxz() {
    return jsxz;
 }

 public void setJsxz(String jsxz) {
    this.jsxz = jsxz;
  }

 /**
 * 
 */
 private String jsdd;

 /**
 * @hibernate.property column="jsdd"
 * @return Returns the jsdd.
 */
 public String getJsdd() {
    return jsdd;
 }

 public void setJsdd(String jsdd) {
    this.jsdd = jsdd;
  }

 /**
 * 
 */
 private String yslx;

 /**
 * @hibernate.property column="yslx"
 * @return Returns the yslx.
 */
 public String getYslx() {
    return yslx;
 }

 public void setYslx(String yslx) {
    this.yslx = yslx;
  }

 /**
 * 
 */
 private Long ys_je;

 /**
 * @hibernate.property column="ys_je"
 * @return Returns the ys_je.
 */
 public Long getYs_je() {
    return ys_je;
 }

 public void setYs_je(Long ys_je) {
    this.ys_je = ys_je;
  }

 /**
 * 
 */
 private Long ys_jaf;

 /**
 * @hibernate.property column="ys_jaf"
 * @return Returns the ys_jaf.
 */
 public Long getYs_jaf() {
    return ys_jaf;
 }

 public void setYs_jaf(Long ys_jaf) {
    this.ys_jaf = ys_jaf;
  }

 /**
 * 
 */
 private Long ys_clf;

 /**
 * @hibernate.property column="ys_clf"
 * @return Returns the ys_clf.
 */
 public Long getYs_clf() {
    return ys_clf;
 }

 public void setYs_clf(Long ys_clf) {
    this.ys_clf = ys_clf;
  }

 /**
 * 
 */
 private Long ys_rgf;

 /**
 * @hibernate.property column="ys_rgf"
 * @return Returns the ys_rgf.
 */
 public Long getYs_rgf() {
    return ys_rgf;
 }

 public void setYs_rgf(Long ys_rgf) {
    this.ys_rgf = ys_rgf;
  }

 /**
 * 
 */
 private Long ys_jggr;

 /**
 * @hibernate.property column="ys_jggr"
 * @return Returns the ys_jggr.
 */
 public Long getYs_jggr() {
    return ys_jggr;
 }

 public void setYs_jggr(Long ys_jggr) {
    this.ys_jggr = ys_jggr;
  }

 /**
 * 
 */
 private Long ys_pggr;

 /**
 * @hibernate.property column="ys_pggr"
 * @return Returns the ys_pggr.
 */
 public Long getYs_pggr() {
    return ys_pggr;
 }

 public void setYs_pggr(Long ys_pggr) {
    this.ys_pggr = ys_pggr;
  }

 /**
 * 
 */
 private Long ys_sbf;

 /**
 * @hibernate.property column="ys_sbf"
 * @return Returns the ys_sbf.
 */
 public Long getYs_sbf() {
    return ys_sbf;
 }

 public void setYs_sbf(Long ys_sbf) {
    this.ys_sbf = ys_sbf;
  }

 /**
 * 
 */
 private Long ys_jxf;

 /**
 * @hibernate.property column="ys_jxf"
 * @return Returns the ys_jxf.
 */
 public Long getYs_jxf() {
    return ys_jxf;
 }

 public void setYs_jxf(Long ys_jxf) {
    this.ys_jxf = ys_jxf;
  }

 /**
 * 
 */
 private Long ys_ybf;

 /**
 * @hibernate.property column="ys_ybf"
 * @return Returns the ys_ybf.
 */
 public Long getYs_ybf() {
    return ys_ybf;
 }

 public void setYs_ybf(Long ys_ybf) {
    this.ys_ybf = ys_ybf;
  }

 /**
 * 
 */
 private Long ys_sjf;

 /**
 * @hibernate.property column="ys_sjf"
 * @return Returns the ys_sjf.
 */
 public Long getYs_sjf() {
    return ys_sjf;
 }

 public void setYs_sjf(Long ys_sjf) {
    this.ys_sjf = ys_sjf;
  }

 /**
 * 
 */
 private Long ys_jlf;

 /**
 * @hibernate.property column="ys_jlf"
 * @return Returns the ys_jlf.
 */
 public Long getYs_jlf() {
    return ys_jlf;
 }

 public void setYs_jlf(Long ys_jlf) {
    this.ys_jlf = ys_jlf;
  }

 /**
 * 
 */
 private Long ys_qtf;

 /**
 * @hibernate.property column="ys_qtf"
 * @return Returns the ys_qtf.
 */
 public Long getYs_qtf() {
    return ys_qtf;
 }

 public void setYs_qtf(Long ys_qtf) {
    this.ys_qtf = ys_qtf;
  }

 /**
 * 
 */
 private Long ys_sgf;

 /**
 * @hibernate.property column="ys_sgf"
 * @return Returns the ys_sgf.
 */
 public Long getYs_sgf() {
    return ys_sgf;
 }

 public void setYs_sgf(Long ys_sgf) {
    this.ys_sgf = ys_sgf;
  }

 /**
 * 
 */
 private Long bg_jggr;

 /**
 * @hibernate.property column="bg_jggr"
 * @return Returns the bg_jggr.
 */
 public Long getBg_jggr() {
    return bg_jggr;
 }

 public void setBg_jggr(Long bg_jggr) {
    this.bg_jggr = bg_jggr;
  }

 /**
 * 
 */
 private Long bg_pggr;

 /**
 * @hibernate.property column="bg_pggr"
 * @return Returns the bg_pggr.
 */
 public Long getBg_pggr() {
    return bg_pggr;
 }

 public void setBg_pggr(Long bg_pggr) {
    this.bg_pggr = bg_pggr;
  }

 /**
 * 
 */
 private Long bg_je;

 /**
 * @hibernate.property column="bg_je"
 * @return Returns the bg_je.
 */
 public Long getBg_je() {
    return bg_je;
 }

 public void setBg_je(Long bg_je) {
    this.bg_je = bg_je;
  }

 /**
 * 
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
 * 
 */
 private String sgd;

 /**
 * @hibernate.property column="sgd"
 * @return Returns the sgd.
 */
 public String getSgd() {
    return sgd;
 }

 public void setSgd(String sgd) {
    this.sgd = sgd;
  }

 /**
 * 
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
 * 
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
 * 
 */
 private String cjr;

 /**
 * @hibernate.property column="cjr"
 * @return Returns the cjr.
 */
 public String getCjr() {
    return cjr;
 }

 public void setCjr(String cjr) {
    this.cjr = cjr;
  }

 /**
 * 
 */
 private String tpgly;

 /**
 * @hibernate.property column="tpgly"
 * @return Returns the tpgly.
 */
 public String getTpgly() {
    return tpgly;
 }

 public void setTpgly(String tpgly) {
    this.tpgly = tpgly;
  }

 /**
 * 
 */
 private String sjpgry;

 /**
 * @hibernate.property column="sjpgry"
 * @return Returns the sjpgry.
 */
 public String getSjpgry() {
    return sjpgry;
 }

 public void setSjpgry(String sjpgry) {
    this.sjpgry = sjpgry;
  }

 /**
 * 
 */
 private String sjshry;

 /**
 * @hibernate.property column="sjshry"
 * @return Returns the sjshry.
 */
 public String getSjshry() {
    return sjshry;
 }

 public void setSjshry(String sjshry) {
    this.sjshry = sjshry;
  }

 /**
 * 
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
 * 
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
 * 
 */
 private String jzpgry;

 /**
 * @hibernate.property column="jzpgry"
 * @return Returns the jzpgry.
 */
 public String getJzpgry() {
    return jzpgry;
 }

 public void setJzpgry(String jzpgry) {
    this.jzpgry = jzpgry;
  }

 /**
 * 
 */
 private String sjfzr;

 /**
 * @hibernate.property column="sjfzr"
 * @return Returns the sjfzr.
 */
 public String getSjfzr() {
    return sjfzr;
 }

 public void setSjfzr(String sjfzr) {
    this.sjfzr = sjfzr;
  }

 /**
 * 
 */
 private String sjfzrdh;

 /**
 * @hibernate.property column="sjfzrdh"
 * @return Returns the sjfzrdh.
 */
 public String getSjfzrdh() {
    return sjfzrdh;
 }

 public void setSjfzrdh(String sjfzrdh) {
    this.sjfzrdh = sjfzrdh;
  }

 /**
 * 
 */
 private String sgfzr;

 /**
 * @hibernate.property column="sgfzr"
 * @return Returns the sgfzr.
 */
 public String getSgfzr() {
    return sgfzr;
 }

 public void setSgfzr(String sgfzr) {
    this.sgfzr = sgfzr;
  }

 /**
 * 
 */
 private String sgfzrdh;

 /**
 * @hibernate.property column="sgfzrdh"
 * @return Returns the sgfzrdh.
 */
 public String getSgfzrdh() {
    return sgfzrdh;
 }

 public void setSgfzrdh(String sgfzrdh) {
    this.sgfzrdh = sgfzrdh;
  }

 /**
 * 
 */
 private String jlgcs;

 /**
 * @hibernate.property column="jlgcs"
 * @return Returns the jlgcs.
 */
 public String getJlgcs() {
    return jlgcs;
 }

 public void setJlgcs(String jlgcs) {
    this.jlgcs = jlgcs;
  }

 /**
 * 
 */
 private String jlgcsdh;

 /**
 * @hibernate.property column="jlgcsdh"
 * @return Returns the jlgcsdh.
 */
 public String getJlgcsdh() {
    return jlgcsdh;
 }

 public void setJlgcsdh(String jlgcsdh) {
    this.jlgcsdh = jlgcsdh;
  }

 /**
 * 
 */
 private Date cjrq;

 /**
 * @hibernate.property column="cjrq"
 * @return Returns the cjrq.
 */
 public Date getCjrq() {
    return cjrq;
 }

 public void setCjrq(Date cjrq) {
    this.cjrq = cjrq;
  }

 /**
 * 
 */
 private Date sjpgsj;

 /**
 * @hibernate.property column="sjpgsj"
 * @return Returns the sjpgsj.
 */
 public Date getSjpgsj() {
    return sjpgsj;
 }

 public void setSjpgsj(Date sjpgsj) {
    this.sjpgsj = sjpgsj;
  }

 /**
 * 
 */
 private Date jhsjwcsj;

 /**
 * @hibernate.property column="jhsjwcsj"
 * @return Returns the jhsjwcsj.
 */
 public Date getJhsjwcsj() {
    return jhsjwcsj;
 }

 public void setJhsjwcsj(Date jhsjwcsj) {
    this.jhsjwcsj = jhsjwcsj;
  }

 /**
 * 
 */
 private Date sjtjsj;

 /**
 * @hibernate.property column="sjtjsj"
 * @return Returns the sjtjsj.
 */
 public Date getSjtjsj() {
    return sjtjsj;
 }

 public void setSjtjsj(Date sjtjsj) {
    this.sjtjsj = sjtjsj;
  }

 /**
 * 
 */
 private Date sgpfsj;

 /**
 * @hibernate.property column="sgpfsj"
 * @return Returns the sgpfsj.
 */
 public Date getSgpfsj() {
    return sgpfsj;
 }

 public void setSgpfsj(Date sgpfsj) {
    this.sgpfsj = sgpfsj;
  }

 /**
 * 
 */
 private Date jhkgsj;

 /**
 * @hibernate.property column="jhkgsj"
 * @return Returns the jhkgsj.
 */
 public Date getJhkgsj() {
    return jhkgsj;
 }

 public void setJhkgsj(Date jhkgsj) {
    this.jhkgsj = jhkgsj;
  }

 /**
 * 
 */
 private Date jhjgsj;

 /**
 * @hibernate.property column="jhjgsj"
 * @return Returns the jhjgsj.
 */
 public Date getJhjgsj() {
    return jhjgsj;
 }

 public void setJhjgsj(Date jhjgsj) {
    this.jhjgsj = jhjgsj;
  }

 /**
 * 
 */
 private Date sjkgsj;

 /**
 * @hibernate.property column="sjkgsj"
 * @return Returns the sjkgsj.
 */
 public Date getSjkgsj() {
    return sjkgsj;
 }

 public void setSjkgsj(Date sjkgsj) {
    this.sjkgsj = sjkgsj;
  }

 /**
 * 
 */
 private Date sjjgsj;

 /**
 * @hibernate.property column="sjjgsj"
 * @return Returns the sjjgsj.
 */
 public Date getSjjgsj() {
    return sjjgsj;
 }

 public void setSjjgsj(Date sjjgsj) {
    this.sjjgsj = sjjgsj;
  }

 /**
 * 
 */
 private Date jlpfsj;

 /**
 * @hibernate.property column="jlpfsj"
 * @return Returns the jlpfsj.
 */
 public Date getJlpfsj() {
    return jlpfsj;
 }

 public void setJlpfsj(Date jlpfsj) {
    this.jlpfsj = jlpfsj;
  }

 /**
 * 
 */
 private Long ssnd;

 /**
 * @hibernate.property column="ssnd"
 * @return Returns the ssnd.
 */
 public Long getSsnd() {
    return ssnd;
 }

 public void setSsnd(Long ssnd) {
    this.ssnd = ssnd;
  }

 /**
 * 
 */
 private Long jlrjtbzq;

 /**
 * @hibernate.property column="jlrjtbzq"
 * @return Returns the jlrjtbzq.
 */
 public Long getJlrjtbzq() {
    return jlrjtbzq;
 }

 public void setJlrjtbzq(Long jlrjtbzq) {
    this.jlrjtbzq = jlrjtbzq;
  }

 /**
 * 
 */
 private Long sgjdtbzq;

 /**
 * @hibernate.property column="sgjdtbzq"
 * @return Returns the sgjdtbzq.
 */
 public Long getSgjdtbzq() {
    return sgjdtbzq;
 }

 public void setSgjdtbzq(Long sgjdtbzq) {
    this.sgjdtbzq = sgjdtbzq;
  }

 /**
 * 
 */
 private Long kcfkzq;

 /**
 * @hibernate.property column="kcfkzq"
 * @return Returns the kcfkzq.
 */
 public Long getKcfkzq() {
    return kcfkzq;
 }

 public void setKcfkzq(Long kcfkzq) {
    this.kcfkzq = kcfkzq;
  }

 /**
 * 
 */
 private Long yscs;

 /**
 * @hibernate.property column="yscs"
 * @return Returns the yscs.
 */
 public Long getYscs() {
    return yscs;
 }

 public void setYscs(Long yscs) {
    this.yscs = yscs;
  }

 /**
 * 
 */
 private String jgzlry;

 /**
 * @hibernate.property column="jgzlry"
 * @return Returns the jgzlry.
 */
 public String getJgzlry() {
    return jgzlry;
 }

 public void setJgzlry(String jgzlry) {
    this.jgzlry = jgzlry;
  }

 /**
 * 
 */
 private String clhxry;

 /**
 * @hibernate.property column="clhxry"
 * @return Returns the clhxry.
 */
 public String getClhxry() {
    return clhxry;
 }

 public void setClhxry(String clhxry) {
    this.clhxry = clhxry;
  }

 /**
 * 
 */
 private String shjdw;

 /**
 * @hibernate.property column="shjdw"
 * @return Returns the shjdw.
 */
 public String getShjdw() {
    return shjdw;
 }

 public void setShjdw(String shjdw) {
    this.shjdw = shjdw;
  }

 /**
 * 
 */
 private Long dnljtzwce;

 /**
 * @hibernate.property column="dnljtzwce"
 * @return Returns the dnljtzwce.
 */
 public Long getDnljtzwce() {
    return dnljtzwce;
 }

 public void setDnljtzwce(Long dnljtzwce) {
    this.dnljtzwce = dnljtzwce;
  }

 /**
 * 
 */
 private Long ljtzwce;

 /**
 * @hibernate.property column="ljtzwce"
 * @return Returns the ljtzwce.
 */
 public Long getLjtzwce() {
    return ljtzwce;
 }

 public void setLjtzwce(Long ljtzwce) {
    this.ljtzwce = ljtzwce;
  }

 /**
 * 
 */
 private Long wcbfb;

 /**
 * @hibernate.property column="wcbfb"
 * @return Returns the wcbfb.
 */
 public Long getWcbfb() {
    return wcbfb;
 }

 public void setWcbfb(Long wcbfb) {
    this.wcbfb = wcbfb;
  }

 /**
 * 
 */
 private Date sjtzqgq;

 /**
 * @hibernate.property column="sjtzqgq"
 * @return Returns the sjtzqgq.
 */
 public Date getSjtzqgq() {
    return sjtzqgq;
 }

 public void setSjtzqgq(Date sjtzqgq) {
    this.sjtzqgq = sjtzqgq;
  }

 /**
 * 
 */
 private Date sgtzqgq;

 /**
 * @hibernate.property column="sgtzqgq"
 * @return Returns the sgtzqgq.
 */
 public Date getSgtzqgq() {
    return sgtzqgq;
 }

 public void setSgtzqgq(Date sgtzqgq) {
    this.sgtzqgq = sgtzqgq;
  }

 /**
 * 
 */
 private Date cstjsj;

 /**
 * @hibernate.property column="cstjsj"
 * @return Returns the cstjsj.
 */
 public Date getCstjsj() {
    return cstjsj;
 }

 public void setCstjsj(Date cstjsj) {
    this.cstjsj = cstjsj;
  }

 /**
 * 
 */
 private String xqbm;

 /**
 * @hibernate.property column="xqbm"
 * @return Returns the xqbm.
 */
 public String getXqbm() {
    return xqbm;
 }

 public void setXqbm(String xqbm) {
    this.xqbm = xqbm;
  }

 /**
 * 
 */
 private String xmgly;

 /**
 * @hibernate.property column="xmgly"
 * @return Returns the xmgly.
 */
 public String getXmgly() {
    return xmgly;
 }

 public void setXmgly(String xmgly) {
    this.xmgly = xmgly;
  }

 /**
 * 
 */
 private Long xm_id;

 /**
 * @hibernate.property column="xm_id"
 * @return Returns the xm_id.
 */
 public Long getXm_id() {
    return xm_id;
 }

 public void setXm_id(Long xm_id) {
    this.xm_id = xm_id;
  }

 /**
 * 
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
 * 
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
 * 
 */
 private String gcsm;

 /**
 * @hibernate.property column="gcsm"
 * @return Returns the gcsm.
 */
 public String getGcsm() {
    return gcsm;
 }

 public void setGcsm(String gcsm) {
    this.gcsm = gcsm;
  }

 /**
 * 
 */
 private String gcjd;

 /**
 * @hibernate.property column="gcjd"
 * @return Returns the gcjd.
 */
 public String getGcjd() {
    return gcjd;
 }

 public void setGcjd(String gcjd) {
    this.gcjd = gcjd;
  }

 /**
 * 
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
 * 
 */
 private Long sgzldf;

 /**
 * @hibernate.property column="sgzldf"
 * @return Returns the sgzldf.
 */
 public Long getSgzldf() {
    return sgzldf;
 }

 public void setSgzldf(Long sgzldf) {
    this.sgzldf = sgzldf;
  }

 /**
 * 
 */
 private Long clgldf;

 /**
 * @hibernate.property column="clgldf"
 * @return Returns the clgldf.
 */
 public Long getClgldf() {
    return clgldf;
 }

 public void setClgldf(Long clgldf) {
    this.clgldf = clgldf;
  }

 /**
 * 
 */
 private Long gqdf;

 /**
 * @hibernate.property column="gqdf"
 * @return Returns the gqdf.
 */
 public Long getGqdf() {
    return gqdf;
 }

 public void setGqdf(Long gqdf) {
    this.gqdf = gqdf;
  }

 /**
 * 
 */
 private Long whzldf;

 /**
 * @hibernate.property column="whzldf"
 * @return Returns the whzldf.
 */
 public Long getWhzldf() {
    return whzldf;
 }

 public void setWhzldf(Long whzldf) {
    this.whzldf = whzldf;
  }

 /**
 * 
 */
 private Long kcfksj;

 /**
 * @hibernate.property column="kcfksj"
 * @return Returns the kcfksj.
 */
 public Long getKcfksj() {
    return kcfksj;
 }

 public void setKcfksj(Long kcfksj) {
    this.kcfksj = kcfksj;
  }

 /**
 * 
 */
 private Long sjsx;

 /**
 * @hibernate.property column="sjsx"
 * @return Returns the sjsx.
 */
 public Long getSjsx() {
    return sjsx;
 }

 public void setSjsx(Long sjsx) {
    this.sjsx = sjsx;
  }

 /**
 * 
 */
 private Long jdtbzq;

 /**
 * @hibernate.property column="jdtbzq"
 * @return Returns the jdtbzq.
 */
 public Long getJdtbzq() {
    return jdtbzq;
 }

 public void setJdtbzq(Long jdtbzq) {
    this.jdtbzq = jdtbzq;
  }

 /**
 * 
 */
 private Long rztbzq;

 /**
 * @hibernate.property column="rztbzq"
 * @return Returns the rztbzq.
 */
 public Long getRztbzq() {
    return rztbzq;
 }

 public void setRztbzq(Long rztbzq) {
    this.rztbzq = rztbzq;
  }

 /**
 * 
 */
 private String sjyq;

 /**
 * @hibernate.property column="sjyq"
 * @return Returns the sjyq.
 */
 public String getSjyq() {
    return sjyq;
 }

 public void setSjyq(String sjyq) {
    this.sjyq = sjyq;
  }

 /**
 * 
 */
 private String sjfk;

 /**
 * @hibernate.property column="sjfk"
 * @return Returns the sjfk.
 */
 public String getSjfk() {
    return sjfk;
 }

 public void setSjfk(String sjfk) {
    this.sjfk = sjfk;
  }

 /**
 * 
 */
 private String sgyq;

 /**
 * @hibernate.property column="sgyq"
 * @return Returns the sgyq.
 */
 public String getSgyq() {
    return sgyq;
 }

 public void setSgyq(String sgyq) {
    this.sgyq = sgyq;
  }

 /**
 * 
 */
 private String sgfk;

 /**
 * @hibernate.property column="sgfk"
 * @return Returns the sgfk.
 */
 public String getSgfk() {
    return sgfk;
 }

 public void setSgfk(String sgfk) {
    this.sgfk = sgfk;
  }

 /**
 * 
 */
 private String jlyq;

 /**
 * @hibernate.property column="jlyq"
 * @return Returns the jlyq.
 */
 public String getJlyq() {
    return jlyq;
 }

 public void setJlyq(String jlyq) {
    this.jlyq = jlyq;
  }

 /**
 * 
 */
 private String jlfk;

 /**
 * @hibernate.property column="jlfk"
 * @return Returns the jlfk.
 */
 public String getJlfk() {
    return jlfk;
 }

 public void setJlfk(String jlfk) {
    this.jlfk = jlfk;
  }

 /**
 * 
 */
 private Date yqwcsj;

 /**
 * @hibernate.property column="yqwcsj"
 * @return Returns the yqwcsj.
 */
 public Date getYqwcsj() {
    return yqwcsj;
 }

 public void setYqwcsj(Date yqwcsj) {
    this.yqwcsj = yqwcsj;
  }

 /**
 * 
 */
 private String xmbh;

 /**
 * @hibernate.property column="xmbh"
 * @return Returns the xmbh.
 */
 public String getXmbh() {
    return xmbh;
 }

 public void setXmbh(String xmbh) {
    this.xmbh = xmbh;
  }

 /**
 * 
 */
 private String xmmc;

 /**
 * @hibernate.property column="xmmc"
 * @return Returns the xmmc.
 */
 public String getXmmc() {
    return xmmc;
 }

 public void setXmmc(String xmmc) {
    this.xmmc = xmmc;
  }

 /**
 * 
 */
 private String qkdl;

 /**
 * @hibernate.property column="qkdl"
 * @return Returns the qkdl.
 */
 public String getQkdl() {
    return qkdl;
 }

 public void setQkdl(String qkdl) {
    this.qkdl = qkdl;
  }

 /**
 * 
 */
 private String qkxl;

 /**
 * @hibernate.property column="qkxl"
 * @return Returns the qkxl.
 */
 public String getQkxl() {
    return qkxl;
 }

 public void setQkxl(String qkxl) {
    this.qkxl = qkxl;
  }

 /**
 * 
 */
 private Long lxje;

 /**
 * @hibernate.property column="lxje"
 * @return Returns the lxje.
 */
 public Long getLxje() {
    return lxje;
 }

 public void setLxje(Long lxje) {
    this.lxje = lxje;
  }

 /**
 * 
 */
 private Long ss_jggr;

 /**
 * @hibernate.property column="ss_jggr"
 * @return Returns the ss_jggr.
 */
 public Long getSs_jggr() {
    return ss_jggr;
 }

 public void setSs_jggr(Long ss_jggr) {
    this.ss_jggr = ss_jggr;
  }

 /**
 * 
 */
 private Long ss_pggr;

 /**
 * @hibernate.property column="ss_pggr"
 * @return Returns the ss_pggr.
 */
 public Long getSs_pggr() {
    return ss_pggr;
 }

 public void setSs_pggr(Long ss_pggr) {
    this.ss_pggr = ss_pggr;
  }

 /**
 * 
 */
 private Long ss_rgf;

 /**
 * @hibernate.property column="ss_rgf"
 * @return Returns the ss_rgf.
 */
 public Long getSs_rgf() {
    return ss_rgf;
 }

 public void setSs_rgf(Long ss_rgf) {
    this.ss_rgf = ss_rgf;
  }

 /**
 * 
 */
 private Long ss_clf;

 /**
 * @hibernate.property column="ss_clf"
 * @return Returns the ss_clf.
 */
 public Long getSs_clf() {
    return ss_clf;
 }

 public void setSs_clf(Long ss_clf) {
    this.ss_clf = ss_clf;
  }

 /**
 * 
 */
 private Long ss_clf_j;

 /**
 * @hibernate.property column="ss_clf_j"
 * @return Returns the ss_clf_j.
 */
 public Long getSs_clf_j() {
    return ss_clf_j;
 }

 public void setSs_clf_j(Long ss_clf_j) {
    this.ss_clf_j = ss_clf_j;
  }

 /**
 * 
 */
 private Long ss_jxf;

 /**
 * @hibernate.property column="ss_jxf"
 * @return Returns the ss_jxf.
 */
 public Long getSs_jxf() {
    return ss_jxf;
 }

 public void setSs_jxf(Long ss_jxf) {
    this.ss_jxf = ss_jxf;
  }

 /**
 * 
 */
 private Long ss_ybf;

 /**
 * @hibernate.property column="ss_ybf"
 * @return Returns the ss_ybf.
 */
 public Long getSs_ybf() {
    return ss_ybf;
 }

 public void setSs_ybf(Long ss_ybf) {
    this.ss_ybf = ss_ybf;
  }

 /**
 * 
 */
 private Long ss_qtf;

 /**
 * @hibernate.property column="ss_qtf"
 * @return Returns the ss_qtf.
 */
 public Long getSs_qtf() {
    return ss_qtf;
 }

 public void setSs_qtf(Long ss_qtf) {
    this.ss_qtf = ss_qtf;
  }

 /**
 * 
 */
 private Long ss_sgf;

 /**
 * @hibernate.property column="ss_sgf"
 * @return Returns the ss_sgf.
 */
 public Long getSs_sgf() {
    return ss_sgf;
 }

 public void setSs_sgf(Long ss_sgf) {
    this.ss_sgf = ss_sgf;
  }

 /**
 * 
 */
 private Long ss_jlf;

 /**
 * @hibernate.property column="ss_jlf"
 * @return Returns the ss_jlf.
 */
 public Long getSs_jlf() {
    return ss_jlf;
 }

 public void setSs_jlf(Long ss_jlf) {
    this.ss_jlf = ss_jlf;
  }

 /**
 * 
 */
 private Long ss_sjf;

 /**
 * @hibernate.property column="ss_sjf"
 * @return Returns the ss_sjf.
 */
 public Long getSs_sjf() {
    return ss_sjf;
 }

 public void setSs_sjf(Long ss_sjf) {
    this.ss_sjf = ss_sjf;
  }

 /**
 * 
 */
 private Long ss_je;

 /**
 * @hibernate.property column="ss_je"
 * @return Returns the ss_je.
 */
 public Long getSs_je() {
    return ss_je;
 }

 public void setSs_je(Long ss_je) {
    this.ss_je = ss_je;
  }

 /**
 * 
 */
 private Long cs_jggr;

 /**
 * @hibernate.property column="cs_jggr"
 * @return Returns the cs_jggr.
 */
 public Long getCs_jggr() {
    return cs_jggr;
 }

 public void setCs_jggr(Long cs_jggr) {
    this.cs_jggr = cs_jggr;
  }

 /**
 * 
 */
 private Long cs_pggr;

 /**
 * @hibernate.property column="cs_pggr"
 * @return Returns the cs_pggr.
 */
 public Long getCs_pggr() {
    return cs_pggr;
 }

 public void setCs_pggr(Long cs_pggr) {
    this.cs_pggr = cs_pggr;
  }

 /**
 * 
 */
 private Long cs_rgf;

 /**
 * @hibernate.property column="cs_rgf"
 * @return Returns the cs_rgf.
 */
 public Long getCs_rgf() {
    return cs_rgf;
 }

 public void setCs_rgf(Long cs_rgf) {
    this.cs_rgf = cs_rgf;
  }

 /**
 * 
 */
 private Long cs_clf;

 /**
 * @hibernate.property column="cs_clf"
 * @return Returns the cs_clf.
 */
 public Long getCs_clf() {
    return cs_clf;
 }

 public void setCs_clf(Long cs_clf) {
    this.cs_clf = cs_clf;
  }

 /**
 * 
 */
 private Long cs_clf_j;

 /**
 * @hibernate.property column="cs_clf_j"
 * @return Returns the cs_clf_j.
 */
 public Long getCs_clf_j() {
    return cs_clf_j;
 }

 public void setCs_clf_j(Long cs_clf_j) {
    this.cs_clf_j = cs_clf_j;
  }

 /**
 * 
 */
 private Long cs_jxf;

 /**
 * @hibernate.property column="cs_jxf"
 * @return Returns the cs_jxf.
 */
 public Long getCs_jxf() {
    return cs_jxf;
 }

 public void setCs_jxf(Long cs_jxf) {
    this.cs_jxf = cs_jxf;
  }

 /**
 * 
 */
 private Long cs_ybf;

 /**
 * @hibernate.property column="cs_ybf"
 * @return Returns the cs_ybf.
 */
 public Long getCs_ybf() {
    return cs_ybf;
 }

 public void setCs_ybf(Long cs_ybf) {
    this.cs_ybf = cs_ybf;
  }

 /**
 * 
 */
 private Long cs_qtf;

 /**
 * @hibernate.property column="cs_qtf"
 * @return Returns the cs_qtf.
 */
 public Long getCs_qtf() {
    return cs_qtf;
 }

 public void setCs_qtf(Long cs_qtf) {
    this.cs_qtf = cs_qtf;
  }

 /**
 * 
 */
 private Long cs_sgf;

 /**
 * @hibernate.property column="cs_sgf"
 * @return Returns the cs_sgf.
 */
 public Long getCs_sgf() {
    return cs_sgf;
 }

 public void setCs_sgf(Long cs_sgf) {
    this.cs_sgf = cs_sgf;
  }

 /**
 * 
 */
 private Long cs_jlf;

 /**
 * @hibernate.property column="cs_jlf"
 * @return Returns the cs_jlf.
 */
 public Long getCs_jlf() {
    return cs_jlf;
 }

 public void setCs_jlf(Long cs_jlf) {
    this.cs_jlf = cs_jlf;
  }

 /**
 * 
 */
 private Long cs_sjf;

 /**
 * @hibernate.property column="cs_sjf"
 * @return Returns the cs_sjf.
 */
 public Long getCs_sjf() {
    return cs_sjf;
 }

 public void setCs_sjf(Long cs_sjf) {
    this.cs_sjf = cs_sjf;
  }

 /**
 * 
 */
 private Long cs_je;

 /**
 * @hibernate.property column="cs_je"
 * @return Returns the cs_je.
 */
 public Long getCs_je() {
    return cs_je;
 }

 public void setCs_je(Long cs_je) {
    this.cs_je = cs_je;
  }

 /**
 * 
 */
 private Long sd_jggr;

 /**
 * @hibernate.property column="sd_jggr"
 * @return Returns the sd_jggr.
 */
 public Long getSd_jggr() {
    return sd_jggr;
 }

 public void setSd_jggr(Long sd_jggr) {
    this.sd_jggr = sd_jggr;
  }

 /**
 * 
 */
 private Long sd_pggr;

 /**
 * @hibernate.property column="sd_pggr"
 * @return Returns the sd_pggr.
 */
 public Long getSd_pggr() {
    return sd_pggr;
 }

 public void setSd_pggr(Long sd_pggr) {
    this.sd_pggr = sd_pggr;
  }

 /**
 * 
 */
 private Long sd_rgf;

 /**
 * @hibernate.property column="sd_rgf"
 * @return Returns the sd_rgf.
 */
 public Long getSd_rgf() {
    return sd_rgf;
 }

 public void setSd_rgf(Long sd_rgf) {
    this.sd_rgf = sd_rgf;
  }

 /**
 * 
 */
 private Long sd_clf;

 /**
 * @hibernate.property column="sd_clf"
 * @return Returns the sd_clf.
 */
 public Long getSd_clf() {
    return sd_clf;
 }

 public void setSd_clf(Long sd_clf) {
    this.sd_clf = sd_clf;
  }

 /**
 * 
 */
 private Long sd_clf_j;

 /**
 * @hibernate.property column="sd_clf_j"
 * @return Returns the sd_clf_j.
 */
 public Long getSd_clf_j() {
    return sd_clf_j;
 }

 public void setSd_clf_j(Long sd_clf_j) {
    this.sd_clf_j = sd_clf_j;
  }

 /**
 * 
 */
 private Long sd_jxf;

 /**
 * @hibernate.property column="sd_jxf"
 * @return Returns the sd_jxf.
 */
 public Long getSd_jxf() {
    return sd_jxf;
 }

 public void setSd_jxf(Long sd_jxf) {
    this.sd_jxf = sd_jxf;
  }

 /**
 * 
 */
 private Long sd_ybf;

 /**
 * @hibernate.property column="sd_ybf"
 * @return Returns the sd_ybf.
 */
 public Long getSd_ybf() {
    return sd_ybf;
 }

 public void setSd_ybf(Long sd_ybf) {
    this.sd_ybf = sd_ybf;
  }

 /**
 * 
 */
 private Long sd_qtf;

 /**
 * @hibernate.property column="sd_qtf"
 * @return Returns the sd_qtf.
 */
 public Long getSd_qtf() {
    return sd_qtf;
 }

 public void setSd_qtf(Long sd_qtf) {
    this.sd_qtf = sd_qtf;
  }

 /**
 * 
 */
 private Long sd_sgf;

 /**
 * @hibernate.property column="sd_sgf"
 * @return Returns the sd_sgf.
 */
 public Long getSd_sgf() {
    return sd_sgf;
 }

 public void setSd_sgf(Long sd_sgf) {
    this.sd_sgf = sd_sgf;
  }

 /**
 * 
 */
 private Long sd_jlf;

 /**
 * @hibernate.property column="sd_jlf"
 * @return Returns the sd_jlf.
 */
 public Long getSd_jlf() {
    return sd_jlf;
 }

 public void setSd_jlf(Long sd_jlf) {
    this.sd_jlf = sd_jlf;
  }

 /**
 * 
 */
 private Long sd_sjf;

 /**
 * @hibernate.property column="sd_sjf"
 * @return Returns the sd_sjf.
 */
 public Long getSd_sjf() {
    return sd_sjf;
 }

 public void setSd_sjf(Long sd_sjf) {
    this.sd_sjf = sd_sjf;
  }

 /**
 * 
 */
 private Long sd_je;

 /**
 * @hibernate.property column="sd_je"
 * @return Returns the sd_je.
 */
 public Long getSd_je() {
    return sd_je;
 }

 public void setSd_je(Long sd_je) {
    this.sd_je = sd_je;
  }

 /**
 * 
 */
 private String xmsm;

 /**
 * @hibernate.property column="xmsm"
 * @return Returns the xmsm.
 */
 public String getXmsm() {
    return xmsm;
 }

 public void setXmsm(String xmsm) {
    this.xmsm = xmsm;
  }

 /**
 * 
 */
 private String lxgly;

 /**
 * @hibernate.property column="lxgly"
 * @return Returns the lxgly.
 */
 public String getLxgly() {
    return lxgly;
 }

 public void setLxgly(String lxgly) {
    this.lxgly = lxgly;
  }

 /**
 * 
 */
 private String shjry;

 /**
 * @hibernate.property column="shjry"
 * @return Returns the shjry.
 */
 public String getShjry() {
    return shjry;
 }

 public void setShjry(String shjry) {
    this.shjry = shjry;
  }

 /**
 * 
 */
 private String fsry;

 /**
 * @hibernate.property column="fsry"
 * @return Returns the fsry.
 */
 public String getFsry() {
    return fsry;
 }

 public void setFsry(String fsry) {
    this.fsry = fsry;
  }

 /**
 * 
 */
 private String shjbh;

 /**
 * @hibernate.property column="shjbh"
 * @return Returns the shjbh.
 */
 public String getShjbh() {
    return shjbh;
 }

 public void setShjbh(String shjbh) {
    this.shjbh = shjbh;
  }

 /**
 * 
 */
 private String sjshjbh;

 /**
 * @hibernate.property column="sjshjbh"
 * @return Returns the sjshjbh.
 */
 public String getSjshjbh() {
    return sjshjbh;
 }

 public void setSjshjbh(String sjshjbh) {
    this.sjshjbh = sjshjbh;
  }

 /**
 * 
 */
 private Date lxsj;

 /**
 * @hibernate.property column="lxsj"
 * @return Returns the lxsj.
 */
 public Date getLxsj() {
    return lxsj;
 }

 public void setLxsj(Date lxsj) {
    this.lxsj = lxsj;
  }

	/**
	 * 坐落地点
	 */
	 private String zldd;
	
	 /**
	  * 纬度
	  */
	 private String jd;
	 
	 /**
	  * 纬度
	  */
	 private String wd;
	 
	 /**
	  * 覆盖属性
	  */
	 private String fgsx;
	 
	 /**
	  * 幢数
	  */
	 private Long zs;
	 
	 /**
	  * 层数
	  */
	 private Long cs;
	 
	 /**
	  * 户数
	  */
	 private Long hs;

	 /**
	  * @hibernate.property column="zldd"
	  * @return Returns the zldd.
	  */
		public String getZldd() {
			return zldd;
		}
		
		public void setZldd(String zldd) {
			this.zldd = zldd;
		}
		
		/**
		 * @hibernate.property column="jd"
		 * @return Returns the jd.
		 */
		public String getJd() {
			return jd;
		}
		
		public void setJd(String jd) {
			this.jd = jd;
		}
		
		/**
		 * @hibernate.property column="wd"
		 * @return Returns the wd.
		 */
		public String getWd() {
			return wd;
		}
		
		public void setWd(String wd) {
			this.wd = wd;
		}
		
		/**
		 * @hibernate.property column="lxsj"
		 * @return Returns the lxsj.
		 */
		public String getFgsx() {
			return fgsx;
		}
		
		public void setFgsx(String fgsx) {
			this.fgsx = fgsx;
		}
		
		/**
		 * @hibernate.property column="zs"
		 * @return Returns the zs.
		 */
		public Long getZs() {
			return zs;
		}
		
		public void setZs(Long zs) {
			this.zs = zs;
		}
		
		/**
		 * @hibernate.property column="cs"
		 * @return Returns the cs.
		 */
		public Long getCs() {
			return cs;
		}
		
		public void setCs(Long cs) {
			this.cs = cs;
		}
		
		/**
		 * @hibernate.property column="hs"
		 * @return Returns the hs.
		 */
		public Long getHs() {
			return hs;
		}
		
		public void setHs(Long hs) {
			this.hs = hs;
		}
}
