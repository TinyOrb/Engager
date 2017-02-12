package xyz.tinyorb.noLogin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import xyz.tinyorb.hibernate.App.ProfileManager;
import xyz.tinyorb.hibernate.entity.userprofile;

/**
 * Servlet implementation class UserProfileFetcher
 */
@WebServlet("/UserProfileFetcher")
public class UserProfileFetcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileFetcher() {
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession(false);
		
		if(session != null)
		{
			String username = (String) session.getAttribute("username");
			
			userprofile profile = new ProfileManager().getUserProfile(username);
			if(profile == null)
			{
				JSONObject jObj = new JSONObject();
				
				jObj.put("fname", "");
				jObj.put("lname", "");
				jObj.put("dob", "");
				jObj.put("country", "");
				jObj.put("state", "");
				jObj.put("gender", "");
				jObj.put("Amail", "");
				
				String result = JSONValue.toJSONString(jObj);
				
				response.getWriter().write(result);
			}
			else
			{
				JSONObject jObj = new JSONObject();
				
				jObj.put("fname", profile.getFirstname() != null ? profile.getFirstname() : "");
				jObj.put("lname", profile.getLastname() != null ? profile.getLastname() : "");
				jObj.put("dob", profile.getDOB() != null ? profile.getDOB().toString() : "");
				jObj.put("country", profile.getCountry() != null ? profile.getCountry() : "");
				jObj.put("state", profile.getState() != null ? profile.getState() : "");
				jObj.put("gender", profile.getGender() != null ? profile.getGender() : "");
				jObj.put("Amail", profile.getaEmail() != null ? profile.getaEmail() : "");
				
				String result = JSONValue.toJSONString(jObj);
				
				response.getWriter().write(result);
			}
			
			
		}
		else
		{
			response.getWriter().write("Unauthorize");
		}
	}

}
