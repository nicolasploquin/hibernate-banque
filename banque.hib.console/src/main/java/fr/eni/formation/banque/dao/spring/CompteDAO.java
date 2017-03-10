package fr.eni.formation.banque.dao.spring;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.Credit;
import fr.eni.formation.banque.Debit;
import fr.eni.formation.banque.Operation;
import fr.eni.formation.banque.dao.ICompteDAO;

@Component("compteDAO")
public class CompteDAO implements ICompteDAO {
	
	// Bean Spring sessionFactory
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Transactional(TxType.REQUIRED)
	public Compte create(String numero) {
		Session session = sessionFactory.getCurrentSession();

		Compte compte = new Compte(numero);
		session.persist(compte);
		
		
		return compte;
	}

	@Transactional(TxType.SUPPORTS)
	public Compte read(long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Compte) session.load(Compte.class, id);
	}

	@Transactional(TxType.SUPPORTS)
	public Compte read(String numero) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Compte.class);
		crit.add(Restrictions.eq("numero", numero));
		return (Compte) crit.uniqueResult();
	}

	@Transactional(TxType.SUPPORTS)
	public List<Compte> readAll() {
		//TODO: liste des opérations readAll()
		return (List<Compte>) Collections.EMPTY_LIST;
	}

	@Transactional(TxType.REQUIRED)
	public void virement(Compte emet, Compte dest, String libelle, double montant) {
		Session session = sessionFactory.getCurrentSession();
		
		if(!session.contains(emet)) session.merge(emet);
		if(!session.contains(dest)) session.merge(dest);
		
//		emet.virement(dest, libelle, montant);
	
		Operation debit = new Debit(new Timestamp(new Date().getTime()), libelle, -montant);
		Operation credit = new Credit(new Timestamp(new Date().getTime()), libelle, montant);
		
		emet.getOperations().add(debit);
		dest.getOperations().add(credit);
		
//		System.out.println(emet);
//		System.out.println(dest);
		
	}
	
	@Transactional(TxType.REQUIRED)
	public void addOperation(Compte compte, Operation operation) {
		Session session = sessionFactory.getCurrentSession();
		
		compte.getOperations().add(operation);
				
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	

}
