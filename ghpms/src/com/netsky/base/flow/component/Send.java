package com.netsky.base.flow.component;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta05_group;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Tb02_node;
import com.netsky.base.dataObjects.Tb03_relation;
import com.netsky.base.dataObjects.Tb12_opernode;
import com.netsky.base.dataObjects.Tb13_operrelation;
import com.netsky.base.dataObjects.Tb15_docflow;
import com.netsky.base.flow.FlowConstant;
import com.netsky.base.flow.buttonControl.ButtonControl;
import com.netsky.base.flow.trigger.DoTrigger;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

/**
 * @description:
  * 发送文档
 * @class name:com.netsky.base.flow.component.Send
 * @author wind Jan 20, 2010
 */
@Component
public class Send  {

	/**
	 * 数据对应操作类
	 */
	@Autowired
	private SaveService saveService;

	@Autowired
	private QueryService queryService;

	/**
	 * 触发器
	 */
	@Autowired(required = false)
	private DoTrigger doTrigger;
	
	@Autowired
	private BaseUtil baseUtil;

	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());
	
	
	/**
	 * 发送文档
	 * <br>选择人员，或部门
	 * @param paraMap
	 * <br> map 中须包括relation_id,doc_id,project_id,module_id,node_id
	 * <br>user_id,user_name,opernode_id
	 * @return
	 * @throws Exception ModelAndView
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(Map paraMap) throws Exception {
		
		ModelMap modelMap = new ModelMap();
		Tb03_relation tb03 = null;//发送关系
		Tb02_node target_tb02 = null;//目标节点
		
		
		//数据操作相关变量
		StringBuffer hsql = new StringBuffer();
		QueryBuilder queryBuilder = null;
		List<?> tmpList = new LinkedList();
		
		//获得发送关系 tb03 如果找不到返回打开文档，
		tb03 = (Tb03_relation)queryService.searchById(Tb03_relation.class, MapUtil.getLong(paraMap,"relation_id"));
		target_tb02 = (Tb02_node)queryService.searchById(Tb02_node.class, tb03.getDest_id());
		paraMap.put("tb03", tb03);
		
		//发送请求 是否重复提交，重复提交系统不予处理
		hsql.delete(0, hsql.length());
		hsql.append("select 'x' from  Tb13_operrelation tb13 where tb13.relation_id = ? and tb13.source_id =? ");
		tmpList = queryService.searchList(hsql.toString(), new Object[]{tb03.getId(),MapUtil.getLong(paraMap, "opernode_id")});
		if(tmpList.size()> 0){
			String warnMessage = "操作已完成，不必二次操作!";
			modelMap.put("warnMessage", warnMessage);
			log.warn(warnMessage);
			return new ModelAndView(FlowConstant.CTR_OPENFORM,modelMap);
		}
		
		//发送选择人员或群组时，是单选还是多选
		if(FlowConstant.NODE_TYPE_MORE_CHECK.equals( target_tb02.getNode_type())){
			modelMap.put("selectType", "select-multiple");
		} else {
			modelMap.put("selectType", "select-one");
		}
		
		if(tb03 == null){
			String warnMessage = "发送失败，找不到发送关系；关系标识为" + paraMap.get("relation") +"!";
			modelMap.put("warnMessage","未找到相应节点人员(发送关系找不到)");
			log.warn(warnMessage);
			return new ModelAndView(FlowConstant.CTR_OPENFORM,modelMap);
		}
		
		//*************************************是否为二次发送****************************************************/
		//从选择群组页面返回
		if(paraMap.containsKey("to_group")){
			Ta05_group to_group = (Ta05_group)queryService.searchById(Ta05_group.class, MapUtil.getLong(paraMap, "to_group"));
			if(to_group!= null){
				paraMap.put("to_group",to_group);
				return sendOperDb(paraMap);
			}else {
				String warnMessage = "选择群组不存在；群组标识为：" + paraMap.get("to_group") +"!";
				modelMap.put("warnMessage","未找到相应节点人员("+warnMessage + ")");
				log.warn(warnMessage);
				return new ModelAndView(FlowConstant.CTR_OPENFORM,modelMap);
			}
		}else if(paraMap.containsKey("to_user")) {
			if(paraMap.get("to_user") instanceof String){
				String [] tmpArr = ((String)paraMap.get("to_user")).split(",");
				Long [] user_ids = new Long[tmpArr.length];
				for(int i = 0;i< tmpArr.length;i++){
					user_ids[i] = convertUtil.toLong(tmpArr[i]);
				}
				
				queryBuilder = new HibernateQueryBuilder(Ta03_user.class);
				queryBuilder.in("id", user_ids);
				List<Ta03_user> userList = (List<Ta03_user> )queryService.searchList(queryBuilder);
				if(userList.size()> 0){
					paraMap.put("to_user",userList);
					return sendOperDb(paraMap);
				}
			}
			
			String warnMessage = "发送失败；请重新发送!";
			modelMap.put("warnMessage","未找到相应节点人员");
			log.warn(warnMessage);
			return new ModelAndView(FlowConstant.CTR_OPENFORM,modelMap);			
		}
		
		//*************************************发送到历史处理节点************************************************/
		if(tb03.getHis_node()!= null){
			//获得回退节点对应的tb02 
			Tb02_node his_tb02 = (Tb02_node)queryService.searchById(Tb02_node.class, convertUtil.toLong(tb03.getHis_node()));
			if(his_tb02 == null){
				String warnMessage = "发送至历史节点处理人失败，<br>Tb03_relation.hisNode配置错误，hisNode标识:<br>" + tb03.getHis_node() +"!";
				modelMap.put("warnMessage","未找到相应节点人员(his_node 对应的人没权限)！");
				log.warn(warnMessage);
				return new ModelAndView(FlowConstant.CTR_OPENFORM,modelMap);
			}
			
			//获得回退节点对应的tb15，id最大的
			Tb15_docflow his_tb15 = null;
			hsql.delete(0, hsql.length());
			hsql.append(" from Tb15_docflow where node_id = ? and project_id = ? order by id desc");
			tmpList = queryService.searchList(hsql.toString(), new Object[]{his_tb02.getId(),MapUtil.getLong(paraMap, "project_id")});
			if(tmpList.size()> 0){
				his_tb15 = (Tb15_docflow)tmpList.get(0);
				tmpList.clear();
				//判断历史节点处理人员当前是否有权限
				hsql.delete(0, hsql.length());
				hsql.append("select ta03 from Ta03_user ta03,Ta11_sta_user ta11,Ta13_sta_node ta13");
				hsql.append(" where ta03.id = ta11.user_id and ta13.station_id = ta11.station_id ");
				hsql.append(" and ta13.node_id = ? ");
				hsql.append(" and ta11.user_id = ? ");
				tmpList = queryService.searchList(hsql.toString(), new Object[]{his_tb15.getNode_id(),his_tb15.getHuser_id()});
				//有权限，发送，如果没有权限向下执行。
				if(tmpList.size()> 0){
					paraMap.put("to_user",tmpList.get(0));
					return sendOperDb(paraMap);
				}
			}
		}
		
		//************************************通过getUsers或得发送人员*****************************************************/
		if(tb03.getParas()  != null&&tb03.getParas().indexOf("getUsers=") != -1){
			Map map = new HashMap();
			MapUtil.load(map, tb03.getParas());
			List<Ta03_user> userList = null;
			try {
				Class clazz = Class.forName((String) map.get("getUsers"));
				ButtonControl m_bc = (ButtonControl) clazz.newInstance();
				// 设置trigger中持久化处理类
				m_bc.setQueryService(queryService);
				m_bc.setSaveService(saveService);
				userList = m_bc.getUsers(paraMap);
			} catch (ClassNotFoundException e) {
				log.error("getUsers时配置类不存在，配置信息：" + map.toString());
			} catch (InstantiationException e) {
				log.error("getUsers配置类调用时错误，配置信息：" + map.toString());
			} catch (IllegalAccessException e) {
				log.error("getUsers配置类不能有效访问，配置信息：" + map.toString());
			}
			if(userList != null && userList.size()==1){
				paraMap.put("to_user", userList);
				return sendOperDb(paraMap);
			} else if(userList != null && userList.size()> 1){
				modelMap.put("to_user", userList);
				return new ModelAndView(FlowConstant.VIEW_SEND,modelMap);
			} else {
				log.error("getUsers获取不到人，配置信息：" + map.toString());
				modelMap.put("warnMessage","未找到相应节点人员(getUsers获取不到人员)！");
				return new ModelAndView(FlowConstant.CTR_OPENFORM,modelMap);
			}
		}
		
		//************************************发送接收人员范围为：当前群组****************************************************/
		if(FlowConstant.SEND_SCOPE_CUR_GROUP.equals(tb03.getTargetScope())){
			/**
			 * 发送到群组处理
			 * 1、获得发送人员的所在当前群组。
			 * 2、获得当前群组中所包含的拥有，目标节点权限的人员
			 * 3、判断目标节点权限的人员是否只有一个，如果有一个，直接发送到人
			 * 4、                     多个人员:如果发送到人（tb03.getToPeople()==1），转到“选择人员视图”，否则直接发送到群组
			 * 5、如果当前群组下没有人员，返回“打开表单” 并给出提醒－配置错误                      
			 */
			//获得当前群组
			List<Ta05_group> groupList = baseUtil.getGroup(MapUtil.getLong(paraMap, "user_id"), new Long[] {
				tb03.getSource_id(), tb03.getDest_id() });
			
			if(groupList == null || groupList.size() == 0){
				String warnMessage = "未找到相应节点人员(发送人员未配置群组)";
				modelMap.put("warnMessage",warnMessage);
				log.warn(warnMessage);
				return new ModelAndView(FlowConstant.CTR_OPENFORM,modelMap);
			}
			//获得当前群组下有目标节点的人员，
			List<Ta03_user> userList = baseUtil.getUser(tb03.getDest_id(), new Long[]{groupList.get(0).getId()});
			
			if(userList == null || userList.size() == 0){
				String warnMessage = "未找到相应节点人员(目标节点未配置人员)";
				modelMap.put("warnMessage",warnMessage);
				log.warn(warnMessage);
				return new ModelAndView(FlowConstant.CTR_OPENFORM,modelMap);
			} else if(userList.size() ==1 ){
				paraMap.put("to_user", userList);
				return sendOperDb(paraMap);
			} else {
				if("Y".equals(tb03.getToPeople())){
					modelMap.put("to_user", userList);
					return new ModelAndView(FlowConstant.VIEW_SEND,modelMap);
				} else {
					paraMap.put("to_group", groupList.get(0));
					return sendOperDb(paraMap);
				}
			}
		}
		//************************************发送接收人员范围为：所有群组****************************************************/
		else if (FlowConstant.SEND_SCOPE_GROUP.equals(tb03.getTargetScope())) {
			/**
			 * 发送到所有群组
			 * 1、获得目标节点所在属的群组（群组下包含目标节点，且有（拥有目标节点权限的）人员）groupList
			 * 2、判断群组是否为空，如果群组为空，返回错误
			 * 2、获得群组下拥有目标节点权限的所有人员userList
			 * 3、判断userList中是否只有一个人，如果是，直接发送到人
			 * 4、                     多个人员:如果发送到人（tb03.getToPeople()==1），转到“选择人员视图”
			 * 5、判断groupList中是否只有一个群组，如果是 直接发送群组
			 * 6、                               否  转到"选择人员视图"               
			 */
			List<Ta05_group> groupList = baseUtil.getGroup(new Long[] {
					tb03.getDest_id()});
			
			
			//如果找不到对应的
			if(groupList == null ||groupList.size() == 0 ){
				String warnMessage = "未找到相应节点人员（目标群组未配置）!";
				modelMap.put("warnMessage", warnMessage);
				log.warn(warnMessage);
				return new ModelAndView(FlowConstant.CTR_OPENFORM,modelMap);
			} else {
				Long[] group_ids = new Long[groupList.size()] ;
				for(int i = 0;i< groupList.size();i++){
					group_ids[i] = convertUtil.toLong(groupList.get(i));
				}
				List<Ta03_user> userList = baseUtil.getUser(tb03.getDest_id(), group_ids);
				if(userList.size() ==1 ){
					paraMap.put("to_user", userList);
					return sendOperDb(paraMap);
				} else {
					if("Y".equals(tb03.getToPeople())){
						modelMap.put("to_user", userList);
						return new ModelAndView(FlowConstant.VIEW_SEND,modelMap);
					} else {
						if(groupList.size() ==1){
							paraMap.put("to_group", groupList.get(0));
							return sendOperDb(paraMap);
						} else {
							modelMap.put("to_group", groupList);
							return new ModelAndView(FlowConstant.VIEW_SEND,modelMap);
						}
					}
				}
			}
		}	
		//************************************发送接收人员范围为：所有人员****************************************************/
		else {
			/**
			 * 发送到所有人员
			 * 1、获得获拥有目标节点权限的所有人员userList
			 * 2、判断userList 为空，则返回 "打开表单" 且提醒“发送失败，目标节点没有人员” 
			 * 3、判断userList中是否只有一个人，如果是，直接发送到人
			 * 4、                     多个人员:如果发送到人（tb03.getToPeople()==1），转到“选择人员视图”
			 */
			List<Ta03_user> userList = baseUtil.getUser(tb03.getDest_id());
			if(userList == null || userList.size() == 0){
				String warnMessage = "未找到相应节点人员（目标节点未配置人员）!";
				modelMap.put("warnMessage", warnMessage);
				log.warn(warnMessage);
				return new ModelAndView(FlowConstant.CTR_OPENFORM,modelMap);
			} else if(userList.size() ==1 ){
				paraMap.put("to_user", userList);
				return sendOperDb(paraMap);
			} else {
				if("Y".equals(tb03.getToPeople())){
					modelMap.put("to_user", userList);
					return new ModelAndView(FlowConstant.VIEW_SEND,modelMap);
				} else {
					return sendOperDb(paraMap);
				}
				
			}
		}
	}
	
	/**
	 * 发送操作的数据处理
	 * <br>插入tb13,tb12,tb15里
	 * @param paraMap 需求包括：tb03,opernode_id,user_id,user_name,to_user,to_group
	 * 
	 * @return
	 * @throws Exception ModelAndView
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView sendOperDb(Map paraMap) throws Exception {
		ModelMap modelMap = new ModelMap();
		Tb03_relation tb03 = null;// 发送基础relation
		Tb02_node target_tb02 = null;
		Tb13_operrelation tb13 = null;// 发送relation
		Tb12_opernode tb12 = null; // 节点信息
		Tb15_docflow tb15 = null; // 文档节点信息
		Tb12_opernode target_tb12 = null; // 节点信息
		Tb15_docflow target_tb15 = null; // 文档节点信息

		StringBuffer hsql = new StringBuffer();
		List tmpList = new LinkedList();
		List<Ta03_user> userList = null;
		Ta05_group to_group = null;

		// **********************************************数据准备************************************************/
		Session session = null; //事务相关
		Transaction tx = null;//事务相关
		try{
			session = saveService.getHiberbateSession();
			tx = session.beginTransaction(); 
			tx.begin(); //开始事务
			
			tb03 = (Tb03_relation) paraMap.get("tb03");
			target_tb02 = (Tb02_node) queryService.searchById(Tb02_node.class, tb03.getDest_id());
			tb12 = (Tb12_opernode) queryService.searchById(Tb12_opernode.class, MapUtil.getLong(paraMap, "opernode_id"));
			hsql = new StringBuffer(" from Tb15_docflow where opernode_id = ? and user_id = ?");
			tmpList = queryService.searchList(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "opernode_id"),
					MapUtil.getLong(paraMap, "user_id") });
			if (tmpList.size() > 0) {
				tb15 = (Tb15_docflow) tmpList.get(0);
			}

			if(paraMap.containsKey("to_user")){
				if(paraMap.get("to_user") instanceof List ){
					userList =(List<Ta03_user>)paraMap.get("to_user");
				}else {
					userList = new LinkedList<Ta03_user>();
					userList.add((Ta03_user)paraMap.get("to_user"));
				}
				
			}
			if(paraMap.containsKey("to_group")){
				to_group = (Ta05_group)paraMap.get("to_group");
			}
		
		//***********************************发送参数数据一致性校验,如不一致返回*****************************/
			StringBuffer argAcc = new StringBuffer();
			//tb03.source_id 是否和 tb12.node_id 一致
			if(!tb03.getSource_id().equals(tb12.getNode_id())){
				argAcc.append(" opernode_id,relation_id 参数不一致<br/>");
			}
			
			if(tb12.getProject_id().equals(MapUtil.getLong(paraMap, "project_id"))
					&&tb12.getDoc_id().equals(MapUtil.getLong(paraMap, "doc_id"))
					&&tb12.getModule_id().equals(MapUtil.getLong(paraMap, "module_id"))){
				;
			} else {
				argAcc.append(" module_id,doc_id,project_id 参数不一致<br/>");
			}

			//目标人员是否拥有权限。
			if(userList != null && userList.size()> 0 ){
				hsql.delete(0, hsql.length());
				hsql.append("select  1 from Ta11_sta_user ta11,Ta13_sta_node ta13 ");
				hsql.append("where ta11.station_id = ta13.station_id and ta13.node_id = ? and ta11.user_id = ?");
				for(Ta03_user user:userList){
					tmpList = queryService.searchList(hsql.toString(), new Object[]{tb03.getDest_id(),user.getId()});
					if(tmpList.size()<1){
						argAcc.append(" user_id 参数错误，目标用户没有权限");
						break;
					}
				}
			}
			
			if(argAcc.length() > 0){
				String warnMessage = "操作失败，<br>" + argAcc.toString() + "请刷新后重试！";
				modelMap.put("warnMessage", warnMessage);
				log.warn(warnMessage);
				return new ModelAndView(FlowConstant.CTR_OPENFORM,modelMap);
			}
			
		//************************************发送请求 是否重复提交，重复提交系统不予处理******************************/
			hsql.delete(0, hsql.length());
			hsql.append("select 'x' from  Tb13_operrelation tb13 where tb13.relation_id = ? and tb13.source_id =? ");
			tmpList = queryService.searchList(hsql.toString(), new Object[]{tb03.getId(),tb12.getId()});
			if(tmpList.size()> 0){
				String warnMessage = "操作已完成，不必二次操作!";
				modelMap.put("warnMessage", warnMessage);
				log.warn(warnMessage);
				return new ModelAndView(FlowConstant.CTR_OPENFORM,modelMap);
			}

			
		// *******************************处理发送目标节点*********************************/
			// 插入Tb12
			target_tb12 = new Tb12_opernode();
			target_tb12.setNode_id(target_tb02.getId());
			target_tb12.setNode_name(target_tb02.getName());
			target_tb12.setNode_type(target_tb02.getNode_type());
			target_tb12.setNode_ext(target_tb02.getExt());
			target_tb12.setRollback_nodes(target_tb02.getRollback_nodes());
			target_tb12.setDoc_id(tb12.getDoc_id());
			target_tb12.setProject_id(tb12.getProject_id());
			target_tb12.setModule_id(tb12.getModule_id());
			target_tb12.setNode_status(FlowConstant.NODE_STATUS_NEED);
			target_tb12.setStart_time(new Date());
			session.saveOrUpdate(target_tb12);
			
			/**
			 * 处理插入tb15
			 */
			// 插入tb15,如果userList 不为空，user_id = user.getID(),group_id = -1
			if(userList != null &&userList.size()> 0){
				for(Ta03_user user:userList){
					target_tb15 = new Tb15_docflow();
					target_tb15.setDoc_id(target_tb12.getDoc_id());
					target_tb15.setProject_id(target_tb12.getProject_id());
					target_tb15.setModule_id(target_tb12.getModule_id());
					target_tb15.setNode_id(target_tb12.getNode_id());
					target_tb15.setWrite_limit(new Integer(0));
					target_tb15.setDoc_status(FlowConstant.NODE_STATUS_NEED);
					target_tb15.setOpernode_id(target_tb12.getId());
					target_tb15.setOper_user(MapUtil.getString(paraMap, "user_name"));
					target_tb15.setOper_status("待复");
					target_tb15.setUser_id(user.getId());
					target_tb15.setHuser_id(user.getId());
					target_tb15.setGroup_id(new Long(-1));
					session.saveOrUpdate(target_tb15);			
				}
				//如果to_group 不为空，则group_id=to_group.getId();user_id为-1
			} else if(to_group != null){
				target_tb15 = new Tb15_docflow();
				target_tb15.setDoc_id(target_tb12.getDoc_id());
				target_tb15.setProject_id(target_tb12.getProject_id());
				target_tb15.setModule_id(target_tb12.getModule_id());
				target_tb15.setNode_id(target_tb12.getNode_id());
				target_tb15.setWrite_limit(new Integer(0));
				target_tb15.setDoc_status(FlowConstant.NODE_STATUS_NEED);
				target_tb15.setOpernode_id(target_tb12.getId());
				target_tb15.setOper_user(MapUtil.getString(paraMap, "user_name"));
				target_tb15.setOper_status("待复");
				target_tb15.setUser_id(new Long(-1));
				target_tb15.setHuser_id(new Long(-1));
				target_tb15.setGroup_id(to_group.getId());
				session.saveOrUpdate(target_tb15);	
				//如果to_user,to_group 都为空，则发送给全体有权限的人员(user_id = -1,group_id = -1)
			} else {
				target_tb15 = new Tb15_docflow();
				target_tb15.setDoc_id(target_tb12.getDoc_id());
				target_tb15.setProject_id(target_tb12.getProject_id());
				target_tb15.setModule_id(target_tb12.getModule_id());
				target_tb15.setNode_id(target_tb12.getNode_id());
				target_tb15.setWrite_limit(new Integer(0));
				target_tb15.setDoc_status(FlowConstant.NODE_STATUS_NEED);
				target_tb15.setOpernode_id(target_tb12.getId());
				target_tb15.setOper_user(MapUtil.getString(paraMap, "user_name"));
				target_tb15.setOper_status("待复");
				target_tb15.setUser_id(new Long(-1));
				target_tb15.setHuser_id(new Long(-1));
				target_tb15.setGroup_id(new Long(-1));
				session.saveOrUpdate(target_tb15);		
			}
			
		// ********************************************发送节点处理*************************************************/
			/**
			 * 当前节点处理成发送
			 */
			// 更新tb12
			if (!FlowConstant.SEND_TYPE_INFO.equals(tb03.getRelation_type())) {
				tb12.setEnd_time(new Date());
				tb12.setNode_status(FlowConstant.NODE_STATUS_SEND);
				session.saveOrUpdate(tb12);
				// 更新tb15

				if (tb15 != null) {
					tb15.setAccept_time(new Date());
					tb15.setOper_status("待办");
					tb15.setOper_user(userList == null ? target_tb02.getName() : userList.get(0).getName());
					tb15.setDoc_status(FlowConstant.NODE_STATUS_SEND);
					tb15.setHWrite_limit(tb15.getWrite_limit());
					tb15.setWrite_limit(new Integer(0));
					session.saveOrUpdate(tb15);
				}
			}
		// *******************************处理发送************************************/
			/**
			 * 插入tb13
			 */
			tb13 = new Tb13_operrelation();
			tb13.setSource_id(tb12.getId());
			tb13.setDest_id(target_tb12.getId());
			tb13.setDoc_id(tb12.getDoc_id());
			tb13.setModule_id(tb12.getModule_id());
			tb13.setProject_id(tb12.getProject_id());
			tb13.setOper_time(new Date());
			tb13.setRelation_id(tb03.getId());
			tb13.setRelation_name(tb03.getName());
			tb13.setSeq(tb03.getSeq());
			session.saveOrUpdate(tb13);
			
			session.flush();
			tx.commit(); //提交事务;
		}catch(Exception e){
			if(tx != null)tx.rollback(); //回滚事务;
			log.error("文档发送出错", e);
			throw new Exception("文档发送出错",e);
		} finally{
			if(session != null){
				session.close();
			}
		}

		//************************发送事件要触发 事务	************************/ 
		paraMap.put("even_id",tb03.getId());
		paraMap.put("even_type", FlowConstant.EVEN_TYPE_SEND);
		if(doTrigger != null){
			doTrigger.findAndExcute(paraMap);
		}

		//*************************发送成功提醒信息 ****************************/
		Ta06_module ta06 = (Ta06_module)queryService.searchById(Ta06_module.class, target_tb12.getModule_id());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		StringBuffer toUserNames = new StringBuffer();
		//获取接收人员
		if(userList != null){
			for(Ta03_user user:userList){
				toUserNames.append(",");
				toUserNames.append("[" + user.getLogin_id()+"]");
				toUserNames.append(user.getName());
			}
		} else if(to_group != null){
			userList = baseUtil.getUser(target_tb12.getNode_id(), new Long[]{to_group.getId()});
			for(Ta03_user user:userList){
				toUserNames.append(",");
				toUserNames.append("[" + user.getLogin_id()+"]");
				toUserNames.append(user.getName());
			}
		} else {
			userList = baseUtil.getUser(target_tb12.getNode_id());
			for(Ta03_user user:userList){
				toUserNames.append(",");
				toUserNames.append("[" + user.getLogin_id()+"]");
				toUserNames.append(user.getName());
			}
		}
		
		StringBuffer warnMessage = new StringBuffer();
		if(ta06 != null){
			//warnMessage.append(ta06.getName());
		}
		warnMessage.append("发送成功!");
		warnMessage.append("      发送时间：");
		warnMessage.append(sdf.format(new Date()));
		if(toUserNames.length() > 0){
			toUserNames.deleteCharAt(0);
			warnMessage.append("      受理人员：");
			warnMessage.append(toUserNames.toString());
		}
		
		modelMap.put("warnMessage", warnMessage.toString());
		return new ModelAndView(FlowConstant.CTR_OPENFORM,modelMap);
	}
}
