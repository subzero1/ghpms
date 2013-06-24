package com.netsky.base.tree.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Tb17_approve;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.DateFormatUtil;

/**
 * @author Chiang
 * 
 * 审批明细
 */
@Controller("/tree/spyj.do")
public class SpmxController implements org.springframework.web.servlet.mvc.Controller {

	private static Map<Integer, String> approve_result = new HashMap<Integer, String>();
	static {
		approve_result.put(new Integer(-1), "默认");
		approve_result.put(new Integer(4), "同意");
		approve_result.put(new Integer(5), "修改");
		approve_result.put(new Integer(6), "不同意");
		approve_result.put(new Integer(7), "不发表意见");
	}

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

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		Long project_id = null;
		Long module_id = null;
		if (request.getParameter("project_id") != null && request.getParameter("project_id").length() > 0) {
			project_id = Long.valueOf(request.getParameter("project_id"));
		}
		if (request.getParameter("module_id") != null && request.getParameter("module_id").length() > 0) {
			module_id = Long.valueOf(request.getParameter("module_id"));
		}
		QueryBuilder queryBuilder = new HibernateQueryBuilder(Tb17_approve.class);
		queryBuilder.eq("project_id", project_id);
		if (module_id != null)
			queryBuilder.eq("module_id", module_id);
		queryBuilder.addOrderBy(Order.asc("id"));
		String HSql = "select ta06,tb17 from Tb17_approve tb17,Ta06_module ta06 where ta06.id = tb17.module_id and tb17.project_id = "
				+ project_id;
		if (module_id != null)
			HSql += " and tb17.module_id = " + module_id;
		HSql += " order by tb17.id asc";
		ResultObject ro = queryService.search(HSql);
		List<Result> list = new ArrayList<Result>();
		while (ro.next()) {
			Tb17_approve tb17 = (Tb17_approve) ro.get("tb17");
			Ta06_module ta06 = (Ta06_module) ro.get("ta06");
			Result r = new Result();
			r.check_idea = tb17.getCheck_idea();
			r.module_name = ta06.getName();
			r.check_result = approve_result.get(tb17.getCheck_result());
			r.oper_time = DateFormatUtil.FormatTime(tb17.getOper_time());
			r.user = tb17.getUser_name();
			list.add(r);
		}
		for (int i = 0; i < list.size(); i++) {
			for (int j = i - 1; j >= 0; j--) {
				Result r_l = list.get(i);
				Result r_b = list.get(j);
				if (!r_b.show_flag) {
					continue;
				} else if (r_l.module_name.equals(r_b.module_name) && r_b.show_flag) {
					r_b.rowspan++;
					r_l.show_flag = false;
					break;
				} else {
					break;
				}
			}
		}
		request.setAttribute("list", list);
		return new ModelAndView("/WEB-INF/jsp/tree/spmx.jsp");
	}

	public class Result {
		private String user;

		private String check_result;

		private String check_idea;

		private String oper_time;

		private String module_name;

		private boolean show_flag = true;

		private Integer rowspan = 1;

		/**
		 * @return the user
		 */
		public String getUser() {
			return user;
		}

		/**
		 * @param user
		 *            the user to set
		 */
		public void setUser(String user) {
			this.user = user;
		}

		/**
		 * @return the check_result
		 */
		public String getCheck_result() {
			return check_result;
		}

		/**
		 * @param check_result
		 *            the check_result to set
		 */
		public void setCheck_result(String check_result) {
			this.check_result = check_result;
		}

		/**
		 * @return the check_idea
		 */
		public String getCheck_idea() {
			return check_idea;
		}

		/**
		 * @param check_idea
		 *            the check_idea to set
		 */
		public void setCheck_idea(String check_idea) {
			this.check_idea = check_idea;
		}

		/**
		 * @return the oper_time
		 */
		public String getOper_time() {
			return oper_time;
		}

		/**
		 * @param oper_time
		 *            the oper_time to set
		 */
		public void setOper_time(String oper_time) {
			this.oper_time = oper_time;
		}

		/**
		 * @return the module_name
		 */
		public String getModule_name() {
			return module_name;
		}

		/**
		 * @param module_name
		 *            the module_name to set
		 */
		public void setModule_name(String module_name) {
			this.module_name = module_name;
		}

		/**
		 * @return the show_flag
		 */
		public boolean isShow_flag() {
			return show_flag;
		}

		/**
		 * @param show_flag
		 *            the show_flag to set
		 */
		public void setShow_flag(boolean show_flag) {
			this.show_flag = show_flag;
		}

		/**
		 * @return the rowspan
		 */
		public Integer getRowspan() {
			return rowspan;
		}

		/**
		 * @param rowspan
		 *            the rowspan to set
		 */
		public void setRowspan(Integer rowspan) {
			this.rowspan = rowspan;
		}

	}
}
