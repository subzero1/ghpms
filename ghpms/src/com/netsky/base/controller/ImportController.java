package com.netsky.base.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.CellType;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.utils.DateFormatUtil;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import org.springframework.stereotype.Controller;
import com.netsky.base.flow.utils.convertUtil;

/**
 * @author Chiang 2009-08-20
 * @modified by lee.xiangyu 2010-05-10
 * 
 * EXCEL信息导入 配置方法
 * 
 * <input type="hidden" name="config" value="import.xml中的配置名称">
 * 
 * 其他存在于request中的参数均写在hidden中
 * 
 * 返回路径 <input type="hidden" name="dispatchStr" value="">
 * 
 * 返回参数 <input type="hidden" name="perproty" value="request中参数名称">
 */
@Controller("/import.do")
public class ImportController implements org.springframework.web.servlet.mvc.Controller {
	/**
	 * 默认持久化对象包路径
	 */
	private static String packgePath = "com.ghpms.dataObjects.form";

	private static String configFile = "/importConfig/import.xml";

	private String webInfPatch = "";

	@Autowired
	private SaveService saveService;

	@Autowired
	private QueryService queryService;

	@Autowired
	private ExceptionService exceptionService;

	public ModelAndView handleRequest(HttpServletRequest HttpRequest, HttpServletResponse response) throws Exception {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) HttpRequest;
		String statusCode = "200";
		String message = "";
		/**
		 * 获取配置文件信息
		 */
		String ConfigName = new String(request.getParameter("config").getBytes("iso-8859-1"), "GBK");
		webInfPatch = request.getSession().getServletContext().getRealPath("WEB-INF");
		String t_packgePath = request.getParameter("packgePath");
		if (t_packgePath != null) {
			this.packgePath = t_packgePath;
		}

		if (ConfigName == null || ConfigName.equals("")) {
			throw new Exception("缺少配置信息参数！");
		}

		String ConfigFileName = getConfinFileName(ConfigName);
		if (ConfigFileName == null || ConfigFileName.equals("")) {
			throw new Exception("未找到对应的导入信息" + ConfigName);
		}

