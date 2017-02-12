package xyz.tinyorb.noLogin;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.tinyorb.hibernate.App.ProfileManager;

/**
 * Servlet implementation class UserProfileUploader
 */
@WebServlet("/UserProfileUploader")
public class UserProfileUploader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileUploader() {
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
		
		if(session != null)
		{
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String country = request.getParameter("country");
			String state = request.getParameter("state");
			String Amail = request.getParameter("Amail");
			String gender = request.getParameter("gender");
			String stringdate = request.getParameter("dob");
			String username = (String) session.getAttribute("username");
			
			java.util.Date date = null;
			java.sql.Date sqlDate = null;
			//Date conversion
			DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
			if(stringdate != "")
			try {
				date = df.parse(stringdate);
				sqlDate = new java.sql.Date(date.getTime());
				System.out.println(stringdate + " " + date + " "+ sqlDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//send data to server
			String result = new ProfileManager().update(username, fname, lname, country, state, Amail, gender,sqlDate);
			
			if(result != null)
			{
				session.setAttribute("firstname", fname);
				session.setAttribute("lastname", lname);
				response.getWriter().write(result);
			}
			else
			{
				response.getWriter().write("something went wrong");
			}
		}
		else
		{
			response.getWriter().write("Unauthorize");
		}
		
	}

}
