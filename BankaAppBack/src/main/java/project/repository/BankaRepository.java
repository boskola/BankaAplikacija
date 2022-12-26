package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.model.Banka;


@Repository
public interface BankaRepository extends JpaRepository<Banka, Long>{
	
	Banka findOneById(Long id);

}
