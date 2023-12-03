package edu.ifgoiano.trabalho.controller;

import edu.ifgoiano.trabalho.dto.PessoaDto;
import edu.ifgoiano.trabalho.dto.ProdutoDto;
import edu.ifgoiano.trabalho.service.PessoaService;
import edu.ifgoiano.trabalho.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/pessoa")
public class PessoaController {

  @Autowired private PessoaService pessoaService;
  @Autowired private ProdutoService produtoService;

  /**
   * Retorna todas as pessoas registradas.
   *
   * @return Uma lista de todas as pessoas registradas.
   */
  @GetMapping
  public Iterable<? extends PessoaDto> buscarTodos() {
    return pessoaService.buscarTodos();
  }

  /**
   * Busca uma pessoa por seu id na base de dados.
   *
   * @param id O id da pessoa a ser buscada.
   * @return A pessoa, caso esteja registrada.
   */
  @GetMapping("/{id}")
  public PessoaDto buscarPorId(@PathVariable Long id) {
    return pessoaService.buscarPorId(id);
  }

  /**
   * Busca todos os produtos pertencentes a uma pessoa representada por certo id.
   *
   * @param id O id do dono dos produtos.
   * @return Uma lista contendo os produtos da pessoa.
   */
  @GetMapping("/{id}/produtos")
  public Iterable<? extends ProdutoDto> buscarPorDono(@PathVariable Long id) {
    return produtoService.buscarPorDono(id);
  }

  /**
   * Deleta uma pessoa do banco de dados por meio de seu id.
   *
   * @param id O id da pessoa a ser deletada.
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletarPorId(@PathVariable Long id) {
    produtoService.deletarPorId(id);
  }
}
