package xyz.tinyorb.reportTransaction;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.tinyorb.converter.ToJsonString;
import xyz.tinyorb.hibernate.App.report.Quiry;
import xyz.tinyorb.hibernate.entity.Inquiry;

/**
 * Servlet implementation class ListQuiryReport
 */
@WebServlet("/ListQuiryReport")
public class ListQuiryReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListQuiryReport() {
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
		// doGet(request, response);
		HttpSession session = request.getSession(false);
		
		if(session != null)
		{
			String username = (String)session.getAttribute("username");
			List<Inquiry> list = new Quiry().getUserQuiryList(username);
			if(list.isEmpty())
			{
				response.getWriter().write("No data");
			}
			else
			{
				System.out.println(new ToJsonString().converter(list));
				response.getWriter().write(new ToJsonString().converter(list));
			}
			//System.out.println(new ToJsonString().converter(list));
		}
		else
		{
			response.getWriter().write("Unauthorize");
		}
	}

}
