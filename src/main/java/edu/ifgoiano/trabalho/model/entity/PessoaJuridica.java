package edu.ifgoiano.trabalho.model.entity;

import java.time.LocalDate;

import edu.ifgoiano.trabalho.model.enums.TipoPessoa;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("JURIDICA")
public class PessoaJuridica extends Pessoa {

	private static final long serialVersionUID = 1L;

	protected String nomeFantasia;

	public PessoaJuridica() {
		this.tipoPessoa = TipoPessoa.JURIDICA;
	}

	public PessoaJuridica(Long id) {
		super.id = id;
		this.tipoPessoa = TipoPessoa.JURIDICA;
	}

	public PessoaJuridica(Long id, String cnpj, String razaoSocial, String nomeFantasia, String telefone,
			String endereco, LocalDate dataFundacao, String observacoes) {
		super(id, razaoSocial, cnpj, telefone, endereco, dataFundacao, observacoes);
		this.tipoPessoa = TipoPessoa.JURIDICA;
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
		return "Pessoa [id=" + getId() + ", tipoPessoa=" + getTipoPessoa() + ", razaoSocial=" + nome + ", nomeFantasia="
				+ nomeFantasia + ", cnpj=" + documento + ", telefone=" + telefone + ", endereco=" + endereco
				+ ", dataGenese=" + dataGenese + ", observacoes=" + observacoes + "]";
	}

}
