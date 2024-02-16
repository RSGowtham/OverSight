//$Id$
package POJOclasses;

public class Tasks extends MasterClass{
	
	private String taskname;
	private int taskid,projectid,companyuserid,taskstate;
	private long CreatedTime,ModifiedTime;
	public Tasks()
	{
		super();
	}
	
	@Override
	public String getTableName() {
		return  this.getClass().getSimpleName().toLowerCase();
	}
	
	public String getTaskName() {
		return this.taskname;
	}

	public void setTaskName(String taskname) {
		this.setColumnNames("TaskName");
		this.taskname = taskname;
	}

	public int getTaskId() {
		return this.taskid;
	}

	public void setTaskId(int taskid) {
		this.setColumnNames("TaskId");
		this.taskid = taskid;
	}

	public int getProjectId() {
		return this.projectid;
	}

	public void setProjectId(int projectid) {
		this.setColumnNames("ProjectId");
		this.projectid = projectid;
	}

	public int getCompanyUserId() {
		return this.companyuserid;
	}

	public void setCompanyUserId(int companyuserid) {
		this.setColumnNames("CompanyUserId");
		this.companyuserid = companyuserid;
	}

	public int getTaskState() {
		return this.taskstate;
	}

	public void setTaskState(int taskstate ) {
		this.setColumnNames("TaskState");
		this.taskstate = taskstate ;
	}
	public long getCreatedTime(){
		return this.CreatedTime;
	}
	public void setCreatedTime(){
		this.setColumnNames("CreatedTime");
		CreatedTime = System.currentTimeMillis();
	}
	public long getModifiedTime(){
		return this.ModifiedTime;
	}
	public void setModifiedTime(long modifiedTime){
		this.setColumnNames("ModifiedTime");
		ModifiedTime = modifiedTime;
	}
}
