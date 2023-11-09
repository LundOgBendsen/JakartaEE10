package dk.universitet.adm.indskrivning.boundary;

import java.util.Random;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import dk.universitet.adm.infrastructure.Configurable;
import dk.universitet.adm.model.Studerende;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class Indskrivning {
	Logger log = Logger.getLogger(Indskrivning.class.getName());

	@Inject
	//Configurator: her angives en nøgle til konfiguration
	@Configurable(key="greetings") 
	private String message;

	@Inject 
	//Configurator: her injectes ud fra en nøgle som er classnavn.feltnavn
	private String tekst;

	@Inject
	//Configurator: enDefaultTekst findes ikke i Configurator, annotationens defaultværdi bruges
	@Configurable(defaultValue="default værdi fra annotation") 
	private String enDefaultTekst;

	
	@PersistenceContext(unitName="primary")
	EntityManager em;
	
	public Studerende indskrivStuderende(Studerende studerende) {
		//Configurator: her benyttes de konfiguerede værdier
		log.info("Configurator: tekst: " + tekst);
		log.info("Configurator: message: " + message);
		log.info("Configurator: enDefaultTekst: " + enDefaultTekst);
		if (studerende.getStudienummer()!=null) {
			throw new IllegalArgumentException("Studerende har allerede et studienummer og kan ikke indskrives!");
		}
		if (studerende.getId()!=null) {
			throw new IllegalArgumentException("Studerende har allerede en id (" + studerende.getId() + ")");			
		}
		studerende.setStudienummer(getStudienummer());
		em.persist(studerende);
		
		log.info("Studerende er indskrevet");
		return studerende;
	}
	
	//Simulerer dannelsen af et nyt studienummer på 10 cifre med 0-padding.
	private String getStudienummer() {		
		return String.format("%010d", new Random().nextInt(1000000000));
	}
}
