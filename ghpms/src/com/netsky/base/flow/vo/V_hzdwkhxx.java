package com.netsky.base.flow.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-02-20
 * @hibernate.class table="V_hzdwkhxx"
 */

public class V_hzdwkhxx implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 459597171198863232L;

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
 private String wxdw_mc;

 /**
 * @hibernate.property column="wxdw_mc"
 * @return Returns the wxdw_mc.
 */
 public String getWxdw_mc() {
    return wxdw_mc;
 }

 public void setWxdw_mc(String wxdw_mc) {
    this.wxdw_mc = wxdw_mc;
  }

 /**
 * 
 */
 private String wxdw_lb;

 /**
 * @hibernate.property column="wxdw_lb"
 * @return Returns the wxdw_lb.
 */
 public String getWxdw_lb() {
    return wxdw_lb;
 }

 public void setWxdw_lb(String wxdw_lb) {
    this.wxdw_lb = wxdw_lb;
  }

 /**
 * 
 */
 private String khry_name;

 /**
 * @hibernate.property column="khry_name"
 * @return Returns the khry_name.
 */
 public String getKhry_name() {
    return khry_name;
 }

 public void setKhry_name(String khry_name) {
    this.khry_name = khry_name;
  }

 /**
 * 
 */
 private String khyy;

 /**
 * @hibernate.property column="khyy"
 * @return Returns the khyy.
 */
 public String getKhyy() {
    return khyy;
 }

 public void setKhyy(String khyy) {
    this.khyy = khyy;
  }

 /**
 * 
 */
 private String khlb;

 /**
 * @hibernate.property column="khlb"
 * @return Returns the khlb.
 */
 public String getKhlb() {
    return khlb;
 }

 public void setKhlb(String khlb) {
    this.khlb = khlb;
  }

 /**
 * 
 */
 private Long fkje;

 /**
 * @hibernate.property column="fkje"
 * @return Returns the fkje.
 */
 public Long getFkje() {
    return fkje;
 }

 public void setFkje(Long fkje) {
    this.fkje = fkje;
  }
 
 private Long jlje;

 
 /**
 * 
 */
 private Long jkfz;

 /**
 * @hibernate.property column="jkfz"
 * @return Returns the jkfz.
 */
 public Long getJkfz() {
    return jkfz;
 }

 public void setJkfz(Long jkfz) {
    this.jkfz = jkfz;
  }

 /**
 * 
 */
 private String khjg;

 /**
 * @hibernate.property column="khjg"
 * @return Returns the khjg.
 */
 public String getKhjg() {
    return khjg;
 }

 public void setKhjg(String khjg) {
    this.khjg = khjg;
  }

 /**
 * 
 */
 private Date cjsj;

 /**
 * @hibernate.property column="cjsj"
 * @return Returns the cjsj.
 */
 public Date getCjsj() {
    return cjsj;
 }

 public void setCjsj(Date cjsj) {
    this.cjsj = cjsj;
  }

 /**
 * 
 */
 private String khdz;

 /**
 * @hibernate.property column="khdz"
 * @return Returns the khdz.
 */
 public String getKhdz() {
    return khdz;
 }

 public void setKhdz(String khdz) {
    this.khdz = khdz;
  }

 /**
 * 
 */
 private Date khsj;

 /**
 * @hibernate.property column="khsj"
 * @return Returns the khsj.
 */
 public Date getKhsj() {
    return khsj;
 }

 public void setKhsj(Date khsj) {
    this.khsj = khsj;
  }

 /**
 * 
 */
 private String khwz;

 /**
 * @hibernate.property column="khwz"
 * @return Returns the khwz.
 */
 public String getKhwz() {
    return khwz;
 }

 public void setKhwz(String khwz) {
    this.khwz = khwz;
  }

 /**
  * @hibernate.property column="jlje"
  * @return Returns the jlje.
  */
public Long getJlje() {
	return jlje;
}

public void setJlje(Long jlje) {
	this.jlje = jlje;
}

}
