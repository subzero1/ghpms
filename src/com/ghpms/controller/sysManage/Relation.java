package com.ghpms.controller.sysManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ghpms.service.CreateJspFile;
import com.ghpms.service.GcsjDataService;
import com.netsky.base.dataObjects.Ta07_formfield;
import com.netsky.base.dataObjects.Ta11_sta_user;
import com.netsky.base.dataObjects.Ta12_sta_role;
import com.netsky.base.dataObjects.Ta13_sta_node;
import com.netsky.base.dataObjects.Ta14_group_user;
import com.netsky.base.dataObjects.Ta15_group_node;
import com.netsky.base.dataObjects.Ta16_node_field;
import com.netsky.base.dataObjects.Tb02_node;
import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.convertUtil;

/**
 * @description: 基础表对应关系维护
 * @class name:com.netsky.base.controller.Relation
 * @author Administrator Mar 8, 2011
 */
@Controller
public class Relation {
	/**
	 * 数据服务
	 */
	@Autowired
	private Dao dao;

	/**
	 * 异常处理服务
	 */
	@Autowired
	private ExceptionService exceptionService;

	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;

	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * 生成JSP文件
	 */
	@Autowired
	private CreateJspFile createJspFile;
	
	/**
	 * 设置下拉框的值
	 */
	@Autowired
	private GcsjDataService gcsjDataService;
	/**
	 * 岗位角色配置
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/sysManage/staRoles.do")
	public ModelAndView staRoles(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		ModelMap modelMap = new ModelMap();
		// 获取岗位相关的角色对象
		StringBuffer role_rsql = new StringBuffer();
		role_rsql
				.append(" select ta04 from Ta12_sta_role ta12,Ta04_role ta04 ");
		role_rsql
				.append(" where ta12.role_id = ta04.id  and ta12.station_id = ");
		role_rsql.append(id);
		List roles = dao.search(role_rsql.toString() + " order by ta04.name");
		modelMap.put("select_roles", roles);
		// 获取岗位非相关的角色对象
		StringBuffer unrole_rsql = new StringBuffer();
		unrole_rsql
				.append(" select ta04 from Ta04_role ta04 where ta04 not in(");
		unrole_rsql.append(role_rsql + ")");
		unrole_rsql.append(" order by ta04.name");
		List unroles = dao.search(unrole_rsql.toString());
		modelMap.put("unselect_roles", unroles);
		return new ModelAndView("/WEB-INF/jsp/sysManage/staRoles.jsp", modelMap);
	}

	/**
	 * 处理岗位角色配置信息
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param ModelAndView
	 */
	@RequestMapping("/sysManage/saveStaRoles.do")
	public ModelAndView saveStaRoles(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		String[] roles = request.getParameterValues("t_role");
		Long id = convertUtil.toLong(request.getParameter("station_id"), -1L);

		String forwardUrl = "sysManage/staList.do?sta_id="
				+ convertUtil.toString(request.getParameter("station_id"), "");

		// 获取岗位的对象
		StringBuffer checkRole = new StringBuffer();
		checkRole
				.append("select ta12 from Ta12_sta_role ta12 where station_id=");
		checkRole.append(id);
		try {
			// 把数据库所有岗位相关的角色放到集合里面
			List allRoleList = (dao.search(checkRole.toString()));
			if (roles == null || roles.length == 0) {
				// 对现有的岗位对象进行判断，如果不为空，进行清空
				if (!allRoleList.isEmpty()) {
					for (int r = 0; r < allRoleList.size(); r++) {
						dao.removeObject(allRoleList.get(r));
					}
				}
			} else {
				// 对现有的岗位对象进行判断，如果不为空，进行清空
				if (!allRoleList.isEmpty()) {
					for (int r = 0; r < allRoleList.size(); r++) {
						dao.removeObject(allRoleList.get(r));
					}
				}
				// 对配置的角色进行保存
				for (int i = 0; i < roles.length; i++) {
					Ta12_sta_role sta_role = new Ta12_sta_role();
					sta_role.setStation_id(id);
					sta_role.setRole_id(new Long(roles[i]));
					if (id != -1) {
						dao.saveObject(sta_role);
					}
				}
			}
			response
					.getWriter()
					.print(
							"{\"statusCode\":\"200\", \"message\":\"操作成功\", \"navTabId\":\"staList\",\"forwardUrl\":\""
									+ forwardUrl + "\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			response.getWriter().print(
					"{\"statusCode\":\"300\", \"message\":\"操作失败\"}");
		}
		return null;
	}

