package fr.eni.formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.dao.IClientDAO;
import fr.eni.formation.banque.dao.ICompteDAO;

public class MainSpring {
	
	private static ApplicationContext context = null;
	
	@Autowired
	@Qualifier("clientDAO")
	private static IClientDAO clientDAO;
	
	@Autowired
	@Qualifier("compteDAO")
	private static ICompteDAO compteDAO;
		
	static {
		
		context = new ClassPathXmlApplicationContext("applicationContext-hibernate.xml");
		
		clientDAO = (IClientDAO) context.getBean("clientDAO");
		compteDAO = (ICompteDAO) context.getBean("compteDAO");
		
	}
	

	public static void main(String[] args) {
		
//		
//		Client cli1 = (Client) context.getBean("clientSpring");
//		
//		System.out.println(cli1);
		
		
		Client cli2 = clientDAO.read(1L);

		
		System.out.println(cli2.getNom());
		
		compteDAO.create("6789");
		
		
		
//		Client cli1 = clientDAO.create("Leblanc", "Marc");
//		Client cli2 = clientDAO.create("Ainslie", "Ben");
//		
//		for (Client cli : clientDAO.readAll()) {
//			System.out.println(cli);
//		}
//
//		System.out.println(clientDAO.read("Bla"));
//		
//		System.out.println("Quel est l'autre client ?");
//		System.out.println(context.getBean("unAutreClient"));
		
		
	}
	

}
