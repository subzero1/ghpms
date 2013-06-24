package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta09_menu"
 */

public class Ta09_menu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 736990130568948864L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta09_num"
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
 private String name;

 /**
 * @hibernate.property column="name"
 * @return Returns the name.
 */
 public String getName() {
    return name;
 }

 public void setName(String name) {
    this.name = name;
  }

 /**
 * 
 */
 private String url;

 /**
 * @hibernate.property column="url"
 * @return Returns the url.
 */
 public String getUrl() {
    return url;
 }

 public void setUrl(String url) {
    this.url = url;
  }

 /**
 * 
 */
 private String target;

 /**
 * @hibernate.property column="target"
 * @return Returns the target.
 */
 public String getTarget() {
    return target;
 }

 public void setTarget(String target) {
    this.target = target;
  }

 /**
 * 
 */
 private String rel;

 /**
 * @hibernate.property column="rel"
 * @return Returns the rel.
 */
 public String getRel() {
    return rel;
 }

 public void setRel(String rel) {
    this.rel = rel;
  }

 /**
 * 
 */
 private Long up_id;

 /**
 * @hibernate.property column="up_id"
 * @return Returns the up_id.
 */
 public Long getUp_id() {
    return up_id;
 }

 public void setUp_id(Long up_id) {
    this.up_id = up_id;
  }

 /**
 * 
 */
 private Long seq;

 /**
 * @hibernate.property column="seq"
 * @return Returns the seq.
 */
 public Long getSeq() {
    return seq;
 }

 public void setSeq(Long seq) {
    this.seq = seq;
  }


}
