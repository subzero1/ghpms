package com.ghpms.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.netsky.base.flow.buttonControl.Button;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.LoadFormListService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.StringFormatUtil;
import com.netsky.base.utils.DateGetUtil;
import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.flow.utils.MapUtil;

@Service("loadFormListService")
public class LoadFormListServiceImp implements LoadFormListService {
	/**
	 * 数据服务
	 */
	@Autowired
	private Dao dao;
	
	/**
	 * 数据库查询操作服务
	 */
	@Autowired
	private QueryService queryService;

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

	public void load(HttpServletRequest request, Map paraMap) throws Exception {

		Long module_id = null;
		Long project_id = null;
		Long doc_id = null;
		Long node_id = null;
		Long user_id = null;
		String user_name = null;
		Long flow_id = null;
		List<?> tmpList = null;
		Long cur_nd = new Long(DateGetUtil.getYear());
		Long tmp_nd = null;
		ResultObject ro = null;
		Class<?> clazz = null;
		StringBuffer hsql = null;
		HttpSession session=request.getSession();

		QueryBuilder queryBuilder = null;
		try {
			String t_module_id = StringFormatUtil.format((String) paraMap
					.get("module_id"), "-1");
			String t_flow_id = StringFormatUtil.format((String) paraMap
					.get("flow_id"), "-1");
			String t_project_id = StringFormatUtil.format((String) paraMap
					.get("project_id"), "-1");
			String t_doc_id = StringFormatUtil.format((String) paraMap
					.get("doc_id"), "-1");
			String t_node_id = StringFormatUtil.format((String) paraMap
					.get("node_id"), "-1");
			String t_user_id = StringFormatUtil.format((String) paraMap
					.get("user_id"), "-1");
			String t_year = StringFormatUtil.format((String) paraMap
					.get("year"), "-1");
			String cansave = StringFormatUtil.format((String) paraMap
					.get("cansave"), "no");
			Ta03_user user=(Ta03_user) session.getAttribute("user");

			module_id = convertUtil.toLong(t_module_id);
			project_id = convertUtil.toLong(t_project_id);
			doc_id = convertUtil.toLong(t_doc_id);
			node_id = convertUtil.toLong(t_node_id);
			flow_id = convertUtil.toLong(t_flow_id);
			user_id = convertUtil.toLong(t_user_id);
			hsql = new StringBuffer("");

			
			/**
			 * 以下为表单附件区域@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			 */

			Vector<HashMap<String, String>> v_slave = new Vector<HashMap<String, String>>();

			//
			
			if (v_slave.size() > 0) {
				request.setAttribute("extslave", v_slave);
			}

			// end if
		} catch (Exception e) {
			throw new Exception(
					"com.ghpms.serviceImpl.form.LoadFormListServiceImp:"
							+ e);
		}
	}

}
