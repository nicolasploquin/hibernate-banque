package fr.eni.formation.banque.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.Credit;
import fr.eni.formation.banque.Debit;
import fr.eni.formation.banque.Operation;

public class HibernateUtil {
	
	public static SessionFactory factory = null;
	public static ThreadLocal<Session> thread = new ThreadLocal<Session>();
	
	static {
		
		Configuration config = new Configuration()
//				.setProperty("connection.url", "jdbc:mysql://localhost:3306/banque")
				.addAnnotatedClass(Compte.class)
				.addAnnotatedClass(Operation.class)
				.addAnnotatedClass(Debit.class)
				.addAnnotatedClass(Credit.class)
				.configure();
		
		ServiceRegistry service = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties())
				.build();
		
		factory = config.buildSessionFactory(service);
	}
	
	public static Session getSession(){
		Session session = thread.get();
		if(session == null || !session.isOpen()){
			session = factory.openSession();
		}
		return session;
	}

	public static void closeSession() {
		Session session = thread.get();
		if(session != null) session.getSessionFactory().close();
	}
	
	

}
