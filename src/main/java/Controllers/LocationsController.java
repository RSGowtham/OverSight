//$Id$
package Controllers;
import POJOclasses.*;
public class LocationsController implements CentralController{
	
	
	public boolean filterParser(String uri,Locations obj)
	{
		String uriparts[]=uri.split("?");
		String quest[]=uriparts[1].split("&");
		for(String key_value:quest)
		{
			String pair[]=key_value.split("=");
			int id=Integer.parseInt(pair[1]);
			obj.setwhere(pair[0], id);
		}
		
		return true;
	}

	@Override
	public String processor(String uri) {
		
		Locations obj  = new Locations();
		
		if(uri.contains("?"))
		{
			this.filterParser(uri,obj);
		}
		
		String uriparts[]=uri.split("/");
		
		for(int i=0;i<uriparts.length-1;i++)
		{
			if(uriparts[i].equals("companies"))
			{
				int id=Integer.parseInt(uriparts[i+1]);
				obj.setwhere("company_id", id);
			}
			
			
		}

		return new String();
		
	}
	
	
	

}
