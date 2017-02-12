package xyz.tinyorb.converter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import xyz.tinyorb.hibernate.entity.Article;

public class ToJsonString<T> {

	/**
	 * This method take list type object and convert in JSON Object.
	 */
	public String converter(List<T> list)
	{
		// initializing string value
		String strReturn="";
		// defining json object
		JSONObject jObj;
		// creating array
		JSONArray jArr = new JSONArray();
		int sz = list.size();
		
		//if list is not empty proceeding creation json object
		if(sz>0)
		{
				for(T l :list)
				{
					Object obj = l;
					jObj = new JSONObject();
					
					//getting all getter methods name and invoking current object obj and passing paramter null
					Method [] fields = l.getClass().getMethods();
					for(Method f : fields)
					{
						
						if(f.getName().charAt(0) == 'g' && f.getName().charAt(1)=='e' && f.getName().charAt(2)=='t' && !f.getName().equals("getClass"))
						{
						//System.out.print(f.invoke(obj, null));
							
							try {
								// make sure before putting both key and value is in string format 
								jObj.put(f.getName(), f.invoke(obj, null) != null ? f.invoke(obj, null).toString() : "");
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					// end of each loop iteration adding json object to json array
					jArr.add(jObj);
					//System.out.println();
				}
				//last step converting into string for return
				strReturn = JSONValue.toJSONString(jArr);
		}
		
		return strReturn;

	}
	
	
	public String converter(Object obj)
	{
		// initializing string value
		String strReturn="";
		
		if(obj != null)
		{
		JSONObject jObj = new JSONObject();
		
		//getting all getter methods name and invoking current object obj and passing paramter null
		Method [] fields = obj.getClass().getMethods();
		for(Method f : fields)
		{
			
			if(f.getName().charAt(0) == 'g' && f.getName().charAt(1)=='e' && f.getName().charAt(2)=='t' && !f.getName().equals("getClass"))
			{
			//System.out.print(f.invoke(obj, null));
				
				try {
					// make sure before putting both key and value is in string format 
					jObj.put(f.getName(), f.invoke(obj, null).toString());
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return strReturn = JSONValue.toJSONString(jObj);
	}
		return strReturn;
	}

		

}
