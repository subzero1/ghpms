package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-08-09
 * @hibernate.class table="Td15_gdgc"
 */

public class Td15_gdgc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 762763784416371072L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td15_num"
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
 * 收到网络工程部转来任务通知书、器材表
 */
 private Date rwtzsqcb;

 /**
 * @hibernate.property column="rwtzsqcb"
 * @return Returns the rwtzsqcb.
 */
 public Date getRwtzsqcb() {
    return rwtzsqcb;
 }

 public void setRwtzsqcb(Date rwtzsqcb) {
    this.rwtzsqcb = rwtzsqcb;
  }

 /**
 * 领料部门
 */
 private String llbm;

 /**
 * @hibernate.property column="llbm"
 * @return Returns the llbm.
 */
 public String getLlbm() {
    return llbm;
 }

 public void setLlbm(String llbm) {
    this.llbm = llbm;
  }

 /**
 * 领料时间
 */
 private Date llsj;

 /**
 * @hibernate.property column="llsj"
 * @return Returns the llsj.
 */
 public Date getLlsj() {
    return llsj;
 }

 public void setLlsj(Date llsj) {
    this.llsj = llsj;
  }

 /**
 * 领料人
 */
 private String llr;

 /**
 * @hibernate.property column="llr"
 * @return Returns the llr.
 */
 public String getLlr() {
    return llr;
 }

 public void setLlr(String llr) {
    this.llr = llr;
  }

 /**
 * 发料人
 */
 private String flr;

 /**
 * @hibernate.property column="flr"
 * @return Returns the flr.
 */
 public String getFlr() {
    return flr;
 }

 public void setFlr(String flr) {
    this.flr = flr;
  }

 /**
 * 收到网络工程部转来补料器材表
 */
 private String bqcb;

 /**
 * @hibernate.property column="bqcb"
 * @return Returns the bqcb.
 */
 public String getBqcb() {
    return bqcb;
 }

 public void setBqcb(String bqcb) {
    this.bqcb = bqcb;
  }

 /**
 * 二次领料部门
 */
 private String llbm_2;

 /**
 * @hibernate.property column="llbm_2"
 * @return Returns the llbm_2.
 */
 public String getLlbm_2() {
    return llbm_2;
 }

 public void setLlbm_2(String llbm_2) {
    this.llbm_2 = llbm_2;
  }

 /**
 * 二次设计领料时间
 */
 private Date sjllsj_2;

 /**
 * @hibernate.property column="sjllsj_2"
 * @return Returns the sjllsj_2.
 */
 public Date getSjllsj_2() {
    return sjllsj_2;
 }

 public void setSjllsj_2(Date sjllsj_2) {
    this.sjllsj_2 = sjllsj_2;
  }

 /**
 * 二次领料人
 */
 private String llr_2;

 /**
 * @hibernate.property column="llr_2"
 * @return Returns the llr_2.
 */
 public String getLlr_2() {
    return llr_2;
 }

 public void setLlr_2(String llr_2) {
    this.llr_2 = llr_2;
  }

 /**
 * 二次发料人
 */
 private String flr_2;

 /**
 * @hibernate.property column="flr_2"
 * @return Returns the flr_2.
 */
 public String getFlr_2() {
    return flr_2;
 }

 public void setFlr_2(String flr_2) {
    this.flr_2 = flr_2;
  }

 /**
 * 接收采购申请表时间
 */
 private Date jscgsqbsj;

 /**
 * @hibernate.property column="jscgsqbsj"
 * @return Returns the jscgsqbsj.
 */
 public Date getJscgsqbsj() {
    return jscgsqbsj;
 }

 public void setJscgsqbsj(Date jscgsqbsj) {
    this.jscgsqbsj = jscgsqbsj;
  }

 /**
 * 申请部门
 */
 private String sqbm;

 /**
 * @hibernate.property column="sqbm"
 * @return Returns the sqbm.
 */
 public String getSqbm() {
    return sqbm;
 }

 public void setSqbm(String sqbm) {
    this.sqbm = sqbm;
  }

 /**
 * 公司领导批准时间
 */
 private Date gsldpzsj;

 /**
 * @hibernate.property column="gsldpzsj"
 * @return Returns the gsldpzsj.
 */
 public Date getGsldpzsj() {
    return gsldpzsj;
 }

 public void setGsldpzsj(Date gsldpzsj) {
    this.gsldpzsj = gsldpzsj;
  }

 /**
 * 采购器材到库时间
 */
 private Date cgqdksj;

 /**
 * @hibernate.property column="cgqdksj"
 * @return Returns the cgqdksj.
 */
 public Date getCgqdksj() {
    return cgqdksj;
 }

 public void setCgqdksj(Date cgqdksj) {
    this.cgqdksj = cgqdksj;
  }

 /**
 * 采购器材领料人
 */
 private String cgqllr;

 /**
 * @hibernate.property column="cgqllr"
 * @return Returns the cgqllr.
 */
 public String getCgqllr() {
    return cgqllr;
 }

 public void setCgqllr(String cgqllr) {
    this.cgqllr = cgqllr;
  }

 /**
 * 采购器材发料时间
 */
 private Date cgqflsj;

 /**
 * @hibernate.property column="cgqflsj"
 * @return Returns the cgqflsj.
 */
 public Date getCgqflsj() {
    return cgqflsj;
 }

 public void setCgqflsj(Date cgqflsj) {
    this.cgqflsj = cgqflsj;
  }

 /**
 * 采购器材发料人
 */
 private String cgqflr;

 /**
 * @hibernate.property column="cgqflr"
 * @return Returns the cgqflr.
 */
 public String getCgqflr() {
    return cgqflr;
 }

 public void setCgqflr(String cgqflr) {
    this.cgqflr = cgqflr;
  }

 /**
 * 施工部门完工后第一次核料时间
 */
 private Date wg_hksj1;

 /**
 * @hibernate.property column="wg_hksj1"
 * @return Returns the wg_hksj1.
 */
 public Date getWg_hksj1() {
    return wg_hksj1;
 }

 public void setWg_hksj1(Date wg_hksj1) {
    this.wg_hksj1 = wg_hksj1;
  }

 /**
 * 第一次核料歌华料是否需要补退料[选项][XOR]
 */
 private String btk_btl1;

 /**
 * @hibernate.property column="btk_btl1"
 * @return Returns the btk_btl1.
 */
 public String getBtk_btl1() {
    return btk_btl1;
 }

 public void setBtk_btl1(String btk_btl1) {
    this.btk_btl1 = btk_btl1;
  }

 /**
 * 第一次核料视宽自备料是否需要补退料[选项][XOR]
 */
 private String skzblsfxbtl_hl1;

 /**
 * @hibernate.property column="skzblsfxbtl_hl1"
 * @return Returns the skzblsfxbtl_hl1.
 */
 public String getSkzblsfxbtl_hl1() {
    return skzblsfxbtl_hl1;
 }

 public void setSkzblsfxbtl_hl1(String skzblsfxbtl_hl1) {
    this.skzblsfxbtl_hl1 = skzblsfxbtl_hl1;
  }

 /**
 * 第一次核料核料单位
 */
 private String hkdw_hl1;

 /**
 * @hibernate.property column="hkdw_hl1"
 * @return Returns the hkdw_hl1.
 */
 public String getHkdw_hl1() {
    return hkdw_hl1;
 }

 public void setHkdw_hl1(String hkdw_hl1) {
    this.hkdw_hl1 = hkdw_hl1;
  }

 /**
 * 第一次核料核料人
 */
 private String hkr_hl1;

 /**
 * @hibernate.property column="hkr_hl1"
 * @return Returns the hkr_hl1.
 */
 public String getHkr_hl1() {
    return hkr_hl1;
 }

 public void setHkr_hl1(String hkr_hl1) {
    this.hkr_hl1 = hkr_hl1;
  }

 /**
 * 第一次核料补退料人
 */
 private String btlr_hl1;

 /**
 * @hibernate.property column="btlr_hl1"
 * @return Returns the btlr_hl1.
 */
 public String getBtlr_hl1() {
    return btlr_hl1;
 }

 public void setBtlr_hl1(String btlr_hl1) {
    this.btlr_hl1 = btlr_hl1;
  }

 /**
 * 第一次核料补退料时间
 */
 private Date btlsj_hl1;

 /**
 * @hibernate.property column="btlsj_hl1"
 * @return Returns the btlsj_hl1.
 */
 public Date getBtlsj_hl1() {
    return btlsj_hl1;
 }

 public void setBtlsj_hl1(Date btlsj_hl1) {
    this.btlsj_hl1 = btlsj_hl1;
  }

 /**
 * 收到合同结算部移交验收报告、器材结算单
 */
 private Date ysbgqcjsd;

 /**
 * @hibernate.property column="ysbgqcjsd"
 * @return Returns the ysbgqcjsd.
 */
 public Date getYsbgqcjsd() {
    return ysbgqcjsd;
 }

 public void setYsbgqcjsd(Date ysbgqcjsd) {
    this.ysbgqcjsd = ysbgqcjsd;
  }

 /**
 * 二次歌华调拨料核料
 */
 private Date ghtbl_hl2;

 /**
 * @hibernate.property column="ghtbl_hl2"
 * @return Returns the ghtbl_hl2.
 */
 public Date getGhtbl_hl2() {
    return ghtbl_hl2;
 }

 public void setGhtbl_hl2(Date ghtbl_hl2) {
    this.ghtbl_hl2 = ghtbl_hl2;
  }

 /**
 * 是否二次需要补退料[选项][XOR]
 */
 private String sfxybtl2;

 /**
 * @hibernate.property column="sfxybtl2"
 * @return Returns the sfxybtl2.
 */
 public String getSfxybtl2() {
    return sfxybtl2;
 }

 public void setSfxybtl2(String sfxybtl2) {
    this.sfxybtl2 = sfxybtl2;
  }

 /**
 * 二次补退料时间
 */
 private Date sj_btl2;

 /**
 * @hibernate.property column="sj_btl2"
 * @return Returns the sj_btl2.
 */
 public Date getSj_btl2() {
    return sj_btl2;
 }

 public void setSj_btl2(Date sj_btl2) {
    this.sj_btl2 = sj_btl2;
  }

 /**
 * 二次补退料领料部门
 */
 private String llbm_btl2;

 /**
 * @hibernate.property column="llbm_btl2"
 * @return Returns the llbm_btl2.
 */
 public String getLlbm_btl2() {
    return llbm_btl2;
 }

 public void setLlbm_btl2(String llbm_btl2) {
    this.llbm_btl2 = llbm_btl2;
  }

 /**
 * 二次补退料领料人
 */
 private String llr_btl2;

 /**
 * @hibernate.property column="llr_btl2"
 * @return Returns the llr_btl2.
 */
 public String getLlr_btl2() {
    return llr_btl2;
 }

 public void setLlr_btl2(String llr_btl2) {
    this.llr_btl2 = llr_btl2;
  }

 /**
 * 二次补退料发料人
 */
 private String flr_btl2;

 /**
 * @hibernate.property column="flr_btl2"
 * @return Returns the flr_btl2.
 */
 public String getFlr_btl2() {
    return flr_btl2;
 }

 public void setFlr_btl2(String flr_btl2) {
    this.flr_btl2 = flr_btl2;
  }

 /**
 * 出具此工程最终器材施工使用量出单人
 */
 private String cjcgczzqcsgsylcdr;

 /**
 * @hibernate.property column="cjcgczzqcsgsylcdr"
 * @return Returns the cjcgczzqcsgsylcdr.
 */
 public String getCjcgczzqcsgsylcdr() {
    return cjcgczzqcsgsylcdr;
 }

 public void setCjcgczzqcsgsylcdr(String cjcgczzqcsgsylcdr) {
    this.cjcgczzqcsgsylcdr = cjcgczzqcsgsylcdr;
  }

 /**
 * 出具此工程最终器材施工使用量时间
 */
 private Date cjcgczzqcsgsylsj;

 /**
 * @hibernate.property column="cjcgczzqcsgsylsj"
 * @return Returns the cjcgczzqcsgsylsj.
 */
 public Date getCjcgczzqcsgsylsj() {
    return cjcgczzqcsgsylsj;
 }

 public void setCjcgczzqcsgsylsj(Date cjcgczzqcsgsylsj) {
    this.cjcgczzqcsgsylsj = cjcgczzqcsgsylsj;
  }

 /**
 * 歌华器材结算核对完成时间
 */
 private Date ghqcjshdwcsj;

 /**
 * @hibernate.property column="ghqcjshdwcsj"
 * @return Returns the ghqcjshdwcsj.
 */
 public Date getGhqcjshdwcsj() {
    return ghqcjshdwcsj;
 }

 public void setGhqcjshdwcsj(Date ghqcjshdwcsj) {
    this.ghqcjshdwcsj = ghqcjshdwcsj;
  }

 /**
 * 与歌华核对人
 */
 private String yghhdr;

 /**
 * @hibernate.property column="yghhdr"
 * @return Returns the yghhdr.
 */
 public String getYghhdr() {
    return yghhdr;
 }

 public void setYghhdr(String yghhdr) {
    this.yghhdr = yghhdr;
  }

 /**
 * 收到合同结算部结算单
 */
 private Date sdhtjsbjsd;

 /**
 * @hibernate.property column="sdhtjsbjsd"
 * @return Returns the sdhtjsbjsd.
 */
 public Date getSdhtjsbjsd() {
    return sdhtjsbjsd;
 }

 public void setSdhtjsbjsd(Date sdhtjsbjsd) {
    this.sdhtjsbjsd = sdhtjsbjsd;
  }

 /**
 * 完成审核合同结算部结算单
 */
 private Date wcshhtjsbjsd;

 /**
 * @hibernate.property column="wcshhtjsbjsd"
 * @return Returns the wcshhtjsbjsd.
 */
 public Date getWcshhtjsbjsd() {
    return wcshhtjsbjsd;
 }

 public void setWcshhtjsbjsd(Date wcshhtjsbjsd) {
    this.wcshhtjsbjsd = wcshhtjsbjsd;
  }

 /**
 * 结算单审核人
 */
 private String jsdshr;

 /**
 * @hibernate.property column="jsdshr"
 * @return Returns the jsdshr.
 */
 public String getJsdshr() {
    return jsdshr;
 }

 public void setJsdshr(String jsdshr) {
    this.jsdshr = jsdshr;
  }

 /**
 * 收到施工部门转交自备料结算表
 */
 private Date zbljsb_sdsgbmzj;

 /**
 * @hibernate.property column="zbljsb_sdsgbmzj"
 * @return Returns the zbljsb_sdsgbmzj.
 */
 public Date getZbljsb_sdsgbmzj() {
    return zbljsb_sdsgbmzj;
 }

 public void setZbljsb_sdsgbmzj(Date zbljsb_sdsgbmzj) {
    this.zbljsb_sdsgbmzj = zbljsb_sdsgbmzj;
  }

 /**
 * 完成审核自备料结算表
 */
 private Date zbljsb_wcsh;

 /**
 * @hibernate.property column="zbljsb_wcsh"
 * @return Returns the zbljsb_wcsh.
 */
 public Date getZbljsb_wcsh() {
    return zbljsb_wcsh;
 }

 public void setZbljsb_wcsh(Date zbljsb_wcsh) {
    this.zbljsb_wcsh = zbljsb_wcsh;
  }

 /**
 * 审核自备料结算表审核人
 */
 private String zbljsb_shshr;

 /**
 * @hibernate.property column="zbljsb_shshr"
 * @return Returns the zbljsb_shshr.
 */
 public String getZbljsb_shshr() {
    return zbljsb_shshr;
 }

 public void setZbljsb_shshr(String zbljsb_shshr) {
    this.zbljsb_shshr = zbljsb_shshr;
  }

 /**
 * 收到合同结算部撤令结算单时间
 */
 private Date sdhtjsb_cljsdsj;

 /**
 * @hibernate.property column="sdhtjsb_cljsdsj"
 * @return Returns the sdhtjsb_cljsdsj.
 */
 public Date getSdhtjsb_cljsdsj() {
    return sdhtjsb_cljsdsj;
 }

 public void setSdhtjsb_cljsdsj(Date sdhtjsb_cljsdsj) {
    this.sdhtjsb_cljsdsj = sdhtjsb_cljsdsj;
  }

 /**
 * 完成合同结算部撤令结算单时间
 */
 private Date wchtb_cljsdsj;

 /**
 * @hibernate.property column="wchtb_cljsdsj"
 * @return Returns the wchtb_cljsdsj.
 */
 public Date getWchtb_cljsdsj() {
    return wchtb_cljsdsj;
 }

 public void setWchtb_cljsdsj(Date wchtb_cljsdsj) {
    this.wchtb_cljsdsj = wchtb_cljsdsj;
  }

 /**
 * 撤令结算表审核人
 */
 private String cljsbshr;

 /**
 * @hibernate.property column="cljsbshr"
 * @return Returns the cljsbshr.
 */
 public String getCljsbshr() {
    return cljsbshr;
 }

 public void setCljsbshr(String cljsbshr) {
    this.cljsbshr = cljsbshr;
  }

 /**
 * 甲方质保金
 */
 private Long jfzbj;

 /**
 * @hibernate.property column="jfzbj"
 * @return Returns the jfzbj.
 */
 public Long getJfzbj() {
    return jfzbj;
 }

 public void setJfzbj(Long jfzbj) {
    this.jfzbj = jfzbj;
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
 * 施工单位质保金
 */
 private Long sgdwzbj;

 /**
 * @hibernate.property column="sgdwzbj"
 * @return Returns the sgdwzbj.
 */
 public Long getSgdwzbj() {
    return sgdwzbj;
 }

 public void setSgdwzbj(Long sgdwzbj) {
    this.sgdwzbj = sgdwzbj;
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


}
