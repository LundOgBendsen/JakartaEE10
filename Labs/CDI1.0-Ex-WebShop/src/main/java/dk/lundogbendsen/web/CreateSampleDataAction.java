package dk.lundogbendsen.web;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import dk.lundogbendsen.annotations.LoggedCall;
import dk.lundogbendsen.jpa.Customer;
import dk.lundogbendsen.jpa.Product;

@Named
@Stateless
public class CreateSampleDataAction {

	@PersistenceContext(unitName = "primary")
	EntityManager em;

	@Inject
	Messages messages;

	@LoggedCall
	public boolean isDataAvailable() {
		Number count = (Number) em.createQuery(
				"SELECT COUNT(c) FROM Customer c").getSingleResult();
		if (count.intValue() == 0) {
			return false;
		}
		return true;
	}

	@LoggedCall
	public void exec() {
		if (isDataAvailable()) {
			messages
					.add("Cannot create sample data because the database is not empty");
		} else {
			Product johnMayer = new Product("CD: John Mayer - Battle Studies", 129.0);
			em.persist(johnMayer);
			Product thisIsIt = new Product("CD: Michael Jackson - This is it", 149.0);
			em.persist(thisIsIt);
			Product nightAtTheMuseum = new Product("DVD: Night at the Museum 2", 179.0);
			em.persist(nightAtTheMuseum);
			Product bruno = new Product("DVD: Bruno", 169.0);
			em.persist(bruno);
			Product uncharted2 = new Product("PS3: Uncharted 2", 429.0);
			em.persist(uncharted2);
			Product callOfDuty = new Product("PS3: Call of Duty - World at War", 299.0);
			em.persist(callOfDuty);

			Customer kasper = new Customer("Kasper", "dk");
			kasper.getWishListProducts().add(uncharted2);
			kasper.getWishListProducts().add(bruno);
			em.persist(kasper);
			Customer john = new Customer("John", "uk");
			john.getWishListProducts().add(callOfDuty);
			em.persist(john);

			messages
					.add("Sample data created with two customers: Kasper & John");
		}
	}
}
