package edu.ifgoiano.trabalho.dto;

import java.math.BigDecimal;
import java.util.List;

import edu.ifgoiano.trabalho.model.entity.Bicicleta;
import edu.ifgoiano.trabalho.model.entity.Peca;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.entity.Produto;

public abstract sealed class ProdutoDto permits PecaDto, BicicletaDto {

	public final Long id;
	public final Long donoId;
	public final String marca;
	public final BigDecimal valor;

	public ProdutoDto(Long id, Long donoId, String marca, BigDecimal valor) {
		this.id = id;
		this.donoId = donoId;
		this.marca = marca;
		this.valor = valor;
	}

	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.donoId = produto.getId();
		this.marca = produto.getMarca();
		this.valor = produto.getValor();
	}

	public abstract Produto toEntity(Pessoa dono);

	public static ProdutoDto ofProduto(Produto produto) {
		if (produto instanceof Peca peca)
			return new PecaDto(peca);
		else if (produto instanceof Bicicleta bicicleta)
			return new BicicletaDto(bicicleta);
		else
			throw new IllegalStateException("Produto pode ser apenas Peca ou Bicicleta");
	}

	public static List<? extends ProdutoDto> ofProdutos(List<Produto> produtos) {
		return produtos.stream().map(ProdutoDto::ofProduto).toList();
	}

}
