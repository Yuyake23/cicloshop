package edu.ifgoiano.trabalho.dto;

import java.math.BigDecimal;
import java.util.List;

import edu.ifgoiano.trabalho.model.entity.Peca;
import edu.ifgoiano.trabalho.model.entity.Pessoa;

public final class PecaDto extends ProdutoDto {

	public final String nome;
	public final Integer quantidade;

	public PecaDto(Long id, Long donoId, String marca, BigDecimal valor, String nome, Integer quantidade) {
		super(id, donoId, marca, valor);
		this.nome = nome;
		this.quantidade = quantidade;
	}

	public PecaDto(Peca peca) {
		super(peca);
		this.nome = peca.getNome();
		this.quantidade = peca.getQuantidade();
	}

	@Override
	public Peca toEntity(Pessoa dono) {
		return new Peca(id, dono, marca, valor, nome, quantidade);
	}

	public static PecaDto ofPeca(Peca peca) {
		return new PecaDto(peca);
	}
	
	public static List<PecaDto> ofPecas(List<Peca> pecas) {
		return pecas.stream().map(PecaDto::ofPeca).toList();
	}

}
