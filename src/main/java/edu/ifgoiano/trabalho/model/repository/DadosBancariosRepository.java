package edu.ifgoiano.trabalho.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifgoiano.trabalho.model.entity.DadosBancarios;

@Repository
public interface DadosBancariosRepository extends JpaRepository<DadosBancarios, Long> {

	List<DadosBancarios> findByContratoFuncionarioId(Long contratoFuncionarioId);

}
