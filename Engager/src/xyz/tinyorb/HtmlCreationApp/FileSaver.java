package xyz.tinyorb.HtmlCreationApp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileSaver {

	public String save(String htmlString, Integer id, String username, String BasePath)
	{
		String strReturn = null;
		
		// Creating directory 
		File file = new File(BasePath+File.separator+username);
		boolean created = file.mkdirs();
		
		// creating file path object and creating file
		file = new File(BasePath+File.separator+username+File.separator+id+".html");
		System.out.println(file);
		try {
			created = file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Writing at file
		try {
				//creating file writer object
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				
				BufferedWriter bw = new BufferedWriter(fw);
				
				//writing html string into file
				bw.write(htmlString);
				
				// closing buffer writer
				bw.close();
				
				strReturn = file.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		
		return strReturn;
	}
	
}
