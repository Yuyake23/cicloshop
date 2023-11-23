package edu.ifgoiano.trabalho.domain;

import java.io.Serial;
import java.time.LocalDate;

import edu.ifgoiano.trabalho.domain.enums.TipoPessoa;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("FISICA")
public class PessoaFisica extends Pessoa {

	@Serial
	private static final long serialVersionUID = 1L;
	
	public PessoaFisica() {
		super();
		this.tipoPessoa = TipoPessoa.FISICA;
	}

	public PessoaFisica(Long id, String nome, String cpf, String telefone, String endereco, LocalDate dataNascimento,
			String observacoes) {
		super(id, nome, cpf, telefone, endereco, dataNascimento, observacoes);
		this.tipoPessoa = TipoPessoa.FISICA;
	}
	
	@Override
	public String toString() {
		return "Pessoa [id=" + this.getId() + ", tipoPessoa=" + getTipoPessoa() + ", nome=" + nome + ", cpf=" + documento
				+ ", telefone=" + telefone + ", endereco=" + endereco + ", dataGenese=" + dataGenese + ", observacoes="
				+ observacoes + "]";
	}
	
}