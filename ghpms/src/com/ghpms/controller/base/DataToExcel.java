package com.ghpms.controller.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.ConfigXMLService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.convertUtil;

@Controller
public class DataToExcel {

	@Autowired
	private QueryService queryService;

	@Autowired
	private ConfigXMLService configXML;
	

	private static String CONFIG_FILE = "/importConfig/import.xml";
	
	private String webInfPatch="";

	/**
	 * 通用导出Excel
	 * 
	 * @param request
	 * @param response
	 * config 传递的import配置的参数
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/tableToExcel.do")
	public ModelAndView tableToExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String config = convertUtil.toString(request.getParameter("config"));
		String keyword=convertUtil.toString(request.getParameter("keyword"));
		String orderField=convertUtil.toString(request.getParameter("orderField"),"id");
		int k = 0;
		ResultObject ro = null;
		StringBuffer hql = new StringBuffer("");
		List mbkTitleList = new LinkedList(); // 标题列表
		List mbkColList = new LinkedList();// 列的字段值
		List mbkDocList = new LinkedList();// 表单数据
		Map<String, List> sheetMap = new HashMap<String, List>();
		List sheetList = new LinkedList();

		// 读取配置文件的标题列表
		String webinfpath = request.getSession().getServletContext()
				.getRealPath("WEB-INF");
		mbkTitleList = configXML.getTagListByConfig(config, webinfpath, "name");
		mbkColList = configXML.getTagListByConfig(config, webinfpath,
				"columnName");
		// 表名
		String tableName = (String) configXML.getElementsByName(
				webinfpath + configXML.getConfigFilePath(config, webinfpath),
				"tableName").get(0);
		// excel文件名
		String excelName = (String) configXML.getElementsByName(
				webinfpath + configXML.getConfigFilePath(config, webinfpath),
				"title").get(0);
		Iterator it = mbkColList.iterator();
		Object mbk = null;
		hql.append("select ");
		while (it.hasNext()) {
			if (k == 0)
				hql.append(" a." + ((it.next().toString()).toLowerCase()));
			else
				hql.append(" ,a." + ((it.next().toString()).toLowerCase()));
			k++;
		} 
		hql.append(" from "+tableName+" a ");
		hql.append(" order by a.");
		hql.append(orderField);

		mbkDocList = queryService.searchList(hql.toString());

		sheetList.add(mbkTitleList);
		sheetList.add(mbkDocList);
		sheetMap.put("form_title", sheetList);
		request.setAttribute("ExcelName", excelName + ".xls");
		request.setAttribute("sheetMap", sheetMap);
		return new ModelAndView("/export/toExcelWhithList.do");
	}
	
}
