//$Id$
package POJOclasses;

public class Departments extends MasterClass{
	
	private String departmentname;
	private int departmentid,managerid;
	private boolean ismainbranch=false;
	
	private long CreatedTime,ModifiedTime;
	public Departments()
	{
		super();
	}

	@Override
	public String getTableName() {
		return  getClass().getSimpleName().toLowerCase();
	}

	public String getDepartmentName() {
		return departmentname;
	}

	public void setDepartmentName(String departmentname) {
		this.setColumnNames("DepartmentName");
		this.departmentname = departmentname;
	}

	public int getDepartmentId() {
		return departmentid;
	}

	public void setDepartmentId(int departmentid) {
		this.setColumnNames("DepartmentId");
		this.departmentid = departmentid;
	}

	public int getManagerId() {
		return managerid;
	}

	public void setManagerId(int managerid) {
		this.setColumnNames("ManagerId");
		this.managerid = managerid;
	}

	public boolean getIsMainBranch() {
		return ismainbranch;
	}

	public void setIsMainBranch(boolean ismainbranch) {
		this.setColumnNames("IsMainBranch");
		this.ismainbranch = ismainbranch;
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
