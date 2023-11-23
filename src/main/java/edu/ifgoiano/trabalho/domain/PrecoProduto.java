package edu.ifgoiano.trabalho.domain;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PrecoProduto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "preco_produto_id")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	@JsonIgnore
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
