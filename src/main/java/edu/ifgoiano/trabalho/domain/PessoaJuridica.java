package edu.ifgoiano.trabalho.domain;

import java.time.LocalDate;

import edu.ifgoiano.trabalho.domain.enums.TipoPessoa;

public class PessoaJuridica extends Pessoa{

	private static final long serialVersionUID = 1L;
	
	protected String nomeFantasia;
	
	public PessoaJuridica() {
		super();
	}
	
	public PessoaJuridica(Long id, TipoPessoa tipoPessoa, String cnpj, String razaoSocial, String nomeFantasia, String telefone, String endereco, LocalDate data, String observacoes) {
		super(id, tipoPessoa, razaoSocial, cnpj, telefone, endereco, data, observacoes);
		this.nomeFantasia = nomeFantasia;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + getId() + ", tipoPessoa=" + getTipoPessoa() + ", razaoSocial=" + nome + ", nomeFantasia=" + nomeFantasia + ", cnpj=" + documento
				+ ", telefone=" + telefone + ", endereco=" + endereco + ", data=" + data + ", observacoes="
				+ observacoes + "]";
	}

}
