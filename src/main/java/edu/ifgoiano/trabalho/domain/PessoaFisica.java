package edu.ifgoiano.trabalho.domain;

import java.io.Serial;
import java.time.LocalDate;

import edu.ifgoiano.trabalho.domain.enums.TipoPessoa;

public class PessoaFisica extends Pessoa {

	@Serial
	private static final long serialVersionUID = 1L;
	
	public PessoaFisica() {
		super();
	}

	public PessoaFisica(Long id, TipoPessoa tipoPessoa, String nome, String cpf, String telefone, String endereco, LocalDate data,
			String observacoes) {
		super(id, tipoPessoa, nome, cpf, telefone, endereco, data, observacoes);
	}
	
	@Override
	public String toString() {
		return "Pessoa [id=" + this.getId() + ", tipoPessoa=" + getTipoPessoa() + ", nome=" + nome + ", cpf=" + documento
				+ ", telefone=" + telefone + ", endereco=" + endereco + ", data=" + data + ", observacoes="
				+ observacoes + "]";
	}
	
}