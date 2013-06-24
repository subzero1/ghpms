package com.ghpms.controller.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.service.QueryService;
/**
 * 用户查询--用户信息卡片
 * @author CT
 * @create 2010-03-20
 */
@Controller("/search/user_tail.do")
public class User_tail implements org.springframework.web.servlet.mvc.Controller {
	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		
		String user_id= request.getParameter("user_id");
		//查询用户信息姓名；工号；电话；部门
		String hql = "select ta03.name as name,ta03.login_id as login_id,ta03.fix_tel as tel ,ta01.name as dept_name,ta03.mobile_tel as mobile from Ta03_user ta03,Ta01_dept ta01 where ta03.dept_id=ta01.id and ta03.id= "+user_id;		
		ResultObject ro1=queryService.search(hql);
		Map<String,Object> mo=null;
		if (ro1.next()) {
			mo = ro1.getMap();
		}
		request.setAttribute("userlist", mo);
		
		//查询岗位,角色,节点
		Map<String,List<?>> model = new HashMap<String,List<?>>();	
		String hql_sta = "select ta02.id as id,ta02.name as name  from Ta11_sta_user ta11,Ta02_station ta02 where ta11.station_id=ta02.id and ta11.user_id="+user_id;		
		String hql_role = "select ta04.id as id,ta04.name as name from Ta04_role ta04 where ta04.id in( select ta12.role_id from Ta12_sta_role ta12 where ta12.station_id in( select ta11.station_id as id  from Ta11_sta_user ta11 where  ta11.user_id="+user_id+"))";		
		String hql_node = "select tb02.id as id,tb02.remark as name from Tb02_node tb02 where id in( select ta13.node_id as id from Ta13_sta_node ta13 where ta13.station_id in(select ta11.station_id as id  from Ta11_sta_user ta11 where  ta11.user_id="+user_id+"))";
		
		//岗位list_sta
		List list_sta = new ArrayList();
		ResultObject ro_sta =queryService.search(hql_sta);
		while(ro_sta.next()){
			Map<String,Object> list1 = ro_sta.getMap();	
			list_sta.add(list1);
		}
		model.put("list_sta", list_sta);
		
		//角色list_role
		List list_role = new ArrayList();
		ResultObject ro_role =queryService.search(hql_role);
		while(ro_role.next()){
			Map<String,Object> list1 = ro_role.getMap();	
			list_role.add(list1);
		}
		model.put("list_role", list_role);
		
		//节点list_node
		List list_node = new ArrayList();
		ResultObject ro_node =queryService.search(hql_node);
		while(ro_node.next()){
			Map<String,Object> list1 = ro_node.getMap();	
			list_node.add(list1);
		}
		model.put("list_node", list_node);
		
		return new ModelAndView("/WEB-INF/jsp/search/user_tail.jsp",model);
	}

	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

}
