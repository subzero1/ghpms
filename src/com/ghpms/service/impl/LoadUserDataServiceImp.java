package com.ghpms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.*;
import com.netsky.base.service.LoadUserDataService;
import com.netsky.base.service.QueryService;

/**
 * @description:
 * 加载用户数据
 * @class name:com.ghpms.serviceImpl.LoadUserDataServiceImp
 * @author wind Jan 20, 2010
 */
@Service
public class LoadUserDataServiceImp implements LoadUserDataService {
	/**
	 * 日志处理器
	 */
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
	
	/* (non-Javadoc)
	 * @see com.netsky.base.service.LoadUserDataService#load(com.netsky.base.dataObjects.Ta03_user)
	 */
	public Map<String,Object> load(Ta03_user user) {
		// TODO Auto-generated method stub
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		ResultObject rs ;
		QueryBuilder queryBuilder;
		StringBuffer hsql = new StringBuffer();
		
		/**
		 * 加载用户部门信息
		 */
		Ta01_dept ta01 = null;
		if(user != null && user.getDept_id() != null){
			ta01 = (Ta01_dept)queryService.searchById(Ta01_dept.class, user.getDept_id());
			dataMap.put("user",user);
		}
		
		/**
		 * 加载用户扩展表
		 */
		queryBuilder = new HibernateQueryBuilder(Ta21_user_ext.class);
		queryBuilder.eq("user_id",user.getId());
		rs = queryService.search(queryBuilder);
		if (rs.next()) {
			dataMap.put("userExt",(Ta21_user_ext) rs.get(Ta21_user_ext.class.getName()));
		}

		/**
		 * 加载用户拥有的角色
		 */
		Map<String, Ta04_role> rolesMap = new HashMap<String,Ta04_role>();
		List<Ta04_role> tmpList  =(List<Ta04_role>)queryService.searchList("select a from Ta04_role a,Ta12_sta_role b,Ta11_sta_user c" +
				" where a.id = b.role_id and b.station_id = c.station_id and c.user_id = " + user.getId());
		for(Ta04_role role:tmpList){
			rolesMap.put(String.valueOf(role.getId()),role);
		}
		
		dataMap.put("rolesMap", rolesMap);
		
		/**
		 * 加载用户拥有的节点
		 */
		Map<String, Tb02_node> nodesMap = new HashMap<String,Tb02_node>();
		rs =queryService.search("select a from Tb02_node a,Ta13_sta_node b,Ta11_sta_user c" +
				" where a.id = b.node_id and b.station_id = c.station_id and c.user_id = " + user.getId());
		while(rs.next()){
			Tb02_node tmpNode = (Tb02_node)rs.get("a");
			nodesMap.put(String.valueOf(tmpNode.getId()), tmpNode);
		}
		dataMap.put("nodesMap", nodesMap);		
		
		/**
		 * 加载委托用户
		 */
		hsql.delete(0, hsql.length());
		hsql.append("select ta03 from Ta28_work_trust ta28,Ta03_user ta03");
		hsql.append(" where ta28.from_userid = ta03.id");
		hsql.append(" and ta28.end_time is null");
		hsql.append(" and ta28.to_userid = " );
		hsql.append(user.getId());
		List<Ta03_user> trustUserList = (List<Ta03_user>)queryService.searchList(hsql.toString());
		if(trustUserList.size()>0){
			Map<String,Ta03_user> trustUserMap = new HashMap<String,Ta03_user>();
			for(Ta03_user tmpUser:trustUserList){
				trustUserMap.put(String.valueOf(tmpUser.getId()), tmpUser);
			}
			dataMap.put("trustUserMap", trustUserMap);
		}
		
		/**
		 * 文档列表，列定义
		 * 列结构：ta07_formfield
		 */
		List docColsList=null;
		hsql.delete(0, hsql.length());
		hsql.append("select ta07 from Ta07_formfield ta07,Ta31_worklist_cfg ta31 ");
		hsql.append(" where ta31.field_id = ta07.id");
		hsql.append(" and ta31.user_id = " + user.getId());
		hsql.append(" order by ta31.ord");
		docColsList = queryService.searchList(hsql.toString());
		if (docColsList == null || docColsList.size() == 0) {
			docColsList = queryService
					.searchList("from Ta07_formfield ta07 where module_id = 100 and ta07.show_flag = 1 order by ord");
		}
		
		int docTabWitdh = 0;
		for(Object o:docColsList){
			Ta07_formfield ta07 = (Ta07_formfield)o;
			if(ta07.getObject_name().equals("com.netsky.base.flow.vo.DocStruct")){
				ta07.setObject_name("doc");
			} else {
				ta07.setObject_name("gcxx");
			}
			docTabWitdh += ta07.getWidth();
		} 
				
		dataMap.put("docColsList", docColsList);
		dataMap.put("docTabWitdh", docTabWitdh);
		/**
		 * 加载用户模板
		 */
		return dataMap;
	}

}
