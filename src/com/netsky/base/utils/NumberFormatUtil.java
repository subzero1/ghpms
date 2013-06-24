package com.netsky.base.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 精确计算基础类
 * 
 * @author Chiang 2009-3-16
 */
public class NumberFormatUtil {

	/**
	 * 默认除法保留小数位数
	 */
	private static final int DEFULT_DIV_SCAL = 2;

	/**
	 * 默认四舍五入保留小数位数 2
	 */
	private static final int DEFULT_ROUND_SCAL = 2;

	private NumberFormatUtil() {

	}
	
	private static  char HanDigiStr[] = new char[] { '零', '壹', '贰', '叁',
			'肆', '伍', '陆', '柒', '捌', '玖' };

	private static String HanDiviStr[] = new String[] { "", "拾", "佰",
			"仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟",
			"亿", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万",
			"拾", "佰", "仟" };	

	/**
	 * 加法运算.
	 * 
	 * @param number1
	 *            被加数
	 * @param number2
	 *            加数
	 * @return double 两个参数的和
	 */
	public static double addToDouble(double number1, double number2) {
		return addToDouble(Double.toString(number1), Double.toString(number2));
	}

	/**
	 * 加法运算.
	 * 
	 * @param number1
	 *            被加数
	 * @param number2
	 *            加数
	 * @return double 两个参数的和
	 */
	public static double addToDouble(String number1, String number2) {
		return new BigDecimal(number1).add(new BigDecimal(number2)).doubleValue();
	}

	/**
	 * 加法运算.
	 * 
	 * @param number1
	 *            被加数
	 * @param number2
	 *            加数
	 * @return String 两个参数的和
	 */
	public static String addToString(String number1, String number2) {
		return new BigDecimal(number1).add(new BigDecimal(number2)).toString();
	}

	/**
	 * 加法运算.
	 * 
	 * @param number1
	 *            被加数
	 * @param number2
	 *            加数
	 * @return String 两个参数的和
	 */
	public static String addToString(double number1, double number2) {
		return addToString(Double.toString(number1), Double.toString(number2));
	}

	/**
	 * 减法运算.
	 * 
	 * @param number1
	 *            被减数
	 * @param number2
	 *            减数
	 * @return double 两个参数的差
	 */
	public static double subToDouble(String number1, String number2) {
		return new BigDecimal(number1).subtract(new BigDecimal(number2)).doubleValue();
	}

	/**
	 * 减法运算.
	 * 
	 * @param number1
	 *            被减数
	 * @param number2
	 *            减数
	 * @return double 两个参数的差
	 */
	public static double subToDouble(double number1, double number2) {
		return subToDouble(Double.toString(number1), Double.toString(number2));
	}

	/**
	 * 减法运算.
	 * 
	 * @param number1
	 *            被减数
	 * @param number2
	 *            减数
	 * @return String 两个参数的差
	 */
	public static String subToString(String number1, String number2) {
		return new BigDecimal(number1).subtract(new BigDecimal(number2)).toString();
	}

	/**
	 * 减法运算.
	 * 
	 * @param number1
	 *            被减数
	 * @param number2
	 *            减数
	 * @return String 两个参数的差
	 */
	public static String subToString(double number1, double number2) {
		return subToString(Double.toString(number1), Double.toString(number2));
	}

	/**
	 * 乘法运算.
	 * 
	 * @param number1
	 *            被乘数
	 * @param number2
	 *            乘数
	 * @return double 两个参数的积
	 */
	public static double mulToDouble(String number1, String number2) {
		return new BigDecimal(number1).multiply(new BigDecimal(number2)).doubleValue();
	}

	/**
	 * 乘法运算.
	 * 
	 * @param number1
	 *            被乘数
	 * @param number2
	 *            乘数
	 * @return double 两个参数的积
	 */
	public static double mulToDouble(double number1, double number2) {
		return mulToDouble(Double.toString(number1), Double.toString(number2));
	}

