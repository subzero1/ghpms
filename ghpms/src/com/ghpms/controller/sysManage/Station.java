package com.ghpms.controller.sysManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.netsky.base.dataObjects.Ta02_station;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Ta12_sta_role;
import com.netsky.base.dataObjects.Tb02_node;
import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.tree.controller.SpmxController.Result;
import com.netsky.base.utils.convertUtil;

/**
 * @description:
 * 岗位维护
 * @class name:com.netsky.base.controller.Station
 * @author Administrator Mar 8, 2011
 */
@Controller
public class Station {
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
	 * 岗位列表
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/sysManage/staList.do")
	public ModelAndView staList(HttpServletRequest request,HttpServletResponse response ,HttpSession session)  {
		ModelMap modelMap = new ModelMap();
		List<Ta02_station> treeData = (List<Ta02_station>)dao.search("from Ta02_station order by name,id ");//所有岗位
		
		modelMap.put("staList", treeData);
		return new ModelAndView("/WEB-INF/jsp/sysManage/staList.jsp",modelMap);
	}
	/**
	 * 岗位信息
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	
	@RequestMapping("/sysManage/staEdit.do")
	public ModelAndView staEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"),-1L);
		ModelMap modelMap = new ModelMap();
		Ta02_station sta= null;
		
		//获取岗位对象
		sta = (Ta02_station) dao.getObject(Ta02_station.class, id);
		modelMap.put("sta", sta);
		
		//获取岗位角色对象
		StringBuffer role_rsql = new StringBuffer();
		role_rsql.append(" select ta04 from Ta12_sta_role ta12,Ta04_role ta04 ");
		role_rsql.append(" where ta12.role_id = ta04.id  and ta12.station_id = ");
		role_rsql.append(id);
		role_rsql.append(" order by ta04.name ");
		List roles = dao.search(role_rsql.toString());
		modelMap.put("role", roles);
		
		//获取岗位用户对象
		StringBuffer user_rsql=new StringBuffer();
		user_rsql.append(" select ta03 from Ta11_sta_user ta11,Ta03_user ta03");
		user_rsql.append(" where ta11.user_id=ta03.id and ta11.station_id=");
		user_rsql.append(id);
		List users=dao.search(user_rsql.toString());
		modelMap.put("users", users);

		//获取岗位节点对象
		StringBuffer node_rsql = new StringBuffer();
		ResultObject ro=null;
		List nodeMaps=new ArrayList();
		node_rsql.append(" select tb02,ta06 from Ta13_sta_node ta13,Tb02_node tb02,Ta06_module ta06 ");
		node_rsql.append(" where ta13.node_id = tb02.id  and tb02.flow_id=ta06.id and ta13.station_id = ");
		node_rsql.append(id);
		node_rsql.append(" order by tb02.flow_id,tb02.node_type,tb02.name ");
		ro=queryService.search(node_rsql.toString());
		while (ro.next()) {
			Map nodeMap=new HashMap();
			nodeMap.put("node", ro.get("tb02"));
			nodeMap.put("module", ro.get("ta06"));
			nodeMaps.add(nodeMap);
		}
		modelMap.put("nodeMaps", nodeMaps);
		
		return new ModelAndView("/WEB-INF/jsp/sysManage/staEdit.jsp",modelMap);
	}	
	
	/**
	 * 岗位删除
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/sysManage/ajaxStaDel.do")
	public ModelAndView ajaxStaDel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		Long id = convertUtil.toLong(request.getParameter("id"),-1L);
		ModelMap modelMap = new ModelMap();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			List<Ta02_station> treeData = (List<Ta02_station>)dao.getObjects(Ta02_station.class);
			dao.removeObject(Ta02_station.class,id);
			//删除岗位表角色对应关系
			StringBuffer hsql=new StringBuffer();
			hsql.append("select ta12 from Ta12_sta_role ta12 where ta12.station_id=");
			hsql.append(id);
			List <Ta12_sta_role>tempList=(List <Ta12_sta_role>)dao.search(hsql.toString());
			if(!tempList.isEmpty()){
				for(int i=0;i<tempList.size();i++){
					dao.removeObject(tempList.get(i));
				}
			}
			out.print("{\"statusCode\":\"200\", \"message\":\"岗位删除成功\", \"navTabId\":\"\", \"forwardUrl\":\"sysManage/staList.do\", \"callbackType\":\"forward\"}");
		} catch (IOException e) {
			exceptionService.exceptionControl("com.crht.controller.sysManage.Station", "岗位删除失败", e);
		}
		return null;
	}	
}
