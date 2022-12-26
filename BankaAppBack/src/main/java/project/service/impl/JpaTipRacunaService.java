package project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.model.TipRacuna;
import project.repository.TipRacunaRepository;
import project.service.TipRacunaService;

@Service
public class JpaTipRacunaService implements TipRacunaService{
	
	@Autowired
	private TipRacunaRepository tipRacunaRepository;
	
	@Override
	public List<TipRacuna> findByBankaId(Long id) {
		// TODO Auto-generated method stub
		return tipRacunaRepository.findByBankaId(id);
	}

	@Override
	public TipRacuna findOne(Long id) {
		// TODO Auto-generated method stub
		return tipRacunaRepository.findOneById(id);
	}

	@Override
	public List<TipRacuna> getAll() {
		// TODO Auto-generated method stub
		return tipRacunaRepository.findAll();
	}

}
