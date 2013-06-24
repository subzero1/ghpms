package com.netsky.base.flow.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import com.netsky.base.flow.buttonControl.Button;

/**
 * @description:
 * 网天公司流程服务接口
 * @class name:<br>com.netsky.base.flow.service.FlowService
 * @author wind Jan 11, 2010
 */
public interface FlowService {
	/**
	 * 获得流程按扭
	 * @方法名称:listButtons
	 * @param paraMap
	 * @return List<?>
	 */
	public List<Button> listButtons(Map paraMap);
	
	/**
	 * 获得流程按扭
	 * @方法名称:listButtons
	 * @param paraMap
	 * @return List<?>
	 */
	public List<Button> listActions(Map paraMap);	

	/**
	 * 获得流程新建表单项
	 * <p>返回Buttons 的List
	 * method:listNewFormButtons
	 * @param paraMap
	 * @return List<?>
	 */
	public List<Button> listNewFormButtons(Map paraMap);
	

	/**
	 * 创建表单流程
	 * @param project_id
	 * @param doc_id
	 * @param module_id
	 * @param node_id
	 * @param user_id
	 * @param user_name
	 * @param preOpernode_id
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean CreateFormByString(String project_id,String doc_id,String module_id,String node_id,String user_id,String user_name,String preOpernode_id) throws Exception;
	
	/**
	 * 创建表单流程
	 * <p>当新建表单时调用
	 * @param project_id,doc_id,module_id,node_id,user_id,user_name;
	 * @return
	 * @throws Exception 
	 */
	public boolean CreateForm(Map paraMap) throws Exception;
	
}
