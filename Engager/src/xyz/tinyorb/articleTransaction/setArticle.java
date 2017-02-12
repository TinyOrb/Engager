package xyz.tinyorb.articleTransaction;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.tinyorb.hibernate.App.Article.CreateArticle;

/**
 * Servlet implementation class setArticle
 */
@WebServlet("/setArticle")
public class setArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public setArticle() {
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
			String data = request.getParameter("data");
			String head = request.getParameter("head");
			String cat = request.getParameter("cat");
			String username = (String) session.getAttribute("username");
			Integer id = Integer.parseInt(request.getParameter("id"));
			String result = new CreateArticle().saveArticle(id, username, data, head, cat);
			if(result.equals("successful"))
			{
				response.getWriter().write("successful");
			}
			else
			{
				response.getWriter().write("something unusual");
			}
		}
		else{
			response.getWriter().write("fail");
		}
	}

}
