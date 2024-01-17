package dk.university.adm.model.init;

import dk.university.adm.model.Address;
import dk.university.adm.model.Student;
import dk.university.adm.model.Studyboard;
import dk.university.adm.model.Subject;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.logging.Logger;

@Singleton
@Startup
public class CreateData {
	static Logger log = Logger.getLogger(CreateData.class.getName());
	
	@PersistenceContext(unitName="pu")
	EntityManager em;
	
	@PostConstruct
	public void init() {
		Studyboard datalogiSN = new Studyboard();
		datalogiSN.setCode("CS");
		datalogiSN.setName("Computer Science");
		
		Subject algorithms = new Subject();
		algorithms.setCampus("North");
		algorithms.setEctsPoints(7.5);
		algorithms.setName("Algorithms and data structures");
		algorithms.setStudienaevn(datalogiSN);
		datalogiSN.getFag().add(algorithms);
		
		Student james = new Student();
		james.setFirstName("James");
		james.setLastName("Gosling");
		james.setCpr("1212121213");
		james.setStudentnumber("123456");
		james.getFag().add(algorithms);
		algorithms.getStuderende().add(james);
		
		Address addr = new Address();
		addr.setNumber("14");
		addr.setFloor("1");
		addr.setZip(9000);
		addr.setStreet("Slotsgade");
		james.setAdresse(addr);
		em.persist(james);
		log.info("Data created");
	}
}
