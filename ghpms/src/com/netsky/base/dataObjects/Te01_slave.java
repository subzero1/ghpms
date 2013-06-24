package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

import com.netsky.base.dataObjects.interfaces.SlaveObject;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Te01_slave"
 */

public class Te01_slave implements SlaveObject , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 544675272876937088L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="te01_num"
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
 * project_id
 */
 private Long project_id;

 /**
 * @hibernate.property column="project_id"
 * @return Returns the project_id.
 */
 public Long getProject_id() {
    return project_id;
 }

 public void setProject_id(Long project_id) {
    this.project_id = project_id;
  }

 /**
 * doc_id
 */
 private Long doc_id;

 /**
 * @hibernate.property column="doc_id"
 * @return Returns the doc_id.
 */
 public Long getDoc_id() {
    return doc_id;
 }

 public void setDoc_id(Long doc_id) {
    this.doc_id = doc_id;
  }

 /**
 * 附件类型
 */
 private String slave_type;

 /**
 * @hibernate.property column="slave_type"
 * @return Returns the slave_type.
 */
 public String getSlave_type() {
    return slave_type;
 }

 public void setSlave_type(String slave_type) {
    this.slave_type = slave_type;
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
 * 扩展名
 */
 private String ext_name;

 /**
 * @hibernate.property column="ext_name"
 * @return Returns the ext_name.
 */
 public String getExt_name() {
    return ext_name;
 }

 public void setExt_name(String ext_name) {
    this.ext_name = ext_name;
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
 * 机房信息:200;申请单:101;变更单:102;用户头像:0;
 */
 private Long module_id;

 /**
 * @hibernate.property column="module_id"
 * @return Returns the module_id.
 */
 public Long getModule_id() {
    return module_id;
 }

 public void setModule_id(Long module_id) {
    this.module_id = module_id;
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

 public String getSlaveIdentifier() {
		return "Slave";
	}

	public void setFileName(String FileName) {
		this.file_name = FileName;
		
	}

	public void setFilePatch(String FilePatch) {
		this.ftp_url = FilePatch;
		
	}

	public String getType() {
		return this.slave_type;
	}
	public String getSaveType() {
		return null;
	}
}
