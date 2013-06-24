package com.netsky.base.controller.search;
 
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta08_reportfield;
import com.netsky.base.dataObjects.Ta29_report_template;
import com.netsky.base.dataObjects.Ta30_template_list;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

/**
 * 工程报表级查询模板设置
 * 
 * @author Chiang
 * 
 */
@Controller
public class ReportTemplate {
	@Autowired
	private QueryService queryService;

	@Autowired
	private ExceptionService exceptionService;

	@Autowired
	private SaveService saveService;

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

	/**
	 * @return the saveService
	 */
	public SaveService getSaveService() {
		return saveService;
	}

	/**
	 * @param saveService
	 *            the saveService to set
	 */
	public void setSaveService(SaveService saveService) {
		this.saveService = saveService;
	}

	/**
	 * 模板保存
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/search/saveTemplate.do")
	public void saveTemplate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		ResultObject ro;
		String HSql;
		QueryBuilder queryBuilder;

		String list_type = "";
		String key = "";
		String value = "";
		Ta30_template_list ta30;
		List<?> list;
		Long type = null;
		Long module_id;
		if (request.getParameter("module_id") != null
				&& !request.getParameter("module_id").equals("")) {
			module_id = Long.valueOf(request.getParameter("module_id"));
		} else {
			module_id = new Long(100);
		}
		Long template_id = null;
		Ta29_report_template ta29 = null;
		String flag = "";
		try {
			/**
			 * 处理模板表
			 */
			if (request.getParameter("type") != null
					&& request.getParameter("type").length() > 0) {
				type = Long.valueOf(request.getParameter("type"));
			} else {
				type = new Long(1);
			}
			if (request.getParameter("template_id") != null
					&& request.getParameter("template_id").length() > 0) {
				template_id = Long.valueOf(request.getParameter("template_id"));
				ta29 = (Ta29_report_template) queryService.searchById(
						Ta29_report_template.class, template_id);
				ta29.setName(request.getParameter("template_name"));
				flag = "update";
			} else {
				ta29 = new Ta29_report_template();
				Ta03_user user = (Ta03_user) request.getSession().getAttribute(
						"user");
				if (user == null) {
					response.getWriter().print(
							"{\"statusCode\":\"200\", \"message\":\"用户未登录\", \"act\":\""
									+ flag + "\", \"template_name\":\""
									+ ta29.getName() + "\", \"template_id\":\""
									+ ta29.getId() + "\"}");
					return;
				}
				ta29.setUser_id(user.getId());
				ta29.setUser_name(user.getName());
				ta29.setName(request.getParameter("template_name"));
				ta29.setType(type);
				ta29.setModule_id(module_id);
				flag = "insert";
			}
			saveService.save(ta29);
			template_id = ta29.getId();

