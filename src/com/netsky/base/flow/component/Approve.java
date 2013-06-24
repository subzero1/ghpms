package com.netsky.base.flow.component;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.dataObjects.Ta02_station;
import com.netsky.base.dataObjects.Tb12_opernode;
import com.netsky.base.dataObjects.Tb15_docflow;
import com.netsky.base.dataObjects.Tb17_approve;
import com.netsky.base.flow.FlowConstant;
import com.netsky.base.flow.trigger.DoTrigger;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

/**
 * @description: 审批处理
 * @class name:com.netsky.base.flow.component.Approve
 * @author wind Jan 27, 2010
 */
@Component
public class Approve {
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
	
	/**
	 * 日志处理类
	 */
	private  Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 
	 * @param paraMap
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	public ModelAndView handleRequest(Map paraMap) throws Exception {
		StringBuffer hsql = new StringBuffer();
		ModelMap modelMap = new ModelMap();
		List<?> tmpList	=null;
		Tb17_approve tb17 = null;
		Tb12_opernode tb12 = null;
		Tb15_docflow tb15 = null;
		String node_ext = "";
		String default_idear = "同意";
		// ***************************初始化数据**************************************/
		hsql.append(" from  Tb17_approve tb17");
		hsql.append(" where  tb17.user_id = ? and tb17.opernode_id = ? and tb17.check_result != ? order by tb17.id desc ");
		tmpList = queryService.searchList(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "user_id"),
				MapUtil.getLong(paraMap, "opernode_id"),FlowConstant.CHECK_RESULT_BJTJ });
		if(tmpList.size() > 0){
			tb17 = (Tb17_approve)tmpList.get(0);
		} 
		
		tb12 = (Tb12_opernode)queryService.searchById(Tb12_opernode.class, MapUtil.getLong(paraMap, "opernode_id"));
		if(tb12 != null && tb12.getNode_ext() != null){
			node_ext = tb12.getNode_ext();
		}
		// ***************************转到审批页面***********************************/
		/**
		 * 如果参数中未包含 check_result 转到审批页面
		 */
		if (!paraMap.containsKey("check_result")) {
			//不同节点不同类别的意见
			Map<Integer,String>  checkType= new HashMap<Integer,String>();
			List checkTypeList = new LinkedList();
			if(node_ext.indexOf(FlowConstant.NODE_EXT_CY)!= -1){				
				checkTypeList.add(FlowConstant.CHECK_RESULT_OK);
				checkType.put(FlowConstant.CHECK_RESULT_OK, "已阅");
				default_idear = "已阅";
			} else {
				checkTypeList.add(FlowConstant.CHECK_RESULT_OK);
				checkType.put(FlowConstant.CHECK_RESULT_OK, "同意");
				
				checkTypeList.add(FlowConstant.CHECK_RESULT_MODIFY);
				checkType.put(FlowConstant.CHECK_RESULT_MODIFY, "修改后再报");
			}
			
			if(node_ext.indexOf(FlowConstant.NODE_EXT_BJTJ)!= -1){
				checkType.put(FlowConstant.CHECK_RESULT_BJTJ, "不具条件");
				checkTypeList.add(FlowConstant.CHECK_RESULT_BJTJ);
			} 
			if(node_ext.indexOf(FlowConstant.NODE_EXT_SUSPEND)!= -1){
				checkType.put(FlowConstant.CHECK_RESULT_NO, "不同意");
				checkTypeList.add(FlowConstant.CHECK_RESULT_NO);
			}
			
			modelMap.put("default_idear", default_idear);
			modelMap.put("checkType", checkType);
			modelMap.put("checkTypeList", checkTypeList);
			//上次审批信息
			if(tb17 != null){
				modelMap.put("tb17", tb17);
			}
			//当前日期，审批时间默认值
			modelMap.put("curDate", new Date());
			//用户自定义意见
			hsql.delete(0,hsql.length());
			hsql.append(" from Ta22_user_idea ta22 where  ta22.user_id = ?  order by ta22.check_result,ta22.id ");
			tmpList = queryService.searchList(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "user_id")});
			modelMap.put("ideaList", tmpList);
			modelMap.put("user", paraMap.get("user"));
			return new ModelAndView(FlowConstant.VIEW_APPROVE,modelMap);
		}

		// ***************************审批处理***********************************/
		Session session = null; // 事务相关
		Transaction tx = null;// 事务相关
		try {
			session = saveService.getHiberbateSession();
			tx = session.beginTransaction();
			tx.begin(); // 开始事务
			
			//获取已存在的审批意见。
			hsql.delete(0, hsql.length());
			hsql.append(" from  Tb17_approve tb17");
			hsql.append(" where  tb17.user_id = ? and tb17.opernode_id = ? and tb17.check_result != ? order by tb17.id desc ");
			tmpList = queryService.searchList(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "user_id"),
					MapUtil.getLong(paraMap, "opernode_id"),FlowConstant.CHECK_RESULT_BJTJ });
			if(tmpList.size() > 0){
				tb17 = (Tb17_approve)tmpList.get(0);
			}		
			
			// 取得审批岗位
			hsql.delete(0, hsql.length());
			hsql.append("select ta02 from Ta02_station ta02,Ta11_sta_user ta11,Ta13_sta_node ta13 ");
			hsql.append(" where ta02.id =  ta11.station_id  and ta11.station_id= ta13.station_id ");
			hsql.append(" and ta11.user_id = ? and ta13.node_id = ? order by ta02.id ");
			tmpList = queryService.searchList(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "user_id"),
					MapUtil.getLong(paraMap, "node_id") });
			Ta02_station ta02 = null;
			if (tmpList.size() > 0) {
				ta02 = (Ta02_station) tmpList.get(0);
			}

			Integer check_result = MapUtil.getInteger(paraMap, "check_result");
			String result_str = "";
			if (check_result.equals(FlowConstant.CHECK_RESULT_DEFER)) {
				result_str = "暂缓";
			} else if (check_result.equals(FlowConstant.CHECK_RESULT_MODIFY)) {
				result_str = "修改";
			} else if (check_result.equals(FlowConstant.CHECK_RESULT_OK)) {
				result_str = "同意"; 
				result_str = default_idear;
			} else if (check_result.equals(FlowConstant.CHECK_RESULT_BJTJ)) {
				result_str = "不具条件";
			} else if (check_result.equals(FlowConstant.CHECK_RESULT_NO)) {
				result_str = "不同意(终止)";
			} else {
				log.error("check_result 参数错误:check_result = " + paraMap.get("check_result"));
				result_str = "不发表意见";
			}

			// 获得preTb12_id
			//tb17 = (tb17 == null ? new Tb17_approve() : tb17);
			if(tb17 == null ){
				tb17 = new Tb17_approve() ;
			}
			tb17.setProject_id(MapUtil.getLong(paraMap, "project_id"));
			tb17.setNode_id(MapUtil.getLong(paraMap, "node_id"));
			tb17.setDoc_id(MapUtil.getLong(paraMap, "doc_id"));
			tb17.setModule_id(MapUtil.getLong(paraMap, "module_id"));
			tb17.setOpernode_id(MapUtil.getLong(paraMap, "opernode_id"));
			tb17.setCheck_idea(MapUtil.getString(paraMap, "check_idea"));
			tb17.setCheck_result(check_result);
			tb17.setResult_str(result_str);
			tb17.setOper_time(new Date());
			tb17.setUser_id(MapUtil.getLong(paraMap, "user_id"));
			tb17.setUser_name(MapUtil.getString(paraMap, "user_name"));
			tb17.setStation(ta02 == null ? "" : ta02.getName());
			session.saveOrUpdate(tb17);

			// 更新TB15信息 处理不具条件审批
			hsql.delete(0, hsql.length());
			hsql.append(" from  Tb15_docflow where user_id = ? and opernode_id = ? order by id desc");
			tmpList = queryService.searchList(hsql.toString(), new Object[] { tb17.getUser_id(), tb17.getOpernode_id() });
			if (tmpList.size() > 0) {
				tb15 = (Tb15_docflow) tmpList.get(0);
				if (check_result.equals(FlowConstant.CHECK_RESULT_BJTJ)) {
					tb15.setDoc_status(FlowConstant.NODE_STATUS_BJTJ);
					tb15.setOper_user(MapUtil.getString(paraMap, "user_name"));
					tb15.setOper_status("不具条件");
					tb15.setOper_time(new Date());
				} else {
					tb15.setDoc_status(FlowConstant.NODE_STATUS_WORK);
					if ("不具条件".equals(tb15.getOper_status())) {
						tb15.setOper_status("已具条件");
						tb15.setOper_time(new Date());
					}
				}
				session.saveOrUpdate(tb15);
			}

			session.flush();
			tx.commit(); // 提交事务;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback(); // 回滚事务;
			log.error("文档审批出错", e);
			throw e;
		}finally{
			if(session != null){
				session.close();
			}
		}
		
		//************************审批事件要触发 事务	************************/ 
		paraMap.put("check_result", tb17.getResult_str());
		paraMap.put("check_idea", tb17.getCheck_idea());
		paraMap.put("even_id",tb17.getNode_id());
		paraMap.put("even_type", FlowConstant.EVEN_TYPE_APPROVE);
		if(doTrigger != null){
			doTrigger.findAndExcute(paraMap);
		}
		
		modelMap.put("approve_return", "success");
		return new ModelAndView(FlowConstant.VIEW_APPROVE,modelMap);
	}
}
