package com.ghpms.flowTrigger;

import java.io.Serializable;
import java.util.Map;

import com.netsky.base.flow.component.Turnback;
import com.netsky.base.flow.component.BaseUtil;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.baseObject.ResultObject;

/**
 * 概预算接口
 * @author lee.xiangyu
 * @create 2010-02-04
 */
public class ApproveSuccessAction extends com.netsky.base.flow.trigger.Trigger implements Serializable {

	public String process(Map paraMap) {
				
		StringBuffer sql = new StringBuffer("");
		Long project_id = MapUtil.getLong(paraMap,"project_id");
		Long node_id = MapUtil.getLong(paraMap,"node_id");
		Long module_id = MapUtil.getLong(paraMap,"module_id");
		Long user_id = MapUtil.getLong(paraMap,"user_id");
		Long doc_id = MapUtil.getLong(paraMap,"doc_id");
		String node_name = null;
		Integer approve_result = MapUtil.getInteger(paraMap,"approve_result");

		ResultObject ro = null;
		
		try{
			/**
			 * 获得节点名称
			 */
			sql.delete(0, sql.length());
			sql.append("select name from Tb02_node where id = ");
			sql.append(node_id);
			ro = queryService.search(sql.toString());
			if(ro.next()){
				node_name = convertUtil.toString((String)ro.get("name"));
			}

			/**
			 * 审结同意
			 */
			if(approve_result == 4){

//				if((module_id == 110 || module_id == 111) && node_name.indexOf("资源确认") != -1){
//					Ta03_user user = (Ta03_user)queryService.searchById(Ta03_user.class, user_id);
//					saveService.updateByHSql("update Td00_gcxx set zyqrsj = sysdate,zyyszt = '通过',zygly='"+user.getName()+"' where id = "+project_id);
//					saveService.updateByHSql("update Td01_xmxx set ycystg = '是' where ycystg is null and id = "+project_id);
//				}
			}
			
			/**
			 * 审结修改
			 */
			if(approve_result == 5){
				
				BaseUtil baseUtil = new BaseUtil();
				baseUtil.setQueryService(this.queryService);
				baseUtil.setSaveService(this.saveService);
				
				Turnback turnback = new Turnback();
				turnback.setQueryService(this.queryService);
				turnback.setSaveService(this.saveService);
				turnback.setBaseUtil(baseUtil);
				turnback.handleRequest(paraMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String unProcess(Map paraMap) {	

		return null;
	}
}
