package dk.university.adm.enrollment.boundary.model.init;


import dk.university.adm.enrollment.boundary.model.Address;
import dk.university.adm.enrollment.boundary.model.Student;
import dk.university.adm.enrollment.boundary.model.Studyboard;
import dk.university.adm.enrollment.boundary.model.Subject;
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
		Studyboard csSB = new Studyboard();
		csSB.setCode("CS");
		csSB.setName("Computer Science");

		Subject algorithms = new Subject();
		algorithms.setCampus("North");
		algorithms.setEctsPoints(7.5);
		algorithms.setName("Algorithms and data structures");
		algorithms.setStudienaevn(csSB);
		csSB.getFag().add(algorithms);

		Student james = new Student();
		james.setFirstName("James");
		james.setLastName("Gosling");
		james.setCpr("1212121213");
		james.setStudentnumber("123456");
		james.getSubject().add(algorithms);
		algorithms.getStuderende().add(james);

		Address addr = new Address();
		addr.setNumber("14");
		addr.setFloor("1");
		addr.setZip(9000);
		addr.setStreet("Slotsgade");
		james.setAddress(addr);
		em.persist(james);
		log.info("Data created");
	}
}
