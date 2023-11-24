package edu.ifgoiano.trabalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifgoiano.trabalho.domain.Peca;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Long>{

}
