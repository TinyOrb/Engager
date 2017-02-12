package xyz.tinyorb.articleTransaction;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import xyz.tinyorb.converter.ToJsonString;
import xyz.tinyorb.hibernate.App.Article.LoadArticle;
import xyz.tinyorb.hibernate.entity.Article;

/**
 * Servlet implementation class fetchArticle
 */
@WebServlet("/fetchArticle")
public class fetchArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fetchArticle() {
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
			int id  = Integer.parseInt(request.getParameter("x1"));
			Article article = new LoadArticle().load(id);
			if(session.getAttribute("username").equals(article.getAuthor()))
			{
				JSONObject jObj = new JSONObject();
				jObj.put("heading", article.getHeading());
				jObj.put("data", article.getsData());
				String str = JSONValue.toJSONString(jObj);
				response.getWriter().write(str);
			}
			else{
				//System.out.println("not valid user");
				
				response.getWriter().write("unauthorize");
			}
		}
		else
		{
			//response.sendRedirect("/denied.html");
			//response.setHeader("Location", "denied.html");
			response.getWriter().write("unauthorize");
			
		}
	}

}
