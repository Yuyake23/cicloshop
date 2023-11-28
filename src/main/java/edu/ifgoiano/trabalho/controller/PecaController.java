package edu.ifgoiano.trabalho.controller;

import edu.ifgoiano.trabalho.dto.PecaDto;
import edu.ifgoiano.trabalho.service.PecaService;
import edu.ifgoiano.trabalho.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/peca")
public class PecaController {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private PecaService pecaService;

    /**
     * Registra uma peça na base de dados.
     *
     * @param dto Um objeto representando a peça a ser registrada.
     * @return A peça registrado na base de dados.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
    public Iterable<PecaDto> salvarTodos(@RequestBody Iterable<PecaDto> dtos) {
        return produtoService.salvarTodos(dtos);
    }

    /**
     * Retorna todas as peças registradas.
     *
     * @return Uma lista de todas as peças registradas.
     */
    @GetMapping
    public List<PecaDto> buscarTodos() {
        return pecaService.buscarTodos();
    }

    /**
     * Busca uma peça por seu id na base de dados.
     *
     * @param id O id da peça a ser buscada.
     * @return A peça, caso esteja registrada.
     */
    @GetMapping("/{id}")
    public PecaDto buscarPorId(@PathVariable Long id) {
        return pecaService.buscarPorId(id);
    }

    /**
     * Atualiza uma peça com dados novos, sobrescrevendo-a.
     *
     * @param id  O id da peça a ser sobrescrita.
     * @param dto A peça que sobrescreverá a antiga.
     * @return A peça entrada na base de dados.
     */
    @PutMapping("/{id}")
    public PecaDto atualizarCompletamente(@PathVariable Long id, @RequestBody PecaDto dto) {
        return pecaService.atualizarCompletamente(dto, id);
    }

    /**
     * Modifica os dados de uma peça sem sobrescrevê-la.
     *
     * @param id  O id da peça a ser atualizada.
     * @param dto Um objeto contendo os dados a serem modificados.
     * @return Os dados da peça que foi atualizada.
     */
    @PatchMapping("/{id}")
    public PecaDto atualizarParcialmente(@PathVariable Long id, @RequestBody PecaDto dto) {
        return pecaService.atualizarParcialmente(dto, id);
    }

    /**
     * Deleta uma peça do banco de dados por meio de seu id.
     *
     * @param id O id da peça a ser deletada.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPorId(@PathVariable Long id) {
        produtoService.deletarPorId(id);
    }
}
