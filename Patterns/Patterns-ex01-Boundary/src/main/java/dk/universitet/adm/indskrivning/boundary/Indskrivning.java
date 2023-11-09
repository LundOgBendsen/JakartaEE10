package dk.universitet.adm.indskrivning.boundary;

import java.util.Random;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import dk.universitet.adm.model.Studerende;

@Stateless
//Boundary: bør have requires_new tx-support, så man effektivt kommunikerer at 
//transaktioner starter her. 
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class Indskrivning {
	Logger log = Logger.getLogger(Indskrivning.class.getName());

	//Boundary: injectede felter har default access, så de kan sættes fra testcases.
	@PersistenceContext(unitName="primary")
	EntityManager em;
	
	/*Boundary: Her vælger vi at returnere en JPA-entity. Alternativet er at bruge
	  DTO'er*/
	public Studerende indskrivStuderende(Studerende studerende) {
		if (studerende.getStudienummer()!=null) {
			throw new IllegalArgumentException("Studerende har allerede et studienummer og kan ikke indskrives!");
		}
		if (studerende.getId()!=null) {
			throw new IllegalArgumentException("Studerende har allerede en id (" + studerende.getId() + ")");			
		}
		studerende.setStudienummer(getStudienummer());
		em.persist(studerende);
		
		studerende = em.merge(studerende);
		log.info("Studerende er indskrevet");
		return studerende;
	}
	
	//Simulerer dannelsen af et nyt studienummer på 10 cifre med 0-padding.
	private String getStudienummer() {		
		return String.format("%010d", new Random().nextInt(1000000000));
	}
}
