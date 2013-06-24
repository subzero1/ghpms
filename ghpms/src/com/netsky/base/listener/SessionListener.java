package com.netsky.base.listener;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.dataObjects.Tz03_login_log;

/**
 * 统计在线人数
 * 
 * @author CT
 * @create 2009-09-21
 */
public class SessionListener implements HttpSessionListener {

	/**
	 * 数据库操作类
	 */
	private Dao dao;
	
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		initApplicationContext(session);
		ServletContext application = session.getServletContext();
		Integer totalSessions = (Integer) application.getAttribute("totalSessions");
		if(totalSessions == null){
			totalSessions = new Integer(1);
			application.setAttribute("totalSessions", totalSessions);
		}else{
			totalSessions = new Integer(totalSessions.intValue() + 1);
			application.setAttribute("totalSessions", totalSessions);
		}
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		initApplicationContext(session);
		ServletContext application = session.getServletContext();
		Integer totalSessions = (Integer) application.getAttribute("totalSessions");
		if(totalSessions != null && totalSessions.intValue()>0){
			totalSessions = new Integer(totalSessions.intValue() - 1);
			application.setAttribute("totalSessions", totalSessions);
		}
		Map onlineUserList = (Map) application.getAttribute("onlineUserList");
		if (onlineUserList != null && !onlineUserList.containsKey(session.getId())){
			onlineUserList.remove(session.getId());
		}
		Tz03_login_log tz03 = (Tz03_login_log)session.getAttribute("tz03");
		if(tz03 != null){
			tz03.setLogout_date(new Date());
			dao.saveObject(tz03);
		}
	}

	/**
	 * 初始化ApplicationContext
	 * @param session void
	 */
	private void initApplicationContext(HttpSession session){
		if (dao == null){
			dao = (Dao)WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean("dao");
		}
	}
}
