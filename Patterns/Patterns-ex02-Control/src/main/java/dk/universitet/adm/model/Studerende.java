package dk.universitet.adm.model;

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
import jakarta.validation.constraints.Null;

@Entity
public class Studerende {
	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 30)
	private String fornavn;
	@Column(length = 30)
	private String efternavn;
	@Column(unique = true, length = 10)
	private String cprnummer;
	@Column(unique = true, length = 10)
	private String studienummer;
	@ManyToMany(cascade = { PERSIST, REFRESH, MERGE })
	private List<Fag> fag = new ArrayList<Fag>();
	@ManyToOne(cascade={ PERSIST, REFRESH, MERGE })
	private Adresse adresse;
	
	public Long getId() {
		return id;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEfternavn() {
		return efternavn;
	}

	public void setEfternavn(String efternavn) {
		this.efternavn = efternavn;
	}

	public String getCprnummer() {
		return cprnummer;
	}

	public void setCprnummer(String cprnummer) {
		this.cprnummer = cprnummer;
	}

	public String getStudienummer() {
		return studienummer;
	}

	public void setStudienummer(String studienummer) {
		this.studienummer = studienummer;
	}

	public List<Fag> getFag() {
		return fag;
	}

	public void setFag(List<Fag> fag) {
		this.fag = fag;
	}
	
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((studienummer == null) ? 0 : studienummer.hashCode());
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
		Studerende other = (Studerende) obj;
		if (studienummer == null) {
			if (other.studienummer != null)
				return false;
		} else if (!studienummer.equals(other.studienummer))
			return false;
		return true;
	}
}
