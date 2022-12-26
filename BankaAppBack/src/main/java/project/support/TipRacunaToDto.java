package project.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import project.model.TipRacuna;
import project.web.dto.TipRacunaDTO;

@Component
public class TipRacunaToDto implements Converter<TipRacuna, TipRacunaDTO>{

	@Override
	public TipRacunaDTO convert(TipRacuna tipRacuna) {
		
		TipRacunaDTO dto = new TipRacunaDTO();
		
		dto.setId(tipRacuna.getId());
		dto.setNaziv(tipRacuna.getNaziv());
		dto.setProvizija(tipRacuna.getProvizija());
		
		
		return dto;
	}
	
	public List<TipRacunaDTO> convert(List<TipRacuna>tipoviRacuna){
		
		List<TipRacunaDTO> tipoviRacunaDto = new ArrayList<>();
		
		for(TipRacuna tipRacuna: tipoviRacuna) {
			tipoviRacunaDto.add(convert(tipRacuna));
		}
		
		
		return tipoviRacunaDto;
		
	}
	
	
}
