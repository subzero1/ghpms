package com.netsky.base.flow.component;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.netsky.base.flow.FlowConstant;
import com.netsky.base.flow.trigger.DoTrigger;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb12_opernode;
import com.netsky.base.dataObjects.Tb15_docflow;
import com.netsky.base.dataObjects.Tb17_approve;

import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;


/**
 * @description:
 *  表单受理、取消受理
 * @class name:com.netsky.base.flow.component.Accept
 * @author wind Jan 20, 2010
 */
@Component
public class Accept  {

	/**
	 * 数据对应操作类
	 */
	@Autowired
	private SaveService saveService;

	@Autowired
	private QueryService queryService;
	
	/**
	 * 事件处理转发类
	 */
	@Autowired(required=false)
	private DoTrigger doTrigger;
	
	@Autowired(required=false)
	private ApproveComplete approveComplete;
	
	
	/**
	 * 日志处理类
	 */
	private  Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 受理
	 * @param paraMap
	 * @return
	 * @throws Exception String
	 */
	public String handleRequest(Map paraMap) throws Exception {
		Long tb12_id = MapUtil.getLong(paraMap, "opernode_id");//tb12_id
		Long pretb12_id = null ;
		Long node_id = null;
		Integer node_type = null;
		String node_name = null;
		
		StringBuffer hsql = new StringBuffer();
		List tmpList = new LinkedList();
		QueryBuilder queryBuilder;
		
		//获得preTb12_id
		hsql.append("select source_id from Tb13_operrelation where dest_id = ?");
		tmpList = queryService.searchList(hsql.toString(), new Object[] {tb12_id});
		if (tmpList.size() > 0) {
			pretb12_id = convertUtil.toLong(tmpList.get(0));
		}
		tmpList.clear();
		
		//获得当前节点名称
		hsql.delete(0, hsql.length());
		hsql.append("select node_name,node_id,node_type from Tb12_opernode tb12 where   tb12.id = ? ");
		tmpList = queryService.searchList(hsql.toString(), new Object[] {tb12_id});
		if (tmpList.size() > 0) {
			Object[] arr = (Object[]) tmpList.get(0);
			node_name = convertUtil.toString(arr[0]);
			node_id = convertUtil.toLong(arr[1]);
			node_type = convertUtil.toInteger(arr[2]);
		}
		
		//************************受理、取消受理业务处理************************/ 
		Session session = null; //事务相关
		Transaction tx = null;//事务相关
		try {
			session = saveService.getHiberbateSession();
			tx = session.beginTransaction(); 
			tx.begin(); //开始事务			
			//************************取消受理处理************************/ 
			if("cancel".equals(paraMap.get("doType"))){
				// 置当前节点为待办 0
				hsql.delete(0, hsql.length());
				hsql.append("update Tb12_opernode set node_status = 0 where id =  ");
				hsql.append(tb12_id);			
				//dao.update(hsql.toString());
				session.createQuery(hsql.toString()).executeUpdate();
				
				// 置当前节点文档状态,先得到tb15当前节点对应记录，后在更新。
				Tb15_docflow tb15 = null;
				hsql.delete(0, hsql.length());
				hsql.append(" select tb15 from Tb15_docflow tb15 ");
				hsql.append(" where tb15.user_id =  ? and tb15.opernode_id = ?");
				tmpList = queryService.searchList(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "user_id"), tb12_id });
				if (tmpList.size() > 0) {
					tb15 = (Tb15_docflow) tmpList.get(0);
				}
				tmpList.clear();	
				//是否发送到人
				String topeople ="N";
				hsql.delete(0, hsql.length());
				hsql.append(" select tb03.toPeople from Tb13_operrelation tb13,Tb03_relation tb03 ");
				hsql.append(" where tb13.relation_id = tb03.id and tb13.dest_id = ? ");
				tmpList = queryService.searchList(hsql.toString(), new Object[]{ tb12_id });
				if(tmpList.size() > 0){
					topeople = (String)tmpList.get(0);
					tmpList.clear();
				} else {
					hsql.delete(0, hsql.length());
					hsql.append(" select 'x' from Tb13_operrelation tb13 ");
					hsql.append(" where tb13.dest_id = ? and tb13.relation_id = -1");
					tmpList = queryService.searchList(hsql.toString(), new Object[]{ tb12_id });
					if(tmpList.size() > 0){
						topeople = "Y";
					}
					tmpList.clear();
				}

				/**
				 * 驻地网接口，客响接口发送到调配特殊处理
				 */
				long tmpNode_id = node_id.longValue();
				if(tmpNode_id == 110103 || tmpNode_id == 120102 ||tmpNode_id == 130102 ){
					tmpList = queryService.searchList("select 'x' from Ti03_xqly where project_id = ?",new Object[]{ MapUtil.getLong(paraMap, "project_id")});
					if(tmpList.size() >0){
						topeople ="N";
					}
				}
				
				if(tb15 != null){
					tb15.setAccept_time(null);
					tb15.setDoc_status(new Integer(0));
					tb15.setOper_time(null);
					if("Y".equals(topeople)){
						tb15.setUser_id(MapUtil.getLong(paraMap, "user_id"));
						tb15.setHuser_id(MapUtil.getLong(paraMap, "user_id"));
					}else {
						tb15.setUser_id(new Long(-1));
						tb15.setHuser_id(new Long(-1));
					}
					//保存tb15
					session.saveOrUpdate(tb15);
				}
				
				//删除审批意见,不具备条件不删除
				hsql.delete(0, hsql.length());
				hsql.append("delete from Tb17_approve where opernode_id =  ");
				hsql.append(tb12_id);
				hsql.append(" and user_id = ");
				hsql.append(MapUtil.getLong(paraMap, "user_id"));
				hsql.append(" and check_result != ");
				hsql.append(FlowConstant.CHECK_RESULT_BJTJ);
				//dao.update(hsql.toString());
				session.createQuery(hsql.toString()).executeUpdate();

				tmpList.clear();
				//更新上一节点的TB15
				hsql.delete(0, hsql.length());
				hsql.append("update Tb15_docflow set oper_status = '待办' , oper_user = '");
				hsql.append(node_name);
				hsql.append("' where doc_status != 8 and opernode_id =  ");
				hsql.append(pretb12_id);
				session.createQuery(hsql.toString()).executeUpdate();
				//dao.update(hsql.toString());
				
			}
			//************************受理处理************************/ 
			else {
				// 置当前节点为待办 0
				Tb12_opernode tb12 = (Tb12_opernode)queryService.searchById(Tb12_opernode.class, tb12_id);
				tb12.setNode_status(FlowConstant.NODE_STATUS_WORK);
				session.saveOrUpdate(tb12);
				
				// 置当前节点文档状态,先得到tb15当前节点对应记录，后在更新。
				Tb15_docflow tb15 = null;
				hsql.delete(0, hsql.length());
				hsql.append(" select tb15 from Tb15_docflow tb15 ");
				hsql.append(" where (tb15.user_id =  ? or tb15.user_id = -1 ) and tb15.opernode_id = ?");
				tmpList = queryService.searchList(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "user_id"), tb12_id });
				if (tmpList.size() > 0) {
					tb15 = (Tb15_docflow) tmpList.get(0);
				}
				tmpList.clear();	
				if(tb15 != null){
					tb15.setAccept_time(new Date());
					tb15.setDoc_status(new Integer(2));
					tb15.setOper_time(new Date());
					tb15.setUser_id(MapUtil.getLong(paraMap, "user_id"));
					tb15.setHuser_id(MapUtil.getLong(paraMap, "user_id"));
					tb15.setWrite_limit(new Integer(1));
					
					//保存tb15
					session.saveOrUpdate(tb15);
				}		
				//更新上一节点的TB15
				hsql.delete(0, hsql.length());
				hsql.append("update Tb15_docflow set oper_status = '在办' , oper_user = '");
				hsql.append(MapUtil.getString(paraMap, "user_name"));
				hsql.append("' where doc_status != 8 and opernode_id =  ");
				hsql.append(pretb12_id);
				//dao.update(hsql.toString());
				session.createQuery(hsql.toString()).executeUpdate();
				
				//非审批节点系统自动审批同意
				if(FlowConstant.NODE_TYPE_NO_CHECK.equals(node_type)){
					Tb17_approve tb17 = new Tb17_approve();
					tb17.setResult_str("接收");
					tb17.setCheck_idea("受理，系统默认接收");
					tb17.setDoc_id(MapUtil.getLong(paraMap, "doc_id"));
					tb17.setProject_id(MapUtil.getLong(paraMap, "project_id"));
					tb17.setModule_id(MapUtil.getLong(paraMap, "module_id"));
					tb17.setNode_id(node_id);
					tb17.setOpernode_id(tb12_id);
					tb17.setCheck_result(FlowConstant.CHECK_RESULT_OK);
					tb17.setOper_time(new Date());
					tb17.setUser_id(MapUtil.getLong(paraMap, "user_id"));
					tb17.setUser_name(MapUtil.getString(paraMap, "user_name"));
					tb17.setStation(node_name);
					session.saveOrUpdate(tb17);
				}
			}
			session.flush();
			tx.commit(); // 提交事务;
		} catch (Exception e) {
			if(tx != null ) {
				session.flush();
				tx.rollback(); // 回滚事务;
			}
			log.error("受理、取消受理文档出错", e);
			throw new Exception("受理、取消受理文档出错",e);
		} finally{
			if(session != null){
				session.close();
			}
		}

		//************************受理事件要触发 事务	************************/ 
		paraMap.put("even_id",node_id);
		paraMap.put("even_type", FlowConstant.EVEN_TYPE_ACCEPT);
		if(doTrigger != null){
			doTrigger.findAndExcute(paraMap);
		}
		
		if(FlowConstant.NODE_TYPE_NO_CHECK.equals(node_type)&&!"cancel".equals(paraMap.get("doType"))){
			//审结
			approveComplete.handleRequest(paraMap);
		}
		
		//返回打开表单
		return com.netsky.base.flow.FlowConstant.CTR_OPENFORM;
	}


	/**
	 * @return Returns the doTrigger.
	 */
	public DoTrigger getDoTrigger() {
		return doTrigger;
	}

	/**
	 * @param doTrigger The doTrigger to set.
	 */
	public void setDoTrigger(DoTrigger doTrigger) {
		this.doTrigger = doTrigger;
	}


	/**
	 * @hibernate.property column="saveService"
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
	 * @hibernate.property column="queryService"
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
	 * @hibernate.property column="approveComplete"
	 * @return Returns the approveComplete.
	 */
	public ApproveComplete getApproveComplete() {
		return approveComplete;
	}


	/**
	 * @param approveComplete The approveComplete to set.
	 */
	public void setApproveComplete(ApproveComplete approveComplete) {
		this.approveComplete = approveComplete;
	}


}
