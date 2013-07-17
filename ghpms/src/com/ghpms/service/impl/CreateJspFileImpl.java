package com.ghpms.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
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
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Ta07_formfield;
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
			
			//如果文本框沒湊夠兩個一排,進入队列1
			if (fieldQueue1.size() % 2 == 1) {
				if (formfield.getDatalength() > 200) {
					fieldQueue2.offer(formfield);
					continue;
				} else {
					fieldQueue1.offer(formfield);
				}
			} //如果是偶数
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
			
			//如果文本框沒湊夠兩個一排,進入队列1
			if (fieldQueue1.size() % 2 == 1) {
				if (formfield.getDatalength() > 200) {
					fieldQueue2.offer(formfield);
					continue;
				} else {
					fieldQueue1.offer(formfield);
				}
			} //如果是偶数
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

	public void getNode(HttpServletRequest request, Map paraMap) {
		StringBuffer hsql = new StringBuffer();
		String t_module_id = StringFormatUtil.format((String) paraMap
				.get("module_id"), "-1");
		Long module_id = convertUtil.toLong(t_module_id);
		Ta03_user user = (Ta03_user) (request.getSession().getAttribute("user"));
		hsql
				.append("select distinct(d.id),d.name,d.flow_id,d.node_type,d.remark ");
		hsql
				.append(" from Ta03_user a,Ta02_station b,Ta11_sta_user c,Tb02_node d,Ta13_sta_node e ");
		hsql
				.append("where a.id=c.user_id and b.id=c.station_id and d.id=e.node_id and e.station_id=b.id ");
		hsql.append(" and a.id=");
		hsql.append(user.getId());
		hsql.append(" and d.id>");
		hsql.append(module_id * 100);
		hsql.append(" and d.id<");
		hsql.append((module_id + 1) * 100);
		List recordButtons = queryService.searchList(hsql.toString());
		List nodeMaps = new ArrayList();
		for (Object object : recordButtons) {
			Object[] node = (Object[]) object;
			Map nodeMap = new HashMap();
			nodeMap.put("editURL", "gcsj/gcsjEdit.do?node_id=" + node[0]
					+ "&module_id=" + module_id);
			nodeMap.put("node_id", node[0]);
			nodeMap.put("title", node[1]);
			nodeMaps.add(nodeMap);
		}
		request.setAttribute("nodeMaps", nodeMaps);
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
				//加载文本框的值
				Map map=gcsjDataService.setSelectValue(request, formfield);
				
				hsql.append("<netsky:htmlSelect name=\"" + packTableName);
				hsql.append(".");
				hsql.append(formfield.getName().toUpperCase());
				hsql.append("\" ");
				hsql.append(" objectForOption=\""+map.get("objectForOption")+"\" style=\"width:256px;\"");
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

	public void AutoCreateJspFileByTwo(HttpServletRequest request,
			Long module_id) {
		StringBuffer hsql = new StringBuffer();
		String filePath = "";

		hsql.append("select a from Ta07_formfield a where 1=1 ");
		hsql.append(" and a.module_id=");
		hsql.append(module_id);
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

		hsql.append("<script type=\"text/javascript\" > ");
		hsql.append(" \n ");
		hsql.append(" $(\".column1\").each(function(){ ");
		hsql.append(" \n ");
		hsql.append("       $(this).append(\"123456\"); ");
		hsql.append(" \n ");
		hsql.append("}); ");
		hsql.append(" \n ");
		hsql.append("</script>");
		hsql.append(" \n ");
		hsql
				.append("<div class=\"pageFormContent\" style=\"overflow: auto; \"> ");
		hsql.append("<fieldset>");
		hsql.append(" \n ");
		hsql
				.append("<legend style=\"margin:0 0 0 20px;padding:5px;border:dotted 2px #ccc;width:160px;text-align:center;font-size:14px;font-weight:bold;\">"
						+ module.getName() + "</legend>");
		hsql.append(" \n ");

		StringBuffer hsql1 = new StringBuffer();
		StringBuffer hsql2 = new StringBuffer();
		hsql1.append("<div class=\"column1\">");
		hsql.append(" \n ");
		hsql2.append("<div class=\"column2\">");
		hsql.append(" \n ");
		for (int i = 1; i < fields.size(); i++) {
			Ta07_formfield formfield = (Ta07_formfield) fields.get(i);

			if (formfield.getPosition() == 0) {
				hsql1.append(" <dl> \n");
				hsql1.append("<dt> " + formfield.getComments() + ":</dt> \n");
				hsql1.append("<dd> \n");
				hsql1.append("<input type=\"text\" ");
				hsql1.append(" value=\"");
				// 如果是日期
				if (formfield.getDatatype().equals("DATE")) {
					hsql1.append("<fmt:formatDate value=\"");
				}
				hsql1.append("${");
				hsql1.append(formfield.getObject_name().substring(
						formfield.getObject_name().lastIndexOf(".") + 1,
						formfield.getObject_name().length()).toLowerCase());
				hsql1.append("." + formfield.getName() + "}\" ");

				// 如果是日期
				if (formfield.getDatatype().equals("DATE")) {
					hsql1.append(" pattern=\"yyyy-MM-dd \"/>\" ");
				}
				hsql1.append("readonly/>");
				hsql1.append("</dd>");
				hsql1.append("\n </dl> \n");

			} else {
				hsql2.append(" <dl> \n");
				hsql2.append("<dt> " + formfield.getComments() + ":</dt> \n");
				hsql2.append("<dd> \n");
				hsql2.append("<input type=\"text\" ");
				hsql2.append(" value=\"");
				// 如果是日期
				if (formfield.getDatatype().equals("DATE")) {
					hsql2.append("<fmt:formatDate value=\"");
				}
				hsql2.append("${");
				hsql2.append(formfield.getObject_name().substring(
						formfield.getObject_name().lastIndexOf(".") + 1,
						formfield.getObject_name().length()).toLowerCase());
				hsql2.append("." + formfield.getName() + "}\" ");

				// 如果是日期
				if (formfield.getDatatype().equals("DATE")) {
					hsql2.append(" pattern=\"yyyy-MM-dd \"/>\" ");
				}
				hsql2.append("readonly/>");
				hsql2.append("</dd>");
				hsql2.append("\n </dl> \n");

			}

		}

		hsql1.append("</div>");
		hsql2.append("</div>");
		hsql.append(hsql1);
		hsql.append(hsql2);
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
}
