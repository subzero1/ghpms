package com.netsky.base.flow.buttonControl;

import java.util.List;
import java.util.Map;

import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb03_relation;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.utils.convertUtil;
/**
 * @description:
* @class name:com.netsky.base.flow.buttonControl.ButtonShow
 * @author Administrator Mar 22, 2010
 */
public class ButtonShow extends ButtonControl {

	/**
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#checkCondition(java.util.Map)
	 */
	public String checkCondition(Map paraMap) {
		return "OK";
	}

	/**
	  * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#getUsers(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<Ta03_user> getUsers(Map paraMap) {
		return null;
	}

	/**
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#isShow(java.util.Map)
	 */
	public boolean isShow(Map paraMap) {
		
		StringBuffer sql = new StringBuffer("");
		
		try{
			Long node_id = convertUtil.toLong(paraMap.get("node_id"));
			Long relation_id = convertUtil.toLong(paraMap.get("relation_id"));
			Long project_id = convertUtil.toLong(paraMap.get("project_id"));
			Long module_id = convertUtil.toLong(paraMap.get("module_id"));
			Long affair_id = convertUtil.toLong(paraMap.get("affair_id"));
			
			Tb03_relation tb03 = (Tb03_relation)queryService.searchById(Tb03_relation.class, relation_id);
			if(tb03 == null){
				tb03 = new Tb03_relation();
			}
			String relation_desc = convertUtil.toString(tb03.getDescription());
			
			/**
			 * 只有设备项目才需要走安全验收流程
			 * 显示‘安全验收ACTION’
			 */
			if(((module_id == 104 || module_id == 107) && relation_desc.indexOf("安全验收") != -1)||affair_id==16){
				
				String project_table = "Td01_xmxx"; 
				if(module_id == 104 || module_id == 101){
					project_table = "Td01_xmxx";
				}
				else{
					project_table = "Td00_gcxx";
				}
				
				sql.delete(0, sql.length());
				sql.append("select id ");
				sql.append("from "+project_table+" pt ");
				sql.append("where pt.gclb like '%设备%' and pt.id = ");
				sql.append(project_id);
				List list = queryService.searchList(sql.toString());
				if(list != null && list.size() > 0 ){
					return true;
				} 
				else{
					return false;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
}
