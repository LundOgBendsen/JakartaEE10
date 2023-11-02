package dk.lundogbendsen.ejb;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Stream;

import dk.lundogbendsen.model.Person;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;



@Singleton
@Startup
public class Initializer {
	Logger log = Logger.getLogger(Initializer.class.getName());

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	private void init() {
		for (int i = 0; i < 1000; i++) {
			Person person = new Person();
			person.setFirstName(getRandomFirstName());
			person.setLastName(getRandomLastName());
			person.setBirthDate(getRandomDate());
			em.persist(person);
			log.info("Created " + person);
		}
		printByFirstName("Ada");
		printByFirstNameAndMonth("Ada", 1);
	}

	private void printByFirstName(String firstName) {
		log.info("Find by first name: " + firstName);
		Query query = em.createNamedQuery("Person.FindByFirstName");
		query.setParameter("firstName", firstName);
		Stream stream = query.getResultStream();
		stream.forEach(p -> log.info("  " + p));
		log.info("-----------------");
	}

	
	private void printByFirstNameAndMonth(String firstName, int month) {
		log.info("Find by first name: " + firstName + " and month: " + month);
		TypedQuery<Person> query = em.createNamedQuery("Person.FindByFirstName", Person.class);		
		query.setParameter("firstName", firstName);
		Stream<Person> stream = query.getResultStream();
		stream.filter(p -> p.getBirthDate().getMonth()==Month.of(month)).sorted().forEach(p -> log.info("  " + p));
		log.info("-----------------");
	}
	
	private String getRandomFirstName() {
		List<String> firstNames = new ArrayList<String>() {
			{
				add("Ada");
				add("Grace");
				add("Blaise");
				add("Charles");
				add("Alan");
				add("George");
				add("Adele");
				add("John");
				add("Seymur");
				add("Tim");
			}
		};

		return firstNames.get((int) (Math.random() * firstNames.size()));
	}

	private String getRandomLastName() {
		List<String> lastNames = new ArrayList<String>() {
			{
				add("Lovelace");
				add("Hooper");
				add("Pascal");
				add("Babbage");
				add("Turing");
				add("Boole");
				add("Goldstine");
				add("von Neumann");
				add("Craig");
				add("Berners-Lee");
			}
		};

		return lastNames.get((int) (Math.random() * lastNames.size()));
	}

	/**
	 * 
	 * @return a random date in the period 1800-2000. Only days 1-28 is included.  
	 */
	private LocalDate getRandomDate() {
		return LocalDate.of(new Random().nextInt(200) + 1800, new Random().nextInt(12) + 1,
				new Random().nextInt(28) + 1);
	}
}
