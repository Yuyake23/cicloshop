package edu.ifgoiano.trabalho.controller;

import edu.ifgoiano.trabalho.dto.BicicletaDto;
import edu.ifgoiano.trabalho.service.BicicletaService;
import edu.ifgoiano.trabalho.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/bicicleta")
public class BicicletaController {

  @Autowired private ProdutoService produtoService;
  @Autowired private BicicletaService bicicletaService;

  /**
   * Registra uma bicicleta na base de dados.
   *
   * @param dto Um objeto representando a bicicleta a ser salva. O {@link BicicletaDto#id id} da
   *     bike é opcional.
   * @return A bicicleta salva.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public BicicletaDto salvar(@RequestBody BicicletaDto dto) {
    return produtoService.salvar(dto);
  }

  /**
   * Registra várias bicicletas na base de dados.
   *
   * @param dtos Uma lista iterável de bicicletas.
   * @return A lista de bicicletas salvas.
   */
  @PostMapping("/varias")
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public Iterable<BicicletaDto> salvarTodos(@RequestBody Iterable<BicicletaDto> dtos) {
    return produtoService.salvarTodos(dtos);
  }

  /**
   * Busca todas as bicicletas na base de dados.
   *
   * @return Todas as bicicletas na base de dados da oficina.
   */
  @GetMapping
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public Iterable<BicicletaDto> buscarTodos() {
    return bicicletaService.buscarTodos();
  }

  /**
   * Busca bicicletas pelo id na base de dados.
   *
   * @param id O id da bicicleta a ser buscada.
   * @return A bicicleta buscada.
   */
  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public BicicletaDto buscarPorId(@PathVariable Long id) {
    return bicicletaService.buscarPorId(id);
  }

  /**
   * Atualiza uma bicicleta no banco de dados, sobrescrevendo-a com os dados novos.
   *
   * @param id O id da bicicleta a ser sobrescrita.
   * @param dto A bicicleta nova a ser entrada na base de dados.
   * @return A bicicleta que foi colocada na base de dados.
   */
  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public BicicletaDto atualizarCompletamente(@PathVariable Long id, @RequestBody BicicletaDto dto) {
    return bicicletaService.atualizarCompletamente(dto, id);
  }

  /**
   * Modifica os atributos de uma bicicleta na base de dados sem sobrescrevê-la.
   *
   * @param id O id da bicicleta a ser modificada.
   * @param dto Um objeto contendo os dados novos.
   * @return A bicicleta com os dados novos.
   */
  @PatchMapping("/{id}")
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public BicicletaDto atualizarParcialmente(@PathVariable Long id, @RequestBody BicicletaDto dto) {
    return bicicletaService.atualizarParcialmente(dto, id);
  }

  /**
   * Deleta uma bicicleta por seu id.
   *
   * @param id O id da bicicleta a ser deletada.
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public void deletarPorId(@PathVariable Long id) {
    produtoService.deletarPorId(id);
  }
}
