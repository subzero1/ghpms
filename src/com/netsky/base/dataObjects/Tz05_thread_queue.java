package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tz05_thread_queue"
 */

public class Tz05_thread_queue implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 539533357578907072L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tz05_num"
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
 private String servicename;

 /**
 * @hibernate.property column="servicename"
 * @return Returns the servicename.
 */
 public String getServicename() {
    return servicename;
 }

 public void setServicename(String servicename) {
    this.servicename = servicename;
  }

 /**
 * 参数列,json串的形式
 */
 private String parameters;

 /**
 * @hibernate.property column="parameters"
 * @return Returns the parameters.
 */
 public String getParameters() {
    return parameters;
 }

 public void setParameters(String parameters) {
    this.parameters = parameters;
  }

 /**
 * 插入时间
 */
 private Date inserttime;

 /**
 * @hibernate.property column="inserttime"
 * @return Returns the inserttime.
 */
 public Date getInserttime() {
    return inserttime;
 }

 public void setInserttime(Date inserttime) {
    this.inserttime = inserttime;
  }

 /**
 * 处理时间
 */
 private Date handletime;

 /**
 * @hibernate.property column="handletime"
 * @return Returns the handletime.
 */
 public Date getHandletime() {
    return handletime;
 }

 public void setHandletime(Date handletime) {
    this.handletime = handletime;
  }

 /**
 * 批次号
 */
 private String batchno;

 /**
 * @hibernate.property column="batchno"
 * @return Returns the batchno.
 */
 public String getBatchno() {
    return batchno;
 }

 public void setBatchno(String batchno) {
    this.batchno = batchno;
  }

 /**
 * 状态(0:未处理;1:已处理;2:处理失败)
 */
 private String status;

 /**
 * @hibernate.property column="status"
 * @return Returns the status.
 */
 public String getStatus() {
    return status;
 }

 public void setStatus(String status) {
    this.status = status;
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
 * 说明
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


}
