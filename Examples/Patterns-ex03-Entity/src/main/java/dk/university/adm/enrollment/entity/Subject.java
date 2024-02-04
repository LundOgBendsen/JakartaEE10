package dk.university.adm.enrollment.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
public class Subject {
	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 40)
	private String name;
	private double ectsPoints;
	@Column(length = 30)
	private String campus;
	@ManyToMany(mappedBy="subject")
	List<Student> student = new ArrayList<Student>();
	@ManyToOne(cascade={PERSIST,REFRESH,MERGE})
	Studyboard studyboard;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getEctsPoints() {
		return ectsPoints;
	}

	public void setEctsPoints(double ectsPoints) {
		this.ectsPoints = ectsPoints;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public Long getId() {
		return id;
	}
	
	public List<Student> getStuderende() {
		return student;
	}

	public void setStudents(List<Student> student) {
		this.student = student;
	}

	
	
	public Studyboard getStudyboard() {
		return studyboard;
	}

	public void setStudyboard(Studyboard studyboard) {
		this.studyboard = studyboard;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campus == null) ? 0 : campus.hashCode());
		long temp;
		temp = Double.doubleToLongBits(ectsPoints);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Subject other = (Subject) obj;
		if (campus == null) {
			if (other.campus != null)
				return false;
		} else if (!campus.equals(other.campus))
			return false;
		if (Double.doubleToLongBits(ectsPoints) != Double.doubleToLongBits(other.ectsPoints))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
}
