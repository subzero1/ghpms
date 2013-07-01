package com.ghpms.controller.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ghpms.service.CreateJspFile;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Te01_slave;
import com.netsky.base.flow.buttonControl.Button;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.LoadFormListService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.DateGetUtil;
import com.netsky.base.utils.StringFormatUtil;

@Controller
public class OpenFormControler {



	/**
	 * 项目进度表
	 */

	@Autowired
	private ExceptionService exceptionService;

	@Autowired
	private QueryService queryService;
	
	@Autowired
	private SaveService saveService;

	@Autowired
	private LoadFormListService loadFormListService;
	
	@Autowired
	CreateJspFile createJspFile;

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

	@RequestMapping("/gh/openForm.do")
	public ModelAndView openForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Long project_id = null;
		Long doc_id = null;
		Long module_id = null;
		Long node_id = null;
		Long user_id = null;
		Long flow_id = null;
		String loginClient = StringFormatUtil.format((String)request.getSession().getAttribute("loginClient"),"");
		Ta03_user user = (Ta03_user)(request.getSession().getAttribute("user"));
		
		Class<?> clazz = null;
		StringBuffer hsql =new StringBuffer();

		String infoTable = null;
		String formTable = null;
		String auxTables = null;
		String detailTables = null;
		String slaveModules = null;
		String[] auxTableArray = null;
		String[] detailTableArray = null;
		String[] slaveModuleArray = null;
		QueryBuilder queryBuilder = null;
		ResultObject ro;
		HashMap<String, String> hm_limit = null;
		StringBuffer validateList = null;
		Vector<HashMap<String, String>> v_slave = null;
		boolean canSave = false;

