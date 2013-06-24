package com.netsky.base.tree.serviceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Tb01_flow;
import com.netsky.base.dataObjects.Tb02_node;
import com.netsky.base.dataObjects.Tb03_relation;
import com.netsky.base.dataObjects.Tb04_flowgroup;
import com.netsky.base.dataObjects.Tb12_opernode;
import com.netsky.base.dataObjects.Tb13_operrelation;
import com.netsky.base.service.QueryService;
import com.netsky.base.tree.service.TreeService;
import com.netsky.base.tree.struct.Dot;
import com.netsky.base.tree.struct.LineWord;
import com.netsky.base.tree.struct.Node;
import com.netsky.base.tree.struct.NodeStruct;
import com.netsky.base.utils.NumberFormatUtil;

/**
 * 流程显示实现类
 * 
 * @author Chiang
 */
@Service("treeService")
public class TreeServiceImpl implements TreeService {

	/**
	 * 默认保存配置文件位置
	 */
	private static String configFilePath = "/treeConfig/treeConfig.xml";

	@Autowired
	private QueryService queryService;

	private Map<String, String> nodeConfig;

	private Map<String, String> statusClass;

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
	 * @return the nodeConfig
	 */
	public Map<String, String> getNodeConfig() {
		return nodeConfig;
	}

	/**
	 * @param nodeConfig
	 *            the nodeConfig to set
	 */
	public void setNodeConfig(Map<String, String> nodeConfig) {
		this.nodeConfig = nodeConfig;
	}

	/**
	 * @return the statusColor
	 */
	public Map<String, String> getStatusColor() {
		return statusClass;
	}

