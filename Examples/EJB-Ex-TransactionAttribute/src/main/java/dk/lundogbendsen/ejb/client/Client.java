package dk.lundogbendsen.ejb.client;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dk.lundogbendsen.ejb.FamilyService;
import dk.lundogbendsen.ejb.dao.PersonDao;
import dk.lundogbendsen.model.Person;

public class Client {
	private static final String EJB_JNDI_PERSONDAO = "EJB-Ex-TransactionAttribute-1.0-SNAPSHOT/PersonDaoBean!dk.lundogbendsen.ejb.dao.PersonDao";
	private static final String EJB_JNDI_FAMILYDAO = "EJB-Ex-TransactionAttribute-1.0-SNAPSHOT/FamilyServiceBean!dk.lundogbendsen.ejb.FamilyService";


	private static Context createInitialContext() throws NamingException {
		Hashtable<String, Object> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
		InitialContext ctx = new InitialContext(env);
		return ctx;
	}

	public static void main(String[] args) throws Exception {

		System.out.println("Calling EJB...");

		final Context ctx = createInitialContext();

		PersonDao personDao = (PersonDao) ctx.lookup(EJB_JNDI_PERSONDAO);

		FamilyService familyService = (FamilyService) ctx.lookup(EJB_JNDI_FAMILYDAO);

		{
			int noOfEntitiesDeleted = personDao.deleteAllPersons();
			System.out.println("**** Deleted " + noOfEntitiesDeleted + " old entities ****");
		}
		{
			List<Person> legalFamily = new LinkedList<Person>();
			legalFamily.add(new Person("Sidsel"));
			legalFamily.add(new Person("Kurt"));
			legalFamily.add(new Person("Jens"));
			try {
				familyService.persistFamily(legalFamily);
			} catch (Exception e) {
				System.out.println("Unexpected error: " + e);
			}
			System.out.println(personDao.findAllPersons());
		}
		{
			List<Person> illegalFamily = new LinkedList<Person>();
			illegalFamily.add(new Person("Dorthe"));
			illegalFamily.add(new Person("Pia"));
			illegalFamily.add(new Person(null));
			illegalFamily.add(new Person("Lise"));
			try {
				familyService.persistFamily(illegalFamily);
			} catch (Exception e) {
				System.out.println("Expected error: " + e);
			}
			System.out.println(personDao.findAllPersons());
		}

	}
}
