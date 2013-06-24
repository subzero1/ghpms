package com.ghpms.controller.search;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.utils.NumberFormatUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.baseDao.JdbcSupport;

/**
 * 用户查询
 * 
 * @author CT
 * @create 2010-03-17
 */
@Controller
public class SysUseSearch {

	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;

	@Autowired
	private ExceptionService exceptionService;
	
	@Autowired
	private JdbcSupport jdbcSupport;

	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping("/search/wxdwReceiveAndTimeout.do")
	public ModelAndView wxdwReceiveAndTimeout(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		Ta03_user user = null;
		user = (Ta03_user) session.getAttribute("user");
		if (user == null) {
			return exceptionService.exceptionControl(this.getClass().getName(),
					"用户未登录或登录超时", new Exception("用户未登录"));
		}
		String lxsj1 = convertUtil.toString(request.getParameter("lxsj1"), "");
		String lxsj2 = convertUtil.toString(request.getParameter("lxsj2"), "");
		String pdsj1 = convertUtil.toString(request.getParameter("pdsj1"), "");
		String pdsj2 = convertUtil.toString(request.getParameter("pdsj2"), "");
		String dwlb = convertUtil.toString(request.getParameter("dwlb"), "sg");
		String ywxm = convertUtil.toString(request.getParameter("ywxm"), "");

		StringBuffer sql = new StringBuffer();
		ResultObject ro = null;
		ResultObject ro2 = null;
		List list = new LinkedList();
		ModelMap modelMap = new ModelMap();
		try {
			String sql_tmp = "";
			if (!lxsj1.equals("") && !lxsj2.equals("")) {
				sql_tmp += "and lxsj >= to_date('" + lxsj1 + "','yyyy-mm-dd') ";
				sql_tmp += "and lxsj <= to_date('" + lxsj2 + "','yyyy-mm-dd') ";
			}
			if (!pdsj1.equals("") && !pdsj2.equals("")) {
				if (dwlb.equals("sj")) {
					sql_tmp += "and sjpgsj >= to_date('" + pdsj1
							+ "','yyyy-mm-dd') ";
					sql_tmp += "and sjpgsj <= to_date('" + pdsj2
							+ "','yyyy-mm-dd') ";
				}
				if (dwlb.equals("sg")) {
					sql_tmp += "and sgpfsj >= to_date('" + pdsj1
							+ "','yyyy-mm-dd') ";
					sql_tmp += "and sgpfsj <= to_date('" + pdsj2
							+ "','yyyy-mm-dd') ";
				}
				if (dwlb.equals("jl")) {
					sql_tmp += "and jlpfsj >= to_date('" + pdsj1
							+ "','yyyy-mm-dd') ";
					sql_tmp += "and jlpfsj <= to_date('" + pdsj2
							+ "','yyyy-mm-dd') ";
				}
			}

			String sql_tmp2 = "";
			if (!lxsj1.equals("") && !lxsj2.equals("")) {
				sql_tmp2 += "and lxsj >= to_date('" + lxsj1
						+ "','yyyy-mm-dd') ";
				sql_tmp2 += "and lxsj <= to_date('" + lxsj2
						+ "','yyyy-mm-dd') ";
			}
			if (!pdsj1.equals("") && !pdsj2.equals("")) {
				sql_tmp2 += "and sgpfsj >= to_date('" + pdsj1
						+ "','yyyy-mm-dd') ";
				sql_tmp2 += "and sgpfsj <= to_date('" + pdsj2
						+ "','yyyy-mm-dd') ";
			}

			sql.delete(0, sql.length());
			sql.append("select mc from Tf01_wxdw where 1 = 1 ");
			sql.append("and decode(lb,'施工','sg','设计','sj','jl') = '");
			sql.append(dwlb);
			sql.append("' order by mc");
			ro = queryService.search(sql.toString());
			while (ro.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				String mc = (String) ro.get("mc");
				map.put("mc", mc);

				/*
				 * 派单数
				 */
				sql.delete(0, sql.length());
				sql.append("select count(id) as pds ");
				sql.append("from Td01_xmxx ");
				sql.append("where ");
				sql.append(dwlb);
				sql.append("dw = '");
				sql.append(mc);
				sql.append("' ");
				ro2 = queryService.search(sql.toString() + sql_tmp);
				Long pds = 0L;
				if (ro2.next()) {
					pds = convertUtil.toLong(ro2.get("pds"));
				}
				map.put("pds", pds);

				/*
				 * 接单数
				 */
				sql.delete(0, sql.length());
				sql.append("select count(id) as jds ");
				sql.append("from Td01_xmxx where ");
				sql.append(dwlb);
				sql.append("dw = '");
				sql.append(mc);
				sql.append("' and ");
				sql.append(dwlb);
				sql.append("ysl is not null ");
				ro2 = queryService.search(sql.toString() + sql_tmp);
				Long jds = 0L;
				if (ro2.next()) {
					jds = convertUtil.toLong(ro2.get("jds"));
				}
				map.put("jds", jds);

				if (dwlb.equals("sg")) {
					/*
					 * 超期数
					 */
					sql.delete(0, sql.length());
					sql.append("select count(id) as cqs ");
					sql.append("from Td01_xmxx where ");
					sql.append(dwlb);
					sql.append("dw = '");
					sql.append(mc);
					sql
							.append("' and (sjkgsj + yqgq < sjjgsj or (sjjgsj is null and sjkgsj + yqgq < sysdate)) ");
					ro2 = queryService.search(sql.toString() + sql_tmp2);
					Long cqs = 0L;
					if (ro2.next()) {
						cqs = convertUtil.toLong(ro2.get("cqs"));
					}
					map.put("cqs", cqs);

					/*
					 * 超期率
					 */
					Double cql = 0d;
					if (pds != 0) {
						cql = NumberFormatUtil.divToDouble(cqs, pds);
					}
					map.put("cql", cql);

					/*
					 * 决算数
					 */
					sql.delete(0, sql.length());
					sql.append("select count(id) as jss ");
					sql.append("from Td01_xmxx where ");
					sql.append(dwlb);
					sql.append("dw = '");
					sql.append(mc);
					sql.append("' and jssj is not null ");
					ro2 = queryService.search(sql.toString() + sql_tmp2);
					Long jss = 0L;
					if (ro2.next()) {
						jss = convertUtil.toLong(ro2.get("jss"));
					}
					map.put("jss", jss);

					/*
					 * 决算率
					 */
					Double jsl = 0d;
					if (pds != 0) {
						jsl = NumberFormatUtil.divToDouble(jss, pds);
					}
					map.put("jsl", jsl);
				}

				if ((ywxm.equals("有项目") && pds > 0)
						|| (ywxm.equals("无项目") && pds == 0)
						|| (ywxm.equals(""))) {
					list.add(map);
				}
				
				//实际选择数
				sql.delete(0, sql.length());
				sql.append("select count(a.id) as sjxzs ");
				sql.append("from Td08_pgspd a,Td01_xmxx b where 1=1 ");
				sql.append("and a.sjxzdw=b.sgdw ");
				sql.append("and b.id=a.project_id ");
				sql.append(" and a.sjxzdw='");
				sql.append(mc);
				sql.append("' ");
				sql.append(" and a.splb='");
				sql.append("项目派工");
				sql.append("' ");
				sql.append(" and a.sp_flag=1 ");
				Long sjxzs=0L;
				ro2 =queryService.search(sql.toString()+sql_tmp);
				while (ro2.next()) {
					sjxzs=convertUtil.toLong(ro2.get("sjxzs"));
				}
				map.put("sjxzs", sjxzs);
				
			}
			modelMap.put("jdcqList", list);

			String[] ywxmList = { "有项目", "无项目" };
			modelMap.put("ywxmList", ywxmList);

			List<Object> dwlbList = new LinkedList<Object>();
			Properties p = new Properties();
			p.setProperty("show", "施工");
			p.setProperty("value", "sg");
			dwlbList.add(p);
			p = new Properties();
			p.setProperty("show", "设计");
			p.setProperty("value", "sj");
			dwlbList.add(p);
			p = new Properties();
			p.setProperty("show", "监理");
			p.setProperty("value", "jl");
			dwlbList.add(p);
			modelMap.put("dwlbList", dwlbList);
		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(),
					"系统出错，请联系管理员", new Exception(e + e.getMessage()));
		}

