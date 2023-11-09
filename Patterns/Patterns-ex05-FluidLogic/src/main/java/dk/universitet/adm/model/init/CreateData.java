package dk.universitet.adm.model.init;

import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import dk.universitet.adm.model.Adresse;
import dk.universitet.adm.model.Fag;
import dk.universitet.adm.model.Studerende;
import dk.universitet.adm.model.Studienaevn;

@Singleton
@Startup
public class CreateData {
	static Logger log = Logger.getLogger(CreateData.class.getName());
	
	@PersistenceContext(unitName="primary")
	EntityManager em;
	
	@PostConstruct
	public void init() {
		Studienaevn datalogiSN = new Studienaevn();
		datalogiSN.setKode("DAT");
		datalogiSN.setNavn("Datalogi");
		
		Fag algoritmer = new Fag();
		algoritmer.setCampus("North");
		algoritmer.setEctspoints(7.5);
		algoritmer.setNavn("Algoritmer og datastrukturer");
		algoritmer.setStudienaevn(datalogiSN);
		datalogiSN.getFag().add(algoritmer);
		
		Studerende james = new Studerende();
		james.setFornavn("James");
		james.setEfternavn("Gosling");
		james.setCprnummer("1212121213");
		james.setStudienummer("123456");
		james.getFag().add(algoritmer);
		algoritmer.getStuderende().add(james);
		
		Adresse slotsgade14 = new Adresse();
		slotsgade14.setHusnummer("14");
		slotsgade14.setEtage("1");
		slotsgade14.setPostnummer(9000);
		slotsgade14.setVejnavn("Slotsgade");
		james.setAdresse(slotsgade14);
		em.persist(james);
		log.info("Data created");
	}
}
