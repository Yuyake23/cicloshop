package edu.ifgoiano.trabalho.domain;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public class Bicicleta extends Produto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private String codigoSerial;
	private String modelo;
	private String cor;

	public Bicicleta() {
		super();
	}

	public Bicicleta(Long id, Pessoa dono, String marca, BigDecimal precoCompra, String codigoSerial, String modelo,
			String cor) {
		super(id, dono, marca, precoCompra);
		this.codigoSerial = codigoSerial;
		this.modelo = modelo;
		this.cor = cor;
	}

	public String getCodigoSerial() {
		return codigoSerial;
	}

	public void setCodigoSerial(String codigoSerial) {
		this.codigoSerial = codigoSerial;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	@Override
	public String toString() {
		return "Bicicleta [id=" + id + ", dono=" + dono + ", marca=" + marca + ", precoCompra=" + precoCompra
				+ "codigoSerial=" + codigoSerial + ", modelo=" + modelo + ", cor=" + cor + "]";
	}

}
