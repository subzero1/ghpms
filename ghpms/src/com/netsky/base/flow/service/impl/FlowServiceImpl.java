package com.netsky.base.flow.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netsky.base.flow.buttonControl.Button;
import com.netsky.base.flow.component.FlowBussines;
import com.netsky.base.flow.service.FlowService;

/**
 * @description:
 * 
 * @class name:com.netsky.base.flow.service.impl.FlowServiceImpl
 * @author wind Jan 12, 2010
 */
@Service
public class FlowServiceImpl implements FlowService {

	/**
	 * flowService 代理类
	 */
	@Autowired
	FlowBussines flowBussines;
	/**
	 *　重载方法：CreateForm
	 * (non-Javadoc)
	 * @throws Exception 
	 * @see com.netsky.base.flow.service.FlowService#CreateForm(java.util.Map)
	 */
	public boolean CreateForm(Map paraMap) throws Exception {
		//project_id,doc_id,module_id,node_id,user_id,user_name;
		return flowBussines.CreateForm(paraMap);
	}

	/**
	 *　重载方法：listButtons
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.service.FlowService#listButtons(java.util.Map)
	 */
	public List<Button> listButtons(Map paraMap) {
		return flowBussines.listButtons(paraMap);
	}

	/**
	 *　重载方法：listNewFormButtons
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.service.FlowService#listNewFormButtons(java.util.Map)
	 */
	public List<Button> listNewFormButtons(Map paraMap) {
		return flowBussines.listNewFormButtons(paraMap);
	}

	/**
	 *　重载方法：CreateForm
	 * (non-Javadoc)
	 * @throws Exception 
	 * @see com.netsky.base.flow.service.FlowService#CreateForm(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public boolean CreateFormByString(String project_id, String doc_id, String module_id, String node_id, String user_id,
			String user_name, String preOpernode_id) throws Exception {
		if (project_id == null || "-1".equals(project_id) || (doc_id == null || "-1".equals(doc_id))
				||module_id == null || "-1".equals(module_id) || node_id == null || "-1".equals(node_id)
				||user_id == null || "-1".equals(user_id)) {
			return false;
		}
		
		Map paraMap = new HashMap();
		paraMap.put("project_id", project_id);
		paraMap.put("doc_id", doc_id);
		paraMap.put("module_id", module_id);
		paraMap.put("node_id", node_id);
		paraMap.put("user_name", user_name);
		paraMap.put("user_id", user_id);
		paraMap.put("preOpernode_id", preOpernode_id);
		return flowBussines.CreateForm(paraMap);
	}

	public List<Button> listActions(Map paraMap) {
		return flowBussines.ListActions(paraMap);
	}

}
