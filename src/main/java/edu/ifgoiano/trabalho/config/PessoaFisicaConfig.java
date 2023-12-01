package edu.ifgoiano.trabalho.config;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import edu.ifgoiano.trabalho.controller.PessoaFisicaController;
import edu.ifgoiano.trabalho.dto.PessoaFisicaDto;
import java.util.List;
import java.util.Objects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;

/**
 * Configuração automática para gerar links da {@link
 * edu.ifgoiano.trabalho.model.entity.PessoaFisica Pessoa física}.
 */
@Configuration
public class PessoaFisicaConfig {

  @Bean
  RepresentationModelProcessor<EntityModel<PessoaFisicaDto>> buscarPorIdProcessor() {
    return model ->
        model.add(
            linkTo(
                    methodOn(PessoaFisicaController.class)
                        .buscarPorId(Objects.requireNonNull(model.getContent()).id))
                .withRel(LinkRelation.of("self"))
                .withType("GET"));
  }

  @Bean
  RepresentationModelProcessor<CollectionModel<List<EntityModel<PessoaFisicaDto>>>>
      buscarTodosProcessor() {
    return model ->
        model.add(
            linkTo(methodOn(PessoaFisicaController.class).buscarTodos())
                .withRel(LinkRelation.of("self"))
                .withType("GET"));
  }
}
