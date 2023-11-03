package dk.lundogbendsen.ejb;

import java.util.List;

import jakarta.ejb.Remote;

import dk.lundogbendsen.model.Person;

@Remote
public interface FamilyService {
	void persistFamily(List<Person> family);
}