package com.netsky.base.controller;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Te01_slave;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.convertUtil;

import sun.net.TelnetInputStream;
import sun.net.ftp.FtpClient;
/**
 * 附件操作
 * @author CT
 * @cerate 2010-03-11
 */
@Controller
public class OperFile{
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
	
	//下载文件
	@RequestMapping("/download.do")
	public ModelAndView downloadRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma","No-cache"); 
		response.setHeader("Cache-Control","no-cache"); 
		response.setDateHeader("Expires", 0); 
			
			Map<String,String> ftpConfig = this.getFtpConfig(request);			
			FtpClient client = new FtpClient();		
			client.openServer(ftpConfig.get("address"));
			client.login(ftpConfig.get("username"), ftpConfig.get("password"));
			client.binary();
			QueryBuilder queryBuilder = new HibernateQueryBuilder(Te01_slave.class);
			queryBuilder.eq("id", new Long(request.getParameter("slave_id")));
			ResultObject ro = queryService.search(queryBuilder);
			Te01_slave te01= new Te01_slave();
			if(ro.next()){
				te01 = (Te01_slave)ro.get(Te01_slave.class.getName());
			}
			try{
			TelnetInputStream is = client.get(te01.getFtp_url());    
			response.reset();
			response.setHeader("Content-Disposition",
					"attachment;filename="+URLEncoder.encode(te01.getFile_name(),"UTF-8"));

			byte[] bytes = new byte[1024];   
			int readBye;   
			while ((readBye = is.read(bytes)) != -1) {      
               response.getOutputStream().write(bytes, 0, readBye);
			} 
			is.close(); 
			response.getOutputStream().close();
            
			}catch(Exception e){
				return null;
			}finally {
				client.closeServer(); 
			}
			  
			
            return null;
	}
	
	//删除文件
	@RequestMapping("/delfile.do")
	public ModelAndView delfileRequest(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("gb2312");
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		
		String navTabId = convertUtil.toString(request.getParameter("navTabId"), "");
		String forwardUrl = convertUtil.toString(request.getParameter("forwardUrl"), "");
		String callbackType = convertUtil.toString(request.getParameter("callbackType"), "");
		String slave_index = convertUtil.toString(request.getParameter("slave_index"), "");

		Long id=convertUtil.toLong(request.getParameter("slave_id"));
		if(id == -1)
			id = (Long)request.getAttribute("slave_id");
		
		Map<String,String> ftpConfig = this.getFtpConfig(request);			
		FtpClient client = new FtpClient();				
		client.openServer(ftpConfig.get("address"));
		client.login(ftpConfig.get("username"), ftpConfig.get("password"));
		
		Te01_slave te01 = (Te01_slave)queryService.searchById(Te01_slave.class, id);
		
		try{
			client.sendServer("dele "+te01.getFtp_url()+ "\r\n");	
			client.readServerResponse();
			client.closeServer();  
			saveService.updateByHSql("delete from Te01_slave where id="+id);
			
			out.print("{\"statusCode\":\"200\", \"message\":\"删除文件成功\", \"navTabId\":\"" + navTabId + "\", \"forwardUrl\":\"" + forwardUrl + "\", \"callbackType\":\"" + callbackType + "\", \"slaveIndex\":\"" + slave_index + "\"}");
			
		}catch(Exception e){
			out.print("{\"statusCode\":\"300\", \"message\":\"删除文件失败\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\", \"slaveIndex\":\"\"}");
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
	
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}
	
	//删除文件
	public void delfile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Long id=convertUtil.toLong(request.getParameter("slave_id"));
		if(id == -1)
			id = (Long)request.getAttribute("slave_id");
		
		Map<String,String> ftpConfig = this.getFtpConfig(request);			
		FtpClient client = new FtpClient();				
		client.openServer(ftpConfig.get("address"));
		client.login(ftpConfig.get("username"), ftpConfig.get("password"));
		
		Te01_slave te01 = (Te01_slave)queryService.searchById(Te01_slave.class, id);
		
		try{
			client.sendServer("dele "+te01.getFtp_url()+ "\r\n");	
			client.readServerResponse();
			client.closeServer();  
			saveService.updateByHSql("delete from Te01_slave where id="+id);
		}catch(Exception e){
		}
	}
}
