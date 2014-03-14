package com.netsky.base.controller.search;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.dataObjects.Ta08_reportfield;
import com.netsky.base.dataObjects.Ta29_report_template;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 查询、统计报表条件
 * 
 * @author Chiang 2010-01-19
 */
@Controller("/search/condition.do")
public class Condition implements org.springframework.web.servlet.mvc.Controller {

	@Autowired
	private QueryService queryService;

	@Autowired
	private ExceptionService exceptionService;

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

		/**
		 * 关键字类型存放list
		 */
		List<?> keyList = null;
		/**
		 * 多选类型存放list
		 */
		List<?> selectList = null;
		/**
		 * 数字类型存放list
		 */
		List<?> numberList = null;
		/**
		 * 人员类型存放list
		 */
		List<?> userList = null;
		/**
		 * 日期类型存放list
		 */
		List<?> dateList = null;

		/**
		 * 统计选项存放list
		 */
		List<?> statisticList = null;

		/**
		 * 查询字段选择
		 */
		List<?> fieldList = null;

		QueryBuilder queryBuilder;
		try {
			HttpSession session = request.getSession();
			Ta03_user user=(Ta03_user)session.getAttribute("user");
			if (user == null) {
				return exceptionService.exceptionControl(this.getClass().getName(), "用户未登录或登录超时",
						new Exception("用户未登录"));
			}
			/**
			 * 获取用户模板
			 */
			queryBuilder = new HibernateQueryBuilder(Ta29_report_template.class);
			if (user.getId()==1) {
				queryBuilder.eq("user_id", user.getId());
			}else {
				queryBuilder.in("user_id", new Object[]{new Long(user.getId()),new Long(1)});
			}
			if (type.equals("reportflag")) {
				queryBuilder.eq("type", new Long(1));
			} else {
				queryBuilder.eq("type", new Long(2));
			}
			queryBuilder.eq("module_id", module_id);
			templateList = queryService.searchList(queryBuilder);

			/**
			 * 获取关键字类型查询字段,searchtype=1;
			 */
			queryBuilder = new HibernateQueryBuilder(Ta08_reportfield.class);
			queryBuilder.eq(type, new Long(1));
			queryBuilder.eq("searchtype", new Long(1));
			queryBuilder.eq("module_id", module_id);
			queryBuilder.addOrderBy(Order.asc("ord"));
			keyList = queryService.searchList(queryBuilder);

			/**
			 * 获取多选类型查询字段,searchtype=2;
			 */
			queryBuilder = new HibernateQueryBuilder(Ta08_reportfield.class);
			queryBuilder.eq(type, new Long(1));
			queryBuilder.eq("searchtype", new Long(2));
			queryBuilder.eq("module_id", module_id);
			queryBuilder.addOrderBy(Order.asc("ord"));
			selectList = queryService.searchList(queryBuilder);

			/**
			 * 获取数字类型查询字段,searchtype=3;
			 */
			queryBuilder = new HibernateQueryBuilder(Ta08_reportfield.class);
			queryBuilder.eq(type, new Long(1));
			queryBuilder.eq("searchtype", new Long(3));
			queryBuilder.eq("module_id", module_id);
			queryBuilder.addOrderBy(Order.asc("ord"));
			numberList = queryService.searchList(queryBuilder);

			/**
			 * 获取人员类型查询字段,searchtype=4;
			 */
			queryBuilder = new HibernateQueryBuilder(Ta08_reportfield.class);
			queryBuilder.eq(type, new Long(1));
			queryBuilder.eq("searchtype", new Long(4));
			queryBuilder.eq("module_id", module_id);
			queryBuilder.addOrderBy(Order.asc("ord"));
			userList = queryService.searchList(queryBuilder);

			/**
			 * 获取人员类型查询字段,searchtype=5;
			 */
			queryBuilder = new HibernateQueryBuilder(Ta08_reportfield.class);
			queryBuilder.eq(type, new Long(1));
			queryBuilder.eq("searchtype", new Long(5));
			queryBuilder.eq("module_id", module_id);
			queryBuilder.addOrderBy(Order.asc("ord"));
			dateList = queryService.searchList(queryBuilder);

			/**
			 * 获取统计选项
			 */
			if (type.equals("reportflag")) {
				queryBuilder = new HibernateQueryBuilder(Ta08_reportfield.class);
				queryBuilder.eq("statisticflag", new Long(1));
				queryBuilder.eq("module_id", module_id);
				queryBuilder.addOrderBy(Order.asc("comments"));
				statisticList = queryService.searchList(queryBuilder);
			} else {
				queryBuilder = new HibernateQueryBuilder(Ta08_reportfield.class);
				queryBuilder.eq(type, new Long(1));
				queryBuilder.addOrderBy(Order.asc("searchtype"));
				queryBuilder.eq("module_id", module_id);
				queryBuilder.addOrderBy(Order.asc("comments"));
				fieldList = queryService.searchList(queryBuilder);
			}
			
			/**
			 * 取默認部門:當前部門+下屬一級部門
			 */
			StringBuffer hql=new StringBuffer();
			hql.append("select distinct(a.name) from Ta01_dept a where a.id=");
			hql.append(user.getDept_id());
			hql.append(" or a.parent_dept=");
			hql.append(user.getDept_id());
			List xqbmList=queryService.searchList(hql.toString());
			String xqbms = null;
			for (int i = 0; i < xqbmList.size(); i++) {
				if (i==0) {
					xqbms=(String) xqbmList.get(i).toString();
				}else {
					xqbms+=","+xqbmList.get(i).toString();
				}
			}
			request.setAttribute("xqbms", xqbms);
		} catch (Exception e) {
			return exceptionService.exceptionControl("ProjectReport", "获取报表选项错误", e);
		}

		request.setAttribute("templateList", templateList);
		request.setAttribute("keyList", keyList);
		request.setAttribute("selectList", selectList);
		request.setAttribute("numberList", numberList);
		request.setAttribute("userList", userList);
		request.setAttribute("dateList", dateList);
		request.setAttribute("statisticList", statisticList);
		request.setAttribute("fieldList", fieldList);
		if (type.equals("reportflag")) {
			return new ModelAndView("/WEB-INF/jsp/search/reportCondition.jsp");
		} else {
			return new ModelAndView("/WEB-INF/jsp/search/searchCondition.jsp");
		}
	}

}
