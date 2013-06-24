package com.netsky.base.Office2Html;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sun.net.TelnetInputStream;
import sun.net.ftp.FtpClient;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Te01_slave;
import com.netsky.base.service.QueryService;

/**
 * @description:在线预览附件
 * 
 * @class name:com.netsky.base.Office2Html.Slave2Html
 * @author Chiang Oct 31, 2011
 */
@Controller
public class Slave2Html {
	/**
	 * 默认ftp配置文件路径
	 */
	private static String ftpConfigFile = "/ftpConfig/ftpConfig.xml";
	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;

	@RequestMapping("/show_slave.do")
	public ModelAndView show_slave(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("slave_id", request.getParameter("slave_id"));
		return new ModelAndView("/WEB-INF/jsp/show_slave.jsp");
	}

	@RequestMapping("/slave2Html/slave2Html.do")
	public ModelAndView slave2Html(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> ftpConfig = this.getFtpConfig(request);
		//response.setCharacterEncoding("GBK");
		response.setCharacterEncoding("UTF-8");
		QueryBuilder queryBuilder = new HibernateQueryBuilder(Te01_slave.class);
		queryBuilder.eq("id", new Long(request.getParameter("slave_id")));
		ResultObject ro = queryService.search(queryBuilder);
		Te01_slave te01 = new Te01_slave();
		if (ro.next()) {
			te01 = (Te01_slave) ro.get(Te01_slave.class.getName());
		}
		if (".doc".equalsIgnoreCase(te01.getExt_name()) || ".docx".equalsIgnoreCase(te01.getExt_name())
				|| ".xls".equalsIgnoreCase(te01.getExt_name()) || ".xlsx".equalsIgnoreCase(te01.getExt_name())
				|| ".ppt".equalsIgnoreCase(te01.getExt_name()) || ".pptx".equalsIgnoreCase(te01.getExt_name())
				|| ".rtf".equalsIgnoreCase(te01.getExt_name()) || ".txt".equalsIgnoreCase(te01.getExt_name())
				|| ".java".equalsIgnoreCase(te01.getExt_name()) || ".c".equalsIgnoreCase(te01.getExt_name())
				|| ".log".equalsIgnoreCase(te01.getExt_name()) || ".xml".equalsIgnoreCase(te01.getExt_name())
				|| ".js".equalsIgnoreCase(te01.getExt_name())) {
			String path = request.getSession().getServletContext().getRealPath("slave2Html");
			String fileName = te01.getFtp_url().substring(te01.getFtp_url().lastIndexOf("/") + 1);
			if (".txt".equalsIgnoreCase(te01.getExt_name()) || ".java".equalsIgnoreCase(te01.getExt_name())
					|| ".c".equalsIgnoreCase(te01.getExt_name()) || ".log".equalsIgnoreCase(te01.getExt_name())
					|| ".xml".equalsIgnoreCase(te01.getExt_name()) || ".js".equalsIgnoreCase(te01.getExt_name())) {
				/**
				 * 纯文本格式
				 */
				fileName = fileName.substring(0, fileName.lastIndexOf(".")) + ".odt";
			}
			File inputFile = new File(path + "/" + fileName);
			File outputFile = new File(path + "/" + fileName.substring(0, fileName.lastIndexOf(".")) + ".html");
			if (!outputFile.exists()) {
				FtpClient client = new FtpClient();
				client.openServer(ftpConfig.get("address"));
				client.login(ftpConfig.get("username"), ftpConfig.get("password"));
				client.binary();
				TelnetInputStream is = client.get(te01.getFtp_url());
				OutputStream out = new FileOutputStream(inputFile);
				byte[] bytes = new byte[1024];
				int readBye;
				while ((readBye = is.read(bytes)) != -1) {
					out.write(bytes, 0, readBye);
				}
				is.close();
				out.close();

				Office2Html.convert(inputFile, outputFile);
				inputFile.delete();
			}
			return new ModelAndView(fileName.substring(0, fileName.lastIndexOf(".")) + ".html");
		} else if (".jpg".equalsIgnoreCase(te01.getExt_name()) || ".jpeg".equalsIgnoreCase(te01.getExt_name())
				|| ".jpe".equalsIgnoreCase(te01.getExt_name()) || ".jfif".equalsIgnoreCase(te01.getExt_name())
				|| ".bmp".equalsIgnoreCase(te01.getExt_name()) || ".dib".equalsIgnoreCase(te01.getExt_name())
				|| ".gif".equalsIgnoreCase(te01.getExt_name()) || ".png".equalsIgnoreCase(te01.getExt_name())
				|| ".tif".equalsIgnoreCase(te01.getExt_name()) || ".tiff".equalsIgnoreCase(te01.getExt_name())) {
			request.setAttribute("slave_id", request.getParameter("slave_id"));
			return new ModelAndView("/WEB-INF/jsp/show_img.jsp");
		}else if(".pdf".equalsIgnoreCase(te01.getExt_name())){
			request.setAttribute("slave_id", request.getParameter("slave_id"));
			return new ModelAndView("/WEB-INF/jsp/show_pdf.jsp");
		}
		return null;
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
}