	/**
	 * 处理岗位用户配置信息
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param ModelAndView
	 */
	@RequestMapping("/sysManage/saveStaUsers.do")
	public ModelAndView saveStaUsers(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		String[] users = request.getParameterValues("t_user");
		Long id = convertUtil.toLong(request.getParameter("station_id"), -1L);

		String forwardUrl = "sysManage/staList.do?sta_id="
				+ convertUtil.toString(request.getParameter("station_id"), "");

		// 获取岗位相关的用户对象那个
		StringBuffer checkUser = new StringBuffer();
		checkUser
				.append("select ta11 from Ta11_sta_user ta11 where station_id=");
		checkUser.append(id);
		try {
			List allUsersList = dao.search(checkUser.toString());
			if (users == null || users.length == 0) {
				if (!allUsersList.isEmpty()) {
					for (int r = 0; r < allUsersList.size(); r++) {
						dao.removeObject(allUsersList.get(r));
					}
				}
			} else {
				if (!allUsersList.isEmpty()) {
					for (int r = 0; r < allUsersList.size(); r++) {
						dao.removeObject(allUsersList.get(r));
					}
				}
				if (users.length > 0) {
					for (int i = 0; i < users.length; i++) {
						Ta11_sta_user sta_user = new Ta11_sta_user();
						sta_user.setStation_id(id);
						sta_user.setUser_id(new Long(users[i]));
						if (id != -1) {
							dao.saveObject(sta_user);
						}
					}
				}
			}
			response
					.getWriter()
					.print(
							"{\"statusCode\":\"200\", \"message\":\"操作成功\", \"navTabId\":\"staList\",\"forwardUrl\":\""
									+ forwardUrl + "\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			response.getWriter().print(
					"{\"statusCode\":\"300\", \"message\":\"操作失败\"}");
		}
		return null;
	}

