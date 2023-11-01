package dk.lundogbendsen.examples;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.RollbackException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import dk.lundogbendsen.model.CourseInstance;
import dk.lundogbendsen.model.Student;

public class BeanValidation implements Serializable {

	/**
	 * 
	 */ 
	private static final long serialVersionUID = 4840129998310778277L;

	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StandaloneJpaTestPersistenceUnit");
		EntityManager em = emf.createEntityManager();

		persist(em, new Student());
		persist(em, new Student("Roy Fielding", null, null));
		persist(em, new Student("Ada Lovelace", "ad@lovelace.com", null));
		persist(em, new Student("Alan Turing", "alan@turing.dk", null));

		CourseInstance course = new CourseInstance(null, new Date());
		persist(em, course);
		persist(em, new Student("James Gosling", "james@gosling.dk", course));

		em.close();
	}

	private static void persist(EntityManager em, Object o) {
		System.out.println("******* Saves: " + o + " *******");
		try {
			em.getTransaction().begin();
			em.persist(o);
			em.getTransaction().commit();
			System.out.println("  It went ok!");
		} catch (RollbackException ex) {
			ConstraintViolationException e = (ConstraintViolationException) ex.getCause();
			
			System.out.println("  An error occured: " + e.getMessage());
			Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
			for (ConstraintViolation<?> constraintViolation : constraintViolations) {
				System.out.println("   - " + constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage());
			}
			
		}
		
		System.out.println();
	}
}
