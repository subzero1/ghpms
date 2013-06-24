package com.ghpms.flowTrigger;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import org.json.JSONObject;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Tz05_thread_queue;
import com.netsky.base.dataObjects.Ta06_module;

/**
 * 概预算接口
 * @author lee.xiangyu
 * @create 2010-02-04
 */
public class MsgPreSendAction extends com.netsky.base.flow.trigger.Trigger implements Serializable {

	
	public String process(Map paraMap) {
				
		StringBuffer sql = new StringBuffer("");

		Long project_id = MapUtil.getLong(paraMap,"project_id");
		Long node_id = MapUtil.getLong(paraMap,"node_id");
		Long module_id = MapUtil.getLong(paraMap,"module_id");
		Long doc_id = MapUtil.getLong(paraMap,"doc_id");
		Long opernode_id = convertUtil.toLong((String)paraMap.get("opernode_id"));
		String node_name = null;
		Integer approve_result = MapUtil.getInteger(paraMap,"approve_result");

		ResultObject ro = null;
		
		try{
			sql.append("select tb15.user_id as user_id,ta03.name as user_name,ta03.mobile_tel as tel ");
			sql.append(" from Tb13_operrelation tb13,Tb15_docflow tb15,Ta03_user ta03");
			sql.append(" where tb13.project_id="+project_id);
			sql.append(" and tb13.source_id="+opernode_id);
			sql.append(" and tb15.user_id = ta03.id ");
			sql.append(" and tb15.opernode_id=tb13.dest_id order by tb15.id");
			ro = queryService.search(sql.toString());
			Long user_id = -1L;
			String user_name = "";
			String user_tel = "";
			if(ro.next()){
				user_id = convertUtil.toLong(ro.get("user_id"));
				user_name = convertUtil.toString(ro.get("user_name"));
				user_tel = convertUtil.toString(ro.get("tel"));
			}
			if(user_id != -1L) {
				Ta06_module ta06 = (Ta06_module)queryService.searchById(Ta06_module.class, module_id);
				
				Tz05_thread_queue thread = new Tz05_thread_queue();
				thread.setInserttime(new Date());
				thread.setServicename("messageToPhoneService");
				JSONObject jo = new JSONObject();
				
				jo.put("content", "您好，一条"+ta06.getName()+"已发至您的待办事宜，请及时处理。");
				jo.put("sender_name", "管理员");
				jo.put("additionTel", "");
				jo.put("reader_tel", user_tel);
				jo.put("reader_name", user_name);
				
				thread.setRemark("流程提醒");
				thread.setStatus("未处理");
				thread.setType("sendTelMsg");
				thread.setParameters(jo.toString());
				saveService.save(thread);
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
