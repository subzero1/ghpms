package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-08-15
 * @hibernate.class table="Tf16_temp"
 */

public class Tf16_temp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 287576878450645600L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf16_num"
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
 * 用户标识
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
 * 操作标识
 */
 private Long operate_id;

 /**
 * @hibernate.property column="operate_id"
 * @return Returns the operate_id.
 */
 public Long getOperate_id() {
    return operate_id;
 }

 public void setOperate_id(Long operate_id) {
    this.operate_id = operate_id;
  }

 /**
 * 类别
 */
 private String type;

 /**
 * @hibernate.property column="type"
 * @return Returns the type.
 */
 public String getType() {
    return type;
 }

 public void setType(String type) {
    this.type = type;
  }

 /**
 * 
 */
 private String c1;

 /**
 * @hibernate.property column="c1"
 * @return Returns the c1.
 */
 public String getC1() {
    return c1;
 }

 public void setC1(String c1) {
    this.c1 = c1;
  }

 /**
 * 
 */
 private String c2;

 /**
 * @hibernate.property column="c2"
 * @return Returns the c2.
 */
 public String getC2() {
    return c2;
 }

 public void setC2(String c2) {
    this.c2 = c2;
  }

 /**
 * 
 */
 private String c3;

 /**
 * @hibernate.property column="c3"
 * @return Returns the c3.
 */
 public String getC3() {
    return c3;
 }

 public void setC3(String c3) {
    this.c3 = c3;
  }

 /**
 * 
 */
 private String c4;

 /**
 * @hibernate.property column="c4"
 * @return Returns the c4.
 */
 public String getC4() {
    return c4;
 }

 public void setC4(String c4) {
    this.c4 = c4;
  }

 /**
 * 
 */
 private String c5;

 /**
 * @hibernate.property column="c5"
 * @return Returns the c5.
 */
 public String getC5() {
    return c5;
 }

 public void setC5(String c5) {
    this.c5 = c5;
  }

 /**
 * 
 */
 private String c6;

 /**
 * @hibernate.property column="c6"
 * @return Returns the c6.
 */
 public String getC6() {
    return c6;
 }

 public void setC6(String c6) {
    this.c6 = c6;
  }

 /**
 * 
 */
 private String c7;

 /**
 * @hibernate.property column="c7"
 * @return Returns the c7.
 */
 public String getC7() {
    return c7;
 }

 public void setC7(String c7) {
    this.c7 = c7;
  }

 /**
 * 
 */
 private String c8;

 /**
 * @hibernate.property column="c8"
 * @return Returns the c8.
 */
 public String getC8() {
    return c8;
 }

 public void setC8(String c8) {
    this.c8 = c8;
  }

 /**
 * 
 */
 private String c9;

 /**
 * @hibernate.property column="c9"
 * @return Returns the c9.
 */
 public String getC9() {
    return c9;
 }

 public void setC9(String c9) {
    this.c9 = c9;
  }

 /**
 * 
 */
 private String c10;

 /**
 * @hibernate.property column="c10"
 * @return Returns the c10.
 */
 public String getC10() {
    return c10;
 }

 public void setC10(String c10) {
    this.c10 = c10;
  }

 /**
 * 
 */
 private String c11;

 /**
 * @hibernate.property column="c11"
 * @return Returns the c11.
 */
 public String getC11() {
    return c11;
 }

 public void setC11(String c11) {
    this.c11 = c11;
  }

 /**
 * 
 */
 private String c12;

 /**
 * @hibernate.property column="c12"
 * @return Returns the c12.
 */
 public String getC12() {
    return c12;
 }

 public void setC12(String c12) {
    this.c12 = c12;
  }

 /**
 * 
 */
 private String c13;

 /**
 * @hibernate.property column="c13"
 * @return Returns the c13.
 */
 public String getC13() {
    return c13;
 }

 public void setC13(String c13) {
    this.c13 = c13;
  }

 /**
 * 
 */
 private String c14;

 /**
 * @hibernate.property column="c14"
 * @return Returns the c14.
 */
 public String getC14() {
    return c14;
 }

 public void setC14(String c14) {
    this.c14 = c14;
  }

 /**
 * 
 */
 private String c15;

 /**
 * @hibernate.property column="c15"
 * @return Returns the c15.
 */
 public String getC15() {
    return c15;
 }

 public void setC15(String c15) {
    this.c15 = c15;
  }

 /**
 * 
 */
 private String c16;

 /**
 * @hibernate.property column="c16"
 * @return Returns the c16.
 */
 public String getC16() {
    return c16;
 }

 public void setC16(String c16) {
    this.c16 = c16;
  }

 /**
 * 
 */
 private String c17;

 /**
 * @hibernate.property column="c17"
 * @return Returns the c17.
 */
 public String getC17() {
    return c17;
 }

 public void setC17(String c17) {
    this.c17 = c17;
  }

 /**
 * 
 */
 private String c18;

 /**
 * @hibernate.property column="c18"
 * @return Returns the c18.
 */
 public String getC18() {
    return c18;
 }

 public void setC18(String c18) {
    this.c18 = c18;
  }

 /**
 * 
 */
 private String c19;

 /**
 * @hibernate.property column="c19"
 * @return Returns the c19.
 */
 public String getC19() {
    return c19;
 }

 public void setC19(String c19) {
    this.c19 = c19;
  }

 /**
 * 
 */
 private String c20;

 /**
 * @hibernate.property column="c20"
 * @return Returns the c20.
 */
 public String getC20() {
    return c20;
 }

 public void setC20(String c20) {
    this.c20 = c20;
  }


}
