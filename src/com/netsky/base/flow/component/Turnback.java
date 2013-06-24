package com.netsky.base.flow.component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Tb02_node;
import com.netsky.base.dataObjects.Tb12_opernode;
import com.netsky.base.dataObjects.Tb13_operrelation;
import com.netsky.base.dataObjects.Tb15_docflow;
import com.netsky.base.flow.FlowConstant;
import com.netsky.base.flow.trigger.DoTrigger;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

/**
 * @description:
 *  回退文档
 * @class name:com.netsky.base.flow.component.Turnback
 * @author wind Jan 20, 2010
 */
@Component
public class Turnback {
	/**
	 * 数据对应操作类
	 */
	@Autowired
	private SaveService saveService;

	@Autowired
	private QueryService queryService;

	@Autowired
	private BaseUtil baseUtil;
	
	/**
	 * 触发器
	 */
	@Autowired(required = false)
	private DoTrigger doTrigger;

	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @param paraMap
	 *  必含有：rollto_nodes，opernode_id
	 * @return			
	 * 回退处理：当本节点审批意见为修改时，回退
	 * <br>如果配置多个回退节点，转到 回退节点 选择页面
	 * <br>如果没有配置，或配置一个节点，转至回退处理程序
	 * @throws Exception ModelAndView
	 */
	
