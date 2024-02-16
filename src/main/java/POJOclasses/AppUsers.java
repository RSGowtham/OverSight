//$Id$
package POJOclasses;


public class AppUsers extends MasterClass{
	private String username,email,password,contactnumber;
	private int appuserid;
	private long CreatedTime,ModifiedTime,dateofbirth;
	public AppUsers()
	{
		super();
	}
	@Override
	public String getTableName() {
		return  this.getClass().getSimpleName().toLowerCase();
	}
	public int getAppUserId(){
		return this.appuserid;
	}
	public void setAppUserId(int appuserid){
		this.setColumnNames("AppUserId");
		this.appuserid = appuserid;
	}

	public String getUserName(){
		return this.username;
	}
	public void setUserName(String username){
		this.setColumnNames("UserName");
		this.username = username;
	}
	public String getEmail(){
		return this.email;
	}
	public void setEmail(String email){
		this.setColumnNames("Email");
		this.email = email;
	}
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.setColumnNames("Password");
		this.password = password;
	}
	public String getContactNumber(){
		return this.contactnumber;
	}
	public void setContactNumber(String contactnumber){
		this.setColumnNames("ContactNumber");
		this.contactnumber = contactnumber;
	}
	public long getDateOfBirth() {
		return dateofbirth;
	}
	public void setDateOfBirth(long dateofbirth) {
		this.setColumnNames("DateOfBirth");
		this.dateofbirth = dateofbirth;
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
