package project.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import project.model.Racun;
import project.service.BankaService;
import project.service.RacunService;
import project.service.TipRacunaService;
import project.web.dto.RacunDTO;

@Component
public class RacunDtoToRacun implements Converter<RacunDTO, Racun>{
	
	@Autowired
	private RacunService racunService;
	
	@Autowired
	private BankaService bankaService;
	
	@Autowired
	private TipRacunaService tipRacunaService;
	
	@Override
	public Racun convert(RacunDTO dto) {
		
		Racun racun;
		
		if(dto.getId() == null) {
			racun = new Racun();
		}else {
			racun = racunService.findOne(dto.getId());
		}
		
		if(racun!=null) {
			racun.setImePrezime(dto.getImePrezime());
			racun.setBrojRacuna(dto.getBrojRacuna());
			racun.setJmbg(dto.getJmbg());
			racun.setStanjeRacuna(dto.getStanjeRacuna());
			racun.setTipRacuna(tipRacunaService.findOne(dto.getIdTipaRacuna()));
			racun.setBanka(bankaService.findOne(dto.getIdBanke()));
		}
		
		return racun;
	}

}
