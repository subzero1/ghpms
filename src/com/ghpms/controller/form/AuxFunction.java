package com.ghpms.controller.form;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.Cell;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseDao.JdbcSupport;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.utils.DateGetUtil;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.service.ConfigXMLService;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.service.impl.ConfigXMLServiceImpl;
import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.baseObject.PropertyInject;


@Controller
public class AuxFunction {

	@Autowired
	private Dao dao;

	@Autowired
	private QueryService queryService;

	@Autowired
	private SaveService saveService;

	@Autowired
	private ExceptionService exceptionService;

	@Autowired
	private JdbcSupport jdbcSupport;

	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping("/aux/gdcqtj.do")
	public ModelAndView gdcqtj(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws SQLException, UnsupportedEncodingException {		
		//return new ModelAndView(view,"/WEB-INF/jsp/search/gdcqtj.jsp");
		return null;
	}
}
