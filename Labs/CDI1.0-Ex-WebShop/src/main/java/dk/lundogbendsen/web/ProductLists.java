package dk.lundogbendsen.web;

import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import dk.lundogbendsen.annotations.All;
import dk.lundogbendsen.annotations.LoggedIn;
import dk.lundogbendsen.annotations.WishList;
import dk.lundogbendsen.jpa.Customer;
import dk.lundogbendsen.jpa.Product;

@Dependent
public class ProductLists {

	@PersistenceContext(unitName = "primary")
	EntityManager em;

	@Inject @LoggedIn
	Customer customer;

	@Named("allProducts")
	@All
	@Produces
	public List<Product> getAllProducts() {
		Query q = em.createQuery("SELECT p FROM Product p");

		@SuppressWarnings("unchecked")
		List<Product> resultList = q.getResultList();
		return resultList;
	}

	@Named("wishListProducts")
	@WishList
	@Produces
	public List<Product> getWishListProducts() {
		if (customer == null) {
			System.out.println("No @LoggedIn Customer available");
			return Collections.emptyList();
		}
		return customer.getWishListProducts();
	}
}
