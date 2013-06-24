package com.ghpms.controller.base;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Vector;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.LoadFormListService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.StringFormatUtil;
import com.netsky.base.utils.DateGetUtil;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Te01_slave;
import com.netsky.base.flow.service.FlowService;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.flow.buttonControl.Button;
import com.netsky.base.flow.buttonControl.ButtonControl;
import com.netsky.base.dataObjects.Ta03_user;

/**
 * 通用表单数据装载类，处理表单数据装载
 * 
 * @author lee.xiangyu 2010-1-6
 * 
 */
public class FlowFormController implements org.springframework.web.servlet.mvc.Controller {

	/**
	 * 表单模块配置表
	 */
	private String moduleTable;
	
	/**
	 * 节点表
	 */
	private String nodeTable;

	/**
	 * 工程表
	 */
	private String flowTable;
	
	/**
	 * 附件表
	 */
	private String slaveTable;

	/**
	 * 表单字段配置表
	 */
	private String fieldTable;

	/**
	 * 表单字段节点对应配置表
	 */
	private String fieldNodeTable;

	/**
	 * 表单字段节点对应配置表
	 */
	private String approveTable;
	
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
	private FlowService flowServiceImpl;

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

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Long project_id = null;
		Long doc_id = null;
		Long module_id = null;
		Long node_id = null;
		Long user_id = null;
		Long flow_id = null;
		String loginClient = StringFormatUtil.format((String)request.getSession().getAttribute("loginClient"),"");
		Ta03_user user = (Ta03_user)(request.getSession().getAttribute("user"));
		
		Class<?> clazz = null;
		StringBuffer hsql = null;

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
			clazz = Class.forName(moduleTable);
			Object o_module = queryService.searchById(clazz, module_id);
			request.setAttribute("module",o_module);
			
			/**
			 * 读Tb02_node表,得到节点信息
			 */
			clazz = Class.forName(nodeTable);
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
			 * 获得工程流程标识
			 */
			if(flow_id <= 0){
				clazz = Class.forName(flowTable);
				queryBuilder = new HibernateQueryBuilder(clazz);
				queryBuilder.eq("project_id", project_id);
				ro = queryService.search(queryBuilder);
				if (ro.next()) {
					Object o_flow = ro.get(clazz.getName());
					flow_id = (Long)PropertyInject.getProperty(o_flow, "flow_id");
				}
			}
			request.setAttribute("flow_id", flow_id);
			
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
				cjrq = ((Date)PropertyInject.getProperty(o_form, "cjrq")).toString();
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
			List buttonList = flowServiceImpl.listButtons(paraMap);
			request.setAttribute("buttons", buttonList);
			
			/**
			 * 只针对R-MS系统有效，start-------------
			 */
			if(module_id == 101 || module_id == 102 || module_id == 114){
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
			}
			/**
			 * 只针对R-MS系统有效，end-------------
			 */
			
			/**
			 * 判断是否有保存按钮(按钮列表第一个是否为'保存')
			 */
			if(buttonList.size() > 0){
				Object o_btn = buttonList.get(0);
				if(o_btn instanceof Button){
					Button btn = (Button)o_btn;
					if(btn.getUrl().indexOf("docSave") != -1){
						canSave = true;
						request.setAttribute("canSave", "yes");
					}
				}
			}
			
			/**
			 * 获得表单Actions
			 */
			buttonList = flowServiceImpl.listActions(paraMap);
			
