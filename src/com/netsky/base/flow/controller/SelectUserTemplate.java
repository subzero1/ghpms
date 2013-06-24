package com.netsky.base.flow.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta32_node_user_limit;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

/**
 * 发送人员选择模板（会审岗加模板）
 * @author CT
 * @create 2010-05-14
 */
@Controller
public class SelectUserTemplate {
	@Autowired
	private SaveService saveService;

	@Autowired
	private QueryService queryService;

	@Autowired
	private ExceptionService exceptionService;
	/**
	 * AJAX
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/flow/selectSendTarget.do")
	public ModelAndView SelectUserListRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		Long node_id = new Long(request.getParameter("node_id"));
		Long user_id = new Long(request.getParameter("user_id"));
		
		String userids = request.getParameter("userids");
		String hql = "select ta03 from Ta32_node_user_limit ta32,Ta03_user ta03 " +
				" where ta32.node_user_id = ta03.id and ta32.user_id = "+ user_id +" and ta32.node_id="+node_id;
			hql += " and ta32.node_user_id in("+userids+")";
		hql += " order by ta03.login_id";
		request.setAttribute("userList", queryService.searchList(hql.toString()));
		return new ModelAndView("/WEB-INF/jsp/flow/selectSendTarget.jsp");	
	}
	
	
	/**
	 * AJAX
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/flow/saveConfig.do")
	public ModelAndView SUSaveTemplateRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		Long node_id = new Long(request.getParameter("node_id"));
		Long user_id = new Long(request.getParameter("user_id"));
		try{
			saveService.updateByHSql(" delete Ta32_node_user_limit where user_id="+user_id+" and node_id="+node_id);
			if(request.getParameterValues("_userids")!= null ){
				String[] options = request.getParameterValues("_userids");
				for(int i=0;i<options.length;i++){
					Ta32_node_user_limit ta32 = new Ta32_node_user_limit();
					ta32.setNode_id(node_id);
					ta32.setUser_id(user_id);
					ta32.setNode_user_id(new Long(options[i]));
					saveService.save(ta32);
				}
			}
			out.println("保存成功");
		}catch(Exception e){
			out.println("保存失败");
		}
		out.flush();
		out.close();
		return null;
	}
	
}
