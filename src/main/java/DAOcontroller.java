//$Id$

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import Controllers.CentralController;
import Utility.Components;
import DAO.MasterDAO;

public class DAOcontroller {

	public static String postRequestRouter(String uri, JSONObject payload) {
		// TODO Auto-generated method stub
		String uriparts[] = uri.split("/");
		int size = uriparts.length;
		String path = "DAO." + Components.toClassName(uriparts[size - 1]) + "DAO";
		System.out.println("\n"+path);
		String value = null;
		try {
			Class<?> clazz = Class.forName(path);
			MasterDAO resourceInstance = (MasterDAO) clazz.newInstance(); 
			value=resourceInstance.insert(payload);
			System.out.println("Object by class.forname "+resourceInstance);

		} catch (Exception e) {
			System.out.println(e);
		}
		return value;
	}
	public static String getRequestRouter(String uri,JSONObject payload)
	{
		
		String uriparts[] = uri.split("/");
		int size = uriparts.length;
		String path="Controllers."+Components.toClassName(uriparts[size - 1])+"Controller";
		String value = null;
		try
		{
			Class<?> clazz = Class.forName(path);
			MasterDAO resourceInstance = (MasterDAO) clazz.newInstance(); 
			value=resourceInstance.processor(payload);
			System.out.println("Object by class.forname "+resourceInstance);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		
		return new String();
	}
	
	public static String router(HttpServletRequest request)
	{
		String method=request.getMethod();
		String uri=request.getRequestURI();
		if(method.equals("get"))
		{
			String uriparts[] = uri.split("/");
			int size = uriparts.length;
			String path="Controllers."+Components.toClassName(uriparts[size - 1])+"Controller";
			String value = null;
			try
			{
				Class<?> clazz = Class.forName(path);
				CentralController resourceInstance = (CentralController) clazz.newInstance(); 
				value=resourceInstance.processor(uri);
				System.out.println("Object by class.forname "+resourceInstance);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		
		return new String();
	}
	

}
