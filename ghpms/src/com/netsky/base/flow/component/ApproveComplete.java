package com.netsky.base.flow.component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.netsky.base.dataObjects.Tb03_relation;
import com.netsky.base.dataObjects.Tb12_opernode;
import com.netsky.base.dataObjects.Tb15_docflow;
import com.netsky.base.dataObjects.Tb17_approve;
import com.netsky.base.flow.trigger.DoTrigger;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.flow.FlowConstant;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

/**
 * @description:
 * 审结
 * @class name:com.netsky.base.flow.component.ApproveComplete
 * @author wind Jan 20, 2010
 */
@Component
public class ApproveComplete {

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
	@Autowired(required = false)
	private DoTrigger doTrigger;

	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * 自动办结处理器
	 */
	@Autowired(required = false)
	private Archive archive;

	/**
	 * 审结操作类
	 * @param paraMap 须含有如下参数：
	 * <br>project_id,module_id,doc_id,node_id,user_id,user_name,opernode_id;
	 * @return
	 * @throws Exception ModelAndView
	 */
	public String handleRequest(Map paraMap) throws Exception {
		//******************定义变量****************************/
		String returnView = FlowConstant.CTR_OPENFORM;//默认打开表单
		Long tb12_id = MapUtil.getLong(paraMap, "opernode_id");//tb12_id
		Long node_id = null;//当前节点标识 从tb12中取值
		Integer node_type = null;//当前节点类别 从tb12中取值
		String node_name = null; //当前节点名称 从tb12中取值
		String rollto_nodes = "";//本节点可回退节点
		Long module_id = MapUtil.getLong(paraMap, "module_id");//当前节点标识 对应的表单module

		Integer check_result = -1; //当前节点审批意见
		String oper_status = ""; //审批状态。
		Tb03_relation tb03 = null;//发送关系

		Tb12_opernode tb12 = null;
		Tb15_docflow tb15 = null;
		Tb17_approve tb17 = null;

		StringBuffer hsql = new StringBuffer();
		List tmpList = new LinkedList();
		QueryBuilder queryBuilder;
		


		//******************变量赋值赋值****************************/
		//获得当前节点名称
		tb12 = (Tb12_opernode) queryService.searchById(Tb12_opernode.class, tb12_id);
		if (tb12 != null) {
			node_name = tb12.getNode_name();
			node_type = tb12.getNode_type();
			node_id = tb12.getNode_id();
			rollto_nodes = tb12.getRollback_nodes();
		}
		//获得审批节点审批意见,
		hsql.delete(0, hsql.length());
		hsql.append("from  Tb17_approve tb17 where tb17.opernode_id = ? and user_id = ? order by id desc ");
		tmpList = queryService.searchList(hsql.toString(), new Object[] { tb12_id, MapUtil.getLong(paraMap, "user_id") });
		if (tmpList.size() > 0) {
			tb17 = (Tb17_approve) tmpList.get(0);
			check_result = tb17.getCheck_result();
			oper_status = tb17.getResult_str();
		}

		//获得关系
		hsql.delete(0, hsql.length());
		hsql.append("select tb03 from Tb03_relation tb03,Tb13_operrelation tb13 where tb13.relation_id = tb03.id ");
		hsql.append(" and tb13.dest_id = ? ");
		tmpList = queryService.searchList(hsql.toString(), new Object[] { tb12_id });
		if (tmpList.size() > 0) {
			tb03 = (Tb03_relation) tmpList.get(0);
		}
		
		//****************************************非会审节点审结不同意处理*****************************************************/
		/**
		 * 子流程类别：FLOW_TYPE_ONLY_ARCHIVE; 
		 *        办结所有除起草节点外的节点、起草节点放到回复文档中（点开只有办结按扭）。
		 *        且修改tb11.status状态为FLOW_STATUS_SHUTDOWN
		 * 		  
		 * 		  其它
		 *         办结所有除起草节点外的节点、起草节点放到回复文档中（点开只有办结按扭）。
		 */

		if(FlowConstant.CHECK_RESULT_NO.equals(check_result)&&!FlowConstant.NODE_TYPE_MORE_CHECK.equals(node_type)){
			//获取当前流程状态；
			Object flow_type ;
			hsql.delete(0, hsql.length());
			hsql.append("select tb01.type from Tb01_flow tb01,Tb02_node tb02 ");
			hsql.append(" where tb01.id = tb02.flow_id ");
			hsql.append(" and tb02.id = ?");
			tmpList = queryService.searchList(hsql.toString(),new Object[]{MapUtil.getLong(paraMap, "node_id")});
			flow_type = tmpList.get(0);
			/**
			 * 子流程类别 为FLOW_TYPE_ONLY_ARCHIVE; 
			 */
			if(FlowConstant.FLOW_TYPE_ONLY_ARCHIVE.equals(flow_type)){
				/**
				 * 关闭流程
				 */
				saveService.updateByHSql("update Tb11_operflow set end_time = sysdate ,status = " + FlowConstant.FLOW_STATUS_SHUTDOWN
						+ " where project_id = " + MapUtil.getString(paraMap, "project_id"));
				/**
				 * 处理各节点oper_user,oper_status
				 */
				saveService.updateByHSql("update  Tb15_docflow set oper_status ='终止' , oper_user = '"
						+ MapUtil.getString(paraMap, "user_name") + "', doc_status = " + FlowConstant.NODE_STATUS_OFF
						+ " where doc_status != " + FlowConstant.NODE_STATUS_OFF + " and project_id = "
						+ MapUtil.getString(paraMap, "project_id"));
				saveService.updateByHSql("update Tb12_opernode tb12 set node_status = " + FlowConstant.NODE_STATUS_OFF
						+ " where node_status != " + FlowConstant.NODE_STATUS_OFF + " and project_id = "
						+ MapUtil.getString(paraMap, "project_id"));

				/**
				 * 获得起草节点的tb12_id
				 */
				Tb12_opernode firstTb12;
				Tb15_docflow firstTb15 ;
				hsql.delete(0, hsql.length());
				hsql.append("from Tb12_opernode tb12 ");
				hsql.append(" where tb12.doc_id = ? and tb12.module_id =?  order by id");
				tmpList = queryService.searchList(hsql.toString(),new Object[]{MapUtil.getLong(paraMap, "doc_id"),MapUtil.getLong(paraMap, "module_id")});
				if(tmpList.size() > 0){
					firstTb12 = (Tb12_opernode)tmpList.get(0);
					firstTb12.setNode_status(FlowConstant.NODE_STATUS_OFF);
					saveService.save(firstTb12);
					tmpList.clear();

					saveService.updateByHSql("update  Tb15_docflow set oper_status ='终止' , oper_user = '"
							+ MapUtil.getString(paraMap, "user_name") + "' ,doc_status = " + FlowConstant.NODE_STATUS_OFF 
							+ " , approve_result ="  + FlowConstant.CHECK_RESULT_NO 
							+ " where  opernode_id = " + firstTb12.getId());
	
				}
		
			} else{
				/**
				 * 子流程类别 不为FLOW_TYPE_ONLY_ARCHIVE; 
				 */		
				/**
				 * 处理各节点oper_user,oper_status
				 */
				saveService.updateByHSql("update  Tb15_docflow set oper_status ='终止' , oper_user = '"
						+ MapUtil.getString(paraMap, "user_name") + "' ,doc_status = " + FlowConstant.NODE_STATUS_OFF 
						+ " where doc_status != " + FlowConstant.NODE_STATUS_OFF + " and doc_id = "
						+ MapUtil.getString(paraMap, "doc_id") + " and module_id = " + MapUtil.getString(paraMap, "module_id"));
				saveService.updateByHSql("update Tb12_opernode tb12 set node_status = " + FlowConstant.NODE_STATUS_OFF
						+ " where node_status != " + FlowConstant.NODE_STATUS_OFF + " and doc_id = "
						+ MapUtil.getString(paraMap, "doc_id") + " and module_id = " + MapUtil.getString(paraMap, "module_id"));

				/**
				 * 获得起草节点的tb12_id
				 */
				Tb12_opernode firstTb12;
				Tb15_docflow firstTb15 ;
				hsql.delete(0, hsql.length());
				hsql.append("from Tb12_opernode tb12");
				hsql.append(" where tb12.doc_id = ? and tb12.module_id =? order by id");
				tmpList = queryService.searchList(hsql.toString(),new Object[]{MapUtil.getLong(paraMap, "doc_id"),MapUtil.getLong(paraMap, "module_id")});
				if(tmpList.size() > 0){
					firstTb12 = (Tb12_opernode)tmpList.get(0);
					firstTb12.setNode_status(FlowConstant.NODE_STATUS_OFF);
					saveService.save(firstTb12);
					tmpList.clear();

					hsql.delete(0, hsql.length());
					saveService.updateByHSql("update  Tb15_docflow set oper_status ='终止' , oper_user = '"
							+ MapUtil.getString(paraMap, "user_name") + "' ,doc_status = " + FlowConstant.NODE_STATUS_OFF 
							+ " , approve_result ="  + FlowConstant.CHECK_RESULT_NO 
							+ " where  opernode_id = " + firstTb12.getId());
				}
			}
			
			//处理完毕，返回
			returnView = FlowConstant.VIEW_CLOSE;
			return returnView;
		}

		/**
		 * 暂缓:挂起流程
		 */
		if (check_result.equals(FlowConstant.CHECK_RESULT_NO)
				|| check_result.equals(FlowConstant.CHECK_RESULT_DEFER)) {
			saveService.updateByHSql("update Tb11_operflow set end_time = sysdate , status = " + FlowConstant.FLOW_STATUS_SUSPEND
					+ " where project_id = " + MapUtil.getString(paraMap, "project_id"));
			returnView = FlowConstant.VIEW_CLOSE;
		}
		
		//****************************************会签处理****************************************************/
		/**
		 * 会签处理
		 * <br> 参与会审的所有节点会审都办结了算会结，办结当前节点，上一节点调整至在办中
		 * <br> 如果没有会结，则只处理本人对应tb15_docflow 。
		 */
		if (FlowConstant.NODE_TYPE_MORE_CHECK.equals(node_type)) {

			// 办结当前节点,先得到tb15当前节点对应记录，后在更新。
			boolean isHj = false; // 是否会结

			queryBuilder = new HibernateQueryBuilder(Tb15_docflow.class);
			queryBuilder.eq("user_id", MapUtil.getLong(paraMap, "user_id"));
			queryBuilder.eq("opernode_id", tb12_id);
			tmpList = queryService.searchList(queryBuilder);
			if (tmpList.size() > 0) {
				tb15 = (Tb15_docflow) tmpList.get(0);
			}
			tmpList.clear();
			if (tb15 != null) {
				tb15.setDoc_status(FlowConstant.NODE_STATUS_OFF);
				tb15.setApprove_result(check_result);
				tb15.setOper_time(new Date());
				tb15.setOper_user(MapUtil.getString(paraMap, "user_name"));
				tb15.setOper_status("办结");
				tb15.setWrite_limit(new Integer(0));
				// 保存tb15
				saveService.save(tb15);
			}

			// 查看是否所有人员都办结，如果办结。
			hsql.delete(0, hsql.length());
			hsql.append("select 'x' from  Tb15_docflow tb15 where tb15.opernode_id = ? and approve_result is  null");
			tmpList = queryService.searchList(hsql.toString(), new Object[] { tb12_id });
			isHj = tmpList.size() == 0;
			tmpList.clear();

			// 如果会结：1、 置当前节点 tb12状态办结；2、上一节点状态
			if (isHj) {
				// 办结tb12
				tb12 = (Tb12_opernode) queryService.searchById(Tb12_opernode.class, tb12_id);
				if (tb12 != null) {
					tb12.setEnd_time(new Date());
					tb12.setNode_status(FlowConstant.NODE_STATUS_OFF);
				}
				saveService.save(tb12);

				// 获得上一节点tb12_ID
				Long preTb12_id = null;
				hsql.delete(0, hsql.length());
				hsql.append("select source_id from  Tb13_operrelation tb13 where dest_id = ? ");
				tmpList = queryService.searchList(hsql.toString(), new Object[] { tb12_id });
				if (tmpList.size() > 0) {
					preTb12_id = convertUtil.toLong(tmpList.get(0));
				}
				tmpList.clear();

				// 更新上一节点tb12至在办状态。
				tb12 = (Tb12_opernode) queryService.searchById(Tb12_opernode.class, preTb12_id);
				if (tb12 != null) {
					tb12.setNode_status(FlowConstant.NODE_STATUS_WORK);
					saveService.save(tb12);
				}
				// 更新上一节点tb15 至在办状态。
				queryBuilder = new HibernateQueryBuilder(Tb15_docflow.class);
				queryBuilder.eq("opernode_id", preTb12_id);
				tmpList = queryService.searchList(queryBuilder);
				if (tmpList.size() > 0) {
					tb15 = (Tb15_docflow) tmpList.get(0);
					tb15.setDoc_status(FlowConstant.NODE_STATUS_WORK);
					tb15.setOper_status("会结");
					tb15.setOper_user(node_name);
					saveService.save(tb15);
				}
			}
		}
		// ****************************************非审结处理****************************************************/
		/**
		 * 如果审批意见为修改，回退文档 <br>
		 * 意见为暂缓，把本项目转，转至不具条件中去。 <br>
		 * 意见为同意，或默认接收，逐一向上审结。
		 */
		else {
			// 处理tb15
			queryBuilder = new HibernateQueryBuilder(Tb15_docflow.class);
			queryBuilder.eq("user_id", MapUtil.getLong(paraMap, "user_id"));
			queryBuilder.eq("opernode_id", tb12_id);
			tmpList = queryService.searchList(queryBuilder);
			if (tmpList.size() > 0) {
				tb15 = (Tb15_docflow) tmpList.get(0);
			}
			tmpList.clear();
			if (tb15 != null) {
				tb15.setApprove_result(check_result);
				tb15.setOper_time(new Date());
				tb15.setOper_user(MapUtil.getString(paraMap, "user_name"));
				tb15.setOper_status("在办");
				tb15.setWrite_limit(new Integer(1));
				// 保存tb15
				saveService.save(tb15);
			}

			// ************************调用触发器********************************/
			paraMap.put("even_id", node_id);
			paraMap.put("even_type", FlowConstant.EVEN_TYPE_APPROVECOMPLETE);
			paraMap.put("approve_result", tb15.getApprove_result());

			if (doTrigger != null) {
				doTrigger.findAndExcute(paraMap);
			}

			// ***********************自动办结处理*********************************/
			if (archive != null) {
				if (archive.autoArchive(paraMap)) {// 如果初自动办结，返回关闭
					returnView = FlowConstant.VIEW_CLOSE;
				}
			}

			// ***********************循环处理上一节点*******************************/
			if (tb03 != null && FlowConstant.SEND_TYPE_INFO.equals(tb03.getRelation_type())) {
				return FlowConstant.VIEW_CLOSE;
			}

			Map forMap = new HashMap();
			MapUtil.load(forMap, MapUtil.getUrl(paraMap, new String[] { "user_id", "user_name", "node_id",
					"opernode_id", "project_id", "doc_id", "module_id" }));
			// 获得本表单开始 min_tb12_id
			hsql.delete(0, hsql.length());
			hsql.append("select min(id) from  Tb12_opernode tb12 where module_id = ? and doc_id = ?  ");
			tmpList = queryService.searchList(hsql.toString(), new Object[] { module_id,
					MapUtil.getLong(paraMap, "doc_id") });
			Long min_tb12_id = (Long) tmpList.get(0);
			tmpList.clear();
			do {
				hsql.delete(0, hsql.length());
				hsql
						.append("select tb12 from  Tb12_opernode tb12,Tb13_operrelation tb13 where  tb12.id = tb13.source_id and tb13.dest_id = ?");
				tmpList = queryService.searchList(hsql.toString(), new Object[] { tb12_id });
				if (tmpList.size() > 0) {
					tb12 = (Tb12_opernode) tmpList.get(0);
					if (tb12.getId() < min_tb12_id) {// 回退到本表单最小ID
						break;
					}
					if (!FlowConstant.NODE_STATUS_SEND.equals(tb12.getNode_status())) {// 如果上一节点不是发送状态，继续
						tb12_id = tb12.getId();
						continue;
					}
				} else {
					break;
				}
				tmpList.clear();

				// 获得tb15;
				hsql.delete(0, hsql.length());
				hsql.append("from  Tb15_docflow where  opernode_id = ?");
				tmpList = queryService.searchList(hsql.toString(), new Object[] { tb12.getId() });
				if (tmpList.size() > 0) {
					tb15 = (Tb15_docflow) tmpList.get(0);
				}

				// 更新tb12状态
				tb12.setNode_status(check_result);
				saveService.save(tb12);
				// 更新tb15状态
				tb15.setDoc_status(check_result);
				tb15.setOper_time(new Date());
				tb15.setApprove_result(check_result);
				tb15.setOper_status(oper_status);
				tb15.setOper_user(MapUtil.getString(paraMap, "user_name"));
				saveService.save(tb15);

				// ************************调用触发器********************************/
				forMap.put("user_id", tb15.getUser_id());
				forMap.put("approve_result", tb15.getApprove_result());
				forMap.put("opernode_id", tb12.getId());
				forMap.put("node_id", tb12.getNode_id());
				forMap.put("even_id", tb12.getNode_id());
				forMap.put("even_type",  FlowConstant.EVEN_TYPE_APPROVECOMPLETE);
						
				if (doTrigger != null) {
					doTrigger.findAndExcute(forMap);
				}

				// ************************自动办结*********************************/
				if (archive != null) {
					archive.autoArchive(forMap);
				}

				tb12_id = tb12.getId();
			} while (true);

			/**
			 * 回退处理：当本节点审批意见为修改时，回退
			 */
			if (check_result.equals(FlowConstant.CHECK_RESULT_MODIFY)) {
				returnView = FlowConstant.VIEW_TRUNBACK;
			}
		}
		
		// 打开表单 或关闭
		return returnView;
	}

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
	 * @param doTrigger The doTrigger to set.
	 */
	public void setDoTrigger(DoTrigger doTrigger) {
		this.doTrigger = doTrigger;
	}

	/**
	 * @param archive The archive to set.
	 */
	public void setArchive(Archive archive) {
		this.archive = archive;
	}

}
