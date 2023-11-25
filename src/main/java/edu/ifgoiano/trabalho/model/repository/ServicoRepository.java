package edu.ifgoiano.trabalho.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifgoiano.trabalho.model.entity.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

}