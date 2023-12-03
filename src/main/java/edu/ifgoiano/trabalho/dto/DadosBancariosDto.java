package edu.ifgoiano.trabalho.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import edu.ifgoiano.trabalho.controller.DadosBancariosController;
import edu.ifgoiano.trabalho.model.entity.DadosBancarios;

public class DadosBancariosDto extends RepresentationModel<DadosBancariosDto> {

  public final Long id;
  public final String nomeBanco;
  public final String numeroAgencia;
  public final String tipoConta;
  public final String numeroConta;
  public final String operacao;

  public DadosBancariosDto(
      Long id,
      String nomeBanco,
      String numeroAgencia,
      String tipoConta,
      String numeroConta,
      String operacao,
      Long contratoFuncionarioId) {
    this.id = id;
    this.nomeBanco = nomeBanco;
    this.numeroAgencia = numeroAgencia;
    this.tipoConta = tipoConta;
    this.numeroConta = numeroConta;
    this.operacao = operacao;
    addLinks();
  }

  public DadosBancariosDto(DadosBancarios dadosBancarios) {
    this.id = dadosBancarios.getId();
    this.nomeBanco = dadosBancarios.getNomeBanco();
    this.numeroAgencia = dadosBancarios.getNumeroAgencia();
    this.tipoConta = dadosBancarios.getTipoConta();
    this.numeroConta = dadosBancarios.getNumeroConta();
    this.operacao = dadosBancarios.getOperacao();
    addLinks();
  }
  
  private void addLinks() {
    Link selfLink = linkTo(
        methodOn(DadosBancariosController.class).buscarPorId(id))
        .withSelfRel()
        .withType("GET");
    
    super.add(selfLink);
  }

  public DadosBancarios toEntity() {
    return new DadosBancarios(id, nomeBanco, numeroAgencia, tipoConta, numeroConta, operacao, null);
  }

  public static DadosBancariosDto ofDadosBancarios(DadosBancarios dadosBancarios) {
    return new DadosBancariosDto(dadosBancarios);
  }

  public static CollectionModel<DadosBancariosDto> ofDadosBancarios(List<DadosBancarios> dadosBancarios) {
    Link selfLink = linkTo(
        methodOn(DadosBancariosController.class).buscarTodos())
        .withSelfRel()
        .withType("GET");
    return CollectionModel.of(
        dadosBancarios.stream()
          .map(DadosBancariosDto::ofDadosBancarios)
          .toList(), selfLink);
  }
}
