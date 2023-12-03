package edu.ifgoiano.trabalho.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import edu.ifgoiano.trabalho.controller.ContratoFuncionarioController;
import edu.ifgoiano.trabalho.controller.DadosBancariosController;
import edu.ifgoiano.trabalho.controller.PessoaController;
import edu.ifgoiano.trabalho.model.entity.ContratoFuncionario;
import edu.ifgoiano.trabalho.model.entity.DadosBancarios;
import edu.ifgoiano.trabalho.model.entity.Pessoa;

public class ContratoFuncionarioDto extends RepresentationModel<ContratoFuncionarioDto> {

  public final Long id;
  public final Long funcionarioId;
  public final String cargo;
  public final BigDecimal salario;
  public final DadosBancariosDto dadosBancarios;
  public final LocalDate dataEntrada;
  public final LocalDate dataSaida;

  public ContratoFuncionarioDto(
      Long id,
      Long funcionarioId,
      String cargo,
      BigDecimal salario,
      DadosBancarios dadosBancarios,
      LocalDate dataEntrada,
      LocalDate dataSaida) {
    this.id = id;
    this.funcionarioId = funcionarioId;
    this.cargo = cargo;
    this.salario = salario;
    this.dadosBancarios = DadosBancariosDto.ofDadosBancarios(dadosBancarios);
    this.dataEntrada = dataEntrada;
    this.dataSaida = dataSaida;
    addLinks();
  }

  public ContratoFuncionarioDto(ContratoFuncionario contratoFuncionario) {
    this.id = contratoFuncionario.getId();
    this.funcionarioId = contratoFuncionario.getFuncionario().getId();
    this.cargo = contratoFuncionario.getCargo();
    this.salario = contratoFuncionario.getSalario();
    this.dadosBancarios =
        DadosBancariosDto.ofDadosBancarios(contratoFuncionario.getDadosBancarios());
    this.dataEntrada = contratoFuncionario.getDataEntrada();
    this.dataSaida = contratoFuncionario.getDataSaida();
    addLinks();
  }
  
  private void addLinks() {
    Link selfLink = linkTo(
        methodOn(ContratoFuncionarioController.class).buscarPorId(id))
        .withSelfRel()
        .withType("GET");
    Link funcionarioLink = linkTo(
        methodOn(PessoaController.class).buscarPorId(funcionarioId))
        .withRel("funcionario")
        .withType("GET");
    Link dadosBancariosLink = linkTo(
        methodOn(DadosBancariosController.class).buscarPorId(dadosBancarios.id))
        .withRel("dadosBancarios")
        .withType("GET");
    
    super.add(selfLink, funcionarioLink, dadosBancariosLink);
  }

  public ContratoFuncionario toEntity() {
    return new ContratoFuncionario(
        id,
        new Pessoa(funcionarioId),
        cargo,
        salario,
        dadosBancarios.toEntity(),
        dataEntrada,
        dataSaida);
  }

  public static ContratoFuncionarioDto ofContratoFuncionario(
      ContratoFuncionario contratoFuncionario) {
    return new ContratoFuncionarioDto(contratoFuncionario);
  }

  public static CollectionModel<ContratoFuncionarioDto> ofContratosFuncionario(
      List<ContratoFuncionario> contratosFuncionario) {
    Link selfLink = linkTo(
        methodOn(ContratoFuncionarioController.class).buscarTodos())
        .withSelfRel()
        .withType("GET");
    return CollectionModel.of(contratosFuncionario.stream()
        .map(ContratoFuncionarioDto::ofContratoFuncionario)
        .toList(), selfLink);
  }
}
