/**
 * 
 */
package fr.eni.formation.banque;

import java.io.Serializable;

import javax.persistence.Embeddable;


/**
 * @author Nicolas Ploquin - ENI Service
 *
 */
//@Entity
@Embeddable
public class Adresse implements Serializable {
	
	private static final long serialVersionUID = 5226166059366060409L;

//	@Id
//	private long idAdresse;
	
	private String rue = "";
	private String codePostal = "";
	private String ville = "";

	public Adresse(){
	}
	
	/**
	 * 
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */
	public Adresse(String rue, String codePostal, String ville) {
		super();
		this.setRue(rue);
		this.setCodePostal(codePostal);
		this.setVille(ville);
	}

	
//	public long getIdAdresse() {
//		return idAdresse;
//	}
//
//	public void setIdAdresse(long idAdresse) {
//		this.idAdresse = idAdresse;
//	}

	private String getRue() {
		return rue;
	}

	private void setRue(String rue) {
		this.rue = rue;
	}

	private String getCodePostal() {
		return codePostal;
	}

	private void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	private String getVille() {
		return ville;
	}

	private void setVille(String ville) {
		this.ville = ville.toUpperCase();
	}

	@Override
	public String toString(){
		return getRue() + ", " + getCodePostal() + " " + getVille();
	}
	
}


