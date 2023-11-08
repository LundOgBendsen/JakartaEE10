package dk.lundogbendsen.rest.domain;

import jakarta.ws.rs.GET;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {

	private int id;
	private String name;
	private String company;
	private String address;
	private String zip;
	private String city;

	@GET
	public Student get() {
		return this;
	}
	
	public Student() {
	}

	public Student(int id, String name, String company, String address,
			String zip, String city) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.address = address;
		this.zip = zip;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
