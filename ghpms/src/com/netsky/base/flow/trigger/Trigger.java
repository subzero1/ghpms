package com.netsky.base.flow.trigger;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseDao.JdbcSupport;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

/**
 * @description:
 * 流程后台执行抽象类
 * @class name:com.netsky.base.flow.trigger.Trigger
 * @author wind Jan 12, 2010
 */

public abstract class Trigger {
	
	/**
	 * 数据操作类 hibernate
	 */
	protected SaveService saveService;

	protected QueryService queryService;
	
	/**
	 * 数据操作类 jdbc
	 */
	protected JdbcSupport jdbcSupport;
	
    
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
	/**
	 * @return Returns the jdbcSupport.
	 */
	public JdbcSupport getJdbcSupport() {
		return jdbcSupport;
	}
	/**
     * 设置数据库操作jdbc支持
     * @param jdbcSupport void
     */
    public void setJdbcSupport(JdbcSupport jdbcSupport){
    	this.jdbcSupport =  jdbcSupport;
    }
     /**
     * 正向操作
     * @param paraMap
     * @return String
     */
    public abstract String process(Map paraMap) throws Exception;
     
     /**
     * 逆向操作
     * @param paraMap
     * @return String
     */
    public abstract String unProcess(Map paraMap)  throws Exception;
}