package com.netsky.base.controller.other;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.controller.OperFile;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Ta11_sta_user;
import com.netsky.base.dataObjects.Te03_online;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

/**
 * 共享文档--工具下载
 * 
 * @author CT
 * @create 2010-04-21
 */
@Controller
public class Gxwd {
	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;
	@Autowired
	private SaveService saveService;
	@Autowired
	private ExceptionService exceptionService;

	@RequestMapping("/other/Gxwd.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获得MODULE_ID
		List<Ta06_module> module_list = (List<Ta06_module>) queryService
				.searchList("from Ta06_module where name='共享文档'");
		Long module_id = -100L;
		if (module_list != null && module_list.size() != 0) {
			Ta06_module ta06 = module_list.get(0);
			module_id = ta06.getId();
		}
		request.setAttribute("module_id", convertUtil.toLong(module_id, -1L));
		Integer pageNum = convertUtil
				.toInteger(request.getParameter("pageNum"));
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		int totalPages = 1;
		int totalCount = 1;
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		//keywords
		String keywords = convertUtil.toString(request.getParameter("keywords"),"");
		String hql = "select id,file_name,user_name,ftp_date,remark,ftp_url  from Te01_slave  where (file_name like '%"+keywords+"%' or remark like '%"+keywords+"%') and module_id="+module_id;
		String hql_sum = " from Te01_slave where (file_name like '%"+keywords+"%' or remark like '%"+keywords+"%') and module_id="+module_id;

		hql_sum += " order by ftp_date desc ";
		hql += " order by ftp_date desc ";
		ResultObject ro = null;

		// 获得记录条数

		ResultObject rohql_sum = queryService.search(hql_sum);
		totalCount = rohql_sum.getLength();
		if (totalCount > 0) {
			totalPages = totalCount % numPerPage == 0 ? totalCount / numPerPage
					: totalCount / numPerPage + 1;
		}
		if (pageNum < 1 || pageNum > totalPages) {
			pageNum = 1;
		}
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("pageNum", pageNum);
		// 取列表数据
		ro = queryService.searchByPage(hql, pageNum, numPerPage);

		List te01_list = new ArrayList();
		while (ro.next()) {
			Map<String, Object> mo = ro.getMap();
			te01_list.add(mo);
		}
		request.setAttribute("te01_list", te01_list);

		return new ModelAndView("/WEB-INF/jsp/other/gxwd.jsp");
	}

}
