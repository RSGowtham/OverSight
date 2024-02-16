
package Authenticate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class AuthenticateServlet
 */
//@WebServlet("/*")
public class AuthenticateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		String jsonString = request.getReader().lines().collect(Collectors.joining());
		JSONObject json = new JSONObject(jsonString);
		String result=AuthenticateController.router(json,uri);
		System.err.println("in Authenticate Servlet "+result);
//		response.sendRedirect("home.jsp");
		if(result.contains("welcome"))
		{
			response.sendRedirect("home.jsp");
		}
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Write JSON data to response body
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
	}

}
