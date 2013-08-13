package com.ghpms.controller.base;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ghpms.service.GcsjDataService;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta04_role;
import com.netsky.base.dataObjects.Ta07_formfield;
import com.netsky.base.dataObjects.Te01_slave;
import com.netsky.base.flow.buttonControl.Button;
import com.netsky.base.flow.service.FlowService;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.StringFormatUtil;

/**
 * @description: 主页面内的workList列表
 * @class name:com.netsky.base.controller.workList
 * @author wind Jan 28, 2010
 */
@Controller
// 注释1
public class workList {
	
	@Autowired
	private ExceptionService exceptionService;

	@Autowired
	private QueryService queryService;
	
	@Autowired
	private FlowService flowServiceImpl;
	
	@Autowired
	GcsjDataService gcsjDataService;

	/**
	 * 文档列表列示
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/workList.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			ModelMap modelMap = new ModelMap();
			Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"),1);
			Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"),20);
			String keyWord = convertUtil.toString(request.getParameter("keyWord"));
			Integer module_id = convertUtil.toInteger(request.getParameter("module_id"));
			Integer year = convertUtil.toInteger(request.getParameter("year"));
			Integer workState = convertUtil.toInteger(request.getParameter("workState"));
			String orderField = StringFormatUtil.format(request.getParameter("orderField"),"doc.oper_time");
			String orderDirection = StringFormatUtil.format(request.getParameter("orderDirection"),"desc");
			StringBuffer hsql = new StringBuffer();
			List<Ta07_formfield> docColsList = (List<Ta07_formfield>)session.getAttribute("docColsList");
			
			//获取用户客户端
			String loginClient = StringFormatUtil.format((String)request.getSession().getAttribute("loginClient"),"");
			
			year = year < 1900 ? Calendar.getInstance().get(Calendar.YEAR) : year;
						
			/*
			 * 取年度列表
			 */
			List yearList = new LinkedList();
			for(int y = 2012;y < Calendar.getInstance().get(Calendar.YEAR)+1;y++){
				yearList.add(new Integer(y));
			}
			//request.setAttribute("yearList", yearList);
			modelMap.put("yearList", yearList);
			modelMap.put("showYear", year);
			
			Integer docTabWitdh = convertUtil.toInteger(session.getAttribute("docTabWitdh"));
						
			int totalPages = 1;
			int totalCount = 1;
			
			modelMap.put("pageNum", pageNum);
			modelMap.put("curYear", Calendar.getInstance().get(Calendar.YEAR));
			modelMap.put("numPerPage", numPerPage);
			modelMap.put("year", year);
			modelMap.put("keyWord", keyWord);
			
			/**
			 * 判断是否有委托工作，如果有取出所有委托人ID
			 */
			String user_ids = "";
			if(session.getAttribute("trustUserMap")!= null){
				Map<String,?> trustUserMap =(Map<String,?>)session.getAttribute("trustUserMap");
				for(String tmpId:trustUserMap.keySet()){
					user_ids += "," + tmpId ;
				}
				user_ids = user_ids.substring(1);
			}
			
			Ta03_user user = (Ta03_user) (request.getSession().getAttribute("user"));
			// 构造hsql
			String docView = "NeedWork";
			String form_title = "文档";
			switch (workState) {
			case 2:
				docView = " OnWork";
				form_title = "在办文档";
				break;
			case 3:
				docView = " WaitWork";
				form_title = "待复文档";
				break;
			case 4:
				docView = " ReplyWork";
				form_title = "回复文档";
				break;
			case 5:
				docView = " OffWork";
				form_title = "办结文档";
				break;
			case 6:
				docView = " RespiteWork";
				form_title = "不具条件";
				break;
			case 7:
				docView = " HaltWork";
				form_title = "终止工程";
				break;
			default:
				docView = " NeedWork";
				form_title = "待办文档";
			}

			modelMap.put("form_title", form_title);
			
			modelMap.put("totalRows", 0);
			modelMap.put("totalPages", 1);
			modelMap.put("page", 1);
			modelMap.put("cols",docColsList.size());
			
			/**
			 * 判断当前人工作是否委托出去
			 */
			List tmpList = queryService.searchList(" select 'x' from Ta28_work_trust where from_userid = ? and end_time is  null",new Object[]{((Ta03_user)session.getAttribute("user")).getId()});
			if(tmpList.size()>0){				
				return new ModelAndView("/WEB-INF/" + loginClient+ "jsp/workList.jsp",modelMap);
			}
			
			/**
			 * 取文档列表
			 */
			hsql.append(" from " + docView);
			hsql.append(" doc,ProjectInf gcxx where gcxx.id = doc.project_id ");
			if (module_id > -1) {
				hsql.append(" and doc.module_id = " + module_id);
			}
			if (!"".equals(keyWord)) {
				hsql.append(" and ( gcxx.xmmc like '%" + keyWord + "%' or gcxx.xmbh like '%"+keyWord+"%' or gcxx.xmjl like '%"+keyWord+"%')");
			}
			if (year > 1900 && workState == 5) {
				hsql.append(" and doc.oper_time like '" + year + "%'");
			}
			
			if("".equals(user_ids)){
				hsql.append(" and doc.user_id = " + user.getId());
			} else {
				hsql.append(" and doc.user_id in(" + user_ids +")");
			}
			

