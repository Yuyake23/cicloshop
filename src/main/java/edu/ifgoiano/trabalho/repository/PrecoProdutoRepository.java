package edu.ifgoiano.trabalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifgoiano.trabalho.domain.PrecoProduto;

@Repository
public interface PrecoProdutoRepository extends JpaRepository<PrecoProduto, Long> {

}
