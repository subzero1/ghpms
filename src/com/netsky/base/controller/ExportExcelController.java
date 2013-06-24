package com.netsky.base.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.export.ExportExcel;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.QueryService;

/**
 * @description: 导出Excel
 * @class name:com.netsky.base.controller.ExportExcelController
 * @author Administrator May 7, 2010
 */
@Controller
// 注释1
public class ExportExcelController {

	@Autowired
	private QueryService queryService;

	private static String CONFIG_FILE = "/importConfig/import.xml";
	
	private String webInfPatch="";

	/**
	 * 参数说明 fileName 导出Excel的名字 sheetMap 导出Excel的各sheet的名称及取sheet数据的hsql
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *             void
	 */
	@RequestMapping("/export/toExcelWhithHsql.do")
	public void toExcelWhithHsql(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("GBK");
		ResultObject ro = null;

		String fileName = convertUtil.toString(request.getAttribute("ExcelName"), "export.xls");
		Map<String, String> sheetMap = (Map<String, String>) request.getAttribute("sheetMap");

		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		// response.setContentType("application/vnd.ms-excel;charset=GBK;filename="
		// + fileName);
		jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		Iterator itr = sheetMap.keySet().iterator();
		int i = 0;
		while (itr.hasNext()) {
			String sheetName = convertUtil.toString(itr.next());
			jxl.write.WritableSheet ws0 = wwb.createSheet(sheetName, i++);
			ro = queryService.search(sheetMap.get(sheetName));
			ExportExcel.Ro2Excel(ro, ws0);
		}
		wwb.write();
		wwb.close();
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	@RequestMapping("/export/toExcelWhithList.do")
	public void toExcelWhithList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("GBK");
		ResultObject ro = null;

		String fileName = convertUtil.toString(request.getAttribute("ExcelName"), "export.xls");
		Map<String, List> sheetMap = (Map<String, List>) request.getAttribute("sheetMap");

		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		// response.reset();
		// response.setContentType("application/vnd.ms-excel;charset=GBK;filename="
		// + fileName);
		jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		Iterator itr = sheetMap.keySet().iterator();
		int i = 0;
		while (itr.hasNext()) {
			String sheetName = convertUtil.toString(itr.next());
			jxl.write.WritableSheet ws0 = wwb.createSheet(sheetName, i++);
			List tmp = sheetMap.get(sheetName);
			ExportExcel.List2Excel((List) tmp.get(0), (List) tmp.get(1), ws0);
		}
		wwb.write();
		wwb.close();
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	// http://127.0.0.1:8080/ghpms/exportController.do?excelModelName=gysExcelModel.xls&id=10
	@RequestMapping("/exportController.do")
	public void exportController(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String excelModelName = convertUtil.toString(request.getParameter("excelModelName"));
		Workbook wb = Workbook.getWorkbook(new FileInputStream(getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath().substring(0,
						getClass().getProtectionDomain().getCodeSource().getLocation().getPath().lastIndexOf("/"))
				+ "/" + excelModelName));
		response.setContentType("application/vnd.ms-excel;");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream(), wb);
		// 获得EXCEL配置表
		WritableSheet pzb = wwb.getSheet("配置表");
		// 观察页面配置
		// SheetSettings ss = pzb.getSettings();
		// System.out.println(ss.isHidden());
		// System.out.println(ss.isProtected());
		// 获得入参信息
		Cell rcxx = pzb.findCell("入参信息");
		Cell[] rc = pzb.getRow(rcxx.getRow());
		List<Cell> rcList = Arrays.asList(rc);
		rcList = rcList.subList(rcxx.getColumn() + 1, rcList.size());
		// 入参MAP
		Map<String, String> paramMap = new HashMap<String, String>();
		for (Cell cell : rcList) {
			if (convertUtil.toString(cell.getContents()).equals("")) {
				break;
			} else {
				String content = cell.getContents().trim();
				paramMap.put(content, convertUtil.toString(request.getParameter(content), convertUtil.toString(request
						.getAttribute(content))));
			}
		}
		// 替换EXCEL中所有入参
		for (WritableSheet sheet : wwb.getSheets()) {
			for (String key : paramMap.keySet()) {
				Cell cell = null;
				Pattern p = Pattern.compile(".*\\$\\{\\s*" + key + "\\s*\\}.*");
				while ((cell = sheet.findCell(p, 0, 0, sheet.getColumns() - 1, sheet.getRows() - 1, false)) != null) {
					sheet.addCell(new Label(cell.getColumn(), cell.getRow(), cell.getContents().replaceAll(
							"\\$\\{\\s*" + key + "\\s*\\}", paramMap.get(key)).trim()));
				}
			}
		}
		// 获得对象信息
		Cell dxxx = pzb.findCell("对象信息");
		Cell[] dxxxTitle = pzb.getRow(dxxx.getRow());
		List<Cell> dxxxTitleList = Arrays.asList(dxxxTitle);
		dxxxTitleList = dxxxTitleList.subList(dxxx.getColumn() + 1, dxxxTitleList.size());
		// 对象信息TITLEMAP
		Map<String, Cell> dxxxTitleMap = new HashMap<String, Cell>();
		for (Cell cell : dxxxTitleList) {
			if (convertUtil.toString(cell.getContents()).equals("")) {
				break;
			} else {
				String content = cell.getContents();
				dxxxTitleMap.put(content, cell);
			}
		}
		Map<String, Object> objectMap = new HashMap<String, Object>();
		for (int j = dxxx.getRow() + 1; j < pzb.getRows()
				&& pzb.getCell(dxxx.getColumn(), j).getContents().trim().equals(""); j++) {
			Map<String, String> dxxxMap = new HashMap<String, String>();
			for (String key : dxxxTitleMap.keySet()) {
				dxxxMap.put(key, pzb.getCell(dxxxTitleMap.get(key).getColumn(), j).getContents().trim());
			}
			String tableName = dxxxMap.get("数据库表名");
			String where = dxxxMap.get("条件");
			String[] tableNames = tableName.split(";");
			String[] wheres = where.split(";");
			for (int i = 0; i < tableNames.length; i++) {
				List tmpList = queryService.searchList("from "
						+ tableNames[i].substring(tableNames[i].lastIndexOf(".") + 1)
						+ (wheres[i].length() > 0 ? (" where " + wheres[i]) : ("")) + " order by "
						+ convertUtil.toString(dxxxMap.get("排序"), "id asc"));
				if (tmpList.size() > 0) {
					if (dxxxMap.get("是否唯一").equals("是")) {
						String ysdx = dxxxMap.get("元素对象");
						Object o = tmpList.get(0);
						objectMap.put(ysdx, o);
						// 替换EL表达式
						for (WritableSheet sheet : wwb.getSheets()) {
							Cell cell = null;
							Pattern p = Pattern.compile(".*\\$\\{\\s*" + ysdx + "\\s*\\.\\s*[^\\{\\}\\.\\$]*\\s*\\}.*");
							while ((cell = sheet.findCell(p, 0, 0, sheet.getColumns() - 1, sheet.getRows() - 1, false)) != null) {
								Pattern p1 = Pattern
										.compile("\\$\\{\\s*" + ysdx + "\\s*\\.\\s*[^\\{\\}\\.\\$]*\\s*\\}");
								Matcher m = p1.matcher(cell.getContents());
								int start = 0;
								String content = cell.getContents();
								while (m.find(start)) {
									String match = m.group();
									String property = match.substring(match.indexOf(".") + 1, match.length() - 1)
											.trim();
									String tmp_content = "";
									try {
										tmp_content = convertUtil.toString(o.getClass().getDeclaredMethod(
												"get" + convertUtil.firstLetterToUpperCase(property), null).invoke(o));
									} catch (NoSuchMethodException e) {
										if (sheet.getName().equals("配置表")) {
											tmp_content = "null";
										}
									}
									content = content.replaceFirst("\\$\\{\\s*" + ysdx
											+ "\\s*\\.\\s*[^\\{\\}\\.\\$]*\\s*\\}", tmp_content);
									start = m.end();
								}
								sheet.addCell(new Label(cell.getColumn(), cell.getRow(), content));
							}
						}
					} else {
						objectMap.put(dxxxMap.get("集合对象"), tmpList);
					}
					break;
				}
			}

		}
		// 写入数据
		// 获得SHEET页信息
		Cell sheetxx = pzb.findCell("SHEET页信息");
		Cell[] sheetxxTitle = pzb.getRow(sheetxx.getRow());
		List<Cell> sheetxxTitleList = Arrays.asList(sheetxxTitle);
		sheetxxTitleList = sheetxxTitleList.subList(sheetxx.getColumn() + 1, sheetxxTitleList.size());
		// 对象信息TITLEMAP
		Map<String, Cell> sheetxxTitleMap = new HashMap<String, Cell>();
		for (Cell cell : sheetxxTitleList) {
			if (convertUtil.toString(cell.getContents()).equals("")) {
				break;
			} else {
				String content = cell.getContents();
				sheetxxTitleMap.put(content, cell);
			}
		}
		List<List<Map<String, String>>> sheetxxPkgList = new ArrayList<List<Map<String, String>>>();
		List<Map<String, String>> sheetxxList = new ArrayList<Map<String, String>>();
		String sheetName = "";
		String objName = "";
		for (int j = sheetxx.getRow() + 1; j < pzb.getRows()
				&& pzb.getCell(sheetxx.getColumn(), j).getContents().trim().equals(""); j++) {
			Map<String, String> sheetxxMap = new HashMap<String, String>();
			for (String key : sheetxxTitleMap.keySet()) {
				sheetxxMap.put(key, pzb.getCell(sheetxxTitleMap.get(key).getColumn(), j).getContents().trim());
			}
			if (!sheetxxMap.get("SHEET页名称").equals(sheetName) || !sheetxxMap.get("对象").equals(objName)) {
				if (!sheetxxList.isEmpty()) {
					sheetxxPkgList.add(sheetxxList);
					sheetxxList = new ArrayList<Map<String, String>>();
				}
				sheetxxList.add(sheetxxMap);
			}
		}
		if (!sheetxxList.isEmpty()) {
			sheetxxPkgList.add(sheetxxList);
		}
		for (List<Map<String, String>> pkg : sheetxxPkgList) {
			Object obj = objectMap.get(pkg.get(0).get("对象"));
			WritableSheet ws = wwb.getSheet(pkg.get(0).get("SHEET页名称"));
			if (obj instanceof List) {
				List list = (List) obj;
				int j = convertUtil.toInteger(pkg.get(0).get("数据从第几行开始"));
				for (Object o : list) {
					for (Map<String, String> sheetxxMap : pkg) {
						String content = convertUtil.toString(
								o.getClass().getDeclaredMethod(
										"get"
												+ convertUtil.firstLetterToUpperCase(sheetxxMap.get("对应数据库字段")
														.toLowerCase()), null).invoke(o)).trim();
						Label cell = new Label(convertUtil.toInteger(sheetxxMap.get("列编号")), j, content);
						WritableCellFormat wcf = new WritableCellFormat();
						Alignment a = null;
						if (convertUtil.toString(sheetxxMap.get("对齐方式")).equals("CENTER")) {
							a = Alignment.CENTRE;
						} else if (convertUtil.toString(sheetxxMap.get("对齐方式")).equals("LEFT")) {
							a = Alignment.LEFT;
						} else if (convertUtil.toString(sheetxxMap.get("对齐方式")).equals("RIGHT")) {
							a = Alignment.RIGHT;
						}
						wcf.setAlignment(a);
						WritableFont wf = null;
						String zt = convertUtil.toString(sheetxxMap.get("字体"));
						Integer zh = convertUtil.toInteger(sheetxxMap.get("字号"));
						if (zh.equals(-1)) {
							zh = null;
						}
						if (zh == null)
							wf = new WritableFont(WritableFont.createFont(zt));
						else {
							wf = new WritableFont(WritableFont.createFont(zt), zh);
						}
						if (wf != null) {
							wcf.setFont(wf);
						}
						if (convertUtil.toString(sheetxxMap.get("有无边框")).equals("有")) {
							Border b = Border.ALL;
							BorderLineStyle ls = BorderLineStyle.THIN;
							wcf.setBorder(b, ls);
						}
						cell.setCellFormat(wcf);
						ws.addCell(cell);
					}
					j++;
				}
			}
		}
		// 设置配置表为隐藏保护工作表
		SheetSettings ss = pzb.getSettings();
		ss.setHidden(true);
		ss.setProtected(true);
		ss.setPassword("1834927605");
		wwb.write();
		wwb.close();
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ModelAndView
	 */
	@RequestMapping("/export/tempDownload.do")
	public ModelAndView tempDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ConfigName = new String(request.getParameter("config").getBytes("iso-8859-1"), "GBK");
		this.webInfPatch = request.getSession().getServletContext().getRealPath("WEB-INF");
		String t_packgePath = request.getParameter("packgePath");
		String packgePath=""; 
		if (t_packgePath != null) {
			packgePath = t_packgePath;
		}

		if (ConfigName == null || ConfigName.equals("")) {
			throw new Exception("缺少配置信息参数！");
		}

		String ConfigFileName = this.getConfinFileName(ConfigName,webInfPatch,CONFIG_FILE);
		if (ConfigFileName == null || ConfigFileName.equals("")) {
			throw new Exception("未找到对应的导入信息" + ConfigName);
		}

		//Map configInfo = getConfigInfo(ConfigFileName);
		List list=this.getConfigInfoList(ConfigFileName,webInfPatch);  
		String form_title="template";
		Map sheetMap=new HashMap(); 
		List sheetList=new LinkedList(); 
		
		sheetList.add(list);
		sheetList.add(new LinkedList());
		
		sheetMap.put(form_title, sheetList); 
		request.setAttribute("ExcelName", "template.xls");
		request.setAttribute("sheetMap", sheetMap);
		return new ModelAndView("/export/toExcelWhithList.do");
	}
	

