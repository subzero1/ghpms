package com.netsky.base.utils;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.netsky.base.baseObject.SharedCfgData;

public class FtpUtil extends FTPClient{
	
	/**
	 * ftp配置文件路径（绝对路径）
	 */
	private String ftpCfg;
	
	/**
	 * ftp配置文件信息（地址、用户名、密码等信息）
	 */
	private Map<String, String> result;
	
	public Map<String, String> getResult() {
		return result;
	}

	/**
	 * 通过FTP文件名配置文件读出FTP相关信息
	 * @throws Exception void
	 */
	public void setResult() throws Exception{
		
		File file = new File(ftpCfg);
		if (!file.exists()) {
			throw new Exception("FTP配置文件不存在" + ftpCfg);
		}
		
		result = new HashMap<String, String>();
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(file);
			Element root = doc.getRootElement();
			result.put("folder", root.elementText("folder"));
			result.put("address", root.elementText("address"));
			result.put("username", root.elementText("username"));
			result.put("password", root.elementText("password"));
			Iterator<?> j;
			for (j = root.elementIterator("typeFolder"); j != null && j.hasNext();) {
				Element element = (Element) j.next();
				result.put(element.elementText("type"), element.elementText("folder"));
			}
		} catch (DocumentException ex) {
			throw new Exception("读取FTP配置文件失败" + ftpCfg + ":" + ex);
		}
	}

	/**
	 * 设置默认ftp配置文件路径[WEB-INF/autoConfig/baseConfig.properties]
	 */
	public void setDefaultFtpCfg() throws Exception{
		
		String t_webRoot = null;
		String ftpConfigFile = null;
		Map<String,String> cfgMap = SharedCfgData.getMap();
		String base_cfg_uri = cfgMap.get("baseConfig.properties");
		OperProperties op = new OperProperties();
		op.load(base_cfg_uri);
		t_webRoot = op.getValue("webRoot");
		ftpConfigFile = convertUtil.toString(op.getValue("ftpConfigFile"));
		Map<String, String> result = new HashMap<String, String>();
		this.ftpCfg = t_webRoot + System.getProperty("file.separator") + "WEB-INF" + ftpConfigFile;
	}
	
	public String getFtpCfg() {
		return ftpCfg;
	}

	public void setFtpCfg(String ftpCfg) {
		this.ftpCfg = ftpCfg;
	}

	/**
	 * 使用默认配置文件，连接并登录FTP
	 */
	public void ftpConnect()throws Exception{
		
		this.setDefaultTimeout(60000);
		this.setDataTimeout(60000);
		this.setDefaultFtpCfg();
		this.setResult();
		this.connect(result.get("address"));
		this.login(result.get("username"), result.get("password"));
		this.enterLocalPassiveMode();
		this.setFileType(FTP.BINARY_FILE_TYPE);
	}
	
	/**
	 * 使用指认配置文件，连接并登录FTP
	 */
	public void ftpConnect(String ftpCfg)throws Exception{
		
		this.setDefaultTimeout(60000);
		this.setDataTimeout(60000);
		this.setFtpCfg(ftpCfg);
		this.setResult();
		this.connect(result.get("address"));
		this.login(result.get("username"), result.get("password"));
		this.enterLocalPassiveMode();
		this.setFileType(FTP.BINARY_FILE_TYPE);
	}
	
	/**
	 * 关闭FTP链接
	 */
	public void ftpDisConnect()throws IOException{
		this.disconnect();
	}
	
	/**
	 * 切换目录
	 */
	public void changeDirectory(String url)throws IOException{
		
		String folders[] = url.split("/");
		for (int j = 0; j < folders.length; j++) {
			if (!this.changeWorkingDirectory(folders[j])) {
				if (!this.makeDirectory(folders[j])) {
					try {
						throw new Exception("创建目录失败");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				this.changeWorkingDirectory(folders[j]);
			}
		}
	}
}
