package fr.eni.formation.banque;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Credit")
public class Credit extends Operation {
	private static final long serialVersionUID = 1359490157537629433L;

	public Credit() {
		super();
	}

	public Credit(Date date, String libelle, double montant) {
		super(date, libelle, montant);
	}

	
}
