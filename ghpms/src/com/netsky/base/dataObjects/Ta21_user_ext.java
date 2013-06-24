package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta21_user_ext"
 */

public class Ta21_user_ext implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 349839396551981056L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta21_num"
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
 * 标识
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
 * 界面风格（文件路径）
 */
 private String ui_style;

 /**
 * @hibernate.property column="ui_style"
 * @return Returns the ui_style.
 */
 public String getUi_style() {
    return ui_style;
 }

 public void setUi_style(String ui_style) {
    this.ui_style = ui_style;
  }

 /**
 * 主界面模块一（文件路径）(ta25 ID )	
 */
 private Long ui_m1;

 /**
 * @hibernate.property column="ui_m1"
 * @return Returns the ui_m1.
 */
 public Long getUi_m1() {
    return ui_m1;
 }

 public void setUi_m1(Long ui_m1) {
    this.ui_m1 = ui_m1;
  }

 /**
 * 主界面模块二（文件路径）
 */
 private Long ui_m2;

 /**
 * @hibernate.property column="ui_m2"
 * @return Returns the ui_m2.
 */
 public Long getUi_m2() {
    return ui_m2;
 }

 public void setUi_m2(Long ui_m2) {
    this.ui_m2 = ui_m2;
  }

 /**
 * 主界面模块三（文件路径）
 */
 private Long ui_m3;

 /**
 * @hibernate.property column="ui_m3"
 * @return Returns the ui_m3.
 */
 public Long getUi_m3() {
    return ui_m3;
 }

 public void setUi_m3(Long ui_m3) {
    this.ui_m3 = ui_m3;
  }

 /**
 * 主界面模块四（文件路径）
 */
 private Long ui_m4;

 /**
 * @hibernate.property column="ui_m4"
 * @return Returns the ui_m4.
 */
 public Long getUi_m4() {
    return ui_m4;
 }

 public void setUi_m4(Long ui_m4) {
    this.ui_m4 = ui_m4;
  }

 /**
 * 主界面模块五（文件路径）
 */
 private Long ui_m5;

 /**
 * @hibernate.property column="ui_m5"
 * @return Returns the ui_m5.
 */
 public Long getUi_m5() {
    return ui_m5;
 }

 public void setUi_m5(Long ui_m5) {
    this.ui_m5 = ui_m5;
  }

 /**
 * 主界面模块六（文件路径）
 */
 private Long ui_m6;

 /**
 * @hibernate.property column="ui_m6"
 * @return Returns the ui_m6.
 */
 public Long getUi_m6() {
    return ui_m6;
 }

 public void setUi_m6(Long ui_m6) {
    this.ui_m6 = ui_m6;
  }


}
