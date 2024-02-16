
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

//import DBconnection.DBUtili;
//import PojoClasses.Users;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/*")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result=RequestHandler.router(request);
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Write JSON data to response body
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		System.out.println("Filter works i am in TestServlet");
		String uri=request.getRequestURI();
		String jsonString = request.getReader().lines().collect(Collectors.joining());

		System.out.print("Servlet runs");
        // Parse the JSON string into a JsonObject
        JSONObject json = new JSONObject(jsonString);
 //       request.getMethod();
		String jsonData = DAOcontroller.postRequestRouter(uri, json);


		if(jsonData.length()>0)
		{
	         // Set content type and character encoding for the response
	         response.setContentType("application/json");
	         response.setCharacterEncoding("UTF-8");

	         // Write JSON data to response body
	         PrintWriter out = response.getWriter();
	         out.print(jsonData);
	         out.flush();
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 response.setStatus(HttpServletResponse.SC_OK);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

}
