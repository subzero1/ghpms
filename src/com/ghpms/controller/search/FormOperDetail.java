package com.ghpms.controller.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.StringFormatUtil;

/**
 * 各表单处理情况
 * @author CT
 * @create 2010-04-24
 */
@Controller("/search/FormOperDetail.do")
public class FormOperDetail implements org.springframework.web.servlet.mvc.Controller {

	@Autowired
	private QueryService queryService;

	@Autowired
	private ExceptionService exceptionService;
	
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("GBK");
		request.setCharacterEncoding("GBK");
		
		String sortField = StringFormatUtil.format(request.getParameter("sortField"),"login_id");
		String sortType = StringFormatUtil.format(request.getParameter("sortType"),"desc");
		
		Ta03_user user = null;
		user = (Ta03_user) request.getSession().getAttribute("user");
		if (user == null) {
			return exceptionService.exceptionControl(this.getClass().getName(), "用户未登录或登录超时", new Exception("用户未登录"));
		}
//		String area = StringFormatUtil.format(request.getParameter("area"),user.getArea_name());
//		String dept = StringFormatUtil.format(request.getParameter("dept_name"),"");
		// 查询部门
//		if(request.getParameter("area")==null){
//			dept = user.getDept_id().toString();
//		}
//		request.setAttribute("dept_id", dept);
//		request.setAttribute("area",area);
		Integer pageNum = convertUtil.toInteger(request.getParameter("page"),1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"),20);
		int totalPages = 1;
		int totalCount = 1;
		request.setAttribute("page", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		
		String hql =" select login_id,user_name,module_name,dbs,zbs,bjs from FormOperDatail where 1=1 ";
		
		if( request.getParameter("search_name") != null && !request.getParameter("search_name").equals("")){
			request.setAttribute("search_name", request.getParameter("search_name"));			
				hql += " and (user_name like '%"+request.getParameter("search_name")+"%'"+" or login_id like '%"+request.getParameter("search_name")+"%')";
		}
		if(request.getParameter("type")!=null && request.getParameter("type").equals("per")){
			request.setAttribute("type", request.getParameter("type"));
			hql += " and login_id= '"+user.getLogin_id()+"'";
		}
		
		ResultObject ro = null;
		
		//设置排序
		StringBuffer order = new StringBuffer();
		order.append(" order by ");
		order.append(sortField+" ");
		order.append(sortType);
		
		//取列表数据
		ro  =  queryService.searchByPage(hql+order.toString(), pageNum, numPerPage);
		totalCount = ro.getTotalRows();
		totalPages = ro.getTotalPages();
		if(pageNum < 1 || pageNum > totalPages){
			pageNum = 1;
		}	
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("pageNum", pageNum);
		List oper_list = new ArrayList();
		while(ro.next()){
			Map<String,Object> mo = ro.getMap();
			int hj=0;
			if(mo.get("bjs")!=null){
				hj += new Integer( mo.get("bjs").toString());
			}
			if(mo.get("zbs")!=null){
				hj += new Integer(mo.get("zbs").toString());
			}
			if(mo.get("dbs")!=null){
				hj += new Integer(mo.get("dbs").toString());
			}
			mo.put("hj", hj);
			oper_list.add(mo);
		}
		request.setAttribute("oper_list", oper_list);
		
		return new ModelAndView("/WEB-INF/jsp/search/formOperDetail.jsp");
	}
	
	

}
