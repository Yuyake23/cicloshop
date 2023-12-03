package edu.ifgoiano.trabalho.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import edu.ifgoiano.trabalho.controller.PecaController;
import edu.ifgoiano.trabalho.model.entity.Peca;
import edu.ifgoiano.trabalho.model.entity.Pessoa;

public class PecaDto extends ProdutoDto {

  public final String nome;
  public final Integer quantidade;

  public PecaDto(
      Long id, Long donoId, String marca, BigDecimal valor, String nome, Integer quantidade) {
    super(id, donoId, marca, valor);
    this.nome = nome;
    this.quantidade = quantidade;
  }

  public PecaDto(Peca peca) {
    super(peca);
    this.nome = peca.getNome();
    this.quantidade = peca.getQuantidade();
  }

  @Override
  public Peca toEntity() {
    return new Peca(id, new Pessoa(donoId), marca, valor, nome, quantidade);
  }

  public static PecaDto ofPeca(Peca peca) {
    return new PecaDto(peca);
  }

  public static CollectionModel<PecaDto> ofPecas(List<Peca> pecas) {
    Link selfLink = linkTo(
        methodOn(PecaController.class).buscarTodos())
        .withSelfRel()
        .withType("GET");
    return CollectionModel.of(
        pecas.stream()
          .map(PecaDto::ofPeca)
          .toList(), selfLink);
  }
}
