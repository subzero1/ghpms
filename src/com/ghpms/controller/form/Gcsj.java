package com.ghpms.controller.form;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ghpms.dataObjects.form.Td01_glsj;
import com.ghpms.service.CreateJspFile;
import com.ghpms.service.GcsjDataService;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta04_role;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Ta07_formfield;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.convertUtil;

@Controller
public class Gcsj {
	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;

	@Autowired
	private SaveService saveService;

	@Autowired
	private CreateJspFile createJspFile;

	@Autowired
	private ExceptionService exceptionService;

	@Autowired
	private GcsjDataService gcsjDataService;

	/**
	 * 表单列表
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@RequestMapping("/form/gcsjList.do")
	public ModelAndView gcsjList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		ModelMap modelMap = new ModelMap();
		Map docMap = null;
		ResultObject rs = null;

		// 条件
		Long module_id = convertUtil.toLong(request.getParameter("module_id"));
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "id");
		if (orderField.equals("")) {
			orderField = "id";
		}
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "asc";
		}

		Long node_id = -1L;

		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		Ta06_module module = (Ta06_module) queryService.searchById(
				Ta06_module.class, module_id);
		// 查询条件
		String keyword = convertUtil.toString(request.getParameter("keyword"));
		StringBuffer hsql = new StringBuffer();

		Map<String, Ta04_role> rolesMap = (Map<String, Ta04_role>) request
				.getSession().getAttribute("rolesMap");
		String login_id = convertUtil.toString(user.getLogin_id());

		if (module != null) {
			hsql.append("select a from ");
			hsql.append(module.getProject_table());
			hsql.append(" a where 1=1 ");
			
			if (!keyword.equals("")) {
				hsql.append(" and (a.ghbh like'%");
				hsql.append(keyword);
				hsql.append("%' ");
				hsql.append(" or a.skbh like'%");
				hsql.append(keyword);
				hsql.append("%' ");
				hsql.append(" or a.gcmc like'%");
				hsql.append(keyword);
				hsql.append("%') ");
			}
			
			
			hsql.append(" order by " + orderField);
			hsql.append(" " + orderDirection);
			rs = queryService
					.searchByPage(hsql.toString(), pageNum, numPerPage);
		}

		// 取标题列
		docMap = gcsjDataService.getFormTitleMap(user, module_id);
		List<Ta07_formfield> docColsList = (List<Ta07_formfield>) docMap
				.get("docColsList");
		// 取表单数据
		List<List> docList = new LinkedList<List>();
		DecimalFormat df = new DecimalFormat("#0.00");
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Object a = null;
		while (rs.next()) {
			List row = new LinkedList(); // 行对像，先初始化各列数据
			a = rs.get("a");
			// 初始化各列数据
			for (Ta07_formfield ta07 : docColsList) {
				Object obj = null;
				// 取数据
				obj = PropertyInject.getProperty(a, ta07.getName().trim());

				// 格式化数据
				if ("NUMBER".equals(ta07.getDatatype()) && obj != null) {
					row.add(df.format(new BigDecimal(obj.toString())));
				} else if (obj instanceof Date) {
					row.add(dateformat.format(obj));
				} else {
					row.add(obj);
				}
			}
			// 导EXCEL不需求后面对象
			if (!"yes".equals(request.getParameter("toExcel"))) {
				row.add(a);
			}
			docList.add(row);
		}
		// 获取总条数和总页数
		totalPages = rs.getTotalPages();
		totalCount = rs.getTotalRows();
		// 导EXCEL
		if ("yes".equals(request.getParameter("toExcel"))) {
			Map<String, List> sheetMap = new HashMap<String, List>();
			List sheetList = new LinkedList();
			List titleList = new LinkedList();
			for (Ta07_formfield ta07 : docColsList) {
				titleList.add(ta07.getComments().trim());
			}
			sheetList.add(titleList);
			sheetList.add(docList);
			sheetMap.put("form_title", sheetList);
			request.setAttribute("ExcelName", module.getName() + ".xls");
			request.setAttribute("sheetMap", sheetMap);
			return new ModelAndView("/export/toExcelWhithList.do");
		}

		modelMap.put("keyword", keyword);
		modelMap.put("cols", docColsList.size());
		modelMap.put("docs", docList);
		modelMap.put("docColsList", docColsList);
		modelMap.put("node_id", node_id);
		modelMap.put("module_id", module_id);
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);

		return new ModelAndView("/WEB-INF/jsp/search/gcsjList.jsp", modelMap);

	}

	/**
	 * 生成jsp錄入文件
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws ClassNotFoundException
	 */
	@RequestMapping("/gcsj/gcsjEdit.do")
	public ModelAndView gcsjEdit(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException {
		String view = "/WEB-INF/jsp/form/autoEdit.jsp";
		ModelMap map = new ModelMap();
		StringBuffer hsql = new StringBuffer();
		String tableClassName = "";
		String tableName = "";
		Class c = null;
		Object obj = null;
		Long project_id = convertUtil
				.toLong(request.getParameter("project_id"));
		String node_id = convertUtil.toString(request.getParameter("node_id"));
		Map paraMap = new HashMap<String, String>();

		Ta06_module module = (Ta06_module) queryService.searchById(
				Ta06_module.class, convertUtil.toLong(node_id.substring(0, 3)));
		if (module != null) {
			tableClassName = module.getProject_table();
			tableName = tableClassName.substring(tableClassName
					.lastIndexOf(".") + 1, tableClassName.length());
			c = Class.forName(tableClassName);
			obj = queryService.searchById(c, project_id);
		}

		paraMap.put("node_id", node_id);
		paraMap.put("project_id", project_id);
		map.put(tableName.toLowerCase(), obj);
		createJspFile.createJspFileToRecord(request, paraMap);
		return new ModelAndView(view, map);
	}

	/**
	 * 刪除操作
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 *             void
	 */
	@RequestMapping("/gcsj/ajaxGcsjDel.do")
	public void ajaxGcsjDel(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Long id = convertUtil.toLong(request.getParameter("id"));
		Long module_id = convertUtil.toLong(request.getParameter("module_id"));

		Ta06_module module = (Ta06_module) queryService.searchById(
				Ta06_module.class, module_id);
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding(request.getCharacterEncoding());
		Class c = null;
		// 获取用户对象
		try {

			c = Class.forName(module.getProject_table());
			saveService.removeObject(c, id);

			out
					.print("{\"statusCode\":\"200\", \"message\":\"操作成功\", \"callbackType\":\"\",\"navTabId\":\"\"}");

		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"statusCode\":\"300\", \"message\":\"操作失败\"}");
		}
	}

}
