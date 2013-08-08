package com.netsky.base.taglib.html;

import java.util.Iterator;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.servlet.jsp.tagext.BodyContent;

import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.utils.convertUtil;


/**
 * 作者：李翔宇
 * 
 * 日期：2010-01-25
 * 
 * 描述：html里面的select标记类
 */
public class HtmlCheckbox extends BodyTagSupport {
	/**
	 * 默认的版本标识
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 组件ID
	 */
	private String id;

	/**
	 * 组件名称
	 */
	private String name;

	/**
	 * 是否可用
	 */
	private String isDisabled;

	/**
	 * 用于选择的对象名
	 */
	private String object_for_option;

	/**
	 * 用于选择的列名
	 */
	private String value_for_option;

	/**
	 * 用于选择的显示数据
	 */
	private String show_for_option;
	
	private String style;
	
	private String htmlClass;
	
	private String separtor;

	/**
	 * 当前值
	 */
	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(String isDisabled) {
		this.isDisabled = isDisabled;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObject_for_option() {
		return object_for_option;
	}

	public void setObject_for_option(String object_for_option) {
		this.object_for_option = object_for_option;
	}

	public String getShow_for_option() {
		return show_for_option;
	}

	public void setShow_for_option(String show_for_option) {
		this.show_for_option = show_for_option;
	}

	public String getValue_for_option() {
		return value_for_option;
	}

	public void setValue_for_option(String value_for_option) {
		this.value_for_option = value_for_option;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getSepartor() {
		return separtor;
	}

	public void setSepartor(String separtor) {
		this.separtor = separtor;
	}

	/**
	 * 设置数据对象名
	 */
	public void setObjectForOption(String object_for_option) {
		this.object_for_option = object_for_option;
	}

	/**
	 * 获得数据对象名
	 */
	public String getObjectForOption() {
		return object_for_option;
	}

	/**
	 * 设置选项的值
	 */
	public void setValueForOption(String value_for_option) {
		this.value_for_option = value_for_option;
	}

	/**
	 * 获得选项的值
	 */
	public String getValueForOption() {
		return value_for_option;
	}

	/**
	 * 设置选项的显示信息
	 */
	public void setShowForOption(String show_for_option) {
		this.show_for_option = show_for_option;
	}

	/**
	 * 获得选项的显示信息
	 */
	public String getShowForOption() {
		return show_for_option;
	}


	/**
	 * 获得选择值信息
	 */
	public String getValue() {
		return value;
	}


	/**
	 * 设置选择值信息
	 */
	public void setCurValue(String value) {
		this.value = value;
	}
	
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getHtmlClass() {
		return htmlClass;
	}

	public void setHtmlClass(String htmlClass) {
		this.htmlClass = htmlClass;
	}

	/**
	 * 标记结束
	 */
	public int doEndTag() throws javax.servlet.jsp.JspException {
		try {
			printSelect();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return EVAL_PAGE;
	}

	/**
	 * 标记打印
	 */
	public void printSelect() throws Exception {

		Object obj = null;
		List<Object> objectList = null;
		Iterator<?> it = null;
		String tmp_type = null;

		if (object_for_option != null && getName() != null) {
			/*
			 * 从request中取数据
			 */
			obj = pageContext.getRequest().getAttribute(object_for_option);
			if(obj == null){
				/*
				 * 从session中取数据
				 */
				obj = pageContext.getSession().getAttribute(object_for_option);
				if(obj == null){
					/*
					 * 从servletContext中取数据
					 */
					obj = pageContext.getServletContext().getAttribute(object_for_option);
				}
			}
			
			
			if(obj instanceof Object[]){
				Object[] objArray = (Object[])obj;
				objectList = new LinkedList();
				for(Object tmpObj:objArray) objectList.add(tmpObj);
				obj = objectList;
			}
			
			if (obj instanceof List) {
				/**
				 * 判断列表类型，并判断列表中对象的类型
				 */
				objectList = (List<Object>) obj;
				it = objectList.iterator();
				if(it.hasNext()){
					Object tmp_obj = it.next();
					if (tmp_obj instanceof Properties) {
						tmp_type = "Properties";
					} 
					else if (tmp_obj instanceof String) {
						tmp_type = "String";
					} 
					else if (tmp_obj instanceof Long) {
						tmp_type = "Long";
					} 
					else if (tmp_obj instanceof Integer) {
						tmp_type = "Integer";
					}
					else{
						tmp_type = "other";
					}
				}
				else{
					obj = null;
				}
			} else {
				obj = null;
			}

			String value_selected = this.value;
			StringBuffer sb_tmp = new StringBuffer();
			if (value_selected == null || "".equals(value_selected)) {
				BodyContent content = getBodyContent();
				if (content != null && content.getString() != null
						&& !"".equals(content.getString()))
					value_selected = content.getString();
				else
					value_selected = "";
			}
			
			String[] value_list = null;
			if(value_selected != null){
				if(convertUtil.toString(separtor).equals("")){
					separtor = ",";
				}
				value_list = value_selected.split(separtor);
			}

			/**
			 * 设置下拉列表具体的项
			 */
			if (obj != null) {
				
				String value_tmp = null;
				String show_tmp = null;
				
				for (it = objectList.iterator(); it.hasNext();) {

					Object tmp_obj = it.next();
					if (tmp_type.equals("Properties")) {
						Properties p = (Properties) tmp_obj;
						show_tmp = p.getProperty("show");
						value_tmp = p.getProperty("value");
					} else if (tmp_type.equals("String")) {
						show_tmp = tmp_obj.toString();
						value_tmp = tmp_obj.toString();
					} else if (tmp_type.equals("Integer")) {
						show_tmp = tmp_obj.toString();
						value_tmp = tmp_obj.toString();
					} else if (tmp_type.equals("Long")) {
						show_tmp = tmp_obj.toString();
						value_tmp = tmp_obj.toString();
					} else {
						show_tmp =  convertUtil.toString(PropertyInject.getProperty(tmp_obj,
								show_for_option));
						value_tmp = convertUtil.toString(PropertyInject.getProperty(
								tmp_obj, value_for_option));
					}

					if (value_tmp == null)
						value_tmp = "";
					if (show_tmp == null)
						show_tmp = "";
					if (value_tmp.equals("-1"))
						value_tmp = "";
					sb_tmp.delete(0, sb_tmp.length());
					sb_tmp.append("<input onclick=\"selectCheckboxFunc('"+getName()+"')\" type=\"checkbox\" value=\"");
					sb_tmp.append(value_tmp);
					sb_tmp.append("\" ");
					if(getId() != null){
						sb_tmp.append(" id=\"_");
						sb_tmp.append(getName());
						sb_tmp.append("\"");
					}
					if(getName() != null){
						sb_tmp.append(" name=\"_");
						sb_tmp.append(getName());
						sb_tmp.append("\"");
					}
					if(getStyle() != null){
						sb_tmp.append(" style=\"_");
						sb_tmp.append(getStyle());
						sb_tmp.append("\"");
					}
					if(getHtmlClass() != null){
						sb_tmp.append(" class=\"_");
						sb_tmp.append(getHtmlClass());
						sb_tmp.append("\"");
					}
					
					if (value_list != null) {
						for(int ti = 0;ti < value_list.length;ti++){
							if(value_list[ti].equals(value_tmp)){
								sb_tmp.append(" checked");
							}
						}
					}
					sb_tmp.append("/>");
					sb_tmp.append(show_tmp);
					sb_tmp.append("&nbsp;&nbsp;");
					pageContext.getOut().println(sb_tmp);
				}
			}
			pageContext.getOut().println("<input type=\"hidden\" name=\"" + getName() + "\" id=\"" + getId() + "\" value=\"" + getValue() + "\">");
		}
	}
}
