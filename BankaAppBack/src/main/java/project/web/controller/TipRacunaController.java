package project.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.model.TipRacuna;
import project.service.TipRacunaService;
import project.support.TipRacunaToDto;
import project.web.dto.TipRacunaDTO;

@RestController
@RequestMapping(value = "/api/tip", produces = MediaType.APPLICATION_JSON_VALUE)
public class TipRacunaController {
	
	@Autowired
	private TipRacunaService tipRacunaService;
	
	@Autowired
	private TipRacunaToDto toDto;
	
	@GetMapping
	public ResponseEntity<List<TipRacunaDTO>> getAll(){
		
		List<TipRacuna> tipovi = tipRacunaService.getAll();
		
		return new ResponseEntity<>(toDto.convert(tipovi), HttpStatus.OK);
	}
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
