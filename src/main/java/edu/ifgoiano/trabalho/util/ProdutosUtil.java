package edu.ifgoiano.trabalho.util;

import java.util.List;
import edu.ifgoiano.trabalho.exception.PecasInsuficientesException;
import edu.ifgoiano.trabalho.model.entity.Bicicleta;
import edu.ifgoiano.trabalho.model.entity.Peca;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.entity.Produto;

public class ProdutosUtil {

  public static void atualizaEstoqueEDono(Pessoa comprador, List<Produto> produtos) {
    produtos.forEach(prod -> {
      if(prod instanceof Bicicleta bike) {
        bike.setDono(comprador);
        bike.setValor(null);
      } else if (prod instanceof Peca peca) {
        int qtd = peca.getQuantidade();
        
        if (qtd <= 0) {
          throw new PecasInsuficientesException(
              "Não há %s o suficiente. Id=%d".formatted(peca.getNome(), peca.getId()));
        }
        
        peca.setQuantidade(qtd - 1);
      }
    });
  }
  
}
