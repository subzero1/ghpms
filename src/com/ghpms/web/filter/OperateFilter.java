package com.ghpms.web.filter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ghpms.dataObjects.form.Tf02_operation_log;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

/**
 * @description:
 * 
 * @class name:com.ghpms.web.filter.OperateFilter
 * @author liao Sep 24, 2013
 */
public class OperateFilter implements Filter{
	private QueryService queryService;
	private SaveService saveService;
	private String[] notFilter;
	public void destroy() {
	}

	/**
	 *　重载方法：doFilter
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest paramServletRequest,
			ServletResponse paramServletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) paramServletRequest;
		String uri=request.getRequestURI();
		boolean doFilter = isFilter(uri);
		Tf02_operation_log operation_log = null;
		if (doFilter) {
			operation_log=previous(paramServletRequest);
		}  
		chain.doFilter(paramServletRequest, paramServletResponse);
		if (doFilter) {
			if (operation_log!=null) {
			operation_log.setEnd_time(new Date());
			operation_log.setTatal_time(operation_log.getEnd_time().getTime()-operation_log.getStart_time().getTime());
			saveService.save(operation_log);
		}	
		}
	
	}

	public void init(FilterConfig paramFilterConfig) throws ServletException {
		ApplicationContext ctx = WebApplicationContextUtils
		.getWebApplicationContext(paramFilterConfig
				.getServletContext());
		this.queryService=(QueryService) ctx.getBean("queryService");
		this.saveService=(SaveService) ctx.getBean("saveService");
		this.notFilter=new String[] {"login.do","download.do","batchAutoCreateJavaXml.do" };
	}
	
	/**
	 * 检测是否可过滤
	 * @param uri
	 * @return boolean
	 */
	private boolean isFilter(String uri) {
		boolean doFilter = true;
		for (String s : notFilter) {
			if (uri.indexOf(s) != -1) {
				doFilter = false;
				break;
			}
		} if (uri.equals("/ghpms/")) {
			doFilter=false;
		}
		return doFilter;
	}
	
	/**
	 * 
	 * @param paramServletRequest
	 * @return Tf02_operation_log
	 */
	private Tf02_operation_log previous(ServletRequest paramServletRequest){
		HttpServletRequest request=(HttpServletRequest) paramServletRequest;
		Tf02_operation_log operation_log=new Tf02_operation_log();
		List list;
	    StringBuffer hql=new StringBuffer("from Tf02_operation_log tf02 where 1=1 ");
		HttpSession session=request.getSession();
		Ta03_user user = (Ta03_user)session.getAttribute("user");
		operation_log.setRequest_url(request.getRequestURL().toString());
		operation_log.setLogin_id(user.getId());
		operation_log.setUser_name(user.getName());
		operation_log.setUser_address(request.getRemoteAddr());
		operation_log.setSession_id(session.getId());
		 
		hql.append("and tf02.request_url like '%");
		hql.append(request.getRequestURI().toString()+"' ");
		hql.append("and tf02.end_time is null ");
		hql.append("and tf02.session_id='");
		hql.append(session.getId()+"' ");
		hql.append("and tf02.login_id=");
		hql.append(user.getId());
		
		operation_log.setStart_time(new Date());
		saveService.save(operation_log);
		list=queryService.searchList(hql.toString());
		if (list!=null&&list.size()>0) {
			operation_log=(Tf02_operation_log) list.get(0);	
			return operation_log;
		}else {
			return null;
		}
	}

}
