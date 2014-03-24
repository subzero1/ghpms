package com.ghpms.controller.base;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ghpms.service.GcsjDataService;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Ta07_formfield;
import com.netsky.base.service.ConfigXMLService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.DateFormatUtil;
import com.netsky.base.utils.RegExp;
import com.netsky.base.utils.convertUtil;

@Controller
public class DataToExcel {

	@Autowired
	private QueryService queryService;

	@Autowired
	private ConfigXMLService configXML;

	@Autowired
	private GcsjDataService gcsjDataService;

	@Autowired
	private SaveService saveService;

	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * 通用导出Excel 需要配置文件
	 * 
	 * @param request
	 * @param response
	 *            config 传递的import配置的参数
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/tableToExcel.do")
	public ModelAndView tableToExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String config = convertUtil.toString(request.getParameter("config"));
		String keyword = convertUtil.toString(request.getParameter("keyword"));
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "id");
		int k = 0;
		ResultObject ro = null;
		StringBuffer hql = new StringBuffer("");
		List mbkTitleList = new LinkedList(); // 标题列表
		List mbkColList = new LinkedList();// 列的字段值
		List mbkDocList = new LinkedList();// 表单数据
		Map<String, List> sheetMap = new HashMap<String, List>();
		List sheetList = new LinkedList();

		// 读取配置文件的标题列表
		String webinfpath = request.getSession().getServletContext()
				.getRealPath("WEB-INF");
		mbkTitleList = configXML.getTagListByConfig(config, webinfpath, "name");
		mbkColList = configXML.getTagListByConfig(config, webinfpath,
				"columnName");
		// 表名
		String tableName = (String) configXML.getElementsByName(
				webinfpath + configXML.getConfigFilePath(config, webinfpath),
				"tableName").get(0);
		// excel文件名
		String excelName = (String) configXML.getElementsByName(
				webinfpath + configXML.getConfigFilePath(config, webinfpath),
				"title").get(0);
		Iterator it = mbkColList.iterator();
		Object mbk = null;
		hql.append("select ");
		while (it.hasNext()) {
			if (k == 0)
				hql.append(" a." + ((it.next().toString()).toLowerCase()));
			else
				hql.append(" ,a." + ((it.next().toString()).toLowerCase()));
			k++;
		}
		hql.append(" from " + tableName + " a ");
		hql.append(" order by a.");
		hql.append(orderField);

		mbkDocList = queryService.searchList(hql.toString());

		sheetList.add(mbkTitleList);
		sheetList.add(mbkDocList);
		sheetMap.put("form_title", sheetList);
		request.setAttribute("ExcelName", excelName + ".xls");
		request.setAttribute("sheetMap", sheetMap);
		return new ModelAndView("/export/toExcelWhithList.do");
	}

	/**
	 * Excel导入到数据库
	 * 
	 * @param HttpRequest
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/excelToData.do")
	public ModelAndView excelToData(HttpServletRequest HttpRequest,
			HttpServletResponse response) throws Exception {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) HttpRequest;
		String statusCode = "200";
		String message = "";
		int excelRow=0;
		int success=0;//保存成功记录数
		int error=0;//保存出错记录数
		boolean isSave=true;//是否保存
		ModelMap modelMap = new ModelMap();
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		Long module_id = convertUtil.toLong(request.getParameter("module_id"));
		List<Ta07_formfield> docColsList = gcsjDataService.getExcelTitleList(
				user, module_id);
		if (docColsList==null||docColsList.size()==0) {
			isSave=false;
		}
		Ta06_module module = (Ta06_module) queryService.searchById(
				Ta06_module.class, module_id);
		// 权限之外添加ID校验
		docColsList.addAll(gcsjDataService.getUpdateProperty(module_id));
		if (isSave) {
		// 取session
//		Session session = null;
//		Transaction tx = null;
//		session = saveService.getHiberbateSession();
//		tx = session.beginTransaction();
//		tx.begin();
		/**
		 * 处理上传文件
		 */
		try {
			Iterator it = request.getFileNames();
			while (it.hasNext()) {
				String fileDispath = (String) it.next();
				MultipartFile file = request.getFile(fileDispath);
				Workbook wb = Workbook.getWorkbook(file.getInputStream());
				/**
				 * 循环处理单个表格
				 */
				Sheet st = wb.getSheet(0);
				/**
				 * 获取标题行所有列
				 */
				boolean rightExcel = false;
				Cell cell[] = st.getRow(0);
				Map columnMap = new HashMap();
				for (Ta07_formfield ta07_formfield : docColsList) {
					String name = (String) ta07_formfield.getName();
					String title = (String) ta07_formfield.getComments();
					for (int i = 0; i < cell.length; i++) {
						if (cell[i].getContents() != null
								&& cell[i].getContents().equals(title)) {
							rightExcel = true;
							Map col = new HashMap();
							col.put("$index", new Integer(cell[i].getColumn()));
							columnMap.put(name.toUpperCase(), col);
						}
					}
				}
				int startRow = 1;
				int totalRows = st.getRows();
				while (startRow < totalRows) {
					try {
						Object o = Class.forName(module.getProject_table())
								.newInstance();
						/**
						 * 注入request中于表格相关内容
						 */
						PropertyInject.inject(request, o, 0, "iso-8859-1", "GBK");
						/**
						 * 从excel注入信息
						 */
						injectFromExcel(o, columnMap, st, startRow, request);
						o = request.getAttribute("obj");
						
						//处理空白记录
						Cell cell2[]=st.getRow(startRow);
						int nullCount=0;
						for (int i = 0; i < cell2.length; i++) {
							if (cell2[i].getContents()==null||cell2[i].getContents().equals("")) {
								nullCount++;
							}
						}
						startRow++;
						excelRow=startRow;
						if (nullCount<cell.length) {
							saveService.save(o);
							success++;
						}
							
//					session.saveOrUpdate(o);
					} catch (Exception e) {
						error++;
						log.error(e.getMessage());
						log.error("导入出错：文件名"+file.getName()+" 路径:"+fileDispath+"出错行数："+excelRow);
						System.out.println("出错行数===================="+excelRow);
						continue;
					}
				}
			}
//			session.flush();
//			tx.commit();
		} catch (RuntimeException e) {
//			tx.rollback();
			statusCode = "300";
			message = e.getMessage();
			log.error("导入出错："+e.getMessage());
			if (message != null
					&& message.indexOf("recognize OLE stream") != -1) {
				message = "Excel格式非法，请将Excel另存为<font color=red>2003版</font>的<font color=red>标准</font>的Excel后再尝试";
			}else if (message!=null&&message.indexOf("could not insert")!=-1) {
				message="编号重复，请您仔细校对";
			}else if (message!=null&&message.indexOf("same identifier value")!=-1) {
				message="关键字段重复， 请您仔细校对";
			} else if (message!=null&&message.indexOf("Could not execute JDBC batch update")!=-1) {
				message="数据不规范， 请您仔细校对";
			}else {
				message = "请参考导入模板或者用户手册的数据导入规范";
			}
		} 
		finally {

//			session.close();

			/**
			 * 处理返回路径
			 */
			message="有效保存："+success+",失效记录："+error;
			Map<String, String> dispathMap = new HashMap<String, String>();
			if (request.getParameter("perproty") != null
					&& request.getParameter("perproty").length() > 0) {
				String perprotys[] = request.getParameter("perproty")
						.split("/");
				for (int i = 0; i < perprotys.length; i++) {
					dispathMap.put(perprotys[i], request
							.getParameter(perprotys[i]));
				}
//				statusCode="300";
				printJson(request, response, statusCode, dispathMap, message);

			}

		}

		
		}else {//无相关权限的情况
			/**
			 * 处理返回路径
			 */
			Map<String, String> dispathMap = new HashMap<String, String>();
			if (request.getParameter("perproty") != null
					&& request.getParameter("perproty").length() > 0) {
				String perprotys[] = request.getParameter("perproty")
						.split("/");
				for (int i = 0; i < perprotys.length; i++) {
					dispathMap.put(perprotys[i], request
							.getParameter(perprotys[i]));
				}
				statusCode="300";
				message="无相关权限！";
				printJson(request, response, statusCode, dispathMap, message);

			}

		}

		return null;
	}

	@RequestMapping("/dataToExcelTemplate.do")
	public ModelAndView dataToExcelTemplate(HttpServletRequest request,
			HttpServletResponse response) {

		// 条件
		Long moudle_id = convertUtil.toLong(request.getParameter("moudle_id"));
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		Ta06_module module = (Ta06_module) queryService.searchById(
				Ta06_module.class, moudle_id);
		// 取标题列
		List<Ta07_formfield> docColsList = gcsjDataService.getExcelTitleList(
				user, moudle_id);
		Map<String, List> sheetMap = new HashMap<String, List>();
		List sheetList = new LinkedList();
		List titleList = new LinkedList();
		for (Ta07_formfield ta07 : docColsList) {
			titleList.add(ta07.getComments().trim());
		}
		sheetList.add(titleList);
		sheetList.add(new ArrayList());
		sheetMap.put("form_title", sheetList);
		request.setAttribute("ExcelName", module.getName() + ".xls");
		request.setAttribute("sheetMap", sheetMap);
		return new ModelAndView("/export/toExcelWhithList.do");

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
	public boolean injectFromExcel(Object o, Map<?, ?> columnIndex,
			Sheet sheet, int row, HttpServletRequest request) throws Exception {
		boolean set = false;
		Class<?> clazz = o.getClass();
		Method method[] = clazz.getDeclaredMethods();
		String className=clazz.getName();

		Map<?, ?> t_colMap = (Map<?, ?>) columnIndex.get("ID"); 
		Map<?, ?> t_colMap1 = (Map<?, ?>) columnIndex.get("GHBH");
		Map<?, ?> t_colMap2 = (Map<?, ?>) columnIndex.get("SKBH");
		String t_value = null;
		if (t_colMap != null) {
			int index = ((Integer) t_colMap.get("$index")).intValue();
			Cell cell = sheet.getCell(index, row);
			if (cell.getContents() != null && cell.getContents().length() > 0) {
				t_value = cell.getContents();
			}
			if (t_value != null && !t_value.equals("")) {
				o = queryService.searchById(o.getClass(), convertUtil
						.toLong(t_value));
			}
		}else if (t_colMap1 != null) {
			int index = ((Integer) t_colMap1.get("$index")).intValue();
			Cell cell = sheet.getCell(index, row);
			if (cell.getContents() != null && cell.getContents().length() > 0) {
				t_value = cell.getContents();
			}
			if (t_value != null && !t_value.equals("")) {
				List objList=new ArrayList();
				objList = queryService.searchList("select t from "+className+" t where t.ghbh='"+t_value+"'");
				if (objList.size()>0) {
					o=objList.get(0);
				}
			} 
		}else if (t_colMap2 != null) {
			int index = ((Integer) t_colMap2.get("$index")).intValue();
			Cell cell = sheet.getCell(index, row);
			if (cell.getContents() != null && cell.getContents().length() > 0) {
				t_value = cell.getContents();
			}
			if (t_value != null && !t_value.equals("")) {
				List objList=new ArrayList();
				objList = queryService.searchList("select t from "+className+" t where t.skbh='"+t_value+"'");
				if (objList.size()>0) {
					o=objList.get(0);
				}
			} 
		}

		for (int i = 0; i < method.length; i++) {
			Class<?> clazz1[] = method[i].getParameterTypes();
			if (clazz1.length == 1) {
				if (method[i].getName().indexOf("set") != -1) {
					String property[] = null;
					String colName = method[i].getName()
							.replaceFirst("set", "").toUpperCase();
					Map<?, ?> colMap = (Map<?, ?>) columnIndex.get(colName);
					if (colMap != null) {
						int index = ((Integer) colMap.get("$index")).intValue();
						Cell cell = sheet.getCell(index, row);
						// 转换日期
						CellType cellType = cell.getType();
						String dataType = clazz1[0].getName();
						Date date;
						if (cell.getContents() != null
								&& cell.getContents().length() > 0) {
							if (cellType == CellType.DATE) {
								date = ((DateCell) cell).getDate();
								property = new String[] { DateFormatUtil
										.FormatDate(date) };
							} else {
								if (dataType.equals("java.util.Date")) {
									// 替掉.,取第一个日期
									property = new String[] { new RegExp()
											.pickup("(\\d{4}(\\-\\d{1,2}){2})",
													cell.getContents().replace(
															".", "-").replace(
															"/", "-")) };
								} else if (dataType.equals("java.lang.Long")) {
									property = new String[] { new RegExp()
											.pickup("([0-9]+)", cell
													.getContents().replace(",",
															"")) };
								} else if (dataType.equals("java.lang.Double")) {
									property = new String[] { new RegExp()
											.pickup("(\\d+\\.?\\d*)", cell
													.getContents().replace(",",
															"")) };
								} else {
									property = new String[] { cell
											.getContents() };
								}

							}

						}
					}
					try {
						if (property != null) {
							if (PropertyInject.invoke(o, method[i], property,
									"GBK", "GBK"))
								set = true;
						}
					} catch (Exception e) {
						// TODO: handle exception;
						System.err.println(e.getMessage());
						log.error("字段" + colName + ",出错信息:" + e.getMessage());
						continue;
					}
				}
			}
		}
		request.setAttribute("obj", o);
		return set;
	}

	private void printJson(HttpServletRequest request,
			HttpServletResponse response, String statusCode,
			Map<String, String> dispathMap, String originalMessage)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		String message = convertUtil.toString(request.getParameter("_message"));
		if ("200".equals(statusCode)) {
			message = message + "成功"+originalMessage;
		} else if ("301".equals(statusCode)) {
			message = message + "超时失败";
		} else {
			message = message + "失败，" + originalMessage;
		}
		String navTabId = convertUtil.toString(request
				.getParameter("_navTabId"));
		String forwardUrl = convertUtil.toString(request
				.getParameter("_forwardUrl"));
		String callbackType = convertUtil.toString(request
				.getParameter("_callbackType"));
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
				"{\"statusCode\":\"" + statusCode + "\", \"message\":\""
						+ message + "\", \"navTabId\":\"" + navTabId
						+ "\", \"forwardUrl\":\"" + forwardUrl
						+ "\", \"callbackType\":\"" + callbackType + "\""
						+ backParam + "}");
	}

}
