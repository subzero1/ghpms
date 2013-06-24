package com.netsky.base.tree.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta04_role;
import com.netsky.base.dataObjects.Tb01_flow;
import com.netsky.base.dataObjects.Tb04_flowgroup;
import com.netsky.base.dataObjects.Tb11_operflow;
import com.netsky.base.dataObjects.Tb12_opernode;
import com.netsky.base.flow.vo.ProjectInf;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.tree.service.TreeService;
import com.netsky.base.tree.struct.Dot;
import com.netsky.base.tree.struct.LineWord;
import com.netsky.base.tree.struct.Node;
import com.netsky.base.tree.struct.NodeStruct;
import com.netsky.base.flow.vo.ProjectInf;

/**
 * 流程显示
 * 
 * @author Chiang
 */
@Controller("/showTree.do")
public class TreeController implements org.springframework.web.servlet.mvc.Controller {

	@Autowired
	private QueryService queryService;

	@Autowired
	private TreeService treeService;

	@Autowired
	private ExceptionService exceptionService;

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

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		NodeStruct root;
		try {
			/**
			 * 构建流程数关系，获取数据
			 */
			Long flow_id;// 总流程id
			Long module_id;// module_id
			Long project_id;// 工程id
			Long doc_id;// 表单id

			try {
				
				
				if (request.getParameter("flow_id") != null && request.getParameter("flow_id").length() > 0) {
					flow_id = Long.valueOf(request.getParameter("flow_id"));
				} else {
					flow_id = null;
				}
			} catch (NumberFormatException e) {
				throw new Exception("错误的流程id格式！" + e);
			}
			try {
				if (request.getParameter("module_id") != null && request.getParameter("module_id").length() > 0) {
					module_id = Long.valueOf(request.getParameter("module_id"));
				} else {
					module_id = null;
				}
			} catch (NumberFormatException e) {
				throw new Exception("错误的module_id格式！" + e);
			}
			try {
				if (request.getParameter("project_id") != null && request.getParameter("project_id").length() > 0) {
					project_id = Long.valueOf(request.getParameter("project_id"));
				} else {
					project_id = null;
				}
			} catch (NumberFormatException e) {
				throw new Exception("错误的工程id格式！" + e);
			}
			try {
				if (request.getParameter("doc_id") != null && request.getParameter("doc_id").length() > 0) {
					doc_id = Long.valueOf(request.getParameter("doc_id"));
				} else {
					doc_id = null;
				}
			} catch (NumberFormatException e) {
				throw new Exception("错误的doc_id格式！" + e);
			}
			
			/**
			 * 获取根节点
			 */
			root = treeService.getRoot(flow_id, module_id, project_id, doc_id);
			/**
			 * 构建树
			 */
			Map<Long, List<NodeStruct>> realNodes = treeService.getRealNodes(module_id, project_id, doc_id);
			/**
			 * 根据当前已走流程确定所走flow
			 */
			if (project_id != null) {
				Tb11_operflow tb11;
				QueryBuilder queryBuilder = new HibernateQueryBuilder(Tb11_operflow.class);
				queryBuilder.eq("project_id", project_id);
				List<?> result = queryService.searchList(queryBuilder);
				if (result.size() == 1) {
					tb11 = (Tb11_operflow) result.get(0);
					flow_id = tb11.getFlow_id();
					if (module_id != null) {
						Tb04_flowgroup tb04;
						queryBuilder = new HibernateQueryBuilder(Tb04_flowgroup.class);
						queryBuilder.eq("pflow_id", flow_id);
						queryBuilder.eq("module_id", module_id);
						result = queryService.searchList(queryBuilder);
						if (result.size() == 1) {
							tb04 = (Tb04_flowgroup) result.get(0);
							flow_id = tb04.getCflow_id();
						}
					}
				}
			}

			Map<Long, List<NodeStruct>> sysNodes = treeService.getSysNodes(flow_id);
			treeService.setNodeConfig(request);
			treeService.setStatusClass(request);
			treeService.treeStruct(root, realNodes, sysNodes);
			List<Node> node_list = new ArrayList<Node>();
			List<Dot> line_list = new ArrayList<Dot>();
			List<LineWord> word_list = new ArrayList<LineWord>();
			int width = treeService.showTree(root, 0, 0, node_list, line_list, word_list);

			/**
			 * 处理表单回退情况
			 */
			if (project_id != null && module_id != null && doc_id != null) {
				String HSql = "select tb12 from Tb12_opernode tb12 where project_id = " + project_id
						+ " and module_id = " + module_id + " and doc_id = " + doc_id
						+ " and cflow_root = 1 and id != " + root.getTb12_id();
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					root = new NodeStruct();
					Tb12_opernode tb12 = (Tb12_opernode) ro.get("tb12");
					root.setNode_name(tb12.getNode_name());
					root.setNode_status(tb12.getNode_status());
					root.setTb12_id(tb12.getId());
					root.setTb02_id(tb12.getNode_id());
					root.setProject_id(project_id);
					root.setDoc_id(doc_id);
					root.setModule_id(module_id);
					treeService.treeStruct(root, realNodes, sysNodes);
					width += treeService.showTree(root, width, 0, node_list, line_list, word_list);
				}
			}

