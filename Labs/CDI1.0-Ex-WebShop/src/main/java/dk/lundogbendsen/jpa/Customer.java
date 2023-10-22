package dk.lundogbendsen.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String name;
	private String countryCode;

	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="Customer_WishList")
	private List<Product> wishListProducts = new ArrayList<Product>();
	
	public Customer() {
	}
	
	public Customer(String name, String countryCode) {
		this.name = name;
		this.countryCode = countryCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public List<Product> getWishListProducts() {
		return wishListProducts;
	}

	public void setWishListProducts(List<Product> wishListProducts) {
		this.wishListProducts = wishListProducts;
	}

	public boolean isDanish() {
		return "dk".equalsIgnoreCase(countryCode);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", countryCode="
				+ countryCode + "]";
	}
}
