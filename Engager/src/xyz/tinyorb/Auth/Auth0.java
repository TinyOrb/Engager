package xyz.tinyorb.Auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.tinyorb.hibernate.App.AuthenticUser;
import xyz.tinyorb.hibernate.App.ProfileManager;
import xyz.tinyorb.hibernate.entity.userprofile;

/**
 * Servlet implementation class Auth0
 */
@WebServlet("/Auth0")
public class Auth0 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth0() {
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession(false);
		String str = request.getParameter("x1");
		String str2 = request.getParameter("x2");
		//System.out.println(str+" "+ str2);
			String temp = new AuthenticUser().Auth(str, str2);
			
			if(temp.equals("successful"))
			{
				session = request.getSession(true);
				session.setAttribute("username", str);
				userprofile profile = new ProfileManager().getUserProfile(str);
				if(profile != null)
				{
					session.setAttribute("firstname", profile.getFirstname());
					session.setAttribute("lastname", profile.getLastname());
				}
				else{
					session.setAttribute("firstname", null);
					session.setAttribute("lastname", null);
				}
				session.setMaxInactiveInterval(60*60*24*365);
				response.getWriter().write((String)session.getAttribute("username"));
			}
			else
			{
				response.getWriter().write("");
			}
		
	}

}
