package dk.lundogbendsen.domain;

import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Room {
	private int id;
	private String local_name;
	private int capacity;
	private Location location;

	public Room() {
	}

	public Room(int id, String local_name, int capacity) {
		this.id = id;
		this.local_name = local_name;
		this.capacity = capacity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocalName() {
		return local_name;
	}

	public void setLocalName(String local_name) {
		this.local_name = local_name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
