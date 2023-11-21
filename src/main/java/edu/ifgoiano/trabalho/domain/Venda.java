package edu.ifgoiano.trabalho.domain;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Venda implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private List<Produto> produtos;
	private Pessoa comprador;
	private ContratoFuncionario funcionario;
	private LocalDate dataVenda;
	private BigDecimal valor;
	private String observacoes;
	
	public Venda() {
		super();
		this.produtos = new ArrayList<>();
	}

	public Venda(Long id, List<Produto> produtos, Pessoa comprador, ContratoFuncionario funcionario,
			LocalDate dataVenda, BigDecimal valor, String observacoes) {
		this.id = id;
		this.produtos = produtos;
		this.comprador = comprador;
		this.funcionario = funcionario;
		this.dataVenda = dataVenda;
		this.valor = valor;
		this.observacoes = observacoes;
	}
	
	public Venda(Long id, Pessoa comprador, ContratoFuncionario funcionario,
			LocalDate dataVenda, String observacoes) {
		this.id = id;
		this.produtos = new ArrayList<>();
		this.comprador = comprador;
		this.funcionario = funcionario;
		this.dataVenda = dataVenda;
		this.observacoes = observacoes;
		calcularValor();
	}

	private void calcularValor() {
		this.valor = produtos.stream()
				.map(Produto::getValor)
				.reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
		calcularValor();
	}
	
	public void setProdutos(List<Produto> produtos, BigDecimal valorTotal) {
		this.produtos = produtos;
		this.valor = valorTotal;
	}

	public Pessoa getComprador() {
		return comprador;
	}

	public void setComprador(Pessoa comprador) {
		this.comprador = comprador;
	}

	public ContratoFuncionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(ContratoFuncionario funcionario) {
		this.funcionario = funcionario;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
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
		Venda other = (Venda) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", produtos=" + produtos + ", comprador=" + comprador + ", funcionario="
				+ funcionario + ", dataVenda=" + dataVenda + ", valor=" + valor + ", observacoes=" + observacoes + "]";
	}
	
}
