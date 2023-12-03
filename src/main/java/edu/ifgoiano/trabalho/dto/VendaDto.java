package edu.ifgoiano.trabalho.dto;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import edu.ifgoiano.trabalho.controller.PessoaController;
import edu.ifgoiano.trabalho.controller.VendaController;
import edu.ifgoiano.trabalho.model.entity.ContratoFuncionario;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.entity.Produto;
import edu.ifgoiano.trabalho.model.entity.Venda;

public class VendaDto extends RepresentationModel<VendaDto> {

  public final Long id;
  public final List<Long> produtosId;
  public final Long compradorId;
  public final Long contratoFuncionarioId;
  public final LocalDate dataVenda;
  public final BigDecimal valor;
  public final String observacoes;

  public VendaDto(
      Long id,
      List<Long> produtosId,
      Long compradorId,
      Long contratoFuncionarioId,
      LocalDate dataVenda,
      BigDecimal valor,
      String observacoes) {
    this.id = id;
    this.produtosId = produtosId;
    this.compradorId = compradorId;
    this.contratoFuncionarioId = contratoFuncionarioId;
    this.dataVenda = dataVenda;
    this.valor = valor;
    this.observacoes = observacoes;
    addLinks();
  }

  public VendaDto(Venda venda) {
    this.id = venda.getId();
    this.produtosId = venda.getProdutos().stream().map(Produto::getId).toList();
    this.compradorId = venda.getComprador().getId();
    this.contratoFuncionarioId = venda.getFuncionario().getId();
    this.dataVenda = venda.getDataVenda();
    this.valor = venda.getValor();
    this.observacoes = venda.getObservacoes();
    addLinks();
  }
  
  private void addLinks() {
    Link selfLink = linkTo(
        methodOn(VendaController.class).buscarPorId(id))
        .withSelfRel()
        .withType("GET");
    Link clienteLink = linkTo(
        methodOn(PessoaController.class).buscarPorId(compradorId))
        .withRel("cliente")
        .withType("GET");
    
    super.add(selfLink, clienteLink);
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

  public static CollectionModel<VendaDto> ofVendas(List<Venda> vendas) {
    Link selfLink = linkTo(
        methodOn(VendaController.class).buscarTodos())
        .withSelfRel()
        .withType("GET");
    return CollectionModel.of(
        vendas.stream()
          .map(VendaDto::ofVenda)
          .toList(), selfLink);
  }
}
