package project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.model.Racun;

public interface RacunService {
	
	
	List<Racun> findAll();
	Page<Racun> findAll(Pageable pageable);
	
	Racun findOne(Long id);
	
	Racun save(Racun racun);
	
	Racun update(Racun racun);
	
	Racun delete(Long id);
	
	Page<Racun> find(String jmbg, Long bankaId, int page);
	
	Racun findByBrojRacuna(String brojRacuna);

}
