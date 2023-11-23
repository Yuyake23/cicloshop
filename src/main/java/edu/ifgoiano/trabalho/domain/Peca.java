package edu.ifgoiano.trabalho.domain;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;

@Entity
public class Peca extends Produto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private String nome;
	private Integer quantidade;

	public Peca() {
		super();
	}

	public Peca(Long id, Pessoa dono, String marca, BigDecimal precoCompra, String nome,
			Integer quantidade) {
		super(id, dono, marca, precoCompra);
		this.nome = nome;
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "Peca [id=" + id + ", nome=" + nome + ", dono=" + dono + ", marca=" + marca + ", valor="
				+ valor + ", quantidade=" + quantidade + "]";
	}

}
