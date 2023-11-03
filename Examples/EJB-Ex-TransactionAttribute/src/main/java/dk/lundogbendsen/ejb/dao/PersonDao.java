package dk.lundogbendsen.ejb.dao;

import java.util.List;

import jakarta.ejb.Remote;

import dk.lundogbendsen.model.Person;

@Remote
public interface PersonDao {

	void persist(Person person);

	Person findPerson(Integer id);

	List<Person> findAllWithName(String name);

	long countPersons();

	List<Person> findAllPersons();

	void removePerson(Person person);

	void saveChanges(Person person);

	int deleteAllPersons();
}