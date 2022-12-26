package project.service;

import java.util.List;

import project.model.TipRacuna;

public interface TipRacunaService {
	
	List<TipRacuna> findByBankaId(Long id);
	TipRacuna findOne(Long id);
	List<TipRacuna> getAll();

}
