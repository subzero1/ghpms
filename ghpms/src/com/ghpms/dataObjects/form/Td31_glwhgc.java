package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2014-03-12
 * @hibernate.class table="Td31_glwhgc"
 */

public class Td31_glwhgc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 162859150644168224L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td31_num"
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
 * 光缆类型4数量
 */
 private Long gllx4_sl;

 /**
 * @hibernate.property column="gllx4_sl"
 * @return Returns the gllx4_sl.
 */
 public Long getGllx4_sl() {
    return gllx4_sl;
 }

 public void setGllx4_sl(Long gllx4_sl) {
    this.gllx4_sl = gllx4_sl;
  }

 /**
 * 光缆类型5[选项]
 */
 private String gllx5;

 /**
 * @hibernate.property column="gllx5"
 * @return Returns the gllx5.
 */
 public String getGllx5() {
    return gllx5;
 }

 public void setGllx5(String gllx5) {
    this.gllx5 = gllx5;
  }

 /**
 * 光缆类型5数量
 */
 private Long gllx5_sl;

 /**
 * @hibernate.property column="gllx5_sl"
 * @return Returns the gllx5_sl.
 */
 public Long getGllx5_sl() {
    return gllx5_sl;
 }

 public void setGllx5_sl(Long gllx5_sl) {
    this.gllx5_sl = gllx5_sl;
  }

 /**
 * 备注（延误抢修情况说明）
 */
 private String bz_ywsm;

 /**
 * @hibernate.property column="bz_ywsm"
 * @return Returns the bz_ywsm.
 */
 public String getBz_ywsm() {
    return bz_ywsm;
 }

 public void setBz_ywsm(String bz_ywsm) {
    this.bz_ywsm = bz_ywsm;
  }

 /**
 * 歌华工号
 */
 private String ghgh;

 /**
 * @hibernate.property column="ghgh"
 * @return Returns the ghgh.
 */
 public String getGhgh() {
    return ghgh;
 }

 public void setGhgh(String ghgh) {
    this.ghgh = ghgh;
  }

 /**
 * 对下结算工号
 */
 private String dxjsgh;

 /**
 * @hibernate.property column="dxjsgh"
 * @return Returns the dxjsgh.
 */
 public String getDxjsgh() {
    return dxjsgh;
 }

 public void setDxjsgh(String dxjsgh) {
    this.dxjsgh = dxjsgh;
  }

 /**
 * 反馈工单时间[yyyy-MM-dd HH:mm:ss]
 */
 private Date fksj2;

 /**
 * @hibernate.property column="fksj2"
 * @return Returns the fksj2.
 */
 public Date getFksj2() {
    return fksj2;
 }

 public void setFksj2(Date fksj2) {
    this.fksj2 = fksj2;
  }

 /**
 * 紧急反馈时间[yyyy-MM-dd HH:mm:ss]
 */
 private Date fksj3;

 /**
 * @hibernate.property column="fksj3"
 * @return Returns the fksj3.
 */
 public Date getFksj3() {
    return fksj3;
 }

 public void setFksj3(Date fksj3) {
    this.fksj3 = fksj3;
  }

 /**
 * 反馈调度时间[yyyy-MM-dd HH:mm:ss]
 */
 private Date fksj1;

 /**
 * @hibernate.property column="fksj1"
 * @return Returns the fksj1.
 */
 public Date getFksj1() {
    return fksj1;
 }

 public void setFksj1(Date fksj1) {
    this.fksj1 = fksj1;
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
 * 工单号
 */
 private String gdh;

 /**
 * @hibernate.property column="gdh"
 * @return Returns the gdh.
 */
 public String getGdh() {
    return gdh;
 }

 public void setGdh(String gdh) {
    this.gdh = gdh;
  }

 /**
 * 抢修类型[选项]
 */
 private String qxlx;

 /**
 * @hibernate.property column="qxlx"
 * @return Returns the qxlx.
 */
 public String getQxlx() {
    return qxlx;
 }

 public void setQxlx(String qxlx) {
    this.qxlx = qxlx;
  }

 /**
 * 报修日期
 */
 private Date bxrq;

 /**
 * @hibernate.property column="bxrq"
 * @return Returns the bxrq.
 */
 public Date getBxrq() {
    return bxrq;
 }

 public void setBxrq(Date bxrq) {
    this.bxrq = bxrq;
  }

 /**
 * 报修时间[yyyy-MM-dd HH:mm:ss]
 */
 private Date bxsj;

 /**
 * @hibernate.property column="bxsj"
 * @return Returns the bxsj.
 */
 public Date getBxsj() {
    return bxsj;
 }

 public void setBxsj(Date bxsj) {
    this.bxsj = bxsj;
  }

 /**
 * 信息来源[选项]
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
 * 客服员[人员]
 */
 private String kfy;

 /**
 * @hibernate.property column="kfy"
 * @return Returns the kfy.
 */
 public String getKfy() {
    return kfy;
 }

 public void setKfy(String kfy) {
    this.kfy = kfy;
  }

 /**
 * 报修地点
 */
 private String bxdd;

 /**
 * @hibernate.property column="bxdd"
 * @return Returns the bxdd.
 */
 public String getBxdd() {
    return bxdd;
 }

 public void setBxdd(String bxdd) {
    this.bxdd = bxdd;
  }

 /**
 * 报修原因
 */
 private String bxyy;

 /**
 * @hibernate.property column="bxyy"
 * @return Returns the bxyy.
 */
 public String getBxyy() {
    return bxyy;
 }

 public void setBxyy(String bxyy) {
    this.bxyy = bxyy;
  }

 /**
 * 光缆抢修单位[选项]
 */
 private String glqxdw;

 /**
 * @hibernate.property column="glqxdw"
 * @return Returns the glqxdw.
 */
 public String getGlqxdw() {
    return glqxdw;
 }

 public void setGlqxdw(String glqxdw) {
    this.glqxdw = glqxdw;
  }

 /**
 * 数据点抢修单位[选项]
 */
 private String sjdqxdw;

 /**
 * @hibernate.property column="sjdqxdw"
 * @return Returns the sjdqxdw.
 */
 public String getSjdqxdw() {
    return sjdqxdw;
 }

 public void setSjdqxdw(String sjdqxdw) {
    this.sjdqxdw = sjdqxdw;
  }

 /**
 * 维护站负责人[人员]
 */
 private String whzfzr;

 /**
 * @hibernate.property column="whzfzr"
 * @return Returns the whzfzr.
 */
 public String getWhzfzr() {
    return whzfzr;
 }

 public void setWhzfzr(String whzfzr) {
    this.whzfzr = whzfzr;
  }

 /**
 * 起始时间[yyyy-MM-dd HH:mm:ss]
 */
 private Date qssj;

 /**
 * @hibernate.property column="qssj"
 * @return Returns the qssj.
 */
 public Date getQssj() {
    return qssj;
 }

 public void setQssj(Date qssj) {
    this.qssj = qssj;
  }

 /**
 * 终止时间[yyyy-MM-dd HH:mm:ss]
 */
 private Date zzsj;

 /**
 * @hibernate.property column="zzsj"
 * @return Returns the zzsj.
 */
 public Date getZzsj() {
    return zzsj;
 }

 public void setZzsj(Date zzsj) {
    this.zzsj = zzsj;
  }

 /**
 * 到现场时间[yyyy-MM-dd HH:mm:ss]
 */
 private Date dxcsj;

 /**
 * @hibernate.property column="dxcsj"
 * @return Returns the dxcsj.
 */
 public Date getDxcsj() {
    return dxcsj;
 }

 public void setDxcsj(Date dxcsj) {
    this.dxcsj = dxcsj;
  }

 /**
 * 历时
 */
 private String ls;

 /**
 * @hibernate.property column="ls"
 * @return Returns the ls.
 */
 public String getLs() {
    return ls;
 }

 public void setLs(String ls) {
    this.ls = ls;
  }

 /**
 * 项目负责人
 */
 private String xmfzr;

 /**
 * @hibernate.property column="xmfzr"
 * @return Returns the xmfzr.
 */
 public String getXmfzr() {
    return xmfzr;
 }

 public void setXmfzr(String xmfzr) {
    this.xmfzr = xmfzr;
  }

 /**
 * 出险地点
 */
 private String cxdd;

 /**
 * @hibernate.property column="cxdd"
 * @return Returns the cxdd.
 */
 public String getCxdd() {
    return cxdd;
 }

 public void setCxdd(String cxdd) {
    this.cxdd = cxdd;
  }

 /**
 * 出险原因
 */
 private String cxyy;

 /**
 * @hibernate.property column="cxyy"
 * @return Returns the cxyy.
 */
 public String getCxyy() {
    return cxyy;
 }

 public void setCxyy(String cxyy) {
    this.cxyy = cxyy;
  }

 /**
 * 抢修内容
 */
 private String qxnr;

 /**
 * @hibernate.property column="qxnr"
 * @return Returns the qxnr.
 */
 public String getQxnr() {
    return qxnr;
 }

 public void setQxnr(String qxnr) {
    this.qxnr = qxnr;
  }

 /**
 * 是否反馈调度中心[选项][XOR]
 */
 private String sffkddzx;

 /**
 * @hibernate.property column="sffkddzx"
 * @return Returns the sffkddzx.
 */
 public String getSffkddzx() {
    return sffkddzx;
 }

 public void setSffkddzx(String sffkddzx) {
    this.sffkddzx = sffkddzx;
  }

 /**
 * 信号恢复时间[yyyy-MM-dd HH:mm:ss]
 */
 private Date xxhfsj;

 /**
 * @hibernate.property column="xxhfsj"
 * @return Returns the xxhfsj.
 */
 public Date getXxhfsj() {
    return xxhfsj;
 }

 public void setXxhfsj(Date xxhfsj) {
    this.xxhfsj = xxhfsj;
  }

 /**
 * 是否反馈工单[选项][XOR]
 */
 private String sffkgd;

 /**
 * @hibernate.property column="sffkgd"
 * @return Returns the sffkgd.
 */
 public String getSffkgd() {
    return sffkgd;
 }

 public void setSffkgd(String sffkgd) {
    this.sffkgd = sffkgd;
  }

 /**
 * 是否紧急[选项][XOR]
 */
 private String sfjj;

 /**
 * @hibernate.property column="sfjj"
 * @return Returns the sfjj.
 */
 public String getSfjj() {
    return sfjj;
 }

 public void setSfjj(String sfjj) {
    this.sfjj = sfjj;
  }

 /**
 * 光缆架空敷设（米）
 */
 private Double gljkfs_m;

 /**
 * @hibernate.property column="gljkfs_m"
 * @return Returns the gljkfs_m.
 */
 public Double getGljkfs_m() {
    return gljkfs_m;
 }

 public void setGljkfs_m(Double gljkfs_m) {
    this.gljkfs_m = gljkfs_m;
  }

 /**
 * 光缆管道敷设（米）
 */
 private Double glgdfs_m;

 /**
 * @hibernate.property column="glgdfs_m"
 * @return Returns the glgdfs_m.
 */
 public Double getGlgdfs_m() {
    return glgdfs_m;
 }

 public void setGlgdfs_m(Double glgdfs_m) {
    this.glgdfs_m = glgdfs_m;
  }

 /**
 * 光缆拆除（米）
 */
 private Double glcc_m;

 /**
 * @hibernate.property column="glcc_m"
 * @return Returns the glcc_m.
 */
 public Double getGlcc_m() {
    return glcc_m;
 }

 public void setGlcc_m(Double glcc_m) {
    this.glcc_m = glcc_m;
  }

 /**
 * 光缆熔接（芯）
 */
 private Long glrj_x;

 /**
 * @hibernate.property column="glrj_x"
 * @return Returns the glrj_x.
 */
 public Long getGlrj_x() {
    return glrj_x;
 }

 public void setGlrj_x(Long glrj_x) {
    this.glrj_x = glrj_x;
  }

 /**
 * 光机装调（台）
 */
 private Long glct_t;

 /**
 * @hibernate.property column="glct_t"
 * @return Returns the glct_t.
 */
 public Long getGlct_t() {
    return glct_t;
 }

 public void setGlct_t(Long glct_t) {
    this.glct_t = glct_t;
  }

 /**
 * 更换供电器（台）
 */
 private Long ghgdq_t;

 /**
 * @hibernate.property column="ghgdq_t"
 * @return Returns the ghgdq_t.
 */
 public Long getGhgdq_t() {
    return ghgdq_t;
 }

 public void setGhgdq_t(Long ghgdq_t) {
    this.ghgdq_t = ghgdq_t;
  }

 /**
 * 新立杆1[选项]
 */
 private String xlg1;

 /**
 * @hibernate.property column="xlg1"
 * @return Returns the xlg1.
 */
 public String getXlg1() {
    return xlg1;
 }

 public void setXlg1(String xlg1) {
    this.xlg1 = xlg1;
  }

 /**
 * 新立杆类型1数量
 */
 private Long xlglx1_sl;

 /**
 * @hibernate.property column="xlglx1_sl"
 * @return Returns the xlglx1_sl.
 */
 public Long getXlglx1_sl() {
    return xlglx1_sl;
 }

 public void setXlglx1_sl(Long xlglx1_sl) {
    this.xlglx1_sl = xlglx1_sl;
  }

 /**
 * 新立杆2[选项]
 */
 private String xlg2;

 /**
 * @hibernate.property column="xlg2"
 * @return Returns the xlg2.
 */
 public String getXlg2() {
    return xlg2;
 }

 public void setXlg2(String xlg2) {
    this.xlg2 = xlg2;
  }

 /**
 * 新立杆类型2数量
 */
 private Long xlglx2_sl;

 /**
 * @hibernate.property column="xlglx2_sl"
 * @return Returns the xlglx2_sl.
 */
 public Long getXlglx2_sl() {
    return xlglx2_sl;
 }

 public void setXlglx2_sl(Long xlglx2_sl) {
    this.xlglx2_sl = xlglx2_sl;
  }

 /**
 * 新打拉线（条）
 */
 private Long xdlx_t;

 /**
 * @hibernate.property column="xdlx_t"
 * @return Returns the xdlx_t.
 */
 public Long getXdlx_t() {
    return xdlx_t;
 }

 public void setXdlx_t(Long xdlx_t) {
    this.xdlx_t = xdlx_t;
  }

 /**
 * 光机类型1[选项]
 */
 private String gjlx1;

 /**
 * @hibernate.property column="gjlx1"
 * @return Returns the gjlx1.
 */
 public String getGjlx1() {
    return gjlx1;
 }

 public void setGjlx1(String gjlx1) {
    this.gjlx1 = gjlx1;
  }

 /**
 * 光机类型1数量
 */
 private Long gjlx1_sl;

 /**
 * @hibernate.property column="gjlx1_sl"
 * @return Returns the gjlx1_sl.
 */
 public Long getGjlx1_sl() {
    return gjlx1_sl;
 }

 public void setGjlx1_sl(Long gjlx1_sl) {
    this.gjlx1_sl = gjlx1_sl;
  }

 /**
 * 换上光机1型号[选项]
 */
 private String hsgj1_xh;

 /**
 * @hibernate.property column="hsgj1_xh"
 * @return Returns the hsgj1_xh.
 */
 public String getHsgj1_xh() {
    return hsgj1_xh;
 }

 public void setHsgj1_xh(String hsgj1_xh) {
    this.hsgj1_xh = hsgj1_xh;
  }

 /**
 * 换下光机1型号[选项]
 */
 private String hxgj1_xh;

 /**
 * @hibernate.property column="hxgj1_xh"
 * @return Returns the hxgj1_xh.
 */
 public String getHxgj1_xh() {
    return hxgj1_xh;
 }

 public void setHxgj1_xh(String hxgj1_xh) {
    this.hxgj1_xh = hxgj1_xh;
  }

 /**
 * 光机类型2[选项]
 */
 private String gjlx2;

 /**
 * @hibernate.property column="gjlx2"
 * @return Returns the gjlx2.
 */
 public String getGjlx2() {
    return gjlx2;
 }

 public void setGjlx2(String gjlx2) {
    this.gjlx2 = gjlx2;
  }

 /**
 * 光机类型2数量
 */
 private Long gjlx2_sl;

 /**
 * @hibernate.property column="gjlx2_sl"
 * @return Returns the gjlx2_sl.
 */
 public Long getGjlx2_sl() {
    return gjlx2_sl;
 }

 public void setGjlx2_sl(Long gjlx2_sl) {
    this.gjlx2_sl = gjlx2_sl;
  }

 /**
 * 换上光机2型号[选项]
 */
 private String hsgj2_xh;

 /**
 * @hibernate.property column="hsgj2_xh"
 * @return Returns the hsgj2_xh.
 */
 public String getHsgj2_xh() {
    return hsgj2_xh;
 }

 public void setHsgj2_xh(String hsgj2_xh) {
    this.hsgj2_xh = hsgj2_xh;
  }

 /**
 * 换下光机2型号[选项]
 */
 private String hxgj2_xh;

 /**
 * @hibernate.property column="hxgj2_xh"
 * @return Returns the hxgj2_xh.
 */
 public String getHxgj2_xh() {
    return hxgj2_xh;
 }

 public void setHxgj2_xh(String hxgj2_xh) {
    this.hxgj2_xh = hxgj2_xh;
  }

 /**
 * 供电器（新）
 */
 private Long gdq_x;

 /**
 * @hibernate.property column="gdq_x"
 * @return Returns the gdq_x.
 */
 public Long getGdq_x() {
    return gdq_x;
 }

 public void setGdq_x(Long gdq_x) {
    this.gdq_x = gdq_x;
  }

 /**
 * 供电器（旧）
 */
 private Long gdq_j;

 /**
 * @hibernate.property column="gdq_j"
 * @return Returns the gdq_j.
 */
 public Long getGdq_j() {
    return gdq_j;
 }

 public void setGdq_j(Long gdq_j) {
    this.gdq_j = gdq_j;
  }

 /**
 * 熔接盒类型1[选项]
 */
 private String rjhlx1;

 /**
 * @hibernate.property column="rjhlx1"
 * @return Returns the rjhlx1.
 */
 public String getRjhlx1() {
    return rjhlx1;
 }

 public void setRjhlx1(String rjhlx1) {
    this.rjhlx1 = rjhlx1;
  }

 /**
 * 熔接盒类型1数量

 */
 private Long rjhlx1_sl;

 /**
 * @hibernate.property column="rjhlx1_sl"
 * @return Returns the rjhlx1_sl.
 */
 public Long getRjhlx1_sl() {
    return rjhlx1_sl;
 }

 public void setRjhlx1_sl(Long rjhlx1_sl) {
    this.rjhlx1_sl = rjhlx1_sl;
  }

 /**
 * 熔接盒类型2
 */
 private String rjhlx2;

 /**
 * @hibernate.property column="rjhlx2"
 * @return Returns the rjhlx2.
 */
 public String getRjhlx2() {
    return rjhlx2;
 }

 public void setRjhlx2(String rjhlx2) {
    this.rjhlx2 = rjhlx2;
  }

 /**
 * 熔接盒类型2数量
 */
 private Long rjhlx2_sl;

 /**
 * @hibernate.property column="rjhlx2_sl"
 * @return Returns the rjhlx2_sl.
 */
 public Long getRjhlx2_sl() {
    return rjhlx2_sl;
 }

 public void setRjhlx2_sl(Long rjhlx2_sl) {
    this.rjhlx2_sl = rjhlx2_sl;
  }

 /**
 * 光纤托盘
 */
 private Long gxtp;

 /**
 * @hibernate.property column="gxtp"
 * @return Returns the gxtp.
 */
 public Long getGxtp() {
    return gxtp;
 }

 public void setGxtp(Long gxtp) {
    this.gxtp = gxtp;
  }

 /**
 * 光纤热缩管
 */
 private Long gxrsg;

 /**
 * @hibernate.property column="gxrsg"
 * @return Returns the gxrsg.
 */
 public Long getGxrsg() {
    return gxrsg;
 }

 public void setGxrsg(Long gxrsg) {
    this.gxrsg = gxrsg;
  }

 /**
 * 尾纤
 */
 private Long wx;

 /**
 * @hibernate.property column="wx"
 * @return Returns the wx.
 */
 public Long getWx() {
    return wx;
 }

 public void setWx(Long wx) {
    this.wx = wx;
  }

 /**
 * 光缆类型1[选项]
 */
 private String gllx1;

 /**
 * @hibernate.property column="gllx1"
 * @return Returns the gllx1.
 */
 public String getGllx1() {
    return gllx1;
 }

 public void setGllx1(String gllx1) {
    this.gllx1 = gllx1;
  }

 /**
 * 光缆类型1数量
 */
 private Long gllx1_sl;

 /**
 * @hibernate.property column="gllx1_sl"
 * @return Returns the gllx1_sl.
 */
 public Long getGllx1_sl() {
    return gllx1_sl;
 }

 public void setGllx1_sl(Long gllx1_sl) {
    this.gllx1_sl = gllx1_sl;
  }

 /**
 * 光缆类型2[选项]
 */
 private String gllx2;

 /**
 * @hibernate.property column="gllx2"
 * @return Returns the gllx2.
 */
 public String getGllx2() {
    return gllx2;
 }

 public void setGllx2(String gllx2) {
    this.gllx2 = gllx2;
  }

 /**
 * 光缆类型2数量
 */
 private Long gllx2_sl;

 /**
 * @hibernate.property column="gllx2_sl"
 * @return Returns the gllx2_sl.
 */
 public Long getGllx2_sl() {
    return gllx2_sl;
 }

 public void setGllx2_sl(Long gllx2_sl) {
    this.gllx2_sl = gllx2_sl;
  }

 /**
 * 光缆类型3[选项]
 */
 private String gllx3;

 /**
 * @hibernate.property column="gllx3"
 * @return Returns the gllx3.
 */
 public String getGllx3() {
    return gllx3;
 }

 public void setGllx3(String gllx3) {
    this.gllx3 = gllx3;
  }

 /**
 * 光缆类型3数量
 */
 private Long gllx3_sl;

 /**
 * @hibernate.property column="gllx3_sl"
 * @return Returns the gllx3_sl.
 */
 public Long getGllx3_sl() {
    return gllx3_sl;
 }

 public void setGllx3_sl(Long gllx3_sl) {
    this.gllx3_sl = gllx3_sl;
  }

 /**
 * 光缆类型4[选项]
 */
 private String gllx4;

 /**
 * @hibernate.property column="gllx4"
 * @return Returns the gllx4.
 */
 public String getGllx4() {
    return gllx4;
 }

 public void setGllx4(String gllx4) {
    this.gllx4 = gllx4;
  }


}
