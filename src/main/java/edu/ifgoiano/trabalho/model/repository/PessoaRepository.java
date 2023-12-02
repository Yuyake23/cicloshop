package edu.ifgoiano.trabalho.model.repository;

import edu.ifgoiano.trabalho.model.entity.Pessoa;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
  public Optional<Pessoa> findByDocumento(String documento);
}
