package com.ghpms.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghpms.service.CreateJspFile;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Ta07_formfield;
import com.netsky.base.dataObjects.Tb02_node;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.StringFormatUtil;
import com.netsky.base.utils.convertUtil;

@Service("createJspFile")
public class CreateJspFileImpl implements CreateJspFile {
	@Autowired
	QueryService queryService;
	@Autowired
	SaveService saveService;

	public void AutoCreateJspFile(HttpServletRequest request, Long module_id) {
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
				.append("<jsp:include page=\"basicForm.jsp\"  flush=\"true\"></jsp:include>");
		hsql.append(" \n ");
		for (int i = 1; i < fields.size(); i++) {
			Ta07_formfield formfield = (Ta07_formfield) fields.get(i);
			hsql.append(" <p> \n");
			hsql.append("<label> " + formfield.getComments() + ":</label> \n");

			hsql.append("<input type=\"text\"  value=\"");
			hsql.append("${");
			hsql.append(formfield.getObject_name().substring(
					formfield.getObject_name().lastIndexOf(".") + 1,
					formfield.getObject_name().length()).toLowerCase());
			hsql.append("." + formfield.getName() + "}\"");
			hsql.append("style=\"width:256px;\" readonly/>");
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

	public void getNode(HttpServletRequest request, Map paraMap) {
		StringBuffer hsql = new StringBuffer();
		String t_module_id = StringFormatUtil.format((String) paraMap
				.get("module_id"), "-1");
		Long module_id = convertUtil.toLong(t_module_id);
		Ta03_user user = (Ta03_user) (request.getSession().getAttribute("user"));
		hsql.append("select distinct(d.id),d.name,d.flow_id,d.node_type,d.remark ");
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
			nodeMap.put("editURL", "gcsj/gcsjEdit.do?node_id=" + node[0]);
			nodeMap.put("node_id", node[0]);
			nodeMap.put("title", node[1]);
			nodeMaps.add(nodeMap);
		}
		request.setAttribute("nodeMaps", nodeMaps);
	}

	public void createJspFileToRecord(HttpServletRequest request, Map paraMap) {
		StringBuffer hsql = new StringBuffer();
		String filePath = "";

		String node_id = StringFormatUtil.format((String) paraMap
				.get("node_id"), "-1");
		hsql
				.append("select a from Ta07_formfield a,Tb02_node b,Ta16_node_field c where a.id=c.field_id and b.id=c.node_id ");
		hsql.append(" and b.id=");
		hsql.append(node_id);
		List fields = queryService.searchList(hsql.toString());

		filePath = request.getSession().getServletContext().getRealPath(
				"/WEB-INF");
		filePath += "\\jsp\\form\\" + node_id + ".jsp";

		hsql.delete(0, hsql.length());
		hsql
				.append("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>");
		hsql.append(" \n ");
		hsql
				.append("<jsp:include page=\"basicEdit.jsp\"  flush=\"true\"></jsp:include>");
		hsql.append(" \n ");
		for (int i = 1; i < fields.size(); i++) {
			Ta07_formfield formfield = (Ta07_formfield) fields.get(i);
			hsql.append(" <p> \n");
			hsql.append("<label> " + formfield.getComments() + ":</label> \n");

			hsql.append("<input type=\"text\"  value=\"");
			hsql.append("${");
			hsql.append(formfield.getObject_name().substring(
					formfield.getObject_name().lastIndexOf(".") + 1,
					formfield.getObject_name().length()).toLowerCase());
			hsql.append("." + formfield.getName() + "}\"");
			hsql.append("style=\"width:256px;\" />");
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

}
