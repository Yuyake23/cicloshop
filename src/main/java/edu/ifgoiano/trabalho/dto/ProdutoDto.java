package edu.ifgoiano.trabalho.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import edu.ifgoiano.trabalho.controller.PessoaController;
import edu.ifgoiano.trabalho.controller.ProdutoController;
import edu.ifgoiano.trabalho.model.entity.Bicicleta;
import edu.ifgoiano.trabalho.model.entity.Peca;
import edu.ifgoiano.trabalho.model.entity.Produto;

public abstract class ProdutoDto extends RepresentationModel<ProdutoDto> {

  public final Long id;
  public final Long donoId;
  public final String marca;
  public final BigDecimal valor;

  public ProdutoDto(Long id, Long donoId, String marca, BigDecimal valor) {
    this.id = id;
    this.donoId = donoId;
    this.marca = marca;
    this.valor = valor;
    addLinks();
  }

  public ProdutoDto(Produto produto) {
    this.id = produto.getId();
    this.donoId = produto.getDono().getId();
    this.marca = produto.getMarca();
    this.valor = produto.getValor();
    addLinks();
  }
  
  protected void addLinks() {
    Link selfLink = linkTo(
        methodOn(ProdutoController.class).buscarPorId(id))
        .withSelfRel()
        .withType("GET");
    
    Link donoLink = linkTo(
        methodOn(PessoaController.class).buscarPorId(donoId))
        .withRel("dono")
        .withType("GET");
    
    super.add(selfLink, donoLink);
  }

  public abstract Produto toEntity();

  public static ProdutoDto ofProduto(Produto produto) {
    if (produto instanceof Peca peca) return PecaDto.ofPeca(peca);
    else if (produto instanceof Bicicleta bicicleta) return BicicletaDto.ofBicicleta(bicicleta);
    else throw new IllegalStateException("Produto pode ser apenas Peca ou Bicicleta");
  }

  public static CollectionModel<? extends ProdutoDto> ofProdutos(List<Produto> produtos) {
    Link selfLink = linkTo(
        methodOn(ProdutoController.class).buscarTodos())
        .withSelfRel()
        .withType("GET");
    return CollectionModel.of(
        produtos.stream()
          .map(ProdutoDto::ofProduto)
          .toList(), selfLink);
  }
}
