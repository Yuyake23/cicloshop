package edu.ifgoiano.trabalho.model.repository;

import edu.ifgoiano.trabalho.model.entity.PrecoProduto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecoProdutoRepository extends JpaRepository<PrecoProduto, Long> {

  List<PrecoProduto> findByProdutoId(Long produtoId);
}
