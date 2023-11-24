package edu.ifgoiano.trabalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifgoiano.trabalho.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
