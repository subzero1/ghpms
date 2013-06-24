package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="WdView"
 */

public class WdView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 544675272876937088L;

	/**
	 * 标识
	 */
	private Long id;

	/**
	 * @hibernate.id generator-class="assigned"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 文件名
	 */
	private String file_name;

	/**
	 * @hibernate.property column="file_name"
	 * @return Returns the file_name.
	 */
	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	/**
	 * ftp路径
	 */
	private String ftp_url;

	/**
	 * @hibernate.property column="ftp_url"
	 * @return Returns the ftp_url.
	 */
	public String getFtp_url() {
		return ftp_url;
	}

	public void setFtp_url(String ftp_url) {
		this.ftp_url = ftp_url;
	}

	/**
	 * 用户名
	 */
	private String user_name;

	/**
	 * @hibernate.property column="user_name"
	 * @return Returns the user_name.
	 */
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * 用户ID
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
	 * 上传日期
	 */
	private Date ftp_date;

	/**
	 * @hibernate.property column="ftp_date"
	 * @return Returns the ftp_date.
	 */
	public Date getFtp_date() {
		return ftp_date;
	}

	public void setFtp_date(Date ftp_date) {
		this.ftp_date = ftp_date;
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
	 * 查阅范围（全部/部门1,部门2/岗位1,岗位2/姓名1,姓名2）
	 */
	private String cyfw;

	/**
	 * @hibernate.property column="cyfw"
	 * @return Returns the cyfw.
	 */
	public String getCyfw() {
		return cyfw;
	}

	public void setCyfw(String cyfw) {
		this.cyfw = cyfw;
	}

	/**
	 * 查阅类型（所有：0；按部门：1；按岗位2；按人：3；）
	 */
	private String cylb;

	/**
	 * @hibernate.property column="cylb"
	 * @return Returns the cylb.
	 */
	public String getCylb() {
		return cylb;
	}

	public void setCylb(String cylb) {
		this.cylb = cylb;
	}

	/**
	 * 目录ID
	 */
	private Long ml_id;

	/**
	 * @hibernate.property column="ml_id"
	 * @return Returns the ml_id.
	 */
	public Long getMl_id() {
		return ml_id;
	}

	/**
	 * @param ml_id
	 *            The ml_id to set.
	 */
	public void setMl_id(Long ml_id) {
		this.ml_id = ml_id;
	}

	/**
	 * 目录名称
	 */
	private String mlmc;

	/**
	 * @hibernate.property column="mlmc"
	 * @return Returns the mlmc.
	 */
	public String getMlmc() {
		return mlmc;
	}

	/**
	 * @param mlmc
	 *            The mlmc to set.
	 */
	public void setMlmc(String mlmc) {
		this.mlmc = mlmc;
	}

	/**
	 * 目录
	 */
	private String contents;

	/**
	 * @hibernate.property column="contents"
	 * @return Returns the contents.
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * @param contents
	 *            The contents to set.
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

}
