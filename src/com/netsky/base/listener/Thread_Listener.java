package com.netsky.base.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.netsky.base.service.ThreadToolService;

/**
 * 线程监听器
 * 
 * @author lee.xiangyu
 * 
 */
public class Thread_Listener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		ThreadToolService threadToolService = (ThreadToolService) ctx.getBean("threadToolService");
		threadToolService.setServletContext(arg0.getServletContext());
		threadToolService.start();
	}
}
