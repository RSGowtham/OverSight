//$Id$
package POJOclasses;
import java.util.LinkedHashMap;
import java.util.LinkedList;



public abstract class MasterClass {
	private boolean join=false,count=false;
	private int wherecondition;
	private String tablenametwo,commonfield;
	private LinkedList<String> columnname;
	private LinkedHashMap<String,Object> where;
//	private LinkedHashMap<String,Object> update;
	
	public MasterClass() {
		columnname=new LinkedList<String>();
    	where=new LinkedHashMap<String,Object>();
//    	update=new LinkedHashMap<String, Object>();
	}
	
	abstract  public String getTableName();

	public void setCount()
	{
		this.count=true;
	}
	public boolean getCount()
	{
		return this.count;
	}
	public void setJoin()
	{
		this.join=true;
	}
	
	public boolean getJoin()
	{
		return this.join;
	}
	
	public void setWhereCondition(int value)
	{
		this.wherecondition=value;
	}
	
	public int getWhereCondition()
	{
		return this.wherecondition;
	}
	
	public String getCommonField()
	{
		return this.commonfield;
	}
	
    public void setTableNameTwo(String table,String commonfield,int wherecondition)
    {
    	this.tablenametwo=table;
    	this.commonfield=commonfield;
    	this.wherecondition=wherecondition;
    	this.join=true;
    }
    
    public String getTableNameTwo()
    {
    	return this.tablenametwo;
    }
	
	public void setColumnNames(String col)
	{
		this.columnname.add(col);
	}
	
	public LinkedList<String> getColumnNames()
	{
		return this.columnname;
	}
	
	public LinkedHashMap<String,Object>  getwhere() {

		return this.where;
	}
	
	public void setwhere(String key,Object value) {
		this.where.put(key,value);
	}
	
//	 
//	public LinkedHashMap<String,Object> getupdate() {
//
//		return this.update;
//	}
//
//	 
//	public void setupdate(String key,Object value) {
//		LinkedHashMap<String,Object> k=new LinkedHashMap<String, Object>();
//		this.update.put(key,value);
//	}
	
}