	/**
	 * 岗位人员配置
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/sysManage/staUsers.do")
	public ModelAndView staUsers(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Long id = convertUtil.toLong(request.getParameter("id"), -1l);
		ModelMap modelMap = new ModelMap();
		// 获取岗位相关的用户对象
		StringBuffer user_rsql = new StringBuffer();
		user_rsql.append(" select ta03 from Ta11_sta_user ta11,Ta03_user ta03");
		user_rsql.append(" where ta11.user_id=ta03.id and ta11.station_id=");
		user_rsql.append(id);
		List users = dao.search(user_rsql.toString() + " order by ta03.name");
		modelMap.put("select_users", users);
		// 获取岗位非相关用户的对象
		StringBuffer unuser_rsql = new StringBuffer();
		unuser_rsql
				.append("select ta03 from Ta03_user ta03 where ta03 not in(");
		unuser_rsql.append(user_rsql + ")");
		unuser_rsql.append(" order by ta03.name");
		List unusers = dao.search(unuser_rsql.toString());
		modelMap.put("unselect_users", unusers);
		return new ModelAndView("/WEB-INF/jsp/sysManage/staUsers.jsp", modelMap);
	}

	/**
	 * 岗位节点配置
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/sysManage/staNodes.do")
	public ModelAndView staNodes(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		String flow_id = convertUtil.toString(request.getParameter("flow_id"),
		"101");
//		Long node_type = convertUtil.toLong(request.getParameter("node_type"),1L);
		ModelMap modelMap = new ModelMap();
		ResultObject ro=null;

		// 获取流程列表
		StringBuffer sql = new StringBuffer();
		sql.append(" from Tb01_flow order by id");
		List flowList = dao.search(sql.toString());
		modelMap.put("flowList", flowList);
		modelMap.put("flow_id", flow_id);

		// 获取岗位相关的角色对象
		List nodes=new ArrayList();
		sql.delete(0, sql.length());
		sql.append(" select tb02,ta06 from Ta13_sta_node ta13,Tb02_node tb02,Ta06_module ta06 ");
		sql.append(" where ta13.node_id = tb02.id and tb02.flow_id=ta06.id and ta13.station_id = ");
		sql.append(id);
		ro=queryService.search(sql.toString()+" order by  tb02.node_type, tb02.flow_id, tb02.name");
		while (ro.next()) {
			Map nodeMap=new HashMap();
			nodeMap.put("node", ro.get("tb02"));
			nodeMap.put("module", ro.get("ta06"));
			nodes.add(nodeMap);
		}
		modelMap.put("select_nodes", nodes);

		// 获取岗位非相关的角色对象
		List unnodes=new ArrayList();
		StringBuffer unrole_rsql = new StringBuffer();
		unrole_rsql
				.append(" select tb02,ta06 from Tb02_node tb02,Ta06_module ta06 where tb02.flow_id=ta06.id and tb02 not in(");
		unrole_rsql.append(" select tb02 from Ta13_sta_node ta13,Tb02_node tb02 ");
		unrole_rsql.append(" where ta13.node_id = tb02.id and ta13.station_id = ");
		unrole_rsql.append(id);
		unrole_rsql.append(")");
		unrole_rsql.append(" and tb02.flow_id = " + flow_id);
		unrole_rsql.append(" order by tb02.node_type, tb02.name");
		ro=queryService.search(unrole_rsql.toString());
		while (ro.next()) {
			Map nodeMap=new HashMap();
			nodeMap.put("node", ro.get("tb02"));
			nodeMap.put("module", ro.get("ta06"));
			unnodes.add(nodeMap);
		}
		modelMap.put("unselect_nodes", unnodes);
//		modelMap.put("node_type", node_type);
		return new ModelAndView("/WEB-INF/jsp/sysManage/staNodes.jsp", modelMap);
	}

	/**
	 * 处理岗位节点配置信息
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param ModelAndView
	 */
	@RequestMapping("/sysManage/saveStaNodes.do")
	public ModelAndView saveStaNodes(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		String[] nodes = request.getParameterValues("t_node");
		Long id = convertUtil.toLong(request.getParameter("station_id"), -1L);
//		Long node_type=convertUtil.toLong(request.getParameter("node_type"),1L);
		String forwardUrl = "sysManage/staList.do?sta_id="
				+ convertUtil.toString(request.getParameter("station_id"), "");//+"&node_type="+node_type;
		

		// 获取岗位的对象
		StringBuffer checkRole = new StringBuffer();
		checkRole
				.append("select ta13 from Ta13_sta_node ta13,Tb02_node tb02 where ta13.node_id=tb02.id and station_id=");
		checkRole.append(id);
//		checkRole.append(" and tb02.node_type=");
//		checkRole.append(node_type);
		try {
			// 把数据库所有岗位相关的角色放到集合里面
			List allRoleList = (dao.search(checkRole.toString()));
			if (nodes == null || nodes.length == 0) {
				// 对现有的岗位对象进行判断，如果不为空，进行清空
				if (!allRoleList.isEmpty()) {
					for (int r = 0; r < allRoleList.size(); r++) {
						dao.removeObject(allRoleList.get(r));
					}
				}
			} else {
				// 对现有的岗位对象进行判断，如果不为空，进行清空
				if (!allRoleList.isEmpty()) {
					for (int r = 0; r < allRoleList.size(); r++) {
						dao.removeObject(allRoleList.get(r));
					}
				}
				// 对配置的角色进行保存
				for (int i = 0; i < nodes.length; i++) {
					Ta13_sta_node sta_node = new Ta13_sta_node();
					sta_node.setStation_id(id);
					sta_node.setNode_id(new Long(nodes[i]));
					if (id != -1) {
						dao.saveObject(sta_node);
					}
				}
			}
			response
					.getWriter()
					.print(
							"{\"statusCode\":\"200\", \"message\":\"操作成功\", \"navTabId\":\"staList\",\"forwardUrl\":\""
									+ forwardUrl + "\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			response.getWriter().print(
					"{\"statusCode\":\"300\", \"message\":\"操作失败\"}");
		}
		return null;
	}

