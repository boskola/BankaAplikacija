package project.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.model.Banka;
import project.model.TipRacuna;
import project.service.BankaService;
import project.service.TipRacunaService;
import project.support.BankaToBankaDto;
import project.support.TipRacunaToDto;
import project.web.dto.BankaDTO;
import project.web.dto.TipRacunaDTO;

@RestController
@RequestMapping(value = "/api/banke", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class BankaController {
	
	@Autowired
	private BankaService bankaService;
	
	@Autowired
	private BankaToBankaDto toBankaDto;
	
	@Autowired
	private TipRacunaService tipRacunaService;
	
	@Autowired
	private TipRacunaToDto toDto;
	
	@GetMapping
	public ResponseEntity<List<BankaDTO>> getAll(){
		List<Banka> banke = bankaService.findAll();
		
		return new ResponseEntity<>(toBankaDto.convert(banke), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/tipovi-racuna")
	public ResponseEntity<List<TipRacunaDTO>>getAllTypes(@PathVariable Long id){
		List<TipRacuna>tipovi = tipRacunaService.findByBankaId(id);
		
		if(tipovi != null) {
			return new ResponseEntity<>(toDto.convert(tipovi), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
