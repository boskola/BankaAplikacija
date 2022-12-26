package project.service;

import java.util.List;

import project.model.Banka;
import project.model.TipRacuna;

public interface BankaService {
	
	Banka findOne(Long id);
	Banka save(Banka banka);
	
	List<Banka> findAll();
	//Page<Banka> findAll(Pageable pageable);

}
