package xyz.tinyorb.Publish;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import xyz.tinyorb.HtmlCreationApp.*;
import xyz.tinyorb.hibernate.App.Publisher.Post_Publish;

/**
 * Servlet implementation class InitPublish
 */
@WebServlet("/InitPublish")
public class InitPublish extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitPublish() {
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
		
		/**
		 * Getting publish information
		 */
		
		//getting file
		// "file.getAbsoluteFile()" this will resolve to absolute path
		//System.getProperty("user.dir")  will give home directory
		//Test.class.getResource("Test.class") will return file path of Test class
		//Test.class.getResource("") will return directory of Test class
		// then get project src and so on...
		//So it is better to keep file itself in java path;
		
		File file = new File(FileSaver.class.getResource("").getPath().toString()+"//buildhtmlconfig.json");
				
		Scanner in = new Scanner(file);
		
		String jsonData = in.next();
		
		JSONParser parser = new JSONParser();
		Map map = null;
		try {
			//Map<String, String> map = new HashMap<String, String>();
			map = (Map)parser.parse(jsonData);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		HttpSession session = request.getSession(false);
		
		String content = request.getParameter("data");
		String category = request.getParameter("cat");
		String heading = request.getParameter("head");
		Integer PID = Integer.parseInt(request.getParameter("pid"));
		Integer AID = Integer.parseInt(request.getParameter("aid"));
		
		String BasePath = (String) map.get("BasePath");
		String ContextPath = (String) map.get("ContextPath");
		String MainUrl = (String) map.get("MainUrl");
		
		
		Calendar currenttime = Calendar.getInstance();
        Date sqldate = new Date((currenttime.getTime()).getTime());
        
        if(session != null)
        {
        	String username = (String) session.getAttribute("username");
        	String fname =  (String) session.getAttribute("firstname");
        	String lname = (String) session.getAttribute("lastname");
	        //create publish object from above information in publish_post table and get publish_id
        	Integer result = new Post_Publish().publish(username, sqldate, heading, category,PID,AID);
        	
        			if(result != null)
        			{
        				/**
        		         *  Send send heading, category, date and content for static html page creation and 
        		         *  save it into the folder
        		         */
        				String bh = new BuildHtml().buildAndSave(username,fname, lname, content, sqldate, heading, result, BasePath, ContextPath, MainUrl);
        				if(bh != null)
        				{
        					response.getWriter().write(bh);
        				}
        				else
        				{
        					response.getWriter().write("Something went wrong at html build");
        				}
        				
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
		
		/*System.out.print(content + category + heading);
		
		response.getWriter().write("done");*/
		
	}

}
