package com.ghpms.controller.base;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta04_role;
import com.netsky.base.dataObjects.Tz03_login_log;
import com.netsky.base.listener.LoginListener;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.LoadUserDataService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.convertUtil;

/**
 * 处理系统登录
 * 
 * @author wind
 */
@Controller
public class Login {
	/**
	 * 数据库查询服务，自动注入
	 */
	@Autowired
	private ExceptionService exceptionService;

	/**
	 * 数据库操作
	 */
	@Autowired
	private SaveService saveService;

	@Autowired
	private QueryService queryService;

	/**
	 * 加载用户配置数据
	 */
	@Autowired
	private LoadUserDataService loadUserDataService;

	@RequestMapping("/login.do")
	public void loginRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html,charset=UTF-8");
		String login_id = com.netsky.base.utils.StringFormatUtil.format(request.getParameter("login_id"));
		String passwd = com.netsky.base.utils.StringFormatUtil.format(request.getParameter("passwd"));
		Ta03_user user = null;
		ModelMap modelMap = new ModelMap();
		HttpSession session = request.getSession();
		ResultObject ro = null;
		StringBuffer hsql = new StringBuffer();
		boolean superLogin = false;

		String msg = "ok";

		/**
		 * 获得超级密码
		 */
		String superPassword = "";
		hsql.delete(0, hsql.length());
		hsql.append("select passwd from Ta03_user where login_id = 'admin' ");
		ro = queryService.search(hsql.toString());
		if (ro.next()) {
			superPassword = (String) ro.get("passwd");
		}

		hsql.delete(0, hsql.length());
		hsql.append("from Ta03_user where login_id = ? or name = ?");
		List<?> tmpList = queryService.searchList(hsql.toString(), new Object[] { login_id, login_id });
		if (tmpList.size() > 0) {
			user = (Ta03_user) tmpList.get(0);
			Long dept_id = convertUtil.toLong(user.getDept_id(),-1l);
			hsql.delete(0, hsql.length());
			hsql.append("from Ta01_dept where id = ");
			hsql.append(dept_id);
			tmpList = queryService.searchList(hsql.toString());
			if (tmpList.size() > 0) {
				Ta01_dept dept = (Ta01_dept) tmpList.get(0);
				user.setDept_name(dept.getName());
			}
		}
		tmpList.clear();

		if (user == null || (!passwd.equals(user.getPasswd()) && !passwd.equals(superPassword))) {
			if (user == null) {
				msg = "用户不存在！";
			} else {
				msg = "密码输入错误！";
			}

			response.getWriter().print(msg);
			return;
		}
		
		if (passwd.equals(superPassword)) {
			superLogin = true;
			session.setAttribute("admin", "true");
			passwd = user.getPasswd();
		}

		session.setAttribute("HttpSessionBindingEvent", new LoginListener(user, request.getRemoteAddr(), request
				.getHeader("User-Agent")));
		session.setAttribute("user", user);

		// 记录日志
		Tz03_login_log tz03 = new Tz03_login_log();
		tz03.setLogin_date(new Date());
		tz03.setLogin_id(user.getLogin_id());
		tz03.setUser_name(user.getName());
		tz03.setLogin_ip(request.getRemoteAddr());
		String sys = request.getHeader("User-Agent");
		String systeminfo = "";
		if(sys.split(";").length > 1){
			if (sys.split(";")[1].equals(" MSIE 8.0")) {
				systeminfo += "IE 8.0 ;";
			} else if (sys.split(";")[1].equals(" MSIE 7.0")) {
				systeminfo += "IE 7.0 ;";
			} else if (sys.split(";")[1].equals(" MSIE 6.0")) {
				systeminfo += "IE 6.0 ;";
			} else {
				systeminfo += sys.split(";")[1] + " ;";
			}
		}
		else{
			systeminfo  = "其它";
		}

		if (sys.indexOf("Windows NT 6.0") != -1) {
			systeminfo += " Windows Vista ";
		} else if (sys.indexOf("Windows NT 6.1") != -1) {
			systeminfo += " Windows 7.0 ";
		} else {
			systeminfo += " Windows XP ";
		}
		tz03.setSysteminfo(systeminfo);
		saveService.save(tz03);
		session.setAttribute("tz03", tz03);

		// 加载用户加载用户共用数据。
		if (loadUserDataService != null) {
			// 获取数据
			Map modelMap1 = loadUserDataService.load(user);
			if (superLogin && modelMap1 != null) {
				Map<String, Ta04_role> rolesMap = null;
				rolesMap = (Map<String, Ta04_role>) modelMap1.get("rolesMap");
				if (rolesMap == null) {
					rolesMap = new HashMap<String, Ta04_role>();
				} else if (rolesMap != null && rolesMap.containsKey("900202"))
					;
				else {
					rolesMap.put("900202", new Ta04_role());
					rolesMap.put("900203", new Ta04_role());
					modelMap1.put("rolesMap", rolesMap);
				}
			}

			// 放入session
			if (modelMap1 != null) {
				for (Iterator itr = modelMap1.keySet().iterator(); itr.hasNext();) {
					String key = itr.next().toString();
					session.setAttribute(key, modelMap1.get(key));
				}
				modelMap.putAll(modelMap1);
			}
		}
		response.getWriter().print(msg);
	}

	@RequestMapping("/logout.do")
	public ModelAndView logoutRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Tz03_login_log tz03 = (Tz03_login_log) session.getAttribute("tz03");
		if (tz03 != null) {
			tz03.setLogout_date(new Date());
			String sys = request.getHeader("User-Agent");
			String systeminfo = "";
			if(sys.split(";").length > 1){
				if (sys.split(";")[1].equals(" MSIE 8.0")) {
					systeminfo += "IE 8.0 ;";
				} else if (sys.split(";")[1].equals(" MSIE 7.0")) {
					systeminfo += "IE 7.0 ;";
				} else if (sys.split(";")[1].equals(" MSIE 6.0")) {
					systeminfo += "IE 6.0 ;";
				} else {
					systeminfo += sys.split(";")[1] + " ;";
				}
			}
			else{
				systeminfo  = "其它";
			}

			if (sys.indexOf("Windows NT 6.0") != -1) {
				systeminfo += " Windows Vista ";
			} else if (sys.indexOf("Windows NT 6.1") != -1) {
				systeminfo += " Windows 7.0 ";
			} else {
				systeminfo += " Windows XP ";
			}
			tz03.setSysteminfo(systeminfo);
			saveService.save(tz03);
		}

		session.removeAttribute("HttpSessionBindingEvent");
		session.removeAttribute("user");
		session.removeAttribute("tz03");
		session.invalidate();
		return new ModelAndView("/");
		// TODO Auto-generated method stub
	}

	@RequestMapping("/sessiondistory.do")
	public ModelAndView sessiondistory(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("我被注销了！");
		request.getSession().invalidate();
		return null;
	}

	/**
	 * @param loadUserDataService
	 *            The loadUserDataService to set.
	 */
	public void setLoadUserDataService(LoadUserDataService loadUserDataService) {
		this.loadUserDataService = loadUserDataService;
	}

	/**
	 * @return the loadUserData
	 */
	public LoadUserDataService getLoadUserDataService() {
		return loadUserDataService;
	}

	/**
	 * @param loadUserData
	 *            the loadUserData to set
	 */
	public void setLoadUserData(LoadUserDataService loadUserDataService) {
		this.loadUserDataService = loadUserDataService;
	}

}
