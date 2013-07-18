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
	public ModelAndView keySelect(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<String> result = new ArrayList<String>();
			String type = convertUtil.toString(request.getParameter("type"));
			if (type.equals("gclb")) {
				/*
				 * 工程类别
				 */
				String HSql = "select tc01 from Tc01_property tc01 where type='工程类别' order by id";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add(PropertyInject.getProperty(ro.get("tc01"), "name") + "");
				}
			}
			else if(type.equals("ssdq")){
				/*
				 * 所属区域
				 */
				String HSql = "select tc02 from Tc02_area tc02 order by id ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add(PropertyInject.getProperty(ro.get("tc02"), "name") + "");
				}
			}
			else if(type.equals("dept")){
				/*
				 *部门
				 */
				String HSql = "select ta01 from Ta01_dept ta01 order by id ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add(PropertyInject.getProperty(ro.get("ta01"), "name") + "");
				}
			} 
			else if(type.equals("sgdw")){
				/*
				 * 施工单位
				 */
				String HSql = "select mc from Tf01_wxdw where lb = '施工' ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("mc"));
				}
			}
			else if(type.equals("sjdw")){
				/*
				 * 设计单位
				 */
				String HSql = "select mc from Tf01_wxdw where lb = '设计' ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("mc"));
				}
			}
			else if(type.equals("jldw")){
				/*
				 * 监理单位
				 */
				String HSql = "select mc from Tf01_wxdw where lb = '监理' ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("mc"));
				}
			}
			else if(type.equals("gcjd")){
				/*
				 * 工程状态
				 */
				String HSql = "select distinct gcjd as gcjd from Td00_gcxx ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("gcjd"));
				}
			}
			else if(type.equals("gczt")){
				/*
				 * 工程状态
				 */
				String HSql = "select distinct gczt as gczt from Td00_gcxx ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("gczt"));
				}
			}else if (type.equals("jsxz")) {//建设性质
				String HSql = "select name from Tc01_property tc01 where type='建设性质'";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("name"));
				}
			}
			else if (type.equals("jsfs")) {//建设方式
				String HSql = "select name from Tc01_property tc01 where type='建设方式'";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("name"));
				}
			}  else if (type.equals("kclx")) {//勘察类型
				String HSql="select name from Tc01_property tc01 where typecode='KCLX'";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("name"));
				}
			}  else if (type.equals("zxgc")) {//专项工程
				String HSql="select name from Tc01_property tc01 where typecode='ZXGC'";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("name"));
				}
			}  else if (type.equals("rwwcqk")) {//任务完成情况
				String HSql="select name from Tc01_property tc01 where typecode='RWWCQK'";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("name"));
				}
			} else if(type.equals("ghjl")){
				String HSql="select name from Tc01_property tc01 where typecode='GHJL'";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("name"));
				}
			}else if (type.equals("gclx")) {
				String HSql="select name from Tc01_property tc01 where typecode='GCLX'";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("name"));
				}
			}
			request.setAttribute("result", result);
		} catch (Exception e) {
			return exceptionService.exceptionControl(KeySelect.class.getName(), "选择基础多选项", e);
		}
		return new ModelAndView("/WEB-INF/jsp/search/keySelect.jsp");
	}
}