			if (ta29.getType().intValue() == 1) {
				/**
				 * 工程统计
				 */
				/**
				 * 处理统计字段
				 */
				String sum[] = request.getParameterValues("sum");
				//if (sum == null || sum.length == 0) {
					//throw new Exception("未找到统计信息");
				//}
				if(sum != null){
					for (int i = 0; i < sum.length; i++) {
						value += "," + sum[i];
					}
				}
				value = value.replaceFirst(",", "");
				list_type = "sum_field";
				key = "sum_field";
				queryBuilder = new HibernateQueryBuilder(
						Ta30_template_list.class);
				queryBuilder.eq("parent_id", template_id);
				queryBuilder.eq("key", key);
				queryBuilder.eq("type", list_type);
				list = queryService.searchList(queryBuilder);
				if (list.size() > 0) {
					ta30 = (Ta30_template_list) list.get(0);
					ta30.setValue(value);
				} else {
					ta30 = new Ta30_template_list();
					ta30.setParent_id(template_id);
					ta30.setType(list_type);
					ta30.setKey(key);
					ta30.setValue(value);
				}
				saveService.save(ta30);

				/**
				 * 处理横纵统计项
				 */
				String statistic[] = request.getParameterValues("statistic");
				String way[] = request.getParameterValues("way");
				String xResult = "";
				String yResult = "";
				if (statistic != null && way != null
						&& statistic.length == way.length) {
					for (int i = 0; i < statistic.length; i++) {
						if ("X".equals(way[i])) {
							xResult += "," + statistic[i];
						} else if (statistic[i] != null
								&& statistic[i].length() > 0) {
							yResult += "," + statistic[i];
						}
					}
				}
				xResult = xResult.replaceFirst(",", "");
				yResult = yResult.replaceFirst(",", "");

				/**
				 * x坐标
				 */
				key = "xway";
				list_type = "xway";
				queryBuilder = new HibernateQueryBuilder(
						Ta30_template_list.class);
				queryBuilder.eq("parent_id", template_id);
				queryBuilder.eq("key", key);
				queryBuilder.eq("type", list_type);
				list = queryService.searchList(queryBuilder);
				if (list.size() > 0) {
					ta30 = (Ta30_template_list) list.get(0);
					ta30.setValue(xResult);
				} else {
					ta30 = new Ta30_template_list();
					ta30.setParent_id(template_id);
					ta30.setType(list_type);
					ta30.setKey(key);
					ta30.setValue(xResult);
				}
				saveService.save(ta30);

				/**
				 * y坐标
				 */
				key = "yway";
				list_type = "yway";
				queryBuilder = new HibernateQueryBuilder(
						Ta30_template_list.class);
				queryBuilder.eq("parent_id", template_id);
				queryBuilder.eq("key", key);
				queryBuilder.eq("type", list_type);
				list = queryService.searchList(queryBuilder);
				if (list.size() > 0) {
					ta30 = (Ta30_template_list) list.get(0);
					ta30.setValue(yResult);
				} else {
					ta30 = new Ta30_template_list();
					ta30.setParent_id(template_id);
					ta30.setType(list_type);
					ta30.setKey(key);
					ta30.setValue(yResult);
				}
				saveService.save(ta30);
			} else {
				/**
				 * 工程查询
				 */
				String[] cols = request.getParameterValues("fields_select");
				for (int i = 0; cols != null && i < cols.length; i++) {
					value += "," + cols[i];
				}
				value = value.replaceFirst(",", "");
				list_type = "select_field";
				key = "select_field";
				queryBuilder = new HibernateQueryBuilder(
						Ta30_template_list.class);
				queryBuilder.eq("parent_id", template_id);
				queryBuilder.eq("key", key);
				queryBuilder.eq("type", list_type);
				list = queryService.searchList(queryBuilder);
				if (list.size() > 0 && (value == null || value.length() == 0)) {
					ta30 = (Ta30_template_list) list.get(0);
					saveService.removeObject(ta30);
				} else if (list.size() > 0) {
					ta30 = (Ta30_template_list) list.get(0);
					ta30.setValue(value);
					saveService.save(ta30);
				} else {
					ta30 = new Ta30_template_list();
					ta30.setParent_id(template_id);
					ta30.setType(list_type);
					ta30.setKey(key);
					ta30.setValue(value);
					saveService.save(ta30);
				}
			}

