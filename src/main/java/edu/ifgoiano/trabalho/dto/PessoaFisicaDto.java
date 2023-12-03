package edu.ifgoiano.trabalho.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.time.LocalDate;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import edu.ifgoiano.trabalho.controller.PessoaFisicaController;
import edu.ifgoiano.trabalho.model.entity.PessoaFisica;
import edu.ifgoiano.trabalho.model.enums.TipoPessoa;

public class PessoaFisicaDto extends PessoaDto {

  public final String nome;
  public final String cpf;
  public final LocalDate dataNascimento;

  public PessoaFisicaDto(
      Long id,
      TipoPessoa tipoPessoa,
      String telefone,
      String endereco,
      String observacoes,
      String nome,
      String cpf,
      LocalDate dataNascimento) {
    super(id, tipoPessoa, telefone, endereco, observacoes);
    this.nome = nome;
    this.cpf = cpf;
    this.dataNascimento = dataNascimento;
  }

  public PessoaFisicaDto(PessoaFisica pessoaFisica) {
    super(pessoaFisica);
    this.nome = pessoaFisica.getNome();
    this.cpf = pessoaFisica.getDocumento();
    this.dataNascimento = pessoaFisica.getDataGenese();
  }

  @Override
  public PessoaFisica toEntity() {
    return new PessoaFisica(id, nome, cpf, telefone, endereco, dataNascimento, observacoes);
  }

  public static PessoaFisicaDto ofPessoaFisica(PessoaFisica pessoaFisica) {
    return new PessoaFisicaDto(pessoaFisica);
  }

  public static CollectionModel<PessoaFisicaDto> ofPessoasFisicas(List<PessoaFisica> pessoasFisicas) {
    Link selfLink = linkTo(
        methodOn(PessoaFisicaController.class).buscarTodos())
        .withSelfRel()
        .withType("GET");
    return CollectionModel.of(
        pessoasFisicas.stream()
          .map(PessoaFisicaDto::ofPessoaFisica)
          .toList(), selfLink);
  }
}
