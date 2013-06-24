package com.netsky.base.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * 使浏览器不缓存页面的过滤器
 * <br>来自网络
 * @class name:com.netsky.base.web.filter.ForceNoCacheFilter
 * @author wind Jan 28, 2010
 */
public class ForceNoCacheFilter implements Filter {

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);
//		if(request.getSession().getAttribute("user")== null && 
//				!(request.getServletPath().endsWith("index.do")
//						||request.getServletPath().endsWith("login.do")||request.getServletPath().endsWith("checkPassword.do"))){
//			response.sendRedirect(request.getContextPath() + "/");
//		}
		
		filterChain.doFilter(request, response);
	}

	public void destroy() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}
}