package edu.ifgoiano.trabalho.controller;

import edu.ifgoiano.trabalho.dto.PessoaJuridicaDto;
import edu.ifgoiano.trabalho.service.PessoaJuridicaService;
import edu.ifgoiano.trabalho.service.PessoaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/pessoa_juridica")
public class PessoaJuridicaController {

  @Autowired private PessoaService pessoaService;
  @Autowired private PessoaJuridicaService pessoaJuridicaService;

  /**
   * Registra uma pessoa jurídica na base de dados.
   *
   * @param dto Um objeto representando a pessoa jurídica a ser registrada.
   * @return A pessoa jurídica registrado na base de dados.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PessoaJuridicaDto salvar(@RequestBody PessoaJuridicaDto dto) {
    return pessoaService.salvar(dto);
  }

  /**
   * Registra várias pessoas jurídicas na base de dados.
   *
   * @param dtos Uma lista de pessoas jurídicas para serem registradas.
   * @return A lista das pessoas jurídicas registradas.
   */
  @PostMapping("/varias")
  @ResponseStatus(HttpStatus.CREATED)
  public List<PessoaJuridicaDto> salvarTodos(@RequestBody List<PessoaJuridicaDto> dtos) {
    return pessoaService.salvarTodos(dtos);
  }

  /**
   * Retorna todas as pessoas jurídicas registradas.
   *
   * @return Uma lista de todas as pessoas jurídicas registradas.
   */
  @GetMapping
  public List<? extends PessoaJuridicaDto> buscarTodos() {
    return pessoaJuridicaService.buscarTodos();
  }

  /**
   * Busca uma pessoa jurídica por seu id na base de dados.
   *
   * @param id O id da pessoa jurídica a ser buscada.
   * @return A pessoa, caso esteja registrada.
   */
  @GetMapping("/{id}")
  public PessoaJuridicaDto buscarPorId(@PathVariable Long id) {
    return pessoaJuridicaService.buscarPorId(id);
  }

  /**
   * Atualiza uma pessoa jurídica com dados novos, sobrescrevendo-a.
   *
   * @param id O id da pessoa jurídica a ser sobrescrita.
   * @param dto A pessoa jurídica que sobrescreverá a antiga.
   * @return A pessoa jurídica entrada na base de dados.
   */
  @PutMapping("/{id}")
  public PessoaJuridicaDto atualizarCompletamente(
      @PathVariable Long id, @RequestBody PessoaJuridicaDto dto) {
    return pessoaJuridicaService.atualizarCompletamente(dto, id);
  }

  /**
   * Modifica os dados de uma pessoa jurídica sem sobrescrevê-la.
   *
   * @param id O id da pessoa jurídica a ser atualizada.
   * @param dto Um objeto contendo os dados a serem modificados.
   * @return Os dados da pessoa jurídica atualizada.
   */
  @PatchMapping("/{id}")
  public PessoaJuridicaDto atualizarParcialmente(
      @PathVariable Long id, @RequestBody PessoaJuridicaDto dto) {
    return pessoaJuridicaService.atualizarParcialmente(dto, id);
  }

  /**
   * Deleta uma pessoa jurídica do banco de dados por meio de seu id.
   *
   * @param id O id da pessoa jurídica a ser deletada.
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletarPorId(@PathVariable Long id) {
    pessoaService.deletarPorId(id);
  }
}
