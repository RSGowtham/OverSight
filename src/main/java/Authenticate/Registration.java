//$Id$
package Authenticate;
import DAO.MasterDAO;
import DBconnection.DBUtili;

import java.util.LinkedHashMap;

import org.json.JSONObject;

import DAO.AppUsersDAO;
import POJOclasses.AppUsers;
import POJOclasses.MasterClass;
import Utility.Components;

public class Registration extends BusinessLayer{
	
	public boolean signup(JSONObject payload)
	{
		MasterClass obj = new AppUsers();
		
		boolean result=false;
		try
		{
		LinkedHashMap<String, Object> values = Components.listgenerator(payload);
		System.out.println("In Registration Business Layer");
		for(String key:values.keySet())
		{
			if(key.equals("Email"))
			{
				obj.setwhere(key, values.get(key));
			}
			
			System.out.println(key+" "+values.get(key));
		}
		if(Components.pojoInserter(obj, values))
		{
			
			AppUsersDAO obj2=new AppUsersDAO();
			if(obj2.isUserExists(obj))
			{
				obj.getwhere().clear();
				System.out.println("New users ??????");
				if(DBUtili.insert(obj))
				{
					result=true;
				}
			}
			else
			{
				System.out.println("Old Users");
			}
		}
		else
		{
			result=false;
		}
		}
		catch(Exception e)
		{
			result=false;
		}
		
		
		
		return result;
	}
	

}
