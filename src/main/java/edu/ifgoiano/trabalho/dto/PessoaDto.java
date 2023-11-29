package edu.ifgoiano.trabalho.dto;

import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.entity.PessoaFisica;
import edu.ifgoiano.trabalho.model.entity.PessoaJuridica;
import edu.ifgoiano.trabalho.model.enums.TipoPessoa;
import java.util.List;

public abstract sealed class PessoaDto permits PessoaFisicaDto, PessoaJuridicaDto {

  public final Long id;
  public final TipoPessoa tipoPessoa;
  public final String telefone;
  public final String endereco;
  public final String observacoes;

  public PessoaDto(
      Long id, TipoPessoa tipoPessoa, String telefone, String endereco, String observacoes) {
    this.id = id;
    this.tipoPessoa = tipoPessoa;
    this.telefone = telefone;
    this.endereco = endereco;
    this.observacoes = observacoes;
  }

  public PessoaDto(Pessoa pessoa) {
    this.id = pessoa.getId();
    this.tipoPessoa = pessoa.getTipoPessoa();
    this.telefone = pessoa.getTelefone();
    this.endereco = pessoa.getEndereco();
    this.observacoes = pessoa.getObservacoes();
  }

  public abstract Pessoa toEntity();

  public static PessoaDto ofPessoa(Pessoa pessoa) {
    if (pessoa instanceof PessoaFisica pessoaFisica)
      return PessoaFisicaDto.ofPessoaFisica(pessoaFisica);
    else if (pessoa instanceof PessoaJuridica pessoaJuridica)
      return PessoaJuridicaDto.ofPessoaJuridica(pessoaJuridica);
    else throw new IllegalStateException("Produto pode ser apenas Peca ou Bicicleta");
  }

  public static List<? extends PessoaDto> ofPessoas(List<Pessoa> pessoas) {
    return pessoas.stream().map(PessoaDto::ofPessoa).toList();
  }
}
