package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author mengying 2010-1-7
 * @hibernate.class table="Tb06_phase"
 */

public class Tb06_phase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5827255888131026968L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 描述
	 */
	private String description;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tb06_NUM"
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
}
