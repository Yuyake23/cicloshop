package edu.ifgoiano.trabalho.domain;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class ContratoFuncionario implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private Long id;
	private Pessoa funcionario;
	private String cargo;
	private BigDecimal salario;
	private DadosBancarios dadosBancarios;
	private LocalDate dataEntrada;
	private LocalDate dataSaida;

	public ContratoFuncionario() {
		super();
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
