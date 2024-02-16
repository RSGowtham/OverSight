//$Id$
package DAO;

import java.util.LinkedHashMap;

import org.json.JSONObject;

import com.google.gson.JsonObject;
import POJOclasses.Locations;
import POJOclasses.MasterClass;
import Utility.Components;

public class LocationsDAO implements MasterDAO
{

	public LocationsDAO()
	{
		
	}
	public String insert(JSONObject payload)
	{
		String resp=null;
		try
		{
			
			 MasterClass obj = new Locations();
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
