package dk.lundogbendsen.client.standalone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import dk.lundogbendsen.jpa.util.JpaUtil;
import dk.lundogbendsen.string.util.StringUtil;
import dk.lundogbendsen.model.*;


public class StandaloneJpaClient {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("StandaloneJpaTestPersistenceUnit");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {

			// /////////////////////////////////////////////////////////////
			StringUtil.prettyPrintHeadline("Create 2 owners and 3 cars");
			entityManager.getTransaction().begin();
			Person thomas = new Person("Thomas");
			entityManager.persist(thomas);
			Person lise = new Person("Lise");
			entityManager.persist(lise);

			Car skoda = new Car("Skoda");
			entityManager.persist(skoda);
			Car opel = new Car("Opel");
			entityManager.persist(opel);
			Car fiat = new Car("Fiat");
			entityManager.persist(fiat);

			String jpaql = "select p from Person p";
			JpaUtil.prettyPrintQueryResult(jpaql, entityManager.createQuery(jpaql).getResultList());
			jpaql = "select c from Car c";
			JpaUtil.prettyPrintQueryResult(jpaql, entityManager.createQuery(jpaql).getResultList());

			entityManager.getTransaction().commit();

			// /////////////////////////////////////////////////////////////
			StringUtil.prettyPrintHeadline("Associate owners and cars");
			entityManager.getTransaction().begin();
			skoda.setOwner(thomas);
			opel.setOwner(thomas);
			fiat.setOwner(lise);

			jpaql = "select p from Person p";
			JpaUtil.prettyPrintQueryResult(jpaql, entityManager.createQuery(jpaql).getResultList());
			jpaql = "select c from Car c";
			JpaUtil.prettyPrintQueryResult(jpaql, entityManager.createQuery(jpaql).getResultList());

			entityManager.getTransaction().commit();

			// /////////////////////////////////////////////////////////////

			StringUtil.prettyPrintHeadline("Here we can not violate referential integrity");
			entityManager.getTransaction().begin();
			skoda.setOwner(lise);

			jpaql = "select p from Person p";
			JpaUtil.prettyPrintQueryResult(jpaql, entityManager.createQuery(jpaql).getResultList());
			jpaql = "select c from Car c";
			JpaUtil.prettyPrintQueryResult(jpaql, entityManager.createQuery(jpaql).getResultList());

			entityManager.getTransaction().commit();

			// /////////////////////////////////////////////////////////////
			entityManager.close();

		} catch (Exception e) {
			entityManager.close();
			entityManagerFactory.close();
		}
	}
}