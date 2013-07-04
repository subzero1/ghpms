package com.ghpms.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.netsky.base.dataObjects.Ta03_user;

public interface GcsjDataService {
	public Map getFormTitleMap(Ta03_user user,Long module_id);
	public void setDocColsList(HttpServletRequest request);
}
