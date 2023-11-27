package edu.ifgoiano.trabalho.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;

/**
 * Representa uma peça fungível, que pode ter como dono qualquer {@link PessoaFisica} ou
 * {@link PessoaJuridica}.
 * </br>
 * Um mesmo tipo de peça pode ter preços de compra ({@link PrecoProduto}) diferentes dependendo de
 * qual {@link Fornecedor} foram compradas. Contudo, peças de um mesmo tipo são vendidas com um
 * preço único baseado na compra mais cara efetuada dentro desse tipo, e que ainda esteja em estoque.
 */
@Entity
public class Peca extends Produto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private String nome;
	private Integer quantidade;

	public Peca() {
		super();
	}

	public Peca(Long id, Pessoa dono, String marca, BigDecimal valor, String nome,
			Integer quantidade) {
		super(id, dono, marca, valor);
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
