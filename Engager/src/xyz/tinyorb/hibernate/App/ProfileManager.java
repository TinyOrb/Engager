package xyz.tinyorb.hibernate.App;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import xyz.tinyorb.hibernate.entity.user;
import xyz.tinyorb.hibernate.entity.userprofile;

public class ProfileManager {
	public String update(String username, String fname, String lname, String country, String state, String amail, String gender, Date sqlDate)
	{
		String strReturn = null;
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(userprofile.class)
								.addAnnotatedClass(user.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try{
			session.beginTransaction();
			user User = session.get(user.class, username);
			userprofile profile = session.get(userprofile.class, username);
			
			if(profile != null)
			{
				profile.setFirstname(fname);
				profile.setLastname(lname);
				profile.setCountry(country);
				profile.setState(state);
				profile.setaEmail(amail);
				profile.setDOB(sqlDate);
				profile.setGender(gender);
				
				session.update(profile);
			}
			else
			{
				profile = new userprofile();
				
				profile.setUsername(User.getUsername());
				profile.setFirstname(fname);
				profile.setLastname(lname);
				profile.setCountry(country);
				profile.setState(state);
				profile.setaEmail(amail);
				profile.setDOB(sqlDate);
				profile.setGender(gender);
				
				session.save(profile);
			}
			
			session.beginTransaction().commit();
			
			strReturn = "success";
		}
		finally{
			factory.close();
		}
		
		return strReturn;
	}
	
	public userprofile getUserProfile(String username)
	{
		userprofile profile = null;
		
		// create session factory
				SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(userprofile.class)
										.buildSessionFactory();
				
		// create session
		Session session = factory.getCurrentSession();
		
		try
		{
			session.beginTransaction();
			profile = session.get(userprofile.class, username);
			session.beginTransaction().commit();
			
		}
		finally{
			factory.close();
		}
		
		return profile;
	}
	
}
