package edu.ifgoiano.trabalho.dto;

import edu.ifgoiano.trabalho.model.entity.Fornecedor;
import edu.ifgoiano.trabalho.model.entity.PrecoProduto;
import edu.ifgoiano.trabalho.model.entity.Produto;
import java.math.BigDecimal;
import java.util.List;

public final class PrecoProdutoDto {

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
  }

  public PrecoProdutoDto(PrecoProduto a) {
    this.id = a.getId();
    this.produtoId = a.getProduto().getId();
    this.fornecedorId = a.getFornecedor().getId();
    this.preco = a.getPreco();
    this.porcentagemLucro = a.getPorcentagemLucro();
  }

  public PrecoProduto toEntity() {
    return new PrecoProduto(
        id, new Produto(produtoId), new Fornecedor(fornecedorId), preco, porcentagemLucro);
  }

  public static PrecoProdutoDto ofPrecoProduto(PrecoProduto precoProduto) {
    return new PrecoProdutoDto(precoProduto);
  }

  public static List<PrecoProdutoDto> ofPrecosProdutos(List<PrecoProduto> precosProdutos) {
    return precosProdutos.stream().map(PrecoProdutoDto::ofPrecoProduto).toList();
  }
}
