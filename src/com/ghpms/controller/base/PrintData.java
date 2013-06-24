package com.ghpms.controller.base;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.hibernate.criterion.Order;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.StringFormatUtil;
import com.netsky.base.dataObjects.Ta33_tszf;
import com.netsky.base.flow.utils.convertUtil;
import java.lang.reflect.Field;

@Controller
public class PrintData {

	@Autowired
	private QueryService queryService;


	@SuppressWarnings("unchecked")
	@RequestMapping("/print.do")
	public ModelAndView print(HttpServletRequest request, HttpServletResponse response) throws Exception {
		InetAddress addr = InetAddress.getLocalHost();
		String IP = addr.getHostAddress().toString();
		String url = "http://" + IP + ":" + request.getLocalPort() + request.getContextPath();
		String flag = request.getParameter("flag");
		
		if(flag == null || "single".equals(flag)){
			url += "/formprint.do";
			url += "?project_id=" + request.getParameter("project_id");
			url += "&module_id=" + request.getParameter("module_id");
			url += "&doc_id=" + request.getParameter("doc_id");
			request.setAttribute("url", url);
		}
		if("batch".equals(flag)){
			url += "/bacthprint.do";
			url += "?project_ids=" + request.getParameter("project_id");
			url += "&module_ids=" + request.getParameter("module_id");
			url += "&pages=" + request.getParameter("pages");
			url += "&filter=" + request.getParameter("filter");
			request.setAttribute("url", url);
		}
		return new ModelAndView("/WEB-INF/jsp/print_show.jsp");
	}

	/**
	 * @param args
	 * 表单打印
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/formprint.do")
	public ModelAndView formdata(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		Long project_id = null;
		Long doc_id = null;
		Long module_id = null;
		Class<?> clazz = null;
		String infoTable = null;
		String formTable = null;
		String auxTables = null;
		String detailTables = null;
		String[] auxTableArray = null;
		String[] detailTableArray = null;
		QueryBuilder queryBuilder = null;
		ResultObject ro;
		List module_list = new ArrayList();
		try {
			Map module_map = new HashMap();
			project_id = new Long(StringFormatUtil.format(request.getParameter("project_id"), "-1"));
			doc_id = new Long(StringFormatUtil.format(request.getParameter("doc_id"), StringFormatUtil.format(request.getParameter("id"), "-1")));
			module_id = new Long(StringFormatUtil.format(request.getParameter("module_id"), "-1"));
			
			/**
			 * 读Ta06_module表,得到表名信息
			 */
			clazz = Ta06_module.class;
			Object o_module = queryService.searchById(clazz, module_id);

			module_map.put("module_id", module_id);
			module_map.put("pages", new Long(1));
			// request.setAttribute("module",o_module);

			/**
			 * 获取工程信息表
			 */
			infoTable = (String) PropertyInject.getProperty(o_module, "project_table");

			/**
			 * 获取表单主表
			 */
			formTable = (String) PropertyInject.getProperty(o_module, "form_table");

			/**
			 * 获取表单辅表，通过project_id关联
			 */
			auxTables = StringFormatUtil.format((String) PropertyInject.getProperty(o_module, "aux_table"));
			if (!auxTables.equals("")) {
				auxTableArray = auxTables.split(",");
			}

			/**
			 * 获取明细表名称，通过parent_id关联
			 */
			detailTables = StringFormatUtil.format((String) PropertyInject.getProperty(o_module, "detail_table"));
			if (!detailTables.equals("")) {
				detailTableArray = detailTables.split(",");
			}

			/**
			 * 获得工程信息数据
			 */
			if (!StringFormatUtil.format(infoTable, "").equals("")) {
				clazz = Class.forName(infoTable);
				queryBuilder = new HibernateQueryBuilder(clazz);
				queryBuilder.eq("id", project_id);
				ro = queryService.search(queryBuilder);
				if (ro.next()) {
					Object o_info = ro.get(clazz.getName());
					replaceSpecChar(o_info,clazz);
					module_map.put(clazz.getSimpleName().toLowerCase(), o_info);
				}
			}

