package xyz.tinyorb.hibernate.App.Article;


import java.sql.Date;
import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import xyz.tinyorb.hibernate.entity.Article;

public class CreateArticle {
	
	public String saveArticle(Integer id,String username, String data, String head, String cat)
	{
		String strReturn = null;
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Article.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		Calendar currenttime = Calendar.getInstance();
        Date sqldate = new Date((currenttime.getTime()).getTime());
        
        
		try{
			
			session.beginTransaction();
			
			//getting object
			Article article  = session.get(Article.class, id);
			if(article.getAuthor().equals(username))
			{
				//updating content
				article.setHeading(head);
		        article.setPDate(sqldate);
		        article.setsData(data);
		        article.setCategory(cat);
		        
		        //update the article
				session.update(article);
				session.beginTransaction().commit();
				strReturn="successful";
			}
		}
		finally
		{
			factory.close();
		}
		
		return strReturn;
	}
	
	public int createArticle(String username)
	{
		int strReturn = -1;
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Article.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		Calendar currenttime = Calendar.getInstance();
        Date sqldate = new Date((currenttime.getTime()).getTime());
		
        Article article = new Article();
        article.setAuthor(username);
        article.setPDate(sqldate);
        article.setPID(-1);
        
		try{
			
			session.beginTransaction();
			
			session.save(article);
			
			strReturn = article.getIdArticle();
			
			session.beginTransaction().commit();
		}
		finally
		{
			factory.close();
		}
		
		return strReturn;
	}

}
