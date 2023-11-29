package edu.ifgoiano.trabalho.model.repository;

import edu.ifgoiano.trabalho.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {}
