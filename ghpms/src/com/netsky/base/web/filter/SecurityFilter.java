package com.netsky.base.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @description:
 * 权限校验
 * <br>1、校验用户有无登录如无登录转到；登录页面
 * <br>2、如果请求参数中包括Node_id或，role_id校验当前用户有无权限如果无，返回 noLimit页面。
 * @class name:com.netsky.base.web.filter.SecurityFilter
 * @author wind Jan 28, 2010
 */
public class SecurityFilter implements Filter{
	protected FilterConfig filterConfig = null;
	private String loginURL = null;
	private String noLimitURL = null;
	private List notCheckURLList = new ArrayList();
	
	public void destroy() {
		notCheckURLList.clear();
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		HttpSession session = request.getSession();
		
		//如果请求地址不需校验，或请求参数不包括role_id、node_id
		if (checkRequestURIIntNotFilterList(request) || (request.getParameter("node_id") == null
				&& request.getParameter("role_id") == null)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		//本用户包含角色，通过
		Map<String,?> nodesMap = (Map<String,?>)session.getAttribute("nodeMap");
		Map<String,?> rolesMap = (Map<String,?>)session.getAttribute("rolesMap");
		if ((nodesMap != null && nodesMap.containsKey(request.getParameter("node_id")))
				|| (rolesMap != null && rolesMap.containsKey(request.getParameter("role_id")))) {
			filterChain.doFilter(request, response);;
		}
		
		//nodesMap 为空说明用户未登录，返回登录页面loginURL
		if(nodesMap == null ){
			response.sendRedirect(request.getContextPath() + loginURL);
			return;
			
			//否则返回没有权限
		} else {
			response.sendRedirect(request.getContextPath() + noLimitURL);
			return;
		}
		
	}
	private boolean checkRequestURIIntNotFilterList(HttpServletRequest request) {
		String uri = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
		return notCheckURLList.contains(uri);
	}
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		loginURL = filterConfig.getInitParameter("loginURL");
		noLimitURL = filterConfig.getInitParameter("noLimitURL");

		String notCheckURLListStr = filterConfig.getInitParameter("notCheckURLList");
		if (notCheckURLListStr != null) {
			StringTokenizer st = new StringTokenizer(notCheckURLListStr, ";");
			notCheckURLList.clear();
			while (st.hasMoreTokens()) {
				notCheckURLList.add(st.nextToken());
			}
		}		
	}

}
