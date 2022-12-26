package project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.model.Racun;

@Repository
public interface RacunRepository extends JpaRepository<Racun, Long>{

	Racun findOneById(Long id);
	
	Page<Racun> findByJmbgIgnoreCaseContainsAndBankaId(String jmbg, Long bankaId, Pageable pageable);
	Page<Racun> findByJmbgIgnoreCaseContains(String jmbg, Pageable pageable);
	Racun findByBrojRacuna(String brojRacuna);
}
