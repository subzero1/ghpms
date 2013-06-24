package com.netsky.base.imagecut;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;

public class ImageFtp {

	private static String ftpConfigFile = "/ftpConfig/ftpConfig.xml";
	
	/**
	 * 头像上传到ftp
	 * 
	 */
	public boolean photoftp(HttpServletRequest request,String photoUrl,String photoFtpName,String folder){
		
		try{
			Map<String,String> ftpConfig = this.getFtpConfig(request);
			Map<String, String> ftpFolder = this.getFtpFolder(request);
			
			String Folder;
			if (folder != null) {
				Folder = ftpFolder.get(folder);
			} else {
				Folder = ftpConfig.get("folder");
			}
			
			FileInputStream fis = new FileInputStream(photoUrl); 
			FtpClient client = new FtpClient();
			client.openServer(ftpConfig.get("address"));
			client.login(ftpConfig.get("username"), ftpConfig.get("password"));
			client.cd(Folder);
			TelnetOutputStream out = client.put(photoFtpName);
			int c;   
	        byte buffer[]=new byte[1024];   
	        while((c=fis.read(buffer))!=-1){   
	            for(int k=0;k<c;k++) {
	                out.write(buffer[k]); 
	            }
	        }   
			out.flush();
			out.close();
			fis.close(); 
			client.closeServer();
			
			return true;
		}catch (Exception e){
			System.out.println(e);
			return false;
		}
	}
	/**
	 * 
	 * @param request
	 * @return xml文件获取配置信息 ftp的
	 * @throws Exception
	 */
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
