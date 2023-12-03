package edu.ifgoiano.trabalho.dto;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.time.LocalDate;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import edu.ifgoiano.trabalho.controller.FornecedorController;
import edu.ifgoiano.trabalho.model.entity.Fornecedor;
import edu.ifgoiano.trabalho.model.entity.PrecoProduto;
import edu.ifgoiano.trabalho.model.enums.TipoPessoa;

public class FornecedorDto extends PessoaJuridicaDto {

  public final CollectionModel<PrecoProdutoDto> produtosFornecidos;

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

  public static CollectionModel<FornecedorDto> ofFornecedores(List<Fornecedor> fornecedores) {
    Link selfLink = linkTo(
        methodOn(FornecedorController.class).buscarTodos())
        .withSelfRel()
        .withType("GET");
    return CollectionModel.of(
        fornecedores.stream()
          .map(FornecedorDto::ofFornecedor)
          .toList(), selfLink);
  }
}
