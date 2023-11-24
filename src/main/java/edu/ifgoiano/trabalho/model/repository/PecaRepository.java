package edu.ifgoiano.trabalho.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifgoiano.trabalho.model.entity.Peca;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Long>{

}
