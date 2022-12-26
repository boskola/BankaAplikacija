package project.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import project.model.Banka;
import project.web.dto.BankaDTO;

@Component
public class BankaToBankaDto implements Converter<Banka, BankaDTO>{

	@Override
	public BankaDTO convert(Banka banka) {
		
		BankaDTO dto = new BankaDTO();
		dto.setId(banka.getId());
		dto.setNaziv(banka.getNaziv());
		dto.setSredstvaBanke(banka.getSredstvaBanke());
		
		
		return dto;
	}
	
	public List<BankaDTO> convert(List<Banka>banke){
		
		List<BankaDTO> bankeDto = new ArrayList<>();
		
		for(Banka banka: banke) {
			bankeDto.add(convert(banka));
		}
		
		
		return bankeDto;
		
	}

}
