package dk.university.adm.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

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

	public List<Subject> getSubject() {
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
