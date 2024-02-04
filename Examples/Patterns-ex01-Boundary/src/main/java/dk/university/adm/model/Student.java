package dk.university.adm.model;

import static jakarta.persistence.CascadeType.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Student {
	@Id
	@GeneratedValue() 
	private Long id;
	@Column(length = 30)
	private String firstName;
	@Column(length = 30)
	private String lastName;
	@Column(unique = true, length = 10)
	private String cpr;
	@Column(unique = true, length = 10)
	private String studentnumber;
	@ManyToMany(cascade = { PERSIST, REFRESH, MERGE}, fetch=FetchType.EAGER)
	private List<Subject> subject = new ArrayList<Subject>();
	@ManyToOne(cascade={ PERSIST, REFRESH, MERGE })
	private Address address;

	public Long getId() {
		return id;
	}
    String getFirstName() {
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

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	public String getStudentnumber() {
		return studentnumber;
	}

	public void setStudentnumber(String studentnumber) {
		this.studentnumber = studentnumber;
	}

	public List<Subject> getFag() {
		return subject;
	}

	public void setFag(List<Subject> subject) {
		this.subject = subject;
	}
	
	public Address getAdresse() {
		return address;
	}

	public void setAdresse(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((studentnumber == null) ? 0 : studentnumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (studentnumber == null) {
			if (other.studentnumber != null)
				return false;
		} else if (!studentnumber.equals(other.studentnumber))
			return false;
		return true;
	}
}
