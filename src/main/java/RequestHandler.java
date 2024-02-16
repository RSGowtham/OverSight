//$Id$

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import Controllers.CentralController;
import DAO.MasterDAO;
import Utility.Components;

public class RequestHandler {

	public static String className(String uri)
	{
		String classname=null;
			String uriparts[] = uri.split("\\?");
			String uripart1[]=uriparts[0].split("/");
			int size = uripart1.length;
			
			if(uri.contains("companies")&& uri.contains("users"))
			{
				classname="Company.Companies";
			}
			else if(uri.contains("users"))
			{
				classname="OverSight.AppUsers";
			}
			else
			{
				classname=Components.toClassName(uripart1[size - 1]);	
			}

		return classname;
	}
	public static String router(HttpServletRequest request)
	{
		
		String method=request.getMethod();
		String uri=request.getRequestURI();
		String path="Controllers."+className(uri)+"Controller";
		String jsonString = request.getReader().lines().collect(Collectors.joining());
		JSONObject payload = new JSONObject(jsonString);
		Class<?> clazz = Class.forName(path);
		CentralController resourceInstance = (CentralController) clazz.newInstance();
		System.err.println(path+" Method :"+method);
		String values=null;
		if(method.equals("GET"))
		{
			 
			if(singleOrMultiple(uri))
			{
				values=resourceInstance.get(uri);
			}
			else
			{
				values=resourceInstance.getAll(uri);
			}
			
		}
		else if(method.equals("POST"))
		{
			if(singleOrMultiple(uri))
			{
				values=resourceInstance.post(uri,payload);
			}
			else
			{
				values=resourceInstance.postAll(uri,payload);
			}
			
		}
		else if(method.equals("PUT"))
		{
			if(singleOrMultiple(uri))
			{
				values=resourceInstance.put(uri,payload);
			}
			else
			{
				values=resourceInstance.putAll(uri,payload);
			}
		}
		else if(method.equals("DELETE"))
		{
			values=resourceInstance.delete(uri,payload);
			
		}
		else
		{
			return "{'error':'No Method Present'}";
		}
		
		
		
		return new String();
	}
	
}
