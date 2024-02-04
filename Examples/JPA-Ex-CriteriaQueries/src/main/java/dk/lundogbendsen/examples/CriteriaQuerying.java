package dk.lundogbendsen.examples;

import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import dk.lundogbendsen.dao.CourseInstanceDao;
import dk.lundogbendsen.model.CourseInstance;
import dk.lundogbendsen.model.Instructor;

public class CriteriaQuerying {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StandaloneJpaTestPersistenceUnit");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Instructor k = new Instructor();
		k.setName("Kent Beck");
		em.persist(k);

		Instructor j = new Instructor();
		j.setName("James Gosling");
		em.persist(j);

		Instructor r = new Instructor();
		r.setName("Roy Fielding");
		em.persist(r);

		em.persist(new CourseInstance(k, new Date()));
		em.persist(new CourseInstance(j, new Date()));
		em.persist(new CourseInstance(r, new Date()));
		em.persist(new CourseInstance(k, new Date()));

		em.getTransaction().commit();

		CourseInstanceDao dao = new CourseInstanceDao(em);
		List<CourseInstance> instances = dao.findByInstructor(k);

		for (CourseInstance courseInstance : instances) {
			System.out.println("Found instance: " + courseInstance);
		}

		em.close();
	}
}
