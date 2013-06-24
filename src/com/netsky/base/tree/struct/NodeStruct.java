package com.netsky.base.tree.struct;

import java.util.ArrayList;
import java.util.List;

/**
 * 节点数据结构
 * 
 * @author Chiang
 */
public class NodeStruct {

	/**
	 * x坐标
	 */
	private int x;

	/**
	 * y坐标
	 */
	private int y;

	/**
	 * 节点所占宽度开始坐标
	 */
	private int startX;

	/**
	 * 节点以及子树所占宽度
	 */
	private int width;

	/**
	 * 节点id,Tb02_node.id
	 */
	private Long tb02_id;

	/**
	 * 节点id,Tb12_opernode.id
	 */
	private Long tb12_id;

	/**
	 * module_id
	 */
	private Long module_id;

	/**
	 * 工程id
	 */
	private Long project_id;

	/**
	 * 表单id
	 */
	private Long doc_id;

	/**
	 * 节点名称
	 */
	private String node_name;

	/**
	 * 节点状态,-1为未走节点
	 */
	private Integer node_status;

	/**
	 * 关系名称
	 */
	private String relation_name;

	/**
	 * 子节点list
	 */
	private List<NodeStruct> children_list = new ArrayList<NodeStruct>();

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the startX
	 */
	public int getStartX() {
		return startX;
	}

	/**
	 * @param startX
	 *            the startX to set
	 */
	public void setStartX(int startX) {
		this.startX = startX;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the tb02_id
	 */
	public Long getTb02_id() {
		return tb02_id;
	}

	/**
	 * @param tb02_id
	 *            the tb02_id to set
	 */
	public void setTb02_id(Long tb02_id) {
		this.tb02_id = tb02_id;
	}

	/**
	 * @return the tb12_id
	 */
	public Long getTb12_id() {
		return tb12_id;
	}

	/**
	 * @param tb12_id
	 *            the tb12_id to set
	 */
	public void setTb12_id(Long tb12_id) {
		this.tb12_id = tb12_id;
	}

	/**
	 * @return the module_id
	 */
	public Long getModule_id() {
		return module_id;
	}

	/**
	 * @param module_id
	 *            the module_id to set
	 */
	public void setModule_id(Long module_id) {
		this.module_id = module_id;
	}

	/**
	 * @return the project_id
	 */
	public Long getProject_id() {
		return project_id;
	}

	/**
	 * @param project_id
	 *            the project_id to set
	 */
	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	/**
	 * @return the doc_id
	 */
	public Long getDoc_id() {
		return doc_id;
	}

	/**
	 * @param doc_id
	 *            the doc_id to set
	 */
	public void setDoc_id(Long doc_id) {
		this.doc_id = doc_id;
	}

	/**
	 * @return the node_name
	 */
	public String getNode_name() {
		return node_name;
	}

	/**
	 * @param node_name
	 *            the node_name to set
	 */
	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}

	/**
	 * @return the node_status
	 */
	public Integer getNode_status() {
		return node_status;
	}

	/**
	 * @param node_status
	 *            the node_status to set
	 */
	public void setNode_status(Integer node_status) {
		this.node_status = node_status;
	}

	/**
	 * @return the relation_name
	 */
	public String getRelation_name() {
		return relation_name;
	}

	/**
	 * @param relation_name
	 *            the relation_name to set
	 */
	public void setRelation_name(String relation_name) {
		this.relation_name = relation_name;
	}

	/**
	 * 添加子节点关系
	 */
	public void addChild(NodeStruct child) {
		children_list.add(child);
	}

	/**
	 * @return the children_list
	 */
	public List<NodeStruct> getChildren_list() {
		return children_list;
	}

	public boolean equals(Object obj) {
		if (obj instanceof NodeStruct) {
			NodeStruct n = (NodeStruct) obj;
			if ((this.tb02_id == null && n.getTb02_id() == null)
					|| this.tb02_id.longValue() != n.getTb02_id().longValue())
				return false;
			if ((this.tb12_id == null && n.getTb12_id() == null)
					|| this.tb12_id.longValue() != n.getTb12_id().longValue())
				return false;
			return true;
		} else {
			return false;
		}
	}
}
