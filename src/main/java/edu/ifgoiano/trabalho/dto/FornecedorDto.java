package edu.ifgoiano.trabalho.dto;

import edu.ifgoiano.trabalho.model.entity.Fornecedor;
import edu.ifgoiano.trabalho.model.entity.PrecoProduto;
import edu.ifgoiano.trabalho.model.enums.TipoPessoa;
import java.time.LocalDate;
import java.util.List;

public final class FornecedorDto extends PessoaJuridicaDto {

  public final List<PrecoProdutoDto> produtosFornecidos;

  public FornecedorDto(
      Long id,
      TipoPessoa tipoPessoa,
      String telefone,
      String endereco,
      String observacoes,
      String cnpj,
      String razaoSocial,
      String nomeFantasia,
      LocalDate dataCriacao,
      List<PrecoProduto> produtosFornecidos) {
    super(
        id,
        tipoPessoa,
        telefone,
        endereco,
        observacoes,
        cnpj,
        razaoSocial,
        nomeFantasia,
        dataCriacao);
    this.produtosFornecidos = PrecoProdutoDto.ofPrecosProdutos(produtosFornecidos);
    ;
  }

  public FornecedorDto(Fornecedor fornecedor) {
    super(fornecedor);
    this.produtosFornecidos = PrecoProdutoDto.ofPrecosProdutos(fornecedor.getProdutosFornecidos());
  }

  public Fornecedor toEntity() {
    Fornecedor fornecedor =
        new Fornecedor(
            id, cnpj, razaoSocial, nomeFantasia, telefone, endereco, dataCriacao, observacoes);
    return fornecedor;
  }

  public static FornecedorDto ofFornecedor(Fornecedor fornecedor) {
    return new FornecedorDto(fornecedor);
  }

  public static List<FornecedorDto> ofFornecedores(List<Fornecedor> fornecedores) {
    return fornecedores.stream().map(FornecedorDto::ofFornecedor).toList();
  }
}
