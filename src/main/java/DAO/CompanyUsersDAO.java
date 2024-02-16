//$Id$
package DAO;

import java.util.LinkedHashMap;

import org.json.JSONObject;

import POJOclasses.CompanyUsers;
import POJOclasses.MasterClass;

import Utility.Components;

public class CompanyUsersDAO implements MasterDAO
{
	
	public CompanyUsersDAO()
	{
		
	}
	
	public String insert(JSONObject payload)
	{
		String resp=null;
		try
		{
			
			 MasterClass obj = new CompanyUsers();
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