package com.netsky.base.imagecut;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;

@Controller("/personalHead.do")
public class Open implements org.springframework.web.servlet.mvc.Controller{
	@Autowired
	private QueryService queryService;

	@Autowired
	private ExceptionService exceptionService;
	
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response){
		//用户头像获取
		Ta03_user user = null;
		user = (Ta03_user) request.getSession().getAttribute("user");
		if (user == null) {
			return exceptionService.exceptionControl(this.getClass().getName(), "用户未登录或登录超时", new Exception("用户未登录"));
		}
				
		String sql_salve="select id,file_name,ext_name,ftp_url from Te01_slave where doc_id="+user.getId()+" and module_id=0 and user_id="+user.getId()+" order by ftp_date desc";
		ResultObject ro_salve = queryService.search(sql_salve);
		if(ro_salve.next()){
			Map<String,Object> mo_salve = ro_salve.getMap();
			request.setAttribute("fj", mo_salve);
		}
		if(request.getParameter("save")!=null)request.setAttribute("save", "1");
		return new ModelAndView("/WEB-INF/jsp/personalization/personalHead.jsp");
	}
}
