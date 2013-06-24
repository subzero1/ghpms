package com.netsky.base.flow.buttonControl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb03_relation;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.utils.FtpUtil;
import com.netsky.base.baseObject.ResultObject;

/**
 * @description:
 * 判断发送是否满足条件，如果不满足，给予提醒或不显示按扭
 * @class name:com.netsky.base.flow.buttonControl.SendShowMessage
 * @author leexiangyu Oct 09, 2011
 */
public class SendShowMessage extends ButtonControl {
	
	/**
	 * 判断发送是否满足条件，如果不满足，给予提醒
	 * 参数中需包含：project_id,relation_id
	 *　重载方法：checkCondition
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#checkCondition(java.util.Map)
	 */
	private  Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public String checkCondition(Map paraMap) {
		
		String returnStr = "OK";
		Long relation_id = MapUtil.getLong(paraMap,"relation_id");
		Long project_id = MapUtil.getLong(paraMap,"project_id");
		Long module_id = MapUtil.getLong(paraMap,"module_id");
		Long doc_id = MapUtil.getLong(paraMap,"doc_id");
		StringBuffer hsql = new StringBuffer();
		
		Tb03_relation tb03 = (Tb03_relation)queryService.searchById(Tb03_relation.class, relation_id);
		String relation_desc = convertUtil.toString(tb03.getDescription());
		String relatioin_name = convertUtil.toString(tb03.getName());
		
		/**
		 * 不选择设计单位不能继续走流程
		 */
		if (module_id == 101) {
			
			if(relatioin_name.trim().indexOf("下发设计") != -1){
				hsql.delete(0, hsql.length());
				hsql.append("select id ");
				hsql.append("from Td01_xmxx td01 ");
				hsql.append("where td01.sjdw is null ");
				hsql.append("and td01.id = ");
				hsql.append(project_id);
				
				List list = queryService.searchList(hsql.toString());
				
				if(list != null && list.size() > 0 ){
					returnStr = "请先选择【设计单位】，并点上方【保存】按钮!";
				} else {
					hsql.delete(0, hsql.length());
					hsql.append("select id ");
					hsql.append("from Td00_gcxx td00 ");
					hsql.append("where td00.xm_id = ");
					hsql.append(project_id);
					list = queryService.searchList(hsql.toString());
					if(list == null || list.size() == 0){
						returnStr ="请先点击右上方【选择打包工程】，打包完成后才可以下发!";
					}
					else{
						returnStr ="OK";
					}
					
				}
			}
			
			else if(relatioin_name.trim().indexOf("下发施工") != -1){
				hsql.delete(0, hsql.length());
				hsql.append("select id ");
				hsql.append("from Td01_xmxx td01 ");
				hsql.append("where td01.sgdw is null ");
				hsql.append("and td01.id = ");
				hsql.append(project_id);
				
				List list = queryService.searchList(hsql.toString());
				
				if(list != null && list.size() > 0 ){
					returnStr = "请先选择【施工单位】，并点上方【保存】按钮!";
				} else {
					returnStr ="OK";
				}
			}
			
			else if(relatioin_name.trim().indexOf("下发监理") != -1){
				hsql.delete(0, hsql.length());
				hsql.append("select id ");
				hsql.append("from Td01_xmxx td01 ");
				hsql.append("where td01.jldw is null ");
				hsql.append("and td01.id = ");
				hsql.append(project_id);
				
				List list = queryService.searchList(hsql.toString());
				
				if(list != null && list.size() > 0 ){
					returnStr = "请先选择【监理单位】，并点上方【保存】按钮!";
				} else {
					returnStr ="OK";
				}
			}
		}
		
		/**
		 * 不选择设计单位不能继续走流程
		 */
		if (module_id == 102) {
			
			if(relatioin_name.trim().indexOf("下发设计") != -1){
				hsql.delete(0, hsql.length());
				hsql.append("select id ");
				hsql.append("from Td00_gcxx td00 ");
				hsql.append("where td00.sjdw is null ");
				hsql.append("and td00.id = ");
				hsql.append(project_id);
				
				List list = queryService.searchList(hsql.toString());
				
				if(list != null && list.size() > 0 ){
					returnStr = "请先选择【设计单位】，并点上方【保存】按钮!";
				} else {
					returnStr ="OK";
				}
			}
			
			else if(relatioin_name.trim().indexOf("下发施工") != -1){
				hsql.delete(0, hsql.length());
				hsql.append("select id ");
				hsql.append("from Td00_gcxx td00 ");
				hsql.append("where td00.sgdw is null ");
				hsql.append("and td00.id = ");
				hsql.append(project_id);
				
				List list = queryService.searchList(hsql.toString());
				
				if(list != null && list.size() > 0 ){
					returnStr = "请先选择【施工单位】，并点上方【保存】按钮!";
				} else {
					returnStr ="OK";
				}
			}
			
			else if(relatioin_name.trim().indexOf("下发监理") != -1){
				hsql.delete(0, hsql.length());
				hsql.append("select id ");
				hsql.append("from Td00_gcxx td00 ");
				hsql.append("where td00.jldw is null ");
				hsql.append("and td00.id = ");
				hsql.append(project_id);
				
				List list = queryService.searchList(hsql.toString());
				
				if(list != null && list.size() > 0 ){
					returnStr = "请先选择【监理单位】，并点上方【保存】按钮!";
				} else {
					returnStr ="OK";
				}
			}
			
			else if(relatioin_name.trim().indexOf("上报审核") != -1 && relation_desc.trim().indexOf("设计人员") != -1){
				boolean noHaveYs = false;
				boolean noHaveTz = false;
				boolean noHaveSm = false;
				
				hsql.delete(0, hsql.length());
				hsql.append("select id ");
				hsql.append("from Te03_gcgys_zhxx te03 ");
				hsql.append("where te03.gc_id = ");
				hsql.append(project_id);
				List list = queryService.searchList(hsql.toString());
				if(list == null || list.size() == 0 ){
					noHaveYs = true;
				} 
				
				if(noHaveYs){
					returnStr = "请先选择【预算上传】，导入预算!";
				}
				else{
					returnStr ="OK";
				}
			}
		}
		
		/**
		 * 不选择设计单位不能继续走流程
		 */
		if (module_id == 104 || module_id == 107) {

			if(relatioin_name.trim().indexOf("验收申请") != -1 && relation_desc.trim().indexOf("验收人员") != -1){
				boolean isSbXm = false;
				boolean isExamSecurity = false;
				String project_table = "Td01_xmxx"; 
				if(module_id == 104){
					project_table = "Td01_xmxx";
				}
				else{
					project_table = "Td00_gcxx";
				}
				
				hsql.delete(0, hsql.length());
				hsql.append("select id ");
				hsql.append("from "+project_table+" pt ");
				hsql.append("where pt.gclb like '%设备%' and pt.id = ");
				hsql.append(project_id);
				List list = queryService.searchList(hsql.toString());
				if(list != null && list.size() > 0 ){
					isSbXm = true;
				} 
				
				hsql.delete(0, hsql.length());
				hsql.append("from Tb15_docflow where node_id in(10404,10704) and approve_result = 4 and project_id =  ");
				hsql.append(project_id);
				list = queryService.searchList(hsql.toString());
				if(list != null && list.size() > 0 ){
					isExamSecurity = true;
				} 
				
				if(isSbXm && !isExamSecurity){
					returnStr = "请先走【安全验收】流程!";
				}
				else{
					returnStr ="OK";
				}
			}
		}

		return returnStr;	
	}

	/**
	 *　重载方法：isShow
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#getUsers(java.util.Map)
	 */
	@Override
	public boolean isShow(Map paraMap) {
		
		return true;
	}

	
	/**
	 *　重载方法：getUsers
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#getUsers(java.util.Map)
	 */
	@Override
	public List<Ta03_user> getUsers(Map paraMap) {
		
		return null;
	}

}
