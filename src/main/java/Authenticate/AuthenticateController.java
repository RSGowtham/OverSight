//$Id$

package Authenticate;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import DAO.MasterDAO;
import Utility.Components;

public class AuthenticateController {
	
	public static String router(JSONObject payload,String uri)
	{
		
		String uriparts[] = uri.split("/");
		int size = uriparts.length;
		String value = null;
		try {
			if(uriparts[size - 1].equals("registration"))
			{
				Registration reg=new Registration();
				if(reg.signup(payload))
				{
					return "{'message':'welcome'}";
				}
				else
				{
					return "{'error':'User Already Exit'}";
				}
			}
			else if(uriparts[size - 1].equals("login"))
			{
				Login log=new Login();
				if(log.login(payload))
				{
					System.out.println("In Login If else condition");
					return "{'message':'welcome'}";
					
				}
				else
				{
					return "{'error':'No User Exit'}";
				}
			}
			else if(uriparts[size - 1].equals("invite"))
			{
				Invite inv=new Invite();
				if(inv.requesting(payload))
				{
					System.out.println("In Login If else condition");
					return "{'message':'welcome'}";
					
				}
				else
				{
					return "{'error':'No User Exit'}";
				}
			}
//			else if(uriparts[size - 1].equals("invite"))
//			{
//				Invite inv=new Invite();
//				value=inv.requesting(payload);
//			}
//			else
//			{
//				value="{'error':'Not Prover Url :('}";
//			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		return value;
		
	}
	private static boolean checkPassword(String plainPassword, String hashedPassword) {
    	System.out.println("In Bcry Check Password Method");
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

}
