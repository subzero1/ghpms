package com.netsky.base.tree.controller;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.JdbcSupport;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta04_role;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Ta17_module_info;
import com.netsky.base.dataObjects.Tb12_opernode;
import com.netsky.base.dataObjects.Tb15_docflow;
import com.netsky.base.dataObjects.Tb02_node;
import com.netsky.base.dataObjects.Tz04_node_del;
import com.netsky.base.flow.vo.ProjectInf;
import com.netsky.base.flow.vo.NeedWork;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.tree.service.TreeService;
import com.netsky.base.tree.struct.NodeStruct;
import com.netsky.base.utils.DateFormatUtil;

/**
 * 显示修改节点状态
 * 
 * @author Chiang
 * 
 */
@Controller
public class NodeInfoController {

	private static Map<Integer, String> status = new HashMap<Integer, String>();
	private static Map<Integer, String> approve_result = new HashMap<Integer, String>();
	static {
		status.put(new Integer(1), "新建");
		status.put(new Integer(2), "在办");
		status.put(new Integer(3), "发送");
		status.put(new Integer(4), "回复同意");
		status.put(new Integer(5), "回复修改");
		status.put(new Integer(6), "回复不同意");
		status.put(new Integer(7), "回复不发表意见");
		status.put(new Integer(8), "办结");
		approve_result.put(new Integer(-1), "默认");
		approve_result.put(new Integer(4), "同意");
		approve_result.put(new Integer(5), "修改");
		approve_result.put(new Integer(6), "不同意");
		approve_result.put(new Integer(7), "不发表意见");
	}

	@Autowired
	private QueryService queryService;

	@Autowired
	private SaveService saveService;

	@Autowired
	private TreeService treeService;

	@Autowired
	private ExceptionService exceptionService;

	@Autowired
	private JdbcSupport jdbcSupport;

	/**
	 * @return the queryService
	 */
	public QueryService getQueryService() {
		return queryService;
	}

	/**
	 * @param queryService
	 *            the queryService to set
	 */
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	/**
	 * @return the saveService
	 */
	public SaveService getSaveService() {
		return saveService;
	}

	/**
	 * @param saveService
	 *            the saveService to set
	 */
	public void setSaveService(SaveService saveService) {
		this.saveService = saveService;
	}

	/**
	 * @return the treeService
	 */
	public TreeService getTreeService() {
		return treeService;
	}

	/**
	 * @param treeService
	 *            the treeService to set
	 */
	public void setTreeService(TreeService treeService) {
		this.treeService = treeService;
	}

	/**
	 * @return the exceptionService
	 */
	public ExceptionService getExceptionService() {
		return exceptionService;
	}

	/**
	 * @param exceptionService
	 *            the exceptionService to set
	 */
	public void setExceptionService(ExceptionService exceptionService) {
		this.exceptionService = exceptionService;
	}

	/**
	 * @return the jdbcSupport
	 */
	public JdbcSupport getJdbcSupport() {
		return jdbcSupport;
	}

	/**
	 * @param jdbcSupport
	 *            the jdbcSupport to set
	 */
	public void setJdbcSupport(JdbcSupport jdbcSupport) {
		this.jdbcSupport = jdbcSupport;
	}

