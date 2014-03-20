package com.ghpms.controller.sysManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
public class Dept {
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
	 * 部门列表
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/sysManage/deptList.do")
	public ModelAndView deptList(HttpServletRequest request,HttpServletResponse response ,HttpSession session)  {
		
		ModelMap modelMap = new ModelMap();
		Map deptListMap = new HashMap();
		List<Ta01_dept> depList = (List<Ta01_dept>)dao.search("from Ta01_dept where area_name in (select area.name from Tc02_area area) order by area_name,name");//所有部门（树）
		List tmpList = null;//前序遍历结果节点列表
		String area_name = "部门表";
		for(Ta01_dept ta01:depList){
			if(!ta01.getArea_name().equals(area_name)){
				if(tmpList !=null && tmpList.size() > 0){
					deptListMap.put(area_name, tmpList);
				}
				
				tmpList = new LinkedList();
				area_name = ta01.getArea_name();
			}
			tmpList.add(ta01);
		}
		
		deptListMap.put( area_name , tmpList);
		modelMap.put("deptListMap",deptListMap);
		modelMap.put("areaList", dao.search("  from Tc02_area where type like '%[3]%' order by name "));
		return new ModelAndView("/WEB-INF/jsp/sysManage/deptList.jsp",modelMap);
	}
	
	@RequestMapping("/sysManage/deptEdit.do")
	public ModelAndView deptEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"),-1L);
		ModelMap modelMap = new ModelMap();
		
		Ta01_dept dept = null;
		//获取所属地区列表
		modelMap.put("areaList", dao.search("from Tc02_area where type like '%[3]%' order by name"));
		
		//获取部门对象
		dept = (Ta01_dept) dao.getObject(Ta01_dept.class, id);
		modelMap.put("deptObj", dept);
		return new ModelAndView("/WEB-INF/jsp/sysManage/deptEdit.jsp",modelMap);
	}	
	
	@RequestMapping("/sysManage/ajaxDeptDel.do")
	public ModelAndView ajaxDeptDel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		Long id = convertUtil.toLong(request.getParameter("id"),-1L);
		ModelMap modelMap = new ModelMap();
		PrintWriter out = null;
       
    	//获取用户对象
		try {
			out = response.getWriter();
			dao.removeObject(Ta01_dept.class,id);
			out.print("{\"statusCode\":\"200\", \"message\":\"部门删除成功\", \"navTabId\":\"\", \"forwardUrl\":\"sysManage/deptList.do\", \"callbackType\":\"forward\"}");
		} catch (IOException e) {
			exceptionService.exceptionControl("com.crht.controller.sysManage.User", "部门删除失败", e);
		}
		return null;
	}	
}
