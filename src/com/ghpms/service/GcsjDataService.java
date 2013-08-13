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
	public List getOutDateList();
}