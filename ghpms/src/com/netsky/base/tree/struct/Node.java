package com.netsky.base.tree.struct;

/**
 * @author Chiang
 * 
 * 节点显示类
 */
public class Node {

	/**
	 * 节点宽度
	 */
	private String width;

	/**
	 * 节点高度
	 */
	private String height;

	/**
	 * x坐标
	 */
	private String x;

	/**
	 * y坐标
	 */
	private String y;

	/**
	 * 样式表
	 */
	private String htmlClass;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * javascript function params
	 */
	private String param;

	/**
	 * 是否显示js函数
	 */
	private boolean jsFlag;

	/**
	 * @return the width
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @return the x
	 */
	public String getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(String x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public String getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(String y) {
		this.y = y;
	}

	/**
	 * @return the htmlClass
	 */
	public String getHtmlClass() {
		return htmlClass;
	}

	/**
	 * @param htmlClass
	 *            the htmlClass to set
	 */
	public void setHtmlClass(String htmlClass) {
		this.htmlClass = htmlClass;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the param
	 */
	public String getParam() {
		return param;
	}

	/**
	 * @param param
	 *            the param to set
	 */
	public void setParam(String param) {
		this.param = param;
	}

	/**
	 * @return the jsFlag
	 */
	public boolean isJsFlag() {
		return jsFlag;
	}

	/**
	 * @param jsFlag
	 *            the jsFlag to set
	 */
	public void setJsFlag(boolean jsFlag) {
		this.jsFlag = jsFlag;
	}

}
