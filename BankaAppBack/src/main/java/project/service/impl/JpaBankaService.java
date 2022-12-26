package project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.model.Banka;
import project.model.TipRacuna;
import project.repository.BankaRepository;
import project.repository.TipRacunaRepository;
import project.service.BankaService;

@Service
public class JpaBankaService implements BankaService{
	
	@Autowired
	private BankaRepository bankaRepository;

	
	@Override
	public List<Banka> findAll() {
		// TODO Auto-generated method stub
		return bankaRepository.findAll();
	}


	@Override
	public Banka findOne(Long id) {
		// TODO Auto-generated method stub
		return bankaRepository.findOneById(id);
	}

	@Override
	public Banka save(Banka banka) {
		// TODO Auto-generated method stub
		return bankaRepository.save(banka);
	}

}
