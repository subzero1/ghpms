package com.netsky.base.flow.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-01-06
 * @hibernate.class table="Vc3_gcxx_jlrj"
 */

public class Vc3_gcxx_jlrj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 827210920970206592L;

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
 private Long jlrj_id;

 /**
 * @hibernate.property column="jlrj_id"
 * @return Returns the jlrj_id.
 */
 public Long getJlrj_id() {
    return jlrj_id;
 }

 public void setJlrj_id(Long jlrj_id) {
    this.jlrj_id = jlrj_id;
  }

 /**
 * 
 */
 private Long user_id;

 /**
 * @hibernate.property column="user_id"
 * @return Returns the user_id.
 */
 public Long getUser_id() {
    return user_id;
 }

 public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

 /**
 * 
 */
 private Date create_date;

 /**
 * @hibernate.property column="create_date"
 * @return Returns the create_date.
 */
 public Date getCreate_date() {
    return create_date;
 }

 public void setCreate_date(Date create_date) {
    this.create_date = create_date;
  }

 /**
 * 
 */
 private String zt;

 /**
 * @hibernate.property column="zt"
 * @return Returns the zt.
 */
 public String getZt() {
    return zt;
 }

 public void setZt(String zt) {
    this.zt = zt;
  }

 /**
 * 
 */
 private String xcms;

 /**
 * @hibernate.property column="xcms"
 * @return Returns the xcms.
 */
 public String getXcms() {
    return xcms;
 }

 public void setXcms(String xcms) {
    this.xcms = xcms;
  }


}
