package com.ghpms.controller.base;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta09_menu;
import com.netsky.base.dataObjects.Tz03_login_log;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

/**
 * @description:
 * 系统主页面相关
 * @class name:com.netsky.base.controller.Main
 * @author Administrator Jul 21, 2011
 */
@Controller
public class Main {
	/**
	 * 数据库操作服务，自动注入
	 */
	@Autowired
	private Dao dao;
	
	/**
	 * 查询服务接口
	 */
	@Autowired
	private QueryService queryService;
	@Autowired
	private SaveService saveService;
	/**
	 * 异常处理服务
	 */
	@Autowired
	private ExceptionService exceptionService;
	
	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 系统主界面
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/main.do")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ModelMap modelMap = new ModelMap();
		Map menuListMap = new HashMap();
		Ta03_user user=(Ta03_user)session.getAttribute("user");
		if(user!=null){
			//获取二级菜单列表
			StringBuffer sql =new StringBuffer();
			sql.append("select ta09 from Ta09_menu ta09");  
			sql.append(" where exists(");
			sql.append(" select 1 ");
			sql.append(" from Ta04_role ta04,Ta12_sta_role ta12,Ta11_sta_user ta11");
			sql.append(" where ta11.station_id = ta12.station_id");
			sql.append(" and ta12.role_id = ta04.id ");
			sql.append(" and ta09.id = ta04.menu_id ");
			sql.append(" and ta11.user_id = ?) order by seq");
//			System.out.println(sql);
			List<Ta09_menu> menuList= (List<Ta09_menu>)dao.search(sql.toString(),new Object[]{user.getId()}); 
			String menu_ids = "-1";
			List tmpList = null;
			Long menu_id = -1L;
			for(Ta09_menu menu:menuList){
				if(!menu.getUp_id().equals(menu_id )){
					if(tmpList !=null && tmpList.size() > 0){
						menuListMap.put(menu_id, tmpList);
					}
					
					tmpList = new LinkedList();
					menu_id = menu.getUp_id();
					menu_ids += "," + menu.getUp_id();
				}
				tmpList.add(menu);
			}
			
			menuListMap.put( menu_id , tmpList);
			
			//获取一级菜单列表
			sql.delete(0, sql.length());
			sql.append("from Ta09_menu ta09 ");
			sql.append(" where ta09.id in( ");
			sql.append(menu_ids);
			sql.append(") order by seq");
			modelMap.put("menuList",dao.search(sql.toString()));
			modelMap.put("menuListMap", menuListMap);
			
			Map csMap = new HashMap();
			
			//获取待办文档数
			sql.delete(0, sql.length());
			sql.append(" select 'x' from NeedWork where user_id = ?");
			tmpList = dao.search(sql.toString(), new Object[]{user.getId()});
			if(tmpList.size() > 0){
				csMap.put("dbWds", tmpList.size());
			} else {
				csMap.put("dbWds", 0);
			}
			tmpList.clear();
			
			//获取在办文档数
			sql.delete(0, sql.length());
			sql.append(" select 'x' from OnWork where user_id = ?");
			tmpList = dao.search(sql.toString(), new Object[]{user.getId()});
			if(tmpList.size() > 0){
				csMap.put("zbWds", tmpList.size());
			} else {
				csMap.put("zbWds", 0);
			}
			tmpList.clear();
			
			//获取待复文档数
			sql.delete(0, sql.length());
			sql.append(" select 'x' from WaitWork where user_id = ?");
			tmpList = dao.search(sql.toString(), new Object[]{user.getId()});
			if(tmpList.size() > 0){
				csMap.put("dfWds", tmpList.size());
			} else {
				csMap.put("dfWds", 0);
			}
			tmpList.clear();
			
			//获取回复文档数
			sql.delete(0, sql.length());	
			sql.append(" select 'x' from ReplyWork where user_id = ?");
			tmpList = dao.search(sql.toString(), new Object[]{user.getId()});
			if(tmpList.size() > 0){
				csMap.put("hfWds", tmpList.size());
			} else {
				csMap.put("hfWds", 0);
			}
			tmpList.clear();
			
			//获取办结文档数
			sql.delete(0, sql.length());	
			sql.append(" select 'x' from OffWork where user_id = ?");
			tmpList = dao.search(sql.toString(), new Object[]{user.getId()});
			if(tmpList.size() > 0){
				csMap.put("bjWds", tmpList.size());
			} else {
				csMap.put("bjWds", 0);
			}
			tmpList.clear();
			
			request.setAttribute("csMap", csMap);
			
			/*
			 * 提醒列表
			 */
			String remindContent = getRemindList(request,response,session);
			if(remindContent != null && remindContent.length() > 0){
				request.setAttribute("remindContent", remindContent);
			}
			
			return new ModelAndView("/WEB-INF/jsp/main.jsp",modelMap);
		}else{
			return new ModelAndView("/index.jsp");
		}
	}
	
	/**
	 * 系统桌面
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/desktop.do")
	public ModelAndView desktop(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception {
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		
		//超时提醒数量
		Integer remind_num = 0; 
		
		//人员信息
		Ta03_user user=(Ta03_user)session.getAttribute("user");
		if (user == null) {
			return exceptionService.exceptionControl(this.getClass().getName(), "用户未登录或登录超时", new Exception("用户未登录"));
		}
				
		// 判断是不是管理岗
		boolean isAdmin = true;
		List<?> sta_user_list = queryService.searchList("select user_id from Ta11_sta_user where station_id=1");
		if(sta_user_list.contains(user.getId())){
			isAdmin = false;
		}
		
		//公告
		ResultObject ro_wclgg = queryService.search(" from Te03_online where role_id=601 and up_id is null");
		request.setAttribute("wclgg", ro_wclgg.getLength());
		
		ResultObject ro = queryService.searchByPage("select te03.title as title ,te03.id as id,te03.aq_date as aq_date ,aq_date-trunc(sysdate) as dif  from Te03_online te03 where te03.role_id=601 and te03.up_id is null order by te03.aq_date desc ", 1, 5);
		List online_list = new ArrayList();
		while(ro.next()){
			Map<String,Object> list = ro.getMap();
			online_list.add(list);
		}
		request.setAttribute("online_list", online_list);
		
		//短消息
		StringBuffer sql = new StringBuffer("");
		sql.delete(0, sql.length());
		sql.append("from Te04_message te04,Te11_message_receiver te11 ");
		sql.append("where te04.id = te11.msg_id ");
		sql.append("and te04.send_flag <> 0 ");
		sql.append("and te11.read_flag = 0 ");
		sql.append("and te11.delete_flag is null ");
		sql.append("and te11.reader_id = ");
		sql.append(user.getId());
		
		ResultObject ro_wcldxx = queryService.search(sql.toString());
		if(ro_wcldxx.getLength() > 0){
			remind_num++;
		}
		request.setAttribute("wcldxx", ro_wcldxx.getLength());
		List<Map<String, Object>> message_list = new ArrayList<Map<String, Object>>();
		
		sql.delete(0, sql.length());
		sql.append("select te04.title as title,te04.id as id,te11.read_flag as read_flag,te04.send_date as send_date,te04.send_date-trunc(sysdate) as dif,te04.repeat_flag as repeat_flag ");
		sql.append("from Te04_message te04,Te11_message_receiver te11 ");
		sql.append("where te04.id = te11.msg_id ");
		sql.append("and te04.send_flag <> 0 ");
		sql.append("and te11.read_flag = 0 ");
		sql.append("and te11.delete_flag is null ");
		sql.append("and te11.reader_id = ");
		sql.append(user.getId());
		sql.append(" order by te11.read_flag,te04.id desc");
		ResultObject ro_dxx = queryService.searchByPage(sql.toString(),1,5);
		while(ro_dxx.next()){
			Map<String,Object> list = ro_dxx.getMap();
			message_list.add(list);
		}
		request.setAttribute("message_list",message_list);
		
		
		//用户头像
		List list_fj = new ArrayList();
		String sql_salve="select id,file_name,ext_name,ftp_url from Te01_slave where doc_id="+user.getId()+" and module_id=0 and user_id="+user.getId()+" order by ftp_date desc";
		ResultObject ro_salve = queryService.search(sql_salve);
		if(ro_salve.next()){
			Map<String,Object> mo_salve = ro_salve.getMap();
			list_fj.add(mo_salve);
			request.setAttribute("fj", mo_salve);
		}
				
		//在线人数
		ServletContext application = request.getSession().getServletContext();
		//request.setAttribute("zxrs", application.getAttribute("totalSessions"));		
		Map<?,List<?>> onlineUserList = (Map<?,List<?>>) application.getAttribute("onlineUserList");
		request.setAttribute("zxrs", onlineUserList.size());		
		
		/**
		 * 超时提醒数据获取
		 */
		
		Map csMap = new HashMap();
		StringBuffer hsql = new StringBuffer();
		List tmpList = new LinkedList();
		//获取用户登录信息
		
		hsql.append(" from Tz03_login_log tz03 where login_id = ? order by id desc");
		tmpList = dao.search(hsql.toString(), new Object[]{user.getLogin_id()});
		if(tmpList.size() > 0){
			csMap.put("dlcs", tmpList.size());
			Tz03_login_log tz03 = (Tz03_login_log)tmpList.get(0);
			csMap.put("zhdl", tz03.getLogin_date());
		} else {
			csMap.put("dlcs", 0);
			csMap.put("zhdl", new Date());
		}
		tmpList.clear();
		hsql.delete(0, hsql.length());
		
		//获取待办文档数
		hsql.append(" select 'x' from NeedWork where user_id = ?");
		tmpList = dao.search(hsql.toString(), new Object[]{user.getId()});
		if(tmpList.size() > 0){
			csMap.put("dbWds", tmpList.size());
			remind_num++;
		} else {
			csMap.put("dbWds", 0);
		}
		tmpList.clear();
		hsql.delete(0, hsql.length());
		
		//获取在办文档数
		hsql.append(" select 'x' from OnWork where user_id = ?");
		tmpList = dao.search(hsql.toString(), new Object[]{user.getId()});
		if(tmpList.size() > 0){
			csMap.put("zbWds", tmpList.size());
		} else {
			csMap.put("zbWds", 0);
		}
		tmpList.clear();
		hsql.delete(0, hsql.length());
		
		//获取待复文档数
		hsql.append(" select 'x' from WaitWork where user_id = ?");
		tmpList = dao.search(hsql.toString(), new Object[]{user.getId()});
		if(tmpList.size() > 0){
			csMap.put("dfWds", tmpList.size());
		} else {
			csMap.put("dfWds", 0);
		}
		tmpList.clear();
		hsql.delete(0, hsql.length());
		
		//获取回复文档数
		hsql.append(" select 'x' from ReplyWork where user_id = ?");
		tmpList = dao.search(hsql.toString(), new Object[]{user.getId()});
		if(tmpList.size() > 0){
			csMap.put("hfWds", tmpList.size());
			remind_num++;
		} else {
			csMap.put("hfWds", 0);
		}
		tmpList.clear();
		hsql.delete(0, hsql.length());		
		
		//获取在线提问，权限申请 未答复数
		hsql.append(" select 'x' from Te03_online te03 where up_id is null and role_id = 15 ");
		hsql.append(" and  status = '未处理' ");
		if(isAdmin){
			hsql.append(" and login_id = '"+user.getLogin_id()+"'");
		}
		tmpList = queryService.searchList(hsql.toString());
		//tmpList = dao.search(hsql.toString(), new Object[]{user.getLogin_id()});
		if(tmpList.size() > 0){
			csMap.put("zxWdfs", tmpList.size());
		} else {
			csMap.put("zxWdfs", 0);
		}
		tmpList.clear();
		hsql.delete(0, hsql.length());		

		//获取在线提问，权限申请 未答复数
		hsql.append(" select 'x' from Te03_online te03 where up_id is null and role_id = 15 ");
		if(isAdmin){
			hsql.append(" and login_id = '"+user.getLogin_id()+"'");
		}
		tmpList = queryService.searchList(hsql.toString());
		//tmpList = dao.search(hsql.toString(), new Object[]{user.getLogin_id()});
		if(tmpList.size() > 0){
			csMap.put("zxTws", tmpList.size());
		} else {
			csMap.put("zxTws", 0);
		}
		tmpList.clear();
		hsql.delete(0, hsql.length());	
		
		//获取在线提问，权限申请 未答复数
		hsql.append(" select 'x' from Te03_online te03 where up_id is null and role_id = 17 and login_id = ?");
		tmpList = dao.search(hsql.toString(), new Object[]{user.getLogin_id()});
		if(tmpList.size() > 0){
			csMap.put("zxSqs", tmpList.size());
		} else {
			csMap.put("zxSqs", 0);
		}
		tmpList.clear();
		hsql.delete(0, hsql.length());	
			
		request.setAttribute("csMap", csMap);
		
		tmpList.clear();
		hsql.delete(0, hsql.length());
		
		request.setAttribute("now", new Date());
		request.setAttribute("nowStr",new java.text.SimpleDateFormat("MM/dd").format( new Date()));
		return new ModelAndView("/WEB-INF/jsp/desktop.jsp");
	}
	
	/*
	 * 获得需要提醒列表
	 */
	private String getRemindList(HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception{
		
		Ta03_user user = (Ta03_user)session.getAttribute("user");
		if(user == null){
			user = new Ta03_user();
		}
		String remindContent = "";
		String login_id = convertUtil.toString(user.getLogin_id());
		String dept_name = convertUtil.toString(user.getDept_name());
		String user_name = convertUtil.toString(user.getName());
		String zys = convertUtil.toString(user.getZys());
		StringBuffer hsql = new StringBuffer("");

		/*
		 * 工程列表
		 */
//		List t_list = dao.search("from Td00_gcxx td00 where not exists(select 'x' from Ti03_xqly ti03 where td00.id = ti03.project_id) and  sjysl is null and sjdw = '"+dept_name+"'");
//		if(t_list != null && t_list.size() > 0){
//			remindContent += "<li><a href=\"javascript:navTab.openTab(\\'gcxxList\\',\\'form/gcxxListForNeed.do\\',{title:\\'工程信息\\'})\">您收到（"+t_list.size()+"）个新工程</a></li>";
//		}
		
		return remindContent;
	}
}