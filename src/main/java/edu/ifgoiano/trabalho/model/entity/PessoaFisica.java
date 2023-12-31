package edu.ifgoiano.trabalho.model.entity;

import edu.ifgoiano.trabalho.model.enums.TipoPessoa;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.io.Serial;
import java.time.LocalDate;

/** Representa uma {@link Pessoa} física. */
@Entity
@DiscriminatorValue("FISICA")
public class PessoaFisica extends Pessoa {

  @Serial private static final long serialVersionUID = 1L;

  public PessoaFisica() {
    this.tipoPessoa = TipoPessoa.FISICA;
  }

  public PessoaFisica(Long id) {
    super(id);
  }

  public PessoaFisica(
      Long id,
      String nome,
      String cpf,
      String telefone,
      String endereco,
      LocalDate dataNascimento,
      String observacoes) {
    super(id, nome, cpf, telefone, endereco, dataNascimento, observacoes);
    this.tipoPessoa = TipoPessoa.FISICA;
  }

  @Override
  public String toString() {
    return "Pessoa [id="
        + this.getId()
        + ", tipoPessoa="
        + getTipoPessoa()
        + ", nome="
        + nome
        + ", cpf="
        + documento
        + ", telefone="
        + telefone
        + ", endereco="
        + endereco
        + ", dataGenese="
        + dataGenese
        + ", observacoes="
        + observacoes
        + "]";
  }
}
