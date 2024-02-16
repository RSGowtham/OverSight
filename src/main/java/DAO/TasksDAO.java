//$Id$
package DAO;

import java.util.LinkedHashMap;

import org.json.JSONObject;
import POJOclasses.MasterClass;
import POJOclasses.Tasks;
import Utility.Components;

public class TasksDAO implements MasterDAO
{
	
	public TasksDAO()
	{
		
	}
	
	public String insert(JSONObject payload)
	{
		String resp=null;
		try
		{
			
			 MasterClass obj = new Tasks();
			 LinkedHashMap<String,Object> values=Components.listgenerator(payload);	  
			  if(Components.pojoInserter(obj, values))
			  {
				  resp="{'return':'Successfully Added'}";
			  }
			  else
			  {
				  resp="{'return':'Failed'}";
			  }
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return resp;
	}

}
