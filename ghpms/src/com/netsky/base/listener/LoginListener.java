package com.netsky.base.listener;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.dataObjects.Ta03_user;


/**
 * @author Chiang 2009-09-24
 */
public class LoginListener implements HttpSessionBindingListener {

	/**
	 * 用户类
	 */
	private Ta03_user user;
	
	/**
	 * 用户登录IP
	 */
	private String ip = "";
	
	private String systems ="";
	
	/**
	 * 数据库操作类
	 */
	private Dao dao;

	public LoginListener(Ta03_user user,String Ip,String sys) {
		this.user = user;
		this.ip = Ip;
		this.systems = sys;
	}

	public void valueBound(HttpSessionBindingEvent event) {
		
		HttpSession session = event.getSession();
		initApplicationContext(session);
		ServletContext application = session.getServletContext();
		List usermessage = new ArrayList();
		usermessage.add(this.user.getLogin_id());
		usermessage.add(this.user.getName());
		usermessage.add(new Date());
		usermessage.add(this.ip);
		usermessage.add(this.systems);

		// 把用户名放入在线列表
		Map onlineUserList = (Map) application.getAttribute("onlineUserList");
		if (onlineUserList == null) {
			onlineUserList = new HashMap();
			application.setAttribute("onlineUserList", onlineUserList);
		}
//		if (!onlineUserList.containsKey(this.user.getId())){
//			onlineUserList.put(this.user.getId(), this.user);
//		}
		if (!onlineUserList.containsKey(session.getId())){
			onlineUserList.put(session.getId(), usermessage);
		}
			
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		initApplicationContext(session);
		ServletContext application = session.getServletContext();
		// 从在线列表中删除用户名
		Map onlineUserList = (Map) application.getAttribute("onlineUserList");
//		if (onlineUserList != null)
//			onlineUserList.remove(this.user.getId());
		if (onlineUserList != null)
			onlineUserList.remove(session.getId());

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
