package dk.lundogbendsen.client.standalone;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import dk.lundogbendsen.jpa.util.JpaUtil;
import dk.lundogbendsen.model.Person;
import dk.lundogbendsen.string.util.StringUtil;

public class StandaloneJpaClient {

//	@PersistenceContext(unitName="StandaloneJpaTestPersistenceUnit") //When in a JEE container this can be injected
//	private static EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		
		// ///////////////////////////////////////////////////////////////////////
		// Create new JPA entity manager (similar to JDBC Connection)
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StandaloneJpaTestPersistenceUnit");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// ///////////////////////////////////////////////////////////////////////
		StringUtil.prettyPrintHeadline("Create and persist persons some persons");
		// Begin a new JTA transaction
		entityManager.getTransaction().begin();
		// Persist is used to save an entity instance
		Person person = new Person("Tomas");
		// At this moment Tomas is in-memory only (will dissappear at shutdown)
		entityManager.persist(person);
		// Now Tomas is made persistent, which means, that he will stay in the
		// database after the Java program has shutdown.
		// Let's make some more person entities and save them to the database.
		entityManager.persist(new Person("Jens"));
		entityManager.persist(new Person("Ole"));
		entityManager.persist(new Person("Lotte"));
		entityManager.persist(new Person("Lise"));
		// Try to commit the current transaction (the commit may fail which will
		// result in the changes made in the transaction will be rollback and an
		// exception will be thrown)
		entityManager.getTransaction().commit();
		// Clear cached entities from the entityManager (called managed entities
		// in JPA). We will talk more about what "managed" means later on...
		entityManager.clear();

		// ///////////////////////////////////////////////////////////////////////
		StringUtil.prettyPrintHeadline("Find all persistent persons");
		entityManager.getTransaction().begin();
		// Notice the similarity to SQL. Altso notice that we can select the
		// full entity object, and not just its fields one by one (as in SQL).
		String jpaql = "select p from Person p";
		// A Query is similar to a Statements in JDBC
		Query query = entityManager.createQuery(jpaql);
		// Excecute the query and get the result.
		// The returned list is like getting ResultSet in JDBC.
		// Note that we can cast the non-generic list to a generic list!
		List<Person> resultList = (List<Person>) query.getResultList();
		JpaUtil.prettyPrintQueryResult(jpaql, resultList);
		entityManager.getTransaction().commit();
		entityManager.clear();

		// ///////////////////////////////////////////////////////////////////////
		StringUtil.prettyPrintHeadline("Use JPA QL count aggregate function");
		entityManager.getTransaction().begin();
		// Notice the use of the count-function
		jpaql = "select count(p) from Person p";
		query = entityManager.createQuery(jpaql);
		Long personCount = (Long) query.getSingleResult();
		JpaUtil.prettyPrintQueryResult(jpaql, personCount);
		entityManager.getTransaction().commit();
		entityManager.clear();

		// ///////////////////////////////////////////////////////////////////////
		StringUtil.prettyPrintHeadline("Use like in select");
		entityManager.getTransaction().begin();
		// % is any number of chars and _ is a single char (as in SQL)
		jpaql = "select p from Person p where p.name like 'L%e'";
		query = entityManager.createQuery(jpaql);
		resultList = (List<Person>) query.getResultList();
		JpaUtil.prettyPrintQueryResult(jpaql, resultList);
		entityManager.getTransaction().commit();
		entityManager.clear();

		// ///////////////////////////////////////////////////////////////////////
		StringUtil.prettyPrintHeadline("Use named parameter in JPA QL");
		entityManager.getTransaction().begin();
		// Use colon to define a named parameter
		jpaql = "select p from Person p where p.name like :namePattern";
		query = entityManager.createQuery(jpaql);
		// Set the value of the named parameter (don't include the colon)
		query.setParameter("namePattern", "%s%");
		System.out.println("Has set JPA QL parameter [:namePattern] to [%s%]");
		resultList = (List<Person>) query.getResultList();
		JpaUtil.prettyPrintQueryResult(jpaql, resultList);
		entityManager.getTransaction().commit();
		entityManager.clear();

