package fr.eni.formation.banque.dao.hibernate;


import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.Credit;
import fr.eni.formation.banque.Debit;
import fr.eni.formation.banque.Operation;
import fr.eni.formation.banque.dao.IClientDAO;

public class ClientDAO implements IClientDAO {
	
	// Configuration Hibernate
	private SessionFactory factory = null;
	private Session session = null;
	
	public ClientDAO() {
		super();
		// Chargement de la configuration Hibernate
		Configuration config = new Configuration()
				// Déclaration des classes annotées
				.addAnnotatedClass(Compte.class)
				.addAnnotatedClass(Operation.class)
				.addAnnotatedClass(Credit.class)
				.addAnnotatedClass(Debit.class)
				.configure();
		ServiceRegistry service = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties())
				.build();
		factory = config.buildSessionFactory(service);
		session = factory.openSession();
	}


	@Override
	protected void finalize() throws Throwable {
		session.close();
		factory.close();
		super.finalize();
	}


	public Client create(String nom, String prenom) {
		
		Transaction tx = session.beginTransaction();
		
		Client cli = new Client(nom, prenom);
		session.persist(cli);
		
		tx.commit();
		
		return cli;
	}

	public Client read(long id) {
		Client cli = (Client)session.load(Client.class, id);
		
		return cli;
	}
	// %tro%
	public Client read(String nom) {
		Client client = null;
		
		Query requete = session.createQuery("from Client as cli where cli.nom like :nom");
		requete.setString("nom", "%"+nom+"%");
		client = (Client)requete.list().get(0);
		return client;
	}

	public List<Client> readAll() {
		List<Client> clients = Collections.EMPTY_LIST;

		Criteria requete = session.createCriteria(Client.class);
		clients = (List<Client>)requete.list();
		
		return clients;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
