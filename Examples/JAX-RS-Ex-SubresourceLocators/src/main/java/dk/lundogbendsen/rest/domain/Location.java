package dk.lundogbendsen.rest.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Location {
	private String name;
	private String address;
	private String zip;
	private String city;
	private List<Room> rooms = new ArrayList<Room>();

	public Location() {
		// TODO Auto-generated constructor stub
	}

	public Location(String name, String address, String zip, String city) {
		super();
		this.name = name;
		this.address = address;
		this.zip = zip;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

}
