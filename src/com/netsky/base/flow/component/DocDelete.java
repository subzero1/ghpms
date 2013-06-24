package com.netsky.base.flow.component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseDao.JdbcSupport;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Ta17_module_info;
import com.netsky.base.dataObjects.Tb12_opernode;
import com.netsky.base.dataObjects.Tz04_node_del;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.flow.FlowConstant;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;

@Component
public class DocDelete {

	private  Logger log = Logger.getLogger(this.getClass());
	/**
	 * 数据对应操作类
	 */
	@Autowired
	private SaveService saveService;

	@Autowired
	private QueryService queryService;
	
	@Autowired
	private JdbcSupport jdbcSupport;
	
	/**
	 * 删除表单
	 * @param paraMap
	 * @return
	 * @throws Exception String
	 */
	public String handleRequest(Map paraMap) throws Exception {
			
		StringBuffer hsql = new StringBuffer();
		List tmpList = new LinkedList();
		boolean deleteProject = false;
		
		//判断本工程是否只有一个表单
		hsql.append("select distinct doc_id from Tb15_docflow where project_id = ?");
		tmpList = queryService.searchList(hsql.toString(), new Object[] {MapUtil.getLong(paraMap, "project_id")});
		deleteProject = tmpList.size()==1;
		tmpList.clear();
		
		/**
		 * 记录删除日志
		 */
		Tz04_node_del tz04 = new Tz04_node_del();
		Ta03_user ta03 = (Ta03_user)paraMap.get("user");
		tz04.setLogin_id(ta03.getLogin_id());
		tz04.setUser_name(ta03.getName());
		tz04.setNode_id( MapUtil.getLong(paraMap, "node_id"));
		tz04.setOper_ip(MapUtil.getString(paraMap, "clientIp","132.232.158.1"));
		tz04.setOper_date(new Date());
		
		//删除工程
		if(deleteProject){
			Connection con = jdbcSupport.getConnection();
			con.setAutoCommit(false);
			String procedure = "{call delete_project(?,?,?)}";
			CallableStatement cstmt = con.prepareCall(procedure);
			cstmt.setString(1, "result");
			cstmt.setLong(2, MapUtil.getLong(paraMap, "project_id"));
			cstmt.setString(3, "PSS_NJ");
			cstmt.executeUpdate();
			cstmt.close();
			con.commit();
			con.close();	
			
			tz04.setAction("删除工程");
			saveService.save(tz04);
		} else {//删除表单
			tz04.setAction("删除节点");
			saveService.save(tz04);
			
			//删除表单
			Ta06_module ta06 = (Ta06_module) queryService.searchById(Ta06_module.class, MapUtil.getLong(paraMap, "module_id"));
			if (ta06 != null) {
				Object formObj = queryService.searchById(Class.forName(ta06.getForm_table()),MapUtil.getLong(paraMap, "doc_id"));
				if(formObj != null){
					QueryBuilder queryBuilder = new HibernateQueryBuilder(Ta17_module_info.class);
					queryBuilder.eq("module_id", MapUtil.getLong(paraMap, "module_id"));
					queryBuilder.eq("type", "list");
					tmpList = queryService.searchList(queryBuilder);
					for (Object obj : tmpList) {
						Ta17_module_info ta17 = (Ta17_module_info) obj;
						if ((ta17.getRelevance_table().substring(ta17.getRelevance_table().lastIndexOf(".") + 1))
								.equals(ta06.getForm_table().substring(ta06.getForm_table().lastIndexOf(".") + 1))) {
							hsql.delete(0, hsql.length());
							hsql.append("delete from ");
							hsql.append(ta17.getObject_name().substring(ta17.getObject_name().lastIndexOf(".") + 1));
							hsql.append(" t where t." + ta17.getObject_column() + "=");
							hsql.append(PropertyInject.getProperty(formObj, ta17.getRelevance_column()));
							saveService.updateByHSql(hsql.toString());
						}
					}
					hsql.delete(0, hsql.length());
					hsql.append("delete from ");
					hsql.append(ta06.getForm_table().substring(ta06.getForm_table().lastIndexOf(".") + 1));
					hsql.append(" where id = " + PropertyInject.getProperty(formObj, "id"));
					saveService.updateByHSql(hsql.toString());
				}
			}
			
			//修改上一表单新建节点状态;
			hsql.delete(0, hsql.length());
			hsql.append(" select tb12 from Tb12_opernode tb12,Tb13_operrelation tb13 ");
			hsql.append(" where tb12.id = tb13.source_id and tb13.dest_id = ?");
			tmpList = queryService.searchList(hsql.toString(), new Object[] {MapUtil.getLong(paraMap, "opernode_id")});
			if(tmpList.size()> 0){
				Tb12_opernode tb12 = (Tb12_opernode)tmpList.get(0);
				tb12.setNode_status(FlowConstant.NODE_STATUS_WORK);
				saveService.save(tb12);
				
				hsql.delete(0, hsql.length());
				hsql.append(" update   Tb15_docflow set  doc_status = " + FlowConstant.NODE_STATUS_WORK + ",approve_result = null where opernode_id = ");
				hsql.append(tb12.getId());
				saveService.updateByHSql(hsql.toString());
			}
			
			//删除表单tb13新建记录
			saveService.updateByHSql(" delete from Tb13_operrelation where dest_id = ? ", new Object[]{MapUtil.getLong(paraMap, "opernode_id")});
			//删除表单对于应的 tb12,tb13,tb15,tb17记录。
			saveService.updateByHSql(" delete from Tb12_opernode where module_id = ? and doc_id = ? ", new Object[]{MapUtil.getLong(paraMap, "module_id"),MapUtil.getLong(paraMap, "doc_id")});
			saveService.updateByHSql(" delete from Tb13_operrelation where module_id = ? and doc_id = ? ", new Object[]{MapUtil.getLong(paraMap, "module_id"),MapUtil.getLong(paraMap, "doc_id")});
			saveService.updateByHSql(" delete from Tb15_docflow where module_id = ? and doc_id = ? ", new Object[]{MapUtil.getLong(paraMap, "module_id"),MapUtil.getLong(paraMap, "doc_id")});
			saveService.updateByHSql(" delete from Tb17_approve where module_id = ? and doc_id = ? ", new Object[]{MapUtil.getLong(paraMap, "module_id"),MapUtil.getLong(paraMap, "doc_id")});
		}
		return FlowConstant.VIEW_CLOSE;
	}
}
