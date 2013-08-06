package com.ghpms.controller.sysManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ghpms.dataObjects.base.Tc01_property;
import com.netsky.base.baseDao.Dao;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.convertUtil;

import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta03_user;

/**
 * @description:
 * 部门维护
 * @class name:com.netsky.base.controller.Dept
 * @author Administrator Mar 8, 2011
 */
@Controller
public class Property {
	/**
	 * 数据服务
	 */
	@Autowired
	private Dao dao;	
	
	/**
	 * 异常处理服务 
	 */
	@Autowired
	private ExceptionService exceptionService;
	
	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;	
	
	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 基础属性维护列表
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/sysManage/propertySettingList.do")
	public ModelAndView propertySettingList(HttpServletRequest request,HttpServletResponse response ,HttpSession session)  {
		
		ModelMap modelMap = new ModelMap();
		Map prooertyListMap = new HashMap();
		List<Tc01_property> tc01List = (List<Tc01_property>)dao.search("from Tc01_property order by type,name");
		List tmpList = null;//前序遍历结果节点列表
		String type = "";
		for(Tc01_property tc01:tc01List){
			if(!tc01.getType().equals(type)){
				if(tmpList !=null && tmpList.size() > 0){
					prooertyListMap.put(type, tmpList);
				}
				tmpList = new LinkedList();
				type = tc01.getType();
			}
			tmpList.add(tc01);
		}
		prooertyListMap.put(type, tmpList);
		modelMap.put("propertyListMap",prooertyListMap);
		modelMap.put("propertyList", dao.search("select distinct type from Tc01_property"));
		return new ModelAndView("/WEB-INF/jsp/sysManage/propertySetting.jsp",modelMap);
	}
	
	@RequestMapping("/sysManage/propertyEdit.do")
	public ModelAndView propertyEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"),-1L);
		String type=convertUtil.toString(request.getParameter("type"));
		String typecode=convertUtil.toString(request.getParameter("typecode"));
		if (type!=null&&!type.equals("")) {
			type=new String(type.getBytes("ISO-8859-1"),"gbk");
		}
		ModelMap modelMap = new ModelMap();
		Tc01_property property_type = null;
		request.setAttribute("sxfl", dao.search("select distinct type from Tc01_property "));
		
		//获取属性对象
		property_type = (Tc01_property) dao.getObject(Tc01_property.class, id);
		if (property_type==null) {
			property_type=new Tc01_property();
			property_type.setType(type);
			property_type.setTypecode(typecode);
		}
		modelMap.put("property_type", property_type);
		return new ModelAndView("/WEB-INF/jsp/sysManage/propertyEdit.jsp",modelMap);
	}	
	
}
