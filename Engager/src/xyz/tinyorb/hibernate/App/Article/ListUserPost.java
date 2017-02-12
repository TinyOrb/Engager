package xyz.tinyorb.hibernate.App.Article;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import xyz.tinyorb.hibernate.entity.Article;

public class ListUserPost {

	public List<Article> getList(String username) {
		// TODO Auto-generated method stub
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Article.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		List<Article> ls = null;
		try{
			session.beginTransaction();
			
			ls = session.createQuery("from Article a where Author =" +"'"+ username+"'").list();
			
			session.beginTransaction().commit();
		}
		finally{
			factory.close();
		}
		return ls;
	}

}
