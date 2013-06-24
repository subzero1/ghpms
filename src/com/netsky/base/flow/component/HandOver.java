package com.netsky.base.flow.component;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.dataObjects.Ta02_station;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb15_docflow;
import com.netsky.base.dataObjects.Tb17_approve;
import com.netsky.base.flow.FlowConstant;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

/**
 * @description:
 * 转发，移交处理，把自己在办的文档转发给同
 * @class name:com.netsky.base.flow.component.HandOver
 * @author wind Jan 29, 2010
 */
@Component
public class HandOver {
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
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * @param paraMap
	 * 须有：opernode_id，node_id，to_user（如果为空返回选择用户界面）
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(Map paraMap) throws Exception {
		StringBuffer hsql = new StringBuffer();
		Ta03_user user = null;
		Tb15_docflow tb15 = null;
		ModelMap modelMap = new ModelMap();

		Session session = null; //事务相关
		Transaction tx = null;//事务相关
		try {
			session = saveService.getHiberbateSession();
			tx = session.beginTransaction();
			tx.begin(); //开始事务

			// ***************************转到审批页面***********************************/
			/**
			 * 如果参数中未包含 check_result 转到审批页面
			 */
			user = (Ta03_user) queryService.searchById(Ta03_user.class, MapUtil.getLong(paraMap, "to_user"));
			//获得当前节点Tb15
			hsql.append("select  tb15 from Tb15_docflow tb15 ");
			hsql.append(" where tb15.user_id = ? and tb15.opernode_id = ?  ");
			List<?> tmpList = queryService.searchList(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "user_id"),
					MapUtil.getLong(paraMap, "opernode_id") });
			if (tmpList.size() > 0) {
				tb15 = (Tb15_docflow) tmpList.get(0);
			}
			tmpList.clear();

			if (user == null) {
				List<Ta03_user> to_users = new LinkedList<Ta03_user>();
				if (tb15.getGroup_id() == null || tb15.getGroup_id().longValue() == -1) {
					to_users = baseUtil.getUser(tb15.getNode_id());
				} else {
					to_users = baseUtil.getUser(tb15.getNode_id(), new Long[] { tb15.getGroup_id() });
				}
				//把当前节点除去
				for (Ta03_user user1 : to_users) {
					if (MapUtil.getLong(paraMap, "user_id").equals(user1.getId())) {
						to_users.remove(user1);
						break;
					}
				}

				if (to_users.size() == 1) {
					user = to_users.get(0);
				} else if (to_users.size() > 1) {
					modelMap.put("to_user", to_users);
					return new ModelAndView(FlowConstant.VIEW_SEND, modelMap);
				} else {
					session.flush();
					tx.commit(); // 提交事务;
					//如果开节点只有一个人可以受理，不能移交
					modelMap.put("warnMessage", "操作失败， 本节点其它人员无处理权限!");
					return new ModelAndView(FlowConstant.CTR_OPENFORM, modelMap);
				}
			}

			// ***************************移交处理***********************************/
			// 取得审批岗位
			hsql.delete(0, hsql.length());
			hsql.append("select ta02 from Ta02_station ta02,Ta11_sta_user ta11,Ta13_sta_node ta13 ");
			hsql.append(" where ta02.id =  ta11.station_id  and ta11.station_id = ta13.station_id ");
			hsql.append(" and ta11.user_id = ? and ta13.node_id = ? order by ta02.id ");
			tmpList = queryService.searchList(hsql.toString(), new Object[] { MapUtil.getLong(paraMap, "node_id"),
					MapUtil.getLong(paraMap, "user_id") });
			Ta02_station ta02 = null;
			if (tmpList.size() > 0) {
				ta02 = (Ta02_station) tmpList.get(0);
			}

			// 获得preTb12_id
			Tb17_approve tb17 = new Tb17_approve();
			tb17.setProject_id(tb15.getProject_id());
			tb17.setNode_id(tb15.getNode_id());
			tb17.setDoc_id(tb15.getDoc_id());
			tb17.setModule_id(tb15.getModule_id());
			tb17.setOpernode_id(tb15.getOpernode_id());
			tb17.setCheck_idea("文档转至[" + user.getName() + "]在办中");
			tb17.setCheck_result(new Integer(-1));
			tb17.setResult_str("转交");
			tb17.setOper_time(new Date());
			tb17.setUser_id(MapUtil.getLong(paraMap, "user_id"));
			tb17.setUser_name(MapUtil.getString(paraMap, "user_name"));
			tb17.setStation(ta02 == null ? "" : ta02.getName());
			saveService.save(tb17);

			//更新tb15
			tb15.setUser_id(user.getId());
			tb15.setHuser_id(user.getId());
			saveService.save(tb15);

			session.flush();
			tx.commit(); // 提交事务;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback(); // 回滚事务;
			log.error(" 取消会审处理出错！", e);
			throw new Exception("取消会审处理出错！", e);
		}finally{
			if(session != null){
				session.close();
			}
		}
		modelMap.put("warnMessage", "转交成功!");
		return new ModelAndView(FlowConstant.CTR_OPENFORM, modelMap);
	}

}
