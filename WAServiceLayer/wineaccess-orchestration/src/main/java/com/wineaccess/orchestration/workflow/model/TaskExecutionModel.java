package com.wineaccess.orchestration.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wineaccess.data.model.user.Persistent;

/**
 * @author jyoti.yadav@globallogic.com
 */
@Entity
@Table(name = "SYS_TASK_EXECUTION_LOGS")
@NamedQueries({
				@NamedQuery(name = "getByRequestIdAndCurrentTask", query = "from TaskExecutionModel p where p.requestId = :requestId and p.currentTask = :currentTask"),
				@NamedQuery(name = "getByRequestIdAndNextTask", query = "from TaskExecutionModel p where p.requestId = :requestId and p.nextTask = :nextTask and taskStatus = :taskStatus"),
			})
public class TaskExecutionModel extends Persistent{

	private static final long serialVersionUID = -5757645597912789940L;

	public enum TaskStatus {
		P, C, D
	}
	
	public TaskExecutionModel() {
	}

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "REQUEST_ID", columnDefinition = "VARCHAR(200) NOT NULL")
	private String requestId;

	@Column(name = "CURRENT_TASK", columnDefinition = "VARCHAR(100) NOT NULL")
	private String currentTask;

	@Column(name = "NEXT_TASK", columnDefinition = "VARCHAR(100) NOT NULL")
	private String nextTask;

	// Status Will Be (P, C, D)
	@Column(name = "TASK_STATUS", columnDefinition = "VARCHAR(2) NOT NULL")
	private String taskStatus = "P";

	public TaskExecutionModel(String requestId, String currentTask, String nextTask) {
		this.requestId = requestId;
		this.currentTask = currentTask;
		this.nextTask = nextTask;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(String currentTask) {
		this.currentTask = currentTask;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getNextTask() {
		return nextTask;
	}

	public void setNextTask(String nextTask) {
		this.nextTask = nextTask;
	}
}
