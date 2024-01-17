package dk.university.adm.enrollment.boundary;

import java.util.Random;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import dk.university.adm.model.Student;

@Stateless
//Boundary: should have requires_new tx-support, so it communicates that tx starts here.
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class Enrollment {
	Logger log = Logger.getLogger(Enrollment.class.getName());

	//Boundary: injected field has default access, i.e. accessible from test cases
	@PersistenceContext(unitName="pu")
	EntityManager em;
	
	/*Boundary: Here we choose to return an entity. Alternatively DTOs*/
	public Student enrollStudent(Student student) {
		if (student.getStudentnumber()!=null) {
			throw new IllegalArgumentException("Student already has a student number an cannot be enrolled!");
		}
		if (student.getId()!=null) {
			throw new IllegalArgumentException("Student already has an ID (" + student.getId() + ")");
		}
		student.setStudentnumber(getStudentNumber());
		em.persist(student);
		
		student = em.merge(student);
		log.info("Student is enrolled!");
		return student;
	}
	
	//Simulates creation of a new student number, 10 digits, zero padded
	private String getStudentNumber() {
		return String.format("%010d", new Random().nextInt(1000000000));
	}
}
