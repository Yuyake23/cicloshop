package edu.ifgoiano.trabalho.dto;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import edu.ifgoiano.trabalho.controller.FornecedorController;
import edu.ifgoiano.trabalho.controller.PrecoProdutoController;
import edu.ifgoiano.trabalho.model.entity.Fornecedor;
import edu.ifgoiano.trabalho.model.entity.PrecoProduto;
import edu.ifgoiano.trabalho.model.entity.Produto;

public class PrecoProdutoDto extends RepresentationModel<PrecoProdutoDto> {

  public final Long id;
  public final Long produtoId;
  public final Long fornecedorId;
  public final BigDecimal preco;
  public final Float porcentagemLucro;

  public PrecoProdutoDto(
      Long id, Long produtoId, Long fornecedorId, BigDecimal preco, Float porcentagemLucro) {
    this.id = id;
    this.produtoId = produtoId;
    this.fornecedorId = fornecedorId;
    this.preco = preco;
    this.porcentagemLucro = porcentagemLucro;
    addLinks();
  }

  public PrecoProdutoDto(PrecoProduto a) {
    this.id = a.getId();
    this.produtoId = a.getProduto().getId();
    this.fornecedorId = a.getFornecedor().getId();
    this.preco = a.getPreco();
    this.porcentagemLucro = a.getPorcentagemLucro();
    addLinks();
  }
  
  private void addLinks() {
    Link selfLink = linkTo(
        methodOn(PrecoProdutoController.class).buscarPorId(id))
        .withSelfRel()
        .withType("GET");
    Link fornecedorLink = linkTo(
        methodOn(FornecedorController.class).buscarPorId(fornecedorId))
        .withRel("fornecedor")
        .withType("GET");
    
    super.add(selfLink, fornecedorLink);
  }

  public PrecoProduto toEntity() {
    return new PrecoProduto(
        id, new Produto(produtoId), new Fornecedor(fornecedorId), preco, porcentagemLucro);
  }

  public static PrecoProdutoDto ofPrecoProduto(PrecoProduto precoProduto) {
    return new PrecoProdutoDto(precoProduto);
  }

  public static CollectionModel<PrecoProdutoDto> ofPrecosProdutos(List<PrecoProduto> precosProdutos) {
    Link selfLink = linkTo(
        methodOn(PrecoProdutoController.class).buscarTodos())
        .withSelfRel()
        .withType("GET");
    return CollectionModel.of(
        precosProdutos.stream()
          .map(PrecoProdutoDto::ofPrecoProduto) 
          .toList(), selfLink);
  }
}
