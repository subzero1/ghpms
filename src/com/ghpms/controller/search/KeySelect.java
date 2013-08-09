package com.ghpms.controller.search;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ghpms.controller.search.KeySelect;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta07_formfield;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.convertUtil;

/**
 * 工程查询与报表各类选择处理类
 * 
 * @author lee wang
 * 
 */
@Controller
public class KeySelect {

	@Autowired
	private QueryService queryService;

	@Autowired
	private ExceptionService exceptionService;

	/**
	 * @return the queryService
	 */
	public QueryService getQueryService() {
		return queryService;
	}

	/**
	 * @param queryService
	 *            the queryService to set
	 */
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	/**
	 * @return the exceptionService
	 */
	public ExceptionService getExceptionService() {
		return exceptionService;
	}

	/**
	 * @param exceptionService
	 *            the exceptionService to set
	 */
	public void setExceptionService(ExceptionService exceptionService) {
		this.exceptionService = exceptionService;
	}

	@RequestMapping("/search/keySelect.do")
	public ModelAndView keySelect(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<String> result = new ArrayList<String>();
			String type = convertUtil.toString(request.getParameter("type"));
			Long module_id=convertUtil.toLong(request.getParameter("module_id"));
			StringBuffer hql=new StringBuffer();
			Long data_type=0L;
			if (type.equals("XZQY")) {
				/*
				 * 所属区域
				 */
				String HSql = "select tc02 from Tc02_area tc02 order by id ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add(PropertyInject.getProperty(ro.get("tc02"),
							"name")
							+ "");
				}
			}else {
				hql.append("select ta07 from Ta07_formfield ta07 where 1=1 ");
				hql.append("and name='");
				hql.append(type.toLowerCase());
				hql.append("' and module_id=");
				hql.append(module_id);
				List list=queryService.searchList(hql.toString());
				if (list!=null&&list.size()>0) {
					Ta07_formfield ta07_formfield=(Ta07_formfield) list.get(0);
					data_type=ta07_formfield.getData_type();
				}
				if (data_type==4) {
					type="XOR";
				}
				 /*
				 * 通用复选条件,来自TC01
				 */
				String HSql = "select name from Tc01_property tc01 where  typecode='"+type+"'";
					 ResultObject ro = queryService.search(HSql);
					 while (ro.next()) {
					 result.add((String)ro.get("name"));
					 }
			}
			request.setAttribute("result", result);
		} catch (Exception e) {
			return exceptionService.exceptionControl(KeySelect.class.getName(),
					"选择基础多选项", e);
		}
		return new ModelAndView("/WEB-INF/jsp/search/keySelect.jsp");
	}
}
