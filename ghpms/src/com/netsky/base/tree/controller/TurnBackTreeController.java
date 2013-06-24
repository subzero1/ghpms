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

import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.tree.service.TreeService;
import com.netsky.base.tree.struct.Dot;
import com.netsky.base.tree.struct.LineWord;
import com.netsky.base.tree.struct.Node;
import com.netsky.base.tree.struct.NodeStruct;

/**
 * 回退流程显示
 * @author CT
 * @create 2010-02-25
 */
@Controller("/turnBackTree.do")
public class TurnBackTreeController implements org.springframework.web.servlet.mvc.Controller {

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
			
			//得到节点nodes ID
			Map<Long,Long> map = new HashMap<Long,Long>();  
			String nod=request.getParameter("nodes");
			Long []nodes  = new Long[(nod.split(",")).length];
			for(int i = 0; i < nodes.length; i++){
				nodes[i] = Long.valueOf(nod.split(",")[i]);
				map.put(nodes[i], nodes[i]);
			}
			
			//得到回退节点分布的子流程
			StringBuffer hsql = new StringBuffer(" select distinct  tb01.id,tb01.name from Tb02_node tb02,Tb01_flow tb01  ");
			hsql.append(" where  tb02.flow_id = tb01.id");
			hsql.append(" and tb02.id in(");
			hsql.append(nod);
			hsql.append(") order by tb01.id desc");
			List flowList = queryService.searchList(hsql.toString());
			if(flowList.size()>0&&!"onChange".equals(request.getParameter("fromType"))){
				flow_id = (Long)((Object[])flowList.get(0))[0];
			}
			if(flowList.size()> 1){
				request.setAttribute("flowList", flowList);
				request.setAttribute("flow_id", flow_id);
				request.setAttribute("nodes", nod);
			}
			
			
			

			/**
			 * 获取根节点
			 */
			root = treeService.getRoot(flow_id, null, null, null);
			/**
			 * 构建树
			 */
			Map<Long, List<NodeStruct>> realNodes = treeService.getRealNodes(null, null, null);

			Map<Long, List<NodeStruct>> sysNodes = treeService.getSysNodes(flow_id);

			/**
			 * -------------------------遍历树------------------------------
			 */
	
			//判断根节点
			if(map.containsKey(root.getTb02_id())){
				root.setNode_status(new Integer(1));//状态改为1
			}
			
			Map<Long, List<NodeStruct>> sysNodes_map = new HashMap<Long, List<NodeStruct>>();
			for(Long o  : sysNodes.keySet()){    
			   Long key = o;
			   List list =	sysNodes.get(o);  
			   List nodeStruct_list = new ArrayList();
			   for(int i=0;i<list.size();i++){
				   NodeStruct nodeStruct =(NodeStruct)list.get(i);
				   if(map.containsKey(nodeStruct.getTb02_id())){
					   nodeStruct.setNode_status(new Integer(1));//状态改为1
				   }
				   nodeStruct_list.add(nodeStruct);
			   }
			   sysNodes_map.put(key, nodeStruct_list);
			} 
			/**
			 * ----------------------------------------------------------
			 */
			
			treeService.setNodeConfig(request);
			treeService.setStatusClass(request);
			//treeService.treeStruct(root, realNodes, sysNodes);
			treeService.treeStruct(root, realNodes, sysNodes_map);
			List<Node> node_list = new ArrayList<Node>();
			List<Dot> line_list = new ArrayList<Dot>();
			List<LineWord> word_list = new ArrayList<LineWord>();
			treeService.showTree(root, 0, 0, node_list, line_list, word_list);
			request.setAttribute("node_list", node_list);
			request.setAttribute("line_list", line_list);
			request.setAttribute("word_list", word_list);
		} catch (Exception e) {
			return exceptionService.exceptionControl("TurnBackTreeController", "流程显示", e);
		}
		return new ModelAndView("/WEB-INF/jsp/flow/showtrun_tree.jsp");
	}

}
