package dk.university.adm.model.init;

import java.util.logging.Logger;

import dk.university.adm.enrollment.entity.Address;
import dk.university.adm.enrollment.entity.Student;
import dk.university.adm.enrollment.entity.Studyboard;
import dk.university.adm.enrollment.entity.Subject;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Singleton
@Startup
public class CreateData {
	static Logger log = Logger.getLogger(CreateData.class.getName());
	
	@PersistenceContext(unitName="primary")
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
		algorithms.setStudyboard(csSB);
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
