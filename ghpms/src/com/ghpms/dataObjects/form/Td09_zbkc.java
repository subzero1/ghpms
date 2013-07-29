package com.ghpms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-07-29
 * @hibernate.class table="Td09_zbkc"
 */

public class Td09_zbkc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 249257930167284160L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td09_num"
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

	
}
