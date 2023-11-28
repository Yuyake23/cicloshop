package edu.ifgoiano.trabalho.dto;

import java.util.List;

import edu.ifgoiano.trabalho.model.entity.DadosBancarios;

public final class DadosBancariosDto {

	public final Long id;
	public final String nomeBanco;
	public final String numeroAgencia;
	public final String tipoConta;
	public final String numeroConta;
	public final String operacao;

	public DadosBancariosDto(Long id, String nomeBanco, String numeroAgencia, String tipoConta, String numeroConta,
			String operacao, Long contratoFuncionarioId) {
		this.id = id;
		this.nomeBanco = nomeBanco;
		this.numeroAgencia = numeroAgencia;
		this.tipoConta = tipoConta;
		this.numeroConta = numeroConta;
		this.operacao = operacao;
	}

	public DadosBancariosDto(DadosBancarios dadosBancarios) {
		this.id = dadosBancarios.getId();
		this.nomeBanco = dadosBancarios.getNomeBanco();
		this.numeroAgencia = dadosBancarios.getNumeroAgencia();
		this.tipoConta = dadosBancarios.getTipoConta();
		this.numeroConta = dadosBancarios.getNumeroConta();
		this.operacao = dadosBancarios.getOperacao();
	}

	public DadosBancarios toEntity() {
		return new DadosBancarios(id, nomeBanco, numeroAgencia, tipoConta, numeroConta, operacao, null);
	}

	public static DadosBancariosDto ofDadosBancarios(DadosBancarios dadosBancarios) {
		return new DadosBancariosDto(dadosBancarios);
	}

	public static List<DadosBancariosDto> ofDadosBancarios(List<DadosBancarios> dadosBancarios) {
		return dadosBancarios.stream().map(DadosBancariosDto::ofDadosBancarios).toList();
	}

}
