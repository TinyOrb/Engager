package xyz.tinyorb.reportTransaction;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.tinyorb.hibernate.App.report.Quiry;

/**
 * Servlet implementation class OtherQuiryOperation
 */
@WebServlet("/OtherQuiryOperation")
public class OtherQuiryOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OtherQuiryOperation() {
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
			String username = (String)session.getAttribute("username");
			Integer id = Integer.parseInt(request.getParameter("id"));
			String comment = request.getParameter("comment");
			
			/**
			 * opcode 1 mean update comment
			 * opcode 2 mean close the ticket
			 * opcode 3 mean reopen the ticket
			 */
			Integer opcode = Integer.parseInt(request.getParameter("opcode"));
			
			switch(opcode)
			{
			case 1:
				String result = new Quiry().updateComment(id, comment, username);
				response.getWriter().write(result);
				break;
			case 2:
				String result1 = new Quiry().closeQuery(id, comment, username);
				response.getWriter().write(result1);
				break;
			case 3:
				String result11 = new Quiry().openQuery(id, comment, username);
				response.getWriter().write(result11);
				break;
			default:
				response.getWriter().write("Invalid operation code");
				break;
			}
		}
		else
		{
			response.getWriter().write("Unauthorize");
		}
	}

}