	/**
	 * 用户岗位配置
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/sysManage/userStas.do")
	public ModelAndView userStas(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		ModelMap modelMap = new ModelMap();
		// 获取用户相关的岗位对象
		StringBuffer sta_rsql = new StringBuffer();
		sta_rsql
				.append(" select ta02 from Ta11_sta_user ta11,Ta02_station ta02 ");
		sta_rsql
				.append(" where ta11.station_id = ta02.id  and ta11.user_id = ");
		sta_rsql.append(id);
		List roles = dao.search(sta_rsql.toString() + " order by ta02.name");
		modelMap.put("select_stas", roles);
		// 获取岗位非相关的角色对象
		StringBuffer unsta_rsql = new StringBuffer();
		unsta_rsql
				.append(" select ta02 from Ta02_station ta02 where ta02 not in(");
		unsta_rsql.append(sta_rsql + ")");
		unsta_rsql.append(" order by ta02.name");
		List unroles = dao.search(unsta_rsql.toString());
		modelMap.put("unselect_stas", unroles);
		return new ModelAndView("/WEB-INF/jsp/sysManage/userStas.jsp", modelMap);
	}

	/**
	 * 用户群组配置
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/sysManage/userGroups.do")
	public ModelAndView userGroups(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		ModelMap modelMap = new ModelMap();
		// 获取岗位相关的角色对象
		StringBuffer group_rsql = new StringBuffer();
		group_rsql
				.append(" select ta05 from Ta14_group_user ta14,Ta05_group ta05 ");
		group_rsql
				.append(" where ta14.group_id = ta05.id  and ta14.user_id = ");
		group_rsql.append(id);
		List groups = dao.search(group_rsql.toString() + " order by ta05.name");
		modelMap.put("select_groups", groups);
		// 获取岗位非相关的角色对象
		StringBuffer ungroup_rsql = new StringBuffer();
		ungroup_rsql
				.append(" select ta05 from Ta05_group ta05 where ta05 not in(");
		ungroup_rsql.append(group_rsql + ")");
		ungroup_rsql.append(" order by ta05.name");
		List ungroups = dao.search(ungroup_rsql.toString());
		modelMap.put("unselect_groups", ungroups);
		return new ModelAndView("/WEB-INF/jsp/sysManage/userGroups.jsp",
				modelMap);
	}

	/**
	 * 群组用户配置
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/sysManage/groupUsers.do")
	public ModelAndView groupUsers(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		ModelMap modelMap = new ModelMap();
		// 获取岗位相关的角色对象
		StringBuffer user_rsql = new StringBuffer();
		user_rsql
				.append(" select ta03 from Ta14_group_user ta14,Ta03_user ta03 ");
		user_rsql.append(" where ta14.user_id = ta03.id  and ta14.group_id = ");
		user_rsql.append(id);
		List groups = dao.search(user_rsql.toString() + " order by ta03.name");
		modelMap.put("select_users", groups);
		// 获取岗位非相关的角色对象
		StringBuffer unuser_rsql = new StringBuffer();
		unuser_rsql
				.append(" select ta03 from Ta03_user ta03 where ta03 not in(");
		unuser_rsql.append(user_rsql + ")");
		unuser_rsql.append(" order by ta03.name");
		List unusers = dao.search(unuser_rsql.toString());
		modelMap.put("unselect_users", unusers);
		return new ModelAndView("/WEB-INF/jsp/sysManage/groupUsers.jsp",
				modelMap);
	}

	/**
	 * 群组用户配置
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/sysManage/groupNodes.do")
	public ModelAndView groupNodes(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		ModelMap modelMap = new ModelMap();
		// 获取岗位相关的角色对象
		StringBuffer node_rsql = new StringBuffer();
		node_rsql
				.append(" select tb02 from Ta15_group_node ta15,Tb02_node tb02 ");
		node_rsql.append(" where ta15.node_id = tb02.id  and ta15.group_id = ");
		node_rsql.append(id);
		List groups = dao.search(node_rsql.toString() + " order by tb02.name");
		modelMap.put("select_nodes", groups);
		// 获取岗位非相关的角色对象
		StringBuffer unnode_rsql = new StringBuffer();
		unnode_rsql
				.append(" select tb02 from Tb02_node tb02 where tb02 not in(");
		unnode_rsql.append(node_rsql + ")");
		unnode_rsql.append(" order by tb02.name");
		List unnodes = dao.search(unnode_rsql.toString());
		modelMap.put("unselect_nodes", unnodes);
		return new ModelAndView("/WEB-INF/jsp/sysManage/groupNodes.jsp",
				modelMap);
	}

	/**
	 * 节点字段配置
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/sysManage/nodeFields.do")
	public ModelAndView nodeFields(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		Long node_type=convertUtil.toLong(request.getParameter("node_type"),1L);
		ModelMap modelMap = new ModelMap();
		// 获取岗位相关的角色对象
		StringBuffer field_rsql = new StringBuffer();
		field_rsql
				.append(" select ta07 from Ta16_node_field ta16,Ta07_formfield ta07 ");
		field_rsql.append(" where ta07.id = ta16.field_id and ta16.node_id = ");
		field_rsql.append(id);
		if (node_type==2) {
			field_rsql.append(" and ta16.node_type=");
			field_rsql.append(node_type);
		}else if(node_type==1){
			field_rsql.append(" and (ta16.node_type=");
			field_rsql.append(node_type);
			field_rsql.append(" or ta16.node_type is null)");
		}
		List fields = dao.search(field_rsql.toString() + " order by ta16.id, ta07.ord,ta07.comments");
		modelMap.put("select_fields", fields);
		// 获取节点
		Tb02_node tb02_node=(Tb02_node) queryService.searchById(Tb02_node.class, convertUtil.toLong(id));
		// 获取岗位非相关的角色对象
		StringBuffer unfield_rsql = new StringBuffer();
		unfield_rsql
				.append(" select ta07 from Ta07_formfield ta07 where ta07 not in(");
		unfield_rsql.append(field_rsql + ")");
		unfield_rsql.append(" and ta07.module_id='"
				+ tb02_node.getFlow_id() + "' ");
		unfield_rsql.append(" and ta07.name <>'");
		unfield_rsql.append("id' ");
		unfield_rsql.append(" order by ta07.comments,ta07.ord");
		List unfields = dao.search(unfield_rsql.toString());
		modelMap.put("unselect_fields", unfields);
		return new ModelAndView("/WEB-INF/jsp/sysManage/nodeFields.jsp",
				modelMap);
	}

	/**
	 * 处理用户岗位配置信息
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param ModelAndView
	 */
	@RequestMapping("/sysManage/saveUserStas.do")
	public ModelAndView saveUserStas(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		String[] stas = request.getParameterValues("t_sta");
		Long id = convertUtil.toLong(request.getParameter("user_id"), -1L);

		String forwardUrl = "sysManage/userList.do?user_id="
				+ convertUtil.toString(request.getParameter("user_id"), "");

		StringBuffer checkSta = new StringBuffer();
		checkSta.append("select ta11 from Ta11_sta_user ta11 where user_id=");
		checkSta.append(id);
		try {
			List allStaList = (dao.search(checkSta.toString()));
			if (stas == null || stas.length == 0) {
				if (!allStaList.isEmpty()) {
					for (int r = 0; r < allStaList.size(); r++) {
						dao.removeObject(allStaList.toArray()[r]);
					}
				}
			} else {
				if (!allStaList.isEmpty()) {
					for (int r = 0; r < allStaList.size(); r++) {
						dao.removeObject(allStaList.toArray()[r]);
					}
				}
				for (int i = 0; i < stas.length; i++) {
					Ta11_sta_user sta_user = new Ta11_sta_user();
					sta_user.setUser_id(id);
					sta_user.setStation_id(new Long(stas[i]));
					if (id != -1) {
						dao.saveObject(sta_user);
					}
				}
			}
			response
					.getWriter()
					.print(
							"{\"statusCode\":\"200\", \"message\":\"操作成功\", \"navTabId\":\"userList\",\"forwardUrl\":\""
									+ forwardUrl + "\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			response.getWriter().print(
					"{\"statusCode\":\"300\", \"message\":\"操作失败\"}");
		}
		return null;
	}

