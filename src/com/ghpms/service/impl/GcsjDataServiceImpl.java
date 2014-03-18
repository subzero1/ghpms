package com.ghpms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghpms.dataObjects.base.Tc01_property;
import com.ghpms.dataObjects.form.Td31_glwhgc;
import com.ghpms.service.GcsjDataService;
import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Ta07_formfield;
import com.netsky.base.dataObjects.Tz02_oper_log;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.convertUtil;

@Service("gcsjDataService")
public class GcsjDataServiceImpl implements GcsjDataService {

	@Autowired
	private QueryService queryService;
	
	@Autowired
	SaveService saveService;

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

	public List<Ta07_formfield> getExcelTitleList(Ta03_user user, Long module_id) {
		StringBuffer hsql = new StringBuffer();
		hsql
				.append("select distinct(a.id),a from Ta07_formfield a , Ta16_node_field b,Tb02_node c,Ta13_sta_node d,Ta02_station e,Ta11_sta_user f,Ta03_user g ");
		hsql
				.append(" where a.id=b.field_id and b.node_id=c.id and c.id=d.node_id and d.station_id=e.id and e.id=f.station_id and f.user_id=g.id ");
		hsql.append(" and a.module_id=");
		hsql.append(module_id);
		hsql.append(" and g.id=");
		hsql.append(user.getId());
		hsql.append(" order by a.ord");
		List<Ta07_formfield> excelTitleList = new ArrayList<Ta07_formfield>();
		ResultObject ro = queryService.search(hsql.toString());
		while (ro.next()) {
			Ta07_formfield field = (Ta07_formfield) ro.get("a");
			excelTitleList.add(field);
		}
		return excelTitleList;
	}

	public List getUpdateProperty(Long module_id) {
		StringBuffer hsql = new StringBuffer();
		hsql.append("select a from Ta07_formfield a where a.module_id=");
		hsql.append(module_id);
		hsql.append(" and a.name ='");
		hsql.append("id' ");
		List list = queryService.searchList(hsql.toString());
		return list;
	}

	public Map setSelectValue(HttpServletRequest request, Long node_id) {
		StringBuffer hsql = new StringBuffer();
		Map map = new HashMap<String, String>();
		List objs = null;

		hsql
				.append("select a from Ta07_formfield a,Tb02_node b,Ta16_node_field c where a.id=c.field_id and b.id=c.node_id ");
		hsql.append(" and b.id=");
		hsql.append(node_id);
		hsql.append(" and a.data_type in(1,3,4)");
		hsql.append(" order by  a.ord");
		List fields = queryService.searchList(hsql.toString());
		for (Object object : fields) {
			Ta07_formfield formfield = (Ta07_formfield) object;
			/**
			 * 配置所属地区
			 */
			if (formfield.getName().equals("ssdq")
					|| formfield.getName().equals("xzqb")
					|| formfield.getName().equals("ssxzq")
					|| formfield.getName().equals("xzqy")) {
				hsql.delete(0, hsql.length());
				hsql.append("select tc02 from Tc02_area tc02 where 1=1 ");
				hsql.append(" order by tc02.name ");
				objs = queryService.searchList(hsql.toString());
				request.setAttribute(formfield.getName(), objs);
				map.put("objectForOption", "ssdq");

			}
			/**
			 * 配到Tc01的情況
			 */
			else {
				// 仅包含是否的下拉框
				if (formfield.getData_type() == 4) {
					hsql.delete(0, hsql.length());
					hsql
							.append("select tc01 from Tc01_property  tc01 where 1=1 ");
					hsql.append(" and tc01.type = 'XOR'");
					objs = (List<Tc01_property>) queryService.searchList(hsql
							.toString());
					Tc01_property property = null;
					if (objs != null && objs.size() > 0) {
						property = (Tc01_property) objs.get(0);
						request.setAttribute(formfield.getName(), objs);
						map.put("objectForOption", property.getTypecode());
					}
					// 其他情况
				} else {
					
					//勘察单位，施工单位的情况
					if (formfield.getComments().startsWith("勘察单位")||formfield.getComments().startsWith("施工单位")) {
						hsql.delete(0, hsql.length());
						hsql
								.append("select tc01 from Tc01_property  tc01 where 1=1 ");
						hsql.append(" and tc01.type  like '");
						hsql.append(formfield.getComments().substring(0, 3));
						hsql.append("%'");
					} else {
						hsql.delete(0, hsql.length());
						hsql
								.append("select tc01 from Tc01_property  tc01 where 1=1 ");
						hsql.append(" and tc01.type = '");
						hsql.append(formfield.getComments());
						hsql.append("'");
					}
					objs = (List<Tc01_property>) queryService.searchList(hsql
							.toString());
					Tc01_property property = null;
					if (objs != null && objs.size() > 0) {
						property = (Tc01_property) objs.get(0);
						request.setAttribute(formfield.getName(), objs);
						map.put("objectForOption", property.getTypecode());
					}

				}
			}

		}
		return map;
	}

