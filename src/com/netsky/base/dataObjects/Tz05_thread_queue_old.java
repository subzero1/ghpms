package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * 操作日志表
 * @class name:com.netsky.base.dataObjects.Tz05_thread_queue
 * @hibernate.class table="Tz05_thread_queue"
 * @author lee~xiangyu Jan 16, 2012 
 * 
 */

public class Tz05_thread_queue_old implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2004652965009073411L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 操作人员工号
	 */
	private String type;
	
	/**
	 * 操作人员姓名
	 */
	private String paras;
	
	/**
	 * 操作人员IP
	 */
	private String status;
	
	/**
	 * 操作时间
	 */
	private Date oper_date ;
	
	/**
	 * 插入时间
	 */
	private Date insert_date ;
	
	/**
	 * 执行批次（8位的随机数，防止同时起多个WEB服务的情况下多次处理）
	 */
	private String oper_batchs;
	
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tz05_NUM"
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
	 * @hibernate.property column="oper_date"
	 * @return Returns the oper_date.
	 */
	public Date getOper_date() {
		return oper_date;
	}

	/**
	 * @param oper_date The oper_date to set.
	 */
	public void setOper_date(Date oper_date) {
		this.oper_date = oper_date;
	}

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
	 * @hibernate.property column="paras"
	 * @return Returns the paras.
	 */
	public String getParas() {
		return paras;
	}

	public void setParas(String paras) {
		this.paras = paras;
	}

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
	 * @hibernate.property column="insert_date"
	 * @return Returns the insert_date.
	 */
	public Date getInsert_date() {
		return insert_date;
	}

	public void setInsert_date(Date insert_date) {
		this.insert_date = insert_date;
	}

	/**
	 * @hibernate.property column="oper_batchs"
	 * @return Returns the oper_batchs.
	 */
	public String getOper_batchs() {
		return oper_batchs;
	}

	public void setOper_batchs(String oper_batchs) {
		this.oper_batchs = oper_batchs;
	}

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
