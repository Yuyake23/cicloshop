package edu.ifgoiano.trabalho.model.entity;

import edu.ifgoiano.trabalho.model.enums.TipoPessoa;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Representa um fornecedor de {@link Produto}s para a oficina de bicicletas. */
@Entity
@DiscriminatorValue("FORNECEDOR")
public class Fornecedor extends PessoaJuridica {

  @Serial private static final long serialVersionUID = 1L;

  @OneToMany(mappedBy = "fornecedor")
  private List<PrecoProduto> produtosFornecidos;

  public Fornecedor() {
    this.tipoPessoa = TipoPessoa.FORNECEDOR;
    this.produtosFornecidos = new ArrayList<>();
  }

  public Fornecedor(Long id) {
    super.id = id;
    this.tipoPessoa = TipoPessoa.FORNECEDOR;
    this.produtosFornecidos = new ArrayList<>();
  }

  public Fornecedor(
      Long id,
      String cnpj,
      String razaoSocial,
      String nomeFantasia,
      String telefone,
      String endereco,
      LocalDate dataFundacao,
      String observacoes) {
    super(id, cnpj, razaoSocial, nomeFantasia, telefone, endereco, dataFundacao, observacoes);
    this.tipoPessoa = TipoPessoa.FORNECEDOR;
    this.produtosFornecidos = new ArrayList<>();
  }

  public List<PrecoProduto> getProdutosFornecidos() {
    return produtosFornecidos;
  }

  public void setProdutosFornecidos(List<PrecoProduto> produtosFornecidos) {
    this.produtosFornecidos = produtosFornecidos;
  }

  @Override
  public String toString() {
    return "Pessoa [id="
        + getId()
        + ", tipoPessoa="
        + getTipoPessoa()
        + ", razaoSocial="
        + nome
        + ", nomeFantasia="
        + nomeFantasia
        + ", cnpj="
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
