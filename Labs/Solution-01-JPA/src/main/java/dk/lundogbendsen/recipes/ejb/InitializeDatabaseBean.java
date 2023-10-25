package dk.lundogbendsen.recipes.ejb;

import java.util.List;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import dk.lundogbendsen.recipes.model.Ingredient;
import dk.lundogbendsen.recipes.model.MeasureUnit;

@Singleton
@Startup
public class InitializeDatabaseBean {

	Logger log = Logger.getLogger(InitializeDatabaseBean.class.getSimpleName());

	@PersistenceContext(unitName="pu")
	EntityManager em;

	@PostConstruct
	public void init() {
		Ingredient milk = getMilk();
		em.persist(milk);
		log.info("Created milk: " + milk);

		Ingredient sugar = getSugar();
		em.persist(sugar);
		log.info("Created sugar: " + sugar);

		Query query = em.createNamedQuery("Ingredient.all");
		List<Ingredient> resultList = (List<Ingredient>) query.getResultList();
		for (Ingredient ingredient : resultList) {
			System.out.println(ingredient);
		}
		List<Ingredient> findByName = findByName("pandekager");


	}

	private List<Ingredient> findByName(String name) {
		Query query;
		List<Ingredient> resultList;
		query = em.createNamedQuery("Ingredient.findByName");
		query.setParameter("name", name);
		resultList = (List<Ingredient>) query.getResultList();
		for (Ingredient ingredient : resultList) {
			System.out.println(ingredient);
		}
		return resultList;
	}

	private Ingredient getSugar() {
		Ingredient sugar = new Ingredient();
		sugar.setName("Sugar");
		sugar.setUnit(MeasureUnit.Gr);
		sugar.setCalories(3.87f);
		return sugar;
	}

	private Ingredient getMilk() {
		Ingredient milk = new Ingredient();
		milk.setName("Milk");
		milk.setUnit(MeasureUnit.L);
		milk.setCalories(420);
		return milk;
	}

}
