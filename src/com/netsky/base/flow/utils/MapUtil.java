package com.netsky.base.flow.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.netsky.base.dataObjects.Ta03_user;

/**
 * @description:
 * map 工具类
 * @class name:com.netsky.base.flow.utils.MapUtil
 * @author wind Jan 15, 2010
 */
public class MapUtil {
	/**
	 * 日志处理类
	 */
	private static Logger log = Logger.getLogger("com.netsky.base.flow.utils.MapUtils");
	
    /**
     * 指定分隔符的，串，加载到map中去
     * method:load
     * @param paramMap
     * @param arg
     * @param outSplitChar
     * @param inSplitChar
     * @return boolean
     */
    public static boolean load(Properties paraPro,String arg, String outSplitChar, String inSplitChar) {
        if (arg == null) {
            return false;
        }

        //去掉字符串中空格
        arg.replaceAll("\t", "");
        arg.replaceAll("\r", "");
        arg.replaceAll("\n", "");
        arg.replaceAll("\f", "");

        String[] arrStr = arg.split(outSplitChar);
        String pair = null;
        for (int i = 0; i < arrStr.length; i++) {
            pair = arrStr[i];
            String[] arrTmp = pair.split(inSplitChar);
            if (arrTmp.length == 2) {
            	paraPro.setProperty(arrTmp[0].trim(), arrTmp[1].trim());
            }
        }
        return true;
    }

    /**
     *  通过属性串加载属性（属性串格式 ： key=value&key1=value1&key2=value2)
     * @param batchPara
     * @return
     */
    public static boolean load(Map paraMap,String batchPara) {
        if (batchPara == null) {
            return false;
        }

        //去掉字符串中空格
        batchPara.replaceAll("\t", "");
        batchPara.replaceAll("\r", "");
        batchPara.replaceAll("\n", "");
        batchPara.replaceAll("\f", "");

        String[] arrStr = batchPara.split("&");
        String pair = null;
        for (int i = 0; i < arrStr.length; i++) {
            pair = arrStr[i];
            String[] arrTmp = pair.split("=");
            if (arrTmp.length == 2) {
            	paraMap.put(arrTmp[0].trim(), arrTmp[1].trim());
            }
        }
        return true;
    }
    
    /**
     * 把request里的内容加载到Map中
     * @param paraMap
     * @param request
     * @return boolean
     */
    @SuppressWarnings("unchecked")
	public  static boolean load(Map<String,Object> paraMap,HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	//把reqeust,Attribute里的值放到paraMap
		for(Enumeration enu= request.getAttributeNames();enu.hasMoreElements();){
			String name = (String)enu.nextElement();
			paraMap.put(name, request.getAttribute(name));
		}
		
		for(Enumeration enu= request.getParameterNames();enu.hasMoreElements();){
			String name = (String)enu.nextElement();
			paraMap.put(name, request.getParameter(name));
		}
		
		//user_id,user_name 里放到paraMap里
		String user_id = request.getParameter("user_id");
		Object obj = session.getAttribute("user");
		if(obj!=null && obj instanceof Ta03_user){
			Ta03_user user = (Ta03_user)obj;
			
			//判断是否为委托用户的工单请求
			if(!String.valueOf(user.getId()).equals(user_id)&& user_id != null){
				if(session.getAttribute("trustUserMap")!= null){
					Map<String,Ta03_user> trustUserMap =(Map<String,Ta03_user>)session.getAttribute("trustUserMap");
					user = trustUserMap.get(user_id);
				}
			}
			paraMap.put("user_id", user.getId());
			paraMap.put("user_name", user.getName());
			paraMap.put("user",user);
		}
		
		//放入用户权限
		obj = session.getAttribute("nodesMap");
		if(obj != null && obj instanceof Map){
			paraMap.put("nodesMap", obj);
		}
		obj = session.getAttribute("rolesMap");
		if(obj != null && obj instanceof Map){
			paraMap.put("rolesMap", obj);
		}
		
		//log.warn(paraMap);
		return false;
    }

    /**
     * 获得由所有键值都为String的键值对，拼接成Url参数串
     * @return
     * 属性串 格式如下 key=value&key=value
     */
    public static String getUrl(Map paraMap) {
		StringBuffer strBuf = new StringBuffer();
		for (Object key : paraMap.keySet()) {
			if (key instanceof String) {
				Object value = paraMap.get(key);
				if (value instanceof String || value instanceof Long || value instanceof Integer) {
					strBuf.append((String) key);
					strBuf.append("=");
					strBuf.append(paraMap.get(key));
				}
			}
		}
		if (strBuf.length() > 0) {
			strBuf.delete(strBuf.lastIndexOf("&"), 0);
		}
		return strBuf.toString();
	}
    
