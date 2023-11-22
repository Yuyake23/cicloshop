package edu.ifgoiano.trabalho.domain;

import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.ifgoiano.trabalho.domain.enums.TipoPessoa;

public class Fornecedor extends PessoaJuridica {

	@Serial
	private static final long serialVersionUID = 1L;

	private List<PrecoProduto> produtosFornecidos;

	public Fornecedor() {
		super();
		this.produtosFornecidos = new ArrayList<>();
	}

	public Fornecedor(Long id, TipoPessoa tipoPessoa, String cnpj, String razaoSocial, String nomeFantasia,
			String telefone, String endereco, LocalDate data, String observacoes) {
		super(id, tipoPessoa, cnpj, razaoSocial, nomeFantasia, telefone, endereco, data, observacoes);
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
		return "Pessoa [id=" + getId() + ", tipoPessoa=" + getTipoPessoa() + ", razaoSocial=" + nome + ", nomeFantasia="
				+ nomeFantasia + ", cnpj=" + documento + ", telefone=" + telefone + ", endereco=" + endereco + ", data="
				+ data + ", observacoes=" + observacoes + "]";
	}

}
