package project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
public class TipRacuna {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String naziv;
	
	@Column
	private double provizija;
	
	@ManyToOne
	private Banka banka;
	
	@OneToMany(mappedBy = "tipRacuna", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Racun> racuni = new ArrayList<>();

	public TipRacuna() {}
	
	public List<Racun> getRacuni() {
		return racuni;
	}

	public void dodajRacun(Racun racun) {
		this.racuni.add(racun);
		if(!equals(racun.getTipRacuna())) {
			racun.setTipRacuna(this);
		}
	}

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

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
		if(banka != null && !banka.getTipoviRacuna().contains(this)) {
			banka.getTipoviRacuna().add(this);
		}
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
		TipRacuna other = (TipRacuna) obj;
		return Objects.equals(id, other.id);
	}
}
