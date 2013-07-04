package com.ghpms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghpms.service.GcsjDataService;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta04_role;
import com.netsky.base.dataObjects.Ta07_formfield;
import com.netsky.base.dataObjects.Ta21_user_ext;
import com.netsky.base.dataObjects.Tb02_node;
import com.netsky.base.service.QueryService;

@Service("gcsjDataService")
public class GcsjDataServiceImpl implements GcsjDataService {

	@Autowired
	private QueryService queryService;

	public Map getFormTitleMap(Ta03_user user, Long module_id) {
		// TODO Auto-generated method stub

		Map<String, Object> dataMap = new HashMap<String, Object>();
		ResultObject rs;
		StringBuffer hsql = new StringBuffer();


		/**
		 * 文档列表，列定义 列结构：ta07_formfield
		 */
		List docColsList = null;
		hsql.delete(0, hsql.length());
		hsql
				.append("select ta07 from Ta07_formfield ta07,Ta31_worklist_cfg ta31 ");
		hsql.append(" where ta31.field_id = ta07.id");
		hsql.append(" and ta31.user_id = " + user.getId());
		hsql.append(" and ta31.module_id=");
		hsql.append(module_id);
		hsql.append(" order by ta31.ord");
		docColsList = queryService.searchList(hsql.toString());
		if (docColsList == null || docColsList.size() == 0) {
			docColsList = queryService
					.searchList("from Ta07_formfield ta07 where module_id = "
							+ module_id
							+ " and ta07.show_flag = 1 order by ord");
		}

		int docTabWitdh = 0;
		for (Object o : docColsList) {
			Ta07_formfield ta07 = (Ta07_formfield) o;
			ta07.setObject_name("a");
			docTabWitdh += ta07.getWidth();
		}

		dataMap.put("docColsList", docColsList);
		dataMap.put("docTabWitdh", docTabWitdh);
		/**
		 * 加载用户模板
		 */
		return dataMap;
	}

	public void setDocColsList(HttpServletRequest request) {
	}

}
