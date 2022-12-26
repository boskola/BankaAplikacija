package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.model.TipRacuna;

@Repository
public interface TipRacunaRepository extends JpaRepository<TipRacuna, Long>{
	
	List<TipRacuna> findByBankaId(Long id);
	TipRacuna findOneById(Long id);
	
}
