package com.netsky.base.utils;
import java.util.Calendar;
import com.netsky.base.utils.StringFormatUtil;
import java.util.Date;

/**
 * @description:
 * 
 * @class name:com.netsky.base.utils.DateGetUtil
 *
 * @author lee.xiangyu 2010-1-21
 */
public class DateGetUtil {

	/**
	 * @param args
	 */
	private static int year;

	private static int month;

	private static int day;

	private static int week;

	private static int hour;

	private static int minute;

	private static int second;

	/**
	 * @return current year;
	 */
	public static int getYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(cal.YEAR);
	}
	
	/**
	 * @return 指定 year;
	 */
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(cal.YEAR);
	}

	/**
	 * @return current month;
	 */
	public static int getMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(cal.MONTH) + 1;
	}

	/**
	 * @return current day;
	 */
	public static int getDay() {
		Calendar cal = Calendar.getInstance();
		return cal.get(cal.DAY_OF_MONTH);
	}

	/**
	 * @return current hour;
	 */
	public static int getHour() {
		Calendar cal = Calendar.getInstance();
		return cal.get(cal.HOUR_OF_DAY);
	}

	/**
	 * @return current minute;
	 */
	public static int getMinute() {
		Calendar cal = Calendar.getInstance();
		return cal.get(cal.MINUTE);
	}

	public int getSecond() {
		Calendar cal = Calendar.getInstance();
		return cal.get(cal.SECOND);
	}

	/**
	 * @return current week;
	 */
	public static int getWeek() {
		Calendar cal = Calendar.getInstance();
		return cal.get(cal.DAY_OF_WEEK);
	}

	/**
	 * @return current date;
	 */
	public static String getCurDate() {
		year = getYear();
		month = getMonth();
		day = getDay();
		return year + "-" + StringFormatUtil.getCompleteString(""+month,2) + "-" + StringFormatUtil.getCompleteString(""+day,2);
	}

	/**
	 * @return current date;
	 */
	public static String getCurTime() {
		year = getYear();
		month = getMonth();
		day = getDay();
		hour = getHour();
		minute = getMinute();
		return year + "-" + StringFormatUtil.getCompleteString(""+month,2) + "-" + StringFormatUtil.getCompleteString(""+day,2) + " " + StringFormatUtil.getCompleteString(""+hour,2) + ":" + StringFormatUtil.getCompleteString(""+minute,2);
	}
	
	/**
	 * @return current date;
	 */
	public static Date getCurDate2() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	/**
	 * method:addDay
	 * @param sourceDate 源日期
	 * @param days 增加的天数
	 * @return Date
	 */
	public static Date addDay(Date sourceDate,int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(sourceDate);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}
	
	/**
	 * method:addDay
	 * @param sourceDate 源日期
	 * @param days 增加的天数
	 * @return Date
	 */
	public static Date addMonth(Date sourceDate,int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(sourceDate);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}
	
	/**
	 * method:addDay
	 * @param sourceDate 源日期
	 * @param days 增加的天数
	 * @return Date
	 */
	public static Date addYear(Date sourceDate,int years) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(sourceDate);
		cal.add(Calendar.YEAR, years);
		return cal.getTime();
	}
	
	/**
	 * @param smallDate 较小日期
	 * @param bigDate 较大日期
	 * @param pattern 返回模式 year|month|day|hour|minute 默认是day
	 * @return 时间间隔
	 */
	public static Long dateDiff(Date smallDate,Date bigDate,String pattern) {
		
		Calendar s_cal = Calendar.getInstance();
		Calendar e_cal = Calendar.getInstance();
		Long interval = new Long(0);
		if(smallDate == null || bigDate == null)
			return new Long(0);
			
		s_cal.setTime(smallDate);
		e_cal.setTime(bigDate);
		
		if(pattern == null)
			pattern = "DAY";
		
		if(pattern.toUpperCase().equals("MINUTE")){
			interval = (e_cal.getTimeInMillis() - s_cal.getTimeInMillis()) / (1000 * 60) ;
		}
		else if(pattern.toUpperCase().equals("HOUR")){
			interval = (e_cal.getTimeInMillis() - s_cal.getTimeInMillis()) / (1000 * 60 * 60) ;
		}
		else if(pattern.toUpperCase().equals("DAY")){
			interval = (e_cal.getTimeInMillis() - s_cal.getTimeInMillis()) / (1000 * 60 * 60 * 24) ;
		}
		else if(pattern.toUpperCase().equals("MONTH")){
			interval = (e_cal.getTimeInMillis() - s_cal.getTimeInMillis()) / (1000 * 60 * 60 * 24 * 30) ;
		}
		else if(pattern.toUpperCase().equals("YEAR")){
			interval = (e_cal.getTimeInMillis() - s_cal.getTimeInMillis()) / (1000 * 60 * 60 * 24 * 30 * 365) ;
		}	
		return interval;
	}


	/**
	 * @return current jd;
	 */
	public static String getCurQuarter() {

		month = getMonth();
		if (month == 1 || month == 2 || month == 3)
			return "1";

		if (month == 4 || month == 5 || month == 6)
			return "2";

		if (month == 7 || month == 8 || month == 9)
			return "3";

		if (month == 10 || month == 11 || month == 12)
			return "4";
		return "-1";
	}
	
	public static void main(String[] args){
		System.out.println(addYear(new Date(),1));
		//Object o = null;
		//System.out.println(getCurTime());
		//o.toString();
	}
}
