package com.ghpms.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.QueryService;


/**
 * @description:
 * 装载tc表数据，tc02按类别分别放到不能的List中去。
 * @class name:com.ghpms.serviceImpl.LoadShareDataServiceImp
 * @author wind Jan 20, 2010
 */
@Service("loadShareDataService")
public class LoadShareDataServiceImp implements com.netsky.base.service.LoadShareDataService{
	
	private  Logger log = Logger.getLogger(this.getClass());
	/**
	 * 数据库查询操作服务
	 */
	@Autowired
	private QueryService queryService ;
	
	/**
	 * @return the queryService
	 */
	public QueryService getQueryService() {
		return queryService;
	}

	/**
	 * @param queryService the queryService to set
	 */
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	public Map<String, List<?>> load() {
		// TODO Auto-generated method stub
		Map <String,List<?>> dataMap = new HashMap <String , List<?>>();
		
		//加载本应用共用数据
		return dataMap;
	}
}