	/**
	 * 超期列表 重载方法：getOutDateList (non-Javadoc)
	 * 
	 * @see com.ghpms.service.GcsjDataService#getOutDateList()
	 */
	public List getOutDateList(Ta03_user user) {

		StringBuffer hsql = new StringBuffer("");

		String tableName;
		String className;
		List outDateMapList = new ArrayList();
		List<Ta06_module> modules = (List<Ta06_module>) queryService
				.searchList(Ta06_module.class);
		for (Ta06_module ta06_module : modules) {
			List outDateList = new ArrayList();
			className = ta06_module.getForm_table();
			tableName = className.substring(className.lastIndexOf(".") + 1,
					className.length());

			// 已经超期
			hsql.delete(0, hsql.length());
			hsql.append("from ");
			hsql.append(tableName);
			hsql.append(" t where t.sjwcsj is null and t.jhwcsj-sysdate<0 ");
			hsql.append(" and exists(");
			hsql.append(" select distinct(d.id),f.id,f.name ,f.comments ");
			hsql
					.append(" from Ta03_user a,Ta02_station b,Ta11_sta_user c,Tb02_node d,Ta13_sta_node e,Ta07_formfield f ,Ta16_node_field g ");
			hsql
					.append(" where a.id=c.user_id and b.id=c.station_id and d.id=e.node_id and e.station_id=b.id and d.id=g.node_id and g.field_id=f.id ");
			hsql.append(" and f.name in('jhwcsj','sjwcsj') ");
			hsql.append(" and a.id=");
			hsql.append(user.getId());
			hsql.append(" and d.flow_id=");
			hsql.append(ta06_module.getId());
			hsql.append(")");
			List inOutDateList = new ArrayList();
			try {
				inOutDateList = queryService.searchList(hsql.toString());
			} catch (Exception e) {
				continue;
			}
			for (Object object : inOutDateList) {
				Map tableMap = new HashMap();
				tableMap.put("module_name", ta06_module.getName());
				tableMap.put("module_id", ta06_module.getId());
				tableMap.put("project", object);
				outDateMapList.add(tableMap);
			}
		}

		return outDateMapList;
	}