	/**
	 * @param statusColor
	 *            the statusColor to set
	 */
	public void setStatusColor(Map<String, String> statusColor) {
		this.statusClass = statusColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netsky.base.tree.service.TreeService#getRoot(java.lang.Long,
	 *      java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	public NodeStruct getRoot(Long flow_id, Long module_id, Long project_id, Long doc_id) throws Exception {
		QueryBuilder queryBuilder;
		ResultObject ro;
		NodeStruct root = new NodeStruct();
		if (project_id == null) {
			/**
			 * 获取系统流程根结点
			 */
			Tb01_flow tb01 = null;
			if (flow_id == null) {
				/**
				 * 未指定系统流程id，获取默认第一个总流程
				 */
				queryBuilder = new HibernateQueryBuilder(Tb01_flow.class);
				queryBuilder.eq("type", new Integer(0));
				queryBuilder.addOrderBy(Order.asc("id"));
				ro = queryService.search(queryBuilder);
				if (ro.next()) {
					tb01 = (Tb01_flow) ro.get(Tb01_flow.class.getName());
				}
			} else {
				tb01 = (Tb01_flow) queryService.searchById(Tb01_flow.class, flow_id);
			}
			if (tb01.getType().intValue() == 0) {
				Tb04_flowgroup tb04;
				queryBuilder = new HibernateQueryBuilder(Tb04_flowgroup.class);
				queryBuilder.eq("pflow_id", tb01.getId());
				queryBuilder.addOrderBy(Order.asc("seq"));
				ro = queryService.search(queryBuilder);
				if (ro.next()) {
					tb04 = (Tb04_flowgroup) ro.get(Tb04_flowgroup.class.getName());
					tb01 = (Tb01_flow) queryService.searchById(Tb01_flow.class, tb04.getCflow_id());
				}
			}
			if (tb01 != null) {
				Tb02_node tb02 = (Tb02_node) queryService
						.searchById(Tb02_node.class, Long.valueOf(tb01.getstartNode()));
				root.setNode_name(tb02.getName());
				root.setNode_status(new Integer(-1));
				root.setTb02_id(tb02.getId());
			} else {
				throw new Exception("未找到系统流程根节点：flow_id=" + flow_id + "。");
			}
		} else {
			/**
			 * 获取实际流程根结点
			 */
			queryBuilder = new HibernateQueryBuilder(Tb12_opernode.class);
			queryBuilder.eq("project_id", project_id);
			if (module_id != null) {
				if (doc_id == null)
					throw new Exception("缺少参数：doc_id");
				/**
				 * 指定表单流程
				 */
				queryBuilder.eq("module_id", module_id);
				queryBuilder.eq("doc_id", doc_id);
			}
			queryBuilder.addOrderBy(Order.asc("id"));
			ro = queryService.searchByPage(queryBuilder, 1, 1);
			if (ro.next()) {
				Tb12_opernode tb12 = (Tb12_opernode) ro.get(Tb12_opernode.class.getName());
				root.setNode_name(tb12.getNode_name());
				root.setNode_status(tb12.getNode_status());
				root.setTb12_id(tb12.getId());
				root.setTb02_id(tb12.getNode_id());
				root.setProject_id(project_id);
				root.setDoc_id(doc_id);
				root.setModule_id(module_id);
			} else {
				throw new Exception("未找到工程根节点！");
			}
		}
		return root;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netsky.base.tree.service.TreeService#treeStruct(com.netsky.base.tree.struct.NodeStruct)
	 */
	public void treeStruct(NodeStruct root, Map<Long, List<NodeStruct>> realNodes, Map<Long, List<NodeStruct>> sysNodes)
			throws Exception {
		if (root.getTb12_id() != null) {
			/**
			 * 获取实际流程子节点
			 */
			List<NodeStruct> list = realNodes.get(root.getTb12_id());
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					root.addChild((NodeStruct) PropertyInject.cloneObject(list.get(i)));
				}
			}
		}
		if (root.getTb02_id() != null) {
			/**
			 * 获取未走系统节点
			 */
			List<NodeStruct> list = sysNodes.get(root.getTb02_id());
			if (list != null) {
				List<NodeStruct> realList = root.getChildren_list();
				for (int i = 0; i < list.size(); i++) {
					boolean isReal = false;
					for (int j = 0; j < realList.size(); j++) {
						if (realList.get(j).getTb02_id().longValue() == list.get(i).getTb02_id().longValue()) {
							isReal = true;
							break;
						}
					}
					if (!isReal) {
						root.addChild(list.get(i));
					}
				}
			}
		}
		if (root.getChildren_list().size() > 0) {
			for (int i = 0; i < root.getChildren_list().size(); i++) {
				treeStruct(root.getChildren_list().get(i), realNodes, sysNodes);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netsky.base.tree.service.TreeService#getRealNodes(java.lang.Long,
	 *      java.lang.Long, java.lang.Long)
	 */
	public Map<Long, List<NodeStruct>> getRealNodes(Long module_id, Long project_id, Long doc_id) throws Exception {
		String HSql;
		ResultObject ro;
		Map<Long, List<NodeStruct>> result = new HashMap<Long, List<NodeStruct>>();
		if (project_id == null)
			return result;
		HSql = " select tb12,tb13 from Tb12_opernode tb12,Tb13_operrelation tb13 where tb12.id=tb13.dest_id";
		HSql += " and tb12.project_id = " + project_id;
		if (module_id != null)
			HSql += " and tb12.module_id = " + module_id;
		if (doc_id != null)
			HSql += " and tb12.doc_id = " + doc_id;
		ro = queryService.search(HSql);
		while (ro.next()) {
			Tb12_opernode tb12 = (Tb12_opernode) ro.get("tb12");
			Tb13_operrelation tb13 = (Tb13_operrelation) ro.get("tb13");
			NodeStruct node = new NodeStruct();
			node.setDoc_id(tb12.getDoc_id());
			node.setModule_id(tb12.getModule_id());
			node.setProject_id(tb12.getProject_id());
			node.setNode_name(tb12.getNode_name());
			node.setNode_status(tb12.getNode_status());
			node.setRelation_name(tb13.getRelation_name());
			node.setTb12_id(tb12.getId());
			node.setTb02_id(tb12.getNode_id());
			if (result.get(tb13.getSource_id()) != null) {
				result.get(tb13.getSource_id()).add(node);
			} else {
				List<NodeStruct> list = new ArrayList<NodeStruct>();
				list.add(node);
				result.put(tb13.getSource_id(), list);
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netsky.base.tree.service.TreeService#getSysNodes(java.lang.Long)
	 */
	public Map<Long, List<NodeStruct>> getSysNodes(Long flow_id) throws Exception {
		String HSql;
		QueryBuilder queryBuilder;
		ResultObject ro;
		Map<Long, List<NodeStruct>> result = new HashMap<Long, List<NodeStruct>>();
		Tb01_flow tb01 = null;
		String flow_ids = "";
		if (flow_id == null) {
			/**
			 * 未指定系统流程id，获取默认第一个总流程
			 */
			queryBuilder = new HibernateQueryBuilder(Tb01_flow.class);
			queryBuilder.eq("type", new Integer(0));
			queryBuilder.addOrderBy(Order.asc("id"));
			ro = queryService.search(queryBuilder);
			if (ro.next()) {
				tb01 = (Tb01_flow) ro.get(Tb01_flow.class.getName());
				flow_id = tb01.getId();
			} else {
				throw new Exception("未找到系统默认流程");
			}
		}
		tb01 = (Tb01_flow) queryService.searchById(Tb01_flow.class, flow_id);
		if (tb01.getType().intValue() == 0) {
			Tb04_flowgroup tb04;
			queryBuilder = new HibernateQueryBuilder(Tb04_flowgroup.class);
			queryBuilder.eq("pflow_id", tb01.getId());
			ro = queryService.search(queryBuilder);
			while (ro.next()) {
				tb04 = (Tb04_flowgroup) ro.get(Tb04_flowgroup.class.getName());
				flow_ids += (flow_ids.equals("") ? tb04.getCflow_id() : "," + tb04.getCflow_id());
			}
		} else {
			flow_ids += tb01.getId();
		}
		/**
		 * 获取系统配置节点与关系
		 */
		HSql = "select tb02,tb03 from Tb02_node tb02,Tb03_relation tb03 where tb02.id = tb03.dest_id and tb02.flow_id in ("
				+ flow_ids + ") order by tb03.seq";
		ro = queryService.search(HSql);
		while (ro.next()) {
			Tb02_node tb02 = (Tb02_node) ro.get("tb02");
			Tb03_relation tb03 = (Tb03_relation) ro.get("tb03");
			NodeStruct node = new NodeStruct();
			node.setNode_name(tb02.getName());
			node.setNode_status(new Integer(-1));
			node.setRelation_name(tb03.getName());
			node.setTb02_id(tb02.getId());
			if (result.get(tb03.getSource_id()) != null) {
				result.get(tb03.getSource_id()).add(node);
			} else {
				List<NodeStruct> list = new ArrayList<NodeStruct>();
				list.add(node);
				result.put(tb03.getSource_id(), list);
			}
		}
		/**
		 * 获取系统配置新建表单关系与节点
		 */
		HSql = "select tb04,tb02 from Tb04_flowgroup tb04,Tb02_node tb02 where tb02.id = tb04.startNode and tb02.flow_id in ("
				+ flow_ids + ")";
		ro = queryService.search(HSql);
		while (ro.next()) {
			Tb02_node tb02 = (Tb02_node) ro.get("tb02");
			Tb04_flowgroup tb04 = (Tb04_flowgroup) ro.get("tb04");
			if (tb04.getSourceNode() != null && tb04.getSourceNode().length() > 0) {
				String sourceNodes[] = tb04.getSourceNode().split(",");
				for (int i = 0; i < sourceNodes.length; i++) {
					if (sourceNodes[i].length() > 2) {
						NodeStruct node = new NodeStruct();
						node.setNode_name(tb02.getName());
						node.setNode_status(new Integer(-1));
						node.setRelation_name("新建表单");
						node.setTb02_id(tb02.getId());
						if (result.get(Long.valueOf(sourceNodes[i].substring(1, sourceNodes[i].length() - 1))) != null) {
							result.get(Long.valueOf(sourceNodes[i].substring(1, sourceNodes[i].length() - 1)))
									.add(node);
						} else {
							List<NodeStruct> list = new ArrayList<NodeStruct>();
							list.add(node);
							result.put(Long.valueOf(sourceNodes[i].substring(1, sourceNodes[i].length() - 1)), list);
						}
					}
				}
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netsky.base.tree.service.TreeService#showTree(com.netsky.base.tree.struct.NodeStruct,
	 *      int, int)
	 */
	public int showTree(NodeStruct root, int startX, int floor, List<Node> node_list, List<Dot> line_list,
			List<LineWord> word_list) throws Exception {
		root.setWidth(0);
		root.setStartX(startX);
		if (root.getChildren_list().size() > 0) {
			for (int i = 0; i < root.getChildren_list().size(); i++) {
				root.setWidth(root.getWidth()
						+ showTree(root.getChildren_list().get(i), root.getWidth() + startX, floor + 1, node_list,
								line_list, word_list));
			}
		} else {
			root.setWidth(Integer.parseInt(nodeConfig.get("singleWidth")));
		}
		root.setY(floor * Integer.parseInt(nodeConfig.get("floorHeight")));
		if (root.getChildren_list().size() < 1) {
			root.setX(root.getWidth() / 2 + root.getStartX());
		} else if (root.getChildren_list().size() == 1) {
			root.setX(root.getChildren_list().get(0).getX());
		} else {
			root.setX(root.getChildren_list().get(0).getX()
					+ (root.getChildren_list().get(root.getChildren_list().size() - 1).getX() - root.getChildren_list()
							.get(0).getX()) / 2);
		}
		this.drawNode(root, node_list);
		for (int i = 0; i < root.getChildren_list().size(); i++) {
			drawLine(root.getX() + Integer.parseInt(this.nodeConfig.get("nodeWidth")) / 2, root.getY()
					+ Integer.parseInt(this.nodeConfig.get("nodeHeight")), root.getChildren_list().get(i).getX()
					+ Integer.parseInt(this.nodeConfig.get("nodeWidth")) / 2, root.getChildren_list().get(i).getY(),
					line_list);
			drawArrow(root, root.getChildren_list().get(i), line_list);
			this.drawWords(root, root.getChildren_list().get(i), word_list);
		}
		return root.getWidth();
	}

	/**
	 * 绘制流程节点
	 * 
	 * @param root
	 * @param node_list
	 */
	private void drawNode(NodeStruct root, List<Node> node_list) {
		if (node_list == null) {
			node_list = new ArrayList<Node>();
		}
		Node node = new Node();
		node.setHeight(this.nodeConfig.get("nodeHeight"));
		node.setWidth(this.nodeConfig.get("nodeWidth"));
		node.setX(root.getX() + "");
		node.setY(root.getY() + "");
		node.setName(root.getNode_name());
		node.setHtmlClass(this.statusClass.get(root.getNode_status() + ""));

		String param = root.getTb02_id() + "," + root.getTb12_id() + "," + root.getProject_id() + ","
				+ root.getModule_id() + "," + root.getDoc_id() + "," + root.getNode_status();
		node.setParam(param);
		if (root.getNode_status() == -1 && root.getProject_id() != null) {
			node.setJsFlag(false);
		} else {
			node.setJsFlag(true);
		}
		node_list.add(node);
	}

	private void drawLine(int x1, int y1, int x2, int y2, List<Dot> line_list) {
		if (line_list == null) {
			line_list = new ArrayList<Dot>();
		}
		int min_y = min(y1, y2);
		int max_y = max(y1, y2);
		int min_x = min(x1, x2);
		int max_x = max(x1, x2);
		if (x1 == x2) {
			for (int i = min_y; i <= max_y; i++) {
				/**
				 * 添加线条
				 */
				line_list.add(drawDot(x1, i));
			}
		} else {
			double k = Double.parseDouble((y1 - y2) + "") / Double.parseDouble((x1 - x2) + "");
			double b = Double.parseDouble((x1 * y2 - x2 * y1) + "") / Double.parseDouble((x1 - x2) + "");
			if (k > 1 || k < -1) {
				for (int i = min_y; i <= max_y; i++) {
					line_list.add(drawDot(Integer.parseInt(NumberFormatUtil.roundToString((i - b) / k, 0)), i));
				}
			} else {
				for (int i = min_x; i <= max_x; i++) {
					line_list.add(drawDot(i, Integer.parseInt(NumberFormatUtil.roundToString(i * k + b, 0))));
				}
			}
		}
	}

	/**
	 * 绘制箭头
	 * 
	 * @param root
	 * @param child
	 * @param line_list
	 */
	private void drawArrow(NodeStruct root, NodeStruct child, List<Dot> line_list) {
		int x1 = root.getX() + Integer.parseInt(this.nodeConfig.get("nodeWidth")) / 2;// 位移
		int y1 = root.getY() + Integer.parseInt(this.nodeConfig.get("nodeHeight"));
		int x2 = child.getX() + Integer.parseInt(this.nodeConfig.get("nodeWidth")) / 2;
		int y2 = child.getY();
		double x3, y3;// 虚拟旋转后直线经过的点
		double arrow_length = 10;// 默认箭头长度
		double x1_y1_x2_y2 = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));// x1,y1与x2,y2距离
		double turn = 20;// 旋转角度,要求小于45度
		double x1_y1_x3_y3 = NumberFormatUtil.mulToDouble(x1_y1_x2_y2, Math.tan(turn * Math.PI / 180));// x1,y1与x3,y3距离

		if (x1 == x2) {
			/**
			 * 斜率不存在
			 */
			y3 = y1;
			x3 = x1 + x1_y1_x3_y3;
			this.drawSegment(x2, y2, x3, y3, arrow_length, line_list);
			x3 = x1 - x1_y1_x3_y3;
			this.drawSegment(x2, y2, x3, y3, arrow_length, line_list);
		} else {
			/**
			 * 绘制箭头
			 */
			double k = Double.parseDouble((y1 - y2) + "") / Double.parseDouble((x1 - x2) + "");
			/**
			 * 运算平方根为正情况
			 */
			x3 = Math.sqrt(x1_y1_x3_y3 * x1_y1_x3_y3 / (k * k + 1)) + x1;
			y3 = -k * x3 + y1 + k * x1;
			this.drawSegment(x2, y2, x3, y3, arrow_length, line_list);
			/**
			 * 运算平方根为负情况
			 */
			x3 = x1 - Math.sqrt(x1_y1_x3_y3 * x1_y1_x3_y3 / (k * k + 1));
			y3 = -k * x3 + y1 + k * x1;
			this.drawSegment(x2, y2, x3, y3, arrow_length, line_list);
		}
	}

	/**
	 * 绘制线段,以x1,y1为开始,以y坐标递减,长度等于length
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param length
	 * @param line_list
	 */
	private void drawSegment(double x1, double y1, double x2, double y2, double length, List<Dot> line_list) {
		double x3 = 0, y3 = 0;// 箭头除x2,y2外另外一点
		if (x1 == x2) {
			drawLine(Integer.parseInt(NumberFormatUtil.roundToString(x1, 0)), Integer.parseInt(NumberFormatUtil
					.roundToString(y1 - length, 0)), Integer.parseInt(NumberFormatUtil.roundToString(x1, 0)), Integer
					.parseInt(NumberFormatUtil.roundToString(y1, 0)), line_list);
		} else {
			double a_k = (y1 - y2) / (x1 - x2);
			double a_b = (x1 * y2 - x2 * y1) / (x1 - x2);
			for (y3 = y1; y3 > 0; y3 -= 0.1) {
				if (a_k != 0)
					x3 = (y3 - a_b) / a_k;
				else
					x3 = x1;
				if ((x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1) > length * length) {
					break;
				}
			}
			drawLine(Integer.parseInt(NumberFormatUtil.roundToString(x3, 0)), Integer.parseInt(NumberFormatUtil
					.roundToString(y3, 0)), Integer.parseInt(NumberFormatUtil.roundToString(x1, 0)), Integer
					.parseInt(NumberFormatUtil.roundToString(y1, 0)), line_list);
		}
	}

	/**
	 * 绘制点
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private Dot drawDot(int x, int y) {
		Dot d = new Dot();
		d.setX(x + "");
		d.setY(y + "");
		d.setColor(this.nodeConfig.get("lineColor"));
		return d;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netsky.base.tree.service.TreeService#setNodeConfig(javax.servlet.http.HttpServletRequest)
	 */
	public void setNodeConfig(HttpServletRequest request) throws Exception {
		String filePath = request.getSession().getServletContext().getRealPath("WEB-INF") + configFilePath;
		File file = new File(filePath);
		if (!file.exists()) {
			throw new Exception("配置文件不存在，路径：" + filePath);
		}
		Map<String, String> result = new HashMap<String, String>();
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(file);
			Element root = doc.getRootElement();
			result.put("nodeWidth", root.elementText("nodeWidth"));
			result.put("nodeHeight", root.elementText("nodeHeight"));
			result.put("floorHeight", root.elementText("floorHeight"));
			result.put("singleWidth", root.elementText("singleWidth"));
			result.put("lineColor", root.elementText("lineColor"));
			result.put("relationClass", root.elementText("relationClass"));
		} catch (DocumentException ex) {
			throw new Exception("错误的xml格式，路径：" + filePath + "错误:" + ex);
		}
		this.nodeConfig = result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netsky.base.tree.service.TreeService#setStatusColor(javax.servlet.http.HttpServletRequest)
	 */
	public void setStatusClass(HttpServletRequest request) throws Exception {
		String filePath = request.getSession().getServletContext().getRealPath("WEB-INF") + configFilePath;
		File file = new File(filePath);
		if (!file.exists()) {
			throw new Exception("配置文件不存在，路径：" + filePath);
		}
		Map<String, String> result = new HashMap<String, String>();
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(file);
			Element root = doc.getRootElement();
			Iterator<?> it = root.elementIterator("statusClass");
			while (it.hasNext()) {
				Element element = (Element) it.next();
				result.put(element.elementText("status"), element.elementText("class"));
			}
		} catch (DocumentException ex) {
			throw new Exception("错误的xml格式，路径：" + filePath + "错误:" + ex);
		}
		this.statusClass = result;
	}

	/**
	 * 绘制节点路径描述
	 * 
	 * @param root
	 * @param child
	 * @param word_list
	 */
	private void drawWords(NodeStruct root, NodeStruct child, List<LineWord> word_list) {
		int x1 = root.getX() + Integer.parseInt(this.nodeConfig.get("nodeWidth")) / 2;// 位移
		int y1 = root.getY() + Integer.parseInt(this.nodeConfig.get("nodeHeight"));
		int x2 = child.getX() + Integer.parseInt(this.nodeConfig.get("nodeWidth")) / 2;
		int y2 = child.getY();
		double height = 15;// 字符高度偏移动量
		char words[] = child.getRelation_name().toCharArray();
		if (x1 == x2) {
			/**
			 * 斜率不存在
			 */
			for (int i = 0; i < words.length; i++) {
				int n_y = y1 + (i + 1) * (y2 - y1) / (words.length + 2);
				this.drawWord(x1 + 3, n_y, words[i], word_list);
			}
		} else {
			/**
			 * 
			 */
			double k = Double.parseDouble((y1 - y2) + "") / Double.parseDouble((x1 - x2) + "");
			double b = Double.parseDouble((x1 * y2 - x2 * y1) + "") / Double.parseDouble((x1 - x2) + "");
			for (int i = 0; i < words.length; i++) {
				int n_y = y1 + (i + 1) * (y2 - y1) / (words.length + 2);
				int n_x = 0;
				if (k > 0) {
					n_x = Integer.parseInt(NumberFormatUtil.roundToString((Double.parseDouble(n_y + "") - b + height)
							/ k, 0));
				} else {
					n_x = Integer.parseInt(NumberFormatUtil.roundToString((Double.parseDouble(n_y + "") - b) / k
							- height + height / k, 0));
				}
				this.drawWord(n_x, n_y, words[i], word_list);
			}
		}
	}

	private void drawWord(int x, int y, char word, List<LineWord> word_list) {
		LineWord lw = new LineWord();
		lw.setX(x + "");
		lw.setY(y + "");
		lw.setName(word + "");
		lw.setHtmlClass(this.nodeConfig.get("relationClass"));
		word_list.add(lw);
	}

	private int max(int x, int y) {
		if (x > y)
			return x;
		else
			return y;
	}

	private int min(int x, int y) {
		if (x < y)
			return x;
		else
			return y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netsky.base.tree.service.TreeService#getFormRoot(java.lang.Long,
	 *      java.lang.Long)
	 */
	public NodeStruct getFormRoot(Long flow_id, Long project_id) throws Exception {
		QueryBuilder queryBuilder;
		ResultObject ro;
		NodeStruct root = new NodeStruct();
		if (project_id == null) {
			/**
			 * 获取系统流程根结点
			 */
			Tb01_flow tb01 = null;
			if (flow_id == null) {
				/**
				 * 未指定系统流程id，获取默认第一个总流程
				 */
				queryBuilder = new HibernateQueryBuilder(Tb01_flow.class);
				queryBuilder.eq("type", new Integer(0));
				queryBuilder.addOrderBy(Order.asc("id"));
				ro = queryService.search(queryBuilder);
				if (ro.next()) {
					tb01 = (Tb01_flow) ro.get(Tb01_flow.class.getName());
				}
			} else {
				tb01 = (Tb01_flow) queryService.searchById(Tb01_flow.class, flow_id);
			}
			if (tb01.getType().longValue() == 0) {
				Tb04_flowgroup tb04;
				queryBuilder = new HibernateQueryBuilder(Tb04_flowgroup.class);
				queryBuilder.eq("pflow_id", tb01.getId());
				queryBuilder.addOrderBy(Order.asc("seq"));
				ro = queryService.search(queryBuilder);
				if (ro.next()) {
					tb04 = (Tb04_flowgroup) ro.get(Tb04_flowgroup.class.getName());
					tb01 = (Tb01_flow) queryService.searchById(Tb01_flow.class, tb04.getCflow_id());
				}
			}
			if (tb01 != null) {
				root.setNode_name(tb01.getName());
				root.setNode_status(new Integer(-1));
				root.setTb02_id(tb01.getId());
			} else {
				throw new Exception("未找到系统流程根节点：flow_id=" + flow_id + "。");
			}
		} else {
			/**
			 * 获取实际流程根结点
			 */
			queryBuilder = new HibernateQueryBuilder(Tb12_opernode.class);
			queryBuilder.eq("project_id", project_id);
			queryBuilder.addOrderBy(Order.asc("id"));
			ro = queryService.searchByPage(queryBuilder, 1, 1);
			if (ro.next()) {
				Tb12_opernode tb12 = (Tb12_opernode) ro.get(Tb12_opernode.class.getName());
				Tb02_node tb02 = (Tb02_node) queryService.searchById(Tb02_node.class, tb12.getNode_id());
				Ta06_module ta06 = (Ta06_module) queryService.searchById(Ta06_module.class, tb12.getModule_id());
				root.setNode_name(ta06.getName());
				root.setNode_status(new Integer(1));
				root.setTb12_id(tb12.getModule_id());
				root.setTb02_id(tb02.getFlow_id());
				root.setProject_id(project_id);
				root.setDoc_id(tb12.getDoc_id());
				root.setModule_id(tb12.getModule_id());
			} else {
				throw new Exception("未找到工程根节点！");
			}
		}
		return root;
	}

	public Map<Long, List<NodeStruct>> getRealFormNodes(Long project_id) throws Exception {
		String HSql;
		ResultObject ro;
		Map<Long, List<NodeStruct>> result = new HashMap<Long, List<NodeStruct>>();
		if (project_id == null)
			return result;
		HSql = " select tb12,tb13,tb12_s from Tb12_opernode tb12,Tb13_operrelation tb13,Tb12_opernode tb12_s where tb12.id=tb13.dest_id"
				+ " and tb13.source_id = tb12_s.id and tb13.relation_id = -1 and tb12.project_id = " + project_id;
		ro = queryService.search(HSql);
		while (ro.next()) {
			Tb12_opernode tb12 = (Tb12_opernode) ro.get("tb12");
			Tb12_opernode tb12_s = (Tb12_opernode) ro.get("tb12_s");
			Tb02_node tb02 = (Tb02_node) queryService.searchById(Tb02_node.class, tb12.getNode_id());
			Ta06_module ta06 = (Ta06_module) queryService.searchById(Ta06_module.class, tb12.getModule_id());
			NodeStruct node = new NodeStruct();
			node.setDoc_id(tb12.getDoc_id());
			node.setModule_id(tb12.getModule_id());
			node.setProject_id(project_id);
			node.setNode_name(ta06.getName());
			node.setNode_status(new Integer(1));
			node.setRelation_name("");
			node.setTb12_id(tb12.getModule_id());
			node.setTb02_id(tb02.getFlow_id());
			if (result.get(tb12_s.getModule_id()) != null) {
				result.get(tb12_s.getModule_id()).add(node);
			} else {
				List<NodeStruct> list = new ArrayList<NodeStruct>();
				list.add(node);
				result.put(tb12_s.getModule_id(), list);
			}
		}
		return result;
	}

	public Map<Long, List<NodeStruct>> getSysFormNodes(Long flow_id) throws Exception {
		String HSql;
		QueryBuilder queryBuilder;
		ResultObject ro;
		Map<Long, List<NodeStruct>> result = new HashMap<Long, List<NodeStruct>>();
		Tb01_flow tb01 = null;
		String flow_ids = "";
		if (flow_id == null) {
			/**
			 * 未指定系统流程id，获取默认第一个总流程
			 */
			queryBuilder = new HibernateQueryBuilder(Tb01_flow.class);
			queryBuilder.eq("type", new Integer(0));
			queryBuilder.addOrderBy(Order.asc("id"));
			ro = queryService.search(queryBuilder);
			if (ro.next()) {
				tb01 = (Tb01_flow) ro.get(Tb01_flow.class.getName());
				flow_id = tb01.getId();
			} else {
				throw new Exception("未找到系统默认流程");
			}
		}
		tb01 = (Tb01_flow) queryService.searchById(Tb01_flow.class, flow_id);
		if (tb01.getType().intValue() == 0) {
			Tb04_flowgroup tb04;
			queryBuilder = new HibernateQueryBuilder(Tb04_flowgroup.class);
			queryBuilder.eq("pflow_id", tb01.getId());
			ro = queryService.search(queryBuilder);
			while (ro.next()) {
				tb04 = (Tb04_flowgroup) ro.get(Tb04_flowgroup.class.getName());
				flow_ids += (flow_ids.equals("") ? tb04.getCflow_id() : "," + tb04.getCflow_id());
			}
		} else {
			flow_ids += tb01.getId();
		}
		/**
		 * 获取系统配置新建表单关系与节点
		 */
		HSql = "select tb04,tb02 from Tb04_flowgroup tb04,Tb02_node tb02 where tb02.id = tb04.startNode and tb02.flow_id in ("
				+ flow_ids + ")";
		ro = queryService.search(HSql);
		while (ro.next()) {
			Tb02_node tb02 = (Tb02_node) ro.get("tb02");
			Tb04_flowgroup tb04 = (Tb04_flowgroup) ro.get("tb04");
			if (tb04.getSourceNode() != null && tb04.getSourceNode().length() > 0) {
				String sourceNodes[] = tb04.getSourceNode().split(",");
				for (int i = 0; i < sourceNodes.length; i++) {
					if (sourceNodes[i].length() > 2) {
						Tb02_node tb02_s = (Tb02_node) queryService.searchById(Tb02_node.class, Long
								.valueOf(sourceNodes[i].substring(1, sourceNodes[i].length() - 1)));
						tb01 = (Tb01_flow) queryService.searchById(Tb01_flow.class, tb02.getFlow_id());
						NodeStruct node = new NodeStruct();
						node.setNode_name(tb01.getName());
						node.setNode_status(new Integer(-1));
						node.setRelation_name("");
						node.setTb02_id(tb02.getFlow_id());
						if (result.get(tb02_s.getFlow_id()) != null) {
							List<NodeStruct> list_t = result.get(tb02_s.getFlow_id());
							if (list_t.indexOf(node) == -1)
								list_t.add(node);
						} else {
							List<NodeStruct> list = new ArrayList<NodeStruct>();
							list.add(node);
							result.put(tb02_s.getFlow_id(), list);
						}
					}
				}
			}
		}
		return result;
	}
}
