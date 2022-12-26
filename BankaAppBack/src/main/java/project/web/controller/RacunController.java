package project.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.model.Racun;
import project.service.RacunService;
import project.support.RacunDtoToRacun;
import project.support.RacunToRacunDto;
import project.web.dto.RacunDTO;

@RestController
@RequestMapping(value = "/api/racuni", produces = MediaType.APPLICATION_JSON_VALUE)
public class RacunController {
	
	@Autowired
	private RacunService racunService;
	
	@Autowired
	private RacunDtoToRacun dtoToRacun;
	
	@Autowired
	private RacunToRacunDto toRacunDto;
	
	@GetMapping
	public ResponseEntity<List<RacunDTO>> getAll(
			 @RequestParam(required=false) String jmbg,
			 @RequestParam(required = false) Long bankaId,
			 @RequestParam(value = "pageNo", defaultValue = "0") int pageNo
			){
		
		Page<Racun> page = racunService.find(jmbg, bankaId, pageNo);
		
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));
        
	    System.out.println("HEDER: "+headers);
	    System.out.println(Integer.toString(page.getTotalPages()));
		
		return new ResponseEntity<>(toRacunDto.convert(page.getContent()), headers, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<RacunDTO> getOne(@PathVariable Long id){
		Racun racun = racunService.findOne(id);
		
		if(racun != null) {
			return new ResponseEntity<>(toRacunDto.convert(racun), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RacunDTO> create(@Valid @RequestBody RacunDTO racunDTO){
		Racun racun = dtoToRacun.convert(racunDTO);
		
		Racun sacuvanRacun = racunService.save(racun);
		
		
		return new ResponseEntity<>(toRacunDto.convert(sacuvanRacun), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RacunDTO> update(@PathVariable Long id, @Valid @RequestBody RacunDTO racunDTO){
		
		if(!id.equals(racunDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Racun racun = dtoToRacun.convert(racunDTO);
		
		Racun sacuvanRacun = racunService.save(racun);
		
		return new ResponseEntity<>(toRacunDto.convert(sacuvanRacun), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		
		Racun racun = racunService.findOne(id);
		
		
		if(racun.getStanjeRacuna()==0.0) {
			racun = racunService.delete(id);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
		
		if(racun != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@PutMapping(value = "/prenos/{brojRacunaPlatioca}/{brojRacunaPrimaoca}/{iznos}")
	public ResponseEntity<Void> prenos(@PathVariable String brojRacunaPlatioca,
			@PathVariable String brojRacunaPrimaoca,
			@PathVariable Double iznos){
		
		System.out.println("kengur na konju");
		System.out.println(brojRacunaPlatioca);
		System.out.println(brojRacunaPrimaoca);
		System.out.println(iznos);
		
		Racun platioc = racunService.findByBrojRacuna(brojRacunaPlatioca);
		Racun primaoc = racunService.findByBrojRacuna(brojRacunaPrimaoca);
		
		if(platioc==null || primaoc==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(platioc.getStanjeRacuna()>=iznos) {
			primaoc.setStanjeRacuna(primaoc.getStanjeRacuna()+iznos);
			if(platioc.getTipRacuna().getProvizija()*iznos>1000) {
				platioc.setStanjeRacuna(platioc.getStanjeRacuna()-iznos);
				platioc.getBanka().setSredstvaBanke(platioc.getBanka().getSredstvaBanke()+1000);
			}else {
				platioc.setStanjeRacuna(platioc.getStanjeRacuna()-iznos-platioc.getTipRacuna().getProvizija()*iznos);
				platioc.getBanka().setSredstvaBanke(platioc.getBanka().getSredstvaBanke()+platioc.getTipRacuna().getProvizija()*iznos);
			}
			
			racunService.save(platioc);
			racunService.save(primaoc);
			
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
