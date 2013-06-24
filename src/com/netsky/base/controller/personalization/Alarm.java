package com.netsky.base.controller.personalization;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta23_personalization;
import com.netsky.base.dataObjects.Ta27_user_remind;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.convertUtil;

@Controller("/alarm.do") 
public class Alarm implements org.springframework.web.servlet.mvc.Controller{
	@Autowired
	private QueryService queryService;
	@Autowired
	private SaveService saveService;
	
	@Autowired
	private ExceptionService exceptionService;
	
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Ta03_user user=(Ta03_user)session.getAttribute("user");
		PrintWriter out = response.getWriter();
		String user_id=user.getId().toString();
		ResultObject ro = null;
		String sql="select distinct ta06.id as id,ta06.name as name from Ta11_sta_user ta11,Ta03_user ta03,Ta13_sta_node ta13,Tb02_node tb02,Tb04_flowgroup tb04,Ta06_module ta06 where ta11.user_id = ta03.id and ta11.station_id = ta13.station_id and ta13.node_id = tb02.id and tb02.flow_id = tb04.cflow_id and tb04.module_id = ta06.id and ta03.id ="+user_id;
		ro  =  queryService.search(sql);
		List alarm_list = new ArrayList();
		//List<Object[]>  docList = new LinkedList<Object[]>();
		while(ro.next()){
			Map<String,Object> mo = ro.getMap();	
			alarm_list.add(mo);
		}
		request.setAttribute("alarm_list", alarm_list);
		if(request.getParameter("save")!=null && !request.getParameter("save").equals("")){
			if(request.getParameterValues("po_id")!=null){
				saveService.updateByHSql("delete from Ta23_personalization where user_id="+user_id);
				for(int i=0;i<request.getParameterValues("po_id").length;i++){
					Ta23_personalization  ta23 = new Ta23_personalization();
					ta23.setUser_id(new Long(user_id));
					ta23.setType(new Integer(2));
					ta23.setPo_id(new Long(request.getParameterValues("po_id")[i]));
					saveService.save(ta23);
				}
			}
			saveService.updateByHSql("delete from Ta27_user_remind where user_id="+user_id);
			for(int i=0;i<request.getParameterValues("Ta27_user_remind.REMIND_TYPE").length;i++){
				Ta27_user_remind ta27= new Ta27_user_remind();
				ta27.setUser_id(new Long(user_id));
				ta27.setRemind_type(new Long(request.getParameterValues("Ta27_user_remind.REMIND_TYPE")[i]));
				if(request.getParameterValues("Ta27_user_remind.MOBILE_FLAG")[i].equals("1")){
					ta27.setMobile_flag(new Long(1));
				}
				else{
					ta27.setMobile_flag(new Long(0));
				}
				if(request.getParameterValues("Ta27_user_remind.MESSAGE_FLAG")[i].equals("1")){
					ta27.setMessage_flag(new Long(1));
				}
				else{
					ta27.setMessage_flag(new Long(0));
				}
				saveService.save(ta27);
			}
			out.print("{\"statusCode\":\"200\",\"message\":\"超时提醒设置成功！\", \"navTabId\":\"desktop\", \"forwardUrl\":\"\", \"callbackType\":\"closeCurrentDialog\"}");
			return null;
		}
		
		String sql_ta23="select po_id from Ta23_personalization where type=2 and user_id="+user_id;
		ro=queryService.search(sql_ta23);
		String obj="";
		if(ro.next()){
			obj=ro.get("po_id").toString()+",";
			while(ro.next()){
				obj=obj+ro.get("po_id").toString()+",";
			}
			String obj_new=obj.substring(0, obj.length()-1);
			request.setAttribute("obj_new", obj_new);
		}
		
		String sql1_ta27="select mobile_flag,message_flag from Ta27_user_remind where remind_type=1 and user_id="+user_id;
		ro  =  queryService.search(sql1_ta27);
		String message_flag1="";
		String mobile_flag1="";
		if(ro.next()){
			message_flag1=convertUtil.toString(ro.get("message_flag"));
			mobile_flag1=convertUtil.toString(ro.get("mobile_flag"));
		}
		else{
			message_flag1="1";
			mobile_flag1="1";
		}
		request.setAttribute("message_flag1", message_flag1);
		request.setAttribute("mobile_flag1", mobile_flag1);
		
	    sql1_ta27="select mobile_flag,message_flag from Ta27_user_remind where remind_type=2 and user_id="+user_id;
		ro  =  queryService.search(sql1_ta27);
		String message_flag2="";
		String mobile_flag2="";
		if(ro.next()){
			message_flag2=convertUtil.toString(ro.get("message_flag"));
			mobile_flag2=convertUtil.toString(ro.get("mobile_flag"));
		}
		else{
			message_flag2="1";
			mobile_flag2="1";
		}
		request.setAttribute("message_flag2", message_flag2);
		request.setAttribute("mobile_flag2", mobile_flag2);
		
		sql1_ta27="select mobile_flag,message_flag from Ta27_user_remind where remind_type=3 and user_id="+user_id;
		ro  =  queryService.search(sql1_ta27);
		String message_flag3="";
		String mobile_flag3="";
		if(ro.next()){
			message_flag3=convertUtil.toString(ro.get("message_flag"));
			mobile_flag3=convertUtil.toString(ro.get("mobile_flag"));
		}
		else{
			message_flag3="1";
			mobile_flag3="1";
		}
		request.setAttribute("message_flag3", message_flag3);
		request.setAttribute("mobile_flag3", mobile_flag3);
		
		return  new ModelAndView("/WEB-INF/jsp/personalization/alarm.jsp");
	}
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}
	public void setSaveService(SaveService saveService) {
		this.saveService = saveService;
	}
	public void setExceptionService(ExceptionService exceptionService) {
		this.exceptionService = exceptionService;
	}
}