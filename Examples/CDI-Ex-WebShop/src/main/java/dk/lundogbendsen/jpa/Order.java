package dk.lundogbendsen.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="Ordre")
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Customer customer;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<OrderLine> orderLines = new ArrayList<OrderLine>();
	private double totalPriceBeforeTax;
	private double totalPriceAfterTax;

	public Order(Customer customer, List<OrderLine> orderLines) {
		this.customer = customer;
		this.orderLines = orderLines;
	}

	public Order() {
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public double getTotalPriceBeforeTax() {
		return totalPriceBeforeTax;
	}

	public void setTotalPriceBeforeTax(double totalPriceBeforeTax) {
		this.totalPriceBeforeTax = totalPriceBeforeTax;
	}

	public double getTotalPriceAfterTax() {
		return totalPriceAfterTax;
	}

	public void setTotalPriceAfterTax(double totalPriceAfterTax) {
		this.totalPriceAfterTax = totalPriceAfterTax;
	}

	public double calculateTotalPriceBeforeTax() {
		double result = 0;
		for (OrderLine ol : orderLines) {
			double orderLinePrice = ol.calculateTotalPriceBeforeTax();
			result += orderLinePrice;
		}
		return result;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", totalPriceAfterTax=" + totalPriceAfterTax
				+ ", totalPriceBeforeTax=" + totalPriceBeforeTax + "]";
	}
}