			//不走流程的ACTION
			if((buttonList == null || buttonList.size() == 0) && doc_id != -1){
				
				buttonList = new LinkedList();
				hsql = new StringBuffer("");
				hsql.append("select tb03.id ");
				hsql.append("from Tb03_relation tb03 ");
				hsql.append("where tb03.source_id = ");
				hsql.append(node_id);
				hsql.append(" or tb03.dest_id =");
				hsql.append(node_id);
				List t_list = queryService.searchList(hsql.toString());
				if(t_list == null || t_list.size() == 0){
					Map<String,String> urlParaMap = new HashMap<String,String>();
					String urlParaStr = MapUtil.getUrl(paraMap, new String[] { "project_id", "node_id", "module_id", "doc_id",
							"user_id", "user_name", "opernode_id" });
					MapUtil.load(urlParaMap, urlParaStr);
					hsql = new StringBuffer(" select tb05.name,tb05.doclass,tb05.description,tb05.paras as tb05_paras ,tb14.parameters as tb14_paras,tb05.id ");
					hsql.append(" from Tb05_affair tb05,Tb14_even_affair tb14");
					hsql.append(" where tb05.id = tb14.affair_id");
					hsql.append(" and tb05.affair_type = 'show_action'");
					hsql.append(" and tb14.even_id =");
					hsql.append(node_id);
					hsql.append(" order by tb14.seq");
					t_list = queryService.searchList(hsql.toString());
					Button m_button = null;
					
					for (Object row : t_list) {
						if (row instanceof Object[]) {
							
							Object[] arr = (Object[]) row;
							// 把action相关参数串到window_pro
							Map<String, String> proMap = new HashMap<String, String>();
							MapUtil.load(proMap, (String) arr[3]);
							MapUtil.load(proMap, (String) arr[4]);
							MapUtil.load(proMap, urlParaStr);
							/**
							 * 判断action是否显示
							 */
							
							try {
								// 判断是否可以显示
								if (proMap.containsKey("showClass")) {
									Class c = Class.forName(MapUtil.getString(proMap, "showClass"));
									ButtonControl bc;
									bc = (ButtonControl) c.newInstance();
									bc.setQueryService(queryService);
									bc.setSaveService(saveService);
									if (!bc.isShow(proMap))
										continue;
								}
							} catch (ClassNotFoundException e) {
								//log.error("showClass配置类不存在，配置信息：" + proMap.toString());
							} catch (InstantiationException e) {
								//log.error("showClass配置类调用时错误，配置信息：" + proMap.toString());
							} catch (IllegalAccessException e) {
								//log.error("showClass配置类不能有效访问，配置信息：" + proMap.toString());
							}
							
							m_button = new Button((String) arr[0]);
							String tmp_str = "";
							if (proMap.containsKey("height")) {// 窗口高度
								tmp_str = "height=" + proMap.get("height");
							}
							if (proMap.containsKey("width")) {// 窗口宽度
								tmp_str += "width=" + proMap.get("width");
							}
							if (proMap.containsKey("scrollbars")) {// 窗口是否可以滚动
								tmp_str += "scrollbars=" + proMap.get("scrollbars");
							}
							
							m_button.comment = (String) arr[2];
							m_button.window_pro = tmp_str;
							
							//URL参数处理
							 tmp_str = arr[1] == null ?"":(String) arr[1];
							if(tmp_str.indexOf("javascript:") != -1&&tmp_str.indexOf('(') != -1&&tmp_str.indexOf(')') != -1){
								for (String key : urlParaMap.keySet()) {
									tmp_str = tmp_str.replace("${" + key + "}", urlParaMap.get(key));
								}
							}else if(!proMap.containsKey("notHasFlowPara")){
								tmp_str += "&" + urlParaStr;
							}
							m_button.url = tmp_str;
							buttonList.add(m_button);
						}
					}
				}
			}
			request.setAttribute("actions", buttonList);			

			
			/**
			 * 获得表单数据修改权限和验证信息
			 */
//			hm_limit = new HashMap<String, String>();
//			
//			clazz = Class.forName(fieldTable);
//			queryBuilder = new HibernateQueryBuilder(clazz);
//			queryBuilder.eq("module_id", module_id);
//			ro = queryService.search(queryBuilder);
//			while (ro.next()) {
//				
//				Object t_field_obj = ro.get(clazz.getName());
//				String t_column_name = StringFormatUtil.format((String)PropertyInject.getProperty(t_field_obj, "name"),"no value");
//				String t_obj_name = StringFormatUtil.format((String)PropertyInject.getProperty(t_field_obj, "object_name"),"no value");
//				String t_table_name = Class.forName(t_obj_name).getSimpleName();
//				
//				hm_limit.put(t_column_name.toLowerCase(), "disabled");
//				hm_limit.put((t_table_name + "$" + t_column_name).toLowerCase(),
//						"disabled");
//			}
			
			
			validateList = new StringBuffer("");
			String lastTable = null;
			/*
			 * 考虑节点ID
			 */
			if(node_id != null && node_id != -1){
				hsql = new StringBuffer("");
				hsql.append("select field.comments,field.datatype,field.datalength,field.nullable as nullable,field.name,field.object_name from ");
				hsql.append(fieldTable);
				hsql.append(" field ,");
				hsql.append(fieldNodeTable);
				hsql.append(" fnm where field.id = fnm.field_id and fnm.node_id = ");
				hsql.append(node_id);
				hsql.append(" order by field.object_name ");
			}
			else{
				hsql = new StringBuffer("");
				hsql.append("select field.comments,field.datatype,field.datalength,'Y' as nullable,field.name,field.object_name from ");
				hsql.append(fieldTable);
				hsql.append(" field ");
				hsql.append(" where field.module_id = ");
				hsql.append(module_id);
				hsql.append(" order by field.object_name ");
			}
			ro = queryService.search(hsql.toString());
			while (ro.next()) {
				
				String t_column_name = StringFormatUtil.format((String)ro.get("field.name"));
				String t_comment = StringFormatUtil.format((String)ro.get("field.comments"));
				String t_dayatype = StringFormatUtil.format((String)ro.get("field.datatype"));
				Double t_datalength = (Double)ro.get("field.datalength");
				String t_nullable = StringFormatUtil.format((String)ro.get("nullable"));
				String t_obj_name = StringFormatUtil.format((String)ro.get("field.object_name"));
				String t_table_name = StringFormatUtil.format(Class.forName(t_obj_name).getSimpleName());
				String t_datalength_str = StringFormatUtil.format(t_datalength.toString());

				if(lastTable == null || !lastTable.equals(t_table_name)){
					if(lastTable != null){
						validateList.append("}");
					}
					validateList.append(",'");
					validateList.append(t_table_name);
					validateList.append("'");
					validateList.append(":{");
					validateList.append("'");
					validateList.append(t_column_name.toUpperCase());
					validateList.append("'");
					validateList.append(":{'comments':'");
					validateList.append(t_comment);
					validateList.append("','datatype':'");
					validateList.append(t_dayatype);
					validateList.append("','datalength':'");
					validateList.append(t_datalength_str.replace(".0","").replace(".", ","));
					validateList.append("','nullable':'");
					validateList.append(t_nullable);
					validateList.append("'}");
				}
				else{
					validateList.append(",'");
					validateList.append(t_column_name.toUpperCase());
					validateList.append("'");
					validateList.append(":{'comments':'");
					validateList.append(t_comment);
					validateList.append("','datatype':'");
					validateList.append(t_dayatype);
					validateList.append("','datalength':'");
					validateList.append(t_datalength_str.replace(".0","").replace(".", ","));
					validateList.append("','nullable':'");
					validateList.append(t_nullable);
					validateList.append("'}");
				}
				
				lastTable = t_table_name;
			}
			if(validateList.length()> 0 && validateList.substring(0,1).equals(",")){
				validateList.replace(0, 1, "{");
				validateList.append("}}");
			}
			
//			request.setAttribute("limit", hm_limit);
			/**
			 * 表单中有保存按钮再显示VALIDATE信息
			 */
			if(canSave){
				request.setAttribute("validate", validateList.toString());
			}

