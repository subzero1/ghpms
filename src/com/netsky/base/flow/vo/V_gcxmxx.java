package com.netsky.base.flow.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-01-06
 * @hibernate.class table="V_gcxmxx"
 */

public class V_gcxmxx implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 667254414846387968L;

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
 private String module_id;

 /**
 * @hibernate.property column="module_id"
 * @return Returns the module_id.
 */
 public String getModule_id() {
    return module_id;
 }

 public void setModule_id(String module_id) {
    this.module_id = module_id;
  }

 /**
 * 
 */
 private String objectname;

 /**
 * @hibernate.property column="objectname"
 * @return Returns the objectname.
 */
 public String getObjectname() {
    return objectname;
 }

 public void setObjectname(String objectname) {
    this.objectname = objectname;
  }


}
