package com.ghpms.controller.search;

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

import jxl.Workbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta07_formfield;
import com.netsky.base.dataObjects.Tz03_login_log;
import com.netsky.base.export.ExportExcel;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.StringFormatUtil;

/**
 * 登陆日志
 * 
 * @author CT
 * @create 2010-03-10
 */
@Controller
public class LoginLog {

	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;
	/**
	 * 错误信息
	 */
	@Autowired
	private ExceptionService exceptionService;

	@RequestMapping("/search/LoginLog.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);

		Ta03_user user = null;
		user = (Ta03_user) request.getSession().getAttribute("user");
		if (user == null) {
			return exceptionService.exceptionControl(this.getClass().getName(),
					"用户未登录或登录超时", new Exception("用户未登录"));
		}
		String startdate = convertUtil.toString(request
				.getParameter("startdate"), "");
		String enddate = convertUtil.toString(request.getParameter("enddate"),
				"");
		if (startdate.compareTo(enddate) > 0) {
			String temp = startdate;
			startdate = enddate;
			enddate = temp;
		}
		request.setAttribute("startdate", startdate);
		request.setAttribute("enddate", enddate);
		String hsql = "from Tz03_login_log where login_id='"
				+ user.getLogin_id()
				+ "' and login_date >= to_date('"
				+ (startdate.equals("")?"1900-01-01":startdate)
				+ " 00:00:00','yyyy-mm-dd hh24:mi:ss') and login_date <= to_date('"
				+ (enddate.equals("")?"2099-12-31":enddate)
				+ " 23:59:59','yyyy-mm-dd hh24:mi:ss') order by login_date desc";
		int totalPages = 1;
		int totalCount = 1;
		totalCount = convertUtil.toInteger(queryService.searchList(
				" select count(*) " + hsql).get(0));
		if (totalCount > 0) {
			totalPages = totalCount % numPerPage == 0 ? totalCount / numPerPage
					: totalCount / numPerPage + 1;
		} else {
			totalPages = 1;
		}
		if (pageNum < 1 || pageNum > totalPages) {
			pageNum = 1;
		}
		ModelMap modelMap = new ModelMap();
		modelMap.put("totalCount", totalCount);
		modelMap.put("totalPages", totalPages);
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		// 导EXCEL取全部数据
		if ("yes".equals(request.getParameter("toExcel"))) {
			numPerPage = totalCount == 0 ? 1 : totalCount;
			pageNum = 1;
		}
		ResultObject ro = queryService.searchByPage(
				"select id,login_date,login_id,login_ip,logout_date,systeminfo,user_name "
						+ hsql, pageNum, numPerPage);

		List<Tz03_login_log> list = new ArrayList<Tz03_login_log>();
		List<List> docList = new LinkedList<List>();
		while (ro.next()) {
			List list1 = new ArrayList();
			Tz03_login_log tz03 = new Tz03_login_log();
			tz03.setId((Long) ro.get("id"));
			tz03.setLogin_date((Date) ro.get("login_date"));
			list1.add((Date) ro.get("login_date"));
			list1.add((Date) ro.get("logout_date"));
			tz03.setLogin_id((String) ro.get("login_id"));
			tz03.setLogin_ip((String) ro.get("login_ip"));
			list1.add((String) ro.get("login_ip"));
			tz03.setLogout_date((Date) ro.get("logout_date"));
			tz03.setSysteminfo((String) ro.get("systeminfo"));
			list1.add((String) ro.get("systeminfo"));
			tz03.setUser_name((String) ro.get("user_name"));
			list.add(tz03);
			docList.add(list1);
		}
		modelMap.put("tz03_list", list);
		String form_title = "文档";
		if ("yes".equals(request.getParameter("toExcel"))) {
			Map<String, List> sheetMap = new HashMap<String, List>();
			List sheetList = new LinkedList();
			List titleList = new LinkedList();
			titleList.add("登录时间");
			titleList.add("登出时间");
			titleList.add("IP");
			titleList.add("系统信息");
			sheetList.add(titleList);
			sheetList.add(docList);
			sheetMap.put(form_title, sheetList);
			request.setAttribute("ExcelName", form_title);
			request.setAttribute("sheetMap", sheetMap);
			return new ModelAndView("/export/toExcelWhithList.do");
		}
		return new ModelAndView("/WEB-INF/jsp/search/loginLog.jsp", modelMap);
	}

	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

}
