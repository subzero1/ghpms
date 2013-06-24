package com.netsky.base.controller.form;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
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

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

import sun.net.ftp.FtpClient;
/**
 * 附件操作
 * @author liuxu
 * @cerate 2010-06-24
 */
@Controller
public class Delsend {
	/**
	 * 保存服务
	 */
	@Autowired
	private SaveService saveService;
	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;
	/**
	 * 错误信息
	 */
	@Autowired
	private ExceptionService exceptionService;
	/**
	 * 注入服务
	 * @param exceptionService
	 */
	public void setExceptionService(ExceptionService exceptionService) {
		this.exceptionService = exceptionService;
	}
	/**
	 * 默认ftp配置文件路径
	 */
	private static String ftpConfigFile = "/ftpConfig/ftpConfig.xml";
	
@RequestMapping("/delsend.do")
	
	public ModelAndView delsendRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String id=request.getParameter("slave_id");
		String pro_id=request.getParameter("project_id");
		
		
		try{
			
			if(id!=null&&!"".equals(id))
			{   
				Map<String,String> ftpConfig = this.getFtpConfig(request);			
				FtpClient client = new FtpClient();				
				client.openServer(ftpConfig.get("address"));
				client.login(ftpConfig.get("username"), ftpConfig.get("password"));
				String sql1="select ftp_url from Te01_slave where id="+id;
				ResultObject ro1 = queryService.search(sql1);
				if (ro1.next())
				client.sendServer("dele "+ro1.get("ftp_url")+ "\r\n");	
				client.readServerResponse();
				
				client.closeServer(); 
				saveService.updateByHSql("delete Te01_slave where id="+id);
				
				}
				saveService.updateByHSql("delete Te02_jlfk  where id="+pro_id);
				out.print("{\"statusCode\":\"200\", \"message\":\"删除文件成功\"}");
				
		}catch(Exception e){
			out.print("{\"statusCode\":\"300\", \"message\":\"删除文件失败\"}");
		}
		out.flush();
		out.close();
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


public void setSaveService(SaveService saveService) {
	this.saveService = saveService;
}
}
