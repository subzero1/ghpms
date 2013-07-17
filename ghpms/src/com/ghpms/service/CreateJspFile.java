package com.ghpms.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface CreateJspFile {
	/**
	 * 生成表单
	 * @param request
	 * @param module_id void
	 */
	public void AutoCreateJspFile(HttpServletRequest request,Long module_id);
	
	/**
	 * 生成表单,分左右
	 * @param request
	 * @param module_id void
	 */
	public void AutoCreateJspFileByTwo(HttpServletRequest request,Long module_id) ;
	/**
	 * 生成录入文件
	 * @param request
	 * @param paraMap void
	 */
	public void createJspFileToRecord(HttpServletRequest request,Long node_id);
	/**
	 * 读取录入文件权限列表
	 * @param request
	 * @param paraMap void
	 */
	public void getNode(HttpServletRequest request,Map paraMap);
}
