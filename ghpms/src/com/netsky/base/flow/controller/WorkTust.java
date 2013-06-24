package com.netsky.base.flow.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta28_work_trust;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.DateGetUtil;
import com.netsky.base.utils.PHSService;
import com.netsky.base.utils.StringFormatUtil;

/**
 * @description:
 * 工作委托
 * @class name:com.netsky.base.flow.controller.WorkTransfer
 * @author Administrator Apr 18, 2010
 */
@Controller
public class WorkTust {

	/**
	 * 用户对象
	 */
	Ta03_user user;

	/**
	 * 数据库操作
	 */
	@Autowired
	private SaveService saveService;

	@Autowired
	private QueryService queryService;

	/**
	 * 异常处理服务
	 */
	@Autowired
	private ExceptionService exceptionService;

	/**
	 * 选择交接人员
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/flow/workTrust_selectUser.do")
	public ModelAndView selectUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("GBK");
		HttpSession session = request.getSession();
		
		ModelMap modelMap = new ModelMap();

		String area_id = convertUtil.toString(request.getParameter("area_id"));
		String keyWord = convertUtil.toString(request.getParameter("keyWord"));
		List tmpList = null;
		
		//查询关联用户
		String hsql = "select ta03.id as id,ta03.login_id as loign_id,ta03.name as name,tc01.name as area from Ta03_user ta03 ,Ta01_dept ta01,Tc01_area tc01 where  ta03.dept_id=ta01.id and ta01.area_id = tc01.id ";

		if (!"".equals(area_id)) {
			hsql += " and tc01.id = " + area_id;
		}
		if (!"".equals(keyWord)) {
			hsql += " and (ta03.name like '%" + keyWord + "%' or ta03.login_id like '%" + keyWord + "%' ) ";
		}

		hsql += " order by ta03.login_id ";
		
		if ("".equals(area_id) && "".equals(keyWord)) {
			;
		} else {
			tmpList = queryService.searchList(hsql.toString());
			modelMap.put("user_list", tmpList);
		}
		
		//初始化地区.
		if ("".equals(area_id)) {
			tmpList = queryService.searchList("select ta01.area_id from Ta03_user ta03,Ta01_dept ta01 where ta03.dept_id = ta01.id and ta03.id = ?",new Object[]{((Ta03_user)session.getAttribute("user")).getId()});
			 if(tmpList.size() >0 ){
				 area_id = String.valueOf(tmpList.get(0));
			 }
		}
		
		modelMap.put("area_id", area_id);
		
		//地区下拉框
		tmpList = queryService.searchList("select tc01.name as name,tc01.id as id from Tc01_area tc01 ");
		modelMap.put("area_list", tmpList);
		
		tmpList = queryService.searchList(" select 'x' from Ta28_work_trust where from_userid = ? and end_time is  null",new Object[]{((Ta03_user)session.getAttribute("user")).getId()});
		if(tmpList.size()>0){
			String warnMessage = "本人工作已委托出去，收回前不可以再次操作！";
			modelMap.put("warnMessage", warnMessage);		
		}
		
		modelMap.put("now", new Date());
		return new ModelAndView("/WEB-INF/jsp/flow/workTrust.jsp", modelMap);
	}

	/**
	 * 工作委托
	 * @param request
	 * @param response
	 * @param session void
	 */
	@RequestMapping("/flow/workTrust.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		/**
		 * 工程交接，岗位删除
		 */
		try {
			request.setCharacterEncoding("GBK");
			Ta28_work_trust ta28 = new Ta28_work_trust();
			ta28.setFrom_userid(convertUtil.toLong(request.getParameter("from_userId")));
			ta28.setFrom_username(convertUtil.toString(request.getParameter("from_userName")));
			ta28.setTo_userid(convertUtil.toLong(request.getParameter("to_userId")));
			ta28.setTo_username(convertUtil.toString(request.getParameter("to_userName")));
			ta28.setRemark(convertUtil.toString(request.getParameter("remark")));
			ta28.setStart_time(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").parse(request.getParameter("start_time")));
			
			if("yes".equals(request.getParameter("infoTo"))){
				Ta03_user to_user = (Ta03_user)queryService.searchById(Ta03_user.class, convertUtil.toLong(request.getParameter("to_userId")));
				PHSService phs = new PHSService();
				phs.setSaveService(saveService);
				StringBuffer message_phone = new StringBuffer();
				message_phone.append("发自：[GHPMS系统]"); 
				message_phone.append("\n");
				message_phone.append("内容：");
				message_phone.append(convertUtil.toString(request.getParameter("to_userName")));
				message_phone.append(request.getParameter("start_time"));
				message_phone.append("将[R-MS系统]中工作委托与您！");
				message_phone.append("委托原因：");
				message_phone.append(convertUtil.toString(request.getParameter("remark")));
				message_phone.append(";请确认!");
				phs.setRecvNum(to_user.getMobile_tel());					
				phs.setMsg(message_phone.toString());
				String state=phs.sendSMS();
				phs.dxjl("管理员",to_user.getName(),"工作交接",message_phone.toString(),state);//保存短信记录
			}
			
			saveService.save(ta28);
			String warnMessage = "工作委托成功！";
			modelMap.put("warnMessage", warnMessage);
			return new ModelAndView("/WEB-INF/jsp/flow/workTrust.jsp", modelMap);
		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(), "工作委托出错", e);
		}
	}
	
