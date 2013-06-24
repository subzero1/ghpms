package com.netsky.base.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.dataObjects.Tz01_exception;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

/**
 * 异常处理服务实现类
 * 
 * @author Chiang 2010-01-06
 */
@Service("exceptionService")
public class ExceptionServiceImpl implements ExceptionService {

	@Autowired
	private SaveService saveService;

	@Autowired
	private QueryService queryService;

	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * @return the saveService
	 */
	public SaveService getSaveService() {
		return saveService;
	}

	/**
	 * @param saveService
	 *            the saveService to set
	 */
	public void setSaveService(SaveService saveService) {
		this.saveService = saveService;
	}

	/**
	 * @return the queryService
	 */
	public QueryService getQueryService() {
		return queryService;
	}

	/**
	 * @param queryService
	 *            the queryService to set
	 */
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

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
	public ModelAndView exceptionControl(String opclass, String opType, Exception ex) {
		Tz01_exception tz01 = new Tz01_exception();
		try {
			log.error(opType,ex);
			tz01.setOpclass(opclass);
			tz01.setOptime(new Date());
			tz01.setName(ex + "");
			OutputStream o = new ByteArrayOutputStream();
			PrintStream p = new PrintStream(o);
			ex.printStackTrace(p);
			byte[] b = new byte[2000];
			p.write(b);
			tz01.setStacktrace(new String(b));
			saveService.save(tz01);
		} catch (Exception e) {
			log.error("异常类",e);
		}
		return null;
	}

}
