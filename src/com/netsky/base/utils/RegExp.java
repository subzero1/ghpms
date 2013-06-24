package com.netsky.base.utils;

import org.apache.oro.text.regex.*;
import java.util.Vector;

/**
 * @description:
 * 
 * @class name:com.netsky.base.utils.RegExp
 * 
 * @author lee.xiangyu 2006-12-6
 */
public class RegExp {
	
	private PatternCompiler compiler = null;

	private Pattern pattern = null;

	private PatternMatcher matcher = null;

	public RegExp() {
		compiler = new Perl5Compiler();
		matcher = new Perl5Matcher();
	}

	/**
	 * @param regExp
	 * @param str
	 * @return boolean 判断字符串与正则表达式是否匹配
	 */
	public boolean match(String regExp, String str) {
		try {
			pattern = compiler.compile(regExp);
		} catch (MalformedPatternException e) {
			System.out.println(e + "error");
		}
		return matcher.matches(str, pattern);
		
	}

	/**
	 * @param regExp
	 * @param str
	 * @return boolean 判断字符串与正则表达式是否匹配
	 */
	public boolean contain(String regExp, String str) {
		try {
			pattern = compiler.compile(regExp);
		} catch (MalformedPatternException e) {
			System.out.println(e + "error");
		}
		return matcher.contains(str, pattern);
		
	}

	
	/**
	 * 如果字符串与正则表达式匹配，则返回想要提取的值
	 * 
	 * @param regExp
	 * @param str
	 * @return
	 */
	public String pickup(String regExp, String str) {
		try {
			pattern = compiler.compile(regExp);
		} catch (MalformedPatternException e) {
			System.out.println(e);
		}
		if (matcher.contains(str, pattern)) {
			MatchResult result = matcher.getMatch();
			return result.group(1);
		}
		return null;
	}
	
	/**
	 * 如果字符串与正则表达式匹配，则返回想要提取的全部值
	 * 
	 * @param regExp
	 * @param str
	 * @return
	 */
	public Vector pickupAll(String regExp, String str,int group_num) {
		try {
			pattern = compiler.compile(regExp);
			
		} catch (MalformedPatternException e) {
			System.out.println(e);
		}
		
		PatternMatcherInput input = new PatternMatcherInput(str);
		Vector vector = new Vector();
		
		while (matcher.contains(input, pattern)) {
			MatchResult result = matcher.getMatch();
			vector.add(result.group(group_num));
		}
		return vector;
	}

	public static void main(String[] args) {

		try {
			//String yy = "dds<img src=\"/crht_bms/download.do?slave_id=28\" alt=\"\" />sdfew<img src=\"/crht_bms/download.do?slave_id=29\" alt=\"\" />few";
			//Vector v = new RegExp().pickupAll("<img[\\s+[a-z]+=\"[^\"]\"]*\\s+src=\"/[a-zA-Z]+_[a-zA-Z]+/download.do\\?slave_id=(\\d+)\"[\\s+[a-z]+=\"[^\"]\"]*\\s+/>",yy);
			
			String yy = "dds<img src=\"/crht_bms/download.do?slave_id=28\" alt=\"\" />sdfew<img src=\"/crht_bms/download.do?slave_id=29\" alt=\"\" />few";
			//Vector v = new RegExp().pickupAllGroup("<img(\\s+[a-z]+=\"[^\"]*\")*\\s+src=\"/[a-zA-Z]+_[a-zA-Z]+/download.do\\?slave_id=(\\d+)\"(\\s+[a-z]+=\"[^\"]*\")*\\s+/>",yy);
			//System.out.println(v.size());
			//yy="{id:[id],name:[name],sex:sex}";
			//Vector v = new RegExp().pickupAll("\\[([a-zA-Z0-9]+)\\]+",yy,1);
			//System.out.println(v.size());
			//double tmp_xs = Double.parseDouble(new RegExp().pickup(".+(\\d*\\.?\\d*)",yy));
			//System.out.println(tmp_xs);
			//System.out.println(new RegExp().pickup(".+(\\d*)",yy));
//			String xx = "技工总工日×34.00";
//
//			System.out.println(new RegExp().pickup(".+×((\\d)*\\.?(\\d)*)%?",
//					xx));
//
//			String aa = "Content-Disposition: form-data; name=\"aa\"";
//			System.out.println(new RegExp().pickup(".+name=\"((.)+)\".*", aa));
//			String aaa = "<html><head><title>name<title/></head></html>";
//			System.out.println(new RegExp().pickup("(<.+>+)name<.+>+", aaa));
//			String bb = "2008-01-02 01:34 ";
//			System.out.println(new RegExp().pickup(
//					"(\\d{4}(\\-\\d{1,2}){2}\\s+\\d{1,2}:\\d{1,2})", bb));
//			String bb2 = "2008-01-02 1:34";
//			System.out.println(new RegExp().match("(\\d{4}(\\-\\d{1,2}){2})",
//					bb2));
//			String bb3 = "09/01/02";
//			System.out.println(new RegExp().match("(\\d{1,2}(/\\d{1,2}){2})",
//					bb3));
			
			//if(t_content.length() == 8 && t_content.indexOf("201") == 0 && t_content.indexOf(".") != -1){
			String a = "20131212";
			//System.out.println(a.length());
			//System.out.println(a.indexOf("201"));
			//System.out.println(a.indexOf("."));
			System.out.println(a.substring(0,4)+"-"+a.substring(4,6)+"-"+a.substring(6,8));
			
			System.out.println(new RegExp().match("\\d+\\.?\\d*", "12.58"));
			
			String b = "南京电信_";
			System.out.println(b.substring(b.indexOf("_")+1,b.length())+"2");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