	@RequestMapping("/nodeInfo.do")
	public ModelAndView nodeInfo(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		try {
			Long tb12_id;
			if (request.getParameter("node_id") == null || request.getParameter("node_id").length() == 0)
				throw new Exception("缺少节点标识");
			try {
				tb12_id = Long.valueOf(request.getParameter("node_id"));
			} catch (NumberFormatException e) {
				throw new Exception("错误的节点标识格式" + e);
			}
			List<NodeInfo> list = new ArrayList<NodeInfo>();
			Tb12_opernode tb12 = (Tb12_opernode) queryService.searchById(Tb12_opernode.class, tb12_id);
			if (tb12 == null)
				throw new Exception("未找到节点");

			/**
			 * 处理非待办状态节点
			 */
			if (tb12.getNode_status().intValue() != 0) {
				Tb15_docflow tb15;
				QueryBuilder queryBuilder = new HibernateQueryBuilder(Tb15_docflow.class);
				queryBuilder.eq("opernode_id", tb12.getId());
				ResultObject ro = queryService.search(queryBuilder);
				while (ro.next()) {
					NodeInfo node = new NodeInfo();
					tb15 = (Tb15_docflow) ro.get(Tb15_docflow.class.getName());
					if (tb15.getAccept_time() != null)
						node.setAccept_time(DateFormatUtil.FormatTime(tb15.getAccept_time()));
					if (tb15.getApprove_result() != null) {
						node.setApprove_result(approve_result.get(tb15.getApprove_result()));
					}
					if (tb12.getEnd_time() != null)
						node.setEnd_time(DateFormatUtil.FormatTime(tb12.getEnd_time()));
					if (tb12.getNode_status() != null) {
						node.setNode_status(status.get(tb12.getNode_status()));
					}
					if (tb15.getOper_time() != null)
						node.setOper_time(DateFormatUtil.FormatTime(tb15.getOper_time()));
					Ta03_user ta03 = (Ta03_user) queryService.searchById(Ta03_user.class, tb15.getUser_id());
					if (ta03 != null)
						node.setOper_user(ta03.getName() + "[" + ta03.getLogin_id() + "]");
					if (tb12.getStart_time() != null)
						node.setStart_time(DateFormatUtil.FormatTime(tb12.getStart_time()));
					list.add(node);
					request.setAttribute("approve_result", tb15.getApprove_result());
				}
			} else {
				NeedWork needWork;
				QueryBuilder queryBuilder = new HibernateQueryBuilder(NeedWork.class);
				queryBuilder.eq("opernode_id", tb12.getId());
				ResultObject ro = queryService.search(queryBuilder);
				while (ro.next()) {
					needWork = (NeedWork) ro.get(NeedWork.class.getName());
					NodeInfo node = new NodeInfo();
					if (tb12.getStart_time() != null)
						node.setStart_time(DateFormatUtil.FormatTime(tb12.getStart_time()));
					node.setNode_status("待办");
					Ta03_user ta03 = (Ta03_user) queryService.searchById(Ta03_user.class, needWork.getUser_id());
					if (ta03 != null)
						node.setOper_user(ta03.getName() + "[" + ta03.getLogin_id() + "]");
					list.add(node);
				}
			}
			request.setAttribute("list", list);
			request.setAttribute("status", tb12.getNode_status());
			Map<String, Ta04_role> rolesMap = (Map<String, Ta04_role>) request.getSession().getAttribute("rolesMap");
			if (rolesMap != null && rolesMap.get("900202") != null) {
				request.setAttribute("admin", "true");
			}
		} catch (Exception e) {
			return exceptionService.exceptionControl("NodeInfoController", "节点信息", e);
		}
		return new ModelAndView("/WEB-INF/jsp/tree/nodeInfo.jsp");
	}

