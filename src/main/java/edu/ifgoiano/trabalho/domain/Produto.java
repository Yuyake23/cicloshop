package edu.ifgoiano.trabalho.domain;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public abstract class Produto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	protected Long id;
	protected Pessoa dono;
	protected String marca;
	protected BigDecimal precoCompra;

	public Produto() {

	}

	public Produto(Long id, Pessoa dono, String marca, BigDecimal precoCompra) {
		this.id = id;
		this.dono = dono;
		this.marca = marca;
		this.precoCompra = precoCompra;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getDono() {
		return dono;
	}

	public void setDono(Pessoa dono) {
		this.dono = dono;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public BigDecimal getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(BigDecimal precoCompra) {
		this.precoCompra = precoCompra;
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", dono=" + dono + ", marca=" + marca + ", precoCompra=" + precoCompra + "]";
	}

}
