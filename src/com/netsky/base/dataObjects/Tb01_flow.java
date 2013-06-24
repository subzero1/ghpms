package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author mengying 2010-1-7
 * @hibernate.class table="Tb01_flow"
 */

public class Tb01_flow implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7307924619384208648L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 名称
	 */
	private String name;
	

	
	/**
	 * 当流程为子流程时：流程开始节点
	 */
	private Long startNode;
	
	/**
	 * 流程描述
	 */
	private String description;
	
	/**
	 * 显示：代表性简要说明，或流程标识性图标
	 */
	private String display;
	
	/**
	 * 流程分类：0 一个流程组合中只能出现一次 且起草后source_node自动办结； 1 一个流程中只能出现一次，source_node不自动办结；2 变更调整型 可以多次出现在一个流程中，起草后source_node不办结 
	 */
	private Integer type;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tb01_NUM"
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
	 * @hibernate.property column="startNode"
	 * @return the startNode
	 */
	public Long getstartNode() {
		return startNode;
	}

	/**
	 * @param startNode the startNode to set
	 */
	public void setstartNode(Long startNode) {
		this.startNode = startNode;
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
	 * @hibernate.property column="display"
	 * @return the display
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * @param display the display to set
	 */
	public void setDisplay(String display) {
		this.display = display;
	}

	/**
	 * @hibernate.property column="type"
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
}
