package com.netsky.base.controller.help;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Properties;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.DateGetUtil;
import com.netsky.base.utils.StringFormatUtil;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.utils.RegExp;
import com.netsky.base.controller.OperFile;
import com.netsky.base.dataObjects.Tz06_help;
import com.netsky.base.dataObjects.Tz08_help_map;
import com.netsky.base.dataObjects.Ta03_user;

/**
 * @description: ֪ʶ�����
 * @class name:com.netsky.base.controller.help.Help
 * @author lee.xiangyu Sep. 2, 2012
 */
@Controller
public class Help  {

	/**
	 * ��ݷ���
	 */
	@Autowired
	private Dao dao;

	@Autowired
	private ExceptionService exceptionService;

	/**
	 * ��ѯ����
	 */
	@Autowired
	private QueryService queryService;

	/**
	 * ��ѯ����
	 */
	@Autowired
	private SaveService saveService;


	/**
	 * ֪ʶ���б�
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/help/helpList.do")
	public ModelAndView repositoryList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		// ��ҳ��
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		Integer totalCount = 0;
		Integer pageNumShown = 0;

		// �����
		String orderField = convertUtil.toString(request.getParameter("orderField"), "id");
		String orderType = convertUtil.toString(request.getParameter("orderDirection"), "asc");

		// ��ѯ��
		String keywords = convertUtil.toString(request.getParameter("keywords"), "");
		
		// ת����
		String showPart = convertUtil.toString(request.getParameter("showPart"), "");

		// ��ݿ���ر�
		StringBuffer sql = new StringBuffer("");
		ModelMap modelMap = new ModelMap();

		// ������߰����б�
		sql.delete(0, sql.length());
		sql.append("from Tz06_help obj ");
		sql.append("where 1 = 1 ");

		// �ؼ���
		if (!keywords.equals("")) {
			sql.append("and (keys like '%");
			sql.append(keywords);
			sql.append("%' or title like '%");
			sql.append(keywords);
			sql.append("%') ");
		}

		sql.append(" order by ");
		sql.append(orderField);
		sql.append(" ");
		sql.append(orderType);

		ResultObject ro = queryService.searchByPage(sql.toString(), pageNum,numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();

		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderType", orderType);

		List<Object> helpList = new LinkedList<Object>();
		while (ro.next()) {
			helpList.add(ro.get("obj"));
		}

		modelMap.put("helpList", helpList);
		if(showPart.equals("yes"))
			return new ModelAndView("/WEB-INF/jsp/help/helpPartList.jsp",modelMap);
		else
			return new ModelAndView("/WEB-INF/jsp/help/helpList.jsp",modelMap);
	}

	/**
	 * ֪ʶ��༭
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/help/helpEdit.do")
	public ModelAndView repositoryEdit(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		// ��ݿ���ر�
		ModelMap modelMap = new ModelMap();
		Class<?> clazz = null;

		// ��ѯ��
		Long id = convertUtil.toLong(request.getParameter("id"), new Long(-1));

		// ��ǰʱ��
		modelMap.put("now", DateGetUtil.getCurTime());

		// ��ȡ֪ʶ�����
		Tz06_help tz06 = null;
		clazz = Tz06_help.class;
		tz06 = (Tz06_help) dao.getObject(clazz, id);
		modelMap.put("tz06", tz06);

		return new ModelAndView("/WEB-INF/jsp/help/helpEdit.jsp", modelMap);
	}

	/**
	 * ֪ʶ��ɾ��ajaxʵ��
	 * 
	 * @param reqeust
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/help/ajaxHelpDel.do")
	public void ajaxRepositoryDel(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		response.setContentType("text/xml");

		Long id = convertUtil.toLong(request.getParameter("id"), -1L);

		// ��ȡ�û�����
		try {
			out = response.getWriter();
			
			dao.removeObject(Tz06_help.class, id);

			/**
			 * ɾ���
			 */
			OperFile of = new OperFile();
			of.setQueryService(queryService);
			of.setSaveService(saveService);

			String sql = "select id from Te01_slave where doc_id = " + id;
			ResultObject ro = queryService.search(sql);
			while (ro.next()) {
				request.setAttribute("slave_id", ro.get("id"));
				of.delfile(request, response);
				dao.update("delete from Te01_slave where doc_id = "
						+ (Long) ro.get("id"));
			}

