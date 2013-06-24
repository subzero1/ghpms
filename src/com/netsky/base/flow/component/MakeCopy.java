package com.netsky.base.flow.component;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;

/**
 * @description:
 * 文档抄送
 * @class name:com.netsky.base.flow.component.MakeCopy
 * @author wind Jan 20, 2010
 */
@Component
public class MakeCopy {

	/**
	 * 数据对应操作类
	 */
	@Autowired
	private Dao dao;
	
	/**
	 * 
	 * @param paraMap
	 * @return
	 * @throws Exception ModelAndView
	 */
	public ModelAndView handleRequest(Map paraMap) throws Exception {
		return null;
	}

}
