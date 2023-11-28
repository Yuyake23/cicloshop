package edu.ifgoiano.trabalho.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class ContratoFuncionario implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "contrato_funcionario_id")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Pessoa funcionario;
	private String cargo;
	private BigDecimal salario;
	@OneToOne
	@JoinColumn(name = "dados_bancarios_id")
	private DadosBancarios dadosBancarios;
	@Temporal(TemporalType.DATE)
	private LocalDate dataEntrada;
	@Temporal(TemporalType.DATE)
	private LocalDate dataSaida;

	public ContratoFuncionario() {

	}

	public ContratoFuncionario(Long id) {
		this.id = id;
	}

	public ContratoFuncionario(Long id, Pessoa funcionario, String cargo, BigDecimal salario,
			DadosBancarios dadosBancarios, LocalDate dataEntrada, LocalDate dataSaida) {
		this.id = id;
		this.funcionario = funcionario;
		this.cargo = cargo;
		this.salario = salario;
		this.dadosBancarios = dadosBancarios;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Pessoa funcionario) {
		this.funcionario = funcionario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public DadosBancarios getDadosBancarios() {
		return dadosBancarios;
	}

	public void setDadosBancarios(DadosBancarios dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
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
		ContratoFuncionario other = (ContratoFuncionario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ContratoFuncionario [id=" + id + ", funcionario=" + funcionario + ", cargo=" + cargo + ", salario="
				+ salario + ", dadosBancarios=" + dadosBancarios + ", dataEntrada=" + dataEntrada + ", dataSaida="
				+ dataSaida + "]";
	}

}
