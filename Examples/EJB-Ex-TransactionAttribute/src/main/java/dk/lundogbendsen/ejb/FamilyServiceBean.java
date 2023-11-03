package dk.lundogbendsen.ejb;

import java.util.List;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;

import dk.lundogbendsen.ejb.dao.PersonDao;
import dk.lundogbendsen.model.Person;

@Stateless
// Not necessary since it is default for EJBs:
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FamilyServiceBean implements FamilyService {

	@EJB
	PersonDao personDao;

	// @TransactionAttribute(TransactionAttributeType.NEVER)
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistFamily(List<Person> family) {
		for (Person person : family) {
			personDao.persist(person);
		}
	}
}
