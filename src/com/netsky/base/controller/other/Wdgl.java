package com.netsky.base.controller.other;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sun.net.ftp.FtpClient;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta02_station;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Te01_slave;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.dataObjects.Te10_wdml;
import com.netsky.base.dataObjects.WdView;

@Controller
public class Wdgl {
	@Autowired
	private Dao dao;
	@Autowired
	private ExceptionService exceptionService;
	/**
	 * 默认ftp配置文件路径
	 */
	private static String ftpConfigFile = "/ftpConfig/ftpConfig.xml";

	/**
	 * 文档列表(质量体系） limit(0:查看;1:管理)
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/other/wdList.do")
	public ModelAndView wdList(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		// 管理员可见所有树
		StringBuffer conditionStr = new StringBuffer();
		if (request.getSession().getAttribute("admin") == null
				|| !"true".equals(request.getSession().getAttribute("admin"))) {
			if (!"1".equals(request.getParameter("limit"))) {
				// 查阅树
				conditionStr.append(" and (");
				// 所有
				conditionStr.append("(cylb=0)");
				conditionStr.append(" or ");
				// 部门
				conditionStr.append("(cylb=1 and (cyfw like '%" + user.getDept_name() + "%' or cyfw like '%" + user.getParent_dept() + "%')) ");
				conditionStr.append(" or ");
				// 岗
				List<Ta02_station> staList = (List<Ta02_station>) dao
						.search("from Ta02_station ta02 where exists(select 'x' from Ta11_sta_user ta11 where ta11.station_id=ta02.id and ta11.user_id="
								+ user.getId() + ")");
				conditionStr.append("(cylb=2 and (");
				for (Ta02_station ta02 : staList) {
					conditionStr.append("cyfw like '%" + ta02.getName() + "%' or ");
				}
				conditionStr.append("1=2))");
				conditionStr.append(" or ");
				// 人
				conditionStr.append("(cylb=3 and cyfw like '%" + user.getName() + "%')");
				conditionStr.append(")");
			} else {
				// 管理树
				conditionStr.append(" and (cjbm='" + user.getDept_name() + "')");
			}
		}
		Long super_id = 1L;
		List<Map<String, Object>> te10List = getTe10List(super_id, conditionStr);
		modelMap.put("te10List", te10List);
		return new ModelAndView("/WEB-INF/jsp/other/wdList.jsp", modelMap);
	}

	@RequestMapping("/other/wdEdit.do")
	public ModelAndView wdEdit(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		Long id = convertUtil.toLong(request.getParameter("id"));
		Te10_wdml te10 = (Te10_wdml) dao.getObject(Te10_wdml.class, id);
		modelMap.put("te10", te10);
		if (convertUtil.toLong(request.getParameter("up_id")) != -1L) {
			modelMap.put("parent_te10", dao.getObject(Te10_wdml.class, convertUtil
					.toLong(request.getParameter("up_id"))));
		} else if (te10.getUp_id() != null) {
			modelMap.put("parent_te10", dao.getObject(Te10_wdml.class, te10.getUp_id()));
		}
		// 获取部门列表 管理员列出所有部门，非管理员列出所在部门
		{
			StringBuffer hsql = new StringBuffer();
			Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
			hsql.append("from Ta01_dept");
			if (request.getSession().getAttribute("admin") == null
					|| !"true".equals(request.getSession().getAttribute("admin"))) {
				hsql.append(" where id=" + user.getDept_id());
			}
			hsql.append(" order by id");
			modelMap.put("deptList", dao.search(hsql.toString()));
		}
		// 文件
		modelMap.put("uploadslave", dao.search("from Te01_slave where module_id=92 and doc_id=" + id));
		return new ModelAndView("/WEB-INF/jsp/other/wdEdit.jsp", modelMap);
	}

	@RequestMapping("/other/wdView.do")
	public ModelAndView wdView(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		Long id = convertUtil.toLong(request.getParameter("id"));
		Te10_wdml te10 = (Te10_wdml) dao.getObject(Te10_wdml.class, id);
		modelMap.put("te10", te10);
		// 显示该文件夹及以上3级文件夹 更多级的用..表示，点击后跳至上四级文件夹
		// 因此除当前级外还需要最多查三级文件夹
		List<Te10_wdml> te10List = new ArrayList<Te10_wdml>();
		Long up_id = te10.getUp_id();
		for (int i = 0; i < 3; i++) {
			if (up_id == null) {
				break;
			}
			Te10_wdml content = (Te10_wdml) dao.getObject(Te10_wdml.class, up_id);
			// 查询的最后一级目录 如果还有本级，则显示..
			if (i == 2) {
				content.setMc("..");
			}
			te10List.add(content);
			up_id = content.getUp_id();
		}
		// 反转LIST顺序
		Collections.reverse(te10List);
		modelMap.put("contents", te10List);

		// 文件
		modelMap.put("uploadslave", dao.search("from Te01_slave where module_id=92 and doc_id=" + id));
		return new ModelAndView("/WEB-INF/jsp/other/wdView.jsp?test=1", modelMap);

	}

	/**
	 * 通过该私有函数实现递归，实现多级树
	 * 
	 * @param up_id
	 * @return List<Map<String,Object>>
	 */
	private List<Map<String, Object>> getTe10List(Long up_id, StringBuffer conditionStr) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		StringBuffer hsql = new StringBuffer();
		hsql.append("from Te10_wdml where up_id=" + up_id);
		hsql.append(conditionStr);
		hsql.append(" order by mc");
		List<Te10_wdml> tmpList = (List<Te10_wdml>) dao.search(hsql.toString());
		for (Te10_wdml te10 : tmpList) {
			Map<String, Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("te10", te10);
			tmpMap.put("te10List", getTe10List(te10.getId(), conditionStr));
			resultList.add(tmpMap);
		}
		return resultList;
	}

	/**
	 * 文档目录删除
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *             void
	 */
	@RequestMapping("/other/wdmlDel.do")
	public void wdmlDel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"));
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			List<Te10_wdml> sonList = (List<Te10_wdml>) dao.search("from Te10_wdml where up_id=" + id);
			if (sonList != null && !sonList.isEmpty()) {
				out.print("{\"statusCode\":\"300\", \"message\":\"该目录有子目录，无法删除\"}");
				return;
			}
			// 因为需要删除附件，删除失败只记录异常信息，不做事务回滚
			dao.removeObject(Te10_wdml.class, id);
			List<Te01_slave> slaveList = (List<Te01_slave>) dao
					.search("from Te01_slave where module_id=92 and doc_id=" + id);
			Map<String, String> ftpConfig = this.getFtpConfig(request);
			FtpClient client = null;
			// 本TRY块意义：FTPCLIENT的开启与关闭
			try {
				client = new FtpClient();
				client.openServer(ftpConfig.get("address"));
				client.login(ftpConfig.get("username"), ftpConfig.get("password"));

				for (Te01_slave te01 : slaveList) {
					// 本try意义：个别FTP删除失败后记录异常信息，继续其他文件的删除
					try {
						client.sendServer("dele " + te01.getFtp_url() + "\r\n");
						client.readServerResponse();
					} catch (Exception e) {
						exceptionService.exceptionControl(this.getClass().getName(),
								"FTP删除失败，URL:" + te01.getFtp_url(), e);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (client != null && client.serverIsOpen())
					client.closeServer();
			}
			dao.update("delete from Te01_slave where module_id=92 and doc_id=" + id);
			out.print("{\"statusCode\":\"200\", \"message\":\"删除成功\", \"forwardUrl\":\""
					+ request.getParameter("_forwardUrl") + "\",\"callbackType\":\"forward\"}");
		} catch (Exception e) {
			out.print("{\"statusCode\":\"300\", \"message\":\"删除失败\"}");
		}
	}

	private Map<String, String> getFtpConfig(HttpServletRequest request) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		/**
		 * 通过xml文件获取配置信息
		 */
		String filePath = request.getSession().getServletContext().getRealPath("WEB-INF") + ftpConfigFile;
		File file = new File(filePath);
		if (!file.exists()) {
			throw new Exception("配置文件不存在，路径：" + filePath);
		}
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(file);
			Element root = doc.getRootElement();
			result.put("folder", root.elementText("folder"));
			result.put("address", root.elementText("address"));
			result.put("username", root.elementText("username"));
			result.put("password", root.elementText("password"));
		} catch (DocumentException ex) {
			throw new Exception("错误的xml格式，路径：" + filePath + "错误:" + ex);
		}
		return result;
	}

	/**
	 * 文档搜索
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/other/wdSearch.do")
	public ModelAndView wdSearch(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "contents");
		if (orderField.equals("")) {
			orderField = "contents";
		}
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		// 查询条件
		String keyword = convertUtil.toString(request.getParameter("keyword"));
		StringBuffer hsql = new StringBuffer();
		hsql.append("select wd from WdView wd where 1=1");
		// where条件
		// 关键字（wdView.remark）
		if (!keyword.equals("")) {
			hsql.append(" and (remark like '%" + keyword + "%' or file_name like '%" + keyword + "%')");
			// 如果不输入关键字 什么都不查出来
		} else {
			hsql.append(" and 1=2");
		}
		// 查阅权限引发的限制条件
		if (request.getSession().getAttribute("admin") == null
				|| !"true".equals(request.getSession().getAttribute("admin"))) {
			hsql.append(" and (");
			// 所有
			hsql.append("(cylb=0)");
			hsql.append(" or ");
			// 部门
			hsql.append("(cylb=1 and cyfw like '%" + user.getDept_name() + "%')");
			hsql.append(" or ");
			// 岗
			List<Ta02_station> staList = (List<Ta02_station>) dao
					.search("from Ta02_station ta02 where exists(select 'x' from Ta11_sta_user ta11 where ta11.station_id=ta02.id and ta11.user_id="
							+ user.getId() + ")");
			hsql.append("(cylb=2 and (");
			for (Ta02_station ta02 : staList) {
				hsql.append("cyfw like '%" + ta02.getName() + "%' or ");
			}
			hsql.append("1=2))");
			hsql.append(" or ");
			// 人
			hsql.append("(cylb=3 and cyfw like '%" + user.getName() + "%')");
			hsql.append(")");
		}
		// order排序
		// orderField
		hsql.append(" order by " + orderField);
		// orderDirection
		hsql.append(" " + orderDirection);

		ResultObject ro = dao.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<WdView> wdList = new ArrayList<WdView>();
		while (ro.next()) {
			wdList.add((WdView) ro.get("wd"));
		}
		modelMap.put("wdList", wdList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		// 页面所需内容
		return new ModelAndView("/WEB-INF/jsp/other/wdSearch.jsp", modelMap);
	}
	
	/**
	 * 查阅范围查找带回
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/other/getCyfw.do")
	public ModelAndView getCyfw(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		Long parentCylb = convertUtil.toLong(request.getParameter("parentCylb"), 0L);
		String parentCyfw = "'" + convertUtil.toString(request.getParameter("parentCyfw")).replaceAll("，", "','") + "'";
		// 获取部门列表（最大查阅范围为全部或为部门列表时）
		if (parentCylb == 0L || parentCylb == 1L) {
			String condition = "";
			if (parentCylb == 1L) {
				condition = " and name in (" + parentCyfw + ")";
			}
			List<Ta01_dept> ta01List = (List<Ta01_dept>) dao.search("from Ta01_dept where 1 = 1 " + condition + " and showflag is null order by id");
			modelMap.put("ta01List", ta01List);
		}
		// 获取岗位列表（最大查阅范围为全部或为岗位列表时）
		if (parentCylb == 0L || parentCylb == 2L) {
			String condition = "";
			if (parentCylb == 2L) {
				condition = " where name in (" + parentCyfw + ")";
			}
			List<Ta02_station> ta02List = (List<Ta02_station>) dao.search("from Ta02_station " + condition
					+ " order by id");
			modelMap.put("ta02List", ta02List);
		}
		// 按部门分类的人员列表
		{
			String condition = "";
			if (parentCylb == 1L) {
				condition = " where dept_id in (select id from Ta01_dept where showflag is null and name in(" + parentCyfw + "))";
			} else if (parentCylb == 2L) {
				condition = " where id in (select user_id from Ta11_sta_user where station_id in (select id from Ta02_station where name in ("
						+ parentCyfw + ")))";
			} else if (parentCylb == 3L) {
				condition = " where name in (" + parentCyfw + ")";
			}
			List<Ta03_user> ta03List = (List<Ta03_user>) dao
					.search("from Ta03_user " + condition + " order by dept_id");
			Map<Long, List<Ta03_user>> deptUserMap = new HashMap<Long, List<Ta03_user>>();
			List<Ta03_user> tmpList = new ArrayList<Ta03_user>();
			long dept_id = -1L;
			for (Ta03_user ta03 : ta03List) {
				if (dept_id != ta03.getDept_id()) {
					if (!tmpList.isEmpty()) {
						deptUserMap.put(dept_id, tmpList);
						tmpList = new ArrayList<Ta03_user>();
					}
				}
				dept_id = ta03.getDept_id();
				tmpList.add(ta03);
			}
			if (!tmpList.isEmpty()) {
				deptUserMap.put(dept_id, tmpList);
			}
			modelMap.put("deptUserMap", deptUserMap);
			modelMap.put("ta01AllList", dao.search("from Ta01_dept where showflag is null order by id"));
		}
		return new ModelAndView("/WEB-INF/jsp/other/getCyfw.jsp", modelMap);
	}
}
