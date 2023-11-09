package dk.universitet.adm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Adresse {
	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 30)
	private String vejnavn;
	@Column(length = 10)
	private String husnummer;
	private Integer postnummer;
	@Column(length = 10)
	private String etage;

	public String getVejnavn() {
		return vejnavn;
	}

	public void setVejnavn(String vejnavn) {
		this.vejnavn = vejnavn;
	}

	public String getHusnummer() {
		return husnummer;
	}

	public void setHusnummer(String husnummer) {
		this.husnummer = husnummer;
	}

	public Integer getPostnummer() {
		return postnummer;
	}

	public void setPostnummer(Integer postnummer) {
		this.postnummer = postnummer;
	}

	public String getEtage() {
		return etage;
	}

	public void setEtage(String etage) {
		this.etage = etage;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((etage == null) ? 0 : etage.hashCode());
		result = prime * result + ((husnummer == null) ? 0 : husnummer.hashCode());
		result = prime * result + ((postnummer == null) ? 0 : postnummer.hashCode());
		result = prime * result + ((vejnavn == null) ? 0 : vejnavn.hashCode());
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
		Adresse other = (Adresse) obj;
		if (etage == null) {
			if (other.etage != null)
				return false;
		} else if (!etage.equals(other.etage))
			return false;
		if (husnummer == null) {
			if (other.husnummer != null)
				return false;
		} else if (!husnummer.equals(other.husnummer))
			return false;
		if (postnummer == null) {
			if (other.postnummer != null)
				return false;
		} else if (!postnummer.equals(other.postnummer))
			return false;
		if (vejnavn == null) {
			if (other.vejnavn != null)
				return false;
		} else if (!vejnavn.equals(other.vejnavn))
			return false;
		return true;
	}

}
