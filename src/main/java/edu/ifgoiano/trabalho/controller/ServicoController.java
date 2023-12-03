package edu.ifgoiano.trabalho.controller;

import edu.ifgoiano.trabalho.dto.ServicoDto;
import edu.ifgoiano.trabalho.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/servico")
public class ServicoController {

  @Autowired private ServicoService servicoService;

  /**
   * Registra um serviço na base de dados.
   *
   * @param dto Um objeto representando o serviço a ser registrado.
   * @return O serviço registrado na base de dados.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public ServicoDto salvar(@RequestBody ServicoDto dto) {
    return servicoService.salvar(dto);
  }

  /**
   * Registra vários serviços na base de dados.
   *
   * @param dtos Uma lista de serviços para serem registrados.
   * @return A lista dos serviços registrados.
   */
  @PostMapping("/varias")
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public Iterable<ServicoDto> salvarTodos(@RequestBody Iterable<ServicoDto> dtos) {
    return servicoService.salvarTodos(dtos);
  }

  /**
   * Retorna todos os serviços registrados.
   *
   * @return Uma lista de todos os serviços registrados.
   */
  @GetMapping
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public Iterable<ServicoDto> buscarTodos() {
    return servicoService.buscarTodos();
  }

  /**
   * Busca um serviço por seu id na base de dados.
   *
   * @param id O id do serviço a ser buscado.
   * @return O serviço, caso esteja registrado.
   */
  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public ServicoDto buscarPorId(@PathVariable Long id) {
    return servicoService.buscarPorId(id);
  }

  /**
   * Atualiza um serviço com dados novos, sobrescrevendo-o.
   *
   * @param id O id do serviço a ser sobrescrito.
   * @param dto O serviço que sobrescreverá o antigo.
   * @return O serviço que foi entrado na base de dados.
   */
  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public ServicoDto atualizarCompletamente(@PathVariable Long id, @RequestBody ServicoDto dto) {
    return servicoService.atualizarCompletamente(dto, id);
  }

  /**
   * Modifica os dados de um serviço sem sobrescrevê-lo.
   *
   * @param id O id do serviço a ser atualizado.
   * @param dto Um objeto contendo os dados a serem modificados.
   * @return Os dados do serviço que foi atualizado.
   */
  @PatchMapping("/{id}")
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public ServicoDto atualizarParcialmente(@PathVariable Long id, @RequestBody ServicoDto dto) {
    return servicoService.atualizarParcialmente(dto, id);
  }

  /**
   * Deleta um serviço do banco de dados por meio de seu id.
   *
   * @param id O id do serviço a ser deletado.
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasAuthority('ADMINISTRADOR')")
  public void deletarPorId(@PathVariable Long id) {
    servicoService.deletarPorId(id);
  }
}
