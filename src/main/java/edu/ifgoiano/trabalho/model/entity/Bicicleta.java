package edu.ifgoiano.trabalho.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;

/**
 * Representa uma bicicleta única e não-fungível no contexto de uma oficina.
 * Uma bicicleta é diferenciada por seu {@link Bicicleta#codigoSerial}.
 */
@Entity
public class Bicicleta extends Produto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private String codigoSerial;
	private String modelo;
	private String cor;

	public Bicicleta() {
		super();
	}

	public Bicicleta(Long id, Pessoa dono, String marca, BigDecimal valor, String codigoSerial, String modelo,
			String cor) {
		super(id, dono, marca, valor);
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
		return "Bicicleta [id=" + id + ", dono=" + dono + ", marca=" + marca + ", valor=" + valor
				+ ", codigoSerial=" + codigoSerial + ", modelo=" + modelo + ", cor=" + cor + "]";
	}

}
