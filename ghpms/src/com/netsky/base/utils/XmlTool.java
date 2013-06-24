package com.netsky.base.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.utils.Regex;

public class XmlTool {
	/**
	 * 处理xml 模板，生成目标xml文件
	 * 
	 * @param pathname
	 *            模板文件名(完整路径)
	 * @param dataMap
	 *            key:td00(与模板entity:td00.id中的td00对应);value:Td00_gcxx的一个实例
	 * @return String
	 */
	public static String xmlTool(String pathname, Map dataMap) {
		String responseXml = convertUtil.toString(getXmlModule(pathname));
		Map<String, String> paramMap = new HashMap<String, String>();
		String[] matchedStrs = Regex.getStringsByRegex(responseXml, "\\[[^\\]]*\\]");
		String fail = "";
		for (String string : matchedStrs) {
			paramMap.put(string, "");
			String matcher = string.substring(1, string.length() - 1);
			if (matcher.trim().toLowerCase().startsWith("entity:")) {
				String entity = matcher.substring(7).trim();
				if (entity.indexOf(".") != -1) {
					String entityClass = entity.substring(0, entity.indexOf("."));
					String entityName = entity.substring(entity.indexOf(".") + 1);
					Object o = dataMap.get(entityClass);
					if (o != null) {
						Method[] methods = o.getClass().getDeclaredMethods();
						for (Method method : methods) {
							if (method.getName().toLowerCase().equals("get" + entityName)) {
								try {
									Object result = method.invoke(o);
									paramMap.put(string, result == null ? "" : result.toString());
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					} else {
						fail = "找不到工程";
					}
				}
			} else if ("state".equals(matcher)) {
				paramMap.put(string, "SUCCESS");
			} else if ("time".equals(matcher)) {
				paramMap.put(string, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).replace(" ", "T"));
			}
		}
		if (!"".equals(fail)) {
			paramMap.put("[state]", "f");
			paramMap.put("[fail]", fail);
		}
		for (String key : paramMap.keySet()) {
			responseXml = responseXml.replace(key, paramMap.get(key));
		}

		responseXml = responseXml.replace("\n\n", "\n");
		return responseXml.trim();
	}

	// 读文件版本二
	public static String getXmlModule(String pathname) {
		StringBuffer xmlModule = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(pathname), "UTF-8"));
			String line = "";
			try {
				while ((line = br.readLine()) != null) {
					if(line.indexOf("<!--")>0 && line.indexOf("--")>0)
						;
					else
						xmlModule.append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return xmlModule.toString();
	}
}
