package project.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import project.model.TipRacuna;

public class RacunDTO {
	
	private Long id;
	private String imePrezime;
	@Size(min=13, max=13)
	private String jmbg;
	@NotBlank(message = "Broj racuna nije zadat.")
	private String brojRacuna;
	private double stanjeRacuna;
	private String nazivTipaRacuna;
	private Long idTipaRacuna;
	private String nazivBanke;
	private Long idBanke;
	
	public RacunDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImePrezime() {
		return imePrezime;
	}

	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public double getStanjeRacuna() {
		return stanjeRacuna;
	}

	public void setStanjeRacuna(double stanjeRacuna) {
		this.stanjeRacuna = stanjeRacuna;
	}


	public String getNazivTipaRacuna() {
		return nazivTipaRacuna;
	}

	public void setNazivTipaRacuna(String nazivTipaRacuna) {
		this.nazivTipaRacuna = nazivTipaRacuna;
	}

	public Long getIdTipaRacuna() {
		return idTipaRacuna;
	}

	public void setIdTipaRacuna(Long idTipaRacuna) {
		this.idTipaRacuna = idTipaRacuna;
	}

	public String getNazivBanke() {
		return nazivBanke;
	}

	public void setNazivBanke(String nazivBanke) {
		this.nazivBanke = nazivBanke;
	}

	public Long getIdBanke() {
		return idBanke;
	}

	public void setIdBanke(Long idBanke) {
		this.idBanke = idBanke;
	}
	
	

}