	/**
	 * 处理用户群组配置信息
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param ModelAndView
	 */
	@RequestMapping("/sysManage/saveUserGroups.do")
	public ModelAndView saveUserGroups(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		String[] groups = request.getParameterValues("t_group");
		Long id = convertUtil.toLong(request.getParameter("user_id"), -1L);

		String forwardUrl = "sysManage/userList.do?user_id="
				+ convertUtil.toString(request.getParameter("user_id"), "");

		// 获取岗位的对象
		StringBuffer checkGroup = new StringBuffer();
		checkGroup
				.append("select ta14 from Ta14_group_user ta14 where user_id=");
		checkGroup.append(id);
		try {
			// 把数据库所有岗位相关的角色放到集合里面
			List allGroupList = (dao.search(checkGroup.toString()));
			if (groups == null || groups.length == 0) {
				// 对现有的岗位对象进行判断，如果不为空，进行清空
				if (!allGroupList.isEmpty()) {
					for (int r = 0; r < allGroupList.size(); r++) {
						dao.removeObject(allGroupList.toArray()[r]);
					}
				}
			} else {
				// 对现有的岗位对象进行判断，如果不为空，进行清空
				if (!allGroupList.isEmpty()) {
					for (int r = 0; r < allGroupList.size(); r++) {
						dao.removeObject(allGroupList.toArray()[r]);
					}
				}
				// 对配置的角色进行保存
				for (int i = 0; i < groups.length; i++) {
					Ta14_group_user group_user = new Ta14_group_user();
					group_user.setUser_id(id);
					group_user.setGroup_id(new Long(groups[i]));
					if (id != -1) {
						dao.saveObject(group_user);
					}
				}
			}
			response
					.getWriter()
					.print(
							"{\"statusCode\":\"200\", \"message\":\"操作成功\", \"navTabId\":\"userList\",\"forwardUrl\":\""
									+ forwardUrl + "\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			response.getWriter().print(
					"{\"statusCode\":\"300\", \"message\":\"操作失败\"}");
		}
		return null;
	}

