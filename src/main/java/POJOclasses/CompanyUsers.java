//$Id$
package POJOclasses;

public class CompanyUsers extends MasterClass{
	
	private int companyuserid,userid,cldid,role,companyuserstate;
	private boolean isactive;
	private long dateofjoin,CreatedTime,ModifiedTime;
	
	@Override
	public String getTableName() {
		return  this.getClass().getSimpleName().toLowerCase();
	}
	public int getCompanyUserId() {
		return this.companyuserid;
	}
	public void setCompanyUserId(int companyuserid) {
		this.setColumnNames("CompanyUserId");
		this.companyuserid = companyuserid;
	}
	public int getUserId() {
		return this.userid;
	}
	public void setUserId(int userid) {
		this.setColumnNames("UserId");
		this.userid = userid;
	}
	public int getCLDId() {
		return this.cldid;
	}
	public void setCLDId(int cldid) {
		this.setColumnNames("CLDId");
		this.cldid = cldid;
	}
	public int getRole() {
		return this.role;
	}
	public void setRole(int role) {
		this.setColumnNames("Role");
		this.role = role;
	}
	public int getCompanyUserState() {
		return this.companyuserstate;
	}
	public void setCompanyUserState(int companyuserstate) {
		this.setColumnNames("CompanyUserState");
		this.companyuserstate = companyuserstate;
	}
	public boolean getIsActive() {
		return this.isactive;
	}
	public void setIsActive(boolean isactive) {
		this.setColumnNames("IsActive");
		this.isactive = isactive;
	}
	public long getDateOfJoin() {
		return this.dateofjoin;
	}
	public void setDateOfJoin(long dateofjoin) {
		this.setColumnNames("DateOfJoin");
		this.dateofjoin = dateofjoin;
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
