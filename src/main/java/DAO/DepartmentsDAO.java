//$Id$
package DAO;

import java.util.LinkedHashMap;

import org.json.JSONObject;

import com.google.gson.JsonObject;
import POJOclasses.Departments;
import POJOclasses.MasterClass;
import Utility.Components;

public class DepartmentsDAO implements MasterDAO
{

	public DepartmentsDAO()
	{
		
	}
	
	public String insert(JSONObject payload)
	{
		String resp=null;
		try
		{
			
			 MasterClass obj = new Departments();
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