	/**
	 * 处理群组用户配置信息
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param ModelAndView
	 */
	@RequestMapping("/sysManage/saveGroupUsers.do")
	public ModelAndView saveGroupUsers(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		String[] users = request.getParameterValues("t_user");
		Long id = convertUtil.toLong(request.getParameter("group_id"), -1L);

		String forwardUrl = "sysManage/groupList.do?group_id="
				+ convertUtil.toString(request.getParameter("group_id"), "");

		// 获取岗位的对象
		StringBuffer checkuser = new StringBuffer();
		checkuser
				.append("select ta14 from Ta14_group_user ta14 where group_id=");
		checkuser.append(id);
		try {
			// 把数据库所有岗位相关的角色放到集合里面
			List alluserList = (dao.search(checkuser.toString()));
			if (users == null || users.length == 0) {
				// 对现有的岗位对象进行判断，如果不为空，进行清空
				if (!alluserList.isEmpty()) {
					for (int r = 0; r < alluserList.size(); r++) {
						dao.removeObject(alluserList.toArray()[r]);
					}
				}
			} else {
				// 对现有的岗位对象进行判断，如果不为空，进行清空
				if (!alluserList.isEmpty()) {
					for (int r = 0; r < alluserList.size(); r++) {
						dao.removeObject(alluserList.toArray()[r]);
					}
				}
				// 对配置的角色进行保存
				for (int i = 0; i < users.length; i++) {
					Ta14_group_user user_group = new Ta14_group_user();
					user_group.setGroup_id(id);
					user_group.setUser_id(new Long(users[i]));
					if (id != -1) {
						dao.saveObject(user_group);
					}
				}
			}
			response
					.getWriter()
					.print(
							"{\"statusCode\":\"200\", \"message\":\"操作成功\", \"navTabId\":\"groupList\",\"forwardUrl\":\""
									+ forwardUrl + "\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			response.getWriter().print(
					"{\"statusCode\":\"300\", \"message\":\"操作失败\"}");
		}
		return null;
	}

