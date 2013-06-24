package com.netsky.base.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.annotation.OperLogControl;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tz02_oper_log;
import com.netsky.base.dataObjects.interfaces.SlaveObject;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.StringFormatUtil;
import com.netsky.base.utils.convertUtil;

/**
 * 通用表单保存类，处理无附件保存
 * 
 * @author Chiang 2009-12-22
 * 
 * 配置方法，通过隐藏域或xml文件
 * 
 */
@Controller("/save.do")
public class DataSaveController implements org.springframework.web.servlet.mvc.Controller {

	/**
	 * 默认保存配置文件位置
	 */
	private static String configFilePath = "/formConfig/";

	/**
	 * 默认ftp配置文件路径
	 */
	private static String ftpConfigFile = "/ftpConfig/ftpConfig.xml";

	/**
	 * 默认持久化对象包
	 */
	private static String DefaultPackgePath = "com.netsky.dataObject";

	@Autowired
	private SaveService saveService;

	@Autowired
	private QueryService queryService;

	@Autowired
	private ExceptionService exceptionService;

	/**
	 * @return the saveService
	 */
	public SaveService getSaveService() {
		return saveService;
	}

	/**
	 * @param saveService
	 *            the saveService to set
	 */
	public void setSaveService(SaveService saveService) {
		this.saveService = saveService;
	}

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

	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding(arg0.getCharacterEncoding());
		response.setContentType("text/html");
		String fromEncode = arg0.getCharacterEncoding();
		String targetEncode = arg0.getCharacterEncoding();
		String dispath;
		Map<String, String> dispathMap = null;
		HttpServletRequest request;
		boolean slave = false;
		if (arg0.getContentType().toLowerCase().indexOf("multipart/form-data") != -1) {
			request = (MultipartHttpServletRequest) arg0;
			slave = true;
			fromEncode = "UTF-8";
			targetEncode = "UTF-8";
		} else {
			request = arg0;
		}
		String t3 = arg0.getContentType();
		Session session = null;
		Transaction tx = null;
		Ta03_user user = null;
		dispath = StringFormatUtil.format(getDispath(request));
		user = (Ta03_user) request.getSession().getAttribute("user");
		/**
		 * if (user == null) { if (!"".equals(dispath)) return
		 * exceptionService.exceptionControl(this.getClass().getName(), "用户未登录",
		 * new Exception("用户未登录")); else {
		 * response.getWriter().println("用户未登录"); return null; } }
		 */
		try {
			session = saveService.getHiberbateSession();
			tx = session.beginTransaction();
			tx.begin();
		} catch (Exception e) {
			e.printStackTrace();
			if (!"".equals(dispath))
				return exceptionService.exceptionControl(this.getClass().getName(), "获取session失败", e);
			else {
				// response.getWriter().println("获取session失败");
				printJson(request, response, "300", "获取session失败");
				return null;
			}
		}
		/**
		 * 存放主表map
		 */
		Map<String, Object> resultMap = new HashMap<String, Object>();

