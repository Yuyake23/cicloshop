package edu.ifgoiano.trabalho.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import edu.ifgoiano.trabalho.domain.enums.TipoPessoa;

public abstract class Pessoa implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private TipoPessoa tipoPessoa;
	// nome se pessoa física
	// razão social se pessoa jurídica
	protected String nome;
	// cpf se pessoa física
	// cnpj se pessoa jurídica
	protected String documento;
	protected String telefone;
	protected String endereco;
	protected LocalDate data;
	protected String observacoes;
	
	protected Pessoa() {
		super();
	}
	
	protected Pessoa(Long id, TipoPessoa tipoPessoa, String nome, String documento, String telefone, String endereco, LocalDate data, String observacoes) {
		this.id = id;
		this.nome = nome;
		this.documento = documento;
		this.telefone = telefone;
		this.endereco = endereco;
		this.data = data;
		this.observacoes = observacoes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", tipoPessoa=" + tipoPessoa + ", nome=" + nome + ", documento=" + documento
				+ ", telefone=" + telefone + ", endereco=" + endereco + ", data=" + data + ", observacoes="
				+ observacoes + "]";
	}
	
}
