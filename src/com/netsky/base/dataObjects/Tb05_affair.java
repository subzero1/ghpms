package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author mengying 2010-1-7
 * @hibernate.class table="tb05_affair"
 */


public class Tb05_affair implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4948265091834636607L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 事务类别：create_form 表单，show_action 表单显示操作；background后台事务处理 
	 */
	private String affair_type;
	
	/**
	 * 处理类;对于show_action 可以列示URL
	 */
	private String doclass;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 *执行类参数:(key=value'=value1'=value2)
	 */
	private String paras;
	
	/**
	 * 可执行sql，如果本安段有值，则配置java类无效（将不执行）
	 */
	private String excute_sql;		

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tb05_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="name"
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property column="affair_type"
	 * @return the affair_type
	 */
	public String getAffair_type() {
		return affair_type;
	}

	/**
	 * @param affair_type the affair_type to set
	 */
	public void setAffair_type(String affair_type) {
		this.affair_type = affair_type;
	}

	/**
	 * @hibernate.property column="doclass"
	 * @return the doclass
	 */
	public String getDoclass() {
		return doclass;
	}

	/**
	 * @param doclass the doclass to set
	 */
	public void setDoclass(String doclass) {
		this.doclass = doclass;
	}

	/**
	 * @hibernate.property column="description"
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @hibernate.property column="paras"
	 * @return the paras
	 */
	public String getParas() {
		return paras;
	}

	/**
	 * @param paras the paras to set
	 */
	public void setParas(String paras) {
		this.paras = paras;
	}
	
	/**
	 * @hibernate.property column="excute_sql"
	 * @return Returns the excute_sql.
	 */
	public String getExcute_sql() {
		return excute_sql;
	}

	/**
	 * @param excute_sql The excute_sql to set.
	 */
	public void setExcute_sql(String excute_sql) {
		this.excute_sql = excute_sql;
	}	
}
