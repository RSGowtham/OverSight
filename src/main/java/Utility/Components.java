//$Id$
package Utility;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.LazilyParsedNumber;

import DBconnection.DBUtili;
import POJOclasses.AppUsers;
import POJOclasses.MasterClass;

public class Components {

	public static String toClassName(String value) {
//		char charaters[] = value.toCharArray();
//		StringBuilder classname = new StringBuilder();
//		for (int i = 0; i < charaters.length; i++) {
//			if (i == 0) {
//				classname.append(Character.toUpperCase(charaters[0]));
//			} else if (charaters[i] == '-') {
//				classname.append(Character.toUpperCase(charaters[i + 1]));
//				i += 1;
//			} else {
//				classname.append(charaters[i]);
//			}
//		}
//		return new String(classname);
		char charaters[] = value.toCharArray();
		charaters[0] = Character.toUpperCase(charaters[0]);
		return new String(charaters);
	}

	public static String converter(String value) {
		char charaters[] = value.toCharArray();
		StringBuilder column = new StringBuilder();
		for (int i = 0; i < charaters.length; i++) {
			if(i==0)
			{
				column.append(Character.toLowerCase(charaters[i]));	
			}
		   else if (charaters[i] >= 'A' && charaters[i] <= 'Z') {
				column.append("_" + Character.toLowerCase(charaters[i]));
			} else {
				column.append(charaters[i]);
			}
		}
		return new String(column);
	}

	public static String methodName(String value) {
		char charaters[] = value.toCharArray();
		charaters[0] = Character.toUpperCase(charaters[0]);
		return new String(charaters);
	}

	public static LinkedHashMap<String, Object> listgenerator(JSONObject payload) {
		LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
		Set<String> keys = payload.keySet();
		for (String key : keys) {
			Object value = payload.get(key);
			if(key.equals("password"))
			{
				String pass=hashPassword(payload.getString(key));
				values.put(Components.methodName(key), pass);
				System.err.println("Hashed Password : "+pass);
			}
			else if(value instanceof BigDecimal)
			{
				BigDecimal bigDecimalValue = (BigDecimal) value; // Cast Object to BigDecimal
	            double revenueDouble = bigDecimalValue.doubleValue();
				values.put(Components.methodName(key), revenueDouble);
			}
			else
			
			values.put(Components.methodName(key), value);
			}
		
		System.out.println("MMMMMMMMMMMMMMM\n"+values);
		
		return values;
	}

	public static boolean pojoInserter(MasterClass obj, LinkedHashMap<String, Object> keyvalues) {
		boolean state = false;

		for (Map.Entry<String, Object> entry : keyvalues.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			String setMethodName = "set" + key;

			// Get the class of the valuejson
			Class<?> valueType = value.getClass();

			System.out.println("Set Method name " + setMethodName + "\nValue Type : " + valueType + ", Object Value type : " + value);
			try {
				Method method=null;
				if (valueType == Integer.class) {
			        method = obj.getClass().getDeclaredMethod(setMethodName, int.class);
			    } else if (valueType == Long.class) {
			        method = obj.getClass().getDeclaredMethod(setMethodName, long.class);
			    }
			    else if(valueType == Boolean.class) {
			        method = obj.getClass().getDeclaredMethod(setMethodName, boolean.class);
			    }
			    else if(valueType ==Float.class)
			    {
			    	method = obj.getClass().getDeclaredMethod(setMethodName, float.class);
			    }
			    else if(valueType ==Double.class)
			    {
			    	method = obj.getClass().getDeclaredMethod(setMethodName, double.class);
			    }
			    else if(valueType ==BigDecimal.class)
			    {
			    	method = obj.getClass().getDeclaredMethod(setMethodName, double.class);
			    }
			    else
			    {
			     method = obj.getClass().getDeclaredMethod(setMethodName, String.class);
			    }
				if (method.getName().endsWith(setMethodName)) 
				{
					method.invoke(obj, value);
				}
			} catch (Exception e) {
				System.out.println(e);
			}

		}

		// Create Time
		try {
			Method method = obj.getClass().getDeclaredMethod("setCreatedTime");
			method.invoke(obj);
			state=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			state=false;
		}

//		if (DBUtili.insert(obj)) {
//			state = true;
//		}

		return state;
	}
	private static String hashPassword(String password) {
        // The higher the log rounds, the more secure but slower
        int logRounds = 12;

        return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
    }



}
