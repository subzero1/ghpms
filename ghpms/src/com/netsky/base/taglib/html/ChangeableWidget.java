package com.netsky.base.taglib.html;

/**
 * 作者：代伟
 * 
 * 日期：2006-2-19
 * 
 * 描述： Html中的有OnChange事件的可视控件
 */
public class ChangeableWidget extends Widget {
	/**
	 * 默认的版本标识
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 控件的状态改变事件属性
	 */
	private String on_change = null;

	/**
	 * 设置控件的状态改变事件
	 */
	public void setOnChange(String on_change) {
		this.on_change = on_change;
	}

	/**
	 * 获得控件的状态改变事件
	 */
	public String getOnChange() {
		return on_change;
	}
	
	/**
	 * 设置Html属性
	 */
	protected void setHtmlProperties() {
		super.setHtmlProperties();
		if (on_change != null)
			appendHtmlProperties("onChange=\"" + on_change + "\"");
	}
}
