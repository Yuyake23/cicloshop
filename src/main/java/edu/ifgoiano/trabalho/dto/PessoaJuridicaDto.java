package edu.ifgoiano.trabalho.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.time.LocalDate;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import com.fasterxml.jackson.annotation.JsonCreator;
import edu.ifgoiano.trabalho.controller.PessoaJuridicaController;
import edu.ifgoiano.trabalho.model.entity.Fornecedor;
import edu.ifgoiano.trabalho.model.entity.PessoaJuridica;
import edu.ifgoiano.trabalho.model.enums.TipoPessoa;

public class PessoaJuridicaDto extends PessoaDto {

  public final String cnpj;
  public final String razaoSocial;
  public final String nomeFantasia;
  public final LocalDate dataCriacao;

  @JsonCreator
  public PessoaJuridicaDto(
      Long id,
      String telefone,
      String endereco,
      String observacoes,
      String cnpj,
      String razaoSocial,
      String nomeFantasia,
      LocalDate dataCriacao) {
    super(id, TipoPessoa.JURIDICA, telefone, endereco, observacoes);
    this.cnpj = cnpj;
    this.razaoSocial = razaoSocial;
    this.nomeFantasia = nomeFantasia;
    this.dataCriacao = dataCriacao;
  }

  public PessoaJuridicaDto(PessoaJuridica pessoaJuridica) {
    super(pessoaJuridica);
    this.cnpj = pessoaJuridica.getDocumento();
    this.razaoSocial = pessoaJuridica.getNome();
    this.nomeFantasia = pessoaJuridica.getNomeFantasia();
    this.dataCriacao = pessoaJuridica.getDataGenese();
  }
  
  protected PessoaJuridicaDto(
      Long id,
      TipoPessoa tipoPessoa,
      String telefone,
      String endereco,
      String observacoes,
      String cnpj,
      String razaoSocial,
      String nomeFantasia,
      LocalDate dataCriacao) {
    super(id, tipoPessoa, telefone, endereco, observacoes);
    this.cnpj = cnpj;
    this.razaoSocial = razaoSocial;
    this.nomeFantasia = nomeFantasia;
    this.dataCriacao = dataCriacao;
  }

  @Override
  public PessoaJuridica toEntity() {
    return new PessoaJuridica(
        id, cnpj, razaoSocial, nomeFantasia, telefone, endereco, dataCriacao, observacoes);
  }

  public static PessoaJuridicaDto ofPessoaJuridica(PessoaJuridica pessoaJuridica) {
    if (pessoaJuridica instanceof Fornecedor fornecedor) {
      return FornecedorDto.ofFornecedor(fornecedor);
    } else {
      return new PessoaJuridicaDto(pessoaJuridica);
    }
  }

  public static CollectionModel<? extends PessoaJuridicaDto> ofPessoasJuridicas(
      List<PessoaJuridica> pessoasJuridicas) {
    Link selfLink = linkTo(
        methodOn(PessoaJuridicaController.class).buscarTodos())
        .withSelfRel()
        .withType("GET");
    return CollectionModel.of(
        pessoasJuridicas.stream()
          .map(PessoaJuridicaDto::ofPessoaJuridica)
          .toList(), selfLink);
  }
}