			/**
			 * 处理查询字段
			 */
			String ids[] = request.getParameterValues("ids");
			String id = "";
			if (ids == null || ids.length == 0) {
				throw new Exception("未找到查询关键字");
			}
			for (int i = 0; i < ids.length; i++) {
				id += "," + ids[i];
			}
			id = id.replaceFirst(",", "");
			HSql = "select ta08 from Ta08_reportfield ta08 where ta08.id in ("
					+ id + ")";
			ro = queryService.search(HSql);
			while (ro.next()) {
				Ta08_reportfield ta08 = (Ta08_reportfield) ro.get("ta08");
				list_type = "";
				key = "" + ta08.getId();
				value = "";
				if (ta08.getSearchtype().intValue() == 1) {
					/**
					 * 关键字类型
					 */
					list_type = "key";
					if (request.getParameter(ta08.getId() + "") != null
							&& request.getParameter(ta08.getId() + "").length() > 0) {
						value = request.getParameter(ta08.getId() + "");
					}
				} else if (ta08.getSearchtype().intValue() == 2
						|| ta08.getSearchtype().intValue() == 4) {
					/**
					 * 多选类型与人员类型
					 */
					list_type = "key";
					if (request.getParameter(ta08.getId() + "") != null
							&& request.getParameter(ta08.getId() + "").length() > 0) {
						value = request.getParameter(ta08.getId() + "");
					}
				} else if (ta08.getSearchtype().intValue() == 3) {
					/**
					 * 金额类型
					 */
					list_type = "interval";
					if (request.getParameter(ta08.getId() + "_low") != null
							&& request.getParameter(ta08.getId() + "_low")
									.length() > 0) {
						value += "low="
								+ request.getParameter(ta08.getId() + "_low")
								+ ",";
					}
					if (request.getParameter(ta08.getId() + "_high") != null
							&& request.getParameter(ta08.getId() + "_high")
									.length() > 0) {
						value += "high="
								+ request.getParameter(ta08.getId() + "_high");
					}
				} else if (ta08.getSearchtype().intValue() == 5) {
					/**
					 * 日期类型
					 */
					list_type = "interval";
					if (request.getParameter(ta08.getId() + "_low") != null
							&& request.getParameter(ta08.getId() + "_low")
									.length() > 0) {
						value += "low="
								+ request.getParameter(ta08.getId() + "_low")
								+ ",";
					}
					if (request.getParameter(ta08.getId() + "_high") != null
							&& request.getParameter(ta08.getId() + "_high")
									.length() > 0) {
						value += "high="
								+ request.getParameter(ta08.getId() + "_high");
					}
				}
				queryBuilder = new HibernateQueryBuilder(
						Ta30_template_list.class);
				queryBuilder.eq("parent_id", template_id);
				queryBuilder.eq("key", ta08.getId() + "");
				queryBuilder.eq("type", list_type);
				list = queryService.searchList(queryBuilder);
				if (list.size() > 0) {
					ta30 = (Ta30_template_list) list.get(0);
					ta30.setValue(value);
				} else {
					ta30 = new Ta30_template_list();
					ta30.setParent_id(template_id);
					ta30.setType(list_type);
					ta30.setKey(key);
					ta30.setValue(value);
				}
				saveService.save(ta30);
			}
		} catch (Exception e) {
			response.getWriter().print(
					"{\"statusCode\":\"200\", \"message\":\"保存失败\", \"act\":\""
							+ flag + "\", \"template_name\":\""
							+ ta29.getName() + "\", \"template_id\":\""
							+ ta29.getId() + "\"}");
			return;
		}
		// request.setAttribute("ta29", ta29);
		// request.setAttribute("flag", flag);
		response.getWriter().print(
				"{\"statusCode\":\"200\", \"message\":\"保存成功\", \"act\":\""
						+ flag + "\", \"template_name\":\"" + ta29.getName()
						+ "\", \"template_id\":\"" + ta29.getId() + "\"}");
	}

	/**
	 * 获取模板
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/search/getTemplate.do")
	public void getTemplate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		response.setContentType("text/xml");
		QueryBuilder queryBuilder;
		List<?> list;

		Long template_id = null;
		Ta30_template_list ta30;
		if (request.getParameter("template_id") != null
				&& request.getParameter("template_id").length() > 0) {
			template_id = Long.valueOf(request.getParameter("template_id"));
		} else {
			response.getWriter().print(
					"<? xml version=\"1.0\" encoding=\"GBK\"?>");
			response.getWriter().print("<root>");
			response.getWriter().print("</root>");
			return;
		}
		queryBuilder = new HibernateQueryBuilder(Ta30_template_list.class);
		queryBuilder.eq("parent_id", template_id);
		list = queryService.searchList(queryBuilder);
		response.getWriter().print("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		response.getWriter().print("<root>");
		for (int i = 0; i < list.size(); i++) {
			ta30 = (Ta30_template_list) list.get(i);
			response.getWriter().print("<template>");

			response.getWriter().print("<type>");
			response.getWriter().print(ta30.getType());
			response.getWriter().print("</type>");

			response.getWriter().print("<key>");
			response.getWriter().print(ta30.getKey());
			response.getWriter().print("</key>");

			response.getWriter().print("<value>");
			response.getWriter().print(
					ta30.getValue() != null ? ta30.getValue() : "");
			response.getWriter().print("</value>");

			response.getWriter().print("</template>");
		}
		response.getWriter().print("</root>");
	}

	/**
	 * 删除模板
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/search/delTemplate.do")
	public void delTemplate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		String HSql;
		Long template_id = null;
		if (request.getParameter("template_id") != null
				&& request.getParameter("template_id").length() > 0) {
			template_id = Long.valueOf(request.getParameter("template_id"));
			HSql = "delete from Ta30_template_list where parent_id = "
					+ template_id;
			saveService.updateByHSql(HSql);
			HSql = "delete from Ta29_report_template where id = " + template_id;
			saveService.updateByHSql(HSql);
		} else {
			response.getWriter().print(
					"{\"statusCode\":\"200\", \"message\":\"删除失败\"}");
			return;
		}
		response.getWriter().print(
				"{\"statusCode\":\"200\", \"message\":\"删除成功\"}");
	}
	
	@RequestMapping("/search/queryTemplate.do")
	public ModelAndView queryTemplate(HttpServletRequest request,HttpServletResponse response){
		String view ="/WEB-INF/jsp/search/queryTemplateList.jsp";
		ModelMap modelMap=new ModelMap();
		StringBuffer hql=new StringBuffer();
		ResultObject ro=null;
		Integer totalPages = 1;
		Integer totalCount = 0;
		List objectList=new ArrayList();
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "name");
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");
		String keyword=convertUtil.toString(request.getParameter("keyword"));
		Ta03_user user=(Ta03_user) request.getSession().getAttribute("user");
		
		hql.append("select ta29,ta06.name from Ta29_report_template ta29,Ta06_module ta06 where 1=1 ");
		hql.append("and ta29.module_id=ta06.id ");
		if (user!=null) {
			hql.append("and ta29.user_name='");
			hql.append(user.getName());
			hql.append("' ");
		}
		if (keyword!=null&&!keyword.equals("")) {
			hql.append("and ta29.name like '%");
			hql.append(keyword);
			hql.append("%' ");
		} 
		hql.append("order by ta29.");
		hql.append(orderField);
		hql.append(" ");
		hql.append(orderDirection);
		ro=queryService.searchByPage(hql.toString(), pageNum, numPerPage);
		if (ro!=null) {
		while (ro.next()) {
			Object object[]=new Object[2];
			object[0]=ro.get("ta29");
			object[1]=ro.get("ta06.name");
			objectList.add(object);
		}
			totalCount=ro.getTotalRows();
			totalPages=ro.getTotalPages();
		}
		modelMap.put("keyword", keyword);
		modelMap.put("objectList", objectList);
		modelMap.put("totalCount", totalCount);
		modelMap.put("totalPages", totalPages);
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		return new ModelAndView(view, modelMap);
	}
	
	@RequestMapping("/search/queryForBoss.do")
	public ModelAndView queryForBoss(HttpServletRequest request,HttpServletResponse response){
		String view ="/WEB-INF/jsp/search/queryForBossList.jsp";
		ModelMap modelMap=new ModelMap();
		StringBuffer hql=new StringBuffer();
		ResultObject ro=null;
		Integer totalPages = 1;
		Integer totalCount = 0;
		List objectList=new ArrayList();
		Ta03_user user=(Ta03_user) request.getSession().getAttribute("user");
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "seq");
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");
		String keyword=convertUtil.toString(request.getParameter("keyword"));
		hql.append("from Tc13_report_title tc13 where 1=1 ");
		if (!keyword.equals("")) {
			hql.append("and (tc13.title like '%");
			hql.append(keyword);
			hql.append("%' or tc13.remark like '%");
			hql.append(keyword);
			hql.append("%') ");
		} 
		hql.append("order by ");
		hql.append(orderField);
		hql.append(" ");
		hql.append(orderDirection);
		ro=queryService.searchByPage(hql.toString(), pageNum, numPerPage);
		if (ro!=null) {
			while (ro.next()) {
				Object object=ro.get("tc13");
				objectList.add(object);
			}
			totalCount=ro.getTotalRows();
			totalPages=ro.getTotalPages();
		}
		modelMap.put("keyword", keyword);
		modelMap.put("objectList", objectList);
		modelMap.put("totalCount", totalCount);
		modelMap.put("totalPages", totalPages);
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		return new ModelAndView(view, modelMap);
	}
}
