package xyz.tinyorb.reportTransaction;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.tinyorb.hibernate.App.report.Quiry;

/**
 * Servlet implementation class SendQuiry
 */
@WebServlet("/SendQuiryReport")
public class SendQuiryReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendQuiryReport() {
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
		
		String title = request.getParameter("title_l");
		String type = request.getParameter("type_l");
		String description = request.getParameter("description_l");
		
		Calendar currenttime = Calendar.getInstance();
		Date sqldate = new Date((currenttime.getTime()).getTime());
		boolean status = true;
		
		if(session != null)
		{
			String username = (String)session.getAttribute("username");
			int result = new Quiry().CreateQuiry(title, type, description, sqldate, status,username);
			
			if(result != 0)
			{
				response.getWriter().write(new Integer(result).toString());
			}
			else
			{
				response.getWriter().write("Something went wrong");
			}
		}
		else
		{
			response.getWriter().write("Unauthorize");
		}
	}

}
