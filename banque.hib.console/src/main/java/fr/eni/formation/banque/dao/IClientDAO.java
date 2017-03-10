package fr.eni.formation.banque.dao;

import java.util.List;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.Compte;

public interface IClientDAO {
		
	public Client create(String nom, String prenom);
	public Client read(long id);
	public Client read(String nom);
	public List<Client> readAll();
	
	public Compte addCompte(Client client, String numero);
	

}
