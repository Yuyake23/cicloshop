package edu.ifgoiano.trabalho.domain;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class PrecoProduto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Produto produto;
	private Fornecedor fornecedor;
	private BigDecimal preco;
	private Float porcentagemLucro;

	public PrecoProduto() {
		super();
	}

	public PrecoProduto(Long id, Produto produto, Fornecedor fornecedor, BigDecimal preco, Float porcentagemLucro) {
		this.id = id;
		this.produto = produto;
		this.fornecedor = fornecedor;
		this.preco = preco;
		this.porcentagemLucro = porcentagemLucro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Float getPorcentagemLucro() {
		return porcentagemLucro;
	}

	public void setPorcentagemLucro(Float porcentagemLucro) {
		this.porcentagemLucro = porcentagemLucro;
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
		PrecoProduto other = (PrecoProduto) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "PrecoProduto [id=" + id + ", produto=" + produto + ", fornecedor=" + fornecedor + ", preco=" + preco
				+ ", porcentagemLucro=" + porcentagemLucro + "]";
	}

}