			/**
			 * 获得表单审批信息
			 */
			clazz = Class.forName(approveTable);
			queryBuilder = new HibernateQueryBuilder(clazz);
			queryBuilder.eq("module_id", module_id);
			queryBuilder.eq("doc_id", doc_id);
			queryBuilder.eq("project_id", project_id);
			queryBuilder.addOrderBy(Order.asc("id"));
			List<?> approveList = queryService.searchList(queryBuilder);
			request.setAttribute("approve", approveList);
			request.setAttribute("length_approve", approveList.size());

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
			clazz = Class.forName(slaveTable);
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
			
		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(), "装载表单数据", e);
		}
		
		//如果是从手机登录
		String viewName = "/WEB-INF/" + loginClient+ "jsp/autoform.jsp";
		
		return new ModelAndView(viewName);
	}

	public void setModuleTable(String moduleTable) {
		this.moduleTable = moduleTable;
	}

	public void setFlowTable(String flowTable) {
		this.flowTable = flowTable;
	}
	
	public void setApproveTable(String approveTable) {
		this.approveTable = approveTable;
	}

	public void setFieldNodeTable(String fieldNodeTable) {
		this.fieldNodeTable = fieldNodeTable;
	}

	public void setFieldTable(String fieldTable) {
		this.fieldTable = fieldTable;
	}

	public void setSlaveTable(String slaveTable) {
		this.slaveTable = slaveTable;
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

	public void setNodeTable(String nodeTable) {
		this.nodeTable = nodeTable;
	}
	
}
