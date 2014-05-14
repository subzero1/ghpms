package com.ghpms.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghpms.service.CreateJspFile;
import com.ghpms.service.GcsjDataService;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Ta07_formfield;
import com.netsky.base.dataObjects.Ta11_sta_user;
import com.netsky.base.dataObjects.Tb02_node;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.StringFormatUtil;
import com.netsky.base.utils.convertUtil;

@Service("createJspFile")
public class CreateJspFileImpl implements CreateJspFile {
	@Autowired
	private QueryService queryService;
	@Autowired
	private SaveService saveService;

	@Autowired
	private GcsjDataService gcsjDataService;

	/**
	 * 获取本人对应的节点列表
	 * 外协单位涉及节点,录入字段列示时,先判断是否为本单做的. 如施工单位,判断本工程是否为本单,如果是本单位才列示,否则不列示.
	 *　重载方法：getNode
	 * (non-Javadoc)
	 * @see com.ghpms.service.CreateJspFile#getNode(javax.servlet.http.HttpServletRequest, java.util.Map)
	 */
	public void getNode(HttpServletRequest request, Map paraMap) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException  {
		StringBuffer hsql = new StringBuffer();
		String t_module_id = StringFormatUtil.format((String) paraMap
				.get("module_id"), "-1");
		
		
		Long module_id = convertUtil.toLong(t_module_id);
		Ta06_module module_obj = ((Ta06_module)queryService.searchById(Ta06_module.class, module_id));
		Object dataObject = queryService.searchById(Class.forName(module_obj.getForm_table()), MapUtil.getLong(paraMap, "project_id"));
		
		Ta03_user user = (Ta03_user) (request.getSession().getAttribute("user"));
		hsql
				.append("select distinct(d.id),d.name,d.flow_id,d.node_type,d.remark ");
		hsql
				.append(" from Ta03_user a,Ta02_station b,Ta11_sta_user c,Tb02_node d,Ta13_sta_node e ");
		hsql
				.append("where a.id=c.user_id and b.id=c.station_id and d.id=e.node_id and e.station_id=b.id ");
		hsql.append(" and a.id=");
		hsql.append(user.getId());
		hsql.append(" and d.flow_id=");
		hsql.append(module_id);
		//
		hsql.append(" and d.node_type=1 ");
		String wxdw=user.getWxdw();
		
		List recordButtons = queryService.searchList(hsql.toString());
		List nodeMaps = new ArrayList();
		for (Object object : recordButtons) {
			Object[] node = (Object[]) object;
			String field_name ="";
			if(node[1].toString().indexOf("单位")> 1){
				String node_name=node[1].toString();
				List list=queryService.searchList( "select name from Ta07_formfield where comments like '" +node_name + "%' "+" and module_id="+module_id);
				if (list!=null&&list.size()==1) {
					field_name =(String) list.get(0);
					if(!PropertyInject.getProperty(dataObject, field_name).equals(wxdw)){
						continue;
					}
					
				}
				if (list!=null&&list.size()>0) {
					if(!PropertyInject.getProperty(dataObject, field_name).equals(wxdw)){
						continue;
					}
				}
				
			}
			Map nodeMap = new HashMap();
			nodeMap.put("editURL", "gcsj/gcsjEdit.do?node_id=" + node[0]
					+ "&module_id=" + module_id);
			nodeMap.put("node_id", node[0]);
			nodeMap.put("node_name", node[1]);
			nodeMap.put("title", node[4]);
			nodeMaps.add(nodeMap);
		}
		request.setAttribute("nodeMaps", nodeMaps);
	}

