package edu.ifgoiano.trabalho.controller;

import edu.ifgoiano.trabalho.dto.PrecoProdutoDto;
import edu.ifgoiano.trabalho.service.PrecoProdutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/preco_produto")
public class PrecoProdutoController {

  @Autowired private PrecoProdutoService precoProdutoService;

  /**
   * Registra um preço de produto na base de dados.
   *
   * @param dto Um objeto representando o preço de produto a ser registrado.
   * @return O preço de produto registrado na base de dados.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PrecoProdutoDto salvar(@RequestBody PrecoProdutoDto dto) {
    return precoProdutoService.salvar(dto);
  }

  /**
   * Registra vários preços de produtos na base de dados.
   *
   * @param dtos Uma lista de preço de produtos para serem registrados.
   * @return A lista dos preços de produtos registrados.
   */
  @PostMapping("/varias")
  @ResponseStatus(HttpStatus.CREATED)
  public List<PrecoProdutoDto> salvarTodos(@RequestBody List<PrecoProdutoDto> dtos) {
    return precoProdutoService.salvarTodos(dtos);
  }

  /**
   * Retorna todos os preços de produtos registrados.
   *
   * @return Uma lista de todos os preços de produtos registrados.
   */
  @GetMapping
  public List<PrecoProdutoDto> buscarTodos() {
    return precoProdutoService.buscarTodos();
  }

  /**
   * Busca um preço de produto por seu id na base de dados.
   *
   * @param id O id do preço de produto a ser buscado.
   * @return O preço de produto, caso esteja registrado.
   */
  @GetMapping("/{id}")
  public PrecoProdutoDto buscarPorId(@PathVariable Long id) {
    return precoProdutoService.buscarPorId(id);
  }

  /**
   * Atualiza um preço de produto com dados novos, sobrescrevendo-o.
   *
   * @param id O id do preço de produto a ser sobrescrito.
   * @param dto O preço de produto que sobrescreverá o antigo.
   * @return O preço de produto entrado na base de dados.
   */
  @PutMapping("/{id}")
  public PrecoProdutoDto atualizarCompletamente(
      @PathVariable Long id, @RequestBody PrecoProdutoDto dto) {
    return precoProdutoService.atualizarCompletamente(dto, id);
  }

  /**
   * Modifica os dados de um preço de produto sem sobrescrevê-lo.
   *
   * @param id O id do preço de produto a ser atualizado.
   * @param dto Um objeto contendo os dados a serem modificados.
   * @return Os dados do preço de produto atualizado.
   */
  @PatchMapping("/{id}")
  public PrecoProdutoDto atualizarParcialmente(
      @PathVariable Long id, @RequestBody PrecoProdutoDto dto) {
    return precoProdutoService.atualizarParcialmente(dto, id);
  }

  /**
   * Deleta um preço de produto do banco de dados por meio de seu id.
   *
   * @param id O id do preço de produto a ser deletado.
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletarPorId(@PathVariable Long id) {
    precoProdutoService.deletarPorId(id);
  }
}
