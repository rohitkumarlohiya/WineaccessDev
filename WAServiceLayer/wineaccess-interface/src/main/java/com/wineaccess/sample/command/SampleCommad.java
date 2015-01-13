package com.wineaccess.sample.command;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.orchestration.commad.context.RequestContext;
import com.wineaccess.orchestration.commad.context.RequestHeader;
import com.wineaccess.orchestration.core.constants.ComponentConstants;
import com.wineaccess.orchestration.data.repository.DataRepositoryManager;
import com.wineaccess.orchestration.workflow.pipeline.PipelineManager;
import com.wineaccess.property.utils.PropertyholderUtils;

/**
 * @author jyoti.yadav
 */
@Path("/{v1}/{command}")
public class SampleCommad {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Object execute(@PathParam("v1") String version, @PathParam("command") String command) {
		PipelineManager pipelineManager = (PipelineManager) CoreBeanFactory.getBean(PropertyholderUtils.getStringProperty(ComponentConstants.PIPELINE_MANAGER));
		RequestHeader rHeader = new RequestHeader();
		Map<String, Object> contextParameters = new HashMap<String, Object>();
		RequestContext rContext = new RequestContext(rHeader, "Test", version, contextParameters);
		
		
		pipelineManager.process(rContext);
		
		DataRepositoryManager dataRepositoryManager = (DataRepositoryManager) CoreBeanFactory.getBean(PropertyholderUtils.getStringProperty(ComponentConstants.DATA_REPOSITORY_MANAGER));
		
		return   dataRepositoryManager.getOutput(rContext.getMessageHeader().getRequestId()).get("FINAL-RESPONSE");
	}
}


