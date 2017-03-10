package fr.eni.formation;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import fr.eni.formation.banque.Adresse;
import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.Credit;
import fr.eni.formation.banque.Debit;
import fr.eni.formation.banque.Operation;

public class Main {
	
	// Configuration Hibernate
	public static SessionFactory factory = null;
	
	static {
		// Chargement de la configuration Hibernate
		Configuration config = new Configuration()
				// Déclaration des classes annotées
//				.addAnnotatedClass(Adresse.class) // si @OneToOne
				.addAnnotatedClass(Compte.class)
				.addAnnotatedClass(Operation.class)
				.addAnnotatedClass(Credit.class)
				.addAnnotatedClass(Debit.class)
				.configure();
		ServiceRegistry service = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties())
				.build();
		factory = config.buildSessionFactory(service);
	}

	public static void main(String[] args) {
		
		System.out.println("Java OK !");
		
		Session session = factory.openSession();
		if(session == null){
			System.out.println("Hibernate KO !");
			return;
		}
		
		System.out.println("Hibernate OK !");
			
		// Ouvrir une transaction
		Transaction tx = session.beginTransaction();
		
		// Création d'un objet client et enregistrement dans la table Client	
		
		// objet transient
		Client cli1 = new Client("Troadec", "Nolwenn");
		Client cli2 = new Client("Leblanc", "Marc");
		
		Compte cpt1 = new Compte("12345678");
		cli1.addCompte(cpt1);
		
		cli1.setAdresse(new Adresse("", "", "Lannion"));
		
		Operation ope1 = new Credit(new Date(), "Salaire Renault", 1300.0);
		Operation ope2 = new Debit(new Date(), "Engie", -38.0);
		Operation ope3 = new Debit(new Date(), "TAN Octobre", -52.0);
		Operation ope4 = new Debit(new Date(), "Free Mobile", -29.99);
		Operation ope5 = new Debit(new Date(), "Impôts.gouv", -123.0);
		
//		cpt1.getOperations().add(ope1);

		cpt1.getOperations().addAll(
				Arrays.asList( new Operation[]{ope1,ope2,ope3,ope4,ope5} )
		);
		
		
		// cli1 devient persistant
		session.persist(cli1);
		session.persist(cli2);
		cli2.setPrenom("Michel");
		session.persist(cpt1);
		
		session.load(Client.class, 3L);

		tx.commit();
		

		for (Client cli : listeClients(session)) {
			System.out.println(cli);
		}
		
		for (Compte cpt : cli1.getComptes()) {
			System.out.println(cpt);
		}
		System.out.println(cpt1);
		
		System.out.println(findClient("tro", session));
		
		session.close();
		factory.close();
	}
	
	/**
	 * Recherche d'un client par son prénom
	 * @param session
	 * @return Client
	 */
	public static List<Client> listeClients(Session session){
		Query requete = session.createQuery("from Client");
		return requete.list();
	}
	/**
	 * Recherche d'un client par son prénom
	 * @param session session hibernate ouverte
	 * @return Client
	 */
	public static Client findClient(String nom, Session session){
		Criteria requete = session.createCriteria(Client.class);
		requete.add(Restrictions.ilike("nom", nom, MatchMode.ANYWHERE));
		return (Client)requete.list().get(0);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
