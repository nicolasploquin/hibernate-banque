/**
 * 
 */
package fr.eni.formation.banque;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


/**
 * @author Nicolas Ploquin - ENI Service
 * 
 */
@Entity
public class Compte implements Serializable {

	private static final long serialVersionUID = -6768523482580774672L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idCompte;
	private String numero;

	/**
	 * Solde calculé à partir de la liste des opérations
	 * <code>(
	 * 	select ifnull(sum(ope.montant),0) 
	 * 		from Operation ope 
	 * 		where ope.idCompte = idCompte
	 * ) as solde</code>
	 */
	@Transient
	private double solde;
	
	@ManyToOne
	@JoinColumn(name="idClient")
	private Client client;
	
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},
			fetch=FetchType.LAZY)
	@JoinColumn(name="idCompte")
	private List<Operation> operations = new LinkedList<Operation>();

	public Compte() {
		super();
	}

	public Compte(String numero) {
		super();
		this.numero = numero;
		this.solde = 0.0;
	}	
	
	/**
	 * @return the id
	 */
	public long getIdCompte() {
		return idCompte;
	}

	/**
	 * @param id the id to set
	 */
	public void setIdCompte(long idCompte) {
		this.idCompte = idCompte;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		client.getComptes().add(this);
		this.client = client;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	
	private void majSolde(){
		double solde = 0.0;
		for(Operation ope : getOperations()){
			solde += ope.getMontant();
		}
		setSolde(solde);
	}	

	@Override
	public String toString() {
		return String.format("%s (%s)\n",
						numero, client!=null?client.getNom():"");
	}

}
