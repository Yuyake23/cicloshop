package edu.ifgoiano.trabalho.controller;

import edu.ifgoiano.trabalho.dto.FornecedorDto;
import edu.ifgoiano.trabalho.service.FornecedorService;
import edu.ifgoiano.trabalho.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/fornecedor")
public class FornecedorController {

  @Autowired private PessoaService pessoaService;
  @Autowired private FornecedorService fornecedorService;

  /**
   * Registra um fornecedor na base de dados.
   *
   * @param dto Um objeto representando o fornecedor a ser registrado.
   * @return O fornecedor registrado na base de dados.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FornecedorDto salvar(@RequestBody FornecedorDto dto) {
    return pessoaService.salvar(dto);
  }

  /**
   * Registra vários fornecedores na base de dados.
   *
   * @param dtos Uma lista de fornecedores para serem registrados.
   * @return A lista dos fornecedores registrados.
   */
  @PostMapping("/varias")
  @ResponseStatus(HttpStatus.CREATED)
  public Iterable<FornecedorDto> salvarTodos(@RequestBody Iterable<FornecedorDto> dtos) {
    return pessoaService.salvarTodos(dtos);
  }

  /**
   * Retorna todos os fornecedores registrados.
   *
   * @return Uma lista de todos os fornecedores registrados.
   */
  @GetMapping
  public Iterable<FornecedorDto> buscarTodos() {
    return fornecedorService.buscarTodos();
  }

  /**
   * Busca um fornecedor por seu id na base de dados.
   *
   * @param id O id do fornecedor a ser buscado.
   * @return O fornecedor, caso esteja registrado.
   */
  @GetMapping("/{id}")
  public FornecedorDto buscarPorId(@PathVariable Long id) {
    return fornecedorService.buscarPorId(id);
  }

  /**
   * Atualiza um fornecedor com dados novos, sobrescrevendo-o.
   *
   * @param id O id do fornecedor a ser sobrescrito.
   * @param dto O fornecedor que sobrescreverá o antigo.
   * @return O fornecedor que foi entrado na base de dados.
   */
  @PutMapping("/{id}")
  public FornecedorDto atualizarCompletamente(
      @PathVariable Long id, @RequestBody FornecedorDto dto) {
    return fornecedorService.atualizarCompletamente(dto, id);
  }

  /**
   * Modifica os dados de um fornecedor sem sobrescrevê-lo.
   *
   * @param id O id do fornecedor a ser atualizado.
   * @param dto Um objeto contendo os dados a serem modificados.
   * @return Os dados do fornecedor que foi atualizado.
   */
  @PatchMapping("/{id}")
  public FornecedorDto atualizarParcialmente(
      @PathVariable Long id, @RequestBody FornecedorDto dto) {
    return fornecedorService.atualizarParcialmente(dto, id);
  }

  /**
   * Deleta um fornecedor do banco de dados por meio de seu id.
   *
   * @param id O id do fornecedor a ser deletado.
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletarPorId(@PathVariable Long id) {
    pessoaService.deletarPorId(id);
  }
}
