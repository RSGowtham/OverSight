//$Id$
package POJOclasses;

public class Locations  extends MasterClass{
	
	private String locationname,locationaddress;
	private int locationid;
	
	private long CreatedTime,ModifiedTime;
	public Locations()
	{
		super();
	}

	@Override
	public String getTableName() {
		return  this.getClass().getSimpleName().toLowerCase();
	}

	public String getLocationName() {
		return this.locationname;
	}

	public void setLocationName(String locationname) {
		this.setColumnNames("LocationName");
		this.locationname = locationname;
	}

	public String getLocationAddress() {
		return this.locationaddress;
	}

	public void setLocationAddress(String locationaddress) {
		this.setColumnNames("LocationAddress");
		this.locationaddress = locationaddress;
	}

	public int getLocationId() {
		return this.locationid;
	}

	public void setLocationId(int locationid) {
		this.setColumnNames("LocationId");
		this.locationid = locationid;
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
