package com.netsky.base.flow.component;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.flow.FlowConstant;
import com.netsky.base.flow.buttonControl.Button;
import com.netsky.base.flow.buttonControl.ButtonControl;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

import com.netsky.base.dataObjects.*;

/**
 * @description: 流程对外接口功能类
 * @class name:com.netsky.base.flow.component.FlowBussines
 * @author wind Jan 14, 2010
 */
@Component
public class FlowBussines {

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
	 * 获得流程按扭 <br>
	 * 审批、审结意见：4 同意； 5 修改； 6 不同意 ；7 不发表意见；-1 默认值未审结 <br>
	 * 节点类别：1 审批；2 非审批；3 会签型； <br>
	 * 节点、文档状态：0 待办；1 新建；2 在办；3 发送；4 回复同意； 5 回复修改； 6 回复不同意 ；7 回复不发表意见； 8 办结
	 * method:listButtons
	 * 
	 * @param paraMap
	 *            必含 project_id,doc_id,node_id,user_id
	 * @return List<Button>
	 */
	@SuppressWarnings("unchecked")
	public List<Button> listButtons(Map paraMap)  {
		// ***************变量定义******************************/
		List<Button> buttonList = new LinkedList<Button>();// 返回结果集
		List<Button> baseButtonList = new LinkedList<Button>();// 基础按扭(放到最后)
		List<Button> relationList = null;
		Button m_button;// 按扭变量
		String effacement = MapUtil.getString(paraMap,"effacement"); // 是否有管理权限

		String flowStatus = "-1"; // 流程状态
		
		boolean hasWriteFields = false; // 当前节点对应角色是否是表单可写字段
		Long tb12_id = new Long(-1); // tb12表ID
		Tb12_opernode tb12 = null;
		Tb02_node tb02 = null;  //当前节点信息
		Integer node_type = -1; // 节点类别
		String node_ext = "";//节点扩展信息，主要用来显示按扭的
		Integer doc_status = -1; // 文档状态（与节点状态一至）
		Integer approve_result = null; // 审结意见
		Integer write_limit = -1; // 文档读写权限
		Integer check_result = -1; // 审批意见
		boolean isFormStart = false; //当前是否为本表单的创建节点
		
		/**
		 * 基础按扭,返回时追加到后面
		 */
		m_button = new Button("打 印");
		m_button.comment = "打印当前文档";
		m_button.picUri = "print";
		m_button.url = "javascript:docPrint();";
		baseButtonList.add(m_button);
						
		// ***************检查参数**********************************/
		// 如果没有node_id,user_id返回关闭按扭
		if (paraMap.containsKey("module_id")) {
			;
		} else {
			return baseButtonList;
		}
		 
		//*******************是否为新建表单或管理权限进入，如果是显示下面按扭退出******************************/
		if ((!paraMap.containsKey("doc_id") && paraMap.containsKey("node_id") && paraMap.containsKey("module_id"))
				|| (!paraMap.containsKey("node_id") && paraMap.containsKey("doc_id") && "supressao".equals(effacement))
				|| (paraMap.containsKey("doc_id") && paraMap.containsKey("node_id") && paraMap.containsKey("module_id")
						&& !paraMap.containsKey("save") && paraMap.containsKey("renew"))) {
			m_button = new Button("保 存");
			m_button.comment = "保存当前文档";
			m_button.picUri = "save";
			m_button.url = "javascript:docSave();";
			buttonList.add(m_button);
			
			buttonList.addAll(baseButtonList);
			return buttonList;
		}
		
		// ***************变量赋值**********************/
		// 流程状态
		StringBuffer hsql = new StringBuffer("select status from Tb11_operflow where project_id = ?");
		List tmpList = queryService.searchList(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "project_id") });
		if (tmpList.size() > 0) {
			flowStatus = convertUtil.toString(tmpList.get(0));
		}
		tmpList.clear();
		if(!FlowConstant.FLOW_STATUS_RUN.equals(flowStatus)){
			return baseButtonList;
		}
		
		// 节点是否有可操作的表单字段
		hsql.delete(0, hsql.length());
		hsql.append("select 'x' from Ta16_node_field where node_id =?");
		tmpList = queryService.searchList(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "node_id") });
		hasWriteFields = tmpList.size() > 0;
		tmpList.clear();

		// tb12_id,节点状态，节点类别,表单是否为新建节点
		if (paraMap.containsKey("opernode_id")) {
			tb12 = (Tb12_opernode) queryService.searchById(Tb12_opernode.class, MapUtil.getLong(paraMap, "opernode_id"));
		} else {
			hsql.delete(0, hsql.length());
			hsql.append("  from Tb12_opernode where project_id = ? ");
			hsql.append(" and node_id = ?  order by id desc");
			tmpList = queryService.searchList(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "project_id"),
					MapUtil.getLong(paraMap, "node_id") });
			if (tmpList.size() > 0) {
				tb12 = (Tb12_opernode)tmpList.get(0);
			}
			tmpList.clear();
		}
		if(tb12 != null){
			tb12_id = tb12.getId();
			node_type = tb12.getNode_type();
			node_ext =	tb12.getNode_ext();
			isFormStart = "1".equals(tb12.getCflow_root());
		}
		
		

		// 获得表单状态，审结意见,文档的读写权限
		hsql.delete(0, hsql.length());
		hsql.append(" select tb15 from Tb15_docflow tb15 ");
		hsql.append(" where (tb15.user_id =  ? or tb15.user_id = -1 ) and tb15.opernode_id = ?");
		tmpList = queryService.searchList(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "user_id"), tb12_id });
		if (tmpList.size() > 0) {
			Tb15_docflow tb15 = (Tb15_docflow)tmpList.get(0);
			doc_status = tb15.getDoc_status();
			approve_result = tb15.getApprove_result();
			write_limit =  tb15.getWrite_limit();
		}
		tmpList.clear();

		// 获得审批意见
		hsql.delete(0, hsql.length());
		hsql.append(" select check_result from Tb17_approve where opernode_id = ? and user_id = ? order by id desc ");
		tmpList = queryService.searchList(hsql.toString(), new Object[] { tb12_id, convertUtil.toLong(paraMap.get("user_id")) });
		if (tmpList.size() > 0) {
			check_result = convertUtil.toInteger(tmpList.get(0));
		}
		tmpList.clear();

		// 如果是待得得或回复查看目标节点的节点类型及，受理情况
		Long nextb12_id = new Long(-1);
		boolean isAccepted = false;
		Integer nextnode_type = -1;
		hsql.delete(0, hsql.length());
		hsql.append("select tb13.id,tb13.dest_id,tb12.node_type from Tb13_operrelation tb13,Tb12_opernode tb12");
		hsql.append(" where tb13.dest_id = tb12.id and  source_id = ?  order by tb13.id desc ");
		tmpList = queryService.searchList(hsql.toString(), new Object[] { tb12_id });
		if (tmpList.size() > 0) {
			Object[] row = (Object[]) tmpList.get(0);
			nextb12_id = convertUtil.toLong(row[1]);
			nextnode_type = convertUtil.toInteger(row[2]);
		}
		tmpList.clear();
		
		hsql.delete(0, hsql.length());
		hsql.append("select 'x' from Tb15_docflow where doc_status !=0 and opernode_id = ?");
		tmpList = queryService.searchList(hsql.toString(), new Object[] { nextb12_id });
		isAccepted = tmpList.size() > 0;
		tmpList.clear();
		
		
		// 调用Relation.getRelations 传递的参数
		paraMap.put("approve_result", approve_result);
		paraMap.put("doc_status", doc_status);
		paraMap.put("check_result", check_result);
		paraMap.put("tb12_id", tb12_id);
		paraMap.put("opernode_id", tb12_id);
		paraMap.put("node_type", node_type);
		paraMap.put("isFormStart", isFormStart);

		String urlParas = MapUtil.getUrl(paraMap, new String[] { "project_id", "doc_id", "module_id", "node_id","opernode_id","user_id"});
		// **********************待办文档状态 (0)************************************************/
		if (doc_status.equals(FlowConstant.NODE_STATUS_NEED)) {
			m_button = new Button("受 理");
			m_button.comment = "受理当前文档到在办中";//受理当前文档，并承接当前文档所承载任务
			m_button.picUri = "accept";
			m_button.url = "javascript:docAccept('accept.do?doType=do&"
					+ urlParas + "')";
			buttonList.add(m_button);
			
			paraMap.put("hasMustDo", "yes");
		}
		// **********************在办文档状态 (1-2))***********************************************/
		else if (doc_status.equals(FlowConstant.NODE_STATUS_NEW)||doc_status.equals(FlowConstant.NODE_STATUS_WORK)) {
			/**
			 * 保存按扭列示条件
			 * 1、在办
			 * 2、已审批同意或默认接收
			 * 3、表单中本节点有可写字段
			 * 4、本节点为非会签节点
			 */
			if ((FlowConstant.CHECK_RESULT_OK.equals(check_result)||isFormStart)
					&& write_limit == 1 && hasWriteFields //有写字段的权限
					&& (node_type.equals(FlowConstant.NODE_TYPE_CHECK)
					|| node_type.equals(FlowConstant.NODE_TYPE_NO_CHECK))) {
				m_button = new Button("保 存");
				m_button.comment = "保存当前文档";
				m_button.picUri = "save";
				m_button.url = "javascript:docSave();";
				buttonList.add(m_button);
				
				m_button = new Button("附 件");
				m_button.comment = "上传附件，上传后将出现当前文档下面的附件区";
				m_button.picUri = "attach";
				m_button.url = "javascript:docSlave('slave.do?" +urlParas +"');";
				buttonList.add(m_button);				
			}
			
			/**
			 * 删除按扭列示条件
			 * 1、状态新建
			 * 2、本表单没有流转,当前节点为表单的起草节点
			 */
			hsql.delete(0, hsql.length());
			hsql.append(" from Tb12_opernode where module_id = ? and doc_id = ? and cflow_root = 1 order by id");
			tmpList = queryService.searchList(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "module_id"), MapUtil.getLong(paraMap, "doc_id")});
			if(tmpList.size() > 0){
				Tb12_opernode tb12_root = (Tb12_opernode)tmpList.get(0);
				if(tb12_root.getNode_id().equals(MapUtil.getLong(paraMap, "node_id"))){
					m_button = new Button("删 除");
					m_button.comment = "删除表单：如果是工程的第一张工单删除工程，否则只删除表单且起草表单的节点设置为在办";
					m_button.picUri = "delete";
					m_button.url = "javascript:docDelete('deleteForm.do?" +MapUtil.getUrl(paraMap, new String[] { "project_id", "doc_id", "module_id", "node_id","user_id"}) +"&opernode_id=" + tb12_root.getId() +"');";
					buttonList.add(m_button);
				}
			}

			
			// 如果为审批类节点，如果没有审结，按扭为”取消受理“”审批“”审结“
			if (FlowConstant.NODE_TYPE_CHECK.equals(node_type)) {
				if(approve_result == null && !isFormStart){
					m_button = new Button("取消受理");
					m_button.url = "javascript:docAccept('accept.do?doType=cancel&"
						+ urlParas + "')";
					m_button.comment = "取消受理，发送节点可以收回，同类节点可以受理；取消受理后，本人的审批意见系统会删除";
					m_button.picUri = "cancelaccept";
					buttonList.add(m_button);

					m_button = new Button("审 批");
					m_button.comment = "对此文档发表自己的意见";
					m_button.url = "javascript:docApprove('approve.do?"+ urlParas+"');";
					m_button.picUri = "examine";
					buttonList.add(m_button);
					if(check_result != -1 && check_result != null){
						m_button = new Button("审 结");
						m_button.comment = "确认所发表意见，审结后审批意见不能修改，审结前可以点审批修改自己所发表意见";
						m_button.url = "javascript:docApproveComplete('approveComplete.do?" + urlParas + "');";
						m_button.picUri = "concluded";
						buttonList.add(m_button);
					}
					paraMap.put("hasMustDo", "yes");
				}

			}
			// 非审批类节点 ”转发“，如果没有其它操作加”办结“按扭
			else if (FlowConstant.NODE_TYPE_NO_CHECK.equals(node_type)) {
				m_button = new Button("回 退");
				m_button.url = "javascript:docTurnback('turnback.do?"+urlParas+"');";
				m_button.comment = "回退流程至需修改节点";
				m_button.picUri = "rollback";
				buttonList.add(m_button);
				paraMap.put("hasMustDo", "yes");
				/*
				m_button = new Button("转 发");
				m_button.comment = "对此文档发表自己的意见";
				m_button.url = "javascript:docHandOver('handOver.do?"+urlParas+"');";
				m_button.picUri = "send";
				buttonList.add(m_button);
				*/
			}
			// 会签型:”审批“,“审结”，”办结“
			else {
				if(approve_result == null ){
					m_button = new Button("审 批");
					m_button.url = "javascript:docApprove('approve.do?"+ urlParas+"');";
					m_button.comment = "对此文档发表自己的意见";
					m_button.picUri = "examine";
					buttonList.add(m_button);

					if(check_result != null && check_result != -1){
						m_button = new Button("审 结");
						m_button.comment = "确认所发表意见，审结后审批意见不能修改，审结前可以点审批修改自己所发表意见";
						m_button.url = "javascript:docApproveComplete('approveComplete.do?"+ urlParas+"');";
						m_button.picUri = "concluded";
						buttonList.add(m_button);
					}
					paraMap.put("hasMustDo", "yes");
				}
			}
			
			//只有审批后才可能继续
			if(FlowConstant.CHECK_RESULT_OK.equals(check_result)||isFormStart||FlowConstant.NODE_TYPE_NO_CHECK.equals(node_type)){
				relationList = listRelation(paraMap);
				if(relationList.size() > 0){
					buttonList.addAll(relationList);
				}
			} else 	if(FlowConstant.CHECK_RESULT_MODIFY.equals(approve_result) && FlowConstant.NODE_TYPE_CHECK.equals(node_type)){
				m_button = new Button("回 退");
				m_button.url = "javascript:docTurnback('turnback.do?"+urlParas+"');";
				m_button.comment = "回退流程至需修改节点";
				m_button.picUri = "rollback";
				buttonList.add(m_button);
				paraMap.put("hasMustDo", "yes");
			}
		}
		// ********************** 不具备条件 (9)**********************/
		else if (doc_status.equals(FlowConstant.NODE_STATUS_BJTJ)) {
			// 如果为审批类节点，如果没有审结，按扭为”取消受理“”审批“”审结“
			if (FlowConstant.NODE_TYPE_CHECK.equals(node_type)) {
				if(approve_result == null && !isFormStart){
					m_button = new Button("已具条件");
					m_button.comment = "对此文档发表自己的意见";
					m_button.url = "javascript:docApprove('approve.do?"+ urlParas+"');";
					m_button.picUri = "icon";
					buttonList.add(m_button);
				}
			}

			paraMap.put("hasMustDo", "yes");
		}
		// **********************待复文档状态 (3)**********************/
		else if (doc_status.equals(FlowConstant.NODE_STATUS_SEND)) {
			// 目标节点非会签型且未受理，显示”收回按扭“
			if (nextnode_type != 3) {
				if(!isAccepted){
					m_button = new Button("收回文档");
					m_button.url = "javascript:docCallback('callback.do?doType=cancel&"
						+ urlParas + "')";
					m_button.comment = "收回文档，收回后可以重新发送";
					m_button.picUri = "retrieve";
					buttonList.add(m_button);	
				}
			}
			// 如果是发送的会审，可以“取消会审”，“结束会审”。 如果所有会审人员，都同意了为会结，直接回到在办中，否则状态为会审中。
			else {
				m_button = new Button("取消会审");
				m_button.url = "javascript:docCallback('interruptMeeting.do?" + urlParas + "')";
				m_button.comment = "发起会审的人可以根据具体情况,取消会审；取消会审后可以重新发起会审";
				m_button.picUri = "icon";
				buttonList.add(m_button);

				m_button = new Button("结束会审");
				m_button.comment = "发起会审的人可以根据具体情况，人为结束会审；结束后，所有会审人员的节点将办结";
				m_button.url ="javascript:docCallback('closeMeeting.do?" + urlParas + "')";
				m_button.picUri = "icon";
				buttonList.add(m_button);
			}
			paraMap.put("hasMustDo", "yes");
		}
		// **********************回复文档状态 (4-7)********************/
		else {
			/**
			 * 保存按扭列示条件（特殊情况特殊用）
			 * 1、回复
			 * 2、write_limit = 1 ;正常情况是都不为 1
			 * 3、有可写字段
			 */
			if (!FlowConstant.NODE_STATUS_OFF.equals(doc_status)
					&& write_limit == 1 && hasWriteFields //有写字段的权限
					) {
				m_button = new Button("保 存");
				m_button.comment = "保存当前文档";
				m_button.url = "javascript:docSave();";
				m_button.picUri = "save";
				buttonList.add(m_button);
				
				m_button = new Button("附 件");
				m_button.comment = "上传附件，上传后将出现当前文档下面的附件区";
				m_button.url = "javascript:docSlave('slave.do?" +urlParas +"');";
				m_button.picUri = "attach";
				buttonList.add(m_button);				
			}
			
			// 回复后可以直接看一下，是否有流转按扭，如果没有，只有办结按扭。
			if(FlowConstant.NODE_STATUS_RETURN_OK.equals(doc_status)){
				relationList = listRelation(paraMap);
				buttonList.addAll(relationList);
			} 
		}

		/**
		 * 办结按扭条件
		 * 1、doc_status为在办或回复
		 * 2、tb03中没有必走节点
		 * 3、已审结。
		 */
		if ( MapUtil.getLong(paraMap, "doc_id") > 0&&MapUtil.getLong(paraMap, "node_id") > 0&& !"yes".equals(paraMap.get("hasMustDo"))&& !doc_status.equals(FlowConstant.NODE_STATUS_OFF)) {
			m_button = new Button("办 结");
			m_button.comment = "办结当前文档，办结后，文档将移动到办结事宜中去";
			m_button.url = "javascript:docAchive('archive.do?" + urlParas + "')";
			m_button.picUri = "finish";
			buttonList.add(m_button);
		}
		
		// *********************************************************************************************抄送、关闭按扭
		buttonList.addAll(baseButtonList);
		return buttonList;
	}

	@SuppressWarnings("unchecked")
	public List<Button> listRelation(Map paraMap)  {
		// ******************变量定义*****************************/
		Button m_button = null;// 按扭数据结构体
		List<Button> relationList = new LinkedList<Button>(); // 按扭存放List
																// 也是返回结果List

		Long tb12_id = MapUtil.getLong(paraMap, "tb12_id");// 当前节点的tb12_id
		Integer seq = 0;// 流程操作序列
		Integer approve_result = MapUtil.getInteger(paraMap, "approve_result");
		Integer doc_status = MapUtil.getInteger(paraMap, "doc_status");// doc_status
		Integer node_type = MapUtil.getInteger(paraMap, "node_type"); // 当前节点类别

		Long project_id = MapUtil.getLong(paraMap,"project_id");// 流程任务ID
		Long node_id = MapUtil.getLong(paraMap, "node_id"); // 流程节点ID
		Long user_id = MapUtil.getLong(paraMap, "user_id");// 表单类型ID

		// *******************初始化参数****************************/
		Map<String,String> urlParaMap = new HashMap<String,String>();
		//可执行sql；sql参数用${user_id}表示用户ID${user_name},${project_id},${doc_id},${module_id},${node_id},${opernode_id}
		String urlParaStr = MapUtil.getUrl(paraMap, new String[] { "project_id", "node_id", "module_id", "doc_id",
				"user_id", "user_name", "opernode_id" });
		MapUtil.load(urlParaMap, urlParaStr);
		
		// 流程发送序列
		StringBuffer hsql = new StringBuffer("select max(seq) from Tb13_operrelation tb13 where tb13.source_id = ?");
		List tmpList = queryService.searchList(hsql.toString(), new Object[] { tb12_id });
		if (tmpList.size() > 0) {
			seq = convertUtil.toInteger(tmpList.get(0));
		}
		tmpList.clear();

		// ***************tb15中配置按扭**************************/
		hsql.delete(0, hsql.length());
		hsql.append(" select tb05.name,tb05.doclass,tb05.description,");
		hsql.append("tb05.paras as tb05_paras ,tb14.parameters as tb14_paras");
		hsql.append(" from Tb05_affair tb05,Tb14_even_affair tb14");
		hsql.append(" where tb05.id = tb14.affair_id");
		hsql.append(" and tb05.affair_type = 'show_button'");
		hsql.append(" and tb14.node_status like '%");
		hsql.append(doc_status);
		hsql.append("%' and tb14.even_id =");
		hsql.append(node_id);
		hsql.append(" order by tb14.seq");
		tmpList = queryService.searchList(hsql.toString());
		for (Object row : tmpList) {
			if (row instanceof Object[]) {
				Object[] arr = (Object[]) row;

				// 把action相关参数串到window_pro
				Map<String, String> proMap = new HashMap<String, String>();
				MapUtil.load(proMap, (String) arr[3]);
				MapUtil.load(proMap, (String) arr[4]);
				MapUtil.load(proMap,urlParaStr);
				/**
				 * 判断action是否显示
				 */
				
				try {
					// 判断是否可以显示
					if (proMap.containsKey("showClass")) {
						Class c = Class.forName(MapUtil.getString(proMap, "showClass"));
						ButtonControl bc;
						bc = (ButtonControl) c.newInstance();
						bc.setQueryService(queryService);
						bc.setSaveService(saveService);
						if (!bc.isShow(proMap))
							continue;
					}
				} catch (ClassNotFoundException e) {
					log.error("showClass配置类不存在，配置信息：" + proMap.toString());
				} catch (InstantiationException e) {
					log.error("showClass配置类调用时错误，配置信息：" + proMap.toString());
				} catch (IllegalAccessException e) {
					log.error("showClass配置类不能有效访问，配置信息：" + proMap.toString());
				}
				
				
				m_button = new Button((String) arr[0]);
				m_button.comment = (String) arr[2];
				//URL参数处理
				 String tmp_str = arr[1] == null ?"":(String) arr[1];
				if(tmp_str.indexOf("javascript:") != -1&&tmp_str.indexOf('(') != -1&&tmp_str.indexOf(')') != -1){
					for (String key : urlParaMap.keySet()) {
						tmp_str = tmp_str.replace("${" + key + "}", urlParaMap.get(key));
					}
				}else if(!proMap.containsKey("notHasFlowPara")){
					tmp_str += "&" + urlParaStr;
				}
				
				m_button.url = tmp_str;
				
				relationList.add(m_button);
			}
		}

		// ***************tb03配置流程发送、报批、通知操作按扭**************************/
		// 获得末走relation －－其中不包括 新建
		hsql.delete(0, hsql.length());
		hsql.append(" select tb03.id,tb03.name,tb03.pic_url,");
		hsql.append(" tb03.paras,tb03.mustRouted,tb03.description,tb03.jsVerify ");
		hsql.append(" from Tb03_relation tb03 ");
		hsql.append(" where  tb03.source_id = ");
		hsql.append(node_id);
		hsql.append(" and tb03.seq >");
		hsql.append(seq);
		// 如果审结话，不能在 上报审批；会审
		if(paraMap.get("isFormStart").equals(false)){
			if (approve_result == -1) {
				hsql.append(" and  tb03.relation_type = 'report'");
			} else {
				hsql.append(" and ( tb03.relation_type = 'info' or  tb03.relation_type = 'send')");
			}
		}
		

		hsql.append(" ORDER BY tb03.seq");
		tmpList = queryService.searchList(hsql.toString());
		for (Object row : tmpList) {
			if (row instanceof Object[]) {
				Object[] arr = (Object[]) row;
				// 把relation相关参数串到proMap中
				Map<String, String> proMap = new HashMap<String, String>();
				MapUtil.load(proMap, (String) arr[3]);
				paraMap.put("relation_id", arr[0]);
				paraMap.put("tb03_desc", arr[5]);
				String url = "OK";
				try {
					// 判断是否可以显示
					if (proMap.containsKey("showClass")) {
						Class c = Class.forName(MapUtil.getString(proMap, "showClass"));
						ButtonControl bc;
						bc = (ButtonControl) c.newInstance();
						bc.setQueryService(queryService);
						bc.setSaveService(saveService);
						if (!bc.isShow(paraMap))
							continue;
					}

					if (proMap.containsKey("condition")) {
						Class c = Class.forName(MapUtil.getString(proMap, "condition"));
						ButtonControl bc = (ButtonControl) c.newInstance();
						bc.setQueryService(queryService);
						bc.setSaveService(saveService);
						url = bc.checkCondition(paraMap);
					}
				} catch (ClassNotFoundException e) {
					log.error("showClass或condition时配置类不存在，配置信息：" + proMap.toString());
				} catch (InstantiationException e) {
					log.error("showClass或condition配置类调用时错误，配置信息：" + proMap.toString());
				} catch (IllegalAccessException e) {
					log.error("showClass或condition配置类不能有效访问，配置信息：" + proMap.toString());
				}
				m_button = new Button((String) arr[1]);
				if ("OK".equals(url)) {
					if ("1".equals(arr[6])) {
						m_button.url = "javascript:jsVerySend("
								+ arr[0]
								+ ", 'send.do?relation_id="
								+ arr[0]
								+ "&"
								+ MapUtil.getUrl(paraMap, new String[] { "project_id", "module_id", "doc_id",
										"node_id", "opernode_id", "user_id" }) + "')";
					} else {
						m_button.url = "javascript:docSend('send.do?relation_id="
								+ arr[0]
								+ "&"
								+ MapUtil.getUrl(paraMap, new String[] { "project_id", "module_id", "doc_id",
										"node_id", "opernode_id", "user_id" }) + "')";
					}
					
				} else  {
					m_button.url = "javascript:showMsgBox('" + url + "','warning',null,1);";
				}
				m_button.comment = (String) arr[5]; 
				m_button.picUri = (String) arr[2];
				relationList.add(m_button);
				if ("must".equals(arr[4])) {
					paraMap.put("hasMustDo", "yes");
					break;
				}
			}
		}
		

		// ***************新建表单按扭**************************/
		Tb11_operflow tb11 = null;
		hsql.delete(0, hsql.length());
		hsql.append(" from Tb11_operflow tb11  ");
		hsql.append(" where  tb11.project_id = ?");
		tmpList = queryService.searchList(hsql.toString(),new Object[]{MapUtil.getLong(paraMap, "project_id")});
		tb11 = (Tb11_operflow)tmpList.get(0);
		
		/**
		 * 新建表单，只要节点已同意，都可以列示。
		 * 新建表单没有先后顺序，没有必走或不必走。
		 * 如果flowType =3 ,起草后
		 */
		if ((FlowConstant.CHECK_RESULT_OK.equals(approve_result) || paraMap
				.get("isFormStart").equals(true)||FlowConstant.NODE_TYPE_NO_CHECK.equals(node_type))
				&& !doc_status.equals(FlowConstant.NODE_STATUS_OFF)&&!"yes".equals(paraMap.get("hasMustDo"))) {
			hsql.delete(0, hsql.length());
			hsql.append(" select tb04.startNode,tb04.module_id,ta06.name,tb01.type,tb04.paras,tb04.cflow_id ");
			hsql.append(" from Tb04_flowgroup tb04 ,Ta06_module ta06,Tb01_flow tb01");
			hsql.append(" where  tb04.module_id = ta06.id");
			hsql.append(" and tb01.id = tb04.cflow_id");
			hsql.append(" and tb04.sourceNode like '%[" + MapUtil.getString(paraMap, "node_id") + "]%'");
			hsql.append(" and pflow_id = ? ");
			hsql.append(" order by tb04.seq");
			tmpList = queryService.searchList(hsql.toString(),new Object[]{tb11.getFlow_id()});
			// 循环加载至List
			for (Object row : tmpList) {
				if (row instanceof Object[]) {
					Object[] arr = (Object[]) row;
					
					int flowType = convertUtil.toInteger(arr[3]);
					/**
					 * 验证表单的新建基本条件：
					 * flow_type=3，只有当前没有活动表单
					 * flow_type != 3,当前没有新建表单
					 * flow_type = 4 ,任何时间都可以新建
					 */
					hsql.delete(0, hsql.length());
					hsql.append(" select 'x' from Tb15_docflow tb15");
					hsql.append(" where  tb15.module_id = ? ");
					hsql.append(" and tb15.project_id = ? ");
					if (flowType == FlowConstant.FLOW_TYPE_ASYN_MORE) {
						hsql.append(" and tb15.doc_status != 8  ");
					}
					if(flowType == FlowConstant.FLOW_TYPE_ASYN_MORE){
						hsql.append(" and 1 != 1  ");
					}
					tmpList = queryService.searchList(hsql.toString(),new Object[]{arr[1],project_id});

					if (tmpList.size() > 0
							&& (tb11 != null && !convertUtil.toString(tb11.getRollback_modules()).equals(
									convertUtil.toString(arr[1])))) {
						tmpList.clear();
						continue;
					}
					
					/**
					 * 验证，配置新建表单先决条件是否成立
					 * 
					 */
					Map<String, String> proMap = new HashMap<String, String>();
					MapUtil.load(proMap, (String) arr[4]);
					paraMap.put("new_flow_id", arr[5]);
					paraMap.put("new_module_id", arr[1]);
					String url = "OK";
					try {
						// 判断是否可以显示
						if (proMap.containsKey("showClass")) {
							Class c = Class.forName(MapUtil.getString(proMap, "showClass"));
							ButtonControl bc;
							bc = (ButtonControl) c.newInstance();
							bc.setQueryService(queryService);
							bc.setSaveService(saveService);
							if (!bc.isShow(paraMap))
								continue;
						}

						if (proMap.containsKey("condition")) {
							Class c = Class.forName(MapUtil.getString(proMap, "condition"));
							ButtonControl bc = (ButtonControl) c.newInstance();
							bc.setQueryService(queryService);
							bc.setSaveService(saveService);
							url = bc.checkCondition(paraMap);
						}
					} catch (ClassNotFoundException e) {
						log.error("showClass或condition时配置类不存在，配置信息：" + proMap.toString());
					} catch (InstantiationException e) {
						log.error("showClass或condition配置类调用时错误，配置信息：" + proMap.toString());
					} catch (IllegalAccessException e) {
						log.error("showClass或condition配置类不能有效访问，配置信息：" + proMap.toString());
					}
					
					
					/**
					 * 符合基本条件、配置条件
					 * 可以新建
					 */
					m_button = new Button((String) arr[2]);
					m_button.url = "javascript:docNew('flowForm.do?module_id=" + arr[1] + "&node_id=" + arr[0]
							+ "&project_id=" + project_id + "&preOpernode_id=" + tb12_id + "&user_id=" + user_id
							+ "');";
					m_button.picUri = "newform";
					
					
					/**
					 * 新建 已存在的主表单，需先获得已存在表单ID
					 */
					if(tb11 != null &&convertUtil.toString(tb11.getRollback_modules()).equals(convertUtil.toString(arr[1]))){
						hsql.delete(0, hsql.length());
						hsql.append(" select doc_id from Tb15_docflow tb15");
						hsql.append(" where  tb15.module_id = ? ");
						hsql.append(" and tb15.project_id = ?  order by id desc");
						tmpList = queryService.searchList(hsql.toString(),new Object[]{arr[1],project_id});
						if(tmpList.size()>0){
							m_button.url = "javascript:docNew('flowForm.do?renew=yes&module_id=" + arr[1] + "&node_id=" + arr[0] + "&project_id="
							+ project_id + "&preOpernode_id=" + tb12_id+"&doc_id="+ convertUtil.toLong(tmpList.get(0)) +"');";		
						}
					}
					
					/**
					 * 如果condition条件不符合，
					 * 更改button的url
					 */
					if (!"OK".equals(url)) {
						m_button.url = "javascript:showMsgBox('" + url + "','warning',null,1);";
					}
					
					relationList.add(m_button);
					if (flowType == FlowConstant.FLOW_TYPE_ONLY_ARCHIVE){
						paraMap.put("hasMustDo", "yes");
					}
				}
			}
		}

		return relationList;
	}
	
	/**
	 * 获得流程新建表单列表
	 * <p>
	 * 返回Buttons 的List method:listNewFormButtons
	 * 
	 * @param paraMap
	 *            中必有user_id
	 * @return List<Button>
	 */
	@SuppressWarnings("unchecked")
	public List<Button> listNewFormButtons(Map paraMap) {

		List<Button> buttonList = new LinkedList<Button>();

		// 如果参数中不包括user_id直接返回
		if (!paraMap.containsKey("user_id")) {
			return buttonList;
		}
		/**
		 * 从流程配置表获取 一个人新建流程的按扭 主流程
		 */
		StringBuffer hsql = new StringBuffer(
				" select distinct tb02.id,tb04.module_id,substr(tb01.name,0,1),ta06.name,tb04.pflow_id ");
		hsql.append(" from Tb04_flowgroup tb04 ,Tb01_flow tb01,Tb02_node tb02,");
		hsql.append(" Ta06_module ta06,Ta13_sta_node ta13,Ta11_sta_user ta11 ");
		hsql.append(" where tb04.module_id = ta06.id ");
		hsql.append(" and tb04.pflow_id = tb01.id");
		hsql.append(" and tb04.cflow_id = tb02.flow_id");
		hsql.append(" and tb02.id = ta13.node_id");
		hsql.append(" and ta13.station_id = ta11.station_id");
		hsql.append(" and ta11.user_id =");
		hsql.append(MapUtil.getLong(paraMap, "user_id"));
		hsql.append(" and tb04.seq = 1");
		hsql.append(" and tb02.canStart = 'Y'");
		List<?> tmpList = queryService.searchList(hsql.toString());
		// 循环加载至List
		Button m_button = null;
		for (Object row : tmpList) {
			if (row instanceof Object[]) {
				Object[] arr = (Object[]) row;
				m_button = new Button("[" + (String) arr[2] + "]" + (String) arr[3]);
				m_button.url = "flowForm.do?module_id=" + arr[1] + "&node_id=" + arr[0]+"&flow_id=" + arr[4];
				buttonList.add(m_button);
			}
		}
		return buttonList;
	}

	/**
	 * 获得流程右上角的相关链接 method:ListActions
	 * 链接地址配置在数据库tb05.doclass中
	 * action链接中可以配置javascript函数,javascript参数中含有${user_id},${project_id}…用当前表单对应的user_id,project….替换
	 * 
	 * @param paraMap
	 *            中必有： <br>
	 *            project_id,node_id,user_id
	 * @return List<Button>
	 */
	@SuppressWarnings("unchecked")
	public List<Button> ListActions(Map paraMap) {

		List<Button> actionsList = new LinkedList<Button>();// 返回结果集。
		
		//如果paraMap中参数不完整，直接返回空Map 
		if (paraMap.containsKey("opernode_id") && paraMap.containsKey("project_id") && paraMap.containsKey("node_id")
				&& paraMap.containsKey("user_id")) {
			;
		} else {
			return actionsList;
		}
		
		//初始化变量
		Long tb12_id = MapUtil.getLong(paraMap, "opernode_id"); // 当前节点tb12中最大的ID;
		Integer doc_status;// 当前节点tb15对应状态;
		Long project_id = MapUtil.getLong(paraMap, "project_id");
		Long node_id = MapUtil.getLong(paraMap, "node_id");
		Map<String,String> urlParaMap = new HashMap<String,String>();
		//可执行sql；sql参数用${user_id}表示用户ID${user_name},${project_id},${doc_id},${module_id},${node_id},${opernode_id}
		String urlParaStr = MapUtil.getUrl(paraMap, new String[] { "project_id", "node_id", "module_id", "doc_id",
				"user_id", "user_name", "opernode_id" });
		MapUtil.load(urlParaMap, urlParaStr);

		// 获得doc_status
		StringBuffer hsql = new StringBuffer("select doc_status from Tb15_docflow where opernode_id = ? and user_id = ? ");
		List tmpList = queryService.searchList(hsql.toString(), new Object[] { tb12_id, convertUtil.toLong(paraMap.get("user_id")) });
		if (tmpList.size() >= 1) {
			doc_status = (Integer) tmpList.get(0);
			;
		} else {
			return actionsList;
		}
		
		// 从tb14、tb05中取根据doc_status,node_id或得action
		hsql = new StringBuffer(
				" select tb05.name,tb05.doclass,tb05.description,tb05.paras as tb05_paras ,tb14.parameters as tb14_paras,tb05.id ");
		hsql.append(" from Tb05_affair tb05,Tb14_even_affair tb14");
		hsql.append(" where tb05.id = tb14.affair_id");
		hsql.append(" and tb05.affair_type = 'show_action'");
		hsql.append(" and tb14.node_status like '%");
		hsql.append(doc_status);
		hsql.append("%' and tb14.even_id =");
		hsql.append(node_id);
		hsql.append(" order by tb14.seq");
		tmpList = queryService.searchList(hsql.toString());
		Button m_button = null;
		for (Object row : tmpList) {
			if (row instanceof Object[]) {
				
				Object[] arr = (Object[]) row;
				// 把action相关参数串到window_pro
				Map<String, String> proMap = new HashMap<String, String>();
				MapUtil.load(proMap, (String) arr[3]);
				MapUtil.load(proMap, (String) arr[4]);
				MapUtil.load(proMap, urlParaStr);
				/**
				 * 判断action是否显示
				 */
				
				try {
					// 判断是否可以显示
					if (proMap.containsKey("showClass")) {
						Class c = Class.forName(MapUtil.getString(proMap, "showClass"));
						ButtonControl bc;
						bc = (ButtonControl) c.newInstance();
						bc.setQueryService(queryService);
						bc.setSaveService(saveService);
						if (!bc.isShow(proMap))
							continue;
					}
				} catch (ClassNotFoundException e) {
					log.error("showClass配置类不存在，配置信息：" + proMap.toString());
				} catch (InstantiationException e) {
					log.error("showClass配置类调用时错误，配置信息：" + proMap.toString());
				} catch (IllegalAccessException e) {
					log.error("showClass配置类不能有效访问，配置信息：" + proMap.toString());
				}
				
				
				m_button = new Button((String) arr[0]);
				String tmp_str = "";
				if (proMap.containsKey("height")) {// 窗口高度
					tmp_str = "height=" + proMap.get("height");
				}
				if (proMap.containsKey("width")) {// 窗口宽度
					tmp_str += "width=" + proMap.get("width");
				}
				if (proMap.containsKey("scrollbars")) {// 窗口是否可以滚动
					tmp_str += "scrollbars=" + proMap.get("scrollbars");
				}
				
				m_button.comment = (String) arr[2];
				m_button.window_pro = tmp_str;
				
				//URL参数处理
				 tmp_str = arr[1] == null ?"":(String) arr[1];
				if(tmp_str.indexOf("javascript:") != -1&&tmp_str.indexOf('(') != -1&&tmp_str.indexOf(')') != -1){
					for (String key : urlParaMap.keySet()) {
						tmp_str = tmp_str.replace("${" + key + "}", urlParaMap.get(key));
					}
				}else if(!proMap.containsKey("notHasFlowPara")){
					tmp_str += "&" + urlParaStr;
				}
				
				m_button.url = tmp_str;
				
				
				actionsList.add(m_button);
			}
		}
		return actionsList;
	}

	/**
	 * 创建表单流程
	 * <p>
	 * 当新建表单时调用
	 * @param paraMap 须包含如果下参数
	 * <br>project_id,doc_id,module_id,node_id,user_id,user_name
	 * @return
	 * @throws Exception 
	 */
	public boolean CreateForm(Map paraMap) throws Exception {
		//***********************************检查参数**********************************/
		if ( MapUtil.getLong(paraMap, "project_id") == -1 || MapUtil.getLong(paraMap, "doc_id") == -1 
				||MapUtil.getLong(paraMap, "module_id") == -1 || MapUtil.getLong(paraMap, "node_id") == -1 || MapUtil.getLong(paraMap, "user_id") == -1 ) {
			return false;
		}
		
		//**********************************变量定义数据准备**********************************/
		Session session = null; //事务相关
		Transaction tx = null;//事务相关
		
		Tb11_operflow tb11 = null;
		Tb12_opernode preTb12 = null;
		Tb12_opernode tb12 = null;
		Tb13_operrelation tb13 = null;
		Tb15_docflow tb15 = null;
		Tb02_node tb02 = null;
		Tb01_flow tb01 = null;
		preTb12 = (Tb12_opernode)queryService.searchById(Tb12_opernode.class, MapUtil.getLong(paraMap,"preOpernode_id"));
		
		StringBuffer hsql = new StringBuffer();
		List<?> tmpList = new LinkedList();
		
		tb02 = (Tb02_node) queryService.searchById(Tb02_node.class, MapUtil.getLong(paraMap,"node_id"));
		if(tb02 == null){
			log.error("创建流程失败，找到对应节点，请核对参数node_id = " + MapUtil.getLong(paraMap,"node_id"));
			return false;
		}		
		//获得TB11
		hsql.delete(0, hsql.length());
		hsql.append(" from Tb11_operflow tb11  ");
		hsql.append(" where  tb11.project_id = ?");
		tmpList = queryService.searchList(hsql.toString(),new Object[]{MapUtil.getLong(paraMap, "project_id")});
		if(tmpList.size() > 0)
		tb11 = (Tb11_operflow)tmpList.get(0);
		//***********************************判断是否已新建过*******************************************/
		/**
		 * 如果tb15里有新节点的记录，则说明是已新建过。
		 */
		hsql.delete(0, hsql.length());
		hsql.append("select 'x' from Tb15_docflow tb15 ");
		hsql.append(" where tb15.module_id = ?  and tb15.doc_id = ? ");
		tmpList = queryService.searchList(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "module_id"),
				MapUtil.getLong(paraMap, "doc_id") });
		if(tmpList.size() > 0&&(tb11 != null && !MapUtil.getString(paraMap, "module_id").equals(tb11.getRollback_modules()))){
			return false;
		}
		
		//***********************************新建表单*******************************************/
		try{
			session = saveService.getHiberbateSession();
			tx = session.beginTransaction(); 
			tx.begin(); //开始事务
			
			//获得user_name
			Ta03_user user = (Ta03_user)queryService.searchById(Ta03_user.class, MapUtil.getLong(paraMap, "user_id"));
			String user_name = user != null?user.getName():"";

			if(preTb12 != null ){
				//判断是否是办结上一节点
				tb01 = (Tb01_flow) queryService.searchById(Tb01_flow.class, tb02.getFlow_id());
				if (tb01 == null) {
					log.error("创建流程失败，参数node_id无效，找不到对应的tb01；node_id = " + MapUtil.getLong(paraMap, "node_id"));
					return false;
				}
				
			    //保存tb12
			    tb12 = new Tb12_opernode();
			    tb12.setDoc_id(MapUtil.getLong(paraMap,"doc_id"));
			    tb12.setProject_id(MapUtil.getLong(paraMap,"project_id"));
			    tb12.setModule_id(MapUtil.getLong(paraMap,"module_id"));
				tb12.setNode_id(tb02.getId());
				tb12.setNode_name(tb02.getName());
				tb12.setNode_type(tb02.getNode_type());
				tb12.setNode_ext(tb02.getExt());
				tb12.setRollback_nodes(tb02.getRollback_nodes());
				tb12.setNode_status(FlowConstant.NODE_STATUS_NEW);
				tb12.setStart_time(new Date());
				tb12.setCflow_root("1");
				session.saveOrUpdate(tb12);
				
				//保存tb15
				tb15 = new Tb15_docflow();
				tb15.setDoc_id(tb12.getDoc_id());
				tb15.setProject_id(tb12.getProject_id());
				tb15.setModule_id(tb12.getModule_id());
				tb15.setNode_id(tb12.getNode_id());
				tb15.setWrite_limit(new Integer(1));
				tb15.setDoc_status(FlowConstant.NODE_STATUS_NEW);
				tb15.setOpernode_id(tb12.getId());
				tb15.setOper_user(user_name);
				tb15.setOper_status("待复");
				tb15.setUser_id(MapUtil.getLong(paraMap, "user_id"));
				tb15.setHuser_id(MapUtil.getLong(paraMap, "user_id"));
				tb15.setGroup_id(new Long(-1));
				tb15.setOper_time(new Date());
				tb15.setAccept_time(new Date());
				session.saveOrUpdate(tb15);
				
				//插入tb13
				tb13 = new Tb13_operrelation();
				tb13.setSource_id(preTb12.getId());
				tb13.setDest_id(tb12.getId());
				tb13.setDoc_id(tb12.getDoc_id());
				tb13.setProject_id(tb12.getProject_id());
				tb13.setOper_time(new Date());
				tb13.setRelation_id(new Long(-1));
				tb13.setRelation_name("新建表单");
				tb13.setSeq(new Integer(0));
				session.saveOrUpdate(tb13);
				
				//如果当前表单对应的流程类型如果为1 则办结起草表单
				int flow_type = -1;
				hsql.delete(0, hsql.length());
				hsql.append("select tb01.type from Tb01_flow tb01,Tb02_node tb02 ");
				hsql.append(" where  tb02.flow_id = tb01.id ");
				hsql.append(" and tb02.id = ?  ");
				tmpList = queryService.searchList(hsql.toString(), new Object[] {MapUtil.getLong(paraMap, "node_id")});
				if(tmpList.size() > 0){
					flow_type = convertUtil.toInteger(tmpList.get(0));
				}
				//办结起草表单
				if(flow_type == FlowConstant.FLOW_TYPE_ONLY_ARCHIVE.intValue() ){
					preTb12.setEnd_time(new Date());
					preTb12.setNode_status(FlowConstant.NODE_STATUS_OFF);
					session.saveOrUpdate(preTb12);

					// 置当前节点文档状态办结 tb15
					QueryBuilder queryBuilder = new HibernateQueryBuilder(Tb15_docflow.class);
					queryBuilder.eq("opernode_id", preTb12.getId());
					tmpList = queryService.searchList(queryBuilder);
					if (tmpList.size() > 0) {
						tb15 = (Tb15_docflow) tmpList.get(0);
						tb15.setDoc_status(FlowConstant.NODE_STATUS_OFF);
						tb15.setOper_time(new Date());
						tb15.setWrite_limit(new Integer(0));// 文档可写标识
						tb15.setOper_user(user_name);
						tb15.setOper_status("办结");
						session.saveOrUpdate(tb15);
					}
					tmpList.clear();
				}
				
				//如果是新建回退表单，则清空tb11.rollback_modules
				if(tb11 != null && MapUtil.getString(paraMap, "module_id").equals(tb11.getRollback_modules())){
					tb11.setRollback_modules(null);
					session.saveOrUpdate(tb11);
				}
			//***********************************新建流程********************************************/	
			} else {
				//取当前表单对应的主流程
				hsql.delete(0, hsql.length());
				hsql.append("select tb01 from Tb04_flowgroup tb04 ,Tb01_flow tb01,Tb02_node tb02 ");
				hsql.append(" where tb01.id = tb04.pflow_id and tb02.flow_id = tb04.cflow_id ");
				hsql.append(" and tb04.module_id = ?  and tb02.id = ?  order by tb04.seq ");
				tmpList = queryService.searchList(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "module_id"),
						MapUtil.getLong(paraMap, "node_id") });
				if(tmpList.size() > 0){
					tb01 = (Tb01_flow)tmpList.get(0);
				}
				
				if(tb01 == null){
					log.error("创建流程失败，找不到对应的主流程：hsql = " +  hsql.toString() );
					log.error("module_id = " + MapUtil.getLong(paraMap, "module_id") +"; node_id =" + MapUtil.getLong(paraMap, "node_id"));
					return false;
				}
				
				//保存TB11
				if (tb11 == null) {
					tb11 = new Tb11_operflow();
					tb11.setProject_id(MapUtil.getLong(paraMap,"project_id"));
				    tb11.setFlow_id(tb01.getId());
				    tb11.setName(tb01.getName());
				    tb11.setDescription(tb01.getDescription());
				    tb11.setDisplay(tb01.getDisplay());
				    tb11.setStart_time(new Date());
				    tb11.setStatus(FlowConstant.FLOW_STATUS_RUN);
				    session.saveOrUpdate(tb11);
				}
			    //保存tb12
			    tb12 = new Tb12_opernode();
			    tb12.setDoc_id(MapUtil.getLong(paraMap,"doc_id"));
			    tb12.setProject_id(MapUtil.getLong(paraMap,"project_id"));
			    tb12.setModule_id(MapUtil.getLong(paraMap,"module_id"));
				tb12.setNode_id(tb02.getId());
				tb12.setNode_name(tb02.getName());
				tb12.setNode_type(tb02.getNode_type());
				tb12.setRollback_nodes(tb02.getRollback_nodes());
				tb12.setNode_status(FlowConstant.NODE_STATUS_NEW);
				tb12.setStart_time(new Date());
				tb12.setCflow_root("1");
				session.saveOrUpdate(tb12);
				
				//保存tb15
				tb15 = new Tb15_docflow();
				tb15.setDoc_id(tb12.getDoc_id());
				tb15.setProject_id(tb12.getProject_id());
				tb15.setModule_id(tb12.getModule_id());
				tb15.setNode_id(tb12.getNode_id());
				tb15.setWrite_limit(new Integer(1));
				tb15.setDoc_status(FlowConstant.NODE_STATUS_NEW);
				tb15.setOpernode_id(tb12.getId());
				tb15.setOper_user(user_name);
				tb15.setOper_status("待复");
				tb15.setUser_id(MapUtil.getLong(paraMap, "user_id"));
				tb15.setHuser_id(MapUtil.getLong(paraMap, "user_id"));
				tb15.setGroup_id(new Long(-1));
				tb15.setOper_time(new Date());
				tb15.setAccept_time(new Date());			
				session.saveOrUpdate(tb15);
			}
			
			session.flush();
			tx.commit(); //提交事务;
		}catch(Exception e){
			if(tx != null) tx.rollback();
			log.error("创建表单出错", e);
			throw new Exception("创建表单出错", e);
		}finally{
			if(session != null){
				session.close();
			}
		}
		return true;
	}
	
	/**
	 * 修改流程：
	 * 传入tb12.id 
	 * 判断后续流程是否新建表单。如果新建表单则不能删除。
	 * 修改当前节点状态，删除后续节点、关系（tb12,tb13,tb15,tb17）
	 * @param paraMap
	 * @return String
	 */
	public String modifyFlow(final Map paraMap) throws Exception {
		
		StringBuffer hsql = new StringBuffer();
		List tmpList = null;
		Session session = null; //事务相关
		Transaction tx = null;//事务相关
		/**
		 * 判断是后续流程是否已建新表单
		 */
		hsql.append("select distinct module_id from Tb15_docflow ");
		hsql.append(" where  module_id > 100 and module_id < 109 ");
		hsql.append(" and opernode_id > ? and project_id  = ? ");
		tmpList =  queryService.searchList(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "opernode_id"),
			MapUtil.getLong(paraMap, "project_id") });
		if(tmpList.size() > 1){
			return "后续流程已建新表单，不能调整！";
		}
		
		try{
			session = saveService.getHiberbateSession();
			tx = session.beginTransaction(); 
			tx.begin(); //开始事务
			/**
			 * 删除节点
			 */
			hsql.delete(0, hsql.length());
			hsql.append("delete from Tb12_opernode where id > ?  and module_id = ? and  doc_id = ?");
			saveService.updateByHSql(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "opernode_id"),
				MapUtil.getLong(paraMap, "module_id"),MapUtil.getLong(paraMap, "doc_id") });
			
			hsql.delete(0, hsql.length());
			hsql.append("delete from Tb15_docflow where opernode_id > ? and module_id = ?  and doc_id = ?");
			saveService.updateByHSql(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "opernode_id"),
				MapUtil.getLong(paraMap, "module_id"),MapUtil.getLong(paraMap, "doc_id") });
			
			/**
			 * 删除关系
			 */
			Long minRelation_id = Long.MAX_VALUE;
			hsql.delete(0, hsql.length());
			hsql.append("select min(id) from Tb13_operrelation where source_id = ?");
			tmpList = queryService.searchList(hsql.toString(),new Object[] { MapUtil.getLong(paraMap, "opernode_id")});
			
			if(tmpList.size() > 0 && tmpList.get(0) != null){
				minRelation_id = (Long)tmpList.get(0);
			}
			hsql.delete(0, hsql.length());
			hsql.append("delete from Tb13_operrelation where id >= ? and project_id = ?  and doc_id = ?");
			saveService.updateByHSql(hsql.toString(), new Object[] { minRelation_id,
				MapUtil.getLong(paraMap, "project_id"),MapUtil.getLong(paraMap, "doc_id") });
			
			/**
			 * 删除审批意见
			 */
			hsql.delete(0, hsql.length());
			hsql.append("delete from Tb17_approve where id > ? and module_id = ?  and doc_id = ?");
			saveService.updateByHSql(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "opernode_id"),
				MapUtil.getLong(paraMap, "module_id"),MapUtil.getLong(paraMap, "doc_id") });
			
			/**
			 * 修改节点状态
			 */
			hsql.delete(0, hsql.length());
			hsql.append("update Tb12_opernode set node_status = 2  where id = ? ");
			saveService.updateByHSql(hsql.toString(), new Object[] {MapUtil.getLong(paraMap, "opernode_id") });
			
			hsql.delete(0, hsql.length());
			hsql.append("update Tb15_docflow set doc_status =2 ,write_limit = 1 ,approve_result = null  where opernode_id = ? ");
			saveService.updateByHSql(hsql.toString(), new Object[] {MapUtil.getLong(paraMap, "opernode_id") });
				
			session.flush();
			tx.commit(); // 提交事务;
		} catch (Exception e) {
			if(tx != null) tx.rollback();
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return "流程调整成功";		
	}

	/**
	 * 判断是否包含名称为buttonName的按扭
	 * @param paraMap
	 * @param buttonName
	 * @return boolean
	 */
	public boolean containsButton(Map paraMap,String buttonName){
			List<Button> buttonList =  listButtons(paraMap);
			for(Button button :buttonList){
				if (button.getName().equals(buttonName)){
					return true;
				}
			}
		return false;
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
	
}