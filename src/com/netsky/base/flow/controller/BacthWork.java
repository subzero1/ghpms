package com.netsky.base.flow.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb17_approve;
import com.netsky.base.flow.FlowConstant;
import com.netsky.base.flow.buttonControl.Button;
import com.netsky.base.flow.component.Accept;
import com.netsky.base.flow.component.Archive;
import com.netsky.base.flow.component.Approve;
import com.netsky.base.flow.component.ApproveComplete;
import com.netsky.base.flow.component.FlowBussines;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;


/**
 * @description:
 * 相关批量处理
 * 受理、取消处理、审批、审结、办结、打印
 * @class name:com.ghpms.controller.BacthWork
 * @author wind Mar 3, 2010
 */
@Controller
public class BacthWork {

	/**
	 * Spring beanFactory
	 */
	ApplicationContext ctx ;
	
	Ta03_user user;
	
	@Autowired
	private SaveService saveService;

	@Autowired
	private QueryService queryService;
	
	@Autowired
	private ExceptionService exceptionService;
	
	/**
	 * 批量打印
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws sjbg,sjjd,sjfsqd  打印页数
	 * @throws project_id,gcmc 工程名称和id
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/BatchPrint.do")
	public ModelAndView BatchPrint(HttpServletRequest request, HttpServletResponse response) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		
		ModelMap modelMap = new ModelMap();
		try{
			/*
			 * 读取当前人走过的表单
			 */
			Ta03_user user = (Ta03_user)(request.getSession().getAttribute("user"));
			List moduleList = queryService.searchList(" select distinct tb15.module_id as module_id,ta06.name as module_name from Tb15_docflow tb15,Ta06_module ta06 where tb15.module_id = ta06.id and tb15.user_id = ? order by module_id ", new Object[] { user.getId() });
			modelMap.put("moduleList", moduleList);
			/*
			 * module_id_str module_id 串
			 * 拼成module_id 串
			 */
//			Long [] arrModule_id = new Long[moduleList.size()];
//			for( int i =0;i< moduleList.size();i++){
//				Object [] arr =(Object[])moduleList.get(i);
//				arrModule_id[i] = (Long)arr[0];
//			}
			String module_id_str = "";
			Iterator itr = moduleList.iterator();
			while(itr.hasNext()) {
				Object [] arr =(Object [])itr.next();
				module_id_str += arr[0].toString()+",";
			}
			module_id_str = module_id_str.substring(0,module_id_str.length()-1);
			/*
			 * 读取所有的表单
			 */
//			QueryBuilder queryBuilder = new HibernateQueryBuilder(clazz);
//			queryBuilder.eq("type",new Long(1));
//			List allmoduleList = queryService.searchList(queryBuilder);
			List allmoduleList = queryService.searchList(" select id,name from Ta06_module where type = 1 and id not in ("+module_id_str+") order by id ");
			modelMap.put("allmoduleList", allmoduleList);
						
