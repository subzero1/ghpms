package com.ghpms.controller.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

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
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta07_formfield;
import com.netsky.base.service.ConfigXMLService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
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

	private static String CONFIG_FILE = "/importConfig/import.xml";

	private String webInfPatch = "";

	/**
	 * 通用导出Excel
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

	@RequestMapping("/excelToData.do")
	public ModelAndView excelToData(HttpServletRequest HttpRequest,
			HttpServletResponse response) throws Exception {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) HttpRequest;
		String statusCode = "200";
		String message = "";
		ModelMap modelMap = new ModelMap();
		Map docMap = null;
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		Long module_id = convertUtil.toLong(request.getParameter("module_id"));
		docMap = gcsjDataService.getFormTitleMap(user, module_id);
		List<Ta07_formfield> docColsList = (List<Ta07_formfield>) docMap
				.get("docColsList");

		//取session
		Session session = null;
		Transaction tx = null;
		session = saveService.getHiberbateSession();
		tx = session.beginTransaction();
		tx.begin();
		/**
		 * 处理上传文件
		 */
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
			 for (Ta07_formfield ta07_formfield : docColsList) {
				 String name = (String)ta07_formfield.getName();
				 String title=(String)ta07_formfield.getComments();
				 for (int i = 0; i < cell.length; i++) {
						if (cell[i].getContents() != null && cell[i].getContents().equals(title)) {
							rightExcel = true;
//							col.put("$index", new Integer(cell[i].getColumn()));
//							columnMap.put(name, col);
						}
					}
				 
			}
		}

		return null;
	}

}
