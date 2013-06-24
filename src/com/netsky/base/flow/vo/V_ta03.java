package com.netsky.base.flow.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="V_ta03"
 */

public class V_ta03 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 197263558245974528L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta03_num"
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
 * 姓名
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
 * 登录名
 */
 private String login_id;

 /**
 * @hibernate.property column="login_id"
 * @return Returns the login_id.
 */
 public String getLogin_id() {
    return login_id;
 }

 public void setLogin_id(String login_id) {
    this.login_id = login_id;
  }

 /**
 * 密码
 */
 private String passwd;

 /**
 * @hibernate.property column="passwd"
 * @return Returns the passwd.
 */
 public String getPasswd() {
    return passwd;
 }

 public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

 /**
 * 固定电话
 */
 private String fix_tel;

 /**
 * @hibernate.property column="fix_tel"
 * @return Returns the fix_tel.
 */
 public String getFix_tel() {
    return fix_tel;
 }

 public void setFix_tel(String fix_tel) {
    this.fix_tel = fix_tel;
  }

 /**
 * 移动电话
 */
 private String mobile_tel;

 /**
 * @hibernate.property column="mobile_tel"
 * @return Returns the mobile_tel.
 */
 public String getMobile_tel() {
    return mobile_tel;
 }

 public void setMobile_tel(String mobile_tel) {
    this.mobile_tel = mobile_tel;
  }

 /**
 * 电子邮件
 */
 private String email;

 /**
 * @hibernate.property column="email"
 * @return Returns the email.
 */
 public String getEmail() {
    return email;
 }

 public void setEmail(String email) {
    this.email = email;
  }

 /**
 * 备注
 */
 private String remark;

 /**
 * @hibernate.property column="remark"
 * @return Returns the remark.
 */
 public String getRemark() {
    return remark;
 }

 public void setRemark(String remark) {
    this.remark = remark;
  }

 /**
 * 部门标识
 */
 private Long dept_id;

 /**
 * @hibernate.property column="dept_id"
 * @return Returns the dept_id.
 */
 public Long getDept_id() {
    return dept_id;
 }

 public void setDept_id(Long dept_id) {
    this.dept_id = dept_id;
  }
 
 /**
  * 部门名称
  */
  private String dept_name;

  /**
  * @hibernate.property column="dept_name"
  * @return Returns the dept_name.
  */
  public String getDept_name() {
     return dept_name;
  }

  public void setDept_name(String dept_name) {
     this.dept_name = dept_name;
   }

 /**
 * 0:不可用，1：可用
 */
 private Long useflag;

 /**
 * @hibernate.property column="useflag"
 * @return Returns the useflag.
 */
 public Long getUseflag() {
    return useflag;
 }

 public void setUseflag(Long useflag) {
    this.useflag = useflag;
  }

 /**
 * 上次密码修改时间
 */
 private Date last_pwd_date;

 /**
 * @hibernate.property column="last_pwd_date"
 * @return Returns the last_pwd_date.
 */
 public Date getLast_pwd_date() {
    return last_pwd_date;
 }

 public void setLast_pwd_date(Date last_pwd_date) {
    this.last_pwd_date = last_pwd_date;
  }

 /**
 * 性别 (男/女)
 */
 private String sex;

 /**
 * @hibernate.property column="sex"
 * @return Returns the sex.
 */
 public String getSex() {
    return sex;
 }

 public void setSex(String sex) {
    this.sex = sex;
  }

 /**
 * 工程查询级别：1:全部工程; 2: 本地区工程; 3:本人经历过的工程
 */
 private Long search_level;

 /**
 * @hibernate.property column="search_level"
 * @return Returns the search_level.
 */
 public Long getSearch_level() {
    return search_level;
 }

 public void setSearch_level(Long search_level) {
    this.search_level = search_level;
  }

 /**
 * 地区名称
 */
 private String area_name;

 /**
 * @hibernate.property column="area_name"
 * @return Returns the area_name.
 */
 public String getArea_name() {
    return area_name;
 }

 public void setArea_name(String area_name) {
    this.area_name = area_name;
  }

 /**
 * 临时字段
 */
 private String new_flag;

 /**
 * @hibernate.property column="new_flag"
 * @return Returns the new_flag.
 */
 public String getNew_flag() {
    return new_flag;
 }

 public void setNew_flag(String new_flag) {
    this.new_flag = new_flag;
  }
 
 public String dept_remark;

public String getDept_remark() {
	return dept_remark;
}

public void setDept_remark(String dept_remark) {
	this.dept_remark = dept_remark;
}
 

}
