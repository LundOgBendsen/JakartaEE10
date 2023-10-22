package dk.lundogbendsen.web;

import java.io.Serializable;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import dk.lundogbendsen.annotations.LoggedIn;
import dk.lundogbendsen.jpa.Customer;

@Named
@SessionScoped
public class CustomerLogin implements Serializable {

	@PersistenceContext
	EntityManager em;

	private String name;
	private Customer customer;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isLoggedIn() {
		return customer != null;
	}

	
	public void login() {
		Query q = em
				.createQuery("SELECT c FROM Customer c WHERE c.name = :name");
		q.setParameter("name", name);

		@SuppressWarnings("unchecked")
		List<Customer> customers = q.getResultList();

		FacesMessage m;

		if (customers.isEmpty()) {
			customer = null;
			m = new FacesMessage("No customer with the name '" + name + "'");
		} else {
			customer = customers.get(0);
			m = new FacesMessage("Customer set: " + customer);
		}

		FacesContext.getCurrentInstance().addMessage(null, m);
	}

	@Produces
	@LoggedIn
	public Customer getCustomer() {
		return customer;
	}
}
