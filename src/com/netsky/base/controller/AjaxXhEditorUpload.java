package com.netsky.base.controller;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.convertUtil;

import com.netsky.base.dataObjects.Ta03_user;

public class AjaxXhEditorUpload  implements org.springframework.web.servlet.mvc.Controller {
	
	@Autowired
	private SaveService saveService;

	@Autowired
	private QueryService queryService;
	
	/**
	 * 上传文件扩展名
	 */
	private String fileExt = "jpg,jpeg,bmp,gif,png";
	
	/**
	 * 上传文件文件大小限制
	 */
	private Long maxSize = 0l;
	
	/**
	 * 默认ftp配置文件路径
	 */
	private String ftpConfigFile = "/ftpConfig/ftpConfig.xml";

	/**
	 * 默认ftp配置文件路径
	 */
	private String slaveTableName;

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public Long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Long maxSize) {
		this.maxSize = maxSize;
	}

	public String getFtpConfigFile() {
		return ftpConfigFile;
	}

	public void setFtpConfigFile(String ftpConfigFile) {
		this.ftpConfigFile = ftpConfigFile;
	}

	public String getSlaveTableName() {
		return slaveTableName;
	}

	public void setSlaveTableName(String slavetableName) {
		slaveTableName = slavetableName;
	}

	/**
	 * 知识库删除ajax实现
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ModelAndView
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
	
		/**
		 * ftp相关参数
		 */
		Map<String, String> ftpConfig = this.getFtpConfig(request);
		Map<String, String> ftpFolder = this.getFtpFolder(request);
        InputStream ftp_input = null;
        
        /**
         * 数据表相关
         */
        String sFileName = null;
        String sExtName = null;
        Ta03_user user = (Ta03_user)request.getSession().getAttribute("user");
        Long user_id = user.getId();
        String user_name = user.getName();

	    // 上传文件数据处理过程
        String err = "";
        String newFileName = "";
        if ("application/octet-stream".equals(request.getContentType())) { //HTML 5 上传
            try {
                String dispoString = request.getHeader("Content-Disposition");
                int iFindStart = dispoString.indexOf("name=\"")+6;
                int iFindEnd = dispoString.indexOf("\"", iFindStart);
                iFindStart = dispoString.indexOf("filename=\"")+10;
                iFindEnd = dispoString.indexOf("\"", iFindStart);
                sFileName = dispoString.substring(iFindStart, iFindEnd);
                sExtName = sFileName.substring(sFileName.lastIndexOf(".")+1,sFileName.length());

                int i = request.getContentLength();
                byte buffer[] = new byte[i];
                int j = 0;
                while(j < i) { //获取表单的上传文件
                    int k = request.getInputStream().read(buffer, j, i-j);
                    j += k;
                }

                if (buffer.length == 0) { //文件是否为空
                    out.println("{\"err\":\"" + err + "\",\"msg\":\"上传文件不能为空\"}");
                    return null;
                }

                if (maxSize > 0 && buffer.length > maxSize) { //检查文件大小
                    out.println("{\"err\":\"" + err + "\",\"msg\":\"上传文件的大小超出限制\"}");
                    return null;
                }
                ftp_input = new ByteArrayInputStream(buffer);
                
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                newFileName = "";
                err = "错误: " + ex.getMessage();
            }

        } else {
			MultipartHttpServletRequest Mrequest = (MultipartHttpServletRequest) request;
			MultipartFile file = Mrequest.getFile("filedata");
			sFileName = file.getOriginalFilename();
			sExtName = sFileName.substring(sFileName.indexOf(".")+1,sFileName.length());
			if (file.getSize() == 0) { //文件是否为空
				out.println("{\"err\":\"" + err + "\",\"msg\":\"上传文件不能为空\"}");
                return null;
            }

            if (maxSize > 0 && file.getSize() > maxSize) { //检查文件大小
            	out.println("{\"err\":\"" + err + "\",\"msg\":\"上传文件的大小超出限制\"}");
                return null;
            }
			ftp_input = file.getInputStream();
        }
        
        /**
         * 保存到附件表
         */
        Object obj = null;
        Class clazz = Class.forName(slaveTableName);
        obj = clazz.newInstance();
        PropertyInject.setProperty(obj, "module_id", new Long(-1));
        PropertyInject.setProperty(obj, "file_name", sFileName);
        PropertyInject.setProperty(obj, "ext_name", sExtName);
        PropertyInject.setProperty(obj, "slave_type", "xhEditor");
        PropertyInject.setProperty(obj, "user_id", user_id);
        PropertyInject.setProperty(obj, "user_name", user_name);
        PropertyInject.setProperty(obj, "ftp_date", new Date());
        saveService.save(obj);
        
        /**
         * 上传到FTP
         */
        String fileName = convertUtil.toString((Long)PropertyInject.getProperty(obj, "id"))+"Slave."+sExtName;
        String folder = ftpFolder.get("xhEditor上传");
        FTPClient ftp = new FTPClient();
		ftp.connect(ftpConfig.get("address"));
		ftp.login(ftpConfig.get("username"), ftpConfig.get("password"));
		ftp.enterLocalPassiveMode();
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		folder += "/" + new SimpleDateFormat("yyyyMM").format(new Date());
		String folders[] = folder.split("/");
		for (int k = 0; k < folders.length; k++) {
			if (!ftp.changeWorkingDirectory(folders[k])) {
				if (!ftp.makeDirectory(folders[k])) {
					throw new Exception("创建目录失败");
				}
				ftp.changeWorkingDirectory(folders[k]);
			}
		}
		ftp.storeFile(fileName, ftp_input);
		if(ftp_input != null){
			ftp_input.close();
		}
		ftp.disconnect();
        
		/**
         * 保存到附件表（续）
         */
		PropertyInject.setProperty(obj, "ftp_url", folder + "/" + fileName);
		saveService.save(obj);
		newFileName = request.getContextPath() + "/download.do?slave_id="+PropertyInject.getProperty(obj, "id");
		out.println("{\"err\":\"" + err + "\",\"msg\":\"" + newFileName + "\"}");
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
