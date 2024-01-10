package dk.lundogbendsen.client.standalone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import dk.lundogbendsen.model.Car;
import dk.lundogbendsen.model.Person;
import dk.lundogbendsen.jpa.util.JpaUtil;
import dk.lundogbendsen.string.util.StringUtil;

public class StandaloneJpaClient {

	public static void main(String[] args) {

		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StandaloneJpaTestPersistenceUnit");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

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
		thomas.addOwnedCar(skoda);
		thomas.addOwnedCar(opel);
		lise.addOwnedCar(fiat);

		entityManager.getTransaction().commit();
		
		jpaql = "select p from Person p";
		JpaUtil.prettyPrintQueryResult(jpaql, entityManager.createQuery(jpaql).getResultList());
		jpaql = "select c from Car c";
		JpaUtil.prettyPrintQueryResult(jpaql, entityManager.createQuery(jpaql).getResultList());
		
//		entityManager.getTransaction().commit();
		
		// /////////////////////////////////////////////////////////////
		StringUtil.prettyPrintHeadline("Remove car from Thomas");
		entityManager.getTransaction().begin();
		thomas.removeOwnedCar(skoda);

		jpaql = "select p from Person p";
		JpaUtil.prettyPrintQueryResult(jpaql, entityManager.createQuery(jpaql).getResultList());
		jpaql = "select c from Car c";
		JpaUtil.prettyPrintQueryResult(jpaql, entityManager.createQuery(jpaql).getResultList());
				
		entityManager.getTransaction().commit();

		// /////////////////////////////////////////////////////////////
		StringUtil.prettyPrintHeadline("Move car from Thomas to Lise - but without securing referential integrity");
		entityManager.getTransaction().begin();
		lise.addOwnedCar(opel);
		

		jpaql = "select p from Person p";
		JpaUtil.prettyPrintQueryResult(jpaql, entityManager.createQuery(jpaql).getResultList());
		jpaql = "select c from Car c";
		JpaUtil.prettyPrintQueryResult(jpaql, entityManager.createQuery(jpaql).getResultList());
				
		try{
			entityManager.getTransaction().commit();
		}catch(Exception e){
			assert true;
		}
		// /////////////////////////////////////////////////////////////
		StringUtil.prettyPrintHeadline("Move car from Thomas to Lise - this time assuring referential integrity");
		entityManager.getTransaction().begin();
		// It is left for the developer to remember both add and remove operations.
		lise.addOwnedCar(opel);
		thomas.removeOwnedCar(opel);
		

		jpaql = "select p from Person p";
		JpaUtil.prettyPrintQueryResult(jpaql, entityManager.createQuery(jpaql).getResultList());
		jpaql = "select c from Car c";
		JpaUtil.prettyPrintQueryResult(jpaql, entityManager.createQuery(jpaql).getResultList());
				
		entityManager.getTransaction().commit();
		 /////////////////////////////////////////////////////////////		entityManager.close();
	}
}