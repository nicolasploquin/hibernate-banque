package fr.eni.formation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.dao.IClientDAO;

public class Main {
	
	private static ApplicationContext context = null;
	
	private static IClientDAO clientDAO = null;
	
	static {
		
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		clientDAO = (IClientDAO) context.getBean("monClientDAO");
		clientDAO = context.getBean(IClientDAO.class);
		
	}
	
	

	public static void main(String[] args) {
		
		Client cli1 = clientDAO.create("Leblanc", "Marc");
		Client cli2 = clientDAO.create("Ainslie", "Ben");
		
		for (Client cli : clientDAO.readAll()) {
			System.out.println(cli);
		}

		System.out.println(clientDAO.read("Bla"));
		
		System.out.println("Quel est l'autre client ?");
		System.out.println(context.getBean("unAutreClient"));
		
		
	}
	

}
