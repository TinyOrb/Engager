package xyz.tinyorb.HtmlCreationApp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Date;

public class BuildHtml {
		
		public String buildAndSave(String Author, String fname, String lname, String Data, Date date, String heading, Integer id, String BasePath, String contextPath, String mainUrl)
		{
			String strReturn = null;
			
			// Creating html String
			String htmlString = new HtmlWrite().write(Author, fname, lname, Data, date, heading, id);
			
			//if html string is not null saving to the file
			if(htmlString != null)
			{
				String path = new FileSaver().save(htmlString, id, Author, BasePath);
			
				// Checking url is accessible
				if(path != null)
				{
					URL u;
					try {
						u = new URL (mainUrl +"//"+contextPath+"//"+Author+"//"+id+".html");
						HttpURLConnection huc =  ( HttpURLConnection )  u.openConnection (); 
						huc.setRequestMethod ("GET");  //OR  huc.setRequestMethod ("HEAD"); 
						huc.connect () ; 
						int code = huc.getResponseCode() ;
						
						// if http response code is 200 everything is ok else you receive 4xx or 5xx
						if(code == 200)
						{
							strReturn = u.toString();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
			
			return strReturn;
		}
		
}