		/**
		 * 处理保存
		 */
		try {
			List<Map<String, List<Map<String, String>>>> tables = this.getTableList(request);
			/**
			 * 循环处理表格
			 */
			for (int tableIndex = 0; tableIndex < tables.size(); tableIndex++) {
				Map<String, List<Map<String, String>>> tableMap = tables.get(tableIndex);
				List<Map<String, String>> tableList = tableMap.get("tableList");
				List<Map<String, String>> fatherList = tableMap.get("fatherList");
				for (int i = 0; i < tableList.size(); i++) {
					Map<String, String> table = tableList.get(i);
					String tableClass = table.get("name");
					String tableName = null;
					if (tableClass.indexOf(".") == -1) {
						/**
						 * 对象未指定包,使用默认包
						 */
						tableName = tableClass;
						tableClass = DefaultPackgePath + "." + tableClass;
					} else {
						tableName = tableClass.substring(tableClass.lastIndexOf(".") + 1);
					}
					for (int j = 0; request.getParameterValues(tableName + ".ID") != null
							&& j < request.getParameterValues(tableName + ".ID").length; j++) {
						/**
						 * 创建对象
						 */
						Object o = Class.forName(tableClass).newInstance();
						boolean logFlag = false;
						String ObjectComment = "";
						if (OperLogControl.judgType(o) != null) {
							ObjectComment = OperLogControl.judgType(o);
							logFlag = true;
						}
						String beforOper = "";
						String afterOper = "";
						String operFlag = "";
						if (request.getParameterValues(tableName + ".ID")[j] != null
								&& request.getParameterValues(tableName + ".ID")[j].length() > 0) {
							/**
							 * 更新时获取数据库中最新信息
							 */
							o = queryService.searchById(o.getClass(), Long.valueOf(request.getParameterValues(tableName
									+ ".ID")[j]));
							if (logFlag) {
								beforOper = OperLogControl.StructLog(o);
							}
							operFlag = "update";
						} else {
							operFlag = "insert";
						}
						if (PropertyInject.inject(request, o, j, fromEncode, targetEncode)) {
							/**
							 * 更新或插入
							 */
							if (fatherList.size() == 0) {
								/**
								 * 主表
								 */
								session.saveOrUpdate(o);
							} else {
								/**
								 * 从表
								 */
								for (int fatherIndex = 0; fatherIndex < fatherList.size(); fatherIndex++) {
									Map<String, String> fatherMap = fatherList.get(fatherIndex);
									if (fatherMap.get("name") != null && fatherMap.get("name").length() > 0) {
										PropertyInject.injectNexus(resultMap.get(fatherMap.get("name")), fatherMap
												.get("column"), o, fatherMap.get("injectColumn"));
									} else {
										PropertyInject.setProperty(o, fatherMap.get("injectColumn"), fatherMap
												.get("column"));
									}
								}
								session.saveOrUpdate(o);
							}
							resultMap.put(tableName, o);
						} else if (request.getParameterValues(tableName + ".ID")[j] != null
								&& request.getParameterValues(tableName + ".ID")[j].length() > 0) {
							/**
							 * 删除
							 */
							session.delete(o);
							operFlag = "delete";
						}
						if (logFlag) {
							afterOper = OperLogControl.StructLog(o);
							Tz02_oper_log tz02 = new Tz02_oper_log();
							tz02.setOptable(tableName);
							tz02.setOptime(new Date());
							tz02.setOptype(operFlag);
							tz02.setOld_data(beforOper);
							tz02.setNew_data(afterOper);
							if (user != null) {
								tz02.setLogin_id(user.getLogin_id());
								tz02.setOpuser(user.getName());
							}
							tz02.setOpdesc(ObjectComment);
							session.saveOrUpdate(tz02);
						}
					}
				}
			}

			/**
			 * 处理附件
			 */
			if (slave) {
				MultipartHttpServletRequest Mrequest = (MultipartHttpServletRequest) arg0;
				Map<String, String> table = this.getSlaveTable(Mrequest);
				List<Map<String, String>> fatherTable = this.getSlaveFatherTable(Mrequest);
				Map<String, String> ftpConfig = this.getFtpConfig(Mrequest);
				String tableClass = table.get("name");
				String tableName = null;
				if (tableClass.indexOf(".") == -1) {
					/**
					 * 对象未指定包,使用默认包
					 */
					tableName = tableClass;
					tableClass = DefaultPackgePath + "." + tableClass;
				} else {
					tableName = tableClass.substring(tableClass.lastIndexOf(".") + 1);
				}

				Object o[] = new Object[Mrequest.getParameterValues(tableName + ".ID").length];
				for (int i = 0; i < o.length; i++) {
					o[i] = Class.forName(tableClass).newInstance();
					if (Mrequest.getParameterValues(tableName + ".ID")[i] != null
							&& !"".equals(Mrequest.getParameterValues(tableName + ".ID")[i])) {
						o[i] = queryService.searchById(o[i].getClass(), new Long(Mrequest.getParameterValues(tableName
								+ ".ID")[i]));
					}
					PropertyInject.inject(request, o[i], i, fromEncode, targetEncode);
					/**
					 * 处理主从表信息
					 */
					for (int j = 0; j < fatherTable.size(); j++) {
						Map<String, String> fatherMap = fatherTable.get(j);
						if (fatherMap.get("name") != null && fatherMap.get("name").length() > 0) {
							PropertyInject.injectNexus(resultMap.get(fatherMap.get("name")), fatherMap.get("column"),
									o[i], fatherMap.get("injectColumn"));
						} else {
							PropertyInject.setProperty(o[i], fatherMap.get("injectColumn"), fatherMap.get("column"));
						}
					}
				}
				if ("db".equals(table.get("type"))) {
					/**
					 * 保存到数据库
					 */
					saveService.saveBlobs((com.netsky.base.dataObjects.interfaces.BlobObject[]) o, Mrequest);
				} else if ("disk".equals(table.get("type"))) {
					/**
					 * 保存到磁盘
					 */
				} else if ("ftp".equals(table.get("type"))) {
					Map<String, String> ftpFolder = this.getFtpFolder(Mrequest);
					Iterator<?> it = Mrequest.getFileNames();
					int i = 0;
					while (it.hasNext() && i < o.length) {
						String fileDispath = (String) it.next();
						MultipartFile file = Mrequest.getFile(fileDispath);
						if (file.getName() != null && !file.getName().equals("")
								&& file.getInputStream().available() > 0) {
							session.saveOrUpdate(o[i]);
							SlaveObject so = (SlaveObject) o[i];
							String fileName = so.getId() + so.getSlaveIdentifier();
							String extends_name = "";
							String folder;
							if (ftpFolder.get(so.getType()) != null) {
								folder = ftpFolder.get(so.getType());
							} else {
								folder = ftpConfig.get("folder");
							}
							if (file.getOriginalFilename().indexOf(".") != -1) {
								extends_name = file.getOriginalFilename().substring(
										file.getOriginalFilename().lastIndexOf("."));
								if (extends_name.length() == extends_name.getBytes().length)
									fileName += extends_name;
							}
							FTPClient ftp = new FTPClient();
							ftp.connect(ftpConfig.get("address"));
							ftp.login(ftpConfig.get("username"), ftpConfig.get("password"));
							ftp.enterLocalPassiveMode();
							ftp.setFileType(FTP.BINARY_FILE_TYPE);
							// ftp.setFileTransferMode(FTP.BLOCK_TRANSFER_MODE);
							folder += "/" + new SimpleDateFormat("yyyyMM").format(new Date());
							String folders[] = folder.split("/");
							for (int j = 0; j < folders.length; j++) {
								if (!ftp.changeWorkingDirectory(folders[j])) {
									if (!ftp.makeDirectory(folders[j])) {
										throw new Exception("创建目录失败");
									}
									ftp.changeWorkingDirectory(folders[j]);
								}
							}
							ftp.storeFile(fileName, file.getInputStream());
							file.getInputStream().close();

							ftp.disconnect();
							so.setFileName(new String(file.getOriginalFilename().getBytes(fromEncode), targetEncode));
							so.setExt_name(extends_name);
							so.setFilePatch(folder + "/" + fileName);
							session.saveOrUpdate(so);
						}
						i++;
					}
				}
			}

			session.flush();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			if (!"".equals(dispath))
				return exceptionService.exceptionControl(this.getClass().getName(), "保存", e);
			else {
				// response.getWriter().println("保存出错！");
				printJson(request, response, "300", new HashMap<String, String>());
				throw e;
				// return null;
			}
		} finally {
			session.close();
		}
		try {
			/**
			 * 处理保存后调用
			 */
			List<Map<String, Object>> beanList = this.getBeans(request);
			if (beanList.size() > 0) {
				for (int beanIndex = 0; beanIndex < beanList.size(); beanIndex++) {
					Map<String, Object> beanMap = beanList.get(beanIndex);
					Object bean = null;
					Method method = null;
					/**
					 * 获取bean
					 */
					if ("service".equals((String) beanMap.get("type"))) {
						/**
						 * 调用service
						 */
						ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request
								.getSession().getServletContext());
						bean = ctx.getBean((String) beanMap.get("beanName"));

					} else if ("bean".equals((String) beanMap.get("type"))) {
						/**
						 * 调用bean
						 */
						bean = Class.forName((String) beanMap.get("beanName")).newInstance();
					}
					if (bean == null) {
						throw new Exception("未找到定义bean,name:" + beanMap.get("beanName"));
					}
					/**
					 * 获取method
					 */
					Method methods[] = bean.getClass().getDeclaredMethods();
					for (int methodIndex = 0; methodIndex < methods.length; methodIndex++) {
						if (methods[methodIndex].getName().equalsIgnoreCase((String) beanMap.get("method"))) {
							method = methods[methodIndex];
						}
					}
					if (method == null) {
						throw new Exception("未找到定义的method,name:" + beanMap.get("method"));
					}
					/**
					 * 处理bean参数
					 */
					List<Map<String, String>> propertyList = (List<Map<String, String>>) beanMap.get("propertyList");

					String beanProperty[] = new String[propertyList.size()];
					for (int propertyIndex = 0; propertyIndex < propertyList.size(); propertyIndex++) {
						Map<String, String> propertyMap = (Map<String, String>) propertyList.get(propertyIndex);
						if ("table".equals(propertyMap.get("type"))) {
							Object o = PropertyInject.getProperty(resultMap.get(propertyMap.get("tableName")),
									propertyMap.get("column"));
							beanProperty[propertyIndex] = (o != null ? o.toString() : null);
						} else {
							beanProperty[propertyIndex] = request.getParameter(propertyMap.get("column"));
						}
					}
					/**
					 * 调用bean方法
					 */
					PropertyInject.invoke(bean, method, beanProperty, null, null);
				}
			}
			/**
			 * 处理返回路径
			 */
			List<Map<String, String>> dispathProperty = this.getDispathProperty(request);
			dispathMap = new HashMap<String, String>();
			for (int propertyIndex = 0; propertyIndex < dispathProperty.size(); propertyIndex++) {
				Map<String, String> map = dispathProperty.get(propertyIndex);
				if ("table".equals(map.get("type"))) {
					/**
					 * 主表中字段
					 */
					if (map.get("tableName") != null) {
						Object o = PropertyInject.getProperty(resultMap.get(map.get("tableName")), map.get("column"));
						if (o != null)
							dispathMap.put(map.get("name"), o.toString());
					}
				} else {
					/**
					 * request中信息
					 */
					dispathMap.put(map.get("name"), request.getParameter(map.get("column")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (!"".equals(dispath))
				return exceptionService.exceptionControl(this.getClass().getName(), "保存", e);
			else {
				// response.getWriter().println("保存后处理！");
				printJson(request, response, "300", dispathMap);
				return null;
			}
		}
		if (!"".equals(dispath)) {
			if (!dispathMap.containsKey("save")) {
				dispathMap.put("save", "yes");
			}
			return new ModelAndView("redirect:" + dispath, dispathMap);
		} else if (slave) {
			this.printFrameJson(request, response, "200");
			return null;
		} else {
			// response.getWriter().println("ok");
			printJson(request, response, "200", dispathMap);
			return null;
		}
	}

	@SuppressWarnings("unused")
	private void printFrameJson(HttpServletRequest request, HttpServletResponse response, String statusCode)
			throws IOException {
		String navTabId = convertUtil.toString(request.getParameter("_navTabId"));
		String forwardUrl = convertUtil.toString(request.getParameter("_forwardUrl"));
		String callbackType = convertUtil.toString(request.getParameter("_callbackType"));
		response.setCharacterEncoding("GBK");

		response.getWriter().print(
				"{\"statusCode\":\"" + statusCode + "\", \"message\":\"操作成功\", \"navTabId\":\"" + navTabId
						+ "\", \"forwardUrl\":\"" + forwardUrl + "\", \"callbackType\":\"" + callbackType + "\"}");
	}

	private void printJson(HttpServletRequest request, HttpServletResponse response, String statusCode,
			Map<String, String> dispathMap) throws IOException {

		String message = convertUtil.toString(request.getParameter("_message"));
		if ("200".equals(statusCode)) {
			message = message + "成功";
		} else if ("301".equals(statusCode)) {
			message = message + "失败";
		} else {
			message = message + "失败";
		}
		String navTabId = convertUtil.toString(request.getParameter("_navTabId"));
		String forwardUrl = convertUtil.toString(request.getParameter("_forwardUrl"));
		String callbackType = convertUtil.toString(request.getParameter("_callbackType"));
		String backParam = "";
		if (dispathMap != null) {
			Iterator<String> it = dispathMap.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				String param = dispathMap.get(key);
				backParam += ",\"" + key + "\":\"" + param + "\"";
				if (forwardUrl.indexOf("?") != -1) {
					forwardUrl += "&" + key + "=" + param;
				} else {
					forwardUrl += "?" + key + "=" + param;
				}
			}
		}
		response.getWriter().print(
				"{\"statusCode\":\"" + statusCode + "\", \"message\":\"" + message + "\", \"navTabId\":\"" + navTabId
						+ "\", \"forwardUrl\":\"" + forwardUrl + "\", \"callbackType\":\"" + callbackType + "\""
						+ backParam + "}");
	}

	/**
	 * 打印 （dwz json数据结构）
	 * 
	 * @param request
	 * @param response
	 * @param statusCode
	 * @param _message
	 * @throws IOException
	 *             void
	 */
	@SuppressWarnings("unused")
	private void printJson(HttpServletRequest request, HttpServletResponse response, String statusCode, String _message)
			throws IOException {
		String message = convertUtil.toString(request.getParameter("_message"));

		if (!"".equals(convertUtil.toString(_message))) {
			message = _message;
		} else {
			if ("200".equals(statusCode)) {
				message = message + "成功";
			} else if ("301".equals(statusCode)) {
				message = message + "失败";
			} else {
				message = message + "失败";
			}
		}
		String navTabId = convertUtil.toString(request.getParameter("_navTabId"));
		String forwardUrl = convertUtil.toString(request.getParameter("_forwardUrl"));
		String callbackType = convertUtil.toString(request.getParameter("_callbackType"));
		response.getWriter().print(
				"{\"statusCode\":\"" + statusCode + "\", \"message\":\"" + message + "\", \"navTabId\":\"" + navTabId
						+ "\", \"forwardUrl\":\"" + forwardUrl + "\", \"callbackType\":\"" + callbackType + "\"}");
	}

	/**
	 * 返回保存表格
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, List<Map<String, String>>>> getTableList(HttpServletRequest request) throws Exception {
		List<Map<String, List<Map<String, String>>>> tables = new ArrayList<Map<String, List<Map<String, String>>>>();

		if (request.getParameter("configType") == null || request.getParameter("configType").equals("hidden")) {
			/**
			 * 通过表单内隐藏域获取信息
			 */
			if (request.getParameterValues("tableInfomation") == null
					|| request.getParameterValues("tableInfomation").length == 0) {
				return tables;
			}
			String tableInformations[] = request.getParameterValues("tableInfomation");
			for (int i = 0; i < tableInformations.length; i++) {
				Map<String, List<Map<String, String>>> tableMap = new HashMap<String, List<Map<String, String>>>();
				/**
				 * 父表信息及本表信息数组
				 */
				String tableInfo[] = tableInformations[i].split(":");
				/**
				 * 处理父表
				 */
				List<Map<String, String>> fatherList = new ArrayList<Map<String, String>>();
				if (!tableInfo[0].equals("noFatherTable")) {
					String fatherTables[] = tableInfo[0].split("/");
					for (int k = 0; k < fatherTables.length; k++) {
						Map<String, String> fatherMap = new HashMap<String, String>();
						String fatherInfo[] = fatherTables[k].split(",");
						fatherMap.put("name", fatherInfo[0]);
						fatherMap.put("column", fatherInfo[1]);
						fatherMap.put("injectColumn", fatherInfo[2]);
						fatherList.add(fatherMap);
					}
				}
				/**
				 * 处理表格信息
				 */
				List<Map<String, String>> tableList = new ArrayList<Map<String, String>>();
				Map<String, String> table = new HashMap<String, String>();
				table.put("name", tableInfo[tableInfo.length - 1]);
				tableList.add(table);
				/**
				 * 将处理结果放到resultMap中
				 */
				tableMap.put("fatherList", fatherList);
				tableMap.put("tableList", tableList);
				tables.add(tableMap);
			}
		} else {
			/**
			 * 通过xml文件获取配置信息
			 */
			String filePath = request.getSession().getServletContext().getRealPath("WEB-INF") + configFilePath
					+ request.getParameter("profile");
			File file = new File(filePath);
			if (!file.exists()) {
				throw new Exception("配置文件不存在，路径：" + filePath);
			}
			try {
				SAXReader reader = new SAXReader();
				Document doc = reader.read(file);
				Element root = doc.getRootElement();
				Element foo;
				Iterator<?> i;
				Iterator<?> j;

				for (i = root.elementIterator("tables"); i.hasNext();) {
					Map<String, List<Map<String, String>>> tableMap = new HashMap<String, List<Map<String, String>>>();
					foo = (Element) i.next();
					/**
					 * 处理父表
					 */
					List<Map<String, String>> fatherList = new ArrayList<Map<String, String>>();
					for (j = foo.element("fatherTable").elementIterator("table"); j != null && j.hasNext();) {
						Element element = (Element) j.next();
						Map<String, String> fatherMap = new HashMap<String, String>();
						fatherMap.put("name", element.element("name").getText());
						fatherMap.put("column", element.element("column").getText());
						fatherMap.put("injectColumn", element.element("injectColumn").getText());
						fatherList.add(fatherMap);
					}

					/**
					 * 处理表格信息
					 */
					List<Map<String, String>> tableList = new ArrayList<Map<String, String>>();
					for (j = foo.element("table").elementIterator("name"); j != null && j.hasNext();) {
						Element element = (Element) j.next();
						Map<String, String> table = new HashMap<String, String>();
						table.put("name", element.getText());
						tableList.add(table);
					}

					/**
					 * 将处理结果放到resultMap中
					 */
					tableMap.put("fatherList", fatherList);
					tableMap.put("tableList", tableList);
					tables.add(tableMap);
				}
			} catch (DocumentException ex) {
				throw new Exception("错误的xml格式，路径：" + filePath + "错误:" + ex);
			}
		}
		return tables;
	}

	/**
	 * 获取附件表
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private Map<String, String> getSlaveTable(HttpServletRequest request) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		if (request.getParameter("configType") == null || request.getParameter("configType").equals("hidden")) {
			/**
			 * 通过表单内隐藏域获取信息
			 */
			result.put("name", request.getParameter("slaveTable"));
			result.put("type", request.getParameter("slaveType"));
		} else {
			/**
			 * 通过xml文件获取配置信息
			 */
			String filePath = request.getSession().getServletContext().getRealPath("WEB-INF") + configFilePath
					+ request.getParameter("profile");
			File file = new File(filePath);
			if (!file.exists()) {
				throw new Exception("配置文件不存在，路径：" + filePath);
			}
			try {
				SAXReader reader = new SAXReader();
				Document doc = reader.read(file);
				Element root = doc.getRootElement();
				result.put("name", root.element("slaveTable").element("name").getText());
				result.put("type", root.element("slaveTable").element("type").getText());
			} catch (DocumentException ex) {
				throw new Exception("错误的xml格式，路径：" + filePath + "错误:" + ex);
			}
		}

		return result;
	}

	private List<Map<String, String>> getSlaveFatherTable(HttpServletRequest request) throws Exception {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		if (request.getParameter("configType") == null || request.getParameter("configType").equals("hidden")) {
			/**
			 * 处理主从表信息
			 */
			if (request.getParameter("slaveFatherTables") != null
					&& request.getParameter("slaveFatherTables").length() > 0) {
				String slaveFatherTables[] = request.getParameter("slaveFatherTables").split("/");
				for (int k = 0; k < slaveFatherTables.length; k++) {
					String fatherInfo[] = slaveFatherTables[k].split(",");
					Map<String, String> fatherMap = new HashMap<String, String>();
					fatherMap.put("name", fatherInfo[0]);
					fatherMap.put("column", fatherInfo[1]);
					fatherMap.put("injectColumn", fatherInfo[2]);
					result.add(fatherMap);
				}
			}
		} else {
			/**
			 * 通过xml文件获取配置信息
			 */
			String filePath = request.getSession().getServletContext().getRealPath("WEB-INF") + configFilePath
					+ request.getParameter("profile");
			File file = new File(filePath);
			if (!file.exists()) {
				throw new Exception("配置文件不存在，路径：" + filePath);
			}
			try {
				SAXReader reader = new SAXReader();
				Document doc = reader.read(file);
				Element root = doc.getRootElement();
				Element foo;
				Iterator<?> i;
				for (i = root.element("slaveTable").element("fatherTable").elementIterator("table"); i.hasNext();) {
					foo = (Element) i.next();
					Map<String, String> fatherMap = new HashMap<String, String>();
					fatherMap.put("name", foo.element("name").getText());
					fatherMap.put("column", foo.element("column").getText());
					fatherMap.put("injectColumn", foo.element("injectColumn").getText());
					result.add(fatherMap);
				}

			} catch (DocumentException ex) {
				throw new Exception("错误的xml格式，路径：" + filePath + "错误:" + ex);
			}
		}
		return result;
	}

	/**
	 * 获取返回路径
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private String getDispath(HttpServletRequest request) throws Exception {
		String dispath = null;
		if (request.getParameter("configType") == null || request.getParameter("configType").equals("hidden")) {
			/**
			 * 通过表单内隐藏域获取信息
			 */
			dispath = request.getParameter("dispatchStr");
		} else {
			/**
			 * 通过xml文件获取配置信息
			 */
			String filePath = request.getSession().getServletContext().getRealPath("WEB-INF") + configFilePath
					+ request.getParameter("profile");
			File file = new File(filePath);
			if (!file.exists()) {
				throw new Exception("配置文件不存在，路径：" + filePath);
			}
			try {
				SAXReader reader = new SAXReader();
				Document doc = reader.read(file);
				Element root = doc.getRootElement();
				dispath = root.element("dispath").element("path").getText();
			} catch (DocumentException ex) {
				throw new Exception("错误的xml格式，路径：" + filePath + "错误:" + ex);
			}
		}
		return dispath;
	}

	/**
	 * 获取返回参数
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, String>> getDispathProperty(HttpServletRequest request) throws Exception {
		List<Map<String, String>> properties = new ArrayList<Map<String, String>>();
		if (request.getParameter("configType") == null || request.getParameter("configType").equals("hidden")) {
			/**
			 * 通过表单内隐藏域获取信息
			 */
			if (request.getParameter("perproty") != null && request.getParameter("perproty").length() > 0) {
				String perprotys[] = request.getParameter("perproty").split("/");
				for (int i = 0; i < perprotys.length; i++) {
					if (perprotys[i].split(",").length > 1) {
						Map<String, String> propertyMap = new HashMap<String, String>();
						propertyMap.put("type", "table");
						propertyMap.put("tableName", perprotys[i].split(",")[1]);
						propertyMap.put("column", perprotys[i].split(",")[2]);
						propertyMap.put("name", perprotys[i].split(",")[0]);
						properties.add(propertyMap);
					} else {
						Map<String, String> propertyMap = new HashMap<String, String>();
						propertyMap.put("type", "request");
						propertyMap.put("tableName", "");
						propertyMap.put("column", perprotys[i]);
						propertyMap.put("name", perprotys[i]);
						properties.add(propertyMap);
					}
				}
			}
		} else {
			/**
			 * 通过xml文件获取配置信息
			 */
			String filePath = request.getSession().getServletContext().getRealPath("WEB-INF") + configFilePath
					+ request.getParameter("profile");
			File file = new File(filePath);
			if (!file.exists()) {
				throw new Exception("配置文件不存在，路径：" + filePath);
			}
			try {
				SAXReader reader = new SAXReader();
				Document doc = reader.read(file);
				Element root = doc.getRootElement();
				Iterator<?> j;
				for (j = root.element("dispath").element("propertise").elementIterator("property"); j != null
						&& j.hasNext();) {
					Element element = (Element) j.next();
					Map<String, String> propertyMap = new HashMap<String, String>();
					propertyMap.put("type", element.element("type").getText());
					propertyMap.put("tableName", element.element("tableName").getText());
					propertyMap.put("column", element.element("column").getText());
					propertyMap.put("name", element.element("name").getText());
					properties.add(propertyMap);
				}
			} catch (DocumentException ex) {
				throw new Exception("错误的xml格式，路径：" + filePath + "错误:" + ex);
			}
		}
		return properties;
	}

	/**
	 * 返回保存后调用bean
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, Object>> getBeans(HttpServletRequest request) throws Exception {
		List<Map<String, Object>> beanList = new ArrayList<Map<String, Object>>();
		if (request.getParameter("configType") == null || request.getParameter("configType").equals("hidden")) {
			/**
			 * 通过表单内隐藏域获取信息
			 */
			if (request.getParameter("ServiceName") != null && request.getParameter("ServiceName").length() > 0) {
				Map<String, Object> beanMap = new HashMap<String, Object>();
				beanMap.put("type", "service");
				beanMap.put("beanName", request.getParameter("ServiceName"));
				beanMap.put("method", request.getParameter("ServiceFunction"));
				List<Map<String, String>> propertyList = new ArrayList<Map<String, String>>();
				if (request.getParameter("ServicePerproty") != null
						&& request.getParameter("ServicePerproty").length() > 0) {
					String perprotys[] = request.getParameter("ServicePerproty").split("/");
					for (int j = 0; j < perprotys.length; j++) {
						if (perprotys[j].split(",").length > 1) {
							Map<String, String> propertyMap = new HashMap<String, String>();
							propertyMap.put("type", "table");
							propertyMap.put("tableName", perprotys[j].split(",")[0]);
							propertyMap.put("column", perprotys[j].split(",")[1]);
							propertyList.add(propertyMap);
						} else {
							Map<String, String> propertyMap = new HashMap<String, String>();
							propertyMap.put("type", "request");
							propertyMap.put("tableName", "");
							propertyMap.put("column", perprotys[j]);
							propertyList.add(propertyMap);
						}
					}
				}
				beanMap.put("propertyList", propertyList);
				beanList.add(beanMap);
			}
		} else {
			/**
			 * 通过xml文件获取配置信息
			 */
			String filePath = request.getSession().getServletContext().getRealPath("WEB-INF") + configFilePath
					+ request.getParameter("profile");
			File file = new File(filePath);
			if (!file.exists()) {
				throw new Exception("配置文件不存在，路径：" + filePath);
			}
			try {
				SAXReader reader = new SAXReader();
				Document doc = reader.read(file);
				Element root = doc.getRootElement();
				Iterator<?> j;
				for (j = root.element("beans").elementIterator("bean"); j != null && j.hasNext();) {
					Element element = (Element) j.next();
					Map<String, Object> beanMap = new HashMap<String, Object>();
					beanMap.put("type", element.element("type").getText());
					beanMap.put("beanName", element.element("beanName").getText());
					beanMap.put("method", element.element("method").getText());
					Iterator<?> k;
					List<Map<String, String>> propertyList = new ArrayList<Map<String, String>>();
					for (k = element.element("propertise").elementIterator("property"); k.hasNext();) {
						Map<String, String> propertyMap = new HashMap<String, String>();
						Element property = (Element) k.next();
						propertyMap.put("type", property.element("type").getText());
						propertyMap.put("tableName", property.element("tableName").getText());
						propertyMap.put("column", property.element("column").getText());
						propertyList.add(propertyMap);
					}
					beanMap.put("propertyList", propertyList);
					beanList.add(beanMap);
				}
			} catch (DocumentException ex) {
				throw new Exception("错误的xml格式，路径：" + filePath + "错误:" + ex);
			}
		}
		return beanList;
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

	private Map<String, String> getFtpFolder(HttpServletRequest request) throws Exception {
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
			Iterator<?> j;
			for (j = root.elementIterator("typeFolder"); j != null && j.hasNext();) {
				Element element = (Element) j.next();
				result.put(element.elementText("type"), element.elementText("folder"));
			}
		} catch (DocumentException ex) {
			throw new Exception("错误的xml格式，路径：" + filePath + "错误:" + ex);
		}
		return result;
	}
}
