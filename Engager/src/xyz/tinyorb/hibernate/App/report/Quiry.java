package xyz.tinyorb.hibernate.App.report;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import xyz.tinyorb.hibernate.entity.Inquiry;
import xyz.tinyorb.hibernate.entity.userprofile;

public class Quiry {
	
	public int CreateQuiry(String title, String type, String description, Date sqldate, boolean status, String username)
	{
		int ticketnumber = 0;
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Inquiry.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		Inquiry inquiry = new Inquiry();
		
		inquiry.setOpendate(sqldate);
		inquiry.setUser(username);
		inquiry.setDescription(description);
		inquiry.setTitle(title);
		inquiry.setType(type);
		inquiry.setOpenstatus(status);
		
		try{
			
			session.beginTransaction();
			session.save(inquiry);
			session.beginTransaction().commit();
			ticketnumber = inquiry.getId();
		}
		finally{
			factory.close();
		}
		
		return ticketnumber;
	}
	
	public Inquiry getUserQuiry(int id)
	{
		Inquiry inquiry = null;
		
		
		return inquiry;
	}
	
	public List<Inquiry> getUserQuiryList(String username)
	{
		List<Inquiry> list = null;
		
		// create session factory
				SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Inquiry.class)
										.buildSessionFactory();
				
				// create session
				Session session = factory.getCurrentSession();
				
				try{
					session.beginTransaction();
					list = session.createQuery("from Inquiry where user='" + username +"'").list();
					session.beginTransaction().commit();
				}
				finally{
					factory.close();
				}
		
		return list;
	}
	
	//Close the query
	public String closeQuery(int id, String comment, String username)
	{
		String strReturn = null;
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Inquiry.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		Calendar currenttime = Calendar.getInstance();
		Date sqldate = new Date((currenttime.getTime()).getTime());
		String stringdate = sqldate.toString();
		
		try{
			session.beginTransaction();
			Inquiry inquiry = session.get(Inquiry.class, id);
			if(inquiry.getUser().equals(username))
			{
				String data = inquiry.getDescription();
				String newDescription = data+"<br>********************************************";
				newDescription+="<br> Date : "+stringdate;
				newDescription+="<br>" + username + " : <br>"+comment;
				newDescription+="<br> Ticket has been closed";
				newDescription+="<br>-----------------------------------------------";
				
				inquiry.setDescription(newDescription);
				inquiry.setCloseddate(sqldate);
				inquiry.setOpenstatus(false);
				
				session.update(inquiry);
				
				strReturn = " Ticket has been closed successfully ";
			}
			else
			{
				strReturn = "Unauthorize";
			}
			session.beginTransaction().commit();
		}
		finally{
			factory.close();
		}
		
		return strReturn;
	}
	
	//Open the query
	public String openQuery(int id, String comment, String username)
	{
		String strReturn = null;

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Inquiry.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		Calendar currenttime = Calendar.getInstance();
		Date sqldate = new Date((currenttime.getTime()).getTime());
		String stringdate = sqldate.toString();
		try{
			session.beginTransaction();
			
			Inquiry inquiry = session.get(Inquiry.class, id);
			if(inquiry.getUser().equals(username))
			{
				String data = inquiry.getDescription();
				String newDescription = data+"<br>********************************************";
				newDescription+="<br> Date : "+stringdate;
				newDescription+="<br>" + username + " : <br>"+comment;
				newDescription+="<br> Ticket has been Re-open";
				newDescription+="<br>-----------------------------------------------";
				
				inquiry.setDescription(newDescription);
				inquiry.setCloseddate(null);
				inquiry.setReopendate(sqldate);
				inquiry.setOpenstatus(true);
				
				session.update(inquiry);
				
				strReturn = " Ticket has been Re-opened successfully ";
			}
			else
			{
				strReturn = "Unauthorize";
			}
			
			session.beginTransaction().commit();
		}
		finally{
			factory.close();
		}
		return strReturn;
	}
	
	public String updateComment(int id, String comment, String username)
	{
		String strReturn = null;
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Inquiry.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		Calendar currenttime = Calendar.getInstance();
        Date sqldate = new Date((currenttime.getTime()).getTime());
        String stringdate = sqldate.toString();
		
		try{
			session.beginTransaction();
			Inquiry inquiry = session.get(Inquiry.class, id);
			if(inquiry.getUser().equals(username))
			{
				String data = inquiry.getDescription();
				String newDescription = data+"<br>********************************************";
				newDescription+="<br> Date : "+stringdate;
				newDescription+="<br>" + username + " : <br>"+comment;
				newDescription+="<br>-----------------------------------------------";
				
				inquiry.setDescription(newDescription);
				session.update(inquiry);
				strReturn = " Comment has been update successfully ";
			}
			else
			{
				strReturn = "Unauthorize";
			}
			session.beginTransaction().commit();
		}
		finally{
			factory.close();
		}
		
		return strReturn;
	}
}
