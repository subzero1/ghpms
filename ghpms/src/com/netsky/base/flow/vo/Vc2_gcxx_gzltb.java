package com.netsky.base.flow.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Vc2_gcxx_gzltb"
 */

public class Vc2_gcxx_gzltb implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 358619373513707456L;

	/**
	 * 标识
	 */
	private Long id;

	/**
	 * @hibernate.id generator-class="assigned"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 项目ID
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
	 * 所属地区
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
	 * 需求部门
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
	 * 勘察反馈时限
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
	 * 工程类别
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
	 * 专业大类
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
	 * 专业小项
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
	 * 设计时限
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
	 * 进度填报周期
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
	 * 建设性质
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
	 * 建设地点
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
	 * 预算类型
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
	 * 日志填报周期
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
	 * 项目管理员
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
	 * 设计要求
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
	 * 设计反馈
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
	 * 施工要求
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
	 * 施工反馈
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
	 * 监理要求
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
	 * 预算金额
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
	 * 预算建安费
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
	 * 预算材料费
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
	 * 预算人工费
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
	 * 预算技工工日
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
	 * 预算普工工日
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
	 * 预算设备费
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
	 * 预算机械费
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
	 * 预算仪表费
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
	 * 预算设计费
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
	 * 预算监理费
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
	 * 仪表其它费
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
	 * 预算施工费
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
	 * 监理反馈
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
	 * 变更技工工日
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
	 * 变更普工工日
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
	 * 变更金额
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
	 * 工程说明
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
	 * 施工队
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
	 * 需求提出人
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
	 * 调配管理人员
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
	 * 设计派工人员
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
	 * 设计审核人员
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
	 * 设计人员
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
	 * 集中派工人员
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
	 * 设计负责人
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
	 * 设计负责人电话
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
	 * 施工负责人
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
	 * 施工负责人电话
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
	 * 监理工程师
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
	 * 监理工程师电话
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
	 * 需求提出时间
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
	 * 要求完成时间
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
	 * 设计派工时间
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
	 * 计划设计完成时间
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
	 * 设计提交时间
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
	 * 施工派发时间
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
	 * 计划开工时间
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
	 * 计划竣工时间
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
	 * 实际开工时间
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
	 * 实际竣工时间
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
	 * 监理派发时间
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
	 * 工程阶段
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
	 * 所属年度
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
	 * 监理日志填报周期
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
	 * 施工进度填报周期（施工单位）
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
	 * 勘察反馈周期
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
	 * 施工质量（优 良 中 差）
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
	 * 材料管理（优 良 中 差）
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
	 * 工期得分
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
	 * 验收次数
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
	 * 维护资料（优 良 中 差）
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
	 * 竣工资料岗
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
	 * 材料核销人员
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
	 * 外协审计单位
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
	 * 当年累计投资完成额
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
	 * 累计投资完成额
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
	 * 施工进度百分比（*工作量填报）
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
	 * 设计调整前工期
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
	 * 施工调整前工期
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
	 * 初审提交时间
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
	 * 手动派工原因
	 */
	private String sdpgyy;

	/**
	 * @hibernate.property column="sdpgyy"
	 * @return Returns the sdpgyy.
	 */
	public String getSdpgyy() {
		return sdpgyy;
	}

	/**
	 * @param sdpgyy
	 *            The sdpgyy to set.
	 */
	public void setSdpgyy(String sdpgyy) {
		this.sdpgyy = sdpgyy;
	}

	/**
	 * 填报数量
	 */
	private Long tbsl;

	/**
	 * @hibernate.property column="tbsl"
	 * @return Returns the tbsl.
	 */
	public Long getTbsl() {
		return tbsl;
	}

	public void setTbsl(Long tbsl) {
		this.tbsl = tbsl;
	}

	/**
	 * 填报日期
	 */
	private Date tbrq;

	/**
	 * @hibernate.property column="tbrq"
	 * @return Returns the tbrq.
	 */
	public Date getTbrq() {
		return tbrq;
	}

	public void setTbrq(Date tbrq) {
		this.tbrq = tbrq;
	}

	/**
	 * 填报人
	 */
	private String tbr;

	/**
	 * @hibernate.property column="tbr"
	 * @return Returns the tbr.
	 */
	public String getTbr() {
		return tbr;
	}

	public void setTbr(String tbr) {
		this.tbr = tbr;
	}

	/**
	 * 时间进度
	 */
	private Double sjjd;

	/**
	 * @hibernate.property column="sjjd"
	 * @return Returns the sjjd.
	 */
	public Double getSjjd() {
		return sjjd;
	}

	public void setSjjd(Double sjjd) {
		this.sjjd = sjjd;
	}

	/**
	 * 填报进度
	 */
	private Double tbjd;

	/**
	 * @hibernate.property column="tbjd"
	 * @return Returns the tbjd.
	 */
	public Double getTbjd() {
		return tbjd;
	}

	public void setTbjd(Double tbjd) {
		this.tbjd = tbjd;
	}

	/**
	 * 进度描述
	 */
	private String jdms;

	/**
	 * @hibernate.property column="jdms"
	 * @return Returns the jdms.
	 */
	public String getJdms() {
		return jdms;
	}

	public void setJdms(String jdms) {
		this.jdms = jdms;
	}

	private Long gzltb_id;

	/**
	 * @hibernate.property column="gzltb_id"
	 * @return Returns the gzltb_id.
	 */
	public Long getGzltb_id() {
		return gzltb_id;
	}

	/**
	 * @param gzltb_id
	 *            The gzltb_id to set.
	 */
	public void setGzltb_id(Long gzltb_id) {
		this.gzltb_id = gzltb_id;
	}

	/**
	 * @hibernate.property column="yqgq"
	 * @return Returns the yqgq.
	 */
	private Long yqgq;

	public Long getYqgq() {
		return yqgq;
	}

	public void setYqgq(Long yqgq) {
		this.yqgq = yqgq;
	}
}
