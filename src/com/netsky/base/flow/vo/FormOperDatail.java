package com.netsky.base.flow.vo;
/**
 * 各表单处理情况视图
 * @author CT
 * @create 2010-04-24
 * @hibernate.class table="formoperdatial"
 */
public class FormOperDatail {
	/**
	 * 表单名称
	 */
	private String module_name;
	/**
	 * 用户名
	 */
	private String user_name;
	/**
	 * 用户iD
	 */
	private Long user_id;
	/**
	 * module_id
	 */
	private String module_id;
	/**
	 * 登录号
	 */
	private String login_id;
	/**
	 * 办结数
	 */
	private Integer bjs;
	/**
	 * 在办数
	 */
	private Integer zbs;
	/**
	 * 待办数
	 */
	private Integer dbs;
	/**
	 * @hibernate.property column="module_name"
	 * @return module_name
	 */
	public String getModule_name() {
		return module_name;
	}
	/**
	 * @param module_name
	 */
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	/**
	 * @hibernate.property column="user_name"
	 * @return user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 *  @hibernate.id generator-class="assigned"
	 * @return user_id
	 */
	public Long getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	/**
	 * @hibernate.property column="module_id"
	 * @return module_id
	 */
	public String getModule_id() {
		return module_id;
	}
	/**
	 * @param module_id
	 */
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}
	/**
	 * @hibernate.property column="login_id"
	 * @return login_id
	 */
	public String getLogin_id() {
		return login_id;
	}
	/**
	 * @param login_id
	 */
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	/**
	 * @hibernate.property column="bjs"
	 * @return bjs
	 */
	public Integer getBjs() {
		return bjs;
	}
	/**
	 * @param bjs
	 */
	public void setBjs(Integer bjs) {
		this.bjs = bjs;
	}
	/**
	 * @hibernate.property column="zbs"
	 * @return zbs
	 */
	public Integer getZbs() {
		return zbs;
	}
	/**
	 * @param zbs
	 */
	public void setZbs(Integer zbs) {
		this.zbs = zbs;
	}
	/**
	 * @hibernate.property column="dbs"
	 * @return dbs
	 */
	public Integer getDbs() {
		return dbs;
	}
	/**
	 * @param dbs
	 */
	public void setDbs(Integer dbs) {
		this.dbs = dbs;
	}
	
	
}
