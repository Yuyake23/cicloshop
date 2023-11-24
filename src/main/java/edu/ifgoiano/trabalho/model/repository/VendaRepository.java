package edu.ifgoiano.trabalho.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifgoiano.trabalho.model.entity.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}
