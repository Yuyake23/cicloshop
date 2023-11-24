package edu.ifgoiano.trabalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifgoiano.trabalho.domain.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}
