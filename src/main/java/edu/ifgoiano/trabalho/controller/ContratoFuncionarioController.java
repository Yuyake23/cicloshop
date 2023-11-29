package edu.ifgoiano.trabalho.controller;

import edu.ifgoiano.trabalho.dto.ContratoFuncionarioDto;
import edu.ifgoiano.trabalho.service.ContratoFuncionarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/contrato_funcionario")
public class ContratoFuncionarioController {

  @Autowired private ContratoFuncionarioService contratoFuncionarioService;

  /**
   * Registra um funcionário na base de dados.
   *
   * @param dto Um objeto representando o funcionário a ser registrado.
   * @return O funcionário registrado na base de dados.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ContratoFuncionarioDto salvar(@RequestBody ContratoFuncionarioDto dto) {
    return contratoFuncionarioService.salvar(dto);
  }

  /**
   * Registra vários funcionários na base de dados.
   *
   * @param dtos Uma lista de funcionários para serem registrados.
   * @return A lista dos funcionários registrados.
   */
  @PostMapping("/varias")
  @ResponseStatus(HttpStatus.CREATED)
  public List<ContratoFuncionarioDto> salvarTodos(@RequestBody List<ContratoFuncionarioDto> dtos) {
    return contratoFuncionarioService.salvarTodos(dtos);
  }

  /**
   * Retorna todos os funcionários registrados.
   *
   * @return Uma lista de todos os funcionários registrados.
   */
  @GetMapping
  public List<ContratoFuncionarioDto> buscarTodos() {
    return contratoFuncionarioService.buscarTodos();
  }

  /**
   * Busca um funcionário por seu id na base de dados.
   *
   * @param id O id do funcionário a ser buscado.
   * @return O funcionário, caso esteja registrado.
   */
  @GetMapping("/{id}")
  public ContratoFuncionarioDto buscarPorId(@PathVariable Long id) {
    return contratoFuncionarioService.buscarPorId(id);
  }

  /**
   * Atualiza um funcionário com dados novos, sobrescrevendo-o.
   *
   * @param id O id do funcionário a ser sobrescrito.
   * @param dto O funcionário que sobrescreverá o antigo.
   * @return O funcionário que foi entrado na base de dados.
   */
  @PutMapping("/{id}")
  public ContratoFuncionarioDto atualizarCompletamente(
      @PathVariable Long id, @RequestBody ContratoFuncionarioDto dto) {
    return contratoFuncionarioService.atualizarCompletamente(dto, id);
  }

  /**
   * Modifica os dados de um funcionário sem sobrescrevê-lo.
   *
   * @param id O id do funcionário a ser atualizado.
   * @param dto Um objeto contendo os dados a serem modificados.
   * @return Os dados do funcionário que foi atualizado.
   */
  @PatchMapping("/{id}")
  public ContratoFuncionarioDto atualizarParcialmente(
      @PathVariable Long id, @RequestBody ContratoFuncionarioDto dto) {
    return contratoFuncionarioService.atualizarParcialmente(dto, id);
  }

  /**
   * Deleta um funcionário do banco de dados por meio de seu id.
   *
   * @param id O id do funcionário a ser deletado.
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletarPorId(@PathVariable Long id) {
    contratoFuncionarioService.deletarPorId(id);
  }
}
