package dk.lundogbendsen.dao;

import java.util.List;

import dk.lundogbendsen.model.CourseInstance_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

import dk.lundogbendsen.model.CourseDescription;
import dk.lundogbendsen.model.CourseInstance;

import dk.lundogbendsen.model.Instructor;

public class CourseInstanceDao {

	@PersistenceContext
	EntityManager entityManager;
	
	public CourseInstanceDao(EntityManager em) {
		this.entityManager = em;
	}
	
	public CourseInstanceDao() {
	}

	public CourseInstance create(CourseDescription description,
			Instructor instructor) {
		CourseInstance c = new CourseInstance();
		c.setCourseDescription(description);
		c.setInstructor(instructor);

		entityManager.persist(c);
		
		return c;
	}

	//demonstrating a criteria query
	public List<CourseInstance> findByInstructor(Instructor i) {
		// the following criteria query is equivalent to:
		// SELECT ci FROM CourseInstance ci WHERE ci.instructor = :i
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<CourseInstance> q = cb.createQuery(CourseInstance.class);

		Root<CourseInstance> from = q.from(CourseInstance.class);

		Path<Instructor> instructorPath = from.get(CourseInstance_.instructor);

		q.where(cb.equal(instructorPath, i));

		return entityManager.createQuery(q).getResultList();
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
