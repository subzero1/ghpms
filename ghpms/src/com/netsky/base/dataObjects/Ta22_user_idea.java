package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Ta22_user_idea"
 */

public class Ta22_user_idea implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 969885857778502912L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ta22_num"
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
 * 审批结果
 */
 private Long check_result;

 /**
 * @hibernate.property column="check_result"
 * @return Returns the check_result.
 */
 public Long getCheck_result() {
    return check_result;
 }

 public void setCheck_result(Long check_result) {
    this.check_result = check_result;
  }

 /**
 * 审批意见
 */
 private String check_idea;

 /**
 * @hibernate.property column="check_idea"
 * @return Returns the check_idea.
 */
 public String getCheck_idea() {
    return check_idea;
 }

 public void setCheck_idea(String check_idea) {
    this.check_idea = check_idea;
  }


}
