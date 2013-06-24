package com.netsky.base.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.io.*;


import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.netsky.base.baseObject.SharedCfgData;
import com.netsky.base.service.LoadShareDataService;

/**
 * @description:
 * 加载公用数据
 * @class name:com.netsky.base.controller.LoadShareServLet
 * @author wind Jan 20, 2010
 */
public class LoadShareServLet implements Servlet {

	private  Logger log = Logger.getLogger(this.getClass());
	
	public void destroy() {
	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public String getServletInfo() {
		return null;
	}

	public void init(ServletConfig arg0) throws ServletException {
		
		log.warn("init servlet ");
		/**
		 * 初始化配置文件路径[WEB-INF/autoConfig/*.*]
		 */
		String file_separator = System.getProperty("file.separator");
		Map<String,String> map = new HashMap<String,String>();
		String cfgUrl = arg0.getServletContext().getRealPath(file_separator + "WEB-INF" + file_separator + "autoConfig" + file_separator);
		if(cfgUrl != null){
			File file = new File(cfgUrl);
			if(file.isDirectory()){
				String[] filelist = file.list();
				for(int i = 0;i < filelist.length; i ++){
					if(filelist[i].indexOf(".") != -1){
						map.put(filelist[i], cfgUrl + file_separator +filelist[i]);
					}
				}
				SharedCfgData.setMap(map);
			}
		}
		log.warn("end init servlet ");
		
	}

	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
	}

}
