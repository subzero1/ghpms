package com.netsky.base.flow.component;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.flow.trigger.DoTrigger;


/**
 * @description:
 * 结束，中止流程;或恢复流程至正常状态
 * @class name:com.netsky.base.flow.component.CompleteFlow
 * @author wind Jan 20, 2010
 */
@Component
public class CompleteFlow {
	/**
	 * 数据对应操作类
	 */
	@Autowired
	private Dao dao;

	/**
	 * 触发器
	 */
	@Autowired(required=false)
	private DoTrigger doTrigger;
	
	/**
	 * 自动办结处理器
	 */
	@Autowired(required=false)
	private Archive archive;
	
	/**
	 * 日志处理类
	 */
	private  Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 
	 * @param paraMap
	 * @return
	 * @throws Exception ModelAndView
	 */
	public ModelAndView handleRequest(Map paraMap) throws Exception {
		return null;
	}
	
	/**
	 * 恢复流程至正常状态
	 * @param paraMap
	 * @return
	 * @throws Exception boolean
	 */
	public boolean recoverRequest(Map paraMap) {
		try{
			
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static void main(String[] args){
		Logger log = Logger.getLogger("sadfasd");
		try{
			String aa = "wind";
			Long cc = Long.valueOf(aa);
		} catch (Exception e){
			log.warn(e, e);
		}
	}

}
