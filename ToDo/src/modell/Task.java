package modell;

import java.sql.Date;

public class Task {

	private int  userid;
	
	private Date dateCreated;
	private String description;
	private String task;
	

	
	public Task(int userid, Date dateCreated, String description, String task) {
		super();
		this.userid = userid;
		this.dateCreated = dateCreated;
		this.description = description;
		this.task = task;
	}
	
	public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Task() {
		
	}
	
	public String writeTask() {
		return description +"\n"+task +"\n"+dateCreated;
	}
}
