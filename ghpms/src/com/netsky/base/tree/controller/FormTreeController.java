package com.netsky.base.tree.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta04_role;
import com.netsky.base.dataObjects.Tb01_flow;
import com.netsky.base.dataObjects.Tb02_node;
import com.netsky.base.dataObjects.Tb04_flowgroup;
import com.netsky.base.dataObjects.Tb11_operflow;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.tree.service.TreeService;
import com.netsky.base.tree.struct.Dot;
import com.netsky.base.tree.struct.LineWord;
import com.netsky.base.tree.struct.Node;
import com.netsky.base.tree.struct.NodeStruct;
import com.netsky.base.flow.vo.ProjectInf;
/**
 * 显示表单流程
 * 
 * @author Chiang
 */
@Controller("/showFormTree.do")
public class FormTreeController implements org.springframework.web.servlet.mvc.Controller {
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

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		NodeStruct root;
		try {
			/**
			 * 构建流程数关系，获取数据
			 */
			Long flow_id;// 总流程id
			Long project_id;// 工程id
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
				if (request.getParameter("project_id") != null && request.getParameter("project_id").length() > 0) {
					project_id = Long.valueOf(request.getParameter("project_id"));
				} else {
					project_id = null;
				}
			} catch (NumberFormatException e) {
				throw new Exception("错误的工程id格式！" + e);
			}
			/**
			 * 获取根节点
			 */
			root = treeService.getFormRoot(flow_id, project_id);

			Map<Long, List<NodeStruct>> realNodes = treeService.getRealFormNodes(project_id);

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
				}
			}

			Map<Long, List<NodeStruct>> sysNodes = treeService.getSysFormNodes(flow_id);
			treeService.setNodeConfig(request);
			treeService.setStatusClass(request);
			treeService.treeStruct(root, realNodes, sysNodes);
			List<Node> node_list = new ArrayList<Node>();
			List<Dot> line_list = new ArrayList<Dot>();
			List<LineWord> word_list = new ArrayList<LineWord>();
			treeService.showTree(root, 0, 0, node_list, line_list, word_list);

			/**
			 * 获取其他显示信息
			 */
			String title = "";
			List<?> flowList = null;
			if (project_id != null) {
				/**
				 * 标题信息
				 */
				ProjectInf td00 = (ProjectInf) queryService.searchById(ProjectInf.class, project_id);
				title = td00.getXmmc();

			} else {
				/**
				 * 标题信息
				 */
				Tb01_flow tb01 = (Tb01_flow) queryService.searchById(Tb01_flow.class, root.getTb02_id());
				if (tb01.getType().longValue() != 0) {
					Tb04_flowgroup tb04;
					QueryBuilder queryBuilder = new HibernateQueryBuilder(Tb04_flowgroup.class);
					queryBuilder.eq("cflow_id", tb01.getId());
					ResultObject ro = queryService.search(queryBuilder);
					if (ro.next()) {
						tb04 = (Tb04_flowgroup) ro.get(Tb04_flowgroup.class.getName());
						tb01 = (Tb01_flow) queryService.searchById(Tb01_flow.class, tb04.getPflow_id());
					}
				}
				title = tb01.getName();
				String HSql = " select tb01 from Tb01_flow tb01 where tb01.type=0";
				flowList = queryService.searchList(HSql);
			}
			Map<String, Ta04_role> rolesMap = (Map<String, Ta04_role>) request.getSession().getAttribute("rolesMap");
			if (rolesMap != null && rolesMap.get("900202") != null) {
				request.setAttribute("admin", "effacement=supressao");
			}
			List<NodeStruct> forms = new ArrayList<NodeStruct>();
			forms.add(root);
			Iterator<Long> it = realNodes.keySet().iterator();
			while (it.hasNext()) {
				Long l = it.next();
				List<NodeStruct> list = realNodes.get(l);
				for (int i = 0; i < list.size(); i++) {
					forms.add(list.get(i));
				}
			}
			for (int i = 0; i < forms.size() - 1; i++) {
				for (int j = 0; j < forms.size() - i - 1; j++) {
					if (forms.get(j).getModule_id() > forms.get(j + 1).getModule_id()) {
						NodeStruct n = forms.get(j + 1);
						forms.set(j + 1, forms.get(j));
						forms.set(j, n);
					}
				}
			}
			if (project_id != null) {
				Tb11_operflow tb11;
				QueryBuilder queryBuilder = new HibernateQueryBuilder(Tb11_operflow.class);
				queryBuilder.eq("project_id", project_id);
				List<?> list = queryService.searchList(queryBuilder);
				if (list.size() > 0) {
					tb11 = (Tb11_operflow) list.get(0);
					title += "（" + tb11.getName() + "）";
				}
			}
			request.setAttribute("forms", forms);
			request.setAttribute("title", title);
			request.setAttribute("flowList", flowList);
			request.setAttribute("node_list", node_list);
			request.setAttribute("line_list", line_list);
			request.setAttribute("word_list", word_list);
		} catch (Exception e) {
			return exceptionService.exceptionControl("TreeController", "流程显示", e);
		}
		return new ModelAndView("/WEB-INF/jsp/tree/show_form_tree.jsp");
	}

}
