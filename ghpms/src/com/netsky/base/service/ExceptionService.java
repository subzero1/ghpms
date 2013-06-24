package com.netsky.base.service;

import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理服务接口
 * 
 * @author Chiang 2010-01-06
 */
public interface ExceptionService {

	/**
	 * 异常处理方法，记录异常到数据库
	 * 
	 * @param opclass
	 *            异常类
	 * @param opType
	 *            操作类别，用作返回显示
	 * @param ex
	 *            出错异常
	 * @return 错误页面ModelAndView
	 */
	public ModelAndView exceptionControl(String opclass, String opType, Exception ex);
}
