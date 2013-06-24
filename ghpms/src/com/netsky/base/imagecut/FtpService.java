package com.netsky.base.imagecut;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.multipart.MultipartFile;

public class FtpService {

	/**
	 * ftp 文件路径
	 */
	private static String ftpConfigFile = "/ftpConfig/ftpConfig.xml";

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
	
	/**
	 * @author wlx 文件上传
	 * @param request,FileUrl 读取文件路径,FileFtpName 文件的FTP名称,FtpXmlFolder FTP路径名称
	 */
	public String FileFtpUpload(HttpServletRequest request,String FileUrl,String FileFtpName,String FtpXmlFolder){

		try{
			Map<String,String> ftpConfig = this.getFtpConfig(request);
			Map<String,String> ftpFolder = this.getFtpFolder(request);
			
			String folder;
			if (FtpXmlFolder != null) {
				folder = ftpFolder.get(FtpXmlFolder);
			} else {
				folder = ftpConfig.get("folder");
			}
			FileInputStream fis = new FileInputStream(FileUrl); 
			FTPClient ftp = new FTPClient();
			ftp.connect(ftpConfig.get("address"));
			ftp.login(ftpConfig.get("username"), ftpConfig.get("password"));
			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			if (!ftp.changeWorkingDirectory(folder)) {
				if (!ftp.makeDirectory(folder)) {
					throw new Exception("创建目录失败");
				}
				ftp.changeWorkingDirectory(folder);
			}
			OutputStream out = ftp.storeFileStream(new String(FileFtpName.getBytes("GBK"), "iso8859-1")); 
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
			
			ftp.disconnect();
			return folder+"/"+FileFtpName;
			
		}catch (Exception e){
			System.out.println(e);
			return "failed";
		}
	}
	
	/**
	 * @author wlx 文件上传
	 * @param request,
	 */
	public String FileFtpUpload(HttpServletRequest request,MultipartFile file,String FileFtpName,String FtpXmlFolder){
		
		try{
			Map<String,String> ftpConfig = this.getFtpConfig(request);
			Map<String, String> ftpFolder = this.getFtpFolder(request);
			
			String folder;
			if (FtpXmlFolder != null) {
				folder = ftpFolder.get(FtpXmlFolder);
			} else {
				folder = ftpConfig.get("folder");
			}
			
			FTPClient ftp = new FTPClient();
			ftp.connect(ftpConfig.get("address"));
			ftp.login(ftpConfig.get("username"), ftpConfig.get("password"));
			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
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
			ftp.storeFile(FileFtpName, file.getInputStream());
			ftp.disconnect();
			
			return folder+"/"+FileFtpName;
			
		}catch (Exception e){
			System.out.println(e);
			return "failed";
		}
	}
	
	/**
	 * @author wlx 文件删除
	 * @param request,FtpFileUrl 文件在ftp上的路径
	 */
	public boolean FtpFileDel(HttpServletRequest request,String FtpFileUrl){
		
		try{
			Map<String,String> ftpConfig = this.getFtpConfig(request);
			
			FTPClient ftp = new FTPClient();
			ftp.connect(ftpConfig.get("address"));
			ftp.login(ftpConfig.get("username"), ftpConfig.get("password"));
			boolean result = ftp.deleteFile(FtpFileUrl);
			ftp.disconnect();
			return result;
		}catch (Exception e){
			System.out.println(e);
			return false;
		}	
	}
	
	/**
	 * @author wlx 文件复制
	 * @param request,FtpFileUrl 文件在ftp上的路径
	 */
	public String FtpFileCopy(HttpServletRequest request,String FtpFileUrl,String FtpXmlFolder,String FileFtpName){
		
		try{
			
			Map<String,String> ftpConfig = this.getFtpConfig(request);
			Map<String, String> ftpFolder = this.getFtpFolder(request);
			
			String folder;
			if (FtpXmlFolder != null) {
				folder = ftpFolder.get(FtpXmlFolder);
			} else {
				folder = ftpConfig.get("folder");
			}
			
			FTPClient ftp = new FTPClient();
			ftp.connect(ftpConfig.get("address"));
			ftp.login(ftpConfig.get("username"), ftpConfig.get("password"));
			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			
			FTPClient ftp2 = new FTPClient();
			ftp2.connect(ftpConfig.get("address"));
			ftp2.login(ftpConfig.get("username"), ftpConfig.get("password"));
			ftp2.enterLocalPassiveMode();
			ftp2.setFileType(FTP.BINARY_FILE_TYPE);
			folder += "/" + new SimpleDateFormat("yyyyMM").format(new Date());
			String folders[] = folder.split("/");
			for (int j = 0; j < folders.length; j++) {
				if (!ftp2.changeWorkingDirectory(folders[j])) {
					if (!ftp2.makeDirectory(folders[j])) {
						throw new Exception("创建目录失败");
					}
					ftp2.changeWorkingDirectory(folders[j]);
				}
			}
			ftp2.storeFile(FileFtpName, ftp.retrieveFileStream(new String(FtpFileUrl.getBytes("GBK"), "iso8859-1")));
			
			ftp2.disconnect();
			ftp.disconnect();
			return folder+"/"+FileFtpName;
		}catch (Exception e){
			System.out.println(e);
			return "failed";
		}
		
	}
}
