package com.netsky.base.controller.personalization;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta07_formfield;
import com.netsky.base.dataObjects.Ta11_sta_user;
import com.netsky.base.dataObjects.Ta31_worklist_cfg;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.service.impl.SaveServiceImpl;
import com.netsky.base.utils.convertUtil;

/**
 * 工作列表配置
 * 
 * @author wangflan 2010-05-06
 */
@Controller
public class WorkListConfig {

	@Autowired
	private QueryService queryService;

	@Autowired
	private SaveService saveService;

	@Autowired
	private Dao dao;

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

	@RequestMapping("/workListCfg.do")
	public ModelAndView workListCfg(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Ta03_user user = (Ta03_user) session.getAttribute("user");
		String user_id = user.getId().toString();
		/**
		 * 默认配置信息存放list
		 */
		List<?> defaultList = null;

		/**
		 * 配置信息存放list
		 */
		List<?> configList = null;
		/**
		 * 文档字段存放list
		 */
		List<?> fieldList = null;

		QueryBuilder queryBuilder;

		try {
			/**
			 * 获取配置信息
			 */
			String config_hsql = "select ta07 from Ta31_worklist_cfg ta31,Ta07_formfield ta07 where ta31.user_id="
					+ user_id + " and ta31.field_id=ta07.id ";
			configList = queryService.searchList(config_hsql
					+ "order by ta31.ord");

			/**
			 * 获取文档字段,未配置的获取默认配置列表
			 */
			if (configList.isEmpty()) {

				config_hsql = "select ta07 from Ta07_formfield ta07 where module_id=100 and ta07.show_flag = 1 ";
				configList = queryService.searchList(config_hsql
						+ "order by ord");
			}
			String field_hsql = "select ta07 from Ta07_formfield ta07 where module_id=100 and ta07 not in("
					+ config_hsql + ") order by ord";
			fieldList = queryService.searchList(field_hsql);

		} catch (Exception e) {
			return exceptionService.exceptionControl(
					"com.rms.controller.personalization.WorkListConfig",
					"获取工作列表字段配置错误", e);
		}
		request.setAttribute("configList", configList);

		request.setAttribute("fieldList", fieldList);

		return new ModelAndView(
				"/WEB-INF/jsp/personalization/worklistConfig.jsp");
	}

	@RequestMapping("/saveworkListConfig.do")
	public void saveworkListConfig(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			response.setCharacterEncoding(request.getCharacterEncoding());
			String[] stas = request.getParameterValues("t_sta");
			Long user_id = ((Ta03_user) request.getSession().getAttribute(
					"user")).getId();
			String forwardUrl = "";
			saveService
			.updateByHSql("delete from Ta31_worklist_cfg where user_id="
					+ user_id);
			if (!"yes".equals(request.getParameter("clear"))) {
				int i = 0;
				for (String field_id : stas) {
					String field_id1 = field_id.substring(0,field_id.indexOf("("));
					Ta31_worklist_cfg ta31_worklist_cfg = new Ta31_worklist_cfg();
					ta31_worklist_cfg.setField_id(Long.parseLong(field_id1));
					ta31_worklist_cfg.setOrd(i++);
					ta31_worklist_cfg.setUser_id(user_id);
					saveService.save(ta31_worklist_cfg);
				}
			}
			response
					.getWriter()
					.print(
							"{\"statusCode\":\"200\", \"message\":\"设置成功!本次操作在下次登录前不会生效\", \"navTabId\":\"\",\"forwardUrl\":\""
									+ forwardUrl + "\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(
					"{\"statusCode\":\"300\", \"message\":\"操作失败\"}");
		}
	}
}
