package edu.ifgoiano.trabalho.model.repository;

import edu.ifgoiano.trabalho.model.entity.DadosBancarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosBancariosRepository extends JpaRepository<DadosBancarios, Long> {}
