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
	 * 生成表单
	 * @param path　路径
	 * @param module_id void
	 */
	public void AutoCreateJspFile(String path,Long module_id);
	
	/**
	 * 生成录入文件
	 * @param request
	 * @param paraMap void
	 */
	public void createJspFileToRecord(HttpServletRequest request,Long node_id);
	
	/**
	 * 生成录入文件
	 * @param path 文件路径
	 * @param node_id void
	 */
	public void createJspFileToRecord(String path,Long node_id);
	
	/**
	 * 读取录入文件权限列表
	 * @param request
	 * @param paraMap void
	 */
	public void getNode(HttpServletRequest request,Map paraMap);
	
	/**
	 * 生成大表单
	 * @param path
	 * @param module_id void
	 */
	public void createJspFileToForm(String path,Long node_id);
}