		try {
			/**
			 * 初始化参数容器
			 */
			Map <String,Object> paraMap = new HashMap<String,Object>();
			MapUtil.load(paraMap, request);
			
			/**
			 * 表单修改权限
			 */
			if(!paraMap.containsKey("node_id")&&!paraMap.containsKey("effacement")){
				Map rolesMap = (Map)paraMap.get("rolesMap");
				if(rolesMap!= null && rolesMap.containsKey("900203")){
					paraMap.put("effacement", "supressao");
				}
			}
			
			/**
			 * 初始化参数,既可以从数据request里获得,
			 * 也可以从参数容器获得
			 */
			flow_id = new Long(StringFormatUtil.format(request.getParameter("flow_id"), "-1"));
			project_id = new Long(StringFormatUtil.format(request.getParameter("project_id"), "-1"));
			doc_id = new Long(StringFormatUtil.format(request.getParameter("doc_id"), StringFormatUtil.format(request.getParameter("id"), "-1")));
			module_id = new Long(StringFormatUtil.format(request.getParameter("module_id"), "-1"));
			node_id = new Long(StringFormatUtil.format(request.getParameter("node_id"), "-1"));
			user_id = (Long)paraMap.get("user_id");
			
			if(user_id == null)
				user_id = new Long(-1);
			
			/**
			 * 读Ta06_module表,得到表名信息
			 */
			clazz = Class.forName("com.netsky.base.dataObjects.Ta06_module");
			Object o_module = queryService.searchById(clazz, module_id);
			request.setAttribute("module",o_module);
			
			/**
			 * 读Tb02_node表,得到节点信息
			 */
			clazz = Class.forName("com.netsky.base.dataObjects.Tb02_node");
			Object o_node = queryService.searchById(clazz, node_id);
			request.setAttribute("node",o_node);
			
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
			 * 获取表单类型附件module_id
			 */
			slaveModules = StringFormatUtil.format((String) PropertyInject.getProperty(o_module, "slave_module"));
			if (!slaveModules.equals("")) {
				slaveModuleArray = slaveModules.split(",");
			}
			/**
			 * 获得工程信息数据
			 */
			String cjrq = null;
			if (!StringFormatUtil.format(infoTable, "").equals("")) {
				clazz = Class.forName(infoTable);
				queryBuilder = new HibernateQueryBuilder(clazz);
				queryBuilder.eq("id", project_id);
				ro = queryService.search(queryBuilder);
				if (ro.next()) {
					Object o_info = ro.get(clazz.getName());
					request.setAttribute(clazz.getSimpleName().toLowerCase(), o_info);
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
				request.setAttribute(clazz.getSimpleName().toLowerCase(), o_form);
//				cjrq = ((Date)PropertyInject.getProperty(o_form, "cjrq")).toString();
			}

			/**
			 * 获得表单明细表数据
			 */
			if (detailTableArray != null) {
				for (int i = 0; i < detailTableArray.length; i++) {
					clazz = Class.forName(detailTableArray[i]);
					queryBuilder = new HibernateQueryBuilder(clazz);
					queryBuilder.eq("parent_id", doc_id);
					queryBuilder.addOrderBy(Order.asc("id"));
					List<?> detailList = queryService.searchList(queryBuilder);
					request.setAttribute(clazz.getSimpleName().toLowerCase(), detailList);
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
						Object o_aux_form = ro.get(clazz.getName());
						request.setAttribute(clazz.getSimpleName().toLowerCase(), o_aux_form);
					}
				}
			}

			/**
			 * 获得表单按钮
			 */
			List buttonList = new ArrayList();
			
			/**
			 * 只针对R-MS系统有效，start-------------
			 */
				/*
				 * 添加 “保存”按钮
				 */
				List new_btnList = new LinkedList();
				Button btn = new Button("保 存");
				btn.comment = "保存当前文档";
				btn.picUri = "save";
				btn.url = "javascript:docSave();";
				new_btnList.add(btn);
				
				buttonList = new_btnList;
				
				request.setAttribute("buttons", buttonList);
			/**
			 * 只针对R-MS系统有效，end-------------
			 */


			/**
			 * 获得表单附件信息
			 */
			v_slave = new Vector<HashMap<String, String>>();
			if (slaveModuleArray != null) {
				for (int i = 0; i < slaveModuleArray.length; i++) {
					
					hsql.delete(0, hsql.length());
					hsql.append("select distinct tb15.doc_id ,ta06.name ");
					hsql.append("from Ta06_module ta06,Tb15_docflow tb15 ");
					hsql.append("where ta06.id = tb15.module_id ");
					hsql.append("and tb15.project_id = ");
					hsql.append(project_id);
					hsql.append(" and tb15.module_id = ");
					hsql.append(new Long(slaveModuleArray[i]));
					ro = queryService.search(hsql.toString());
					while(ro.next()){
						HashMap<String, String> tmp_hm_slave = new HashMap<String, String>();
						String tmp_slave_name = (String)ro.get("ta06.name");
						Long t_doc_id = (Long)ro.get("tb15.doc_id");
						String tmp_formurl = "javascript:parent.popOperWeb('openForm.do?project_id=" + project_id + "&module_id=" + slaveModuleArray[i] + "&doc_id=" + t_doc_id+"')";
						
						if (!tmp_slave_name.equals("")) {
							tmp_hm_slave.put("slave_name", tmp_slave_name);
							tmp_hm_slave.put("formurl", tmp_formurl);
							tmp_hm_slave.put("rw", "r");
							v_slave.add(tmp_hm_slave);
							request.setAttribute("formslave", v_slave);
							request.setAttribute("length_formslave", v_slave.size());
						}
					}
				}
			}

			/**
			 * 获得上传附件信息
			 */
			v_slave = new Vector<HashMap<String, String>>();
			clazz = Class.forName("com.netsky.base.dataObjects.Te01_slave");
			queryBuilder = new HibernateQueryBuilder(clazz);
			queryBuilder.eq("doc_id", doc_id);
			queryBuilder.eq("module_id", module_id);
			queryBuilder.eq("project_id", project_id);
			queryBuilder.eq("slave_type", "普通附件");
			ro = queryService.search(queryBuilder);
			while (ro.next()) {
				HashMap<String, String> tmp_hm_slave = new HashMap<String, String>();
				
				Object t_slave_obj = ro.get(clazz.getName());
				Long tmp_slave_id = (Long)PropertyInject.getProperty(t_slave_obj,"id");
				String tmp_slave_name = StringFormatUtil.format((String) PropertyInject.getProperty(t_slave_obj,"file_name"), "");
				String tmp_slave_ext = StringFormatUtil.format((String) PropertyInject.getProperty(t_slave_obj,"ext_name"), "");
				String tmp_ftp_url = StringFormatUtil.format((String) PropertyInject.getProperty(t_slave_obj,"ftp_url"), "");
				String tmp_slave_remark = StringFormatUtil.format((String) PropertyInject.getProperty(t_slave_obj,"remark"), "");
				Long tmp_user_id =  (Long)PropertyInject.getProperty(t_slave_obj,"user_id");
				
				tmp_hm_slave.put("slave_id", tmp_slave_id.toString());
				tmp_hm_slave.put("slave_name", tmp_slave_name);
				tmp_hm_slave.put("ftp_url", tmp_ftp_url);
				tmp_hm_slave.put("slave_remark", tmp_slave_remark);
				if (canSave && user_id.equals(tmp_user_id)) {
					tmp_hm_slave.put("rw", "w");
				} else {
					tmp_hm_slave.put("rw", "r");
				}
				v_slave.add(tmp_hm_slave);
			}
			request.setAttribute("uploadslave", v_slave);
			request.setAttribute("length_uploadslave", v_slave.size());
			
			/**
			 * 获得交流反馈信息
			 */			
			List<Map> jlfkList = new LinkedList<Map>();
			hsql.delete(0, hsql.length());
			hsql.append("select te02.project_id,te02.id,ta03.name,ta03.id,te02.yj,te02.time ");
			hsql.append("from Te02_jlfk te02,Ta03_user ta03 ");
			hsql.append("where te02.user_id = ta03.id ");
			hsql.append("and project_id = ");
			hsql.append(project_id);
			hsql.append(" and module_id = "); 
			hsql.append(module_id);
			hsql.append(" and document_id =  ");
			hsql.append(doc_id);
			hsql.append(" order by te02.time ");
			ro = queryService.search(hsql.toString());
			while(ro.next()){
				HashMap<String,Object> jlfk = new HashMap<String,Object>();
				Long tmp_user_id =  (Long)ro.get("ta03.id");
				jlfk.put("name", ro.get("ta03.name"));
				jlfk.put("project_id", ro.get("te02.id"));
				jlfk.put("yj", ro.get("te02.yj"));
				jlfk.put("time", ro.get("te02.time"));
				if (user_id.equals(tmp_user_id)) {
					jlfk.put("rw", "w");
				}
				else{
					jlfk.put("rw", "r");
				}
				
				Long te02_project_id = new Long(ro.get("te02.project_id").toString());
				Long te02_id = new Long(ro.get("te02.id").toString());
				QueryBuilder queryBuilder99 = new HibernateQueryBuilder(Te01_slave.class);
				queryBuilder99.eq("doc_id", te02_id);
				queryBuilder99.eq("project_id", te02_project_id);
				queryBuilder99.eq("module_id", new Long(9003));
				ResultObject ro99 = queryService.search(queryBuilder99);
				if(ro99.next()){
					Te01_slave te01 = (Te01_slave)ro99.get(Te01_slave.class.getName());
					jlfk.put("slave_id", te01.getId());
				}
				jlfkList.add(jlfk);
			}
			if(jlfkList != null && jlfkList.size() > 0){
				request.setAttribute("jlfk", jlfkList);
			}
			request.setAttribute("length_jlfk", jlfkList.size());
			
			/**
			 * 获得当前时间信息
			 */
			request.setAttribute("now", DateGetUtil.getCurTime());
			if(cjrq == null || cjrq.equals(""))
				request.setAttribute("year", DateGetUtil.getYear());
			else
				request.setAttribute("year", cjrq.substring(0,4));
			
		
			/**
			 * 获得表单列表信息
			 */
			paraMap.clear();
			paraMap.put("module_id",module_id.toString());
			paraMap.put("flow_id",flow_id.toString());
			paraMap.put("project_id",project_id.toString());
			paraMap.put("doc_id",doc_id.toString());
			paraMap.put("node_id",node_id.toString());
			paraMap.put("user_id",user_id.toString());
			paraMap.put("year","" + request.getAttribute("year"));
			paraMap.put("curArea",user.getArea_name());
			if(canSave){
				paraMap.put("cansave","yes");
			}
			else{
				paraMap.put("cansave","no");
			}
			loadFormListService.load(request, paraMap);		
			/**
			 * 動態得到jspform表單
			 */
			createJspFile.AutoCreateJspFile(request, module_id);
		 	createJspFile.getNode(request, paraMap);
			
			
			
			
		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(), "装载表单数据", e);
		}
		
		//如果是从手机登录
		String viewName = "/WEB-INF/" + loginClient+ "jsp/autoform.jsp";
		
		return new ModelAndView(viewName);
	}


	public ExceptionService getExceptionService() {
		return exceptionService;
	}

	public void setExceptionService(ExceptionService exceptionService) {
		this.exceptionService = exceptionService;
	}

	public LoadFormListService getLoadFormListService() {
		return loadFormListService;
	}

	public void setLoadFormListService(LoadFormListService loadFormListService) {
		this.loadFormListService = loadFormListService;
	}
	
}
