package com.sonal.hibernatetraining.util;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


public class SessionFactoryUtil {

  
  private static org.hibernate.SessionFactory sessionFactory;

	private SessionFactoryUtil() {
	}

	static{
// Annotation and XML
    sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
// XML only
//    sessionFactory = new Configuration().configure().buildSessionFactory();
  }

	public static SessionFactory getInstance() {
		return sessionFactory;
	}

 
	public static Session openSession() {
		return sessionFactory.openSession();
	}


	public static Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public static void close(){
		if (sessionFactory != null)
			sessionFactory.close();
		sessionFactory = null;
	
	}
}
