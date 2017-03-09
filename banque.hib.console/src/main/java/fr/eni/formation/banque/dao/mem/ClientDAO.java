package fr.eni.formation.banque.dao.mem;

import java.util.LinkedList;
import java.util.List;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.dao.IClientDAO;

public class ClientDAO implements IClientDAO {
	
	
	private long generator = 0L;
	
	private List<Client> clients = new LinkedList<Client>();
	
	
	public Client create(String nom, String prenom) {
		Client cli = new Client(nom, prenom);
		cli.setIdClient(generator++);
		clients.add(cli);
		return cli;
	}

	public Client read(long id) {
		for (Client cli : clients) {
			if(cli.getIdClient() == id) return cli;
		}
		return null;
	}

	public Client read(String nom) {
		for (Client cli : clients) {
			if( cli.getNom().toLowerCase().contains(nom.toLowerCase()) ) {
				return cli;				
			}
		}
		return null;
	}

	public List<Client> readAll() {
		return clients;
	}

}
