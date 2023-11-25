package edu.ifgoiano.trabalho.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifgoiano.trabalho.model.entity.Peca;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Long>{

	List<Peca> findByDonoId(Long idDono);

}