		return new ModelAndView(
				"/WEB-INF/jsp/search/wxdwReceiveAndTimeout.jsp", modelMap);
	}
	
	@RequestMapping("/aux/wxdwReceiveAndTimeout2.do")
	public ModelAndView wxdwReceiveAndTimeout2(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws SQLException {
		String view="/WEB-INF/jsp/search/wxdwReceiveAndTimeout2.jsp";
		ModelMap modelMap=new ModelMap();
		Ta03_user user = null;
		user = (Ta03_user) session.getAttribute("user");
		if (user == null) {
			return exceptionService.exceptionControl(this.getClass().getName(),
					"用户未登录或登录超时", new Exception("用户未登录"));
		}
		String lxsj1 = convertUtil.toString(request.getParameter("lxsj1"), "");
		String lxsj2 = convertUtil.toString(request.getParameter("lxsj2"), "");
		String pdsj1 = convertUtil.toString(request.getParameter("pdsj1"), "");
		String pdsj2 = convertUtil.toString(request.getParameter("pdsj2"), "");
		String dwlb = convertUtil.toString(request.getParameter("dwlb"), "sg");
		String ywxm = convertUtil.toString(request.getParameter("ywxm"), "");
		String s_dwlb = null;
		if(dwlb.equals("sg")){
			s_dwlb = "1";
		}
		else if(dwlb.equals("sj")){
			s_dwlb = "2";
		}
		else{
			s_dwlb = "3";
		}
		
		Connection con = jdbcSupport.getConnection();
		con.setAutoCommit(false);
		String procedure = "{call hzdw_case(?,?,?,?,?,?)}";
		CallableStatement cstmt = con.prepareCall(procedure);
		cstmt.setString(1, lxsj1);
		cstmt.setString(2, lxsj2);
		cstmt.setString(3, pdsj1);
		cstmt.setString(4, pdsj2);
		cstmt.setString(5, s_dwlb);
		cstmt.setString(6, ywxm);
		cstmt.executeUpdate(); 
		cstmt.close();
		con.commit();
		con.close();
		List jdcqList=queryService.searchList("select a from Tf32_hzdw_status a order by a.mc ");
		modelMap.put("jdcqList", jdcqList);
		
		String[] ywxmList = { "有项目", "无项目" };
		modelMap.put("ywxmList", ywxmList);

		List<Object> dwlbList = new LinkedList<Object>();
		Properties p = new Properties();
		p.setProperty("show", "施工");
		p.setProperty("value", "sg");
		dwlbList.add(p);
		p = new Properties();
		p.setProperty("show", "设计");
		p.setProperty("value", "sj");
		dwlbList.add(p);
		p = new Properties();
		p.setProperty("show", "监理");
		p.setProperty("value", "jl");
		dwlbList.add(p);
		modelMap.put("dwlbList", dwlbList);
		return new ModelAndView(view,modelMap);
	}

	
	
	@RequestMapping("/search/xmglyDownAndTimeout.do")
	public ModelAndView xmglyDownAndTimeout(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		Ta03_user user = null;
		user = (Ta03_user) session.getAttribute("user");
		if (user == null) {
			return exceptionService.exceptionControl(this.getClass().getName(),
					"用户未登录或登录超时", new Exception("用户未登录"));
		}
		String lxsj1 = convertUtil.toString(request.getParameter("lxsj1"), "");
		String lxsj2 = convertUtil.toString(request.getParameter("lxsj2"), "");
		String pdsj1 = convertUtil.toString(request.getParameter("pdsj1"), "");
		String pdsj2 = convertUtil.toString(request.getParameter("pdsj2"), "");
		String ywxm = convertUtil.toString(request.getParameter("ywxm"), "");

		StringBuffer sql = new StringBuffer();
		ResultObject ro = null;
		ResultObject ro2 = null;
		List list = new LinkedList();
		ModelMap modelMap = new ModelMap();
		try {
			sql.delete(0, sql.length());
			sql.append("select ta03.name as name ");
			sql
					.append(" from Ta03_user ta03,Ta11_sta_user ta11,Ta02_station ta02 ");
			sql.append("where ta03.id = ta11.user_id ");
			sql.append("and ta02.id = ta11.station_id ");
			sql.append("and ta02.name like '%项目管理岗%' ");
			sql.append("and ta03.dept_id = ");
			sql.append(user.getDept_id());
			sql.append(" order by ta03.name");
			ro = queryService.search(sql.toString());
			while (ro.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				String name = (String) ro.get("name");
				map.put("name", name);

				/*
				 * 项目数
				 */
				sql.delete(0, sql.length());
				sql.append("select count(id) as xms ");
				sql.append("from Td01_xmxx ");
				sql.append("where xmgly = '");
				sql.append(name);
				sql.append("' ");
				if (!lxsj1.equals("") && !lxsj2.equals("")) {
					sql.append("and lxsj >= to_date('" + lxsj1
							+ "','yyyy-mm-dd') ");
					sql.append("and lxsj <= to_date('" + lxsj2
							+ "','yyyy-mm-dd') ");
				}
				if (!pdsj1.equals("") && !pdsj2.equals("")) {
					sql.append("and ((sjpgsj >= to_date('" + pdsj1
							+ "','yyyy-mm-dd') ");
					sql.append("and sjpgsj <= to_date('" + pdsj2
							+ "','yyyy-mm-dd')) ");
					sql.append("or (sgpfsj >= to_date('" + pdsj1
							+ "','yyyy-mm-dd') ");
					sql.append("and sgpfsj <= to_date('" + pdsj2
							+ "','yyyy-mm-dd')) ");
					sql.append("or (jlpfsj >= to_date('" + pdsj1
							+ "','yyyy-mm-dd') ");
					sql.append("and jlpfsj <= to_date('" + pdsj2
							+ "','yyyy-mm-dd'))) ");
				}
				ro2 = queryService.search(sql.toString());
				Long xms = 0L;
				if (ro2.next()) {
					xms = convertUtil.toLong(ro2.get("xms"));
				}
				map.put("xms", xms);

				/*
				 * 派工数
				 */
				sql.delete(0, sql.length());
				sql.append("select count(id) as pgs ");
				sql.append("from Td01_xmxx ");
				sql.append("where xmgly = '");
				sql.append(name);
				sql.append("' and [dw] is not null ");
				if (!lxsj1.equals("") && !lxsj2.equals("")) {
					sql.append("and lxsj >= to_date('" + lxsj1
							+ "','yyyy-mm-dd') ");
					sql.append("and lxsj <= to_date('" + lxsj2
							+ "','yyyy-mm-dd') ");
				}
				if (!pdsj1.equals("") && !pdsj2.equals("")) {
					sql.append("and [pdsj] >= to_date('" + pdsj1
							+ "','yyyy-mm-dd') ");
					sql.append("and [pdsj] <= to_date('" + pdsj2
							+ "','yyyy-mm-dd') ");
				}
				/*
				 * 派设计
				 */
				ro2 = queryService.search(sql.toString()
						.replace("[dw]", "sjdw").replace("[pdsj]", "sjpgsj"));
				Long psjs = 0L;
				if (ro2.next()) {
					psjs = convertUtil.toLong(ro2.get("pgs"), 0L);
				}
				map.put("psjs", psjs);
				/*
				 * 派施工
				 */
				ro2 = queryService.search(sql.toString()
						.replace("[dw]", "sgdw").replace("[pdsj]", "sgpfsj"));
				Long psgs = 0L;
				if (ro2.next()) {
					psgs = convertUtil.toLong(ro2.get("pgs"), 0L);
				}
				map.put("psgs", psgs);
				/*
				 * 派监理
				 */
				ro2 = queryService.search(sql.toString()
						.replace("[dw]", "jldw").replace("[pdsj]", "jlpfsj"));
				Long pjls = 0L;
				if (ro2.next()) {
					pjls = convertUtil.toLong(ro2.get("pgs"), 0L);
				}
				map.put("pjls", pjls);

				/*
				 * 超期数
				 */
				sql.delete(0, sql.length());
				sql.append("select count(id) as cqs ");
				sql.append("from Td01_xmxx ");
				sql.append("where xmgly = '");
				sql.append(name);
				sql
						.append("' and (sjkgsj + yqgq < sjjgsj or (sjjgsj is null and sjkgsj + yqgq < sysdate)) ");
				if (!lxsj1.equals("") && !lxsj2.equals("")) {
					sql.append("and lxsj >= to_date('" + lxsj1
							+ "','yyyy-mm-dd') ");
					sql.append("and lxsj <= to_date('" + lxsj2
							+ "','yyyy-mm-dd') ");
				}
				if (!pdsj1.equals("") && !pdsj2.equals("")) {
					sql.append("and sgpfsj >= to_date('" + pdsj1
							+ "','yyyy-mm-dd') ");
					sql.append("and sgpfsj <= to_date('" + pdsj2
							+ "','yyyy-mm-dd') ");
				}
				ro2 = queryService.search(sql.toString());
				Long cqs = 0L;
				if (ro2.next()) {
					cqs = convertUtil.toLong(ro2.get("cqs"));
				}
				map.put("cqs", cqs);

				/*
				 * 超期率
				 */
				Double cql = 0d;
				if (xms != 0) {
					cql = NumberFormatUtil.divToDouble(cqs, xms);
				}
				map.put("cql", cql);

				/*
				 * 决算数
				 */
				sql.delete(0, sql.length());
				sql.append("select count(id) as jss ");
				sql.append("from Td01_xmxx ");
				sql.append("where xmgly = '");
				sql.append(name);
				sql.append("' and jssj is not null ");
				if (!lxsj1.equals("") && !lxsj2.equals("")) {
					sql.append("and lxsj >= to_date('" + lxsj1
							+ "','yyyy-mm-dd') ");
					sql.append("and lxsj <= to_date('" + lxsj2
							+ "','yyyy-mm-dd') ");
				}
				if (!pdsj1.equals("") && !pdsj2.equals("")) {
					sql.append("and sgpfsj >= to_date('" + pdsj1
							+ "','yyyy-mm-dd') ");
					sql.append("and sgpfsj <= to_date('" + pdsj2
							+ "','yyyy-mm-dd') ");
				}
				ro2 = queryService.search(sql.toString());
				Long jss = 0L;
				if (ro2.next()) {
					jss = convertUtil.toLong(ro2.get("jss"));
				}
				map.put("jss", jss);

				/*
				 * 决算率
				 */
				Double jsl = 0d;
				if (xms != 0) {
					jsl = NumberFormatUtil.divToDouble(jss, xms);
				}
				map.put("jsl", jsl);
				if ((ywxm.equals("有项目") && xms > 0)
						|| (ywxm.equals("无项目") && xms == 0)
						|| (ywxm.equals(""))) {
					list.add(map);
				}
			}
			modelMap.put("pdcqList", list);

			String[] ywxmList = { "有项目", "无项目" };
			modelMap.put("ywxmList", ywxmList);
		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(),
					"系统出错，请联系管理员", new Exception(e + e.getMessage()));
		}

		return new ModelAndView("/WEB-INF/jsp/search/xmglyDownAndTimeout.jsp",
				modelMap);

	}
	
	@RequestMapping("/search/xmglyDownAndTimeout2.do")
	public ModelAndView xmglyDownAndTimeout2(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		Ta03_user user = null;
		user = (Ta03_user) session.getAttribute("user");
		if (user == null) {
			return exceptionService.exceptionControl(this.getClass().getName(),
					"用户未登录或登录超时", new Exception("用户未登录"));
		}
		String lxsj1 = convertUtil.toString(request.getParameter("lxsj1"), "");
		String lxsj2 = convertUtil.toString(request.getParameter("lxsj2"), "");
		String pdsj1 = convertUtil.toString(request.getParameter("pdsj1"), "");
		String pdsj2 = convertUtil.toString(request.getParameter("pdsj2"), "");
		String dwlb = convertUtil.toString(request.getParameter("dwlb"), "sg");
		String ywxm = convertUtil.toString(request.getParameter("ywxm"), "");
		String s_dwlb=null;

		StringBuffer sql = new StringBuffer();
		ResultObject ro = null;
		ResultObject ro2 = null;
		List list = new LinkedList();
		ModelMap modelMap = new ModelMap();
		try { 
			if(dwlb.equals("sg")){
				s_dwlb = "1";
			}
			else if(dwlb.equals("sj")){
				s_dwlb = "2";
			}
			else{
				s_dwlb = "3";
			}
			
			Connection con = jdbcSupport.getConnection();
			con.setAutoCommit(false);
			String procedure = "{call xmglyDownAndTimeout(?,?,?,?,?,?,?)}";
			CallableStatement cstmt = con.prepareCall(procedure);
			cstmt.setString(1, lxsj1);
			cstmt.setString(2, lxsj2);
			cstmt.setString(3, pdsj1);
			cstmt.setString(4, pdsj2);
			cstmt.setString(5, s_dwlb);
			cstmt.setString(6, ywxm);
			cstmt.setLong(7, user.getDept_id());
			cstmt.executeUpdate(); 
			cstmt.close();
			con.commit();
			con.close();
			sql.append("select a from Tf43_temp a order by a.c1");
			list=queryService.searchList(sql.toString());
			modelMap.put("pdcqList", list);
		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(),
					"系统出错，请联系管理员", new Exception(e + e.getMessage()));
		}

		return new ModelAndView("/WEB-INF/jsp/search/xmglyDownAndTimeout2.jsp",
				modelMap);

	}
	

	@RequestMapping("/search/userLogin.do")
	public ModelAndView userLogin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		Ta03_user user = null;
		user = (Ta03_user) session.getAttribute("user");
		if (user == null) {
			return exceptionService.exceptionControl(this.getClass().getName(),
					"用户未登录或登录超时", new Exception("用户未登录"));
		}
		String dlsj1 = convertUtil.toString(request.getParameter("dlsj1"), "");
		String dlsj2 = convertUtil.toString(request.getParameter("dlsj2"), "");
		String tjlb = convertUtil
				.toString(request.getParameter("tjlb"), "hzdw");

		StringBuffer sql = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		ResultObject ro = null;
		ResultObject ro2 = null;
		List list = new LinkedList();
		ModelMap modelMap = new ModelMap();
		try {
			String sql_tmp = "";
			if (!dlsj1.equals("") && !dlsj2.equals("")) {
				sql_tmp += "and login_date >= to_date('" + dlsj1
						+ "','yyyy-mm-dd') ";
				sql_tmp += "and login_date <= to_date('" + dlsj2
						+ "','yyyy-mm-dd') ";
			}
			if (tjlb.equals("xmgly")) {
				sql.delete(0, sql.length());
				sql.append("select ta03.name as mc ,count(tz03.id) ");
				sql
						.append(" from Ta03_user ta03,Ta11_sta_user ta11,Ta02_station ta02,Tz03_login_log tz03 ");
				sql.append("where ta03.id = ta11.user_id ");
				sql.append("and tz03.login_id = ta03.login_id ");
				sql.append("and ta02.id = ta11.station_id ");
				sql.append("and ta02.name like '%项目管理岗%' ");
				sql.append(sql_tmp);
				sql.append("and ta03.dept_id = ");
				sql.append(user.getDept_id());
				sql.append(" group by ta03.name ");
				sql.append("order by ta03.name");

				sql2.delete(0, sql.length());
				sql2.append("select ta03.name as mc  ");
				sql2
						.append(" from Ta03_user ta03,Ta11_sta_user ta11,Ta02_station ta02 ");
				sql2.append("where ta03.id = ta11.user_id ");
				sql2.append("and ta02.id = ta11.station_id ");
				sql2.append("and ta02.name like '%项目管理岗%' ");
				sql2
						.append("and not exists(select 'x' from Tz03_login_log tz03 where tz03.login_id = ta03.login_id "
								+ sql_tmp + ")");
				sql2.append("and ta03.dept_id = ");
				sql2.append(user.getDept_id());
				sql2.append(" ");
				sql2.append("order by ta03.name");

			} else {
				sql.delete(0, sql.length());
				sql.append("select tf01.mc as mc ,count(tz03.id) as dls ");
				sql
						.append(" from Ta03_user ta03,Tf04_wxdw_user tf04,Tf01_wxdw tf01,Tz03_login_log tz03 ");
				sql.append("where ta03.id = tf04.user_id ");
				sql.append("and tf04.wxdw_id = tf01.id ");
				sql.append(sql_tmp);
				sql.append("and ta03.login_id = tz03.login_id ");
				sql.append(" group by tf01.mc ");
				sql.append("order by tf01.mc");

				sql2.delete(0, sql.length());
				sql2
						.append("select tf011.mc as mc from Tf01_wxdw tf011 where mc not in(");
				sql2.append("select tf01.mc as mc  ");
				sql2
						.append(" from Ta03_user ta03,Tf04_wxdw_user tf04,Tf01_wxdw tf01,Tz03_login_log tz03 ");
				sql2.append("where ta03.id = tf04.user_id ");
				sql2.append(sql_tmp);
				sql2.append("and ta03.login_id = tz03.login_id ");
				sql2.append("and tf04.wxdw_id = tf01.id ) ");
				sql2.append("order by tf011.mc ");
			}
			list = queryService.searchList(sql.toString());
			modelMap.put("dlList", list);

			List list2 = queryService.searchList(sql2.toString());
			modelMap.put("dlList2", list2);

			List<Object> tjlbList = new LinkedList<Object>();
			Properties p = new Properties();
			p.setProperty("show", "合作单位");
			p.setProperty("value", "hzdw");
			tjlbList.add(p);
			p = new Properties();
			p.setProperty("show", "项目管理员");
			p.setProperty("value", "xmgly");
			tjlbList.add(p);
			modelMap.put("tjlbList", tjlbList);
		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(),
					"系统出错，请联系管理员", new Exception(e + e.getMessage()));
		}

		return new ModelAndView("/WEB-INF/jsp/search/userLogin.jsp", modelMap);

	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @data 地区一:{专业一:{单位一:金额一,单位二:金额二,单位三:金额三.....},专业二:{单位三:金额三,单位四:金额四,单位五:金额五}},地区二{}：
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/search/dqZyFezb.do")
	public ModelAndView dqZyFezb(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		Ta03_user user = null;
		user = (Ta03_user) session.getAttribute("user");
		String m_results="";
		String searchFlag=convertUtil.toString(request.getParameter("searchFlag"));
		if (user == null) {
			return exceptionService.exceptionControl(this.getClass().getName(),
					"用户未登录或登录超时", new Exception("用户未登录"));
		}

		StringBuffer sql = new StringBuffer();
		ResultObject ro = null;
		String g_gclb = convertUtil.toString(request.getParameter("gclb"));
		String g_ssdq = convertUtil.toString(request.getParameter("ssdq"));
		String start_sgpfsj = convertUtil.toString(request
				.getParameter("start_sgpfsj"));
		String end_sgpfsj = convertUtil.toString(request
				.getParameter("end_sgpfsj"));
		String start_lxsj = convertUtil.toString(request
				.getParameter("start_lxsj"));
		String end_lxsj = convertUtil
				.toString(request.getParameter("end_lxsj"));
		ModelMap modelMap = new ModelMap();
		try {
			Map dqMap = new HashMap();
			sql.delete(0, sql.length());
			sql.append("select ssdq,gclb,sgdw,sum(sghtje) as sghtje ");
			sql.append("from Td01_xmxx ");
			sql.append("where ssdq is not null ");
			sql.append("and gclb is not null ");
			sql.append("and sgdw is not null ");
			sql.append("and sghtje is not null ");

			if (!g_ssdq.equals("")) {
				sql.append("and ssdq='");
				sql.append(g_ssdq);
				sql.append("' ");
			}
			if (!g_gclb.equals("")) {
				sql.append("and gclb='");
				sql.append(g_gclb);
				sql.append("' ");
			}
			//时间比较
			 if (!start_lxsj.equals("")) {
				sql.append("and lxsj");
				sql.append(">=to_date('");
				sql.append(start_lxsj);
				sql.append("','yyyy-mm-dd') ");
			}
			 if (!end_lxsj.equals("")) {
					sql.append("and lxsj");
					sql.append("<=to_date('");
					sql.append(end_lxsj);
					sql.append("','yyyy-mm-dd') ");
				}

			 if (!start_sgpfsj.equals("")) {
				sql.append("and sgpfsj");
				sql.append(">=to_date('");
				sql.append(start_sgpfsj);
				sql.append("','yyyy-mm-dd') ");
			}

			 if (!end_sgpfsj.equals("")) {
				sql.append("and lxsj");
				sql.append(">=to_date('");
				sql.append(end_sgpfsj);
				sql.append("','yyyy-mm-dd') ");
			}

			sql.append("group by ssdq,gclb,sgdw ");
			ro = queryService.search(sql.toString());
			while (ro.next()) {
				String ssdq = (String) ro.get("ssdq");
				String gclb = (String) ro.get("gclb");
				String sgdw = (String) ro.get("sgdw");
				Double sghtje = (Double) ro.get("sghtje");
				if (!dqMap.containsKey(ssdq)) {
					Map zyMap = new HashMap();
					zyMap.put(gclb, "\\'" + sgdw + "\\':" + sghtje);
					dqMap.put(ssdq, zyMap);
				} else {
					Map zyMap = (HashMap) dqMap.get(ssdq);
					if (!zyMap.containsKey(gclb)) {
						zyMap.put(gclb, "\\'" + sgdw + "\\':" + sghtje);
					} else {
						String t_v = (String) zyMap.get(gclb);
						zyMap.put(gclb, t_v + ",\\'" + sgdw + "\\':" + sghtje);
					}
				}
			}
			String result = "";
			for (Object o : dqMap.keySet()) {
				result += ",1]" + o + ":1]";
				Map zyMap = (HashMap) dqMap.get(o);
				String i_result = "";
				for (Object o2 : zyMap.keySet()) {
					i_result += ",2]" + o2 + ":2]{";
					i_result += zyMap.get(o2);
					i_result += "}";
				}
				i_result = i_result.substring(3, i_result.length());// 去内层第一个逗号
				result += "{" + i_result + "}";
			}
			if (!result.equals("")) {
				result = result.substring(3, result.length());// 去外层第一个逗号
			}
			m_results=result;
			modelMap.put("show_data", result);
		} catch (Exception e) {
			return exceptionService.exceptionControl(this.getClass().getName(),
					"系统出错，请联系管理员", new Exception(e + e.getMessage()));
		}
		if (!m_results.equals("")) {
			return new ModelAndView("/WEB-INF/jsp/search/dqZyFezb.jsp", modelMap);	
		}else {
			if (searchFlag.equals("1")) {
				return new ModelAndView("/search/queryForBoss.do?searchFlag=1", modelMap);	
			}else {
				return new ModelAndView("/search/queryForBoss.do", modelMap);
			}	
		}
	}

	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	@RequestMapping("/search/dqFezbSearch.do")
	public ModelAndView dqFezbSearch(HttpServletRequest request,
			HttpServletResponse response) {
		String view = "/WEB-INF/jsp/search/dqFezbSearch.jsp";
		ModelMap modelMap = new ModelMap();
		List areaList = queryService
				.searchList("select distinct(a.name) from Tc02_area a");
		List gclbList = queryService
				.searchList("select distinct(x.gclb) from Td01_xmxx x where x.gclb is not null");
		modelMap.put("areaList", areaList);
		modelMap.put("gclbList", gclbList);
		return new ModelAndView(view, modelMap);
	}

}
