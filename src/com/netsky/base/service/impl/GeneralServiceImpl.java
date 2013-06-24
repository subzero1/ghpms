package com.netsky.base.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import com.netsky.base.service.others.GeneralService;
import sun.net.ftp.FtpClient;


@Service("generalService")
public class GeneralServiceImpl implements GeneralService {
	/**
	 * Ĭ��ftp�����ļ�·��
	 */
	private static String ftpConfigFile = "/WEB-INF/ftpConfig/ftpConfig.xml";
	
	public boolean deleteFtpFile(String url,String webRoot) throws Exception{
		
		if(url == null || "".equals(url)){
			return false;
		}
		Map<String, String> ftpConfig = this.getFtpConfig(webRoot);
		FtpClient client = new FtpClient();
		client.openServer(ftpConfig.get("address"));
		client.login(ftpConfig.get("username"), ftpConfig.get("password"));

		try {
			client.sendServer("dele " + url + "\r\n");
			client.readServerResponse();
			client.closeServer();

		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean deleteFtpFiles(String[] urls,String webRoot) throws Exception{
		if(urls == null || urls.length ==0){
			return false;
		}
		Map<String, String> ftpConfig = this.getFtpConfig(webRoot);
		FtpClient client = new FtpClient();
		client.openServer(ftpConfig.get("address"));
		client.login(ftpConfig.get("username"), ftpConfig.get("password"));

		try {
			for(String url:urls){
				client.sendServer("dele " + url + "\r\n");
				client.readServerResponse();
			}
			client.closeServer();

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private Map<String, String> getFtpConfig(String webRoot) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		/**
		 * ͨ��xml�ļ���ȡ������Ϣ
		 */
		String filePath = webRoot + ftpConfigFile;
		File file = new File(filePath);
		if (!file.exists()) {
			throw new Exception("�����ļ������ڣ�·����" + filePath);
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
			throw new Exception("�����xml��ʽ��·����" + filePath + "����:" + ex);
		}
		return result;
	}

}