			out.print("{\"statusCode\":\"200\", \"message\":\"֪ʶ��ɾ��ɹ�\", \"navTabId\":\"\", \"forwardUrl\":\"help/helpList.do\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			exceptionService.exceptionControl(
					"com.crht.controller.business.Repository", "֪ʶ��ɾ��ʧ��", e);
		}
	}

	/**
	 * ֪ʶ�Ᵽ��ajaxʵ��
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/help/ajaxHelpSave.do")
	public void ajaxRepositorySave(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		out = response.getWriter();
		StringBuffer hsql = new StringBuffer("");
		Ta03_user user = (Ta03_user) session.getAttribute("user");

		try {
			if (user != null) {
				Long id = convertUtil.toLong(request.getParameter("Tz06_help.ID"), -1L);
				String title = request.getParameter("Tz06_help.TITLE");
				String keys = request.getParameter("Tz06_help.KEYS");
				String content = request.getParameter("Tz06_help.CONTENT");
				String recordor = request.getParameter("Tz06_help.RECORDOR");
				String record_date = request.getParameter("Tz06_help.RECORD_DATE");
				Transaction tx = null;

				/**
				 * ����֪ʶ��
				 */
				Long tz06_id = null;
				Tz06_help tz06 = (Tz06_help) dao.getObject(Tz06_help.class, id);
				if (tz06 == null) {
					tz06 = new Tz06_help();
				}
				tz06.setTitle(title);
				tz06.setKeys(keys);
				tz06.setRecord_date(new SimpleDateFormat("yyyy-MM-dd").parse(record_date));
				tz06.setRecordor(recordor);
				
				/*
				 * ���������ַ�
				 */
				content = content.replaceAll("</p><p\\s*[^>]*>", "<br>");// �س�
				content = content.replaceAll("</p>", "");// �ֶ�
				content = content.replaceAll("<p\\s*[^>]*>", "");// �ֶ�
				content = content.replaceAll("<\\s*a\\s+[^>]*>", "");// t��
				content = content.replaceAll("<a>", "");// t��
				content = content.replaceAll("</a>", "");// t��
				
				tz06.setContent(content);
				dao.saveObject(tz06);
				tz06_id = tz06.getId();

				out.print("{\"statusCode\":\"200\",\"message\":\"�������߰���ɹ�\",\"navTabId\":\"helpList\",\"callbackType\":\"forward\",\"forwardUrl\":\"help/helpEdit.do?id="+ tz06_id + "\"}");
			} else {
				out.print("{\"statusCode\":\"301\",\"message\":\"�Ự��ʱ�����µ�¼\",\"navTabId\":\"\",\"callbackType\":\"\",\"forwardUrl\":\"\"}");
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"statusCode\":\"300\",\"message\":\"�������߰���ʧ��\",\"navTabId\":\"\",\"callbackType\":\"\",\"forwardUrl\":\"\"}");
		} finally {
			// session.flush();
			// tx.commit();
			// session.close();
		}
	}

	/**
	 * ֪ʶ����Ϣ��ʾ
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/help/helpDisp.do")
	public ModelAndView repositoryDisp(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		// ��ݿ���ر�
		QueryBuilder queryBuilder = null;
		StringBuffer sql = new StringBuffer("");
		ModelMap modelMap = new ModelMap();
		Class<?> clazz = null;

		// ��ѯ��
		Long id = convertUtil.toLong(request.getParameter("id"), new Long(-1));

		// ��ȡ֪ʶ�����
		Tz06_help tz06 = null;
		clazz = Tz06_help.class;
		tz06 = (Tz06_help) dao.getObject(clazz, id);
		modelMap.put("tz06", tz06);

		return new ModelAndView("/WEB-INF/jsp/help/helpDisp.jsp", modelMap);
	}
	
	/**
	 * ��ʾ��������
	 * 
	 * @param reqeust
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/help/ajaxGetHelp.do")
	public void ajaxGetHelp(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		response.setContentType("text/xml");

		String module_name = convertUtil.toString(request.getParameter("module_name"));
		StringBuffer sql = new StringBuffer("");
		HttpSession session = request.getSession();
		String adminRole = null;

		// ��ȡ�û�����
		try {
			Long help_id = -1l;
			out = response.getWriter();
			sql.delete(0, sql.length());
			sql.append("select help_id from Tz08_help_map where module_name = '");
			sql.append(module_name);
			sql.append("'");
			ResultObject ro = queryService.search(sql.toString());
			if (ro.next()) {
				help_id = (Long)ro.get("help_id");
			}
			if(session == null){
				adminRole = "false";
			}else{
				adminRole = (String)session.getAttribute("admin");
			}
			
			
			out.print("{\"statusCode\":\"200\", \"message\":\"�ɹ�\", \"help_id\":\""+help_id+"\", \"adminRole\":\""+adminRole+"\"}");
			//out.print("{\"statusCode\":\"200\", \"message\":\"�ɹ�\", \"navTabId\":\"\", \"forwardUrl\":\"help/helpList.do\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			exceptionService.exceptionControl(
					"com.ghpms.controller.help.Help.openHelpDisp()", "�����߰���ʧ��", e);
		}
	}
	
	/**
	 * ��ʾ��������
	 * 
	 * @param reqeust
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/help/ajaxSetHelpMap.do")
	public void ajaxSetHelpMap(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		response.setContentType("text/xml");

		String module_name = convertUtil.toString(request.getParameter("module_name"));
		Long help_id = convertUtil.toLong(request.getParameter("help_id"));
		StringBuffer sql = new StringBuffer("");

		// ��ȡ�û�����
		try {
			out = response.getWriter();
			saveService.updateByHSql("delete from Tz08_help_map where module_name = '"+module_name+"'");
			Tz08_help_map tz08 = new Tz08_help_map();
			tz08.setHelp_id(help_id);
			tz08.setModule_name(module_name);
			saveService.save(tz08);
			out.print("{\"statusCode\":\"200\", \"message\":\"�ɹ�\", \"help_id\":\""+help_id+"\"}");
		} catch (Exception e) {
			exceptionService.exceptionControl(
					"com.rms.controller.help.Help.openHelpDisp()", "�����߰���ʧ��", e);
		}
	}
}