	/**
	 * 工作委托
	 * @param request
	 * @param response
	 * @param session void
	 */
	@RequestMapping("/flow/workTrustCancel.do")
	public void workTrustCancel(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		/**
		 * 工程交接，岗位删除
		 */
		try {
			request.setCharacterEncoding("GBK");
			StringBuffer hsql = new StringBuffer();
			Ta28_work_trust ta28 = (Ta28_work_trust)queryService.searchById(Ta28_work_trust.class, convertUtil.toLong(request.getParameter("ta28_id")));
			
			if("yes".equals(request.getParameter("infoTo"))){
				Ta03_user to_user = (Ta03_user)queryService.searchById(Ta03_user.class, convertUtil.toLong(request.getParameter("to_userId")));
				PHSService phs = new PHSService();
				phs.setSaveService(saveService);
				StringBuffer message_phone = new StringBuffer();
				message_phone.append("发自：[RMS系统]"); 
				message_phone.append("\n"); 		
				message_phone.append("内容：");		
				message_phone.append(convertUtil.toString(request.getParameter("to_userName")));
				message_phone.append(request.getParameter("start_time"));
				message_phone.append("将[RMS系统]中工作委托与您！");
				message_phone.append("委托原因：");
				message_phone.append(convertUtil.toString(request.getParameter("remark")));
				message_phone.append(";请确认!");
				phs.setRecvNum(to_user.getMobile_tel());					
				phs.setMsg(message_phone.toString());
				String state=phs.sendSMS();
				phs.dxjl("管理员",to_user.getName(),"工作交接",message_phone.toString(),state);//保存短信记录
			}
			
			ta28.setEnd_time(new Date());
			saveService.save(ta28);
			
			response.getWriter().print("ok");
			response.getWriter().flush();
		} catch (Exception e) {
			 exceptionService.exceptionControl(this.getClass().getName(), "工作委托出错", e);
		}
	}
	
	/**
	 * 工作委托查询
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/flow/workTrustList.do")
	public ModelAndView workTrustList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		/**
		 * 工程交接，岗位删除
		 */
		try {
			request.setCharacterEncoding("GBK");
			StringBuffer hsql = new StringBuffer();
			
			String sortField = StringFormatUtil.format(request.getParameter("sortField"),"id");
			String sortType = StringFormatUtil.format(request.getParameter("sortType"),"desc");
			
			String keyWord = convertUtil.toString(request.getParameter("keyWord"));
			String area_id = convertUtil.toString(request.getParameter("area_id"));
			String start_date = StringFormatUtil.format(request.getParameter("start_date"),DateGetUtil.getYear() + "-" + StringFormatUtil.getCompleteString(""+(DateGetUtil.getMonth()-1),2) + "-01");
			String end_date = StringFormatUtil.format(request.getParameter("end_date"),DateGetUtil.getCurDate());
			request.setAttribute("start_date",start_date );
			request.setAttribute("end_date",end_date );
			Integer page = convertUtil.toInteger(request.getParameter("page"));
			Integer pageRowSize = convertUtil.toInteger(request.getParameter("pageRowSize"));			
			if(pageRowSize <2){
				pageRowSize = 18;
			}
			request.setAttribute("page",page );
			request.setAttribute("pageRowSize",pageRowSize );
			
			
			Ta03_user user = (Ta03_user)session.getAttribute("user");
			
			hsql.append("select ta28 from Ta28_work_trust ta28,Ta03_user ta03,Ta01_dept ta01");
			hsql.append("	where ta28.from_userid = ta03.id");
			hsql.append("	and ta03.dept_id = ta01.id");
			if(!"".equals(keyWord)){
				hsql.append(" and (from_userName like '%" + keyWord + "%' or to_userName like '%" + keyWord + "%' )");
			}
			if(!"".equals(start_date)){
				hsql.append(" and start_time > to_date('" + start_date + "','yyyy-mm-dd')");
			}
			if(!"".equals(end_date)){
				hsql.append("and start_time < to_date('" + end_date + "','yyyy-mm-dd') + 1");
			}
			if(3 == user.getSearch_level()){
				hsql.append(" and (from_userid =" + user.getId() + " or to_userid  =" +  user.getId() +")" );
			}
			if(2 >= user.getSearch_level()){
				if ("".equals(area_id)) {
					Ta01_dept ta01 = (Ta01_dept) queryService.searchById(Ta01_dept.class, user.getDept_id());
					area_id = ta01.getId().toString();
				}
				hsql.append(" and ta01.area_id = " + area_id);
			}

			//设置排序
			StringBuffer order = new StringBuffer();
			order.append("order by ta28.");
			order.append(sortField+" ");
			order.append(sortType);
			
			//如果能查看全区项目增加地区下拉列表
			if (1 == user.getSearch_level()) {
				modelMap.put("area_list", queryService.searchList("select tc01.name as name,tc01.id as id from Tc01_area tc01 order by seq "));
			}
			
			modelMap.put("area_id", area_id);
			
			modelMap.put("ta28List", (List<Ta28_work_trust>)queryService.searchList(hsql.toString()+order.toString()));
			

			return new ModelAndView("/WEB-INF/jsp/flow/workTrustList.jsp", modelMap);
		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(), "工作委托出错", e);
		}
	}
}
