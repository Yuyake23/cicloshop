package edu.ifgoiano.trabalho.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import edu.ifgoiano.trabalho.model.enums.TipoPessoa;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pessoa", discriminatorType = DiscriminatorType.STRING)
public abstract class Pessoa implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pessoa_id")
	protected Long id;
	@Transient
	protected TipoPessoa tipoPessoa;
	// nome se pessoa física
	// razão social se pessoa jurídica
	protected String nome;
	// cpf se pessoa física
	// cnpj se pessoa jurídica
	protected String documento;
	protected String telefone;
	protected String endereco;
	@Temporal(value = TemporalType.DATE)
	protected LocalDate dataGenese;
	protected String observacoes;
	
	protected Pessoa() {
		super();
	}
	
	protected Pessoa(Long id, String nome, String documento, String telefone, String endereco, LocalDate dataGenese, String observacoes) {
		this.id = id;
		this.nome = nome;
		this.documento = documento;
		this.telefone = telefone;
		this.endereco = endereco;
		this.dataGenese = dataGenese;
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

	public LocalDate getDataGenese() {
		return dataGenese;
	}

	public void setDataGenese(LocalDate dataGenese) {
		this.dataGenese = dataGenese;
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
				+ ", telefone=" + telefone + ", endereco=" + endereco + ", dataGenese=" + dataGenese + ", observacoes="
				+ observacoes + "]";
	}
	
}
