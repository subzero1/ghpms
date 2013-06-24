package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author mengying 2009-12-11
 * @hibernate.class Tb14_even_affair"
 */

public class Tb14_even_affair  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1078064457857884643L;

	/**
	 * 标识，hibernate 用
	 */
	private Long id;
	
	/**
	 * 节点ID或关系ID
	 */
	private Long even_id;
	
	/**
	 * 事务标识
	 */
	private Long affair_id;
	
	/**
	 * 参数;key=value=value
	 */
	private String parameters;

	/**
	 * 序列；标识为此节点的第几项事务
	 */
	private Long seq;
	
	/**
	 * 节点状态
	 */
	private String node_status;
	
	/**
	 * 事件类型
	 */
	private Long even_type;
	
	/**
	 * 说明
	 */
	private String descr;


	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tb14_NUM"
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
	 * @hibernate.property column="even_id"
	 * @return Returns the even_id.
	 */
	public Long getEven_id() {
		return even_id;
	}

	/**
	 * @param even_id The even_id to set.
	 */
	public void setEven_id(Long even_id) {
		this.even_id = even_id;
	}

	/**
	 * @hibernate.property column="affair_id"
	 * @return Returns the affair_id.
	 */
	public Long getAffair_id() {
		return affair_id;
	}

	/**
	 * @param affair_id The affair_id to set.
	 */
	public void setAffair_id(Long affair_id) {
		this.affair_id = affair_id;
	}

	/**
	 * @hibernate.property column="parameters"
	 * @return Returns the parameters.
	 */
	public String getParameters() {
		return parameters;
	}

	/**
	 * @param parameters The parameters to set.
	 */
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	/**
	 * @hibernate.property column="seq"
	 * @return Returns the seq.
	 */
	public Long getSeq() {
		return seq;
	}

	/**
	 * @param seq The seq to set.
	 */
	public void setSeq(Long seq) {
		this.seq = seq;
	}

	/**
	 * @hibernate.property column="node_status"
	 * @return Returns the node_status.
	 */
	public String getNode_status() {
		return node_status;
	}

	/**
	 * @param node_status The node_status to set.
	 */
	public void setNode_status(String node_status) {
		this.node_status = node_status;
	}

	/**
	 * @hibernate.property column="even_type"
	 * @return Returns the even_type.
	 */
	public Long getEven_type() {
		return even_type;
	}

	/**
	 * @param even_type The even_type to set.
	 */
	public void setEven_type(Long even_type) {
		this.even_type = even_type;
	}

	/**
	 * @hibernate.property column="descr"
	 * @return Returns the descr.
	 */
	public String getDescr() {
		return descr;
	}

	/**
	 * @param descr The descr to set.
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}
}
