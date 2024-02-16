//$Id$
package DAO;

import Utility.Components;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import DBconnection.DBUtili;
import POJOclasses.AppUsers;
import POJOclasses.MasterClass;

public class AppUsersDAO implements MasterDAO,AppUsersInterface {

	public AppUsersDAO() {

	}

	public String insert(JSONObject payload) {
		String resp = null;
		try {

			MasterClass obj = new AppUsers();
			LinkedHashMap<String, Object> values = Components.listgenerator(payload);
			if (Components.pojoInserter(obj, values)&& DBUtili.insert(obj)) {
	
					resp = "{'return':'Successfully Added'}";
			
				
			} else {
				resp = "{'return':'Failed'}";
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return resp;
	}
	
	public boolean isUserExists(MasterClass obj)
	{
		List<Map<String, Object>> result=DBUtili.select(obj);
		System.err.println(result);
		if(result.isEmpty())
		{
			return true;
		}
		else
		{
			
		}
		return false;
	}
	public List<Map<String, Object>> verifyUser(MasterClass obj)
	{
		return DBUtili.select(obj);
	}
	
}
