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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Banka {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String naziv;
	
	@Column
	private double sredstvaBanke;
	
	@OneToMany(mappedBy = "banka", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<TipRacuna> tipoviRacuna = new ArrayList<>();
	
	@OneToMany(mappedBy = "banka", cascade = CascadeType.ALL) //ili ubacim fetch LAZY, ne radi kad su oba ista!
	private List<Racun> racuni = new ArrayList<>();
	
	public Banka() {}
	
	public List<Racun> getRacuni() {
		return racuni;
	} 

	public void dodajRacun(Racun racun) {
		this.racuni.add(racun);
		if(!equals(racun.getBanka())) {
			racun.setBanka(this);
		}
	}

	public List<TipRacuna> getTipoviRacuna() {
		return tipoviRacuna;
	}

	public void dodajTipRacuna(TipRacuna tipRacuna) {
		this.tipoviRacuna.add(tipRacuna);
		if(!equals(tipRacuna.getBanka())) {
			tipRacuna.setBanka(this);
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

	public double getSredstvaBanke() {
		return sredstvaBanke;
	}

	public void setSredstvaBanke(double sredstvaBanke) {
		this.sredstvaBanke = sredstvaBanke;
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
		Banka other = (Banka) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
