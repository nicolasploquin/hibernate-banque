/**
 * 
 */
package fr.eni.formation.banque;

import java.io.Serializable;

/**
 * @author Nicolas Ploquin - ENI Service
 *
 */

public class ClientHBM implements Serializable {
	
	private static final long serialVersionUID = 5226166059366060409L;

	private long idClient;
	private String nom;
	private String prenom;
	
//	private Adresse adresse;
	
	public ClientHBM(){
		nom = "";
		prenom = "";
	}
	
	/**
	 * @param nom
	 * @param prenom
	 */
	public ClientHBM(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public long getIdClient() {
		return idClient;
	}

	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString(){
		String result = prenom + " " + nom + " :\n";
		return result;
	}
	
}