	/**
	 * 乘法运算.
	 * 
	 * @param number1
	 *            被乘数
	 * @param number2
	 *            乘数
	 * @return String 两个参数的积
	 */
	public static String mulToString(String number1, String number2) {
		return new BigDecimal(number1).multiply(new BigDecimal(number2)).toString();
	}

	/**
	 * 乘法运算.
	 * 
	 * @param number1
	 *            被乘数
	 * @param number2
	 *            乘数
	 * @return String 两个参数的积
	 */
	public static String mulToString(double number1, double number2) {
		return mulToString(Double.toString(number1), Double.toString(number2));
	}

	/**
	 * 除法运算，默认保留小数点后2位 DEFULT_DIV_SCAL = 2.
	 * 
	 * @param number1
	 *            被除数
	 * @param number2
	 *            除数
	 * @return String
	 */
	public static String divToString(String number1, String number2) {
		return new BigDecimal(number1).divide(new BigDecimal(number2), DEFULT_DIV_SCAL, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * 除法运算，默认保留小数点后2位 DEFULT_DIV_SCAL = 2.
	 * 
	 * @param number1
	 *            被除数
	 * @param number2
	 *            除数
	 * @return String
	 */
	public static String divToString(double number1, double number2) {
		return divToString(Double.toString(number1), Double.toString(number2));
	}

	/**
	 * 除法运算，默认保留小数点后2位 DEFULT_DIV_SCAL = 2.
	 * 
	 * @param number1
	 *            被除数
	 * @param number2
	 *            除数
	 * @return double
	 */
	public static double divToDouble(String number1, String number2) {
		return new BigDecimal(number1).divide(new BigDecimal(number2), DEFULT_DIV_SCAL, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 除法运算，默认保留小数点后2位 DEFULT_DIV_SCAL = 2.
	 * 
	 * @param number1
	 *            被除数
	 * @param number2
	 *            除数
	 * @return double
	 */
	public static double divToDouble(double number1, double number2) {
		return divToDouble(Double.toString(number1), Double.toString(number2));
	}

	/**
	 * 除法运算，保留scal位小数点
	 * 
	 * @param number1
	 *            被除数
	 * @param number2
	 *            除数
	 * @param scal
	 *            保留小数点位数
	 * @return double
	 */
	public static double divToDouble(String number1, String number2, int scal) {
		return new BigDecimal(number1).divide(new BigDecimal(number2), scal, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 除法运算，保留scal位小数点
	 * 
	 * @param number1
	 *            被除数
	 * @param number2
	 *            除数
	 * @param scal
	 *            保留小数点位数
	 * @return double
	 */
	public static double divToDouble(double number1, double number2, int scal) {
		return divToDouble(Double.toString(number1), Double.toString(number2), scal);
	}

	/**
	 * 除法运算，保留scal位小数点
	 * 
	 * @param number1
	 *            被除数
	 * @param number2
	 *            除数
	 * @param scal
	 *            保留小数点位数
	 * @return String
	 */
	public static String divToString(String number1, String number2, int scal) {
		return new BigDecimal(number1).divide(new BigDecimal(number2), scal, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * 除法运算，保留scal位小数点
	 * 
	 * @param number1
	 *            被除数
	 * @param number2
	 *            除数
	 * @param scal
	 *            保留小数点位数
	 * @return String
	 */
	public static String divToString(double number1, double number2, int scal) {
		return divToString(Double.toString(number1), Double.toString(number2), scal);
	}

	/**
	 * 默认保留小数点后两位 DEFULT_DIV_SCAL = 2
	 * 
	 * @param number
	 * @return String
	 */
	public static String roundToString(String number) {
		return new BigDecimal(number).divide(new BigDecimal("1"), DEFULT_ROUND_SCAL, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * 默认保留小数点后两位 DEFULT_DIV_SCAL = 2
	 * 
	 * @param number
	 * @param str
	 *            number为0时返回字符串
	 * @return String
	 */
	public static String roundToString(String number, String str) {
		if (number != null && Double.parseDouble(number) != 0.0)
			return new BigDecimal(number).divide(new BigDecimal("1"), DEFULT_ROUND_SCAL, BigDecimal.ROUND_HALF_UP).toString();
		else
			return str;
	}

	/**
	 * 默认保留小数点后两位 DEFULT_DIV_SCAL = 2
	 * 
	 * @param number
	 * @return String
	 */
	public static String roundToString(double number) {
		return roundToString(Double.toString(number));
	}

	/**
	 * 默认保留小数点后两位 DEFULT_DIV_SCAL = 2
	 * 
	 * @param number
	 * @param str
	 *            number为0时返回字符串
	 * @return String
	 */
	public static String roundToString(double number, String str) {
		if (number != 0.0)
			return roundToString(Double.toString(number));
		else
			return str;
	}

	/**
	 * 默认保留小数点后两位 DEFULT_DIV_SCAL = 2
	 * 
	 * @param number
	 * @return String
	 */
	public static String roundToString(Double number) {
		if (number != null)
			return roundToString(number.toString());
		else
			return roundToString("0");
	}

	/**
	 * 默认保留小数点后两位 DEFULT_DIV_SCAL = 2
	 * 
	 * @param number
	 * @param str
	 *            当number为空或者为0时返回字符
	 * @return String
	 */
	public static String roundToString(Double number, String str) {
		if (number != null && number.doubleValue() != 0.0)
			return roundToString(number.toString());
		else
			return str;
	}

	/**
	 * 默认保留小数点后两位 DEFULT_DIV_SCAL = 2
	 * 
	 * @param number
	 * @return double
	 */
	public static double roundToDouble(String number) {
		return new BigDecimal(number).divide(new BigDecimal("1"), DEFULT_ROUND_SCAL, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 默认保留小数点后两位 DEFULT_DIV_SCAL = 2
	 * 
	 * @param number
	 * @return String
	 */
	public static double roundToDouble(double number) {
		return roundToDouble(Double.toString(number));
	}

	/**
	 * 默认保留小数点后两位 DEFULT_DIV_SCAL = 2
	 * 
	 * @param number
	 * @return String
	 */
	public static double roundToDouble(Double number) {
		if (number != null)
			return roundToDouble(number.toString());
		else
			return 0;
	}

	/**
	 * 保留小数点后scal位
	 * 
	 * @param number
	 * @param scal
	 * @return String
	 */
	public static String roundToString(String number, int scal) {
		return new BigDecimal(number).divide(new BigDecimal("1"), scal, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * 保留小数点后scal位
	 * 
	 * @param number
	 * @param scal
	 * @return String
	 */
	public static String roundToString(double number, int scal) {
		return roundToString(Double.toString(number), scal);
	}

	/**
	 * 保留小数点后scal位
	 * 
	 * @param number
	 * @param scal
	 * @return String
	 */
	public static String roundToString(Double number, int scal) {
		if (number != null)
			return roundToString(number.toString(), scal);
		else
			return roundToString("0", scal);
	}

	/**
	 * 保留小数点后scal位
	 * 
	 * @param number
	 * @param scal
	 * @return double
	 */
	public static double roundToDouble(String number, int scal) {
		return new BigDecimal(number).divide(new BigDecimal("1"), scal, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 保留小数点后scal位
	 * 
	 * @param number
	 * @param scal
	 * @return String
	 */
	public static double roundToDouble(double number, int scal) {
		return roundToDouble(Double.toString(number), scal);
	}

	/**
	 * 保留小数点后scal位
	 * 
	 * @param number
	 * @param scal
	 * @return String
	 */
	public static double roundToDouble(Double number, int scal) {
		if (number != null)
			return roundToDouble(number.toString(), scal);
		else
			return 0;
	}

	/**
	 * 取整,不4舍5入
	 * 
	 * @param number
	 * @return String
	 */
	public static String subScal(String number) {
		if (number != null) {
			return number = number.substring(0, number.indexOf("."));
		} else {
			return "";
		}
	}
	

	/**
	 * 数字转大写（汉字）
	 * @param number
	 * @return
	 */
	private static String toCapitalization(String source) {
	
		char JF[] = new char[] { '角', '分' };
		
		char[] cs = source.toCharArray();
		StringBuffer sb = new StringBuffer();
		int len = cs.length;
		int zerolen = 0;
		for (int i = cs.length; i > 0; i--) {
			int num = Integer.parseInt(cs[len - i] + "");
			if (num != 0) {
				if (zerolen != 0) {
					sb.append(HanDigiStr[0]);
				}
				sb.append(HanDigiStr[num]).append(HanDiviStr[i - 1]);
				zerolen = 0;
			} else {
				zerolen++;
				String s = HanDiviStr[i - 1];
				if (zerolen % 4 == 0 && !"亿".equals(s)) {
					continue;
				}
				if ((i - 1) % 4 == 0) {
					sb.append(HanDiviStr[i - 1]);
					zerolen = 0;
				}
			}
		}
		if (sb.length() == 0) {
			sb.append("零");
		}
		return sb.toString();
	}

	/**
	 * 人民币小写转大写
	 * @param val
	 * @return
	 */
	public static String NumToRMBStr(Double val) {
		String SignStr = "";
		String TailStr = "";
		long fraction, integer;
		
		if (val < 0) {
			val = -val;
			SignStr = "负";
		}
		if (val > 99999999999999.999 || val < -99999999999999.999)
			return "数值位数过大!";
		// 四舍五入到分
		long temp = Math.round(val * 100);
		integer = temp / 100;
		fraction = temp % 100;
		int jiao = (int) (fraction / 10);
		int fen = (int)(fraction % 10);
		if (fraction == 0) {
			TailStr = "整";
		} else {
			if (jiao >0||fen > 0){
				TailStr += HanDigiStr[jiao];
				TailStr += "角";
			}
			
			if(fen > 0){
				TailStr += HanDigiStr[fen];
				TailStr += "分";
			}
		}

		// 下一行可用于非正规金融场合，0.03只显示“叁分”而不是“零元叁分”
		if( integer ==0 ) return SignStr+TailStr;

		return SignStr + toCapitalization(String.valueOf(integer)) + "元"
				+ TailStr;
	}
		
	/**
	 * 判断字符串是不是为数字
	 * @param val
	 * @return
	 */
	public static boolean isNumeric(String str){
		for (int i = str.length();--i>=0;){   
		   if (!Character.isDigit(str.charAt(i))){
			   return false;
		   }
		}
		return true;
	}
	
	/**
	 * 阿拉伯数字转换成中国数字
	 * @param val
	 * @return
	 */
	public static String alb_to_cha(String str){
		 HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
		 hashMap.put(0, "零");
		 hashMap.put(1, "一");
		 hashMap.put(2, "二");
		 hashMap.put(3, "三");
		 hashMap.put(4, "四");
		 hashMap.put(5, "五");
		 hashMap.put(6, "六");
		 hashMap.put(7, "七");
		 hashMap.put(8, "八");
		 hashMap.put(9, "九");
		 StringBuffer cha_num = new StringBuffer();

		 for (int i=0;i<str.length();i++) {
			 int a = str.charAt(i);
			 cha_num.append(hashMap.get(a-48));
		 }
		return cha_num.toString();
	}
	
	/**
	 * 生成0到end范围内number个随机数无重复
	 * @param val
	 * @return List<Integer>
	 */
	public static List<Integer> random(int end,int number){
		List<Integer> nums = new ArrayList<Integer>();
		while(nums.size()<number){
			int str = (int)(Math.random()*end);
			if(!nums.contains(str)){
				nums.add(str);
			}
		}
		return nums;
	}
	/**
	 * Double 四舍五入取整
	 * @param num 需要格式化的数字
	 * @return str 小数点后位数
	 */
	public static double myround(double num,int str){
		BigDecimal b = new BigDecimal(num);
		num = b.setScale(str, BigDecimal.ROUND_HALF_UP).doubleValue();
		return num;
	}
}
