package dk.lundogbendsen.ejb.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import dk.lundogbendsen.model.Person;

@Stateless
// Not necessary since it is default for EJBs:
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PersonDaoBean implements PersonDao {

	@PersistenceContext(unitName = "ServerJpaTestPersistenceUnit")
	EntityManager entityManager;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	// Not necessary since it is default for EJBs:
	public void persist(Person person) {
		entityManager.persist(person);
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Person findPerson(Integer id) {
		return entityManager.find(Person.class, id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removePerson(Person person) {
		entityManager.remove(person);
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Person> findAllWithName(String name) {
		Query q = entityManager.createQuery("select p from Person p where p.name = :name");
		q.setParameter("name", name);
		List<Person> result = (List<Person>) q.getResultList();
		return result;
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public long countPersons() {
		Query q = entityManager.createQuery("select count(p) from Person p");
		Long count = (Long) q.getSingleResult();
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Person> findAllPersons() {
		Query q = entityManager.createQuery("select p from Person p");
		List<Person> result = (List<Person>) q.getResultList();
		return result;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveChanges(Person person) {
		entityManager.merge(person);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int deleteAllPersons() {
		int rowsAffected = entityManager.createQuery("delete from Person").executeUpdate();
		return rowsAffected;
	}
}
