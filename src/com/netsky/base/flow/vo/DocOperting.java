package com.netsky.base.flow.vo;

/**
 * @description:
 * 待办文档列表
 * @class name:com.netsky.base.flow.vo.DocOperting
 * @author wind Jan 28, 2010
 * @hibernate.class table="docoperting"
 */
public class DocOperting {
	
	/**
	 * 用户名
	 */
	private String name;
	
	/**
	 * 登录名
	 */
	private String login_id;
	
	/**
	 * 登录密码
	 */
	private String passwd;
	
	/**
	 * 用户标识
	 */
	private Long user_id;
	
	/**
	 * 待办文档数
	 */
	private Long need_work;
	
	/**
	 * 在办文档数
	 */
	private Long on_work;
	
	/**
	 * 待复文档数
	 */
	private Long wait_work;
	
	/**
	 * 回复文档数
	 */
	private Long reply_work;
	
	/**
	 * 办结文档数
	 */
	private Long off_work;
	
	/**
	 * 地区标识
	 */
	private Integer area_id;
	
	/**
	 * @hibernate.id generator-class="assigned"
	 * @return Long
	 */
	public Long getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id The user_id to set.
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	/**
	 * @hibernate.property column="name"
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property column="need_work"
	 * @return Returns the need_work.
	 */
	public Long getNeed_work() {
		return need_work;
	}

	/**
	 * @param need_work The need_work to set.
	 */
	public void setNeed_work(Long need_work) {
		this.need_work = need_work;
	}

	/**
	 * @hibernate.property column="on_work"
	 * @return Returns the on_work.
	 */
	public Long getOn_work() {
		return on_work;
	}

	/**
	 * @param on_work The on_work to set.
	 */
	public void setOn_work(Long on_work) {
		this.on_work = on_work;
	}

	/**
	 * @hibernate.property column="wait_work"
	 * @return Returns the wait_work.
	 */
	public Long getWait_work() {
		return wait_work;
	}

	/**
	 * @param project_id The wait_work to set.
	 */
	public void setWait_work(Long wait_work) {
		this.wait_work = wait_work;
	}

	/**
	 * @hibernate.property column="reply_work"
	 * @return Returns the reply_work.
	 */
	public Long getReply_work() {
		return reply_work;
	}

	/**
	 * @param reply_work The reply_work to set.
	 */
	public void setReply_work(Long reply_work) {
		this.reply_work = reply_work;
	}

	/**
	 * @hibernate.property column="off_work"
	 * @return Returns the off_work.
	 */
	public Long getOff_work() {
		return off_work;
	}

	/**
	 * @param off_work The off_work to set.
	 */
	public void setOff_work(Long off_work) {
		this.off_work = off_work;
	}

	/**
	 * @hibernate.property column="login_id"
	 * @return Returns the login_id.
	 */
	public String getLogin_id() {
		return login_id;
	}

	/**
	 * @param login_id The login_id to set.
	 */
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	/**
	 * @hibernate.property column="passwd"
	 * @return Returns the passwd.
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd The passwd to set.
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * @hibernate.property column="area_id"
	 * @return Returns the area_id.
	 */
	public Integer getArea_id() {
		return area_id;
	}

	/**
	 * @param area_id The area_id to set.
	 */
	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
	}
}
