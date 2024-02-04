package dk.lundogbendsen.client.standalone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import dk.lundogbendsen.dao.PersonRepositoryImpl;
import dk.lundogbendsen.jpa.util.EntityPager;
import dk.lundogbendsen.model.Person;

public class StandaloneJpaClient {

	public static void main(String[] args) {

		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StandaloneJpaTestPersistenceUnit");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// Instantiate DAO
		PersonRepositoryImpl personRepository = new PersonRepositoryImpl();

		// Tell the DAO which entity manager to use
		personRepository.setEntityManager(entityManager);

		// Start new TX on the entity manager the DAO uses. Notice how we can
		// control the transactions to span one or more DAO method invocations.
		entityManager.getTransaction().begin();
		personRepository.persist(new Person("Ole"));
		personRepository.persist(new Person("Charlotte"));
		personRepository.persist(new Person("Inga"));
		personRepository.persist(new Person("Oluf"));
		personRepository.persist(new Person("Søren"));
		personRepository.persist(new Person("Jesper"));
		personRepository.persist(new Person("Lisbeth"));
		entityManager.getTransaction().commit();

		// Find AND remove person in one tx
		entityManager.getTransaction().begin();
		Person p = personRepository.findPerson(2);
		personRepository.removePerson(p);
		entityManager.getTransaction().commit();
		
		// The DAO can support avanced helpers like the pager below
		entityManager.getTransaction().begin();
		EntityPager<Person> personPager = personRepository.findAllPersonsAsPager(2);
		System.out.println(personPager);
		System.out.println();
		while (personPager.hasNextPage()) {
			System.out.println(personPager.nextPage(entityManager));
			System.out.println(personPager);
			System.out.println();
		}
		entityManager.getTransaction().commit();

		// Free resources taken used by the JPA entity manager (should have been
		// a finally block)
		entityManager.close();
	}

}