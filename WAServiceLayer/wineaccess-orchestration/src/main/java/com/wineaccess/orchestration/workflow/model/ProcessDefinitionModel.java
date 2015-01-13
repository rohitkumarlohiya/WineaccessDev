package com.wineaccess.orchestration.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.user.Persistent;

/**
 * Represent the process definition object in the database.
 * 
 * @author jyoti.yadav@globallogic.com
 */
@Entity
@Table(name = "SYS_PROCESS_DEFINITION")
@EntityListeners(EntityListener.class)
@NamedQueries(
			{
				@NamedQuery(name = "getByNameAndVersion", query = "from ProcessDefinitionModel p where p.processName = :processName and p.version = :version"),
				@NamedQuery(name = "getAllProcessDefinition", query = "from ProcessDefinitionModel"),
			}
)
public class ProcessDefinitionModel extends Persistent {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME", columnDefinition = "VARCHAR(100) NOT NULL")
	private String processName;
	
	@Column(name = "VERSION", columnDefinition = "VARCHAR(4) NOT NULL")
	private String version;
	
	@Column(name = "XML", columnDefinition = "LONGTEXT NOT NULL")
	private String xml;
	
	
	public ProcessDefinitionModel() {
	}
	
	
	public ProcessDefinitionModel(String processName, String version, String xml) {
		this.processName = processName;
		this.version = version;
		this.xml = xml;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}
}
