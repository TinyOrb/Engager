package xyz.tinyorb.hibernate.App.Article;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import xyz.tinyorb.hibernate.entity.Article;

public class DeleteArticle {
	
	public String delete(Integer id)
	{
		String strReturn = null;
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Article.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try{
			session.beginTransaction();
			Article article = session.get(Article.class, id);
			session.delete(article);
			session.beginTransaction().commit();
			strReturn = "success";
		}
		finally
		{
			factory.close();
		}
		
		return strReturn;
		
	}

}
