package edu.ifgoiano.trabalho.controller;

import edu.ifgoiano.trabalho.dto.BicicletaDto;
import edu.ifgoiano.trabalho.service.ArquivoService;
import edu.ifgoiano.trabalho.service.BicicletaService;
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
@RequestMapping("/v1/bicicleta")
public class BicicletaController {

  @Autowired private ProdutoService produtoService;
  @Autowired private BicicletaService bicicletaService;
  @Autowired private ArquivoService arquivoService;

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
   * Busca a imagem de uma bicicleta pelo id da bicicleta.
   *
   * @param id O id da bicicleta.
   * @return A imagem da bicicleta.
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
   * Salva a imagem de uma bicicleta.
   *
   * @param arquivo O arquivo da imagem.
   * @param id O id da bicicleta.
   * @return O link para recuperar a imagem da bicicleta.
   */
  @PostMapping("/{id}/imagem")
  @ResponseStatus(HttpStatus.CREATED)
  public Link salvarImagem(@RequestParam MultipartFile arquivo, @PathVariable Long id) {
    arquivoService.salvarArquivo(arquivo, id);

    Link link = linkTo(
        methodOn(BicicletaController.class).buscarImagem(id, null))
        .withRel("imagem")
        .withType("GET");;

    return link;
  }
  
  /**
   * Deleta a imagem de uma bicicleta caso exista.
   *
   * @param id O id da bicicleta que deve ter a imagem deletada.
   */
  @DeleteMapping("/{id}/imagem")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasAuthority('ADMINISTRADOR')")
  public void deletarImagem(@PathVariable Long id) {
    arquivoService.deletarArquivoSeExistir(id);
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
