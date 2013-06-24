package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Te09_view"
 */

public class Te09_view implements Serializable {
	private static final long serialVersionUID = 7489353214447769306L;
	private Long id;
	private Long module_id;
	private Long doc_id;
	private String nr;
	private Date fbsj;
	private Long fbr_id;
	private String fbr;
	private Long up_id;
	private String tzr_names;
	private String comments;
	private String title;
	private String module_name;

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
	 * @hibernate.property column="module_id"
	 * @return Returns the module_id.
	 */
	public Long getModule_id() {
		return module_id;
	}

	/**
	 * @param module_id
	 *            The module_id to set.
	 */
	public void setModule_id(Long module_id) {
		this.module_id = module_id;
	}

	/**
	 * @hibernate.property column="doc_id"
	 * @return Returns the doc_id.
	 */
	public Long getDoc_id() {
		return doc_id;
	}

	/**
	 * @param doc_id
	 *            The doc_id to set.
	 */
	public void setDoc_id(Long doc_id) {
		this.doc_id = doc_id;
	}

	/**
	 * @hibernate.property column="nr"
	 * @return Returns the nr.
	 */
	public String getNr() {
		return nr;
	}

	/**
	 * @param nr
	 *            The nr to set.
	 */
	public void setNr(String nr) {
		this.nr = nr;
	}

	/**
	 * @hibernate.property column="fbsj"
	 * @return Returns the fbsj.
	 */
	public Date getFbsj() {
		return fbsj;
	}

	/**
	 * @param fbsj
	 *            The fbsj to set.
	 */
	public void setFbsj(Date fbsj) {
		this.fbsj = fbsj;
	}

	/**
	 * @hibernate.property column="fbr_id"
	 * @return Returns the fbr_id.
	 */
	public Long getFbr_id() {
		return fbr_id;
	}

	/**
	 * @param fbr_id
	 *            The fbr_id to set.
	 */
	public void setFbr_id(Long fbr_id) {
		this.fbr_id = fbr_id;
	}

	/**
	 * @hibernate.property column="fbr"
	 * @return Returns the fbr.
	 */
	public String getFbr() {
		return fbr;
	}

	/**
	 * @param fbr
	 *            The fbr to set.
	 */
	public void setFbr(String fbr) {
		this.fbr = fbr;
	}

	/**
	 * @hibernate.property column="up_id"
	 * @return Returns the up_id.
	 */
	public Long getUp_id() {
		return up_id;
	}

	/**
	 * @param up_id
	 *            The up_id to set.
	 */
	public void setUp_id(Long up_id) {
		this.up_id = up_id;
	}

	/**
	 * @hibernate.property column="tzr_names"
	 * @return Returns the tzr_names.
	 */
	public String getTzr_names() {
		return tzr_names;
	}

	/**
	 * @param tzr_names
	 *            The tzr_names to set.
	 */
	public void setTzr_names(String tzr_names) {
		this.tzr_names = tzr_names;
	}

	/**
	 * @hibernate.property column="comments"
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @hibernate.property column="title"
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @hibernate.property column="module_name"
	 * @return Returns the module_name.
	 */
	public String getModule_name() {
		return module_name;
	}

	/**
	 * @param module_name
	 *            The module_name to set.
	 */
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

}
