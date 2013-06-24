package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta33_tszf"
 */

public class Ta33_tszf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 346914153301338816L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta33_num"
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
 * 替换前字符
 */
 private String thqzf;

 /**
 * @hibernate.property column="thqzf"
 * @return Returns the thqzf.
 */
 public String getThqzf() {
    return thqzf;
 }

 public void setThqzf(String thqzf) {
    this.thqzf = thqzf;
  }

 /**
 * 替换后字符
 */
 private String thhzf;

 /**
 * @hibernate.property column="thhzf"
 * @return Returns the thhzf.
 */
 public String getThhzf() {
    return thhzf;
 }

 public void setThhzf(String thhzf) {
    this.thhzf = thhzf;
  }


}
