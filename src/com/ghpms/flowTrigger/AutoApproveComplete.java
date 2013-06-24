package com.ghpms.flowTrigger;

import java.util.Map;

import com.netsky.base.flow.component.Archive;
import com.netsky.base.flow.component.ApproveComplete;
import com.netsky.base.flow.component.DoDbTrigger;
import com.netsky.base.flow.component.FlowBussines;
import com.netsky.base.flow.trigger.Trigger;

public class AutoApproveComplete extends Trigger {

	@Override
	public String process(Map paraMap) throws Exception {
		
		/**
		 * �������
		 * */
		ApproveComplete acp = new ApproveComplete();
		acp.setQueryService(queryService);
		acp.setSaveService(saveService);
		DoDbTrigger dt = new DoDbTrigger();
		dt.setJdbcSupport(jdbcSupport);
		dt.setQueryService(queryService);
		dt.setSaveService(saveService);
		acp.setDoTrigger(dt);
		FlowBussines fb = new FlowBussines();
		fb.setQueryService(queryService);
		fb.setSaveService(saveService);
		
		Archive archive = new Archive();
		archive.setDoTrigger(dt);
		archive.setQueryService(queryService);
		archive.setSaveService(saveService);
		archive.setFlowBussines(fb);
		acp.setArchive(archive);
		
		acp.handleRequest(paraMap);
		return null;
	}

	@Override
	public String unProcess(Map paraMap) throws Exception {
		return null;
	}

}
