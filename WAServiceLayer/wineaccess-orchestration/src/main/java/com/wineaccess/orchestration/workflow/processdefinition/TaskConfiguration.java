package com.wineaccess.orchestration.workflow.processdefinition;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.globallogic.orch.PropertyType;
import com.globallogic.orch.TaskConfig;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class TaskConfiguration implements Serializable {

	private static final long serialVersionUID = 4815604612076841983L;
	
	private Map<String, String> properties = new HashMap<String, String>();
	private String onErrorAction;
	
	public TaskConfiguration(TaskConfig taskConfig) {
		if (taskConfig != null ) {
			this.onErrorAction = taskConfig.getOnError().getAction();
			for (PropertyType propertyType : taskConfig.getPropertyArray()) {
				this.properties.put(propertyType.getName(), propertyType.getValue());
			}
		}
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public String getOnErrorAction() {
		return onErrorAction;
	}
}
