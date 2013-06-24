package com.netsky.base.taglib.date;

import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * 作者：王龙祥
 * 
 * 日期：2010-05-13
 * 
 * 描述：把天数转换成天、时、分、秒的形式
 */
public class DateTodhms extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 天数
	 */
	private double number;
	
	/**
	 * 精确到 day,hour,minute,second,milliSecond;
	 */
	private String pattern ;

	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
		this.number = number;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	/**
	 * 标记结束
	 */
	public int doEndTag() throws javax.servlet.jsp.JspException {
		try {
			printresult();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
		return EVAL_PAGE;
	}
	
	private void printresult()throws Exception {
		
		if(number==-1){
			pageContext.getOut().println("未超时");
		}else{
			int temp = (int)(number * 86400 * 1000);
			Long ms = new Long(temp);
			int ss = 1000;
			int mi = ss * 60;
			int hh = mi * 60;
			int dd =  hh * 24;
			
			long day = ms/dd;
			long hour = (ms-day*dd)/hh;
			long minute = (ms-day*dd-hour*hh)/mi; 
			long second = (ms-day*dd-hour*hh-minute*mi)/ss; 
			long milliSecond = ms-day*dd-hour*hh-minute*mi-second*ss;
			
			if(pattern.toUpperCase().equals("Day")){
				pageContext.getOut().println(day+"天");
			}if(pattern.toUpperCase().equals("HOUR")){
				pageContext.getOut().println(day+"天"+hour+"时");
			}if(pattern.toUpperCase().equals("MINUTE")){
				StringBuffer dhms = new StringBuffer();
				if(day>0){
					dhms.append(day+"天");
				}if(hour>0){
					dhms.append(hour+"小时");
				}if(minute>0){
					dhms.append(minute+"分钟");
				}if(dhms.length()<=0){
					dhms.append("小于1分钟");
				}
				pageContext.getOut().println(dhms.toString());
			}if(pattern.toUpperCase().equals("SECOND")){
				pageContext.getOut().println(day+"天"+hour+"时"+minute+"分"+second+"秒");
			}if(pattern.toUpperCase().equals("MILLISECOND")){
				pageContext.getOut().println(day+"天"+hour+"时"+minute+"分"+second+"秒"+milliSecond+"毫秒");
			}
		}
		
//	    String strDay = day<10?"0"+day:""+day;
//	    String strHour = hour<10?"0"+hour:""+hour;
//	    String strMinute = minute<10?"0"+minute:""+minute;
//	    String strSecond = second<10?"0"+second:""+second;
//	    String strMilliSecond = milliSecond<10?"0"+milliSecond:""+milliSecond; 
//	    strMilliSecond = milliSecond<100?"0"+strMilliSecond:""+strMilliSecond;    
	}
	 
}
