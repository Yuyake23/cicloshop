package edu.ifgoiano.trabalho.controller;

import edu.ifgoiano.trabalho.dto.PecaDto;
import edu.ifgoiano.trabalho.service.ArquivoService;
import edu.ifgoiano.trabalho.service.PecaService;
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
@RequestMapping("/v1/peca")
public class PecaController {

  @Autowired private ProdutoService produtoService;
  @Autowired private PecaService pecaService;
  @Autowired private ArquivoService arquivoService;

  /**
   * Registra uma peça na base de dados.
   *
   * @param dto Um objeto representando a peça a ser registrada.
   * @return A peça registrado na base de dados.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public PecaDto salvar(@RequestBody PecaDto dto) {
    return produtoService.salvar(dto);
  }

  /**
   * Registra várias peças na base de dados.
   *
   * @param dtos Uma lista de peças para serem registradas.
   * @return A lista das peças registradas.
   */
  @PostMapping("/varias")
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public Iterable<PecaDto> salvarTodos(@RequestBody Iterable<PecaDto> dtos) {
    return produtoService.salvarTodos(dtos);
  }

  /**
   * Retorna todas as peças registradas.
   *
   * @return Uma lista de todas as peças registradas.
   */
  @GetMapping
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public Iterable<PecaDto> buscarTodos() {
    return pecaService.buscarTodos();
  }

  /**
   * Busca uma peça por seu id na base de dados.
   *
   * @param id O id da peça a ser buscada.
   * @return A peça, caso esteja registrada.
   */
  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public PecaDto buscarPorId(@PathVariable Long id) {
    return pecaService.buscarPorId(id);
  }
  
  /**
   * Atualiza uma peça com dados novos, sobrescrevendo-a.
   *
   * @param id O id da peça a ser sobrescrita.
   * @param dto A peça que sobrescreverá a antiga.
   * @return A peça entrada na base de dados.
   */
  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public PecaDto atualizarCompletamente(@PathVariable Long id, @RequestBody PecaDto dto) {
    return pecaService.atualizarCompletamente(dto, id);
  }

  /**
   * Modifica os dados de uma peça sem sobrescrevê-la.
   *
   * @param id O id da peça a ser atualizada.
   * @param dto Um objeto contendo os dados a serem modificados.
   * @return Os dados da peça que foi atualizada.
   */
  @PatchMapping("/{id}")
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public PecaDto atualizarParcialmente(@PathVariable Long id, @RequestBody PecaDto dto) {
    return pecaService.atualizarParcialmente(dto, id);
  }
  
  /**
   * Busca a imagem de uma peça pelo id da peça.
   *
   * @param id O id da peça.
   * @return A imagem da peça.
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
   * Salva a imagem de uma peça.
   *
   * @param arquivo O arquivo da imagem.
   * @param id O id da peça.
   * @return O link para recuperar a imagem da peça.
   */
  @PostMapping("/{id}/imagem")
  @ResponseStatus(HttpStatus.CREATED)
  public Link salvarImagem(@RequestParam MultipartFile arquivo, @PathVariable Long id) {
    arquivoService.salvarArquivo(arquivo, id);

    Link link = linkTo(
        methodOn(PecaController.class).buscarImagem(id, null))
        .withRel("imagem")
        .withType("GET");

    return link;
  }
  
  /**
   * Deleta a imagem de uma peça caso exista.
   *
   * @param id O id da peça que deve ter a imagem deletada.
   */
  @DeleteMapping("/{id}/imagem")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasAuthority('ADMINISTRADOR')")
  public void deletarImagem(@PathVariable Long id) {
    arquivoService.deletarArquivoSeExistir(id);
  }
  
  /**
   * Deleta uma peça do banco de dados por meio de seu id.
   *
   * @param id O id da peça a ser deletada.
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasAuthority('FUNCIONARIO')")
  public void deletarPorId(@PathVariable Long id) {
    produtoService.deletarPorId(id);
  }
}
