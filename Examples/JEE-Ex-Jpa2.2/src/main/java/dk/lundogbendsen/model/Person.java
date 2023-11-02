package dk.lundogbendsen.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

//New in JPA 2.2: NamedQuery is now @Repeatable
@NamedQuery(name = "Person.FindByFirstName", query = "select p from Person p where p.firstName=:firstName")
@NamedQuery(name = "Person.FindByLastName", query = "select p from Person p where p.lastName=:lastName")
@Entity
public class Person implements Comparable<Person>{

	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	
	private LocalDate birthDate;  //New in JPA 2.2: Localdate is supported. 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ "]";
	}

	@Override
	public int compareTo(Person o) {
		if (o==null) return 0;
		
		if (getBirthDate().compareTo(o.getBirthDate()) != 0) {
			return getBirthDate().compareTo(o.getBirthDate());
		}
		
		if (getFirstName().compareTo(o.getFirstName()) != 0) {
			return getFirstName().compareTo(o.getFirstName());
		}
		
		return getLastName().compareTo(o.getLastName());
	}
}
