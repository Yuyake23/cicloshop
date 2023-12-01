package edu.ifgoiano.trabalho.controller;

import edu.ifgoiano.trabalho.dto.PessoaFisicaDto;
import edu.ifgoiano.trabalho.service.PessoaFisicaService;
import edu.ifgoiano.trabalho.service.PessoaService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/pessoa_fisica")
public class PessoaFisicaController {

  @Autowired private PessoaService pessoaService;
  @Autowired private PessoaFisicaService pessoaFisicaService;

  /**
   * Registra uma pessoa física na base de dados.
   *
   * @param dto Um objeto representando a pessoa física a ser registrada.
   * @return A pessoa física registrado na base de dados.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PessoaFisicaDto salvar(@RequestBody PessoaFisicaDto dto) {
    return pessoaService.salvar(dto);
  }

  /**
   * Registra várias pessoas físicas na base de dados.
   *
   * @param dtos Uma lista de pessoas físicas para serem registradas.
   * @return A lista das pessoas físicas registradas.
   */
  @PostMapping("/varias")
  @ResponseStatus(HttpStatus.CREATED)
  public List<PessoaFisicaDto> salvarTodos(@RequestBody List<PessoaFisicaDto> dtos) {
    return pessoaService.salvarTodos(dtos);
  }

  /**
   * Retorna todas as pessoas físicas registradas.
   *
   * @return Uma lista de todas as pessoas físicas registradas.
   */
  @GetMapping
  public CollectionModel<List<EntityModel<PessoaFisicaDto>>> buscarTodos() {
    return CollectionModel.of(
            Collections.singleton(pessoaFisicaService.buscarTodos().stream().map(EntityModel::of).toList()));
  }

  /**
   * Busca uma pessoa física por seu id na base de dados.
   *
   * @param id O id da pessoa física a ser buscada.
   * @return A pessoa, caso esteja registrada.
   */
  @GetMapping("/{id}")
  public EntityModel<PessoaFisicaDto> buscarPorId(@PathVariable Long id) {
    return EntityModel.of(pessoaFisicaService.buscarPorId(id));
  }

  /**
   * Atualiza uma pessoa física com dados novos, sobrescrevendo-a.
   *
   * @param id O id da pessoa física a ser sobrescrita.
   * @param dto A pessoa física que sobrescreverá a antiga.
   * @return A pessoa física entrada na base de dados.
   */
  @PutMapping("/{id}")
  public PessoaFisicaDto atualizarCompletamente(
      @PathVariable Long id, @RequestBody PessoaFisicaDto dto) {
    return pessoaFisicaService.atualizarCompletamente(dto, id);
  }

  /**
   * Modifica os dados de uma pessoa física sem sobrescrevê-la.
   *
   * @param id O id da pessoa física a ser atualizada.
   * @param dto Um objeto contendo os dados a serem modificados.
   * @return Os dados da pessoa física que foi atualizada.
   */
  @PatchMapping("/{id}")
  public PessoaFisicaDto atualizarParcialmente(
      @PathVariable Long id, @RequestBody PessoaFisicaDto dto) {
    return pessoaFisicaService.atualizarParcialmente(dto, id);
  }

  /**
   * Deleta uma pessoa física do banco de dados por meio de seu id.
   *
   * @param id O id da pessoa física a ser deletada.
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletarPorId(@PathVariable Long id) {
    pessoaService.deletarPorId(id);
  }
}