			/**
			 * 获得表单主表数据
			 */
			clazz = Class.forName(formTable);
			queryBuilder = new HibernateQueryBuilder(clazz);
			queryBuilder.eq("id", doc_id);
			ro = queryService.search(queryBuilder);
			if (ro.next()) {
				Object o_form = ro.get(clazz.getName());
				replaceSpecChar(o_form,clazz);
				module_map.put(clazz.getSimpleName().toLowerCase(), o_form);
			}

			/**
			 * 获得表单明细表数据
			 */
			if (detailTableArray != null) {
				for (int i = 0; i < detailTableArray.length; i++) {
					clazz = Class.forName(detailTableArray[i]);
					queryBuilder = new HibernateQueryBuilder(clazz);
					queryBuilder.eq("parent_id", doc_id);
					List<?> detailList = queryService.searchList(queryBuilder);
					for(Object obj:detailList){
						replaceSpecChar(obj,clazz);
					}
					module_map.put(clazz.getSimpleName().toLowerCase(), detailList);
				}
			}

			/**
			 * 获得表单辅表数据
			 */
			if (auxTableArray != null) {
				for (int i = 0; i < auxTableArray.length; i++) {
					clazz = Class.forName(auxTableArray[i]);
					queryBuilder = new HibernateQueryBuilder(clazz);
					queryBuilder.eq("project_id", project_id);
					ro = queryService.search(queryBuilder);
					if (ro.next()) {
						Object o_aux = ro.get(clazz.getName());
						replaceSpecChar(o_aux,clazz);
						module_map.put(clazz.getSimpleName().toLowerCase(), o_aux);
					}
				}
			}
			module_list.add(module_map);
		} catch (Exception e) {
			System.out.println(e);
		}
		request.setAttribute("module_map", module_list);
		return new ModelAndView("/WEB-INF/jsp/print_web.jsp");
	}

	/**
	 * @param args
	 * 批量打印
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/bacthprint.do")
	public ModelAndView formdatas(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		Long project_id = null;
		Long doc_id = null;
		Class<?> clazz = null;
		String formTable = null;
		String infoTable = null;
		String auxTables = null;
		String detailTables = null;
		String[] auxTableArray = null;
		String[] detailTableArray = null;
		QueryBuilder queryBuilder = null;

		ResultObject ro;
		List module_list = new ArrayList();

		String paramstring = request.getParameter("project_ids");
		String module_id = request.getParameter("module_ids");
		String page = request.getParameter("pages");
		String filter = request.getParameter("filter");
		
		String[] batchPara = null;
		if(paramstring.indexOf(",")>0){
			batchPara = paramstring.split(",");
		}else{
			batchPara = new String[1];
			batchPara[0] = paramstring;
		}
		String[] module_ids = null;
		if(module_id.indexOf(",")>0){
			module_ids = module_id.split(",");
		}else{
			module_ids = new String[1];
			module_ids[0] = module_id;
		}
		String[] pages = null;
		if(page.indexOf(",")>0){
			pages = page.split(",");
		}else{
			pages = new String[1];
			pages[0] = page;
		}

		for (int i = 0; i < batchPara.length; i++) {
			for (int j = 0; j < module_ids.length; j++) {
				Map module_map = new HashMap();
				project_id = new Long(StringFormatUtil.format(batchPara[i], "-1"));
				
				/**
				 * 读Ta06_module表,得到表名信息
				 */
				clazz = Ta06_module.class;
				Object o_module = queryService.searchById(clazz, new Long(module_ids[j]));

				/**
				 * 获取工程信息表
				 */
				infoTable = (String) PropertyInject.getProperty(o_module, "project_table");

				/**
				 * 获取表单主表
				 */
				formTable = (String) PropertyInject.getProperty(o_module, "form_table");

				/**
				 * 获取表单辅表，通过project_id关联
				 */
				auxTables = StringFormatUtil.format((String) PropertyInject.getProperty(o_module, "aux_table"));
				if (!auxTables.equals("")) {
					auxTableArray = auxTables.split(",");
				}

				/**
				 * 获取明细表名称，通过parent_id关联
				 */
				detailTables = StringFormatUtil.format((String) PropertyInject.getProperty(o_module, "detail_table"));
				if (!detailTables.equals("")) {
					detailTableArray = detailTables.split(",");
				}

				/**
				 * 获得工程信息数据
				 */
				if (!StringFormatUtil.format(infoTable, "").equals("")) {
					clazz = Class.forName(infoTable);
					queryBuilder = new HibernateQueryBuilder(clazz);
					queryBuilder.eq("id", project_id);
					ro = queryService.search(queryBuilder);
					if (ro.next()) {
						Object o_info = ro.get(clazz.getName());
						replaceSpecChar(o_info,clazz);
						module_map.put(clazz.getSimpleName().toLowerCase(), o_info);
					}
				}

				/**
				 * 获得表单主表数据
				 */
				clazz = Class.forName(formTable);
				queryBuilder = new HibernateQueryBuilder(clazz);
				queryBuilder.eq("project_id", project_id);
				ro = queryService.search(queryBuilder);
				if (ro.next()) {
					Object o_form = ro.get(clazz.getName());
					replaceSpecChar(o_form,clazz);
					module_map.put(clazz.getSimpleName().toLowerCase(), o_form);
					doc_id = new Long(PropertyInject.getProperty(o_form, "id").toString());

					/**
					 * 获得表单明细表数据
					 */
					if (detailTableArray != null) {
						for (int k = 0; k < detailTableArray.length; k++) {
							clazz = Class.forName(detailTableArray[k]);
							queryBuilder = new HibernateQueryBuilder(clazz);
							queryBuilder.eq("parent_id", doc_id);
							List<?> detailList = queryService.searchList(queryBuilder);
							for(Object obj:detailList){
								replaceSpecChar(obj,clazz);
							}
							module_map.put(clazz.getSimpleName().toLowerCase(), detailList);
						}
					}

					/**
					 * 获得表单辅表数据
					 */
					if (auxTableArray != null) {
						for (int k = 0; k < auxTableArray.length; k++) {
							clazz = Class.forName(auxTableArray[k]);
							queryBuilder = new HibernateQueryBuilder(clazz);
							queryBuilder.eq("project_id", project_id);
							ro = queryService.search(queryBuilder);
							if (ro.next()) {
								Object o_aux = ro.get(clazz.getName());
								replaceSpecChar(o_aux,clazz);
								module_map.put(clazz.getSimpleName().toLowerCase(), o_aux);
							}
						}
					}
					module_map.put("module_id", module_ids[j]);
					module_map.put("pages", pages[j]);
					module_list.add(module_map);
				} else {
					if (!"ok".equals(filter)) {
						module_map.put("module_id", module_ids[j]);
						module_map.put("pages", pages[j]);
						module_list.add(module_map);
					}
				}
			}
		}
		request.setAttribute("module_map", module_list);
		return new ModelAndView("/WEB-INF/jsp/print_web.jsp");
	}
	
	/**
	 * @param args
	 * @return args
	 * @throws Exception
	 * @desc 将对象中的特殊字符串Ta33_tszf.thqzf替换成中Ta33_tszf.thhzf；
	 */
	private void replaceSpecChar(Object arg,Class clazz) throws Exception  {
		
		Field[] fields = clazz.getDeclaredFields();
		for(int i = 0;i < fields.length;i++){
			String field_name = fields[i].getName();
			if(!field_name.equals("id") && !field_name.equals("serialVersionUID")){
				Object t_o = PropertyInject.getProperty(arg, field_name);
				
				if(t_o != null && t_o instanceof String){
					QueryBuilder queryBuilder = new HibernateQueryBuilder(Ta33_tszf.class);
					queryBuilder.addOrderBy(Order.asc("id"));
					List list = queryService.searchList(queryBuilder);
					if(list != null && list.size() > 0){
						for(Object obj:list){
							Ta33_tszf ta33 = (Ta33_tszf)obj;
							t_o = t_o.toString().replaceAll(convertUtil.toString(ta33.getThqzf(),""),convertUtil.toString(ta33.getThhzf(),""));
						}
					}
					PropertyInject.setProperty(arg, field_name, t_o);
				}
			}
		}
	}
}
