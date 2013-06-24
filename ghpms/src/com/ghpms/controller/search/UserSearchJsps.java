package com.ghpms.controller.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.QueryService;
/**
 * 用查询卡片关联岗位，角色，节点页面
 * @author CT
 * @create 2010-03-20
 */
@Controller
public class UserSearchJsps {
	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;
	
	//关联岗位
	@RequestMapping("/search/station_user.do")
	public ModelAndView station_user(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("GBK");
		
		String sta_id =request.getParameter("sta_id");
		String sta_name = request.getParameter("sta_name");	
		sta_name=new String(sta_name.getBytes("ISO-8859-1"),"GBK"); 
		request.setAttribute("sta_name", sta_name);
		String l_id=request.getParameter("login_id");

		Map<String,List<?>> model = new HashMap<String,List<?>>();
		String hql = "select ta03.name as name,ta03.login_id as login_id,ta03.fix_tel as tel,ta03.mobile_tel as mobile from Ta11_sta_user ta11,Ta03_user ta03 where ta11.user_id=ta03.id and ta03.id in (select id from Ta03_user where dept_id in (select id from Ta01_dept where area_name=(select area_name from Ta01_dept where id=(select dept_id from Ta03_user where login_id='"+l_id+"')))) and ta11.station_id= "+sta_id;
		ResultObject ro = queryService.search(hql);
		List user_list = new ArrayList();
		while(ro.next()){
			Map<String,Object> list1 = ro.getMap();	
			user_list.add(list1);
		}
		model.put("user_list", user_list);
		
		return new ModelAndView("/WEB-INF/jsp/search/station_user.jsp",model);
	}
	//关联角色
	@RequestMapping("/search/role_node.do")
	public ModelAndView role_node(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("GBK");
		//role
		if(request.getParameter("type").equals("role")){
			String role_id = request.getParameter("role_id");
			String role_name = request.getParameter("role_name");
			role_name = new String(role_name.getBytes("ISO-8859-1"),"GBK");
			request.setAttribute("names", role_name);
			Map<String,List<?>> model = new HashMap<String,List<?>>();
			String hql_sta = "select ta02.name as name from Ta12_sta_role ta12,Ta02_station ta02 where ta12.station_id=ta02.id and ta12.role_id="+role_id;
			String hql_user ="select ta03.name as name,ta03.login_id as login_id  from  Ta11_sta_user ta11,Ta03_user ta03 where ta03.id =ta11.user_id and ta11.station_id in( select ta12.station_id from Ta12_sta_role ta12 where ta12.role_id="+role_id+") order by ta03.login_id";
			//岗位list_sta
			List list_sta = new ArrayList();
			ResultObject ro_sta =queryService.search(hql_sta);
			while(ro_sta.next()){
				Map<String,Object> list1 = ro_sta.getMap();	
				list_sta.add(list1);
			}
			model.put("list_sta", list_sta);
			
			//人员list_user
			List list_user = new ArrayList();
			ResultObject ro_user =queryService.search(hql_user);
			while(ro_user.next()){
				Map<String,Object> list1 = ro_user.getMap();	
				list_user.add(list1);
			}
			model.put("list_user", list_user);
			return new ModelAndView("/WEB-INF/jsp/search/role_node.jsp",model);

		}else{//node
			String node_id = request.getParameter("node_id");
			String node_name = request.getParameter("node_name");
			node_name = new String(node_name.getBytes("ISO-8859-1"),"GBK");
			request.setAttribute("names", node_name);
			Map<String,List<?>> model = new HashMap<String,List<?>>();
			String hql_sta = "select ta02.name as name from Ta13_sta_node ta13 ,Ta02_station ta02 where ta13.station_id=ta02.id and ta13.node_id="+node_id;
			String hql_user ="select ta03.name as name,ta03.login_id as login_id  from  Ta11_sta_user ta11,Ta03_user ta03 where ta03.id =ta11.user_id and ta11.station_id in( select ta13.station_id from Ta13_sta_node ta13 where ta13.node_id="+node_id+") order by ta03.login_id";
			//岗位list_sta
			List list_sta = new ArrayList();
			ResultObject ro_sta =queryService.search(hql_sta);
			while(ro_sta.next()){
				Map<String,Object> list1 = ro_sta.getMap();	
				list_sta.add(list1);
			}
			model.put("list_sta", list_sta);
			
			//人员list_user
			List list_user = new ArrayList();
			ResultObject ro_user =queryService.search(hql_user);
			while(ro_user.next()){
				Map<String,Object> list1 = ro_user.getMap();	
				list_user.add(list1);
			}
			model.put("list_user", list_user);
			return new ModelAndView("/WEB-INF/jsp/search/role_node.jsp",model);
		}
	
	}
	
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}
	
	
}
