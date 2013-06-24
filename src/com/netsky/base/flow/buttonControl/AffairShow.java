package com.netsky.base.flow.buttonControl;

import java.util.List;
import java.util.Map;

import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb03_relation;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.utils.convertUtil;
/**
 * @description:
* @class name:com.netsky.base.flow.buttonControl.ButtonShow
 * @author Administrator Mar 22, 2010
 */
public class AffairShow extends ButtonControl {

	/**
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#checkCondition(java.util.Map)
	 */
	public String checkCondition(Map paraMap) {
		return "OK";
	}

	/**
	  * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#getUsers(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<Ta03_user> getUsers(Map paraMap) {
		return null;
	}

	/**
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#isShow(java.util.Map)
	 */
	public boolean isShow(Map paraMap) {
		
		QueryBuilder queryBuilder = null;
		StringBuffer hsql = new StringBuffer("");
		ResultObject ro = null;
		Long affair_id = convertUtil.toLong(paraMap.get("affair_id"));
		Long project_id = convertUtil.toLong(paraMap.get("project_id"));
		
		/*
		 * 设计打分
		 */
		if(affair_id == 7){
			hsql.delete(0, hsql.length());
			hsql.append("select id ");
			hsql.append("from Td01_xmxx td01 ");
			hsql.append("where td01.sjdw is null ");
			hsql.append("and td01.id = ");
			hsql.append(project_id);
			
			List list = queryService.searchList(hsql.toString());
			
			if(list != null && list.size() > 0 ){
				return false;
			} else {
				return true;
			}
		}
		
		if(affair_id == 8){
			hsql.delete(0, hsql.length());
			hsql.append("select id ");
			hsql.append("from Td01_xmxx td01 ");
			hsql.append("where td01.sgdw is null ");
			hsql.append("and td01.id = ");
			hsql.append(project_id);
			
			List list = queryService.searchList(hsql.toString());
			
			if(list != null && list.size() > 0 ){
				return false;
			} else {
				return true;
			}
		}
		
		if(affair_id == 9){
			hsql.delete(0, hsql.length());
			hsql.append("select id ");
			hsql.append("from Td01_xmxx td01 ");
			hsql.append("where td01.jldw is null ");
			hsql.append("and td01.id = ");
			hsql.append(project_id);
			
			List list = queryService.searchList(hsql.toString());
			
			if(list != null && list.size() > 0 ){
				return false;
			} else {
				return true;
			}
		}
		return true;
	}
}
