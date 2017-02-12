package xyz.tinyorb.articleTransaction;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.tinyorb.converter.ToJsonString;
import xyz.tinyorb.hibernate.App.Article.ListUserPost;
import xyz.tinyorb.hibernate.entity.Article;

/**
 * Servlet implementation class InitialData
 */
@WebServlet("/InitialData")
public class InitialData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitialData() {
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
			String username = (String) session.getAttribute("username");
			// Getting list then converting json format
			String responseStr = new ToJsonString().converter(new ListUserPost().getList(username));
			response.getWriter().write(responseStr);
		}
		else
		{
			response.sendRedirect("/denied.html");
		}
	}
	
	
}