		// ///////////////////////////////////////////////////////////////////////
		StringUtil.prettyPrintHeadline("Find by entity type and entity primary key");
		entityManager.getTransaction().begin();
		// We can find an antity without any JPA QL if we know its type
		// (e.g. Person.class) and the value of its primary key (here 2 using
		// autoboxing to convert from int to Integer which is the pk type of
		// Person ).
		Person personJens = entityManager.find(Person.class, 2);
		JpaUtil.prettyPrintQueryResult("Using find(entityType, entityPrimaryKey)", personJens);
		entityManager.getTransaction().commit();
		entityManager.clear();

		// ///////////////////////////////////////////////////////////////////////
		StringUtil.prettyPrintHeadline("Removing an entity");
		entityManager.getTransaction().begin();
		Person personToDelete = entityManager.find(Person.class, 2);
		// The remove method is one way of deleting entity instances from the db
		entityManager.remove(personToDelete);
		System.out.println("Has removed Jens");
		jpaql = "select p from Person p";
		query = entityManager.createQuery(jpaql);
		resultList = (List<Person>) query.getResultList();
		JpaUtil.prettyPrintQueryResult(jpaql, resultList);
		entityManager.getTransaction().commit();
		entityManager.clear();

		// ///////////////////////////////////////////////////////////////////////
		StringUtil.prettyPrintHeadline("Updating an entity");
		entityManager.getTransaction().begin();
		Person personBeforeEdit = entityManager.find(Person.class, 1);
		JpaUtil.prettyPrintQueryResult("Person before edit", personBeforeEdit);
		// One way of editing an entity is to load it from the database
		// and then edit the object directly. JPA will automatically
		// detect the change and write the changed entity state to the
		// database at commit time. This only works if the entity is
		// managed (we will talk about that later on).
		personBeforeEdit.setName("Thomas");
		Person personAfterEdit = entityManager.find(Person.class, 1);
		JpaUtil.prettyPrintQueryResult("Person after edit", personAfterEdit);
		entityManager.getTransaction().commit();
		entityManager.clear();

		// ///////////////////////////////////////////////////////////////////////
		StringUtil.prettyPrintHeadline("Use paging to go through huge result chunk by chunk");
		entityManager.getTransaction().begin();
		// Make more persons to make paging more fun!
		entityManager.persist(new Person("Charlotte"));
		entityManager.persist(new Person("Thomas"));
		entityManager.persist(new Person("J�rgen"));
		entityManager.persist(new Person("Sidsel"));
		entityManager.getTransaction().commit();
		entityManager.clear();

		// How many persons exist in the database?
		entityManager.getTransaction().begin();
		jpaql = "select count(p) from Person p";
		query = entityManager.createQuery(jpaql);
		personCount = (Long) query.getSingleResult();
		entityManager.getTransaction().commit();
		entityManager.clear();

		// How many pages should we split the result in?
		int totalSize = personCount.intValue();
		int pageSize = 3;
		double noOfPagesAsDouble = ((double) totalSize) / pageSize;
		int noOfPages = (int) Math.ceil(noOfPagesAsDouble);
		// Go through all the persons chunk by chunk
		jpaql = "select p from Person p";
		query = entityManager.createQuery(jpaql);
		// We only want 3 persons at a time
		query.setMaxResults(pageSize);
		for (int n = 0; n < noOfPages; n++) {
			entityManager.getTransaction().begin();
			// The index of the first of the 3 persons we want
			query.setFirstResult(n * pageSize);
			resultList = (List<Person>) query.getResultList();
			System.out.println("Page[" + n + "] -> " + resultList);
			entityManager.getTransaction().commit();
			entityManager.clear();
		}

		// ///////////////////////////////////////////////////////////////////////
		// Free resources used by the JPA entity manager (should have been done
		// in a finally block to make sure close is called even if we get an
		// exception)
		entityManager.close();

		// ///////////////////////////////////////////////////////////////////////
		StringUtil.prettyPrintHeadline("You might want to take a look at the database.");
		System.out.println("1) Connect to JpaTest using the Data Source Explorer view.");
		System.out.println("2) The location of the person entity is:");
		System.out.println("   JpaTest/JpaTest/Schemas/JPATESTUSERNAME/Tables/PERSON");
		System.out.println("3) Right click PERSON and select Data -> Sample Content.");
		System.out.println("4) View the content in the SQL Result view.");
		
	}
}