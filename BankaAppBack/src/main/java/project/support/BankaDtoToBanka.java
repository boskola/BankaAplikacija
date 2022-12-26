package project.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import project.model.Banka;
import project.service.BankaService;
import project.web.dto.BankaDTO;

@Component
public class BankaDtoToBanka implements Converter<BankaDTO, Banka>{
	
	@Autowired
	private BankaService bankaService;
	
	@Override
	public Banka convert(BankaDTO dto) {
		// TODO Auto-generated method stub
		
		Banka banka;
		
		if(dto.getId() == null) {
			banka = new Banka();
		}else {
			banka = bankaService.findOne(dto.getId());
		}
		
		if(banka != null) {
			banka.setNaziv(dto.getNaziv());
			banka.setSredstvaBanke(dto.getSredstvaBanke());
		}
		
		return banka;
	}

}
