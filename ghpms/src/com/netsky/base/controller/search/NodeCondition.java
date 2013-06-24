package com.netsky.base.controller.search;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Ta08_reportfield;
import com.netsky.base.dataObjects.Ta29_report_template;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;

/**
 * 查询、统计报表条件
 * 
 * @author Chiang 2010-01-19
 */
@Controller("/search/nodecondition.do")
public class NodeCondition implements org.springframework.web.servlet.mvc.Controller {

	@Autowired
	private QueryService queryService;

	@Autowired
	private ExceptionService exceptionService;

	private ModelMap modelMap = new ModelMap();
	private String view = "/WEB-INF/jsp/search/nodeSearchCondition.jsp";

	/**
	 * @return the queryService
	 */
	public QueryService getQueryService() {
		return queryService;
	}

	/**
	 * @param queryService
	 *            the queryService to set
	 */
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	/**
	 * @return the exceptionService
	 */
	public ExceptionService getExceptionService() {
		return exceptionService;
	}

	/**
	 * @param exceptionService
	 *            the exceptionService to set
	 */
	public void setExceptionService(ExceptionService exceptionService) {
		this.exceptionService = exceptionService;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type;
		if ("report".equals(request.getParameter("type"))) {
			type = "reportflag";
		} else {
			type = "searchflag";
		}
		Long module_id;
		if (request.getParameter("module_id") != null && !request.getParameter("module_id").equals("")) {
			module_id = Long.valueOf(request.getParameter("module_id"));
		} else {
			module_id = new Long(100);
		}
		/**
		 * 模板list
		 */
		List<?> templateList = null;

		List<?> selectList = null;

		QueryBuilder queryBuilder;
		try {
			HttpSession session = request.getSession();
			Ta03_user user = (Ta03_user) session.getAttribute("user");
			if (user == null) {
				return exceptionService.exceptionControl(this.getClass().getName(), "用户未登录或登录超时",
						new Exception("用户未登录"));
			}
			/**
			 * 获取用户模板
			 */
			queryBuilder = new HibernateQueryBuilder(Ta29_report_template.class);
			queryBuilder.eq("user_id", user.getId());
			if (type.equals("reportflag")) {
				queryBuilder.eq("type", new Long(1));
			} else {
				queryBuilder.eq("type", new Long(2));
			}
			queryBuilder.eq("module_id", module_id);
			templateList = queryService.searchList(queryBuilder);

			/**
			 * 获取多选类型查询字段,searchtype=2;
			 */
			Object[] values = new Object[] { "ssdq", "zydl", "zyxx", "qkdl", "qkxl", "gclb", "tzlb" };
			queryBuilder = new HibernateQueryBuilder(Ta08_reportfield.class);
			queryBuilder.eq(type, new Long(1));
			queryBuilder.eq("searchtype", new Long(2));
			queryBuilder.eq("module_id", new Long(101));
			queryBuilder.in("name", values);
			queryBuilder.addOrderBy(Order.asc("ord"));
			selectList = queryService.searchList(queryBuilder);
			
			/**
			 * 获取默认字段,searchtype=2;
			 */
			
			
		} catch (Exception e) {
			return exceptionService.exceptionControl("ProjectReport", "获取报表选项错误", e);
		}

		/**
		 * 表单名称下拉框选项
		 */
		List searchField=new ArrayList();
		
		
		
		List<Ta06_module> bdmcList = (List<Ta06_module>) queryService
				.searchList("from Ta06_module where type = 1 and id like '1__' order by id");
		modelMap.put("bdmcList", bdmcList);
		request.setAttribute("templateList", templateList);
		request.setAttribute("selectList", selectList);
		//		 
//		return new ModelAndView("/WEB-INF/jsp/search/nodeSearchCondition.jsp");
		return new ModelAndView(view,modelMap);
	}

}
