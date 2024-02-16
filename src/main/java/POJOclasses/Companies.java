//$Id$
package POJOclasses;



public class Companies extends MasterClass{
	
	private String gstregistration,companyname;
	private int companyid;
	private long CreatedTime,ModifiedTime;
	public Companies()
	{
		super();
	}

	@Override
	public String getTableName() {
		return  this.getClass().getSimpleName().toLowerCase();
	}
	public String getGstRegistration() {
		return this.gstregistration;
	}
	public void setGstRegistration(String gstregistration) {
		this.setColumnNames("GstRegistration");
		this.gstregistration = gstregistration;
	}
	public String getCompanyName() {
		return this.companyname;
	}
	public void setCompanyName(String companyname) {
		this.setColumnNames("CompanyName");
		this.companyname = companyname;
	}
	public int getCompanyId() {
		return this.companyid;
	}
	public void setCompanyId(int companyid) {
		this.setColumnNames("CompanyId");
		this.companyid = companyid;
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
