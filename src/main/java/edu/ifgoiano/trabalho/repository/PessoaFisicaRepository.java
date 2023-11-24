package edu.ifgoiano.trabalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifgoiano.trabalho.domain.PessoaFisica;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

}
