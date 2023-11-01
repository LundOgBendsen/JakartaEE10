package dk.lundogbendsen.client.standalone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import dk.lundogbendsen.jpa.util.JpaUtil;
import dk.lundogbendsen.model.Bird;
import dk.lundogbendsen.model.Dog;
import dk.lundogbendsen.model.Person;
import dk.lundogbendsen.model.Pet;
import dk.lundogbendsen.string.util.StringUtil;

public class StandaloneJpaClient {

	public static void main(String[] args) {

		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StandaloneJpaTestPersistenceUnit");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// /////////////////////////////////////////////////////////////
		StringUtil.prettyPrintHeadline("Create 2 owners and 4 pets");
		entityManager.getTransaction().begin();
		Person thomas = new Person("Thomas");
		entityManager.persist(thomas);
		Person lise = new Person("Lise");
		entityManager.persist(lise);

		Pet poppe = new Bird("Poppe", false);
		entityManager.persist(poppe);
		Pet andrea = new Bird("Andrea", true);
		entityManager.persist(andrea);
		Pet emma = new Dog("Emma", false);
		entityManager.persist(emma);
		Pet anton = new Dog("Anton", false);
		entityManager.persist(anton);

		String jpaql = "select p from Person p";
		JpaUtil.prettyPrintQueryResult(jpaql, entityManager.createQuery(jpaql).getResultList());
		jpaql = "select p from Pet p";
		JpaUtil.prettyPrintQueryResult(jpaql, entityManager.createQuery(jpaql).getResultList());

		entityManager.getTransaction().commit();
		
		// /////////////////////////////////////////////////////////////
		StringUtil.prettyPrintHeadline("Associate owners and pets");
		entityManager.getTransaction().begin();
		thomas.addOwnedPet(poppe);
		lise.addOwnedPet(andrea);
		lise.addOwnedPet(emma);

		jpaql = "select p from Person p";
		JpaUtil.prettyPrintQueryResult(jpaql, entityManager.createQuery(jpaql).getResultList());
		jpaql = "select p from Pet p";
		JpaUtil.prettyPrintQueryResult(jpaql, entityManager.createQuery(jpaql).getResultList());
		
		entityManager.getTransaction().commit();
		
		// /////////////////////////////////////////////////////////////
		entityManager.close();
	}
}