	public ModelAndView handleRequest(Map paraMap) throws Exception {
		ModelMap modelMap = new ModelMap();

		Tb13_operrelation tb13= null;//发送relation
		Tb12_opernode tb12 = null; //节点信息
		Tb15_docflow his_tb15 = null; //节点信息
		Tb15_docflow new_tb15 = null; //节点信息
		Tb12_opernode new_tb12 = null; //节点信息
		Tb15_docflow tb15 = null;  //文档节点信息		
		Tb02_node tb02 = null; //节点信息，在此主要用于获得flow_id
		
		String rollto_nodes = null;
		
		StringBuffer hsql = new StringBuffer();
		List tmpList = new LinkedList();
		
		//**********************************************数据准备************************************************/
		//从map中获取rollto_nodes
		rollto_nodes = MapUtil.getString(paraMap,"rollto_nodes");
		//获得当前节点对应的tb12
		tb12 = (Tb12_opernode)queryService.searchById(Tb12_opernode.class, MapUtil.getLong(paraMap, "opernode_id"));
		if(tb12 == null){
			modelMap.put("warnMessage ", "操作失败，参数错误找不到对应的Tb12!");
			return new ModelAndView(FlowConstant.CTR_OPENFORM);
		} else{
			//获得tb02
			tb02 = (Tb02_node)queryService.searchById(Tb02_node.class, tb12.getNode_id());
			modelMap.put("flow_id",tb02.getFlow_id());
		}
		
		
		//如果回退节点参数未传过来，设置为配置节点
		rollto_nodes = "".equals(rollto_nodes)?tb12.getRollback_nodes():rollto_nodes;
		
		//如果回退节点参数未传过来，且当前节点未配置回退节点，则回退至本表单的起草节点
		if(rollto_nodes == null ){
			hsql.append(" select node_id from Tb12_opernode where module_id = ? and doc_id = ? order by id");
			tmpList = queryService.searchList(hsql.toString(), new Object[]{tb12.getModule_id(),tb12.getDoc_id()});
			if(tmpList.size()> 0){
				rollto_nodes = convertUtil.toString(tmpList.get(0));
			}
		}
		
		//********************************************回退处理*************************************************/
		//单一回退节点
		if (rollto_nodes.split(",").length == 1) {
			//*******************************处理回退目标节点*********************************/
			Session session = null; // 事务相关
			Transaction tx = null;// 事务相关
			try {
				session = saveService.getHiberbateSession();
				tx = session.beginTransaction();
				tx.begin(); // 开始事务

				/**
				 * 插入Tb12_opernode,tb15
				 */
				// 获得回退节点对应的tb02
				Tb02_node rollto_tb02 = (Tb02_node) queryService.searchById(Tb02_node.class, convertUtil.toLong(rollto_nodes));

				// 获得回退节点对应的tb15，id最大的
				hsql.delete(0, hsql.length());
				hsql.append(" from Tb15_docflow where node_id = ? and project_id = ? order by id desc");
				tmpList = queryService.searchList(hsql.toString(), new Object[] { convertUtil.toLong(rollto_nodes),
						tb12.getProject_id() });
				if (tmpList.size() > 0) {
					his_tb15 = (Tb15_docflow) tmpList.get(0);
				} 
				
				if(his_tb15.getHuser_id().equals(-1L)){
					modelMap.put("warnMessage", "回退失败，请稍后重试！");
					return new ModelAndView(FlowConstant.CTR_OPENFORM,modelMap);
				}
				
				// 插入Tb12
				new_tb12 = new Tb12_opernode();
				new_tb12.setNode_id(his_tb15.getNode_id());
				new_tb12.setNode_name(rollto_tb02.getName());
				new_tb12.setNode_type(rollto_tb02.getNode_type());
				new_tb12.setRollback_nodes(rollto_tb02.getRollback_nodes());
				new_tb12.setDoc_id(his_tb15.getDoc_id());
				new_tb12.setProject_id(his_tb15.getProject_id());
				new_tb12.setModule_id(his_tb15.getModule_id());
				new_tb12.setNode_status(FlowConstant.NODE_STATUS_NEED);
				new_tb12.setStart_time(new Date());
				new_tb12.setNode_ext(rollto_tb02.getExt());
				if(!new_tb12.getModule_id().equals(tb12.getModule_id()))
				new_tb12.setCflow_root("1");
				session.saveOrUpdate(new_tb12);
				
				// 插入tb15
				new_tb15 = new Tb15_docflow();
				new_tb15.setDoc_id(his_tb15.getDoc_id());
				new_tb15.setProject_id(his_tb15.getProject_id());
				new_tb15.setModule_id(his_tb15.getModule_id());
				new_tb15.setNode_id(his_tb15.getNode_id());
				new_tb15.setWrite_limit(new Integer(0));
				new_tb15.setDoc_status(FlowConstant.NODE_STATUS_NEED);
				new_tb15.setOpernode_id(new_tb12.getId());
				new_tb15.setOper_user(MapUtil.getString(paraMap, "user_name"));
				new_tb15.setOper_status("回退");

				// 判断历史节点处理人员是否还有权限  
				//暂且不判断，如果岗位调整，且没有做岗位交接，那么回退还应回到历史节点处理人
//				hsql.delete(0, hsql.length());
//				hsql.append("select 'x' from Ta11_sta_user ta11,Ta13_sta_node ta13");
//				hsql.append(" where ta11.station_id = ta13.station_id and ta13.node_id = ?  and ta11.user_id = ?");
//				tmpList = queryService.searchList(hsql.toString(), new Object[] { his_tb15.getNode_id(), his_tb15.getHuser_id() });
//				if (tmpList.size() > 0) {
//					new_tb15.setGroup_id(his_tb15.getGroup_id());
//					new_tb15.setUser_id(his_tb15.getHuser_id());
//					new_tb15.setHuser_id(his_tb15.getHuser_id());
//				} else {
//					new_tb15.setGroup_id(his_tb15.getGroup_id());
//					new_tb15.setUser_id(new Long(-1));
//					new_tb15.setHuser_id(new Long(-1));
//				}
				
				new_tb15.setGroup_id(his_tb15.getGroup_id());
				new_tb15.setUser_id(his_tb15.getHuser_id());
				new_tb15.setHuser_id(his_tb15.getHuser_id());

				session.saveOrUpdate(new_tb15);

				// ********************************处理当前节点状态**********************************/
				/**
				 * 当前节点处理成办结
				 */
				// 更新tb12
				tb12.setEnd_time(new Date());
				tb12.setNode_status(FlowConstant.NODE_STATUS_OFF);
				session.saveOrUpdate(tb12);
				// 更新tb15
				hsql.delete(0, hsql.length());
				hsql.append(" from Tb15_docflow tb15 where opernode_id = ? and user_id = ?");
				tmpList = queryService.searchList(hsql.toString(),
						new Object[] { tb12.getId(), MapUtil.getLong(paraMap, "user_id") });
				if (tmpList.size() > 0) {
					tb15 = (Tb15_docflow) tmpList.get(0);
					tb15.setAccept_time(new Date());
					tb15.setOper_status("回退");
					tb15.setOper_user(MapUtil.getString(paraMap, "user_name"));
					tb15.setDoc_status(FlowConstant.NODE_STATUS_OFF);
					session.saveOrUpdate(tb15);
				}
				tmpList.clear();

				// 如果回退至上一表单，需记录前表单。
				if (!his_tb15.getModule_id().equals(tb15.getModule_id())) {
					hsql.delete(0, hsql.length());
					hsql.append("update Tb11_operflow tb11 set tb11.rollback_modules = '");
					hsql.append(tb15.getModule_id());
					hsql.append("' where project_id = ");
					hsql.append(tb15.getProject_id());
					saveService.updateByHSql(hsql.toString());
				}

				// *******************************处理回退发送************************************/
				/**
				 * 插入tb13
				 */
				tb13 = new Tb13_operrelation();
				tb13.setSource_id(MapUtil.getLong(paraMap, "opernode_id"));
				tb13.setDest_id(new_tb12.getId());
				tb13.setDoc_id(new_tb12.getDoc_id());
				tb13.setModule_id(new_tb12.getModule_id());
				tb13.setProject_id(new_tb12.getProject_id());
				tb13.setOper_time(new Date());
				tb13.setRelation_name("回退");
				tb13.setSeq(new Integer(0));
				session.saveOrUpdate(tb13);

				session.flush();
				tx.commit(); // 提交事务;
			} catch (Exception e) {
				if (tx != null)
					tx.rollback(); // 回滚事务;
				log.error("文档回退处理失败", e);
				throw new Exception("文档回退处理失败", e);
			}finally{
				if(session != null){
					session.close();
				}
			}
			
			/**
			 * 提取发送信息
			 */
			StringBuffer warnMessage = new StringBuffer();
			if(new_tb15 != null  ){
				Ta06_module ta06 = (Ta06_module)queryService.searchById(Ta06_module.class, new_tb15.getModule_id());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				StringBuffer toUserNames = new StringBuffer();
				if(new_tb15.getUser_id() != -1){
					Ta03_user to_user = (Ta03_user)queryService.searchById(Ta03_user.class, new_tb15.getUser_id());
					toUserNames.append(",[" + to_user.getLogin_id()+"]");
					toUserNames.append(to_user.getName());
				} else {
					List<Ta03_user> userList ;
					if(new_tb15.getGroup_id() != null && new_tb15.getGroup_id() != -1)
						userList = baseUtil.getUser(new_tb15.getNode_id(), new Long[]{new_tb15.getGroup_id()});
					else 
						userList = baseUtil.getUser(new_tb15.getNode_id());
					for(Ta03_user user:userList){
						toUserNames.append(",");
						toUserNames.append("[" + user.getLogin_id()+"]");
						toUserNames.append(user.getName());
					}
				}
				
				
				if(ta06 != null){
					warnMessage.append(ta06.getName());
				}
				warnMessage.append("发送成功!");
				warnMessage.append("      发送时间：");
				warnMessage.append(sdf.format(new Date()));
				if(toUserNames.length() > 0){
					toUserNames.deleteCharAt(0);
					warnMessage.append("      受理人员：");
					warnMessage.append(toUserNames.toString());
				}
				
			} else {
				warnMessage.append("操作成功!");
			}
			
			modelMap.put("warnMessage", warnMessage);
			
			
			return new ModelAndView(FlowConstant.CTR_OPENFORM,modelMap);
		}
		//多个回退节点	，
		else {
			modelMap.put("rollto_nodes", rollto_nodes);
			return new ModelAndView(FlowConstant.VIEW_TRUNBACK,modelMap);
		}
	}

	public void setSaveService(SaveService saveService) {
		this.saveService = saveService;
	}

	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	public void setBaseUtil(BaseUtil baseUtil) {
		this.baseUtil = baseUtil;
	}

	public void setDoTrigger(DoTrigger doTrigger) {
		this.doTrigger = doTrigger;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

	
}