	/**
	 * 根据名称返回对应的配置文件名称
	 * 
	 * @param ConfigName
	 *            配置名称，存放于import.xml
	 * @return 配置文件名称
	 */
	public String getConfinFileName(String ConfigName,String webInfPatch,String configInfo) throws Exception {
		/**
		 * 获取基本配置文件
		 */
		File f = new File(webInfPatch + CONFIG_FILE);
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
	
	
	
	public List getConfigInfoList(String ConfigFileName,String webInfPatch) throws Exception{
		List list=new LinkedList();
		File file=new File(webInfPatch+ConfigFileName);
		if(!file.exists()){
			throw new Exception("未找到用户配置文件");
		}
		SAXReader reader=new SAXReader();
		Document doc=reader.read(file);
		Element root=doc.getRootElement();
		Element foo;
		Iterator i;
		Iterator j;
		for(i=root.elementIterator("tableInfo"); i.hasNext();){
			foo=(Element) i.next(); 
			for(Iterator it=foo.element("columns").elementIterator("column");it.hasNext();){ 
				Element element=(Element) it.next();  
				list.add(element.elementText("name"));
			}
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	public static void main(String[] args) {
		// Pattern p =
		// Pattern.compile("\\$\\{\\s*[^\\{\\}\\.\\$]*\\.[^\\{\\}\\.\\$]*\\s*\\}");
		// Matcher m = p.matcher("sad${ab.c}fads${bcd}");
		// boolean b = m.matches();
		// if (m.find()) {
		// System.out.println(m.group());
		// } else {
		// System.out.println("nothing");
		// }
	}
}