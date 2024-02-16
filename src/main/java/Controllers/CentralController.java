//$Id$
package Controllers;

import org.json.JSONObject;

public interface CentralController {
	public String get(String uri);
	public String getAll(String uri);
	public String post(String uri,JSONObject payload);
	public String postAll(String uri,JSONObject payload);
	public String delete(String uri);
	public String put(String uri,JSONObject payload);
	public String putAll(String uri,JSONObject payload);

}
