package com.ghpms.controller.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.service.QueryService;

/**
 * 在线列表
 * @author CT
 * @create 2010-04-27
 */
@Controller("/search/OnLineList.do")
public class OnLineList implements org.springframework.web.servlet.mvc.Controller {
	/**
	 * 查寻服务
	 */
	@Autowired
	private QueryService queryService;	
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ServletContext a = request.getSession().getServletContext();
		Map<?,List<?>> onlineUserList = (Map<?,List<?>>) a.getAttribute("onlineUserList");
		
		List user_list = new ArrayList();
		if(onlineUserList!=null){
			for(Object o  : onlineUserList.keySet()){    
				List list =	onlineUserList.get(o);
				
				String sys = list.get(4).toString();
				
				if(sys.split(";").length>2){
					String systeminfo ="";
					if(sys.split(";")[1].equals(" MSIE 8.0")){
						systeminfo += "IE 8.0 ;";
					}else if(sys.split(";")[1].equals(" MSIE 7.0")){
						systeminfo += "IE 7.0 ;";
					}else if(sys.split(";")[1].equals(" MSIE 6.0")){
						systeminfo += "<font color='red' >IE 6.0 ;</font>";
					}else{
						systeminfo += sys.split(";")[1]+" ;";
					}
					
					if(sys.split(";")[2].equals(" Windows NT 6.0")){
						systeminfo += " Windows Vista ";
					}else if(sys.split(";")[2].equals(" Windows NT 6.1")){
						systeminfo += " Windows 7.0 ";
					}else{
						systeminfo += " Windows XP ";
					}
					list.remove(4);
					list.add(systeminfo); 
				}
				String login_id = list.get(0).toString();

				String hql =" select ta03.mobile_tel as mobile,ta01.name as dept_name,ta01.area_name as area_name from Ta03_user ta03,Ta01_dept ta01 where ta03.dept_id=ta01.id and ta03.login_id= '"+login_id+"'";
				
				ResultObject  ro = queryService.search(hql);
				if(ro.next()){
					Map<String,Object> map= ro.getMap();
				
					list.add(map.get("dept_name").toString());
					list.add(map.get("area_name").toString());
					String mobile= "";
					if(map.get("mobile")!=null){
						mobile =map.get("mobile").toString();
					}
					list.add(mobile);
				}
				user_list.add(list);
			}	
		}
		request.setAttribute("user_list", user_list);
		
		return new ModelAndView("/WEB-INF/jsp/search/onLineList.jsp");
	}

}
