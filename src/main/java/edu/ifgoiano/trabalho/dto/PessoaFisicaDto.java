package edu.ifgoiano.trabalho.dto;

import edu.ifgoiano.trabalho.model.entity.PessoaFisica;
import edu.ifgoiano.trabalho.model.enums.TipoPessoa;
import java.time.LocalDate;
import java.util.List;

public final class PessoaFisicaDto extends PessoaDto {

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

  public static List<PessoaFisicaDto> ofPessoasFisicas(List<PessoaFisica> pessoasFisicas) {
    return pessoasFisicas.stream().map(PessoaFisicaDto::ofPessoaFisica).toList();
  }
}