			return new ModelAndView("/WEB-INF/jsp/printlist.jsp",modelMap);
		
		}catch (Exception e){
			System.out.println(e);
			return exceptionService.exceptionControl(this.getClass().getName(), "表单列表错误", e);
		}
	}
	
	/**
	 * 指量受理或取消受理
	 * <p/>只处理页面中包含按扭"受理"或"取消受理"的表单
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/bacthAccept.do")
	public ModelAndView bacthAccept(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		//初始化ApplicationContext
		initApplicationContext(session);
		
		ModelMap modelMap = new ModelMap();
		Map paraMap = new HashMap();
		String[] batchParas = request.getParameterValues("batchParas");
		int doTotals = 0;
		
		/**
		 * 批量受理的条件
		 * 1、ctx包括 accept
		 * 2、batchParas不为空，即必选择表单
		 * 3、表单有"受 理"按扭
		 */
		if(batchParas != null && ctx.containsBean("accept")){
			Accept accept = (Accept)ctx.getBean("accept");
			for(String batchPara : batchParas){
				paraMap.clear();
				MapUtil.load(paraMap,batchPara);
				paraMap.put("user_id", user.getId());
				paraMap.put("user_name", user.getName());
				String result ="";
				String buttonName = "受 理";
				
				if("cancel".equals(paraMap.containsKey("doType"))){
					buttonName = "取消受理";
				}
				
				if(containsButton(paraMap,buttonName)){
					try {
						result = accept.handleRequest(paraMap);
						doTotals ++;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		modelMap.put("warnMessage", "共成功处理了"+doTotals+"个文档!");
		return new ModelAndView("workList.do",modelMap);		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/bacthApprove.do")
	public ModelAndView bacthApprove(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		//初始化ApplicationContext
		initApplicationContext(session);
		
		ModelAndView modelAndView = null;
		ModelMap modelMap = new ModelMap();
		Map paraMap = new HashMap();
		String[] batchParas = request.getParameterValues("batchParas");
		int doTotals = 0;
		String check_idea = convertUtil.toString(request.getParameter("check_idea"));
		Integer check_result = convertUtil.toInteger(request.getParameter("check_result"));
		if(check_result ==-1) {
			Map<Integer,String>  checkType= new HashMap<Integer,String>();
			checkType.put(FlowConstant.CHECK_RESULT_OK, "同意");
			modelMap.put("checkType", checkType);
			checkType.put(FlowConstant.CHECK_RESULT_MODIFY, "修改后再报");
			modelMap.put("checkType", checkType);
			//当前日期，审批时间默认值
			modelMap.put("curDate", new Date());
			//当前日期，审批时间默认值
			modelMap.put("curDate", new Date());
			//用户自定义意见
			StringBuffer hsql = new StringBuffer();
			hsql.delete(0,hsql.length());
			hsql.append(" from Ta22_user_idea ta22 where  ta22.user_id = ? and check_result = 4 order by ta22.check_result,ta22.id ");
			List tmpList = queryService.searchList(hsql.toString(), new Object[] {user.getId()});
			modelMap.put("ideaList", tmpList);
			
			modelMap.put("batchParas", batchParas);
			return new ModelAndView("WEB-INF/jsp/flow/batchApprove.jsp",modelMap);
		}
		/**
		 * 批量审批的条件
		 * 1、ctx包括 accept
		 * 2、batchParas不为空，即必选择表单
		 * 3、表单有"受 理"按扭
		 */
		if(batchParas != null ){
			Approve approve = (Approve)ctx.getBean("approve");
			for(String batchPara : batchParas){
				paraMap.clear();
				MapUtil.load(paraMap,batchPara);
				paraMap.put("user_id", user.getId());
				paraMap.put("user_name", user.getName());
				
				String buttonName = "审 批";
				if(containsButton(paraMap,buttonName)){
					try {
						paraMap.put("check_idea", check_idea);
						paraMap.put("check_result", check_result);						
						modelAndView = approve.handleRequest(paraMap);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					if("success".equals(modelAndView.getModel().get("approve_return"))){
						doTotals ++;
					}					
				}
			}
		}
		
		modelMap.put("warnMessage", "共成功处理了"+doTotals+"个文档!");
		modelMap.put("closewin", "true");
		return new ModelAndView("WEB-INF/jsp/flow/batchApprove.jsp",modelMap);
	}
	/**
	 * 批量审结
	 * <p/>只处理页面中包含按扭"审结"的表单
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/bacthApproveComplete.do")
	public ModelAndView bacthApproveComplete(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		//初始化ApplicationContext
		initApplicationContext(session);
		
		ModelMap modelMap = new ModelMap();
		Map paraMap = new HashMap();
		String[] batchParas = request.getParameterValues("batchParas");
		int doTotals = 0;
		
		/**
		 * 批量办结的条件
		 * 1、ctx包括 Archive
		 * 2、batchParas不为空，即必选择表单
		 * 3、表单有"办 结"按扭
		 */
		if(batchParas != null && ctx.containsBean("approveComplete")){
			ApproveComplete approveComplete = (ApproveComplete)ctx.getBean("approveComplete");
			for(String batchPara : batchParas){
				paraMap.clear();
				MapUtil.load(paraMap,batchPara);
				paraMap.put("user_id", user.getId());
				paraMap.put("user_name", user.getName());
				String result ="";
				if(containsButton(paraMap,"审 结")){
					try {
						result = approveComplete.handleRequest(paraMap);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(FlowConstant.CTR_OPENFORM.equals(result)||FlowConstant.VIEW_CLOSE.equals(result)){
						doTotals ++;
					}
				}
			}
		}
		modelMap.put("warnMessage", "共审结了"+doTotals+"个文档!");
		return new ModelAndView("workList.do",modelMap);
	}
	
	/**
	 * 批量办结
	 * <p/>只处理页面中包含按扭"办结"的表单
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/bacthArchive.do")
	public ModelAndView bacthArchive(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		//初始化ApplicationContext
		initApplicationContext(session);
		
		ModelMap modelMap = new ModelMap();
		Map paraMap = new HashMap();
		String[] batchParas = request.getParameterValues("batchParas");
		int doTotals = 0;
		
		/**
		 * 批量办结的条件
		 * 1、ctx包括 Archive
		 * 2、batchParas不为空，即必选择表单
		 * 3、表单有"办 结"按扭
		 */
		if(batchParas != null && ctx.containsBean("archive")){
			Archive archive = (Archive)ctx.getBean("archive");
			for(String batchPara : batchParas){
				paraMap.clear();
				MapUtil.load(paraMap,batchPara);
				paraMap.put("user_id", user.getId());
				paraMap.put("user_name", user.getName());
				String result ="";
				if(containsButton(paraMap,"办 结")){
					try {
						result = archive.handleRequest(paraMap);
					} catch (Exception e) {
						e.printStackTrace();
					}

					if(FlowConstant.VIEW_CLOSE.equals(result)){
						doTotals ++;
					}
				}
			}
		}
		modelMap.put("warnMessage", "共办结了"+doTotals+"个文档!");
		return new ModelAndView("workList.do",modelMap);
	}
	
	/**
	 * 审计副总，总经理意见批量设置
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/bacthIdear.do")
	public ModelAndView bacthIdear(HttpServletRequest request, HttpServletResponse response, HttpSession session){

		return null;
	}	
	/**
	 * 判断是否包含名称为buttonName的按扭
	 * @param paraMap
	 * @param buttonName
	 * @return boolean
	 */
	public boolean containsButton(Map paraMap,String buttonName){
		if(ctx.containsBean("flowBussines")){
			FlowBussines flowBussines = (FlowBussines)ctx.getBean("flowBussines");
			return flowBussines.containsButton(paraMap, buttonName);
		}
		return false;
	}
	
	
	/**
	 * 初始化ApplicationContext
	 * @param session void
	 */
	private void initApplicationContext(HttpSession session){
		user = (Ta03_user)session.getAttribute("user");
		if (ctx == null){
			ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
		}
	}
}