			// 获得记录条数
			totalCount = convertUtil.toInteger(queryService.searchList(" select count(*) " + hsql.toString()).get(0));
			if (totalCount > 0) {
				totalPages = totalCount % numPerPage == 0 ? totalCount / numPerPage : totalCount / numPerPage + 1;
			} else {
				totalPages = 1;
			}
			if (pageNum < 1 || pageNum > totalPages) {
				pageNum = 1;
			}
			modelMap.put("totalCount", totalCount);
			modelMap.put("totalPages", totalPages);
			modelMap.put("pageNum", pageNum);
			
			//设置排序
			StringBuffer order = new StringBuffer();
			order.append(" order by ");
			order.append(orderField+" ");
			order.append(orderDirection);
			
			//导EXCEL取全部数据
			if("yes".equals(request.getParameter("toExcel"))){
				numPerPage =totalCount == 0?1:totalCount;
				pageNum = 1;
			}
			// 取列表数据
			ResultObject rs = queryService.searchByPage("select doc,gcxx " + hsql.toString()
					+ order.toString(), pageNum, numPerPage);
			List<List> docList = new LinkedList<List>();
			Object doc = null;
			Object gcxx = null;
			DecimalFormat df = new DecimalFormat("#0.00");
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			while (rs.next()) {
				List row = new LinkedList(); //行对像，先初始化各列数据
				doc = rs.get("doc");
				gcxx = rs.get("gcxx");
				//初始化各列数据
				for(Ta07_formfield ta07:docColsList){
					Object obj = null;
					//取数据
					if(ta07.getObject_name().equals("doc")){
						 obj = PropertyInject.getProperty(doc,ta07.getName().trim());
					} else {
						 obj = PropertyInject.getProperty(gcxx,ta07.getName().trim());
					}
					
					//格式化数据
					if("NUMBER".equals(ta07.getDatatype())&& obj != null){
						row.add(df.format(new BigDecimal(obj.toString())));
					}else if(obj instanceof Date){
						row.add(dateformat.format(obj));
					}else{
						row.add(obj);
					}						
				}
				
				//导EXCEL不需求后面对象
				if(!"yes".equals(request.getParameter("toExcel"))){
					row.add(doc);
					row.add(gcxx);
				}	
				
				docList.add(row);
			}
			modelMap.put("docList", docList);
			
			// 取得当前工程的类别列表
			List moduleList = queryService.searchList(" select distinct module_id,module_name from " + docView
					+ " where user_id = ? order by module_id ", new Object[] { user.getId() });
			modelMap.put("moduleList", moduleList);
			
			// 批量审批权限
			Map<String, Ta04_role> rolesMap = (Map<String, Ta04_role>) request.getSession().getAttribute("rolesMap");
			if (rolesMap != null) {
				modelMap.put("batchAccept", rolesMap.containsKey("900101"));
				modelMap.put("batchPrint", rolesMap.containsKey("900102"));
				modelMap.put("batchIdear", rolesMap.containsKey("900103"));
			} else {
				modelMap.put("loginErr", "session已失效，请重新登录");
				return new ModelAndView("index.jsp", modelMap);
			}			
			
			//导EXCEL
			if("yes".equals(request.getParameter("toExcel"))){
				Map<String,List> sheetMap = new HashMap<String,List>();
				List sheetList = new LinkedList();
				List titleList = new LinkedList();
				for(Ta07_formfield ta07:docColsList){
					titleList.add(ta07.getComments().trim());
				}
				sheetList.add(titleList);
				sheetList.add(docList);
				sheetMap.put(form_title, sheetList);
				request.setAttribute("ExcelName", form_title );
				request.setAttribute("sheetMap", sheetMap);
				return new ModelAndView("/export/toExcelWhithList.do");
			} else {

				//新建表单的下拉菜单{}newFormList
				Map <String,Object> paraMap = new HashMap<String,Object>();
				MapUtil.load(paraMap,request);
				List<Button> newFormList = flowServiceImpl.listNewFormButtons(paraMap);
				modelMap.put("newFormList", newFormList);
				return new ModelAndView("/WEB-INF/" + loginClient+ "jsp/workList.jsp", modelMap);
			}
		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(), "表单列表错误", e);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/docListUI.do")
	public ModelAndView docListUIhandleRequest(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"),15);
		Integer workState = convertUtil.toInteger(request.getParameter("workState"),1);
		String orderField = StringFormatUtil.format(request.getParameter("orderField"),"doc.oper_time");
		String orderDirection = StringFormatUtil.format(request.getParameter("orderDirection"),"desc");
		StringBuffer hsql = new StringBuffer();

		int totalPages = 1;
		int totalCount = 1;
		
		ModelMap modelMap = new ModelMap();
		
		try {
			Ta03_user user = (Ta03_user) (request.getSession().getAttribute("user"));
			
			/**
			 * 判断是否有委托工作，如果有取出所有委托人ID
			 */
			String user_ids = "";
			if(session.getAttribute("trustUserMap")!= null){
				Map<String,?> trustUserMap =(Map<String,?>)session.getAttribute("trustUserMap");
				for(String tmpId:trustUserMap.keySet()){
					user_ids += "," + tmpId ;
				}
				user_ids = user.getId() + user_ids;
			}
 
			Map <String,Object> paraMap = new HashMap<String,Object>();
			MapUtil.load(paraMap,request);
			List<Button> newFormList = flowServiceImpl.listNewFormButtons(paraMap);
			modelMap.put("newFormList", newFormList);
			List outDateList=gcsjDataService.getOutDateList(user);
			modelMap.put("outDateList", outDateList);
			if (outDateList!=null&&outDateList.size()>0) {
				modelMap.put("totalCount", outDateList.size());
			}
			return new ModelAndView("/WEB-INF/jsp/docListUI.jsp", modelMap);

		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(), "表单列表错误", e);
		}

	}
}
