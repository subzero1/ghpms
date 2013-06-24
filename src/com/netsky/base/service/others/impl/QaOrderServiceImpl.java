package com.netsky.base.service.others.impl;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.others.QaOrderService;
import com.netsky.base.service.SaveService;
import com.netsky.base.dataObjects.Te07_qa;

/**
 * 修改序号或删除后其它问题重新排序
 * @author wangflan
 * @create 2010-08-09
 */

@Service("qaOrderService")
public class QaOrderServiceImpl implements QaOrderService {

	/**
	 * 数据库查询操作服务
	 */
	@Autowired
	private QueryService queryService;
	
	/**
	 * 数据保存操作服务
	 */
	@Autowired
	private SaveService saveService;
	
	
	/**
	 * 修改序号重新排序
	 */
	public void saveOrder(String order,Long save_id,Long save_ord){
		if("true".equals(order)){ 
			QueryBuilder queryBuilder = new HibernateQueryBuilder(Te07_qa.class);
			queryBuilder.notEq("id", save_id);
			queryBuilder.addOrderBy(Order.asc("ord"));
			ResultObject ro =queryService.search(queryBuilder);
			Integer i=0;
			
			while(ro.next()){
				i++;				
				Te07_qa te07= (Te07_qa)ro.get(Te07_qa.class.getName());

				//当前更改的序号不可占用
				if(save_ord.equals(new Long(i))){
					i++;
				}
					
				te07.setOrd(new Long(i));
				
				saveService.save(te07);
				
			 }
		}
		 
	}
	
	/**
	 * 删除后重新排序
	 */
	public void delOrder(){
		QueryBuilder queryBuilder = new HibernateQueryBuilder(Te07_qa.class);
		queryBuilder.addOrderBy(Order.asc("ord"));
		ResultObject ro =queryService.search(queryBuilder);
		Integer i=0;
		
		while(ro.next()){
			i++;				
			Te07_qa te07= (Te07_qa)ro.get(Te07_qa.class.getName());
			te07.setOrd(new Long(i));
			
			saveService.save(te07);
			
		 }
		 
	}
}