		Map configInfo = this.getConfigInfo(ConfigFileName);
		Session session = null;
		Transaction tx = null;
		try {
			session = saveService.getHiberbateSession();
			tx = session.beginTransaction();
			tx.begin();
		} catch (Exception e) {
			throw new Exception("不能获取session" + e);
		}
		try {
			/**
			 * 处理上传文件
			 */
			Iterator it = request.getFileNames();
			while (it.hasNext()) {
				String fileDispath = (String) it.next();
				MultipartFile file = request.getFile(fileDispath);
				Workbook wb = Workbook.getWorkbook(file.getInputStream());
				Iterator tableIt = configInfo.keySet().iterator();
				/**
				 * 循环处理单个表格
				 */
				while (tableIt.hasNext()) {
					Map tableInfo = (Map) configInfo.get((String) tableIt.next());
					String tableName = (String) tableInfo.get("$tableName");
					if (!"".equals(convertUtil.toString(request.getParameter("executebeforeinsert")))) {
						session.createQuery(new String(request.getParameter("executebeforeinsert").getBytes("iso-8859-1"), "GBK"))
								.executeUpdate();
					}
					int startRow = ((Integer) tableInfo.get("$startRow")).intValue();
					int endRow = -1;
					if (tableInfo.get("$endRow") != null) {
						endRow = ((Integer) tableInfo.get("$endRow")).intValue();
					}
					String endFlag = (String) tableInfo.get("$endFlag");
					String columnType = (String) tableInfo.get("$columnType");

					Sheet st = wb.getSheet(((Integer) tableInfo.get("$sheetNum")).intValue());

					/**
					 * 处理字段对应列信息
					 */
					Map columnMap;
					if (columnType.equals("byIndex")) {
						/**
						 * 通过列信息索引，不需要处理
						 */
						columnMap = (Map) tableInfo.get("$columnMap");
					} else {
						/**
						 * 将通过标题行名称索引转换为通过列信息索引
						 */
						columnMap = new HashMap();
						int titleRow = ((Integer) tableInfo.get("$titleRow")).intValue();
						Map column = (Map) tableInfo.get("$columnMap");
						Iterator colName = column.keySet().iterator();
						/**
						 * 获取标题行所有列
						 */
						boolean rightExcel = false;
						Cell cell[] = st.getRow(titleRow);
						while (colName.hasNext()) {
							rightExcel = false;
							String name = (String) colName.next();
							Map col = (Map) column.get(name);
							String title = (String) col.get("$name");
							String required = (String) col.get("$required");
							for (int i = 0; i < cell.length; i++) {
								if (cell[i].getContents() != null && cell[i].getContents().equals(title)) {
									rightExcel = true;
									col.put("$index", new Integer(cell[i].getColumn()));
									columnMap.put(name, col);
								}
							}
							if (!rightExcel && required.equals("true")) {
								message = "Excel文件中缺少列：" + title;
								statusCode = "300";
								throw new Exception("<br><font color='red'>Excel文件中缺少列：" + title + "</font>");
							}
						}
					}
					int totalRows = st.getRows();
					while (startRow < totalRows) {
						if (endRow != -1 && endRow == startRow) {
							break;
						}
						if (st.getCell(0, startRow).getContents() != null
								&& st.getCell(0, startRow).getContents().equals(endFlag)) {
							break;
						}
						Object o = Class.forName(packgePath + "." + tableName).newInstance();
						/**
						 * 注入request中于表格相关内容
						 */
						PropertyInject.inject(request, o, 0, "iso-8859-1", "GBK");
						/**
						 * 从excel注入信息
						 */
						injectFromExcel(o, columnMap, st, startRow, request);
						o = request.getAttribute("obj");

						/**
						 * 处理父表信息
						 */
						Map fatherMap = (Map) tableInfo.get("$fatherMap");
						if (fatherMap != null) {
							Iterator fatherTableIt = fatherMap.keySet().iterator();
							while (fatherTableIt.hasNext()) {
								Map fatherTable = (Map) fatherMap.get((String) fatherTableIt.next());
								if (((String) fatherTable.get("$type")).equals("db")) {
									/**
									 * 从数据库中查询
									 */
									Object fatherObject = Class.forName(
											packgePath + "." + (String) fatherTable.get("$tableName")).newInstance();
									Class clazz = fatherObject.getClass();
									Class fieldClazz = PropertyInject.getPropertyType(clazz, (String) fatherTable
											.get("$searchColumn"));
									Object searchvalue = null;
									if (fieldClazz.getName().equals("java.lang.Integer")) {
										searchvalue = Integer.valueOf(st.getCell(
												Integer.parseInt((String) fatherTable.get("$index")), startRow)
												.getContents());
									} else if (fieldClazz.getName().equals("java.lang.String")) {
										searchvalue = st.getCell(Integer.parseInt((String) fatherTable.get("$index")),
												startRow).getContents();
									} else if (fieldClazz.getName().equals("java.lang.Double")) {
										searchvalue = Double.valueOf(st.getCell(
												Integer.parseInt((String) fatherTable.get("$index")), startRow)
												.getContents());
									} else if (fieldClazz.getName().equals("java.util.Date")) {
										searchvalue = DateFormatUtil.FormatDateString(st.getCell(
												Integer.parseInt((String) fatherTable.get("$index")), startRow)
												.getContents());
									}
									if (searchvalue != null && !searchvalue.equals("")) {
										QueryBuilder queryBuilder = new HibernateQueryBuilder(clazz);
										queryBuilder.eq((String) fatherTable.get("$searchColumn"), searchvalue);
										ResultObject ro = queryService.search(queryBuilder);
										if (ro.next()) {
											fatherObject = ro.get(clazz.getName());
											PropertyInject.injectNexus(fatherObject, ((String) fatherTable
													.get("$columnForSet")).toUpperCase(), o, ((String) fatherTable
													.get("$columnToSet")).toUpperCase());
										}
									}

								} else {
									/**
									 * 目前无此类型
									 */
								}
							}
						}
						session.saveOrUpdate(o);
						startRow++;
					}
				}
			}
			session.flush();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			statusCode = "300";
			message = e.getMessage();
			if (message != null && message.indexOf("recognize OLE stream") != -1) {
				message = "Excel格式非法，请将Excel另存为<font color=red>2003版</font>的<font color=red>标准</font>的Excel后再导入";
			} else {
				message = "Excel格式非法,请参考导入模板或联系系统管理员";
			}
			e.printStackTrace();
		} finally {
			session.close();
			
			/**
			 * 处理返回路径
			 */
			Map<String, String> dispathMap = new HashMap<String, String>();
			if (request.getParameter("perproty") != null && request.getParameter("perproty").length() > 0) {
				String perprotys[] = request.getParameter("perproty").split("/");
				for (int i = 0; i < perprotys.length; i++) {
					dispathMap.put(perprotys[i],request.getParameter(perprotys[i]));
				}
				printJson(request, response, statusCode, dispathMap,message);
				
			}
		}	
		return null;
		
//		String dispatchStr = request.getParameter("dispatchStr");
//		if (request.getParameter("perproty") != null && request.getParameter("perproty").length() > 0) {
//			String perprotys[] = request.getParameter("perproty").split("/");
//			for (int i = 0; i < perprotys.length; i++) {
//				if (dispatchStr.indexOf("?") != -1) {
//					dispatchStr += "&" + perprotys[i] + "=" + request.getParameter(perprotys[i]);
//				} else {
//					dispatchStr += "?" + perprotys[i] + "=" + request.getParameter(perprotys[i]);
//				}
//			}
//		}
//		return new ModelAndView("redirect:" + dispatchStr);
	}

	/**
	 * 根据名称返回对应的配置文件名称
	 * 
	 * @param ConfigName
	 *            配置名称，存放于import.xml
	 * @return 配置文件名称
	 */
	private String getConfinFileName(String ConfigName) throws Exception {
		/**
		 * 获取基本配置文件
		 */
		File f = new File(webInfPatch + configFile);
		if (!f.exists()) {
			throw new Exception("未找到基础配置文件");
		}
		SAXReader reader = new SAXReader();
		Document doc = reader.read(f);
		Element root = doc.getRootElement();
		Element foo;
		Iterator i;
		for (i = root.elementIterator("config"); i.hasNext();) {
			foo = (Element) i.next();
			String configName = foo.elementText("name");
			if (configName.equals(ConfigName)) {
				return foo.elementText("fileName");
			}
		}
		return null;
	}

	/**
	 * 获取配置信息
	 * 
	 * @param ConfigFileName
	 *            配置文件名称
	 * @return 存放配置信息map
	 * @throws Exception
	 */
	private Map getConfigInfo(String ConfigFileName) throws Exception {
		File f = new File(webInfPatch + ConfigFileName);
		if (!f.exists()) {
			throw new Exception("未找到用户配置文件");
		}
		SAXReader reader = new SAXReader();
		Document doc = reader.read(f);
		Element root = doc.getRootElement();
		Element foo;
		Iterator i;
		i = root.elementIterator("tableInfo");
		Map reuslt = new HashMap();
		/**
		 * 处理配置信息
		 */
		while (i.hasNext()) {
			Map tableMap = new HashMap();
			foo = (Element) i.next();
			/**
			 * 表名称，对应持久化对象名称
			 */
			String tableName = foo.element("tableName").getText();
			tableMap.put("$tableName", tableName);
			/**
			 * 表格信息所在sheet位于excel文件中位置
			 */
			Integer sheetNum = Integer.valueOf(foo.element("sheetNum").getText());
			tableMap.put("$sheetNum", sheetNum);
			/**
			 * 数据开始行
			 */
			Integer startRow = Integer.valueOf(foo.element("startRow").getText());
			tableMap.put("$startRow", startRow);
			/**
			 * 数据结束行
			 */
			if (foo.element("endRow").getText() != null && !foo.element("endRow").getText().equals("")) {
				Integer endRow = Integer.valueOf(foo.element("endRow").getText());
				tableMap.put("$endRow", endRow);
			}
			/**
			 * 数据结束标识
			 */
			String endFlag = foo.element("endFlag").getText();
			tableMap.put("$endFlag", endFlag);
			/**
			 * 处理父表信息
			 */
			Map fatherMap = new HashMap();
			Iterator fatherIt = foo.element("fatherTables").elementIterator("fatherTable");
			while (fatherIt.hasNext()) {
				Element element = (Element) fatherIt.next();
				Map fathertable = new HashMap();
				fathertable.put("$type", element.element("type").getText());
				fathertable.put("$tableName", element.element("tableName").getText());
				fathertable.put("$searchColumn", element.element("searchColumn").getText());
				fathertable.put("$index", element.element("index").getText());
				fathertable.put("$columnForSet", element.element("columnForSet").getText());
				fathertable.put("$columnToSet", element.element("columnToSet").getText());
				fatherMap.put(element.element("tableName").getText(), fathertable);
			}
			tableMap.put("$fatherMap", fatherMap);
			/**
			 * 处理字段信息
			 */
			Iterator columns = foo.element("columns").elementIterator("column");
			tableMap.put("$columnType", foo.element("columns").element("type").getText());
			tableMap.put("$titleRow", Integer.valueOf(foo.element("columns").element("titleRow").getText()));
			Map columnMap = new HashMap();
			while (columns.hasNext()) {
				Element element = (Element) columns.next();
				Map column = new HashMap();
				String index = element.element("index").getText();
				if (!"".equals(index)){
					column.put("$index", new Integer(index));
				}else
				column.put("$index", "");
				column.put("$name", element.element("name").getText());
				Element e_required = element.element("required");
				if(e_required != null)
					column.put("$required", element.element("required").getText());
				else
					column.put("$required", "false");
				
				columnMap.put(element.element("columnName").getText(), column);
			}
			tableMap.put("$columnMap", columnMap);

			reuslt.put(tableName, tableMap);
		}
		return reuslt;
	}

	/**
	 * 将excel信息写入给定对象中
	 * 
	 * @param o
	 *            需要注入的对象
	 * @param columnIndex
	 *            存放字段所在列信息，key：字段名称，与o中属性名称一致。value：所在列
	 * @param sheet
	 *            excel工作表
	 * @param row
	 *            当前所在行
	 * @throws Exception
	 */
	public boolean injectFromExcel(Object o, Map<?, ?> columnIndex, Sheet sheet, int row, HttpServletRequest request)
			throws Exception {
		boolean set = false;
		Class<?> clazz = o.getClass();
		Method method[] = clazz.getDeclaredMethods();

		Map<?, ?> t_colMap = (Map<?, ?>) columnIndex.get("ID");
		String t_value = null;
		if (t_colMap != null) {
			int index = ((Integer) t_colMap.get("$index")).intValue();
			Cell cell = sheet.getCell(index, row);
			if (cell.getContents() != null && cell.getContents().length() > 0) {
				t_value = cell.getContents();
			}
			if (t_value != null && !t_value.equals("")) {
				o = queryService.searchById(o.getClass(), convertUtil.toLong(t_value));
			}
		}

		for (int i = 0; i < method.length; i++) {
			Class<?> clazz1[] = method[i].getParameterTypes();
			if (clazz1.length == 1) {
				if (method[i].getName().indexOf("set") != -1) {
					String property[] = null;
					String colName = method[i].getName().replaceFirst("set", "").toUpperCase();
					Map<?, ?> colMap = (Map<?, ?>) columnIndex.get(colName);
					if (colMap != null) {
						int index = ((Integer)colMap.get("$index")).intValue();
						Cell cell = sheet.getCell(index, row);
						if (cell.getContents() != null && cell.getContents().length() > 0) {
							property = new String[] { cell.getContents() };
						}
					}
					if (property != null) {
						if (PropertyInject.invoke(o, method[i], property, "GBK", "GBK"))
							set = true;
					}
				}
			}
		}
		request.setAttribute("obj", o);
		return set;
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
			Map<String, String> dispathMap,String originalMessage) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		String message = convertUtil.toString(request.getParameter("_message"));
		if ("200".equals(statusCode)) {
			message = message + "成功";
		} else if ("301".equals(statusCode)) {
			message = message + "超时失败";
		} else {
			message = message + "失败，"+originalMessage;
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
	 * @param saveService The saveService to set.
	 */
	public void setSaveService(SaveService saveService) {
		this.saveService = saveService;
	}

	/**
	 * @param queryService The queryService to set.
	 */
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	/**
	 * @param exceptionService The exceptionService to set.
	 */
	public void setExceptionService(ExceptionService exceptionService) {
		this.exceptionService = exceptionService;
	}
}
