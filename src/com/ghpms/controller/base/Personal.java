package com.ghpms.controller.base;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta02_station;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta04_role;
import com.netsky.base.dataObjects.Ta22_user_idea;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.convertUtil;

/**
 * @description:
 * 用户个性化设置相关
 * @class name:com.netsky.base.controller.Main
 * @author Administrator Jul 21, 2011
 */
@Controller
public class Personal {
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
	 * 用户个人信息设置
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/userInfo.do")
	public ModelAndView userInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		user = (Ta03_user) queryService.searchById(Ta03_user.class, user
				.getId());
		setUserDept(user);
		request.getSession().setAttribute("user", user);
		ModelMap modelMap = new ModelMap();
		modelMap
				.put(
						"deptList",
						dao
								.search("from Ta01_dept dept order by dept.name"));
		// 获取地区列表
		modelMap.put("areaList", dao
				.search("from Tc02_area  order by name"));
		return new ModelAndView("/WEB-INF/jsp/personalization/userInfo.jsp", modelMap);
	}
	
	/**
	 * 用户审批意见模板保存
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/ajaxSaveCommentsTemplate.do")
	public void ajaxSaveCommentsTemplate(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();   
		
		Ta03_user user = null;
		user = (Ta03_user) request.getSession().getAttribute("user");
		
		String check_idea = request.getParameter("check_idea");
		String check_result = request.getParameter("check_result");
		
		try {
			Ta22_user_idea ta22 = new Ta22_user_idea();
			ta22.setCheck_idea(check_idea);
			ta22.setUser_id(user.getId());
			ta22.setCheck_result(new Long(check_result));
			QueryBuilder queryBuilder = new HibernateQueryBuilder(Ta22_user_idea.class);
			queryBuilder.eq("check_idea", ta22.getCheck_idea());
			queryBuilder.eq("check_result", ta22.getCheck_result());
			queryBuilder.eq("user_id", ta22.getUser_id());
			ResultObject ro = queryService.search(queryBuilder);
			if(!ro.next()){
				saveService.save(ta22);
			}
			response.setContentType("text/xml");
			out.print("<option value=\""+check_result+"\" title=\""+check_result+"\">"+check_idea+"</option>");
		
		} catch (Exception e) {
			out.print("");
		}
		out.flush();
		out.close();
	}
	/**
	 * 用户审批意见维护
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return void
	 */
	@RequestMapping("/commentsList.do")
	public String commentsList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		Long user_id = user.getId();
		List<Ta22_user_idea> ta22List = (List<Ta22_user_idea>)queryService.searchList("from Ta22_user_idea where user_id="+user_id);
		request.setAttribute("ta22List", ta22List);
		return "/WEB-INF/jsp/personalization/commentsManage.jsp";
	}
	/**
	 * 用户个人信息设置保存
	 * 保存后修改当前用户session信息
	 * @param request
	 * @param response
	 * @param session
	 * @return 
	 */
	@RequestMapping("/userInfoAjaxSave.do")
	public void userInfoAjaxSave(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		Long id=new Long(request.getParameter("Ta03_user.ID"));
		String login_id=convertUtil.toString(request.getParameter("Ta03_user.LOGIN_ID"));
		String passwd=convertUtil.toString(request.getParameter("Ta03_user.PASSWD"));
		String name=convertUtil.toString(request.getParameter("Ta03_user.NAME"));
		String mobile_tel=convertUtil.toString(request.getParameter("Ta03_user.MOBILE_TEL"));
		String sex=convertUtil.toString(request.getParameter("Ta03_user.SEX"));
		String area_name=convertUtil.toString(request.getParameter("Ta03_user.AREA_NAME"));
		String remark=convertUtil.toString(request.getParameter("Ta03_user.REMARK"));
		String email=convertUtil.toString(request.getParameter("Ta03_user.EMAIL"));
		String dept_id=convertUtil.toString(request.getParameter("Ta03_user.DEPT_ID"));
		String fix_tel=convertUtil.toString(request.getParameter("Ta03_user.FIX_TEL"));
		PrintWriter out = response.getWriter();
		//修改用户信息
		Ta03_user user = (Ta03_user)dao.getObject(Ta03_user.class, id);
		user.setArea_name(area_name);
		user.setDept_id(new Long(dept_id));
//		if (!passwd.equals(user.getPasswd())){
//			user.setLast_pwd_date(new Date());
//			user.setPasswd(passwd);
//		}
		user.setEmail(email);
		user.setFix_tel(fix_tel);
		user.setRemark(remark);
		user.setSex(sex);
		user.setName(name);
		user.setLogin_id(login_id);
		user.setMobile_tel(mobile_tel);
		try{
			dao.saveObject(user);
			setUserDept(user);
			session.setAttribute("user", user);	
			out.print("{\"statusCode\":\"200\",\"message\":\"信息更改成功！\", \"navTabId\":\"desktop\", \"forwardUrl\":\"\", \"callbackType\":\"closeCurrentDialog\"}");
		}catch(Exception e){
			out.print("{\"statusCode\":\"300\",\"message\":\"信息更改失败！\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		}
	 }
	
	
	/**
	 * 密码修改
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/pwdSetting.do")
	public ModelAndView pwdMSetting(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("/WEB-INF/jsp/personalization/pwdSetting.jsp");
	}
	
	/**
	 * 保存密码
	 * @param request
	 * @param response
	 * @param session void
	 * @throws IOException 
	 */
	@RequestMapping("/pwdSave.do")
	public void pwdSave(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		response.setCharacterEncoding(request.getCharacterEncoding());
		Long id=new Long(request.getParameter("Ta03_user.ID"));
		String passwd=convertUtil.toString(request.getParameter("Ta03_user.PASSWD"));
		String newpwd=convertUtil.toString(request.getParameter("newpwd").trim());
		Ta03_user user = (Ta03_user)dao.getObject(Ta03_user.class, id);
		PrintWriter out=response.getWriter();
	
		try{
			if(passwd.equals(user.getPasswd())){
				user.setPasswd(newpwd);
				user.setLast_pwd_date(new Date());
				dao.saveObject(user);
				setUserDept(user);
				session.setAttribute("user", user);	
				out.print("{\"statusCode\":\"200\",\"message\":\"密码修改成功！\", \"navTabId\":\"desktop\", \"forwardUrl\":\"\", \"callbackType\":\"closeCurrentDialog\"}");
			}else if(!passwd.equals(user.getPasswd())){
				out.print("{\"statusCode\":\"300\",\"message\":\"原密码输入有误！\", \"navTabId\":\"desktop\", \"forwardUrl\":\"\", \"callbackType\":\"closeCurrentDialog\"}");
			}
			
		}catch(Exception e){
			out.print("{\"statusCode\":\"300\",\"message\":\"密码修改失败！\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		}
		
	}
	
	/**
	 * 
	 * @param user void
	 */
	private void setUserDept(Ta03_user user){
		StringBuffer hsql=new StringBuffer();
		Ta03_user t_user =(Ta03_user) queryService.searchById(Ta03_user.class, user
				.getId());
		if (t_user!=null) {
			Long dept_id = convertUtil.toLong(user.getDept_id(),-1l);
			hsql.delete(0, hsql.length());
			hsql.append("from Ta01_dept where id = ");
			hsql.append(dept_id);
			List tmpList = queryService.searchList(hsql.toString());
			if (tmpList!=null&&tmpList.size() > 0) {
				Ta01_dept dept = (Ta01_dept) tmpList.get(0);
				user.setDept_name(dept.getName());
			}
		}
	}
	
}
