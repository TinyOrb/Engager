package xyz.tinyorb.hibernate.App.Article;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import xyz.tinyorb.hibernate.entity.Article;

public class LoadArticle {
	public Article load(int id)
	{
		Article article = null;
SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Article.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try{
			session.beginTransaction();
			article = session.get(Article.class, id);
			session.beginTransaction().commit();
		}
		finally
		{
			factory.close();
		}
	
		return article;
		
	}
}
