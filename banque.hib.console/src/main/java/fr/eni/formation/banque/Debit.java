package fr.eni.formation.banque;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Debit")
public class Debit extends Operation {
	private static final long serialVersionUID = 1490103402890978311L;

	public Debit() {
		super();
	}

	public Debit(Date date, String libelle, double montant) {
		super(date, libelle, montant);
	}

}
