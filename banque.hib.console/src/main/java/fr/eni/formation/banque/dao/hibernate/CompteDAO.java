package fr.eni.formation.banque.dao.hibernate;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.Credit;
import fr.eni.formation.banque.Debit;
import fr.eni.formation.banque.Operation;
import fr.eni.formation.banque.dao.ICompteDAO;

public class CompteDAO implements ICompteDAO {
	
	public Compte create(String numero) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		Compte compte = new Compte(numero);
		session.persist(compte);
		
		tx.commit();
		
		return compte;
	}

	public Compte read(long id) {
		Session session = HibernateUtil.getSession();
		return (Compte) session.load(Compte.class, id);
	}

	public Compte read(String numero) {
		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(Compte.class);
		crit.add(Restrictions.eq("numero", numero));
		return (Compte) crit.uniqueResult();
	}

	public List<Compte> readAll() {
		//TODO: liste des opérations readAll()
		return (List<Compte>) Collections.EMPTY_LIST;
	}

	public void virement(Compte emet, Compte dest, String libelle, double montant) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		if(!session.contains(emet)) session.merge(emet);
		if(!session.contains(dest)) session.merge(dest);
		
//		emet.virement(dest, libelle, montant);
	
		Operation debit = new Debit(new GregorianCalendar().getTime(), libelle, -montant);
		Operation credit = new Credit(new GregorianCalendar().getTime(), libelle, montant);
		
		emet.getOperations().add(debit);
		dest.getOperations().add(credit);
		
		System.out.println(emet);
		System.out.println(dest);
		
		tx.commit();
	}
	
	public void addOperation(Compte compte, Operation operation) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		compte.getOperations().add(operation);
				
		tx.commit();
	}
	
	

}
