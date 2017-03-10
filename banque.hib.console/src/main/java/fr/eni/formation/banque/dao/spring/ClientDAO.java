package fr.eni.formation.banque.dao.spring;


import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.dao.IClientDAO;

public class ClientDAO implements IClientDAO {
	
	// Bean Spring sessionFactory
	private SessionFactory sessionFactory;
	
	public ClientDAO() {
		super();
	}

	@Transactional(TxType.REQUIRED)
	public Client create(String nom, String prenom) {
		Session session = sessionFactory.getCurrentSession();
		
		Client cli = new Client(nom, prenom);
		session.persist(cli);
		
		return cli;
	}

	@Transactional(TxType.SUPPORTS)
	public Client read(long id) {
		Session session = sessionFactory.getCurrentSession();
		Client cli = (Client)session.load(Client.class, id);
		cli.toString();
		return cli;
	}
	
	// %tro%
	@Transactional(TxType.SUPPORTS)
	public Client read(String nom) {
		Session session = sessionFactory.getCurrentSession();
		Client client = null;
		
		Query requete = session.createQuery("from Client as cli where cli.nom like :nom");
		requete.setString("nom", "%"+nom+"%");
		client = (Client)requete.list().get(0);
		return client;
	}

	@Transactional(TxType.SUPPORTS)
	public List<Client> readAll() {
		Session session = sessionFactory.getCurrentSession();
		Criteria requete = session.createCriteria(Client.class);
		return (List<Client>) requete.list();
	}

	
	
	
	
	
	@Transactional(TxType.REQUIRED)
	public Compte addCompte(Client client, String numero) {
		Session session = sessionFactory.getCurrentSession();

		if(!session.contains(client)) session.merge(client);
		
		Compte compte = new Compte(numero);
		session.persist(compte);
		
		client.getComptes().add(compte);
		compte.setClient(client);
		
		return compte;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
