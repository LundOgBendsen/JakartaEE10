package dk.universitet.adm.indskrivning.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Studienaevn {
	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 30)
	private String navn;
	@Column(length = 10, unique = true)
	private String kode;
	@OneToMany(mappedBy="studienaevn")
	List<Fag> fag = new ArrayList<Fag>();
	
	
	public Long getId() {
		return id;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public List<Fag> getFag() {
		return fag;
	}

	public void setFag(List<Fag> fag) {
		this.fag = fag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kode == null) ? 0 : kode.hashCode());
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
		Studienaevn other = (Studienaevn) obj;
		if (kode == null) {
			if (other.kode != null)
				return false;
		} else if (!kode.equals(other.kode))
			return false;
		return true;
	}
}
