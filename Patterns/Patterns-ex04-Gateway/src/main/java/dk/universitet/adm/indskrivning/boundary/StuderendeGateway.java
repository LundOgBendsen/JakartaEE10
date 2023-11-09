package dk.universitet.adm.indskrivning.boundary;

import jakarta.ejb.Remove;
import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;

import dk.universitet.adm.model.Studerende;

//Gateway: en stateful session bean, som kan holde en instans af en Studerende og lade klienten arbejde på den over flere metodekald.
@Stateful
//Gateway: metoderne er ikke transaktionelle, da vi ikke ønsker at flushe EntityManageren for hvert kald.
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
//Gateway: dependent er nødvendig for at kunne kalde en @Remove-annoteret metode fra klienten.
@Dependent
public class StuderendeGateway {
	@PersistenceContext(unitName = "primary", type = PersistenceContextType.EXTENDED)
	EntityManager em;

	Studerende current;
	
	public StuderendeGateway() {
	}

	public Studerende find(long id) {
		this.current = this.em.find(Studerende.class, id);
		return this.current;
	}

	public Studerende getCurrent() {
		return current;
	}

	public void create(Studerende studerende) {
		this.em.persist(studerende);
		this.current = studerende;
	}

	
	public void remove(long id) {
		Studerende ref = this.em.getReference(Studerende.class, id);
		this.em.remove(ref);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void save() {
	}
	
	@Remove
	public void closeGateway() {
	}

}
