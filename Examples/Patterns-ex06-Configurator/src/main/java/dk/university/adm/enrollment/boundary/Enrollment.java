package dk.university.adm.enrollment.boundary;

import java.util.Random;
import java.util.logging.Logger;

import dk.university.adm.model.Student;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import dk.university.adm.infrastructure.Configurable;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class Enrollment {
	Logger log = Logger.getLogger(Enrollment.class.getName());

	@Inject
	//Configurator: here we use a key to look up config value
	@Configurable(key="greetings") 
	private String message;

	@Inject 
	//Configurator inject from classname+fieldname
	private String text;

	@Inject
	//Configurator: aDefaultText does not exist in Configurator, then the defaultValue attribute value is used
	@Configurable(defaultValue="a default value from annotation")
	private String aDefaultText;

	
	@PersistenceContext(unitName="pu")
	EntityManager em;
	
	public Student enrollStudent(Student student) {
		//Configurator: here we used the injected configuration
		log.info("Configurator: text: " + text);
		log.info("Configurator: message: " + message);
		log.info("Configurator: enDefaultTekst: " + aDefaultText);
		if (student.getStudentnumber()!=null) {
			throw new IllegalArgumentException("Student already has a student number and cannot be enrolled!");
		}
		if (student.getId()!=null) {
			throw new IllegalArgumentException("Student already has an id (" + student.getId() + ")");
		}
		student.setStudentnumber(getStudentnumber());
		em.persist(student);
		
		log.info("Student is enrolled!");
		return student;
	}
	
	//Simulates the creation of a student number with 10 digits
	private String getStudentnumber() {
		return String.format("%010d", new Random().nextInt(1000000000));
	}
}
