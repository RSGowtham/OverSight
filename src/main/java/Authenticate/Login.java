//  $Id$
package Authenticate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import DAO.AppUsersDAO;
import DBconnection.DBUtili;
import POJOclasses.AppUsers;
import POJOclasses.MasterClass;
import Utility.Components;

public class Login extends BusinessLayer{
	
	public boolean login(JSONObject payload)
	{
		MasterClass obj = new AppUsers();
		
		boolean result=false;
		try
		{
			String email,newpassword="";
			Set<String> keys = payload.keySet();
			for (String key : keys) {
				if(key.equals("password"))
				 newpassword = payload.getString(key);
			}
		LinkedHashMap<String, Object> values = Components.listgenerator(payload);
		System.out.println("In Login Business Layer");
		
		for(String key:values.keySet())
		{
			String forWhereColumnName=Components.converter(key);
				if(key.equals("Email"))
				{
					email=(String)values.get(key);
					obj.setwhere(forWhereColumnName, values.get(key));
				}
  		}
  		if(Components.pojoInserter(obj, values))
  		{
  			
  			AppUsersDAO obj2=new AppUsersDAO();
  			List<Map<String, Object>> container=obj2.verifyUser(obj);
  
  			Map<String,Object> value=container.get(0);
  			for (String key : value.keySet()) {
  			    Object val = value.get(key);
  			    if(key.equals("password"))
  			        {
  			    	    String oldpassword="";
  			    	    oldpassword+=val;
  			        	if(Login.checkPassword(newpassword, oldpassword))
  			        	 return true;
  			        	else
  			        	System.err.println("SomeThing went WRONG");
  			        	  return false;
  			        }
  			}
  			
  
  		}
		}
		catch(Exception e)
		{
			result=false;
		}
		return result;
	}
    //   Method to check if a password matches its hash
    private static boolean checkPassword(String plainPassword, String hashedPassword) {
    	System.out.println("In Bcry Check Password Method");
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

}
