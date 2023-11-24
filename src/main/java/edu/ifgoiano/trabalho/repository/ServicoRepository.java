package edu.ifgoiano.trabalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifgoiano.trabalho.domain.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
