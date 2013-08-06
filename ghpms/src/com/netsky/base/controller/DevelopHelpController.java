package com.netsky.base.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Vector;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseDao.JdbcSupport;
import com.netsky.base.utils.MiniUtils;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.service.impl.CreateDataObjectsServiceImpl;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.utils.RegExp;
import com.netsky.base.dataObjects.Tz07_dataobject_cfg;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Ta07_formfield;
import com.netsky.base.dataObjects.Ta08_reportfield;

@Controller
public class DevelopHelpController {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private CreateDataObjectsServiceImpl createDoService;

	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;

	/**
	 * 更新服务
	 */
	@Autowired
	private SaveService saveService;

	/**
	 * 更新服务
	 */
	@Autowired
	private Dao dao;

	@Autowired
	private JdbcSupport jdbcSupport;

	@SuppressWarnings("deprecation")
	@RequestMapping("/autoCreateJavaXml.do")
	public ModelAndView autoCreateJavaXml(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tz07_id", convertUtil.toLong(request
				.getParameter("tz07_id")));
		paramMap.put("appPath", "D:\\workspace\\ghpms");
		paramMap.put("owner", "PMS_GH");
		createDoService.createJavaAndXml(paramMap);
		return null;
	}

	@RequestMapping("/batchAutoCreateJavaXml.do")
	public void batchAutoCreateJavaXml(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");

		try {
			/*
			 * 获得环境变量eclipse_workspace
			 */
			String eclipse_workspace = new MiniUtils()
					.getEnv("eclipse_workspace");
			if (eclipse_workspace == null || eclipse_workspace.equals("")) {
				eclipse_workspace = new MiniUtils().getEnv("ECLIPSE_WORKSPACE");
				if (eclipse_workspace == null || eclipse_workspace.equals("")) {
					throw new Exception("未配置环境变量eclipse_workspace");
				}
			}

			/*
			 * 获得上下文路径
			 */
			String classpath = this.getClass().getProtectionDomain()
					.getCodeSource().getLocation().getPath();
			String contextName = new RegExp().pickup(
					"/([a-zA-Z_]+[a-zA-Z0-9_]*)/WEB-INF", classpath);

			StringBuffer sql = new StringBuffer();
			String file_separator = System.getProperty("file.separator");
			sql.delete(0, sql.length());
			sql.append("from Tz07_dataobject_cfg tz07 where status = '未处理' ");
			ResultObject ro = queryService.search(sql.toString());
			while (ro.next()) {
				Tz07_dataobject_cfg tz07 = (Tz07_dataobject_cfg) ro.get("tz07");
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("tz07_id", tz07.getId());
				paramMap.put("appPath", eclipse_workspace + file_separator
						+ contextName);
				paramMap.put("owner", "PMS_GH");
				createDoService.createJavaAndXml(paramMap);
			}
			response.getWriter().print(
					"{\"statusCode\":\"200\", \"message\":\"操作成功\"}");
		} catch (Exception e) {
			response.getWriter().print(
					"{\"statusCode\":\"300\", \"message\":\"" + e.getMessage()
							+ "\"}");
		}
	}

	@RequestMapping("/generalFormField.do")
	public void generalFormField(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		StringBuffer logBox = new StringBuffer("");
		StringBuffer msgBox = new StringBuffer("");
		String statusCode = "200";
		try {
			int counter = 0;
			HttpSession session = request.getSession();
			if (session == null) {
				throw new Exception("用户超时，请重新登录");
			}

			StringBuffer sql = new StringBuffer();
			sql.delete(0, sql.length());
			sql.append("from Ta06_module ta06 ");
			sql.append("where extFlag like '%[字段]%' ");
			sql.append("and (extdesc not like '%[字段成功]%' or extdesc is null )");
			ResultObject ro = queryService.search(sql.toString());
			while (ro.next()) {
				Long module_id = null;
				try {
					/*
					 * 获取TA06信息
					 */
					Ta06_module ta06 = (Ta06_module) ro.get("ta06");
					module_id = convertUtil.toLong(ta06.getId());
					String formurl = convertUtil.toString(ta06.getForm_url());
					String project_object = convertUtil.toString(ta06
							.getProject_table());
					String project_table = project_object.substring(
							project_object.lastIndexOf(".") + 1, project_object
									.length());
					String form_object = convertUtil.toString(ta06
							.getForm_table());
					String form_table = form_object.substring(form_object
							.lastIndexOf(".") + 1, form_object.length());
					String detail_objects = convertUtil.toString(ta06
							.getDetail_table());
					String aux_objects = convertUtil.toString(ta06
							.getAux_table());

					/*
					 * 备份TA07信息
					 */
					saveService
							.updateByHSql("update Ta07_formfield set module_id = module_id*(-1) where module_id = "
									+ module_id);

					/*
					 * 判断TA06.formurl中对应文件是否存在
					 */
					String file_separator = System
							.getProperty("file.separator");
					formurl = request.getSession().getServletContext()
							.getRealPath("/")
							+ "WEB-INF"
							+ file_separator
							+ "jsp"
							+ file_separator + formurl;
					File file = new File(formurl);
					if (!file.exists()) {

						throw new Exception("formurl is not correct");
					}

					/*
					 * 读取TA06.formurl中对应文件内容
					 */
					String t_v = null;
					StringBuffer content = new StringBuffer("");
					BufferedReader in = new BufferedReader(new FileReader(file));
					while ((t_v = in.readLine()) != null) {
						content.append(t_v);
					}

					/*
					 * 从内容中提取字段信息【project_table 和 form_table]
					 */
					Vector v = null;
					if (project_table.length() > 0) {
						v = new RegExp().pickupAll("name\\s*=\\s*\""
								+ project_table + "\\.([\\S]+)\"", content
								.toString(), 1);
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("module_id", module_id);
						paramMap.put("objectName", project_object);
						paramMap.put("tableName", project_table);
						setTa07(v, paramMap);
					}
					if (form_table.length() > 0) {
						v = new RegExp().pickupAll("name\\s*=\\s*\""
								+ form_table + "\\.([\\S]+)\"", content
								.toString(), 1);
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("module_id", module_id);
						paramMap.put("objectName", form_object);
						paramMap.put("tableName", form_table);
						setTa07(v, paramMap);
					}

					/*
					 * 明细表
					 */
					if (detail_objects.length() > 0) {
						String[] details = detail_objects.split(",");
						if (details != null && details.length > 0) {
							for (int j = 0; j < details.length; j++) {
								String detail_object = details[j];
								String detail_table = detail_object.substring(
										detail_object.lastIndexOf(".") + 1,
										detail_object.length());
								v = new RegExp().pickupAll("name\\s*=\\s*\""
										+ detail_table + "\\.([\\S]+)\"",
										content.toString(), 1);
								Map<String, Object> paramMap = new HashMap<String, Object>();
								paramMap.put("module_id", module_id);
								paramMap.put("objectName", detail_object);
								paramMap.put("tableName", detail_table);
								paramMap.put("isDetail", new Long(1));
								setTa07(v, paramMap);
							}
						}
					}

					/*
					 * 辅助表
					 */
					if (aux_objects.length() > 0) {
						String[] auxs = aux_objects.split(",");
						if (auxs != null && auxs.length > 0) {
							for (int j = 0; j < auxs.length; j++) {
								String aux_object = auxs[j];
								String aux_table = aux_object.substring(
										aux_object.lastIndexOf(".") + 1,
										aux_object.length());
								v = new RegExp().pickupAll("name\\s*=\\s*\""
										+ aux_table + "\\.([\\S]+)\"", content
										.toString(), 1);
								Map<String, Object> paramMap = new HashMap<String, Object>();
								paramMap.put("module_id", module_id);
								paramMap.put("objectName", aux_object);
								paramMap.put("tableName", aux_table);
								setTa07(v, paramMap);
							}
						}
					}
					counter++;

					/*
					 * 记录一下生成结果
					 */
					ta06.setExtdesc(convertUtil.toString(ta06.getExtdesc())
							+ "[字段成功]");
					saveService.save(ta06);

					/*
					 * 删除备份数据
					 */
					saveService
							.updateByHSql("delete from  Ta07_formfield where module_id = "
									+ module_id * (-1));
				} catch (Exception ee) {
					logBox.append("error [module_id=" + module_id + ":"
							+ ee.getMessage() + "]\n\r");
					/*
					 * 恢复历史数据
					 */
					saveService
							.updateByHSql("update Ta07_formfield set module_id = module_id*(-1) where module_id = "
									+ module_id * (-1));

				}
			}
			msgBox.append("成功处理" + counter + "个表的字段");
		} catch (Exception e) {
			logBox.append(" lastError=" + e.getMessage() + "\n\r");
			msgBox.append("操作失败：" + e.getMessage());
			statusCode = "300";
		} finally {
			if (logBox.length() > 0 && logBox.indexOf("error") != -1) {
				log.error(logBox.toString());
			}
			response.getWriter().print(
					"{\"statusCode\":\"" + statusCode + "\", \"message\":\""
							+ msgBox.toString() + "\"}");
		}
	}

	private void setTa07(Vector v, Map paramMap) throws Exception {

		StringBuffer sql = new StringBuffer("");
		String tableName = convertUtil.toString(paramMap.get("tableName"));
		String objectName = convertUtil.toString(paramMap.get("objectName"));
		Long module_id = convertUtil.toLong(paramMap.get("module_id"));
		Long isDetail = (Long) paramMap.get("isDetail");

		JdbcTemplate jdbcTemplate = jdbcSupport.getJdbcTemplate();
		for (int i = 0; i < v.size(); i++) {

			Ta07_formfield ta07 = null;
			QueryBuilder queryBuilder = new HibernateQueryBuilder(
					Ta07_formfield.class);
			queryBuilder.eq("module_id", module_id);
			queryBuilder.eq("object_name", objectName);
			queryBuilder.eq("name", ((String) v.get(i)).toLowerCase());
			ResultObject ro = queryService.search(queryBuilder);
			if (ro.next()) {
				continue;
			} else {
				/*
				 * 根据TA07获得字段名
				 */
				sql.delete(0, sql.length());
				sql
						.append("select col.column_name column_name,col.data_type data_type,com.comments comments, nullable,");
				sql
						.append("case when data_precision is null then data_length else data_precision+data_scale*0.1 end data_length ");
				sql.append("from user_tab_cols col,user_col_comments com ");
				sql.append("where col.table_name = com.table_name ");
				sql.append("and col.column_name = com.column_name ");
				sql.append("and col.column_name = '" + v.get(i) + "' ");
				sql.append("and col.table_name = '" + tableName.toUpperCase()
						+ "' ");
				List list = jdbcTemplate.queryForList(sql.toString());
				if (list != null && list.size() > 0) {
					Map<String, Object> map = (Map<String, Object>) list.get(0);
					String t_name = convertUtil
							.toString(map.get("column_name")).toLowerCase();
					String t_comment = convertUtil.toString(
							map.get("comments"), "未命名");
					String t_datatype = convertUtil.toString(map
							.get("data_type"));
					Double t_datalength = convertUtil.toDouble(map
							.get("data_length"));
					String t_nullable = convertUtil.toString(map
							.get("nullable"));

					ta07 = new Ta07_formfield();
					ta07.setModule_id(module_id);
					ta07.setName(t_name);
					ta07.setComments(t_comment.replace("[选项]", "").replace(
							"[人员]", ""));
					ta07.setDatatype(t_datatype.toUpperCase());
					ta07.setDatalength(t_datalength);
					ta07.setNullable(t_nullable);
					ta07.setObject_name(objectName);
					if (t_datatype.equals("DATE")) {
						ta07.setAlign("center");
						ta07.setWidth(100L);
					} else if (t_datatype.equals("NUMBER")
							&& t_datalength % 1 == 0) {
						ta07.setAlign("center");
						ta07.setWidth(100L);
					} else if (t_datatype.equals("NUMBER")
							&& t_datalength % 1 > 0) {
						ta07.setAlign("right");
						ta07.setWidth(100L);
					} else if (t_datatype.indexOf("VARCHAR") != -1) {
						ta07.setAlign("left");
						Long t_width = t_datalength.longValue() * 3;
						if (t_width < 100) {
							t_width = 100L;
						} else if (t_width > 1000) {
							t_width = 1000L;
						}
						ta07.setWidth(t_width);
					} else {
						ta07.setAlign("center");
						ta07.setWidth(100L);
					}
					if (t_name.toUpperCase() != "ID"
							&& t_name.toUpperCase().lastIndexOf("_ID") != t_name
									.length() - 3 && t_datalength < 300) {
						ta07.setShow_flag(1L);
					}
					if (t_name.toUpperCase() != "ID"
							&& t_name.toUpperCase().lastIndexOf("_ID") != t_name
									.length() - 3) {
						ta07.setSearch_flag(1L);
					}
					ta07.setOrder_flag(1L);
					if (t_datatype.toUpperCase() != "DATE") {
						ta07.setDate_flag(1L);
					}
					ta07.setIsdetail(isDetail);
					saveService.save(ta07);
					ta07.setOrd(ta07.getId());
					saveService.save(ta07);
				}
			}
		}
	}

	@RequestMapping("/generalReport.do")
	public void generalReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		StringBuffer logBox = new StringBuffer("");
		StringBuffer msgBox = new StringBuffer("");
		String statusCode = "200";
		try {
			int counter = 0;
			HttpSession session = request.getSession();
			if (session == null) {
				throw new Exception("用户超时，请重新登录");
			}

			StringBuffer sql = new StringBuffer();
			sql.delete(0, sql.length());
			sql.append("from Ta06_module ta06 ");
			sql.append("where extFlag like '%[统计]%' ");
			sql.append("and (extdesc not like '%[统计成功]%' or extdesc is null )");
			ResultObject ro = queryService.search(sql.toString());
			while (ro.next()) {
				Long module_id = null;
				try {
					/*
					 * 获取TA06信息
					 */
					Ta06_module ta06 = (Ta06_module) ro.get("ta06");
					module_id = convertUtil.toLong(ta06.getId());
					String project_object = convertUtil.toString(ta06
							.getProject_table());
					String project_table = project_object.substring(
							project_object.lastIndexOf(".") + 1, project_object
									.length());

					/*
					 * 备份TA08信息
					 */
					saveService
							.updateByHSql("update Ta08_reportfield set module_id = module_id*(-1) where module_id = "
									+ module_id);

					/*
					 * 从内容中提取字段信息【project_table 和 form_table]
					 */
					if (project_table.length() > 0) {
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("module_id", module_id);
						paramMap.put("objectName", project_object);
						paramMap.put("tableName", project_table);
						setTa08(paramMap);
					}

					counter++;

					/*
					 * 记录一下生成结果
					 */
					ta06.setExtdesc(convertUtil.toString(ta06.getExtdesc())
							+ "[统计成功]");
					saveService.save(ta06);

					/*
					 * 删除备份数据
					 */
					if (module_id != 0) {
						saveService
								.updateByHSql("delete from  Ta08_reportfield where module_id = "
										+ module_id * (-1));
					}
				} catch (Exception ee) {
					logBox.append(" error [module_id=" + module_id + ":"
							+ ee.getMessage() + "]\n\r");

					/*
					 * 恢复历史数据
					 */
					saveService
							.updateByHSql("update Ta08_reportfield set module_id = module_id*(-1) where module_id = "
									+ module_id * (-1));
				}
			}
			msgBox.append("成功处理" + counter + "个表的字段");
		} catch (Exception e) {
			logBox.append(" lastError=" + e.getMessage() + "\n\r");
			msgBox.append("操作失败：" + e.getMessage());
			statusCode = "300";
		} finally {
			if (logBox.length() > 0 && logBox.indexOf("error") != -1) {
				log.error(logBox.toString());
			}
			response.getWriter().print(
					"{\"statusCode\":\"" + statusCode + "\", \"message\":\""
							+ msgBox.toString() + "\"}");
		}
	}

	private void setTa08(Map paramMap) throws Exception {

		StringBuffer sql = new StringBuffer("");
		String tableName = convertUtil.toString(paramMap.get("tableName"));
		String objectName = convertUtil.toString(paramMap.get("objectName"));
		Long module_id = convertUtil.toLong(paramMap.get("module_id"));

		JdbcTemplate jdbcTemplate = jdbcSupport.getJdbcTemplate();

		/*
		 * 获得字段名
		 */
		sql.delete(0, sql.length());
		sql
				.append("select col.column_name column_name,col.data_type data_type,com.comments comments, nullable,");
		sql
				.append("case when data_precision is null then data_length else data_precision+data_scale*0.1 end data_length ");
		sql.append("from user_tab_cols col,user_col_comments com ");
		sql.append("where col.table_name = com.table_name ");
		sql.append("and col.column_name = com.column_name ");
		sql.append("and col.table_name = '" + tableName.toUpperCase() + "' ");
		List list = jdbcTemplate.queryForList(sql.toString());
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = (Map<String, Object>) list.get(i);
			String t_name = convertUtil.toString(map.get("column_name"))
					.toLowerCase();
			String t_comment = convertUtil.toString(map.get("comments"));
			String t_datatype = convertUtil.toString(map.get("data_type"));
			Double t_datalength = convertUtil.toDouble(map.get("data_length"));

			Ta08_reportfield ta08 = new Ta08_reportfield();
			ta08.setModule_id(module_id);
			ta08.setName(t_name);
			ta08.setComments(t_comment.replace("[选项]", "").replace("[人员]", ""));
			ta08.setDatatype(t_datatype.toUpperCase());
			ta08.setObject_name(objectName);

			if (t_datatype.equals("DATE")) {
				ta08.setAlign("center");
				ta08.setWidth(100L);
				ta08.setSearchtype(new Long(5));// 日期
			} else if (t_datatype.equals("NUMBER") && t_datalength % 1 == 0) {
				ta08.setAlign("center");
				ta08.setWidth(100L);
				ta08.setSearchtype(new Long(1));// 一般数值
			} else if (t_datatype.equals("NUMBER") && t_datalength % 1 > 0) {
				ta08.setAlign("right");
				ta08.setWidth(100L);
				ta08.setSearchtype(new Long(3));// 金额类
			} else if (t_datatype.indexOf("VARCHAR") != -1
					&& t_comment.indexOf("[选项]") != -1) {
				ta08.setAlign("left");
				Long t_width = t_datalength.longValue() * 2;
				if (t_width < 100) {
					t_width = 100L;
				} else if (t_width > 1000) {
					t_width = 1000L;
				}
				ta08.setWidth(t_width);
				ta08.setSearchtype(new Long(2));// 选项
			} else if (t_datatype.indexOf("VARCHAR") != -1
					&& (t_name.toUpperCase().lastIndexOf("RY") == t_name
							.length() - 2 || t_comment.indexOf("[人员]") != -1)) {
				ta08.setAlign("center");
				Long t_width = t_datalength.longValue() * 3;
				if (t_width < 100) {
					t_width = 100L;
				} else if (t_width > 1000) {
					t_width = 1000L;
				}
				ta08.setWidth(t_width);
				ta08.setSearchtype(new Long(4));// 人员类
			} else if (t_datatype.indexOf("VARCHAR") != -1) {
				ta08.setAlign("left");
				Long t_width = t_datalength.longValue() * 3;
				if (t_width < 100) {
					t_width = 100L;
				} else if (t_width > 1000) {
					t_width = 1000L;
				}
				ta08.setWidth(t_width);
				ta08.setSearchtype(new Long(1));
			} else {
				ta08.setAlign("center");
				ta08.setWidth(100L);
				ta08.setSearchtype(new Long(1));// 一般类
			}

			if (ta08.getSearchtype() == 2) {
				ta08.setSelecturl("search/keySelect.do?type=xxx");
			}

			/*
			 * 类别为2【选项】或 以名称和编号结尾的
			 */
			if (ta08.getSearchtype() == 2
					|| t_name.toUpperCase().lastIndexOf("MC") == t_name
							.length() - 2
					|| t_name.toUpperCase().lastIndexOf("BH") == t_name
							.length() - 2) {
				ta08.setShowflag(1L);
			}

			/*
			 * 不包含ID 和 _ID的
			 */
			if (t_name.toUpperCase() != "ID"
					&& t_name.toUpperCase().lastIndexOf("_ID") != t_name
							.length() - 3) {
				ta08.setSearchflag(1L);
			}

			/*
			 * 类别为2【选项】或 类别为4【人员】
			 */
			if (ta08.getSearchtype() == 2 || ta08.getSearchtype() == 4) {
				ta08.setStatisticflag(1L);
			}

			/*
			 * 不包含ID 和 _ID的
			 */
			if (t_name.toUpperCase() != "ID"
					&& t_name.toUpperCase().lastIndexOf("_ID") != t_name
							.length() - 3) {
				ta08.setReportflag(1L);
			}
			// 对长度再次做调整,根据comments的长度变化
			Integer t_width2 = (int) (ta08.getComments().length() * 13.125) + 1;
			if (ta08.getWidth() < t_width2) {
				ta08.setWidth(new Long(t_width2));
			}
			if (!t_name.equals("gcmc")&&!t_name.equals("ghbh")&&!t_name.equals("skbh")&&!t_name.equals("xzqy")) {
				ta08.setShowflag(0L);
			}
			ta08.setOrd(new Long(i));
			saveService.save(ta08);
		}
	}

	@RequestMapping("/generalSaveCfg.do")
	public void generalSaveCfg(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		StringBuffer logBox = new StringBuffer("");
		StringBuffer msgBox = new StringBuffer("");
		String statusCode = "200";
		try {
			int counter = 0;
			HttpSession session = request.getSession();
			if (session == null) {
				throw new Exception("用户超时，请重新登录 timeout...");
			}

			/*
			 * 获得环境变量eclipse_workspace
			 */
			String eclipse_workspace = new MiniUtils()
					.getEnv("eclipse_workspace");
			if (eclipse_workspace == null || eclipse_workspace.equals("")) {
				eclipse_workspace = new MiniUtils().getEnv("ECLIPSE_WORKSPACE");
				if (eclipse_workspace == null || eclipse_workspace.equals("")) {
					throw new Exception("未配置环境变量eclipse_workspace");
				}
			}

			/*
			 * 获得持久化对象模板
			 */
			String classpath = this.getClass().getProtectionDomain()
					.getCodeSource().getLocation().getPath();
			String classname = this.getClass().getSimpleName();
			classpath = classpath.replaceAll(classname + ".class",
					"formSaveModel.model");
			String tt_modelContent = createDoService.getModelContent(classpath);
			if (tt_modelContent == null) {
				throw new Exception("not found formSaveModel[" + classpath
						+ "]");
			}

			/*
			 * 获得上下文路径
			 */
			String contextName = new RegExp().pickup(
					"/([a-zA-Z_]+[a-zA-Z0-9_]*)/WEB-INF", classpath);

			StringBuffer sql = new StringBuffer();
			sql.delete(0, sql.length());
			sql.append("from Ta06_module ta06 ");
			sql.append("where extFlag like '%[配置保存]%' ");
			sql
					.append("and (extdesc not like '%[配置保存成功]%' or extdesc is null )");
			ResultObject ro = queryService.search(sql.toString());
			while (ro.next()) {
				Long module_id = null;
				try {
					/*
					 * 获取TA06信息
					 */
					Ta06_module ta06 = (Ta06_module) ro.get("ta06");
					module_id = convertUtil.toLong(ta06.getId());
					String project_object = convertUtil.toString(ta06
							.getProject_table());
					String project_table = project_object.substring(
							project_object.lastIndexOf(".") + 1, project_object
									.length());
					String form_object = convertUtil.toString(ta06
							.getForm_table());
					String form_table = form_object.substring(form_object
							.lastIndexOf(".") + 1, form_object.length());
					String file_name = form_table.substring(form_table
							.lastIndexOf("_") + 1, form_table.length());
					String detail_objects = convertUtil.toString(ta06
							.getDetail_table());

					/*
					 * 替换模板中的内容[$package,$date,$obj_name,$serializable,$sequence,$field_list]
					 */
					String t_modelContent = tt_modelContent;
					t_modelContent = t_modelContent.replaceAll(
							"\\$project_object", project_object);
					t_modelContent = t_modelContent.replaceAll(
							"\\$form_object", form_object);
					t_modelContent = t_modelContent.replaceAll(
							"\\$project_table", project_table);
					t_modelContent = t_modelContent.replaceAll("\\$form_table",
							form_table);

					/*
					 * 生成文件
					 */
					if (t_modelContent != null
							&& !t_modelContent.trim().equals("")) {
						StringBuffer t_container = new StringBuffer("");
						t_container.delete(0, t_container.length());
						t_container.append(eclipse_workspace);
						t_container
								.append(System.getProperty("file.separator"));
						t_container.append(contextName);
						t_container
								.append(System.getProperty("file.separator"));
						t_container.append("WebRoot");
						t_container
								.append(System.getProperty("file.separator"));
						t_container.append("WEB-INF");
						t_container
								.append(System.getProperty("file.separator"));
						t_container.append("formConfig");
						t_container
								.append(System.getProperty("file.separator"));
						t_container.append(file_name + ".xml");
						File file = new File(t_container.toString());
						if (!file.exists()) {
							file.createNewFile();
						}
						FileOutputStream fout = new FileOutputStream(
								t_container.toString());
						Writer out = new OutputStreamWriter(fout, "utf-8");
						out.write(t_modelContent);
						out.close();
					}
					counter++;

					/*
					 * 记录一下生成结果
					 */
					ta06.setExtdesc(convertUtil.toString(ta06.getExtdesc())
							+ "[配置保存成功]");
					saveService.save(ta06);
				} catch (Exception ee) {
					logBox.append(" error[generalSaveCfg] [module_id="
							+ module_id + ":" + ee.getMessage() + "]\n\r");
				}
			}
			msgBox.append("成功配置" + counter + "个保存");
		} catch (Exception e) {
			logBox.append(" lastError[generalSaveCfg]=" + e.getMessage()
					+ "\n\r");
			msgBox.append("操作失败：" + e.getMessage());
			statusCode = "300";
		} finally {
			if (logBox.length() > 0 && logBox.indexOf("error") != -1) {
				log.error(logBox.toString());
			}
			response.getWriter().print(
					"{\"statusCode\":\"" + statusCode + "\", \"message\":\""
							+ msgBox.toString() + "\"}");
		}
	}

	@RequestMapping("/generalListField.do")
	public void generalListField(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		StringBuffer logBox = new StringBuffer("");
		StringBuffer msgBox = new StringBuffer("");
		String statusCode = "200";
		StringBuffer sql = new StringBuffer("");
		final String objectName = "com.netsky.base.flow.vo.ProjectInf";
		try {
			HttpSession session = request.getSession();
			if (session == null) {
				throw new Exception("用户超时，请重新登录");
			}

			JdbcTemplate jdbcTemplate = jdbcSupport.getJdbcTemplate();

			/*
			 * 获得字段名
			 */
			sql.delete(0, sql.length());
			sql.append("select view_name ");
			sql.append("from user_views ");
			sql.append("where view_name = 'PROJECTINF' ");
			List list = jdbcTemplate.queryForList(sql.toString());
			if (list == null || list.size() == 0) {
				throw new Exception("PROJECTINF 视图不存在，请先创建此视图");
			}

			/*
			 * 判断是否有注释
			 */
			sql.delete(0, sql.length());
			sql.append("select column_name ");
			sql.append("from user_col_comments ");
			sql
					.append("where table_name in('PROJECTINF') and comments is null");
			list = jdbcTemplate.queryForList(sql.toString());
			if (list != null && list.size() > 0) {
				Map<String, Object> map = (Map<String, Object>) list.get(0);
				String column_name = convertUtil.toString(
						map.get("column_name")).toLowerCase();
				throw new Exception("PROJECTINF 视图中【" + column_name
						+ "】无注释，请补充");
			}

			dao.update("delete from Ta07_formfield where module_id =1");
			/*
			 * 根据TA07获得字段名
			 */
			sql.delete(0, sql.length());
			sql
					.append("select col.column_name column_name,col.data_type data_type,com.comments comments, nullable,");
			sql
					.append("case when data_precision is null then data_length else data_precision+data_scale*0.1 end data_length ");
			sql.append("from user_tab_cols col,user_col_comments com ");
			sql.append("where col.table_name = com.table_name ");
			sql.append("and col.column_name = com.column_name ");
			sql.append("and col.table_name = 'PROJECTINF' ");
			list = jdbcTemplate.queryForList(sql.toString());
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = (Map<String, Object>) list.get(i);
					String t_name = convertUtil
							.toString(map.get("column_name")).toLowerCase();
					String t_comment = convertUtil.toString(
							map.get("comments"), "未命名");
					String t_datatype = convertUtil.toString(map
							.get("data_type"));
					Double t_datalength = convertUtil.toDouble(map
							.get("data_length"));
					String t_nullable = convertUtil.toString(map
							.get("nullable"));

					Ta07_formfield ta07 = new Ta07_formfield();
					ta07.setModule_id(1L);
					ta07.setName(t_name);
					ta07.setComments(t_comment.replace("[选项]", "").replace(
							"[人员]", ""));
					ta07.setDatatype(t_datatype.toUpperCase());
					ta07.setDatalength(t_datalength);
					ta07.setNullable(t_nullable);
					ta07.setObject_name(objectName);
					if (t_datatype.equals("DATE")) {
						ta07.setAlign("center");
						ta07.setWidth(100L);
					} else if (t_datatype.equals("NUMBER")
							&& t_datalength % 1 == 0) {
						ta07.setAlign("center");
						ta07.setWidth(100L);
					} else if (t_datatype.equals("NUMBER")
							&& t_datalength % 1 > 0) {
						ta07.setAlign("right");
						ta07.setWidth(100L);
					} else if (t_datatype.indexOf("VARCHAR") != -1) {
						ta07.setAlign("left");
						Long t_width = t_datalength.longValue() * 3;
						if (t_width < 100) {
							t_width = 100L;
						} else if (t_width > 1000) {
							t_width = 1000L;
						}
						ta07.setWidth(t_width);
					} else {
						ta07.setAlign("center");
						ta07.setWidth(100L);
					}
					if (t_name.toUpperCase().indexOf("MC") != -1) {
						ta07.setShow_flag(1L);
					}
					if (t_name.toUpperCase() != "ID"
							&& t_name.toUpperCase().lastIndexOf("_ID") != t_name
									.length() - 3) {
						ta07.setSearch_flag(1L);
					}
					ta07.setOrder_flag(1L);
					ta07.setOrd(10L);
					if (t_datatype.toUpperCase() != "DATE") {
						ta07.setDate_flag(1L);
					}
					saveService.save(ta07);
				}
			}

			jdbcTemplate
					.update("insert into ta07_formfield(id,module_id,name,comments,datatype,datalength,nullable,object_name,width,align,show_flag,search_flag,order_flag,ord) values(ta07_num.nextval,1,'near_status','状态','VARCHAR2',40,'Y','com.netsky.base.flow.vo.DocStruct',50,'left',1,1,1,10002)");
			jdbcTemplate
					.update("insert into ta07_formfield(id,module_id,name,comments,datatype,datalength,nullable,object_name,width,align,show_flag,search_flag,order_flag,ord) values(ta07_num.nextval,1,'module_name','表单名称','VARCHAR2',100,'N','com.netsky.base.flow.vo.DocStruct',120,'left',1,1,1,1)");
			jdbcTemplate
					.update("insert into ta07_formfield(id,module_id,name,comments,datatype,datalength,nullable,object_name,width,align,show_flag,search_flag,order_flag,ord) values(ta07_num.nextval,1,'oper_time','处理时间','DATE',14,'Y','com.netsky.base.flow.vo.DocStruct',160,'center',1,1,1,10001)");
			jdbcTemplate
					.update("insert into ta07_formfield(id,module_id,name,comments,datatype,datalength,nullable,object_name,width,align,show_flag,search_flag,order_flag,ord) values(ta07_num.nextval,1,'near_user','节点','VARCHAR2',70,'Y','com.netsky.base.flow.vo.DocStruct',80,'left',1,1,1,10000)");
			msgBox.append("成功生成PROJECTINF[module_id=1]表的字段");
		} catch (Exception e) {
			logBox.append(" [generalListField.do]lastError=" + e.getMessage()
					+ "\n\r");
			msgBox.append("操作失败：" + e.getMessage());
			statusCode = "300";
		} finally {
			if (logBox.length() > 0 && logBox.indexOf("rror") != -1) {
				log.error(logBox.toString());
			}
			response.getWriter().print(
					"{\"statusCode\":\"" + statusCode + "\", \"message\":\""
							+ msgBox.toString() + "\"}");
		}
	}

	@RequestMapping("/generalFormFieldBySys.do")
	public void generalFormFieldBySys(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		response.setCharacterEncoding("UTF-8");
		StringBuffer logBox = new StringBuffer("");
		StringBuffer msgBox = new StringBuffer("");
		String statusCode = "200";
		try {
			int counter = 0;
			HttpSession session = request.getSession();
			if (session == null) {
				throw new Exception("用户超时，请重新登录");
			}

			StringBuffer sql = new StringBuffer();
			sql.delete(0, sql.length());
			sql.append("from Ta06_module ta06 ");
			sql.append("where extFlag like '%[字段]%' ");
			sql.append("and (extdesc not like '%[字段成功]%' or extdesc is null )");
			ResultObject ro = queryService.search(sql.toString());
			while (ro.next()) {
				Long module_id = null;
				try {
					/*
					 * 获取TA06信息
					 */
					Ta06_module ta06 = (Ta06_module) ro.get("ta06");
					module_id = convertUtil.toLong(ta06.getId());
					String form_object = convertUtil.toString(ta06
							.getForm_table());
					String form_table = form_object.substring(form_object
							.lastIndexOf(".") + 1, form_object.length());
					/*
					 * 备份TA07信息
					 */
					saveService
							.updateByHSql("update Ta07_formfield set module_id = module_id*(-1) where module_id = "
									+ module_id);
					/*
					 * 从内容中提取字段信息【project_table 和 form_table]
					 */
					if (form_table.length() > 0) {
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("module_id", module_id);
						paramMap.put("objectName", form_object);
						paramMap.put("tableName", form_table);
						setTa07(paramMap);
					}
					counter++;

					/*
					 * 记录一下生成结果
					 */
					ta06.setExtdesc(convertUtil.toString(ta06.getExtdesc())
							+ "[字段成功]");
					saveService.save(ta06);

					/*
					 * 删除备份数据
					 */
					saveService
							.updateByHSql("delete from  Ta07_formfield where module_id = "
									+ module_id * (-1));
				} catch (Exception ee) {
					logBox.append("error [module_id=" + module_id + ":"
							+ ee.getMessage() + "]\n\r");
					/*
					 * 恢复历史数据
					 */
					saveService
							.updateByHSql("update Ta07_formfield set module_id = module_id*(-1) where module_id = "
									+ module_id * (-1));

				}
			}
			msgBox.append("成功处理" + counter + "个表的字段");
		} catch (Exception e) {
			logBox.append(" lastError=" + e.getMessage() + "\n\r");
			msgBox.append("操作失败：" + e.getMessage());
			statusCode = "300";
		} finally {
			if (logBox.length() > 0 && logBox.indexOf("error") != -1) {
				log.error(logBox.toString());
			}
			response.getWriter().print(
					"{\"statusCode\":\"" + statusCode + "\", \"message\":\""
							+ msgBox.toString() + "\"}");
		}

	}

	public void setTa07(Map paramMap){
		StringBuffer sql = new StringBuffer("");
		String tableName = convertUtil.toString(paramMap.get("tableName"));
		String objectName = convertUtil.toString(paramMap.get("objectName"));
		Long module_id = convertUtil.toLong(paramMap.get("module_id"));
		Long isDetail = (Long)paramMap.get("isDetail");
		
		JdbcTemplate jdbcTemplate = jdbcSupport.getJdbcTemplate();
			
			Ta07_formfield ta07 = null;
				/*
				 * 根据TA07获得字段名
				 */
				sql.delete(0,sql.length());
				sql.append("select col.column_name column_name,col.data_type data_type,com.comments comments, nullable,");
				sql.append("case when data_precision is null then data_length else data_precision+data_scale*0.1 end data_length ");
				sql.append("from user_tab_cols col,user_col_comments com ");
				sql.append("where col.table_name = com.table_name ");
				sql.append("and col.column_name = com.column_name ");
				sql.append("and col.table_name = '"+tableName.toUpperCase()+"' ");
				List list = jdbcTemplate.queryForList(sql.toString());
				int ord=1;
				if(list != null && list.size() > 0){
					for (Object object : list) {
					Map<String,Object> map = (Map<String,Object>)object;
					String t_name = convertUtil.toString(map.get("column_name")).toLowerCase();
					String t_comment = convertUtil.toString(map.get("comments"),"未命名");
					String t_datatype = convertUtil.toString(map.get("data_type"));
					Double t_datalength = convertUtil.toDouble(map.get("data_length"));
					String t_nullable = convertUtil.toString(map.get("nullable"));
					
					ta07 = new Ta07_formfield();
					ta07.setModule_id(module_id);
					ta07.setName(t_name);
					ta07.setComments(t_comment.replace("[选项]", "").replace("[人员]","").replace("[YON]", ""));
					if (t_comment.indexOf("[选项]")>0||t_comment.indexOf("[人员]")>0) {
						ta07.setData_type(new Long(1));
						if (t_comment.indexOf("[YON])")>0) {
							
						}
					} 
					ta07.setDatatype(t_datatype.toUpperCase());
					ta07.setDatalength(t_datalength);
					ta07.setNullable(t_nullable);
					ta07.setObject_name(objectName);
					if(t_datatype.equals("DATE")){
						ta07.setAlign("center");
						ta07.setWidth(100L);
					}
					else if(t_datatype.equals("NUMBER") && t_datalength % 1 == 0 ){
						ta07.setAlign("center");
						ta07.setWidth(100L);
					}
					else if(t_datatype.equals("NUMBER") && t_datalength % 1 > 0 ){
						ta07.setAlign("right");
						ta07.setWidth(100L);
					}
					else if(t_datatype.indexOf("VARCHAR") != -1){
						ta07.setAlign("left");
						Long t_width = t_datalength.longValue()*2;
						if(t_width < 100){
							t_width = 100L;
						}
						else if(t_width > 1000){
							t_width = 1000L;
							ta07.setData_type(new Long(2));
						}
						ta07.setWidth(t_width);
					}
					else{
						ta07.setAlign("center");
						ta07.setWidth(100L);
					}
					if(t_name.toUpperCase() != "ID" && t_name.toUpperCase().lastIndexOf("_ID") != t_name.length() - 3
							&& t_datalength < 300){
						ta07.setShow_flag(1L);
					}
					if(t_name.toUpperCase() != "ID" && t_name.toUpperCase().lastIndexOf("_ID") != t_name.length() - 3){
						ta07.setSearch_flag(1L);
					}
					ta07.setOrder_flag(1L);
					if(t_datatype.toUpperCase() != "DATE"){
						ta07.setDate_flag(1L);
					}
					
					// 对长度再次做调整,根据comments的长度变化
					Integer t_width2=(int) (ta07.getComments().length()*13.125)+1;
					if (ta07.getWidth()<t_width2) {
						ta07.setWidth(new Long(t_width2));
					}
					if (!t_name.equals("gcmc")&&!t_name.equals("ghbh")&&!t_name.equals("skbh")&&!t_name.equals("xzqy")) {
						ta07.setShow_flag(0L);
					}
					
					ta07.setIsdetail(isDetail);
					ta07.setOrd(new Long(ord));
					saveService.save(ta07);
					ord++;
				}	
	}
	
	}
}