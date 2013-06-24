package com.netsky.base.flow.buttonControl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

/**
 * @description:
 * 流程按扭控制类接口
 * @class name:com.netsky.base.flow.buttonControl.ButtonControl
 * @author wind Jan 12, 2010
 */
public abstract class ButtonControl {
	
	/**
	 * 数据操作类 hibernate
	 */
	protected SaveService saveService;

	protected QueryService queryService;
	
		
	/**
	 * 获得本动作是否可以 显示
	 * method:getStatus
	 * @param paras
	 * @return boolean
	 */
	public abstract boolean isShow(Map paraMap);
	
	/**
	 * 发送操作对应目的人群
	 * method:getEmpls
	 * @param paras
	 * @return boolean
	 */
	
	public abstract List<Ta03_user> getUsers(Map paraMap);
	
	/**
	 * 检查本过按扭所依属的条件是否满足
	 * <br>不满足返回“提示语”
	 * <br>满足返回“OK"
	 * method:getEmpls
	 * @param paras
	 * @return boolean
	 */	
	public abstract String checkCondition(Map paraMap);

	/**
	 * @return Returns the saveService.
	 */
	public SaveService getSaveService() {
		return saveService;
	}

	/**
	 * @param saveService The saveService to set.
	 */
	public void setSaveService(SaveService saveService) {
		this.saveService = saveService;
	}

	/**
	 * @return Returns the queryService.
	 */
	public QueryService getQueryService() {
		return queryService;
	}

	/**
	 * @param queryService The queryService to set.
	 */
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}
}