			/**
			 * 获取其他显示信息
			 */
			String title = "";
			List<FormModules> moduleList = null;
			List<?> flowList = null;
			if (project_id != null) {
				/**
				 * 标题信息
				 */
				ProjectInf inf = (ProjectInf) queryService.searchById(ProjectInf.class, project_id);
				if (inf != null)
					title = inf.getXmmc();
				/**
				 * 当前显示树对应同级别表单
				 */
				String HSql = "select distinct tb12.project_id,tb12.module_id,tb12.doc_id,ta06.name from Tb12_opernode tb12,Ta06_module ta06 where tb12.module_id = ta06.id and tb12.project_id="
						+ project_id + " order by tb12.module_id asc";
				ResultObject ro = queryService.search(HSql);
				moduleList = new ArrayList<FormModules>();
				while (ro.next()) {
					FormModules f = new FormModules();
					f.setValue("?project_id=" + ro.get("tb12.project_id") + "&module_id=" + ro.get("tb12.module_id")
							+ "&doc_id=" + ro.get("tb12.doc_id"));
					f.setText(ro.get("ta06.name") + "");
					f.setModule_id(ro.get("tb12.module_id") + "");
					f.setDoc_id(ro.get("tb12.doc_id") + "");
					moduleList.add(f);
				}
			} else {
				/**
				 * 标题信息
				 */
				Tb01_flow tb01 = (Tb01_flow) queryService.searchById(Tb01_flow.class, flow_id);
				title = tb01.getName();
				/**
				 * 当前显示树对应系统配置表单列表
				 */
				if (tb01.getType().longValue() != 0) {
					/**
					 * 获取系统配置总流程
					 */
					Tb04_flowgroup tb04;
					QueryBuilder queryBuilder = new HibernateQueryBuilder(Tb04_flowgroup.class);
					queryBuilder.eq("cflow_id", flow_id);
					List<?> list = queryService.searchList(queryBuilder);
					if (list.size() > 0) {
						tb04 = (Tb04_flowgroup) list.get(0);
						tb01 = (Tb01_flow) queryService.searchById(Tb01_flow.class, tb04.getPflow_id());
					}
				}
				String HSql = " select tb01 from Tb01_flow tb01 where tb01.id in (select cflow_id from Tb04_flowgroup where pflow_id = "
						+ tb01.getId() + ") order by tb01.id asc";
				flowList = queryService.searchList(HSql);
			}
			Map<String, Ta04_role> rolesMap = (Map<String, Ta04_role>) request.getSession().getAttribute("rolesMap");
			if(rolesMap == null) rolesMap = new HashMap();
			
			if (rolesMap != null && rolesMap.get("900202") != null) {
				request.setAttribute("admin", "effacement=supressao");
			}
			if (project_id != null) {
				Tb11_operflow tb11;
				QueryBuilder queryBuilder = new HibernateQueryBuilder(Tb11_operflow.class);
				queryBuilder.eq("project_id", project_id);
				List<?> list = queryService.searchList(queryBuilder);
				if (list.size() > 0) {
					tb11 = (Tb11_operflow) list.get(0);
					title += "（" + tb11.getName().substring(2) + "）";
				}
			}
			request.setAttribute("title", title);
			request.setAttribute("moduleList", moduleList);
			request.setAttribute("flowList", flowList);
			request.setAttribute("node_list", node_list);
			request.setAttribute("line_list", line_list);
			request.setAttribute("word_list", word_list);
		} catch (Exception e) {
			return exceptionService.exceptionControl("TreeController", "流程显示", e);
		}
		return new ModelAndView("/WEB-INF/jsp/tree/show_tree.jsp");
	}

	public class FormModules {

		private String value;

		private String text;

		private String module_id;
		private String doc_id;

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * @param value
		 *            the value to set
		 */
		public void setValue(String value) {
			this.value = value;
		}

		/**
		 * @return the text
		 */
		public String getText() {
			return text;
		}

		/**
		 * @param text
		 *            the text to set
		 */
		public void setText(String text) {
			this.text = text;
		}

		/**
		 * @return the module_id
		 */
		public String getModule_id() {
			return module_id;
		}

		/**
		 * @param module_id
		 *            the module_id to set
		 */
		public void setModule_id(String module_id) {
			this.module_id = module_id;
		}
		
		/**
		 * @return the doc_id
		 */
		public String getDoc_id() {
			return doc_id;
		}

		/**
		 * @param module_id
		 *            the module_id to set
		 */
		public void setDoc_id(String doc_id) {
			this.doc_id = doc_id;
		}

	}

}
