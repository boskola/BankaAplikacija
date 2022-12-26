package project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import project.model.Racun;
import project.repository.RacunRepository;
import project.service.RacunService;

@Service
public class JpaRacunService implements RacunService{

	@Autowired
	private RacunRepository racunRepository;
	
	@Override
	public List<Racun> findAll() {
		// TODO Auto-generated method stub
		return racunRepository.findAll();
	}

	@Override
	public Page<Racun> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return racunRepository.findAll(pageable);
	}

	@Override
	public Racun findOne(Long id) {
		// TODO Auto-generated method stub
		return racunRepository.findOneById(id);
	}

	@Override
	public Racun save(Racun racun) {
		// TODO Auto-generated method stub
		return racunRepository.save(racun);
	}

	@Override
	public Racun update(Racun racun) {
		// TODO Auto-generated method stub
		return racunRepository.save(racun);
	}

	@Override
	public Racun delete(Long id) {
		Racun racun = findOne(id);
		
		if(racun != null) {
			racun.getBanka().getRacuni().remove(racun);
			racun.getTipRacuna().getRacuni().remove(racun);
			racunRepository.delete(racun);
			return racun;
		}
		
		
		return null;
	}

	@Override
	public Page<Racun> find(String jmbg, Long bankaId, int page) {
		
		if(jmbg == null) {
			jmbg = "";
		}
		
		if(bankaId == null) {
			return racunRepository.findByJmbgIgnoreCaseContains(jmbg, PageRequest.of(page, 5));
		}
		
		return racunRepository.findByJmbgIgnoreCaseContainsAndBankaId(jmbg, bankaId, PageRequest.of(page, 5));
	}

	@Override
	public Racun findByBrojRacuna(String brojRacuna) {
		// TODO Auto-generated method stub
		return racunRepository.findByBrojRacuna(brojRacuna);
	}

}
