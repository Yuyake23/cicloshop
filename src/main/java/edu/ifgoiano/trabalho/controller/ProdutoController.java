package edu.ifgoiano.trabalho.controller;

import edu.ifgoiano.trabalho.dto.PessoaDto;
import edu.ifgoiano.trabalho.dto.ProdutoDto;
import edu.ifgoiano.trabalho.service.PessoaService;
import edu.ifgoiano.trabalho.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private PessoaService pessoaService;

    /**
     * Retorna todos os fornecedores registrados.
     *
     * @return Uma lista de todos os fornecedores registrados.
     */
    @GetMapping
    public Iterable<? extends ProdutoDto> buscarTodos() {
        return produtoService.buscarTodos();
    }

    /**
     * Busca um fornecedor por seu id na base de dados.
     *
     * @param id O id do fornecedor a ser buscado.
     * @return O fornecedor, caso esteja registrado.
     */
    @GetMapping("/{id}")
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
    public PessoaDto buscarDonoDoProdutoComId(@PathVariable Long id) {
        return pessoaService.buscarPorId(id);
    }

    /**
     * Deleta um fornecedor do banco de dados por meio de seu id.
     *
     * @param id O id do fornecedor a ser deletado.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPorId(@PathVariable Long id) {
        produtoService.deletarPorId(id);
    }

}
