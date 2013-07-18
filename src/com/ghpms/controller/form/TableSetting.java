package com.ghpms.controller.form;

import java.io.IOException;
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

import com.ghpms.service.CreateJspFile;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta07_formfield;
import com.netsky.base.dataObjects.Ta31_worklist_cfg;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.convertUtil;

@Controller
public class TableSetting {
	
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private ExceptionService exceptionService;
	
	@Autowired
	private SaveService saveService;
	
	@Autowired
	private CreateJspFile createJspFile;
	
	@RequestMapping("/sysManage/tableSetList.do")
	public ModelAndView tableSetList(HttpServletRequest request,
			HttpServletResponse response) {
		String view ="/WEB-INF/jsp/sysManage/tableSetList.jsp";
		ModelMap modelMap=new ModelMap();
		StringBuffer hql=new StringBuffer();
		ResultObject ro=null;
		Integer totalPages = 1;
		Integer totalCount = 0;
		List objectList=new ArrayList();
		Ta03_user user=(Ta03_user) request.getSession().getAttribute("user");
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "seq");
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");
		String keyword=convertUtil.toString(request.getParameter("keyword"));
		hql.append("select tc03 from Tc03_table_setting tc03 where 1=1 ");
		if (!keyword.equals("")) {
			hql.append("and (tc03.title like '%");
			hql.append(keyword);
			hql.append("%' or tc03.remark like '%");
			hql.append(keyword);
			hql.append("%') ");
		} 
		hql.append("order by ");
		hql.append(orderField);
		hql.append(" ");
		hql.append(orderDirection);
		ro=queryService.searchByPage(hql.toString(), pageNum, numPerPage);
		if (ro!=null) {
			while (ro.next()) {
				Object object=ro.get("tc03");
				objectList.add(object);
			}
			totalCount=ro.getTotalRows();
			totalPages=ro.getTotalPages();
		}
		modelMap.put("keyword", keyword);
		modelMap.put("objectList", objectList);
		modelMap.put("totalCount", totalCount);
		modelMap.put("totalPages", totalPages);
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		return new ModelAndView(view, modelMap);
	}
	
	@RequestMapping("/sysManage/columnSort.do")
	public ModelAndView columnSort(HttpServletRequest request ,HttpServletResponse response){


		Long module_id=convertUtil.toLong(request.getParameter("module_id"),new Long(101));
		HttpSession session = request.getSession();
		Ta03_user user = (Ta03_user) session.getAttribute("user");
		String user_id = user.getId().toString();
		StringBuffer hql=new StringBuffer();

		hql.append("select ta07 from Ta07_formfield ta07 where 1=1 ");
		hql.append(" and ta07.module_id=");
		hql.append(module_id);
		hql.append(" and ta07.name <>'id' ");
		hql.append(" order by ta07.ord ,ta07.comments ");
		/**
		 * 取表单列表
		 */
		List modules=queryService.searchList("select ta06 from Ta06_module ta06 where ta06.id<109");
		request.setAttribute("modules", modules);
		/**
		 * 配置信息存放list
		 */
		List<?> configList = null;
		try { 
			configList=queryService.searchList(hql.toString());
		} catch (Exception e) {
			return exceptionService.exceptionControl(
					"com.rms.controller.personalization.GcsjListCfg",
					"获取列表字段配置错误", e);
		}
		request.setAttribute("configList", configList);

		return new ModelAndView(
				"/WEB-INF/jsp/personalization/columnSort.jsp");
	
	}
	
	@RequestMapping("/sysManage/saveColumnSort.do")
	public void saveColumnSort(HttpServletRequest request,HttpServletResponse response) throws IOException {
		try {
			response.setCharacterEncoding(request.getCharacterEncoding());
			String[] stas = request.getParameterValues("t_sta");
			Long user_id = ((Ta03_user) request.getSession().getAttribute(
					"user")).getId();
			Long module_id=convertUtil.toLong(request.getParameter("module_id"));
			String forwardUrl = "";
			int i=1;
			for (String field_id : stas) {
				Ta07_formfield formfield=(Ta07_formfield) queryService.searchById(Ta07_formfield.class, convertUtil.toLong(field_id));
				formfield.setOrd(new Long(i++));
				saveService.save(formfield);
			}
			createJspFile.AutoCreateJspFile(request.getSession().getServletContext().getRealPath(
			"/WEB-INF"), module_id);  

			response
					.getWriter()
					.print(
							"{\"statusCode\":\"200\", \"message\":\"设置成功!\", \"navTabId\":\"\",\"forwardUrl\":\""
									+ forwardUrl + "\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(
					"{\"statusCode\":\"300\", \"message\":\"操作失败\"}");
		}
	}

}
