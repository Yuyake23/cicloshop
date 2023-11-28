package edu.ifgoiano.trabalho.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import edu.ifgoiano.trabalho.model.entity.ContratoFuncionario;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.entity.Produto;
import edu.ifgoiano.trabalho.model.entity.Venda;

public final class VendaDto {

	public final Long id;
	public final List<Long> produtosId;
	public final Long compradorId;
	public final Long contratoFuncionarioId;
	public final LocalDate dataVenda;
	public final BigDecimal valor;
	public final String observacoes;

	public VendaDto(Long id, List<Long> produtosId, Long compradorId, Long contratoFuncionarioId, LocalDate dataVenda,
			BigDecimal valor, String observacoes) {
		this.id = id;
		this.produtosId = produtosId;
		this.compradorId = compradorId;
		this.contratoFuncionarioId = contratoFuncionarioId;
		this.dataVenda = dataVenda;
		this.valor = valor;
		this.observacoes = observacoes;
	}

	public VendaDto(Venda venda) {
		this.id = venda.getId();
		this.produtosId = venda.getProdutos().stream().map(Produto::getId).toList();
		this.compradorId = venda.getComprador().getId();
		this.contratoFuncionarioId = venda.getFuncionario().getId();
		this.dataVenda = venda.getDataVenda();
		this.valor = venda.getValor();
		this.observacoes = venda.getObservacoes();
	}

	public Venda toEntity() {
		Venda venda = new Venda();
		venda.setId(id);
		venda.setProdutos(produtosId.stream().map(Produto::new).toList());
		venda.setComprador(new Pessoa(compradorId));
		venda.setFuncionario(new ContratoFuncionario(contratoFuncionarioId));
		venda.setDataVenda(dataVenda);
		venda.setValor(valor);
		venda.setObservacoes(observacoes);
		return venda;
	}
	
	public static VendaDto ofVenda(Venda venda) {
		return new VendaDto(venda);
	}
	
	public static List<VendaDto> ofVendas(List<Venda> vendas) {
		return vendas.stream().map(VendaDto::ofVenda).toList();
	}

}
