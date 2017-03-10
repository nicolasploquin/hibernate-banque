package fr.eni.formation.banque.dao;

import java.util.List;

import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.Operation;

public interface ICompteDAO {
		
	public Compte create(String numero);
	public Compte read(long id);
	public Compte read(String numero);
	public List<Compte> readAll();
	
	public void virement(Compte emet, Compte dest, String libelle, double montant);
	public void addOperation(Compte compte, Operation operation);
	
}
