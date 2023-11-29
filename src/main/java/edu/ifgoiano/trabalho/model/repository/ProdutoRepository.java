package edu.ifgoiano.trabalho.model.repository;

import edu.ifgoiano.trabalho.model.entity.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

  List<Produto> findByDonoId(Long id);
}