	public void AutoCreateJspFile(HttpServletRequest request, Long module_id) {
		StringBuffer hsql = new StringBuffer();
		String filePath = "";

		hsql.append("select a from Ta07_formfield a where 1=1 ");
		hsql.append(" and a.module_id=");
		hsql.append(module_id);
		hsql.append(" and a.name <> 'id' ");
		hsql.append(" order by a.ord");
		List fields = queryService.searchList(hsql.toString());

		filePath = request.getSession().getServletContext().getRealPath(
				"/WEB-INF");
		Ta06_module module = (Ta06_module) queryService.searchById(
				Ta06_module.class, module_id);
		filePath += "\\jsp\\" + module.getForm_url();

		hsql.delete(0, hsql.length());
		hsql
				.append("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>");
		hsql.append(" \n ");
		hsql
				.append("<%@ taglib prefix=\"fmt\" uri=\"http://java.sun.com/jsp/jstl/fmt\"%>");
		hsql.append(" \n ");
		hsql
				.append("<jsp:include page=\"basicForm.jsp\"  flush=\"true\"></jsp:include>");
		hsql.append(" \n ");
		Queue<Ta07_formfield> fieldQueue1 = new LinkedList<Ta07_formfield>();
		Queue<Ta07_formfield> fieldQueue2 = new LinkedList<Ta07_formfield>();
		for (int i = 0; i < fields.size(); i++) {
			Ta07_formfield formfield = (Ta07_formfield) fields.get(i);

			// 如果文本框沒湊夠兩個一排,進入队列1
			if (fieldQueue1.size() % 2 == 1) {
				if (formfield.getDatalength() > 200) {
					fieldQueue2.offer(formfield);
					continue;
				} else {
					fieldQueue1.offer(formfield);
				}
			} // 如果是偶数
			else {
				if (fieldQueue2.size() > 0) {
					formfield = fieldQueue2.poll();
				} else {
					if (formfield.getDatalength() < 200) {
						fieldQueue1.offer(formfield);
					}
				}
			}

			// 文本域
			if (formfield.getData_type() != null
					&& (formfield.getData_type() == 2 || formfield
							.getDatalength() > 1000)) {
				// 文本域换行
				hsql.append("<div style=\"height:0px;\"></div>");
				hsql.append(" <p> \n");
				hsql.append("<label> " + formfield.getComments()
						+ "：</label> \n");
				hsql.append("<textarea ");
				hsql.append(" style=\"width:619px;height:70px;\" readonly>");
				hsql.append("${");
				hsql.append(formfield.getObject_name().substring(
						formfield.getObject_name().lastIndexOf(".") + 1,
						formfield.getObject_name().length()).toLowerCase());
				hsql.append("." + formfield.getName() + "} ");
				hsql.append("</textarea>");
				hsql.append("\n </p> \n");
				hsql.append("<div style=\"height:0px;\"></div> \n");
			} else {
				if (formfield.getDatalength() > 250) {
					hsql.append("<div style=\"height:0px;\"></div>");
				}
				hsql.append(" <p> \n");
				hsql.append("<label> " + formfield.getComments()
						+ "：</label> \n");

				hsql.append("<input type=\"text\" ");
				hsql.append(" value=\"");
				// 如果是日期
				if (formfield.getDatatype().equals("DATE")) {
					hsql.append("<fmt:formatDate value=\"");
				}
				hsql.append("${");
				hsql.append(formfield.getObject_name().substring(
						formfield.getObject_name().lastIndexOf(".") + 1,
						formfield.getObject_name().length()).toLowerCase());
				hsql.append("." + formfield.getName() + "}\" ");

				// 如果是日期
				if (formfield.getDatatype().equals("DATE")) {
					hsql.append(" pattern=\"yyyy-MM-dd \"/>\" ");
				}
				// 长文本框
				if (formfield.getDatalength() > 200
						&& formfield.getDatalength() < 500) {
					hsql.append("style=\"width:619px;\" readonly/>");
				}
				// 短文本框
				else {
					hsql.append("style=\"width:256px;\" readonly/>");
				}

				hsql.append("\n </p> \n");
				if (fieldQueue1.size() % 2 == 0) {
					hsql.append("<div style=\"height:0px;\"></div> \n");
				}
			}
		}
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			Writer out = new OutputStreamWriter(fos, "utf-8");
			out.write(hsql.toString());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 生成表单查看文件
	 *　重载方法：AutoCreateJspFile
	 * (non-Javadoc)
	 * @see com.ghpms.service.CreateJspFile#AutoCreateJspFile(java.lang.String, java.lang.Long)
	 */
	public void AutoCreateJspFile(String path, Long module_id) {
		StringBuffer hsql = new StringBuffer();

		hsql.append("select a from Ta07_formfield a where 1=1 ");
		hsql.append(" and a.module_id=");
		hsql.append(module_id);
		hsql.append(" and a.name <> 'id' ");
		hsql.append(" order by a.ord");
		List fields = queryService.searchList(hsql.toString());

		Ta06_module module = (Ta06_module) queryService.searchById(
				Ta06_module.class, module_id);
		path += "\\jsp\\" + module.getForm_url();

		hsql.delete(0, hsql.length());
		hsql
				.append("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>");
		hsql.append(" \n ");
		hsql
				.append("<%@ taglib prefix=\"fmt\" uri=\"http://java.sun.com/jsp/jstl/fmt\"%>");
		hsql.append(" \n ");
		hsql
				.append("<jsp:include page=\"basicForm.jsp\"  flush=\"true\"></jsp:include>");
		hsql.append(" \n ");
		Queue<Ta07_formfield> fieldQueue1 = new LinkedList<Ta07_formfield>();
		Queue<Ta07_formfield> fieldQueue2 = new LinkedList<Ta07_formfield>();
		for (int i = 0; i < fields.size(); i++) {
			Ta07_formfield formfield = (Ta07_formfield) fields.get(i);

			// 如果文本框沒湊夠兩個一排,進入队列1
			if (fieldQueue1.size() % 2 == 1) {
				if (formfield.getDatalength() > 200) {
					fieldQueue2.offer(formfield);
					continue;
				} else {
					fieldQueue1.offer(formfield);
				}
			} // 如果是偶数
			else {
				if (fieldQueue2.size() > 0) {
					formfield = fieldQueue2.poll();
				} else {
					if (formfield.getDatalength() < 200) {
						fieldQueue1.offer(formfield);
					}
				}
			}

			// 文本域
			if (formfield.getData_type() != null
					&& (formfield.getData_type() == 2 || formfield
							.getDatalength() > 1000)) {
				// 文本域换行
				hsql.append("<div style=\"height:0px;\"></div>");
				hsql.append(" <p> \n");
				hsql.append("<label> " + formfield.getComments()
						+ "：</label> \n");
				hsql.append("<textarea ");
				hsql.append(" name=\"");
				hsql.append(formfield.getObject_name().substring(
						formfield.getObject_name().lastIndexOf(".") + 1,
						formfield.getObject_name().length()));
				hsql.append("." + formfield.getName().toUpperCase());
				hsql.append("\"");
				hsql.append(" style=\"width:619px;height:70px;\" readonly>");
				hsql.append("${");
				hsql.append(formfield.getObject_name().substring(
						formfield.getObject_name().lastIndexOf(".") + 1,
						formfield.getObject_name().length()).toLowerCase());
				hsql.append("." + formfield.getName() + "} ");
				hsql.append("</textarea>");
				hsql.append("\n </p> \n");
				hsql.append("<div style=\"height:0px;\"></div> \n");
			} else {
				if (formfield.getDatalength() > 250) {
					hsql.append("<div style=\"height:0px;\"></div>");
				}
				hsql.append(" <p> \n");
				hsql.append("<label> " + formfield.getComments()
						+ "：</label> \n");

				hsql.append("<input type=\"text\" ");
				hsql.append(" name=\"");
				hsql.append(formfield.getObject_name().substring(
						formfield.getObject_name().lastIndexOf(".") + 1,
						formfield.getObject_name().length()));
				hsql.append("." + formfield.getName().toUpperCase());
				hsql.append("\"");
				hsql.append(" value=\"");
				// 如果是日期
				if (formfield.getDatatype().equals("DATE")) {
					hsql.append("<fmt:formatDate value=\"");
				}
				hsql.append("${");
				hsql.append(formfield.getObject_name().substring(
						formfield.getObject_name().lastIndexOf(".") + 1,
						formfield.getObject_name().length()).toLowerCase());
				hsql.append("." + formfield.getName() + "}\" ");

				// 如果是日期
				if (formfield.getDatatype().equals("DATE")) {
					hsql.append(" pattern=\"");
					if (formfield.getFormat() != null
							&& formfield.getData_type() == 5) {
						hsql.append(formfield.getFormat());
					} else {
						hsql.append("yyyy-MM-dd");
					}

					hsql.append(" \"/>\" ");
				}
				// 长文本框
				if (formfield.getDatalength() > 200
						&& formfield.getDatalength() < 500) {
					hsql.append("style=\"width:619px;\" readonly/>");
				}
				// 短文本框
				else {
					hsql.append("style=\"width:256px;\" readonly/>");
				}

				hsql.append("\n </p> \n");
				if (fieldQueue1.size() % 2 == 0) {
					hsql.append("<div style=\"height:0px;\"></div> \n");
				}
			}
		}
		try {
			FileOutputStream fos = new FileOutputStream(path);
			Writer out = new OutputStreamWriter(fos, "utf-8");
			out.write(hsql.toString());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void createJspFileToRecord(HttpServletRequest request, Long node_id) {
		StringBuffer hsql = new StringBuffer();
		String filePath = "";

		String tableName = "";
		String packTableName = "";

		hsql
				.append("select a from Ta07_formfield a,Tb02_node b,Ta16_node_field c where a.id=c.field_id and b.id=c.node_id ");
		hsql.append(" and b.id=");
		hsql.append(node_id);
		hsql.append(" order by  a.ord");
		List fields = queryService.searchList(hsql.toString());

		if (fields.size() > 0) {
			tableName = ((Ta07_formfield) fields.get(0)).getObject_name();
			packTableName = tableName.substring(tableName.lastIndexOf(".") + 1,
					tableName.length());
		}
		filePath = request.getSession().getServletContext().getRealPath(
				"/WEB-INF");
		filePath += "\\jsp\\form\\" + node_id + ".jsp";

		hsql.delete(0, hsql.length());
		hsql
				.append("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>");
		hsql.append(" \n ");
		hsql
				.append("<%@ taglib prefix=\"fmt\" uri=\"http://java.sun.com/jsp/jstl/fmt\"%>");
		hsql.append(" \n ");
		hsql.append("<%@ taglib uri=\"NetSkyTagLibs\" prefix=\"netsky\"%>");
		hsql.append(" \n ");
		hsql
				.append("<jsp:include page=\"basicEdit.jsp\"  flush=\"true\"></jsp:include>");
		hsql.append(" \n ");
		hsql
				.append("<input type=\"hidden\" name=\"tableInfomation\" value=\"noFatherTable:"
						+ tableName + "\" /> \n");
		hsql.append("<input type=\"hidden\" name=\"" + packTableName
				+ ".ID\" value=\"${param.project_id}\">");
		for (int i = 0; i < fields.size(); i++) {
			Ta07_formfield formfield = (Ta07_formfield) fields.get(i);
			hsql.append(" <p> \n");
			hsql.append("<label> " + formfield.getComments() + "：</label> \n");
			// 下拉框的情況
			if (formfield.getData_type() != null
					&& formfield.getData_type() == 1) {
				hsql.append("<netsky:htmlSelect name=\"" + packTableName);
				hsql.append(".");
				hsql.append(formfield.getName().toUpperCase());
				hsql.append("\" ");
				hsql.append(" objectForOption=\"" + formfield.getName()
						+ "\" style=\"width:256px;\"");
				hsql.append(" valueForOption=\"name\" showForOption=\"name\" ");
				hsql.append("extend=\"\" extendPrefix=\"true\" value=\"");
				hsql.append("${");
				hsql.append(formfield.getObject_name().substring(
						formfield.getObject_name().lastIndexOf(".") + 1,
						formfield.getObject_name().length()).toLowerCase());
				hsql.append("." + formfield.getName() + "}\" ");
				hsql.append(" htmlClass=\"td-select\"/>");
				// 文本域 data_type=2
			} else if (formfield.getData_type() != null
					&& (formfield.getData_type() == 2 || formfield
							.getDatalength() > 1000)) {

				hsql.append("<textarea ");
				hsql.append(" name=\"");
				hsql.append(packTableName);
				hsql.append(".");
				hsql.append(formfield.getName().toUpperCase());
				hsql.append("\" ");
				hsql.append("style=\"width:256px;height:70px;\" >");

				hsql.append("${");
				hsql.append(formfield.getObject_name().substring(
						formfield.getObject_name().lastIndexOf(".") + 1,
						formfield.getObject_name().length()).toLowerCase());
				hsql.append("." + formfield.getName() + "} ");
				hsql.append("</textarea>");

			} else {
				// 非下拉框
				hsql.append("<input type=\"text\" ");
				hsql.append(" name=\"");
				hsql.append(packTableName);
				hsql.append(".");
				hsql.append(formfield.getName().toUpperCase());
				hsql.append("\" ");
				hsql.append("value=\"");
				// 如果是日期
				if (formfield.getDatatype().equals("DATE")) {
					hsql.append("<fmt:formatDate value=\"");
				}
				hsql.append("${");
				hsql.append(formfield.getObject_name().substring(
						formfield.getObject_name().lastIndexOf(".") + 1,
						formfield.getObject_name().length()).toLowerCase());
				hsql.append("." + formfield.getName() + "}\" ");
				// 如果是日期
				if (formfield.getDatatype().equals("DATE")) {
					hsql.append(" pattern=\"yyyy-MM-dd \"/>\" ");
				}
				// 判断类型
				if (formfield.getDatatype().equals("DATE")) {
					hsql
							.append("class=\"date\" format=\"yyyy-MM-dd \" yearstart=\"-50\" yearend=\"50\"");
				}
				hsql.append("style=\"width:256px;\" />");
			}

			hsql.append("\n </p> \n");
			if (i % 2 == 0) {
				hsql.append("<div style=\"height:0px;\"></div> \n");
			}

		}
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			Writer out = new OutputStreamWriter(fos, "utf-8");
			out.write(hsql.toString());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 录入界面
	 *　重载方法：createJspFileToRecord
	 * (non-Javadoc)
	 * @see com.ghpms.service.CreateJspFile#createJspFileToRecord(java.lang.String, java.lang.Long)
	 */
	public void createJspFileToRecord(String path, Long node_id) {
		StringBuffer hsql = new StringBuffer();

		String tableName = "";
		String packTableName = "";

		hsql
				.append("select a from Ta07_formfield a,Ta16_node_field c where a.id=c.field_id");
		hsql.append(" and c.node_id=");
		hsql.append(node_id);
		hsql.append(" and (c.node_type=1 or c.node_type is null) ");
		hsql.append(" order by c.id,  a.ord");
		List fields = queryService.searchList(hsql.toString());

		if (fields.size() > 0) {
			tableName = ((Ta07_formfield) fields.get(0)).getObject_name();
			packTableName = tableName.substring(tableName.lastIndexOf(".") + 1,
					tableName.length());
		}
		path += "\\jsp\\form\\" + node_id + ".jsp";

		hsql.delete(0, hsql.length());
		hsql
				.append("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>");
		hsql.append(" \n ");
		hsql
				.append("<%@ taglib prefix=\"fmt\" uri=\"http://java.sun.com/jsp/jstl/fmt\"%>");
		hsql.append(" \n ");
		hsql.append("<%@ taglib uri=\"NetSkyTagLibs\" prefix=\"netsky\"%>");
		hsql.append(" \n ");
		hsql
				.append("<jsp:include page=\"basicEdit.jsp\"  flush=\"true\"></jsp:include>");
		hsql.append(" \n ");
		hsql
				.append("<input type=\"hidden\" name=\"tableInfomation\" value=\"noFatherTable:"
						+ tableName + "\" /> \n");
		hsql.append("<input type=\"hidden\" name=\"" + packTableName
				+ ".ID\" value=\"${param.project_id}\">");
		for (int i = 0; i < fields.size(); i++) {
			Ta07_formfield formfield = (Ta07_formfield) fields.get(i);
			hsql.append(" <p> \n");
			hsql.append("<label> " + formfield.getComments() + "：</label> \n");
			// 下拉框的情況
			if (formfield.getData_type() != null
					&& (formfield.getData_type() == 1 || formfield
							.getData_type() == 4)) {
				hsql.append("<netsky:htmlSelect name=\"" + packTableName);
				hsql.append(".");
				hsql.append(formfield.getName().toUpperCase());
				hsql.append("\" ");
				hsql.append(" objectForOption=\"" + formfield.getName()
						+ "\" style=\"width:256px;\"");
				hsql.append(" valueForOption=\"name\" showForOption=\"name\" ");
				hsql.append("extend=\"\" extendPrefix=\"true\" value=\"");
				hsql.append("${");
				hsql.append(formfield.getObject_name().substring(
						formfield.getObject_name().lastIndexOf(".") + 1,
						formfield.getObject_name().length()).toLowerCase());
				hsql.append("." + formfield.getName() + "}\" ");
				hsql.append(" htmlClass=\"td-select\"/>");
				// 文本域 data_type=2
			} else if (formfield.getData_type() != null
					&& (formfield.getData_type() == 2 || formfield
							.getDatalength() > 1000)) {

				hsql.append("<textarea ");
				hsql.append(" name=\"");
				hsql.append(packTableName);
				hsql.append(".");
				hsql.append(formfield.getName().toUpperCase());
				hsql.append("\" ");
				hsql.append("style=\"width:256px;height:70px;\" >");

				hsql.append("${");
				hsql.append(formfield.getObject_name().substring(
						formfield.getObject_name().lastIndexOf(".") + 1,
						formfield.getObject_name().length()).toLowerCase());
				hsql.append("." + formfield.getName() + "} ");
				hsql.append("</textarea>");

			} else {
				// 非下拉框
				hsql.append("<input type=\"text\" ");
				hsql.append(" name=\"");
				hsql.append(packTableName);
				hsql.append(".");
				hsql.append(formfield.getName().toUpperCase());
				hsql.append("\" ");
				hsql.append("value=\"");
				// 如果是日期
				if (formfield.getDatatype().equals("DATE")) {
					hsql.append("<fmt:formatDate value=\"");
				}
				hsql.append("${");
				hsql.append(formfield.getObject_name().substring(
						formfield.getObject_name().lastIndexOf(".") + 1,
						formfield.getObject_name().length()).toLowerCase());
				hsql.append("." + formfield.getName() + "}\" ");
				// 如果是日期
				if (formfield.getDatatype().equals("DATE")) {
					hsql.append(" pattern=\"");
					if (formfield.getFormat() != null
							&& formfield.getData_type() == 5) {
						hsql.append(formfield.getFormat());
					} else {
						hsql.append("yyyy-MM-dd ");
					}

					hsql.append("\"/>\" ");
				}
				// 判断类型
				if (formfield.getDatatype().equals("DATE")) {
					hsql.append("class=\"date\" format=\"");
					if (formfield.getFormat() != null
							&& formfield.getData_type() == 5) {
						hsql.append(formfield.getFormat());
					} else {
						hsql.append("yyyy-MM-dd ");
					}
					hsql.append("\" yearstart=\"-50\" yearend=\"50\"");
				} else if (formfield.getDatatype().equals("NUMBER")) {
					if (convertUtil.toString(formfield.getDatalength())
							.lastIndexOf(".0") != -1) {
						hsql.append("class=\"number\" ");
					} else {
						hsql.append("class=\"digits\" ");
					}

				}
				hsql.append(" style=\"width:256px;\" />");
			}

			hsql.append("\n </p> \n");
			if (i % 2 == 0) {
				hsql.append("<div style=\"height:0px;\"></div> \n");
			}

		}
		try {
			FileOutputStream fos = new FileOutputStream(path);
			Writer out = new OutputStreamWriter(fos, "utf-8");
			out.write(hsql.toString());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 *　重载方法：createJspFileToForm (non-Javadoc)
	 * 
	 * @see com.ghpms.service.CreateJspFile#createJspFileToForm(java.lang.String,
	 *      java.lang.Long)
	 */
	public void createJspFileToForm(String path, Long module_id) {
		StringBuffer hsql = new StringBuffer();
		Ta06_module module = null;
		List userList = queryService
				.searchList("select ta03 from Ta03_user ta03 order by ta03.name");
		for (Object object : userList) {
			hsql.delete(0, hsql.length());
			Ta03_user user = (Ta03_user) object;
			module = (Ta06_module) queryService.searchById(Ta06_module.class,
					module_id);
//			path += "\\jsp\\form\\" + module.getForm_name().toLowerCase() + "_"
//					+ user.getId() + ".jsp";

			hsql
					.append(" select ta07 from  Ta07_formfield ta07 where ta07.id in");
			hsql
					.append("(select ta16.field_id from Ta16_node_field ta16,Tb02_node tb02,Ta13_sta_node ta13, Ta02_station ta02 ,Ta03_user ta03,Ta11_sta_user ta11 where 1=1  and  ta16.node_id=tb02.id and tb02.id=ta13.node_id and ta13.station_id=ta02.id and ta03.id=ta11.user_id and ta02.id=ta11.station_id and ta16.node_type=2 ");
			hsql.append(" and ta03.id=");
			hsql.append(user.getId());
			hsql.append(")");
			hsql.append(" and ta07.name <> 'id' ");
			hsql.append(" and ta07.module_id=");
			hsql.append(module.getId());
			hsql.append(" order by ta07.ord");
			List fields = queryService.searchList(hsql.toString());
			if (fields == null || fields.size() == 0) {
				hsql.delete(0, hsql.length());
				hsql.append("select a from Ta07_formfield a where 1=1 ");
				hsql.append(" and a.module_id=");
				hsql.append(module.getId());
				hsql.append(" and a.name <> 'id' ");
				hsql.append(" order by a.ord");
				fields = queryService.searchList(hsql.toString());
			}

			hsql.delete(0, hsql.length());
			hsql
					.append("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>");
			hsql.append(" \n ");
			hsql
					.append("<%@ taglib prefix=\"fmt\" uri=\"http://java.sun.com/jsp/jstl/fmt\"%>");
			hsql.append(" \n ");
			hsql
					.append("<jsp:include page=\"basicForm.jsp\"  flush=\"true\"></jsp:include>");
			hsql.append(" \n ");
			Queue<Ta07_formfield> fieldQueue1 = new LinkedList<Ta07_formfield>();
			Queue<Ta07_formfield> fieldQueue2 = new LinkedList<Ta07_formfield>();
			for (int i = 0; i < fields.size(); i++) {
				Ta07_formfield formfield = (Ta07_formfield) fields.get(i);

				// 如果文本框沒湊夠兩個一排,進入队列1
				if (fieldQueue1.size() % 2 == 1) {
					if (formfield.getDatalength() > 200) {
						fieldQueue2.offer(formfield);
						continue;
					} else {
						fieldQueue1.offer(formfield);
					}
				} // 如果是偶数
				else {
					if (fieldQueue2.size() > 0) {
						formfield = fieldQueue2.poll();
					} else {
						if (formfield.getDatalength() < 200) {
							fieldQueue1.offer(formfield);
						}
					}
				}

				// 文本域
				if (formfield.getData_type() != null
						&& (formfield.getData_type() == 2 || formfield
								.getDatalength() > 1000)) {
					// 文本域换行
					hsql.append("<div style=\"height:0px;\"></div>");
					hsql.append(" <p> \n");
					hsql.append("<label> " + formfield.getComments()
							+ "：</label> \n");
					hsql.append("<textarea ");
					hsql.append(" name=\"");
					hsql.append(formfield.getObject_name().substring(
							formfield.getObject_name().lastIndexOf(".") + 1,
							formfield.getObject_name().length()));
					hsql.append("." + formfield.getName().toUpperCase());
					hsql.append("\"");
					hsql
							.append(" style=\"width:619px;height:70px;\" readonly>");
					hsql.append("${");
					hsql.append(formfield.getObject_name().substring(
							formfield.getObject_name().lastIndexOf(".") + 1,
							formfield.getObject_name().length()).toLowerCase());
					hsql.append("." + formfield.getName() + "} ");
					hsql.append("</textarea>");
					hsql.append("\n </p> \n");
					hsql.append("<div style=\"height:0px;\"></div> \n");
				} else {
					if (formfield.getDatalength() > 250) {
						hsql.append("<div style=\"height:0px;\"></div>");
					}
					hsql.append(" <p> \n");
					hsql.append("<label> " + formfield.getComments()
							+ "：</label> \n");

					hsql.append("<input type=\"text\" ");
					hsql.append(" name=\"");
					hsql.append(formfield.getObject_name().substring(
							formfield.getObject_name().lastIndexOf(".") + 1,
							formfield.getObject_name().length()));
					hsql.append("." + formfield.getName().toUpperCase());
					hsql.append("\"");
					hsql.append(" value=\"");
					// 如果是日期
					if (formfield.getDatatype().equals("DATE")) {
						hsql.append("<fmt:formatDate value=\"");
					}
					hsql.append("${");
					hsql.append(formfield.getObject_name().substring(
							formfield.getObject_name().lastIndexOf(".") + 1,
							formfield.getObject_name().length()).toLowerCase());
					hsql.append("." + formfield.getName() + "}\" ");

					// 如果是日期
					if (formfield.getDatatype().equals("DATE")) {
						hsql.append(" pattern=\"");
						if (formfield.getFormat() != null
								&& formfield.getData_type() == 5) {
							hsql.append(formfield.getFormat());
						} else {
							hsql.append("yyyy-MM-dd");
						}

						hsql.append(" \"/>\" ");
					}
					// 长文本框
					if (formfield.getDatalength() > 200
							&& formfield.getDatalength() < 500) {
						hsql.append("style=\"width:619px;\" readonly/>");
					}
					// 短文本框
					else {
						hsql.append("style=\"width:256px;\" readonly/>");
					}

					hsql.append("\n </p> \n");
					if (fieldQueue1.size() % 2 == 0) {
						hsql.append("<div style=\"height:0px;\"></div> \n");
					}
				}
			}
			try {
				FileOutputStream fos = new FileOutputStream(path+"\\jsp\\form\\" + module.getForm_name().toLowerCase() + "_"
						+ user.getId() + ".jsp");
				Writer out = new OutputStreamWriter(fos, "utf-8");
				out.write(hsql.toString());
				// out.write(hsql.toString());
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}


	/**
	 * 生成表单文件
	 *　重载方法：createTableFile
	 * (non-Javadoc)
	 * @see com.ghpms.service.CreateJspFile#createTableFile(javax.servlet.http.HttpServletRequest, java.lang.Long)
	 */
	public void createTableFile(HttpServletRequest request, Long module_id) {
		StringBuffer hsql = new StringBuffer();
		Tb02_node node;
		Ta06_module module = null;
		String path = request.getSession().getServletContext().getRealPath(
				"/WEB-INF");
		Long node_id = convertUtil.toLong(request.getParameter("node_id"), -1L);
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		node = (Tb02_node) queryService.searchById(Tb02_node.class, node_id);
		if (module_id == -1&&node!=null) {
			module_id=node.getFlow_id();
		}
		/*
		 * 得到用户列表
		 */
		Integer station_id=convertUtil.toInteger(request.getParameter("station_id"));
		List userList=  queryService.searchList("select distinct(ta11.user_id) from Ta11_sta_user ta11 where  ta11.station_id="+station_id);
		for (Object object : userList) {
		    this.createTableField(module_id, convertUtil.toLong(object.toString()), path);
        }
		
		
	}
	
	/**
	 * 
	 * @param module_id
	 * @param user_id
	 * @param path void
	 */
	public void   createTableField(Long module_id,Long user_id,String path) {
	    StringBuffer hsql = new StringBuffer();
	    Ta06_module   module = (Ta06_module) queryService.searchById(Ta06_module.class,
                module_id);
        path += "\\jsp\\form\\" + module.getForm_name().toLowerCase() + "_"
                + user_id + ".jsp";

        hsql.append(" select distinct ta07 from Ta07_formfield ta07,Ta16_node_field ta16,Ta13_sta_node ta13,Ta11_sta_user ta11 where 1=1 ");
        hsql.append(" and ta07.id=ta16.field_id and ta16.node_id=ta13.node_id and ta13.station_id=ta11.station_id  and ta16.node_type =2 ");
        hsql.append(" and ta07.name <> 'id' ");
        hsql.append(" and ta11.user_id=");
        hsql.append(user_id);
        hsql.append(" and ta07.module_id=");
        hsql.append(module.getId());
        hsql.append(" order by ta07.ord");
        List fields = queryService.searchList(hsql.toString());
        if (fields == null || fields.size() == 0) {
            hsql.delete(0, hsql.length());
            hsql.append("select a from Ta07_formfield a where 1=1 ");
            hsql.append(" and a.module_id=");
            hsql.append(module.getId());
            hsql.append(" and a.name <> 'id' ");
            hsql.append(" order by a.ord");
            fields = queryService.searchList(hsql.toString());
        }

        hsql.delete(0, hsql.length());
        hsql
                .append("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>");
        hsql.append(" \n ");
        hsql
                .append("<%@ taglib prefix=\"fmt\" uri=\"http://java.sun.com/jsp/jstl/fmt\"%>");
        hsql.append(" \n ");
        hsql
                .append("<jsp:include page=\"basicForm.jsp\"  flush=\"true\"></jsp:include>");
        hsql.append(" \n ");
        Queue<Ta07_formfield> fieldQueue1 = new LinkedList<Ta07_formfield>();
        Queue<Ta07_formfield> fieldQueue2 = new LinkedList<Ta07_formfield>();
        for (int i = 0; i < fields.size(); i++) {
            Ta07_formfield formfield = (Ta07_formfield) fields.get(i);

            // 如果文本框沒湊夠兩個一排,進入队列1
            if (fieldQueue1.size() % 2 == 1) {
                if (formfield.getDatalength() > 200) {
                    fieldQueue2.offer(formfield);
                    continue;
                } else {
                    fieldQueue1.offer(formfield);
                }
            } // 如果是偶数
            else {
                if (fieldQueue2.size() > 0) {
                    formfield = fieldQueue2.poll();
                } else {
                    if (formfield.getDatalength() < 200) {
                        fieldQueue1.offer(formfield);
                    }
                }
            }

            // 文本域
            if (formfield.getData_type() != null
                    && (formfield.getData_type() == 2 || formfield
                            .getDatalength() > 1000)) {
                // 文本域换行
                hsql.append("<div style=\"height:0px;\"></div>");
                hsql.append(" <p> \n");
                hsql.append("<label> " + formfield.getComments()
                        + "：</label> \n");
                hsql.append("<textarea ");
                hsql.append(" name=\"");
                hsql.append(formfield.getObject_name().substring(
                        formfield.getObject_name().lastIndexOf(".") + 1,
                        formfield.getObject_name().length()));
                hsql.append("." + formfield.getName().toUpperCase());
                hsql.append("\"");
                hsql.append(" style=\"width:619px;height:70px;\" readonly>");
                hsql.append("${");
                hsql.append(formfield.getObject_name().substring(
                        formfield.getObject_name().lastIndexOf(".") + 1,
                        formfield.getObject_name().length()).toLowerCase());
                hsql.append("." + formfield.getName() + "} ");
                hsql.append("</textarea>");
                hsql.append("\n </p> \n");
                hsql.append("<div style=\"height:0px;\"></div> \n");
            } else {
                if (formfield.getDatalength() > 250) {
                    hsql.append("<div style=\"height:0px;\"></div>");
                }
                hsql.append(" <p> \n");
                hsql.append("<label> " + formfield.getComments()
                        + "：</label> \n");

                hsql.append("<input type=\"text\" ");
                hsql.append(" name=\"");
                hsql.append(formfield.getObject_name().substring(
                        formfield.getObject_name().lastIndexOf(".") + 1,
                        formfield.getObject_name().length()));
                hsql.append("." + formfield.getName().toUpperCase());
                hsql.append("\"");
                hsql.append(" value=\"");
                // 如果是日期
                if (formfield.getDatatype().equals("DATE")) {
                    hsql.append("<fmt:formatDate value=\"");
                }
                hsql.append("${");
                hsql.append(formfield.getObject_name().substring(
                        formfield.getObject_name().lastIndexOf(".") + 1,
                        formfield.getObject_name().length()).toLowerCase());
                hsql.append("." + formfield.getName() + "}\" ");

                // 如果是日期
                if (formfield.getDatatype().equals("DATE")) {
                    hsql.append(" pattern=\"");
                    if (formfield.getFormat() != null
                            && formfield.getData_type() == 5) {
                        hsql.append(formfield.getFormat());
                    } else {
                        hsql.append("yyyy-MM-dd");
                    }

                    hsql.append(" \"/>\" ");
                }
                // 长文本框
                if (formfield.getDatalength() > 200
                        && formfield.getDatalength() < 500) {
                    hsql.append("style=\"width:619px;\" readonly/>");
                }
                // 短文本框
                else {
                    hsql.append("style=\"width:256px;\" readonly/>");
                }

                hsql.append("\n </p> \n");
                if (fieldQueue1.size() % 2 == 0) {
                    hsql.append("<div style=\"height:0px;\"></div> \n");
                }
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(path);
            Writer out = new OutputStreamWriter(fos, "utf-8");
            out.write(hsql.toString());
            // out.write(hsql.toString());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
