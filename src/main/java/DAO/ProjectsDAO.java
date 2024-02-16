//$Id$
package DAO;

import java.util.LinkedHashMap;

import org.json.JSONObject;
import POJOclasses.MasterClass;
import POJOclasses.Projects;
import Utility.Components;

public class ProjectsDAO implements MasterDAO
{
	
	public ProjectsDAO()
	{
		
	}
	
	public String insert(JSONObject payload)
	{
		String resp=null;
		try
		{
			
			 MasterClass obj = new Projects();
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
