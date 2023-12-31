package edu.ifgoiano.trabalho.controller;

import edu.ifgoiano.trabalho.dto.DadosBancariosDto;
import edu.ifgoiano.trabalho.service.DadosBancariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/dados_bancarios")
public class DadosBancariosController {

  @Autowired private DadosBancariosService dadosBancariosService;

  /**
   * Registra os dados bancários de um funcionário na base de dados.
   *
   * @param dto Um objeto representando os dados bancários a serem registrado.
   * @return Os dados bancários registrados na base de dados.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasAuthority('ADMINISTRADOR')")
  public DadosBancariosDto salvar(@RequestBody DadosBancariosDto dto) {
    return dadosBancariosService.salvar(dto);
  }

  /**
   * Registra vários dados bancários na base de dados.
   *
   * @param dtos Uma lista de dados bancários para serem registrados.
   * @return A lista dos dados bancários registrados.
   */
  @PostMapping("/varias")
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasAuthority('ADMINISTRADOR')")
  public Iterable<DadosBancariosDto> salvarTodos(@RequestBody Iterable<DadosBancariosDto> dtos) {
    return dadosBancariosService.salvarTodos(dtos);
  }

  /**
   * Retorna todos os dados bancários registrados.
   *
   * @return Uma lista de todos os dados bancários registrados.
   */
  @GetMapping
  @PreAuthorize("hasAuthority('ADMINISTRADOR')")
  public Iterable<DadosBancariosDto> buscarTodos() {
    return dadosBancariosService.buscarTodos();
  }

  /**
   * Busca dados bancários por seu id na base de dados.
   *
   * @param id O id dos dados bancários a serem buscados.
   * @return Os dados bancários, caso estejam registrados.
   */
  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMINISTRADOR')")
  public DadosBancariosDto buscarPorId(@PathVariable Long id) {
    return dadosBancariosService.buscarPorId(id);
  }

  /**
   * Atualiza dados bancários com dados novos, sobrescrevendo-os.
   *
   * @param id O id dos dados bancários a serem sobrescritos.
   * @param dto Os dados bancários que sobrescreverão os antigos.
   * @return Os dados bancários entrados na base de dados.
   */
  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMINISTRADOR')")
  public DadosBancariosDto atualizarCompletamente(
      @PathVariable Long id, @RequestBody DadosBancariosDto dto) {
    return dadosBancariosService.atualizarCompletamente(dto, id);
  }

  /**
   * Modifica os dados de dados bancários sem sobrescrevê-los.
   *
   * @param id O id dos dados bancários a serem atualizados.
   * @param dto Um objeto contendo os dados a serem modificados.
   * @return Os dados bancários atualizados.
   */
  @PatchMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMINISTRADOR')")
  public DadosBancariosDto atualizarParcialmente(
      @PathVariable Long id, @RequestBody DadosBancariosDto dto) {
    return dadosBancariosService.atualizarParcialmente(dto, id);
  }

  /**
   * Deleta dados bancários do banco de dados por meio de seu id.
   *
   * @param id O id dos dados bancários a serem deletados.
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasAuthority('ADMINISTRADOR')")
  public void deletarPorId(@PathVariable Long id) {
    dadosBancariosService.deletarPorId(id);
  }
}
