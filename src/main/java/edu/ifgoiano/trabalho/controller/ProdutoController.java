package edu.ifgoiano.trabalho.controller;

import edu.ifgoiano.trabalho.dto.PessoaDto;
import edu.ifgoiano.trabalho.dto.ProdutoDto;
import edu.ifgoiano.trabalho.service.ArquivoService;
import edu.ifgoiano.trabalho.service.PessoaService;
import edu.ifgoiano.trabalho.service.ProdutoService;
import jakarta.servlet.http.HttpServletRequest;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/produto")
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;
  @Autowired
  private PessoaService pessoaService;
  @Autowired
  private ArquivoService arquivoService;

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
   * Busca a imagem de um produto pelo id do produto.
   *
   * @param id O id do produto.
   * @return A imagem do produto.
   */
  @GetMapping("/{id}/imagem")
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public ResponseEntity<Resource> buscarImagem(@PathVariable Long id, HttpServletRequest request) {
    Resource resource = arquivoService.buscarArquivo(id);
    
    return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
        .header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
  }

  /**
   * Salva a imagem de um produto.
   *
   * @param arquivo O arquivo da imagem.
   * @param id O id do produto.
   * @return O link para recuperar a imagem do produto.
   */
  @PostMapping("/{id}/imagem")
  @ResponseStatus(HttpStatus.CREATED)
  public Link salvarImagem(@RequestParam MultipartFile arquivo, @PathVariable Long id) {
    arquivoService.salvarArquivo(arquivo, id);

    Link link = linkTo(
        methodOn(ProdutoController.class).buscarImagem(id, null))
        .withRel("imagem")
        .withType("GET");

    return link;
  }

  /**
   * Deleta a imagem de um produto caso exista.
   *
   * @param id O id do produto que deve ter a imagem deletada.
   */
  @DeleteMapping("/{id}/imagem")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasAuthority('ADMINISTRADOR')")
  public void deletarImagem(@PathVariable Long id) {
    arquivoService.deletarArquivoSeExistir(id);
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