	@RequestMapping("/modifyNode.do")
	public void modifyNode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		try {
			Long tb12_id;
			if (request.getParameter("node_id") == null || request.getParameter("node_id").length() == 0)
				throw new Exception("缺少节点标识");
			try {
				tb12_id = Long.valueOf(request.getParameter("node_id"));
			} catch (NumberFormatException e) {
				throw new Exception("错误的节点标识格式" + e);
			}
			Tb12_opernode tb12 = (Tb12_opernode) queryService.searchById(Tb12_opernode.class, tb12_id);
			if (tb12 == null)
				throw new Exception("未找到节点");
			String isRoot = "false";

			/**
			 * 修改节点状态
			 */
			if ("update".equals(request.getParameter("action"))) {
				tb12.setNode_status(Integer.valueOf(request.getParameter("status")));
				saveService.save(tb12);
				String HSql = "update Tb15_docflow set doc_status='" + request.getParameter("status")
						+ "',approve_result = '" + request.getParameter("approve_result") + "',oper_status='"
						+ status.get(tb12.getNode_status()) + "'";

				if (tb12.getNode_status().intValue() == 1 || tb12.getNode_status().intValue() == 1) {
					HSql += ",write_limit = 1";
				}
				HSql += " where opernode_id = " + tb12.getId();
				saveService.updateByHSql(HSql);

				response.getWriter().print(
						"{\"statusCode\":\"200\", \"message\":\"修改成功\", \"forwardUrl\":\"nodeInfo.do?node_id="
								+ tb12.getId() + "\", \"callbackType\":\"forward\",\"navTabId\":\"showTree\"}");
			} else if ("del".equals(request.getParameter("action"))) {
				/**
				 * 删除节点
				 */
				NodeStruct root = treeService.getRoot(null, null, tb12.getProject_id(), null);

				/**
				 * 记录删除日志
				 */
				Tz04_node_del tz04 = new Tz04_node_del();
				Ta03_user ta03 = (Ta03_user) request.getSession().getAttribute("user");
				tz04.setLogin_id(ta03.getLogin_id());
				tz04.setUser_name(ta03.getName());
				tz04.setNode_id(tb12.getNode_id());
				tz04.setNode_name(tb12.getNode_name());
				tz04.setOper_ip(request.getRemoteHost());
				tz04.setOper_date(new Date());

				ProjectInf td00 = (ProjectInf) queryService.searchById(ProjectInf.class, tb12.getProject_id());
				tz04.setGcbh(td00.getBdbh());
				tz04.setGcmc(td00.getXmmc());

				if (root.getTb12_id().longValue() == tb12.getId().longValue()) {
					isRoot = "true";
					/**
					 * 删除工程
					 */
					tz04.setAction("删除工程");
					saveService.save(tz04);

					Connection con = jdbcSupport.getConnection();
					con.setAutoCommit(false);
					String procedure = "{call delete_project(?,?,?)}";
					CallableStatement cstmt = con.prepareCall(procedure);
					cstmt.registerOutParameter(1, Types.VARCHAR);
					cstmt.setLong(2, root.getProject_id());
					cstmt.setString(3, "PSS_NJ");
					cstmt.executeUpdate();
					String result = cstmt.getString(1);
					if (!"success".equals(result)) {
						throw new Exception(result);
					}
					cstmt.close();
					con.commit();
					con.close();

				} else {
					tz04.setAction("删除节点");
					saveService.save(tz04);

					root = new NodeStruct();
					root.setNode_name(tb12.getNode_name());
					root.setNode_status(tb12.getNode_status());
					root.setTb12_id(tb12.getId());
					root.setTb02_id(tb12.getNode_id());
					root.setProject_id(tb12.getProject_id());
					root.setDoc_id(tb12.getDoc_id());
					root.setModule_id(tb12.getModule_id());
					Map<Long, NodeStruct> formNodes = new HashMap<Long, NodeStruct>();
					String HSql = " select tb12,tb13,tb12_s from Tb12_opernode tb12,Tb13_operrelation tb13,Tb12_opernode tb12_s where tb12.id=tb13.dest_id"
							+ " and tb13.source_id = tb12_s.id and tb13.relation_id = -1 and tb12.project_id = "
							+ tb12.getProject_id();
					ResultObject ro = queryService.search(HSql);
					while (ro.next()) {
						Tb12_opernode tb12_n = (Tb12_opernode) ro.get("tb12");
						NodeStruct node = new NodeStruct();
						node.setTb12_id(tb12_n.getId());
						formNodes.put(tb12_n.getId(), node);
					}
					Map<Long, List<NodeStruct>> realNodes = treeService.getRealNodes(null, tb12.getProject_id(), null);
					Map<Long, List<NodeStruct>> sysNodes = new HashMap<Long, List<NodeStruct>>();
					treeService.setNodeConfig(request);
					treeService.setStatusClass(request);
					treeService.treeStruct(root, realNodes, sysNodes);
					isRoot = delTree(root, formNodes);
				}
				response
						.getWriter()
						.print(
								"{\"statusCode\":\"200\", \"message\":\"删除成功\", \"callbackType\":\"closeCurrent\",\"navTabId\":\"showTree\",\"isRoot\":\""
										+ isRoot + "\"}");
			}
		} catch (Exception e) {
			response.getWriter().print("{\"statusCode\":\"300\", \"message\":\"操作失败:" + e.toString() + "\"}");
		}
	}

	/**
	 * 删除节点以及其子节点,如节点为当前表单根节点,则删除当前表单
	 * 
	 * @param root
	 * @param formNodes
	 */
	private String delTree(NodeStruct root, Map<Long, NodeStruct> formNodes) throws Exception {
		String isRoot = "false";
		String HSql;
		if (formNodes.get(root.getTb12_id()) != null) {
			isRoot = "true";
			/**
			 * 表单根节点
			 */
			Ta06_module ta06 = (Ta06_module) queryService.searchById(Ta06_module.class, root.getModule_id());
			if (ta06 != null) {
				Object formObj = queryService.searchById(Class.forName(ta06.getForm_table()), root.getDoc_id());
				QueryBuilder queryBuilder = new HibernateQueryBuilder(Ta17_module_info.class);
				queryBuilder.eq("module_id", root.getModule_id());
				queryBuilder.eq("type", "list");
				ResultObject ro = queryService.search(queryBuilder);
				while (ro.next()) {
					Ta17_module_info ta17 = (Ta17_module_info) ro.get(Ta17_module_info.class.getName());
					if ((ta17.getRelevance_table().substring(ta17.getRelevance_table().lastIndexOf(".") + 1))
							.equals(ta06.getForm_table().substring(ta06.getForm_table().lastIndexOf(".") + 1))) {
						HSql = "delete from "
								+ ta17.getObject_name().substring(ta17.getObject_name().lastIndexOf(".") + 1)
								+ " t where t." + ta17.getObject_column() + "="
								+ PropertyInject.getProperty(formObj, ta17.getRelevance_column());
						saveService.updateByHSql(HSql);
					}
				}
				HSql = "delete from " + ta06.getForm_table().substring(ta06.getForm_table().lastIndexOf(".") + 1)
						+ " where id = " + PropertyInject.getProperty(formObj, "id");
				saveService.updateByHSql(HSql);
			}
		}
		HSql = "delete from Tb12_opernode t where t.id = " + root.getTb12_id();
		saveService.updateByHSql(HSql);
		HSql = "delete from Tb13_operrelation t where t.dest_id = " + root.getTb12_id();
		saveService.updateByHSql(HSql);
		HSql = "delete from Tb15_docflow t where t.opernode_id = " + root.getTb12_id();
		saveService.updateByHSql(HSql);
		HSql = "delete from Tb17_approve t where t.opernode_id = " + root.getTb12_id();
		saveService.updateByHSql(HSql);
		for (int i = 0; i < root.getChildren_list().size(); i++) {
			delTree(root.getChildren_list().get(i), formNodes);
		}
		return isRoot;
	}

	public class NodeInfo {
		private String oper_user;

		private String accept_time;

		private String oper_time;

		private String start_time;

		private String end_time;

		private String approve_result;

		private String node_status;

		/**
		 * @return the oper_user
		 */
		public String getOper_user() {
			return oper_user;
		}

		/**
		 * @param oper_user
		 *            the oper_user to set
		 */
		public void setOper_user(String oper_user) {
			this.oper_user = oper_user;
		}

		/**
		 * @return the accept_time
		 */
		public String getAccept_time() {
			return accept_time;
		}

		/**
		 * @param accept_time
		 *            the accept_time to set
		 */
		public void setAccept_time(String accept_time) {
			this.accept_time = accept_time;
		}

		/**
		 * @return the oper_time
		 */
		public String getOper_time() {
			return oper_time;
		}

		/**
		 * @param oper_time
		 *            the oper_time to set
		 */
		public void setOper_time(String oper_time) {
			this.oper_time = oper_time;
		}

		/**
		 * @return the start_time
		 */
		public String getStart_time() {
			return start_time;
		}

		/**
		 * @param start_time
		 *            the start_time to set
		 */
		public void setStart_time(String start_time) {
			this.start_time = start_time;
		}

		/**
		 * @return the end_time
		 */
		public String getEnd_time() {
			return end_time;
		}

		/**
		 * @param end_time
		 *            the end_time to set
		 */
		public void setEnd_time(String end_time) {
			this.end_time = end_time;
		}

		/**
		 * @return the approve_result
		 */
		public String getApprove_result() {
			return approve_result;
		}

		/**
		 * @param approve_result
		 *            the approve_result to set
		 */
		public void setApprove_result(String approve_result) {
			this.approve_result = approve_result;
		}

		/**
		 * @return the node_status
		 */
		public String getNode_status() {
			return node_status;
		}

		/**
		 * @param node_status
		 *            the node_status to set
		 */
		public void setNode_status(String node_status) {
			this.node_status = node_status;
		}

	}

}
