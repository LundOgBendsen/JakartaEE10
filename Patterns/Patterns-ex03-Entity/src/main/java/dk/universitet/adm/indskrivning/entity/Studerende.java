package dk.universitet.adm.indskrivning.entity;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REFRESH;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.interceptor.Interceptors;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import static dk.universitet.adm.utils.ThreadLocalEntityManager.*;

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
	

	//Entity: domæneobjektet indeholder selv forretningslogikken
	public Studerende indskriv() {
		if (this.getId()!=null) {
			throw new IllegalArgumentException("Studerende har allerede en id (" + this.getId() + ")");			
		}
		this.setStudienummer(getNextStudienummer());
		em().persist(this); //em() er statisk importeret
		return this;
	}
    
	//Simulerer dannelsen af et nyt studienummer på 10 cifre med 0-padding.
	private String getNextStudienummer() {		
		return String.format("%010d", new Random().nextInt(1000000000));
	}
	
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
