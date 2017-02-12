package xyz.tinyorb.hibernate.App;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import xyz.tinyorb.hibernate.entity.user;

public class CreateUser {

	
	public String regUser( String username, String email, String pass)
	{
		// create session factory
				SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(user.class)
										.buildSessionFactory();
				
				// create session
				Session session = factory.getCurrentSession();
				
				String strReturn = "";
				int userSize = 0;
				int emailSize = 0;
				try{
					session.beginTransaction();
					userSize = session.createQuery("from user u where u.username="+"'"+username+"'").list().size();
					
					emailSize = session.createQuery("from user u where u.Email="+"'"+email+"'").list().size();
					
					if( userSize >0 || emailSize > 0)
					{
						strReturn = "already exist";
						session.beginTransaction().commit();
					}
				else{
					user u = new user();
					u.setUsername(username);
					u.setEmail(email);
					u.setPass(pass);
					
					session.save(u);
					session.getTransaction().commit();
					strReturn = "successful";
				}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally{
					factory.close();
				}
				return strReturn;
	}
	
	
	
}
