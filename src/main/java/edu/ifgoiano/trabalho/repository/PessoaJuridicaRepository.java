package edu.ifgoiano.trabalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifgoiano.trabalho.domain.PessoaJuridica;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {

}
