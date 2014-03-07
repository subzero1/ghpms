package com.ghpms.controller.base;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ghpms.service.CreateJspFile;
import com.ghpms.service.GcsjDataService;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Tb02_node;
import com.netsky.base.service.QueryService;

public class JspBuildServlet implements Servlet {

	private QueryService queryService;

	private CreateJspFile createJspFile;

	private GcsjDataService gcsjDataService;

	public void destroy() {
	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public String getServletInfo() {
		return null;
	}

	/**
	 *　重载方法：init (non-Javadoc)
	 * 
	 * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig paramServletConfig) throws ServletException {
		StringBuffer hql = new StringBuffer();
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(paramServletConfig
						.getServletContext());
		// 获取web-inf目录
		String path = paramServletConfig.getServletContext().getRealPath(
				"/WEB-INF");
		queryService = (QueryService) ctx.getBean("queryService");
		createJspFile = (CreateJspFile) ctx.getBean("createJspFile");
		gcsjDataService = (GcsjDataService) ctx.getBean("gcsjDataService");
		List<Ta06_module> modules = (List<Ta06_module>) queryService
				.searchList("select ta06 from Ta06_module ta06 where ta06.type=1 ");
		 //初始化Ta06的的jsp文件
		if (modules != null && modules.size() > 0) {
			for (Ta06_module ta06_module : modules) {
				createJspFile.AutoCreateJspFile(path, ta06_module.getId());
			}
		}
		hql.append("select tb02 from Tb02_node tb02 where 1=1 order by id ");
		List nodeList = queryService.searchList(hql.toString());
		// 生成录入界面
		for (Object object : nodeList) {
			Tb02_node node = (Tb02_node) object;
			createJspFile.createJspFileToRecord(path, node.getId());
		}
		// 生成表单界面
		hql.delete(0, hql.length());
		hql.append("select t from Tb02_node t where 1=1 and t.node_type=2 order by t.id ");
		nodeList = queryService.searchList(hql.toString());
		for (Object object : nodeList) {
			Tb02_node node = (Tb02_node) object;
			createJspFile.createJspFileToForm(path, node.getId());
		}

	}

	public void service(ServletRequest paramServletRequest,
			ServletResponse paramServletResponse) throws ServletException,
			IOException {
	}

}
