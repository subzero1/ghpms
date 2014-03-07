package com.ghpms.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta07_formfield;

public interface GcsjDataService {
	public Map getFormTitleMap(Ta03_user user,Long module_id);
	public void setDocColsList(HttpServletRequest request);
	public List<Ta07_formfield> getExcelTitleList(Ta03_user user,Long module_id);
	public List getUpdateProperty(Long module_id);
	public Map setSelectValue(HttpServletRequest request,Long node_id); 
	/**
	 * 获得超期列表
	 * @return List
	 */
	public List getOutDateList(Ta03_user user);
	
	/**
	 * 获得借料超期提醒列表
	 * @param user
	 * @return List
	 */
	public List getLendOutDateList(Ta03_user user);
	/**
	 * 反馈调度中心提醒列表
	 * @param user
	 * @return List
	 */
	public List getFkddList(Ta03_user user);
	/**
	 * 反馈工单列表
	 * @param user
	 * @return List
	 */
	public List getFkgdList(Ta03_user user);
	/**
	 * 紧急反馈列表
	 * @param user
	 * @return List
	 */
	public List getFkjjList(Ta03_user user);
	/**
	 * 大表单节点列表
	 * 
	 * @param user
	 * @param module_id
	 * @return List
	 */
	public List getTableNodeList(Ta03_user user,Long module_id);
}
