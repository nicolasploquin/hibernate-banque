package fr.eni.formation.banque.dao.hibernate;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.dao.IClientDAO;

public class ClientDAO implements IClientDAO {
	
	// Configuration Hibernate
	private Session session = HibernateUtil.getSession();
	
	public ClientDAO() {
		super();
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
		Criteria requete = session.createCriteria(Client.class);
		return (List<Client>) requete.list();
	}

	
	
	
	
	
	public Compte addCompte(Client client, String numero) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		if(!session.contains(client)) session.merge(client);
		
		Compte compte = new Compte(numero);
		session.persist(compte);
		
		client.getComptes().add(compte);
		compte.setClient(client);
		
		tx.commit();
		
		return compte;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
