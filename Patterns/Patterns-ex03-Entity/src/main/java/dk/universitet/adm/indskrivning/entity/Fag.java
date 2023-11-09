package dk.universitet.adm.indskrivning.entity;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REFRESH;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Fag {
	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 40)
	private String navn;
	private double ectspoints;
	@Column(length = 30)
	private String campus;
	@ManyToMany(mappedBy="fag")
	List<Studerende> studerende = new ArrayList<Studerende>();
	@ManyToOne(cascade={PERSIST,REFRESH,MERGE})
	Studienaevn studienaevn;
	
	
	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public double getEctspoints() {
		return ectspoints;
	}

	public void setEctspoints(double ectspoints) {
		this.ectspoints = ectspoints;
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
	
	public List<Studerende> getStuderende() {
		return studerende;
	}

	public void setStuderende(List<Studerende> studerende) {
		this.studerende = studerende;
	}

	
	
	public Studienaevn getStudienaevn() {
		return studienaevn;
	}

	public void setStudienaevn(Studienaevn studienaevn) {
		this.studienaevn = studienaevn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campus == null) ? 0 : campus.hashCode());
		long temp;
		temp = Double.doubleToLongBits(ectspoints);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((navn == null) ? 0 : navn.hashCode());
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
		Fag other = (Fag) obj;
		if (campus == null) {
			if (other.campus != null)
				return false;
		} else if (!campus.equals(other.campus))
			return false;
		if (Double.doubleToLongBits(ectspoints) != Double.doubleToLongBits(other.ectspoints))
			return false;
		if (navn == null) {
			if (other.navn != null)
				return false;
		} else if (!navn.equals(other.navn))
			return false;
		return true;
	}

	
}