	/**
	 * 处理群组节点配置信息
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param ModelAndView
	 */
	@RequestMapping("/sysManage/saveGroupNodes.do")
	public ModelAndView saveGroupNodes(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		String[] nodes = request.getParameterValues("t_node");
		Long id = convertUtil.toLong(request.getParameter("group_id"), -1L);

		String forwardUrl = "sysManage/groupList.do?group_id="
				+ convertUtil.toString(request.getParameter("group_id"), "");
		// 获取岗位的对象
		StringBuffer checknode = new StringBuffer();
		checknode
				.append("select ta15 from Ta15_group_node ta15 where group_id=");
		checknode.append(id);
		try {
			// 把数据库所有岗位相关的角色放到集合里面
			List allnodeList = (dao.search(checknode.toString()));
			if (nodes == null || nodes.length == 0) {
				// 对现有的岗位对象进行判断，如果不为空，进行清空
				if (!allnodeList.isEmpty()) {
					for (int r = 0; r < allnodeList.size(); r++) {
						dao.removeObject(allnodeList.toArray()[r]);
					}
				}
			} else {
				// 对现有的岗位对象进行判断，如果不为空，进行清空
				if (!allnodeList.isEmpty()) {
					for (int r = 0; r < allnodeList.size(); r++) {
						dao.removeObject(allnodeList.toArray()[r]);
					}
				}
				// 对配置的角色进行保存
				for (int i = 0; i < nodes.length; i++) {
					Ta15_group_node ta15_group_node = new Ta15_group_node();
					ta15_group_node.setGroup_id(id);
					ta15_group_node.setNode_id(new Long(nodes[i]));
					if (id != -1) {
						dao.saveObject(ta15_group_node);
					}
				}
			}
			response
					.getWriter()
					.print(
							"{\"statusCode\":\"200\", \"message\":\"操作成功\", \"navTabId\":\"groupList\",\"forwardUrl\":\""
									+ forwardUrl + "\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			response.getWriter().print(
					"{\"statusCode\":\"300\", \"message\":\"操作失败\"}");
		}
		return null;
	}
	
	/**
	 * 处理节点字段配置信息
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param ModelAndView
	 */
	@RequestMapping("/sysManage/saveNodeFields.do")
	public ModelAndView saveNodeFields(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		String[] fields = request.getParameterValues("t_field");
		Long id = convertUtil.toLong(request.getParameter("node_id"), -1L);
		Long node_type = convertUtil.toLong(request.getParameter("node_type"), 1L);

		
		// 获取岗位的对象
		StringBuffer checkfield = new StringBuffer();
		checkfield
				.append("select ta16 from Ta16_node_field ta16 where node_id=");
		checkfield.append(id);
		if (node_type==1) {
			checkfield.append(" and (ta16.node_type=");
			checkfield.append(node_type);
			checkfield.append(" or ta16.node_type is null)");
		}else {
			checkfield.append(" and ta16.node_type=");
			checkfield.append(node_type);
		}

		try {
			// 把数据库所有岗位相关的角色放到集合里面
			List allfieldList = (dao.search(checkfield.toString()));
			if (fields == null || fields.length == 0) {
				// 对现有的岗位对象进行判断，如果不为空，进行清空
				if (!allfieldList.isEmpty()) {
					for (int r = 0; r < allfieldList.size(); r++) {
						dao.removeObject(allfieldList.toArray()[r]);
					}
				}
			} else {
				// 对现有的岗位对象进行判断，如果不为空，进行清空
				if (!allfieldList.isEmpty()) {
					for (int r = 0; r < allfieldList.size(); r++) {
						dao.removeObject(allfieldList.toArray()[r]);
					}
				}
				// 对配置的角色进行保存
				for (int i = 0; i < fields.length; i++) {
					Ta16_node_field ta16_node_field = new Ta16_node_field();
					ta16_node_field.setField_id(new Long(fields[i]));
					ta16_node_field.setNode_id(id);
					ta16_node_field.setNode_type(node_type);
					if (id != -1) {
						dao.saveObject(ta16_node_field);
					}
				}
			}
			gcsjDataService.setSelectValue(request, id);
			createJspFile.createJspFileToRecord(request.getSession().getServletContext().getRealPath(
			"/WEB-INF"), id);
//			createJspFile.createJspFileToForm(request.getSession().getServletContext().getRealPath(
//			"/WEB-INF"), id);
			createJspFile.createTableFile(request, -1L);
			response
					.getWriter()
					.print(
							"{\"statusCode\":\"200\", \"message\":\"操作成功\", \"navTabId\":\"\",\"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(
					"{\"statusCode\":\"300\", \"message\":\"操作失败\"}");
		}
		return null;
	}
}
