/**
 * 
 */
package fr.eni.formation.banque;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author Nicolas Ploquin - ENI Service
 *
 */

@Entity
public class Client implements Serializable {
	
	private static final long serialVersionUID = 5226166059366060409L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idClient;
	
	private String nom;
	private String prenom;
	
//	@OneToOne
	@Embedded
	private Adresse adresse;
	
	@OneToMany(	mappedBy="client",
				cascade=CascadeType.ALL,
				fetch=FetchType.LAZY)
	private Set<Compte> comptes = new HashSet<Compte>();
	
	public Client(){
		nom = "";
		prenom = "";
	}
	
	/**
	 * @param nom
	 * @param prenom
	 */
	public Client(String nom, String prenom) {
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
	
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Set<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Set<Compte> comptes) {
		this.comptes = comptes;
	}
	
	public void addCompte(Compte cpt){
		cpt.setClient(this);
	}

	@Override
	public String toString(){
		return String.format("%s %s (%s, %d comptes)\n", 
						prenom, nom, idClient, getComptes().size());
	}
	
}


