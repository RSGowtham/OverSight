//$Id$
package POJOclasses;

public class Projects extends MasterClass{
	
	private String projectname;
	private int projectid,departmentid;
	private double revenue;
	private boolean projectstate;
	private long CreatedTime,ModifiedTime;
	
	public Projects()
	{
		super();
	}
	@Override
	public String getTableName() {
		return this.getClass().getSimpleName().toLowerCase();
	}
	public String getProjectName() {
		return projectname;
	}
	public void setProjectName(String projectname) {
		this.setColumnNames("ProjectName");
		this.projectname = projectname;
	}
	public int getProjectId() {
		return this.projectid;
	}
	public void setProjectId(int projectid) {
		this.setColumnNames("ProjectId");
		this.projectid = projectid;
	}
	public int getDepartmentId() {
		return this.departmentid;
	}
	public void setDepartmentId(int departmentid) {
		this.setColumnNames("DepartmentId");
		this.departmentid = departmentid;
	}
	public double getRevenue() {
		return this.revenue;
	}
	public void setRevenue(double revenue) {
		this.setColumnNames("Revenue");
		this.revenue = revenue;
	}
	public boolean getProjectState() {
		return this.projectstate;
	}
	public void setProjectState(boolean projectstate) {
		this.setColumnNames("ProjectState");
		this.projectstate = projectstate;
	}
	public long getCreatedTime(){
		return this.CreatedTime;
	}
	public void setCreatedTime(){
		this.setColumnNames("CreatedTime");
		this.CreatedTime = System.currentTimeMillis();
	}
	public long getModifiedTime(){
		return this.ModifiedTime;
	}
	public void setModifiedTime(long modifiedTime){
		this.setColumnNames("ModifiedTime");
		this.ModifiedTime = modifiedTime;
	}
}
