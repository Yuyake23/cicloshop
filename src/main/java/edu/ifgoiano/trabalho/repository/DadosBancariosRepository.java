package edu.ifgoiano.trabalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifgoiano.trabalho.domain.DadosBancarios;

@Repository
public interface DadosBancariosRepository extends JpaRepository<DadosBancarios, Long> {

}