	public List getLendOutDateList(Ta03_user user) {

		StringBuffer hsql = new StringBuffer("");

		String tableName;
		String className;
		List outDateMapList = new ArrayList();
		hsql.append("select t from Ta06_module t where t.outdate like '%[2]%'");
		List<Ta06_module> modules = (List<Ta06_module>) queryService
				.searchList(hsql.toString());

		for (Ta06_module ta06_module : modules) {
			List outDateList = new ArrayList();
			className = ta06_module.getForm_table();
			tableName = className.substring(className.lastIndexOf(".") + 1,
					className.length());

			// 已经超期
			hsql.delete(0, hsql.length());
			hsql.append("from ");
			hsql.append(tableName);
			hsql.append(" t where  t.sfyjycl='是' and (sysdate-t.jlsj)>7  ");
			hsql.append(" and exists(");
			hsql.append(" select distinct(d.id),f.id,f.name ,f.comments ");
			hsql
					.append(" from Ta03_user a,Ta02_station b,Ta11_sta_user c,Tb02_node d,Ta13_sta_node e,Ta07_formfield f ,Ta16_node_field g ");
			hsql
					.append(" where a.id=c.user_id and b.id=c.station_id and d.id=e.node_id and e.station_id=b.id and d.id=g.node_id and g.field_id=f.id ");
			hsql.append(" and f.name in('sfyjycl','jlsj') ");
			hsql.append(" and a.id=");
			hsql.append(user.getId());
			hsql.append(" and d.flow_id=");
			hsql.append(ta06_module.getId());
			hsql.append(")");
			List inOutDateList = new ArrayList();
			try {
				inOutDateList = queryService.searchList(hsql.toString());
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			for (Object object : inOutDateList) {
				Map tableMap = new HashMap();
				tableMap.put("module_name", ta06_module.getName());
				tableMap.put("module_id", ta06_module.getId());
				tableMap.put("project", object);
				outDateMapList.add(tableMap);
			}
		}

		return outDateMapList;
	}


	public List getFkddList(Ta03_user user) {

		StringBuffer hsql = new StringBuffer("");

		// 已经超期
		hsql.delete(0, hsql.length());
		hsql.append("from Td31_glwhgc ");
		hsql.append(" t where  t.sffkddzx='是' and (sysdate-t.fksj1)>1  ");
		hsql.append(" and exists(");
		hsql.append(" select distinct(d.id),f.id,f.name ,f.comments ");
		hsql
				.append(" from Ta03_user a,Ta02_station b,Ta11_sta_user c,Tb02_node d,Ta13_sta_node e,Ta07_formfield f ,Ta16_node_field g ");
		hsql
				.append(" where a.id=c.user_id and b.id=c.station_id and d.id=e.node_id and e.station_id=b.id and d.id=g.node_id and g.field_id=f.id ");
		hsql.append(" and f.name in('sffkddzx','fksj1') ");
		hsql.append(" and a.id=");
		hsql.append(user.getId());
		hsql.append(" and d.flow_id=401");
		hsql.append(")");
		List<Td31_glwhgc> inOutDateList = new ArrayList<Td31_glwhgc>();
		try {
			inOutDateList = (List<Td31_glwhgc>) queryService.searchList(hsql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return inOutDateList;
	}

	public List getFkgdList(Ta03_user user) {

		StringBuffer hsql = new StringBuffer("");

		// 已经超期
		hsql.delete(0, hsql.length());
		hsql.append("from Td31_glwhgc ");
		hsql.append(" t where  t.sffkgd='是' and (sysdate-t.fksj2)>1  ");
		hsql.append(" and exists(");
		hsql.append(" select distinct(d.id),f.id,f.name ,f.comments ");
		hsql
				.append(" from Ta03_user a,Ta02_station b,Ta11_sta_user c,Tb02_node d,Ta13_sta_node e,Ta07_formfield f ,Ta16_node_field g ");
		hsql
				.append(" where a.id=c.user_id and b.id=c.station_id and d.id=e.node_id and e.station_id=b.id and d.id=g.node_id and g.field_id=f.id ");
		hsql.append(" and f.name in('sffkgd','fksj2') ");
		hsql.append(" and a.id=");
		hsql.append(user.getId());
		hsql.append(" and d.flow_id=401");
		hsql.append(")");
		List<Td31_glwhgc> inOutDateList = new ArrayList<Td31_glwhgc>();
		try {
			inOutDateList = (List<Td31_glwhgc>) queryService.searchList(hsql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return inOutDateList;
	}

	public List getFkjjList(Ta03_user user) {

		StringBuffer hsql = new StringBuffer("");

		// 已经超期
		hsql.delete(0, hsql.length());
		hsql.append("from Td31_glwhgc ");
		hsql.append(" t where  t.sfjj='是' and (sysdate-t.fksj3)>1  ");
		hsql.append(" and exists(");
		hsql.append(" select distinct(d.id),f.id,f.name ,f.comments ");
		hsql
				.append(" from Ta03_user a,Ta02_station b,Ta11_sta_user c,Tb02_node d,Ta13_sta_node e,Ta07_formfield f ,Ta16_node_field g ");
		hsql
				.append(" where a.id=c.user_id and b.id=c.station_id and d.id=e.node_id and e.station_id=b.id and d.id=g.node_id and g.field_id=f.id ");
		hsql.append(" and f.name in('sfjj','fksj3') ");
		hsql.append(" and a.id=");
		hsql.append(user.getId());
		hsql.append(" and d.flow_id=401");
		hsql.append(")");
		List<Td31_glwhgc> inOutDateList = new ArrayList<Td31_glwhgc>();
		try {
			inOutDateList = (List<Td31_glwhgc>) queryService.searchList(hsql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return inOutDateList;
	}

	public List getTableNodeList(Ta03_user user, Long module_id) {
		StringBuffer hsql = new StringBuffer();
		hsql
				.append("select d ");
		hsql
				.append(" from Ta03_user a,Ta02_station b,Ta11_sta_user c,Tb02_node d,Ta13_sta_node e ");
		hsql
				.append("where a.id=c.user_id and b.id=c.station_id and d.id=e.node_id and e.station_id=b.id ");
		hsql.append(" and a.id=");
		hsql.append(user.getId());
		hsql.append(" and d.flow_id=");
		hsql.append(module_id);
		//
		hsql.append(" and d.node_type=2");
		List  list = queryService.searchList(hsql.toString());
		return list;
	}

	public void saveDelLog(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		StringBuffer hql=new StringBuffer();
		Ta03_user user=(Ta03_user) request.getSession().getAttribute("user");
		String ids = convertUtil.toString(request.getParameter("ids"));
		Long module_id = convertUtil.toLong(request.getParameter("module_id"));

		Ta06_module module = (Ta06_module) queryService.searchById(
				Ta06_module.class, module_id);
		Tz02_oper_log operLog=new Tz02_oper_log();
		Class c = Class.forName(module.getProject_table());

		operLog.setLogin_id(user.getLogin_id());
		operLog.setOpuser(user.getName());
		if (ids!=null&&!ids.equals("")) {
			operLog.setOpdesc("所属表："+module.getName()+"  删除ID["+ids+"]"+"  影响行数:"+ids.split(",").length);
		}
		
		operLog.setOptype("Delete");
		operLog.setOld_data(ids);
		operLog.setOptable(module.getProject_table());
		operLog.setOptime(new Date());
		saveService.save(operLog);
		
	}

}
