package edu.ifgoiano.trabalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifgoiano.trabalho.domain.Bicicleta;

@Repository
public interface BicicletaRepository extends JpaRepository<Bicicleta, Long> {

}
