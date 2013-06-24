package com.netsky.base.controller;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.impl.CreateDataObjectsServiceImpl;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.dataObjects.Tz07_dataobject_cfg;

@Controller
public class AutoCreateJavaXml{

	@Autowired
	private CreateDataObjectsServiceImpl createDoService;

	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;	
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/autoCreateJavaXml.do")
	public ModelAndView autoCreateJavaXml(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tz07_id", convertUtil.toLong(request.getParameter("tz07_id")));
		paramMap.put("appPath", "D:\\workspace\\eclipseWorkspace\\txsg_mobile");
		paramMap.put("owner", "TXPMS_SG");
		createDoService.createJavaAndXml(paramMap);
		return null;
	}
	
	@RequestMapping("/batchAutoCreateJavaXml.do")
	public ModelAndView batchAutoCreateJavaXml(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.delete(0, sql.length());
		sql.append("from Tz07_dataobject_cfg tz07 where status = '未处理' ");
		ResultObject ro = queryService.search(sql.toString());
		while(ro.next()){
			Tz07_dataobject_cfg tz07 = (Tz07_dataobject_cfg)ro.get("tz07");
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tz07_id", tz07.getId());
			File file=new File(Thread.currentThread().getContextClassLoader().getResource("").getPath());
			String pathString=file.getParent();
			System.out.println(pathString);
			paramMap.put("appPath", "D:\\workspace\\ghpms");
			paramMap.put("owner", "PSS_NJ");
			createDoService.createJavaAndXml(paramMap);
		}
		return null;
	}
}  