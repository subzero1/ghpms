package com.netsky.base.flow.buttonControl;

/**
 * @description:
 * 按扭数据结构
 * @class name:com.netsky.base.flow.buttonControl.Button
 * @author wind Jan 14, 2010
 */
public class Button {
	/**
	 * 按钮名称
	 */
	public String name;
	
	/**
	 * 按钮执行url或动作
	 */
	public String url ;
	/**
	 * 按钮注释
	 */
	public String comment;
	
	/**
	 * 打开窗口属性
	 */
	public String window_pro ;
	
	/**
	 * 按钮类别，或show_action
	 */
	public String flag = "flow";
	
	/**
	 * 按钮对应图片地址
	 */
	public String picUri = "";
	
	/**
	 * 构造函数
	 * 描述：通过name 值创建Button
	 */
	public Button(String name) {
		super();
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return Returns the comment.
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment The comment to set.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return Returns the window_pro.
	 */
	public String getWindow_pro() {
		return window_pro;
	}

	/**
	 * @param window_pro The window_pro to set.
	 */
	public void setWindow_pro(String window_pro) {
		this.window_pro = window_pro;
	}

	/**
	 * @return Returns the flag.
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag The flag to set.
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return Returns the picUri.
	 */
	public String getPicUri() {
		return picUri;
	}

	/**
	 * @param picUri The picUri to set.
	 */
	public void setPicUri(String picUri) {
		this.picUri = picUri;
	}
}
