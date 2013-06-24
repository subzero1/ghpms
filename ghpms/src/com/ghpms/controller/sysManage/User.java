package com.ghpms.controller.sysManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.Search_Level;
import com.netsky.base.utils.convertUtil;

import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta03_user;

/**
 * @description
 * 
 * @class com.netsky.base.controller.User
 * 
 * @author
 */
@Controller
public class User {
	/**
	 * 数据服务
	 */
	@Autowired
	private Dao dao;

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
	 * 检索用户列表，并分页返回
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/sysManage/userList.do")
	public ModelAndView userList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		/**
		List<Ta03_user> all_user_list = (List<Ta03_user>) queryService
				.searchList("from Ta03_user u where u.dept_id in (select d.id from Ta01_dept d)order by u.dept_id,u.name");
		List<Ta03_user> tmp_user_list = new ArrayList<Ta03_user>();
		Map<String, List<Ta03_user>> user_map = new HashMap<String, List<Ta03_user>>();
		String dept_id = "-1";
		for (Ta03_user user : all_user_list) {
			String user_dept_id=String.valueOf(user.getDept_id());
			if (!dept_id.equals(user_dept_id)) {
				if (tmp_user_list.size() != 0) {
					user_map.put(user_dept_id, tmp_user_list);
					tmp_user_list = new ArrayList<Ta03_user>();
				}

				dept_id = user_dept_id;
			}
			tmp_user_list.add(user);
		}
		if (tmp_user_list.size() != 0) {
			user_map.put(dept_id, tmp_user_list);
		}
		modelMap.put("user_map", user_map);
		modelMap.put("deptList", dao.search("from Ta01_dept d order by d.name"));
		*/
		StringBuffer hql=new StringBuffer();
		
		List userList=null;
		hql.append("select d.name,u from Ta03_user u,Ta01_dept d where 1=1 ");
		hql.append("and u.dept_id=d.id order by u.name");
		userList=queryService.searchList(hql.toString());
		
		List deptList=null;
		hql.delete(0, hql.length());
		hql.append("select d.name from Ta01_dept d order by d.name");
		deptList = queryService.searchList(hql.toString());

		modelMap.put("userList", userList);
		modelMap.put("deptList", deptList);
		return new ModelAndView("/WEB-INF/jsp/sysManage/userList.jsp", modelMap);
	}

	/**
	 * 通过ID获取单用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/sysManage/userEdit.do")
	public ModelAndView userEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		ModelMap modelMap = new ModelMap();

		Ta03_user user = null;
		// 获取查询级别
		List<Search_Level> list = new ArrayList<Search_Level>();
		Search_Level search_Level = new Search_Level(1, 1, "全部");
		list.add(search_Level);
		search_Level = new Search_Level(2, 2, "本地区");
		list.add(search_Level);
		search_Level = new Search_Level(3, 3, "本人参与");
		list.add(search_Level);
		modelMap.put("searchlevelList", list);
		
		List<Object> sendHtglyList = new LinkedList<Object>();
		Properties p = new Properties();
		p.setProperty("show", "是");
		p.setProperty("value", "1");
		sendHtglyList.add(p);
		modelMap.put("sendHtglyList", sendHtglyList);
		
		// 获取用户对象
		user = (Ta03_user) dao.getObject(Ta03_user.class, id);
		// 获取部门列表
		modelMap
				.put(
						"deptList",
						dao
								.search("from Ta01_dept dept where dept.area_name="
										+ "(select area_name from Ta03_user user where user.id="
										+ id + ")"));
		// 获取地区列表
		modelMap.put("areaList", dao
				.search("from Tc02_area where type like '%[3]%' order by id"));
		modelMap.put("userObj", user);
		StringBuffer group_rsql = new StringBuffer();
		group_rsql
				.append(" select ta05 from Ta14_group_user ta14,Ta05_group ta05 ");
		group_rsql
				.append(" where ta14.group_id = ta05.id  and ta14.user_id = ");
		group_rsql.append(id);
		List groups = dao.search(group_rsql.toString() + " order by ta05.name");
		modelMap.put("groups", groups);
		StringBuffer sta_rsql = new StringBuffer();
		sta_rsql
				.append(" select ta02 from Ta11_sta_user ta11,Ta02_station ta02 ");
		sta_rsql
				.append(" where ta11.station_id = ta02.id  and ta11.user_id = ");
		sta_rsql.append(id);
		List roles = dao.search(sta_rsql.toString() + " order by ta02.name");
		modelMap.put("stas", roles);
		return new ModelAndView("/WEB-INF/jsp/sysManage/userEdit.jsp", modelMap);
	}

	/**
	 * 用户删除ajax实现
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/sysManage/ajaxUserDel.do")
	public void ajaxUserDel(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		response.setContentType("text/xml");

		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		String act = convertUtil.toString(request.getParameter("act"));
		System.out.println(act + ":act");

		ModelMap modelMap = new ModelMap();

		// 获取用户对象
		try {
			out = response.getWriter();
			dao.removeObject(Ta03_user.class, id);
			out
					.print("{\"statusCode\":\"200\", \"message\":\"用户删除成功\", \"navTabId\":\"\", \"forwardUrl\":\"sysManage/userList.do\", \"callbackType\":\"forward\"}");
		} catch (IOException e) {
			exceptionService.exceptionControl(
					"com.crht.controller.sysManage.User", "用户删除失败", e);
		}
	}

	/**
	 * 查询所属地区的部门AJAX
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/sysManage/ajaxDeptQuery.do")
	public void ajaxDeptQuery(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("text/html;charset=GBK");
		String name = request.getParameter("name");
		List<Ta01_dept> list = (List<Ta01_dept>) dao
				.search("from Ta01_dept dept where dept.area_name=" + name);
		for (Ta01_dept dept : list) {
		}
	}

	/**
	 * 及连菜单ajax
	 * 
	 * @param request
	 * @param response
	 * @param var1
	 *            表名.列名
	 * @param var2
	 *            值
	 * @param var3
	 *            option的value由VAR3填充
	 * @param var4
	 *            option的显示由VAR4填充
	 */
	@RequestMapping("/sysManage/ajaxjiliancaidan.do")
	public void ajaxjiliancaidan(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("text/html;charset=GBK");
		String var1 = request.getParameter("var1");
		String var2 = request.getParameter("var2");
		String var3 = request.getParameter("var3");
		String var4 = request.getParameter("var4");
		String var5 = convertUtil.toString(request.getParameter("var5"),"id");
		if (!var2.equals("null")) {
			var1 = var1.replace('.', '/');
			String[] str_arr = var1.split("/");
			if (str_arr.length != 2) {
				throw new Exception("请确认'" + var1 + "'符合类名.列名的形式!");
			}
			String classname = str_arr[0];
			String columnname = str_arr[1];
			String hsql = "select " + var3 + "," + var4 + " from " + classname
					+ " where " + columnname + "='" + var2 + "'"
					+ " order by "+var5;
			List<Object[]> list = null;
			try {
				list = (List<Object[]>) dao.search(hsql);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(hsql);
			}
			String s = "";
			for (Object[] o : list) {
				s += "<option value='" + o[0].toString() + "'>" + o[1].toString()
						+ "</option>";
			}
			PrintWriter out = response.getWriter();
			out.print(s);
			out.flush();
			out.close();
	} 
	}
}
