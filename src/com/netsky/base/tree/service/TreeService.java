package com.netsky.base.tree.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.netsky.base.tree.struct.Dot;
import com.netsky.base.tree.struct.LineWord;
import com.netsky.base.tree.struct.Node;
import com.netsky.base.tree.struct.NodeStruct;

/**
 * 流程显示服务
 * 
 * @author Chiang
 */
public interface TreeService {

	/**
	 * 获取流程根节点,project_id==null时,获取系统配置根节点
	 * 
	 * @param flow_id
	 * @param module_id
	 * @param project_id
	 * @param doc_id
	 * @return 流程根节点
	 * @throws Exception
	 */
	public NodeStruct getRoot(Long flow_id, Long module_id, Long project_id, Long doc_id) throws Exception;

	/**
	 * 获取表单流程根节点,project_id==null时,获取系统配置根节点
	 * 
	 * @param flow_id
	 * @param project_id
	 * @return 流程根节点
	 * @throws Exception
	 */
	public NodeStruct getFormRoot(Long flow_id, Long project_id) throws Exception;

	/**
	 * 获取所给节点下所有子节点
	 * 
	 * @param root
	 * @throws Exception
	 */
	public void treeStruct(NodeStruct root, Map<Long, List<NodeStruct>> realNodes, Map<Long, List<NodeStruct>> sysNodes)
			throws Exception;

	/**
	 * 获取除根节点外所有节点与关系
	 * 
	 * @param module_id
	 * @param project_id
	 * @param doc_id
	 * @return Map<父节点id, 子节点>
	 * @throws Exception
	 */
	public Map<Long, List<NodeStruct>> getRealNodes(Long module_id, Long project_id, Long doc_id) throws Exception;
	
	/**
	 * 获取除根节点外所有表单节点与关系
	 * 
	 * @param project_id
	 * @return Map<父节点id, 子节点>
	 * @throws Exception
	 */
	public Map<Long, List<NodeStruct>> getRealFormNodes(Long project_id) throws Exception;

	/**
	 * 获取系统配置流程除根节点外所有节点与关系
	 * 
	 * @param flow_id
	 * @return Map<父节点id, 子节点>
	 * @throws Exception
	 */
	public Map<Long, List<NodeStruct>> getSysNodes(Long flow_id) throws Exception;
	
	/**
	 * 获取系统配置流程除根节点外所有表单节点与关系
	 * 
	 * @param flow_id
	 * @return Map<父节点id, 子节点>
	 * @throws Exception
	 */
	public Map<Long, List<NodeStruct>> getSysFormNodes(Long flow_id) throws Exception;

	/**
	 * 完善节点信息
	 * 
	 * @param root
	 * @param startX
	 *            起始位置x坐标，调用时输入0
	 * @param floor
	 *            节点所在层，调用时输入0
	 * @param node_list
	 *            存放节点显示信息
	 * @param line_list
	 *            存放路径显示信息
	 * @return
	 * @throws Exception
	 */
	public int showTree(NodeStruct root, int startX, int floor, List<Node> node_list, List<Dot> line_list,List<LineWord> word_list)
			throws Exception;

	/**
	 * 设置节点显示配置
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public void setNodeConfig(HttpServletRequest request) throws Exception;

	/**
	 * 获取状态对应样式表
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public void setStatusClass(HttpServletRequest request) throws Exception;
}
