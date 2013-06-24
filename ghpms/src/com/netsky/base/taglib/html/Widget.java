package com.netsky.base.taglib.html;

import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * 作者：代伟
 * 
 * 日期：2006-2-19
 * 
 * 描述： Html中的可视控件的公共属性
 */
public class Widget  extends BodyTagSupport {
	/**
	 * 默认的版本标识
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * html标签的属性字符串
	 */
	private StringBuffer properties = null;
	
	/**
	 * 焦点事件属性： 描述：失去焦点事件属性
	 */
	private String on_blur = null;

	/**
	 * 焦点事件属性： 描述：获得焦点事件属性
	 */
	private String on_focus = null;

	/**
	 * 键盘事件属性： 描述：键按下事件属性
	 */
	private String on_key_down = null;

	/**
	 * 键盘事件属性： 描述：键抬起事件属性
	 */
	private String on_key_up = null;

	/**
	 * 键盘事件属性： 描述：键敲击事件属性
	 */
	private String on_key_press = null;

	/**
	 * 鼠标事件属性： 描述：鼠标单击事件属性
	 */
	private String on_click = null;

	/**
	 * 鼠标事件属性： 描述：鼠标双击事件属性
	 */
	private String on_dbl_click = null;

	/**
	 * 鼠标事件属性： 描述：鼠标按键按下事件属性
	 */
	private String on_mouse_down = null;

	/**
	 * 鼠标事件属性： 描述：鼠标按键抬起事件属性
	 */
	private String on_mouse_up = null;

	/**
	 * 鼠标事件属性： 描述：鼠标指针在控件上移动事件属性
	 */
	private String on_mouse_move = null;

	/**
	 * 鼠标事件属性： 描述：鼠标指针移入控件事件属性
	 */
	private String on_mouse_over = null;

	/**
	 * 鼠标事件属性： 描述：鼠标指针移出控件事件属性
	 */
	private String on_mouse_out = null;

	/**
	 * 控件的样式
	 */
	private String style = null;

	/**
	 * 控件的类
	 */
	private String htmlClass = null;

	/**
	 * 是否是失效的
	 */
	private boolean isDisabled = false;

	/**
	 * 设置失去焦点事件
	 */
	public void setOnBlur(String on_blur) {
		this.on_blur = on_blur;
	}

	/**
	 * 获得失去焦点事件
	 */
	public String getOnBlur() {
		return on_blur;
	}

	/**
	 * 设置得到焦点事件
	 */
	public void setOnFocus(String on_focus) {
		this.on_focus = on_focus;
	}

	/**
	 * 获得得到焦点事件
	 */
	public String getOnFocus() {
		return on_focus;
	}

	/**
	 * 设置键盘按键按下事件
	 */
	public void setOnKeyDown(String on_key_down) {
		this.on_key_down = on_key_down;
	}

	/**
	 * 获得键盘按键按下事件
	 */
	public String getOnKeyDown() {
		return on_key_down;
	}

	/**
	 * 设置键盘按键抬起事件
	 */
	public void setOnKeyUp(String on_key_up) {
		this.on_key_up = on_key_up;
	}

	/**
	 * 获得键盘按键抬起事件
	 */
	public String getOnKeyUp() {
		return on_key_up;
	}

	/**
	 * 设置键盘按键敲击事件
	 */
	public void setOnKeyPress(String on_key_press) {
		this.on_key_press = on_key_press;
	}

	/**
	 * 获得键盘按键敲击事件
	 */
	public String getOnKeyPress() {
		return on_key_press;
	}

	/**
	 * 设置鼠标单击事件
	 */
	public void setOnClick(String on_click) {
		this.on_click = on_click;
	}

	/**
	 * 获得鼠标单击事件
	 */
	public String getOnClick() {
		return on_click;
	}

	/**
	 * 设置鼠标双击事件
	 */
	public void setOnDblClick(String on_dbl_click) {
		this.on_dbl_click = on_dbl_click;
	}

	/**
	 * 获得鼠标双击事件
	 */
	public String getOnDblClick() {
		return on_dbl_click;
	}

	/**
	 * 设置鼠标按下事件
	 */
	public void setOnMouseDown(String on_mouse_down) {
		this.on_mouse_down = on_mouse_down;
	}

	/**
	 * 获得鼠标按下事件
	 */
	public String getOnMouseDown() {
		return on_mouse_down;
	}

	/**
	 * 设置鼠标抬起事件
	 */
	public void setOnMouseUp(String on_mouse_up) {
		this.on_mouse_up = on_mouse_up;
	}