    /**
	 * 指定键,从map中获得键值（值的类型为String,Long,Integer）对，拼接成Url参数串
	 * 
	 * @param paraMap
	 * @param keys
	 * @return String
	 */
	public static String getUrl(Map paraMap, String[] keys) {
		StringBuffer strBuf = new StringBuffer();
		for (String key : keys) {
			if(!paraMap.containsKey(key)){
				continue;
			}
			Object value = paraMap.get(key);
			if (value instanceof String || value instanceof Long || value instanceof Integer) {
				strBuf.append((String) key);
				strBuf.append("=");
				strBuf.append(paraMap.get(key));
				strBuf.append("&");
			}
		}
		if (strBuf.length() > 0) {
			strBuf.delete(strBuf.lastIndexOf("&"), strBuf.length());
		}
		return strBuf.toString();
	}
	
    /**
	 * 把Map 中的键值转换成 Long <br>
	 * 如果为值的类别为Long或者String ,转换返回 <br>
	 * 否则，返回new Long(-1)<br>
	 * 
	 * @param paraMap
	 * @param key
	 * @return Long
	 */
    public static Long getLong(Map paraMap,Object key){
    	if(paraMap.containsKey(key)){
    		if(paraMap.get(key) instanceof Long){
    			return (Long)paraMap.get(key);
    		} else if(paraMap.get(key) instanceof String){
    			String tmpStr = (String)paraMap.get(key);
    			if(tmpStr != null&&tmpStr.matches("^0|[+,-]?[1-9][0-9]{0,17}$")){
    				return new Long(tmpStr);
    			}
    		} else if(paraMap.get(key) instanceof Integer){
    			return new Long((Integer)paraMap.get(key));
    		}
    	}
    	return new Long(-1);
    }
    
    /**
     * 把Map 中的键值转换成 Long
     * <br>如果为值的类别为Long、Integer或者String ,转换返回 
     * <br>否则，返回defaultValue(如果defaultValue 为空返回new Long(-1)
     * @param paraMap
     * @param key
     * @param defaultValue
     * @return Long
     */
    public static Long getLong(Map paraMap,Object key,Long defaultValue){
    	Long result = getLong( paraMap, key);
    	if(defaultValue != null && result == -1){
    		return defaultValue;
    	}
    	return result;
    }
    
    /**
     * 把Map 中的键值转换成 String
     * <br>如果为值的类别为Long、Integer或者String ,转换返回 
     * <br>否则，返回""
     * @param paraMap
     * @param key
     * @return String
     */
    public static String getString(Map paraMap,Object key){
    	if(paraMap.containsKey(key)){
    		if(paraMap.get(key) instanceof String){
    			return (String)paraMap.get(key);
    		} else {
    			if(paraMap.get(key) != null)
    			return paraMap.get(key).toString();
    		}
    	}
    	return "";
    }
    
    /**
     * 把Map 中的键值转换成 String
     * <br>如果为值的类别为Long、Integer或者String ,转换返回 
     * <br>否则，返回defaultValue(如果defaultValue 为空返回"")
     * @param paraMap
     * @param key
     * @param defaultValue
     * @return String
     */
    public static String getString(Map paraMap,Object key,String defaultValue){
    	String result = getString( paraMap, key);
    	if(defaultValue!=null && "".equals(result)){
    		return defaultValue;
    	}
    	return result;
    }    
    
    /**
     * 把Map 中的键值转换成 Integer
     * <br>如果为值的类别为Long或者String ,转换返回 
     * <br>否则，返回new Integer(-1)<br>
     * @param paraMap
     * @param key
     * @return Integer
     */
    public static Integer getInteger(Map paraMap,Object key){
    	if(paraMap.containsKey(key)){
    		if(paraMap.get(key) instanceof Integer){
    			return (Integer)paraMap.get(key);
    		} else if(paraMap.get(key) instanceof String){
    			String tmpStr = (String)paraMap.get(key);
    			if(tmpStr != null&&tmpStr.matches("^0|[+,-]?[1-9][0-9]{0,8}$")){
    				return new Integer(tmpStr);
    			}
    		} 
    	}
    	return new Integer(-1);
    }
    
    /**
     * 把Map 中的键值转换成 Integer
     * <br>如果为值的类别为Long、Integer或者String ,转换返回 
     * <br>否则，返回defaultValue(如果defaultValue 为空返回new Long(-1)
     * @param paraMap
     * @param key
     * @param defaultValue
     * @return Integer
     */
    public static Integer getInteger(Map paraMap,Object key,Integer defaultValue){
    	Integer result = getInteger( paraMap, key);
    	if(defaultValue != null && result == -1){
    		return defaultValue;
    	}
    	return result;
    }    
    

	public static void  main(String arg[]) throws Exception{
//		Map paraMap = new HashMap();
//		paraMap.put("aa","wind");
//		paraMap.put("aa", "aaa");
//		paraMap.put("user_id", "1");
//		List tmpList = new LinkedList();
//		tmpList.add("wind");
//		tmpList.add(0, "wind1");
//		tmpList.add(1,"user");
//		String reg= "^[+,-]?(0|(0\\.[0-9]{1,3})|([1-9][0-9]{0,9}\\.[0-9]{1,3})|[1-9][0-9]{0,8})$";
//		System.out.println(MapUtil.getInteger(paraMap, "user_id"));
//		System.out.println("123.31".matches(reg));
		String ip = InetAddress.getLocalHost().getHostAddress().toString();
			System.out.println(ip);
		}
}
