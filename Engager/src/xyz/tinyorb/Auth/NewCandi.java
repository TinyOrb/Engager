package xyz.tinyorb.Auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.tinyorb.hibernate.App.CreateUser;

/**
 * Servlet implementation class NewCandi
 */
@WebServlet(description = "RegistrationServlet", urlPatterns = { "/NewCandi" })
public class NewCandi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewCandi() {
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
		String username = request.getParameter("x1");
		String email = request.getParameter("x2");
		String pass = request.getParameter("x3");
		
		String result = new CreateUser().regUser(username, email, pass);
		//response.sendRedirect("/Index.html");
		if(result.equals("successful"))
		{
			HttpSession session = request.getSession(true);
			session.setAttribute("username", username);
			session.setAttribute("firstname", null);
			session.setAttribute("lastname", null);
			session.setMaxInactiveInterval(60*60*24*365);
			System.out.println("session is created " + (String)session.getAttribute("username"));
		}
		response.getWriter().write(result);
	}

}
