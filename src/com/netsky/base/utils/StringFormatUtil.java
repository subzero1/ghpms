package com.netsky.base.utils;

/**
 * 格式化字符串
 * 
 * @author Chiang
 */
	public class StringFormatUtil {

	/**
	 * 格式化字符串,默认返回""
	 * 
	 * @param str
	 */
	public static String format(String str) {
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}

	/**
	 * 格式化字符串,默认返回""
	 * 
	 * @param str
	 */
	public static String format(Integer str) {
		if (str == null) {
			return "";
		} else {
			return str.toString();
		}
	}

	/**
	 * 格式化字符串,默认返回""
	 * 
	 * @param str
	 */
	public static String format(Double str) {
		if (str == null) {
			return "";
		} else {
			return str.toString();
		}
	}

	/**
	 * 格式化字符串,默认返回defaultStr
	 * 
	 * @param str
	 * @param defaultStr
	 */
	public static String format(Integer str, String defaultStr) {
		if (str == null) {
			return defaultStr;
		} else {
			return str.toString();
		}
	}

	/**
	 * 格式化字符串,默认返回defaultStr
	 * 
	 * @param str
	 * @param defaultStr
	 */
	public static String format(Double str, String defaultStr) {
		if (str == null) {
			return defaultStr;
		} else {
			return str.toString();
		}
	}

	/**
	 * 格式化字符串,默认返回defaultStr
	 * 
	 * @param str
	 * @param defaultStr
	 */
	public static String format(String str, String defaultStr) {
		if (str == null || str.trim().equals(""))
			return defaultStr;

		return str;
	}

	/**
	 * method:subString
	 * 
	 * @description 对源字符串截取指定长度,采用了 先加1后减1的方法,即先对截取后 的字节数组[tmp_part_byte]
	 *              加1生成新的字符串[tmp_str] 后再对[tmp_str]截去最后一个字符.
	 * 
	 * @param str
	 *            源字符串
	 * @param length
	 *            指定的长度
	 * @return 截取后的字符串
	 * @throws Exception
	 *             String
	 * @author lee.xiangyu
	 */
	public static String subString(String str, int length) throws Exception {

		byte[] tmp_all_byte = null;
		if (str == null || length < 0) {
			return "";
		}
		tmp_all_byte = str.getBytes();
		if (tmp_all_byte.length <= length) {
			return str;
		} else {
			byte[] tmp_part_byte = new byte[length + 1];
			for (int i = 0; i < length + 1; i++) {
				tmp_part_byte[i] = tmp_all_byte[i];
			}
			String tmp_str = new String(tmp_part_byte, "GBK");
			tmp_str = tmp_str.substring(0, tmp_str.length() - 1);
			return tmp_str;
		}
	}

	/**
	 * method:getStrWithSuffix
	 * 
	 * @param str
	 *            源字符串
	 * @param length
	 *            指定的长度
	 * @param suffix
	 *            后缀
	 * @return
	 * @throws Exception
	 *             String
	 * @author lee.xiangyu
	 */
	public static String getStrWithSuffix(String str, int length, String suffix)
			throws Exception {

		byte[] tmp_all_byte = null;
		if (str == null || length < 0) {
			return "";
		}
		tmp_all_byte = str.getBytes();
		if (tmp_all_byte.length <= length) {
			return str;
		} else {
			return subString(str, length) + suffix;
		}
	}

	/**
	 * method:getStrWithSuffix
	 * 
	 * @param str
	 *            源字符串
	 * @param length
	 *            指定的长度
	 * @return
	 * @throws Exception
	 *             String
	 * @author lee.xiangyu
	 */
	public static String getStrWithSuffix(String str, int length)
			throws Exception {
		return getStrWithSuffix(str, length, "...");
	}

	/**
	 * method:toUtf8String
	 * 
	 * @param s
	 * @return String
	 */
	public static String toUtf8String(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * method:getStrWithComma
	 * 
	 * @param str
	 * @return String
	 */
	public static String getStrWithComma(String str) {

		int index = str.lastIndexOf(".");
		if (index < 0)
			index = str.length();
		for (int i = index; i > 3; i -= 3) {
			str = str.substring(0, i - 3) + ","
					+ str.substring(i - 3, str.length());
		}
		return str;
	}

	
		/**
		 * method:four_char
		 * @param str
		 * @param _num
		 * @return String
		 */
	public static String getCompleteString(String str,int _num){
		
			StringBuffer num=new StringBuffer("");
		    if(str==null||str.equals("")){
		       for(int k=0;k<_num;k++){num.append("0"); }
		          return num.toString();
		    } 
		    
		    for(int i=0;i<_num-str.length();i++){
		      num.append("0");	
		    }
		    num.append(str);
		    return num.toString();
		}
	
	/**
	 *生成诸如类似于['aa','bb','cc']
	 * @param sourceStr [aa,bb,cc || aa,'bb',cc, ...]
	 * @return 规则的字符串
	 */
	public static String formatStrWithName(String sourceStr){
		
		if(sourceStr == null || sourceStr.equals(""))
			return "'x'";
		
		/**
		 * 判断最后一个字符是否为逗号
		 */
		int s = sourceStr.lastIndexOf(",");
		if(sourceStr.length()-1 == s){
			sourceStr = sourceStr.substring(0,sourceStr.length()-1);
		}
		
		/**
		 * 处理字符串中的引号
		 */
		sourceStr = sourceStr.replace("'", "");
		sourceStr = "'" + sourceStr.replace(",", "','") + "'";
		
		/**
		 * 处理字符串中的空格
		 */
		sourceStr = sourceStr.replace(" ", "");
		
		return sourceStr;
	}
	
	/**
	 *生成诸如类似于[1,2,3]
	 * @param sourceStr [1,2,3, || 1,'2',3, ...]
	 * @return 规则的字符串
	 */
	public static String formatStrWithID(String sourceStr){
		
		if(sourceStr == null || sourceStr.equals(""))
			return "'x'";
		
		/**
		 * 判断最后一个字符是否为逗号
		 */
		int s = sourceStr.lastIndexOf(",");
		if(sourceStr.length()-1 == s){
			sourceStr = sourceStr.substring(0,sourceStr.length()-1);
		}
		
		/**
		 * 处理字符串中的引号
		 */
		sourceStr = sourceStr.replace("'", "");
		
		/**
		 * 处理字符串中的空格
		 */
		sourceStr = sourceStr.replace(" ", "");
		
		return sourceStr;
	}
		
	public static void main(String[] args) {
		try {
			Class clazz = Class.forName("com.netsky.base.service.QueryService");
			System.out.println(clazz.getSimpleName());
		} catch (Exception e) {

		}
	}
}
