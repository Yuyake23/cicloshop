package edu.ifgoiano.trabalho.controller;

import edu.ifgoiano.trabalho.dto.PessoaDto;
import edu.ifgoiano.trabalho.dto.ProdutoDto;
import edu.ifgoiano.trabalho.service.PessoaService;
import edu.ifgoiano.trabalho.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/produto")
public class ProdutoController {

  @Autowired private ProdutoService produtoService;
  @Autowired private PessoaService pessoaService;

  /**
   * Retorna todos os produtos registrados.
   *
   * @return Uma lista de todos os produtos registrados.
   */
  @GetMapping
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public Iterable<? extends ProdutoDto> buscarTodos() {
    return produtoService.buscarTodos();
  }

  /**
   * Busca um produto por seu id na base de dados.
   *
   * @param id O id do produto a ser buscado.
   * @return O produto, caso esteja registrado.
   */
  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public ProdutoDto buscarPorId(@PathVariable Long id) {
    return produtoService.buscarPorId(id);
  }

  /**
   * Busca o dono de um produto por seu id.
   *
   * @param id O id do produto.
   * @return O dono do produto.
   */
  @GetMapping("/{id}/dono")
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public PessoaDto buscarDonoDoProdutoComId(@PathVariable Long id) {
    return pessoaService.buscarPorId(id);
  }

  /**
   * Deleta um produto do banco de dados por meio de seu id.
   *
   * @param id O id do produto a ser deletado.
   */
  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMINISTRADOR')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletarPorId(@PathVariable Long id) {
    produtoService.deletarPorId(id);
  }
}
