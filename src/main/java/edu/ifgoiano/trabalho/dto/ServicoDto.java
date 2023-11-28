package edu.ifgoiano.trabalho.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import edu.ifgoiano.trabalho.model.entity.ContratoFuncionario;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.entity.Produto;
import edu.ifgoiano.trabalho.model.entity.Servico;
import edu.ifgoiano.trabalho.model.enums.StatusServico;

public class ServicoDto {

	public final Long id;
	public final Long clienteId;
	public final List<Long> funcionariosId;
	public final StatusServico statusServico;
	public final List<Long> produtosId;
	public final BigDecimal custoMaoDeObra;
	public final BigDecimal custoProdutos;
	public final String descricao;
	public final String observacoes;
	public final LocalDate dataEntrada;
	public final LocalDate dataSaida;

	public ServicoDto(Long id, Long clienteId, List<Long> funcionariosId, StatusServico statusServico,
			List<Long> produtosId, BigDecimal custoMaoDeObra, BigDecimal custoProdutos, String descricao,
			String observacoes, LocalDate dataEntrada, LocalDate dataSaida) {
		this.id = id;
		this.clienteId = clienteId;
		this.funcionariosId = funcionariosId;
		this.statusServico = statusServico;
		this.produtosId = produtosId;
		this.custoMaoDeObra = custoMaoDeObra;
		this.custoProdutos = custoProdutos;
		this.descricao = descricao;
		this.observacoes = observacoes;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
	}

	public ServicoDto(Servico servico) {
		this.id = servico.getId();
		this.clienteId = servico.getCliente().getId();
		this.funcionariosId = servico.getFuncionarios().stream().map(ContratoFuncionario::getId).toList();
		this.statusServico = servico.getStatusServico();
		this.produtosId = servico.getProdutos().stream().map(Produto::getId).toList();
		this.custoMaoDeObra = servico.getCustoMaoDeObra();
		this.custoProdutos = servico.getCustoProdutos();
		this.descricao = servico.getDescricao();
		this.observacoes = servico.getObservacoes();
		this.dataEntrada = servico.getDataEntrada();
		this.dataSaida = servico.getDataSaida();
	}

	public Servico toEntity() {
		Servico servico = new Servico(id);
		servico.setCliente(new Pessoa(clienteId));
		servico.setFuncionarios(funcionariosId.stream().map(ContratoFuncionario::new).toList());
		servico.setStatusServico(statusServico);
		servico.setProdutos(produtosId.stream().map(Produto::new).toList());
		servico.setCustoMaoDeObra(custoMaoDeObra);
		servico.setCustoProdutos(custoProdutos);
		servico.setDescricao(descricao);
		servico.setObservacoes(observacoes);
		servico.setDataEntrada(dataEntrada);
		servico.setDataSaida(dataSaida);
		return servico;
	}

	public static ServicoDto ofServico(Servico servico) {
		return new ServicoDto(servico);
	}

	public static List<ServicoDto> ofServicos(List<Servico> servicos) {
		return servicos.stream().map(ServicoDto::ofServico).toList();
	}

}
