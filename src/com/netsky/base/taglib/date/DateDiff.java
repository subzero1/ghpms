package com.netsky.base.taglib.date;

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * 作者：李翔宇
 * 
 * 日期：2010-03-31
 * 
 * 描述：date里面的DateDiff标记类
 */
public class DateDiff extends BodyTagSupport  {
	/**
	 * 默认的版本标识
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 起始日期
	 */
	private Date startDate ;
	
	/**
	 * 结束日期
	 */
	private Date endDate ;
	
	/**
	 * 返回类型year,month,day,hour,minute;
	 */
	private String pattern ;
	
	
	/**
	 * 获得起始日期
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * 设置起始日期
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * 获得结束日期
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * 设置结束日期
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获得返回模式
	 */
	public String getPattern() {
		return pattern;
	}
	
	/**
	 * 设置返回模式
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * 标记结束
	 */
	public int doEndTag() throws javax.servlet.jsp.JspException {
		try {
			printSelect();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
		return EVAL_PAGE;
	}

	/**
	 * 标记打印
	 */
	public void printSelect() throws Exception {
		
		Calendar s_cal = Calendar.getInstance();
		Calendar e_cal = Calendar.getInstance();
		if(startDate == null || endDate == null || "".equals(startDate) || "".equals(endDate))
			pageContext.getOut().println("");
			
		s_cal.setTime(startDate);
		e_cal.setTime(endDate);
		
		if(pattern == null)
			pattern = "DAY";
		
		if(pattern.toUpperCase().equals("MINUTE")){
			Long interval = (e_cal.getTimeInMillis() - s_cal.getTimeInMillis()) / (1000 * 60) ;
			pageContext.getOut().println(interval);
		}
		else if(pattern.toUpperCase().equals("HOUR")){
			Long interval = (e_cal.getTimeInMillis() - s_cal.getTimeInMillis()) / (1000 * 60 * 60) ;
			pageContext.getOut().println(interval);
		}
		else if(pattern.toUpperCase().equals("DAY")){
			Long interval = (e_cal.getTimeInMillis() - s_cal.getTimeInMillis()) / (1000 * 60 * 60 * 24) ;
			pageContext.getOut().println(interval);
		}
		else if(pattern.toUpperCase().equals("MONTH")){
			Long interval = (e_cal.getTimeInMillis() - s_cal.getTimeInMillis()) / (1000 * 60 * 60 * 24 * 30) ;
			pageContext.getOut().println(interval);
		}
		else if(pattern.toUpperCase().equals("YEAR")){
			Long interval = (e_cal.getTimeInMillis() - s_cal.getTimeInMillis()) / (1000 * 60 * 60 * 24 * 30 * 365) ;
			pageContext.getOut().println(interval);
		}	
	}
}
