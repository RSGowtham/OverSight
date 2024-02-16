//$Id$
package DAO;

import java.util.LinkedHashMap;

import org.json.JSONObject;


import POJOclasses.Companies;
import POJOclasses.MasterClass;
import Utility.Components;

public class CompaniesDAO implements MasterDAO
{

	public CompaniesDAO()
	{
		
	}
	
	public String insert(JSONObject payload)
	{
		String resp=null;
		try
		{
			
			 MasterClass obj = new Companies();
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
