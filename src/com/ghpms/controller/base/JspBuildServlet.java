package com.ghpms.controller.base;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ghpms.service.CreateJspFile;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.convertUtil;

public class JspBuildServlet implements Servlet {

	private QueryService queryService;

	private CreateJspFile createJspFile;

	public void destroy() {
	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public String getServletInfo() {
		return null;
	}

	/**
	 *　重载方法：init
	 * (non-Javadoc)
	 * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig paramServletConfig) throws ServletException {
		StringBuffer hql=new StringBuffer();
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(paramServletConfig
						.getServletContext());
		//获取web-inf目录
		String path=paramServletConfig.getServletContext().getRealPath("/WEB-INF");
		queryService=(QueryService) ctx.getBean("queryService");
		createJspFile=(CreateJspFile) ctx.getBean("createJspFile");
		for (int i = 101; i < 109; i++) {
			createJspFile.AutoCreateJspFile(path, new Long(i)); 
		}

	}

	public void service(ServletRequest paramServletRequest,
			ServletResponse paramServletResponse) throws ServletException,
			IOException {
	}

}
