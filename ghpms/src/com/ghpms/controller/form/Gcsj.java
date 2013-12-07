package com.ghpms.controller.form;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

import com.ghpms.service.CreateJspFile;
import com.ghpms.service.GcsjDataService;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta04_role;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Ta07_formfield;
import com.netsky.base.dataObjects.Tb02_node;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.DateFormatUtil;
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
	 * @throws Exception
	 */
	@RequestMapping("/form/gcsjList.do")
	public ModelAndView gcsjList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
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

		hsql.append("select a from ");
		hsql.append(module.getProject_table());
		hsql.append(" a where 1=1 ");

		if (!keyword.equals("")) {
			keyword = new String(keyword.getBytes("ISO-8859-1"), "gbk");
			hsql.append(" and a.skbh like'%");
			hsql.append(keyword);
			hsql.append("%' ");
			hsql.append(" or a.gcmc like'%");
			hsql.append(keyword);
			hsql.append("%') ");
		}

		hsql.append(" order by " + orderField);
		hsql.append(" " + orderDirection);
		rs = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		if ("yes".equals(request.getParameter("toExcel"))) {
			rs = queryService.search(hsql.toString());
		}
		// 取标题列
		docMap = gcsjDataService.getFormTitleMap(user, module_id);
		List<Ta07_formfield> docColsList = (List<Ta07_formfield>) docMap
				.get("docColsList");
		// 取标题长度
		Integer docTabWitdh = (Integer) docMap.get("docTabWitdh");
		// 如果是导出的话添加ID,重置标题
		List<Ta07_formfield> listToExcel = new ArrayList();
		if ("yes".equals(request.getParameter("toExcel"))) {
			List<Ta07_formfield> list = new ArrayList();
			// 检测是否有ID
			boolean isId = false;
			for (Ta07_formfield ta07 : docColsList) {
				if (ta07.getName().equals("id")) {
					isId = true;
					break;
				}
			}
			if (!isId) {
				list = gcsjDataService.getUpdateProperty(module_id);
			}
			listToExcel.addAll(list);
		}
		listToExcel.addAll(docColsList);

		// 取表单数据
		List<List> docList = new LinkedList<List>();
		DecimalFormat df = new DecimalFormat("#0.00");
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

		Object a = null;
		if (rs != null) {
			while (rs.next()) {
				List row = new LinkedList(); // 行对像，先初始化各列数据
				a = rs.get("a");
				// 初始化各列数据
				for (Ta07_formfield ta07 : listToExcel) {
					Object obj = null;
					// 取数据
					obj = PropertyInject.getProperty(a, ta07.getName().trim());

					// 格式化数据
					if ("NUMBER".equals(ta07.getDatatype()) && obj != null) {
						if ((ta07.getDatalength().toString()).endsWith(".0")) {
							row.add(obj.toString());
						} else {
							row.add(df.format(new BigDecimal(obj.toString())));
						}

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
		}
		// 导EXCEL
		if ("yes".equals(request.getParameter("toExcel"))) {
			Map<String, List> sheetMap = new HashMap<String, List>();
			List sheetList = new LinkedList();
			List titleList = new LinkedList();
			for (Ta07_formfield ta07 : listToExcel) {
				titleList.add(ta07.getComments().trim());
			}
			sheetList.add(titleList);
			sheetList.add(docList);
			sheetMap.put("form_title", sheetList);
			request.setAttribute("ExcelName", module.getName() + "-"
					+ module.getForm_name() + module.getId() + "-"
					+ DateFormatUtil.Format(new Date(), "yyyyMMdd") + ".xls");
			request.setAttribute("sheetMap", sheetMap);
			return new ModelAndView("/export/toExcelWhithList.do");
		}

		modelMap.put("keyword", keyword);
		modelMap.put("cols", docColsList.size());
		modelMap.put("docs", docList);
		modelMap.put("docColsList", docColsList);
		modelMap.put("docTabWitdh", docTabWitdh);
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

		// 取Tb02
		Tb02_node tb02_node = (Tb02_node) queryService.searchById(
				Tb02_node.class, convertUtil.toLong(node_id));
		Ta06_module module = (Ta06_module) queryService.searchById(
				Ta06_module.class, tb02_node.getFlow_id());
		if (module != null) {
			tableClassName = module.getProject_table();
			tableName = tableClassName.substring(tableClassName
					.lastIndexOf(".") + 1, tableClassName.length());
			c = Class.forName(tableClassName);
			obj = queryService.searchById(c, project_id);
		}
		gcsjDataService.setSelectValue(request, convertUtil.toLong(node_id));
		map.put(tableName.toLowerCase(), obj);
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
		response.setCharacterEncoding("UTF-8");
		Long id = convertUtil.toLong(request.getParameter("id"));
		Long module_id = convertUtil.toLong(request.getParameter("module_id"));

		Ta06_module module = (Ta06_module) queryService.searchById(
				Ta06_module.class, module_id);
		PrintWriter out = response.getWriter();
		System.out.println("編碼:" + request.getCharacterEncoding());
		response.setCharacterEncoding(request.getCharacterEncoding());
		Class c = null;
		// 获取用户对象
		try {

			c = Class.forName(module.getProject_table());
			saveService.removeObject(c, id);

			out
					.print("{\"statusCode\":\"200\", \"message\":\"删除成功!\", \"callbackType\":\"\",\"navTabId\":\"\"}");

		} catch (Exception e) {
			e.printStackTrace();
			out
					.print("{\"statusCode\":\"300\", \"message\":\" 删除失败!\"}");
		}
	}

	/**
	 * 取最大ID的记录
	 * 
	 * @param request
	 * @param response
	 * @throws ClassNotFoundException
	 * @throws IOException
	 *             void
	 */
	@RequestMapping("/gcsj/getProjectID.do")
	public void getProjectID(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			IOException {
		Long moudle_id = convertUtil.toLong(request.getParameter("module_id"));
		StringBuffer hql = new StringBuffer();
		Class c = null;
		PrintWriter out = response.getWriter();
		Ta06_module module = (Ta06_module) queryService.searchById(
				Ta06_module.class, convertUtil.toLong(moudle_id));
		if (module != null) {
			String packTableName = module.getProject_table();
			String tableName = packTableName.substring(packTableName
					.lastIndexOf(".") + 1, packTableName.length());
			hql.append("select max(id) from ");
			hql.append(tableName);
			List list = queryService.searchList(hql.toString());
			Object obj = null;
			if (list != null && list.size() > 0) {
				obj = list.get(0);
				System.out.println(obj.toString());
			}
			out.print(obj.toString());
		}
	}

	@RequestMapping("/gcsj/outDateList.do")
	public ModelAndView outDateList(HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		String view = "/WEB-INF/jsp/search/outDateList.jsp";
		ResultObject rs = null;
		StringBuffer hsql = new StringBuffer("");
		Ta03_user user = (Ta03_user) (request.getSession().getAttribute("user"));
		Integer outDateFlag=convertUtil.toInteger(request.getParameter("outDateFlag"),0);
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
		
		String tableName;
		String className;
		List outDateMapList=new ArrayList();
		List<Ta06_module> modules = (List<Ta06_module>) queryService
				.searchList(Ta06_module.class);
		for (Ta06_module ta06_module : modules) {
			className = ta06_module.getForm_table();
			tableName = className.substring(className.lastIndexOf(".") + 1,
					className.length());
			if (outDateFlag==1) {//即将超期
				hsql.delete(0, hsql.length());
				hsql.append("from ");
				hsql.append(tableName);
				hsql.append(" t where t.sjwcsj is null and t.jhwcsj-sysdate<2 and t.jhwcsj-sysdate>0  ");
				hsql.append(" and exists(");
				hsql.append(" select distinct(d.id),f.id,f.name ,f.comments ");
				hsql.append(" from Ta03_user a,Ta02_station b,Ta11_sta_user c,Tb02_node d,Ta13_sta_node e,Ta07_formfield f ,Ta16_node_field g ");
				hsql.append(" where a.id=c.user_id and b.id=c.station_id and d.id=e.node_id and e.station_id=b.id and d.id=g.node_id and g.field_id=f.id ");
				hsql.append(" and f.name in('jhwcsj','sjwcsj') ");
				hsql.append(" and a.id=");
				hsql.append(user.getId());
				hsql.append(" and d.flow_id=");
				hsql.append(ta06_module.getId());
				hsql.append(")");
			}else if (outDateFlag==2) {//已经超期
				hsql.delete(0, hsql.length());
				hsql.append("from ");
				hsql.append(tableName);
				hsql.append(" t where t.sjwcsj is null and t.jhwcsj-sysdate<0 ");
				hsql.append(" and exists(");
				hsql.append(" select distinct(d.id),f.id,f.name ,f.comments ");
				hsql.append(" from Ta03_user a,Ta02_station b,Ta11_sta_user c,Tb02_node d,Ta13_sta_node e,Ta07_formfield f ,Ta16_node_field g ");
				hsql.append(" where a.id=c.user_id and b.id=c.station_id and d.id=e.node_id and e.station_id=b.id and d.id=g.node_id and g.field_id=f.id ");
				hsql.append(" and f.name in('jhwcsj','sjwcsj') ");
				hsql.append(" and a.id=");
				hsql.append(user.getId());
				hsql.append(" and d.flow_id=");
				hsql.append(ta06_module.getId());
				hsql.append(")");
			}
			List list=queryService.searchList(hsql.toString());
			for (Object object : list) {
				Map tableMap=new HashMap();
				tableMap.put("module_name", ta06_module.getName());
				tableMap.put("module_id", ta06_module.getId());
				tableMap.put("project", object);
				outDateMapList.add(tableMap);
			}
		}
		modelMap.put("outDateMapList", outDateMapList);
		return new ModelAndView(view, modelMap);
	}

}