	/**
	 * 获得鼠标抬起事件
	 */
	public String getOnMouseUp() {
		return on_mouse_up;
	}

	/**
	 * 设置鼠标指针移动事件
	 */
	public void setOnMouseMove(String on_mouse_move) {
		this.on_mouse_move = on_mouse_move;
	}

	/**
	 * 获得鼠标指针移动事件
	 */
	public String getOnMouseMove() {
		return on_mouse_move;
	}

	/**
	 * 设置鼠标指针移入控件事件
	 */
	public void setOnMouseOver(String on_mouse_over) {
		this.on_mouse_over = on_mouse_over;
	}

	/**
	 * 获得鼠标指针移入控件事件
	 */
	public String getOnMouseOver() {
		return on_mouse_over;
	}

	/**
	 * 设置鼠标指针移出控件事件
	 */
	public void setOnMouseOut(String on_mouse_out) {
		this.on_mouse_out = on_mouse_out;
	}

	/**
	 * 得到鼠标指针移出控件事件
	 */
	public String getOnMouseOut() {
		return on_mouse_out;
	}

	/**
	 * 设置样式
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * 获得样式
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * 设置是否失效的
	 */
	public void setIsDisabled(String disabled) {
		if ("TRUE".equalsIgnoreCase(disabled)) {
			isDisabled = true;
		}
	}

	/**
	 * 获得是否失效的
	 */
	public String getIsDisabled() {
		if (isDisabled)
			return "TRUE";
		else
			return "FALSE";
	}

	/**
	 * 是否失效的
	 */
	public boolean isDisabled() {
		return isDisabled;
	}

	/**
	 * 设置控件的类
	 */
	public void setHtmlClass(String htmlClass) {
		this.htmlClass = htmlClass;
	}

	/**
	 * 获得控件的类
	 */
	public String getHtmlClass() {
		return htmlClass;
	}

	/**
	 * 设置Html属性
	 */
	protected void setHtmlProperties() {
		if (on_blur != null)
			appendHtmlProperties("onBlur=\"" + on_blur + "\"");
		if (on_focus != null)
			appendHtmlProperties("onFocus=\"" + on_focus + "\"");
		if (on_key_down != null)
			appendHtmlProperties("onKeyDown=\"" + on_key_down + "\"");
		if (on_key_up != null)
			appendHtmlProperties("onKeyUp=\"" + on_key_up + "\"");
		if (on_key_press != null)
			appendHtmlProperties("onKeyPress=\"" + on_key_press + "\"");
		if (on_click != null)
			appendHtmlProperties("onClick=\"" + on_click + "\"");
		if (on_dbl_click != null)
			appendHtmlProperties("onDblClick=\"" + on_dbl_click + "\"");
		if (on_mouse_down != null)
			appendHtmlProperties("onMouseDown=\"" + on_mouse_down + "\"");
		if (on_mouse_up != null)
			appendHtmlProperties("onMouseUp=\"" + on_mouse_up + "\"");
		if (on_mouse_move != null)
			appendHtmlProperties("onMouseMove=\"" + on_mouse_move + "\"");
		if (on_mouse_over != null)
			appendHtmlProperties("onMouseOver=\"" + on_mouse_over + "\"");
		if (on_mouse_out != null)
			appendHtmlProperties("onMouseOut=\"" + on_mouse_out + "\"");
		if (style != null)
			appendHtmlProperties("style=\"" + style + "\"");
		if (htmlClass != null)
			appendHtmlProperties("class=\"" + htmlClass + "\"");

	}
	
	/**
	 * 添加属性字符串
	 */
	protected void appendHtmlProperties(String properties) {
		if (this.properties == null) {
			this.properties = new StringBuffer(" " + properties);
		} else {
			this.properties.append(" " + properties);
		}
	}

	/**
	 * 获得属性字符串
	 */
	protected String getHtmlProperties() {
		String tmp_str = null;
		if (properties != null)
			tmp_str = properties.toString();
		properties = null;
		if (tmp_str == null)
			return "";
		return tmp_str;
	}
	
	/**
	 * 清空事件
	 * 
	 */
	public void clearEvent() {
		on_blur = null;
		on_focus = null;
		on_key_down = null;
		on_key_up = null;
		on_key_press = null;
		on_click = null;
		on_dbl_click = null;
		on_mouse_down = null;
		on_mouse_up = null;
		on_mouse_move = null;
		on_mouse_over = null;
		on_mouse_out = null;
	}
}
