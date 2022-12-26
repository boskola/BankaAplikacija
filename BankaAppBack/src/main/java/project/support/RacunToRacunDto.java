package project.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import project.model.Racun;
import project.web.dto.RacunDTO;

@Component
public class RacunToRacunDto implements Converter<Racun, RacunDTO>{

	@Override
	public RacunDTO convert(Racun racun) {
		RacunDTO dto = new RacunDTO();
		
		dto.setId(racun.getId());
		dto.setBrojRacuna(racun.getBrojRacuna());
		dto.setImePrezime(racun.getImePrezime());
		dto.setJmbg(racun.getJmbg());
		dto.setStanjeRacuna(racun.getStanjeRacuna());
		dto.setNazivTipaRacuna(racun.getTipRacuna().getNaziv());
		dto.setIdTipaRacuna(racun.getTipRacuna().getId());
		dto.setIdBanke(racun.getBanka().getId());
		dto.setNazivBanke(racun.getBanka().getNaziv());
		
		
		return dto;
	}
	
	public List<RacunDTO> convert(List<Racun>racuni){
		List<RacunDTO> racuniDto = new ArrayList<>();
		
		for(Racun racun: racuni) {
			racuniDto.add(convert(racun));
		}
		return racuniDto;
	}

}
