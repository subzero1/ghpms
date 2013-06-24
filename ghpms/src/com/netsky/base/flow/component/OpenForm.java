package com.netsky.base.flow.component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netsky.base.flow.FlowConstant;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

/**
 * @description:
 * 打开文档
 * @class name:com.netsky.base.flow.component.OpenForm
 * @author wind Jan 20, 2010
 */
@Component
public class OpenForm {
	/**
	 * 数据对应操作类
	 */
	@Autowired
	private SaveService saveService;

	@Autowired
	private QueryService queryService;
	
	/**
	 * 数据对应操作类
	 */
	@Autowired
	private Accept accept;	
	/**
	 * 
	 * @param paraMap
	 * @return
	 * @throws Exception ModelAndView
	 */
	public String handleRequest(Map paraMap) throws Exception {
		StringBuffer hsql = new StringBuffer();
		List tmpList = new LinkedList();
		
		//获得preTb12_id
		hsql.append("select 'x' from Tb15_docflow where opernode_id = ? and user_id = ? and doc_status =? ");
		tmpList = queryService.searchList(hsql.toString(), new Object[] {MapUtil.getLong(paraMap, "opernode_id"),MapUtil.getLong(paraMap, "user_id"),FlowConstant.NODE_STATUS_NEED});
		if (tmpList.size() > 0&&MapUtil.getLong(paraMap, "user_id") != -1) {
			//自动受理
			accept.handleRequest(paraMap);
		}

		tmpList.clear();
		return FlowConstant.CTR_OPENFORM;
	}
	
}
