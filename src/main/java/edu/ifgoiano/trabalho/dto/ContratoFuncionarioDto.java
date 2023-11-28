package edu.ifgoiano.trabalho.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import edu.ifgoiano.trabalho.model.entity.ContratoFuncionario;
import edu.ifgoiano.trabalho.model.entity.DadosBancarios;
import edu.ifgoiano.trabalho.model.entity.Pessoa;

public final class ContratoFuncionarioDto {

	public final Long id;
	public final Long funcionarioId;
	public final String cargo;
	public final BigDecimal salario;
	public final DadosBancariosDto dadosBancarios;
	public final LocalDate dataEntrada;
	public final LocalDate dataSaida;

	public ContratoFuncionarioDto(Long id, Long funcionarioId, String cargo, BigDecimal salario,
			DadosBancarios dadosBancarios, LocalDate dataEntrada, LocalDate dataSaida) {
		this.id = id;
		this.funcionarioId = funcionarioId;
		this.cargo = cargo;
		this.salario = salario;
		this.dadosBancarios = DadosBancariosDto.ofDadosBancarios(dadosBancarios);
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
	}

	public ContratoFuncionarioDto(ContratoFuncionario contratoFuncionario) {
		this.id = contratoFuncionario.getId();
		this.funcionarioId = contratoFuncionario.getFuncionario().getId();
		this.cargo = contratoFuncionario.getCargo();
		this.salario = contratoFuncionario.getSalario();
		this.dadosBancarios = DadosBancariosDto.ofDadosBancarios(contratoFuncionario.getDadosBancarios());
		this.dataEntrada = contratoFuncionario.getDataEntrada();
		this.dataSaida = contratoFuncionario.getDataSaida();
	}

	public ContratoFuncionario toEntity() {
		return new ContratoFuncionario(id, new Pessoa(funcionarioId), cargo, salario, dadosBancarios.toEntity(),
				dataEntrada, dataSaida);
	}
	
	public static ContratoFuncionarioDto ofContratoFuncionario(ContratoFuncionario contratoFuncionario) {
		return new ContratoFuncionarioDto(contratoFuncionario);
	}
	
	public static List<ContratoFuncionarioDto> ofContratosFuncionario(List<ContratoFuncionario> contratosFuncionario){
		return contratosFuncionario.stream().map(ContratoFuncionarioDto::ofContratoFuncionario).toList();
	}

}
