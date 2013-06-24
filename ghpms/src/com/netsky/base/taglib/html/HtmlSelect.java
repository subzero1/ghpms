package com.netsky.base.taglib.html;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.Properties;
import javax.servlet.jsp.tagext.BodyContent;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.utils.RegExp;


/**
 * 作者：李翔宇
 * 
 * 日期：2010-01-25
 * 
 * 描述：html里面的select标记类
 */
public class HtmlSelect extends ChangeableWidget {
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

	/**
	 * 用于选择的显示数据
	 */
	private String value_for_extend;
	
	/**
	 * 尺寸
	 */
	private int size = -1;

	/**
	 * 是多选的
	 */
	private boolean isMultiple = false;

	/**
	 * 扩展值
	 */
	private String extend;

	/**
	 * 扩展前置
	 */
	private boolean extendPrefix = false;

	/**
	 * 当前值
	 */
	private String value;
	
	/**
	 * 扩展属性
	 */
	private String extProperties;

	/**
	 * 标题
	 */
	private String title;

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

	public boolean isMultiple() {
		return isMultiple;
	}

	public void setMultiple(boolean isMultiple) {
		this.isMultiple = isMultiple;
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

	
	public String getValue_for_extend() {
		return value_for_extend;
	}

	public void setValue_for_extend(String value_for_extend) {
		this.value_for_extend = value_for_extend;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setTitle(String title) {
		this.title = title;
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
	 * 设置扩展项的值
	 */
	public void setValueForExtend(String value_for_extend) {
		this.value_for_extend = value_for_extend;
	}

	/**
	 * 获得扩展项的值
	 */
	public String getValueForExtend() {
		return value_for_extend;
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
	 * 设置尺寸
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * 获得尺寸
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 设置多选
	 */
	public void setMultiple(String value) {
		if ("TRUE".equalsIgnoreCase(value))
			isMultiple = true;
	}

	/**
	 * 获得多选
	 */
	public String getMultiple() {
		if (isMultiple)
			return "TRUE";
		return "FALSE";
	}

	/**
	 * 设置扩展值
	 */
	public void setExtend(String extend) {
		this.extend = extend;
	}

	/**
	 * 获得扩展值
	 */
	public String getExtend() {
		return extend;
	}

	/**
	 * 设置扩展前置信息
	 */
	public void setExtendPrefix(String value) {
		if ("true".equalsIgnoreCase(value))
			this.extendPrefix = true;
		else
			this.extendPrefix = false;
	}

	/**
	 * 获得扩展前置信息
	 */
	public String getExtendPrefix() {
		return String.valueOf(extendPrefix);
	}

	/**
	 * 获得选择值信息
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 获得标题信息
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置选择值信息
	 */
	public void setCurValue(String value) {
		this.value = value;
	}
	

	public String getExtProperties() {
		return extProperties;
	}

	/**
	 * 设置扩展属性
	 */
	public void setExtProperties(String extProperties) {
		this.extProperties = extProperties;
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

			/**
			 * 设置下拉列表扩展项（比如标题，说明）
			 */
			setHtmlProperties();
			pageContext.getOut().println("<select" + getHtmlProperties());
			if (title != null && title.equals("true")) {
				pageContext.getOut().println(
						" title=\"" + value_selected + "\" ");
			}
			pageContext.getOut().println(">");
			if (extend != null && extendPrefix) {
				String[] ex_array = extend.split(";");
				for (int i = 0; i < ex_array.length; i++) {
					String[] info = ex_array[i].split(",");
					sb_tmp.delete(0, sb_tmp.length());
					sb_tmp.append("<option value=\"");
					sb_tmp.append(info.length > 1 ? info[1] : "");
					sb_tmp.append("\" ");
					sb_tmp.append(info.length > 2 ? info[2] : "");
					sb_tmp.append(">");
					sb_tmp.append(info[0]);
					sb_tmp.append("</option>");
					pageContext.getOut().println(sb_tmp);
				}
			}

			/**
			 * 设置下拉列表具体的项
			 */
			boolean hasSelected = false;
			String value = "";
			if (obj != null) {
				
				String value_tmp = null;
				String show_tmp = null;
				
				/*
				 * 此处主要处理自定义属性valueForExtend="abc[xx]ddef[yy]g"
				 * xx yy 为objectForOption中对象的属性
				 * 将[xx][yy]替换为具体的属性值 [xx]=XX [yy]=YY
				 * 显示效果应该为<option value="a" valueForExtend="abcXXddefYYg">show</option>
				 */
				String extend_tmp = null;
				Vector tv = new Vector();
				if(value_for_extend != null){
					RegExp re = new RegExp();
					tv = re.pickupAll("\\[([a-zA-Z0-9]+)\\]+",value_for_extend,1);
				}
				
				for (it = objectList.iterator(); it.hasNext();) {

					extend_tmp = value_for_extend;
					Object tmp_obj = it.next();
					if (tmp_type.equals("Properties")) {
						Properties p = (Properties) tmp_obj;
						show_tmp = p.getProperty("show");
						value_tmp = p.getProperty("value");
						if(value_for_extend != null){
							extend_tmp = p.getProperty("extend");
						}
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
						if(value_for_extend != null){
							for(int i = 0;i < tv.size();i++){
								extend_tmp = extend_tmp.replace("["+(String)tv.get(i)+"]",convertUtil.toString(PropertyInject.getProperty(tmp_obj, (String)tv.get(i))));
							}
						}
					}

					if (value_tmp == null)
						value_tmp = "";
					if (show_tmp == null)
						show_tmp = "";
					if (value_tmp.equals("-1"))
						value_tmp = "";
					sb_tmp.delete(0, sb_tmp.length());
					sb_tmp.append("<option  title=\""+value_tmp+"\" value=\"");
					sb_tmp.append(value_tmp);
					sb_tmp.append("\"");
					if (value_selected.equals(value_tmp)
							|| ("-1".equals(value_selected) && ""
									.equals(value_tmp))) {
						hasSelected = true;
						sb_tmp.append(" selected");
						value = value_tmp;
						setValue(value);
					}
					
					if(value_for_extend != null){
						sb_tmp.append(" valueForExtend=\"");
						sb_tmp.append(extend_tmp);
						sb_tmp.append("\" ");
					}

					sb_tmp.append(">");
					sb_tmp.append(show_tmp);
					sb_tmp.append("</option>");
					pageContext.getOut().println(sb_tmp);
				}
			}
			if (extend != null && !extendPrefix) {
				String[] ex_array = extend.split(";");
				for (int i = 0; i < ex_array.length; i++) {
					String[] info = ex_array[i].split(",");
					sb_tmp.delete(0, sb_tmp.length());
					sb_tmp.append("<option value=\"");
					sb_tmp.append(info.length > 1 ? info[1] : "");
					sb_tmp.append("\" ");
					if (!hasSelected)
						sb_tmp.append(info.length > 2 ? info[2] : "");
					sb_tmp.append(">");
					sb_tmp.append(info[0]);
					sb_tmp.append("</option>");
					pageContext.getOut().println(sb_tmp);
				}
			}
			pageContext.getOut().println("</select>");
			if ("HtmlSelect".equalsIgnoreCase(name)) {
				pageContext.getOut().println(
						"<input type=\"hidden\" name=\"" + getName()
								+ "\" value=\"" + value + "\">");
			}
		}
	}

	protected void setHtmlProperties() {

		if (id != null)
			appendHtmlProperties("id=\"" + id + "\"");
		if (name != null)
			appendHtmlProperties("name=\"" + name + "\"");
		if (isDisabled != null
				&& (isDisabled.equals("true") || isDisabled.equals("disabled")))
			appendHtmlProperties(" disabled ");
		if (size != -1)
			appendHtmlProperties("size=\"" + size + "\"");
		if (isMultiple)
			appendHtmlProperties(" multiple ");
		if(extProperties != null && !extProperties.equals("")){
			if(extProperties.indexOf(";") != -1){
				String[] tp = extProperties.split(";");
				for(int i = 0;i < tp.length;i++){
					if(tp[i].indexOf(":") != -1){
						String[] tp2 = tp[i].split(":");
						appendHtmlProperties(" "+tp2[0]+"=\"" + tp2[1] + "\" ");
					}
					else{
						appendHtmlProperties(" "+tp[i]+"=\"" + tp[i] + "\" ");
					}
				}
			}
			else if(extProperties.indexOf(":") != -1){
				String[] tp = extProperties.split(":");
				appendHtmlProperties(" "+tp[0]+"=\"" + tp[1] + "\" ");
			}
			else{
				appendHtmlProperties(" "+extProperties+"=\"" + extProperties + "\" ");
			}
		}

		super.setHtmlProperties();

	}
}
