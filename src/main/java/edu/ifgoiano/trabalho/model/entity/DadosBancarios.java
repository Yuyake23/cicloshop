package edu.ifgoiano.trabalho.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DadosBancarios implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dados_bancarios_id")
	private Long id;
	private String nomeBanco;
	private String numeroAgencia;
	private String tipoConta;
	private String numeroConta;
	private String operacao;

	public DadosBancarios() {
		super();
	}

	public DadosBancarios(Long id, String nomeBanco, String numeroAgencia, String tipoConta, String numeroConta,
			String operacao) {
		this.id = id;
		this.nomeBanco = nomeBanco;
		this.numeroAgencia = numeroAgencia;
		this.tipoConta = tipoConta;
		this.numeroConta = numeroConta;
		this.operacao = operacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
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
		DadosBancarios other = (DadosBancarios) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "DadosBancarios [id=" + id + ", nomeBanco=" + nomeBanco + ", numeroAgencia=" + numeroAgencia
				+ ", tipoConta=" + tipoConta + ", numeroConta=" + numeroConta + ", operacao=" + operacao + "]";
	}

}
