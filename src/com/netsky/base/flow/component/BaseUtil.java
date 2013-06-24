package com.netsky.base.flow.component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta05_group;
import com.netsky.base.dataObjects.Ta14_group_user;
import com.netsky.base.dataObjects.Ta15_group_node;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

/**
 * @description:
 * 群组操作工具类
 * @class name:com.netsky.base.flow.component.GroupUtil
 * @author wind Jan 23, 2010
 */
@Component
public class BaseUtil {
	/**
	 * 数据对应操作类
	 */
	@Autowired
	private SaveService saveService;

	@Autowired
	private QueryService queryService;

	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * hsql
	 */
	StringBuffer hsql ;
	
	/**
	 * 
	 */
	QueryBuilder queryBuilder;



	/**
	 *  判断群组下是存在拥有node_id的人员
	 * @param node_id
	 * @param group_ids
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	public boolean hasUser(Long node_id,Long[] group_ids){
		hsql = new StringBuffer(" select 'x' from Ta03_user user,Ta14_group_user ta14,Ta15_group_node ta15 ");
		hsql.append(" where user.id = ta14.user_id and  ta14.group_id = ta15.group_id  ");
		hsql.append(" and ta14.user_id = ? ");
		String tmpstr = " and ta15.group_id in(";
		for(Long group_id :group_ids){
			tmpstr = tmpstr + group_id + ",";
		}
		if(tmpstr.endsWith("(")){
			hsql.append(tmpstr + ")");
		}	
		
		List<?> tmpList = queryService.searchList(hsql.toString(), new Object[]{node_id});
		return tmpList.size() > 0;
	}
	
	/**
	 * 通过获得群组下拥有权限人员
	 * @param node_id
	 * @param group_id
	 * @return List<Ta03_user>
	 */
	@SuppressWarnings("unchecked")
	public List<Ta03_user> getUser(Long node_id,Long[] group_ids){
		
		//如果群组为空
		if(group_ids == null || group_ids.length == 0){
			return getUser(node_id);
		}

		hsql = new StringBuffer(" select distinct user_id from Ta14_group_user ta14");
		hsql.append(" where ta14.group_id in ");
		String tmpstr = "(";
		for(Long group_id :group_ids){
			tmpstr = tmpstr + group_id + ",";
		}
		hsql.append(tmpstr + "-1)");
		
		List<Long> user_ids = (List<Long>) queryService.searchList(hsql.toString());
		List<Ta03_user> users = getUser(node_id);
		List<Ta03_user> userList = new LinkedList<Ta03_user>();
		
		for(Ta03_user user:users){
			for(Long user_id:user_ids){
				if(user.getId().equals(user_id)){
					userList.add(user);
					//user_ids.remove(user_id);
					continue;
				}
			}
		}
		
		return userList;
	}	
	
	/**
	 * 获得拥有node_id 的人员
	 * @param node_id
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	public List<Ta03_user> getUser(Long node_id){
		hsql = new StringBuffer(" select distinct ta03 from Ta03_user ta03,Ta11_sta_user ta11,Ta13_sta_node ta13 ");
		hsql.append(" where ta03.id = ta11.user_id and ta13.station_id = ta11.station_id ");
		hsql.append(" and ta13.node_id = ? ");
		hsql.append(" order by ta03.login_id ");
		List<Ta03_user> tmpList =  (List<Ta03_user>)queryService.searchList(hsql.toString(), new Object[]{node_id});
		return tmpList;
	}
	
	/**
	 * 通过人员,节点获得群组,
	 * <br>如果有多个群组，则获取级别最高的群组
	 * @param node_id
	 * @param user_id
	 * @return Ta05_group
	 */
	@SuppressWarnings("unchecked")
	public List<Ta05_group> getGroup(Long user_id,Long node_ids[]){
		
		Map<Long,Integer> tmpMap = new HashMap();
		List<Long> group_ids = null;
		
		hsql = new StringBuffer(" select group_id from Ta14_group_user ta14 ");
		hsql.append(" where ta14.user_id = ? ");
		group_ids  =  (List<Long>)queryService.searchList(hsql.toString(), new Object[]{user_id});		
		if(group_ids.size() == 0){
			return null;
		}
		queryBuilder = new HibernateQueryBuilder(Ta15_group_node.class);
		queryBuilder.in("group_id", group_ids.toArray());
		queryBuilder.in("node_id", node_ids);
		List<Ta15_group_node> tmpList = (List<Ta15_group_node> )queryService.searchList(queryBuilder);
		for(Ta15_group_node group_node:tmpList){
			if(tmpMap.containsKey(group_node.getGroup_id())){
				tmpMap.put(group_node.getGroup_id(), tmpMap.get(group_node.getGroup_id()) +1);
			} else {
				tmpMap.put(group_node.getGroup_id(), 1);
			}
		}
		
		group_ids = new LinkedList<Long>();
		for(Long group_id:tmpMap.keySet()){
			if(tmpMap.get(group_id) == node_ids.length){
				group_ids.add(group_id);
			}
		}
		
		if(group_ids.size() == 0){
			group_ids.add(new Long(-1));
		}
		
		queryBuilder = new HibernateQueryBuilder(Ta05_group.class);
		queryBuilder.in("id", group_ids.toArray());
		queryBuilder.addOrderBy(Order.asc("grade"));
		List<Ta05_group> groupList = (List<Ta05_group> )queryService.searchList(queryBuilder);

		return groupList ;
	}
	
	/**
	 * 通过节点来获得群组
	 * @param node_id
	 * @param user_id
	 * @return Ta05_group
	 */
	@SuppressWarnings("unchecked")
	public List<Ta05_group> getGroup(Long node_ids[]){
		Map<Long,Integer> tmpMap = new HashMap();
		QueryBuilder queryBuilder = new HibernateQueryBuilder(Ta15_group_node.class);
		queryBuilder.in("node_id", node_ids);
		List<Ta15_group_node> tmpList = (List<Ta15_group_node> )queryService.searchList(queryBuilder);
		for(Ta15_group_node group_node:tmpList){
			if(tmpMap.containsKey(group_node.getGroup_id())){
				tmpMap.put(group_node.getGroup_id(), tmpMap.get(group_node.getGroup_id()) +1);
			} else {
				tmpMap.put(group_node.getGroup_id(), 1);
			}
		}
		
		List<Long> group_ids = new LinkedList<Long>();
		for(Long group_id:tmpMap.keySet()){
			if(tmpMap.get(group_id) == node_ids.length){
				group_ids.add(group_id);
			}
		}
		
		if(group_ids.size() == 0){
			group_ids.add(new Long(-1));
		}
		
		queryBuilder = new HibernateQueryBuilder(Ta05_group.class);
		queryBuilder.in("id", group_ids.toArray());
		List<Ta05_group> groupList = (List<Ta05_group> )queryService.searchList(queryBuilder);

		return groupList ;
	}

	public void setSaveService(SaveService saveService) {
		this.saveService = saveService;
	}

	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}
	
}
