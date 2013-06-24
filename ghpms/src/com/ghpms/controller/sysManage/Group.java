package com.ghpms.controller.sysManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
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

import com.netsky.base.baseDao.Dao;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.convertUtil;

import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta05_group;

/**
 * @description: 部门维护
 * @class name:com.netsky.base.controller.Dept
 * @author Administrator Mar 8, 2011
 */
@Controller
public class Group {
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
	 * 部门列表
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/sysManage/groupList.do")
	public ModelAndView groupList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		ModelMap modelMap = new ModelMap();
		List<Ta05_group> groupList = (List<Ta05_group>) dao
				.search("from Ta05_group order by id");// 所有部门（树）
		modelMap.put("groupList", groupList);
		return new ModelAndView("/WEB-INF/jsp/sysManage/groupList.jsp",
				modelMap);
	}

	@RequestMapping("/sysManage/groupEdit.do")
	public ModelAndView groupEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		ModelMap modelMap = new ModelMap();

		Ta05_group group = null;
		group = (Ta05_group) dao.getObject(Ta05_group.class, id);
		modelMap.put("group", group);
		StringBuffer user_rsql = new StringBuffer();
		user_rsql
				.append(" select ta03 from Ta14_group_user ta14,Ta03_user ta03 ");
		user_rsql.append(" where ta14.user_id = ta03.id  and ta14.group_id = ");
		user_rsql.append(id);
		List users = dao.search(user_rsql.toString());
		modelMap.put("users", users);

		StringBuffer node_rsql = new StringBuffer();
		node_rsql
				.append(" select tb02 from Ta15_group_node ta15,Tb02_node tb02 ");
		node_rsql.append(" where ta15.node_id = tb02.id  and ta15.group_id = ");
		node_rsql.append(id);
		List nodes = dao.search(node_rsql.toString());
		modelMap.put("nodes", nodes);

		return new ModelAndView("/WEB-INF/jsp/sysManage/groupEdit.jsp",
				modelMap);
	}

	@RequestMapping("/sysManage/ajaxGroupDel.do")
	public ModelAndView ajaxGroupDel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		ModelMap modelMap = new ModelMap();
		PrintWriter out = null;

		// 获取用户对象
		try {
			out = response.getWriter();
			dao.removeObject(Ta05_group.class, id);
			out
					.print("{\"statusCode\":\"200\", \"message\":\"群组删除成功\", \"navTabId\":\"\", \"forwardUrl\":\"sysManage/groupList.do\", \"callbackType\":\"forward\"}");
		} catch (IOException e) {
			exceptionService.exceptionControl(
					"com.ghpms.controller.sysManage.User", "群组删除失败", e);
		}
		return null;
	}
}
