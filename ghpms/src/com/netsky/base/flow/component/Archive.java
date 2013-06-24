package com.netsky.base.flow.component;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.dataObjects.Tb02_node;
import com.netsky.base.dataObjects.Tb12_opernode;
import com.netsky.base.dataObjects.Tb15_docflow;
import com.netsky.base.flow.buttonControl.Button;
import com.netsky.base.flow.trigger.DoTrigger;
import com.netsky.base.flow.trigger.Trigger;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.flow.FlowConstant;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

/**
 * @description:
 *  办结
 * @class name:com.netsky.base.flow.component.Archive
 * @author wind Jan 20, 2010
 */
@Component
public class Archive {
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
	 * 业务处理类
	 */
	@Autowired
	FlowBussines flowBussines;
	
	/**
	 * 日志处理类
	 */
	private  Logger log = Logger.getLogger(this.getClass());

	/**
	 * 办结文档，并把办结事件转发出去
	 * @param paraMap
	 * @return
	 * @throws Exception ModelAndView
	 */
	public String handleRequest(Map paraMap) throws Exception {
		Long tb12_id = MapUtil.getLong(paraMap, "opernode_id");//tb12_id
		Long pretb12_id = null;
		Long node_id = null;
		String node_name = null;

		StringBuffer hsql = new StringBuffer();
		List tmpList = new LinkedList();
		QueryBuilder queryBuilder;
		
		
		//获得preTb12_id
		hsql.append("select source_id from Tb13_operrelation where dest_id = ?");
		tmpList = queryService.searchList(hsql.toString(), new Object[] { tb12_id });
		if (tmpList.size() > 0) {
			pretb12_id = convertUtil.toLong(tmpList.get(0));
		}
		tmpList.clear();
		
		//************************文档办结处理************************/ 
		Session session = null; //事务相关
		Transaction tx = null;//事务相关
		try {
			session = saveService.getHiberbateSession();
			tx = session.beginTransaction(); 
			tx.begin(); //开始事务
			
			//置当前节点为办结 tb12
			Tb12_opernode tb12 = (Tb12_opernode)queryService.searchById(Tb12_opernode.class, tb12_id);
			if(tb12 != null){
				tb12.setEnd_time(new Date());
				tb12.setNode_status(FlowConstant.NODE_STATUS_OFF);
				session.saveOrUpdate(tb12);
			}
			
			
			// 置当前节点文档状态办结 tb15
			Tb15_docflow tb15 = null;
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
				tb15.setOper_time(new Date());
				tb15.setWrite_limit(new Integer(0));//文档可写标识
				tb15.setOper_user(MapUtil.getString(paraMap, "user_name"));
				tb15.setOper_status("办结");
				
				node_id = tb15.getNode_id();
				session.saveOrUpdate(tb15);
			}
			
			session.flush();
			tx.commit(); // 提交事务;
		} catch (Exception e) {
			if(tx != null) tx.rollback(); // 回滚事务;
			log.error("办结文档出错", e);
			throw e;
		} finally{
			if(session != null){
				session.close();
			}
		}
		
		//************************办结事件要触发 事务	************************/ 
		paraMap.put("even_id",node_id);
		paraMap.put("even_type", FlowConstant.EVEN_TYPE_ARCHIVE);
		if(doTrigger != null){
			doTrigger.findAndExcute(paraMap);
		}
		
		//打开表单
		return FlowConstant.VIEW_CLOSE;
	}
	
	/**
	 * 自动审结
	 * <p>条件:
	 * 1、表单中第一个按扭是”办结“
	 * 2、或第一个按扭是”保存“，第三个按扭是”办结“
	 * @param paraMap
	 * @return
	 * @throws Exception boolean
	 */
	public boolean autoArchive(Map paraMap) throws Exception {
		boolean returnVar = false;
		String buttonName = "办 结";
		String saveButton = "保 存";
		paraMap.remove("hasMustDo");
		List<Button> buttonList =  flowBussines.listButtons(paraMap);
		int i =0;
		for(Button button :buttonList){
			i++;
			if (button.getName().equals(buttonName)&&(i==1 || i ==3)){
				returnVar = true;
				break;
			}
		}
		
		if(returnVar){
			Tb02_node tb02 = (Tb02_node)queryService.searchById(Tb02_node.class, MapUtil.getLong(paraMap,"node_id"));
			if(tb02 != null && tb02.getExt() != null && tb02.getExt().indexOf("[不自动审结]") == -1){
				handleRequest(paraMap);
				return true;
			}
		}
		
		return false;
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
	 * @param flowBussines The flowBussines to set.
	 */
	public void setFlowBussines(FlowBussines flowBussines) {
		this.flowBussines = flowBussines;
	}

}
