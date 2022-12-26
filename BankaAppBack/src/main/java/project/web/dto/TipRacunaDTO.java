package project.web.dto;

import java.util.Objects;

public class TipRacunaDTO {
	
	private Long id;
	private String naziv;
	private double provizija;
	
	public TipRacunaDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getProvizija() {
		return provizija;
	}

	public void setProvizija(double provizija) {
		this.provizija = provizija;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipRacunaDTO other = (TipRacunaDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
