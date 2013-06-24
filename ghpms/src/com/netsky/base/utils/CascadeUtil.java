package com.netsky.base.utils;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netsky.base.baseDao.Dao;

@Controller
public class CascadeUtil {

	@Autowired
	private Dao dao;

	@SuppressWarnings("unchecked")
	@RequestMapping("/cascade.do")
	public void cascade(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> requestMap = request.getParameterMap();
		String tableName = convertUtil.toString(request.getParameter("tableName"));
		String conditionColumn = convertUtil.toString(request.getParameter("conditionColumn"));
		String valueForOption = convertUtil.toString(request.getParameter("valueForOption"));
		String conditionValue = convertUtil.toString(request.getParameter("conditionValue"));
		String orderBy = convertUtil.toString(request.getParameter("orderBy"));
		String whereClause = convertUtil.toString(request.getParameter("whereClause"));
		Map<String, String> extendColumns = new HashMap<String, String>();
		Map<String, String> showForOption = new HashMap<String, String>();
		for (String key : requestMap.keySet()) {
			if (key.startsWith("extendColumn_")) {
				extendColumns.put(key.replaceFirst("extendColumn_", ""), convertUtil
						.toString(request.getParameter(key)));
			} else if (key.startsWith("showForOption_")) {
				if (!"pattern".equals(key.replaceFirst("showForOption_", ""))) {
					showForOption.put(key.replaceFirst("showForOption_", ""), convertUtil.toString(request
							.getParameter(key)));
				}
			}
		}
		String pattern = convertUtil.toString(request.getParameter("showForOption_pattern"));
		StringBuffer hsql = new StringBuffer();
		hsql.append("select ");
		hsql.append(valueForOption);
		for (String key : extendColumns.keySet()) {
			hsql.append(",");
			hsql.append(extendColumns.get(key));
		}
		for (String key : showForOption.keySet()) {
			if (!"pattern".equals(key)) {
				hsql.append(",");
				hsql.append(showForOption.get(key));
			}
		}
		hsql.append(" from ");
		hsql.append(tableName);
		hsql.append(" where ");
		hsql.append(conditionColumn);
		hsql.append("='");
		hsql.append(conditionValue);
		hsql.append("'");
		if (!"".equals(whereClause)) {
			hsql.append(" and ");
			hsql.append(whereClause);
		}
		if (!"".equals(orderBy)) {
			hsql.append(" order by ");
			hsql.append(orderBy);
		}
		List<Object[]> list = (List<Object[]>) dao.search(hsql.toString());
		StringBuffer options = new StringBuffer();
		options.append("<option></option>");
		for (Object[] objects : list) {
			options.append("<option value=\"");
			String show = pattern;
			for (int i = 0; i < objects.length; i++) {
				Object o = objects[i];
				if (i == 0) {// value
					options.append(o);
					options.append("\" ");
				} else if (i <= extendColumns.size()) {// extend
					options.append(extendColumns.keySet().toArray()[i - 1]);
					options.append("=\"");
					options.append(o);
					options.append("\" ");
				} else {
					show = show.replaceAll("\\["+(String) showForOption.keySet().toArray()[i - 1 - extendColumns.size()]+"\\]", o
							.toString());
				}
			}
			options.append(">");
			options.append(show);
			options.append("</option>");
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(options);
		out.flush();
		out.close();
	}
}
