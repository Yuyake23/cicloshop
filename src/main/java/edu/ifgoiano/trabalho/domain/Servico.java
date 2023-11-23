package edu.ifgoiano.trabalho.domain;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import edu.ifgoiano.trabalho.domain.enums.StatusServico;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Servico implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "servico_id")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Pessoa cliente;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "funcionario_servico", 
			joinColumns = {@JoinColumn(name = "servico_id")},
			inverseJoinColumns = {@JoinColumn(name = "contrato_funcionario_id")})
	private List<ContratoFuncionario> funcionarios;
	@Enumerated
	private StatusServico statusServico;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "produto_servico", 
			joinColumns = {@JoinColumn(name = "servico_id")},
			inverseJoinColumns = {@JoinColumn(name = "contrato_funcionario_id")})
	private List<Produto> produtos;
	private BigDecimal custoMaoDeObra;
	private BigDecimal custoProdutos;
	private String descricao;
	private String observacoes;
	@Temporal(TemporalType.DATE)
	private LocalDate dataEntrada;
	@Temporal(TemporalType.DATE)
	private LocalDate dataSaida;

	public Servico() {
		super();
	}

	public Servico(Long id, Pessoa cliente, List<ContratoFuncionario> funcionarios, StatusServico statusServico,
			List<Produto> produtos, BigDecimal custoMaoDeObra, BigDecimal custoProdutos, String descricao,
			String observacoes, LocalDate dataEntrada, LocalDate dataSaida) {
		this.id = id;
		this.cliente = cliente;
		this.funcionarios = funcionarios;
		this.statusServico = statusServico;
		this.produtos = produtos;
		this.custoMaoDeObra = custoMaoDeObra;
		this.custoProdutos = custoProdutos;
		this.descricao = descricao;
		this.observacoes = observacoes;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
	}

	public BigDecimal calcularCustoTotal() {
		return custoMaoDeObra.add(custoProdutos);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public List<ContratoFuncionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<ContratoFuncionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public StatusServico getStatusServico() {
		return statusServico;
	}

	public void setStatusServico(StatusServico statusServico) {
		this.statusServico = statusServico;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public BigDecimal getCustoMaoDeObra() {
		return custoMaoDeObra;
	}

	public void setCustoMaoDeObra(BigDecimal custoMaoDeObra) {
		this.custoMaoDeObra = custoMaoDeObra;
	}

	public BigDecimal getCustoProdutos() {
		return custoProdutos;
	}

	public void setCustoProdutos(BigDecimal custoProdutos) {
		this.custoProdutos = custoProdutos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
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
		Servico other = (Servico) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Servico [id=" + id + ", cliente=" + cliente + ", funcionarios=" + funcionarios + ", statusServico="
				+ statusServico + ", produtos=" + produtos + ", custoMaoDeObra=" + custoMaoDeObra + ", custoProdutos="
				+ custoProdutos + ", descricao=" + descricao + ", observacoes=" + observacoes + ", dataEntrada="
				+ dataEntrada + ", dataSaida=" + dataSaida + "]";
	}

}
