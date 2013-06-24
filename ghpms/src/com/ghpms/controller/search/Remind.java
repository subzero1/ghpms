package com.ghpms.controller.search;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.flow.vo.Cswdxx;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;

/**
 * 超时提醒
 */
@Controller
public class Remind {

	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;
	/**
	 * 错误信息
	 */
	@Autowired
	private ExceptionService exceptionService;

	@RequestMapping("/search/remindFlowList.do")
	public ModelAndView remindFlowList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		String remindType = request.getParameter("remindType");
		String t_sql = "";
		if ("jcs".equals(remindType)){
			t_sql = " and cqlx = '将超期'";
			
		} else if ("cs".equals(remindType)){
			t_sql = " and cqlx = '超期'";
		}
		Long user_id = convertUtil.toLong(request.getParameter("user_id"));
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);

		int totalPages = 1;
		int totalCount = 1;
		String hsql = " from Cswdxx base where user_id="+user_id+t_sql;
		totalCount = convertUtil.toInteger(queryService.searchList(
				" select count(*)" + hsql).get(0));
		if (totalCount > 0) {
			totalPages = totalCount % numPerPage == 0 ? totalCount / numPerPage
					: totalCount / numPerPage + 1;
		} else {
			totalPages = 1;
		}
		if (pageNum < 1 || pageNum > totalPages) {
			pageNum = 1;
		}
		ModelMap modelMap = new ModelMap();
		modelMap.put("totalCount", totalCount);
		modelMap.put("totalPages", totalPages);
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		
		ResultObject ro = queryService.searchByPage(
				"select base"+hsql, pageNum, numPerPage);
		List<Cswdxx> list = new ArrayList<Cswdxx>();
		while (ro.next()) {
			Cswdxx cswd = (Cswdxx)ro.get("base");
			list.add(cswd);
		}
		modelMap.put("cswd_list", list);
		
		return new ModelAndView("/WEB-INF/jsp/search/remindFlowList.jsp", modelMap);
	}

	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

}
