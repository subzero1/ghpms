package com.netsky.base.flow.component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseDao.JdbcSupport;
import com.netsky.base.flow.trigger.DoTrigger;
import com.netsky.base.flow.trigger.Trigger;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

/**
 * @description:
 * 流程相关数据库表中配置的trigger 执行
 * @class name:com.netsky.base.flow.component.DoDbTrigger
 * @author wind Jan 20, 2010
 */
@Component
public class DoDbTrigger implements DoTrigger{
	/**
	 * 数据对应操作类
	 */
	@Autowired
	private SaveService saveService;

	@Autowired
	private QueryService queryService;
	
	@Autowired
	private JdbcSupport jdbcSupport;

	/**
	 * @param saveService The saveService to set.
	 */
	public void setSaveService(SaveService saveService) {
		this.saveService = saveService;
	}

	/**
	 * @param queryService The queryService to set.
	 */
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	/**
	 * 查找执行，相关的事务触发器
	 * 
	 * @param paraMap <br>须含有project_id,node_id,
	 * <br>opernode_id,doc_id,relation_id,
	 * <br>even_id,even_type,tb14_status
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	/**
	 *　重载方法：findAndExcute
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.trigger.DoTrigger#findAndExcute(java.util.Map)
	 */
	public void findAndExcute(Map paraMap) throws Exception {
		StringBuffer hsql = new StringBuffer();
		List tmpList = new LinkedList();
		Map<String,String> sqlParaMap = new HashMap<String,String>();
		
		//可执行sql；sql参数用${user_id}表示用户ID${user_name},${project_id},${doc_id},${module_id},${node_id},${opernode_id}
		//审批审结：${approve_result},${check_idea},${check_result}
		String sqlParaStr = MapUtil.getUrl(paraMap, new String[] { "project_id", "node_id", "module_id", "doc_id",
				"user_id", "user_name", "opernode_id","approve_result","check_idea","check_result"});
		MapUtil.load(sqlParaMap, sqlParaStr);
		

		hsql.append(" select tb05.doclass,tb05.paras  as tb05_paras ,tb14.parameters as tb14_paras,tb05.excute_sql  ");
		hsql.append(" from Tb05_affair tb05,Tb14_even_affair tb14 ");
		hsql.append(" where tb05.id = tb14.affair_id");
		hsql.append(" and tb05.affair_type = 'background'");
		hsql.append(" and (tb05.doclass != null or tb05.excute_sql != null ) and tb14.even_type = ? ");
		hsql.append(" and (tb14.even_id = ? or tb14.even_id = -1) ");
		hsql.append(" order by tb14.seq");
		tmpList = queryService.searchList(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "even_type"),
				MapUtil.getLong(paraMap, "even_id") });

		for (Object row : tmpList) {
			
			try{
				if (row instanceof Object[]) {
					Object[] arr = (Object[]) row;
					// **********************************执行sql******************************************
					if (arr[3] != null) {
						
						String tmpSql = (String) arr[3];
						for (String key : sqlParaMap.keySet()) {
							tmpSql = tmpSql.replace("${" + key + "}", sqlParaMap.get(key));
						}
						jdbcSupport.getJdbcTemplate().update(tmpSql);
						continue;
					}
					// **********************************执行类*******************************************
					// 把action相关参数串到window_pro
					Map<String, String> proMap = new HashMap<String, String>();
					MapUtil.load(proMap, (String) arr[1]);
					MapUtil.load(proMap, (String) arr[2]);

					MapUtil.load(proMap, MapUtil.getUrl(paraMap, new String[] { "project_id", "opernode_id", "node_id","module_id","approve_result",
							"doc_id", "user_id", "user_name" }));
					Class c;
					try {
						c = Class.forName((String) arr[0]);

						Trigger m_trigger = (Trigger) c.newInstance();
						// 设置trigger中持久化处理类
						m_trigger.setQueryService(queryService);
						m_trigger.setSaveService(saveService);
						m_trigger.setJdbcSupport(jdbcSupport);
						if ("cancel".equals(paraMap.get("doType"))) {// 逆向操作
							m_trigger.unProcess(proMap);
						} else {// 正向操作
							m_trigger.process(proMap);
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			} catch (Exception ex){
				//一个服务出异常其它继续
				//ex.printStackTrace();
				continue;
			}

		}
	}

	/**
	 * @return Returns the jdbcSupport.
	 */
	public JdbcSupport getJdbcSupport() {
		return jdbcSupport;
	}



	/**
	 * @param jdbcSupport The jdbcSupport to set.
	 */
	public void setJdbcSupport(JdbcSupport jdbcSupport) {
		this.jdbcSupport = jdbcSupport;
		
	}
}
