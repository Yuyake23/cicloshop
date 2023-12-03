package edu.ifgoiano.trabalho.dto;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import edu.ifgoiano.trabalho.controller.PessoaController;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.entity.PessoaFisica;
import edu.ifgoiano.trabalho.model.entity.PessoaJuridica;
import edu.ifgoiano.trabalho.model.enums.TipoPessoa;

public abstract class PessoaDto extends RepresentationModel<PessoaDto> {

  public final Long id;
  public final TipoPessoa tipoPessoa;
  public final String telefone;
  public final String endereco;
  public final String observacoes;

  public PessoaDto(
      Long id, TipoPessoa tipoPessoa, String telefone, String endereco, String observacoes) {
    this.id = id;
    this.tipoPessoa = tipoPessoa;
    this.telefone = telefone;
    this.endereco = endereco;
    this.observacoes = observacoes;
    addLinks();
  }

  public PessoaDto(Pessoa pessoa) {
    this.id = pessoa.getId();
    this.tipoPessoa = pessoa.getTipoPessoa();
    this.telefone = pessoa.getTelefone();
    this.endereco = pessoa.getEndereco();
    this.observacoes = pessoa.getObservacoes();
    addLinks();
  }
  
  protected void addLinks() {
    Link selfLink = linkTo(
        methodOn(PessoaController.class).buscarPorId(id))
        .withSelfRel()
        .withType("GET");
    
    super.add(selfLink);
  }

  public abstract Pessoa toEntity();

  public static PessoaDto ofPessoa(Pessoa pessoa) {
    if (pessoa instanceof PessoaFisica pessoaFisica)
      return PessoaFisicaDto.ofPessoaFisica(pessoaFisica);
    else if (pessoa instanceof PessoaJuridica pessoaJuridica)
      return PessoaJuridicaDto.ofPessoaJuridica(pessoaJuridica);
    else throw new IllegalStateException("Produto pode ser apenas Peca ou Bicicleta");
  }

  public static CollectionModel<? extends PessoaDto> ofPessoas(List<Pessoa> pessoas) {
    Link selfLink = linkTo(
        methodOn(PessoaController.class).buscarTodos())
        .withSelfRel()
        .withType("GET");
    return CollectionModel.of(
        pessoas.stream()
          .map(PessoaDto::ofPessoa)
          .toList(), selfLink);
  }
}
