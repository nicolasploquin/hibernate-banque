package fr.eni.formation;

import java.util.Date;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.Debit;
import fr.eni.formation.banque.dao.IClientDAO;
import fr.eni.formation.banque.dao.ICompteDAO;
import fr.eni.formation.banque.dao.hibernate.ClientDAO;
import fr.eni.formation.banque.dao.hibernate.CompteDAO;
import fr.eni.formation.banque.dao.hibernate.HibernateUtil;

public class MainHib {
	
	
	private static IClientDAO clientDAO = new ClientDAO();
	private static ICompteDAO compteDAO = new CompteDAO();
	
	static {
		
	}
	
	

	public static void main(String[] args) {
		
		// 1 - Créer un client
		Client cli1 = clientDAO.create("Leblanc", "Marc");
		//Client cli2 = 
				clientDAO.create("Ainslie", "Ben");
		
		// 2 - Créer un compte
		// 3 - Ajouter le compte au nouveau client
		Compte cpt5 = clientDAO.addCompte(cli1, "5678");
				
		// 4 - Effectuer un virement entre le compte "1234" et le nouveau compte
		Compte emet = compteDAO.read("1234");
		
		Debit debit = new Debit(new Date(), "test", -123456.78);
		compteDAO.addOperation(emet, debit);
		
//		compteDAO.virement(emet, cpt5, "Remboursement", 123.45);
		
		// 5 - Calculer et afficher le solde d'un compte
		
		
		
		for (Client cli : clientDAO.readAll()) {
			System.out.println(cli);
		}
		
		System.out.println(emet);
		System.out.println(cpt5);
		
		HibernateUtil.closeSession();
		
	}
	

}
