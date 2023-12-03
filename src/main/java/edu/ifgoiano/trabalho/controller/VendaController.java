package edu.ifgoiano.trabalho.controller;

import edu.ifgoiano.trabalho.dto.VendaDto;
import edu.ifgoiano.trabalho.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/venda")
public class VendaController {

  @Autowired private VendaService vendaService;

  /**
   * Registra uma venda na base de dados.
   *
   * @param dto Um objeto representando a venda a ser registrada.
   * @return A venda registrado na base de dados.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public VendaDto salvar(@RequestBody VendaDto dto) {
    return vendaService.salvar(dto);
  }

  /**
   * Registra várias vendas na base de dados.
   *
   * @param dtos Uma lista de vendas para serem registradas.
   * @return A lista das vendas registradas.
   */
  @PostMapping("/varias")
  @ResponseStatus(HttpStatus.CREATED)
  public Iterable<VendaDto> salvarTodos(@RequestBody Iterable<VendaDto> dtos) {
    return vendaService.salvarTodos(dtos);
  }

  /**
   * Retorna todas as vendas registradas.
   *
   * @return Uma lista de todas as vendas registradas.
   */
  @GetMapping
  public Iterable<VendaDto> buscarTodos() {
    return vendaService.buscarTodos();
  }

  /**
   * Busca uma venda por seu id na base de dados.
   *
   * @param id O id da venda a ser buscada.
   * @return A venda, caso esteja registrada.
   */
  @GetMapping("/{id}")
  public VendaDto buscarPorId(@PathVariable Long id) {
    return vendaService.buscarPorId(id);
  }

  /**
   * Atualiza uma venda com dados novos, sobrescrevendo-a.
   *
   * @param id O id da venda a ser sobrescrita.
   * @param dto A venda que sobrescreverá a antiga.
   * @return A venda entrada na base de dados.
   */
  @PutMapping("/{id}")
  public VendaDto atualizarCompletamente(@PathVariable Long id, @RequestBody VendaDto dto) {
    return vendaService.atualizarCompletamente(dto, id);
  }

  /**
   * Modifica os dados de uma venda sem sobrescrevê-la.
   *
   * @param id O id da venda a ser atualizada.
   * @param dto Um objeto contendo os dados a serem modificados.
   * @return Os dados da venda que foi atualizada.
   */
  @PatchMapping("/{id}")
  public VendaDto atualizarParcialmente(@PathVariable Long id, @RequestBody VendaDto dto) {
    return vendaService.atualizarParcialmente(dto, id);
  }

  /**
   * Deleta uma venda do banco de dados por meio de seu id.
   *
   * @param id O id da venda a ser deletada.
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletarPorId(@PathVariable Long id) {
    vendaService.deletarPorId(id);
  }
}
